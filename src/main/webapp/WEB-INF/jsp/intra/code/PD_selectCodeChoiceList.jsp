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
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>코드선택 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/code/js/code.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 초기 상세화면 영역 안내 메시지 표시
        initView("CdChcId_VIEW");
    });

    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 코드선택목록
                    </h6>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <c:forEach var="dataVo" items="${dataList}" varStatus="index">
                            <li class="list-group-item list-group-item-success"><a href="#"
                                onclick="opUpdateForm('INC_updateCodeChoiceForm.do', {q_hghrkCdId : '${dataVo.hghrkCdId}', q_cdId : '${dataVo.cdId}', q_cdChcId : '${dataVo.cdChcId}'}); return false;"
                            >${dataVo.cdChcId}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 btn-group">
                    <div class="pull-right">
                        <button type="button" class="btn btn-info" onclick="opInsertForm('INC_insertCodeChoiceForm.do', {q_hghrkCdId : '${paramMap.q_hghrkCdId}', q_cdId : '${paramMap.q_cdId}'});">코드선택추가</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 코드선택상세
                    </h6>
                </div>
                <div id="CdChcId_VIEW" class="panel-body"></div>
            </div>
        </div>
    </div>

    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>코드선택이 선택되지 않았습니다.</h5>
            <p>좌측 목록에서 선택하시거나 코드선택추가 버튼을 클릭하세요.</p>
        </div>
    </div>
</body>
</html>