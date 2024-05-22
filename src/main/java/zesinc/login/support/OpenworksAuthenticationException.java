/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.support;

import org.springframework.security.core.AuthenticationException;

/**
 * 사용자 정의 인증 실패 오류처리 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 5. 17.    yesno   최초작성
 * </pre>
 * 
 * @author 방기배
 */
public class OpenworksAuthenticationException extends AuthenticationException {

    /** serialVersionUID */
    private static final long serialVersionUID = 9031391084734538392L;

    /**
     * Constructs a <code>UsernameNotFoundException</code> with the specified
     * message.
     *
     * @param msg the detail message.
     */
    public OpenworksAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code OpenworksAuthenticationException} with the specified message and root
     * cause.
     *
     * @param msg the detail message.
     * @param t root cause
     */
    public OpenworksAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
