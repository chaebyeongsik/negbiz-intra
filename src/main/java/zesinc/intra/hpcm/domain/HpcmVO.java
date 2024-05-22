/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.hpcm.domain;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 도움말 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-04-08.    박수정   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class HpcmVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -8230637477067579344L;
    /** 순번 */
    @Required
    @Digits
    private Integer regSn;

    /** 메뉴코드 */
    @Digits
    private Integer menuSn;

    /** 메뉴명 */
    private String menuNm;

    /** 메뉴경로 */
    private String menuPath;

    /** 메뉴사용여부 */
    private String menuUseYn;

    /** 도움말내용 */
    @MaxLength(max = 4000)
    private String docCn;

    /** 메뉴상위정보 */
    private String menuUpperInfo;

    /**
     * 도움말순번 설정
     * 
     * @param regSn을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 도움말순번 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * 메뉴코드 설정
     * 
     * @param menuSn을(를) Integer menuSn로 설정
     */
    public void setMenuSn(Integer menuSn) {
        this.menuSn = menuSn;
    }

    /**
     * 메뉴코드 반환
     * 
     * @return Integer menuSn
     */
    public Integer getMenuSn() {
        return menuSn;
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
     * 메뉴경로 설정
     * 
     * @param menuPath을(를) String menuPath로 설정
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /**
     * 메뉴경로 반환
     * 
     * @return String menuPath
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * String 메뉴사용여부를 반환
     * 
     * @return String menuUseYn
     */
    public String getMenuUseYn() {
        return menuUseYn;
    }

    /**
     * 메뉴사용여부를 설정
     * 
     * @param menuUseYn 을(를) String menuUseYn로 설정
     */
    public void setMenuUseYn(String menuUseYn) {
        this.menuUseYn = menuUseYn;
    }

    /**
     * 도움말내용 설정
     * 
     * @param docCn을(를) String docCn로 설정
     */
    public void setDocCn(String docCn) {
        this.docCn = docCn;
    }

    /**
     * 도움말내용 반환
     * 
     * @return String docCn
     */
    public String getDocCn() {
        return docCn;
    }

    /**
     * String menuUpperInfo을 반환
     * 
     * @return String menuUpperInfo
     */
    public String getMenuUpperInfo() {
        return menuUpperInfo;
    }

    /**
     * menuUpperInfo을 설정
     * 
     * @param menuUpperInfo 을(를) String menuUpperInfo로 설정
     */
    public void setMenuUpperInfo(String menuUpperInfo) {
        this.menuUpperInfo = menuUpperInfo;
    }

}
