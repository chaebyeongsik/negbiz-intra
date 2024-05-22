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
<title>다국어조회</title>

<op:jsTag type="intra" items="tree, opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/multilang/js/multilang.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var contPadId = "Multilang_VIEW";
    // 추가로 등록된 항목 카운트
    var insertCnt = 0;
    // 트리 객체
    var Optree;
    // 예제 옵션 위의 주석을 참조하여 필요한 설정을 하도록 한다. 
    var options = {
        USE_DND : false,
        USE_ROOT_VIEW : true,
        CONT_PAN_ID : contPadId,
        SRC_URL : "ND_selectConnectMultilangTreeList.do",
        VIEW_URL : "INC_updateConnectMultilangForm.do",
        ROOT_NODE_NM : "다국어목록",
        ROOT_NODE_VAL : "${paramMap.q_upMtlngCdNm}",
        P_NODE_NM : "q_upMtlngCdNm",
        NODE_NM : "q_mtlngCdNm"
    };

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 트리 생성
        Optree = $("#TreePan").optree(options);
        // 초기 상세화면 영역 안내 메시지 표시
        initView(contPadId);
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
                        <i class="icon-list2"></i> 다국어목록
                    </h6>
                    <div class="panel-icons-group">
                        <a href="#" class="btn btn-link btn-icon" onclick="reload();"><i class="icon-spinner7"></i></a> <a href="#" class="btn btn-link btn-icon" onclick="expandAll();"><i
                            class="icon-plus-circle"
                        ></i></a> <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();"><i class="icon-minus-circle"></i></a>
                    </div>
                </div>
                <div class="panel-body">
                    <div id="TreePan"></div>
                </div>
            </div>
        </div>

        <div class="col-sm-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 다국어조회
                    </h6>
                </div>
                <div id="Multilang_VIEW" class="panel-body"></div>
            </div>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 다국어 목록에서 선택하세요.</p>
        </div>
    </div>
</body>
</html>