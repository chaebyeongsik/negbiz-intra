/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.samples.crud.domain;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.vo.PageVO;

/**
 * CRUD 셈플 답글 VO 객체
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 1. 11.    방기배   최초작성
 * </pre>
 * @see
 */
public class CrudReplyVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -3949586938128266700L;
    /** 순번 */
    private Integer regSn;
    /** 답변번호 */
    private Integer answerNo;
    /** 답변내용 */
    private String ansDtlCn;
    /** 답변이력 */
    private String answerHist;
    /** 답변자ID */
    private String answrrId;
    /** 답변자명 */
    private String answrrNm;
    /** 답변일시 */
    private String ansDt;
    /** IP주소 */
    private String ipAddr;
    /** 비밀번호 */
    private String pswd;
    /** 등록자명 */
    private String rgtrNm;
    /** 등록자ID */
    private String rgtrId;
    /** 등록일시 */
    private String regDt;
    /** 수정자명 */
    private String updusrNm;
    /** 수정자ID */
    private String mdfrId;
    /** 수정일시 */
    private String updtDt;
    /** 파일순번 */
    private Integer fileSn;

    /** 파일 순번 목록 (수정시 삭제된 파일 정보) */
    private Integer[] fileSns;
    /** 파일 ID 목록 */
    private String[] fileIds;
    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /**
     * Integer regSn을 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * regSn을 설정
     * 
     * @param regSn 을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * Integer answerNo을 반환
     * 
     * @return Integer answerNo
     */
    public Integer getAnswerNo() {
        return answerNo;
    }

    /**
     * answerNo을 설정
     * 
     * @param answerNo 을(를) Integer answerNo로 설정
     */
    public void setAnswerNo(Integer answerNo) {
        this.answerNo = answerNo;
    }

    /**
     * String ansDtlCn을 반환
     * 
     * @return String ansDtlCn
     */
    public String getAnsDtlCn() {
        return ansDtlCn;
    }

    /**
     * ansDtlCn을 설정
     * 
     * @param ansDtlCn 을(를) String ansDtlCn로 설정
     */
    public void setAnsDtlCn(String ansDtlCn) {
        this.ansDtlCn = ansDtlCn;
    }

    /**
     * String answerHist을 반환
     * 
     * @return String answerHist
     */
    public String getAnswerHist() {
        return answerHist;
    }

    /**
     * answerHist을 설정
     * 
     * @param answerHist 을(를) String answerHist로 설정
     */
    public void setAnswerHist(String answerHist) {
        this.answerHist = answerHist;
    }

    /**
     * String answrrId을 반환
     * 
     * @return String answrrId
     */
    public String getAnswrrId() {
        return answrrId;
    }

    /**
     * answrrId을 설정
     * 
     * @param answrrId 을(를) String answrrId로 설정
     */
    public void setAnswrrId(String answrrId) {
        this.answrrId = answrrId;
    }

    /**
     * String answrrNm을 반환
     * 
     * @return String answrrNm
     */
    public String getAnswrrNm() {
        return answrrNm;
    }

    /**
     * answrrNm을 설정
     * 
     * @param answrrNm 을(를) String answrrNm로 설정
     */
    public void setAnswrrNm(String answrrNm) {
        this.answrrNm = answrrNm;
    }

    /**
     * String ansDt을 반환
     * 
     * @return String ansDt
     */
    public String getAnsDt() {
        return ansDt;
    }

    /**
     * ansDt을 설정
     * 
     * @param ansDt 을(를) String ansDt로 설정
     */
    public void setAnsDt(String ansDt) {
        this.ansDt = ansDt;
    }

    /**
     * String ipAddr을 반환
     * 
     * @return String ipAddr
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * ipAddr을 설정
     * 
     * @param ipAddr 을(를) String ipAddr로 설정
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * String pswd을 반환
     * 
     * @return String pswd
     */
    public String getPswd() {
        return pswd;
    }

    /**
     * pswd을 설정
     * 
     * @param pswd 을(를) String pswd로 설정
     */
    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    /**
     * String rgtrNm을 반환
     * 
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * rgtrNm을 설정
     * 
     * @param rgtrNm 을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * String rgtrId을 반환
     * 
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * rgtrId을 설정
     * 
     * @param rgtrId 을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
    }

    /**
     * String regDt을 반환
     * 
     * @return String regDt
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     * 
     * @param regDt 을(를) String regDt로 설정
     */
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    /**
     * String updusrNm을 반환
     * 
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
    }

    /**
     * updusrNm을 설정
     * 
     * @param updusrNm 을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * String mdfrId을 반환
     * 
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * mdfrId을 설정
     * 
     * @param mdfrId 을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * String updtDt을 반환
     * 
     * @return String updtDt
     */
    public String getUpdtDt() {
        return updtDt;
    }

    /**
     * updtDt을 설정
     * 
     * @param updtDt 을(를) String updtDt로 설정
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
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

}
