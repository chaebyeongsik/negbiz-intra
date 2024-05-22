/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.RangeLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 게시판환경설정 정보 VO 클레스.
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
public class BbsSetupVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -8399665239110100344L;

    /** 게시판환경설정일련번호 */
    @Required
    @Digits
    private Integer bbsStngSn;

    /** 게시판환경설정명 */
    @Required
    @RangeLength(max = 100)
    private String bbsStngNm;

    /** 게시판환경설정설명 */
    @RangeLength(max = 4000)
    private String bbsStngExpln;

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

    /** 설정구분코드 */
    private Integer seCdId;

    /** 게시판환경설정일련번호 */
    private String bbsStngSns;

    /** 항목관리 목록 */
    private List<BbsSetupItemVO> bbsItemList;

    /*
     * -----------------------------------------------------------
     * GLOBAL
     */
    /** 계층형 사용여부 */
    private String lyrUseYn = "N";
    /** 썸네일 사용여부 */
    private String thmbUseYn = "N";
    /** 가로사이즈 */
    private Integer wdthSz;
    /** 세로사이즈 */
    private Integer vrtcSz;
    /** 마크사용여부 */
    private String wtmkUseYn;
    /** 마크파일 */
    private String wtmkFileNm;
    /** 신규마크파일 */
    private String newWtmkFileNm;
    /** 마크위치 */
    private String wtmkPstnNm;
    /** 마크투명 */
    private String wtmkTrnspc;

    /*
     * -----------------------------------------------------------
     * LIST
     */
    /** 페이지당표시글수 */
    private Integer pagePstCnt = 10;
    /** 제목조정길이 */
    private Integer ttlAjmtSz = 20;
    /** 새글표시기준일 */
    private Integer newIndctDayCnt = 7;
    /** 게시글강조조회수 */
    private Integer pstEmphsInqCnt = 50;
    /** 작성자표시코드 */
    private Integer wrtrIndctSn;

    /*
     * -----------------------------------------------------------
     * VIEW
     */
    /** 글이동(이전/다음글)표시여부 */
    private String bfrAftrDocIndctYn = "N";
    /** 추천사용여부 */
    private String rcmdtnUseYn = "N";
    /** 신고사용여부 */
    private String dclrUseYn = "N";
    /** 만족도사용여부 */
    private String dgstfnUseYn = "N";
    /** 의견글사용여부 */
    private String opnnDocYn = "N";
    /** 태그사용여부 */
    private String tagUseYn = "N";
    /** 조회수증가제한시간 */
    private Integer inqCntLmtHrCnt = 3;

    /*
     * -----------------------------------------------------------
     * FORM
     */
    /** 관리자에디터사용여부 */
    private String mngrEdtrUseYn = "N";
    /** 사용자에디터사용여부 */
    private String userEdtrUseYn = "N";
    /** 첨부파일사용여부 */
    private String atchFileUseYn = "N";
    /** 업로더종류 */
    private Integer uldClsfSn = 1000;
    /** 최대업로드파일수 */
    private Integer fileLmtCnt;
    /** 최대파일사이즈 */
    private Integer lmtFileSz;
    /** 전체업로드사이즈 */
    private Integer wholUldSz;
    /** 첨부파일허용확장자 */
    private String prmsnFileExtnMttr;
    /** CAPTCHA사용여부 */
    private String cchaUseYn = "N";
    /** 공개여부 */
    private String rlsYn = "Y";
    /** 금지단어사용여부 */
    private String phbwdUseYn = "N";
    /** 금지단어코드 : 금지단어관리 기능 참조 */
    private String phbwdCdId;

    /*
     * -----------------------------------------------------------
     * ITEM
     */
    /** 컬럼ID */
    private String colId;
    /** 컬럼명 */
    private String colNm;
    /** 컬럼명 */
    private String scrnNm;
    /** 컬럼유형 */
    private String colTypeNm;
    /** 안내문구 */
    private String bbsColExpln;
    /** 검색조건여부 */
    private String srchStngYn = "N";
    /** 검색유형 */
    private String srchType;
    /** 필수여부 */
    private String esntlYn;
    /** 목록표시여부 */
    private String lstIndctYn;
    /** 읽기표시여부 */
    private String inqIndctYn;
    /** 폼표시여부 */
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
     * String bbsStngNm을 반환
     * 
     * @return String bbsStngNm
     */
    public String getBbsStngNm() {
        return bbsStngNm;
    }

    /**
     * bbsStngNm을 설정
     * 
     * @param bbsStngNm 을(를) String bbsStngNm로 설정
     */
    public void setBbsStngNm(String bbsStngNm) {
        this.bbsStngNm = bbsStngNm;
    }

    /**
     * String bbsStngExpln을 반환
     * 
     * @return String bbsStngExpln
     */
    public String getBbsStngExpln() {
        return bbsStngExpln;
    }

    /**
     * bbsStngExpln을 설정
     * 
     * @param bbsStngExpln 을(를) String bbsStngExpln로 설정
     */
    public void setBbsStngExpln(String bbsStngExpln) {
        this.bbsStngExpln = bbsStngExpln;
    }

    /**
     * String useYn을 반환
     *
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * useYn을 설정
     *
     * @param useYn 을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * String rgtrId을 반환
     * 
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * rgtrId을 설정
     * 
     * @param rgtrId 을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
    }

    /**
     * String rgtrNm을 반환
     * 
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * rgtrNm을 설정
     * 
     * @param rgtrNm 을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * Date regDt을 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     * 
     * @param regDt 을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    /**
     * String mdfrId을 반환
     * 
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * mdfrId을 설정
     * 
     * @param mdfrId 을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * String updusrNm을 반환
     * 
     * @return String updusrNm
     */
    public String getUpdusrNm() {
        return updusrNm;
    }

    /**
     * updusrNm을 설정
     * 
     * @param updusrNm 을(를) String updusrNm로 설정
     */
    public void setUpdusrNm(String updusrNm) {
        this.updusrNm = updusrNm;
    }

    /**
     * Date updtDt을 반환
     * 
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * updtDt을 설정
     * 
     * @param updtDt 을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * Integer seCdId을 반환
     * 
     * @return Integer seCdId
     */
    public Integer getSeCdId() {
        return seCdId;
    }

    /**
     * seCdId을 설정
     * 
     * @param seCdId 을(를) Integer seCdId로 설정
     */
    public void setSeCdId(Integer seCdId) {
        this.seCdId = seCdId;
    }

    /**
     * String bbsStngSns을 반환
     * 
     * @return String bbsStngSns
     */
    public String getBbsStngSns() {
        return bbsStngSns;
    }

    /**
     * bbsStngSns을 설정
     * 
     * @param bbsStngSns 을(를) String bbsStngSns로 설정
     */
    public void setBbsStngSns(String bbsStngSns) {
        this.bbsStngSns = bbsStngSns;
    }

    /**
     * List<BbsItemVO> bbsItemList을 반환
     *
     * @return List<BbsItemVO> bbsItemList
     */
    public List<BbsSetupItemVO> getBbsItemList() {
        return bbsItemList;
    }

    /**
     * bbsItemList을 설정
     *
     * @param bbsItemList 을(를) List<BbsItemVO> bbsItemList로 설정
     */
    public void setBbsItemList(List<BbsSetupItemVO> bbsItemList) {
        this.bbsItemList = bbsItemList;
    }

    /**
     * String lyrUseYn을 반환
     *
     * @return String lyrUseYn
     */
    public String getLyrUseYn() {
        return lyrUseYn;
    }

    /**
     * lyrUseYn을 설정
     *
     * @param lyrUseYn 을(를) String lyrUseYn로 설정
     */
    public void setLyrUseYn(String lyrUseYn) {
        this.lyrUseYn = lyrUseYn;
    }

    /**
     * String thmbUseYn을 반환
     *
     * @return String thmbUseYn
     */
    public String getThmbUseYn() {
        return thmbUseYn;
    }

    /**
     * thmbUseYn을 설정
     *
     * @param thmbUseYn 을(를) String thmbUseYn로 설정
     */
    public void setThmbUseYn(String thmbUseYn) {
        this.thmbUseYn = thmbUseYn;
    }

    /**
     * Integer wdthSz을 반환
     *
     * @return Integer wdthSz
     */
    public Integer getWdthSz() {
        return wdthSz;
    }

    /**
     * wdthSz을 설정
     *
     * @param wdthSz 을(를) Integer wdthSz로 설정
     */
    public void setWdthSz(Integer wdthSz) {
        this.wdthSz = wdthSz;
    }

    /**
     * Integer vrtcSz을 반환
     *
     * @return Integer vrtcSz
     */
    public Integer getVrtcSz() {
        return vrtcSz;
    }

    /**
     * vrtcSz을 설정
     *
     * @param vrtcSz 을(를) Integer vrtcSz로 설정
     */
    public void setVrtcSz(Integer vrtcSz) {
        this.vrtcSz = vrtcSz;
    }

    /**
     * String wtmkUseYn을 반환
     *
     * @return String wtmkUseYn
     */
    public String getWtmkUseYn() {
        return wtmkUseYn;
    }

    /**
     * wtmkUseYn을 설정
     *
     * @param wtmkUseYn 을(를) String wtmkUseYn로 설정
     */
    public void setWtmkUseYn(String wtmkUseYn) {
        this.wtmkUseYn = wtmkUseYn;
    }

    /**
     * String wtmkFileNm을 반환
     *
     * @return String wtmkFileNm
     */
    public String getWtmkFileNm() {
        return wtmkFileNm;
    }

    /**
     * wtmkFileNm을 설정
     *
     * @param wtmkFileNm 을(를) String wtmkFileNm로 설정
     */
    public void setWtmkFileNm(String wtmkFileNm) {
        this.wtmkFileNm = wtmkFileNm;
    }

    /**
     * String newWtmkFileNm을 반환
     *
     * @return String newWtmkFileNm
     */
    public String getNewWtmkFileNm() {
        return newWtmkFileNm;
    }

    /**
     * newWtmkFileNm을 설정
     *
     * @param newWtmkFileNm 을(를) String newWtmkFileNm로 설정
     */
    public void setNewWtmkFileNm(String newWtmkFileNm) {
        this.newWtmkFileNm = newWtmkFileNm;
    }

    /**
     * String wtmkPstnNm을 반환
     *
     * @return String wtmkPstnNm
     */
    public String getWtmkPstnNm() {
        return wtmkPstnNm;
    }

    /**
     * wtmkPstnNm을 설정
     *
     * @param wtmkPstnNm 을(를) String wtmkPstnNm로 설정
     */
    public void setWtmkPstnNm(String wtmkPstnNm) {
        this.wtmkPstnNm = wtmkPstnNm;
    }

    /**
     * String wtmkTrnspc을 반환
     *
     * @return String wtmkTrnspc
     */
    public String getWtmkTrnspc() {
        return wtmkTrnspc;
    }

    /**
     * wtmkTrnspc을 설정
     *
     * @param wtmkTrnspc 을(를) String wtmkTrnspc로 설정
     */
    public void setWtmkTrnspc(String wtmkTrnspc) {
        this.wtmkTrnspc = wtmkTrnspc;
    }

    /**
     * Integer pagePstCnt을 반환
     *
     * @return Integer pagePstCnt
     */
    public Integer getPagePstCnt() {
        return pagePstCnt;
    }

    /**
     * pagePstCnt을 설정
     *
     * @param pagePstCnt 을(를) Integer pagePstCnt로 설정
     */
    public void setPagePstCnt(Integer pagePstCnt) {
        this.pagePstCnt = pagePstCnt;
    }

    /**
     * Integer ttlAjmtSz을 반환
     *
     * @return Integer ttlAjmtSz
     */
    public Integer getTtlAjmtSz() {
        return ttlAjmtSz;
    }

    /**
     * ttlAjmtSz을 설정
     *
     * @param ttlAjmtSz 을(를) Integer ttlAjmtSz로 설정
     */
    public void setTtlAjmtSz(Integer ttlAjmtSz) {
        this.ttlAjmtSz = ttlAjmtSz;
    }

    /**
     * Integer newIndctDayCnt을 반환
     *
     * @return Integer newIndctDayCnt
     */
    public Integer getNewIndctDayCnt() {
        return newIndctDayCnt;
    }

    /**
     * newIndctDayCnt을 설정
     *
     * @param newIndctDayCnt 을(를) Integer newIndctDayCnt로 설정
     */
    public void setNewIndctDayCnt(Integer newIndctDayCnt) {
        this.newIndctDayCnt = newIndctDayCnt;
    }

    /**
     * Integer pstEmphsInqCnt을 반환
     *
     * @return Integer pstEmphsInqCnt
     */
    public Integer getPstEmphsInqCnt() {
        return pstEmphsInqCnt;
    }

    /**
     * pstEmphsInqCnt을 설정
     *
     * @param pstEmphsInqCnt 을(를) Integer pstEmphsInqCnt로 설정
     */
    public void setPstEmphsInqCnt(Integer pstEmphsInqCnt) {
        this.pstEmphsInqCnt = pstEmphsInqCnt;
    }

    /**
     * Integer wrtrIndctSn을 반환
     *
     * @return Integer wrtrIndctSn
     */
    public Integer getWrtrIndctSn() {
        return wrtrIndctSn;
    }

    /**
     * wrtrIndctSn을 설정
     *
     * @param wrtrIndctSn 을(를) Integer wrtrIndctSn로 설정
     */
    public void setWrtrIndctSn(Integer wrtrIndctSn) {
        this.wrtrIndctSn = wrtrIndctSn;
    }

    /**
     * String bfrAftrDocIndctYn을 반환
     *
     * @return String bfrAftrDocIndctYn
     */
    public String getBfrAftrDocIndctYn() {
        return bfrAftrDocIndctYn;
    }

    /**
     * bfrAftrDocIndctYn을 설정
     *
     * @param bfrAftrDocIndctYn 을(를) String bfrAftrDocIndctYn로 설정
     */
    public void setBfrAftrDocIndctYn(String bfrAftrDocIndctYn) {
        this.bfrAftrDocIndctYn = bfrAftrDocIndctYn;
    }

    /**
     * String rcmdtnUseYn을 반환
     *
     * @return String rcmdtnUseYn
     */
    public String getRcmdtnUseYn() {
        return rcmdtnUseYn;
    }

    /**
     * rcmdtnUseYn을 설정
     *
     * @param rcmdtnUseYn 을(를) String rcmdtnUseYn로 설정
     */
    public void setRcmdtnUseYn(String rcmdtnUseYn) {
        this.rcmdtnUseYn = rcmdtnUseYn;
    }

    /**
     * String dclrUseYn을 반환
     *
     * @return String dclrUseYn
     */
    public String getDclrUseYn() {
        return dclrUseYn;
    }

    /**
     * dclrUseYn을 설정
     *
     * @param dclrUseYn 을(를) String dclrUseYn로 설정
     */
    public void setDclrUseYn(String dclrUseYn) {
        this.dclrUseYn = dclrUseYn;
    }

    /**
     * String dgstfnUseYn을 반환
     *
     * @return String dgstfnUseYn
     */
    public String getDgstfnUseYn() {
        return dgstfnUseYn;
    }

    /**
     * dgstfnUseYn을 설정
     *
     * @param dgstfnUseYn 을(를) String dgstfnUseYn로 설정
     */
    public void setDgstfnUseYn(String dgstfnUseYn) {
        this.dgstfnUseYn = dgstfnUseYn;
    }

    /**
     * String opnnDocYn을 반환
     *
     * @return String opnnDocYn
     */
    public String getOpnnDocYn() {
        return opnnDocYn;
    }

    /**
     * opnnDocYn을 설정
     *
     * @param opnnDocYn 을(를) String opnnDocYn로 설정
     */
    public void setOpnnDocYn(String opnnDocYn) {
        this.opnnDocYn = opnnDocYn;
    }

    /**
     * String tagUseYn을 반환
     *
     * @return String tagUseYn
     */
    public String getTagUseYn() {
        return tagUseYn;
    }

    /**
     * tagUseYn을 설정
     *
     * @param tagUseYn 을(를) String tagUseYn로 설정
     */
    public void setTagUseYn(String tagUseYn) {
        this.tagUseYn = tagUseYn;
    }

    /**
     * Integer inqCntLmtHrCnt을 반환
     *
     * @return Integer inqCntLmtHrCnt
     */
    public Integer getInqCntLmtHrCnt() {
        return inqCntLmtHrCnt;
    }

    /**
     * inqCntLmtHrCnt을 설정
     *
     * @param inqCntLmtHrCnt 을(를) Integer inqCntLmtHrCnt로 설정
     */
    public void setInqCntLmtHrCnt(Integer inqCntLmtHrCnt) {
        this.inqCntLmtHrCnt = inqCntLmtHrCnt;
    }

    /**
     * String mngrEdtrUseYn을 반환
     *
     * @return String mngrEdtrUseYn
     */
    public String getMngrEdtrUseYn() {
        return mngrEdtrUseYn;
    }

    /**
     * mngrEdtrUseYn을 설정
     *
     * @param mngrEdtrUseYn 을(를) String mngrEdtrUseYn로 설정
     */
    public void setMngrEdtrUseYn(String mngrEdtrUseYn) {
        this.mngrEdtrUseYn = mngrEdtrUseYn;
    }

    /**
     * String atchFileUseYn을 반환
     *
     * @return String atchFileUseYn
     */
    public String getAtchFileUseYn() {
        return atchFileUseYn;
    }

    /**
     * atchFileUseYn을 설정
     *
     * @param atchFileUseYn 을(를) String atchFileUseYn로 설정
     */
    public void setAtchFileUseYn(String atchFileUseYn) {
        this.atchFileUseYn = atchFileUseYn;
    }

    /**
     * Integer uldClsfSn을 반환
     *
     * @return Integer uldClsfSn
     */
    public Integer getUldClsfSn() {
        return uldClsfSn;
    }

    /**
     * uldClsfSn을 설정
     *
     * @param uldClsfSn 을(를) Integer uldClsfSn로 설정
     */
    public void setUldClsfSn(Integer uldClsfSn) {
        this.uldClsfSn = uldClsfSn;
    }

    /**
     * Integer fileLmtCnt을 반환
     *
     * @return Integer fileLmtCnt
     */
    public Integer getFileLmtCnt() {
        return fileLmtCnt;
    }

    /**
     * fileLmtCnt을 설정
     *
     * @param fileLmtCnt 을(를) Integer fileLmtCnt로 설정
     */
    public void setFileLmtCnt(Integer fileLmtCnt) {
        this.fileLmtCnt = fileLmtCnt;
    }

    /**
     * Integer lmtFileSz을 반환
     *
     * @return Integer lmtFileSz
     */
    public Integer getLmtFileSz() {
        return lmtFileSz;
    }

    /**
     * lmtFileSz을 설정
     *
     * @param lmtFileSz 을(를) Integer lmtFileSz로 설정
     */
    public void setLmtFileSz(Integer lmtFileSz) {
        this.lmtFileSz = lmtFileSz;
    }

    /**
     * Integer wholUldSz을 반환
     *
     * @return Integer wholUldSz
     */
    public Integer getWholUldSz() {
        return wholUldSz;
    }

    /**
     * wholUldSz을 설정
     *
     * @param wholUldSz 을(를) Integer wholUldSz로 설정
     */
    public void setWholUldSz(Integer wholUldSz) {
        this.wholUldSz = wholUldSz;
    }

    /**
     * String prmsnFileExtnMttr을 반환
     *
     * @return String prmsnFileExtnMttr
     */
    public String getPrmsnFileExtnMttr() {
        return prmsnFileExtnMttr;
    }

    /**
     * prmsnFileExtnMttr을 설정
     *
     * @param prmsnFileExtnMttr 을(를) String prmsnFileExtnMttr로 설정
     */
    public void setPrmsnFileExtnMttr(String prmsnFileExtnMttr) {
        this.prmsnFileExtnMttr = prmsnFileExtnMttr;
    }

    /**
     * String cchaUseYn을 반환
     *
     * @return String cchaUseYn
     */
    public String getCchaUseYn() {
        return cchaUseYn;
    }

    /**
     * cchaUseYn을 설정
     *
     * @param cchaUseYn 을(를) String cchaUseYn로 설정
     */
    public void setCchaUseYn(String cchaUseYn) {
        this.cchaUseYn = cchaUseYn;
    }

    /**
     * String rlsYn을 반환
     *
     * @return String rlsYn
     */
    public String getRlsYn() {
        return rlsYn;
    }

    /**
     * rlsYn을 설정
     *
     * @param rlsYn 을(를) String rlsYn로 설정
     */
    public void setRlsYn(String rlsYn) {
        this.rlsYn = rlsYn;
    }

    /**
     * String phbwdUseYn을 반환
     *
     * @return String phbwdUseYn
     */
    public String getPhbwdUseYn() {
        return phbwdUseYn;
    }

    /**
     * phbwdUseYn을 설정
     *
     * @param phbwdUseYn 을(를) String phbwdUseYn로 설정
     */
    public void setPhbwdUseYn(String phbwdUseYn) {
        this.phbwdUseYn = phbwdUseYn;
    }

    /**
     * String phbwdCdId을 반환
     *
     * @return String phbwdCdId
     */
    public String getPhbwdCdId() {
        return phbwdCdId;
    }

    /**
     * phbwdCdId을 설정
     *
     * @param phbwdCdId 을(를) String phbwdCdId로 설정
     */
    public void setPhbwdCdId(String phbwdCdId) {
        this.phbwdCdId = phbwdCdId;
    }

    /**
     * String userEdtrUseYn을 반환
     *
     * @return String userEdtrUseYn
     */
    public String getUserEdtrUseYn() {
        return userEdtrUseYn;
    }

    /**
     * userEdtrUseYn을 설정
     *
     * @param userEdtrUseYn 을(를) String userEdtrUseYn로 설정
     */
    public void setUserEdtrUseYn(String userEdtrUseYn) {
        this.userEdtrUseYn = userEdtrUseYn;
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
     * String srchStngYn을 반환
     *
     * @return String srchStngYn
     */
    public String getSrchStngYn() {
        return srchStngYn;
    }

    /**
     * srchStngYn을 설정
     *
     * @param srchStngYn 을(를) String srchStngYn로 설정
     */
    public void setSrchStngYn(String srchStngYn) {
        this.srchStngYn = srchStngYn;
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
