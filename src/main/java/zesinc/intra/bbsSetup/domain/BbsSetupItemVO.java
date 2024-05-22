/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup.domain;

import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 게시판환경설정 항목정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-05-04.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsSetupItemVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 2810004184059614309L;

    /** 게시판코드 */
    @Required
    private Integer bbsStngSn;

    /** 컬럼ID */
    @Required
    private String colId;

    /** 컬럼명 */
    @Required
    private String colNm;

    /** 화면명 */
    private String scrnNm;

    /** 컬럼유형 */
    private String colTypeNm;

    /** 안내문구 */
    private String bbsColExpln;

    /** 검색유형 */
    private String srchType;

    /** 필수여부 */
    private String esntlYn;

    /** 정렬순서 */
    private Integer sortSn;

    /** 목록표시여부 */
    @Required
    private String lstIndctYn;

    /** 읽기표시여부 */
    @Required
    private String inqIndctYn;

    /** 폼표시여부 */
    @Required
    private String inptIndctYn;

    /**
     * Integer bbsStngSn을 반환
     *
     * @return Integer bbsStngSn
     */
    public Integer getBbsStngSn() {
        return bbsStngSn;
    }

    /**
     * bbsStngSn을 설정
     *
     * @param bbsStngSn 을(를) Integer bbsStngSn로 설정
     */
    public void setBbsStngSn(Integer bbsStngSn) {
        this.bbsStngSn = bbsStngSn;
    }

    /**
     * String colId을 반환
     *
     * @return String colId
     */
    public String getColId() {
        return colId;
    }

    /**
     * colId을 설정
     *
     * @param colId 을(를) String colId로 설정
     */
    public void setColId(String colId) {
        this.colId = colId;
    }

    /**
     * String colNm을 반환
     *
     * @return String colNm
     */
    public String getColNm() {
        return colNm;
    }

    /**
     * colNm을 설정
     *
     * @param colNm 을(를) String colNm로 설정
     */
    public void setColNm(String colNm) {
        this.colNm = colNm;
    }

    /**
     * String scrnNm을 반환
     *
     * @return String scrnNm
     */
    public String getScrnNm() {
        return scrnNm;
    }

    /**
     * scrnNm을 설정
     *
     * @param scrnNm 을(를) String scrnNm로 설정
     */
    public void setScrnNm(String scrnNm) {
        this.scrnNm = scrnNm;
    }

    /**
     * String colTypeNm을 반환
     *
     * @return String colTypeNm
     */
    public String getColTypeNm() {
        return colTypeNm;
    }

    /**
     * colTypeNm을 설정
     *
     * @param colTypeNm 을(를) String colTypeNm로 설정
     */
    public void setColTypeNm(String colTypeNm) {
        this.colTypeNm = colTypeNm;
    }

    /**
     * String bbsColExpln을 반환
     *
     * @return String bbsColExpln
     */
    public String getBbsColExpln() {
        return bbsColExpln;
    }

    /**
     * bbsColExpln을 설정
     *
     * @param bbsColExpln 을(를) String bbsColExpln로 설정
     */
    public void setBbsColExpln(String bbsColExpln) {
        this.bbsColExpln = bbsColExpln;
    }

    /**
     * String srchType을 반환
     *
     * @return String srchType
     */
    public String getSrchType() {
        return srchType;
    }

    /**
     * srchType을 설정
     *
     * @param srchType 을(를) String srchType로 설정
     */
    public void setSrchType(String srchType) {
        this.srchType = srchType;
    }

    /**
     * String esntlYn을 반환
     *
     * @return String esntlYn
     */
    public String getEsntlYn() {
        return esntlYn;
    }

    /**
     * esntlYn을 설정
     *
     * @param esntlYn 을(를) String esntlYn로 설정
     */
    public void setEsntlYn(String esntlYn) {
        this.esntlYn = esntlYn;
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
     * String lstIndctYn을 반환
     *
     * @return String lstIndctYn
     */
    public String getLstIndctYn() {
        return lstIndctYn;
    }

    /**
     * lstIndctYn을 설정
     *
     * @param lstIndctYn 을(를) String lstIndctYn로 설정
     */
    public void setLstIndctYn(String lstIndctYn) {
        this.lstIndctYn = lstIndctYn;
    }

    /**
     * String inqIndctYn을 반환
     *
     * @return String inqIndctYn
     */
    public String getInqIndctYn() {
        return inqIndctYn;
    }

    /**
     * inqIndctYn을 설정
     *
     * @param inqIndctYn 을(를) String inqIndctYn로 설정
     */
    public void setInqIndctYn(String inqIndctYn) {
        this.inqIndctYn = inqIndctYn;
    }

    /**
     * String inptIndctYn을 반환
     *
     * @return String inptIndctYn
     */
    public String getInptIndctYn() {
        return inptIndctYn;
    }

    /**
     * inptIndctYn을 설정
     *
     * @param inptIndctYn 을(를) String inptIndctYn로 설정
     */
    public void setInptIndctYn(String inptIndctYn) {
        this.inptIndctYn = inptIndctYn;
    }

}
