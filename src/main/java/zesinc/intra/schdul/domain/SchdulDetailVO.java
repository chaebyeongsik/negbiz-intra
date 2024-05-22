/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul.domain;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

/**
 * 일정상세 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-12-08.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class SchdulDetailVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -242189775131145905L;
    /** 일련번호 */
    @Required
    @Digits
    private Integer regSn;

    /** 상세일련번호 */
    @Required
    @Digits
    private Integer dtlSn;

    /** 구분코드 */
    @MaxLength(max = 20)
    private String seCdId;

    /** 구분명 */
    private String seNm;

    /** 시작일 */
    @MaxLength(max = 10)
    private String bgngYmd;

    /** 종료일 */
    @MaxLength(max = 10)
    private String endYmd;

    /** 시작시간 */
    @MaxLength(max = 5)
    private String bgngHr;

    /** 종료시간 */
    @MaxLength(max = 5)
    private String endHr;

    /**
     * 일련번호 설정
     * 
     * @param regSn을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 일련번호 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * 상세일련번호 설정
     * 
     * @param dtlSn을(를) Integer dtlSn로 설정
     */
    public void setDtlSn(Integer dtlSn) {
        this.dtlSn = dtlSn;
    }

    /**
     * 상세일련번호 반환
     * 
     * @return Integer dtlSn
     */
    public Integer getDtlSn() {
        return dtlSn;
    }

    /**
     * 구분코드 설정
     * 
     * @param seCdId을(를) String seCdId로 설정
     */
    public void setSeCdId(String seCdId) {
        this.seCdId = seCdId;
    }

    /**
     * 구분코드 반환
     * 
     * @return String seCdId
     */
    public String getSeCdId() {
        return seCdId;
    }

    /**
     * 구분명 설정
     * 
     * @param seNm을(를) String seNm로 설정
     */
    public void setSeNm(String seNm) {
        this.seNm = seNm;
    }

    /**
     * 구분명 반환
     * 
     * @return String seNm
     */
    public String getSeNm() {
        return seNm;
    }

    /**
     * 시작일 설정
     * 
     * @param bgngYmd을(를) String bgngYmd로 설정
     */
    public void setBgngYmd(String bgngYmd) {
        this.bgngYmd = bgngYmd;
    }

    /**
     * 시작일 반환
     * 
     * @return String bgngYmd
     */
    public String getBgngYmd() {
        return bgngYmd;
    }

    /**
     * 종료일 설정
     * 
     * @param endYmd을(를) String endYmd로 설정
     */
    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
    }

    /**
     * 종료일 반환
     * 
     * @return String endYmd
     */
    public String getEndYmd() {
        return endYmd;
    }

    /**
     * 시작시간 설정
     * 
     * @param bgngHr을(를) String bgngHr로 설정
     */
    public void setBgngHr(String bgngHr) {
        this.bgngHr = bgngHr;
    }

    /**
     * 시작시간 반환
     * 
     * @return String bgngHr
     */
    public String getBgngHr() {
        return bgngHr;
    }

    /**
     * 종료시간 설정
     * 
     * @param endHr을(를) String endHr로 설정
     */
    public void setEndHr(String endHr) {
        this.endHr = endHr;
    }

    /**
     * 종료시간 반환
     * 
     * @return String endHr
     */
    public String getEndHr() {
        return endHr;
    }

}
