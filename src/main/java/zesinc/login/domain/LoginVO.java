/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import zesinc.core.lang.Validate;
import zesinc.web.auth.AuthSupport;
import zesinc.web.vo.ISessVO;

/**
 * 로그인 정보를 관리하는 VO 객체로 Spring Security를 위한 인터페이스를 구현한다.
 * 각각의 인터페이스 메소드는 Spring Security에서 호출되는 메소드이다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 7.    방기배   최초작성
 *  2019. 7. 17.   황신욱   로그인실패건수 추가
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class LoginVO implements UserDetails, ISessVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -6656331078421359388L;

    /** 상위부서코드 */
    private String upDeptCdId;

    /** 상위부서명 */
    private String upperDeptNm;

    /** 부서코드 */
    private String deptCdId;

    /** 부서명 */
    private String deptNm;

    /** 담당자ID */
    private String picId;

    /** 담당자명 */
    private String picNm;

    /** 담당자비밀번호 */
    private String picPswd;

    /** 상태코드 */
    private Integer sttsSn;

    /** 전화번호1 */
    private String rgnTelno;

    /** 전화번호2 */
    private String telofcTelno;

    /** 전화번호3 */
    private String indivTelno;

    /** 사용여부 */
    private String useYn;

    /** 로그인성공여부 */
    private Boolean matcheAt;

    /** 비밀번호변경일 */
    private Date pswdChgDt;

    /** 비밀번호 만료여부 */
    private String passwordEndAt;

    /** 로그인일시 */
    private Date lgnDt;

    /** 권한그룹코드 목록 */
    private List<String> authrtCdIdList;

    /** 권한정보 목록 */
    private Set<GrantedAuthority> authorities;

    /** 인증서계정 */
    private String acntNm;

    /** 로그인실패건수 */
    private Integer lgnFailNmtm;

    /**
     * 권한그룹코드 목록 반환
     *
     * @return List<String> authrtCdIdList
     */
    public List<String> getAuthrtCdIdList() {
        return authrtCdIdList;
    }

    /**
     * 권한그룹코드 목록 설정
     *
     * @param authrtCdIdList 을(를) List<String> authrtCdIdList로 설정
     */
    public void setAuthrtCdIdList(List<String> authrtCdIdList) {
        this.authrtCdIdList = authrtCdIdList;
    }

    /**
     * 권한 목록 설정
     *
     * @param authorities
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {

        this.authorities = Collections.unmodifiableSet(AuthSupport.sortAuthorities(authorities));
    }

    /**
     * 권한 목록 반환
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    /**
     * 권한 목록 반환
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthoritiesList() {

        return getAuthorities();
    }

    /**
     * 비밀번호를 반환한다. 단 암호화가 되어 있는 상태의 비밀번호이어야 하며 로그인 등에서
     * 입력된 비밀번호를 DB내의 비밀번호와 동일하게 암호화하여 비교하여 인증한다.
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getPswd()
     */
    @Override
    public String getPassword() {
        return getPicPswd();
    }

    /**
     * 담당자 ID를 반환한다.(담당자 이름이 아님에 유의한다.)
     *
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {

        return getPicId();
    }

    /**
     * 담당자 이름을 반환한다. (내외부 공용 사용 인터페이스 정의함수)
     */
    @Override
    public String getFullname() {

        return getPicNm();
    }

    /**
     * 담당자 계정의 유효성을 반환한다. 'Y' 상태이면 정상 계정이다.
     * 따라서 'Y' 인경우 true를 반환한다.
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        String useYn = getUseYn();

        return (Validate.isNotEmpty(useYn) && useYn.equals("Y"));
    }

    /**
     * 계정의 잠금상태 여부를 반환한다.
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        Integer statusCode = getSttsSn();

        return (Validate.isNotEmpty(statusCode) && statusCode.equals(1010));
    }

    /**
     * 비밀번호 변경주기에 따른 만료여부 확인.
     * 로그인 허용후 초기화면이 아닌 비밀번호 변경권한 페이지로 이동시킴
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        //String passwordEndAt = getPasswordEndAt();

        //return (Validate.isNotEmpty(passwordEndAt) && passwordEndAt.equals("N"));
        return true;
    }

    /**
     * 계정이 유효한 상태인지를 확인하여 결과를 반환
     * 현재는 사용여부만 확인하여 반환
     *
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        String useYn = getUseYn();

        return (Validate.isNotEmpty(useYn) && useYn.equals("Y"));
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
     * String rgnTelno을 반환
     *
     * @return String rgnTelno
     */
    public String getRgnTelno() {
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
     * String indivTelno을 반환
     *
     * @return String indivTelno
     */
    public String getIndivTelno() {
        return indivTelno;
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
     * telofcTelno을 설정
     *
     * @param telofcTelno 을(를) String telofcTelno로 설정
     */
    public void setTelofcTelno(String telofcTelno) {
        this.telofcTelno = telofcTelno;
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
     * Date pswdChgDt을 반환
     *
     * @return Date pswdChgDt
     */
    public Date getPswdChgDt() {
        return pswdChgDt;
    }

    /**
     * pswdChgDt을 설정
     *
     * @param pswdChgDt 을(를) Date pswdChgDt로 설정
     */
    public void setPswdChgDt(Date pswdChgDt) {
        this.pswdChgDt = pswdChgDt;
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

    /**
     * Integer lgnFailNmtm을 반환
     * @return Integer lgnFailNmtm
     */
    public Integer getLgnFailNmtm() {
        return lgnFailNmtm;
    }

    /**
     * lgnFailNmtm을 설정
     * @param lgnFailNmtm 을(를) Integer lgnFailNmtm로 설정
     */
    public void setLgnFailNmtm(Integer lgnFailNmtm) {
        this.lgnFailNmtm = lgnFailNmtm;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LoginVO [authorities=" + authorities + ", getAuthorities()=" + getAuthorities() + ", getPswd()=" + getPswd()
            + ", getUsername()=" + getUsername() + ", isAccountNonExpired()=" + isAccountNonExpired() + ", isAccountNonLocked()="
            + isAccountNonLocked() + ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()=" + isEnabled() + "]";
    }

	@Override
	public String getPswd() {
		return picPswd;
	}


}
