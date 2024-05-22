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

<op:jsTag type="intra" items="ui, opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/cmsEvl/js/cmsEvl.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

        $("#q_rowPerPage").select2({
            minimumResultsForSearch : "-1"
        });

        $(".from-date").datepicker({
            dateFormat: "yy-mm-dd",
            numberOfMonths : 2,
            showOtherMonths : true,
            onClose : function(selectedDate) {
                $(".to-date").datepicker("option", "minDate", selectedDate);
            }
        });
        $(".to-date").datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate : "+1w",
            numberOfMonths : 2,
            showOtherMonths : true,
            onClose : function(selectedDate) {
                $(".from-date").datepicker("option", "maxDate", selectedDate);
            }
        });

        $("#dataForm").submit(function() {
            var startDt = $("#q_startDt").val();
            var endDt = $("#q_endDt").val();

            var url = "<c:url value="/intra/cms/evl/INC_selectCmsEvlList.do"/>?q_startDt=" + startDt + "&q_endDt=" + endDt;

            var selectedIndex = $tabs.currentIndex();
            $tabs.changeUrl(selectedIndex, url);
            $tabs.loadTab(selectedIndex);

            return false;
        });

        $(".opmodal").opmodal({width : 900, height : 600});
    });

    //]]>
</script>
</head>
<body>
    <div class="block"></div>

    <div class="text-center">
        <form name="dataForm" id="dataForm" method="get" action="INC_selectCmsEvlList.do" class="form-inline">
            <input type="hidden" name="q_regSn" id="q_regSn" value="" />
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
                        <td class="text-right">${dataVo.sumScr} 점</td>
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