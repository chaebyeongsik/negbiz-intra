/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.samples.crud;

import java.util.List;

import zesinc.samples.crud.domain.CrudReplyVO;
import zesinc.samples.crud.domain.CrudVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * CRUD 셈플 쿼리 매퍼 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 1. 11.    방기배   최초작성
 * </pre>
 * @see
 */
@Mapper("opCrudDao")
public interface CrudMapper {

    /**
     * 상세 조회
     * 
     * @param crudVo
     * @return
     */
    CrudVO selectCrud(CrudVO crudVo);

    /**
     * 조회수 증가
     * 
     * @param crudVo
     * @return
     */
    int increase(CrudVO crudVo);

    /**
     * 목록 조회
     * 
     * @param crudVo
     * @return
     */
    List<CrudVO> selectCrudList(CrudVO crudVo);

    /**
     * 목록조회 건수
     * 
     * @param crudVo
     * @return
     */
    int selectCrudListCount(CrudVO crudVo);

    /**
     * 등록
     * 
     * @param crudVo
     * @return
     */
    Integer insertCrud(CrudVO crudVo);

    /**
     * 수정
     * 
     * @param crudVo
     * @return
     */
    Integer updateCrud(CrudVO crudVo);

    /**
     * 삭제
     * 
     * @param crudVo
     * @return
     */
    Integer deleteCrud(CrudVO crudVo);

    /**
     * 답변 상세(답변 수정용)
     * 
     * @param crudReplyVo
     * @return
     */
    CrudReplyVO selectCrudReply(CrudReplyVO crudReplyVo);

    /**
     * 답변 목록
     * 
     * @param crudReplyVo
     * @return
     */
    List<CrudReplyVO> selectCrudReplyList(CrudReplyVO crudReplyVo);

    /**
     * 답변 등록
     * 
     * @param crudReplyVo
     * @return
     */
    Object insertCrudReply(CrudReplyVO crudReplyVo);

    /**
     * 답변 수정
     * 
     * @param crudReplyVo
     * @return
     */
    Integer updateCrudReply(CrudReplyVO crudReplyVo);

    /**
     * 답변 삭제
     * 
     * @param crudReplyVo
     * @return
     */
    Integer deleteCrudReply(CrudReplyVO crudReplyVo);

    /**
     * 답변 목록 삭제
     * 
     * @param crudReplyVo
     * @return
     */
    Integer deleteCrudReplyList(CrudReplyVO crudReplyVo);

    /**
     * 답변 여부 수정
     * 
     * @param crudReplyVo
     * @return
     */
    int updateCrudReplyYn(CrudReplyVO crudReplyVo);
}
