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
<%@ page import="zesinc.web.support.tag.bbsTmplat.support.BbsStyleSupport"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:choose>
    <c:when test="${bbsType eq 'list'}">
        <button type="button" class="btn btn-info" onclick="opToggleSumry();">요약보기</button>
        <button type="button" class="btn btn-primary" onclick="opInsertForm();">등록</button>
        <!-- 메뉴권한관리로 이양 -->
        <%-- <op:auth needAuthType="MANAGER"> --%>
            <button type="button" class="btn btn-danger" onclick="opDeleteList('${bbsConfigVo.bbsSn}');">완전삭제</button>
            <button type="button" class="btn btn-danger" onclick="opFlagDeleteList('${bbsConfigVo.bbsSn}');">플래그삭제</button>
        <%-- </op:auth> --%>
    </c:when>
    <c:when test="${bbsType eq 'view'}">
        <!-- 메뉴권한관리로 이양 -->
        <%-- <op:auth needAuthType="MANAGER"> --%>
            <button type="button" class="btn btn-primary" onclick="opMoveBbsPopup();">게시물이동</button>
            <c:choose>
                <c:when test="${bbsObj.mngrDelYn eq 'Y'}">
                    <button type="button" class="btn btn-danger" onclick="opFlagDelete('N');">플래그삭제복구</button>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn btn-danger" onclick="opFlagDelete('Y');">플래그삭제</button>
                </c:otherwise>
            </c:choose>
        <%-- </op:auth> --%>
        <c:if test="${bbsConfigVo.lyrUseYn eq 'Y' and bbsObj.ntcPstYn ne 'Y'}">
            <button type="button" class="btn btn-primary" onclick="opReplyForm();">답글</button>
        </c:if>
        <!-- 메뉴권한관리로 이양 -->
        <op:auth picId="${bbsObj.rgtrId}">
            <button type="button" class="btn btn-success" onclick="opUpdateForm();">수정</button>
            <button type="button" class="btn btn-danger" onclick="opDelete();">삭제</button>
        </op:auth>
        <button type="button" class="btn btn-info" onclick="opList();">목록</button>
    </c:when>
    <c:when test="${bbsType eq 'form'}">
        <button type="submit" class="btn btn-success">저장</button>
        <button type="button" class="btn btn-info" onclick="opList();">목록</button>
    </c:when>
</c:choose>
