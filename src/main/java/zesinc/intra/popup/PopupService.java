/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.popup;

import zesinc.intra.popup.domain.PopupVO;
import zesinc.web.support.pager.Pager;

/**
 * 팝업 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-20.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface PopupService {

    /**
     * 팝업 상세 조회
     * 
     * @param popupVo
     * @return
     */
    PopupVO selectPopup(PopupVO popupVo);

    /**
     * 팝업 목록 조회
     * 
     * @param popupVo
     * @return
     */
    Pager<PopupVO> selectPopupPageList(PopupVO popupVo);

    /**
     * 팝업 등록
     * 
     * @param popupVo
     * @return
     */
    Integer insertPopup(PopupVO popupVo);

    /**
     * 팝업 수정
     * 
     * @param popupVo
     * @return
     */
    Integer updatePopup(PopupVO popupVo);

    /**
     * 팝업 삭제
     * 
     * @param popupVo
     * @return
     */
    Integer deletePopup(PopupVO popupVo);

}
