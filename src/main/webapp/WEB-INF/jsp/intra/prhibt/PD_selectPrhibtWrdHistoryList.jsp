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
<title>금지단어이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/prhibtWrdHistory/js/prhibtWrdHistory.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    /**
     * 상세
     */
    var opView = function(logSn) {
        $("#q_logSn").val(logSn);
        location.href = "PD_selectPrhibtWrdHistory.do?" + opSearchQueryString("dataForm");
    };

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectPrhibtWrdHistoryList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_phbwdCdId" id="q_phbwdCdId" value="${paramMap.q_phbwdCdId}" />
                <input type="hidden" name="q_logSn" id="q_logSn" value="" />

                <div class="block">
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="금지단어이력 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="금지단어이력  목록으로 순번,로그일시,로그유형,금지단어코드,제목,사용여부,수정자명 정보를 제공합니다.">
            <caption class="hidden">금지단어이력 목록</caption>
            <colgroup>
                <col width="8%" />
                <col width="10%" />
                <col width="10%" />
                <col width="12%" />
                <col width="" />
                <col width="10%" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>로그일시</th>
                    <th>로그유형</th>
                    <th>금지단어코드</th>
                    <th>제목</th>
                    <th>사용여부</th>
                    <th>수정자명</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd" />
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.logType eq 'I'}">등록</c:when>
                                <c:when test="${dataVo.logType eq 'U'}">수정</c:when>
                                <c:when test="${dataVo.logType eq 'D'}">삭제</c:when>
                            </c:choose>
                        </td>
                        <td class="text-center"><code>${dataVo.phbwdCdId}</code></td>
                        <td>
                            <a href="PD_selectPrhibtWrdHistory.do?q_logSn=${dataVo.logSn}&amp;q_phbwdCdId=${dataVo.phbwdCdId}"
                                onclick="opView('${dataVo.logSn}');return false;"
                            >${dataVo.ttl}</a>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'Y'}">사용</c:when>
                                <c:otherwise>
                                    <span class="text-danger">미사용</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.updusrNm}</td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="7" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>