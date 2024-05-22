/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.userInfoIemEstbs.domain;

import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 사용자정보항목설정 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-05-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserInfoIemEstbsVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -1511816555141421356L;
    /** 사용자유형 */
    @Required
    @MaxLength(max = 20)
    private String userTypeNm;

    /** 사용여부코드 */
    @Digits
    private Integer useYnCode;

    /** 설정항목코드 */
    @Required
    @Digits
    private Integer stngArtclSn;

    /** 설정항목명 */
    private String stngArtclNm;

    /** 사용여부코드명 */
    private String useYnCodeNm;

    /** 사용항목코드 */
    @MaxLength(max = 4)
    private String useIemCode;

    /** 사용항목코드명 */
    private String useIemCodeNm;

    /** 항목유형 */
    private String artclTypeCd;

    /** 주민등록번호 사용여부 */
    private Integer ihidnumUseAt;

    /** 이메일 사용여부 */
    private Integer emailUseAt;

    /** 전화번호 사용여부 */
    private Integer telnoUseAt;

    /** 휴대전화 사용여부 */
    private Integer moblphonNoUseAt;

    /** 주소 사용여부 */
    private Integer adresUseAt;

    /** 생일 사용여부 */
    private Integer brthdyUseAt;

    /** 성별 사용여부 */
    private Integer sexdstnUseAt;

    /** 메일링서비스 사용여부 */
    private Integer mailingSvcUseAt;

    /** SMS수신 사용여부 */
    private Integer smsRecptnUseAt;

    /** 자동가입방지 사용여부 */
    private Integer captchaUseAt;

    /** 최종학력 사용여부 */
    private Integer lastAcdmcrUseAt;

    /** 결혼사용여부 */
    private Integer mrrgUseAt;

    /** 직장명 사용여부 */
    private Integer wrcNmUseAt;

    /** 직책 사용여부 */
    private Integer rspofcUseAt;

    /** 직장주소 사용여부 */
    private Integer wrcAdresUseAt;

    /** 직장전화번호 사용여부 */
    private Integer wrcTelnoUseAt;

    /** 관심항목명 사용여부 */
    private Integer intrstIemNmUseAt;

    /** 개인사용자 사용여부 */
    private String indvdlUserUseAt;

    /** 사업자사용자 사용여부 */
    private String bsnmUserUseAt;

    /** 일련번호 */
    private Integer regSn;

    /** 항목명 */
    private String artclNm;

    /** 사용여부 */
    private String useYn;

    private String rprsntvNmUseAt;

    private String bizrnoUseAt;

    private String jurirnoUseAt;

    private String picNmUseAt;

    private String deptNmUseAt;

    private String deptTelnoUseAt;

    private String fxnumUseAt;

    private List<UserInfoIemEstbsVO> basicInfo;

    private List<UserInfoIemEstbsVO> etcInfo;

    private Integer[] stngArtclSns;

    private String mileageUseAt;

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
     * Integer useYnCode을 반환
     * 
     * @return Integer useYnCode
     */
    public Integer getUseYnCode() {
        return useYnCode;
    }

    /**
     * useYnCode을 설정
     * 
     * @param useYnCode 을(를) Integer useYnCode로 설정
     */
    public void setUseYnCode(Integer useYnCode) {
        this.useYnCode = useYnCode;
    }

    /**
     * 설정항목코드 설정
     * 
     * @param stngArtclSn을(를) Integer stngArtclSn로 설정
     */
    public void setStngArtclSn(Integer stngArtclSn) {
        this.stngArtclSn = stngArtclSn;
    }

    /**
     * 설정항목코드 반환
     * 
     * @return Integer stngArtclSn
     */
    public Integer getStngArtclSn() {
        return stngArtclSn;
    }

    /**
     * 설정항목명 설정
     * 
     * @param stngArtclNm을(를) String stngArtclNm로 설정
     */
    public void setStngArtclNm(String stngArtclNm) {
        this.stngArtclNm = stngArtclNm;
    }

    /**
     * 설정항목명 반환
     * 
     * @return String stngArtclNm
     */
    public String getStngArtclNm() {
        return stngArtclNm;
    }

    /**
     * 사용여부코드명 설정
     * 
     * @param useYnCodeNm을(를) String useYnCodeNm로 설정
     */
    public void setUseYnCodeNm(String useYnCodeNm) {
        this.useYnCodeNm = useYnCodeNm;
    }

    /**
     * 사용여부코드명 반환
     * 
     * @return String useYnCodeNm
     */
    public String getUseYnCodeNm() {
        return useYnCodeNm;
    }

    /**
     * 사용항목코드 설정
     * 
     * @param useIemCode을(를) String useIemCode로 설정
     */
    public void setUseIemCode(String useIemCode) {
        this.useIemCode = useIemCode;
    }

    /**
     * 사용항목코드 반환
     * 
     * @return String useIemCode
     */
    public String getUseIemCode() {
        return useIemCode;
    }

    /**
     * 사용항목코드명 설정
     * 
     * @param useIemCodeNm을(를) String useIemCodeNm로 설정
     */
    public void setUseIemCodeNm(String useIemCodeNm) {
        this.useIemCodeNm = useIemCodeNm;
    }

    /**
     * 사용항목코드명 반환
     * 
     * @return String useIemCodeNm
     */
    public String getUseIemCodeNm() {
        return useIemCodeNm;
    }

    /**
     * 항목유형 설정
     * 
     * @param artclTypeCd을(를) String artclTypeCd로 설정
     */
    public void setArtclTypeCd(String artclTypeCd) {
        this.artclTypeCd = artclTypeCd;
    }

    /**
     * 항목유형 반환
     * 
     * @return String artclTypeCd
     */
    public String getArtclTypeCd() {
        return artclTypeCd;
    }

    /**
     * Integer ihidnumUseAt을 반환
     * 
     * @return Integer ihidnumUseAt
     */
    public Integer getIhidnumUseAt() {
        return ihidnumUseAt;
    }

    /**
     * ihidnumUseAt을 설정
     * 
     * @param ihidnumUseAt 을(를) Integer ihidnumUseAt로 설정
     */
    public void setIhidnumUseAt(Integer ihidnumUseAt) {
        this.ihidnumUseAt = ihidnumUseAt;
    }

    /**
     * Integer emailUseAt을 반환
     * 
     * @return Integer emailUseAt
     */
    public Integer getEmailUseAt() {
        return emailUseAt;
    }

    /**
     * emailUseAt을 설정
     * 
     * @param emailUseAt 을(를) Integer emailUseAt로 설정
     */
    public void setEmailUseAt(Integer emailUseAt) {
        this.emailUseAt = emailUseAt;
    }

    /**
     * Integer telnoUseAt을 반환
     * 
     * @return Integer telnoUseAt
     */
    public Integer getTelnoUseAt() {
        return telnoUseAt;
    }

    /**
     * telnoUseAt을 설정
     * 
     * @param telnoUseAt 을(를) Integer telnoUseAt로 설정
     */
    public void setTelnoUseAt(Integer telnoUseAt) {
        this.telnoUseAt = telnoUseAt;
    }

    /**
     * Integer moblphonNoUseAt을 반환
     * 
     * @return Integer moblphonNoUseAt
     */
    public Integer getMoblphonNoUseAt() {
        return moblphonNoUseAt;
    }

    /**
     * moblphonNoUseAt을 설정
     * 
     * @param moblphonNoUseAt 을(를) Integer moblphonNoUseAt로 설정
     */
    public void setMoblphonNoUseAt(Integer moblphonNoUseAt) {
        this.moblphonNoUseAt = moblphonNoUseAt;
    }

    /**
     * Integer adresUseAt을 반환
     * 
     * @return Integer adresUseAt
     */
    public Integer getAdresUseAt() {
        return adresUseAt;
    }

    /**
     * adresUseAt을 설정
     * 
     * @param adresUseAt 을(를) Integer adresUseAt로 설정
     */
    public void setAdresUseAt(Integer adresUseAt) {
        this.adresUseAt = adresUseAt;
    }

    /**
     * Integer brthdyUseAt을 반환
     * 
     * @return Integer brthdyUseAt
     */
    public Integer getBrthdyUseAt() {
        return brthdyUseAt;
    }

    /**
     * brthdyUseAt을 설정
     * 
     * @param brthdyUseAt 을(를) Integer brthdyUseAt로 설정
     */
    public void setBrthdyUseAt(Integer brthdyUseAt) {
        this.brthdyUseAt = brthdyUseAt;
    }

    /**
     * Integer sexdstnUseAt을 반환
     * 
     * @return Integer sexdstnUseAt
     */
    public Integer getSexdstnUseAt() {
        return sexdstnUseAt;
    }

    /**
     * sexdstnUseAt을 설정
     * 
     * @param sexdstnUseAt 을(를) Integer sexdstnUseAt로 설정
     */
    public void setSexdstnUseAt(Integer sexdstnUseAt) {
        this.sexdstnUseAt = sexdstnUseAt;
    }

    /**
     * Integer mailingSvcUseAt을 반환
     * 
     * @return Integer mailingSvcUseAt
     */
    public Integer getMailingSvcUseAt() {
        return mailingSvcUseAt;
    }

    /**
     * mailingSvcUseAt을 설정
     * 
     * @param mailingSvcUseAt 을(를) Integer mailingSvcUseAt로 설정
     */
    public void setMailingSvcUseAt(Integer mailingSvcUseAt) {
        this.mailingSvcUseAt = mailingSvcUseAt;
    }

    /**
     * Integer smsRecptnUseAt을 반환
     * 
     * @return Integer smsRecptnUseAt
     */
    public Integer getSmsRecptnUseAt() {
        return smsRecptnUseAt;
    }

    /**
     * smsRecptnUseAt을 설정
     * 
     * @param smsRecptnUseAt 을(를) Integer smsRecptnUseAt로 설정
     */
    public void setSmsRecptnUseAt(Integer smsRecptnUseAt) {
        this.smsRecptnUseAt = smsRecptnUseAt;
    }

    /**
     * Integer captchaUseAt을 반환
     * 
     * @return Integer captchaUseAt
     */
    public Integer getCaptchaUseAt() {
        return captchaUseAt;
    }

    /**
     * captchaUseAt을 설정
     * 
     * @param captchaUseAt 을(를) Integer captchaUseAt로 설정
     */
    public void setCaptchaUseAt(Integer captchaUseAt) {
        this.captchaUseAt = captchaUseAt;
    }

    /**
     * Integer lastAcdmcrUseAt을 반환
     * 
     * @return Integer lastAcdmcrUseAt
     */
    public Integer getLastAcdmcrUseAt() {
        return lastAcdmcrUseAt;
    }

    /**
     * lastAcdmcrUseAt을 설정
     * 
     * @param lastAcdmcrUseAt 을(를) Integer lastAcdmcrUseAt로 설정
     */
    public void setLastAcdmcrUseAt(Integer lastAcdmcrUseAt) {
        this.lastAcdmcrUseAt = lastAcdmcrUseAt;
    }

    /**
     * Integer mrrgUseAt을 반환
     * 
     * @return Integer mrrgUseAt
     */
    public Integer getMrrgUseAt() {
        return mrrgUseAt;
    }

    /**
     * mrrgUseAt을 설정
     * 
     * @param mrrgUseAt 을(를) Integer mrrgUseAt로 설정
     */
    public void setMrrgUseAt(Integer mrrgUseAt) {
        this.mrrgUseAt = mrrgUseAt;
    }

    /**
     * Integer wrcNmUseAt을 반환
     * 
     * @return Integer wrcNmUseAt
     */
    public Integer getWrcNmUseAt() {
        return wrcNmUseAt;
    }

    /**
     * wrcNmUseAt을 설정
     * 
     * @param wrcNmUseAt 을(를) Integer wrcNmUseAt로 설정
     */
    public void setWrcNmUseAt(Integer wrcNmUseAt) {
        this.wrcNmUseAt = wrcNmUseAt;
    }

    /**
     * Integer rspofcUseAt을 반환
     * 
     * @return Integer rspofcUseAt
     */
    public Integer getRspofcUseAt() {
        return rspofcUseAt;
    }

    /**
     * rspofcUseAt을 설정
     * 
     * @param rspofcUseAt 을(를) Integer rspofcUseAt로 설정
     */
    public void setRspofcUseAt(Integer rspofcUseAt) {
        this.rspofcUseAt = rspofcUseAt;
    }

    /**
     * Integer wrcAdresUseAt을 반환
     * 
     * @return Integer wrcAdresUseAt
     */
    public Integer getWrcAdresUseAt() {
        return wrcAdresUseAt;
    }

    /**
     * wrcAdresUseAt을 설정
     * 
     * @param wrcAdresUseAt 을(를) Integer wrcAdresUseAt로 설정
     */
    public void setWrcAdresUseAt(Integer wrcAdresUseAt) {
        this.wrcAdresUseAt = wrcAdresUseAt;
    }

    /**
     * Integer wrcTelnoUseAt을 반환
     * 
     * @return Integer wrcTelnoUseAt
     */
    public Integer getWrcTelnoUseAt() {
        return wrcTelnoUseAt;
    }

    /**
     * wrcTelnoUseAt을 설정
     * 
     * @param wrcTelnoUseAt 을(를) Integer wrcTelnoUseAt로 설정
     */
    public void setWrcTelnoUseAt(Integer wrcTelnoUseAt) {
        this.wrcTelnoUseAt = wrcTelnoUseAt;
    }

    /**
     * Integer intrstIemNmUseAt을 반환
     * 
     * @return Integer intrstIemNmUseAt
     */
    public Integer getIntrstIemNmUseAt() {
        return intrstIemNmUseAt;
    }

    /**
     * intrstIemNmUseAt을 설정
     * 
     * @param intrstIemNmUseAt 을(를) Integer intrstIemNmUseAt로 설정
     */
    public void setIntrstIemNmUseAt(Integer intrstIemNmUseAt) {
        this.intrstIemNmUseAt = intrstIemNmUseAt;
    }

    /**
     * String indvdlUserUseAt을 반환
     * 
     * @return String indvdlUserUseAt
     */
    public String getIndvdlUserUseAt() {
        return indvdlUserUseAt;
    }

    /**
     * indvdlUserUseAt을 설정
     * 
     * @param indvdlUserUseAt 을(를) String indvdlUserUseAt로 설정
     */
    public void setIndvdlUserUseAt(String indvdlUserUseAt) {
        this.indvdlUserUseAt = indvdlUserUseAt;
    }

    /**
     * String bsnmUserUseAt을 반환
     * 
     * @return String bsnmUserUseAt
     */
    public String getBsnmUserUseAt() {
        return bsnmUserUseAt;
    }

    /**
     * bsnmUserUseAt을 설정
     * 
     * @param bsnmUserUseAt 을(를) String bsnmUserUseAt로 설정
     */
    public void setBsnmUserUseAt(String bsnmUserUseAt) {
        this.bsnmUserUseAt = bsnmUserUseAt;
    }

    /**
     * Integer regSn을 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * regSn을 설정
     * 
     * @param regSn 을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * String artclNm을 반환
     * 
     * @return String artclNm
     */
    public String getArtclNm() {
        return artclNm;
    }

    /**
     * artclNm을 설정
     * 
     * @param artclNm 을(를) String artclNm로 설정
     */
    public void setArtclNm(String artclNm) {
        this.artclNm = artclNm;
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
     * String rprsntvNmUseAt을 반환
     * 
     * @return String rprsntvNmUseAt
     */
    public String getRprsntvNmUseAt() {
        return rprsntvNmUseAt;
    }

    /**
     * rprsntvNmUseAt을 설정
     * 
     * @param rprsntvNmUseAt 을(를) String rprsntvNmUseAt로 설정
     */
    public void setRprsntvNmUseAt(String rprsntvNmUseAt) {
        this.rprsntvNmUseAt = rprsntvNmUseAt;
    }

    /**
     * String bizrnoUseAt을 반환
     * 
     * @return String bizrnoUseAt
     */
    public String getBizrnoUseAt() {
        return bizrnoUseAt;
    }

    /**
     * bizrnoUseAt을 설정
     * 
     * @param bizrnoUseAt 을(를) String bizrnoUseAt로 설정
     */
    public void setBizrnoUseAt(String bizrnoUseAt) {
        this.bizrnoUseAt = bizrnoUseAt;
    }

    /**
     * String jurirnoUseAt을 반환
     * 
     * @return String jurirnoUseAt
     */
    public String getJurirnoUseAt() {
        return jurirnoUseAt;
    }

    /**
     * jurirnoUseAt을 설정
     * 
     * @param jurirnoUseAt 을(를) String jurirnoUseAt로 설정
     */
    public void setJurirnoUseAt(String jurirnoUseAt) {
        this.jurirnoUseAt = jurirnoUseAt;
    }

    /**
     * String picNmUseAt을 반환
     * 
     * @return String picNmUseAt
     */
    public String getPicNmUseAt() {
        return picNmUseAt;
    }

    /**
     * picNmUseAt을 설정
     * 
     * @param picNmUseAt 을(를) String picNmUseAt로 설정
     */
    public void setPicNmUseAt(String picNmUseAt) {
        this.picNmUseAt = picNmUseAt;
    }

    /**
     * String deptNmUseAt을 반환
     * 
     * @return String deptNmUseAt
     */
    public String getDeptNmUseAt() {
        return deptNmUseAt;
    }

    /**
     * deptNmUseAt을 설정
     * 
     * @param deptNmUseAt 을(를) String deptNmUseAt로 설정
     */
    public void setDeptNmUseAt(String deptNmUseAt) {
        this.deptNmUseAt = deptNmUseAt;
    }

    /**
     * String deptTelnoUseAt을 반환
     * 
     * @return String deptTelnoUseAt
     */
    public String getDeptTelnoUseAt() {
        return deptTelnoUseAt;
    }

    /**
     * deptTelnoUseAt을 설정
     * 
     * @param deptTelnoUseAt 을(를) String deptTelnoUseAt로 설정
     */
    public void setDeptTelnoUseAt(String deptTelnoUseAt) {
        this.deptTelnoUseAt = deptTelnoUseAt;
    }

    /**
     * String fxnumUseAt을 반환
     * 
     * @return String fxnumUseAt
     */
    public String getFxnumUseAt() {
        return fxnumUseAt;
    }

    /**
     * fxnumUseAt을 설정
     * 
     * @param fxnumUseAt 을(를) String fxnumUseAt로 설정
     */
    public void setFxnumUseAt(String fxnumUseAt) {
        this.fxnumUseAt = fxnumUseAt;
    }

    /**
     * List<UserInfoIemEstbsVO> basicInfo을 반환
     * 
     * @return List<UserInfoIemEstbsVO> basicInfo
     */
    public List<UserInfoIemEstbsVO> getBasicInfo() {
        return basicInfo;
    }

    /**
     * basicInfo을 설정
     * 
     * @param basicInfo 을(를) List<UserInfoIemEstbsVO> basicInfo로 설정
     */
    public void setBasicInfo(List<UserInfoIemEstbsVO> basicInfo) {
        this.basicInfo = basicInfo;
    }

    /**
     * List<UserInfoIemEstbsVO> etcInfo을 반환
     * 
     * @return List<UserInfoIemEstbsVO> etcInfo
     */
    public List<UserInfoIemEstbsVO> getEtcInfo() {
        return etcInfo;
    }

    /**
     * etcInfo을 설정
     * 
     * @param etcInfo 을(를) List<UserInfoIemEstbsVO> etcInfo로 설정
     */
    public void setEtcInfo(List<UserInfoIemEstbsVO> etcInfo) {
        this.etcInfo = etcInfo;
    }

    /**
     * Integer[] stngArtclSns을 반환
     * 
     * @return Integer[] stngArtclSns
     */
    public Integer[] getStngArtclSns() {
        return stngArtclSns;
    }

    /**
     * stngArtclSns을 설정
     * 
     * @param stngArtclSns 을(를) Integer[] stngArtclSns로 설정
     */
    public void setStngArtclSns(Integer[] stngArtclSns) {
        this.stngArtclSns = stngArtclSns;
    }

    /**
     * String mileageUseAt을 반환
     * 
     * @return String mileageUseAt
     */
    public String getMileageUseAt() {
        return mileageUseAt;
    }

    /**
     * mileageUseAt을 설정
     * 
     * @param mileageUseAt 을(를) String mileageUseAt로 설정
     */
    public void setMileageUseAt(String mileageUseAt) {
        this.mileageUseAt = mileageUseAt;
    }

}
