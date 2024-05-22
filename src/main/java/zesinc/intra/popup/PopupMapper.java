/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.popup;

import java.util.List;

import zesinc.intra.popup.domain.PopupDomnVO;
import zesinc.intra.popup.domain.PopupVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 팝업 정보 DAO 클레스
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
@Mapper("opPopupDao")
public interface PopupMapper {

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
    List<PopupVO> selectPopupList(PopupVO popupVo);

    /**
     * 팝업 목록 건수
     * 
     * @param popupVo
     * @return
     */
    Integer selectPopupListCount(PopupVO popupVo);

    /**
     * 팝업 등록
     * 
     * @param popupVo
     * @return
     */
    Integer insertPopup(PopupVO popupVo);

    /**
     * 팝업 정렬 순서 수정
     * 
     * @param popupVo
     * @return
     */
    Integer updatePopupSortSn(PopupVO popupVo);

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

    /**
     * 팝업 도메인 목록
     * 
     * @param popupVo
     * @return
     */
    List<PopupDomnVO> selectPopupDomnList(PopupVO popupVo);

    /**
     * 팝업 도메인 등록
     * 
     * @param popupDomnVo
     * @return
     */
    Integer insertPopupDomn(PopupDomnVO popupDomnVo);

    /**
     * 팝업 도메인 삭제
     * 
     * @param popupVo
     * @return
     */
    Integer deletePopupDomn(PopupVO popupVo);
}
