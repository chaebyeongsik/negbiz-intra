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

<html lang="ko">
<head>
<title>컨텐츠 관리</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<op:jsTag type="libs" items="jquery, contentbuilder" />

<c:forEach var="cssFileNm" items="${cssFiles}">
    <link rel="stylesheet" type="text/css" href="${cssFileNm}" />
</c:forEach>

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#contentarea").contentbuilder({
            zoom : 0.85,
            snippetOpen : true,
            snippetFile : "${SNIPPET_FILE}",
            imageselect : "${IMAGE_SELECT}",
            fileselect : "${FILE_SELECT}"
        });
    });

    // 오프너 창에 opReceiveContentBuilder 함수를 통하여 html을 전송
    var opTransmitContents = function() {
        if(opener) {
            var html = $('#contentarea').data('contentbuilder').html();
            if(!opener.opReceiveContentBuilder) {
                opErrorMsg("부모윈도우의 화면이 변경되어 편집중인 내용을 저장할 수 없습니다.\n\r[소스보기] 기능을 통하여 별도로 저장 하시기 바랍니다.");
            } else {
                opener.opReceiveContentBuilder(html);
                self.close();
            }
        } else {
            opErrorMsg("부모윈도우를 확인할 수 없어 편집중인 내용을 저장할 수 없습니다.\n\r[소스보기] 기능을 통하여 별도로 저장 하시기 바랍니다.");
        }
    };

    //]]>
</script>

<c:if test="${not empty param.q_header}">
    ${param.q_header}
</c:if>

</head>
<body style="margin: 65px 40px 0px 40px;">
    <div class="row left">
        <button type="button" class="btn btn-primary" style="padding: 0px 5px 0px 5px;" onclick="opTransmitContents();">편집완료</button>
        <strong>
        적용중인 CSS 파일 : <br />
        <c:forEach var="cssFileNm" items="${cssFiles}">
            ${cssFileNm}<br />
        </c:forEach>
        </strong>
    </div>
    <br />
    <br />
    <div>
        <div id="contentarea" class="container">
            ${param.q_content}
        </div>
    </div>
</body>
</html>