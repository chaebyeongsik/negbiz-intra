/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul;

import java.util.List;

import zesinc.intra.schdul.domain.SchdulDetailVO;
import zesinc.intra.schdul.domain.SchdulVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 일정 정보 DAO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-12-08.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opSchdulDao")
public interface SchdulMapper {

    /**
     * 일정 상세 조회
     * 
     * @param schdulVo
     * @return
     */
    SchdulVO selectSchdul(SchdulVO schdulVo);

    /**
     * 일정 목록 조회
     * 
     * @param schdulVo
     * @return
     */
    List<SchdulVO> selectSchdulList(SchdulVO schdulVo);

    /**
     * 일정 목록 건수
     * 
     * @param schdulVo
     * @return
     */
    Integer selectSchdulListCount(SchdulVO schdulVo);

    /**
     * 일정 등록
     * 
     * @param schdulVo
     * @return
     */
    Integer insertSchdul(SchdulVO schdulVo);

    /**
     * 일정 수정
     * 
     * @param schdulVo
     * @return
     */
    Integer updateSchdul(SchdulVO schdulVo);

    /**
     * 일정 삭제
     * 
     * @param schdulVo
     * @return
     */
    Integer deleteSchdul(SchdulVO schdulVo);

    /**
     * 일정상세 목록 조회
     * 
     * @param schdulVo
     * @return
     */
    List<SchdulVO> selectSchdulDetailList(SchdulVO schdulVo);

    /**
     * 일정상세 등록
     * 
     * @param schdulDetailVo
     * @return
     */
    Integer insertSchdulDetail(SchdulDetailVO schdulDetailVo);

    /**
     * 일정상세 삭제
     * 
     * @param schdulDetailVo
     * @return
     */
    Integer deleteSchdulDetail(SchdulDetailVO schdulDetailVo);

}
