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
<title>사용자 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userGrad.js"></script>

<script type="text/javascript">
    //<![CDATA[
    <valid:script type="msgbox" />
    $(document).ready(function() {
        var pUserGrdCdId = '${param.userGrdCdId}';
        if(pUserGrdCdId != ''){
            opUpdateUserGradForm(pUserGrdCdId);
        }
    });

    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 사용자 등급 목록
                    </h6>
                </div>
                <ul class="list-group">
                <c:forEach var="userGradVo" items="${dataList}" varStatus="vst">
                    <li class="list-group-item has-button">
                        <a href="#" onclick="opUpdateUserGradForm('${userGradVo.userGrdCdId}'); return false;">${userGradVo.userGrdNm}</a>
                    </li>
                </c:forEach>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="button" id="insertFormBtn" class="btn btn-info" onclick="opInsertUserGradForm();">사용자 등급 추가</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 사용자 등급 상세
                    </h6>
                </div>
                <div id="userGrad_View" class="panel-body">
                    <div class="callout callout-danger fade in" <c:if test="${not empty param.userGrdCdId}">style="display: none;"</c:if>>
                        <h5>항목이 선택되지 않았습니다.</h5>
                        <p>좌측 사용자 등급 목록에서 선택하시거나 추가 버튼을 클릭하세요.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>