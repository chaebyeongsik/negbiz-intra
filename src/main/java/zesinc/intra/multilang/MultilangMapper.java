/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.multilang;

import java.util.List;

import zesinc.intra.multilang.domain.MultilangGrpVO;
import zesinc.intra.multilang.domain.MultilangVO;
import zesinc.web.vo.TreeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 다국어 정보 DAO 클레스
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
@Mapper("opMultilangDao")
public interface MultilangMapper {

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
     * @return
     */
    MultilangVO selectMultilang(MultilangVO multilangVo);

    /**
     * 다국어 트리 목록 조회
     * 
     * @param multilangVo
     * @return
     */
    List<TreeVO> selectMultilangTreeList(MultilangVO multilangVo);

    /**
     * 다국어 목록 조회
     * 
     * @param multilangVo
     * @return
     */
    List<MultilangVO> selectMultilangList(MultilangVO multilangVo);

    /**
     * 다국어 목록 건수
     * 
     * @param multilangVo
     * @return
     */
    Integer selectMultilangListCount(MultilangVO multilangVo);

    /**
     * 다국어 등록
     * 
     * @param multilangVo
     * @return
     */
    Integer insertMultilang(MultilangVO multilangVo);

    /**
     * 다국어 수정
     * 
     * @param multilangVo
     * @return
     */
    Integer updateMultilang(MultilangVO multilangVo);

    /**
     * 다국어 삭제
     * 
     * @param multilangVo
     * @return
     */
    Integer deleteMultilang(MultilangVO multilangVo);

    /**
     * 다국어그룹 상세 조회
     * 
     * @param multilangGrpVo
     * @return
     */
    MultilangGrpVO selectMultilangGrp(MultilangGrpVO multilangGrpVo);

    /**
     * 다국어그룹 목록 조회
     * 
     * @param multilangGrpVo
     * @return
     */
    List<MultilangGrpVO> selectMultilangGrpList(MultilangGrpVO multilangGrpVo);

    /**
     * 다국어그룹 목록 건수
     * 
     * @param multilangGrpVo
     * @return
     */
    Integer selectMultilangGrpListCount(MultilangGrpVO multilangGrpVo);

    /**
     * 다국어그룹 등록
     * 
     * @param multilangGrpVo
     * @return
     */
    Integer insertMultilangGrp(MultilangGrpVO multilangGrpVo);

    /**
     * 다국어그룹 수정
     * 
     * @param multilangGrpVo
     * @return
     */
    Integer updateMultilangGrp(MultilangGrpVO multilangGrpVo);

    /**
     * 다국어그룹 삭제
     * 
     * @param multilangGrpVo
     * @return
     */
    Integer deleteMultilangGrp(MultilangGrpVO multilangGrpVo);

    /**
     * 기능별 연계 화면 다국어 트리 목록 조회
     * 
     * @param multilangVo
     * @return
     */
    List<TreeVO> selectConnectMultilangTreeList(MultilangVO multilangVo);
}
