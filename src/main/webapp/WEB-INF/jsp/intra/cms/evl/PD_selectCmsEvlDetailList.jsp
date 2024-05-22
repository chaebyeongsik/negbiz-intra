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
<title>사용자메뉴평가상세 관리</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>

    <form name="dataForm" id="dataForm" method="get" action="PD_selectCmsEvlDetailList.do" class="form-inline">
        <input type="hidden" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" />
        <input type="hidden" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" />
        <input type="hidden" name="q_siteSn" id="q_siteSn" value="${paramMap.q_siteSn}" />
        <input type="hidden" name="q_userMenuEngNm" id="q_userMenuEngNm" value="${paramMap.q_userMenuEngNm}" />
        <input type="hidden" name="q_artclSn" id="q_artclSn" value="${paramMap.q_artclSn}" />
        <fieldset>
            <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
            <op:pagerParam title="사용자메뉴평가상세 목록" ignores="" />
        </fieldset>
    </form>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자메뉴평가상세  목록으로 순번,등록IP,등록자명,등록자ID,등록일,의견글내용 정보를 제공합니다.">
            <caption class="hidden">사용자메뉴평가상세 목록</caption>
            <colgroup>
                <col width="10%" />
                <col width="12%" />
                <col width="12%" />
                <col width="12%" />
                <col width="12%" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>등록IP</th>
                    <th>등록자명</th>
                    <th>등록자ID</th>
                    <th>등록일</th>
                    <th>의견글내용</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">${dataVo.rgtrIpAddr}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${empty dataVo.rgtrNm}">비회원</c:when>
                                <c:otherwise>${dataVo.rgtrNm}</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${empty dataVo.rgtrId}"></c:when>
                                <c:otherwise>${dataVo.rgtrId}</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>${dataVo.opnnCn}</td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="6" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-primary" onclick="opList('PD_selectCmsEvlList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>