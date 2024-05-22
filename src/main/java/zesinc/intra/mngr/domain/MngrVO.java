/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.domain;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 담당자 관리 VO 객체
 *
 * @author (주)제스아이엔씨 기술연구소
 *
 *         <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 7.    방기배   최초작성
 * </pre>
 * @see
 */
public class MngrVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -6232529283315462346L;

    /** 담당자ID */
    @Required
    @AlphaNumeric
    @RangeLength(min = 2, max = 20)
    private String picId;

    /** 담당자명 */
    @Required
    @RangeLength(min = 2, max = 100)
    private String picNm;

    /** 직급코드 */
    @Required
    private Integer jbgdCdId;

    /** 직급명 */
    private String clsfNm;

    /** 담당자비밀번호 */
    @Required
    @RangeLength(min = 8, max = 32)
    private String picPswd;

    /** 담당자비밀번호 확인 */
    @Required
    @RangeLength(min = 8, max = 32)
    private String confirmPassword;

    /** 담당업무 */
    @RangeLength(min = 2, max = 4000)
    private String taskCn;

    /** 전화번호1 */
    @Digits
    private String rgnTelno;
    /** 전화번호2 */
    @Digits
    @MaxLength(max = 4)
    private String telofcTelno;
    /** 전화번호3 */
    @Digits
    @MaxLength(max = 4)
    private String indivTelno;

    /** 팩스번호1 */
    @Digits
    private String rgnFxno;
    /** 팩스번호2 */
    @Digits
    @MaxLength(max = 4)
    private String telofcFxno;
    /** 팩스번호3 */
    @Digits
    @MaxLength(max = 4)
    private String indivFxno;

    /** 휴대전화번호1 */
    @Digits
    @MaxLength(max = 4)
    private String mblRgnTelno;
    /** 휴대전화번호2 */
    @Digits
    @MaxLength(max = 4)
    private String mblTelofcTelno;
    /** 휴대전화번호3 */
    @Digits
    @MaxLength(max = 4)
    private String mblIndivTelno;

    /** 이메일1 */
    @RangeLength(min = 2, max = 100)
    private String emlId;
    /** 이메일2 */
    @RangeLength(min = 2, max = 100)
    private String emlSiteNm;

    /** 로그인건수 */
    private Integer lgnNmtm;

    /** 로그인일시 */
    private String lgnDt;

    /** 사용여부 */
    private String useYn;

    /** 알림이실행여부 */
    private String avtsmtExcnYn;

    /** 이메일통지여부 */
    private String emlAvtsmtYn;

    /** SMS통지여부 */
    private String smsAvtsmtYn;

    /** 비밀번호변경일 */
    private String pswdChgDt;

    /** 상태코드 */
    private Integer sttsSn;

    /** 상태시작일시 */
    private String sttsBgngDt;

    /** 상태종료일시 */
    private String sttsEndDt;

    /** 등록자ID */
    private String rgtrId;

    /** 등록일시 */
    private String regDt;

    /** 수정자ID */
    private String mdfrId;

    /** 수정일시 */
    private String updtDt;

    /** 상위부서코드 */
    private String upDeptCdId;

    /** 상위부서명 */
    private String upperDeptNm;

    /** 부서코드 */
    private String deptCdId;

    /** 부서명 */
    private String deptNm;

    /** 비밀번호 만료여부 */
    private String passwordEndAt;
    /** 로그인성공여부 */
    private Boolean matcheAt;

    /** 기존 비밀번호 */
    private String beforePicId;
    /** 새 비밀번호 */
    private String changePasswd;
    /** 새 비밀번호 확인 */
    private String confirmPasswd;
    /** 선택된 담당자ID (구분자 : ",") */
    private String picIds;
    /** 담당자ID (목록삭제,일괄권한지정) */
    private String[] picIdArray;
    /** 권한코드 (담당자권한수정,일괄권한지정) */
    private String authrtCdId;
    /** 권한코드 (구분자 : ",") */
    private String authrtCdIds;
    /** 인증서계정 */
    private String acntNm;

    /**
     * String picId을 반환
     *
     * @return String picId
     */
    public String getPicId() {
        return picId;
    }

    /**
     * picId을 설정
     *
     * @param picId 을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
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
     * String picPswd을 반환
     *
     * @return String picPswd
     */
    public String getPicPswd() {
        return picPswd;
    }

    /**
     * picPswd을 설정
     *
     * @param picPswd 을(를) String picPswd로 설정
     */
    public void setPicPswd(String picPswd) {
        this.picPswd = picPswd;
    }

    /**
     * String confirmPassword을 반환
     *
     * @return String confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * confirmPassword을 설정
     *
     * @param confirmPassword 을(를) String confirmPassword로 설정
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Integer jbgdCdId을 반환
     *
     * @return Integer jbgdCdId
     */
    public Integer getJbgdCdId() {
        return jbgdCdId;
    }

    /**
     * jbgdCdId을 설정
     *
     * @param jbgdCdId 을(를) Integer jbgdCdId로 설정
     */
    public void setJbgdCdId(Integer jbgdCdId) {
        this.jbgdCdId = jbgdCdId;
    }

    /**
     * String clsfNm을 반환
     *
     * @return String clsfNm
     */
    public String getClsfNm() {
        return clsfNm;
    }

    /**
     * clsfNm을 설정
     *
     * @param clsfNm 을(를) String clsfNm로 설정
     */
    public void setClsfNm(String clsfNm) {
        this.clsfNm = clsfNm;
    }

    /**
     * String taskCn을 반환
     *
     * @return String taskCn
     */
    public String getTaskCn() {
        return taskCn;
    }

    /**
     * taskCn을 설정
     *
     * @param taskCn 을(를) String taskCn로 설정
     */
    public void setTaskCn(String taskCn) {
        this.taskCn = taskCn;
    }

    /**
     * String rgnTelno을 반환
     *
     * @return String rgnTelno
     */
    public String getrgnTelno() {
        return rgnTelno;
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
     * String mblRgnTelno을 반환
     *
     * @return String mblRgnTelno
     */
    public String getMblRgnTelno() {
        return mblRgnTelno;
    }

    /**
     * mblRgnTelno을 설정
     *
     * @param mblRgnTelno 을(를) String mblRgnTelno로 설정
     */
    public void setMblRgnTelno(String mblRgnTelno) {
        this.mblRgnTelno = mblRgnTelno;
    }

    /**
     * String mblTelofcTelno을 반환
     *
     * @return String mblTelofcTelno
     */
    public String getMblTelofcTelno() {
        return mblTelofcTelno;
    }

    /**
     * mblTelofcTelno을 설정
     *
     * @param mblTelofcTelno 을(를) String mblTelofcTelno로 설정
     */
    public void setMblTelofcTelno(String mblTelofcTelno) {
        this.mblTelofcTelno = mblTelofcTelno;
    }

    /**
     * String mblIndivTelno을 반환
     *
     * @return String mblIndivTelno
     */
    public String getMblIndivTelno() {
        return mblIndivTelno;
    }

    /**
     * mblIndivTelno을 설정
     *
     * @param mblIndivTelno 을(를) String mblIndivTelno로 설정
     */
    public void setMblIndivTelno(String mblIndivTelno) {
        this.mblIndivTelno = mblIndivTelno;
    }

    /**
     * rgnTelno을 설정
     *
     * @param rgnTelno 을(를) String rgnTelno로 설정
     */
    public void setrgnTelno(String rgnTelno) {
        this.rgnTelno = rgnTelno;
    }

    /**
     * String emlId을 반환
     *
     * @return String emlId
     */
    public String getEmlId() {
        return emlId;
    }

    /**
     * emlId을 설정
     *
     * @param emlId 을(를) String emlId로 설정
     */
    public void setEmlId(String emlId) {
        this.emlId = emlId;
    }

    /**
     * String emlSiteNm을 반환
     *
     * @return String emlSiteNm
     */
    public String getEmlSiteNm() {
        return emlSiteNm;
    }

    /**
     * emlSiteNm을 설정
     *
     * @param emlSiteNm 을(를) String emlSiteNm로 설정
     */
    public void setEmlSiteNm(String emlSiteNm) {
        this.emlSiteNm = emlSiteNm;
    }

    /**
     * Integer lgnNmtm을 반환
     *
     * @return Integer lgnNmtm
     */
    public Integer getLgnNmtm() {
        return lgnNmtm;
    }

    /**
     * lgnNmtm을 설정
     *
     * @param lgnNmtm 을(를) Integer lgnNmtm로 설정
     */
    public void setLgnNmtm(Integer lgnNmtm) {
        this.lgnNmtm = lgnNmtm;
    }

    /**
     * String lgnDt을 반환
     *
     * @return String lgnDt
     */
    public String getLgnDt() {
        return lgnDt;
    }

    /**
     * lgnDt을 설정
     *
     * @param lgnDt 을(를) String lgnDt로 설정
     */
    public void setLgnDt(String lgnDt) {
        this.lgnDt = lgnDt;
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
     * String avtsmtExcnYn을 반환
     *
     * @return String avtsmtExcnYn
     */
    public String getAvtsmtExcnYn() {
        return avtsmtExcnYn;
    }

    /**
     * avtsmtExcnYn을 설정
     *
     * @param avtsmtExcnYn 을(를) String avtsmtExcnYn로 설정
     */
    public void setAvtsmtExcnYn(String avtsmtExcnYn) {
        this.avtsmtExcnYn = avtsmtExcnYn;
    }

    /**
     * String emlAvtsmtYn을 반환
     *
     * @return String emlAvtsmtYn
     */
    public String getEmlAvtsmtYn() {
        return emlAvtsmtYn;
    }

    /**
     * emlAvtsmtYn을 설정
     *
     * @param emlAvtsmtYn 을(를) String emlAvtsmtYn로 설정
     */
    public void setEmlAvtsmtYn(String emlAvtsmtYn) {
        this.emlAvtsmtYn = emlAvtsmtYn;
    }

    /**
     * String smsAvtsmtYn을 반환
     *
     * @return String smsAvtsmtYn
     */
    public String getSmsAvtsmtYn() {
        return smsAvtsmtYn;
    }

    /**
     * smsAvtsmtYn을 설정
     *
     * @param smsAvtsmtYn 을(를) String smsAvtsmtYn로 설정
     */
    public void setSmsAvtsmtYn(String smsAvtsmtYn) {
        this.smsAvtsmtYn = smsAvtsmtYn;
    }

    /**
     * String pswdChgDt을 반환
     *
     * @return String pswdChgDt
     */
    public String getPswdChgDt() {
        return pswdChgDt;
    }

    /**
     * pswdChgDt을 설정
     *
     * @param pswdChgDt 을(를) String pswdChgDt로 설정
     */
    public void setPswdChgDt(String pswdChgDt) {
        this.pswdChgDt = pswdChgDt;
    }

    /**
     * Integer sttsSn을 반환
     *
     * @return Integer sttsSn
     */
    public Integer getSttsSn() {
        return sttsSn;
    }

    /**
     * sttsSn을 설정
     *
     * @param sttsSn 을(를) Integer sttsSn로 설정
     */
    public void setSttsSn(Integer sttsSn) {
        this.sttsSn = sttsSn;
    }

    /**
     * String sttsBgngDt을 반환
     *
     * @return String sttsBgngDt
     */
    public String getSttsBgngDt() {
        return sttsBgngDt;
    }

    /**
     * sttsBgngDt을 설정
     *
     * @param sttsBgngDt 을(를) String sttsBgngDt로 설정
     */
    public void setSttsBgngDt(String sttsBgngDt) {
        this.sttsBgngDt = sttsBgngDt;
    }

    /**
     * String sttsEndDt을 반환
     *
     * @return String sttsEndDt
     */
    public String getSttsEndDt() {
        return sttsEndDt;
    }

    /**
     * sttsEndDt을 설정
     *
     * @param sttsEndDt 을(를) String sttsEndDt로 설정
     */
    public void setSttsEndDt(String sttsEndDt) {
        this.sttsEndDt = sttsEndDt;
    }

    /**
     * String rgtrId을 반환
     *
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * rgtrId을 설정
     *
     * @param rgtrId 을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
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
     * String mdfrId을 반환
     *
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * mdfrId을 설정
     *
     * @param mdfrId 을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
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
     * String passwordEndAt을 반환
     *
     * @return String passwordEndAt
     */
    public String getPasswordEndAt() {
        return passwordEndAt;
    }

    /**
     * passwordEndAt을 설정
     *
     * @param passwordEndAt 을(를) String passwordEndAt로 설정
     */
    public void setPasswordEndAt(String passwordEndAt) {
        this.passwordEndAt = passwordEndAt;
    }

    /**
     * Boolean matcheAt을 반환
     *
     * @return Boolean matcheAt
     */
    public Boolean getMatcheAt() {
        return matcheAt;
    }

    /**
     * matcheAt을 설정
     *
     * @param matcheAt 을(를) Boolean matcheAt로 설정
     */
    public void setMatcheAt(Boolean matcheAt) {
        this.matcheAt = matcheAt;
    }

    /**
     * String beforePicId을 반환
     *
     * @return String beforePicId
     */
    public String getBeforePicId() {
        return beforePicId;
    }

    /**
     * beforePicId을 설정
     *
     * @param beforePicId 을(를) String beforePicId로 설정
     */
    public void setBeforePicId(String beforePicId) {
        this.beforePicId = beforePicId;
    }

    /**
     * String changePasswd을 반환
     *
     * @return String changePasswd
     */
    public String getChangePasswd() {
        return changePasswd;
    }

    /**
     * changePasswd을 설정
     *
     * @param changePasswd 을(를) String changePasswd로 설정
     */
    public void setChangePasswd(String changePasswd) {
        this.changePasswd = changePasswd;
    }

    /**
     * String confirmPasswd을 반환
     *
     * @return String confirmPasswd
     */
    public String getConfirmPasswd() {
        return confirmPasswd;
    }

    /**
     * confirmPasswd을 설정
     *
     * @param confirmPasswd 을(를) String confirmPasswd로 설정
     */
    public void setConfirmPasswd(String confirmPasswd) {
        this.confirmPasswd = confirmPasswd;
    }

    /**
     * String picIds을 반환
     *
     * @return String picIds
     */
    public String getPicIds() {
        return picIds;
    }

    /**
     * picIds을 설정
     *
     * @param picIds 을(를) String picIds로 설정
     */
    public void setPicIds(String picIds) {
        this.picIds = picIds;
    }

    /**
     * String[] picIdArray을 반환
     *
     * @return String[] picIdArray
     */
    public String[] getPicIdArray() {
        return picIdArray;
    }

    /**
     * picIdArray을 설정
     *
     * @param picIdArray 을(를) String[] picIdArray로 설정
     */
    public void setPicIdArray(String[] picIdArray) {
        this.picIdArray = picIdArray;
    }

    /**
     * String authrtCdId을 반환
     *
     * @return String authrtCdId
     */
    public String getAuthrtCdId() {
        return authrtCdId;
    }

    /**
     * authrtCdId을 설정
     *
     * @param authrtCdId 을(를) String authrtCdId로 설정
     */
    public void setAuthrtCdId(String authrtCdId) {
        this.authrtCdId = authrtCdId;
    }

    /**
     * String authrtCdIds을 반환
     *
     * @return String authrtCdIds
     */
    public String getAuthrtCdIds() {
        return authrtCdIds;
    }

    /**
     * authrtCdIds을 설정
     *
     * @param authrtCdIds 을(를) String authrtCdIds로 설정
     */
    public void setAuthrtCdIds(String authrtCdIds) {
        this.authrtCdIds = authrtCdIds;
    }

    /**
     * String acntNm을 반환
     *
     * @return String acntNm
     */
    public String getAcntNm() {
        return acntNm;
    }

    /**
     * acntNm을 설정
     *
     * @param acntNm 을(를) String acntNm로 설정
     */
    public void setAcntNm(String acntNm) {
        this.acntNm = acntNm;
    }

}
