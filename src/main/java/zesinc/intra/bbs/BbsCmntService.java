/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs;

import zesinc.intra.bbs.domain.BbsCmntVO;
import zesinc.web.support.pager.Pager;

/**
 * 게시판커멘트 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-11-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface BbsCmntService {

    /**
     * 게시판커멘트 상세 조회
     * 
     * @param bbsCmntVo
     * @return
     */
    BbsCmntVO selectBbsCmnt(BbsCmntVO bbsCmntVo);

    /**
     * 게시판커멘트 목록 조회
     * 
     * @param bbsCmntVo
     * @return
     */
    Pager<BbsCmntVO> selectBbsCmntPageList(BbsCmntVO bbsCmntVo);

    /**
     * 게시판커멘트 등록
     * 
     * @param bbsCmntVo
     * @return
     */
    Integer insertBbsCmnt(BbsCmntVO bbsCmntVo);

    /**
     * 게시판커멘트 수정
     * 
     * @param bbsCmntVo
     * @return
     */
    Integer updateBbsCmnt(BbsCmntVO bbsCmntVo);

    /**
     * 관리자표시 제한
     * 
     * @param bbsCmntVo
     * @return
     */
    Integer updateBbsDisplayCmnt(BbsCmntVO bbsCmntVo);

    /**
     * 게시판커멘트 삭제
     * 
     * @param bbsCmntVo
     * @return
     */
    Integer deleteBbsCmnt(BbsCmntVO bbsCmntVo) throws Exception;

    /**
     * 게시물 삭제시 게시판커멘트 전체 삭제
     * 
     * @param bbsCmntVo
     * @return
     */
    Integer deleteBbsCmntAll(BbsCmntVO bbsCmntVo) throws Exception;
}
