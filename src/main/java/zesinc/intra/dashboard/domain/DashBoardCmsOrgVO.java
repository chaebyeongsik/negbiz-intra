/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.dashboard.domain;

import zesinc.web.vo.BaseVO;

/**
 * 부서/담당자 목록 조회
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 13.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class DashBoardCmsOrgVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -357981622081756417L;

    /** 부서명 */
    private String deptNm;
    /** 상위부서명 */
    private String upperDeptNm;
    /** 관리담당자ID */
    private String authorPicId;
    /** 관리담당자명 */
    private String authorPicNm;

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
     * String upperDeptNm을 반환
     * 
     * @return String upperDeptNm
     */
    public String getUpperDeptNm() {
        return upperDeptNm;
    }

    /**
     * upperDeptNm을 설정
     * 
     * @param upperDeptNm 을(를) String upperDeptNm로 설정
     */
    public void setUpperDeptNm(String upperDeptNm) {
        this.upperDeptNm = upperDeptNm;
    }

    /**
     * String authorPicId을 반환
     * 
     * @return String authorPicId
     */
    public String getAuthorPicId() {
        return authorPicId;
    }

    /**
     * authorPicId을 설정
     * 
     * @param authorPicId 을(를) String authorPicId로 설정
     */
    public void setAuthorPicId(String authorPicId) {
        this.authorPicId = authorPicId;
    }

    /**
     * String authorPicNm을 반환
     * 
     * @return String authorPicNm
     */
    public String getAuthorPicNm() {
        return authorPicNm;
    }

    /**
     * authorPicNm을 설정
     * 
     * @param authorPicNm 을(를) String authorPicNm로 설정
     */
    public void setAuthorPicNm(String authorPicNm) {
        this.authorPicNm = authorPicNm;
    }

}
