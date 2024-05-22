/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs.domain;

import zesinc.web.vo.BaseVO;

/**
 * 게시판태그 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 8. 5.    woogi   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsTagVO extends BaseVO {

    private static final long serialVersionUID = 325963120381573861L;

    /** 게시판코드 */
    private Integer bbsSn;
    /** 게시글일련번호 */
    private String bbsDocNo;
    /** 태그명 */
    private String tagNm;
    /** 태그수 */
    private Integer tagCo;

    public BbsTagVO(Integer bbsSn, String bbsDocNo, String tagNm) {
        this.bbsSn = bbsSn;
        this.bbsDocNo = bbsDocNo;
        this.tagNm = tagNm;
    }

    public BbsTagVO() {

    }

    /**
     * Integer bbsSn을 반환
     * 
     * @return Integer bbsSn
     */
    public Integer getBbsSn() {
        return bbsSn;
    }

    /**
     * bbsSn을 설정
     * 
     * @param bbsSn 을(를) Integer bbsSn로 설정
     */
    public void setBbsSn(Integer bbsSn) {
        this.bbsSn = bbsSn;
    }

    /**
     * String bbsDocNo을 반환
     * 
     * @return String bbsDocNo
     */
    public String getBbsDocNo() {
        return bbsDocNo;
    }

    /**
     * bbsDocNo을 설정
     * 
     * @param bbsDocNo 을(를) String bbsDocNo로 설정
     */
    public void setBbsDocNo(String bbsDocNo) {
        this.bbsDocNo = bbsDocNo;
    }

    /**
     * String tagNm을 반환
     * 
     * @return String tagNm
     */
    public String getTagNm() {
        return tagNm;
    }

    /**
     * tagNm을 설정
     * 
     * @param tagNm 을(를) String tagNm로 설정
     */
    public void setTagNm(String tagNm) {
        this.tagNm = tagNm;
    }

    /**
     * Integer tagCo을 반환
     * 
     * @return Integer tagCo
     */
    public Integer getTagCo() {
        return tagCo;
    }

    /**
     * tagCo을 설정
     * 
     * @param tagCo 을(를) Integer tagCo로 설정
     */
    public void setTagCo(Integer tagCo) {
        this.tagCo = tagCo;
    }

}
