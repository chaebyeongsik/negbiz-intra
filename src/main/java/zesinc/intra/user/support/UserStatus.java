/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.support;


/**
 *<pre>
 *
 * 회원상태를 나타내며 DB나 소스상에서는 1001, 1002로 표시된다.
 * 가입 : 1001
 * 탈퇴 : 1002
 *
 *<< 개정이력(Modification Information) >>
 *   
 *    수정일       수정자   수정내용
 *--------------  --------  -------------------------------
 * 2015. 6. 2.    ZES-INC   최초작성
 *</pre>
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public enum UserStatus {
    JOIN("1001"), SECEDE("1002"); // 1001 : 가입 / 1002 : 탈퇴

    final private String userStatus;

    private UserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getName() {
        if(this.equals(JOIN)) {
            return "가입";
        } else if(this.equals(SECEDE)) {
            return "탈퇴";
        }
        return "회원상태";
    }
}
