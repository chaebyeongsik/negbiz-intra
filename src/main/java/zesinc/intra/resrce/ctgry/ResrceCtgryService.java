/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.ctgry;

import java.util.List;

import zesinc.intra.resrce.ctgry.domain.ResrceCtgryVO;
import zesinc.web.vo.TreeVO;

/**
 * 자원카테고리 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-15.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface ResrceCtgryService {

    /**
     * 자원카테고리 상세 조회
     * 
     * @param resrceCtgryVo
     * @return
     */
    ResrceCtgryVO selectResrceCtgry(ResrceCtgryVO resrceCtgryVo);

    /**
     * 자원카테고리 트리 목록 조회
     * 
     * @param resrceCtgryVo
     * @return
     */
    List<TreeVO> selectResrceCtgryTreeList(ResrceCtgryVO resrceCtgryVo);

    /**
     * 자원카테고리 목록 조회
     * 
     * @param resrceCtgryVo
     * @return
     */
    List<ResrceCtgryVO> selectResrceCtgryList(ResrceCtgryVO resrceCtgryVo);

    /**
     * 자원카테고리 등록
     * 
     * @param resrceCtgryVo
     * @return
     */
    Integer insertResrceCtgry(ResrceCtgryVO resrceCtgryVo);

    /**
     * 자원카테고리 수정
     * 
     * @param resrceCtgryVo
     * @return
     */
    Integer updateResrceCtgry(ResrceCtgryVO resrceCtgryVo);

    /**
     * 자원카테고리 정렬순서 수정
     * 
     * @param resrceCtgryVo
     * @return
     */
    Boolean updateResrceCtgrySortOrder(ResrceCtgryVO resrceCtgryVo);

    /**
     * 자원카테고리 삭제
     * 
     * @param resrceCtgryVo
     * @return
     * @throws Exception
     */
    Integer deleteResrceCtgry(ResrceCtgryVO resrceCtgryVo) throws Exception;

}
