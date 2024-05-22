/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.prhibt;

import java.util.List;

import zesinc.intra.prhibt.domain.PrhibtWrdHistoryVO;
import zesinc.intra.prhibt.domain.PrhibtWrdVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 금지단어 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-17.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opPrhibtWrdDao")
public interface PrhibtWrdMapper {

    /**
     * 금지단어 중복 체크
     * 
     * @param prhibtWrdVo
     * @return
     */
    Integer selectDplctChckCode(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어 상세 조회
     * 
     * @param prhibtWrdVo
     * @return
     */
    PrhibtWrdVO selectPrhibtWrd(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어 목록 조회
     * 
     * @param prhibtWrdVo
     * @return
     */
    List<PrhibtWrdVO> selectPrhibtWrdList(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어 목록 건수
     * 
     * @param prhibtWrdVo
     * @return
     */
    Integer selectPrhibtWrdListCount(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어 등록
     * 
     * @param prhibtWrdVo
     * @return
     */
    Integer insertPrhibtWrd(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어 수정
     * 
     * @param prhibtWrdVo
     * @return
     */
    Integer updatePrhibtWrd(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어 삭제
     * 
     * @param prhibtWrdVo
     * @return
     */
    Integer deletePrhibtWrd(PrhibtWrdVO prhibtWrdVo);

    /**
     * 금지단어이력 상세 조회
     * 
     * @param prhibtWrdHistoryVo
     * @return
     */
    PrhibtWrdHistoryVO selectPrhibtWrdHistory(PrhibtWrdHistoryVO prhibtWrdHistoryVo);

    /**
     * 금지단어이력 목록 조회
     * 
     * @param prhibtWrdHistoryVo
     * @return
     */
    List<PrhibtWrdHistoryVO> selectPrhibtWrdHistoryList(PrhibtWrdHistoryVO prhibtWrdHistoryVo);

    /**
     * 금지단어이력 목록 건수
     * 
     * @param prhibtWrdHistoryVo
     * @return
     */
    Integer selectPrhibtWrdHistoryListCount(PrhibtWrdHistoryVO prhibtWrdHistoryVo);

    /**
     * 금지단어이력 등록
     * 
     * @param prhibtWrdVo
     * @return
     */
    Integer insertPrhibtWrdHistory(PrhibtWrdVO prhibtWrdVo);
}
