/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce;

import zesinc.intra.resrce.domain.ResrceVO;
import zesinc.web.support.pager.Pager;

/**
 * 자원 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-14.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface ResrceService {

    /**
     * 자원 상세 조회
     * 
     * @param resrceVo
     * @return
     */
    ResrceVO selectResrce(ResrceVO resrceVo);

    /**
     * 자원 목록 조회
     * 
     * @param resrceVo
     * @return
     */
    Pager<ResrceVO> selectResrcePageList(ResrceVO resrceVo);

    /**
     * 자원 등록
     * 
     * @param resrceVo
     * @return
     */
    Integer insertResrce(ResrceVO resrceVo);

    /**
     * 자원 수정
     * 
     * @param resrceVo
     * @return
     */
    Integer updateResrce(ResrceVO resrceVo);

    /**
     * 자원카테고리 이동 수정
     * 
     * @param resrceVo
     * @return
     */
    Integer updateResrceCtgrySn(ResrceVO resrceVo);

    /**
     * 자원 삭제
     * 
     * @param resrceVo
     * @return
     * @throws Exception
     */
    Integer deleteResrce(ResrceVO resrceVo) throws Exception;

}
