/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.popup.domain;

import zesinc.web.vo.BaseVO;

/**
 * 팝업도메인 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class PopupDomnVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 4013544014905456655L;

    /** 일련번호 */
    private Integer regSn;
    /** 도메인코드 */
    private Integer siteSn;
    /** 도메인명 */
    private String siteNm;
    /** 도메인설명 */
    private String siteExpln;

    /**
     * Integer regSn을 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * regSn을 설정
     * 
     * @param regSn 을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * Integer siteSn을 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * siteSn을 설정
     * 
     * @param siteSn 을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * String siteNm을 반환
     * 
     * @return String siteNm
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * siteNm을 설정
     * 
     * @param siteNm 을(를) String siteNm로 설정
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    /**
     * String siteExpln을 반환
     * 
     * @return String siteExpln
     */
    public String getSiteExpln() {
        return siteExpln;
    }

    /**
     * siteExpln을 설정
     * 
     * @param siteExpln 을(를) String siteExpln로 설정
     */
    public void setSiteExpln(String siteExpln) {
        this.siteExpln = siteExpln;
    }
}
