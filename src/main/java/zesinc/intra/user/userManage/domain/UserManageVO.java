/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userManage.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.NotNull;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 사용자 정보 VO 클래스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 27.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserManageVO extends PageVO {

    private static final long serialVersionUID = 5136054641453565605L;

    /** 사용자ID */
    @MaxLength(max = 20)
    @AlphaNumeric
    @NotNull
    private String userId;

    /** 사용자명 */
    @MaxLength(max = 100)
    @Required
    private String userNm;

    /** 사용자비밀번호 */
    @MaxLength(max = 100)
    @Required
    private String userPswd;

    /** 사용자비밀번호 확인 */
    @MaxLength(max = 100)
    @Required
    private String confirmPassword;

    /** 사용자키 */
    private String userIdntfNm;

    /** 이메일1 */
    @MaxLength(max = 100)
    private String emlId;

    /** 이메일2 */
    @MaxLength(max = 100)
    private String emlSiteNm;

    /** 가입유형 */
    private String joinTypeSn;

    /** 가입유형 명 */
    private String sbscrbTyNm;

    /** 전화번호1 */
    @MaxLength(max = 4)
    @Digits
    private String rgnTelno;

    /** 전화번호2 */
    @MaxLength(max = 4)
    @Digits
    private String telofcTelno;

    /** 전화번호3 */
    @MaxLength(max = 4)
    @Digits
    private String indivTelno;

    /** 우편번호 */
    @MaxLength(max = 5)
    @Digits
    private String zip;

    /** 기본주소 */
    @MaxLength(max = 200)
    private String userAddr;

    /** 상세주소 */
    @MaxLength(max = 200)
    private String daddr;

    /** 최근접속일시 */
    private Date lastCntnDt;

    /** 관심항목명 */
    private String itrstArtclCn;

    /** 로그인건수 */
    @Digits
    private Integer lgnNmtm;

    /** 사용자유형 */
    private String userTypeNm;

    /** 회원등급코드 */
    private String userGrdCdId;

    /** 회원등급코드목록 */
    private String userGrdCdDsctn;

    /** 회원등급코드목록[배열] */
    private String[] userGrdCdDsctns;

    /** 회원등급명 */
    private String userGrdNm;

    /** 회원상태 */
    private String userSttsSn;

    /** 비밀번호변경일 */
    private String pswdChgDt;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    private Date regDt;

    /** 수정일시 */
    private Date updtDt;

    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 총마일리지 */
    private Integer totalAmount;

    /** 주민등록번호1 */
    @MaxLength(max = 13)
    @Digits
    private String rrno;

    /** 휴대전화번호1 */
    @MaxLength(max = 4)
    @Digits
    private String mblRgnTelno;

    /** 휴대전화번호2 */
    @MaxLength(max = 4)
    @Digits
    private String mblTelofcTelno;

    /** 휴대전화번호3 */
    @MaxLength(max = 4)
    @Digits
    private String mblIndivTelno;

    /** 생년월일 */
    @MaxLength(max = 8)
    @Digits
    private String brdt;

    /** 생일 타입 - 양력음력윤달여부 */
    private String brthYmdClsfSn;

    /** 성별 */
    private String gndrClsfSn;

    /** 메일링서비스여부 */
    private String emlRcptnAgreClsfSn;

    /** SMS수신여부 */
    private String smsRcptnClsfSn;

    /** 최종학력 */
    private String lastAcbgNo;

    /** 결혼여부 */
    private String mrgSeSn;

    /** 직장명 */
    @MaxLength(max = 100)
    private String wrcNm;

    /** 직책 */
    @MaxLength(max = 100)
    private String jbttlNm;

    /** 직장주소-우편번호 */
    @MaxLength(max = 5)
    @Digits
    private String wrcZip;

    /** 직장기본주소 */
    @MaxLength(max = 200)
    private String wrcAddr;

    /** 직장상세주소 */
    @MaxLength(max = 200)
    private String wrcDaddr;

    /** 직장전화번호1 */
    @MaxLength(max = 4)
    @Digits
    private String wrcRgnTelno;

    /** 직장전화번호2 */
    @MaxLength(max = 4)
    @Digits
    private String wrcTelofcTelno;

    /** 직장전화번호3 */
    @MaxLength(max = 4)
    @Digits
    private String wrcIndivTelno;

    /** 담당자비밀번호 */
    private String picPswd;

    /** 대표자명 */
    @MaxLength(max = 100)
    private String rprsvNm;

    /** 법인등록번호1 */
    @MaxLength(max = 13)
    @Digits
    private String crno;

    /** 사업자등록번호1 */
    @MaxLength(max = 10)
    @Digits
    private String brno;

    /** 담당자명 */
    @MaxLength(max = 100)
    private String picNm;

    /** 부서명 */
    @MaxLength(max = 200)
    private String deptNm;

    /** 부서전화번호1 */
    @MaxLength(max = 4)
    @Digits
    private String deptRgnTelno;

    /** 부서전화번호2 */
    @MaxLength(max = 4)
    @Digits
    private String deptTelofcTelno;

    /** 부서전화번호3 */
    @MaxLength(max = 4)
    @Digits
    private String deptIndivTelno;

    /** 팩스번호1 */
    @MaxLength(max = 4)
    @Digits
    private String rgnFxno;

    /** 팩스번호2 */
    @MaxLength(max = 4)
    @Digits
    private String telofcFxno;

    /** 팩스번호3 */
    @MaxLength(max = 4)
    @Digits
    private String indivFxno;

    /** 리턴 URL */
    private String returnUrl;

    /** 사유 */
    @RangeLength(min = 10, max = 4000)
    private String logCn;

    /**
     * String userId을 반환
     * 
     * @return String userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * userId을 설정
     * 
     * @param userId 을(를) String userId로 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * String userNm을 반환
     * 
     * @return String userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * userNm을 설정
     * 
     * @param userNm 을(를) String userNm로 설정
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * String userPswd을 반환
     * 
     * @return String userPswd
     */
    public String getUserPswd() {
        return userPswd;
    }

    /**
     * userPswd을 설정
     * 
     * @param userPswd 을(를) String userPswd로 설정
     */
    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
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
     * String userIdntfNm을 반환
     * 
     * @return String userIdntfNm
     */
    public String getUserIdntfNm() {
        return userIdntfNm;
    }

    /**
     * userIdntfNm을 설정
     * 
     * @param userIdntfNm 을(를) String userIdntfNm로 설정
     */
    public void setUserIdntfNm(String userIdntfNm) {
        this.userIdntfNm = userIdntfNm;
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
     * String joinTypeSn을 반환
     * 
     * @return String joinTypeSn
     */
    public String getJoinTypeSn() {
        return joinTypeSn;
    }

    /**
     * joinTypeSn을 설정
     * 
     * @param joinTypeSn 을(를) String joinTypeSn로 설정
     */
    public void setJoinTypeSn(String joinTypeSn) {
        this.joinTypeSn = joinTypeSn;
    }

    /**
     * String sbscrbTyNm을 반환
     * 
     * @return String sbscrbTyNm
     */
    public String getSbscrbTyNm() {
        return sbscrbTyNm;
    }

    /**
     * sbscrbTyNm을 설정
     * 
     * @param sbscrbTyNm 을(를) String sbscrbTyNm로 설정
     */
    public void setSbscrbTyNm(String sbscrbTyNm) {
        this.sbscrbTyNm = sbscrbTyNm;
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
     * String zip을 반환
     * 
     * @return String zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * zip을 설정
     * 
     * @param zip 을(를) String zip로 설정
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * String userAddr을 반환
     * 
     * @return String userAddr
     */
    public String getUserAddr() {
        return userAddr;
    }

    /**
     * userAddr을 설정
     * 
     * @param userAddr 을(를) String userAddr로 설정
     */
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    /**
     * String daddr을 반환
     * 
     * @return String daddr
     */
    public String getDaddr() {
        return daddr;
    }

    /**
     * daddr을 설정
     * 
     * @param daddr 을(를) String daddr로 설정
     */
    public void setDaddr(String daddr) {
        this.daddr = daddr;
    }

    /**
     * Date lastCntnDt을 반환
     * 
     * @return Date lastCntnDt
     */
    public Date getLastCntnDt() {
        return lastCntnDt;
    }

    /**
     * lastCntnDt을 설정
     * 
     * @param lastCntnDt 을(를) Date lastCntnDt로 설정
     */
    public void setLastCntnDt(Date lastCntnDt) {
        this.lastCntnDt = lastCntnDt;
    }

    /**
     * String itrstArtclCn을 반환
     * 
     * @return String itrstArtclCn
     */
    public String getItrstArtclCn() {
        return itrstArtclCn;
    }

    /**
     * itrstArtclCn을 설정
     * 
     * @param itrstArtclCn 을(를) String itrstArtclCn로 설정
     */
    public void setItrstArtclCn(String itrstArtclCn) {
        this.itrstArtclCn = itrstArtclCn;
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
     * String userTypeNm을 반환
     * 
     * @return String userTypeNm
     */
    public String getUserTypeNm() {
        return userTypeNm;
    }

    /**
     * userTypeNm을 설정
     * 
     * @param userTypeNm 을(를) String userTypeNm로 설정
     */
    public void setUserTypeNm(String userTypeNm) {
        this.userTypeNm = userTypeNm;
    }

    /**
     * String userGrdCdId을 반환
     * 
     * @return String userGrdCdId
     */
    public String getUserGrdCdId() {
        return userGrdCdId;
    }

    /**
     * userGrdCdId을 설정
     * 
     * @param userGrdCdId 을(를) String userGrdCdId로 설정
     */
    public void setUserGrdCdId(String userGrdCdId) {
        this.userGrdCdId = userGrdCdId;
    }

    /**
     * String userGrdCdDsctn을 반환
     * 
     * @return String userGrdCdDsctn
     */
    public String getUserGrdCdDsctn() {
        return userGrdCdDsctn;
    }

    /**
     * userGrdCdDsctn을 설정
     * 
     * @param userGrdCdDsctn 을(를) String userGrdCdDsctn로 설정
     */
    public void setUserGrdCdDsctn(String userGrdCdDsctn) {
        this.userGrdCdDsctn = userGrdCdDsctn;
    }

    /**
     * String[] userGrdCdDsctns을 반환
     * 
     * @return String[] userGrdCdDsctns
     */
    public String[] getUserGrdCdDsctns() {
        return userGrdCdDsctns;
    }

    /**
     * userGrdCdDsctns을 설정
     * 
     * @param userGrdCdDsctns 을(를) String[] userGrdCdDsctns로 설정
     */
    public void setUserGrdCdDsctns(String[] userGrdCdDsctns) {
        this.userGrdCdDsctns = userGrdCdDsctns;
    }

    /**
     * String userGrdNm을 반환
     * 
     * @return String userGrdNm
     */
    public String getUserGrdNm() {
        return userGrdNm;
    }

    /**
     * userGrdNm을 설정
     * 
     * @param userGrdNm 을(를) String userGrdNm로 설정
     */
    public void setUserGrdNm(String userGrdNm) {
        this.userGrdNm = userGrdNm;
    }

    /**
     * String userSttsSn을 반환
     * 
     * @return String userSttsSn
     */
    public String getUserSttsSn() {
        return userSttsSn;
    }

    /**
     * userSttsSn을 설정
     * 
     * @param userSttsSn 을(를) String userSttsSn로 설정
     */
    public void setUserSttsSn(String userSttsSn) {
        this.userSttsSn = userSttsSn;
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
     * Date regDt을 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     * 
     * @param regDt 을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    /**
     * Date updtDt을 반환
     * 
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * updtDt을 설정
     * 
     * @param updtDt 을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
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
     * Integer totalAmount을 반환
     * 
     * @return Integer totalAmount
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * totalAmount을 설정
     * 
     * @param totalAmount 을(를) Integer totalAmount로 설정
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * String rrno을 반환
     * 
     * @return String rrno
     */
    public String getRrno() {
        return rrno;
    }

    /**
     * rrno을 설정
     * 
     * @param rrno 을(를) String rrno로 설정
     */
    public void setRrno(String rrno) {
        this.rrno = rrno;
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
     * String brdt을 반환
     * 
     * @return String brdt
     */
    public String getBrdt() {
        return brdt;
    }

    /**
     * brdt을 설정
     * 
     * @param brdt 을(를) String brdt로 설정
     */
    public void setBrdt(String brdt) {
        this.brdt = brdt;
    }

    /**
     * String brthYmdClsfSn을 반환
     * 
     * @return String brthYmdClsfSn
     */
    public String getBrthYmdClsfSn() {
        return brthYmdClsfSn;
    }

    /**
     * brthYmdClsfSn을 설정
     * 
     * @param brthYmdClsfSn 을(를) String brthYmdClsfSn로 설정
     */
    public void setBrthYmdClsfSn(String brthYmdClsfSn) {
        this.brthYmdClsfSn = brthYmdClsfSn;
    }

    /**
     * String gndrClsfSn을 반환
     * 
     * @return String gndrClsfSn
     */
    public String getGndrClsfSn() {
        return gndrClsfSn;
    }

    /**
     * gndrClsfSn을 설정
     * 
     * @param gndrClsfSn 을(를) String gndrClsfSn로 설정
     */
    public void setGndrClsfSn(String gndrClsfSn) {
        this.gndrClsfSn = gndrClsfSn;
    }

    /**
     * String emlRcptnAgreClsfSn을 반환
     * 
     * @return String emlRcptnAgreClsfSn
     */
    public String getEmlRcptnAgreClsfSn() {
        return emlRcptnAgreClsfSn;
    }

    /**
     * emlRcptnAgreClsfSn을 설정
     * 
     * @param emlRcptnAgreClsfSn 을(를) String emlRcptnAgreClsfSn로 설정
     */
    public void setEmlRcptnAgreClsfSn(String emlRcptnAgreClsfSn) {
        this.emlRcptnAgreClsfSn = emlRcptnAgreClsfSn;
    }

    /**
     * String smsRcptnClsfSn을 반환
     * 
     * @return String smsRcptnClsfSn
     */
    public String getSmsRcptnClsfSn() {
        return smsRcptnClsfSn;
    }

    /**
     * smsRcptnClsfSn을 설정
     * 
     * @param smsRcptnClsfSn 을(를) String smsRcptnClsfSn로 설정
     */
    public void setSmsRcptnClsfSn(String smsRcptnClsfSn) {
        this.smsRcptnClsfSn = smsRcptnClsfSn;
    }

    /**
     * String lastAcbgNo을 반환
     * 
     * @return String lastAcbgNo
     */
    public String getLastAcbgNo() {
        return lastAcbgNo;
    }

    /**
     * lastAcbgNo을 설정
     * 
     * @param lastAcbgNo 을(를) String lastAcbgNo로 설정
     */
    public void setLastAcbgNo(String lastAcbgNo) {
        this.lastAcbgNo = lastAcbgNo;
    }

    /**
     * String mrgSeSn을 반환
     * 
     * @return String mrgSeSn
     */
    public String getMrgSeSn() {
        return mrgSeSn;
    }

    /**
     * mrgSeSn을 설정
     * 
     * @param mrgSeSn 을(를) String mrgSeSn로 설정
     */
    public void setMrgSeSn(String mrgSeSn) {
        this.mrgSeSn = mrgSeSn;
    }

    /**
     * String wrcNm을 반환
     * 
     * @return String wrcNm
     */
    public String getWrcNm() {
        return wrcNm;
    }

    /**
     * wrcNm을 설정
     * 
     * @param wrcNm 을(를) String wrcNm로 설정
     */
    public void setWrcNm(String wrcNm) {
        this.wrcNm = wrcNm;
    }

    /**
     * String jbttlNm을 반환
     * 
     * @return String jbttlNm
     */
    public String getJbttlNm() {
        return jbttlNm;
    }

    /**
     * jbttlNm을 설정
     * 
     * @param jbttlNm 을(를) String jbttlNm로 설정
     */
    public void setJbttlNm(String jbttlNm) {
        this.jbttlNm = jbttlNm;
    }


    /**
     * String wrcZip을 반환
     * 
     * @return String wrcZip
     */
    public String getWrcZip() {
        return wrcZip;
    }

    /**
     * wrcZip을 설정
     * 
     * @param wrcZip 을(를) String wrcZip로 설정
     */
    public void setWrcZip(String wrcZip) {
        this.wrcZip = wrcZip;
    }

    /**
     * String wrcAddr을 반환
     * 
     * @return String wrcAddr
     */
    public String getWrcAddr() {
        return wrcAddr;
    }

    /**
     * wrcAddr을 설정
     * 
     * @param wrcAddr 을(를) String wrcAddr로 설정
     */
    public void setWrcAddr(String wrcAddr) {
        this.wrcAddr = wrcAddr;
    }

    /**
     * String wrcDaddr을 반환
     * 
     * @return String wrcDaddr
     */
    public String getWrcDaddr() {
        return wrcDaddr;
    }

    /**
     * wrcDaddr을 설정
     * 
     * @param wrcDaddr 을(를) String wrcDaddr로 설정
     */
    public void setWrcDaddr(String wrcDaddr) {
        this.wrcDaddr = wrcDaddr;
    }

    /**
     * String wrcRgnTelno을 반환
     * 
     * @return String wrcRgnTelno
     */
    public String getWrcRgnTelno() {
        return wrcRgnTelno;
    }

    /**
     * wrcRgnTelno을 설정
     * 
     * @param wrcRgnTelno 을(를) String wrcRgnTelno로 설정
     */
    public void setWrcRgnTelno(String wrcRgnTelno) {
        this.wrcRgnTelno = wrcRgnTelno;
    }

    /**
     * String wrcTelofcTelno을 반환
     * 
     * @return String wrcTelofcTelno
     */
    public String getWrcTelofcTelno() {
        return wrcTelofcTelno;
    }

    /**
     * wrcTelofcTelno을 설정
     * 
     * @param wrcTelofcTelno 을(를) String wrcTelofcTelno로 설정
     */
    public void setWrcTelofcTelno(String wrcTelofcTelno) {
        this.wrcTelofcTelno = wrcTelofcTelno;
    }

    /**
     * String wrcIndivTelno을 반환
     * 
     * @return String wrcIndivTelno
     */
    public String getWrcIndivTelno() {
        return wrcIndivTelno;
    }

    /**
     * wrcIndivTelno을 설정
     * 
     * @param wrcIndivTelno 을(를) String wrcIndivTelno로 설정
     */
    public void setWrcIndivTelno(String wrcIndivTelno) {
        this.wrcIndivTelno = wrcIndivTelno;
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
     * String rprsvNm을 반환
     * 
     * @return String rprsvNm
     */
    public String getRprsvNm() {
        return rprsvNm;
    }

    /**
     * rprsvNm을 설정
     * 
     * @param rprsvNm 을(를) String rprsvNm로 설정
     */
    public void setRprsvNm(String rprsvNm) {
        this.rprsvNm = rprsvNm;
    }

    /**
     * String crno을 반환
     * 
     * @return String crno
     */
    public String getCrno() {
        return crno;
    }

    /**
     * crno을 설정
     * 
     * @param crno 을(를) String crno로 설정
     */
    public void setCrno(String crno) {
        this.crno = crno;
    }


    /**
     * String brno을 반환
     * 
     * @return String brno
     */
    public String getBrno() {
        return brno;
    }

    /**
     * brno을 설정
     * 
     * @param brno 을(를) String brno로 설정
     */
    public void setBrno(String brno) {
        this.brno = brno;
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
     * String deptRgnTelno을 반환
     * 
     * @return String deptRgnTelno
     */
    public String getDeptRgnTelno() {
        return deptRgnTelno;
    }

    /**
     * deptRgnTelno을 설정
     * 
     * @param deptRgnTelno 을(를) String deptRgnTelno로 설정
     */
    public void setDeptRgnTelno(String deptRgnTelno) {
        this.deptRgnTelno = deptRgnTelno;
    }

    /**
     * String deptTelofcTelno을 반환
     * 
     * @return String deptTelofcTelno
     */
    public String getDeptTelofcTelno() {
        return deptTelofcTelno;
    }

    /**
     * deptTelofcTelno을 설정
     * 
     * @param deptTelofcTelno 을(를) String deptTelofcTelno로 설정
     */
    public void setDeptTelofcTelno(String deptTelofcTelno) {
        this.deptTelofcTelno = deptTelofcTelno;
    }

    /**
     * String deptIndivTelno을 반환
     * 
     * @return String deptIndivTelno
     */
    public String getDeptIndivTelno() {
        return deptIndivTelno;
    }

    /**
     * deptIndivTelno을 설정
     * 
     * @param deptIndivTelno 을(를) String deptIndivTelno로 설정
     */
    public void setDeptIndivTelno(String deptIndivTelno) {
        this.deptIndivTelno = deptIndivTelno;
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
     * String returnUrl을 반환
     * 
     * @return String returnUrl
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * returnUrl을 설정
     * 
     * @param returnUrl 을(를) String returnUrl로 설정
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    /**
     * String logCn을 반환
     * 
     * @return String logCn
     */
    public String getLogCn() {
        return logCn;
    }

    /**
     * logCn을 설정
     * 
     * @param logCn 을(를) String logCn로 설정
     */
    public void setLogCn(String logCn) {
        this.logCn = logCn;
    }

}
