/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.file.domain;

import java.io.File;
import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 자원파일 정보 VO 클레스
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
public class ResrceFileVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 4961317861582343600L;
    /** 자원일련번호 */
    @Required
    @Digits
    private Integer dataSn;

    /** 자원버전 */
    @Required
    @Digits
    private Integer chgCycl;

    /** 파일일려번호 */
    private Integer fileSn;

    /** 원본파일명 */
    @MaxLength(max = 300)
    private String orgnlFileNm;

    /** 서버파일명 */
    @MaxLength(max = 300)
    private String srvrFileNm;

    /** 파일경로웹 */
    @MaxLength(max = 2000)
    private String fileUrlAddr;

    /** 파일사이즈 */
    @MaxLength(max = 20)
    private String fileSzNm;

    /** 파일바이트사이즈 */
    @Digits
    private long byteFileSz;

    /** 파일유형 */
    @MaxLength(max = 100)
    private String fileTypeNm;

    /** 파일확장자 */
    @MaxLength(max = 5)
    private String fileExtnNm;

    /** 파일설명 */
    @MaxLength(max = 4000)
    private String fileExpln;

    /** 썸네일1경로 */
    @MaxLength(max = 300)
    private String thmbPathNm1;

    /** 썸네일2경로 */
    @MaxLength(max = 300)
    private String thmbPathNm2;

    /** 썸네일3경로 */
    @MaxLength(max = 300)
    private String thmbPathNm3;

    /** 정렬순서 */
    private Integer sortSn;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 업로드된 파일객체 */
    private File file;

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
     * 원본파일명 설정
     * 
     * @param orgnlFileNm을(를) String orgnlFileNm로 설정
     */
    public void setOrgnlFileNm(String orgnlFileNm) {
        this.orgnlFileNm = orgnlFileNm;
    }

    /**
     * 원본파일명 반환
     * 
     * @return String orgnlFileNm
     */
    public String getOrgnlFileNm() {
        return orgnlFileNm;
    }

    /**
     * 서버파일명 설정
     * 
     * @param srvrFileNm을(를) String srvrFileNm로 설정
     */
    public void setSrvrFileNm(String srvrFileNm) {
        this.srvrFileNm = srvrFileNm;
    }

    /**
     * 서버파일명 반환
     * 
     * @return String srvrFileNm
     */
    public String getSrvrFileNm() {
        return srvrFileNm;
    }

    /**
     * 파일경로웹 설정
     * 
     * @param fileUrlAddr을(를) String fileUrlAddr로 설정
     */
    public void setFileUrlAddr(String fileUrlAddr) {
        this.fileUrlAddr = fileUrlAddr;
    }

    /**
     * 파일경로웹 반환
     * 
     * @return String fileUrlAddr
     */
    public String getFileUrlAddr() {
        return fileUrlAddr;
    }

    /**
     * 파일사이즈 설정
     * 
     * @param fileSzNm을(를) String fileSzNm로 설정
     */
    public void setFileSzNm(String fileSzNm) {
        this.fileSzNm = fileSzNm;
    }

    /**
     * 파일사이즈 반환
     * 
     * @return String fileSzNm
     */
    public String getFileSzNm() {
        return fileSzNm;
    }

    /**
     * 파일바이트사이즈 설정
     * 
     * @param byteFileSz을(를) Integer byteFileSz로 설정
     */
    public void setByteFileSz(long byteFileSz) {
        this.byteFileSz = byteFileSz;
    }

    /**
     * 파일바이트사이즈 반환
     * 
     * @return Integer byteFileSz
     */
    public long getByteFileSz() {
        return byteFileSz;
    }

    /**
     * 파일유형 설정
     * 
     * @param fileTypeNm을(를) String fileTypeNm로 설정
     */
    public void setFileTypeNm(String fileTypeNm) {
        this.fileTypeNm = fileTypeNm;
    }

    /**
     * 파일유형 반환
     * 
     * @return String fileTypeNm
     */
    public String getFileTypeNm() {
        return fileTypeNm;
    }

    /**
     * 파일확장자 설정
     * 
     * @param fileExtnNm을(를) String fileExtnNm로 설정
     */
    public void setFileExtnNm(String fileExtnNm) {
        this.fileExtnNm = fileExtnNm;
    }

    /**
     * 파일확장자 반환
     * 
     * @return String fileExtnNm
     */
    public String getFileExtnNm() {
        return fileExtnNm;
    }

    /**
     * 파일설명 설정
     * 
     * @param fileExpln을(를) String fileExpln로 설정
     */
    public void setFileExpln(String fileExpln) {
        this.fileExpln = fileExpln;
    }

    /**
     * 파일설명 반환
     * 
     * @return String fileExpln
     */
    public String getFileExpln() {
        return fileExpln;
    }

    /**
     * 썸네일1경로 설정
     * 
     * @param thmbPathNm1을(를) String thmbPathNm1로 설정
     */
    public void setThmbPathNm1(String thmbPathNm1) {
        this.thmbPathNm1 = thmbPathNm1;
    }

    /**
     * 썸네일1경로 반환
     * 
     * @return String thmbPathNm1
     */
    public String getThmbPathNm1() {
        return thmbPathNm1;
    }

    /**
     * 썸네일2경로 설정
     * 
     * @param thmbPathNm2을(를) String thmbPathNm2로 설정
     */
    public void setThmbPathNm2(String thmbPathNm2) {
        this.thmbPathNm2 = thmbPathNm2;
    }

    /**
     * 썸네일2경로 반환
     * 
     * @return String thmbPathNm2
     */
    public String getThmbPathNm2() {
        return thmbPathNm2;
    }

    /**
     * 썸네일3경로 설정
     * 
     * @param thmbPathNm3을(를) String thmbPathNm3로 설정
     */
    public void setThmbPathNm3(String thmbPathNm3) {
        this.thmbPathNm3 = thmbPathNm3;
    }

    /**
     * 썸네일3경로 반환
     * 
     * @return String thmbPathNm3
     */
    public String getThmbPathNm3() {
        return thmbPathNm3;
    }

    /**
     * Integer sortSn을 반환
     * 
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
    }

    /**
     * sortSn을 설정
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
     * File객체 반환
     * 
     * @return File file
     */
    public File getFile() {
        return file;
    }

    /**
     * File객체 설정
     * 
     * @param file 을(를) File file로 설정
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 기본 썸네일 경로
     * 
     * @return
     */
    public String getThmbPathNm() {

        return this.thmbPathNm1;
    }
}
