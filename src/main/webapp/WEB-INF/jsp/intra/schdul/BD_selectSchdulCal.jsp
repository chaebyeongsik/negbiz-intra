<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>일정 관리 - 달력보기</title>

<style type="text/css">
.day-line {
    padding: 0px;
    margin-top: -2px;
    margin-right: -2px;
    float: right;
}

.label-day {
    font-weight: normal;
    z-index: 1000;
}

.opSchdulList {
    
}

.table-schdul>thead>tr>th {
    color: #FFFFFF; 
    background-color: #8AA0A9;
    text-align: center;
    font-weight: bold;
    font-size: 12pt;
    width: 14.28%;
}

.table-schdul>tbody>tr>td {
    padding: 3px;
    min-height: 150px;
    vertical-align: top;
    width: 14.28%;
}
</style>

<op:jsTag type="intra" items="opform, ui" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/schdul/js/schdul.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

        opSelected("q_year", "${paramMap.q_year}", "");
        opSelected("q_month", "${paramMap.q_month}", "");

        // 빈화면 클릭시 해당 일로 등록화면 팝업
        $(".opScchdulDayTd").click(function(event) {
            var date = $(this).attr("data-schdul-day");
            var seCdId = "${paramMap.q_seCdId}";
            var href = "PD_updateSchdulForm.do?q_schdulDate=" + date + "&q_seCdId" + seCdId;
            $.fn.opmodal({
                href : href,
                width : 800
            });
        });
        // 등록화면
        $("#registBtn").opmodal({
            href : "PD_updateSchdulForm.do",
            width : 800
        });
        // 수정화면
        $(".opSchdulPopup").click(function(event) {
            var href = $(this).attr("href");
            $.fn.opmodal({
                href : href,
                width : 800
            });
            event.stopPropagation();
        });
    });

    //]]>
</script>
</head>
<body>
    <div id="tabs" class="tabbable page-tabs">
        <ul class="nav nav-tabs">
            <li><a href="BD_selectSchdulList.do">목록으로 보기</a></li>
            <li class="active"><a href="BD_selectSchdulCal.do">달력으로 보기</a></li>
        </ul>
    </div>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectSchdulCal.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_seCdId" class="control-label">구분</label>
                        <op:cdId type="select" hghrkCdId="schdul" cdId="se" id="q_seCdId" values="${paramMap.q_seCdId}" etc=" style=\"width: 100px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_hldySeCdId" class="control-label">휴일구분</label>
                        <op:cdId type="select" hghrkCdId="schdul" cdId="restdeSe" id="q_hldySeCdId" values="${paramMap.q_hldySeCdId}" etc=" style=\"width: 100px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_schdlSeCdId" class="control-label">일정구분</label>
                        <op:cdId type="select" hghrkCdId="schdul" cdId="schdulSe" id="q_schdlSeCdId" values="${paramMap.q_schdlSeCdId}" etc=" style=\"width: 100px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="">제목</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <div class="form-group">
                        <label for="q_year" class="control-label">년도</label>
                        <select name="q_year" id="q_year" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${yearList}" var="years">
                                <option value="${years}">${years}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_month" class="control-label">월</label>
                        <select name="q_month" id="q_month" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${monthList}" var="month">
                                <option value="${month}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                    <!-- <button type="button" class="btn btn-success" id="registBtn">등록</button> -->
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성
                <op:pagerParam title="일정  목록" ignores="" />
                --%>
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <table class="table table-bordered table-schdul">
            <thead>
                <tr>
                    <th class="text-danger">일요일</th>
                    <th>월요일</th>
                    <th>화요일</th>
                    <th>수요일</th>
                    <th>목요일</th>
                    <th>금요일</th>
                    <th class="text-info">토요일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dataList}" var="monthList" varStatus="monthIdx">
                    <tr>
                        <c:forEach items="${monthList}" var="weekList" varStatus="weekIdx">
                            <td class="opScchdulDayTd" data-schdul-day="${weekList.year}-${weekList.month}-${weekList.day}">
                                <div class="day-line">
                                    <%-- 휴일 주말 평일 문자 디자인 --%>
                                    <c:choose>
                                        <c:when test="${paramMap.q_month eq weekList.month}">
                                            <strong> <c:choose>
                                                    <c:when test="${weekList.isHoliday}">
                                                        <span class="label label-danger">${weekList.day}</span>
                                                    </c:when>
                                                    <c:when test="${weekList.isSaturDay}">
                                                        <span class="label label-info">${weekList.day}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-primary">${weekList.day}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </strong>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${weekList.isHoliday}">
                                                    <span class="label label-default label-day">${weekList.day}</span>
                                                </c:when>
                                                <c:when test="${weekList.isSaturDay}">
                                                    <span class="label label-default label-day">${weekList.day}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="label label-default label-day">${weekList.day}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <c:if test="${not empty weekList}">
                                    <ul>
                                        <c:forEach items="${weekList.schdulList}" var="dataVo" varStatus="dayIdx">
                                            <li><a href="PD_updateSchdulForm.do?q_regSn=${dataVo.regSn}" onclick="return false;" style="background-color: ${dataVo.colorNm}; padding:3px;" class="opSchdulPopup">${dataVo.ttl}</a></li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- 버튼 -->
    <div class="row block">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-success" id="registBtn">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->

</body>
</html>