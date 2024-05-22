/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbstmplat.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Pattern;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 게시판템플릿 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-23.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsTmplatVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 1586700281387504784L;
    /** 템플릿타입 */
    @Required
    private String tmpltTypeCd;

    /** 템플릿아이디 */
    @Required
    @Pattern(regexp = "[a-zA-Z0-9_]+")
    @MaxLength(max = 20)
    private String tmpltId;

    /** 템플릿이름 */
    @Required
    @MaxLength(max = 100)
    private String tmpltNm;

    /** 템플릿소스 */
    @Required
    private String tmpltCn;

    /** 템플릿파일경로 */
    private String tmpltFilePathNm;

    /** 리소스 파일순번 */
    private Integer dataFileSn = -1;

    /** 템플릿스크린샷경로 */
    private String tmpltScrnPathNm;

    /** 첨부파일순번 */
    private Integer fileSn = -1;

    /** 사용여부 */
    private String useYn;

    /** 등록자ID */
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    private Date regDt;

    /** 수정자ID */
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 파일 ID 목록 */
    private String[] fileIds;
    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 목록스킨 적용된 게시판수 */
    private Integer listApplcBbsCo = 0;
    /** 읽기스킨 적용된 게시판수 */
    private Integer viewApplcBbsCo = 0;
    private Integer redngApplcBbsCo = 0;
    /** 쓰기스킨 적용된 게시판수 */
    private Integer formApplcBbsCo = 0;

    /**
     * 템플릿타입 설정
     *
     * @param tmpltTypeCd을(를) String tmpltTypeCd로 설정
     */
    public void setTmpltTypeCd(String tmpltTypeCd) {
        this.tmpltTypeCd = tmpltTypeCd;
    }

    /**
     * 템플릿타입 반환
     *
     * @return String tmpltTypeCd
     */
    public String getTmpltTypeCd() {
        return tmpltTypeCd;
    }

    /**
     * 템플릿아이디 설정
     *
     * @param tmpltId을(를) String tmpltId로 설정
     */
    public void setTmpltId(String tmpltId) {
        this.tmpltId = tmpltId;
    }

    /**
     * 템플릿아이디 반환
     *
     * @return String tmpltId
     */
    public String getTmpltId() {
        return tmpltId;
    }

    /**
     * 템플릿이름 설정
     *
     * @param tmpltNm을(를) String tmpltNm로 설정
     */
    public void setTmpltNm(String tmpltNm) {
        this.tmpltNm = tmpltNm;
    }

    /**
     * 템플릿이름 반환
     *
     * @return String tmpltNm
     */
    public String getTmpltNm() {
        return tmpltNm;
    }

    /**
     * 템플릿소스 반환
     *
     * @return String tmpltCn
     */
    public String getTmpltCn() {
        return tmpltCn;
    }

    /**
     * 템플릿소스 설정
     *
     * @param tmpltCn 을(를) String tmpltCn로 설정
     */
    public void setTmpltCn(String tmpltCn) {
        this.tmpltCn = tmpltCn;
    }

    /**
     * 템플릿파일경로 설정
     *
     * @param tmpltFilePathNm을(를) String tmpltFilePathNm로 설정
     */
    public void setTmpltFilePathNm(String tmpltFilePathNm) {
        this.tmpltFilePathNm = tmpltFilePathNm;
    }

    /**
     * 템플릿파일경로 반환
     *
     * @return String tmpltFilePathNm
     */
    public String getTmpltFilePathNm() {
        return tmpltFilePathNm;
    }

    /**
     * 리소스 파일순번 반환
     *
     * @return Integer dataFileSn
     */
    public Integer getDataFileSn() {
        return dataFileSn;
    }

    /**
     * 리소스 파일순번 설정
     *
     * @param dataFileSn 을(를) Integer dataFileSn로 설정
     */
    public void setDataFileSn(Integer dataFileSn) {
        this.dataFileSn = dataFileSn;
    }

    /**
     * 템플릿스크린샷경로 설정
     *
     * @param tmpltScrnPathNm을(를) String tmpltScrnPathNm로 설정
     */
    public void setTmpltScrnPathNm(String tmpltScrnPathNm) {
        this.tmpltScrnPathNm = tmpltScrnPathNm;
    }

    /**
     * 템플릿스크린샷경로 반환
     *
     * @return String tmpltScrnPathNm
     */
    public String getTmpltScrnPathNm() {
        return tmpltScrnPathNm;
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
     * 파일순번 설정
     *
     * @param fileSn 을(를) Integer fileSn로 설정
     */
    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
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
     * 사용여부 설정
     *
     * @param useYn 을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
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
     * 파일 ID 목록 반환
     * 
     * @return String[] fileIds
     */
    public String[] getFileIds() {
        return fileIds;
    }

    /**
     * 파일 ID 목록 설정
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
     * Integer listApplcBbsCo을 반환
     *
     * @return Integer listApplcBbsCo
     */
    public Integer getListApplcBbsCo() {
        return listApplcBbsCo;
    }

    /**
     * listApplcBbsCo을 설정
     *
     * @param listApplcBbsCo 을(를) Integer listApplcBbsCo로 설정
     */
    public void setListApplcBbsCo(Integer listApplcBbsCo) {
        this.listApplcBbsCo = listApplcBbsCo;
    }

    /**
     * Integer viewApplcBbsCo을 반환
     *
     * @return Integer viewApplcBbsCo
     */
    public Integer getViewApplcBbsCo() {
        return viewApplcBbsCo;
    }

    /**
     * viewApplcBbsCo을 설정
     *
     * @param viewApplcBbsCo 을(를) Integer viewApplcBbsCo로 설정
     */
    public void setViewApplcBbsCo(Integer viewApplcBbsCo) {
        this.viewApplcBbsCo = viewApplcBbsCo;
    }

    /**
     * Integer redngApplcBbsCo을 반환
     * 
     * @return Integer redngApplcBbsCo
     */
    public Integer getRedngApplcBbsCo() {
        return redngApplcBbsCo;
    }

    /**
     * redngApplcBbsCo을 설정
     * 
     * @param redngApplcBbsCo 을(를) Integer redngApplcBbsCo로 설정
     */
    public void setRedngApplcBbsCo(Integer redngApplcBbsCo) {
        this.redngApplcBbsCo = redngApplcBbsCo;
    }

    /**
     * Integer formApplcBbsCo을 반환
     *
     * @return Integer formApplcBbsCo
     */
    public Integer getFormApplcBbsCo() {
        return formApplcBbsCo;
    }

    /**
     * formApplcBbsCo을 설정
     *
     * @param formApplcBbsCo 을(를) Integer formApplcBbsCo로 설정
     */
    public void setFormApplcBbsCo(Integer formApplcBbsCo) {
        this.formApplcBbsCo = formApplcBbsCo;
    }

}
