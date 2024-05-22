/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.resrce.ctgry.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 자원카테고리 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-10-15.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class ResrceCtgryVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 4038692257371316333L;
    /** 카테고리일련번호 */
    @Required
    @Digits
    private Integer ctgrySn;

    /** 상위카테고리일련번호 */
    @Required
    @Digits
    private Integer upCtgrySn;

    /** 카테고리명 */
    @Required
    @MaxLength(max = 100)
    private String ctgryNm;

    /** 카테고리경로 */
    @MaxLength(max = 300)
    private String ctgryPathNm;

    /** 사용여부 */
    private String useYn;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    @Required
    private Date regDt;

    /** 자식 존재여부 */
    private Boolean isFolder;

    /**
     * 카테고리일련번호 설정
     * 
     * @param ctgrySn을(를) Integer ctgrySn로 설정
     */
    public void setCtgrySn(Integer ctgrySn) {
        this.ctgrySn = ctgrySn;
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
     * 상위카테고리일련번호 설정
     * 
     * @param upCtgrySn을(를) Integer upCtgrySn로 설정
     */
    public void setUpCtgrySn(Integer upCtgrySn) {
        this.upCtgrySn = upCtgrySn;
    }

    /**
     * 상위카테고리일련번호 반환
     * 
     * @return Integer upCtgrySn
     */
    public Integer getUpCtgrySn() {
        return upCtgrySn;
    }

    /**
     * 카테고리명 설정
     * 
     * @param ctgryNm을(를) String ctgryNm로 설정
     */
    public void setCtgryNm(String ctgryNm) {
        this.ctgryNm = ctgryNm;
    }

    /**
     * 카테고리명 반환
     * 
     * @return String ctgryNm
     */
    public String getCtgryNm() {
        return ctgryNm;
    }

    /**
     * 카테고리경로 설정
     * 
     * @param ctgryPathNm을(를) String ctgryPathNm로 설정
     */
    public void setCtgryPathNm(String ctgryPathNm) {
        this.ctgryPathNm = ctgryPathNm;
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

}
