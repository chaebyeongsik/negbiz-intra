/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.accessHist.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.user.accessHist.UserAccessHistMapper;
import zesinc.intra.user.accessHist.UserAccessHistService;
import zesinc.intra.user.accessHist.domain.UserAccessHistStatisticsVO;
import zesinc.intra.user.accessHist.domain.UserAccessHistVO;
import zesinc.web.support.pager.Pager;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-08-01.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opUserAccessHistService")
public class UserAccessHistServiceImpl extends EgovAbstractServiceImpl implements UserAccessHistService {

    @Resource(name = "opUserAccessHistDao")
    private UserAccessHistMapper opUserAccessHistDao;

    @Override
    public Pager<UserAccessHistVO> selectUserAccessHist(UserAccessHistVO userAccessHistVo) {

        List<UserAccessHistVO> dataList = opUserAccessHistDao.selectUserAccessHist(userAccessHistVo);
        Integer totalNum = opUserAccessHistDao.selectUserAccessHistCount(userAccessHistVo);

        return new Pager<UserAccessHistVO>(dataList, userAccessHistVo, totalNum);
    }

    @Override
    public Pager<UserAccessHistVO> selectUserAccessHistPageList(UserAccessHistVO userAccessHistVo) {

        List<UserAccessHistVO> dataList = opUserAccessHistDao.selectUserAccessHistList(userAccessHistVo);
        Integer totalNum = opUserAccessHistDao.selectUserAccessHistListCount(userAccessHistVo);

        return new Pager<UserAccessHistVO>(dataList, userAccessHistVo, totalNum);
    }

    @Override
    public List<UserAccessHistStatisticsVO> selectAccessStatistics() {

        return opUserAccessHistDao.selectAccessStatistics();
    }

    @Override
    public Integer selectUserLoginIpTotal(UserAccessHistVO userAccessHistVo) {

        return opUserAccessHistDao.selectUserLoginIpTotal(userAccessHistVo);
    }

    @Override
    public List<UserAccessHistVO> selectUserLoginIp(UserAccessHistVO userAccessHistVo) {

        return opUserAccessHistDao.selectUserLoginIp(userAccessHistVo);
    }

}
