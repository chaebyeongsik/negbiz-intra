/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.accessHist.domain;

import java.io.Serializable;

/**
 * 사용자 접속 이력 VO
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 8. 1.    Administrator   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserAccessHistStatisticsVO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 2797502785122886433L;

    /** 년 */
    private String cntnYr;

    /** 월 */
    private String cntnMm;

    /** 일 */
    private String cntnDay;

    /** 전체회원수 */
    private Integer wholMbrCnt;

    /** 로그인회원수 */
    private Integer lgnMbrCnt;

    /** G-PIN접속수 */
    private Integer gpinCntnNmtm;

    /** 공인인증서접속수 */
    private Integer certCntnNmtm;

    /** 실명인증접속수 */
    private Integer rnameCertCntnNmtm;

    /** 기타접속수 */
    private Integer etcCntnNmtm;

    /** 십대접속수 */
    private Integer teensCntnNmtm;

    /** 이십대접속수 */
    private Integer twentyCntnNmtm;

    /** 삼십대접속수 */
    private Integer thirtyCntnNmtm;

    /** 사십대접속수 */
    private Integer fortyCntnNmtm;

    /** 오십대접속수 */
    private Integer fiftyCntnNmtm;

    /** 육십대접속수 */
    private Integer sixtyCntnNmtm;

    /** 칠십대이상접속수 */
    private Integer svntAboveCntnNmtm;

    /** 남성접속수 */
    private Integer maleCntnNmtm;

    /** 여성접속수 */
    private Integer femaleCntnNmtm;

    /** 기타성접속수 */
    private Integer etcGndrCntnNmtm;

    /** 미로그인사용자 카운트 */
    private Integer notLoginUserCount;

    /** 회원유형카운트 */
    private Integer memberTypeCount;

    /** 연령 카운트 */
    private Integer ageTypeCount;

    /** 성별타입카운트 */
    private Integer sexTypeCount;

    /** 가입유형 */
    private String joinTypeSn;

    /**
     * String cntnYr을 반환
     * 
     * @return String cntnYr
     */
    public String getCntnYr() {
        return cntnYr;
    }

    /**
     * cntnYr을 설정
     * 
     * @param cntnYr 을(를) String cntnYr로 설정
     */
    public void setCntnYr(String cntnYr) {
        this.cntnYr = cntnYr;
    }

    /**
     * String mt을 반환
     * 
     * @return String cntnMm
     */
    public String getCntnMm() {
        return cntnMm;
    }

    /**
     * mt을 설정
     * 
     * @param cntnMm 을(를) String mt로 설정
     */
    public void setCntnMm(String cntnMm) {
        this.cntnMm = cntnMm;
    }

    /**
     * String de을 반환
     * 
     * @return String cntnDay
     */
    public String getCntnDay() {
        return cntnDay;
    }

    /**
     * de을 설정
     * 
     * @param cntnDay 을(를) String de로 설정
     */
    public void setCntnDay(String cntnDay) {
        this.cntnDay = cntnDay;
    }

    /**
     * Integer wholMbrCnt을 반환
     * 
     * @return Integer wholMbrCnt
     */
    public Integer getWholMbrCnt() {
        return wholMbrCnt;
    }

    /**
     * wholMbrCnt을 설정
     * 
     * @param wholMbrCnt 을(를) Integer wholMbrCnt로 설정
     */
    public void setWholMbrCnt(Integer wholMbrCnt) {
        this.wholMbrCnt = wholMbrCnt;
    }

    /**
     * Integer lgnMbrCnt을 반환
     * 
     * @return Integer lgnMbrCnt
     */
    public Integer getLgnMbrCnt() {
        return lgnMbrCnt;
    }

    /**
     * lgnMbrCnt을 설정
     * 
     * @param lgnMbrCnt 을(를) Integer lgnMbrCnt로 설정
     */
    public void setLgnMbrCnt(Integer lgnMbrCnt) {
        this.lgnMbrCnt = lgnMbrCnt;
    }

    /**
     * Integer gpinCntnNmtm을 반환
     * 
     * @return Integer gpinCntnNmtm
     */
    public Integer getGpinCntnNmtm() {
        return gpinCntnNmtm;
    }

    /**
     * gpinCntnNmtm을 설정
     * 
     * @param gpinCntnNmtm 을(를) Integer gpinCntnNmtm로 설정
     */
    public void setGpinCntnNmtm(Integer gpinCntnNmtm) {
        this.gpinCntnNmtm = gpinCntnNmtm;
    }

    /**
     * Integer certCntnNmtm을 반환
     * 
     * @return Integer certCntnNmtm
     */
    public Integer getCertCntnNmtm() {
        return certCntnNmtm;
    }

    /**
     * certCntnNmtm을 설정
     * 
     * @param certCntnNmtm 을(를) Integer certCntnNmtm로 설정
     */
    public void setCertCntnNmtm(Integer certCntnNmtm) {
        this.certCntnNmtm = certCntnNmtm;
    }

    /**
     * Integer rnameCertCntnNmtm을 반환
     * 
     * @return Integer rnameCertCntnNmtm
     */
    public Integer getRnameCertCntnNmtm() {
        return rnameCertCntnNmtm;
    }

    /**
     * rnameCertCntnNmtm을 설정
     * 
     * @param rnameCertCntnNmtm 을(를) Integer rnameCertCntnNmtm로 설정
     */
    public void setRnameCertCntnNmtm(Integer rnameCertCntnNmtm) {
        this.rnameCertCntnNmtm = rnameCertCntnNmtm;
    }

    /**
     * Integer etcCntnNmtm을 반환
     * 
     * @return Integer etcCntnNmtm
     */
    public Integer getEtcCntnNmtm() {
        return etcCntnNmtm;
    }

    /**
     * etcCntnNmtm을 설정
     * 
     * @param etcCntnNmtm 을(를) Integer etcCntnNmtm로 설정
     */
    public void setEtcCntnNmtm(Integer etcCntnNmtm) {
        this.etcCntnNmtm = etcCntnNmtm;
    }

    /**
     * Integer teensCntnNmtm을 반환
     * 
     * @return Integer teensCntnNmtm
     */
    public Integer getTeensCntnNmtm() {
        return teensCntnNmtm;
    }

    /**
     * teensCntnNmtm을 설정
     * 
     * @param teensCntnNmtm 을(를) Integer teensCntnNmtm로 설정
     */
    public void setTeensCntnNmtm(Integer teensCntnNmtm) {
        this.teensCntnNmtm = teensCntnNmtm;
    }

    /**
     * Integer twentyCntnNmtm을 반환
     * 
     * @return Integer twentyCntnNmtm
     */
    public Integer getTwentyCntnNmtm() {
        return twentyCntnNmtm;
    }

    /**
     * twentyCntnNmtm을 설정
     * 
     * @param twentyCntnNmtm 을(를) Integer twentyCntnNmtm로 설정
     */
    public void setTwentyCntnNmtm(Integer twentyCntnNmtm) {
        this.twentyCntnNmtm = twentyCntnNmtm;
    }

    /**
     * Integer thirtyCntnNmtm을 반환
     * 
     * @return Integer thirtyCntnNmtm
     */
    public Integer getThirtyCntnNmtm() {
        return thirtyCntnNmtm;
    }

    /**
     * thirtyCntnNmtm을 설정
     * 
     * @param thirtyCntnNmtm 을(를) Integer thirtyCntnNmtm로 설정
     */
    public void setThirtyCntnNmtm(Integer thirtyCntnNmtm) {
        this.thirtyCntnNmtm = thirtyCntnNmtm;
    }

    /**
     * Integer fortyCntnNmtm을 반환
     * 
     * @return Integer fortyCntnNmtm
     */
    public Integer getFortyCntnNmtm() {
        return fortyCntnNmtm;
    }

    /**
     * fortyCntnNmtm을 설정
     * 
     * @param fortyCntnNmtm 을(를) Integer fortyCntnNmtm로 설정
     */
    public void setFortyCntnNmtm(Integer fortyCntnNmtm) {
        this.fortyCntnNmtm = fortyCntnNmtm;
    }

    /**
     * Integer fiftyCntnNmtm을 반환
     * 
     * @return Integer fiftyCntnNmtm
     */
    public Integer getFiftyCntnNmtm() {
        return fiftyCntnNmtm;
    }

    /**
     * fiftyCntnNmtm을 설정
     * 
     * @param fiftyCntnNmtm 을(를) Integer fiftyCntnNmtm로 설정
     */
    public void setFiftyCntnNmtm(Integer fiftyCntnNmtm) {
        this.fiftyCntnNmtm = fiftyCntnNmtm;
    }

    /**
     * Integer sixtyCntnNmtm을 반환
     * 
     * @return Integer sixtyCntnNmtm
     */
    public Integer getSixtyCntnNmtm() {
        return sixtyCntnNmtm;
    }

    /**
     * sixtyCntnNmtm을 설정
     * 
     * @param sixtyCntnNmtm 을(를) Integer sixtyCntnNmtm로 설정
     */
    public void setSixtyCntnNmtm(Integer sixtyCntnNmtm) {
        this.sixtyCntnNmtm = sixtyCntnNmtm;
    }

    /**
     * Integer svntAboveCntnNmtm을 반환
     * 
     * @return Integer svntAboveCntnNmtm
     */
    public Integer getSvntAboveCntnNmtm() {
        return svntAboveCntnNmtm;
    }

    /**
     * svntAboveCntnNmtm을 설정
     * 
     * @param svntAboveCntnNmtm 을(를) Integer svntAboveCntnNmtm로 설정
     */
    public void setSvntAboveCntnNmtm(Integer svntAboveCntnNmtm) {
        this.svntAboveCntnNmtm = svntAboveCntnNmtm;
    }

    /**
     * Integer maleCntnNmtm을 반환
     * 
     * @return Integer maleCntnNmtm
     */
    public Integer getMaleCntnNmtm() {
        return maleCntnNmtm;
    }

    /**
     * maleCntnNmtm을 설정
     * 
     * @param maleCntnNmtm 을(를) Integer maleCntnNmtm로 설정
     */
    public void setMaleCntnNmtm(Integer maleCntnNmtm) {
        this.maleCntnNmtm = maleCntnNmtm;
    }

    /**
     * Integer femaleCntnNmtm을 반환
     * 
     * @return Integer femaleCntnNmtm
     */
    public Integer getFemaleCntnNmtm() {
        return femaleCntnNmtm;
    }

    /**
     * femaleCntnNmtm을 설정
     * 
     * @param femaleCntnNmtm 을(를) Integer femaleCntnNmtm로 설정
     */
    public void setFemaleCntnNmtm(Integer femaleCntnNmtm) {
        this.femaleCntnNmtm = femaleCntnNmtm;
    }

    /**
     * Integer etcGndrCntnNmtm을 반환
     * 
     * @return Integer etcGndrCntnNmtm
     */
    public Integer getEtcGndrCntnNmtm() {
        return etcGndrCntnNmtm;
    }

    /**
     * etcGndrCntnNmtm을 설정
     * 
     * @param etcGndrCntnNmtm 을(를) Integer etcGndrCntnNmtm로 설정
     */
    public void setEtcGndrCntnNmtm(Integer etcGndrCntnNmtm) {
        this.etcGndrCntnNmtm = etcGndrCntnNmtm;
    }

    /**
     * Integer notLoginUserCount을 반환
     * 
     * @return Integer notLoginUserCount
     */
    public Integer getNotLoginUserCount() {
        return notLoginUserCount;
    }

    /**
     * notLoginUserCount을 설정
     * 
     * @param notLoginUserCount 을(를) Integer notLoginUserCount로 설정
     */
    public void setNotLoginUserCount(Integer notLoginUserCount) {
        this.notLoginUserCount = notLoginUserCount;
    }

    /**
     * Integer memberTypeCount을 반환
     * 
     * @return Integer memberTypeCount
     */
    public Integer getMemberTypeCount() {
        return memberTypeCount;
    }

    /**
     * memberTypeCount을 설정
     * 
     * @param memberTypeCount 을(를) Integer memberTypeCount로 설정
     */
    public void setMemberTypeCount(Integer memberTypeCount) {
        this.memberTypeCount = memberTypeCount;
    }

    /**
     * Integer ageTypeCount을 반환
     * 
     * @return Integer ageTypeCount
     */
    public Integer getAgeTypeCount() {
        return ageTypeCount;
    }

    /**
     * ageTypeCount을 설정
     * 
     * @param ageTypeCount 을(를) Integer ageTypeCount로 설정
     */
    public void setAgeTypeCount(Integer ageTypeCount) {
        this.ageTypeCount = ageTypeCount;
    }

    /**
     * Integer sexTypeCount을 반환
     * 
     * @return Integer sexTypeCount
     */
    public Integer getSexTypeCount() {
        return sexTypeCount;
    }

    /**
     * sexTypeCount을 설정
     * 
     * @param sexTypeCount 을(를) Integer sexTypeCount로 설정
     */
    public void setSexTypeCount(Integer sexTypeCount) {
        this.sexTypeCount = sexTypeCount;
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

}
