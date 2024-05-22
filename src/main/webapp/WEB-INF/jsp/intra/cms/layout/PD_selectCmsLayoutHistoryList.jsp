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
<title>사용자메뉴레이아웃이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/cmsLayoutHistory/js/cmsLayoutHistory.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectCmsLayoutHistoryList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">페이징 조건</legend>
                <op:pagerParam view="view" ignores="q_currPage,q_pagePerPage,q_rowPerPage,q_sortName,q_sortOrder" />
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="사용자 로그 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list"
            summary="${template.joinTableComment} 목록으로 순번,레이아웃코드,도메인코드,도메인명,로그일련번호,로그유형,로그일시,레이아웃명,레이아웃컨텐츠,파일경로,수정자ID,수정자명, 정보를 제공합니다."
        >
            <caption class="hidden">사용자메뉴레이아웃이력 목록</caption>
            <colgroup>
                <col width="8%" />
                <col width="10%" />
                <col width="15%" />
                <col width="25%" />
                <col width="" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>로그유형</th>
                    <th>로그일시</th>
                    <th>사이트</th>
                    <th>레이아웃명</th>
                    <th>수정자명</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.logType eq 'I'}">등록</c:when>
                                <c:when test="${dataVo.logType eq 'U'}">수정</c:when>
                                <c:when test="${dataVo.logType eq 'D'}">삭제</c:when>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td class="text-center">${dataVo.siteExpln}</td>
                        <td>
                            <a href="PD_selectCmsLayoutHistory.do?q_lytCdNo=${dataVo.lytCdNo}&amp;q_siteSn=${dataVo.siteSn}&amp;q_logSn=${dataVo.logSn}">${dataVo.lytNm}</a>
                        </td>
                        <td class="text-center">${dataVo.updusrNm}</td>
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