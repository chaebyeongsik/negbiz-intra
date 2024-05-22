/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.commons.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.user.accessHist.UserAccessHistController;
import zesinc.intra.user.hist.UserHistController;
import zesinc.intra.user.support.UserConsts;
import zesinc.intra.user.userManage.UserManageController;
import zesinc.web.spring.interceptor.BaseInterceptor;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.DateFormatUtil;
import zesinc.web.utils.RequestPathMatcher.PathVO;
import zesinc.web.utils.RequestUtil;

/**
 * 사용자정보관련 메뉴 진입 시 한번 더 본인확인(비번체크) Interceptor
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 25.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class ProtectedUserInterceptor extends BaseInterceptor {

    protected final Log logger = LogFactory.getLog(getClass());

    /** 접근허용여부 */
    private static String ALLOW_ACCESS_AT = Config.getString("user-config.protectedUserInfo.allowAccess");
    /**  */
    private static String PWD_CHECK_URL = Config.getString("user-config.protectedUserInfo.protectedUserPwdCheckUrl");

    /**
     * handler의 종류에 따라서 최초 결정한다 이후에 url 매칭 검증을 실행한다.
     */
    @Override
    public boolean supported(Object handler) {

        if(HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            HandlerMethod handle = (HandlerMethod) handler;
            Class<?> clz = handle.getBeanType();
            // 컨트롤러 클레스를 비교
            if(UserManageController.class.isAssignableFrom(clz)
                || UserHistController.class.isAssignableFrom(clz)
                || UserAccessHistController.class.isAssignableFrom(clz)) {
                return true;
            }
        }

        return false;
    }

    /*
     * public String getRequestPath(){
     * }
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!"Y".equals(ALLOW_ACCESS_AT)) {

            // 검증 대상인지 확인
            if(supported(handler)) {
                PathVO resultVo = validate(request);
                if(Validate.isNotEmpty(resultVo)) {

                    String requestUrl = RequestUtil.getExtractRequestPath(request);
                    String matchUrl = getRequestUrl(resultVo);
                    Boolean sessionVal = (Boolean) OpHelper.getSession(request, matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE);
                    //logger.debug("> get session[" + matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE + "] > " + sessionVal);

                    String redirectUrl = "";
                    String contextPath = request.getContextPath();
                    if(Validate.isNotEmpty(contextPath)) {
                        redirectUrl += contextPath;
                    }
                    redirectUrl += PWD_CHECK_URL;

                    boolean timeOver = false;
                    if(Validate.isNotEmpty(sessionVal)) {
                        String accessTime = (String) OpHelper.getSession(request, UserConsts.MENU_ACCESS_TIME);
                        String now = DateFormatUtil.getToday();
                        //logger.debug("$ now : " + now + " // accessTime : " + accessTime);

                        Integer timeOut = UserConsts.SESSION_TIME_OUT;

                        // 최종접속시간으로 부터의 시간차
                        Long timeLag = Long.parseLong(now) - Long.parseLong(accessTime);
                        //logger.debug("$ timeLag : " + timeLag);

                        if(timeLag > (100 * timeOut)) {
                            OpHelper.removeSession(request, matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE);
                            //logger.debug("> remove session[" + matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE + "]");
                            timeOver = true;
                        }
                    }

                    //logger.debug("$ timeOver -> " + timeOver);
                    // 인증여부
                    if(Validate.isEmpty(sessionVal) || timeOver) {
                        // SET ACCESS_MENU_URL
                        OpHelper.setSession(request, UserConsts.ACCESS_MENU_URL, requestUrl);
                        OpHelper.removeSession(request, matchUrl + UserConsts.USER_MENU_PASSED_VALIDATE);
                        OpHelper.setSession(request, UserConsts.VALIDATE_DISPATCHER_URL, matchUrl);
                        //logger.debug("> set session[" + UserConsts.VALIDATE_DISPATCHER_URL + "] > " + matchUrl);
                        //logger.debug("> set session[" + UserConsts.ACCESS_MENU_URL + "] > " + requestUrl);
                        //logger.debug("% sendRedirect : " + redirectUrl);
                        // REDIRECT GO
                        response.sendRedirect(redirectUrl);
                        return false;
                    }
                }

                OpHelper.setSession(request, UserConsts.MENU_ACCESS_TIME, DateFormatUtil.getToday());
            }
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * getRequestUrl 반환
     *
     * @return
     */
    public String getRequestUrl(PathVO pathVo) {
        String url = pathVo.getRequestUri();
        List<String> paramList = pathVo.getParamList();
        if(paramList.size() > 0) {
            String paramStr = "";
            for(String param : paramList) {
                paramStr += "&" + param;
            }
            paramStr = paramStr.substring(1);
            url += "?" + paramStr;
        }
        return url;
    }
}
