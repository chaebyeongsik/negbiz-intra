/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.multilang;

import java.util.List;

import zesinc.intra.multilang.domain.MultilangVO;
import zesinc.web.vo.TreeVO;

/**
 * 다국어 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-13.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface MultilangService {

    /**
     * 다국어 코드 중복 체크
     * 
     * @param multilangVo
     * @return
     */
    Integer selectDplctChckMultilang(MultilangVO multilangVo);

    /**
     * 다국어 상세 조회
     * 
     * @param multilangVo
     * @param isUpdate 수정정보에 사용할지 여부
     * @return
     */
    MultilangVO selectMultilang(MultilangVO multilangVo, Boolean isUpdate);

    /**
     * 다국어 트리 목록 조회
     * 
     * @param multilangVo
     * @return
     */
    List<TreeVO> selectMultilangTreeList(MultilangVO multilangVo);

    /**
     * 다국어 등록
     * 
     * @param multilangVo
     * @return
     */
    String insertMultilang(MultilangVO multilangVo);

    /**
     * 다국어 수정
     * 
     * @param multilangVo
     * @return
     * @throws Exception 수정된 갯수가 1개를 초과 하는 경우 시스템 오류 발생으로 처리
     */
    Integer updateMultilang(MultilangVO multilangVo) throws Exception;

    /**
     * 다국어 삭제
     * 
     * @param multilangVo
     * @return
     * @throws Exception 삭제된 갯수가 1개를 초과 하는 경우 시스템 오류 발생으로 처리
     */
    Integer deleteMultilang(MultilangVO multilangVo) throws Exception;

    /**
     * 다국어그룹 목록을 삭제후 신규로 등록
     * 
     * @param multilangGrpVo
     * @return
     */
    Integer insertMultilangGrps(MultilangVO multilangVo);

    /**
     * 기능별 연계 화면 다국어 트리 목록 조회
     * 
     * @param multilangVo
     * @return
     */
    List<TreeVO> selectConnectMultilangTreeList(MultilangVO multilangVo);
}
