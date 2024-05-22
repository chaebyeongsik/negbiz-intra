/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.author.domain.AuthorVO;
import zesinc.intra.authorAsgn.domain.AuthorAsgnVO;
import zesinc.intra.mngr.MngrMapper;
import zesinc.intra.mngr.MngrService;
import zesinc.intra.mngr.change.ChangeService;
import zesinc.intra.mngr.domain.MngrVO;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.PasswdUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-06.    황신욱   최초작성
 *  2015-12-25.    방기배   버그수정
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opMngrService")
public class MngrServiceImpl extends EgovAbstractServiceImpl implements MngrService {

    @Resource(name = "opMngrDao")
    private MngrMapper opMngrDao;

    @Resource(name = "opChangeService")
    private ChangeService opChangeService;

    @Override
    public MngrVO selectMngr(MngrVO mngrVo) {

        MngrVO dataVo = opMngrDao.selectMngr(mngrVo);

        return dataVo;
    }

    @Override
    public Integer updateCrtfcAcnt(MngrVO mngrVo) {

        Integer updateCnt = opMngrDao.updateCrtfcAcnt(mngrVo);

        // 변경이력 등록
        opChangeService.updateChange(mngrVo);

        return updateCnt;
    }

    @Override
    public Pager<MngrVO> selectMngrPageList(MngrVO mngrVo) {

        List<MngrVO> dataList = opMngrDao.selectMngrList(mngrVo);
        Integer totalNum = opMngrDao.selectMngrListCount(mngrVo);

        return new Pager<MngrVO>(dataList, mngrVo, totalNum);
    }

    @Override
    public Integer insertMngr(MngrVO mngrVo) {

        // 비밀번호 암호화
        if(!Validate.isEmpty(mngrVo.getPicPswd())) {
            //mngrVo.setPicPswd(PasswdUtil.encodePasswd(mngrVo.getPicPswd()));
            mngrVo.setPicPswd(PasswdUtil.encode(mngrVo.getPicPswd()));
        }

        Integer insertCnt = opMngrDao.insertMngr(mngrVo);

        // 변경이력 등록
        opChangeService.insertChange(mngrVo);

        return insertCnt;
    }

    @Override
    public Integer updateMngr(MngrVO mngrVo) {

        // 비밀번호 암호화
        if(Validate.isNotEmpty(mngrVo.getChangePasswd())) {
            //mngrVo.setChangePasswd(PasswdUtil.encodePasswd(mngrVo.getChangePasswd()));
            mngrVo.setChangePasswd(PasswdUtil.encode(mngrVo.getChangePasswd()));
        }

        // 부서 변경 시  기존 권한삭제 후 기본권한 등록
        MngrVO dataVo = selectMngr(mngrVo);
        if(!dataVo.getDeptCdId().equals(mngrVo.getDeptCdId())) {
            mngrVo.setPicId(dataVo.getPicId());
            opMngrDao.deleteAuth(mngrVo);

            // 기본권한 등록 - 보류
            /*mngrVo.setAuthrtCdId("basic");
            mngrVo.setRgtrId(mngrVo.getMdfrId());
            opMngrDao.insertAuth(mngrVo);*/
        }

        Integer updateCnt = opMngrDao.updateMngr(mngrVo);

        // 변경이력 등록
        opChangeService.updateChange(mngrVo);

        return updateCnt;
    }

    @Override
    public Integer updateCharger(MngrVO mngrVo) {

        // 비밀번호 암호화
        if(Validate.isNotEmpty(mngrVo.getChangePasswd())) {
            //mngrVo.setChangePasswd(PasswdUtil.encodePasswd(mngrVo.getChangePasswd()));
            mngrVo.setChangePasswd(PasswdUtil.encode(mngrVo.getChangePasswd()));
        }

        Integer updateCnt = opMngrDao.updateCharger(mngrVo);

        // 변경이력 등록
        opChangeService.updateChange(mngrVo);

        return updateCnt;
    }

    @Override
    public Integer updateMngrCrtfcAcnt(MngrVO mngrVo) {

        Integer updateCnt = opMngrDao.updateMngrCrtfcAcnt(mngrVo);

        // 변경이력 등록
        opChangeService.updateChange(mngrVo);

        return updateCnt;
    }

    @Override
    public Integer deleteMngr(MngrVO mngrVo) {

        // 변경이력 등록
        opChangeService.deleteChange(mngrVo);

        // 원본 삭제
        MngrVO dataVo = selectMngr(mngrVo);
        dataVo.setParamMap(mngrVo.getParamMap());

        // 권한삭제
        opMngrDao.deleteAuth(dataVo);
        Integer delCnt = opMngrDao.deleteMngr(dataVo);

        return delCnt;
    }

    @Override
    public Integer deleteListMngr(MngrVO mngrVo) {

        Integer delCnt = 0;

        String[] picIds = mngrVo.getPicIds().split(",");
        MngrVO paramVo = new MngrVO();
        // 권한삭제
        for(String picId : picIds) {
            paramVo.setPicId(picId);
            paramVo.addParam("q_picId", picId);

            // 변경이력 등록
            opChangeService.deleteChange(paramVo);

            opMngrDao.deleteAuth(paramVo);
            delCnt += opMngrDao.deleteMngr(paramVo);
        }

        return delCnt;
    }

    @Override
    public Integer updateUseYn(MngrVO mngrVo) {

        Integer updateCnt = opMngrDao.updateUseYn(mngrVo);

        // 변경이력 등록
        opChangeService.updateChange(mngrVo);

        return updateCnt;
    }

    @Override
    public Integer selectDplctChckId(MngrVO mngrVo) {

        return opMngrDao.selectDplctChckId(mngrVo);
    }

    @Override
    public MngrVO selectDeptTrnsferList(MngrVO mngrVo) {
        mngrVo.setPicIdArray(mngrVo.getPicIds().split(","));

        List<MngrVO> dataList = opMngrDao.selectDeptTrnsferList(mngrVo);
        StringBuffer strbf = new StringBuffer();
        String deptCdId = "";
        String deptNm = "";

        // 담당장 이름+직급 연결
        for(MngrVO dataVo : dataList) {
            if(strbf.length() > 0) {
                strbf.append(", ");
            }
            strbf.append(dataVo.getPicNm() + "(" + dataVo.getClsfNm() + ")");
            if(Validate.isNotEmpty(dataVo.getDeptCdId())) {
                deptCdId = dataVo.getDeptCdId();
            }
            if(Validate.isNotEmpty(dataVo.getDeptNm())) {
                deptNm = dataVo.getDeptNm();
            }
        }

        MngrVO dataVo = new MngrVO();
        dataVo.setPicNm(strbf.toString());
        dataVo.setDeptCdId(deptCdId);
        dataVo.setDeptNm(deptNm);

        return dataVo;
    }

    @Override
    public Integer updateDeptTrnsfer(MngrVO mngrVo) {

        mngrVo.setPicIdArray(mngrVo.getPicIds().split(","));
        // 부서 이동 시에 기존 권한 삭제 후 기본권한 등록
        opMngrDao.deleteExistingAuth(mngrVo);
        // 기본권한 등록은 일단 보류
        /*for(String picId : mngrVo.getPicIdArray()) {
            mngrVo.setPicId(picId);
            mngrVo.setAuthrtCdId("basic");
            mngrVo.setRgtrId(mngrVo.getMdfrId());
            opMngrDao.insertAuth(mngrVo);
        }*/

        // 부서 수정
        return opMngrDao.updateDeptTrnsfer(mngrVo);
    }

    @Override
    public List<AuthorVO> selectAuthorList(MngrVO mngrVo) {

        return opMngrDao.selectAuthorList(mngrVo);
    }

    @Override
    public List<AuthorVO> selectAuthorAsgnList(MngrVO mngrVo) {

        return opMngrDao.selectAuthorAsgnList(mngrVo);
    }

    @Override
    public List<AuthorAsgnVO> selectAuthorAsgnMenuList(MngrVO mngrVo) {

        return opMngrDao.selectAuthorAsgnMenuList(mngrVo);
    }

    @Override
    public Integer updateAuthorAsgn(MngrVO mngrVo) {

        String[] picIds = mngrVo.getPicIds().split(",");
        String[] authrtCdIds = mngrVo.getAuthrtCdIds().split(",");
        int result = 0;

        if(Validate.isNotEmpty(mngrVo.getPicId()) && Validate.isEmpty(mngrVo.getPicIds())) {
            MngrVO authorVo = new MngrVO();
            authorVo.setRgtrId(mngrVo.getRgtrId());
            // picId의 할당된 권한을 전부 삭제
            authorVo.setPicId(mngrVo.getPicId());
            opMngrDao.deleteAuth(authorVo);

            // 선택한 권한들 등록
            for(int j = 0 ; j < authrtCdIds.length ; j++) {
                authorVo.setAuthrtCdId(authrtCdIds[j]);
                result += opMngrDao.insertAuth(authorVo);
            }
        } else {
            for(int i = 0 ; i < picIds.length ; i++) {
                MngrVO authorVo = new MngrVO();
                authorVo.setRgtrId(mngrVo.getRgtrId());
                // picIds[i] 의 할당된 권한을 전부 삭제
                authorVo.setPicId(picIds[i]);
                opMngrDao.deleteAuth(authorVo);

                // 선택한 권한들 등록
                for(int j = 0 ; j < authrtCdIds.length ; j++) {
                    authorVo.setAuthrtCdId(authrtCdIds[j]);
                    result += opMngrDao.insertAuth(authorVo);
                }
            }
        }

        return result;
    }

    @Override
    public Boolean selectPasswordChangeDeCheck(MngrVO mngrVo) {

        MngrVO dataVo = opMngrDao.selectMngr(mngrVo);

        return Validate.isNotEmpty(dataVo.getPswdChgDt());
    }

    @Override
    public Boolean selectPasswordExpiredCheck(MngrVO mngrVo) {

        Boolean isExpired = false;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date expireDt = cal.getTime();
        MngrVO dataVo = opMngrDao.selectMngr(mngrVo);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(Validate.isNotEmpty(dataVo.getPswdChgDt())) {
            try {
                Date date = df.parse(dataVo.getPswdChgDt());
                if(expireDt.compareTo(date) > 0) {
                    isExpired = true;
                }
            } catch (ParseException e) {
                System.err.println("패스워드 만료체크 실패");
            }
        }

        return isExpired;
    }

}
