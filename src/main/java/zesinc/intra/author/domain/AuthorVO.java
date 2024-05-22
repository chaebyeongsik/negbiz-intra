/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.author.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 권한 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-24.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class AuthorVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -926414443875746879L;
    /** 권한코드 */
    @Required
    @RangeLength(min = 1, max = 20)
    @AlphaNumeric
    private String authrtCdId;

    @RangeLength(min = 1, max = 20)
    private String newAuthrtCdId;

    /** 원래의 권한코드 */
    private String originalAuthrtCdId;

    /** 권한코드명 */
    private String authrtCdIdNm;

    /** 상위권한코드 */
    @RangeLength(min = 1, max = 20)
    private String upAuthrtCdId;

    /** 상위권한코드명 */
    private String upperAuthrtCdIdNm;

    /** 권한구분코드 */
    @RangeLength(min = 1, max = 20)
    private String authrtdSeCdId;

    /** 권한구분코드명 */
    private String authorSeCodeNm;

    /** 권한명 */
    @Required
    @RangeLength(min = 2, max = 100)
    private String authrtNm;

    /** 권한설명 */
    @RangeLength(min = 2, max = 4000)
    private String authrtExpln;

    /** 사용여부 */
    private String useYn;

    /** 등록자ID */
    @RangeLength(min = 2, max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    private Date regDt;

    /** 수정자ID */
    @RangeLength(min = 2, max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 권한당-담당자 수 */
    private Integer authorCnt;

    /** 메뉴코드 */
    private String menuSn;

    /** 권한할당코드 */
    private String authrtGrntCdId;

    /**
     * 권한코드을 설정
     *
     * @param authrtCdId을(를) String authrtCdId로 설정
     */
    public void setAuthrtCdId(String authrtCdId) {
        this.authrtCdId = authrtCdId;
    }

    /**
     * 권한코드을 반환
     *
     * @return String authrtCdId
     */
    public String getAuthrtCdId() {
        return authrtCdId;
    }


    /**
     * String newAuthrtCdId을 반환
     * @return String newAuthrtCdId
     */
    public String getNewAuthrtCdId() {
        return newAuthrtCdId;
    }


    /**
     * newAuthrtCdId을 설정
     * @param newAuthrtCdId 을(를) String newAuthrtCdId로 설정
     */
    public void setNewAuthrtCdId(String newAuthrtCdId) {
        this.newAuthrtCdId = newAuthrtCdId;
    }

    /**
     * String originalAuthrtCdId을 반환
     * @return String originalAuthrtCdId
     */
    public String getOriginalAuthrtCdId() {
        return originalAuthrtCdId;
    }

    /**
     * originalAuthrtCdId을 설정
     * @param originalAuthrtCdId 을(를) String originalAuthrtCdId로 설정
     */
    public void setOriginalAuthrtCdId(String originalAuthrtCdId) {
        this.originalAuthrtCdId = originalAuthrtCdId;
    }

    /**
     * 권한코드명을 설정
     *
     * @param authrtCdIdNm을(를) String authrtCdIdNm로 설정
     */
    public void setAuthrtCdIdNm(String authrtCdIdNm) {
        this.authrtCdIdNm = authrtCdIdNm;
    }

    /**
     * 권한코드명을 반환
     *
     * @return String authrtCdIdNm
     */
    public String getAuthrtCdIdNm() {
        return authrtCdIdNm;
    }

    /**
     * 상위권한코드을 설정
     *
     * @param upAuthrtCdId을(를) String upAuthrtCdId로 설정
     */
    public void setUpAuthrtCdId(String upAuthrtCdId) {
        this.upAuthrtCdId = upAuthrtCdId;
    }

    /**
     * 상위권한코드을 반환
     *
     * @return String upAuthrtCdId
     */
    public String getUpAuthrtCdId() {
        return upAuthrtCdId;
    }

    /**
     * 상위권한코드명을 설정
     *
     * @param upperAuthrtCdIdNm을(를) String upperAuthrtCdIdNm로 설정
     */
    public void setUpperAuthrtCdIdNm(String upperAuthrtCdIdNm) {
        this.upperAuthrtCdIdNm = upperAuthrtCdIdNm;
    }

    /**
     * 상위권한코드명을 반환
     *
     * @return String upperAuthrtCdIdNm
     */
    public String getUpperAuthrtCdIdNm() {
        return upperAuthrtCdIdNm;
    }

    /**
     * 권한구분코드을 설정
     *
     * @param authrtdSeCdId을(를) String authrtdSeCdId로 설정
     */
    public void setAuthrtSeCode(String authrtdSeCdId) {
        this.authrtdSeCdId = authrtdSeCdId;
    }

    /**
     * 권한구분코드을 반환
     *
     * @return String authrtdSeCdId
     */
    public String getAuthrtSeCode() {
        return authrtdSeCdId;
    }

    /**
     * 권한구분코드명을 설정
     *
     * @param authorSeCodeNm을(를) String authorSeCodeNm로 설정
     */
    public void setAuthorSeCodeNm(String authorSeCodeNm) {
        this.authorSeCodeNm = authorSeCodeNm;
    }

    /**
     * 권한구분코드명을 반환
     *
     * @return String authorSeCodeNm
     */
    public String getAuthorSeCodeNm() {
        return authorSeCodeNm;
    }

    /**
     * 권한명을 설정
     *
     * @param authrtNm을(를) String authrtNm로 설정
     */
    public void setAuthrtNm(String authrtNm) {
        this.authrtNm = authrtNm;
    }

    /**
     * 권한명을 반환
     *
     * @return String authrtNm
     */
    public String getAuthrtNm() {
        return authrtNm;
    }

    /**
     * 권한설명을 설정
     *
     * @param authrtExpln을(를) String authrtExpln로 설정
     */
    public void setAuthrtExpln(String authrtExpln) {
        this.authrtExpln = authrtExpln;
    }

    /**
     * 권한설명을 반환
     *
     * @return String authrtExpln
     */
    public String getAuthrtExpln() {
        return authrtExpln;
    }

    /**
     * 사용여부을 설정
     *
     * @param useYn을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 사용여부을 반환
     *
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * 등록자ID을 설정
     *
     * @param rgtrId을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
    }

    /**
     * 등록자ID을 반환
     *
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * 등록자명을 설정
     *
     * @param rgtrNm을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * 등록자명을 반환
     *
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * 등록일시을 설정
     *
     * @param regDt을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    /**
     * 등록일시을 반환
     *
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * 수정자ID을 설정
     *
     * @param mdfrId을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * 수정자ID을 반환
     *
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * 수정자명을 설정
     *
     * @param updusrNm을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * 수정자명을 반환
     *
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
    }

    /**
     * 수정일시을 설정
     *
     * @param updtDt을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * 수정일시을 반환
     *
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * Integer authorCnt을 반환
     * @return Integer authorCnt
     */
    public Integer getAuthorCnt() {
        return authorCnt;
    }

    /**
     * authorCnt을 설정
     * @param authorCnt 을(를) Integer authorCnt로 설정
     */
    public void setAuthorCnt(Integer authorCnt) {
        this.authorCnt = authorCnt;
    }

    /**
     * String menuSn을 반환
     * @return String menuSn
     */
    public String getMenuSn() {
        return menuSn;
    }

    /**
     * menuSn을 설정
     * @param menuSn 을(를) String menuSn로 설정
     */
    public void setMenuSn(String menuSn) {
        this.menuSn = menuSn;
    }

    /**
     * String authrtGrntCdId을 반환
     * @return String authrtGrntCdId
     */
    public String getAuthrtGrntCdId() {
        return authrtGrntCdId;
    }

    /**
     * authrtGrntCdId을 설정
     * @param authrtGrntCdId 을(를) String authrtGrntCdId로 설정
     */
    public void setAuthrtGrntCdId(String authrtGrntCdId) {
        this.authrtGrntCdId = authrtGrntCdId;
    }

}
