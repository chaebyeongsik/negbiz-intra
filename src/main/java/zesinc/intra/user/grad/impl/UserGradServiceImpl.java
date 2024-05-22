/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.grad.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.intra.user.grad.UserGradMapper;
import zesinc.intra.user.grad.UserGradService;
import zesinc.intra.user.grad.domain.UserGradVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자 등급 관리
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 8.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opUserGradService")
public class UserGradServiceImpl extends EgovAbstractServiceImpl implements UserGradService {

    @Resource(name = "opUserGradDao")
    private UserGradMapper opUserGradDao;

    @Override
    public List<UserGradVO> selectUserGradList(UserGradVO userGradVo) {

        return opUserGradDao.selectUserGradList(userGradVo);
    }

    @Override
    public List<UserGradVO> selectUserGradInUseList() {

        UserGradVO userGradVo = new UserGradVO();
        userGradVo.setUseYn("Y");
        return opUserGradDao.selectUserGradList(userGradVo);
    }

    @Override
    public Integer selectDplctChckGrad(UserGradVO userGradVo) {
        return opUserGradDao.selectDplctChckGrad(userGradVo);
    }

    @Override
    public UserGradVO selectUserGrad(UserGradVO userGradVo) {
        return opUserGradDao.selectUserGrad(userGradVo);
    }

    @Override
    public Integer insertUserGrad(UserGradVO userGradVo) {
        return opUserGradDao.insertUserGrad(userGradVo);
    }

    @Override
    public Integer updateUserGrad(UserGradVO userGradVo) {
        return opUserGradDao.updateUserGrad(userGradVo);
    }

    @Override
    public List<UserGradVO> selectUserGradList() {
        return opUserGradDao.selectUserGradList();
    }

}
