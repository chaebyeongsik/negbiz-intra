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
<title>게시판템플릿 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbstmplat/js/bbstmplat.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>
    <c:set var="tmpltTypeCd" value="목록템플릿" />
    <c:if test="${paramMap.q_tmpltTypeCd eq 'V'}"><c:set var="tmpltTypeCd" value="읽기템플릿" /></c:if>
    <c:if test="${paramMap.q_tmpltTypeCd eq 'F'}"><c:set var="tmpltTypeCd" value="쓰기템플릿" /></c:if>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectBbsTmplatList.do" class="form-inline">
            <input type="hidden" name="q_tmpltTypeCd" id="q_tmpltTypeCd" value="${paramMap.q_tmpltTypeCd}" />
            <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
            <op:pagerParam title="${tmpltTypeCd} 목록" ignores="" />
        </form>
    </div>

    <div class="tab-pane">
        <c:set var="startDiv" value="<div class='row'>" />
        <c:set var="endDiv" value="</div>" />
        <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
            <c:set var="modVal" value="${status.index % 4}" />
            <c:if test="${modVal eq 0}">
                ${startDiv}
            </c:if>
            <div class="col-md-3">
                <div class="block">
                    <div class="thumbnail thumbnail-boxed">
                        <div class="thumb">
                            <c:choose>
                                <c:when test="${empty dataVo.tmpltScrnPathNm}">
                                    <img src="/resources/openworks4/theme/default/images/commons/noImg.gif" alt="등록된 이미지가 없습니다." width="300" height="300" />
                                </c:when>
                                <c:otherwise>
                                    <img src="${dataVo.tmpltScrnPathNm}" width="300" height="300" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="caption">
                            <p>
                                템플릿명 : <a href="BD_updateBbsTmplatForm.do?q_tmpltTypeCd=${dataVo.tmpltTypeCd}&q_tmpltId=${dataVo.tmpltId}">${dataVo.tmpltNm} (${dataVo.tmpltId})</a>
                            </p>
                            <p>
                                적용된게시판 :
                                <c:if test="${paramMap.q_tmpltTypeCd eq 'L'}">${dataVo.listApplcBbsCo}</c:if>
                                <c:if test="${paramMap.q_tmpltTypeCd eq 'V'}">${dataVo.redngApplcBbsCo}</c:if>
                                <c:if test="${paramMap.q_tmpltTypeCd eq 'F'}">${dataVo.formApplcBbsCo}</c:if>
                            </p>
                            <p>
                                등록일시 : <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${modVal eq 3}">
                ${endDiv}
            </c:if>
        </c:forEach>
        <c:if test="${empty pager.list}">
                <table class="table table-bordered block" summary="등록된 게시물이 없습니다.">
                    <op:no-data obj="${pager}"/>
                </table>
        </c:if>
        <c:if test="${modVal ne 3}">
            ${endDiv}
        </c:if>
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn-info" onclick="opBbsTmplatInsertForm();">템플릿 등록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </div>
    <!-- 버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>