/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.support;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import zesinc.core.lang.Validate;
import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.intra.mngr.connect.support.CONNECT_LOG_TYPE;
import zesinc.intra.mngr.connect.support.ConnectResultCode;
import zesinc.login.LoginService;
import zesinc.login.domain.LoginVO;
import zesinc.web.support.BaseConfig;

/**
 * 로그아웃 성공시 후처리 프로세스(로그아웃 기록용)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 5. 17.    yesno   최초작성
 * </pre>
 *
 * @author yesno
 */
public class OpenworksLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    /** 로그인 서비스 주입 */
    @Resource(name = "opLoginService")
    private LoginService opLoginService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        // 관리자 로그 모드가 true인 경우 로그를 기록
        if(BaseConfig.MGR_LOG_MODE && Validate.isNotEmpty(authentication)) {
            // 접근제한 확인을 위한 데이터
            String remoteIp = request.getRemoteAddr();
            LoginVO loginVo = (LoginVO) authentication.getPrincipal();

            // 로그아웃로그 관련
            CONNECT_LOG_TYPE logType = CONNECT_LOG_TYPE.LOGOUT;
            ConnectVO connectVo = new ConnectVO();
            connectVo.setPicId(loginVo.getPicId());
            connectVo.setLogSeNo(logType.getCdId());
            connectVo.setLgnIpAddr(remoteIp);
            connectVo.setLgnRsltNo(ConnectResultCode.OS_1010.name());

            /*
             * 로그 아웃 로그 등록
             */
            opLoginService.insertConnect(connectVo);
        }
        super.handle(request, response, authentication);
    }
}
