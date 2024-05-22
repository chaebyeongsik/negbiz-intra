/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dashboard.domain;

import java.util.List;

import zesinc.web.vo.PageVO;

/**
 * 사용자메뉴 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-05-18.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class DashBoardCmsVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -8799068566648626206L;
    /** 도메인코드 */
    private Integer siteSn;
    /** 도메인명 */
    private String siteNm;
    /** 도메인설명 */
    private String siteExpln;
    /** 포트번호 */
    private Integer portSn;
    /** 컨텍스트경로 */
    private String sitePathNm;
    /** 사용자메뉴코드 */
    private String userMenuEngNm;
    /** 메뉴명 */
    private String menuNm;
    /** 제목 */
    private String ttl;
    /** 기본경로 */
    private String bscPathNm;
    /** 메뉴경로 */
    private String menuPathNm;
    /** 정렬순서 */
    private Integer sortSn;
    /** 관리자메뉴URL */
    private String mngrMenuUrlAddr;
    /** 사용자메뉴URL */
    private String userMenuUrlAddr;
    /** 메뉴유형 */
    private String menuType;
    /** 링크유형 */
    private String linkTypeNm;
    /** 컨텐츠유형 */
    private String contsTypeNm;
    /** 담당부서코드 */
    private String tkcgDeptCdId;
    /** 담당부서명 */
    private String chrgDeptNm;
    /** 담당자ID */
    private String picId;
    /** 담당자명 */
    private String picNm;
    /** 권한유형 */
    private String authrtGroupNm;
    /** 권한부서코드 */
    private String authrtdDeptCdId;
    /** 권한부서명 */
    private String authorDeptNm;
    /** 권한담당자목록 */
    private List<DashBoardCmsOrgVO> authorChargerList;
    /** 컨텐츠일련번호 */
    private Integer contsSn;
    /** 승인여부 */
    private String aprvYn;
    /** 만족도표시여부 */
    private String dgstfnIndctYn;
    /** 담당자표시여부 */
    private String picIndctYn;
    /** 표시여부 */
    private String indctYn;
    /** 사용여부 */
    private String useYn;

    // 만족도
    /** 총점 */
    private Integer sumScr;
    /** 총응답수 */
    private Integer wholRspnsCnt;
    /** 평균 */
    private Float avgScore;

    /**
     * 도메인코드 설정
     * 
     * @param siteSn을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * 도메인코드 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * 도메인명 설정
     * 
     * @param siteNm을(를) String siteNm로 설정
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    /**
     * 도메인명 반환
     * 
     * @return String siteNm
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * String siteExpln을 반환
     * 
     * @return String siteExpln
     */
    public String getSiteExpln() {
        return siteExpln;
    }

    /**
     * siteExpln을 설정
     * 
     * @param siteExpln 을(를) String siteExpln로 설정
     */
    public void setSiteExpln(String siteExpln) {
        this.siteExpln = siteExpln;
    }

    /**
     * Integer portSn을 반환
     * 
     * @return Integer portSn
     */
    public Integer getPortSn() {
        return portSn;
    }

    /**
     * portSn을 설정
     * 
     * @param portSn 을(를) Integer portSn로 설정
     */
    public void setPortSn(Integer portSn) {
        this.portSn = portSn;
    }

    /**
     * String sitePathNm을 반환
     * 
     * @return String sitePathNm
     */
    public String getSitePathNm() {
        return sitePathNm;
    }

    /**
     * sitePathNm을 설정
     * 
     * @param sitePathNm 을(를) String sitePathNm로 설정
     */
    public void setSitePathNm(String sitePathNm) {
        this.sitePathNm = sitePathNm;
    }

    /**
     * 사용자메뉴코드 설정
     * 
     * @param userMenuEngNm을(를) String userMenuEngNm로 설정
     */
    public void setUserMenuEngNm(String userMenuEngNm) {
        this.userMenuEngNm = userMenuEngNm;
    }

    /**
     * 사용자메뉴코드 반환
     * 
     * @return String userMenuEngNm
     */
    public String getUserMenuEngNm() {
        return userMenuEngNm;
    }

    /**
     * 메뉴명 설정
     * 
     * @param menuNm을(를) String menuNm로 설정
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    /**
     * 메뉴명 반환
     * 
     * @return String menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }

    /**
     * 제목 설정
     * 
     * @param ttl을(를) String ttl로 설정
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * 제목 반환
     * 
     * @return String ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * 기본경로 설정
     * 
     * @param bscPathNm을(를) String bscPathNm로 설정
     */
    public void setBscPathNm(String bscPathNm) {
        this.bscPathNm = bscPathNm;
    }

    /**
     * 기본경로 반환
     * 
     * @return String bscPathNm
     */
    public String getBscPathNm() {
        return bscPathNm;
    }

    /**
     * 메뉴경로 설정
     * 
     * @param menuPathNm을(를) String menuPathNm로 설정
     */
    public void setMenuPathNm(String menuPathNm) {
        this.menuPathNm = menuPathNm;
    }

    /**
     * 메뉴경로 반환
     * 
     * @return String menuPathNm
     */
    public String getMenuPathNm() {
        return menuPathNm;
    }

    /**
     * 정렬순서 설정
     * 
     * @param sortSn을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
    }

    /**
     * 정렬순서 반환
     * 
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
    }

    /**
     * 관리자메뉴URL 설정
     * 
     * @param mngrMenuUrlAddr을(를) String mngrMenuUrlAddr로 설정
     */
    public void setMngrMenuUrlAddr(String mngrMenuUrlAddr) {
        this.mngrMenuUrlAddr = mngrMenuUrlAddr;
    }

    /**
     * 관리자메뉴URL 반환
     * 
     * @return String mngrMenuUrlAddr
     */
    public String getMngrMenuUrlAddr() {
        return mngrMenuUrlAddr;
    }

    /**
     * 사용자메뉴URL 설정
     * 
     * @param userMenuUrlAddr을(를) String userMenuUrlAddr로 설정
     */
    public void setUserMenuUrlAddr(String userMenuUrlAddr) {
        this.userMenuUrlAddr = userMenuUrlAddr;
    }

    /**
     * 사용자메뉴URL 반환
     * 
     * @return String userMenuUrlAddr
     */
    public String getUserMenuUrlAddr() {
        return userMenuUrlAddr;
    }

    /**
     * 메뉴유형 설정
     * 
     * @param menuType을(를) String menuType로 설정
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 메뉴유형 반환
     * 
     * @return String menuType
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 링크유형 설정
     * 
     * @param linkTypeNm을(를) String linkTypeNm로 설정
     */
    public void setLinkTypeNm(String linkTypeNm) {
        this.linkTypeNm = linkTypeNm;
    }

    /**
     * 링크유형 반환
     * 
     * @return String linkTypeNm
     */
    public String getLinkTypeNm() {
        return linkTypeNm;
    }

    /**
     * 컨텐츠유형 설정
     * 
     * @param contsTypeNm을(를) String contsTypeNm로 설정
     */
    public void setContsTypeNm(String contsTypeNm) {
        this.contsTypeNm = contsTypeNm;
    }

    /**
     * 컨텐츠유형 반환
     * 
     * @return String contsTypeNm
     */
    public String getContsTypeNm() {
        return contsTypeNm;
    }

    /**
     * 담당부서코드 설정
     * 
     * @param tkcgDeptCdId을(를) String tkcgDeptCdId로 설정
     */
    public void setTkcgDeptCdId(String tkcgDeptCdId) {
        this.tkcgDeptCdId = tkcgDeptCdId;
    }

    /**
     * 담당부서코드 반환
     * 
     * @return String tkcgDeptCdId
     */
    public String getTkcgDeptCdId() {
        return tkcgDeptCdId;
    }

    /**
     * 담당부서명 설정
     * 
     * @param chrgDeptNm을(를) String chrgDeptNm로 설정
     */
    public void setChrgDeptNm(String chrgDeptNm) {
        this.chrgDeptNm = chrgDeptNm;
    }

    /**
     * 담당부서명 반환
     * 
     * @return String chrgDeptNm
     */
    public String getChrgDeptNm() {
        return chrgDeptNm;
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
     * 담당자명을 반환
     * 
     * @return String picNm
     */
    public String getPicNm() {
        return picNm;
    }

    /**
     * 담당자명을 설정
     * 
     * @param picNm 을(를) String picNm로 설정
     */
    public void setPicNm(String picNm) {
        this.picNm = picNm;
    }

    /**
     * 권한유형 설정
     * 
     * @param authrtGroupNm을(를) String authrtGroupNm로 설정
     */
    public void setAuthrtGroupNm(String authrtGroupNm) {
        this.authrtGroupNm = authrtGroupNm;
    }

    /**
     * 권한유형 반환
     * 
     * @return String authrtGroupNm
     */
    public String getAuthrtGroupNm() {
        return authrtGroupNm;
    }

    /**
     * 권한부서코드 설정
     * 
     * @param authrtdDeptCdId을(를) String authrtdDeptCdId로 설정
     */
    public void setAuthrtdDeptCdId(String authrtdDeptCdId) {
        this.authrtdDeptCdId = authrtdDeptCdId;
    }

    /**
     * 권한부서코드 반환
     * 
     * @return String authrtdDeptCdId
     */
    public String getAuthrtdDeptCdId() {
        return authrtdDeptCdId;
    }

    /**
     * 권한부서명 설정
     * 
     * @param authorDeptNm을(를) String authorDeptNm로 설정
     */
    public void setAuthorDeptNm(String authorDeptNm) {
        this.authorDeptNm = authorDeptNm;
    }

    /**
     * 권한부서명 반환
     * 
     * @return String authorDeptNm
     */
    public String getAuthorDeptNm() {
        return authorDeptNm;
    }

    /**
     * List<CmsOrgVO> authorChargerList을 반환
     * 
     * @return List<CmsOrgVO> authorChargerList
     */
    public List<DashBoardCmsOrgVO> getAuthorChargerList() {
        return authorChargerList;
    }

    /**
     * authorChargerList을 설정
     * 
     * @param authorChargerList 을(를) List<CmsOrgVO> authorChargerList로 설정
     */
    public void setAuthorChargerList(List<DashBoardCmsOrgVO> authorChargerList) {
        this.authorChargerList = authorChargerList;
    }

    /**
     * 컨텐츠일련번호 설정
     * 
     * @param contsSn을(를) Integer contsSn로 설정
     */
    public void setContsSn(Integer contsSn) {
        this.contsSn = contsSn;
    }

    /**
     * 컨텐츠일련번호 반환
     * 
     * @return Integer contsSn
     */
    public Integer getContsSn() {
        return contsSn;
    }

    /**
     * 승인여부 설정
     * 
     * @param aprvYn을(를) String aprvYn로 설정
     */
    public void setAprvYn(String aprvYn) {
        this.aprvYn = aprvYn;
    }

    /**
     * 승인여부 반환
     * 
     * @return String aprvYn
     */
    public String getAprvYn() {
        return aprvYn;
    }

    /**
     * 만족도표시여부 설정
     * 
     * @param dgstfnIndctYn을(를) String dgstfnIndctYn로 설정
     */
    public void setDgstfnIndctYn(String dgstfnIndctYn) {
        this.dgstfnIndctYn = dgstfnIndctYn;
    }

    /**
     * 만족도표시여부 반환
     * 
     * @return String dgstfnIndctYn
     */
    public String getDgstfnIndctYn() {
        return dgstfnIndctYn;
    }

    /**
     * 담당자표시여부 설정
     * 
     * @param picIndctYn을(를) String picIndctYn로 설정
     */
    public void setPicIndctYn(String picIndctYn) {
        this.picIndctYn = picIndctYn;
    }

    /**
     * 담당자표시여부 반환
     * 
     * @return String picIndctYn
     */
    public String getPicIndctYn() {
        return picIndctYn;
    }

    /**
     * 표시여부 설정
     * 
     * @param indctYn을(를) String indctYn로 설정
     */
    public void setIndctYn(String indctYn) {
        this.indctYn = indctYn;
    }

    /**
     * 표시여부 반환
     * 
     * @return String indctYn
     */
    public String getIndctYn() {
        return indctYn;
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
     * 총점 반환
     * 
     * @return Integer sumScr
     */
    public Integer getSumScr() {
        return sumScr;
    }

    /**
     * 총점 설정
     * 
     * @param sumScr 을(를) Integer sumScr로 설정
     */
    public void setSumScr(Integer sumScr) {
        this.sumScr = sumScr;
    }

    /**
     * 총응답수 반환
     * 
     * @return Integer wholRspnsCnt
     */
    public Integer getWholRspnsCnt() {
        return wholRspnsCnt;
    }

    /**
     * 총응답수 설정
     * 
     * @param wholRspnsCnt 을(를) Integer wholRspnsCnt로 설정
     */
    public void setWholRspnsCnt(Integer wholRspnsCnt) {
        this.wholRspnsCnt = wholRspnsCnt;
    }

    /**
     * 평균 반환
     * 
     * @return Float avgScore
     */
    public Float getAvgScore() {
        return avgScore;
    }

    /**
     * 평균 설정
     * 
     * @param avgScore 을(를) Float avgScore로 설정
     */
    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }

}
