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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>
<%@ page import="zesinc.intra.user.support.UserConsts" %>
<c:set value="<%=UserConsts.LOG_USE_YN%>" var="logUseYn" />
<c:set value="<%=UserConsts.LOG_TYPE%>" var="logType" />
<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 로그 목록 조회</title>
<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/openworks4/js/openworks.date.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/intra/user/css/userHist.css">
<script type="text/javascript">
    //<![CDATA[
    var logUseYn = "${logUseYn}";
    var logType = "${logType}";
    $(document).ready(function() {
        $(".mo_why").hide();

        $("#dataForm").submit(function() {
            if (!opCheckDate()) {
                return false;
            }
            $("#q_currPage").val("1");
            return true;
        });
    });

    var customValidate = function() {

        return true;
    };
    //]]>
</script>
</head>
<body>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
               <button type="button" id="excelDown" class="btn btn-info" onclick="opExcelDownForm('user-hist-list-O');">엑셀출력</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectUserLogList.do" class="form-inline">
            <input type="hidden" id="idNum" value=""/>
            <input type="hidden" id="reasonLayerYn" value="N"/>
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_menuSeCd" id="q_menuSeCd" value="${paramMap.q_menuSeCd}" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchKey" class="control-label">검색메뉴</label>
                        <select id="q_searchKey" name="q_searchKey" class="select" style="width: 160px;">
                            <option <c:if test="${paramMap.q_searchKey == 'ALL'}">selected="selected"</c:if> value="ALL">전체</option>
                            <c:forEach items="${menuNmList}" var="menuNmVo">
                                <option value="${menuNmVo.menuNm}" <c:if test="${menuNmVo.menuNm eq paramMap.q_searchKey}">selected="selected"</c:if>>${menuNmVo.menuNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchKey1" class="control-label">검색조건</label>
                        <select id="q_searchKey1" name="q_searchKey1" class="select" style="width: 120px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <option <c:if test="${paramMap.q_searchKey1 == 1001}">selected="selected"</c:if> value="1001">전체</option>
                            <option <c:if test="${paramMap.q_searchKey1 == 1002}">selected="selected"</c:if> value="1002">출력자</option>
                            <option <c:if test="${paramMap.q_searchKey1 == 1003}">selected="selected"</c:if> value="1003">출력자ID</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="control-label">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%@include file="/WEB-INF/jsp/common/include/INC_dateSearchTerms.jsp" %>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="사용자 로그 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자 로그 목록 으로 순번,메뉴명,출력내용,출력일시,출력자,출력자ID,출력사유 정보를 제공합니다.">
            <caption class="hidden">사용자 로그 목록</caption>
            <colgroup>
                <col width="5%" />
                <col width="17%" />
                <col width="10%" />
                <col width="15%" />
                <col width="8%" />
                <col width="8%" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>메뉴명</th>
                    <th>출력내용</th>
                    <th>출력일시</th>
                    <th>출력자</th>
                    <th>출력자ID</th>
                    <th>출력사유</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>${dataVo.menuNm}</td>
                        <td>${dataVo.otptNm}</td>
                        <td class="text-center"><fmt:formatDate value="${dataVo.logUnqNo}" pattern="yyyy-MM-dd" /></td>
                        <td>${dataVo.picNm}</td>
                        <td>${dataVo.picId}</td>
                        <td>
                            <div class="po_rel">
                                <a href="#" onclick="reasonLayer('${index-status.index}'); return false;">${dataVo.logCn}</a>
                                <div id="mo_why_${index-status.index}" class="mo_why" onclick="reasonLayer('${index-status.index}'); return false;">
                                    <h5>출력사유</h5>
                                    <pre>${dataVo.logCn}</pre>
                                </div>
                            </div>
                        </td>
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
    <form name="logForm" id="logForm" method="post">
        <input type="hidden" name="q_searchKey1" value="${paramMap.q_searchKey1}" />
        <input type="hidden" name="q_searchKey" value="${paramMap.q_searchKey}" />
        <input type="hidden" name="q_searchVal" value="${paramMap.q_searchVal}" />
        <input type="hidden" name="q_menuSeCd" value="${paramMap.q_menuSeCd}" />
    </form>
</body>
</html>