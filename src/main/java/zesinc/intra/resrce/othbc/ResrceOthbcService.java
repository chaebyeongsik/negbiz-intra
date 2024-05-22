/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.othbc;

import zesinc.intra.resrce.domain.ResrceVO;
import zesinc.web.support.pager.Pager;

/**
 * 공개 자원 정보 서비스 클레스
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
public interface ResrceOthbcService {

    /**
     * 자원 상세 조회
     * 
     * @param resrceVo
     * @return
     */
    ResrceVO selectResrceOthbc(ResrceVO resrceVo);

    /**
     * 자원 목록 조회
     * 
     * @param resrceVo
     * @return
     */
    Pager<ResrceVO> selectResrceOthbcPageList(ResrceVO resrceVo);

}
