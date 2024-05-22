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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>지정 권한 메뉴목록</title>

    <script type="text/javascript">
        //<![CDATA[

        /* 공통 초기화 기능 */
        $(document).ready(function() {
        });

        //]]>
    </script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h6 class="panel-title">${paramMap.q_authrtNm}</h6>
        <span class="pull-right badge">지정권한 : ${fn:length(dataList)}건</span>
    </div>
        
    <div class="panel-body">
        <div class="block table-responsive">
        <!-- 리스트 -->
            <table class="table table-bordered table-striped table-hover op-table-list" summary="지정 권한 메뉴목록으로 메뉴명,사용권한 정보를 제공합니다.">
                <caption class="hidden">지정 권한 메뉴목록</caption>
                <colgroup>
                    <col width="" />
                    <col width="" />
                </colgroup>
                <thead>
                    <tr>
                        <th>메뉴명</th>
                        <th>사용권한</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dataList}" var="dataVo" varStatus="status">
                        <tr>
                            <td>${dataVo.menuNm}</td>
                            <td class="text-center">
                                <c:forEach items="${authList}" var="auth">
                                    <c:if test="${dataVo.authrtGrntCdId eq auth.cdId}">${auth.name}</c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${dataList}" colspan="2" msg="권한 지정된 메뉴가 없습니다." />
                </tbody>
            </table>
        <!-- //리스트 -->
        </div>
    </div>
</div>

</body>
</html>