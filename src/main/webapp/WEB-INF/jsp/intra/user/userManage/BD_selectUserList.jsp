<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page import="zesinc.intra.user.support.UserType" %>
<%@ page import="zesinc.intra.user.support.UserConsts" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<c:set value="<%=UserType.INDVDL.getUserType()%>" var="indvdl" />
<c:set value="<%=UserType.ENTRPRS.getUserType()%>" var="entrprs" />
<c:set value="<%=UserConsts.LOG_TYPE%>" var="logType" />
<c:set value="<%=UserConsts.LOG_USE_YN%>" var="logUseYn" />

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 관리</title>

<op:jsTag type="intra" items="opform" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/user.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>

<script type="text/javascript">
	//<![CDATA[
	var logType = "${logType}";
	var logUseYn = "${logUseYn}";
	var requestURI = '/intra/user/userManage/BD_selectUserList.do';

	$(document).ready(function() {

	});
	var opReload = function(){
		location.reload();
	};
	var opListSubmit = function(){
		$("#dataForm").submit();
	};
	//]]>
</script>
</head>
<body>
	<div class="row block">
		<div class="col-md-12 btn-group">
			<div class="pull-right">
			<c:choose>
				<c:when test="${not empty pager}">
					<button type="button" id="excelDown" class="btn btn-info" onclick="opExcelDownForm('user-list');">엑셀출력</button>
				</c:when>
				<c:otherwise>
					<button type="button" id="excelDown" class="btn btn-info" onclick="opMsg('사용자목록을 검색하여 주십시오.');">엑셀출력</button>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<div class="block text-center">
		<form name="logForm" id="logForm" method="post">
			<input type="hidden" name="q_searchKey0" value="${paramMap.q_searchKey0}" />
			<input type="hidden" name="q_searchKey1" value="${paramMap.q_searchKey1}" />
			<input type="hidden" name="q_searchKey2" value="${paramMap.q_searchKey2}" />
			<input type="hidden" name="q_searchKey" value="${paramMap.q_searchKey}" />
			<input type="hidden" name="q_searchVal" value="${paramMap.q_searchVal}" />
			<input type="hidden" name="excelKey" value="user-list" />
			<input type="hidden" name="menuNm" value="사용자관리목록" />
			<input type="hidden" name="menuSn" value="83" />
		</form>
		<form name="dataForm" id="dataForm" method="post" action="BD_selectUserList.do" class="form-inline">
			<fieldset>
				<legend class="sr-only">목록검색조건</legend>
				<input type="hidden" name="menuNm" id="menuNm" value="사용자관리목록" />
				<input type="hidden" name="menuSn" id="menuSn" value="83" />
				<input type="hidden" name="searchId" id="searchId" value="userManage" />
				<input type="hidden" name="excelKey" id="excelKey" value="user-list" />
				<div class="block" id="search">
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
								<option<c:if test="${userGradVo.userGrdCdId eq paramMap.q_searchKey2}"> selected="selected"</c:if> value="${userGradVo.userGrdCdId}">${userGradVo.userGrdNm}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="q_searchKey" class="sr-only">검색 항목</label>
						<select id="q_searchKey" name="q_searchKey" class="select" style="width: 100px;">
							<option value="">검색 항목</option>
							<option <c:if test="${paramMap.q_searchKey eq 1001}">selected="selected"</c:if> value="1001">사용자명</option>
							<option <c:if test="${paramMap.q_searchKey eq 1002}">selected="selected"</c:if> value="1002">사용자ID</option>
						</select>
					</div>
					<div class="form-group">
						<label for="q_searchVal" class="sr-only">검색어</label>
						<input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
						<input type="hidden" name="userIds" id="userIds" value="" />
						<input type="hidden" name="userTypeNms" id="userTypeNms" value="" />
					</div>
					<button type="button" class="btn btn-info" onclick="opSearchLogForm();">검색</button>
					<button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
				</div>
				<%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
				<c:if test="${not empty pager}">
					<op:pagerParam title="사용자 목록" ignores="" />
				</c:if>
			</fieldset>
		</form>
	</div>

	<c:choose>
		<c:when test="${not empty pager}">
			<div class="block table-responsive">
				<!-- 리스트 -->
				<table class="table table-bordered table-striped table-hover op-table-list"
					summary="사용자 목록으로 선택,순번,사용자ID,사용자명,가입일시,최근접속일시,로그인건수,사용자등급,사용자가입유형 정보를 제공합니다."
				>
					<caption class="hidden">사용자 목록</caption>
					<colgroup>
						<col width="6%"/>
						<col width="6%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="14%"/>
						<col width="14%"/>
						<col width="7%"/>
						<col width="*%"/>
						<col width="10%"/>
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" value="Y" name="chk-all" id="chk-all" /></th>
							<th>순번</th>
							<th>사용자ID</th>
							<th>사용자명</th>
							<th>가입일시</th>
							<th>최근접속일시</th>
							<th>로그인건수</th>
							<th>사용자등급</th>
							<th>사용자가입유형</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="${pager.indexNo}" />
						<c:forEach items="${pager.list}" var="dataVo" varStatus="status">
							<tr>
								<td class="text-center"><input type="checkbox" name="sns" value="${dataVo.userId}" /></td>
								<td class="text-center">${index-status.index}</td>
								<td>
									<a href="#" onclick="opUserInfoPopup('${dataVo.userId}','${dataVo.userTypeNm}' );">${dataVo.userIdntfNm}</a>
									<input type="hidden" name="userTypeNm" value="${dataVo.userTypeNm}"/>
								</td>
								<td>${dataVo.userNm}</td>
								<td class="text-center"><fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="text-center"><fmt:formatDate value="${dataVo.lastCntnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="text-center"><fmt:formatNumber value="${dataVo.lgnNmtm}" type="number" /></td>
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
						<op:no-data obj="${pager}" colspan="9" />
					</tbody>
				</table>
				<!-- //리스트 -->
			</div>
			<!-- 페이징 -->
			<op:pager pager="${pager}" />
			<!-- //페이징 -->
		</c:when>
		<c:otherwise>
			<div class="panel panel-default">
				<div class="panel-heading"><h6 class="panel-title">안내</h6></div>
				<div class="panel-body">
					사용자 데이터를 확인하고자 할 경우 검색을 이용하여 주십시오.
				</div>
			</div>
		</c:otherwise>
	</c:choose>

	<!-- 버튼 -->
	<div class="block clearfix">
		<div class="col-sm-12 btn-group">
		<c:if test="${not empty pager}">
			<div class="pull-left">
				<button type="button" class="btn btn-danger" onclick="opDelete();">삭제</button>
			</div>
		</c:if>
			<div class="pull-right">
				<button type="button" class="btn btn-success" onclick="opInsertIndvdlForm();">개인사용자등록</button>
				<button type="button" class="btn btn-success" onclick="opInsertEntrprsForm();">사업자사용자등록</button>
			</div>
		</div>
	</div>
	<!-- //버튼 -->

</body>
</html>