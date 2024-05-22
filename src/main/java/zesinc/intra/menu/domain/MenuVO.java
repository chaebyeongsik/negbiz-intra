/*
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.menu.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Numeric;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.validate.annotation.marker.Size;
import zesinc.web.vo.BaseVO;

/**
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 2. 17.    ZES-INC   최초작성
 * </pre>
 * @see
 */
public class MenuVO extends BaseVO {

    private static final long serialVersionUID = -3994882574767031101L;

    /** 메뉴코드 */
    @Required
    @Numeric
    private Integer menuSn;

    /** 순번 */
    private Integer regSn;

    /** 상위메뉴코드 */
    @Numeric
    private Integer upMenuSn;

    /** 상위메뉴명 */
    private String upperMenuNm;

    /** 메뉴명 */
    @Required
    @MaxLength(max = 30)
    private String menuNm;

    /** 메뉴URL */
    private String[] menuUrlAddr;

    /** 권한코드 */
    private String[] grntCdId;

    /** 메뉴URL정보 */
    private List<MenuVO> menuUrlInfo;

    /** 프로그램명 */
    @MaxLength(max = 100)
    private String prgrmNm;

    /** 정렬순서 */
    @Numeric
    private Integer sortSn;

    /** 정렬순서2 */
    private Integer sortSn2;

    /** 표시여부 */
    @Size(min = 1, max = 1)
    private String indctYn;

    /** 사용여부 */
    @Required
    @Size(min = 1, max = 1)
    private String useYn;

    /** 등록일시 */
    private Date regDt;

    /** 수정일시 */
    private Date updtDt;

    /** 매뉴얼 */
    private String menuDtlExpln;

    /** 지정권한목록 */
    private String[] authNms;

    /** 메뉴URLstr(기존menuUrlAddr은 화면단에서 배열형태로 넘어오는 값을 사용) */
    private String menuUrlAddrStr;

    /** 메뉴권한코드str(기존menuUrlAddr은 화면단에서 배열형태로 넘어오는 값을 사용) */
    private String grntCdIdStr;

    /** 프로그램값 */
    private String progrmVal;

    /** 리퀘스트매핑URL */
    private List<String> ctrlReqUrl;

    /** 파라미터1 */
    @MaxLength(max = 100)
    private String prmttNm1;

    /** 파라미터2 */
    @MaxLength(max = 100)
    private String prmttNm2;

    /** 파라미터3 */
    @MaxLength(max = 100)
    private String prmttNm3;

    /** 메인URL사용여부 */
    private String mpgUrlYn;

    /** 구분자 */
    private String delimiter = " > ";

    /**  */
    private String homeStr = "홈";

    /** 하위메뉴를 가지고 있는지 여부 */
    private Boolean isFolder;

    /**
     * 메뉴코드를 설정
     * 
     * @param menuSn을(를) Integer menuSn로 설정
     */
    public void setMenuSn(Integer menuSn) {
        this.menuSn = menuSn;
    }

    /**
     * Integer 메뉴코드를 반환
     * 
     * @return Integer menuSn
     */
    public Integer getMenuSn() {
        return menuSn;
    }

    /**
     * Integer regSn을 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * regSn을 설정
     * 
     * @param regSn 을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 상위메뉴코드를 설정
     * 
     * @param upMenuSn을(를) Integer upMenuSn로 설정
     */
    public void setUpMenuSn(Integer upMenuSn) {
        this.upMenuSn = upMenuSn;
    }

    /**
     * Integer 상위메뉴코드를 반환
     * 
     * @return Integer upMenuSn
     */
    public Integer getUpMenuSn() {
        return upMenuSn;
    }

    /**
     * String upperMenuNm을 반환
     * 
     * @return String upperMenuNm
     */
    public String getUpperMenuNm() {
        return upperMenuNm;
    }

    /**
     * upperMenuNm을 설정
     * 
     * @param upperMenuNm 을(를) String upperMenuNm로 설정
     */
    public void setUpperMenuNm(String upperMenuNm) {
        this.upperMenuNm = upperMenuNm;
    }

    /**
     * 메뉴명을 설정
     * 
     * @param menuNm을(를) String menuNm로 설정
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    /**
     * String 메뉴명을 반환
     * 
     * @return String menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }

    /**
     * String[] menuUrlAddr을 반환
     * 
     * @return String[] menuUrlAddr
     */
    public String[] getMenuUrlAddr() {
        return menuUrlAddr;
    }

    /**
     * String[] grntCdId을 반환
     * 
     * @return String[] grntCdId
     */
    public String[] getGrntCdId() {
        return grntCdId;
    }

    /**
     * grntCdId을 설정
     * 
     * @param grntCdId 을(를) String[] grntCdId로 설정
     */
    public void setGrntCdId(String[] grntCdId) {
        this.grntCdId = grntCdId;
    }

    /**
     * menuUrlAddr을 설정
     * 
     * @param menuUrlAddr 을(를) String[] menuUrlAddr로 설정
     */
    public void setMenuUrlAddr(String[] menuUrlAddr) {
        this.menuUrlAddr = menuUrlAddr;
    }

    /**
     * List<MenuVO> 메뉴URL정보를 반환
     * 
     * @return List<MenuVO> menuUrlInfo
     */
    public List<MenuVO> getMenuUrlInfo() {
        return menuUrlInfo;
    }

    /**
     * 메뉴URL정보를 설정
     * 
     * @param menuUrlInfo 을(를) List<MenuVO> menuUrlInfo로 설정
     */
    public void setMenuUrlInfo(List<MenuVO> menuUrlInfo) {
        this.menuUrlInfo = menuUrlInfo;
    }

    /**
     * 프로그램명을 설정
     * 
     * @param prgrmNm을(를) String prgrmNm로 설정
     */
    public void setPrgrmNm(String prgrmNm) {
        this.prgrmNm = prgrmNm;
    }

    /**
     * String 프로그램명을 반환
     * 
     * @return String prgrmNm
     */
    public String getPrgrmNm() {
        return prgrmNm;
    }

    /**
     * 정렬순서를 설정
     * 
     * @param sortSn을(를) Integer sortSn로 설정
     */
    public void setSortSn(Integer sortSn) {
        this.sortSn = sortSn;
    }

    /**
     * Integer 정렬순서를 반환
     * 
     * @return Integer sortSn
     */
    public Integer getSortSn() {
        return sortSn;
    }

    /**
     * Integer sortSn2을 반환
     * 
     * @return Integer sortSn2
     */
    public Integer getSortSn2() {
        return sortSn2;
    }

    /**
     * sortSn2을 설정
     * 
     * @param sortSn2 을(를) Integer sortSn2로 설정
     */
    public void setSortSn2(Integer sortSn2) {
        this.sortSn2 = sortSn2;
    }

    /**
     * String indctYn을 반환
     * 
     * @return String indctYn
     */
    public String getIndctYn() {
        return indctYn;
    }

    /**
     * indctYn을 설정
     * 
     * @param indctYn 을(를) String indctYn로 설정
     */
    public void setIndctYn(String indctYn) {
        this.indctYn = indctYn;
    }

    /**
     * 사용여부를 설정
     * 
     * @param useYn을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * String 사용여부를 반환
     * 
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * 등록일시를 설정
     * 
     * @param regDt을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    /**
     * Date 등록일시를 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * 수정일시를 설정
     * 
     * @param updtDt을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * Date 수정일시를 반환
     * 
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * 매뉴얼을 설정
     * 
     * @param menuDtlExpln을(를) String menuDtlExpln로 설정
     */
    public void setMenuDtlExpln(String menuDtlExpln) {
        this.menuDtlExpln = menuDtlExpln;
    }

    /**
     * String 매뉴얼을 반환
     * 
     * @return String menuDtlExpln
     */
    public String getMenuDtlExpln() {
        return menuDtlExpln;
    }

    /**
     * String[] authNms을 반환
     * 
     * @return String[] authNms
     */
    public String[] getAuthNms() {
        return authNms;
    }

    /**
     * authNms을 설정
     * 
     * @param authNms 을(를) String[] authNms로 설정
     */
    public void setAuthNms(String[] authNms) {
        this.authNms = authNms;
    }

    /**
     * String menuUrlAddrStr을 반환
     * 
     * @return String menuUrlAddrStr
     */
    public String getMenuUrlAddrStr() {
        return menuUrlAddrStr;
    }

    /**
     * menuUrlAddrStr을 설정
     * 
     * @param menuUrlAddrStr 을(를) String menuUrlAddrStr로 설정
     */
    public void setMenuUrlAddrStr(String menuUrlAddrStr) {
        this.menuUrlAddrStr = menuUrlAddrStr;
    }

    /**
     * String grntCdIdStr을 반환
     * 
     * @return String grntCdIdStr
     */
    public String getGrntCdIdStr() {
        return grntCdIdStr;
    }

    /**
     * grntCdIdStr을 설정
     * 
     * @param grntCdIdStr 을(를) String grntCdIdStr로 설정
     */
    public void setGrntCdIdStr(String grntCdIdStr) {
        this.grntCdIdStr = grntCdIdStr;
    }

    /**
     * String 프로그램값을 반환
     * 
     * @return String progrmVal
     */
    public String getProgrmVal() {
        return progrmVal;
    }

    /**
     * 프로그램값을 설정
     * 
     * @param progrmVal 을(를) String progrmVal로 설정
     */
    public void setProgrmVal(String progrmVal) {
        this.progrmVal = progrmVal;
    }

    /**
     * List<String> 리퀘스트매핑URL을 반환
     * 
     * @return List<String> ctrlReqUrl
     */
    public List<String> getCtrlReqUrl() {
        return ctrlReqUrl;
    }

    /**
     * 리퀘스트매핑URL을 설정
     * 
     * @param ctrlReqUrl 을(를) List<String> ctrlReqUrl로 설정
     */
    public void setCtrlReqUrl(List<String> ctrlReqUrl) {
        this.ctrlReqUrl = ctrlReqUrl;
    }

    /**
     * String prmttNm1을 반환
     * 
     * @return String prmttNm1
     */
    public String getPrmttNm1() {
        return prmttNm1;
    }

    /**
     * prmttNm1을 설정
     * 
     * @param prmttNm1 을(를) String prmttNm1로 설정
     */
    public void setPrmttNm1(String prmttNm1) {
        this.prmttNm1 = prmttNm1;
    }

    /**
     * String prmttNm2을 반환
     * 
     * @return String prmttNm2
     */
    public String getPrmttNm2() {
        return prmttNm2;
    }

    /**
     * prmttNm2을 설정
     * 
     * @param prmttNm2 을(를) String prmttNm2로 설정
     */
    public void setPrmttNm2(String prmttNm2) {
        this.prmttNm2 = prmttNm2;
    }

    /**
     * String prmttNm3을 반환
     * 
     * @return String prmttNm3
     */
    public String getPrmttNm3() {
        return prmttNm3;
    }

    /**
     * prmttNm3을 설정
     * 
     * @param prmttNm3 을(를) String prmttNm3로 설정
     */
    public void setPrmttNm3(String prmttNm3) {
        this.prmttNm3 = prmttNm3;
    }

    /**
     * String mpgUrlYn을 반환
     * 
     * @return String mpgUrlYn
     */
    public String getMpgUrlYn() {
        return mpgUrlYn;
    }

    /**
     * mpgUrlYn을 설정
     * 
     * @param mpgUrlYn 을(를) String mpgUrlYn로 설정
     */
    public void setMpgUrlYn(String mpgUrlYn) {
        this.mpgUrlYn = mpgUrlYn;
    }

    /**
     * String delimiter을 반환
     * 
     * @return String delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * delimiter을 설정
     * 
     * @param delimiter 을(를) String delimiter로 설정
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * String homeStr을 반환
     * 
     * @return String homeStr
     */
    public String getHomeStr() {
        return homeStr;
    }

    /**
     * homeStr을 설정
     * 
     * @param homeStr 을(를) String homeStr로 설정
     */
    public void setHomeStr(String homeStr) {
        this.homeStr = homeStr;
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
