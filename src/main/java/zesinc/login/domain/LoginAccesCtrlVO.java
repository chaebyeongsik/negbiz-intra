/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.domain;

import zesinc.web.vo.BaseVO;

/**
 * 접근제어 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016-03-02.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class LoginAccesCtrlVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 4274241361347445094L;

    /** 허용유형 */
    private String prmsnTypeNm;

    /** 허용ID */
    private String prmsnId;

    /** 허용시작IP */
    private String prmsnBgngIp;

    /** 허용종료IP */
    private String prmsnEndIp;

    /**
     * 허용유형 설정
     * 
     * @param prmsnTypeNm을(를) String prmsnTypeNm로 설정
     */
    public void setPrmsnTypeNm(String prmsnTypeNm) {
        this.prmsnTypeNm = prmsnTypeNm;
    }

    /**
     * 허용유형 반환
     * 
     * @return String prmsnTypeNm
     */
    public String getPrmsnTypeNm() {
        return prmsnTypeNm;
    }

    /**
     * 허용ID 설정
     * 
     * @param prmsnId을(를) String prmsnId로 설정
     */
    public void setPrmsnId(String prmsnId) {
        this.prmsnId = prmsnId;
    }

    /**
     * 허용ID 반환
     * 
     * @return String prmsnId
     */
    public String getPrmsnId() {
        return prmsnId;
    }

    /**
     * 허용시작IP 설정
     * 
     * @param prmsnBgngIp을(를) String prmsnBgngIp로 설정
     */
    public void setPrmsnBgngIp(String prmsnBgngIp) {
        this.prmsnBgngIp = prmsnBgngIp;
    }

    /**
     * 허용시작IP 반환
     * 
     * @return String prmsnBgngIp
     */
    public String getPrmsnBgngIp() {
        return prmsnBgngIp;
    }

    /**
     * 허용종료IP 설정
     * 
     * @param prmsnEndIp을(를) String prmsnEndIp로 설정
     */
    public void setPrmsnEndIp(String prmsnEndIp) {
        this.prmsnEndIp = prmsnEndIp;
    }

    /**
     * 허용종료IP 반환
     * 
     * @return String prmsnEndIp
     */
    public String getPrmsnEndIp() {
        return prmsnEndIp;
    }

}
