/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.hist;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import zesinc.intra.user.hist.domain.UserHistCheckResultVO;
import zesinc.intra.user.hist.domain.UserHistVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.support.pager.Pager;

/**
 * 사용자이력 정보 서비스 클래스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 22.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface UserHistService {

    /**
     * 사용자 로그 등록
     * 
     * @param userHistVo
     * @return
     */
    Integer insertUserLog(UserHistVO userHistVo);

    /**
     * 사용자정보 관련 메뉴 접근 시 유효성 체크
     * (사용자정보 보호와 관련하여 사유를 입력한 사람에게만 접근권한을 부여)
     * 
     * @param request
     * @param userManageVo
     * @param menuNm
     * @return
     */
    boolean accessValidate(HttpServletRequest request);

    // boolean accessValidate(HttpServletRequest request, UserManageVO userManageVo, String menuNm);

    /**
     * 사용자정보 관련 메뉴 접근 시 사용했던 유효성 세션 제거
     * 
     * @param request
     */
    void removeValidate(HttpServletRequest request);

    /**
     * 현재 로그인한 담당자 비밀번호와 입력받은 비밀번호가 동일한지 여부 판단
     * 
     * @param userManageVo
     * @return
     */
    boolean selectCheckMngrPasswordMatch(LoginVO loginVo, UserHistVO userHistVo);

    /**
     * 목록 카운팅
     * 
     * @param userHistVo
     * @return
     */
    Integer selectListCount(String queryId, UserHistVO userHistVo);

    /**
     * ID조회
     * 
     * @param userHistVo
     * @return
     */
    String selectUserId(String queryId, UserHistVO userHistVo);

    /**
     * 검색조건 비교
     * 
     * @param request
     * @param nowSearchCnd
     * @return
     */
    UserHistCheckResultVO checkSearchCnd(HttpServletRequest request, Map<String, Object> nowSearchCnd);

    /**
     * 사용자 로그 목록 조회
     * 
     * @param userHistVo
     * @return
     */
    Pager<UserHistVO> selectUserLogList(UserHistVO userHistVo);

    /**
     * 메뉴명목록 조회
     * 
     * @return
     */
    List<UserHistVO> selectMenuNmList();
}
