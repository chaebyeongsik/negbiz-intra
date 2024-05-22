/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.intra.mngr.connect.ConnectService;
import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.login.LoginMapper;
import zesinc.login.LoginService;
import zesinc.login.domain.LoginAccesCtrlVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.utils.PasswdUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @author (주)제스아이엔씨 기술연구소
 *
 *         <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 7.    방기배   최초작성
 *  2019. 7. 17.   황신욱   로그인실패로직 추가
 * </pre>
 * @see
 */
@Service("opLoginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

    @Resource(name = "opLoginDao")
    private LoginMapper opLoginDao;

    @Resource(name = "opConnectService")
    private ConnectService opConnectService;

    @Override
    public LoginVO processLogin(LoginVO loginVo) {

        LoginVO dataVo = opLoginDao.selectLogin(loginVo);

        if(Validate.isNotEmpty(dataVo)) {
            String passwd = loginVo.getPicPswd();
            //Boolean matcheAt = PasswdUtil.matche(passwd, dataVo.getPicPswd());
            Boolean matcheAt = PasswdUtil.matches(passwd, dataVo.getPicPswd());

            if(matcheAt) {
                dataVo.setMatcheAt(true);
                return dataVo;
            }
        }
        loginVo.setMatcheAt(false);
        return loginVo;
    }

    @Override
    public LoginVO processGpkiLogin(LoginVO loginVo) {

        return opLoginDao.selectGpkiLogin(loginVo);
    }

    @Override
    public List<LoginAccesCtrlVO> selectAccessCtrlList(LoginAccesCtrlVO loginAccesCtrlVo) {

        return opLoginDao.selectAccessCtrlList(loginAccesCtrlVo);
    }

    @Override
    public Integer updateLoginCnt(LoginVO loginVo) {

        return opLoginDao.updateLoginCnt(loginVo);
    }

    @Override
    public Integer insertConnect(ConnectVO connectVo) {

        return opConnectService.insertConnect(connectVo);
    }

    @Override
    public Integer updateLoginFail(LoginVO loginVo) {

        Integer result = opLoginDao.updateLoginFailCnt(loginVo);
        LoginVO dataVo = opLoginDao.selectLogin(loginVo);
        if (Validate.isNotEmpty(dataVo) && dataVo.getLgnFailNmtm() >= 5) {
            opLoginDao.updateMngrUseStop(loginVo);
        }

        return result;
    }
}
