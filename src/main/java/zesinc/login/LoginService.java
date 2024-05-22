/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import java.util.List;

import zesinc.intra.mngr.connect.domain.ConnectVO;
import zesinc.login.domain.LoginAccesCtrlVO;
import zesinc.login.domain.LoginVO;

/**
 * 담당자 로그인 서비스 인터페이스
 *
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
public interface LoginService {

    /**
     * 로그인 처리(DB 정보를
     *
     * @param loginVo
     * @return
     */
    LoginVO processLogin(LoginVO loginVo);

    /**
     * GPKI 인증정보로 담당자정보를 추출
     *
     * @param loginVo
     * @return
     */
    LoginVO processGpkiLogin(LoginVO loginVo);

    /**
     * 접근제어 정보 목록
     *
     * @param loginAccesCtrlVo
     * @return
     */
    List<LoginAccesCtrlVO> selectAccessCtrlList(LoginAccesCtrlVO loginAccesCtrlVo);

    /**
     * 최종 로그인 정보 수정
     *
     * @param loginVo
     * @return
     */
    Integer updateLoginCnt(LoginVO loginVo);

    /**
     * 접속이력 등록
     *
     * @return
     */
    Integer insertConnect(ConnectVO connectVo);

    /**
     * 로그인실패
     *
     * @return
     */
    Integer updateLoginFail(LoginVO loginVo);

}
