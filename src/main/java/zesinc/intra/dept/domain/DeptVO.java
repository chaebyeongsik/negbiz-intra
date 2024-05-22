/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dept.domain;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 부서 관리 VO 클레스
 *
 * @author (주)제스아이엔씨 기술연구소
 *
 *         <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-02-23.    황신욱   최초작성
 * </pre>
 * @see
 */
public class DeptVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -6366951179874523656L;

    /** 상위부서코드 */
    @Required
    private String upDeptCdId;

    /** 상위부서명 */
    private String upperDeptNm;

    /** 부서명 */
    @Required
    @MaxLength(max = 200)
    private String deptNm;

    /** 부서코드 */
    @Required
    @MaxLength(max = 20)
    @AlphaNumeric
    private String deptCdId;

    /** 전화번호 */
    private String telno;
    @Digits
    private String rgnTelno;
    @Digits
    private String telofcTelno;
    @Digits
    private String indivTelno;

    /** 팩스번호 */
    private String fxnum;
    @Digits
    private String rgnFxno;
    @Digits
    private String telofcFxno;
    @Digits
    private String indivFxno;

    /** 주요업무 */
    @MaxLength(max = 300)
    private String tkcgTaskNm;

    /** 정렬순서 */
    private Integer sortSn;

    /** 사용여부 */
    private String useYn;

    /** 등록일시 */
    private String regDt;

    /** 수정일시 */
    private String updtDt;

    /** 변경전 부서코드 */
    private String beforeDeptCdId;

    /** 자식부서존재여부 */
    private Boolean hasChild;

    /** 엑셀일괄 폼전송시 담길 배열 */
    private String[] upDeptCdIds;
    private String[] deptNms;
    private String[] deptCdIds;
    private String[] rgnTelnos;
    private String[] telofcTelnos;
    private String[] indivTelnos;
    private String[] rgnFxnos;
    private String[] telofcFxnos;
    private String[] indivFxnos;
    private String[] tkcgTaskNms;
    private String[] useYns;

    /**
     * String upDeptCdId을 반환
     *
     * @return String upDeptCdId
     */
    public String getUpDeptCdId() {
        return upDeptCdId;
    }

    /**
     * upDeptCdId을 설정
     *
     * @param upDeptCdId 을(를) String upDeptCdId로 설정
     */
    public void setUpDeptCdId(String upDeptCdId) {
        this.upDeptCdId = upDeptCdId;
    }

    /**
     * String upperDeptNm을 반환
     *
     * @return String upperDeptNm
     */
    public String getUpperDeptNm() {
        return upperDeptNm;
    }

    /**
     * upperDeptNm을 설정
     *
     * @param upperDeptNm 을(를) String upperDeptNm로 설정
     */
    public void setUpperDeptNm(String upperDeptNm) {
        this.upperDeptNm = upperDeptNm;
    }

    /**
     * String deptNm을 반환
     *
     * @return String deptNm
     */
    public String getDeptNm() {
        return deptNm;
    }

    /**
     * deptNm을 설정
     *
     * @param deptNm 을(를) String deptNm로 설정
     */
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    /**
     * String deptCdId을 반환
     *
     * @return String deptCdId
     */
    public String getDeptCdId() {
        return deptCdId;
    }

    /**
     * deptCdId을 설정
     *
     * @param deptCdId 을(를) String deptCdId로 설정
     */
    public void setDeptCdId(String deptCdId) {
        this.deptCdId = deptCdId;
    }

    /**
     * String telno을 반환
     *
     * @return String telno
     */
    public String getTelno() {
        return telno;
    }

    /**
     * telno을 설정
     *
     * @param telno 을(를) String telno로 설정
     */
    public void setTelno(String telno) {
        this.telno = telno;
    }

    /**
     * String rgnTelno을 반환
     *
     * @return String rgnTelno
     */
    public String getRgnTelno() {
        return rgnTelno;
    }

    /**
     * rgnTelno을 설정
     *
     * @param rgnTelno 을(를) String rgnTelno로 설정
     */
    public void setRgnTelno(String rgnTelno) {
        this.rgnTelno = rgnTelno;
    }

    /**
     * String telofcTelno을 반환
     *
     * @return String telofcTelno
     */
    public String getTelofcTelno() {
        return telofcTelno;
    }

    /**
     * telofcTelno을 설정
     *
     * @param telofcTelno 을(를) String telofcTelno로 설정
     */
    public void setTelofcTelno(String telofcTelno) {
        this.telofcTelno = telofcTelno;
    }

    /**
     * String indivTelno을 반환
     *
     * @return String indivTelno
     */
    public String getIndivTelno() {
        return indivTelno;
    }

    /**
     * indivTelno을 설정
     *
     * @param indivTelno 을(를) String indivTelno로 설정
     */
    public void setIndivTelno(String indivTelno) {
        this.indivTelno = indivTelno;
    }

    /**
     * String fxnum을 반환
     *
     * @return String fxnum
     */
    public String getFxnum() {
        return fxnum;
    }

    /**
     * fxnum을 설정
     *
     * @param fxnum 을(를) String fxnum로 설정
     */
    public void setFxnum(String fxnum) {
        this.fxnum = fxnum;
    }

    /**
     * String rgnFxno을 반환
     *
     * @return String rgnFxno
     */
    public String getRgnFxno() {
        return rgnFxno;
    }

    /**
     * rgnFxno을 설정
     *
     * @param rgnFxno 을(를) String rgnFxno로 설정
     */
    public void setRgnFxno(String rgnFxno) {
        this.rgnFxno = rgnFxno;
    }

    /**
     * String telofcFxno을 반환
     *
     * @return String telofcFxno
     */
    public String getTelofcFxno() {
        return telofcFxno;
    }

    /**
     * telofcFxno을 설정
     *
     * @param telofcFxno 을(를) String telofcFxno로 설정
     */
    public void setTelofcFxno(String telofcFxno) {
        this.telofcFxno = telofcFxno;
    }

    /**
     * String indivFxno을 반환
     *
     * @return String indivFxno
     */
    public String getIndivFxno() {
        return indivFxno;
    }

    /**
     * indivFxno을 설정
     *
     * @param indivFxno 을(를) String indivFxno로 설정
     */
    public void setIndivFxno(String indivFxno) {
        this.indivFxno = indivFxno;
    }

    /**
     * String tkcgTaskNm을 반환
     *
     * @return String tkcgTaskNm
     */
    public String getTkcgTaskNm() {
        return tkcgTaskNm;
    }

    /**
     * tkcgTaskNm을 설정
     *
     * @param tkcgTaskNm 을(를) String tkcgTaskNm로 설정
     */
    public void setTkcgTaskNm(String tkcgTaskNm) {
        this.tkcgTaskNm = tkcgTaskNm;
    }

    /**
     * Integer sortSn을 반환
     *
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
    }

    /**
     * sortSn을 설정
     *
     * @param sortSn 을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
    }

    /**
     * String useYn을 반환
     *
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * useYn을 설정
     *
     * @param useYn 을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * String regDt을 반환
     *
     * @return String regDt
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     *
     * @param regDt 을(를) String regDt로 설정
     */
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    /**
     * String updtDt을 반환
     *
     * @return String updtDt
     */
    public String getUpdtDt() {
        return updtDt;
    }

    /**
     * updtDt을 설정
     *
     * @param updtDt 을(를) String updtDt로 설정
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * String beforeDeptCdId을 반환
     *
     * @return String beforeDeptCdId
     */
    public String getBeforeDeptCdId() {
        return beforeDeptCdId;
    }

    /**
     * beforeDeptCdId을 설정
     *
     * @param beforeDeptCdId 을(를) String beforeDeptCdId로 설정
     */
    public void setBeforeDeptCdId(String beforeDeptCdId) {
        this.beforeDeptCdId = beforeDeptCdId;
    }

    /**
     * Boolean hasChild을 반환
     * 
     * @return Boolean hasChild
     */
    public Boolean getHasChild() {
        return hasChild;
    }

    /**
     * hasChild을 설정
     * 
     * @param hasChild 을(를) Boolean hasChild로 설정
     */
    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    /**
     * String[] upDeptCdIds을 반환
     *
     * @return String[] upDeptCdIds
     */
    public String[] getUpDeptCdIds() {
        return upDeptCdIds;
    }

    /**
     * upDeptCdIds을 설정
     *
     * @param upDeptCdIds 을(를) String[] upDeptCdIds로 설정
     */
    public void setUpDeptCdIds(String[] upDeptCdIds) {
        this.upDeptCdIds = upDeptCdIds;
    }

    /**
     * String[] deptNms을 반환
     *
     * @return String[] deptNms
     */
    public String[] getDeptNms() {
        return deptNms;
    }

    /**
     * deptNms을 설정
     *
     * @param deptNms 을(를) String[] deptNms로 설정
     */
    public void setDeptNms(String[] deptNms) {
        this.deptNms = deptNms;
    }

    /**
     * String[] deptCdIds을 반환
     *
     * @return String[] deptCdIds
     */
    public String[] getDeptCdIds() {
        return deptCdIds;
    }

    /**
     * deptCdIds을 설정
     *
     * @param deptCdIds 을(를) String[] deptCdIds로 설정
     */
    public void setDeptCdIds(String[] deptCdIds) {
        this.deptCdIds = deptCdIds;
    }

    /**
     * String[] rgnTelnos을 반환
     *
     * @return String[] rgnTelnos
     */
    public String[] getRgnTelnos() {
        return rgnTelnos;
    }

    /**
     * rgnTelnos을 설정
     *
     * @param rgnTelnos 을(를) String[] rgnTelnos로 설정
     */
    public void setRgnTelnos(String[] rgnTelnos) {
        this.rgnTelnos = rgnTelnos;
    }

    /**
     * String[] telofcTelnos을 반환
     *
     * @return String[] telofcTelnos
     */
    public String[] getTelofcTelnos() {
        return telofcTelnos;
    }

    /**
     * telofcTelnos을 설정
     *
     * @param telofcTelnos 을(를) String[] telofcTelnos로 설정
     */
    public void setTelofcTelnos(String[] telofcTelnos) {
        this.telofcTelnos = telofcTelnos;
    }

    /**
     * String[] indivTelnos을 반환
     *
     * @return String[] indivTelnos
     */
    public String[] getIndivTelnos() {
        return indivTelnos;
    }

    /**
     * indivTelnos을 설정
     *
     * @param indivTelnos 을(를) String[] indivTelnos로 설정
     */
    public void setIndivTelnos(String[] indivTelnos) {
        this.indivTelnos = indivTelnos;
    }

    /**
     * String[] rgnFxnos을 반환
     *
     * @return String[] rgnFxnos
     */
    public String[] getRgnFxnos() {
        return rgnFxnos;
    }

    /**
     * rgnFxnos을 설정
     *
     * @param rgnFxnos 을(를) String[] rgnFxnos로 설정
     */
    public void setRgnFxnos(String[] rgnFxnos) {
        this.rgnFxnos = rgnFxnos;
    }

    /**
     * String[] telofcFxnos을 반환
     *
     * @return String[] telofcFxnos
     */
    public String[] getTelofcFxnos() {
        return telofcFxnos;
    }

    /**
     * telofcFxnos을 설정
     *
     * @param telofcFxnos 을(를) String[] telofcFxnos로 설정
     */
    public void setTelofcFxnos(String[] telofcFxnos) {
        this.telofcFxnos = telofcFxnos;
    }

    /**
     * String[] indivFxnos을 반환
     *
     * @return String[] indivFxnos
     */
    public String[] getIndivFxnos() {
        return indivFxnos;
    }

    /**
     * indivFxnos을 설정
     *
     * @param indivFxnos 을(를) String[] indivFxnos로 설정
     */
    public void setIndivFxnos(String[] indivFxnos) {
        this.indivFxnos = indivFxnos;
    }

    /**
     * String[] tkcgTaskNms을 반환
     *
     * @return String[] tkcgTaskNms
     */
    public String[] getTkcgTaskNms() {
        return tkcgTaskNms;
    }

    /**
     * tkcgTaskNms을 설정
     *
     * @param tkcgTaskNms 을(를) String[] tkcgTaskNms로 설정
     */
    public void setTkcgTaskNms(String[] tkcgTaskNms) {
        this.tkcgTaskNms = tkcgTaskNms;
    }

    /**
     * String[] useYns을 반환
     *
     * @return String[] useYns
     */
    public String[] getUseYns() {
        return useYns;
    }

    /**
     * useYns을 설정
     *
     * @param useYns 을(를) String[] useYns로 설정
     */
    public void setUseYns(String[] useYns) {
        this.useYns = useYns;
    }

}
