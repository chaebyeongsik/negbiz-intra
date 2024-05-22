/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.banner.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 배너 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BannerVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 6448678674140870386L;
    /** 순번 */
    @Required
    @Digits
    private Integer regSn;

    /** 제목 */
    @Required
    @MaxLength(max = 256)
    private String ttl;

    /** 시작일 */
    @Required
    @MaxLength(max = 10)
    private String bgngYmd;

    /** 종료일 */
    @Required
    @MaxLength(max = 10)
    private String endYmd;

    /** 사용여부 */
    @Required
    private String useYn;

    /** 링크유형 */
    @Required
    private String linkTypeNm;

    /** 배너유형 */
    @Required
    private String bnnTypeNm;

    /** 배너상세유형 */
    private String bnnDtlTypeNm;

    /** 링크URL */
    @Required
    private String linkUrlAddr;

    /** 이미지대체텍스트 */
    @MaxLength(max = 100)
    private String imgAltrtvNm;

    /** 링크설명 */
    @MaxLength(max = 4000)
    private String linkExpln;

    /** 파일경로 */
    @MaxLength(max = 300)
    private String filePathNm;

    /** 정렬순서 */
    private Integer sortSn;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    private Date regDt;

    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 시작상태 */
    private String statusBegin;

    /** 종료상태 */
    private String statusEnd;

    /** 대기상태 */
    private String statusWait;

    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 정렬순번 */
    private Integer[] regSns;

    /** 도메인코드 목록 */
    @Required
    private Integer[] siteSns;

    /** 배너 도메인 목록 */
    private List<BannerDomnVO> domnList;

    /**
     * 순번 설정
     * 
     * @param regRegSn을(를) Integer regRegSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 순번 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
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
     * 시작일 설정
     * 
     * @param bgngYmd을(를) String bgngYmd로 설정
     */
    public void setBgngYmd(String bgngYmd) {
        this.bgngYmd = bgngYmd;
    }

    /**
     * 시작일 반환
     * 
     * @return String bgngYmd
     */
    public String getBgngYmd() {
        return bgngYmd;
    }

    /**
     * 종료일 설정
     * 
     * @param endYmd을(를) String endYmd로 설정
     */
    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
    }

    /**
     * 종료일 반환
     * 
     * @return String endYmd
     */
    public String getEndYmd() {
        return endYmd;
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
     * 링크유형 설정
     * 
     * @param linkTypeNm을(를) String linkTypeNm로 설정
     */
    public void setLinkTypeNm(String linkTypeNm) {
        this.linkTypeNm = linkTypeNm;
    }

    /**
     * 링크유형 반환
     * 
     * @return String linkTypeNm
     */
    public String getLinkTypeNm() {
        return linkTypeNm;
    }

    /**
     * 배너유형 설정
     * 
     * @param bnnTypeNm을(를) String bnnTypeNm로 설정
     */
    public void setBnnTypeNm(String bnnTypeNm) {
        this.bnnTypeNm = bnnTypeNm;
    }

    /**
     * 배너유형 반환
     * 
     * @return String bnnTypeNm
     */
    public String getBnnTypeNm() {
        return bnnTypeNm;
    }

    /**
     * String bnnDtlTypeNm을 반환
     * 
     * @return String bnnDtlTypeNm
     */
    public String getBnnDtlTypeNm() {
        return bnnDtlTypeNm;
    }

    /**
     * bnnDtlTypeNm을 설정
     * 
     * @param bnnDtlTypeNm 을(를) String bnnDtlTypeNm로 설정
     */
    public void setBnnDtlTypeNm(String bnnDtlTypeNm) {
        this.bnnDtlTypeNm = bnnDtlTypeNm;
    }

    /**
     * 링크URL 설정
     * 
     * @param linkUrlAddr을(를) String linkUrlAddr로 설정
     */
    public void setLinkUrlAddr(String linkUrlAddr) {
        this.linkUrlAddr = linkUrlAddr;
    }

    /**
     * 링크URL 반환
     * 
     * @return String linkUrlAddr
     */
    public String getLinkUrlAddr() {
        return linkUrlAddr;
    }

    /**
     * 이미지대체텍스트 설정
     * 
     * @param imgAltrtvNm을(를) String imgAltrtvNm로 설정
     */
    public void setImgAltrtvNm(String imgAltrtvNm) {
        this.imgAltrtvNm = imgAltrtvNm;
    }

    /**
     * 이미지대체텍스트 반환
     * 
     * @return String imgAltrtvNm
     */
    public String getImgAltrtvNm() {
        return imgAltrtvNm;
    }

    /**
     * 링크설명 설정
     * 
     * @param linkExpln을(를) String linkExpln로 설정
     */
    public void setLinkExpln(String linkExpln) {
        this.linkExpln = linkExpln;
    }

    /**
     * 링크설명 반환
     * 
     * @return String linkExpln
     */
    public String getLinkExpln() {
        return linkExpln;
    }

    /**
     * 파일경로 설정
     * 
     * @param filePathNm을(를) String filePathNm로 설정
     */
    public void setFilePathNm(String filePathNm) {
        this.filePathNm = filePathNm;
    }

    /**
     * 파일경로 반환
     * 
     * @return String filePathNm
     */
    public String getFilePathNm() {
        return filePathNm;
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
     * 정렬순서 설정
     * 
     * @param sortSn 을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
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

    /**
     * String statusBegin을 반환
     * 
     * @return String statusBegin
     */
    public String getStatusBegin() {
        return statusBegin;
    }

    /**
     * statusBegin을 설정
     * 
     * @param statusBegin 을(를) String statusBegin로 설정
     */
    public void setStatusBegin(String statusBegin) {
        this.statusBegin = statusBegin;
    }

    /**
     * String statusEnd을 반환
     * 
     * @return String statusEnd
     */
    public String getStatusEnd() {
        return statusEnd;
    }

    /**
     * statusEnd을 설정
     * 
     * @param statusEnd 을(를) String statusEnd로 설정
     */
    public void setStatusEnd(String statusEnd) {
        this.statusEnd = statusEnd;
    }

    /**
     * String statusWait을 반환
     * 
     * @return String statusWait
     */
    public String getStatusWait() {
        return statusWait;
    }

    /**
     * statusWait을 설정
     * 
     * @param statusWait 을(를) String statusWait로 설정
     */
    public void setStatusWait(String statusWait) {
        this.statusWait = statusWait;
    }

    /**
     * 파일목록을 설정
     * 
     * @param 파일목록을 List&lt;FileVO&gt; fileList로 설정
     */
    public void setFileList(List<FileVO> fileList) {
        this.fileList = fileList;
    }

    /**
     * 파일 목록을 반환
     * 
     * @return List&lt;FileVO&gt; fileList
     */
    public List<FileVO> getFileList() {
        return fileList;
    }

    /**
     * Integer[] regSns을 반환
     * 
     * @return Integer[] regSns
     */
    public Integer[] getRegSns() {
        return regSns;
    }

    /**
     * regSns을 설정
     * 
     * @param regSns 을(를) Integer[] regSns로 설정
     */
    public void setRegSns(Integer[] regSns) {
        this.regSns = regSns;
    }

    /**
     * 배너 도메인 코드목록 반환
     * 
     * @return Integer[] siteSns
     */
    public Integer[] getSiteSns() {
        return siteSns;
    }

    /**
     * 배너 도메인 코드목록 설정
     * 
     * @param siteSns 을(를) Integer[] siteSns로 설정
     */
    public void setSiteSns(Integer[] siteSns) {
        this.siteSns = siteSns;
    }

    /**
     * 배너 도메인목록 반환
     * 
     * @return List<BannerDomnVO> domnList
     */
    public List<BannerDomnVO> getDomnList() {
        return domnList;
    }

    /**
     * 배너 도메인목록 설정
     * 
     * @param domnList 을(를) List<BannerDomnVO> domnList로 설정
     */
    public void setDomnList(List<BannerDomnVO> domnList) {
        this.domnList = domnList;
    }

}
