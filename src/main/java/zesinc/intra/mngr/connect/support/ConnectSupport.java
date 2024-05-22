/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.mngr.connect.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zesinc.core.lang.Validate;

/**
 * 접속 이력 관련 편의 지원 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 5. 18.    yesno   최초작성
 * </pre>
 * 
 * @author yesno
 */
public class ConnectSupport {

    /** 상속 불가 */
    private ConnectSupport() {
    }

    /** 코드목록 */
    private static List<Map<String, String>> RESULT_CODE_LIST;
    private static Map<String, ConnectResultCode> RESULT_CODE_MAP;
    static {
        RESULT_CODE_LIST = new ArrayList<Map<String, String>>();
        RESULT_CODE_MAP = new HashMap<String, ConnectResultCode>();

        ConnectResultCode[] values = ConnectResultCode.values();
        Map<String, String> resultCodeMap;
        for(ConnectResultCode resultCode : values) {
            resultCodeMap = new HashMap<String, String>();
            resultCodeMap.put(resultCode.name(), resultCode.getName());

            RESULT_CODE_LIST.add(resultCodeMap);
            RESULT_CODE_MAP.put(resultCode.name(), resultCode);
        }
    }

    /**
     * 결과 코드 상수값 객체 반환
     * 
     * @param resultCode
     * @return
     */
    public static ConnectResultCode getResultCode(String resultCode) {

        return RESULT_CODE_MAP.get(resultCode);
    }

    /**
     * 결과 코드의 한글 명을 반환
     * 
     * @param resultCode
     * @return
     */
    public static String getResultCodeNm(String resultCode) {
        ConnectResultCode connectResultCode = RESULT_CODE_MAP.get(resultCode);
        if(Validate.isNotEmpty(connectResultCode)) {
            return RESULT_CODE_MAP.get(resultCode).getName();
        }

        return "";
    }

    /**
     * 결과 코드 상수값 객체 Map을 반환
     * 
     * @return
     */
    public static Map<String, ConnectResultCode> getResultCodeMap() {

        return RESULT_CODE_MAP;
    }

    /**
     * 결과 코드 상수값 객체 목록을 반환
     * 
     * @return
     */
    public static List<Map<String, String>> getResultCodeList() {

        return RESULT_CODE_LIST;
    }
}
