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
<html lang="ko" style="width: 100%; height: 100%">
<head>
<title>팝업 관리</title>

<op:jsTag type="libs" items="jquery, cookie" />
<op:jsTag type="intra" items="base" />
<script type="text/javascript" src="/intra/popup/ND_selectPopupPreview.do?q_regSn=${paramMap.q_regSn}&amp;time=${DATE}"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body style="width: 100%; height: 100%">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class=" callout callout-info fade in">
                        <h5>현재 화면이 팝업이 적용될 본 화면으로 생각하고, 위치 등을 확인하세요.</h5>
                        <dl style="margin: 0;">
                            <dt class="text-info">팝업창 유형</dt>
                            <dd>유형이 팝업창인 경우 현재 미리보기 화면에서 새창이 생성되며 내용이 표시됩니다.</dd>
                            <dt class="text-info">레이어 유형</dt>
                            <dd>유형이 레이어인 경우 브라우저를 기준으로 위치가 설정됩니다. 화면내의 위치를 확인하세요.</dd>
                            <dt class="text-info">상단열림 유형</dt>
                            <dd><code>&lt;body&gt;</code> 태그 바로 다음 위치에 내용이 삽입되며 슬라이드로 열립니다. 크기 속성중 <code>세로크기는 무시</code>됩니다.</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>