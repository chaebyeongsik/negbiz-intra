/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.domn.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.Numeric;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 도메인 정보 VO 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-02-27.    방기배   최초작성
 * </pre>
 * @see
 */
public class DomnVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 6246481434288012944L;
    /** 도메인코드 */
    @Required
    @Numeric
    private Integer siteSn;

    /** 도메인명 */
    @Required
    @RangeLength(max = 100)
    private String siteNm;

    /** 도메인설명 */
    @Required
    @RangeLength(max = 4000)
    private String siteExpln;

    /** 포트번호 */
    @Required
    @Digits
    private Integer portSn;

    /** 컨텍스트경로 */
    @Required
    @RangeLength(max = 80)
    private String sitePathNm;

    /** 언어코드 */
    @Required
    private String langCdId;

    /** 담당부서코드 */
    @RangeLength(max = 20)
    private String tkcgDeptCdId;

    /** 담당자ID */
    @RangeLength(max = 20)
    private String picId;

    /** 보안프로토콜여부 */
    @Required
    private String httpsYn;

    /** 보안포트번호 */
    @Digits
    private Integer scrtyPortSn;

    /** 실컨텍스트여부 */
    @Required
    private String actlStngYn;

    /** 사용여부 */
    @Required
    private String useYn;

    /** 등록자ID */
    @RangeLength(max = 20)
    private String rgtrId;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 수정자ID */
    @RangeLength(max = 20)
    private String mdfrId;

    /** 수정일시 */
    private Date updtDt;

    /** 담당부서명 */
    private String chrgDeptNm;
    /** 담당자명 */
    private String picNm;
    /** 등록자명 */
    private String rgtrNm;
    /** 수정자명 */
    private String updusrNm;

    /** 서버그룹IP 목록 */
    private String[] srvrIpAddr;
    /** 서버그룹 포트목록 */
    private Integer[] groupPortSn;

    /** 도메인 그룹 목록 */
    private List<DomnGroupVO> domnGroupList;

    /**
     * 도메인코드을 설정
     * 
     * @param siteSn을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * 도메인코드을 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * 도메인명을 설정
     * 
     * @param siteNm을(를) String siteNm로 설정
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    /**
     * 도메인명을 반환
     * 
     * @return String siteNm
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * 도메인설명을 설정
     * 
     * @param siteExpln을(를) String siteExpln로 설정
     */
    public void setSiteExpln(String siteExpln) {
        this.siteExpln = siteExpln;
    }

    /**
     * 도메인설명을 반환
     * 
     * @return String siteExpln
     */
    public String getSiteExpln() {
        return siteExpln;
    }

    /**
     * 포트번호을 설정
     * 
     * @param portSn을(를) Integer portSn로 설정
     */
    public void setPortSn(Integer portSn) {
        this.portSn = portSn;
    }

    /**
     * 포트번호을 반환
     * 
     * @return Integer portSn
     */
    public Integer getPortSn() {
        return portSn;
    }

    /**
     * 컨텍스트경로을 설정
     * 
     * @param sitePathNm을(를) String sitePathNm로 설정
     */
    public void setSitePathNm(String sitePathNm) {
        this.sitePathNm = sitePathNm;
    }

    /**
     * 컨텍스트경로을 반환
     * 
     * @return String sitePathNm
     */
    public String getSitePathNm() {
        return sitePathNm;
    }

    /**
     * 언어코드을 설정
     * 
     * @param langCdId을(를) String langCdId로 설정
     */
    public void setLangCdId(String langCdId) {
        this.langCdId = langCdId;
    }

    /**
     * 언어코드을 반환
     * 
     * @return String langCdId
     */
    public String getLangCdId() {
        return langCdId;
    }

    /**
     * 담당부서코드을 설정
     * 
     * @param tkcgDeptCdId을(를) String tkcgDeptCdId로 설정
     */
    public void setTkcgDeptCdId(String tkcgDeptCdId) {
        this.tkcgDeptCdId = tkcgDeptCdId;
    }

    /**
     * 담당부서코드을 반환
     * 
     * @return String tkcgDeptCdId
     */
    public String getTkcgDeptCdId() {
        return tkcgDeptCdId;
    }

    /**
     * 담당자ID을 설정
     * 
     * @param picId을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * 담당자ID을 반환
     * 
     * @return String picId
     */
    public String getPicId() {
        return picId;
    }

    /**
     * String 보안프로토콜여부 반환
     * 
     * @return String httpsYn
     */
    public String getHttpsYn() {
        return httpsYn;
    }

    /**
     * 보안프로토콜여부 설정
     * 
     * @param httpsYn 을(를) String httpsYn로 설정
     */
    public void setHttpsYn(String httpsYn) {
        this.httpsYn = httpsYn;
    }

    /**
     * 보안포트번호 반환
     * 
     * @return Integer scrtyPortSn
     */
    public Integer getScrtyPortSn() {
        return scrtyPortSn;
    }

    /**
     * 보안포트번호 설정
     * 
     * @param scrtyPortSn 을(를) Integer scrtyPortSn로 설정
     */
    public void setScrtyPortSn(Integer scrtyPortSn) {
        this.scrtyPortSn = scrtyPortSn;
    }

    /**
     * 실컨텍스트여부 반환
     * 
     * @return String actlStngYn
     */
    public String getActlStngYn() {
        return actlStngYn;
    }

    /**
     * 실컨텍스트여부 설정
     * 
     * @param actlStngYn 을(를) String actlStngYn로 설정
     */
    public void setActlStngYn(String actlStngYn) {
        this.actlStngYn = actlStngYn;
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
     * 등록일시를 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * 등록일시를 설정
     * 
     * @param regDt 을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
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
     * 수정일시를 반환
     * 
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * 수정일시를 설정
     * 
     * @param updtDt 을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * String chrgDeptNm을 반환
     * 
     * @return String chrgDeptNm
     */
    public String getChrgDeptNm() {
        return chrgDeptNm;
    }

    /**
     * chrgDeptNm을 설정
     * 
     * @param chrgDeptNm 을(를) String chrgDeptNm로 설정
     */
    public void setChrgDeptNm(String chrgDeptNm) {
        this.chrgDeptNm = chrgDeptNm;
    }

    /**
     * String picNm을 반환
     * 
     * @return String picNm
     */
    public String getPicNm() {
        return picNm;
    }

    /**
     * picNm을 설정
     * 
     * @param picNm 을(를) String picNm로 설정
     */
    public void setPicNm(String picNm) {
        this.picNm = picNm;
    }

    /**
     * String rgtrNm을 반환
     * 
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * rgtrNm을 설정
     * 
     * @param rgtrNm 을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * String updusrNm을 반환
     * 
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
    }

    /**
     * updusrNm을 설정
     * 
     * @param updusrNm 을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * String[] srvrIpAddr을 반환
     * 
     * @return String[] srvrIpAddr
     */
    public String[] getSrvrIpAddr() {
        return srvrIpAddr;
    }

    /**
     * srvrIpAddr을 설정
     * 
     * @param srvrIpAddr 을(를) String[] srvrIpAddr로 설정
     */
    public void setSrvrIpAddr(String[] srvrIpAddr) {
        this.srvrIpAddr = srvrIpAddr;
    }

    /**
     * Integer[] groupPortSn을 반환
     * 
     * @return Integer[] groupPortSn
     */
    public Integer[] getGroupPortSn() {
        return groupPortSn;
    }

    /**
     * groupPortSn을 설정
     * 
     * @param groupPortSn 을(를) Integer[] groupPortSn로 설정
     */
    public void setGroupPortSn(Integer[] groupPortSn) {
        this.groupPortSn = groupPortSn;
    }

    /**
     * List<DomnGroupVO> domnGroupList을 반환
     * 
     * @return List<DomnGroupVO> domnGroupList
     */
    public List<DomnGroupVO> getDomnGroupList() {
        return domnGroupList;
    }

    /**
     * domnGroupList을 설정
     * 
     * @param domnGroupList 을(를) List<DomnGroupVO> domnGroupList로 설정
     */
    public void setDomnGroupList(List<DomnGroupVO> domnGroupList) {
        this.domnGroupList = domnGroupList;
    }

}
