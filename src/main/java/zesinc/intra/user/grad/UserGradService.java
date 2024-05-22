/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.grad;

import java.util.List;

import zesinc.intra.user.grad.domain.UserGradVO;

/**
 * 사용자 등급 관리
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 8.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface UserGradService {

    /**
     * 사용자 등급 목록 조회
     * 
     * @param userGradVo
     * @return
     */
    List<UserGradVO> selectUserGradList();
    
    /**
     * 사용자 등급 목록 조회
     * 
     * @param userGradVo
     * @return
     */
    List<UserGradVO> selectUserGradList(UserGradVO userGradVo);

    /**
     * 사용자 등급 목록 조회(사용중인것만)
     * 
     * @param userGradVo
     * @return
     */
    List<UserGradVO> selectUserGradInUseList();

    /**
     * 사용자 등급코드 중복체크
     * 
     * @param userGradVo
     * @return
     */
    Integer selectDplctChckGrad(UserGradVO userGradVo);

    /**
     * 사용자 등급 상세 조회
     * 
     * @param userGradVo
     * @return
     */
    UserGradVO selectUserGrad(UserGradVO userGradVo);

    /**
     * 사용자 등급 등록 처리
     * 
     * @param userGradVo
     * @return
     */
    Integer insertUserGrad(UserGradVO userGradVo);

    /**
     * 사용자 등급 수정 처리
     * 
     * @param userGradVo
     * @return
     */
    Integer updateUserGrad(UserGradVO userGradVo);
}
