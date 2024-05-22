/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.prhibt.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 금지단어 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-17.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class PrhibtWrdVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -1174418440001825395L;

    /** 로그일련번호 */
    private Integer logSn;

    /** 로그유형 */
    private String logType;

    /** 금지단어코드 */
    @Required
    @MaxLength(max = 20)
    @AlphaNumeric
    private String phbwdCdId;

    /** 제목 */
    @Required
    @MaxLength(max = 256)
    private String ttl;

    /** 금지단어내용 */
    @Required
    private String phbwdCn;

    /** 사용여부 */
    @Required
    private String useYn;

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
     * 로그유형 반환
     * 
     * @return String logType
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 로그유형 설정
     * 
     * @param logType 을(를) String logType로 설정
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 금지단어코드 반환
     * 
     * @return String phbwdCdId
     */
    public String getPhbwdCdId() {
        return phbwdCdId;
    }

    /**
     * 금지단어코드 설정
     * 
     * @param phbwdCdId 을(를) String phbwdCdId로 설정
     */
    public void setPhbwdCdId(String phbwdCdId) {
        this.phbwdCdId = phbwdCdId;
    }

    /**
     * 제목 설정
     * 
     * @param ttl을(를) String ttl로 설정
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * 제목 반환
     * 
     * @return String ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * 금지단어내용 설정
     * 
     * @param phbwdCn을(를) String phbwdCn로 설정
     */
    public void setPhbwdCn(String phbwdCn) {
        this.phbwdCn = phbwdCn;
    }

    /**
     * 금지단어내용 반환
     * 
     * @return String phbwdCn
     */
    public String getPhbwdCn() {
        return phbwdCn;
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

}
