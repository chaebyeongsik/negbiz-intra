/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zesinc.core.lang.Validate;
import zesinc.web.utils.ReptitDateUtil;

/**
 * 일정 관련 정보 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 12. 3.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class SchdulSupport {

    /** 일정 HGHRK_CD_ID */
    public static String HGHRK_CD_ID = "schdul";

    /** 반복유형 */
    private static Map<String, SchdulReptitTy> RPTT_TYPE_CD_MAP;
    private static List<Map<String, String>> RPTT_TYPE_CD_LIST = new ArrayList<Map<String, String>>();
    static {
        RPTT_TYPE_CD_MAP = new HashMap<String, SchdulReptitTy>();

        SchdulReptitTy[] arrSttus = SchdulReptitTy.values();

        Map<String, String> sttusMap;
        for(SchdulReptitTy sttus : arrSttus) {
            sttusMap = new HashMap<String, String>();
            sttusMap.put("key", sttus.name());
            sttusMap.put("text", sttus.getName());

            RPTT_TYPE_CD_LIST.add(sttusMap);
            RPTT_TYPE_CD_MAP.put(sttus.name(), sttus);
        }
    }

    /**
     * 문자열에 해당하는 반복유형 SchdulReptitTy를 반환
     * 
     * @param schdulReptitTy
     * @return
     */
    public static SchdulReptitTy getSchdulReptitTy(String schdulReptitTy) {

        return RPTT_TYPE_CD_MAP.get(schdulReptitTy);
    }

    /**
     * 문자열에 해당하는 반복유형명 반환
     * 
     * @param schdulReptitTy
     * @return
     */
    public static String getSchdulReptitTyNm(String schdulReptitTy) {

        return RPTT_TYPE_CD_MAP.get(schdulReptitTy).getName();
    }

    /**
     * 반복유형 목록을 반환
     * 
     * @return
     */
    public static List<Map<String, String>> getSchdulReptitTyList() {

        return RPTT_TYPE_CD_LIST;
    }

    /**
     * hhmi 문자열을 hh:mi 포멧으로 변환
     * 예 : 1010 = 10:10
     * 
     * @param timeStr
     * @return
     */
    public static String getFormatTime(String timeStr) {
        String time = "";
        if(Validate.isNotEmpty(timeStr) && timeStr.length() >= 4) {
            time = timeStr.substring(0, 2) + ":" + timeStr.substring(2, 4);
        }
        return time;
    }

    /**
     * 반복유형에 따라서 시작 년월일과 종료년월일 사이의 모든 날짜 목록을 반환한다.
     * 
     * @param bgngYmd 시작일
     * @param endYmd 종료일
     * @param dayOfWeek 반복 요일 목록(
     * @param prttTypeCd
     * @return
     */
    public static List<String> getSchdulDateList(String bgngYmd, String endYmd, String rpttEndYmd, List<Integer> dayOfWeek, SchdulReptitTy prttTypeCd) {
        List<String> dayList = new ArrayList<String>();

        // 반복일정 여부에 따른 분기
        if(Validate.isNotEmpty(prttTypeCd)) {
            switch(prttTypeCd) {
                case Y:
                    dayList = getYearReptitDateList(bgngYmd, endYmd, rpttEndYmd);
                    break;
                case M:
                    dayList = getMonthReptitDateList(bgngYmd, endYmd, rpttEndYmd);
                    break;
                case W:
                    dayList = getWeekReptitDateList(bgngYmd, endYmd, rpttEndYmd, dayOfWeek);
                    break;
                case D:
                    dayList = getDayReptitDateList(bgngYmd, endYmd, rpttEndYmd);
                    break;
                default:
                    break;
            }
        } else {
            dayList = getDayReptitDateList(bgngYmd, endYmd, rpttEndYmd);
        }

        return dayList;
    }

    /**
     * 시작년도부터 종료년도까지 해당 시작월일부터 종료월일 사이의 모든 일자 목록을 반환
     * 
     * @param bgngYmd 시작년월일
     * @param endYmd 종료년월일
     * @param rpttEndYmd 반복종료 년월일
     * @return
     */
    public static List<String> getYearReptitDateList(String bgngYmd, String endYmd, String rpttEndYmd) {

        return ReptitDateUtil.getYearReptitDateList(bgngYmd, endYmd, rpttEndYmd);
    }

    /**
     * 시작년도부터 종료년도까지 매월 지정된 시작일부터 종료일 사이의 모든 일자 목록을 반환
     * 
     * @param bgngYmd 시작년월일
     * @param endYmd 종료년월일
     * @param rpttEndYmd 반복종료 년월일
     * @return
     */
    public static List<String> getMonthReptitDateList(String bgngYmd, String endYmd, String rpttEndYmd) {

        return ReptitDateUtil.getMonthReptitDateList(bgngYmd, endYmd, rpttEndYmd);
    }

    /**
     * 시작 년월일부터 종료년월일까지 지정된 요일의 모든 날짜 목록을 반환.
     * 
     * @param bgngYmd
     * @param endYmd
     * @param rpttEndYmd
     * @param dayOfWeek
     * @return
     */
    public static List<String> getWeekReptitDateList(String bgngYmd, String endYmd, String rpttEndYmd, List<Integer> dayOfWeek) {

        return ReptitDateUtil.getWeekReptitDateList(bgngYmd, rpttEndYmd, dayOfWeek);
    }

    /**
     * 해당 년월의 시작일부터 종료일까지 모든 날짜 목록을 반환.
     * 
     * @param bgngYmd
     * @param endYmd
     * @param rpttEndYmd
     * @return
     */
    public static List<String> getDayReptitDateList(String bgngYmd, String endYmd, String rpttEndYmd) {

        return ReptitDateUtil.getDayReptitDateList(bgngYmd, rpttEndYmd);
    }

}
