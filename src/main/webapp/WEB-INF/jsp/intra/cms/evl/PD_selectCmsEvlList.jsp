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
<title>사용자메뉴평가 관리</title>

<op:jsTag type="intra" items="ui, opform, opChart, opPie" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/cmsEvl/js/cmsEvl.js"></script>
-->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    var pieChart;
    var pieChartDataUrl1 = "ND_selectCmsEvlChartList.do?q_siteSn=${paramMap.q_siteSn}&q_userMenuEngNm=${paramMap.q_userMenuEngNm}&q_startDt=${paramMap.q_startDt}&q_endDt=${paramMap.q_endDt}";
    var pieChartOption1 = {
            graphType : "donut",
            target : "opPieChartDiv",
            dataUrl : pieChartDataUrl1
    };
    $(document).ready(function() {
        pieChart = opChartPie(pieChartOption1);
    });
    //]]>
</script>

</head>
<body>
    <div class="block"></div>

    <div class="text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectCmsEvlList.do" class="form-inline">
            <input type="hidden" name="q_siteSn" id="q_siteSn" value="${paramMap.q_siteSn}" />
            <input type="hidden" name="q_userMenuEngNm" id="q_userMenuEngNm" value="${paramMap.q_userMenuEngNm}" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_startDt" class="">시작일</label>
                        <input type="text" class="from-date form-control" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" maxlength="10" placeholder="시작일" />
                    </div>
                    <div class="form-group">
                        <label for="q_endDt" class="">종료일</label>
                        <input type="text" class="to-date form-control" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" maxlength="10" placeholder="종료일" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
            </fieldset>
        </form>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-calendar2"></i> 사용자메뉴평가통계
            </h6>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-pills nav-themes navbar-right">
                        <li class="am-theme-default"><a title="3D형보기" href="#" onclick="opChange2D3D(this, pieChart, pieChartOption1);return false;" class="change-change-theme" ></a></li>
                        <li class="am-theme-patterns"><a title="패턴형보기" href="#" onclick="opChangeTheme(this, pieChart, pieChartOption1, 'patterns');return false;" class="change-change-theme"></a></li>
                        <li class="am-theme-chalk"><a title="칠판형보기" href="#" onclick="opChangeTheme(this, pieChart, pieChartOption1, 'chalk');return false;" class="change-change-theme"></a></li>
                        <li class="am-theme-dark"><a title="어둡게보기" href="#" onclick="opChangeTheme(this, pieChart, pieChartOption1, 'dark');return false;" class="change-change-theme"></a></li>
                        <li class="am-theme-light active"><a title="밝게보기" href="#" onclick="opChangeTheme(this, pieChart, pieChartOption1, 'light');return false;" class="change-change-theme"></a></li>
                        <li class="am-theme-black"><a title="검은색보기" href="#" onclick="opChangeTheme(this, pieChart, pieChartOption1, 'black');return false;" class="change-change-theme"></a></li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div id="opPieChartDiv" class="col-md-12"></div>
            </div>
        </div>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자메뉴평가  목록으로 항목,총점,총응답수,등록일자 정보를 제공합니다.">
            <caption class="hidden">사용자메뉴평가 목록</caption>
            <colgroup>
                <col width="" />
                <col width="15%" />
                <col width="15%" />
            </colgroup>
            <thead>
                <tr>
                    <th>항목</th>
                    <th>총점</th>
                    <th>총응답수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dataList}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.artclSn eq '5'}">매우만족(5점)</c:when>
                                <c:when test="${dataVo.artclSn eq '4'}">만족(4점)</c:when>
                                <c:when test="${dataVo.artclSn eq '3'}">보통(3점)</c:when>
                                <c:when test="${dataVo.artclSn eq '2'}">불만족(2점)</c:when>
                                <c:otherwise>매우불만족(1점)</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-right">${dataVo.sumScr}점</td>
                        <td class="text-right">
                            <a href="<c:url value="/intra/cms/evl/PD_selectCmsEvlDetailList.do" />?q_siteSn=${dataVo.siteSn}&amp;q_userMenuEngNm=${dataVo.userMenuEngNm}&amp;q_artclSn=${dataVo.artclSn}&amp;q_startDt=${paramMap.q_startDt}&amp;q_endDt=${paramMap.q_endDt}" class="opmodal">${dataVo.wholRspnsCnt} 명</a>
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${dataList}" colspan="3" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
</body>
</html>