/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul.support;

/**
 * 일정 반복 유형 정의
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 12. 7.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public enum SchdulReptitTy {

    Y("매년"), M("매월"), W("매주"), D("매일");

    SchdulReptitTy(String name) {
        this.name = name;
    }

    /** 한글명칭 */
    private String name;

    /**
     * 한글명칭 반환
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }
}
