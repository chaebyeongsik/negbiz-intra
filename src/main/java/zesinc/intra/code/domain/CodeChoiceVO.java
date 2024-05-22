/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 코드선택 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class CodeChoiceVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 5025559016669470383L;

    /** 로그일련번호 */
    private Integer logSn;

    /** 로그유형 */
    private String logType;

    /** 최상코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String hghrkCdId;

    /** 코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String cdId;

    /** 코드선택 */
    @Required
    @AlphaNumeric
    @RangeLength(min = 2, max = 20)
    private String cdChcId;

    /** 상위코드(lwrkCdIds 코드의 상위) */
    private String[] upCdIds;

    /** 하위코드 목록 */
    private String[] lwrkCdIds;

    /** 이전선택상태인코드 */
    private String[] prvLwrkCdIds;

    /** 이전선택상태인부모코드 */
    private String[] prvUpCdIds;

    /** 하위코드 */
    @RangeLength(min = 2, max = 20)
    private String lwrkCdId;

    /** 하위코드명 */
    private String lwrkCdIdNm;

    /** 등록자ID */
    @RangeLength(min = 2, max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 수정자ID */
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

    /**
     * 로그일련번호 반환
     * 
     * @return Integer logSn
     */
    public Integer getLogSn() {
        return logSn;
    }

    /**
     * 로그일련번호 설정
     * 
     * @param logSn 을(를) Integer logSn로 설정
     */
    public void setLogSn(Integer logSn) {
        this.logSn = logSn;
    }

    /**
     * String logType을 반환
     * 
     * @return String logType
     */
    public String getLogType() {
        return logType;
    }

    /**
     * logType을 설정
     * 
     * @param logType 을(를) String logType로 설정
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * String hghrkCdId을 반환
     * 
     * @return String hghrkCdId
     */
    public String getHghrkCdId() {
        return hghrkCdId;
    }

    /**
     * hghrkCdId을 설정
     * 
     * @param hghrkCdId 을(를) String hghrkCdId로 설정
     */
    public void setHghrkCdId(String hghrkCdId) {
        this.hghrkCdId = hghrkCdId;
    }

    /**
     * 코드을 설정
     * 
     * @param code을(를) String code로 설정
     */
    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

    /**
     * 코드을 반환
     * 
     * @return String cdId
     */
    public String getCdId() {
        return cdId;
    }

    /**
     * 코드선택을 설정
     * 
     * @param cdChcId을(를) String cdChcId로 설정
     */
    public void setCdChcId(String cdChcId) {
        this.cdChcId = cdChcId;
    }

    /**
     * 코드선택을 반환
     * 
     * @return String cdChcId
     */
    public String getCdChcId() {
        return cdChcId;
    }

    /**
     * String[] upCdIds을 반환
     * 
     * @return String[] upCdIds
     */
    public String[] getUpCdIds() {
        return upCdIds;
    }

    /**
     * upCdIds을 설정
     * 
     * @param upCdIds 을(를) String[] upCdIds로 설정
     */
    public void setUpCdIds(String[] upCdIds) {
        this.upCdIds = upCdIds;
    }

    /**
     * String[] lwrkCdIds을 반환
     * 
     * @return String[] lwrkCdIds
     */
    public String[] getLwrkCdIds() {
        return lwrkCdIds;
    }

    /**
     * lwrkCdIds을 설정
     * 
     * @param lwrkCdIds 을(를) String[] lwrkCdIds로 설정
     */
    public void setLwrkCdIds(String[] lwrkCdIds) {
        this.lwrkCdIds = lwrkCdIds;
    }

    /**
     * String[] prvLwrkCdIds을 반환
     * 
     * @return String[] prvLwrkCdIds
     */
    public String[] getPrvLwrkCdIds() {
        return prvLwrkCdIds;
    }

    /**
     * prvLwrkCdIds을 설정
     * 
     * @param prvLwrkCdIds 을(를) String[] prvLwrkCdIds로 설정
     */
    public void setPrvLwrkCdIds(String[] prvLwrkCdIds) {
        this.prvLwrkCdIds = prvLwrkCdIds;
    }

    /**
     * String[] prvUpCdIds을 반환
     * 
     * @return String[] prvUpCdIds
     */
    public String[] getPrvUpCdIds() {
        return prvUpCdIds;
    }

    /**
     * prvUpCdIds을 설정
     * 
     * @param prvUpCdIds 을(를) String[] prvUpCdIds로 설정
     */
    public void setPrvUpCdIds(String[] prvUpCdIds) {
        this.prvUpCdIds = prvUpCdIds;
    }

    /**
     * 하위코드 반환
     * 
     * @return String lwrkCdId
     */
    public String getLwrkCdId() {
        return lwrkCdId;
    }

    /**
     * 하위코드 설정
     * 
     * @param lwrkCdId 을(를) String lwrkCdId로 설정
     */
    public void setLwrkCdId(String lwrkCdId) {
        this.lwrkCdId = lwrkCdId;
    }

    /**
     * 하위코드명 반환
     * 
     * @return String lwrkCdIdNm
     */
    public String getLwrkCdIdNm() {
        return lwrkCdIdNm;
    }

    /**
     * 하위코드명 설정
     * 
     * @param lwrkCdIdNm 을(를) String lwrkCdIdNm로 설정
     */
    public void setLwrkCdIdNm(String lwrkCdIdNm) {
        this.lwrkCdIdNm = lwrkCdIdNm;
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
     * 등록자명을 설정
     * 
     * @param rgtrNm을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * 등록자명을 반환
     * 
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
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
     * 등록일시 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * 등록일시 설정
     * 
     * @param regDt 을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

}
