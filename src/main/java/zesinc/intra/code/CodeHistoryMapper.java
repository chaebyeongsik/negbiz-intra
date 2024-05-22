/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code;

import java.util.List;

import zesinc.intra.code.domain.CodeChoiceHistoryVO;
import zesinc.intra.code.domain.CodeChoiceVO;
import zesinc.intra.code.domain.CodeHistoryVO;
import zesinc.intra.code.domain.CodeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 코드이력 정보 DAO 클레스
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
@Mapper("opCodeHistoryDao")
public interface CodeHistoryMapper {

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
    List<CodeHistoryVO> selectCodeHistoryList(CodeHistoryVO codeHistoryVo);

    /**
     * 코드이력 목록 건수
     * 
     * @param codeHistoryVo
     * @return
     */
    Integer selectCodeHistoryListCount(CodeHistoryVO codeHistoryVo);

    /**
     * 코드이력 등록
     * 
     * @param codeVo
     * @return
     */
    Integer insertCodeHistory(CodeVO codeVo);

    /**
     * 코드이력 수정
     * 
     * @param codeHistoryVo
     * @return
     */
    Integer updateCodeHistory(CodeHistoryVO codeHistoryVo);

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
    List<CodeChoiceHistoryVO> selectCodeChoiceHistoryList(CodeChoiceHistoryVO codeChoiceHistoryVo);

    /**
     * 코드선택이력 목록 건수
     * 
     * @param codeChoiceHistoryVo
     * @return
     */
    Integer selectCodeChoiceHistoryListCount(CodeChoiceHistoryVO codeChoiceHistoryVo);

    /**
     * 코드선택이력 등록
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer insertCodeChoiceHistory(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택이력 수정
     * 
     * @param codeChoiceHistoryVo
     * @return
     */
    Integer updateCodeChoiceHistory(CodeChoiceHistoryVO codeChoiceHistoryVo);

    /**
     * 코드선택이력 삭제
     * 
     * @param codeChoiceHistoryVo
     * @return
     */
    Integer deleteCodeChoiceHistory(CodeChoiceHistoryVO codeChoiceHistoryVo);

}
