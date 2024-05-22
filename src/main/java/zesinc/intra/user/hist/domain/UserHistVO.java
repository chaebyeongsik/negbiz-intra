/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.hist.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 사용자이력 정보 VO 클래스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 22.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserHistVO extends PageVO {

    /**  */
    private static final long serialVersionUID = -7622421590444701278L;

    /** 일시 */
    private Date logUnqNo;

    /** 담당자ID */
    @MaxLength(max = 20)
    private String picId;

    /** 담당자명 */
    @MaxLength(max = 100)
    private String picNm;

    /** 사유 */
    @RangeLength(min = 10, max = 4000)
    private String logCn;

    /** 변경내용 */
    @MaxLength(max = 4000)
    private String chgAftrCn;

    /** 메뉴명 */
    @MaxLength(max = 100)
    private String menuNm;

    /** 출력내용 */
    @MaxLength(max = 20)
    private String otptNm;

    /** 출력파일명 */
    @MaxLength(max = 300)
    private String otptFileNm;

    /** 메뉴구분 */
    @MaxLength(max = 1)
    private String menuSeCd;

    /** 사용자ID */
    @MaxLength(max = 20)
    private String userId;

    /** 사용자KEY */
    private String userIdntfNm;

    /** 담당자비밀번호 */
    private String picPswd;

    /** 엑셀키 */
    private String excelKey;

    /** 결과건수 */
    private Integer rsltNocs;

    /** 검색조건 */
    private String srchStngCn;

    /** 메뉴코드 */
    private Integer menuSn;

    /** 검색구분 */
    @Required
    private String searchId;

    /** 사용자명 */
    private String userNm;

    /** 조회구분 */
    private String srchSeCd;

    /**
     * Date logUnqNo을 반환
     * 
     * @return Date logUnqNo
     */
    public Date getLogUnqNo() {
        return logUnqNo;
    }

    /**
     * logUnqNo을 설정
     * 
     * @param logUnqNo 을(를) Date logUnqNo로 설정
     */
    public void setLogUnqNo(Date logUnqNo) {
        this.logUnqNo = logUnqNo;
    }

    /**
     * String picId을 반환
     * 
     * @return String picId
     */
    public String getPicId() {
        return picId;
    }

    /**
     * picId을 설정
     * 
     * @param picId 을(를) String picId로 설정
     */
    public void setPicId(String picId) {
        this.picId = picId;
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
     * String logCn을 반환
     * 
     * @return String logCn
     */
    public String getLogCn() {
        return logCn;
    }

    /**
     * logCn을 설정
     * 
     * @param logCn 을(를) String logCn로 설정
     */
    public void setLogCn(String logCn) {
        this.logCn = logCn;
    }

    /**
     * String chgAftrCn을 반환
     * 
     * @return String chgAftrCn
     */
    public String getChgAftrCn() {
        return chgAftrCn;
    }

    /**
     * chgAftrCn을 설정
     * 
     * @param chgAftrCn 을(를) String chgAftrCn로 설정
     */
    public void setChgAftrCn(String chgAftrCn) {
        this.chgAftrCn = chgAftrCn;
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
     * String otptNm을 반환
     * 
     * @return String otptNm
     */
    public String getOtptNm() {
        return otptNm;
    }

    /**
     * otptNm을 설정
     * 
     * @param otptNm 을(를) String otptNm로 설정
     */
    public void setOtptNm(String otptNm) {
        this.otptNm = otptNm;
    }

    /**
     * String otptFileNm을 반환
     * 
     * @return String otptFileNm
     */
    public String getOtptFileNm() {
        return otptFileNm;
    }

    /**
     * otptFileNm을 설정
     * 
     * @param otptFileNm 을(를) String otptFileNm로 설정
     */
    public void setOtptFileNm(String otptFileNm) {
        this.otptFileNm = otptFileNm;
    }

    /**
     * String menuSeCd을 반환
     * 
     * @return String menuSeCd
     */
    public String getMenuSeCd() {
        return menuSeCd;
    }

    /**
     * menuSeCd을 설정
     * 
     * @param menuSeCd 을(를) String menuSeCd로 설정
     */
    public void setMenuSeCd(String menuSeCd) {
        this.menuSeCd = menuSeCd;
    }

    /**
     * String userId을 반환
     * 
     * @return String userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * userId을 설정
     * 
     * @param userId 을(를) String userId로 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * String userIdntfNm을 반환
     * 
     * @return String userIdntfNm
     */
    public String getUserIdntfNm() {
        return userIdntfNm;
    }

    /**
     * userIdntfNm을 설정
     * 
     * @param userIdntfNm 을(를) String userIdntfNm로 설정
     */
    public void setUserIdntfNm(String userIdntfNm) {
        this.userIdntfNm = userIdntfNm;
    }

    /**
     * String picPswd을 반환
     * 
     * @return String picPswd
     */
    public String getPicPswd() {
        return picPswd;
    }

    /**
     * picPswd을 설정
     * 
     * @param picPswd 을(를) String picPswd로 설정
     */
    public void setPicPswd(String picPswd) {
        this.picPswd = picPswd;
    }

    /**
     * String excelKey을 반환
     * 
     * @return String excelKey
     */
    public String getExcelKey() {
        return excelKey;
    }

    /**
     * excelKey을 설정
     * 
     * @param excelKey 을(를) String excelKey로 설정
     */
    public void setExcelKey(String excelKey) {
        this.excelKey = excelKey;
    }

    /**
     * Integer rsltNocs을 반환
     * 
     * @return Integer rsltNocs
     */
    public Integer getRsltNocs() {
        return rsltNocs;
    }

    /**
     * rsltNocs을 설정
     * 
     * @param rsltNocs 을(를) Integer rsltNocs로 설정
     */
    public void setRsltNocs(Integer rsltNocs) {
        this.rsltNocs = rsltNocs;
    }

    /**
     * String srchStngCn을 반환
     * 
     * @return String srchStngCn
     */
    public String getSrchStngCn() {
        return srchStngCn;
    }

    /**
     * srchStngCn을 설정
     * 
     * @param srchStngCn 을(를) String srchStngCn로 설정
     */
    public void setSrchStngCn(String srchStngCn) {
        this.srchStngCn = srchStngCn;
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
     * String searchId을 반환
     * 
     * @return String searchId
     */
    public String getSearchId() {
        return searchId;
    }

    /**
     * searchId을 설정
     * 
     * @param searchId 을(를) String searchId로 설정
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /**
     * String userNm을 반환
     * 
     * @return String userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * userNm을 설정
     * 
     * @param userNm 을(를) String userNm로 설정
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * String srchSeCd을 반환
     * 
     * @return String srchSeCd
     */
    public String getSrchSeCd() {
        return srchSeCd;
    }

    /**
     * srchSeCd을 설정
     * 
     * @param srchSeCd 을(를) String srchSeCd로 설정
     */
    public void setSrchSeCd(String srchSeCd) {
        this.srchSeCd = srchSeCd;
    }

}
