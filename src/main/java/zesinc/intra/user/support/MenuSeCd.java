/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.support;

/**
 * 메뉴 구분(사용자 로그)
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 29.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public enum MenuSeCd {
    READ("R"), OUTPUT("O"), UPDATE("U"); // 1001 : 가입 / 1002 : 탈퇴

    final private String menuSeCd;

    private MenuSeCd(String menuSeCd) {
        this.menuSeCd = menuSeCd;
    }

    public String getMenuSeCd() {
        return menuSeCd;
    }

    public String getName() {
        if(this.equals(READ)) {
            return "조회";
        } else if(this.equals(OUTPUT)) {
            return "출력";
        }

        return "변경";
    }

}
