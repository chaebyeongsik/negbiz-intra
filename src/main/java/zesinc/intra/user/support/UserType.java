/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.user.support;

/**
 * <pre>
 * 
 *  회원타입으로 DB나 소스상에는 I, E로 표시된다.
 *  개인사용자 : I
 *  기업사용자 : E
 *  
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 2.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public enum UserType {
    INDVDL("I"), ENTRPRS("E"); // I : 개인 / E : 기업

    final private String userType;

    private UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public String getName() {
        if(this.equals(INDVDL)) {
            return "개인";
        } else if(this.equals(ENTRPRS)) {
            return "기업";
        }
        return "회원타입";
    }
}
