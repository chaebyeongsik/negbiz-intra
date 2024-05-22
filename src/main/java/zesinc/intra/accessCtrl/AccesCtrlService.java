/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.accessCtrl;

import java.util.List;

import zesinc.intra.accessCtrl.domain.AccesCtrlVO;

/**
 * 접근제어 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-02.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface AccesCtrlService {

    /**
     * 담당자목록 조회(Auto Complete 용)
     * 
     * @param accesCtrlVo
     * @return
     */
    List<AccesCtrlVO> selectMngrList(AccesCtrlVO accesCtrlVo);

    /**
     * 접근제어 상세 조회
     * 
     * @param accesCtrlVo
     * @return
     */
    AccesCtrlVO selectAccesCtrl(AccesCtrlVO accesCtrlVo);

    /**
     * 접근제어 목록 조회
     * 
     * @param accesCtrlVo
     * @return
     */
    List<AccesCtrlVO> selectAccesCtrlList(AccesCtrlVO accesCtrlVo);

    /**
     * 접근제어 등록
     * 
     * @param accesCtrlVo
     * @return
     */
    Integer insertAccesCtrl(AccesCtrlVO accesCtrlVo);

    /**
     * 접근제어 수정
     * 
     * @param accesCtrlVo
     * @return
     */
    Integer updateAccesCtrl(AccesCtrlVO accesCtrlVo);

    /**
     * 접근제어 삭제
     * 
     * @param accesCtrlVo
     * @return
     */
    Integer deleteAccesCtrl(AccesCtrlVO accesCtrlVo) throws Exception;

}
