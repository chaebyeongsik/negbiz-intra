/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.domain;

import java.util.Date;
import java.util.List;

import zesinc.intra.resrce.file.domain.ResrceFileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 자원 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-14.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class ResrceVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -6331915638546162375L;
    /** 자원일련번호 */
    @Required
    @Digits
    private Integer dataSn;

    /** 자원버전 */
    @Required
    @Digits
    private Integer chgCycl;

    /** 제목 */
    @Required
    @MaxLength(max = 200)
    private String ttl;

    /** 자원유형 */
    @Required
    private String dataTypeNo;

    /** 자원상세유형 */
    @Required
    private String dataDtlTypeNm;

    /** 카테고리일련번호 */
    @Digits
    private Integer ctgrySn;

    /** 카테고리경로 */
    private String ctgryPathNm;

    /** 작성방법 */
    private String wrtTypeNm;

    /** 컨텐츠 */
    private String contsCn;

    /** 자원설명 */
    @MaxLength(max = 4000)
    private String dataExpln;

    /** 자원키워드 */
    @MaxLength(max = 300)
    private String dataSrchNm;

    /** 공개여부 */
    @Required
    private String rlsYn;

    /** 공개일시 */
    private Date rlsDt;

    /** 사용여부 */
    @Required
    private String useYn;

    /** 출처 */
    @MaxLength(max = 100)
    private String srcNm;

    /** 출처설명 */
    @MaxLength(max = 4000)
    private String srcExpln;

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

    /** 첨부파일 목록 */
    private List<ResrceFileVO> fileList;

    /** 파일일련번호 */
    private Integer[] fileSns;

    /**
     * 자원일련번호 설정
     * 
     * @param dataSn을(를) Integer dataSn로 설정
     */
    public void setDataSn(Integer dataSn) {
        this.dataSn = dataSn;
    }

    /**
     * 자원일련번호 반환
     * 
     * @return Integer dataSn
     */
    public Integer getDataSn() {
        return dataSn;
    }

    /**
     * 자원버전 설정
     * 
     * @param chgCycl을(를) Integer chgCycl로 설정
     */
    public void setChgCycl(Integer chgCycl) {
        this.chgCycl = chgCycl;
    }

    /**
     * 자원버전 반환
     * 
     * @return Integer chgCycl
     */
    public Integer getChgCycl() {
        return chgCycl;
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
     * 자원유형 설정
     * 
     * @param dataTypeNo을(를) String dataTypeNo로 설정
     */
    public void setDataTypeNo(String dataTypeNo) {
        this.dataTypeNo = dataTypeNo;
    }

    /**
     * 자원유형 반환
     * 
     * @return String dataTypeNo
     */
    public String getDataTypeNo() {
        return dataTypeNo;
    }

    /**
     * 자원상세유형 설정
     * 
     * @param dataDtlTypeNm을(를) String dataDtlTypeNm로 설정
     */
    public void setDataDtlTypeNm(String dataDtlTypeNm) {
        this.dataDtlTypeNm = dataDtlTypeNm;
    }

    /**
     * 자원상세유형 반환
     * 
     * @return String dataDtlTypeNm
     */
    public String getDataDtlTypeNm() {
        return dataDtlTypeNm;
    }

    /**
     * 카테고리일련번호 반환
     * 
     * @return Integer ctgrySn
     */
    public Integer getCtgrySn() {
        return ctgrySn;
    }

    /**
     * 카테고리일련번호 설정
     * 
     * @param ctgrySn 을(를) Integer ctgrySn로 설정
     */
    public void setCtgrySn(Integer ctgrySn) {
        this.ctgrySn = ctgrySn;
    }

    /**
     * 카테고리경로 반환
     * 
     * @return String ctgryPathNm
     */
    public String getCtgryPathNm() {
        return ctgryPathNm;
    }

    /**
     * 카테고리경로 설정
     * 
     * @param ctgryPathNm 을(를) String ctgryPathNm로 설정
     */
    public void setCtgryPathNm(String ctgryPathNm) {
        this.ctgryPathNm = ctgryPathNm;
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
     * 작성방법 설정
     * 
     * @param wrtTypeNm 을(를) String wrtTypeNm로 설정
     */
    public void setWrtTypeNm(String wrtTypeNm) {
        this.wrtTypeNm = wrtTypeNm;
    }

    /**
     * String contsCn을 반환
     * 
     * @return String contsCn
     */
    public String getContsCn() {
        return contsCn;
    }

    /**
     * contsCn을 설정
     * 
     * @param contsCn 을(를) String contsCn로 설정
     */
    public void setContsCn(String contsCn) {
        this.contsCn = contsCn;
    }

    /**
     * 자원설명 설정
     * 
     * @param dataExpln을(를) String dataExpln로 설정
     */
    public void setDataExpln(String dataExpln) {
        this.dataExpln = dataExpln;
    }

    /**
     * 자원설명 반환
     * 
     * @return String dataExpln
     */
    public String getDataExpln() {
        return dataExpln;
    }

    /**
     * 자원키워드 설정
     * 
     * @param dataSrchNm을(를) String dataSrchNm로 설정
     */
    public void setDataSrchNm(String dataSrchNm) {
        this.dataSrchNm = dataSrchNm;
    }

    /**
     * 자원키워드 반환
     * 
     * @return String dataSrchNm
     */
    public String getDataSrchNm() {
        return dataSrchNm;
    }

    /**
     * 공개여부 설정
     * 
     * @param rlsYn을(를) String rlsYn로 설정
     */
    public void setRlsYn(String rlsYn) {
        this.rlsYn = rlsYn;
    }

    /**
     * 공개여부 반환
     * 
     * @return String rlsYn
     */
    public String getRlsYn() {
        return rlsYn;
    }

    /**
     * 공개일시 설정
     * 
     * @param rlsDt을(를) Date rlsDt로 설정
     */
    public void setRlsDt(Date rlsDt) {
        this.rlsDt = rlsDt;
    }

    /**
     * 공개일시 반환
     * 
     * @return Date rlsDt
     */
    public Date getRlsDt() {
        return rlsDt;
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
     * 출처 설정
     * 
     * @param srcNm을(를) String srcNm로 설정
     */
    public void setSrcNm(String srcNm) {
        this.srcNm = srcNm;
    }

    /**
     * 출처 반환
     * 
     * @return String srcNm
     */
    public String getSrcNm() {
        return srcNm;
    }

    /**
     * 출처설명 설정
     * 
     * @param srcExpln을(를) String srcExpln로 설정
     */
    public void setSrcExpln(String srcExpln) {
        this.srcExpln = srcExpln;
    }

    /**
     * 출처설명 반환
     * 
     * @return String srcExpln
     */
    public String getSrcExpln() {
        return srcExpln;
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
     * 파일목록을 설정
     * 
     * @param 파일목록을 List&lt;ResrceFileVO&gt; fileList로 설정
     */
    public void setFileList(List<ResrceFileVO> fileList) {
        this.fileList = fileList;
    }

    /**
     * 파일 목록을 반환
     * 
     * @return List&lt;ResrceFileVO&gt; fileList
     */
    public List<ResrceFileVO> getFileList() {
        return fileList;
    }

    /**
     * Integer[] fileSns을 반환
     * 
     * @return Integer[] fileSns
     */
    public Integer[] getFileSns() {
        return fileSns;
    }

    /**
     * fileSns을 설정
     * 
     * @param fileSns 을(를) Integer[] fileSns로 설정
     */
    public void setFileSns(Integer[] fileSns) {
        this.fileSns = fileSns;
    }

}
