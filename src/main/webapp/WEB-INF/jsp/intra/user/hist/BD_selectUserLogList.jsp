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
<%@ page import="zesinc.intra.user.support.UserConsts"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>
<c:set value="<%=UserConsts.LOG_USE_YN%>" var="logUseYn" />
<c:set value="<%=UserConsts.LOG_TYPE%>" var="logType" />

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 로그 목록 조회</title>
<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>
<script type="text/javascript">
    //<![CDATA[
    var logUseYn = "${logUseYn}";
    var logType = "${logType}";
    $(document).ready(function() {

    });

    var customValidate = function() {
        return true;
    };
    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectUserLogList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="menuSeCd" id="menuSeCd" value="${param.menuSeCd}" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchKey" class="control-label">검색조건</label>
                        <select id="q_searchKey" name="q_searchKey" class="over">
                            <option <c:if test="${paramMap.q_searchKey == 1001}">selected="selected"</c:if> value="1001">전체</option>
                            <option <c:if test="${paramMap.q_searchKey == 1002}">selected="selected"</c:if> value="1002">사용자명</option>
                            <option <c:if test="${paramMap.q_searchKey == 1003}">selected="selected"</c:if> value="1003">사용자ID</option>
                            <option <c:if test="${paramMap.q_searchKey == 1004}">selected="selected"</c:if> value="1004">조회자</option>
                            <option <c:if test="${paramMap.q_searchKey == 1005}">selected="selected"</c:if> value="1005">조회자ID</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="control-label">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="사용자 로그 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <c:choose>
            <c:when test="${param.menuSeCd eq 'R'}">
                <c:set value="메뉴명,ID,성명,조회일시,조회자,조회ID,조회사유" var="summaryVal" />
            </c:when>
            <c:when test="${param.menuSeCd eq 'O'}">
                <c:set value="메뉴명,출력내용,출력일시,출력자,출력자ID,출력사유" var="summaryVal" />
            </c:when>
            <c:otherwise>
                <c:set value="ID,성명,변경일시,변경자(변경자ID),변경(수정)내용,변경(수정)사유" var="summaryVal" />
            </c:otherwise>
        </c:choose>
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자 로그 목록 으로 순번,${summaryVal} 정보를 제공합니다.">
            <caption class="hidden">사용자 로그 목록</caption>
            <colgroup>
                <col width="8%" />
                <col width="15%" />
                <col width="15%" />
                <col width="" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>사이트</th>
                    <th>이미지</th>
                    <th>특성</th>
                    <th>상태</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <div class="callout callout-success">
                                <c:forEach items="${dataVo.domnList}" var="domnVo">
                                    <strong>${domnVo.siteExpln}<br /></strong>
                                </c:forEach>
                            </div>
                        </td>
                        <td>
                            <div class="thumbnail thumbnail-boxed">
                                <c:choose>
                                    <c:when test="${not empty dataVo.filePathNm}">
                                        <img alt="" src="${dataVo.filePathNm}">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="holder.js/170x70" alt="이미지없음">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <div class="callout callout-info">
                                <a href="BD_updateBannerForm.do?q_regSn=${dataVo.regSn}" onclick="opView('${dataVo.regSn}');return false;">
                                    <strong>종류 : </strong><code><op:cdId hghrkCdId="banner" cdId="${dataVo.bnnTypeNm}" /></code><br />
                                    <strong>제목 : </strong>${dataVo.ttl}<br />
                                    <strong>표시기간 : </strong>${dataVo.bgngYmd} ~ ${dataVo.endYmd}
                                </a>
                            </div>
<%--                             <fmt:formatDate value="${dataVo.bgngYmd}" pattern="yyyy-MM-dd" /> --%>
<!--                             ~ -->
<%--                             <fmt:formatDate value="${dataVo.endYmd}" pattern="yyyy-MM-dd" /> --%>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'N'}">
                                    <span class="label label-danger">미사용</span>
                                </c:when>
                                <c:when test="${dataVo.statusEnd > 0}">
                                    <span class="label label-danger">종료</span>
                                </c:when>
                                <c:when test="${dataVo.statusBegin < 0}">
                                    <span class="label label-default">대기</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-success">진행중</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
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
</body>
</html>