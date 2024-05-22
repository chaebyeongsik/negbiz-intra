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
import zesinc.web.validate.annotation.marker.MinLength;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.RequireFrom;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * CRUD 셈플 VO 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 1. 27.    방기배   최초작성
 * </pre>
 * @see
 */
public class CrudVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 2002441908273848060L;

    /** 순번 */
    private Integer regSn;
    /** 순번목록 */
    private Integer[] regSns;
    /** 참조일련번호 */
    private Integer rfrncDocNo;
    /** 정렬순서 */
    private Integer sortSn;
    /** 깊이 */
    private Integer dp;
    /** 분류코드 */
    @Required
    private Integer clsfNo;
    /** 공지여부 */
    @RequireFrom(fieldName = "rlsYn", fieldValue = "Y", fieldDesc = "공개여부 공개")
    private String ntcPstYn;
    /** 제목 */
    @Required
    @RangeLength(min = 3, max = 256)
    private String ttl;
    /** 장문내용 */
    @Required
    @MinLength(min = 10)
    private String docContsCn;
    /** 내용이력 */
    private String cnHist;
    /** 파일순번 */
    private Integer fileSn;
    /** 조회수 */
    private Integer inqCnt;
    /** IP주소 */
    private String ipAddr;
    /** 공개여부 */
    @Required
    private String rlsYn;
    /** 답변여부 */
    private String answerAt;
    /** 비밀번호 */
    @Required
    @RangeLength(min = 3, max = 20)
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

    /** 파일 ID 목록 */
    private String[] fileIds;
    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 답글목록 */
    private List<CrudReplyVO> answerList;
    /** 첨부파일 갯수 */
    private Integer fileCnt;
    /** 댓글 갯수 */
    private Integer commCnt;

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
     * Integer rfrncDocNo을 반환
     * 
     * @return Integer rfrncDocNo
     */
    public Integer getRfrncDocNo() {
        return rfrncDocNo;
    }

    /**
     * rfrncDocNo을 설정
     * 
     * @param rfrncDocNo 을(를) Integer rfrncDocNo로 설정
     */
    public void setRfrncDocNo(Integer rfrncDocNo) {
        this.rfrncDocNo = rfrncDocNo;
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
     * Integer dp을 반환
     * 
     * @return Integer dp
     */
    public Integer getDp() {
        return dp;
    }

    /**
     * dp을 설정
     * 
     * @param dp 을(를) Integer dp로 설정
     */
    public void setDp(Integer dp) {
        this.dp = dp;
    }

    /**
     * Integer clsfNo을 반환
     * 
     * @return Integer clsfNo
     */
    public Integer getClsfNo() {
        return clsfNo;
    }

    /**
     * clsfNo을 설정
     * 
     * @param clsfNo 을(를) Integer clsfNo로 설정
     */
    public void setClsfNo(Integer clsfNo) {
        this.clsfNo = clsfNo;
    }

    /**
     * String ntcPstYn을 반환
     * 
     * @return String ntcPstYn
     */
    public String getNtcPstYn() {
        return ntcPstYn;
    }

    /**
     * ntcPstYn을 설정
     * 
     * @param ntcPstYn 을(를) String ntcPstYn로 설정
     */
    public void setNtcPstYn(String ntcPstYn) {
        this.ntcPstYn = ntcPstYn;
    }

    /**
     * String ttl을 반환
     * 
     * @return String ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * ttl을 설정
     * 
     * @param ttl 을(를) String ttl로 설정
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * String docContsCn을 반환
     * 
     * @return String docContsCn
     */
    public String getDocContsCn() {
        return docContsCn;
    }

    /**
     * docContsCn을 설정
     * 
     * @param docContsCn 을(를) String docContsCn로 설정
     */
    public void setDocContsCn(String docContsCn) {
        this.docContsCn = docContsCn;
    }

    /**
     * String cnHist을 반환
     * 
     * @return String cnHist
     */
    public String getCnHist() {
        return cnHist;
    }

    /**
     * cnHist을 설정
     * 
     * @param cnHist 을(를) String cnHist로 설정
     */
    public void setCnHist(String cnHist) {
        this.cnHist = cnHist;
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
     * Integer inqCnt을 반환
     * 
     * @return Integer inqCnt
     */
    public Integer getInqCnt() {
        return inqCnt;
    }

    /**
     * inqCnt을 설정
     * 
     * @param inqCnt 을(를) Integer inqCnt로 설정
     */
    public void setInqCnt(Integer inqCnt) {
        this.inqCnt = inqCnt;
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
     * String rlsYn을 반환
     * 
     * @return String rlsYn
     */
    public String getRlsYn() {
        return rlsYn;
    }

    /**
     * rlsYn을 설정
     * 
     * @param rlsYn 을(를) String rlsYn로 설정
     */
    public void setRlsYn(String rlsYn) {
        this.rlsYn = rlsYn;
    }

    /**
     * String answerAt을 반환
     * 
     * @return String answerAt
     */
    public String getAnswerAt() {
        return answerAt;
    }

    /**
     * answerAt을 설정
     * 
     * @param answerAt 을(를) String answerAt로 설정
     */
    public void setAnswerAt(String answerAt) {
        this.answerAt = answerAt;
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
     * List<CrudReplyVO> answerList을 반환
     * 
     * @return List<CrudReplyVO> answerList
     */
    public List<CrudReplyVO> getAnswerList() {
        return answerList;
    }

    /**
     * answerList을 설정
     * 
     * @param answerList 을(를) List<CrudReplyVO> answerList로 설정
     */
    public void setAnswerList(List<CrudReplyVO> answerList) {
        this.answerList = answerList;
    }

    /**
     * Integer fileCnt을 반환
     * 
     * @return Integer fileCnt
     */
    public Integer getFileCnt() {
        return fileCnt;
    }

    /**
     * fileCnt을 설정
     * 
     * @param fileCnt 을(를) Integer fileCnt로 설정
     */
    public void setFileCnt(Integer fileCnt) {
        this.fileCnt = fileCnt;
    }

    /**
     * Integer commCnt을 반환
     * 
     * @return Integer commCnt
     */
    public Integer getCommCnt() {
        return commCnt;
    }

    /**
     * commCnt을 설정
     * 
     * @param commCnt 을(를) Integer commCnt로 설정
     */
    public void setCommCnt(Integer commCnt) {
        this.commCnt = commCnt;
    }

}
