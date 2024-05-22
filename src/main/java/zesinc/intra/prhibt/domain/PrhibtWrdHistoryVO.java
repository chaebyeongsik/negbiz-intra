/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.prhibt.domain;

import java.util.Date;

import zesinc.web.vo.PageVO;

/**
 * 금지단어이력 정보 VO 클레스
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
public class PrhibtWrdHistoryVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -8027412859909303928L;

    /** 로그일련번호 */
    private Integer logSn;

    /** 로그일시 */
    private Date logDt;

    /** 로그유형 */
    private String logType;

    /** 금지단어코드 */
    private String phbwdCdId;

    /** 제목 */
    private String ttl;

    /** 금지단어내용 */
    private String phbwdCn;

    /** 사용여부 */
    private String useYn;

    /** 수정자ID */
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

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
     * 로그일시 설정
     * 
     * @param logDt을(를) Date logDt로 설정
     */
    public void setLogDt(Date logDt) {
        this.logDt = logDt;
    }

    /**
     * 로그일시 반환
     * 
     * @return Date logDt
     */
    public Date getLogDt() {
        return logDt;
    }

    /**
     * 로그유형 설정
     * 
     * @param logType을(를) String logType로 설정
     */
    public void setLogType(String logType) {
        this.logType = logType;
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

}
