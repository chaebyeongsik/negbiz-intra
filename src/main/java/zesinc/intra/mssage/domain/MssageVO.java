/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mssage.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 메시지 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-03-09.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class MssageVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 3957163599833910098L;
    /** 순번 */
    @Required
    @Digits
    private Integer regSn;

    /** 담당자ID */
    @Required
    @RangeLength(min = 2, max = 20)
    private String picId;

    /** 내용 */
    @RangeLength(min = 2, max = 4000)
    private String msgCn;

    /** 송신자ID */
    @RangeLength(min = 2, max = 20)
    private String sndptyId;

    /** 수신일시 */
    private Date rcptnDt;

    /** 수신자삭제여부 */
    private String rcvrDelYn;

    /** 송신자삭제여부 */
    private String sndptyDelYn;

    /** 송신여부 */
    private String dsptchYn;

    /** 파일순번 */
    @Digits
    private Integer fileSn;

    /** 등록일시 */
    private Date regDt;

    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 플래그(탭구분) : 기본값 R(수신) */
    private String flag = "R";

    /** 담당자명 */
    private String picNm;

    /** 송신자명 */
    private String trnsmiterNm;

    /** 배열 일련번호 */
    private String arraySn;

    /** autocomplete label */
    private String label;

    /** autocomplete value */
    private String value;

    /** 수신대상 담당자 */
    private String receivers;

    /**
     * 순번을 설정
     * 
     * @param regSn을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 순번을 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * 담당자ID을 설정
     * 
     * @param picId을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * 담당자ID을 반환
     * 
     * @return String picId
     */
    public String getPicId() {
        return picId;
    }

    /**
     * 내용을 설정
     * 
     * @param msgCn을(를) String msgCn로 설정
     */
    public void setMsgCn(String msgCn) {
        this.msgCn = msgCn;
    }

    /**
     * 내용을 반환
     * 
     * @return String msgCn
     */
    public String getMsgCn() {
        return msgCn;
    }

    /**
     * 송신자ID을 설정
     * 
     * @param sndptyId을(를) String sndptyId로 설정
     */
    public void setSndptyId(String sndptyId) {
        this.sndptyId = sndptyId;
    }

    /**
     * 송신자ID을 반환
     * 
     * @return String sndptyId
     */
    public String getSndptyId() {
        return sndptyId;
    }

    /**
     * 수신일시를 설정
     * 
     * @param rcptnDt을(를) Date rcptnDt로 설정
     */
    public void setRcptnDt(Date rcptnDt) {
        this.rcptnDt = rcptnDt;
    }

    /**
     * 수신일시를 반환
     * 
     * @return Date rcptnDt
     */
    public Date getRcptnDt() {
        return rcptnDt;
    }

    /**
     * 수신자삭제여부을 설정
     * 
     * @param rcvrDelYn을(를) String rcvrDelYn로 설정
     */
    public void setRcvrDelYn(String rcvrDelYn) {
        this.rcvrDelYn = rcvrDelYn;
    }

    /**
     * 수신자삭제여부을 반환
     * 
     * @return String rcvrDelYn
     */
    public String getRcvrDelYn() {
        return rcvrDelYn;
    }

    /**
     * 송신자삭제여부을 설정
     * 
     * @param sndptyDelYn을(를) String sndptyDelYn로 설정
     */
    public void setSndptyDelYn(String sndptyDelYn) {
        this.sndptyDelYn = sndptyDelYn;
    }

    /**
     * 송신자삭제여부을 반환
     * 
     * @return String sndptyDelYn
     */
    public String getSndptyDelYn() {
        return sndptyDelYn;
    }

    /**
     * 송신여부을 설정
     * 
     * @param dsptchYn을(를) String dsptchYn로 설정
     */
    public void setDsptchYn(String dsptchYn) {
        this.dsptchYn = dsptchYn;
    }

    /**
     * 송신여부을 반환
     * 
     * @return String dsptchYn
     */
    public String getDsptchYn() {
        return dsptchYn;
    }

    /**
     * 파일순번을 설정
     * 
     * @param fileSn을(를) Integer fileSn로 설정
     */
    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * 파일순번을 반환
     * 
     * @return Integer fileSn
     */
    public Integer getFileSn() {
        return fileSn;
    }

    /**
     * 등록일시을 설정
     * 
     * @param regDt을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
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
     * String flag을 반환
     * @return String flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * flag을 설정
     * @param flag 을(를) String flag로 설정
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * String picNm을 반환
     * @return String picNm
     */
    public String getPicNm() {
        return picNm;
    }

    /**
     * picNm을 설정
     * @param picNm 을(를) String picNm로 설정
     */
    public void setPicNm(String picNm) {
        this.picNm = picNm;
    }

    /**
     * String trnsmiterNm을 반환
     * @return String trnsmiterNm
     */
    public String getTrnsmiterNm() {
        return trnsmiterNm;
    }

    /**
     * trnsmiterNm을 설정
     * @param trnsmiterNm 을(를) String trnsmiterNm로 설정
     */
    public void setTrnsmiterNm(String trnsmiterNm) {
        this.trnsmiterNm = trnsmiterNm;
    }

    /**
     * String arraySn을 반환
     * @return String arraySn
     */
    public String getArraySn() {
        return arraySn;
    }

    /**
     * arraySn을 설정
     * @param arraySn 을(를) String arraySn로 설정
     */
    public void setArraySn(String arraySn) {
        this.arraySn = arraySn;
    }

    /**
     * String label을 반환
     * @return String label
     */
    public String getLabel() {
        return label;
    }

    /**
     * label을 설정
     * @param label 을(를) String label로 설정
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * String value을 반환
     * @return String value
     */
    public String getValue() {
        return value;
    }

    /**
     * value을 설정
     * @param value 을(를) String value로 설정
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * String receivers을 반환
     * @return String receivers
     */
    public String getReceivers() {
        return receivers;
    }

    /**
     * receivers을 설정
     * @param receivers 을(를) String receivers로 설정
     */
    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

}
