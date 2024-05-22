/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.accessHist.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 사용자 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-08-01.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserAccessHistVO extends PageVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 4121663096515470937L;
	/** 사용자ID */
	@Required
	@MaxLength(max = 20)
	private String userId;

	/** 사용자명 */
	@Required
	@MaxLength(max = 100)
	private String userNm;

	/** 사용자비밀번호 */
	@Required
	@MaxLength(max = 100)
	private String userPswd;

	/** 사용자키 */
	@MaxLength(max = 100)
	private String userIdntfNm;

	/** 이메일1 */
	@MaxLength(max = 100)
	private String emlId;

	/** 이메일2 */
	@MaxLength(max = 100)
	private String emlSiteNm;

	/** 가입유형 */
	@Digits
	private Integer joinTypeSn;

	/** 전화번호1 */
	@MaxLength(max = 4)
	private String rgnTelno;

	/** 전화번호2 */
	@MaxLength(max = 4)
	private String telofcTelno;

	/** 전화번호3 */
	@MaxLength(max = 4)
	private String indivTelno;

	/** 우편번호 */
	@MaxLength(max = 5)
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
	@MaxLength(max = 1000)
	private String itrstArtclCn;

	/** 로그인건수 */
	@Required
	@Digits
	private Integer lgnNmtm;

	/** 사용자유형 */
	@MaxLength(max = 20)
	private String userTypeNm;

	/** 사용자등급코드목록 */
	@MaxLength(max = 4000)
	private String userGrdCdDsctn;

	/** 사용자상태 */
	@Required
	@Digits
	private Integer userSttsSn;

	/** 비밀번호변경일 */
	private Date pswdChgDt;

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

	/** IP카운트 */
	private Integer ipCount;

	/** 로그인IP */
	private String lgnIpAddr;

	/** 로그인결과코드 */
	private String lgnRsltNo;

	/** 로그인날짜 */
	private Date lgnDt;

	/**
	 * 사용자ID 설정
	 * 
	 * @param userId을(를) String userId로 설정
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 사용자ID 반환
	 * 
	 * @return String userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 사용자명 설정
	 * 
	 * @param userNm을(를) String userNm로 설정
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * 사용자명 반환
	 * 
	 * @return String userNm
	 */
	public String getUserNm() {
		return userNm;
	}

	/**
	 * 사용자비밀번호 설정
	 * 
	 * @param userPswd을(를) String userPswd로 설정
	 */
	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}

	/**
	 * 사용자비밀번호 반환
	 * 
	 * @return String userPswd
	 */
	public String getUserPswd() {
		return userPswd;
	}

	/**
	 * 사용자키 설정
	 * 
	 * @param userIdntfNm을(를) String userIdntfNm로 설정
	 */
	public void setUserIdntfNm(String userIdntfNm) {
		this.userIdntfNm = userIdntfNm;
	}

	/**
	 * 사용자키 반환
	 * 
	 * @return String userIdntfNm
	 */
	public String getUserIdntfNm() {
		return userIdntfNm;
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
	 * 가입유형 설정
	 * 
	 * @param joinTypeSn을(를) Integer joinTypeSn로 설정
	 */
	public void setJoinTypeSn(Integer joinTypeSn) {
		this.joinTypeSn = joinTypeSn;
	}

	/**
	 * 가입유형 반환
	 * 
	 * @return Integer joinTypeSn
	 */
	public Integer getJoinTypeSn() {
		return joinTypeSn;
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
	 * 기본주소 설정
	 * 
	 * @param userAddr을(를) String userAddr로 설정
	 */
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	/**
	 * 기본주소 반환
	 * 
	 * @return String userAddr
	 */
	public String getUserAddr() {
		return userAddr;
	}

	/**
	 * 상세주소 설정
	 * 
	 * @param daddr을(를) String daddr로 설정
	 */
	public void setDaddr(String daddr) {
		this.daddr = daddr;
	}

	/**
	 * 상세주소 반환
	 * 
	 * @return String daddr
	 */
	public String getDaddr() {
		return daddr;
	}

	/**
	 * 최근접속일시 설정
	 * 
	 * @param lastCntnDt을(를) Date lastCntnDt로 설정
	 */
	public void setLastCntnDt(Date lastCntnDt) {
		this.lastCntnDt = lastCntnDt;
	}

	/**
	 * 최근접속일시 반환
	 * 
	 * @return Date lastCntnDt
	 */
	public Date getLastCntnDt() {
		return lastCntnDt;
	}

	/**
	 * 관심항목명 설정
	 * 
	 * @param itrstArtclCn을(를) String itrstArtclCn로 설정
	 */
	public void setItrstArtclCn(String itrstArtclCn) {
		this.itrstArtclCn = itrstArtclCn;
	}

	/**
	 * 관심항목명 반환
	 * 
	 * @return String itrstArtclCn
	 */
	public String getItrstArtclCn() {
		return itrstArtclCn;
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
	 * 사용자유형 설정
	 * 
	 * @param userTypeNm을(를) String userTypeNm로 설정
	 */
	public void setUserTypeNm(String userTypeNm) {
		this.userTypeNm = userTypeNm;
	}

	/**
	 * 사용자유형 반환
	 * 
	 * @return String userTypeNm
	 */
	public String getUserTypeNm() {
		return userTypeNm;
	}

	/**
	 * 사용자등급코드목록 설정
	 * 
	 * @param userGrdCdDsctn을(를) String userGrdCdDsctn로 설정
	 */
	public void setUserGrdCdDsctn(String userGrdCdDsctn) {
		this.userGrdCdDsctn = userGrdCdDsctn;
	}

	/**
	 * 사용자등급코드목록 반환
	 * 
	 * @return String userGrdCdDsctn
	 */
	public String getUserGrdCdDsctn() {
		return userGrdCdDsctn;
	}

	/**
	 * 사용자상태 설정
	 * 
	 * @param userSttsSn을(를) Integer userSttsSn로 설정
	 */
	public void setUserSttsSn(Integer userSttsSn) {
		this.userSttsSn = userSttsSn;
	}

	/**
	 * 사용자상태 반환
	 * 
	 * @return Integer userSttsSn
	 */
	public Integer getUserSttsSn() {
		return userSttsSn;
	}

	/**
	 * 비밀번호변경일 설정
	 * 
	 * @param pswdChgDt을(를) Date pswdChgDt로 설정
	 */
	public void setPswdChgDt(Date pswdChgDt) {
		this.pswdChgDt = pswdChgDt;
	}

	/**
	 * 비밀번호변경일 반환
	 * 
	 * @return Date pswdChgDt
	 */
	public Date getPswdChgDt() {
		return pswdChgDt;
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
	 * Integer ipCount을 반환
	 * 
	 * @return Integer ipCount
	 */
	public Integer getIpCount() {
		return ipCount;
	}

	/**
	 * ipCount을 설정
	 * 
	 * @param ipCount 을(를) Integer ipCount로 설정
	 */
	public void setIpCount(Integer ipCount) {
		this.ipCount = ipCount;
	}

	/**
	 * String lgnIpAddr을 반환
	 * 
	 * @return String lgnIpAddr
	 */
	public String getLgnIpAddr() {
		return lgnIpAddr;
	}

	/**
	 * lgnIpAddr을 설정
	 * 
	 * @param lgnIpAddr 을(를) String lgnIpAddr로 설정
	 */
	public void setLgnIpAddr(String lgnIpAddr) {
		this.lgnIpAddr = lgnIpAddr;
	}

	/**
	 * String lgnRsltNo을 반환
	 * 
	 * @return String lgnRsltNo
	 */
	public String getLgnRsltNo() {
		return lgnRsltNo;
	}

	/**
	 * lgnRsltNo을 설정
	 * 
	 * @param lgnRsltNo 을(를) String lgnRsltNo로 설정
	 */
	public void setLgnRsltNo(String lgnRsltNo) {
		this.lgnRsltNo = lgnRsltNo;
	}

	/**
	 * Date lgnDt을 반환
	 * 
	 * @return Date lgnDt
	 */
	public Date getLgnDt() {
		return lgnDt;
	}

	/**
	 * lgnDt을 설정
	 * 
	 * @param lgnDt 을(를) Date lgnDt로 설정
	 */
	public void setLgnDt(Date lgnDt) {
		this.lgnDt = lgnDt;
	}

}
