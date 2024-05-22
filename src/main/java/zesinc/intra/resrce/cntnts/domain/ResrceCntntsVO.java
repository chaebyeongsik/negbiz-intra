/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.cntnts.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 자원컨텐츠 정보 VO 클레스
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
public class ResrceCntntsVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -7349404277454647557L;
    /** 자원일련번호 */
    @Required
    @Digits
    private Integer dataSn;

    /** 자원버전 */
    @Required
    @Digits
    private Integer chgCycl;

    /** 작성방법 */
    private String wrtTypeNm;

    /** 컨텐츠 */
    private String contsCn;

    /** 언어코드 */
    @MaxLength(max = 20)
    private String langCdId;

    /** 언어명 */
    private String langNm;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

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
     * 컨텐츠 설정
     * 
     * @param contsCn을(를) String contsCn로 설정
     */
    public void setContsCn(String contsCn) {
        this.contsCn = contsCn;
    }

    /**
     * 컨텐츠 반환
     * 
     * @return String contsCn
     */
    public String getContsCn() {
        return contsCn;
    }

    /**
     * 언어코드 설정
     * 
     * @param langCdId을(를) String langCdId로 설정
     */
    public void setLangCdId(String langCdId) {
        this.langCdId = langCdId;
    }

    /**
     * 언어코드 반환
     * 
     * @return String langCdId
     */
    public String getLangCdId() {
        return langCdId;
    }

    /**
     * 언어명 설정
     * 
     * @param langNm을(를) String langNm로 설정
     */
    public void setLangNm(String langNm) {
        this.langNm = langNm;
    }

    /**
     * 언어명 반환
     * 
     * @return String langNm
     */
    public String getLangNm() {
        return langNm;
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

}
