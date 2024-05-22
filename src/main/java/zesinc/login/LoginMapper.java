/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import java.util.List;

import zesinc.login.domain.LoginAccesCtrlVO;
import zesinc.login.domain.LoginVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 로그인 정보 쿼리 메퍼 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 7.    방기배   최초작성
 *  2019. 7. 17.   황신욱   로그인실패건수 증가, 계정사용중지 추가
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opLoginDao")
public interface LoginMapper {

    /**
     * 담당자 정보 조회
     *
     * @param loginVo
     * @return
     */
    LoginVO selectLogin(LoginVO loginVo);

    /**
     * GPKI 인증정보로 담당자정보를 추출
     *
     * @param loginVo
     * @return
     */
    LoginVO selectGpkiLogin(LoginVO loginVo);

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
     * 로그인 실패 카운트 수정
     *
     * @param loginVo
     * @return
     */
    Integer updateLoginFailCnt(LoginVO loginVo);

    /**
     * 계정사용중지
     *
     * @param loginVo
     * @return
     */
    Integer updateMngrUseStop(LoginVO loginVo);
}
