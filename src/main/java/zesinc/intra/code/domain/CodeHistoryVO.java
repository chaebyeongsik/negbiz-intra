/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 코드이력 정보 VO 클레스
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
public class CodeHistoryVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -4830583031173180977L;

    /** 로그일련번호 */
    private Integer logSn;

    /** 로그일시 */
    @Required
    private String logDt;

    /** 로그유형 */
    @Required
    private String logType;

    /** 코드 */
    @RangeLength(min = 2, max = 20)
    private String cdId;

    /** 코드명 */
    @RangeLength(min = 2, max = 100)
    private String cdNm;

    /** 최상코드 */
    @Required
    private String hghrkCdId;

    /** 상위코드 */
    @RangeLength(min = 2, max = 20)
    private String upCdId;

    /** 상위코드명 */
    private String upCdIdNm;

    /** 다국어코드 */
    @RangeLength(min = 2, max = 300)
    private String mtlngCdNm;

    /** 다국어코드명 */
    private String multiLangCodeNm;

    /** 코드설명 */
    @RangeLength(min = 2, max = 4000)
    private String cdExpln;

    /** 행정표준코드 */
    private String pbadmsStdCdId;

    /** 행정표준코드여부 */
    @MaxLength(max = 20)
    private String pbadmsStdCdYn;

    /** 사용여부 */
    private String useYn;

    /** 수정자ID */
    @RangeLength(min = 2, max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

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
     * 로그일시을 설정
     * 
     * @param logDt을(를) String logDt로 설정
     */
    public void setLogDt(String logDt) {
        this.logDt = logDt;
    }

    /**
     * 로그일시을 반환
     * 
     * @return String logDt
     */
    public String getLogDt() {
        return logDt;
    }

    /**
     * 로그유형을 설정
     * 
     * @param logType을(를) String logType로 설정
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 로그유형을 반환
     * 
     * @return String logType
     */
    public String getLogType() {
        return logType;
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
     * 코드명을 설정
     * 
     * @param cdNm을(를) String cdNm로 설정
     */
    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    /**
     * 코드명을 반환
     * 
     * @return String cdNm
     */
    public String getCdNm() {
        return cdNm;
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
     * 상위코드을 설정
     * 
     * @param upCdId을(를) String upCdId로 설정
     */
    public void setUpCdId(String upCdId) {
        this.upCdId = upCdId;
    }

    /**
     * 상위코드을 반환
     * 
     * @return String upCdId
     */
    public String getUpCdId() {
        return upCdId;
    }

    /**
     * 상위코드명을 설정
     * 
     * @param upCdIdNm을(를) String upCdIdNm로 설정
     */
    public void setUpCdIdNm(String upCdIdNm) {
        this.upCdIdNm = upCdIdNm;
    }

    /**
     * 상위코드명을 반환
     * 
     * @return String upCdIdNm
     */
    public String getUpCdIdNm() {
        return upCdIdNm;
    }

    /**
     * 다국어코드을 설정
     * 
     * @param mtlngCdNm을(를) String mtlngCdNm로 설정
     */
    public void setMtlngCdNm(String mtlngCdNm) {
        this.mtlngCdNm = mtlngCdNm;
    }

    /**
     * 다국어코드을 반환
     * 
     * @return String mtlngCdNm
     */
    public String getMtlngCdNm() {
        return mtlngCdNm;
    }

    /**
     * 다국어코드명을 설정
     * 
     * @param multiLangCodeNm을(를) String multiLangCodeNm로 설정
     */
    public void setMultiLangCodeNm(String multiLangCodeNm) {
        this.multiLangCodeNm = multiLangCodeNm;
    }

    /**
     * 다국어코드명을 반환
     * 
     * @return String multiLangCodeNm
     */
    public String getMultiLangCodeNm() {
        return multiLangCodeNm;
    }

    /**
     * 코드설명을 설정
     * 
     * @param cdExpln을(를) String cdExpln로 설정
     */
    public void setCdExpln(String cdExpln) {
        this.cdExpln = cdExpln;
    }

    /**
     * 코드설명을 반환
     * 
     * @return String cdExpln
     */
    public String getCdExpln() {
        return cdExpln;
    }

    /**
     * 행정표준코드 반환
     * 
     * @return String pbadmsStdCdId
     */
    public String getPbadmsStdCdId() {
        return pbadmsStdCdId;
    }

    /**
     * 행정표준코드 설정
     * 
     * @param pbadmsStdCdId 을(를) String pbadmsStdCdId로 설정
     */
    public void setPbadmsStdCdId(String pbadmsStdCdId) {
        this.pbadmsStdCdId = pbadmsStdCdId;
    }

    /**
     * 행정표준코드여부 반환
     * 
     * @return String pbadmsStdCdYn
     */
    public String getPbadmsStdCdYn() {
        return pbadmsStdCdYn;
    }

    /**
     * 행정표준코드여부 설정
     * 
     * @param pbadmsStdCdYn 을(를) String pbadmsStdCdYn로 설정
     */
    public void setPbadmsStdCdYn(String pbadmsStdCdYn) {
        this.pbadmsStdCdYn = pbadmsStdCdYn;
    }

    /**
     * 사용여부을 설정
     * 
     * @param useYn을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 사용여부을 반환
     * 
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * 수정자ID을 설정
     * 
     * @param mdfrId을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * 수정자ID을 반환
     * 
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * 수정자명을 설정
     * 
     * @param updusrNm을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * 수정자명을 반환
     * 
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
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
     * 수정일시 설정
     * 
     * @param updtDt 을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

}
