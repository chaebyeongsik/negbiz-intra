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

    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="get" action="PD_selectPrhibtWrdHistoryList.do">
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />
    </form>
    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="금지단어이력 정보입니다.">
            <caption class="hidden">금지단어이력 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <tbody>
                <tr>
                    <th>로그일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th>로그유형</th>
                    <td>
                        <c:choose>
                            <c:when test="${dataVo.logType eq 'I'}">등록</c:when>
                            <c:when test="${dataVo.logType eq 'U'}">수정</c:when>
                            <c:when test="${dataVo.logType eq 'D'}">삭제</c:when>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>금지단어코드</th>
                    <td>
                        <code>${dataVo.phbwdCdId}</code>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${dataVo.ttl}</td>
                </tr>
                <tr>
                    <th>금지단어내용</th>
                    <td><op:nrToBr content="${dataVo.phbwdCn}"/></td>
                </tr>
                <tr>
                    <th>사용여부</th>
                    <td>
                        <c:choose>
                            <c:when test="${dataVo.useYn eq 'Y'}">사용</c:when>
                            <c:otherwise>
                                <span class="text-danger">미사용</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>수정자명</th>
                    <td>${dataVo.updusrNm}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-primary" onclick="opList('PD_selectPrhibtWrdHistoryList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>