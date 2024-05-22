/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.accessCtrl.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RequireFrom;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 접근제어 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-02.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class AccesCtrlVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 5057173123822472990L;

    /** 일련번호 */
    @Required
    @Digits
    private Integer regSn;

    /** 허용유형 */
    @Required
    @MaxLength(max = 20)
    private String prmsnTypeNm;

    /** 허용ID */
    @MaxLength(max = 20)
    @RequireFrom(fieldName = "prmsnTypeNm", fieldValue = "ID", fieldDesc = "ID")
    private String prmsnId;

    /** 허용시작IP */
    @MaxLength(max = 15)
    @RequireFrom(fieldName = "prmsnTypeNm", fieldValue = "IP", fieldDesc = "IP")
    private String prmsnBgngIp;

    /** 허용종료IP */
    @MaxLength(max = 15)
    @RequireFrom(fieldName = "prmsnTypeNm", fieldValue = "IP", fieldDesc = "IP")
    private String prmsnEndIp;

    /** 사용여부 */
    @Required
    private String useYn;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 허용ID 담당자 부서명 */
    private String permChrgDeptNm;

    /** 허용ID 담당자명 */
    private String permPicNm;

    /**
     * 일련번호 설정
     * 
     * @param regSn을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 일련번호 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * 허용유형 설정
     * 
     * @param prmsnTypeNm을(를) String prmsnTypeNm로 설정
     */
    public void setPrmsnTypeNm(String prmsnTypeNm) {
        this.prmsnTypeNm = prmsnTypeNm;
    }

    /**
     * 허용유형 반환
     * 
     * @return String prmsnTypeNm
     */
    public String getPrmsnTypeNm() {
        return prmsnTypeNm;
    }

    /**
     * 허용ID 설정
     * 
     * @param prmsnId을(를) String prmsnId로 설정
     */
    public void setPrmsnId(String prmsnId) {
        this.prmsnId = prmsnId;
    }

    /**
     * 허용ID 반환
     * 
     * @return String prmsnId
     */
    public String getPrmsnId() {
        return prmsnId;
    }

    /**
     * 허용시작IP 설정
     * 
     * @param prmsnBgngIp을(를) String prmsnBgngIp로 설정
     */
    public void setPrmsnBgngIp(String prmsnBgngIp) {
        this.prmsnBgngIp = prmsnBgngIp;
    }

    /**
     * 허용시작IP 반환
     * 
     * @return String prmsnBgngIp
     */
    public String getPrmsnBgngIp() {
        return prmsnBgngIp;
    }

    /**
     * 허용종료IP 설정
     * 
     * @param prmsnEndIp을(를) String prmsnEndIp로 설정
     */
    public void setPrmsnEndIp(String prmsnEndIp) {
        this.prmsnEndIp = prmsnEndIp;
    }

    /**
     * 허용종료IP 반환
     * 
     * @return String prmsnEndIp
     */
    public String getPrmsnEndIp() {
        return prmsnEndIp;
    }

    /**
     * 사용여부 설정
     * 
     * @param useYn을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 사용여부 반환
     * 
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * 등록자ID 설정
     * 
     * @param rgtrId을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
    }

    /**
     * 등록자ID 반환
     * 
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * 등록자명 설정
     * 
     * @param rgtrNm을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * 등록자명 반환
     * 
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * 등록일시 설정
     * 
     * @param regDt을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    /**
     * 등록일시 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * 수정자ID 설정
     * 
     * @param mdfrId을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * 수정자ID 반환
     * 
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * 수정자명 설정
     * 
     * @param updusrNm을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * 수정자명 반환
     * 
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
    }

    /**
     * 수정일시 설정
     * 
     * @param updtDt을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * 수정일시 반환
     * 
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * String permChrgDeptNm을 반환
     * 
     * @return String permChrgDeptNm
     */
    public String getPermChrgDeptNm() {
        return permChrgDeptNm;
    }

    /**
     * permChrgDeptNm을 설정
     * 
     * @param permChrgDeptNm 을(를) String permChrgDeptNm로 설정
     */
    public void setPermChrgDeptNm(String permChrgDeptNm) {
        this.permChrgDeptNm = permChrgDeptNm;
    }

    /**
     * String permPicNm을 반환
     * 
     * @return String permPicNm
     */
    public String getPermPicNm() {
        return permPicNm;
    }

    /**
     * permPicNm을 설정
     * 
     * @param permPicNm 을(를) String permPicNm로 설정
     */
    public void setPermPicNm(String permPicNm) {
        this.permPicNm = permPicNm;
    }

}
