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

<!DOCTYPE html>
<html lang="ko">
<head>
<title>자원파일 정보</title>

<c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
    <c:choose>
        <c:when test="${fileVo.fileExtnNm eq 'css'}">
            <link rel="stylesheet" type="text/css" href="${fileVo.fileUrlAddr}" />
        </c:when>
        <c:when test="${fileVo.fileExtnNm eq 'js'}">
            <script type="text/javascript" src="${fileVo.fileUrlAddr}"></script>
        </c:when>
    </c:choose>
</c:forEach>

</head>
<body>
    ${dataVo.contsCn}
</body>
</html>