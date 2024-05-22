/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.grad.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.AlphaNumeric;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.vo.PageVO;

/**
 * 사용자 등급 VO 클래스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 4. 30.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserGradVO extends PageVO {

    private static final long serialVersionUID = -2364458013903652593L;

    /** 사용자등급코드 */
    @MaxLength(max = 20)
    @AlphaNumeric
    private String userGrdCdId;

    /** 사용자등급명 */
    @MaxLength(max = 30)
    private String userGrdNm;

    /** 사용자등급설명 */
    @MaxLength(max = 4000)
    private String userGrdExpln;

    /** 사용여부 */
    @MaxLength(max = 1)
    private String useYn;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 등록자명 */
    private String rgtrNm;

    /** 등록일시 */
    private Date regDt;

    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;

    /** 수정자명 */
    private String updusrNm;

    /** 수정일시 */
    private Date updtDt;

    /**
     * String userGrdCdId을 반환
     * 
     * @return String userGrdCdId
     */
    public String getUserGrdCdId() {
        return userGrdCdId;
    }

    /**
     * userGrdCdId을 설정
     * 
     * @param userGrdCdId 을(를) String userGrdCdId로 설정
     */
    public void setUserGrdCdId(String userGrdCdId) {
        this.userGrdCdId = userGrdCdId;
    }

    /**
     * String userGrdNm을 반환
     * 
     * @return String userGrdNm
     */
    public String getUserGrdNm() {
        return userGrdNm;
    }

    /**
     * userGrdNm을 설정
     * 
     * @param userGrdNm 을(를) String userGrdNm로 설정
     */
    public void setUserGrdNm(String userGrdNm) {
        this.userGrdNm = userGrdNm;
    }

    /**
     * String userGrdExpln을 반환
     * 
     * @return String userGrdExpln
     */
    public String getUserGrdExpln() {
        return userGrdExpln;
    }

    /**
     * userGrdExpln을 설정
     * 
     * @param userGrdExpln 을(를) String userGrdExpln로 설정
     */
    public void setUserGrdExpln(String userGrdExpln) {
        this.userGrdExpln = userGrdExpln;
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

}
