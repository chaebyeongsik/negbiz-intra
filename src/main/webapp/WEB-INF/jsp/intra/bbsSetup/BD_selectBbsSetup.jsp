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
<title>게시판환경설정 관리</title>

<op:jsTag type="intra" items="ui" />
<op:jsTag type="libs" items="docs" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#opTabs").opTabs();
    });

    //]]>
</script>
</head>
<body>

<div id="opTabs" class="tabbable">
    <ul class="nav nav-tabs block">
        <li><a href="/intra/bbsSetup/INC_updateBbsGlobalSetupForm.do?q_bbsStngSn=${paramMap.q_bbsStngSn}">기본정보</a></li>
        <li><a href="/intra/bbsSetup/INC_updateBbsListSetupForm.do?q_bbsStngSn=${paramMap.q_bbsStngSn}">목록설정</a></li>
        <li><a href="/intra/bbsSetup/INC_updateBbsViewSetupForm.do?q_bbsStngSn=${paramMap.q_bbsStngSn}">상세조회설정</a></li>
        <li><a href="/intra/bbsSetup/INC_updateBbsFormSetupForm.do?q_bbsStngSn=${paramMap.q_bbsStngSn}">입력폼설정</a></li>
        <li><a href="/intra/bbsSetup/INC_updateBbsItemSetupForm.do?q_bbsStngSn=${paramMap.q_bbsStngSn}">항목설정</a></li>
    </ul>
    <div class="well">
        <blockquote>
            <p><%-- ${dataVo.bbsStngSn} : --%> ${dataVo.bbsStngNm}</p>
        </blockquote>
    </div>
</div>

</body>
</html>