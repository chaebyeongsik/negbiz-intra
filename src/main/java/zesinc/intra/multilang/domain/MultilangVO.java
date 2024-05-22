/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.multilang.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 다국어 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-13.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class MultilangVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -3392646848999952875L;
    /** 다국어코드 */
    @Required
    @RangeLength(min = 2, max = 300)
    @AlphaNumeric
    private String mtlngCdNm;

    /** 상위다국어코드 */
    @Required
    @RangeLength(min = 2, max = 300)
    private String upMtlngCdNm;

    /** 상위다국어코드설명 */
    private String upMtlngCdNmDc;

    /** 다국어키 */
    @Required
    @RangeLength(min = 2, max = 50)
    @AlphaNumeric
    private String mtlngUnqCdNm;

    /** 다국어코드설명 */
    @RangeLength(min = 2, max = 4000)
    private String mtlngCdExpln;

    /** 사용여부 */
    private String useYn;

    /** 등록자ID */
    @RangeLength(min = 2, max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 수정자ID */
    @RangeLength(min = 2, max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 메시지 목록 */
    private String[] multiLangMsgCns;
    /** 언어코드 목록 */
    private String[] langCdIds;

    /** 자식 존재여부 */
    private Boolean isFolder;

    /** 다국어메시지목록 */
    private List<MultilangGrpVO> messageList;

    /**
     * 다국어코드을 설정
     *
     * @param mtlngCdNm을(를) String mtlngCdNm로 설정
     */
    public void setMtlngCdNm(String mtlngCdNm) {
        this.mtlngCdNm = mtlngCdNm;
    }

    /**
     * 다국어코드을 반환
     *
     * @return String mtlngCdNm
     */
    public String getMtlngCdNm() {
        return mtlngCdNm;
    }

    /**
     * 상위다국어코드을 설정
     *
     * @param upMtlngCdNm을(를) String upMtlngCdNm로 설정
     */
    public void setUpMtlngCdNm(String upMtlngCdNm) {
        this.upMtlngCdNm = upMtlngCdNm;
    }

    /**
     * 상위다국어코드을 반환
     *
     * @return String upMtlngCdNm
     */
    public String getUpMtlngCdNm() {
        return upMtlngCdNm;
    }

    /**
     * String upMtlngCdNm을 반환
     *
     * @return String upMtlngCdNm
     */
    public String getUpMtlngCdNmDc() {
        return upMtlngCdNmDc;
    }

    /**
     * upMtlngCdNm을 설정
     *
     * @param upMtlngCdNm 을(를) String upMtlngCdNm로 설정
     */
    public void setUpMtlngCdNmDc(String upMtlngCdNmDc) {
        this.upMtlngCdNmDc = upMtlngCdNmDc;
    }

    /**
     * 다국어키을 설정
     *
     * @param mtlngUnqCdNm을(를) String mtlngUnqCdNm로 설정
     */
    public void setMtlngUnqCdNm(String mtlngUnqCdNm) {
        this.mtlngUnqCdNm = mtlngUnqCdNm;
    }

    /**
     * 다국어키을 반환
     *
     * @return String mtlngUnqCdNm
     */
    public String getMtlngUnqCdNm() {
        return mtlngUnqCdNm;
    }

    /**
     * 다국어코드설명을 설정
     *
     * @param mtlngCdExpln을(를) String mtlngCdExpln로 설정
     */
    public void setMtlngCdExpln(String mtlngCdExpln) {
        this.mtlngCdExpln = mtlngCdExpln;
    }

    /**
     * 다국어코드설명을 반환
     *
     * @return String mtlngCdExpln
     */
    public String getMtlngCdExpln() {
        return mtlngCdExpln;
    }

    /**
     * 사용여부을 설정
     *
     * @param useYn을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 사용여부을 반환
     *
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * 등록자ID을 설정
     *
     * @param rgtrId을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
    }

    /**
     * 등록자ID을 반환
     *
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * 등록자명을 설정
     *
     * @param rgtrNm을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * 등록자명을 반환
     *
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * 등록일시을 반환
     *
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * 등록일시을 설정
     *
     * @param regDt 을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    /**
     * 수정자ID을 설정
     *
     * @param mdfrId을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * 수정자ID을 반환
     *
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * 수정자명을 설정
     *
     * @param updusrNm을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * 수정자명을 반환
     *
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
    }

    /**
     * 수정일시을 반환
     *
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * 수정일시을 설정
     *
     * @param updtDt 을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * String[] multiLangMsgCns을 반환
     *
     * @return String[] multiLangMsgCns
     */
    public String[] getMultiLangMsgCns() {
        return multiLangMsgCns;
    }

    /**
     * multiLangMsgCns을 설정
     *
     * @param multiLangMsgCns 을(를) String[] multiLangMsgCns로 설정
     */
    public void setMultiLangMsgCns(String[] multiLangMsgCns) {
        this.multiLangMsgCns = multiLangMsgCns;
    }

    /**
     * String[] langCdIds을 반환
     *
     * @return String[] langCdIds
     */
    public String[] getLangCdIds() {
        return langCdIds;
    }

    /**
     * langCdIds을 설정
     *
     * @param langCdIds 을(를) String[] langCdIds로 설정
     */
    public void setLangCdIds(String[] langCdIds) {
        this.langCdIds = langCdIds;
    }

    /**
     * Boolean isFolder을 반환
     *
     * @return Boolean isFolder
     */
    public Boolean getIsFolder() {
        return isFolder;
    }

    /**
     * isFolder을 설정
     *
     * @param isFolder 을(를) Boolean isFolder로 설정
     */
    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    /**
     * List<MultilangGrpVO> messageList을 반환
     *
     * @return List<MultilangGrpVO> messageList
     */
    public List<MultilangGrpVO> getMessageList() {
        return messageList;
    }

    /**
     * messageList을 설정
     *
     * @param messageList 을(를) List<MultilangGrpVO> messageList로 설정
     */
    public void setMessageList(List<MultilangGrpVO> messageList) {
        this.messageList = messageList;
    }

}
