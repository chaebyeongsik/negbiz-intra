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
<title>관리자변경이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/change/js/change.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        opSelected("q_logType", "${paramMap.q_logType}", "");
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectChangeList.do" class="form-inline">
            <input type="hidden" name="q_picId" value="${paramMap.q_picId}" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_logType" class="control-label">유형</label>
                        <select name="q_logType" id="q_logType" class="select" style="width: 120px;">
                            <option value="">-- 유형선택 --</option>
                            <option value="I">등록</option>
                            <option value="U">수정</option>
                            <option value="D">삭제</option>
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
                <op:pagerParam title="관리자변경이력 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list"
            summary="관리자변경이력  목록으로 순번,담당자,로그일시,로그유형,부서명,담당자명,직급명,담당업무,전화번호,팩스번호,휴대전화번호,이메일,
            로그인건수,로그인일시,사용여부,알림이실행여부,이메일통지여부,SMS통지여부,비밀번호변경일시,인증계정,등록자,등록일시,수정자,수정일시 정보를 제공합니다.">
            <caption class="hidden">관리자변경이력 목록</caption>
            <colgroup>
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="120px" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="100px" />
                <col width="" />
                <col width="" />
            </colgroup>
            <thead>
                <!-- <tr>
                    <th>순번</th>
                    <th>담당자</th>
                    <th>로그일시</th>
                    <th>로그유형</th>
                    <th>부서명</th>
                    <th>직급명</th>
                    <th>담당업무</th>
                    <th>전화번호</th>
                    <th>팩스번호</th>
                    <th>휴대전화번호</th>
                    <th>이메일</th>
                    <th>로그인건수</th>
                    <th>로그인일시</th>
                    <th>사용여부</th>
                    <th>알림이실행여부</th>
                    <th>이메일통지여부</th>
                    <th>SMS통지여부</th>
                    <th>비밀번호변경일시</th>
                    <th>인증계정</th>
                    <th>등록자명</th>
                    <th>등록일시</th>
                    <th>수정자명</th>
                    <th>수정일시</th>
                </tr> -->
                <tr>
					<th rowspan="2">순번</th>
					<th rowspan="2">담당자</th>
					<th>로그일시</th>
					<th>부서명</th>
					<th rowspan="2">담당업무</th>
					<th>전화번호</th>
					<th>휴대전화번호</th>
					<th>로그인건수</th>
					<th>사용여부</th>
					<th>이메일<br>통지여부</th>
					<th>비밀번호<br>변경일시</th>
					<th>등록자명</th>
					<th>수정자명</th>
				</tr>
				<tr>
					<th>로그유형</th>
					<th>직급명</th>
					<th>팩스번호</th>
					<th>이메일</th>
					<th>로그인일시</th>
					<th>알림이<br>실행여부</th>
					<th>SMS<br>통지여부</th>
					<th>인증계정</th>
					<th>등록일시</th>
					<th>수정일시</th>
				</tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td rowspan="2" class="text-center">${index-status.index}</td>
                        <td rowspan="2" class="text-center">${dataVo.picNm}(${dataVo.picId})</td>
                        <td><fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td class="text-center">${dataVo.deptNm}</td>
                        <td rowspan="2">
                            <op:nrToBr content="${dataVo.taskCn}"/>
                        </td>
                        <td class="text-center">
                            <c:if test="${not empty dataVo.rgnTelno}">
                                ${dataVo.rgnTelno}-${dataVo.telofcTelno}-${dataVo.indivTelno}
                            </c:if>
                        </td>
                        <td class="text-center">
                            <c:if test="${not empty dataVo.mblRgnTelno}">
                                ${dataVo.mblRgnTelno}-${dataVo.mblTelofcTelno}-${dataVo.mblIndivTelno}
                            </c:if>
                        </td>
                        <td class="text-center">${dataVo.lgnNmtm}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'Y'}">사용</c:when>
                                <c:otherwise>미사용</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.emlAvtsmtYn eq 'Y'}">사용</c:when>
                                <c:otherwise>미사용</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center"><fmt:formatDate value="${dataVo.pswdChgDt}" pattern="yyyy-MM-dd" /></td>
                        <td class="text-center">${dataVo.rgtrNm}(${dataVo.rgtrId})</td>
                        <td class="text-center">${dataVo.updusrNm}(${dataVo.mdfrId})</td>
                    </tr>
                    <tr>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.logType eq 'I'}">등록</c:when>
                                <c:when test="${dataVo.logType eq 'U'}">수정</c:when>
                                <c:when test="${dataVo.logType eq 'D'}">삭제</c:when>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.clsfNm}</td>
                        <td class="text-center">
                            <c:if test="${not empty dataVo.rgnFxno}">
                                ${dataVo.rgnFxno}-${dataVo.telofcFxno}-${dataVo.indivFxno}
                            </c:if>
                        </td>
                        <td class="text-center">
                            <c:if test="${not empty dataVo.emlId}">
                                ${dataVo.emlId}@${dataVo.emlSiteNm}
                            </c:if>
                        </td>
                        <td class="text-center"><fmt:formatDate value="${dataVo.lgnDt}" pattern="yyyy-MM-dd" /></td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.avtsmtExcnYn eq 'Y'}">사용</c:when>
                                <c:otherwise>미사용</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.smsAvtsmtYn eq 'Y'}">사용</c:when>
                                <c:otherwise>미사용</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${dataVo.acntNm}</td>
                        <td class="text-center"><fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" /></td>
                        <td class="text-center"><fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="13" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>