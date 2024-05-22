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
<title>관리자접속이력 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/connect/js/connect.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        opSelected("q_logSeNo", "${paramMap.q_logSeNo}", "");
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectConnectList.do" class="form-inline">
            <input type="hidden" name="q_picId" value="${paramMap.q_picId}" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_logSeNo" class="control-label">구분</label>
                        <select name="q_logSeNo" id="q_logSeNo" class="select" style="width: 120px;">
                            <option value="">-- 구분선택 --</option>
                            <option value="I">로그인</option>
                            <option value="O">로그아웃</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_startDt" class="control-label">시작일</label>
                        <input type="text" class="from-date form-control" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" />
                    </div>
                    <div class="form-group">
                        <label for="q_endDt" class="control-label">종료일</label>
                        <input type="text" class="to-date form-control" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="관리자접속이력 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="관리자접속이력  목록으로 순번,담당자ID,로그일시,로그구분,로그인결과,로그인IP 정보를 제공합니다.">
            <caption class="hidden">관리자접속이력 목록</caption>
            <colgroup>
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>담당자</th>
                    <th>로그일시</th>
                    <th>로그구분</th>
                    <th>결과</th>
                    <th>로그인IP</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>${dataVo.picNm} (${dataVo.picId})</td>
                        <td><fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${dataVo.logSeNo eq 'I'}">
                                    로그인
                                </c:when>
                                <c:otherwise>
                                    로그아웃
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            ${dataVo.loginResultNm}
                        </td>
                        <td>${dataVo.lgnIpAddr}</td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="6" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>