/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.intra.schdul.support.SchdulSupport;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Range;
import zesinc.web.validate.annotation.marker.RequireFrom;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 일정 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-12-08.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class SchdulVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -586331836528602185L;

    /** 일정 HGHRK_CD_ID */
    private String hghrkCdId = SchdulSupport.HGHRK_CD_ID;

    /** 일련번호 */
    @Required
    @Digits
    private Integer regSn;

    /** 제목 */
    @Required
    @MaxLength(max = 50)
    private String ttl;

    /** 장문내용 */
    @Required
    private String docContsCn;

    /** 파일순번 */
    @Digits
    private Integer fileSn;

    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 구분코드 */
    @Required
    @MaxLength(max = 20)
    private String seCdId;

    /** 구분명 */
    private String seNm;

    /** 일정구분코드 */
    @MaxLength(max = 20)
    private String schdlSeCdId;

    /** 일정구분명 */
    private String schdulSeNm;

    /** 휴일구분코드 */
    @MaxLength(max = 20)
    private String hldySeCdId;

    /** 휴일구분명 */
    private String restdeSeNm;

    /** 장소코드 */
    @MaxLength(max = 20)
    private String plcCdId;

    /** 장소명 */
    private String placeNm;

    /** 시작일 */
    @Required
    @MaxLength(max = 10)
    private String bgngYmd;

    /** 종료일 */
    @MaxLength(max = 10)
    private String endYmd;

    /** 시작시간 */
    @MaxLength(max = 5)
    private String bgngHr;

    /** 종료시간 */
    @MaxLength(max = 5)
    private String endHr;

    /** 반복여부 */
    @Required
    private String rpttYn;

    /** 반복유형 */
    @RequireFrom(fieldName = "rpttYn", fieldValue = "Y", fieldDesc = "반복")
    private String rpttTypeCd;

    /** 반복종료일 */
    @RequireFrom(fieldName = "rpttYn", fieldValue = "Y", fieldDesc = "반복")
    @MaxLength(max = 10)
    private String rpttEndYmd;

    /** 월요일 */
    @Range(min = 2, max = 2)
    private Integer mndayRsvtNo;

    /** 화요일 */
    @Range(min = 3, max = 3)
    private Integer tsdayRsvtNo;

    /** 수요일 */
    @Range(min = 4, max = 4)
    private Integer wddayRsvtNo;

    /** 목요일 */
    @Range(min = 5, max = 5)
    private Integer trdayRsvtNo;

    /** 금요일 */
    @Range(min = 6, max = 6)
    private Integer frdayRsvtNo;

    /** 토요일 */
    @Range(min = 7, max = 7)
    private Integer stdayRsvtNo;

    /** 일요일 */
    @Range(min = 1, max = 1)
    private Integer sndayRsvtNo;

    /** 색상 */
    @MaxLength(max = 20)
    private String colorNm;

    /** 공개여부 */
    private String rlsYn;

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

    /** 상세테이블과 조인시 마스터 테이블의 시작일 */
    private String confBgngYmd;

    /** 상세테이블과 조인시 마스터 테이블의 종료일 */
    private String confEndYmd;

    /** 파일 ID 목록 */
    private String[] fileIds;

    /**
     * String hghrkCdId을 반환
     * 
     * @return String hghrkCdId
     */
    public String getHghrkCdId() {
        return hghrkCdId;
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
     * Integer fileSn을 반환
     * 
     * @return Integer fileSn
     */
    public Integer getFileSn() {
        return fileSn;
    }

    /**
     * fileSn을 설정
     * 
     * @param fileSn 을(를) Integer fileSn로 설정
     */
    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * List<FileVO> fileList을 반환
     * 
     * @return List<FileVO> fileList
     */
    public List<FileVO> getFileList() {
        return fileList;
    }

    /**
     * fileList을 설정
     * 
     * @param fileList 을(를) List<FileVO> fileList로 설정
     */
    public void setFileList(List<FileVO> fileList) {
        this.fileList = fileList;
    }

    /**
     * 구분코드 설정
     * 
     * @param seCdId을(를) String seCdId로 설정
     */
    public void setSeCdId(String seCdId) {
        this.seCdId = seCdId;
    }

    /**
     * 구분코드 반환
     * 
     * @return String seCdId
     */
    public String getSeCdId() {
        return seCdId;
    }

    /**
     * 구분명 설정
     * 
     * @param seNm을(를) String seNm로 설정
     */
    public void setSeNm(String seNm) {
        this.seNm = seNm;
    }

    /**
     * 구분명 반환
     * 
     * @return String seNm
     */
    public String getSeNm() {
        return seNm;
    }

    /**
     * 일정구분코드 설정
     * 
     * @param schdlSeCdId을(를) String schdlSeCdId로 설정
     */
    public void setSchdlSeCdId(String schdlSeCdId) {
        this.schdlSeCdId = schdlSeCdId;
    }

    /**
     * 일정구분코드 반환
     * 
     * @return String schdlSeCdId
     */
    public String getSchdlSeCdId() {
        return schdlSeCdId;
    }

    /**
     * 일정구분명 설정
     * 
     * @param schdulSeNm을(를) String schdulSeNm로 설정
     */
    public void setSchdulSeNm(String schdulSeNm) {
        this.schdulSeNm = schdulSeNm;
    }

    /**
     * 일정구분명 반환
     * 
     * @return String schdulSeNm
     */
    public String getSchdulSeNm() {
        return schdulSeNm;
    }

    /**
     * 휴일구분코드 설정
     * 
     * @param hldySeCdId을(를) String hldySeCdId로 설정
     */
    public void setHldySeCdId(String hldySeCdId) {
        this.hldySeCdId = hldySeCdId;
    }

    /**
     * 휴일구분코드 반환
     * 
     * @return String hldySeCdId
     */
    public String getHldySeCdId() {
        return hldySeCdId;
    }

    /**
     * 휴일구분명 설정
     * 
     * @param restdeSeNm을(를) String restdeSeNm로 설정
     */
    public void setRestdeSeNm(String restdeSeNm) {
        this.restdeSeNm = restdeSeNm;
    }

    /**
     * 휴일구분명 반환
     * 
     * @return String restdeSeNm
     */
    public String getRestdeSeNm() {
        return restdeSeNm;
    }

    /**
     * 장소코드 설정
     * 
     * @param plcCdId을(를) String plcCdId로 설정
     */
    public void setPlcCdId(String plcCdId) {
        this.plcCdId = plcCdId;
    }

    /**
     * 장소코드 반환
     * 
     * @return String plcCdId
     */
    public String getPlcCdId() {
        return plcCdId;
    }

    /**
     * 장소명 설정
     * 
     * @param placeNm을(를) String placeNm로 설정
     */
    public void setPlaceNm(String placeNm) {
        this.placeNm = placeNm;
    }

    /**
     * 장소명 반환
     * 
     * @return String placeNm
     */
    public String getPlaceNm() {
        return placeNm;
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
     * 시작시간 설정
     * 
     * @param bgngHr을(를) String bgngHr로 설정
     */
    public void setBgngHr(String bgngHr) {
        this.bgngHr = bgngHr;
    }

    /**
     * 시작시간 반환
     * 
     * @return String bgngHr
     */
    public String getBgngHr() {
        return bgngHr;
    }

    /**
     * 종료시간 설정
     * 
     * @param endHr을(를) String endHr로 설정
     */
    public void setEndHr(String endHr) {
        this.endHr = endHr;
    }

    /**
     * 종료시간 반환
     * 
     * @return String endHr
     */
    public String getEndHr() {
        return endHr;
    }

    /**
     * 반복여부 설정
     * 
     * @param rpttYn을(를) String rpttYn로 설정
     */
    public void setRpttYn(String rpttYn) {
        this.rpttYn = rpttYn;
    }

    /**
     * 반복여부 반환
     * 
     * @return String rpttYn
     */
    public String getRpttYn() {
        return rpttYn;
    }

    /**
     * 반복유형 설정
     * 
     * @param rpttTypeCd을(를) String rpttTypeCd로 설정
     */
    public void setRpttTypeCd(String rpttTypeCd) {
        this.rpttTypeCd = rpttTypeCd;
    }

    /**
     * 반복유형 반환
     * 
     * @return String rpttTypeCd
     */
    public String getRpttTypeCd() {
        return rpttTypeCd;
    }

    /**
     * 반복종료일 설정
     * 
     * @param rpttEndYmd을(를) String rpttEndYmd로 설정
     */
    public void setRpttEndYmd(String rpttEndYmd) {
        this.rpttEndYmd = rpttEndYmd;
    }

    /**
     * 반복종료일 반환
     * 
     * @return String rpttEndYmd
     */
    public String getRpttEndYmd() {
        return rpttEndYmd;
    }

    /**
     * 월요일 설정
     * 
     * @param mndayRsvtNo을(를) Integer mndayRsvtNo로 설정
     */
    public void setMndayRsvtNo(Integer mndayRsvtNo) {
        this.mndayRsvtNo = mndayRsvtNo;
    }

    /**
     * 월요일 반환
     * 
     * @return Integer mndayRsvtNo
     */
    public Integer getMndayRsvtNo() {
        return mndayRsvtNo;
    }

    /**
     * 화요일 설정
     * 
     * @param tsdayRsvtNo을(를) Integer tsdayRsvtNo로 설정
     */
    public void setTsdayRsvtNo(Integer tsdayRsvtNo) {
        this.tsdayRsvtNo = tsdayRsvtNo;
    }

    /**
     * 화요일 반환
     * 
     * @return Integer tsdayRsvtNo
     */
    public Integer getTsdayRsvtNo() {
        return tsdayRsvtNo;
    }

    /**
     * 수요일 설정
     * 
     * @param wddayRsvtNo을(를) Integer wddayRsvtNo로 설정
     */
    public void setWddayRsvtNo(Integer wddayRsvtNo) {
        this.wddayRsvtNo = wddayRsvtNo;
    }

    /**
     * 수요일 반환
     * 
     * @return Integer wddayRsvtNo
     */
    public Integer getWddayRsvtNo() {
        return wddayRsvtNo;
    }

    /**
     * 목요일 설정
     * 
     * @param trdayRsvtNo을(를) Integer trdayRsvtNo로 설정
     */
    public void setTrdayRsvtNo(Integer trdayRsvtNo) {
        this.trdayRsvtNo = trdayRsvtNo;
    }

    /**
     * 목요일 반환
     * 
     * @return Integer trdayRsvtNo
     */
    public Integer getTrdayRsvtNo() {
        return trdayRsvtNo;
    }

    /**
     * 금요일 설정
     * 
     * @param frdayRsvtNo을(를) Integer frdayRsvtNo로 설정
     */
    public void setFrdayRsvtNo(Integer frdayRsvtNo) {
        this.frdayRsvtNo = frdayRsvtNo;
    }

    /**
     * 금요일 반환
     * 
     * @return Integer frdayRsvtNo
     */
    public Integer getFrdayRsvtNo() {
        return frdayRsvtNo;
    }

    /**
     * 토요일 설정
     * 
     * @param stdayRsvtNo을(를) Integer stdayRsvtNo로 설정
     */
    public void setStdayRsvtNo(Integer stdayRsvtNo) {
        this.stdayRsvtNo = stdayRsvtNo;
    }

    /**
     * 토요일 반환
     * 
     * @return Integer stdayRsvtNo
     */
    public Integer getStdayRsvtNo() {
        return stdayRsvtNo;
    }

    /**
     * 일요일 설정
     * 
     * @param sndayRsvtNo을(를) Integer sndayRsvtNo로 설정
     */
    public void setSndayRsvtNo(Integer sndayRsvtNo) {
        this.sndayRsvtNo = sndayRsvtNo;
    }

    /**
     * 일요일 반환
     * 
     * @return Integer sndayRsvtNo
     */
    public Integer getSndayRsvtNo() {
        return sndayRsvtNo;
    }

    /**
     * String colorNm을 반환
     * 
     * @return String colorNm
     */
    public String getColorNm() {
        return colorNm;
    }

    /**
     * colorNm을 설정
     * 
     * @param colorNm 을(를) String colorNm로 설정
     */
    public void setColorNm(String colorNm) {
        this.colorNm = colorNm;
    }

    /**
     * 공개여부 설정s
     * 
     * @param rlsYn을(를) String rlsYn로 설정
     */
    public void setRlsYn(String rlsYn) {
        this.rlsYn = rlsYn;
    }

    /**
     * 공개여부 반환s
     * 
     * @return String rlsYn
     */
    public String getRlsYn() {
        return rlsYn;
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
     * String confBgngYmd을 반환
     * 
     * @return String confBgngYmd
     */
    public String getConfBgngYmd() {
        return confBgngYmd;
    }

    /**
     * confBgngYmd을 설정
     * 
     * @param confBgngYmd 을(를) String confBgngYmd로 설정
     */
    public void setConfBgngYmd(String confBgngYmd) {
        this.confBgngYmd = confBgngYmd;
    }

    /**
     * String confEndYmd을 반환
     * 
     * @return String confEndYmd
     */
    public String getConfEndYmd() {
        return confEndYmd;
    }

    /**
     * confEndYmd을 설정
     * 
     * @param confEndYmd 을(를) String confEndYmd로 설정
     */
    public void setConfEndYmd(String confEndYmd) {
        this.confEndYmd = confEndYmd;
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

}
