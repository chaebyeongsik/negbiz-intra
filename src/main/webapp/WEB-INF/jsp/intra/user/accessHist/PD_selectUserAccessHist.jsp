<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>
<%@ page import="zesinc.intra.user.support.UserConsts" %>
<%@ page import="zesinc.intra.user.support.UserType" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<c:set value="<%=UserType.INDVDL.getUserType()%>" var="indvdl" />
<c:set value="<%=UserType.ENTRPRS.getUserType()%>" var="entrprs" />
<c:set value="<%=UserConsts.LOG_USE_YN%>" var="logUseYn" />

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 관리</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/openworks4/js/openworks.date.js"></script>
<script src="http://static.pureexample.com/js/flot/excanvas.min.js"></script>
<script src="http://static.pureexample.com/js/flot/jquery.flot.min.js"></script>
<script src="http://static.pureexample.com/js/flot/jquery.flot.pie.min.js"></script>

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/user/js/userAccessHist.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>
<script type="text/javascript">
	//<![CDATA[
	var logUseYn = "${logUseYn}";

	/* 공통 초기화 기능 */
	$(document).ready(function() {

	});

	//]]>
</script>
</head>
<body>
	<div class="block text-center">
		<form name="dataForm" id="dataForm" method="get" action="PD_selectUserAccessHist.do" class="form-inline">
			<input type="hidden" name="q_userId" id="q_userId" value="${paramMap.q_userId}" />
			<fieldset>
				<legend class="sr-only">목록검색조건</legend>

				<%@include file="/WEB-INF/jsp/common/include/INC_dateSearchTerms.jsp"%>

				<button type="submit" class="btn btn-info">검색</button>

				<%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
				<op:pagerParam title="사용자 목록" ignores="" />
			</fieldset>
		</form>
	</div>

	<div class="block table-responsive">
		<!-- 리스트 -->
		<table class="table table-bordered table-striped table-hover op-table-list" summary="사용자 목록으로 접속일시, 인증방식, 결과, 아이피 정보를 제공합니다.">
			<caption class="hidden">사용자접속이력 목록</caption>
			<colgroup>
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>접속일시</th>
					<th>인증방식</th>
					<th>결과</th>
					<th>아이피</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="index" value="${pager.indexNo}" />
				<c:forEach items="${pager.list}" var="dataVo" varStatus="status">
					<tr>
						<td class="text-center">${index-status.index}</td>
						<td class="text-center"><fmt:formatDate value="${dataVo.lgnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="text-center"><c:if test="${not empty dataVo.joinTypeSn}"><op:cdId hghrkCdId="joinTypeSn" cdId="${dataVo.joinTypeSn}"/></c:if></td>
						<td class="text-center"><c:if test="${not empty dataVo.lgnRsltNo}"><op:cdId hghrkCdId="loginResult" cdId="${dataVo.lgnRsltNo}" /></c:if></td>
						<td class="text-center">${dataVo.lgnIpAddr}</td>
					</tr>
				</c:forEach>
				<op:no-data obj="${pager}" colspan="5" />
			</tbody>
		</table>
		<!-- //리스트 -->
	</div>

	<!-- 페이징 -->
	<op:pager pager="${pager}" />
	<!-- //페이징 -->

	<!-- 버튼 -->
	<!-- //버튼 -->
	<!--
	<div class="row">
		<div class="col-xs-12 btn-group">
			<div class="pull-right">
				<button type="button" id="excelDown" class="btn btn-info" onclick="opExcelDownForm('user-access-list');">엑셀출력</button>
			</div>
		</div>
	</div>
	 -->

	<form name="logForm" id="logForm" method="post">
		<input type="hidden" name="excelKey" value="user-access-list" />
		<input type="hidden" name="menuNm" value="사용자접속이력관리-개별목록" />
		<input type="hidden" name="menuSn" value="87" />
	</form>

</body>
</html>