/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code;

import java.util.List;

import zesinc.intra.code.domain.CodeChoiceVO;
import zesinc.intra.code.domain.CodeVO;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 코드 정보 DAO 클레스
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
@Mapper("opCodeDao")
public interface CodeMapper {

    /**
     * 코드 중복 체크
     * 
     * @param codeVo
     * @return
     */
    Integer selectDplctChckCode(CodeVO codeVo);

    /**
     * 코드선택 중복 체크
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer selectDplctChckCodeChoice(CodeChoiceVO codeChoiceVo);

    /**
     * 코드 상세 조회
     * 
     * @param codeVo
     * @return
     */
    CodeVO selectCode(CodeVO codeVo);

    /**
     * 코드 트리 목록 조회
     * 
     * @param codeVo
     * @return
     */
    List<TreeVO> selectCodeTreeList(CodeVO codeVo);

    /**
     * 최상위 코드 목록 조회
     * 
     * @param codeVo
     * @return
     */
    List<CodeVO> selectHghrkCdIdList(CodeVO codeVo);

    /**
     * 코드 목록 조회
     * 
     * @param codeVo
     * @return
     */
    List<CodeVO> selectCodeList(CodeVO codeVo);

    /**
     * 코드 목록 건수
     * 
     * @param codeVo
     * @return
     */
    Integer selectCodeListCount(CodeVO codeVo);

    /**
     * 코드 등록
     * 
     * @param codeVo
     * @return
     */
    Integer insertCode(CodeVO codeVo);

    /**
     * 코드 수정
     * 
     * @param codeVo
     * @return
     */
    Integer updateCode(CodeVO codeVo);

    /**
     * 자식 코드목록
     * 
     * @param codeVo
     * @return
     */
    List<CodeVO> selectChildCodeList(CodeVO codeVo);

    /**
     * 코드 정렬순서 수정
     * 
     * @param codeVo
     * @return
     */
    Integer updateCodeSortOrder(CodeVO codeVo);

    /**
     * 코드 삭제
     * 
     * @param codeVo
     * @return
     */
    Integer deleteCode(CodeVO codeVo);

    /**
     * 코드선택 상세 조회
     * 
     * @param codeChoiceVo
     * @return
     */
    CodeChoiceVO selectCodeChoice(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택 목록 조회
     * 
     * @param codeChoiceVo
     * @return
     */
    List<CodeChoiceVO> selectCodeChoiceList(CodeChoiceVO codeChoiceVo);
    
    /**
     * 코드선택 별 코드 목록 가져오기
     * @param codeChoiceVo
     * @return
     */
    List<CodeChoiceVO> selectCodeChoiceCodeList(CodeChoiceVO codeChoiceVo);
    

    /**
     * 코드선택 목록그룹 조회
     * 
     * @param codeChoiceVo
     * @return
     */
    List<CodeChoiceVO> selectCodeChoiceGroupList(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택 트리 목록 조회
     * 
     * @param codeChoiceVo
     * @return
     */
    List<TreeVO> selectCodeChoiceInsertTreeList(CodeChoiceVO codeChoiceVo);

    /**
     * 수정페이지 코드선택 트리 목록 조회
     * 
     * @param codeChoiceVo
     * @return
     */
    List<TreeVO> selectCodeChoiceUpdateTreeList(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택 목록 건수
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer selectCodeChoiceListCount(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택 등록
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer insertCodeChoice(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택 수정
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer updateCodeChoice(CodeChoiceVO codeChoiceVo);

    /**
     * 코드선택 삭제
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer deleteCodeChoice(CodeChoiceVO codeChoiceVo);

    /**
     * 코드 삭제에 따른 코드선택 목록 조회
     * 
     * @param codeChoiceVo
     * @return
     */
    List<CodeChoiceVO> selectCodeAndCodeChoiceList(CodeChoiceVO codeChoiceVo);

    /**
     * 코드 삭제에 따른 코드선택 삭제
     * 
     * @param codeChoiceVo
     * @return
     */
    Integer deleteCodeAndCodeChoice(CodeChoiceVO codeChoiceVo);

}
