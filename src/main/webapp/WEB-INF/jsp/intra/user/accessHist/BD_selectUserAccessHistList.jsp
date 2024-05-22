<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page import="zesinc.intra.user.support.UserType"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>
<%@ page import="zesinc.intra.user.support.UserConsts"%>
<c:set value="<%=UserConsts.LOG_USE_YN%>" var="logUseYn" />
<c:set value="<%=UserConsts.LOG_TYPE%>" var="logType" />
<c:set value="<%=UserType.INDVDL.getUserType()%>" var="indvdl" />
<c:set value="<%=UserType.ENTRPRS.getUserType()%>" var="entrprs" />

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 관리</title>

<op:jsTag type="intra" items="ui, opform, opChart, opBar" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/openworks4/js/openworks.date.js"></script>
<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userAccessHist.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/analyze/js/analyze.common.js"></script>

<script type="text/javascript">
	//<![CDATA[
	var logUseYn = "${logUseYn}";
	var logType = "${logType}";
	var chartData = [
		<c:forEach items="${accessStatistics}" var="dataVo" varStatus="status">
			{ label : "로그인한 사용자 수", value : "${dataVo.lgnMbrCnt}" },
			{ label : "G-PIN 로그인 수", value : "${dataVo.gpinCntnNmtm}" },
			{ label : "공인인증서 로그인 수", value : "${dataVo.certCntnNmtm}" },
			{ label : "일반인증 로그인 수", value : "${dataVo.rnameCertCntnNmtm}" }
		</c:forEach>
	];

	var columnChartOption = {
		graphType : "column",
		categoryTitle : [{position : "bottom", title : "당일 접속 현황"}],
		data : chartData,
		useLegend : false
	};

	var columnChart;


	/* 공통 초기화 기능 */
	$(document).ready(function() {
		$("#statisticsBtn").click(function() {
			$('#statisticsDiv').slideToggle(200, function() {
			});
		});

		columnChart = opChartColumn(columnChartOption);

		setDatePicker();

		$("#dataForm").submit(function() {
			if (!opCheckDate()) {
				return false;
			}
			$("#q_currPage").val("1");
			var dataString = $("#dataForm").serialize();
			var action = $(this).attr("action");
			opAnalyzeDetailView(action, dataString);
			return true;
		});
	});

	//]]>
</script>
</head>
<body>
	<div class="row">
		<div class="col-sm-12 btn-group">
			<div class="pull-right">
				<button type="button" class="btn btn-primary" id="statisticsBtn">통계그래프열기</button>
			</div>
		</div>
	</div>
	<div id="statisticsDiv" class="row" style="display: none;margin: 10px 0px;" >
		<div class="panel panel-default">
			<div class="panel-heading">
				<h6 class="panel-title">
					<i class="icon-calendar2"></i> 당일 접속통계
				</h6>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-7">
						<div class="row">
							<div class="col-md-12">
								<ul class="nav nav-pills nav-themes navbar-right">
									<li class="am-theme-default"><a title="3D형보기" href="#" onclick="opChange2D3D(this, columnChart, columnChartOption);return false;" class="change-change-theme"></a></li>
									<li class="am-theme-patterns"><a title="패턴형보기" href="#" onclick="opChangeTheme(this, columnChart, columnChartOption, 'patterns');return false;" class="change-change-theme"></a></li>
									<li class="am-theme-chalk"><a title="칠판형보기" href="#" onclick="opChangeTheme(this, columnChart, columnChartOption, 'chalk');return false;" class="change-change-theme"></a></li>
									<li class="am-theme-dark"><a title="어둡게보기" href="#" onclick="opChangeTheme(this, columnChart, columnChartOption, 'dark');return false;" class="change-change-theme"></a></li>
									<li class="am-theme-light active"><a title="밝게보기" href="#" onclick="opChangeTheme(this, columnChart, columnChartOption, 'light');return false;" class="change-change-theme"></a></li>
									<li class="am-theme-black"><a title="검은색보기" href="#" onclick="opChangeTheme(this, columnChart, columnChartOption, 'black');return false;" class="change-change-theme"></a></li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div id="opColumnChartDiv" class="col-md-12"></div>
						</div>
					</div>
					<div id="" class="col-md-5">
						<div class="block table-responsive">
							<!-- 리스트 -->
							<table class="table table-bordered table-striped table-hover op-table-list">
								<caption class="hidden">당일 접속자 목록</caption>
								<colgroup>
									<col width="" />
									<col width="20%" />
									<col width="20%" />
									<col width="20%" />
								</colgroup>
								<thead>
									<tr>
										<th>로그인한 사용자 수</th>
										<th>G-PIN 로그인 수</th>
										<th>공인인증서 로그인 수</th>
										<th>일반인증 로그인 수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="dataVo" items="${accessStatistics}" varStatus="stauts">
										<tr>
											<td class="text-right">${dataVo.lgnMbrCnt}</td>
											<td class="text-right">${dataVo.gpinCntnNmtm}</td>
											<td class="text-right">${dataVo.certCntnNmtm}</td>
											<td class="text-right">${dataVo.rnameCertCntnNmtm}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty accessStatistics}">금일 접속한 회원이 없습니다.</c:if>
								</tbody>
							</table>
							<!-- //리스트 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="block text-center">
		<form name="dataForm" id="dataForm" method="get" action="BD_selectUserAccessHistList.do" class="form-inline">
			<fieldset>
				<legend class="sr-only">목록검색조건</legend>
				<input type="hidden" name="q_regSn" id="q_regSn" value="" />
				<input type="hidden" name="menuNm" id="menuNm" value="사용자접속이력관리" />
				<input type="hidden" name="menuSn" id="menuSn" value="87" />
				<div class="block">
					<div class="form-group">
						<label for="q_searchKey0" class="sr-only">사용자구분</label>
						<select id="q_searchKey0" name="q_searchKey0" class="select" style="width: 130px;">
							<option value="">사용자구분</option>
							<option <c:if test="${paramMap.q_searchKey0 eq indvdl}">selected="selected"</c:if> value="${indvdl}">개인</option>
							<option <c:if test="${paramMap.q_searchKey0 eq entrprs}">selected="selected"</c:if> value="${entrprs}">사업자</option>
						</select>
					</div>
					<div class="form-group">
						<label for="q_searchKey1" class="sr-only">사용자가입유형</label>
						<op:cdId type="select" hghrkCdId="joinTypeSn" cdId="joinTypeSn" id="q_searchKey1" defaultOption="사용자가입유형" values="${paramMap.q_searchKey1}" etc="style=\"width: 130px;\"" />
					</div>
					<div class="form-group">
						<label for="q_searchKey2" class="sr-only">사용자등급</label>
						<select id="q_searchKey2" name="q_searchKey2" class="select" style="width: 130px;">
							<option value="">사용자등급선택</option>
							<c:forEach items="${userGradInUseList}" var="userGradVo" varStatus="status">
								<option <c:if test="${userGradVo.userGrdCdId eq paramMap.q_searchKey2}"> selected="selected"</c:if> value="${userGradVo.userGrdCdId}">${userGradVo.userGrdNm}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="q_searchKey" class="sr-only">검색 항목</label>
						<select id="q_searchKey" name="q_searchKey" class="select" style="width: 100px;">
							<option value="1001">검색 항목</option>
							<option <c:if test="${paramMap.q_searchKey eq 1002}">selected="selected"</c:if> value="1002">사용자명</option>
							<option <c:if test="${paramMap.q_searchKey eq 1003}">selected="selected"</c:if> value="1003">사용자ID</option>
						</select>
					</div>
					<div class="form-group">
						<label for="q_searchVal" class="sr-only">검색어</label>
						<input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
					</div>
					<button type="submit" class="btn btn-info">검색</button>
					<button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
				</div>
				<%@include file="/WEB-INF/jsp/common/include/INC_dateSearchTerms.jsp"%>
				<%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
				<op:pagerParam title="사용자 목록" ignores="" />
			</fieldset>
		</form>
	</div>

	<div class="block table-responsive">
		<!-- 리스트 -->
		<table class="table table-bordered table-striped table-hover op-table-list" summary="사용자 목록으로 순번,사용자ID,사용자명,가입일시,최근접속일시,로그인건수,사용자등급,가입유형 정보를 제공합니다.">
			<caption class="hidden">사용자접속이력 목록</caption>
			<colgroup>
				<col width="" />
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
					<th>사용자ID</th>
					<th>사용자명</th>
					<th>가입일시</th>
					<th>최근접속일시</th>
					<th>로그인건수</th>
					<th>사용자등급</th>
					<th>가입유형</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="index" value="${pager.indexNo}" />
				<c:forEach items="${pager.list}" var="dataVo" varStatus="status">
					<tr>
						<td class="text-center">${index-status.index}</td>
						<td>
							<a href="#" onclick="opUserAccessInfoPop('${dataVo.userId}');">${dataVo.userIdntfNm}</a>
						</td>
						<td>${dataVo.userNm}</td>
						<td class="text-center">
							<fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td class="text-center">
							<fmt:formatDate value="${dataVo.lastCntnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td class="text-center">${dataVo.lgnNmtm}</td>
						<td class="text-center">
							<c:set value="" var="userGrdNms" />
							<c:forTokens items="${dataVo.userGrdCdDsctn}" delims="," var="gradCodeVal">
								<c:forEach items="${userGradList}" var="userGradVo" varStatus="vst">
									<c:if test="${gradCodeVal eq userGradVo.userGrdCdId}">
										<c:set var="userGrdNms">
											 ${userGrdNms}<c:if test="${not empty userGrdNms}">, </c:if>
											 ${userGradVo.userGrdNm}
										</c:set>
									</c:if>
								</c:forEach>
							</c:forTokens>
							<c:out value="${userGrdNms}" />
						</td>
						<td>
							<c:if test="${not empty dataVo.joinTypeSn}">
								<op:cdId hghrkCdId="joinTypeSn" cdId="${dataVo.joinTypeSn}" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<op:no-data obj="${pager}" colspan="8" />
			</tbody>
		</table>
		<!-- //리스트 -->
	</div>

	<!-- 페이징 -->
	<op:pager pager="${pager}" />
	<!-- //페이징 -->

	<!-- 버튼 -->
	<div class="row">
		<div class="col-sm-12 btn-group">
			<div class="pull-right">
				<button type="button" id="excelDown" class="btn btn-info" onclick="opExcelDownForm('user-access-list-all');">엑셀출력</button>
			</div>
		</div>
	</div>
	<!-- //버튼 -->

	<form name="logForm" id="logForm" method="post">
		<input type="hidden" name="q_searchKey0" value="${paramMap.q_searchKey0}" />
		<input type="hidden" name="q_searchKey1" value="${paramMap.q_searchKey1}" />
		<input type="hidden" name="q_searchKey2" value="${paramMap.q_searchKey2}" />
		<input type="hidden" name="q_searchKey" value="${paramMap.q_searchKey}" />
		<input type="hidden" name="q_searchVal" value="${paramMap.q_searchVal}" />
	</form>
</body>
</html>