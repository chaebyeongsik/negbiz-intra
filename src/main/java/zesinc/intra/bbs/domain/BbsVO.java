/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs.domain;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 게시판 정보 VO 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-08.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -974978647617739544L;
    /** 게시판코드 */
    @Required
    private Integer bbsSn;
    /** 게시판명 */
    private String bbsNm;
    /** 게시글일련번호 */
    private String bbsDocNo;
    /** 참조일련번호 */
    private String rfrncDocNo;
    /** 정렬순서 */
    @Digits
    private Integer sortSn = 0;
    /** 의견글레벨 */
    @Digits
    private Integer opnnGrdSn = 0;
    /** 분류코드 */
    private Integer clsfNo = -1;
    /** 분류명 */
    private String clsfNm;
    /** 하위분류코드 */
    private Integer lwrkClsfSn;
    /** 하위분류코드명 */
    private String lwrkClsfNm;
    /** 공지여부 */
    private String ntcPstYn = "N";
    /** 공지시작일시 */
    private String ntcBgngDt;
    /** 공지종료일시 */
    private String ntcEndDt;
    /** 게시시작일시 */
    private String pstgBgngDt;
    /** 게시종료일시 */
    private String pstgEndDt;
    /** 제목 */
    private String ttl;
    /** 장문내용 */
    @Required
    private String docContsCn;
    /** 내용요약 */
    @MaxLength(max = 4000)
    private String mainCn;
    /** 답변내용 */
    private String ansDtlCn;
    /** 답변일시 */
    private String ansDt;
    /** 파일순번 */
    private Integer fileSn = -1;
    /** 조회수 */
    private Integer inqCnt = 0;
    /** 만족도평가횟수 */
    private Integer dgstfnEvlCnt = 0;
    /** 만족도평가총합 */
    private Integer dgstfnSumScr = 0;
    /** 신고횟수 */
    private Integer dclrCnt = 0;
    /** 추천횟수 */
    private Integer rcmdtnCnt = 0;
    /** IP주소 */
    @MaxLength(max = 15)
    private String ipAddr;
    /** 에이전트 */
    @MaxLength(max = 300)
    private String cntnBrwsrNm;
    /** 공개여부 */
    private String rlsYn = "Y";
    /** 금지여부 */
    private String lmtYn = "N";
    /** 사용자키 */
    @MaxLength(max = 100)
    private String userIdntfNm;
    /** 비밀번호 */
    @MaxLength(max = 100)
    private String pswd;
    /** 담당자ID */
    @MaxLength(max = 20)
    private String picId;
    /** 담당자명 */
    @MaxLength(max = 100)
    private String picNm;
    /** 부서코드 */
    @MaxLength(max = 20)
    private String deptCdId;
    /** 부서명 */
    @MaxLength(max = 200)
    private String deptNm;

    /** 확장컬럼1 */
    @MaxLength(max = 4000)
    private String flctnColCn1;
    /** 확장컬럼2 */
    @MaxLength(max = 4000)
    private String flctnColCn2;
    /** 확장컬럼3 */
    @MaxLength(max = 4000)
    private String flctnColCn3;
    /** 확장컬럼4 */
    @MaxLength(max = 4000)
    private String flctnColCn4;
    /** 확장컬럼5 */
    @MaxLength(max = 4000)
    private String flctnColCn5;
    /** 확장컬럼6 */
    @MaxLength(max = 4000)
    private String flctnColCn6;
    /** 확장컬럼7 */
    @MaxLength(max = 4000)
    private String flctnColCn7;
    /** 확장컬럼8 */
    @MaxLength(max = 4000)
    private String flctnColCn8;
    /** 확장컬럼9 */
    @MaxLength(max = 4000)
    private String flctnColCn9;
    /** 확장컬럼10 */
    @MaxLength(max = 4000)
    private String flctnColCn10;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;
    /** 등록자명 */
    @MaxLength(max = 100)
    private String rgtrNm;
    /** 등록자연락처 */
    private String rgtrTelno;
    /** 등록자이메일 */
    private String rgtrEmlAddr;

    /** 등록일시 */
    private String regDt;
    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;
    /** 수정자명 */
    private String updusrNm;
    /** 수정일시 */
    private String updtDt;

    /** 파일 ID 목록 */
    private String[] fileIds;
    /** 첨부파일 목록 */
    private List<FileVO> fileList;

    /** 만족도평가 평균 */
    private Float stsfdgEvlAvrg;
    /** 관리자삭제여부 */
    private String mngrDelYn;
    /** 관리자표시제한여부 */
    private String mngrIndctLmtYn;
    /** 이동한게시판코드 */
    private Integer mvmnBbsSn;
    /** 이동한게시판명 */
    private String mvmnBbsNm;

    /** 삭제여부 */
    private String delYn;
    /** 삭제일시 */
    private String delDt;

    /** 저작권사용여부 */
    private String cprgtUseYn = "N";
    /** 저작자유형 */
    private String autTypeNm;
    /** 저작권유형 */
    private String cprgtTypeNm;
    /** 저작권내용 */
    private String cprgtCn;

    /** 이전글 객체 */
    private BbsVO prevVo;
    /** 다음글 객체 */
    private BbsVO nextVo;
    /** 게시판코드 다중코드 */
    private String bbsDocNos;

    /** 등록일부터 경과일 */
    private Integer passDay;

    /** 태그명 */
    private String tagNm;
    /** 다중태그 */
    private String tagNms;
    /** 태그 목록 */
    private List<String> bbsTagList;

    /** 댓글수 */
    private Integer cmntCo;

    /**
     * bbsVo 객체 복사하기(검색에 필요한 필드만 복사한다.)
     */
    public BbsVO copyCreateVO() {
        BbsVO newVo = new BbsVO();
        newVo.setBbsSn(this.getBbsSn());
        newVo.setBbsDocNo(this.getBbsDocNo());
        newVo.setRfrncDocNo(this.getRfrncDocNo());
        newVo.setSortSn(this.getSortSn());
        newVo.setOpnnGrdSn(this.getOpnnGrdSn());
        return newVo;
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
     * String bbsNm을 반환
     *
     * @return String bbsNm
     */
    public String getBbsNm() {
        return bbsNm;
    }

    /**
     * bbsNm을 설정
     *
     * @param bbsNm 을(를) String bbsNm로 설정
     */
    public void setBbsNm(String bbsNm) {
        this.bbsNm = bbsNm;
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
     * String rfrncDocNo을 반환
     *
     * @return String rfrncDocNo
     */
    public String getRfrncDocNo() {
        return rfrncDocNo;
    }

    /**
     * rfrncDocNo을 설정
     *
     * @param rfrncDocNo 을(를) String rfrncDocNo로 설정
     */
    public void setRfrncDocNo(String rfrncDocNo) {
        this.rfrncDocNo = rfrncDocNo;
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
     * Integer opnnGrdSn을 반환
     *
     * @return Integer opnnGrdSn
     */
    public Integer getOpnnGrdSn() {
        return opnnGrdSn;
    }

    /**
     * opnnGrdSn을 설정
     *
     * @param opnnGrdSn 을(를) Integer opnnGrdSn로 설정
     */
    public void setOpnnGrdSn(Integer opnnGrdSn) {
        this.opnnGrdSn = opnnGrdSn;
    }

    /**
     * Integer clsfNo을 반환
     *
     * @return Integer clsfNo
     */
    public Integer getClsfNo() {
        return clsfNo;
    }

    /**
     * clsfNo을 설정
     *
     * @param clsfNo 을(를) Integer clsfNo로 설정
     */
    public void setClsfNo(Integer clsfNo) {
        this.clsfNo = clsfNo;
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
     * Integer lwrkClsfSn을 반환
     *
     * @return Integer lwrkClsfSn
     */
    public Integer getLwrkClsfSn() {
        return lwrkClsfSn;
    }

    /**
     * lwrkClsfSn을 설정
     *
     * @param lwrkClsfSn 을(를) Integer lwrkClsfSn로 설정
     */
    public void setLwrkClsfSn(Integer lwrkClsfSn) {
        this.lwrkClsfSn = lwrkClsfSn;
    }

    /**
     * String lwrkClsfNm을 반환
     *
     * @return String lwrkClsfNm
     */
    public String getLwrkClsfNm() {
        return lwrkClsfNm;
    }

    /**
     * lwrkClsfNm을 설정
     *
     * @param lwrkClsfNm 을(를) String lwrkClsfNm로 설정
     */
    public void setLwrkClsfNm(String lwrkClsfNm) {
        this.lwrkClsfNm = lwrkClsfNm;
    }

    /**
     * String ntcPstYn을 반환
     *
     * @return String ntcPstYn
     */
    public String getNtcPstYn() {
        return ntcPstYn;
    }

    /**
     * ntcPstYn을 설정
     *
     * @param ntcPstYn 을(를) String ntcPstYn로 설정
     */
    public void setNtcPstYn(String ntcPstYn) {
        this.ntcPstYn = ntcPstYn;
    }

    /**
     * String ntcBgngDt을 반환
     *
     * @return String ntcBgngDt
     */
    public String getNtcBgngDt() {
        return ntcBgngDt;
    }

    /**
     * ntcBgngDt을 설정
     *
     * @param ntcBgngDt 을(를) String ntcBgngDt로 설정
     */
    public void setNtcBgngDt(String ntcBgngDt) {
        this.ntcBgngDt = ntcBgngDt;
    }

    /**
     * String ntcEndDt을 반환
     *
     * @return String ntcEndDt
     */
    public String getNtcEndDt() {
        return ntcEndDt;
    }

    /**
     * ntcEndDt을 설정
     *
     * @param ntcEndDt 을(를) String ntcEndDt로 설정
     */
    public void setNtcEndDt(String ntcEndDt) {
        this.ntcEndDt = ntcEndDt;
    }

    /**
     * String pstgBgngDt을 반환
     *
     * @return String pstgBgngDt
     */
    public String getPstgBgngDt() {
        return pstgBgngDt;
    }

    /**
     * pstgBgngDt을 설정
     *
     * @param pstgBgngDt 을(를) pstgBgngDt pstgBgngDt로 설정
     */
    public void setPstgBgngDt(String pstgBgngDt) {
        this.pstgBgngDt = pstgBgngDt;
    }

    /**
     * String pstgEndDt을 반환
     *
     * @return String pstgEndDt
     */
    public String getPstgEndDt() {
        return pstgEndDt;
    }

    /**
     * pstgEndDt을 설정
     *
     * @param pstgEndDt 을(를) pstgEndDt pstgEndDt로 설정
     */
    public void setPstgEndDt(String pstgEndDt) {
        this.pstgEndDt = pstgEndDt;
    }

    /**
     * String ttl을 반환
     *
     * @return String ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * ttl을 설정
     *
     * @param ttl 을(를) String ttl로 설정
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * String docContsCn을 반환
     *
     * @return String docContsCn
     */
    public String getDocContsCn() {
        return docContsCn;
    }

    /**
     * docContsCn을 설정
     *
     * @param docContsCn 을(를) String docContsCn로 설정
     */
    public void setDocContsCn(String docContsCn) {
        this.docContsCn = docContsCn;
    }

    /**
     * String mainCn을 반환
     *
     * @return String mainCn
     */
    public String getMainCn() {
        return mainCn;
    }

    /**
     * mainCn을 설정
     *
     * @param mainCn 을(를) String mainCn로 설정
     */
    public void setMainCn(String mainCn) {
        this.mainCn = mainCn;
    }

    /**
     * String ansDtlCn을 반환
     *
     * @return String ansDtlCn
     */
    public String getAnsDtlCn() {
        return ansDtlCn;
    }

    /**
     * ansDtlCn을 설정
     *
     * @param ansDtlCn 을(를) String ansDtlCn로 설정
     */
    public void setAnsDtlCn(String ansDtlCn) {
        this.ansDtlCn = ansDtlCn;
    }

    /**
     * String ansDt을 반환
     *
     * @return String ansDt
     */
    public String getAnsDt() {
        return ansDt;
    }

    /**
     * ansDt을 설정
     *
     * @param ansDt 을(를) String ansDt로 설정
     */
    public void setAnsDt(String ansDt) {
        this.ansDt = ansDt;
    }

    /**
     * Integer fileSn을 반환
     *
     * @return Integer fileSn
     */
    public Integer getFileSn() {
        return fileSn;
    }

    /**
     * fileSn을 설정
     *
     * @param fileSn 을(를) Integer fileSn로 설정
     */
    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * Integer inqCnt을 반환
     *
     * @return Integer inqCnt
     */
    public Integer getInqCnt() {
        return inqCnt;
    }

    /**
     * inqCnt을 설정
     *
     * @param inqCnt 을(를) Integer inqCnt로 설정
     */
    public void setInqCnt(Integer inqCnt) {
        this.inqCnt = inqCnt;
    }

    /**
     * Integer dgstfnEvlCnt을 반환
     *
     * @return Integer dgstfnEvlCnt
     */
    public Integer getDgstfnEvlCnt() {
        return dgstfnEvlCnt;
    }

    /**
     * dgstfnEvlCnt을 설정
     *
     * @param dgstfnEvlCnt 을(를) Integer dgstfnEvlCnt로 설정
     */
    public void setDgstfnEvlCnt(Integer dgstfnEvlCnt) {
        this.dgstfnEvlCnt = dgstfnEvlCnt;
    }

    /**
     * Integer dgstfnSumScr을 반환
     *
     * @return Integer dgstfnSumScr
     */
    public Integer getDgstfnSumScr() {
        return dgstfnSumScr;
    }

    /**
     * dgstfnSumScr을 설정
     *
     * @param dgstfnSumScr 을(를) Integer dgstfnSumScr로 설정
     */
    public void setDgstfnSumScr(Integer dgstfnSumScr) {
        this.dgstfnSumScr = dgstfnSumScr;
    }

    /**
     * Integer dclrCnt을 반환
     *
     * @return Integer dclrCnt
     */
    public Integer getDclrCnt() {
        return dclrCnt;
    }

    /**
     * dclrCnt을 설정
     *
     * @param dclrCnt 을(를) Integer dclrCnt로 설정
     */
    public void setDclrCnt(Integer dclrCnt) {
        this.dclrCnt = dclrCnt;
    }

    /**
     * Integer rcmdtnCnt을 반환
     *
     * @return Integer rcmdtnCnt
     */
    public Integer getRcmdtnCnt() {
        return rcmdtnCnt;
    }

    /**
     * rcmdtnCnt을 설정
     *
     * @param rcmdtnCnt 을(를) Integer rcmdtnCnt로 설정
     */
    public void setRcmdtnCnt(Integer rcmdtnCnt) {
        this.rcmdtnCnt = rcmdtnCnt;
    }

    /**
     * String ipAddr을 반환
     *
     * @return String ipAddr
     */
    @Override
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * ipAddr을 설정
     *
     * @param ipAddr 을(를) String ipAddr로 설정
     */
    @Override
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * String cntnBrwsrNm을 반환
     *
     * @return String cntnBrwsrNm
     */
    public String getCntnBrwsrNm() {
        return cntnBrwsrNm;
    }

    /**
     * cntnBrwsrNm을 설정
     *
     * @param cntnBrwsrNm 을(를) String cntnBrwsrNm로 설정
     */
    public void setCntnBrwsrNm(String cntnBrwsrNm) {
        this.cntnBrwsrNm = cntnBrwsrNm;
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
     * String lmtYn을 반환
     *
     * @return String lmtYn
     */
    public String getLmtYn() {
        return lmtYn;
    }

    /**
     * lmtYn을 설정
     *
     * @param lmtYn 을(를) String lmtYn로 설정
     */
    public void setLmtYn(String lmtYn) {
        this.lmtYn = lmtYn;
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
     * String pswd을 반환
     *
     * @return String pswd
     */
    public String getPswd() {
        return pswd;
    }

    /**
     * pswd을 설정
     *
     * @param pswd 을(를) String pswd로 설정
     */
    public void setPswd(String pswd) {
        this.pswd = pswd;
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
     * String deptCdId을 반환
     *
     * @return String deptCdId
     */
    public String getDeptCdId() {
        return deptCdId;
    }

    /**
     * deptCdId을 설정
     *
     * @param deptCdId 을(를) String deptCdId로 설정
     */
    public void setDeptCdId(String deptCdId) {
        this.deptCdId = deptCdId;
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
     * String flctnColCn1을 반환
     *
     * @return String flctnColCn1
     */
    public String getFlctnColCn1() {
        return flctnColCn1;
    }

    /**
     * flctnColCn1을 설정
     *
     * @param flctnColCn1 을(를) String flctnColCn1로 설정
     */
    public void setFlctnColCn1(String flctnColCn1) {
        this.flctnColCn1 = flctnColCn1;
    }

    /**
     * String flctnColCn2을 반환
     *
     * @return String flctnColCn2
     */
    public String getFlctnColCn2() {
        return flctnColCn2;
    }

    /**
     * flctnColCn2을 설정
     *
     * @param flctnColCn2 을(를) String flctnColCn2로 설정
     */
    public void setFlctnColCn2(String flctnColCn2) {
        this.flctnColCn2 = flctnColCn2;
    }

    /**
     * String flctnColCn3을 반환
     *
     * @return String flctnColCn3
     */
    public String getFlctnColCn3() {
        return flctnColCn3;
    }

    /**
     * flctnColCn3을 설정
     *
     * @param flctnColCn3 을(를) String flctnColCn3로 설정
     */
    public void setFlctnColCn3(String flctnColCn3) {
        this.flctnColCn3 = flctnColCn3;
    }

    /**
     * String flctnColCn4을 반환
     *
     * @return String flctnColCn4
     */
    public String getFlctnColCn4() {
        return flctnColCn4;
    }

    /**
     * flctnColCn4을 설정
     *
     * @param flctnColCn4 을(를) String flctnColCn4로 설정
     */
    public void setFlctnColCn4(String flctnColCn4) {
        this.flctnColCn4 = flctnColCn4;
    }

    /**
     * String flctnColCn5을 반환
     *
     * @return String flctnColCn5
     */
    public String getFlctnColCn5() {
        return flctnColCn5;
    }

    /**
     * flctnColCn5을 설정
     *
     * @param flctnColCn5 을(를) String flctnColCn5로 설정
     */
    public void setFlctnColCn5(String flctnColCn5) {
        this.flctnColCn5 = flctnColCn5;
    }

    /**
     * String flctnColCn6을 반환
     *
     * @return String flctnColCn6
     */
    public String getFlctnColCn6() {
        return flctnColCn6;
    }

    /**
     * flctnColCn6을 설정s
     *
     * @param flctnColCn6 을(를) String flctnColCn6로 설정
     */
    public void setFlctnColCn6(String flctnColCn6) {
        this.flctnColCn6 = flctnColCn6;
    }

    /**
     * String flctnColCn7을 반환
     *
     * @return String flctnColCn7
     */
    public String getFlctnColCn7() {
        return flctnColCn7;
    }

    /**
     * flctnColCn7을 설정
     *
     * @param flctnColCn7 을(를) String flctnColCn7로 설정
     */
    public void setFlctnColCn7(String flctnColCn7) {
        this.flctnColCn7 = flctnColCn7;
    }

    /**
     * String flctnColCn8을 반환
     *
     * @return String flctnColCn8
     */
    public String getFlctnColCn8() {
        return flctnColCn8;
    }

    /**
     * flctnColCn8을 설정
     *
     * @param flctnColCn8 을(를) String flctnColCn8로 설정
     */
    public void setFlctnColCn8(String flctnColCn8) {
        this.flctnColCn8 = flctnColCn8;
    }

    /**
     * String flctnColCn9을 반환
     *
     * @return String flctnColCn9
     */
    public String getFlctnColCn9() {
        return flctnColCn9;
    }

    /**
     * flctnColCn9을 설정
     *
     * @param flctnColCn9 을(를) String flctnColCn9로 설정
     */
    public void setFlctnColCn9(String flctnColCn9) {
        this.flctnColCn9 = flctnColCn9;
    }

    /**
     * String flctnColCn10을 반환
     *
     * @return String flctnColCn10
     */
    public String getFlctnColCn10() {
        return flctnColCn10;
    }

    /**
     * flctnColCn10을 설정
     *
     * @param flctnColCn10 을(를) String flctnColCn10로 설정
     */
    public void setFlctnColCn10(String flctnColCn10) {
        this.flctnColCn10 = flctnColCn10;
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
     * String rgtrTelno을 반환
     *
     * @return String rgtrTelno
     */
    public String getRgtrTelno() {
        return rgtrTelno;
    }

    /**
     * rgtrTelno을 설정
     *
     * @param rgtrTelno 을(를) String rgtrTelno로 설정
     */
    public void setRgtrTelno(String rgtrTelno) {
        this.rgtrTelno = rgtrTelno;
    }

    /**
     * String rgtrEmlAddr을 반환
     *
     * @return String rgtrEmlAddr
     */
    public String getRgtrEmlAddr() {
        return rgtrEmlAddr;
    }

    /**
     * rgtrEmlAddr을 설정
     *
     * @param rgtrEmlAddr 을(를) String rgtrEmlAddr로 설정
     */
    public void setRgtrEmlAddr(String rgtrEmlAddr) {
        this.rgtrEmlAddr = rgtrEmlAddr;
    }

    /**
     * String regDt을 반환
     *
     * @return String regDt
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     *
     * @param regDt 을(를) String regDt로 설정
     */
    public void setRegDt(String regDt) {
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
     * String updtDt을 반환
     *
     * @return String updtDt
     */
    public String getUpdtDt() {
        return updtDt;
    }

    /**
     * updtDt을 설정
     *
     * @param updtDt 을(를) String updtDt로 설정
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * String delDt을 반환
     *
     * @return String delDt
     */
    public String getDelDt() {
        return delDt;
    }

    /**
     * delDt을 설정
     *
     * @param delDt 을(를) String delDt로 설정
     */
    public void setDelDt(String delDt) {
        this.delDt = delDt;
    }

    /**
     * String[] fileIds을 반환
     *
     * @return String[] fileIds
     */
    public String[] getFileIds() {
        return fileIds;
    }

    /**
     * fileIds을 설정
     *
     * @param fileIds 을(를) String[] fileIds로 설정
     */
    public void setFileIds(String[] fileIds) {
        this.fileIds = fileIds;
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
     * Float stsfdgEvlAvrg을 반환
     *
     * @return Float stsfdgEvlAvrg
     */
    public Float getStsfdgEvlAvrg() {
        return stsfdgEvlAvrg;
    }

    /**
     * stsfdgEvlAvrg을 설정
     *
     * @param stsfdgEvlAvrg 을(를) Float stsfdgEvlAvrg로 설정
     */
    public void setStsfdgEvlAvrg(Float stsfdgEvlAvrg) {
        this.stsfdgEvlAvrg = stsfdgEvlAvrg;
    }

    /**
     * String mngrDelYn을 반환
     *
     * @return String mngrDelYn
     */
    public String getMngrDelYn() {
        return mngrDelYn;
    }

    /**
     * mngrDelYn을 설정
     *
     * @param mngrDelYn 을(를) String mngrDelYn로 설정
     */
    public void setMngrDelYn(String mngrDelYn) {
        this.mngrDelYn = mngrDelYn;
    }

    /**
     * String mngrIndctLmtYn을 반환
     *
     * @return String mngrIndctLmtYn
     */
    public String getMngrIndctLmtYn() {
        return mngrIndctLmtYn;
    }

    /**
     * mngrIndctLmtYn을 설정
     *
     * @param mngrIndctLmtYn 을(를) String mngrIndctLmtYn로 설정
     */
    public void setMngrIndctLmtYn(String mngrIndctLmtYn) {
        this.mngrIndctLmtYn = mngrIndctLmtYn;
    }

    /**
     * Integer mvmnBbsSn을 반환
     *
     * @return Integer mvmnBbsSn
     */
    public Integer getMvmnBbsSn() {
        return mvmnBbsSn;
    }

    /**
     * mvmnBbsSn을 설정
     *
     * @param mvmnBbsSn 을(를) Integer mvmnBbsSn로 설정
     */
    public void setMvmnBbsSn(Integer mvmnBbsSn) {
        this.mvmnBbsSn = mvmnBbsSn;
    }

    /**
     * String mvmnBbsNm을 반환
     *
     * @return String mvmnBbsNm
     */
    public String getMvmnBbsNm() {
        return mvmnBbsNm;
    }

    /**
     * mvmnBbsNm을 설정
     *
     * @param mvmnBbsNm 을(를) String mvmnBbsNm로 설정
     */
    public void setMvmnBbsNm(String mvmnBbsNm) {
        this.mvmnBbsNm = mvmnBbsNm;
    }

    /**
     * String delYn을 반환
     *
     * @return String delYn
     */
    public String getDelYn() {
        return delYn;
    }

    /**
     * delYn을 설정
     *
     * @param delYn 을(를) String delYn로 설정
     */
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    /**
     * String cprgtUseYn을 반환
     *
     * @return String cprgtUseYn
     */
    public String getCprgtUseYn() {
        return cprgtUseYn;
    }

    /**
     * cprgtUseYn을 설정
     *
     * @param cprgtUseYn 을(를) String cprgtUseYn로 설정
     */
    public void setCprgtUseYn(String cprgtUseYn) {
        this.cprgtUseYn = cprgtUseYn;
    }

    /**
     * String autTypeNm을 반환
     *
     * @return String autTypeNm
     */
    public String getAutTypeNm() {
        return autTypeNm;
    }

    /**
     * autTypeNm을 설정
     *
     * @param autTypeNm 을(를) String autTypeNm로 설정
     */
    public void setAutTypeNm(String autTypeNm) {
        this.autTypeNm = autTypeNm;
    }

    /**
     * String cprgtTypeNm을 반환
     *
     * @return String cprgtTypeNm
     */
    public String getCprgtTypeNm() {
        return cprgtTypeNm;
    }

    /**
     * cprgtTypeNm을 설정
     *
     * @param cprgtTypeNm 을(를) String cprgtTypeNm로 설정
     */
    public void setCprgtTypeNm(String cprgtTypeNm) {
        this.cprgtTypeNm = cprgtTypeNm;
    }

    /**
     * String cprgtCn을 반환
     *
     * @return String cprgtCn
     */
    public String getCprgtCn() {
        return cprgtCn;
    }

    /**
     * cprgtCn을 설정
     *
     * @param cprgtCn 을(를) String cprgtCn로 설정
     */
    public void setCprgtCn(String cprgtCn) {
        this.cprgtCn = cprgtCn;
    }

    /**
     * BbsVO prevVo을 반환
     *
     * @return BbsVO prevVo
     */
    public BbsVO getPrevVo() {
        return prevVo;
    }

    /**
     * prevVo을 설정
     *
     * @param prevVo 을(를) BbsVO prevVo로 설정
     */
    public void setPrevVo(BbsVO prevVo) {
        this.prevVo = prevVo;
    }

    /**
     * BbsVO nextVo을 반환
     *
     * @return BbsVO nextVo
     */
    public BbsVO getNextVo() {
        return nextVo;
    }

    /**
     * nextVo을 설정
     *
     * @param nextVo 을(를) BbsVO nextVo로 설정
     */
    public void setNextVo(BbsVO nextVo) {
        this.nextVo = nextVo;
    }

    /**
     * String bbsDocNos을 반환
     *
     * @return String bbsDocNos
     */
    public String getBbsDocNos() {
        return bbsDocNos;
    }

    /**
     * bbsDocNos을 설정
     *
     * @param bbsDocNos 을(를) String bbsDocNos로 설정
     */
    public void setBbsDocNos(String bbsDocNos) {
        this.bbsDocNos = bbsDocNos;
    }

    /**
     * Integer passDay을 반환
     *
     * @return Integer passDay
     */
    public Integer getPassDay() {
        return passDay;
    }

    /**
     * passDay을 설정
     *
     * @param passDay 을(를) Integer passDay로 설정
     */
    public void setPassDay(Integer passDay) {
        this.passDay = passDay;
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
     * String tagNms을 반환
     *
     * @return String tagNms
     */
    public String getTagNms() {
        return tagNms;
    }

    /**
     * tagNms을 설정
     *
     * @param tagNms 을(를) String tagNms로 설정
     */
    public void setTagNms(String tagNms) {
        this.tagNms = tagNms;
    }

    /**
     * List<String> bbsTagList을 반환
     *
     * @return List<String> bbsTagList
     */
    public List<String> getBbsTagList() {
        return bbsTagList;
    }

    /**
     * bbsTagList을 설정
     *
     * @param bbsTagList 을(를) List<String> bbsTagList로 설정
     */
    public void setBbsTagList(List<String> bbsTagList) {
        this.bbsTagList = bbsTagList;
    }

    /**
     * Integer cmntCo을 반환
     *
     * @return Integer cmntCo
     */
    public Integer getCmntCo() {
        return cmntCo;
    }

    /**
     * cmntCo을 설정
     *
     * @param cmntCo 을(를) Integer cmntCo로 설정
     */
    public void setCmntCo(Integer cmntCo) {
        this.cmntCo = cmntCo;
    }

}
