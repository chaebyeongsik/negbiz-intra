/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mymenu.domain;

import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 내메뉴 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-21.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */
public class MyMenuVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 3690470008939277477L;
    /** 담당자ID */
    @Required
    @MaxLength(max = 30)
    private String picId;

    /** 메뉴코드 */
    @Required
    @Digits
    private Integer menuSn;

    /** 메뉴명 */
    private String menuNm;

    /** 정렬순서 */
    @Digits
    private Integer sortSn;

    /** 메뉴코드 */
    @Required
    private List<Integer> menuSns;

    /**
     * 담당자ID 설정
     *
     * @param picId을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * 담당자ID 반환
     *
     * @return String picId
     */
    public String getPicId() {
        return picId;
    }

    /**
     * 메뉴코드 설정
     *
     * @param menuSn을(를) Integer menuSn로 설정
     */
    public void setMenuSn(Integer menuSn) {
        this.menuSn = menuSn;
    }

    /**
     * 메뉴코드 반환
     *
     * @return Integer menuSn
     */
    public Integer getMenuSn() {
        return menuSn;
    }

    /**
     * 메뉴명 설정
     *
     * @param menuNm을(를) String menuNm로 설정
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    /**
     * 메뉴명 반환
     *
     * @return String menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }

    /**
     * 정렬순서 설정
     *
     * @param sortSn을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
    }

    /**
     * 정렬순서 반환
     *
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
    }

    /**
     * List<Integer> menuSns을 반환
     * 
     * @return List<Integer> menuSns
     */
    public List<Integer> getMenuSns() {
        return menuSns;
    }

    /**
     * menuSns을 설정
     * 
     * @param menuSns 을(를) List<Integer> menuSns로 설정
     */
    public void setMenuSns(List<Integer> menuSns) {
        this.menuSns = menuSns;
    }

}
