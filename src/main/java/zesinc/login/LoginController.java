/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zesinc.core.crypto.Crypto;
import zesinc.core.crypto.CryptoFactory;
import zesinc.core.lang.Validate;
import zesinc.login.domain.LoginVO;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.PasswdUtil;

import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.exception.GpkiApiException;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;

/**
 * 담당자 로그인 처리 컨트롤러.
 * 실제 처리는 Spring Security에서 처리하며 본 컨트롤러는 화면 제공에만 사용된다.
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 7.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends IntraController {

    /** 인증서 로그인시 추가 보안 키 */
    public static String GPKI_SESSID = "challenge";

    @Resource(name = "opLoginService")
    LoginService opLoginService;

    /**
     * 로그인 폼 페이지
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_loginForm.do" })
    public void loginForm(HttpServletRequest request, Model model) {
    }

    /**
     * 세션 종료 페이지
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_LostSession.do" })
    public void lostSession(HttpServletRequest request, Model model) {
    }

    /**
     * 접근권한 오류 페이지(권한 없음 또는 낮음)
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_accessDenied.do" })
    public void accessDenied(HttpServletRequest request, Model model) {
    }

    /**
     * 접근권한 오류 페이지(허용IP/ID 아님)
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_accessCtrlDenied.do" })
    public void accessCtrlDenied(HttpServletRequest request, Model model) {

        LoginVO loginVo = (LoginVO) getMgrSession();
        request.setAttribute("ACCESS_IP", request.getRemoteAddr());
        request.setAttribute("ACCESS_ID", loginVo.getPicId());

        HttpSession hs = request.getSession();
        hs.invalidate();
    }

    /**
     * 비밀번호 변경일 만료 안내 페이지
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_passwdExpired.do" })
    public void passwdExpired(HttpServletRequest request, Model model) {
    }

    /**
     * GPKI 로그인 폼 페이지
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_gpkiLoginForm.do" })
    public void gpkiLoginForm(HttpServletRequest request, Model model) {
    }

    /**
     * GPKI 로그인 폼 페이지
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "ND_gpkiLogin.do" })
    public String gpkiLogin(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttr) {

        LoginVO loginVo = new LoginVO();

        GPKIHttpServletResponse gpkiresponse = null;
        GPKIHttpServletRequest gpkirequest = null;
        try {
            gpkiresponse = new GPKIHttpServletResponse(response);
            gpkirequest = new GPKIHttpServletRequest(request);
            gpkiresponse.setRequest(gpkirequest);

            logger.debug("GPKI QueryString : {}", gpkirequest.getQueryString());

            X509Certificate cert = null;
            byte[] privatekeyRandom = null;
            String acntNm = null;

            int message_type = gpkirequest.getRequestMessageType();

            if(message_type == GPKIHttpServletRequest.ENCRYPTED_SIGNDATA || message_type == GPKIHttpServletRequest.LOGIN_ENVELOP_SIGN_DATA ||
                message_type == GPKIHttpServletRequest.ENVELOP_SIGNDATA || message_type == GPKIHttpServletRequest.SIGNED_DATA) {

                privatekeyRandom = gpkirequest.getSignerRValue();
                cert = gpkirequest.getSignerCert();
                // acntNm = cert.getSubjectUID();
                acntNm = cert.getSubjectDN();

                logger.debug("GPKI Info : {} DN : {}", message_type, acntNm);

                // 추가 인증정보로 주민번호를 사용하는 경우에 JSP 에서 활성화후 아래 로직이 돌도록 수정
                String ssn = gpkirequest.getParameter("ssn");
                if(Validate.isNotEmpty(ssn) && Validate.isNotEmpty(privatekeyRandom)) {
                    try {
                        cert.verifyVID(ssn, privatekeyRandom);
                    } catch (GpkiApiException ex) {
                        logger.error("GPKIKeyInfo Exception = ssn : {}", ex);
                        // 개인 식별 번호가 다른경우 예외처리
                        return alertAndBack(model, "인증정보와 입력한 번호가 일치하지 않습니다.");
                    }
                }
                loginVo.setAcntNm(acntNm);
                loginVo = opLoginService.processGpkiLogin(loginVo);

                if(Validate.isEmpty(loginVo)) {
                    return alertAndBack(model, "등록된 사용자가 없습니다. 인증서가 등록되어 있어야 합니다.");
                }

                List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
                Crypto cry = CryptoFactory.getInstance();
                String encCrtfcAcnt = cry.encrypt(acntNm);
                encCrtfcAcnt = URLEncoder.encode(encCrtfcAcnt, "utf-8");

                String picId = "SERIAL:" + encCrtfcAcnt;
                String picPswd = "SERIAL";

                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("name", "picId");
                paramMap.put("value", picId);
                paramList.add(paramMap);
                paramMap = new HashMap<String, Object>();
                paramMap.put("name", "picPswd");
                paramMap.put("value", picPswd);
                paramList.add(paramMap);

                // 인증 토큰 생성 및 파라미터 생성
                String challenge = PasswdUtil.encodePasswd(PasswdUtil.generatePasswd());
                paramMap = new HashMap<String, Object>();
                paramMap.put("name", GPKI_SESSID);
                paramMap.put("value", challenge);
                paramList.add(paramMap);
                // 파라미터와 비교할 인증 토큰 세션에 설정
                OpHelper.setSession(request, GPKI_SESSID, challenge);

                return postMethodRedirect(model, "/login", paramList);
            }
        } catch (Exception e) {
            logger.error("GPKIKeyInfo Exception : {}", e);
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }
        return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
    }

}
