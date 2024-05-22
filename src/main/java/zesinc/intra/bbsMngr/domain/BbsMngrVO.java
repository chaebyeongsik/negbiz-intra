/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsMngr.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 게시판설정 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-20.    방기배   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsMngrVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 2379616219838017745L;
    /** 게시판코드 */
    @Required
    @Digits
    private Integer bbsSn;

    /** 게시판설정일련번호 */
    @Required
    @Digits
    private Integer bbsStngSn;

    /** 게시판환경설정명 */
    private String bbsStngNm;

    /** 게시판환경설정설명 */
    private String bbsStngExpln;

    /** 게시판명 */
    @Required
    @MaxLength(max = 256)
    private String bbsNm;

    /** 게시판설명 */
    @MaxLength(max = 4000)
    private String bbsExpln;

    /** 공지사용여부 */
    private String ntcUseYn;

    /** 분류사용여부 */
    private String clsfUseYn;

    /** FEED사용여부 */
    private String nfeedUseYn;

    /** 게시사용여부 */
    private String pstgUseYn;

    /** 개인정보수집여부 */
    private String prvcClctAgreYn;
    /** 개인정보삭제구분 */
    private String prvcDelSeCd;
    /** 개인정보삭제일 */
    private String prvcDelYmd;
    /** 개인정보보유연한 */
    private Integer prvcStrgDayCnt;

    /** 정렬순서 */
    @Digits
    private Integer sortSn;

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

    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /** 게시판코드 다중코드 */
    private String bbsSns;

    /** 도메인코드 */
    private String siteSns;

    /** 도메인코드명 */
    private String siteSnNm;

    /** 컬럼ID */
    private String colId;

    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 분류명 */
    private String[] clsfNms;
    /** 하위분류명 */
    private String[] lwrkClsfNms;

    /** 분류명 */
    private List<BbsCtgryVO> clNmList;

    /** 게시글수 */
    private String bbscttCo;
    /** 의견글수 */
    private String opatclCo;
    /** 파일수 */
    private String fileCo;

    /*
     * -----------------------------------------------------------
     * SITE_SN_CONFIG
     */
    /** 목록스킨 */
    private Integer siteSn;
    /** 목록스킨 */
    private String lstTmpltNm;
    /** 읽기스킨 */
    private String pstTmpltNm;
    /** 폼스킨 */
    private String inptTmpltNm;

    /**
     * 게시판코드 설정
     *
     * @param bbsSn을(를) Integer bbsSn로 설정
     */
    public void setBbsSn(Integer bbsSn) {
        this.bbsSn = bbsSn;
    }

    /**
     * 게시판코드 반환
     *
     * @return Integer bbsSn
     */
    public Integer getBbsSn() {
        return bbsSn;
    }

    /**
     * 게시판설정일련번호 설정
     *
     * @param bbsStngSn을(를) Integer bbsStngSn로 설정
     */
    public void setBbsStngSn(Integer bbsStngSn) {
        this.bbsStngSn = bbsStngSn;
    }

    /**
     * 게시판설정일련번호 반환
     *
     * @return Integer bbsStngSn
     */
    public Integer getBbsStngSn() {
        return bbsStngSn;
    }


    /**
     * String bbsStngNm을 반환
     * @return String bbsStngNm
     */
    public String getBbsStngNm() {
        return bbsStngNm;
    }


    /**
     * bbsStngNm을 설정
     * @param bbsStngNm 을(를) String bbsStngNm로 설정
     */
    public void setBbsStngNm(String bbsStngNm) {
        this.bbsStngNm = bbsStngNm;
    }


    /**
     * String bbsStngExpln을 반환
     * @return String bbsStngExpln
     */
    public String getBbsStngExpln() {
        return bbsStngExpln;
    }


    /**
     * bbsStngExpln을 설정
     * @param bbsStngExpln 을(를) String bbsStngExpln로 설정
     */
    public void setBbsStngExpln(String bbsStngExpln) {
        this.bbsStngExpln = bbsStngExpln;
    }

    /**
     * 게시판명 설정
     *
     * @param bbsNm을(를) String bbsNm로 설정
     */
    public void setBbsNm(String bbsNm) {
        this.bbsNm = bbsNm;
    }

    /**
     * 게시판명 반환
     *
     * @return String bbsNm
     */
    public String getBbsNm() {
        return bbsNm;
    }

    /**
     * 게시판설명 설정
     *
     * @param bbsExpln을(를) String bbsExpln로 설정
     */
    public void setBbsExpln(String bbsExpln) {
        this.bbsExpln = bbsExpln;
    }

    /**
     * 게시판설명 반환
     *
     * @return String bbsExpln
     */
    public String getBbsExpln() {
        return bbsExpln;
    }

    /**
     * 공지사용여부 설정
     *
     * @param ntcUseYn을(를) String ntcUseYn로 설정
     */
    public void setNtcUseYn(String ntcUseYn) {
        this.ntcUseYn = ntcUseYn;
    }

    /**
     * 공지사용여부 반환
     *
     * @return String ntcUseYn
     */
    public String getNtcUseYn() {
        return ntcUseYn;
    }

    /**
     * 분류사용여부 설정
     *
     * @param clsfUseYn을(를) String clsfUseYn로 설정
     */
    public void setClsfUseYn(String clsfUseYn) {
        this.clsfUseYn = clsfUseYn;
    }

    /**
     * 분류사용여부 반환
     *
     * @return String clsfUseYn
     */
    public String getClsfUseYn() {
        return clsfUseYn;
    }

    /**
     * FEED사용여부 설정
     *
     * @param nfeedUseYn을(를) String nfeedUseYn로 설정
     */
    public void setNfeedUseYn(String nfeedUseYn) {
        this.nfeedUseYn = nfeedUseYn;
    }

    /**
     * FEED사용여부 반환
     *
     * @return String nfeedUseYn
     */
    public String getNfeedUseYn() {
        return nfeedUseYn;
    }

    /**
     * String pstgUseYn을 반환
     * @return String pstgUseYn
     */
    public String getPstgUseYn() {
        return pstgUseYn;
    }

    /**
     * pstgUseYn을 설정
     * @param pstgUseYn 을(를) String pstgUseYn로 설정
     */
    public void setPstgUseYn(String pstgUseYn) {
        this.pstgUseYn = pstgUseYn;
    }

    /**
     * 개인정보수집여부 반환
     *
     * @return String prvcClctAgreYn
     */
    public String getPrvcClctAgreYn() {
        return prvcClctAgreYn;
    }

    /**
     * 개인정보수집여부 설정
     *
     * @param prvcClctAgreYn 을(를) String prsnInfoColctAt로 설정
     */
    public void setPrvcClctAgreYn(String prvcClctAgreYn) {
        this.prvcClctAgreYn = prvcClctAgreYn;
    }

    /**
     * 개인정보삭제구분 반환
     *
     * @return String prvcDelSeCd
     */
    public String getPrvcDelSeCd() {
        return prvcDelSeCd;
    }

    /**
     * 개인정보삭제구분 설정
     *
     * @param prvcDelSeCd 을(를) String prvcDelSeCd로 설정
     */
    public void setPrvcDelSeCd(String prvcDelSeCd) {
        this.prvcDelSeCd = prvcDelSeCd;
    }

    /**
     * 개인정보삭제일 반환
     *
     * @return String prvcDelYmd
     */
    public String getPrvcDelYmd() {
        return prvcDelYmd;
    }

    /**
     * 개인정보삭제일 설정
     *
     * @param prvcDelYmd 을(를) String prvcDelYmd로 설정
     */
    public void setPrvcDelYmd(String prvcDelYmd) {
        this.prvcDelYmd = prvcDelYmd;
    }

    /**
     * 개인정보보유연한 반환
     *
     * @return Integer prvcStrgDayCnt
     */
    public Integer getPrvcStrgDayCnt() {
        return prvcStrgDayCnt;
    }

    /**
     * 개인정보보유연한 설정
     *
     * @param prvcStrgDayCnt 을(를) Integer prvcStrgDayCnt로 설정
     */
    public void setPrvcStrgDayCnt(Integer prvcStrgDayCnt) {
        this.prvcStrgDayCnt = prvcStrgDayCnt;
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
     * String siteSns을 반환
     *
     * @return String siteSns
     */
    public String getSiteSns() {
        return siteSns;
    }

    /**
     * siteSns을 설정
     *
     * @param siteSns 을(를) String siteSns로 설정
     */
    public void setSiteSns(String siteSns) {
        this.siteSns = siteSns;
    }

    /**
     * String siteSnNm을 반환
     *
     * @return String siteSnNm
     */
    public String getSiteSnNm() {
        return siteSnNm;
    }

    /**
     * siteSnNm을 설정
     *
     * @param siteSnNm 을(를) String siteSnNm로 설정
     */
    public void setSiteSnNm(String siteSnNm) {
        this.siteSnNm = siteSnNm;
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
     * List<FileVO> fileList을 반환
     *
     * @return List<FileVO> fileList
     */
    public List<FileVO> getFileList() {
        return fileList;
    }

    /**
     * fileList을 설정
     *
     * @param fileList 을(를) List<FileVO> fileList로 설정
     */
    public void setFileList(List<FileVO> fileList) {
        this.fileList = fileList;
    }

    /**
     * String[] clsfNms을 반환
     *
     * @return String[] clsfNms
     */
    public String[] getClsfNms() {
        return clsfNms;
    }

    /**
     * clsfNms을 설정
     *
     * @param clsfNms 을(를) String[] clsfNms로 설정
     */
    public void setClsfNms(String[] clsfNms) {
        this.clsfNms = clsfNms;
    }

    /**
     * String[] lwrkClsfNms을 반환
     *
     * @return String[] lwrkClsfNms
     */
    public String[] getLwrkClsfNms() {
        return lwrkClsfNms;
    }

    /**
     * lwrkClsfNms을 설정
     *
     * @param lwrkClsfNms 을(를) String[] lwrkClsfNms로 설정
     */
    public void setLwrkClsfNms(String[] lwrkClsfNms) {
        this.lwrkClsfNms = lwrkClsfNms;
    }

    /**
     * List<BbsCtgryVO> clNmList을 반환
     *
     * @return List<BbsCtgryVO> clNmList
     */
    public List<BbsCtgryVO> getClNmList() {
        return clNmList;
    }

    /**
     * clNmList을 설정
     *
     * @param clNmList 을(를) List<BbsCtgryVO> clNmList로 설정
     */
    public void setClNmList(List<BbsCtgryVO> clNmList) {
        this.clNmList = clNmList;
    }

    /**
     * String bbscttCo을 반환
     *
     * @return String bbscttCo
     */
    public String getBbscttCo() {
        return bbscttCo;
    }

    /**
     * bbscttCo을 설정
     *
     * @param bbscttCo 을(를) String bbscttCo로 설정
     */
    public void setBbscttCo(String bbscttCo) {
        this.bbscttCo = bbscttCo;
    }

    /**
     * String opatclCo을 반환
     *
     * @return String opatclCo
     */
    public String getOpatclCo() {
        return opatclCo;
    }

    /**
     * opatclCo을 설정
     *
     * @param opatclCo 을(를) String opatclCo로 설정
     */
    public void setOpatclCo(String opatclCo) {
        this.opatclCo = opatclCo;
    }

    /**
     * String fileCo을 반환
     *
     * @return String fileCo
     */
    public String getFileCo() {
        return fileCo;
    }

    /**
     * fileCo을 설정
     *
     * @param fileCo 을(를) String fileCo로 설정
     */
    public void setFileCo(String fileCo) {
        this.fileCo = fileCo;
    }

    /**
     * String bbsSns을 반환
     *
     * @return String bbsSns
     */
    public String getBbsSns() {
        return bbsSns;
    }

    /**
     * bbsSns을 설정
     *
     * @param bbsSns 을(를) String bbsSns로 설정
     */
    public void setBbsSns(String bbsSns) {
        this.bbsSns = bbsSns;
    }

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
     * String lstTmpltNm을 반환
     *
     * @return String lstTmpltNm
     */
    public String getLstTmpltNm() {
        return lstTmpltNm;
    }

    /**
     * lstTmpltNm을 설정
     *
     * @param lstTmpltNm 을(를) String lstTmpltNm로 설정
     */
    public void setLstTmpltNm(String lstTmpltNm) {
        this.lstTmpltNm = lstTmpltNm;
    }

    /**
     * String pstTmpltNm을 반환
     *
     * @return String pstTmpltNm
     */
    public String getPstTmpltNm() {
        return pstTmpltNm;
    }

    /**
     * pstTmpltNm을 설정
     *
     * @param pstTmpltNm 을(를) String pstTmpltNm로 설정
     */
    public void setPstTmpltNm(String pstTmpltNm) {
        this.pstTmpltNm = pstTmpltNm;
    }

    /**
     * String inptTmpltNm을 반환
     *
     * @return String inptTmpltNm
     */
    public String getInptTmpltNm() {
        return inptTmpltNm;
    }

    /**
     * inptTmpltNm을 설정
     *
     * @param inptTmpltNm 을(를) String inptTmpltNm로 설정
     */
    public void setInptTmpltNm(String inptTmpltNm) {
        this.inptTmpltNm = inptTmpltNm;
    }

}
