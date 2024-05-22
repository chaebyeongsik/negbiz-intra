/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.connect.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 관리자접속이력 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-05-18.    바꿔라   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class ConnectVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 8466293132809251560L;

    /** 담당자ID */
    @Required
    @MaxLength(max = 20)
    private String picId;

    /** 담당자명 */
    private String picNm;

    /** 로그일시 */
    @Required
    private Date logDt;

    /** 로그구분 */
    @MaxLength(max = 20)
    private String logSeNo;

    /** 로그인결과코드 */
    @MaxLength(max = 20)
    private String lgnRsltNo;

    /** 로그인결과명 */
    private String loginResultNm;

    /** 로그인IP */
    @MaxLength(max = 15)
    private String lgnIpAddr;

    /**
     * 담당자ID 설정
     * 
     * @param picId을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * 담당자ID 반환
     * 
     * @return String picId
     */
    public String getPicId() {
        return picId;
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
     * 로그구분 설정
     * 
     * @param logSeNo을(를) String logSeNo로 설정
     */
    public void setLogSeNo(String logSeNo) {
        this.logSeNo = logSeNo;
    }

    /**
     * 로그구분 반환
     * 
     * @return String logSeNo
     */
    public String getLogSeNo() {
        return logSeNo;
    }

    /**
     * 로그인결과코드 설정
     * 
     * @param lgnRsltNo을(를) String lgnRsltNo로 설정
     */
    public void setLgnRsltNo(String lgnRsltNo) {
        this.lgnRsltNo = lgnRsltNo;
    }

    /**
     * 로그인결과코드 반환
     * 
     * @return String lgnRsltNo
     */
    public String getLgnRsltNo() {
        return lgnRsltNo;
    }

    /**
     * 로그인결과명 설정
     * 
     * @param loginResultNm을(를) String loginResultNm로 설정
     */
    public void setLoginResultNm(String loginResultNm) {
        this.loginResultNm = loginResultNm;
    }

    /**
     * 로그인결과명 반환
     * 
     * @return String loginResultNm
     */
    public String getLoginResultNm() {
        return loginResultNm;
    }

    /**
     * 로그인IP 설정
     * 
     * @param lgnIpAddr을(를) String lgnIpAddr로 설정
     */
    public void setLgnIpAddr(String lgnIpAddr) {
        this.lgnIpAddr = lgnIpAddr;
    }

    /**
     * 로그인IP 반환
     * 
     * @return String lgnIpAddr
     */
    public String getLgnIpAddr() {
        return lgnIpAddr;
    }

}
