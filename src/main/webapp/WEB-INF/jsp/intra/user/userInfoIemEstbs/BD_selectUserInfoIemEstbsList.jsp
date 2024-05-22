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
<%@ page import="zesinc.intra.user.support.UserType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자정보항목설정 관리</title>
<c:set value="<%=UserType.INDVDL.getUserType()%>" var="indvdl" />
<c:set value="<%=UserType.ENTRPRS.getUserType()%>" var="entrprs" />
<op:jsTag type="intra" items="ui, opform" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userInfoIemEstbs.js"></script>

<script type="text/javascript">
    //<![CDATA[
    var $tabs;
    var userTypeNm = '${param.userTypeNm}';
    var options = {
            loadTabIdx : (userTypeNm == '${entrprs}' ? 1 : 0)
    };
    $(document).ready(function() {
        $tabs = $("#opTabs").opTabs(options);
    });
    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-sm-12">
            <div id="opTabs" class="tabbable page-tabs">
                <ul class="nav nav-tabs">
                <c:if test="${param.userTypeNm eq indvdl}"><c:set value="class=\"active\"" var="classIndvdl" /></c:if>
                <c:if test="${param.userTypeNm eq entrprs}"><c:set value="class=\"active\"" var="classEntrprs" /></c:if>
                    <li ${classIndvdl}><a href="INC_selectUserInfoIemEstbs.do?userTypeNm=${indvdl}">개인</a></li>
                    <li ${classEntrprs}><a href="INC_selectUserInfoIemEstbs.do?userTypeNm=${entrprs}">기업</a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>