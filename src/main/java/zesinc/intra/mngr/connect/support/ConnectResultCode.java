/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.connect.support;

/**
 * 로그인 처리 상태 코드
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 05. 17.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public enum ConnectResultCode {

    /* 로그인 */
    IS_1010("일반로그인"), IS_1011("GPKI로그인"),
    IF_1010("비밀번호오류"), IF_1011("메뉴권한없음"), IF_1012("계정사용중지"),
    IF_1020("GPKI인증실패(비정상접근)"), IF_1021("미등록사용자"),
    IF_1030("일반접근제한"), IF_1031("GPKI접근제한"),
    /* 로그아웃 */
    OS_1010("일반로그아웃"), OS_1011("세션만료");

    ConnectResultCode(String name) {
        this.name = name;
    }

    /** 한글 명칭 */
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
