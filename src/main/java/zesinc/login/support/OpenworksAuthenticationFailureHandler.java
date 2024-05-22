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

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import zesinc.core.lang.Validate;
import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.intra.mngr.connect.support.CONNECT_LOG_TYPE;
import zesinc.intra.mngr.connect.support.ConnectResultCode;
import zesinc.login.LoginService;
import zesinc.login.domain.LoginVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.CryptoUtil;

/**
 * 로그인 실패시 보안 처리를 위한 클레스
 * 일정 시간이 경과하면 기존 재시도 횟수를 초기화하고 다시 시도할 수 있도록 한다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 1. 15.    방기배   최초작성
 *  2019. 7. 17.    황신욱   로그인실패로직 추가
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class OpenworksAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /** 로그인 서비스 주입 */
    @Resource(name = "opLoginService")
    private LoginService opLoginService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException {

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        String remoteIp = request.getRemoteAddr();
        String loginid = request.getParameter("encPicId");
        try {
            loginid = CryptoUtil.decrypt(String.valueOf(loginid));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // 접속로그 관련(등록코드로 고정)
        CONNECT_LOG_TYPE logType = CONNECT_LOG_TYPE.LOGIN;
        ConnectVO connectVo = new ConnectVO();
        connectVo.setPicId(loginid);
        connectVo.setLogSeNo(logType.getCdId());
        connectVo.setLgnIpAddr(remoteIp);

        connectVo.setLgnRsltNo(ConnectResultCode.IF_1010.name());

        String message = "";
        if(exception instanceof DisabledException) {
            connectVo.setLgnRsltNo(ConnectResultCode.IF_1012.name());
            message = "사용이 중지된 계정입니다.";
        } else if(exception instanceof BadCredentialsException){
            connectVo.setLgnRsltNo(ConnectResultCode.IF_1010.name());
            message = "ID 또는 비밀번호 오류입니다.<br />(5회이상 틀릴시 관리자에게 문의 하십시오)";

            LoginVO login = new LoginVO();
            login.setPicId(loginid);
            opLoginService.updateLoginFail(login);
        }

        // 관리자 로그 모드가 true인 경우 로그를 기록
        if(BaseConfig.MGR_LOG_MODE) {
            /*
             * 인증 실패 후 접속 로그 등록
             * 이외의 케이스는 OpenworksAuthenticationSuccessHandler.java 클레스를 참조한다.
             */
            opLoginService.insertConnect(connectVo);
        }

        OpenworksAuthenticationException ex = new OpenworksAuthenticationException(message, exception);

        super.setRedirectStrategy(redirectStrategy);
        super.onAuthenticationFailure(request, response, ex);
    }
}
