/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.hist.domain;

import java.io.Serializable;

/**
 * TODO 설명을 입력하세요.
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 17.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserHistCheckResultVO implements Serializable {

    private static final long serialVersionUID = 3896921287627085699L;

    private boolean empty = true;

    private boolean equals = true;

    /**
     * boolean empty을 반환
     * 
     * @return boolean empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * empty을 설정
     * 
     * @param empty 을(를) boolean empty로 설정
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * <pre>
     * boolean
     * 검색조건이 이전의 검색조건과 같다면 통과
     * (ex: 검색조건 동일, 페이징이나 페이지당 표시건수만 달라졌을 경우)
     * </pre>
     * @return boolean equals
     */
    public boolean isEquals() {
        return equals;
    }

    /**
     * equals을 설정
     * 
     * @param equals 을(를) boolean equals로 설정
     */
    public void setEquals(boolean equals) {
        this.equals = equals;
    }

}
