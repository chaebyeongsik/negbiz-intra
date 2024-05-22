/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.support;

import zesinc.core.config.Config;

/**
 * 사용자 정보 관련 상수값
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 6.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserConsts {

    /** 사용자정보 로그(조회,출력,변경) 연관 접두어 */
    public static final String SELECT_USER_ID = "selectUserId";
    public static final String SELECT_MENU_NM = "selectMenuNm";
    public static final String USER_INFO_LOG_VALIDATE = "userInfoLogValidate";
    public static final String USER_INFO_ACCESS_MENU_URL = "__UserInfoAccessMenuUrl";
    public static final String USER_INFO_SEARCH_CND = "__userInfoSearchCnd";
    public static final String USER_INFO_LOG_FORM_URL = Config.getString("user-config.urls.userLog");
    public static final String LOG_AUTO_RESN = "[자동로그]";

    /** 사용자정보와 관련된 메뉴 접속 시 비밀번호 확인 */
    public static final String USER_MENU_PASSED_VALIDATE = "__UserMenuPassed";
    public static final String VALIDATE_DISPATCHER_URL = "__ValidateDispatcherUrl";
    public static final String MENU_ACCESS_TIME = "__AccessTime";
    public static final String ACCESS_MENU_URL = "__AccessMenuUrl";
    public static final Integer SESSION_TIME_OUT = Config.getInt("user-config.protectedUserInfo.session-timeout");

    public static final String LOG_TYPE = Config.getString("user-config.userHist.logType", "personalLog");
    public static final String LOG_USE_YN = Config.getString("user-config.userHist.logUseYn", "Y");
    public static final String INIT_LIST_SHOW = Config.getString("user-config.userHist.initListShow", "N");
}
