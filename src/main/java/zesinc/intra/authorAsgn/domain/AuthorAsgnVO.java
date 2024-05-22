/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.authorAsgn.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 담당자권한할당 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-06.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class AuthorAsgnVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -3462464817992039164L;
    /** 권한코드 */
    @Required
    @RangeLength(min = 2, max = 20)
    private String authrtCdId;

    /** 권한코드명 */
    private String authrtCdIdNm;

    /** 담당자ID */
    @Required
    @RangeLength(min = 2, max = 20)
    private String picId;

    /** 사용여부 */
    private String useYn;

    /** 등록자ID */
    @RangeLength(min = 2, max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    private Date regDt;

    /** 수정자ID */
    @RangeLength(min = 2, max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 메뉴코드 */
    private Integer menuSn;
    /** 메뉴명 */
    private String menuNm;
    /** 부서명 */
    private String deptNm;
    /** 담당자명 */
    private String picNm;
    /** 직급명 */
    private String clsfNm;
    /** 권한명 */
    private String authrtNm;
    /** 권한할당코드 */
    private String authrtGrntCdId;
    /** 할당권한목록 */
    private List<AuthorAsgnVO> authorAsgnList;
    private String picIds;
    private String authrtCdIds;
    private String[] authrtCdIdArray;

    /**
     * 권한코드 설정
     *
     * @param authrtCdId을(를) String authrtCdId로 설정
     */
    public void setAuthrtCdId(String authrtCdId) {
        this.authrtCdId = authrtCdId;
    }

    /**
     * 권한코드 반환
     *
     * @return String authrtCdId
     */
    public String getAuthrtCdId() {
        return authrtCdId;
    }

    /**
     * 권한코드명 설정
     *
     * @param authrtCdIdNm을(를) String authrtCdIdNm로 설정
     */
    public void setAuthrtCdIdNm(String authrtCdIdNm) {
        this.authrtCdIdNm = authrtCdIdNm;
    }

    /**
     * 권한코드명 반환
     *
     * @return String authrtCdIdNm
     */
    public String getAuthrtCdIdNm() {
        return authrtCdIdNm;
    }

    /**
     * 담당자ID 설정
     *
     * @param picId을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * 담당자ID 반환
     *
     * @return String picId
     */
    public String getPicId() {
        return picId;
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
     * Integer menuSn을 반환
     * 
     * @return Integer menuSn
     */
    public Integer getMenuSn() {
        return menuSn;
    }

    /**
     * menuSn을 설정
     * 
     * @param menuSn 을(를) Integer menuSn로 설정
     */
    public void setMenuSn(Integer menuSn) {
        this.menuSn = menuSn;
    }

    /**
     * String menuNm을 반환
     * 
     * @return String menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }

    /**
     * menuNm을 설정
     * 
     * @param menuNm 을(를) String menuNm로 설정
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    /**
     * String deptNm을 반환
     * 
     * @return String deptNm
     */
    public String getDeptNm() {
        return deptNm;
    }

    /**
     * deptNm을 설정
     * 
     * @param deptNm 을(를) String deptNm로 설정
     */
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    /**
     * String picNm을 반환
     * 
     * @return String picNm
     */
    public String getPicNm() {
        return picNm;
    }

    /**
     * picNm을 설정
     * 
     * @param picNm 을(를) String picNm로 설정
     */
    public void setPicNm(String picNm) {
        this.picNm = picNm;
    }

    /**
     * String clsfNm을 반환
     * 
     * @return String clsfNm
     */
    public String getClsfNm() {
        return clsfNm;
    }

    /**
     * clsfNm을 설정
     * 
     * @param clsfNm 을(를) String clsfNm로 설정
     */
    public void setClsfNm(String clsfNm) {
        this.clsfNm = clsfNm;
    }

    /**
     * String authrtNm을 반환
     * 
     * @return String authrtNm
     */
    public String getAuthrtNm() {
        return authrtNm;
    }

    /**
     * authrtNm을 설정
     * 
     * @param authrtNm 을(를) String authrtNm로 설정
     */
    public void setAuthrtNm(String authrtNm) {
        this.authrtNm = authrtNm;
    }

    /**
     * String authrtGrntCdId을 반환
     * 
     * @return String authrtGrntCdId
     */
    public String getAuthrtGrntCdId() {
        return authrtGrntCdId;
    }

    /**
     * authrtGrntCdId을 설정
     * 
     * @param authrtGrntCdId 을(를) String authrtGrntCdId로 설정
     */
    public void setAuthrtGrntCdId(String authrtGrntCdId) {
        this.authrtGrntCdId = authrtGrntCdId;
    }

    /**
     * List<AuthorAsgnVO> authorAsgnList을 반환
     * 
     * @return List<AuthorAsgnVO> authorAsgnList
     */
    public List<AuthorAsgnVO> getAuthorAsgnList() {
        return authorAsgnList;
    }

    /**
     * authorAsgnList을 설정
     * 
     * @param authorAsgnList 을(를) List<AuthorAsgnVO> authorAsgnList로 설정
     */
    public void setAuthorAsgnList(List<AuthorAsgnVO> authorAsgnList) {
        this.authorAsgnList = authorAsgnList;
    }

    /**
     * String authrtCdIds을 반환
     * 
     * @return String authrtCdIds
     */
    public String getAuthrtCdIds() {
        return authrtCdIds;
    }

    /**
     * authrtCdIds을 설정
     * 
     * @param authrtCdIds 을(를) String authrtCdIds로 설정
     */
    public void setAuthrtCdIds(String authrtCdIds) {
        this.authrtCdIds = authrtCdIds;
    }

    /**
     * String picIds을 반환
     * 
     * @return String picIds
     */
    public String getPicIds() {
        return picIds;
    }

    /**
     * picIds을 설정
     * 
     * @param picIds 을(를) String picIds로 설정
     */
    public void setPicIds(String picIds) {
        this.picIds = picIds;
    }

    /**
     * String[] authrtCdIdArray을 반환
     * @return String[] authrtCdIdArray
     */
    public String[] getAuthrtCdIdArray() {
        return authrtCdIdArray;
    }

    /**
     * authrtCdIdArray을 설정
     * @param authrtCdIdArray 을(를) String[] authrtCdIdArray로 설정
     */
    public void setAuthrtCdIdArray(String[] authrtCdIdArray) {
        this.authrtCdIdArray = authrtCdIdArray;
    }

}
