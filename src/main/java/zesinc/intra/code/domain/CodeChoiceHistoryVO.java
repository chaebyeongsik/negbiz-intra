/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code.domain;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 코드선택이력 정보 VO 클레스
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
public class CodeChoiceHistoryVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 8706624186341978466L;

    /** 로그일련번호 */
    private Integer logSn;

    /** 로그일시 */
    @Required
    private String logDt;

    /** 로그유형 */
    @Required
    private String logType;

    /** 코드선택 */
    @Required
    @AlphaNumeric
    @RangeLength(min = 2, max = 20)
    private String codeChoise;

    /** 최상코드 */
    @Required
    private String hghrkCdId;

    /** 코드 */
    @RangeLength(min = 2, max = 20)
    private String cdId;

    /** 상위코드 */
    @RangeLength(min = 2, max = 20)
    private String upCdId;

    /** 상위코드명 */
    private String upCdIdNm;

    /** 수정자ID */
    @RangeLength(min = 2, max = 20)	
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private String updtDt;

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
     * 코드선택을 설정
     * 
     * @param codeChoise을(를) String codeChoise로 설정
     */
    public void setCodeChoise(String codeChoise) {
        this.codeChoise = codeChoise;
    }

    /**
     * 코드선택을 반환
     * 
     * @return String codeChoise
     */
    public String getCodeChoise() {
        return codeChoise;
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
     * 수정일시을 설정
     * 
     * @param updtDt을(를) String updtDt로 설정
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * 수정일시을 반환
     * 
     * @return String updtDt
     */
    public String getUpdtDt() {
        return updtDt;
    }

}
