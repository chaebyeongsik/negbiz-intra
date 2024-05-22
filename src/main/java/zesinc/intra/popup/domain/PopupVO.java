/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.popup.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 팝업 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-20.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class PopupVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -7967117692584470980L;

    /** 쿠키명칭 접두사 */
    private String cookiePrefix = "POPUP_";

    /** 일련번호 */
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

    /** 가로사이즈 */
    @Required
    @Digits
    private Integer wdthSz;

    /** 세로사이즈 */
    @Required
    @Digits
    private Integer vrtcSz;

    /** 상단위치 */
    @Required
    @Digits
    private Integer yAxis;

    /** 좌측위치 */
    @Required
    @Digits
    private Integer xAxis;

    /** 스크롤유무 */
    @Required
    private String scrollYn;

    /** 리사이징유무 */
    @Required
    private String popupSzChgYn;

    /** 팝업유형 */
    @Required
    private String popupTypeNo;

    /** 팝업주기 */
    @Required
    private String popupRpttSeNo;

    /** 작성방법 */
    @Required
    private String wrtTypeNm;

    /** 장문내용 */
    @Required
    private String docContsCn;

    /** 정렬순서 */
    @Digits
    private Integer sortSn;

    /** 파일경로 */
    @MaxLength(max = 300)
    private String filePathNm;

    /** 파일순번 */
    @Digits
    private Integer fileSn;

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

    /** 시작상태 */
    private String statusBegin;

    /** 종료상태 */
    private String statusEnd;

    /** 대기상태 */
    private String statusWait;

    /** 파일 ID 목록 */
    private String[] fileIds;

    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 도메인코드 목록 */
    @Required
    private Integer[] siteSns;

    /** 팝업 도메인 목록 */
    private List<PopupDomnVO> domnList;

    /**
     * String cookiePrefix을 반환
     * 
     * @return String cookiePrefix
     */
    public String getCookiePrefix() {
        return cookiePrefix;
    }

    /**
     * 일련번호 설정
     * 
     * @param regSn을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 일련번호 반환
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
     * 가로사이즈 설정
     * 
     * @param wdthSz을(를) Integer wdthSz로 설정
     */
    public void setWdthSz(Integer wdthSz) {
        this.wdthSz = wdthSz;
    }

    /**
     * 가로사이즈 반환
     * 
     * @return Integer wdthSz
     */
    public Integer getWdthSz() {
        return wdthSz;
    }

    /**
     * 세로사이즈 설정
     * 
     * @param vrtcSz을(를) Integer vrtcSz로 설정
     */
    public void setVrtcSz(Integer vrtcSz) {
        this.vrtcSz = vrtcSz;
    }

    /**
     * 세로사이즈 반환
     * 
     * @return Integer vrtcSz
     */
    public Integer getVrtcSz() {
        return vrtcSz;
    }

    /**
     * 상단위치 설정
     * 
     * @param yAxis을(를) Integer yAxis로 설정
     */
    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * 상단위치 반환
     * 
     * @return Integer yAxis
     */
    public Integer getyAxis() {
        return yAxis;
    }

    /**
     * 좌측위치 설정
     * 
     * @param xAxis을(를) Integer xAxis로 설정
     */
    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * 좌측위치 반환
     * 
     * @return Integer xAxis
     */
    public Integer getxAxis() {
        return xAxis;
    }

    /**
     * 스크롤유무 설정
     * 
     * @param scrollYn을(를) String scrollYn로 설정
     */
    public void setScrollYn(String scrollYn) {
        this.scrollYn = scrollYn;
    }

    /**
     * 스크롤유무 반환
     * 
     * @return String scrollYn
     */
    public String getScrollYn() {
        return scrollYn;
    }

    /**
     * 리사이징유무 설정
     * 
     * @param popupSzChgYn을(를) String popupSzChgYn로 설정
     */
    public void setPopupSzChgYn(String popupSzChgYn) {
        this.popupSzChgYn = popupSzChgYn;
    }

    /**
     * 리사이징유무 반환
     * 
     * @return String popupSzChgYn
     */
    public String getPopupSzChgYn() {
        return popupSzChgYn;
    }

    /**
     * 팝업유형 반환
     * 
     * @return String popupTypeNo
     */
    public String getPopupTypeNo() {
        return popupTypeNo;
    }

    /**
     * 팝업유형 설정
     * 
     * @param popupTypeNo 을(를) String popupTypeNo로 설정
     */
    public void setPopupTypeNo(String popupTypeNo) {
        this.popupTypeNo = popupTypeNo;
    }

    /**
     * 팝업주기 설정
     * 
     * @param popupRpttSeNo을(를) String popupRpttSeNo로 설정
     */
    public void setPopupRpttSeNo(String popupRpttSeNo) {
        this.popupRpttSeNo = popupRpttSeNo;
    }

    /**
     * 팝업주기 반환
     * 
     * @return String popupRpttSeNo
     */
    public String getPopupRpttSeNo() {
        return popupRpttSeNo;
    }

    /**
     * 작성방법 설정
     * 
     * @param wrtTypeNm을(를) String wrtTypeNm로 설정
     */
    public void setWrtTypeNm(String wrtTypeNm) {
        this.wrtTypeNm = wrtTypeNm;
    }

    /**
     * 작성방법 반환
     * 
     * @return String wrtTypeNm
     */
    public String getWrtTypeNm() {
        return wrtTypeNm;
    }

    /**
     * 장문내용 설정
     * 
     * @param docContsCn을(를) String docContsCn로 설정
     */
    public void setDocContsCn(String docContsCn) {
        this.docContsCn = docContsCn;
    }

    /**
     * 장문내용 반환
     * 
     * @return String docContsCn
     */
    public String getDocContsCn() {
        return docContsCn;
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
     * 파일순번 설정
     * 
     * @param fileSn을(를) Integer fileSn로 설정
     */
    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * 파일순번 반환
     * 
     * @return Integer fileSn
     */
    public Integer getFileSn() {
        return fileSn;
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
     * String[] fileIds을 반환
     * 
     * @return String[] fileIds
     */
    public String[] getFileIds() {
        return fileIds;
    }

    /**
     * fileIds을 설정
     * 
     * @param fileIds 을(를) String[] fileIds로 설정
     */
    public void setFileIds(String[] fileIds) {
        this.fileIds = fileIds;
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
     * Integer[] siteSns을 반환
     * 
     * @return Integer[] siteSns
     */
    public Integer[] getSiteSns() {
        return siteSns;
    }

    /**
     * siteSns을 설정
     * 
     * @param siteSns 을(를) Integer[] siteSns로 설정
     */
    public void setSiteSns(Integer[] siteSns) {
        this.siteSns = siteSns;
    }

    /**
     * List&lt;PopupDomnVO&gt; domnList을 반환
     * 
     * @return List<PopupDomnVO&gt; domnList
     */
    public List<PopupDomnVO> getDomnList() {
        return domnList;
    }

    /**
     * domnList을 설정
     * 
     * @param domnList 을(를) List&lt;PopupDomnVO&gt; domnList로 설정
     */
    public void setDomnList(List<PopupDomnVO> domnList) {
        this.domnList = domnList;
    }

}
