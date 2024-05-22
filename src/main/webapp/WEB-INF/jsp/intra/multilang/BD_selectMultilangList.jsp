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
<title>다국어 관리</title>

<op:jsTag type="intra" items="tree, opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/multilang/js/multilang.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /*
     * 공통 초기화 기능
     */
    var contPadId = "Multilang_VIEW";
    var Optree;
    var options = {
        USE_DND : false,
        CONT_PAN_ID : contPadId,
        SRC_URL : "ND_selectMultilangTreeList.do",
        VIEW_URL : "INC_updateMultilangForm.do",
        ROOT_NODE_NM : "다국어목록",
        ROOT_NODE_VAL : OpConfig.defaultCode.highCommonCd,
        P_NODE_NM : "q_upMtlngCdNm",
        NODE_NM : "q_mtlngCdNm"
    };
    $(document).ready(function() {
        // 트리생성
        Optree = $("#TreePan").optree(options);

        // 초기 상세화면 영역 안내 메시지 표시
        initView(contPadId);

        // 코드 등록/수정폼 오픈 
        $("#addModal").opmodal({
            width : 930
        });
    });

    // 상세정보 등록 수정 폼
    var opInsertForm = function(url) {
        $("#" + contPadId).load(url, params, function(result) {
        });
    };

    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-primary">
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
            <div class="row">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <a href="PD_selectMultilangList.do" class="btn btn-info" id="addModal">다국어추가</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 다국어상세
                    </h6>
                </div>
                <div id="Multilang_VIEW" class="panel-body"></div>
            </div>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 다국어 목록에서 선택하시거나 추가 버튼을 클릭하세요.</p>
        </div>
    </div>
</body>
</html>