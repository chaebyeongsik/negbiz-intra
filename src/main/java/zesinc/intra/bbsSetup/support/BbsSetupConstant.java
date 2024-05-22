/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbsSetup.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 게시판환경설정 관련 상수 값을 모은 지원 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 5. 19.    woogi   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public final class BbsSetupConstant {

    private BbsSetupConstant() {
    }

    /*
     * -----------------------------------------------------------
     * 게시판 설정 구분 코드
     */
    public static final int SECTION_CODE_BASE = 0;
    public static final int SECTION_CODE_GLOBAL = 10;
    public static final int SECTION_CODE_LIST = 20;
    public static final int SECTION_CODE_VIEW = 30;
    public static final int SECTION_CODE_FORM = 40;
    public static final int SECTION_CODE_AUTH = 50;
    public static final int SECTION_CODE_ITEM = 60;

    /*
     * -----------------------------------------------------------
     * 게시판 설정 맵 (for select tag support)
     */
    public static List<Map<String, String>> SEARCH_TYPE_LIST = new ArrayList<Map<String, String>>();

    /*
     * -----------------------------------------------------------
     * 게시판 검색 유형 코드
     */
    public static final String SEARCH_TYPE_EQUAL = "1001";
    public static final String SEARCH_TYPE_LIKE = "1002";
    public static final String SEARCH_TYPE_LE = "1003";
    public static final String SEARCH_TYPE_GE = "1004";

    static {

        Map<String, String> SEARCH_TYPE = new HashMap<String, String>();
        SEARCH_TYPE.put("cdId", SEARCH_TYPE_EQUAL);
        SEARCH_TYPE.put("cdNm", "일치");
        SEARCH_TYPE_LIST.add(SEARCH_TYPE);

        SEARCH_TYPE = new HashMap<String, String>();
        SEARCH_TYPE.put("cdId", SEARCH_TYPE_LIKE);
        SEARCH_TYPE.put("cdNm", "부분일치");
        SEARCH_TYPE_LIST.add(SEARCH_TYPE);

        SEARCH_TYPE = new HashMap<String, String>();
        SEARCH_TYPE.put("cdId", SEARCH_TYPE_LE);
        SEARCH_TYPE.put("cdNm", "작거나같음");
        SEARCH_TYPE_LIST.add(SEARCH_TYPE);

        SEARCH_TYPE = new HashMap<String, String>();
        SEARCH_TYPE.put("cdId", SEARCH_TYPE_GE);
        SEARCH_TYPE.put("cdNm", "크거나같음");
        SEARCH_TYPE_LIST.add(SEARCH_TYPE);
    }

}
