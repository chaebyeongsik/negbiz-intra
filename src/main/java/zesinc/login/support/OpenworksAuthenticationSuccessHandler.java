/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.support;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.accessCtrl.AccesCtrlSupport;
import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.intra.mngr.connect.support.CONNECT_LOG_TYPE;
import zesinc.intra.mngr.connect.support.ConnectResultCode;
import zesinc.login.LoginController;
import zesinc.login.LoginService;
import zesinc.login.domain.LoginAccesCtrlVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;

/**
 * 로그인 성공시 보안 처리를 위한 클레스.
 * 현재로는 로그인 성공 후 비밀번호 만료 상태를 판단하여 초기화면이 아닌 변경 화면으로 이동시킴
 * /config/spring/context-security.xml 파일을 참조한다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 1. 15.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class OpenworksAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /** 로그인 서비스 주입 */
    @Resource(name = "opLoginService")
    private LoginService opLoginService;

    private static String MGR_MAIN_PAGE = Config.getString("webapp-config.urls.mgrMain", "/intra/dashboard/BD_selectDashBoard.do");
    private static String MGR_PASSWD_EXPIRED_PAGE = Config.getString("webapp-config.urls.mgrPasswdExpired", "/login/ND_passwdExpired.do");
    private static String MGR_ACCESS_DENIED_PAGE = Config.getString("webapp-config.urls.mgrAccessCtrlDenied", "/login/ND_accessCtrlDenied.do");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        boolean isAllowAccess = false;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        // 접근제한 확인을 위한 데이터
        String remoteIp = request.getRemoteAddr();
        //String remoteIp = "192.168.0.12";
        LoginVO loginVo = (LoginVO) authentication.getPrincipal();

        String sessChallenge = (String) OpHelper.getSession(request, LoginController.GPKI_SESSID);
        String reqChallenge = request.getParameter(LoginController.GPKI_SESSID);

        // 접속로그 관련
        CONNECT_LOG_TYPE logType = CONNECT_LOG_TYPE.LOGIN;
        ConnectVO connectVo = new ConnectVO();
        connectVo.setPicId(loginVo.getPicId());
        connectVo.setLogSeNo(logType.getCdId());
        connectVo.setLgnIpAddr(remoteIp);

        if(Validate.isEmpty(sessChallenge) ||
            (Validate.isNotEmpty(sessChallenge) && sessChallenge.equals(reqChallenge))) {

            // ID로그인과 GPKI 로그인 구분
            if(Validate.isEmpty(sessChallenge)) {
                connectVo.setLgnRsltNo(ConnectResultCode.IS_1010.name());
            } else {
                connectVo.setLgnRsltNo(ConnectResultCode.IS_1011.name());
            }

            // 접근제어 목록 추출
            LoginAccesCtrlVO loginAccesCtrlVo = new LoginAccesCtrlVO();
            List<LoginAccesCtrlVO> accesCtrlList = opLoginService.selectAccessCtrlList(loginAccesCtrlVo);

            // 접근제어 설정이 없다면 모두 허용하며, 설정이 있다면 모든 로그인에 대하여 제한한다.
            if(Validate.isNotEmpty(accesCtrlList)) {

                // 순차적으로 검사하여 하나라도 만족되면 접근 허용
                for(LoginAccesCtrlVO accesVo : accesCtrlList) {
                    // IP 제한 설정인 경우와 ID 설정 제한인 경우를 나눠 하나라도 일치하면 통과한다.
                    if(accesVo.getPrmsnTypeNm().equals("IP")) {
                       /*
                        if(remoteIp.startsWith(accesVo.getPrmsnBgngIp()) && remoteIp.startsWith(accesVo.getPrmsnEndIp())) {
                            isAllowAccess = true;
                            break;
                        }
                        */

                        /*
                         * long값으로 변환한 client IP가
                           long값으로 변환한 허용IP시작 값보다 크고, long값으로 변환한 허용IP종료 값보다 작으면 접근을 허용한다.
                        */
                        if(AccesCtrlSupport.getExchgIp(remoteIp) >= AccesCtrlSupport.getExchgIp(accesVo.getPrmsnBgngIp()) &&
                            AccesCtrlSupport.getExchgIp(remoteIp) <= AccesCtrlSupport.getExchgIp(accesVo.getPrmsnEndIp())) {
                            isAllowAccess = true;
                            break;
                        }
                    } else {
                        if(loginVo.getPicId().equals(accesVo.getPrmsnId())) {
                            isAllowAccess = true;
                            break;
                        }
                    }
                }
            } else {
                isAllowAccess = true;
            }

            if(isAllowAccess) {
                if(loginVo.getPasswordEndAt().equals("Y") && Validate.isEmpty(loginVo.getAcntNm())) {
                    // 인증서 로그인이 아니고, 비번변경일 만료시 비번 변경페이지로 이동
                    redirectStrategy.sendRedirect(request, response, MGR_PASSWD_EXPIRED_PAGE);
                } else {
                    // 정상 페이지로 이동
                    redirectStrategy.sendRedirect(request, response, MGR_MAIN_PAGE);
                }
            } else {
                authentication.setAuthenticated(false);
                // 접근제한 안내 페이지로 이동
                redirectStrategy.sendRedirect(request, response, MGR_ACCESS_DENIED_PAGE);

                // ID로그인과 GPKI 로그인 구분
                if(Validate.isEmpty(sessChallenge)) {
                    connectVo.setLgnRsltNo(ConnectResultCode.IF_1030.name());
                } else {
                    connectVo.setLgnRsltNo(ConnectResultCode.IF_1031.name());
                }
            }
        } else {
            authentication.setAuthenticated(false);
            // 접근제한 안내 페이지로 이동
            redirectStrategy.sendRedirect(request, response, MGR_ACCESS_DENIED_PAGE);
            connectVo.setLgnRsltNo(ConnectResultCode.IF_1020.name());
        }
        // 인증 토큰 삭제
        OpHelper.removeSession(request, LoginController.GPKI_SESSID);

        // 로그인 정보 등록
        if(isAllowAccess) {
            opLoginService.updateLoginCnt(loginVo);
        }

        // 관리자 로그 모드가 true인 경우 로그를 기록
        if(BaseConfig.MGR_LOG_MODE) {
            /*
             * 인증 성공 후 접속 로그 등록
             * 이외 인증 오류 및 권한 오류는 OpenworksAuthenticationFailureHandler.java 클레스를 참조한다.
             */
            opLoginService.insertConnect(connectVo);
        }

        super.setRedirectStrategy(redirectStrategy);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
