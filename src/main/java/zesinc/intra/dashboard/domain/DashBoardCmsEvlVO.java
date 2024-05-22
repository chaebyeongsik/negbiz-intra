package zesinc.intra.dashboard.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.vo.PageVO;

public class DashBoardCmsEvlVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -5991278115873072288L;

    /** 도메인코드 */
    private Integer siteSn;
    /** 도메인명 */
    private String siteNm;
    /** 도메인설명 */
    private String siteExpln;
    /** 사용자메뉴코드 */
    private String userMenuEngNm;
    /** 메뉴명 */
    private String menuNm;
    /** 메뉴경로 */
    private String menuPathNm;
    /** 권한유형 */
    private String authrtGroupNm;
    /** 권한부서코드 */
    private String authrtdDeptCdId;
    /** 권한부서명 */
    private String authorDeptNm;
    /** 권한담당자목록 */
    private List<DashBoardCmsOrgVO> authorChargerList;

    /** 등록일 */
    private Date regYmd;
    /** 항목번호 */
    private Integer artclSn;
    /** 항목번호1 */
    private Integer artclSn1;
    /** 항목번호2 */
    private Integer artclSn2;
    /** 항목번호3 */
    private Integer artclSn3;
    /** 항목번호4 */
    private Integer artclSn4;
    /** 항목번호5 */
    private Integer artclSn5;
    /** 총점 */
    private Integer sumScr;
    /** 총응답수 */
    private Integer wholRspnsCnt;
    /** 총평균 */
    private Float allAvg;

    /**
     * Integer siteSn을 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * siteSn을 설정
     * 
     * @param siteSn 을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * String siteNm을 반환
     * 
     * @return String siteNm
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * siteNm을 설정
     * 
     * @param siteNm 을(를) String siteNm로 설정
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
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
     * String userMenuEngNm을 반환
     * 
     * @return String userMenuEngNm
     */
    public String getUserMenuEngNm() {
        return userMenuEngNm;
    }

    /**
     * userMenuEngNm을 설정
     * 
     * @param userMenuEngNm 을(를) String userMenuEngNm로 설정
     */
    public void setUserMenuEngNm(String userMenuEngNm) {
        this.userMenuEngNm = userMenuEngNm;
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
     * String menuPathNm을 반환
     * 
     * @return String menuPathNm
     */
    public String getMenuPathNm() {
        return menuPathNm;
    }

    /**
     * menuPathNm을 설정
     * 
     * @param menuPathNm 을(를) String menuPathNm로 설정
     */
    public void setMenuPathNm(String menuPathNm) {
        this.menuPathNm = menuPathNm;
    }

    /**
     * String authrtGroupNm을 반환
     * 
     * @return String authrtGroupNm
     */
    public String getAuthrtGroupNm() {
        return authrtGroupNm;
    }

    /**
     * authrtGroupNm을 설정
     * 
     * @param authrtGroupNm 을(를) String authrtGroupNm로 설정
     */
    public void setAuthrtGroupNm(String authrtGroupNm) {
        this.authrtGroupNm = authrtGroupNm;
    }

    /**
     * String authrtdDeptCdId을 반환
     * 
     * @return String authrtdDeptCdId
     */
    public String getAuthrtdDeptCdId() {
        return authrtdDeptCdId;
    }

    /**
     * authrtdDeptCdId을 설정
     * 
     * @param authrtdDeptCdId 을(를) String authrtdDeptCdId로 설정
     */
    public void setAuthrtdDeptCdId(String authrtdDeptCdId) {
        this.authrtdDeptCdId = authrtdDeptCdId;
    }

    /**
     * String authorDeptNm을 반환
     * 
     * @return String authorDeptNm
     */
    public String getAuthorDeptNm() {
        return authorDeptNm;
    }

    /**
     * authorDeptNm을 설정
     * 
     * @param authorDeptNm 을(를) String authorDeptNm로 설정
     */
    public void setAuthorDeptNm(String authorDeptNm) {
        this.authorDeptNm = authorDeptNm;
    }

    /**
     * List<DashBoardCmsOrgVO> authorChargerList을 반환
     * 
     * @return List<DashBoardCmsOrgVO> authorChargerList
     */
    public List<DashBoardCmsOrgVO> getAuthorChargerList() {
        return authorChargerList;
    }

    /**
     * authorChargerList을 설정
     * 
     * @param authorChargerList 을(를) List<DashBoardCmsOrgVO> authorChargerList로 설정
     */
    public void setAuthorChargerList(List<DashBoardCmsOrgVO> authorChargerList) {
        this.authorChargerList = authorChargerList;
    }

    /**
     * Date regYmd을 반환
     * 
     * @return Date regYmd
     */
    public Date getRegYmd() {
        return regYmd;
    }

    /**
     * regYmd을 설정
     * 
     * @param regYmd 을(를) Date regYmd로 설정
     */
    public void setRegYmd(Date regYmd) {
        this.regYmd = regYmd;
    }

    /**
     * Integer artclSn을 반환
     * 
     * @return Integer artclSn
     */
    public Integer getArtclSn() {
        return artclSn;
    }

    /**
     * artclSn을 설정
     * 
     * @param itemNo 을(를) Integer artclSn로 설정
     */
    public void setArtclSn(Integer artclSn) {
        this.artclSn = artclSn;
    }

    /**
     * Integer artclSn1을 반환
     * 
     * @return Integer artclSn1
     */
    public Integer getArtclSn1() {
        return artclSn1;
    }

    /**
     * artclSn1을 설정
     * 
     * @param artclSn1 을(를) Integer artclSn1로 설정
     */
    public void setArtclSn1(Integer artclSn1) {
        this.artclSn1 = artclSn1;
    }

    /**
     * Integer artclSn2을 반환
     * 
     * @return Integer artclSn2
     */
    public Integer getArtclSn2() {
        return artclSn2;
    }

    /**
     * artclSn2을 설정
     * 
     * @param artclSn2 을(를) Integer artclSn2로 설정
     */
    public void setArtclSn2(Integer artclSn2) {
        this.artclSn2 = artclSn2;
    }

    /**
     * Integer artclSn3을 반환
     * 
     * @return Integer artclSn3
     */
    public Integer getArtclSn3() {
        return artclSn3;
    }

    /**
     * artclSn3을 설정
     * 
     * @param artclSn3 을(를) Integer artclSn3로 설정
     */
    public void setArtclSn3(Integer artclSn3) {
        this.artclSn3 = artclSn3;
    }

    /**
     * Integer artclSn4을 반환
     * 
     * @return Integer artclSn4
     */
    public Integer getArtclSn4() {
        return artclSn4;
    }

    /**
     * artclSn4을 설정
     * 
     * @param artclSn4 을(를) Integer artclSn4로 설정
     */
    public void setArtclSn4(Integer artclSn4) {
        this.artclSn4 = artclSn4;
    }

    /**
     * Integer artclSn5을 반환
     * 
     * @return Integer artclSn5
     */
    public Integer getArtclSn5() {
        return artclSn5;
    }

    /**
     * artclSn5을 설정
     * 
     * @param artclSn5 을(를) Integer artclSn5로 설정
     */
    public void setArtclSn5(Integer artclSn5) {
        this.artclSn5 = artclSn5;
    }

    /**
     * Integer sumScr을 반환
     * 
     * @return Integer sumScr
     */
    public Integer getSumScr() {
        return sumScr;
    }

    /**
     * sumScr을 설정
     * 
     * @param sumScr 을(를) Integer sumScr로 설정
     */
    public void setSumScr(Integer sumScr) {
        this.sumScr = sumScr;
    }

    /**
     * Integer wholRspnsCnt을 반환
     * 
     * @return Integer wholRspnsCnt
     */
    public Integer getWholRspnsCnt() {
        return wholRspnsCnt;
    }

    /**
     * wholRspnsCnt을 설정
     * 
     * @param wholRspnsCnt 을(를) Integer wholRspnsCnt로 설정
     */
    public void setWholRspnsCnt(Integer wholRspnsCnt) {
        this.wholRspnsCnt = wholRspnsCnt;
    }

    /**
     * Float allAvg을 반환
     * 
     * @return Float allAvg
     */
    public Float getAllAvg() {
        return allAvg;
    }

    /**
     * allAvg을 설정
     * 
     * @param allAvg 을(를) Float allAvg로 설정
     */
    public void setAllAvg(Float allAvg) {
        this.allAvg = allAvg;
    }

}
