/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.code.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 코드 정보 VO 클레스
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
public class CodeVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 1401997881527420493L;

    /** 로그일련번호 */
    private Integer logSn;

    /** 로그유형 */
    private String logType;

    /** 코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String cdId;

    /** 코드명 */
    @Required
    @RangeLength(min = 2, max = 100)
    private String cdNm;

    /** 최상코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String hghrkCdId;

    /** 상위코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String upCdId;

    /** 상위코드명 */
    private String upCdIdNm;

    /** 다국어코드 */
    private String mtlngCdNm;

    /** 코드설명 */
    @RangeLength(min = 2, max = 4000)
    private String cdExpln;

    /** 정렬순서 */
    @Digits
    private Integer sortSn;

    /** 행정표준코드 */
    @MaxLength(max = 20)
    private String pbadmsStdCdId;

    /** 행정표준코드여부 */
    @Required
    private String pbadmsStdCdYn;

    /** 사용여부 */
    @Required
    private String useYn;

    /** 등록자ID */
    @RangeLength(min = 2, max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 등록일시문자열 */
    private String regDtStr;

    /** 수정자ID */
    @RangeLength(min = 2, max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 수정일시문자열 */
    private String updtDtStr;

    /** 자식 존재여부 */
    private Boolean isFolder;

    /** 검색결과 건수 */
    private Integer totalCnt;

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
     * 정렬순서을 설정
     * 
     * @param sortSn을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
    }

    /**
     * 정렬순서을 반환
     * 
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
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

    /**
     * String regDtStr을 반환
     * 
     * @return String regDtStr
     */
    public String getRegDtStr() {
        return regDtStr;
    }

    /**
     * regDtStr을 설정
     * 
     * @param regDtStr 을(를) String regDtStr로 설정
     */
    public void setRegDtStr(String regDtStr) {
        this.regDtStr = regDtStr;
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

    /**
     * String updtDtStr을 반환
     * 
     * @return String updtDtStr
     */
    public String getUpdtDtStr() {
        return updtDtStr;
    }

    /**
     * updtDtStr을 설정
     * 
     * @param updtDtStr 을(를) String updtDtStr로 설정
     */
    public void setUpdtDtStr(String updtDtStr) {
        this.updtDtStr = updtDtStr;
    }

    /**
     * Boolean isFolder을 반환
     * 
     * @return Boolean isFolder
     */
    public Boolean getIsFolder() {
        return isFolder;
    }

    /**
     * isFolder을 설정
     * 
     * @param isFolder 을(를) Boolean isFolder로 설정
     */
    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    /**
     * Integer totalCnt을 반환
     * 
     * @return Integer totalCnt
     */
    public Integer getTotalCnt() {
        return totalCnt;
    }

    /**
     * totalCnt을 설정
     * 
     * @param totalCnt 을(를) Integer totalCnt로 설정
     */
    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }

}
