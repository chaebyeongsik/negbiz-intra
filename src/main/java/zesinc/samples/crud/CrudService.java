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
import zesinc.web.support.pager.Pager;

/**
 * CRUD 셈플 프로그램 Service 인터페이스
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
public interface CrudService {

    /**
     * 상세 화면
     * 
     * @param crudVo
     * @return
     */
    CrudVO selectCrud(CrudVO crudVo);

    /**
     * 목록 화면
     * 
     * @param crudVo
     * @return
     */
    Pager<CrudVO> selectCrudList(CrudVO crudVo);

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
     * 목록 삭제
     * 
     * @param crudVo
     * @return
     */
    Integer deleteCrudList(CrudVO crudVo);

    /**
     * 답변 상세(답변 수정용)
     * 
     * @param crudVo
     * @return
     */
    CrudReplyVO selectCrudReply(CrudReplyVO crudReplyVo);

    /**
     * 답변 목록(원본 상세 화면 하단에 표시)
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
     * 답글 삭제
     * 
     * @param crudReplyVo
     * @return
     */
    Integer deleteCrudReply(CrudReplyVO crudReplyVo);

}
