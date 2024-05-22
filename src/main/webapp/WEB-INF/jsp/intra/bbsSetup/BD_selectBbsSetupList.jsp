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
<%@ page import="zesinc.intra.bbsSetup.support.BbsSetupConstant" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판환경설정 관리</title>

<op:jsTag type="intra" items="ui, opform" />

<link rel="stylesheet" type="text/css" href="/resources/intra/bbsSetup/css/bbsSetup-custom.css">
<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("q_searchKey", "${paramMap.q_searchKey}");
        
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectBbsSetupList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">게시판명</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="게시판 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <form name="listForm" id="listForm" method="post" action="ND_deleteBbsSetupList.do" class="form-inline">
        <!-- 리스트 -->
        <table class="table table-bordered op-table-list config-list" summary="게시판환경설정 목록으로 순번,게시판명,게시판설명,사용여부,분류사용여부 첨부파일사용여부, 의견글사용여부, 이전/다음글 사용여부, 게시물관리 정보를 제공합니다.">
            <caption class="hidden">게시판환경설정 목록</caption>
            <colgroup>
                <col width="5%" />
                <col width="5%" />
                <col width="*" />
                <col width="12%" />
                <col width="12%" />
                <col width="12%" />
            </colgroup>
            <thead>
                <tr>
                    <th><input type="checkbox" id="chkall" name="chkall" /></th>
                    <th>순번</th>
                    <th>게시판환경설정명</th>
                    <th>사용여부</th>
                    <th>등록자명</th>
                    <th>등록일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <c:set var="style" value="${status.count % 2 != 1 ? 'bg-style' : ''}" />
                    <tr class="${style}">
                        <td class="text-center">
                            <input type="checkbox" id="bbsStngSns${status.count}" name="bbsStngSns" value="${dataVo.bbsStngSn}" />
                        </td>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <div class="block">
                                <a href="BD_selectBbsSetup.do?q_bbsStngSn=${dataVo.bbsStngSn}">${dataVo.bbsStngNm}</a>
                            </div>
                            <div>
                                ${dataVo.bbsStngExpln}
                            </div>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'Y'}">
                                    <span class="label label-success">사용</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-danger">미사용</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="6" />
            </tbody>
        </table>
        <!-- //리스트 -->
        </form>
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="block clearfix">
            <div class="col-sm-12 btn-group">
                <div class="pull-left">
                    <button type="button" class="btn btn-danger" onclick="opCheckDelete();">선택삭제</button>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-info" onclick="opInsertForm('BD_insertBbsSetupForm.do');">신규등록</button>
                </div>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>