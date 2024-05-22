/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.domn.domain;

import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Numeric;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.BaseVO;

/**
 * 도메인그룹 정보 VO 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-02-27.    방기배   최초작성
 * </pre>
 * @see
 */
public class DomnGroupVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -1706213923790474654L;
    /** 도메인코드 */
    @Required
    @Numeric
    private Integer siteSn;

    /** 그룹아이피주소 */
    @Required
    @MaxLength(max = 15)
    private String srvrIpAddr;

    /** 그룹포트번호 */
    @Numeric
    private Integer groupPortSn;

    /**
     * 도메인코드을 설정
     * 
     * @param siteSn을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * 도메인코드을 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * 그룹아이피주소을 설정
     * 
     * @param srvrIpAddr을(를) String srvrIpAddr로 설정
     */
    public void setSrvrIpAddr(String srvrIpAddr) {
        this.srvrIpAddr = srvrIpAddr;
    }

    /**
     * 그룹아이피주소을 반환
     * 
     * @return String srvrIpAddr
     */
    public String getSrvrIpAddr() {
        return srvrIpAddr;
    }

    /**
     * 그룹포트번호을 설정
     * 
     * @param groupPortSn을(를) Integer groupPortSn로 설정
     */
    public void setGroupPortSn(Integer groupPortSn) {
        this.groupPortSn = groupPortSn;
    }

    /**
     * 그룹포트번호을 반환
     * 
     * @return Integer groupPortSn
     */
    public Integer getGroupPortSn() {
        return groupPortSn;
    }

}
