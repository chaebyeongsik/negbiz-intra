/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.change.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 관리자변경이력 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-05-16.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class ChangeVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 729463526187750350L;

    /** 담당자ID */
    @Required
    @MaxLength(max = 20)
    private String picId;

    /** 로그일시 */
    @Required
    private Date logDt;

    /** 로그유형 */
    private String logType;

    /** 부서코드 */
    @MaxLength(max = 20)
    private String deptCdId;

    /** 부서명 */
    private String deptNm;

    /** 담당자명 */
    @MaxLength(max = 100)
    private String picNm;

    /** 담당자비밀번호 */
    @MaxLength(max = 60)
    private String picPswd;

    /** 직급코드 */
    @MaxLength(max = 20)
    private String jbgdCdId;

    /** 직급명 */
    private String clsfNm;

    /** 담당업무 */
    @MaxLength(max = 4000)
    private String taskCn;

    /** 전화번호1 */
    @MaxLength(max = 4)
    private String rgnTelno;

    /** 전화번호2 */
    @MaxLength(max = 4)
    private String telofcTelno;

    /** 전화번호3 */
    @MaxLength(max = 4)
    private String indivTelno;

    /** 팩스번호1 */
    @MaxLength(max = 4)
    private String rgnFxno;

    /** 팩스번호2 */
    @MaxLength(max = 4)
    private String telofcFxno;

    /** 팩스번호3 */
    @MaxLength(max = 4)
    private String indivFxno;

    /** 휴대전화번호1 */
    @MaxLength(max = 4)
    private String mblRgnTelno;

    /** 휴대전화번호2 */
    @MaxLength(max = 4)
    private String mblTelofcTelno;

    /** 휴대전화번호3 */
    @MaxLength(max = 4)
    private String mblIndivTelno;

    /** 이메일1 */
    @MaxLength(max = 100)
    private String emlId;

    /** 이메일2 */
    @MaxLength(max = 100)
    private String emlSiteNm;

    /** 로그인건수 */
    @Digits
    private Integer lgnNmtm;

    /** 로그인일시 */
    private Date lgnDt;

    /** 사용여부 */
    private String useYn;

    /** 알림이실행여부 */
    private String avtsmtExcnYn;

    /** 이메일통지여부 */
    private String emlAvtsmtYn;

    /** SMS통지여부 */
    private String smsAvtsmtYn;

    /** 비밀번호변경일시 */
    private Date pswdChgDt;

    /** 특별여부 */
    private String trgtYn;

    /** 상태코드 */
    @Digits
    private Integer sttsSn;

    /** 상태명 */
    private String sttusNm;

    /** 상태시작일시 */
    private Date sttsBgngDt;

    /** 상태종료일시 */
    private Date sttsEndDt;

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

    /** 인증계정 */
    @MaxLength(max = 100)
    private String acntNm;

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
     * 로그일시 설정
     *
     * @param logDt을(를) Date logDt로 설정
     */
    public void setLogDt(Date logDt) {
        this.logDt = logDt;
    }

    /**
     * 로그일시 반환
     *
     * @return Date logDt
     */
    public Date getLogDt() {
        return logDt;
    }

    /**
     * 로그유형 설정
     *
     * @param logType을(를) String logType로 설정
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 로그유형 반환
     *
     * @return String logType
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 부서코드 설정
     *
     * @param deptCdId을(를) String deptCdId로 설정
     */
    public void setDeptCdId(String deptCdId) {
        this.deptCdId = deptCdId;
    }

    /**
     * 부서코드 반환
     *
     * @return String deptCdId
     */
    public String getDeptCdId() {
        return deptCdId;
    }

    /**
     * 부서명 설정
     *
     * @param deptNm을(를) String deptNm로 설정
     */
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    /**
     * 부서명 반환
     *
     * @return String deptNm
     */
    public String getDeptNm() {
        return deptNm;
    }

    /**
     * 담당자명 설정
     *
     * @param picNm을(를) String picNm로 설정
     */
    public void setPicNm(String picNm) {
        this.picNm = picNm;
    }

    /**
     * 담당자명 반환
     *
     * @return String picNm
     */
    public String getPicNm() {
        return picNm;
    }

    /**
     * 담당자비밀번호 설정
     *
     * @param picPswd을(를) String picPswd로 설정
     */
    public void setPicPswd(String picPswd) {
        this.picPswd = picPswd;
    }

    /**
     * 담당자비밀번호 반환
     *
     * @return String picPswd
     */
    public String getPicPswd() {
        return picPswd;
    }

    /**
     * 직급코드 설정
     *
     * @param jbgdCdId을(를) String jbgdCdId로 설정
     */
    public void setJbgdCdId(String jbgdCdId) {
        this.jbgdCdId = jbgdCdId;
    }

    /**
     * 직급코드 반환
     *
     * @return String jbgdCdId
     */
    public String getJbgdCdId() {
        return jbgdCdId;
    }

    /**
     * 직급명 설정
     *
     * @param clsfNm을(를) String clsfNm로 설정
     */
    public void setClsfNm(String clsfNm) {
        this.clsfNm = clsfNm;
    }

    /**
     * 직급명 반환
     *
     * @return String clsfNm
     */
    public String getClsfNm() {
        return clsfNm;
    }

    /**
     * 담당업무 설정
     *
     * @param taskCn을(를) String taskCn로 설정
     */
    public void setTaskCn(String taskCn) {
        this.taskCn = taskCn;
    }

    /**
     * 담당업무 반환
     *
     * @return String taskCn
     */
    public String getTaskCn() {
        return taskCn;
    }

    /**
     * 전화번호1 설정
     *
     * @param rgnTelno을(를) String rgnTelno로 설정
     */
    public void setrgnTelno(String rgnTelno) {
        this.rgnTelno = rgnTelno;
    }

    /**
     * 전화번호1 반환
     *
     * @return String rgnTelno
     */
    public String getrgnTelno() {
        return rgnTelno;
    }

    /**
     * 전화번호2 설정
     *
     * @param telofcTelno을(를) String telofcTelno로 설정
     */
    public void setTelofcTelno(String telofcTelno) {
        this.telofcTelno = telofcTelno;
    }

    /**
     * 전화번호2 반환
     *
     * @return String telofcTelno
     */
    public String getTelofcTelno() {
        return telofcTelno;
    }

    /**
     * 전화번호3 설정
     *
     * @param indivTelno을(를) String indivTelno로 설정
     */
    public void setIndivTelno(String indivTelno) {
        this.indivTelno = indivTelno;
    }

    /**
     * 전화번호3 반환
     *
     * @return String indivTelno
     */
    public String getIndivTelno() {
        return indivTelno;
    }

    /**
     * 팩스번호1 설정
     *
     * @param rgnFxno을(를) String rgnFxno로 설정
     */
    public void setRgnFxno(String rgnFxno) {
        this.rgnFxno = rgnFxno;
    }

    /**
     * 팩스번호1 반환
     *
     * @return String rgnFxno
     */
    public String getRgnFxno() {
        return rgnFxno;
    }

    /**
     * 팩스번호2 설정
     *
     * @param telofcFxno을(를) String telofcFxno로 설정
     */
    public void setTelofcFxno(String telofcFxno) {
        this.telofcFxno = telofcFxno;
    }

    /**
     * 팩스번호2 반환
     *
     * @return String telofcFxno
     */
    public String getTelofcFxno() {
        return telofcFxno;
    }

    /**
     * 팩스번호3 설정
     *
     * @param indivFxno을(를) String indivFxno로 설정
     */
    public void setIndivFxno(String indivFxno) {
        this.indivFxno = indivFxno;
    }

    /**
     * 팩스번호3 반환
     *
     * @return String indivFxno
     */
    public String getIndivFxno() {
        return indivFxno;
    }

    /**
     * 휴대전화번호1 설정
     *
     * @param mblRgnTelno을(를) String mblRgnTelno로 설정
     */
    public void setMblRgnTelno(String mblRgnTelno) {
        this.mblRgnTelno = mblRgnTelno;
    }

    /**
     * 휴대전화번호1 반환
     *
     * @return String mblRgnTelno
     */
    public String getMblRgnTelno() {
        return mblRgnTelno;
    }

    /**
     * 휴대전화번호2 설정
     *
     * @param mblTelofcTelno을(를) String mblTelofcTelno로 설정
     */
    public void setMblTelofcTelno(String mblTelofcTelno) {
        this.mblTelofcTelno = mblTelofcTelno;
    }

    /**
     * 휴대전화번호2 반환
     *
     * @return String mblTelofcTelno
     */
    public String getMblTelofcTelno() {
        return mblTelofcTelno;
    }

    /**
     * 휴대전화번호3 설정
     *
     * @param mblIndivTelno을(를) String mblIndivTelno로 설정
     */
    public void setMblIndivTelno(String mblIndivTelno) {
        this.mblIndivTelno = mblIndivTelno;
    }

    /**
     * 휴대전화번호3 반환
     *
     * @return String mblIndivTelno
     */
    public String getMblIndivTelno() {
        return mblIndivTelno;
    }

    /**
     * 이메일1 설정
     *
     * @param emlId을(를) String emlId로 설정
     */
    public void setEmlId(String emlId) {
        this.emlId = emlId;
    }

    /**
     * 이메일1 반환
     *
     * @return String emlId
     */
    public String getEmlId() {
        return emlId;
    }

    /**
     * 이메일2 설정
     *
     * @param emlSiteNm을(를) String emlSiteNm로 설정
     */
    public void setEmlSiteNm(String emlSiteNm) {
        this.emlSiteNm = emlSiteNm;
    }

    /**
     * 이메일2 반환
     *
     * @return String emlSiteNm
     */
    public String getEmlSiteNm() {
        return emlSiteNm;
    }

    /**
     * 로그인건수 설정
     *
     * @param lgnNmtm을(를) Integer lgnNmtm로 설정
     */
    public void setLgnNmtm(Integer lgnNmtm) {
        this.lgnNmtm = lgnNmtm;
    }

    /**
     * 로그인건수 반환
     *
     * @return Integer lgnNmtm
     */
    public Integer getLgnNmtm() {
        return lgnNmtm;
    }

    /**
     * 로그인일시 설정
     *
     * @param lgnDt을(를) Date lgnDt로 설정
     */
    public void setLgnDt(Date lgnDt) {
        this.lgnDt = lgnDt;
    }

    /**
     * 로그인일시 반환
     *
     * @return Date lgnDt
     */
    public Date getLgnDt() {
        return lgnDt;
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
     * 알림이실행여부 설정
     *
     * @param avtsmtExcnYn을(를) String avtsmtExcnYn로 설정
     */
    public void setAvtsmtExcnYn(String avtsmtExcnYn) {
        this.avtsmtExcnYn = avtsmtExcnYn;
    }

    /**
     * 알림이실행여부 반환
     *
     * @return String avtsmtExcnYn
     */
    public String getAvtsmtExcnYn() {
        return avtsmtExcnYn;
    }

    /**
     * 이메일통지여부 설정
     *
     * @param emlAvtsmtYn을(를) String emlAvtsmtYn로 설정
     */
    public void setEmlAvtsmtYn(String emlAvtsmtYn) {
        this.emlAvtsmtYn = emlAvtsmtYn;
    }

    /**
     * 이메일통지여부 반환
     *
     * @return String emlAvtsmtYn
     */
    public String getEmlAvtsmtYn() {
        return emlAvtsmtYn;
    }

    /**
     * SMS통지여부 설정
     *
     * @param smsAvtsmtYn을(를) String smsAvtsmtYn로 설정
     */
    public void setSmsAvtsmtYn(String smsAvtsmtYn) {
        this.smsAvtsmtYn = smsAvtsmtYn;
    }

    /**
     * SMS통지여부 반환
     *
     * @return String smsAvtsmtYn
     */
    public String getSmsAvtsmtYn() {
        return smsAvtsmtYn;
    }

    /**
     * 비밀번호변경일시 설정
     *
     * @param pswdChgDt을(를) Date pswdChgDt로 설정
     */
    public void setPswdChgDt(Date pswdChgDt) {
        this.pswdChgDt = pswdChgDt;
    }

    /**
     * 비밀번호변경일시 반환
     *
     * @return Date pswdChgDt
     */
    public Date getPswdChgDt() {
        return pswdChgDt;
    }

    /**
     * 특별여부 설정
     *
     * @param trgtYn을(를) String trgtYn로 설정
     */
    public void setTrgtYn(String trgtYn) {
        this.trgtYn = trgtYn;
    }

    /**
     * 특별여부 반환
     *
     * @return String trgtYn
     */
    public String getTrgtYn() {
        return trgtYn;
    }

    /**
     * 상태코드 설정
     *
     * @param sttsSn을(를) Integer sttsSn로 설정
     */
    public void setSttsSn(Integer sttsSn) {
        this.sttsSn = sttsSn;
    }

    /**
     * 상태코드 반환
     *
     * @return Integer sttsSn
     */
    public Integer getSttsSn() {
        return sttsSn;
    }

    /**
     * 상태명 설정
     *
     * @param sttusNm을(를) String sttusNm로 설정
     */
    public void setSttusNm(String sttusNm) {
        this.sttusNm = sttusNm;
    }

    /**
     * 상태명 반환
     *
     * @return String sttusNm
     */
    public String getSttusNm() {
        return sttusNm;
    }

    /**
     * 상태시작일시 설정
     *
     * @param sttsBgngDt을(를) Date sttsBgngDt로 설정
     */
    public void setSttsBgngDt(Date sttsBgngDt) {
        this.sttsBgngDt = sttsBgngDt;
    }

    /**
     * 상태시작일시 반환
     *
     * @return Date sttsBgngDt
     */
    public Date getSttsBgngDt() {
        return sttsBgngDt;
    }

    /**
     * 상태종료일시 설정
     *
     * @param sttsEndDt을(를) Date sttsEndDt로 설정
     */
    public void setSttsEndDt(Date sttsEndDt) {
        this.sttsEndDt = sttsEndDt;
    }

    /**
     * 상태종료일시 반환
     *
     * @return Date sttsEndDt
     */
    public Date getSttsEndDt() {
        return sttsEndDt;
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
     * 인증계정 설정
     *
     * @param acntNm을(를) String acntNm로 설정
     */
    public void setAcntNm(String acntNm) {
        this.acntNm = acntNm;
    }

    /**
     * 인증계정 반환
     *
     * @return String acntNm
     */
    public String getAcntNm() {
        return acntNm;
    }

}
