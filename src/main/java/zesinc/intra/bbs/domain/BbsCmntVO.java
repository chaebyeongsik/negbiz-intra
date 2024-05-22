/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.NotNull;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 게시판커멘트 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-11-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsCmntVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -8776260936746620971L;

    /** 게시판코드 */
    @NotNull
    @Required
    @Digits
    private Integer bbsSn;

    /** 게시판명 */
    private String bbsNm;

    /** 게시글일련번호 */
    @NotNull
    @Required
    @MaxLength(max = 20)
    private String bbsDocNo;

    /** 의견글일련번호 */
    @Required
    @Digits
    private Integer opnnSn;

    /** 의견글참조일련번호 */
    @Digits
    private Integer opnnRfrncSn;

    /** 정렬순서 */
    @Digits
    private Integer sortSn;

    /** 의견글레벨 */
    @Digits
    private Integer opnnGrdSn;

    /** 의견글내용 */
    @Required
    @MaxLength(max = 4000)
    private String opnnCn;

    /** 신고횟수 */
    @Digits
    private Integer dclrCnt;

    /** 추천횟수 */
    @Digits
    private Integer rcmdtnCnt;

    /** 비추천횟수 */
    @Digits
    private Integer dercmCnt;

    /** IP주소 */
    @MaxLength(max = 15)
    private String ipAddr;

    /** 비밀번호 */
    @MaxLength(max = 100)
    private String pswd;

    /** 등록자명 */
    @MaxLength(max = 100)
    private String rgtrNm;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 삭제여부 */
    private String delYn;

    /** 삭제일시 */
    private Date delDt;

    /** 담당자ID */
    @MaxLength(max = 20)
    private String picId;

    /** 담당자삭제여 */
    @Required
    private String picDelYn;

    /** 담당자삭제일시 */
    private Date picDelDt;

    /** 증분반영유형. sttemnt : 신고, recomend : 추천, nonrecom : 비추천 */
    private String type;

    /**
     * 게시판코드 설정
     * 
     * @param bbsSn을(를) Integer bbsSn로 설정
     */
    public void setBbsSn(Integer bbsSn) {
        this.bbsSn = bbsSn;
    }

    /**
     * 게시판코드 반환
     * 
     * @return Integer bbsSn
     */
    public Integer getBbsSn() {
        return bbsSn;
    }

    /**
     * 게시판명 설정
     * 
     * @param bbsNm을(를) String bbsNm로 설정
     */
    public void setBbsNm(String bbsNm) {
        this.bbsNm = bbsNm;
    }

    /**
     * 게시판명 반환
     * 
     * @return String bbsNm
     */
    public String getBbsNm() {
        return bbsNm;
    }

    /**
     * 게시글일련번호 설정
     * 
     * @param bbsDocNo을(를) String bbsDocNo로 설정
     */
    public void setBbsDocNo(String bbsDocNo) {
        this.bbsDocNo = bbsDocNo;
    }

    /**
     * 게시글일련번호 반환
     * 
     * @return String bbsDocNo
     */
    public String getBbsDocNo() {
        return bbsDocNo;
    }

    /**
     * 의견글일련번호 설정
     * 
     * @param opnnSn을(를) Integer opnnSn로 설정
     */
    public void setOpnnSn(Integer opnnSn) {
        this.opnnSn = opnnSn;
    }

    /**
     * 의견글일련번호 반환
     * 
     * @return Integer opnnSn
     */
    public Integer getOpnnSn() {
        return opnnSn;
    }

    /**
     * 의견글참조일련번호 설정
     * 
     * @param opnnRfrncSn을(를) Integer opnnRfrncSn로 설정
     */
    public void setOpnnRfrncSn(Integer opnnRfrncSn) {
        this.opnnRfrncSn = opnnRfrncSn;
    }

    /**
     * 의견글참조일련번호 반환
     * 
     * @return Integer opnnRfrncSn
     */
    public Integer getOpnnRfrncSn() {
        return opnnRfrncSn;
    }

    /**
     * 정렬순서 설정
     * 
     * @param sortSn을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
    }

    /**
     * 정렬순서 반환
     * 
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
    }

    /**
     * 의견글레벨 설정
     * 
     * @param opnnGrdSn을(를) Integer opnnGrdSn로 설정
     */
    public void setOpnnGrdSn(Integer opnnGrdSn) {
        this.opnnGrdSn = opnnGrdSn;
    }

    /**
     * 의견글레벨 반환
     * 
     * @return Integer opnnGrdSn
     */
    public Integer getOpnnGrdSn() {
        return opnnGrdSn;
    }

    /**
     * 의견글내용 설정
     * 
     * @param opnnCn을(를) String opnnCn로 설정
     */
    public void setOpnnCn(String opnnCn) {
        this.opnnCn = opnnCn;
    }

    /**
     * 의견글내용 반환
     * 
     * @return String opnnCn
     */
    public String getOpnnCn() {
        return opnnCn;
    }

    /**
     * 신고횟수 설정
     * 
     * @param dclrCnt을(를) Integer dclrCnt로 설정
     */
    public void setDclrCnt(Integer dclrCnt) {
        this.dclrCnt = dclrCnt;
    }

    /**
     * 신고횟수 반환
     * 
     * @return Integer dclrCnt
     */
    public Integer getDclrCnt() {
        return dclrCnt;
    }

    /**
     * 추천횟수 설정
     * 
     * @param rcmdtnCnt을(를) Integer rcmdtnCnt로 설정
     */
    public void setRcmdtnCnt(Integer rcmdtnCnt) {
        this.rcmdtnCnt = rcmdtnCnt;
    }

    /**
     * 추천횟수 반환
     * 
     * @return Integer rcmdtnCnt
     */
    public Integer getRcmdtnCnt() {
        return rcmdtnCnt;
    }

    /**
     * 비추천횟수 설정
     * 
     * @param dercmCnt을(를) Integer dercmCnt로 설정
     */
    public void setDercmCnt(Integer dercmCnt) {
        this.dercmCnt = dercmCnt;
    }

    /**
     * 비추천횟수 반환
     * 
     * @return Integer dercmCnt
     */
    public Integer getDercmCnt() {
        return dercmCnt;
    }

    /**
     * IP주소 설정
     * 
     * @param ipAddr을(를) String ipAddr로 설정
     */
    @Override
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * IP주소 반환
     * 
     * @return String ipAddr
     */
    @Override
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * 비밀번호 설정
     * 
     * @param pswd을(를) String pswd로 설정
     */
    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    /**
     * 비밀번호 반환
     * 
     * @return String pswd
     */
    public String getPswd() {
        return pswd;
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
     * 삭제여부 설정
     * 
     * @param delYn을(를) String delYn로 설정
     */
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    /**
     * 삭제여부 반환
     * 
     * @return String delYn
     */
    public String getDelYn() {
        return delYn;
    }

    /**
     * 삭제일시 설정
     * 
     * @param delDt을(를) Date delDt로 설정
     */
    public void setDelDt(Date delDt) {
        this.delDt = delDt;
    }

    /**
     * 삭제일시 반환
     * 
     * @return Date delDt
     */
    public Date getDelDt() {
        return delDt;
    }

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
     * 담당자삭제여 설정
     * 
     * @param picDelYn을(를) String picDelYn로 설정
     */
    public void setPicDelYn(String picDelYn) {
        this.picDelYn = picDelYn;
    }

    /**
     * 담당자삭제여 반환
     * 
     * @return String picDelYn
     */
    public String getPicDelYn() {
        return picDelYn;
    }

    /**
     * 담당자삭제일시 설정
     * 
     * @param picDelDt을(를) Date picDelDt로 설정
     */
    public void setPicDelDt(Date picDelDt) {
        this.picDelDt = picDelDt;
    }

    /**
     * 담당자삭제일시 반환
     * 
     * @return Date picDelDt
     */
    public Date getPicDelDt() {
        return picDelDt;
    }

    /**
     * 증분반영유형 반환
     * 
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * 증분반영유형 설정
     * 
     * @param type 을(를) String type로 설정
     */
    public void setType(String type) {
        this.type = type;
    }

}
