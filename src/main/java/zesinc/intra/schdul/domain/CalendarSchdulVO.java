/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.schdul.domain;

import java.util.List;

import zesinc.web.vo.BaseVO;

/**
 * 달력 일정을 표시하기 위한 VO 객체
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 1. 13.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class CalendarSchdulVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -1994210814539891211L;

    /** 년 */
    private String year;
    /** 월 */
    private String month;
    /** 일 */
    private String day;
    /** 주말여부 */
    private Boolean isSaturDay;
    /** 일요일 또는 휴일여부 */
    private Boolean isHoliday;

    /** 일정목록 */
    private List<SchdulVO> schdulList;

    /**
     * String year을 반환
     * 
     * @return String year
     */
    public String getYear() {
        return year;
    }

    /**
     * year을 설정
     * 
     * @param year 을(를) String year로 설정
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * String month을 반환
     * 
     * @return String month
     */
    public String getMonth() {
        return month;
    }

    /**
     * month을 설정
     * 
     * @param month 을(를) String month로 설정
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * String day을 반환
     * 
     * @return String day
     */
    public String getDay() {
        return day;
    }

    /**
     * day을 설정
     * 
     * @param day 을(를) String day로 설정
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Boolean isSaturDay을 반환
     * 
     * @return Boolean isSaturDay
     */
    public Boolean getIsSaturDay() {
        return isSaturDay;
    }

    /**
     * isSaturDay을 설정
     * 
     * @param isSaturDay 을(를) Boolean isSaturDay로 설정
     */
    public void setIsSaturDay(Boolean isSaturDay) {
        this.isSaturDay = isSaturDay;
    }

    /**
     * Boolean isHoliday을 반환
     * 
     * @return Boolean isHoliday
     */
    public Boolean getIsHoliday() {
        return isHoliday;
    }

    /**
     * isHoliday을 설정
     * 
     * @param isHoliday 을(를) Boolean isHoliday로 설정
     */
    public void setIsHoliday(Boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     * List<SchdulVO> schdulList을 반환
     * 
     * @return List<SchdulVO> schdulList
     */
    public List<SchdulVO> getSchdulList() {
        return schdulList;
    }

    /**
     * schdulList을 설정
     * 
     * @param schdulList 을(를) List<SchdulVO> schdulList로 설정
     */
    public void setSchdulList(List<SchdulVO> schdulList) {
        this.schdulList = schdulList;
    }

}
