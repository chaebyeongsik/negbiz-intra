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
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>개인사용자정보 항목 설정 관리</title>
<op:jsTag type="intra" items="opform" />
<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function() {
    });
    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateUserInfoIemEstbs.do" class="form-horizontal">
        <jsp:include page="INC_formFrame.jsp">
            <jsp:param value="기본정보" name="formNm" />
            <jsp:param value="BASIC" name="listType" />
        </jsp:include>
        <jsp:include page="INC_formFrame.jsp">
            <jsp:param value="부가정보" name="formNm" />
            <jsp:param value="ETC" name="listType" />
        </jsp:include>
        <!-- 버튼 -->
        <div class="block clearfix">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <!--                 <button type="button" class="btn btn-success" onclick="opUpdateForm();">수정</button> -->
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>