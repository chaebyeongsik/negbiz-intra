/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userManage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.user.hist.UserHistService;
import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.intra.user.support.MenuSeCd;
import zesinc.intra.user.support.UserStatus;
import zesinc.intra.user.support.UserType;
import zesinc.intra.user.userManage.UserManageMapper;
import zesinc.intra.user.userManage.UserManageService;
import zesinc.intra.user.userManage.domain.UserManageVO;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.PasswdUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 27.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opUserManageService")
public class UserManageServiceImpl extends EgovAbstractServiceImpl implements UserManageService {

    @Resource(name = "opUserHistService")
    private UserHistService opUserHistService;

    @Resource(name = "opUserManageDao")
    private UserManageMapper opUserManageDao;

    @Override
    public Pager<UserManageVO> selectUserList(UserManageVO userManageVo) {

        List<UserManageVO> dataList = opUserManageDao.selectUserList(userManageVo);
        Integer totalNum = opUserManageDao.selectUserListCount(userManageVo);

        return new Pager<UserManageVO>(dataList, userManageVo, totalNum);
    }

    @Override
    public Integer selectDupCheckUserId(UserManageVO userManageVo) {

        return opUserManageDao.selectDupCheckUserId(userManageVo);
    }

    @Override
    public Integer insertIndvdlUser(UserManageVO userManageVo) {
        userManageVo.setUserTypeNm(UserType.INDVDL.getUserType());
        userManageVo.setUserSttsSn(UserStatus.JOIN.getUserStatus());
        // 비밀번호 암호화
        if(!Validate.isEmpty(userManageVo.getUserPswd())) {
            //userManageVo.setUserPswd(PasswdUtil.encodePasswd(userManageVo.getUserPswd()));
            userManageVo.setUserPswd(PasswdUtil.encode(userManageVo.getUserPswd()));
        }
        int key = opUserManageDao.insertIndvdlUser(userManageVo);
        UserManageVO infoVo = opUserManageDao.selectUserByUserKey(userManageVo);
        userManageVo.setUserId(infoVo.getUserId());
        opUserManageDao.insertIndvdlUserEtcInfo(userManageVo);
        return key;
    }

    @Override
    public UserManageVO selectIndvdlUserInfo(UserManageVO userManageVo) {
        return opUserManageDao.selectIndvdlUserInfo(userManageVo);
    }

    @Override
    public Integer updateIndvdlUser(UserManageVO userManageVo) {
        UserManageVO originalVo = opUserManageDao.selectIndvdlUserInfo(userManageVo);
        String changeContents = getIndvdlUserChangeContents(originalVo, userManageVo);

        int result = opUserManageDao.updateIndvdlUser(userManageVo);
        opUserManageDao.updateIndvdlUserEtcInfo(userManageVo);

        if(Validate.isNotEmpty(changeContents)) {
            // 로그
            UserHistVO userHistVo = new UserHistVO();
            userHistVo.setPicId(userManageVo.getMdfrId());
            userHistVo.setUserId(userManageVo.getUserId());
            userHistVo.setLogCn(userManageVo.getLogCn());
            userHistVo.setChgAftrCn(changeContents);
            userHistVo.setMenuSeCd(MenuSeCd.UPDATE.getMenuSeCd());
            userHistVo.setMenuNm("사용자상세정보-개인");
            opUserHistService.insertUserLog(userHistVo);
        }

        return result;
    }

    @Override
    public Integer insertEntrprsUser(UserManageVO userManageVo) {
        userManageVo.setUserTypeNm(UserType.ENTRPRS.getUserType());
        userManageVo.setUserSttsSn(UserStatus.JOIN.getUserStatus());
        // 비밀번호 암호화
        if(!Validate.isEmpty(userManageVo.getUserPswd())) {
            //userManageVo.setUserPswd(PasswdUtil.encodePasswd(userManageVo.getUserPswd()));
            userManageVo.setUserPswd(PasswdUtil.encode(userManageVo.getUserPswd()));
        }
        int key = opUserManageDao.insertEntrprsUser(userManageVo);
        UserManageVO infoVo = opUserManageDao.selectUserByUserKey(userManageVo);
        userManageVo.setUserId(infoVo.getUserId());
        opUserManageDao.insertEntrprsUserEtcInfo(userManageVo);
        return key;
    }

    @Override
    public UserManageVO selectEntrprsUserInfo(UserManageVO userManageVo) {
        return opUserManageDao.selectEntrprsUserInfo(userManageVo);
    }

    @Override
    public Integer updateEntrprsUser(UserManageVO userManageVo) {
        UserManageVO originalVo = opUserManageDao.selectEntrprsUserInfo(userManageVo);
        String changeContents = getEntrprsUserChangeContents(originalVo, userManageVo);

        int key = opUserManageDao.updateEntrprsUser(userManageVo); // 기업사용자 일반정보 수정
        opUserManageDao.updateEntrprsUserEtcInfo(userManageVo); // 기업사용자 기타정보 수정

        if(Validate.isNotEmpty(changeContents)) {
            // 로그
            UserHistVO userHistVo = new UserHistVO();
            userHistVo.setPicId(userManageVo.getMdfrId());
            userHistVo.setUserId(userManageVo.getUserId());
            userHistVo.setLogCn(userManageVo.getLogCn());
            userHistVo.setChgAftrCn(changeContents);
            userHistVo.setMenuSeCd(MenuSeCd.UPDATE.getMenuSeCd());
            userHistVo.setMenuNm("사용자상세정보-기업");
            opUserHistService.insertUserLog(userHistVo);
        }

        return key;
    }

    @Override
    public Integer deleteEntrprsUser(UserManageVO userManageVo) {
        int result = 0;

        String[] userIds = userManageVo.getUserId().split(",");
        String[] userTypes = userManageVo.getUserTypeNm().split(",");

        for(int i = 0 ; i < userIds.length ; i++) {
            String userTy = userTypes[i];
            userManageVo.setUserId(userIds[i]);
            if(userTy.equals(UserType.INDVDL.getUserType())) {
                // 개인 사용자 삭제
                opUserManageDao.deleteIndvdlUserEtcInfo(userManageVo);
            } else {
                // 기업 사용자 삭제
                opUserManageDao.deleteEntrprsUserEtcInfo(userManageVo);
            }
            result += opUserManageDao.deleteUser(userManageVo);
        }

        return result;
    }

    /**
     * 개인 사용자 변경된 컨텐츠 항목
     *
     * @param originalVo
     * @param targetVo
     * @return
     */
    private String getIndvdlUserChangeContents(UserManageVO originalVo, UserManageVO targetVo) {
        StringBuffer changeContent = new StringBuffer();

        // 이메일
        if(!isEquals(originalVo.getEmlId(), targetVo.getEmlId()) || !isEquals(originalVo.getEmlSiteNm(), targetVo.getEmlSiteNm())) {
            changeContent.append(",[이메일]");
        }
        // 가입유형[★]
        if(!isEquals(originalVo.getJoinTypeSn(), targetVo.getJoinTypeSn())) {
            changeContent.append(",[가입유형]");
        }
        // 전화번호
        if(!isEquals(originalVo.getRgnTelno(), targetVo.getRgnTelno()) || !isEquals(originalVo.getTelofcTelno(), targetVo.getTelofcTelno())
            || !isEquals(originalVo.getIndivTelno(), targetVo.getIndivTelno())) {
            changeContent.append(",[전화번호]");
        }
        // 우편번호
        if(!isEquals(originalVo.getZip(), targetVo.getZip())) {
            changeContent.append(",[우편번호]");
        }
        // 주소
        if(!isEquals(originalVo.getUserAddr(), targetVo.getUserAddr()) || !isEquals(originalVo.getDaddr(), targetVo.getDaddr())) {
            changeContent.append(",[주소]");
        }
        // 관심항목명
        if(!isEquals(originalVo.getItrstArtclCn(), targetVo.getItrstArtclCn())) {
            changeContent.append(",[관심항목명]");
        }
        // 사용자등급코드목록
        if(!isEquals(originalVo.getUserGrdCdDsctn(), targetVo.getUserGrdCdDsctn())) {
            changeContent.append(",[사용자등급코드목록]");
        }
        // 사용자상태
        if(!isEquals(originalVo.getUserSttsSn(), targetVo.getUserSttsSn())) {
            changeContent.append(",[사용자상태]");
        }
        // 주민등록번호
        if(!isEquals(originalVo.getRrno(), targetVo.getRrno())) {
            changeContent.append(",[주민등록번호]");
        }
        // 휴대전화번호
        if(!isEquals(originalVo.getMblRgnTelno(), targetVo.getMblRgnTelno()) || !isEquals(originalVo.getMblTelofcTelno(), targetVo.getMblTelofcTelno())
            || !isEquals(originalVo.getMblIndivTelno(), targetVo.getMblIndivTelno())) {
            changeContent.append(",[휴대전화번호]");
        }
        // 생년월일
        if(!isEquals(originalVo.getBrdt(), targetVo.getBrdt()) || !isEquals(originalVo.getBrthYmdClsfSn(), targetVo.getBrthYmdClsfSn())) {
            changeContent.append(",[생년월일]");
        }
        // 성별
        if(!isEquals(originalVo.getGndrClsfSn(), targetVo.getGndrClsfSn())) {
            changeContent.append(",[성별]");
        }
        // 메일링서비스여부
        if(!isEquals(originalVo.getEmlRcptnAgreClsfSn(), targetVo.getEmlRcptnAgreClsfSn())) {
            changeContent.append(",[메일링서비스여부]");
        }
        // SMS수신여부
        if(!isEquals(originalVo.getSmsRcptnClsfSn(), targetVo.getSmsRcptnClsfSn())) {
            changeContent.append(",[SMS수신여부]");
        }
        // 최종학력
        if(!isEquals(originalVo.getLastAcbgNo(), targetVo.getLastAcbgNo())) {
            changeContent.append(",[최종학력]");
        }
        // 결혼여부
        if(!isEquals(originalVo.getMrgSeSn(), targetVo.getMrgSeSn())) {
            changeContent.append(",[결혼여부]");
        }
        // 직장명
        if(!isEquals(originalVo.getWrcNm(), targetVo.getWrcNm())) {
            changeContent.append(",[직장명]");
        }
        // 직책
        if(!isEquals(originalVo.getJbttlNm(), targetVo.getJbttlNm())) {
            changeContent.append(",[직책]");
        }
        // 직장우편번호
        if(!isEquals(originalVo.getWrcZip(), targetVo.getWrcZip())) {
            changeContent.append(",[직장우편번호]");
        }
        // 직장주소
        if(!isEquals(originalVo.getWrcAddr(), targetVo.getWrcAddr())
            || !isEquals(originalVo.getWrcDaddr(), targetVo.getWrcDaddr())) {
            changeContent.append(",[직장주소]");
        }
        // 직장전화번호
        if(!isEquals(originalVo.getWrcRgnTelno(), targetVo.getWrcRgnTelno()) || !isEquals(originalVo.getWrcTelofcTelno(), targetVo.getWrcTelofcTelno())
            || !isEquals(originalVo.getWrcIndivTelno(), targetVo.getWrcIndivTelno())) {
            changeContent.append(",[직장전화번호]");
        }

        String str = changeContent.toString();
        if(Validate.isNotEmpty(str))
            str = str.substring(1);

        return str;
    }

    /**
     * 기업 사용자 변경된 컨텐츠 항목
     *
     * @param originalVo
     * @param targetVo
     * @return
     */
    private String getEntrprsUserChangeContents(UserManageVO originalVo, UserManageVO targetVo) {
        StringBuffer changeContent = new StringBuffer();

        // 회사명
        if(!isEquals(originalVo.getUserNm(), targetVo.getUserNm())) {
            changeContent.append(",[회사명]");
        }
        // 이메일
        if(!isEquals(originalVo.getEmlId(), targetVo.getEmlId()) || !isEquals(originalVo.getEmlSiteNm(), targetVo.getEmlSiteNm())) {
            changeContent.append(",[이메일]");
        }
        // 전화번호
        if(!isEquals(originalVo.getRgnTelno(), targetVo.getRgnTelno()) || !isEquals(originalVo.getTelofcTelno(), targetVo.getTelofcTelno())
            || !isEquals(originalVo.getIndivTelno(), targetVo.getIndivTelno())) {
            changeContent.append(",[전화번호]");
        }
        // 우편번호
        if(!isEquals(originalVo.getZip(), targetVo.getZip())) {
            changeContent.append(",[우편번호]");
        }
        // 주소
        if(!isEquals(originalVo.getUserAddr(), targetVo.getUserAddr()) || !isEquals(originalVo.getDaddr(), targetVo.getDaddr())) {
            changeContent.append(",[주소]");
        }
        // 관심항목명
        if(!isEquals(originalVo.getItrstArtclCn(), targetVo.getItrstArtclCn())) {
            changeContent.append(",[관심항목명]");
        }
        // 사용자상태
        if(!isEquals(originalVo.getUserSttsSn(), targetVo.getUserSttsSn())) {
            changeContent.append(",[사용자상태]");
        }
        // 법인등록번호
        if(!isEquals(originalVo.getCrno(), targetVo.getCrno())) {
            changeContent.append(",[법인등록번호]");
        }
        // 사업자등록번호
        if(!isEquals(originalVo.getBrno(), targetVo.getBrno())) {
            changeContent.append(",[사업자등록번호]");
        }
        // 담당자명
        if(!isEquals(originalVo.getPicNm(), targetVo.getPicNm())) {
            changeContent.append(",[담당자명]");
        }
        // 부서명
        if(!isEquals(originalVo.getDeptNm(), targetVo.getDeptNm())) {
            changeContent.append(",[부서명]");
        }
        // 직책
        if(!isEquals(originalVo.getJbttlNm(), targetVo.getJbttlNm())) {
            changeContent.append(",[직책]");
        }
        // 휴대전화번호
        if(!isEquals(originalVo.getMblRgnTelno(), targetVo.getMblRgnTelno()) || !isEquals(originalVo.getMblTelofcTelno(), targetVo.getMblTelofcTelno())
            || !isEquals(originalVo.getMblIndivTelno(), targetVo.getMblIndivTelno())) {
            changeContent.append(",[휴대전화번호]");
        }
        // 부서전화번호
        if(!isEquals(originalVo.getDeptRgnTelno(), targetVo.getDeptRgnTelno()) || !isEquals(originalVo.getDeptTelofcTelno(), targetVo.getDeptTelofcTelno())
                || !isEquals(originalVo.getDeptIndivTelno(), targetVo.getDeptIndivTelno())) {
                changeContent.append(",[부서전화번호]");
        }
        // 팩스번호
        if(!isEquals(originalVo.getRgnFxno(), targetVo.getRgnFxno()) || !isEquals(originalVo.getTelofcFxno(), targetVo.getTelofcFxno())
            || !isEquals(originalVo.getIndivFxno(), targetVo.getIndivFxno())) {
            changeContent.append(",[팩스번호]");
        }
        // 대표자명[★]
        if(!isEquals(originalVo.getRprsvNm(), targetVo.getRprsvNm())) {
            changeContent.append(",[대표자명]");
        }

        String str = changeContent.toString();
        if(Validate.isNotEmpty(str))
            str = str.substring(1);

        return str;
    }

    /**
     * originalObj값과 targetObj값이 동일한지 비교
     *
     * @param originalObj
     * @param targetObj
     * @return
     */
    private boolean isEquals(Object originalObj, Object targetObj) {
        originalObj = originalObj != null ? originalObj.toString() : "";
        targetObj = targetObj != null ? targetObj.toString() : "";

        if(originalObj.equals(targetObj)) {
            return true;
        }

        return false;
    }

    @Override
    public String selectUserGrdCdDsctn(String userGrdCdId) {
        String[] userGrdCdDsctn = userGrdCdId.split(",");
        UserManageVO userManageVo = new UserManageVO();
        userManageVo.setUserGrdCdDsctns(userGrdCdDsctn);

        List<UserManageVO> userGradList = opUserManageDao.selectUserGrdCdDsctn(userManageVo);

        String userGrdNm = "";
        if(userGradList.size() > 0) {
            for(UserManageVO userVo : userGradList) {
                userGrdNm += "," + userVo.getUserGrdNm();
            }
            userGrdNm.substring(1);
        }
        return userGrdNm;
    }

}
