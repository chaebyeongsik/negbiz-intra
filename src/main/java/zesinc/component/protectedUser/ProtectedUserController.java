/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.component.protectedUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.core.lang.Validate;
import zesinc.intra.user.hist.UserHistService;
import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.intra.user.support.UserConsts;
import zesinc.login.domain.LoginVO;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.DateFormatUtil;
import zesinc.web.utils.MessageUtil;

/**
 * 사용자정보 보호를 위하여 관련 메뉴 진입 시 비밀번호를 받는 로직을 수행하는 컨트롤러
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 18.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
@RequestMapping(value = { "/component/protectedUser" })
public class ProtectedUserController extends IntraController {

    @Resource(name = "opUserHistService")
    private UserHistService opUserHistService;

    /**
     * 비밀번호 확인 폼
     *
     * @param request
     * @param model
     */
    @RequestMapping(value = "BD_processPwdCheckForm.do")
    public void processPwdCheck(HttpServletRequest request, Model model) {
    }

    /**
     * 입력된 비밀번호가 현재 로그인한 담당자의 비밀번호와 동일한지 비교
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "ND_processPwdCheck.do")
    public String processCheck(HttpServletRequest request, HttpServletResponse response, Model model, UserHistVO userHistVo) throws Exception {
        LoginVO loginVo = (LoginVO) getMgrSession();

        // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
        if (Validate.isNotEmpty(userHistVo.getPicPswd())) {
            userHistVo.setPicPswd(CryptoUtil.decrypt(String.valueOf(userHistVo.getPicPswd())));
        }

        // 비밀번호 일치 여부
        if(opUserHistService.selectCheckMngrPasswordMatch(loginVo, userHistVo)) {
            // ACCESS URL GET
            String accessMenuUrl = (String) OpHelper.getSession(request, UserConsts.ACCESS_MENU_URL);
            String matchUrl = (String) OpHelper.getSession(request, UserConsts.VALIDATE_DISPATCHER_URL);
            logger.debug("> get session[" + UserConsts.ACCESS_MENU_URL + "] > " + accessMenuUrl);
            logger.debug("> get session[" + UserConsts.VALIDATE_DISPATCHER_URL + "] > " + matchUrl);

            // SESSION SET
            OpHelper.setSession(request, matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE, true);
            logger.debug("> set session[" + matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE + "] > true");
            String now = DateFormatUtil.getToday();
            OpHelper.setSession(request, UserConsts.MENU_ACCESS_TIME, now);
            logger.debug("> set session[" + UserConsts.MENU_ACCESS_TIME + "] > " + now);

            // 접근할 URL 세션 삭제
            OpHelper.removeSession(request, UserConsts.ACCESS_MENU_URL);
            logger.debug("> remove session[" + UserConsts.ACCESS_MENU_URL + "]");
            return responseJson(model, MessageUtil.TRUE, accessMenuUrl);
        }

        return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
    }
}
