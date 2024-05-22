/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code;

import zesinc.intra.code.domain.CodeChoiceHistoryVO;
import zesinc.intra.code.domain.CodeChoiceVO;
import zesinc.intra.code.domain.CodeHistoryVO;
import zesinc.intra.code.domain.CodeVO;
import zesinc.web.support.pager.Pager;

/**
 * 코드이력 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface CodeHistoryService {

    /**
     * 코드이력 상세 조회
     * 
     * @param codeHistoryVo
     * @return
     */
    CodeHistoryVO selectCodeHistory(CodeHistoryVO codeHistoryVo);

    /**
     * 코드이력 목록 조회
     * 
     * @param codeHistoryVo
     * @return
     */
    Pager<CodeHistoryVO> selectCodeHistoryPageList(CodeHistoryVO codeHistoryVo);

    /**
     * 코드이력 등록
     * 
     * @param codeVo
     * @return
     */
    Integer insertCodeHistory(CodeVO codeVo);

    /**
     * 코드이력 삭제
     * 
     * @param codeHistoryVo
     * @return
     */
    Integer deleteCodeHistory(CodeHistoryVO codeHistoryVo);

    /**
     * 코드선택이력 상세 조회
     * 
     * @param codeChoiceHistoryVo
     * @return
     */
    CodeChoiceHistoryVO selectCodeChoiceHistory(CodeChoiceHistoryVO codeChoiceHistoryVo);

    /**
     * 코드선택이력 목록 조회
     * 
     * @param codeChoiceHistoryVo
     * @return
     */
    Pager<CodeChoiceHistoryVO> selectCodeChoiceHistoryPageList(CodeChoiceHistoryVO codeChoiceHistoryVo);

    /**
     * 코드선택이력 등록
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer insertCodeChoiceHistory(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택이력 삭제
     * 
     * @param codeChoiceHistoryVo
     * @return
     */
    Integer deleteCodeChoiceHistory(CodeChoiceHistoryVO codeChoiceHistoryVo);

}
