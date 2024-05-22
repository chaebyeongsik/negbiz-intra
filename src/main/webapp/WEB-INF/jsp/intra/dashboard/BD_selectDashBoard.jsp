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
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>DashBoard</title>

<op:jsTag type="intra" items="ui" />
<op:jsTag type="libs" items="form" />

<script type="text/javascript">
    //<![CDATA[

    var $tabs;
    var options = {
        // 내용을 표시할 패널을 지정. 생략 가능
        tabContainer : "tabContainer",
        // 공통 파라미터를 지정할 수 있음. 생략 가능
        baseParam : {
            q_date : new Date()
        }
    };

    /*
     * 공통 초기화 기능
     */
    $(document).ready(function() {
        $tabs = $("#opTabs").opTabs(options);

        if ("${INIT_CHECK}" != "true" || "${EXPIRED_CHECK}" == "true") {
            opChangePasswordForm('');
        }
    });

    // 현재 텝 컨텐츠 리로드
    var opReloadTab = function() {
        $tabs.refresh();
    };

    // 초기비밀번호일경우 수정폼 띄움
    var opChangePasswordForm = function (type) {
        if ("${INIT_CHECK}" != "true") {
            alert('현재 사용하고 계신 비밀번호는 기본 비밀번호입니다.\n 비밀번호를 변경해주세요.');
        }

        if ("${EXPIRED_CHECK}" == "true") {
            alert('비밀번호가 만료되었습니다.\n비밀번호를 변경해주세요.');
        }

        $().opmodal({
            width : 800,
            height : 600,
            href : "/intra/mngr/PD_updateChargerForm.do"
        });
    }

    //]]>
</script>
</head>
<body>

    <div id="opTabs" class="tabbable block">
        <ul class="nav nav-tabs">
            <li><a href="<c:url value="INC_selectCmsCntnsList.do"/>"><i class="icon-paragraph-justify2"></i> 컨텐츠관리</a></li>
            <li><a href="<c:url value="INC_selectCmsEvlList.do"/>"><i class="icon-stats2"></i> 만족도</a></li>
        </ul>
    </div>

</body>
</html>