/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.commons.interceptor;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;

import zesinc.core.lang.Validate;
import zesinc.intra.user.accessHist.UserAccessHistController;
import zesinc.intra.user.hist.UserHistService;
import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.intra.user.support.UserConsts;
import zesinc.intra.user.userManage.UserManageController;
import zesinc.login.domain.LoginVO;
import zesinc.web.spring.interceptor.BaseInterceptor;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.RequestPathMatcher.PathVO;
import zesinc.web.utils.RequestUtil;
import zesinc.web.utils.XssUtil;

/**
 * 사용자 이력 인터셉터
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 8. 5.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserHistInterceptor extends BaseInterceptor {

    protected final Log logger = LogFactory.getLog(getClass());

    @Resource(name = "opUserHistService")
    private UserHistService opUserHistService;

    @Override
    public boolean supported(Object handler) {
        if(HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            HandlerMethod handle = (HandlerMethod) handler;
            Class<?> clz = handle.getBeanType();
            // 컨트롤러 클래스를 비교
            if(UserManageController.class.isAssignableFrom(clz)
                || UserAccessHistController.class.isAssignableFrom(clz)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(supported(handler)) {

            PathVO resultVo = validate(request);
            if(Validate.isNotEmpty(resultVo)) {

                String referer = request.getHeader("referer");
                // 본인 페이지에서 재호출 혹은 페이징일 경우는 제외
                if(!referer.contains(request.getRequestURI())) {
                    String menuSn = XssUtil.cleanTag(request.getParameter("menuSn"), XssUtil.TYPE.ALL);
                    String menuNm = XssUtil.cleanTag(request.getParameter("menuNm"), XssUtil.TYPE.ALL);
                    String userId = XssUtil.cleanTag(request.getParameter("userId"), XssUtil.TYPE.ALL);

                    if(UserConsts.LOG_TYPE.equals("personalLog") && UserConsts.LOG_USE_YN.equals("Y")) {
                        String sessionVal = (String) OpHelper.getSession(request, UserConsts.USER_INFO_LOG_VALIDATE);

                        if(Validate.isEmpty(sessionVal)) {
                            if(Validate.isNotEmpty(userId)) {
                                OpHelper.setSession(request, UserConsts.SELECT_USER_ID, userId);
                            }

                            String paramStr = RequestUtil.getQueryString(request);
                            if(Validate.isNotEmpty(paramStr)) {
                                paramStr = "?" + paramStr;
                            }

                            // 파라메터중 한글 인코딩처리
                            char[] txtChar = paramStr.toCharArray();
                            for (int j = 0; j < txtChar.length; j++) {
                                if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                                    String targetText = String.valueOf(txtChar[j]);
                                    paramStr = paramStr.replace(targetText, URLEncoder.encode(targetText, "utf-8"));
                                }
                            }
                            OpHelper.setSession(request, UserConsts.USER_INFO_ACCESS_MENU_URL, request.getRequestURI() + paramStr);

                            String redirectUrl = UserConsts.USER_INFO_LOG_FORM_URL;
                            menuNm = URLEncoder.encode(menuNm, "utf-8");
                            response.sendRedirect(redirectUrl + "?menuSn=" + menuSn + "&menuNm=" + menuNm+ "&q_menuNm=" + menuNm);
                            return false;
                        } else {
                            OpHelper.removeSession(request, UserConsts.USER_INFO_LOG_VALIDATE);
                        }
                    } else {
                        LoginVO loginVo = (LoginVO) OpHelper.getMgrSession();
                        UserHistVO userHistVo = new UserHistVO();
                        userHistVo.setPicId(loginVo.getPicId());
                        userHistVo.setMenuSeCd("R");
                        userHistVo.setMenuNm(menuNm);
                        if(Validate.isNotEmpty(menuSn))
                            userHistVo.setMenuSn(Integer.parseInt(menuSn));
                        userHistVo.setSrchSeCd("R");
                        userHistVo.setUserId(userId);
                        userHistVo.setLogCn(UserConsts.LOG_AUTO_RESN);

                        // 로그 등록
                        opUserHistService.insertUserLog(userHistVo);
                    }
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

}
