/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userInfoIemEstbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.user.userInfoIemEstbs.UserInfoIemEstbsMapper;
import zesinc.intra.user.userInfoIemEstbs.UserInfoIemEstbsService;
import zesinc.intra.user.userInfoIemEstbs.domain.UserInfoIemEstbsVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자정보항목설정 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-05-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opUserInfoIemEstbsService")
public class UserInfoIemEstbsServiceImpl extends EgovAbstractServiceImpl implements UserInfoIemEstbsService {

    @Resource(name = "opUserInfoIemEstbsDao")
    private UserInfoIemEstbsMapper opUserInfoIemEstbsDao;

    @Override
    public UserInfoIemEstbsVO selectIndvdlUserInfoIemEstbsYn() {
        return opUserInfoIemEstbsDao.selectIndvdlUserInfoIemEstbsYn();
    }

    @Override
    public UserInfoIemEstbsVO selectEntrprsUserInfoIemEstbsYn() {
        return opUserInfoIemEstbsDao.selectEntrprsUserInfoIemEstbsYn();
    }

    @Override
    public UserInfoIemEstbsVO selectUserInfoIemEstbsList(UserInfoIemEstbsVO userInfoIemEstbsVo) {

        // 기본정보 조회
        userInfoIemEstbsVo.setArtclTypeCd("B");
        List<UserInfoIemEstbsVO> dataList = opUserInfoIemEstbsDao.selectUserInfoIemEstbsList(userInfoIemEstbsVo);
        userInfoIemEstbsVo.setBasicInfo(dataList);

        // 부가정보 조회
        userInfoIemEstbsVo.setArtclTypeCd("A");
        dataList = opUserInfoIemEstbsDao.selectUserInfoIemEstbsList(userInfoIemEstbsVo);
        userInfoIemEstbsVo.setEtcInfo(dataList);

        return userInfoIemEstbsVo;
    }

    @Override
    public Integer updateUserInfoIemEstbs(UserInfoIemEstbsVO userInfoIemEstbsVo) {

        Integer[] stngArtclSns = userInfoIemEstbsVo.getStngArtclSns();
        int result = 0;
        for(Integer stngArtclSn : stngArtclSns) {
            UserInfoIemEstbsVO paramVo = new UserInfoIemEstbsVO();
            String useYn = userInfoIemEstbsVo.getString("q_useYn" + stngArtclSn);
            String esntlYn = userInfoIemEstbsVo.getString("q_esntlYn" + stngArtclSn);
            int useYnCode = 1002;
            if("Y".equals(useYn)) {
                if("Y".equals(esntlYn)) {
                    useYnCode = 1003; // 필수체크(사용이면서)
                } else {
                    useYnCode = 1001; // 사용
                }
            }
            paramVo.setUseYnCode(useYnCode);
            paramVo.setStngArtclSn(stngArtclSn);
            result += opUserInfoIemEstbsDao.updateUserInfoIemEstbs(paramVo);
        }
        return result;
    }
}
