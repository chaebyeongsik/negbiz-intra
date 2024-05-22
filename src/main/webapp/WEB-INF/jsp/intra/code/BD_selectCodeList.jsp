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
<title>코드 관리</title>

<op:jsTag type="intra" items="tree, opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/code/js/code.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var contPadId = "Code_VIEW";
    var Optree;
    var options = {
        USE_ROOT_VIEW : false,
        CONT_PAN_ID : contPadId,
        SRC_URL : "ND_selectCodeTreeList.do",
        VIEW_URL : "INC_updateCodeForm.do",
        MOVE_URL : "ND_updateCodeSortOrder.do",
        ROOT_NODE_NM : "코드목록",
        ROOT_NODE_VAL : OpConfig.defaultCode.highCommonCd,
        BASE_PARAM_NM : "q_hghrkCdId",
        BASE_PARAM_VAL : OpConfig.defaultCode.highCommonCd,
        P_NODE_NM : "q_upCdId",
        NODE_NM : "q_cdId",
        ORDER_NM : "q_sortSn",
        lazyLoad : function(event, data) {
            var node = data.node;
            param = {};
            param[options.P_NODE_NM] = node.key;
            if(node.data.baseKey) {
                var baseKey = node.data.baseKey;
                if(node.data.baseKey == options.BASE_PARAM_VAL) {
                    baseKey = node.key;
                }
                param[options.BASE_PARAM_NM] = baseKey;
            }

            data.result = {
                url : options.SRC_URL,
                data : param,
                cache : false
            };
        }
    };

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 트리 생성
        Optree = $("#TreePan").optree(options);

        // 초기 상세화면 영역 안내 메시지 표시
        initView(contPadId);

        // 코드 등록/수정폼 오픈
        $("#addModal").opmodal({width : 950});
        // 다국어조회
        $("#multiLang").click(function(event) {
            var option = "chrome, centerscreen, dependent=yes, width=950, height=650, dialog=yes, modal=yes, ";
            option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
            var href = $(this).attr("href");
            var multiLangWin = window.open(href, "multiLang", option);
            multiLangWin.focus();

            return false;
        });

        // 코드 검색/출력
        $("#searchCode").click(function(event) {
            var option = "chrome, centerscreen, dependent=yes, width=1024, height=650, dialog=yes, modal=yes, ";
            option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
            var href = $(this).attr("href");
            var multiLangWin = window.open(href, "searchCode", option);
            multiLangWin.focus();

            return false;
        });
    });

    /*
     * 마우스 클릭시 기본 이벤트 처리 함수
     */
    var treeView = function(event, data, options) {
        if (!options.VIEW_URL) {
            return;
        }

        var params = {};
        params[options.BASE_PARAM_NM] = data.node.data.baseKey;
        params[options.P_NODE_NM] = data.node.parent.key;
        params[options.NODE_NM] = data.node.key;

        $("#" + options.CONT_PAN_ID).load(options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
            if(statusTxt == "success") {
                if (typeof afterTreeView == "function") {
                    afterTreeView(data.node.key, data.node.parent.key, response);
                 }
            } else {
                opSysErrorMsg(responseTxt);
            }
        }, "json");
    };

    /* 트리이동시 제한 조건 설정 */
    var treeMoveEnter = function(node, data, options) {
        if (!options.MOVE_URL) {
            return false;
        }
        // 메뉴 이동은 동일 레벨에서 순서만 변경
        if(node.parent.key == OpConfig.defaultCode.highCommonCd || node.parent.key != data.otherNode.parent.key) {
            return false;
        }
        return ["before", "after"];
    };

    var treeMove = function(node, data, options) {
        var params = {};
        // 현재 노드의 hghrkCdId
        params[options.BASE_PARAM_NM] = data.otherNode.data.baseKey;
        // 현재 노드의 upCdId
        params[options.P_NODE_NM] = data.otherNode.parent.key;
        // 현재 노드의 cdId
        params[options.NODE_NM] = data.otherNode.key;
        // 순서 번호
        params[options.ORDER_NM] = data.otherNode.getIndex();

        $.ajax({
            type: "get",
            dataType : "json",
            url: options.MOVE_URL,
            data: params,
            success: function(response, statusTxt, xhr){
                opJsonMsg(response);
                // 초기 상세화면 영역 안내 메시지 표시
                initView(contPadId);
            },
            error: function(response,status,error) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
    };
    //]]>
</script>
</head>
<body>

    <div class="row block">
        <div class="col-md-12 btn-group">
            <div class="pull-right">
                <a href="PD_selectSearchCodeList.do" class="btn btn-info" id="searchCode">코드 검색/출력</a>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 코드목록
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
                <div class="col-sm-12 btn-group">
                    <div class="pull-right">
                        <%-- <a href="/intra/multilang/PD_selectConnectMultilangList.do?q_upMtlngCdNm=dummy" class="btn btn-info" id="multiLang">다국어조회</a> --%>
                        <a href="PD_selectCodeList.do" class="btn btn-info" id="addModal">코드추가</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 코드상세
                    </h6>
                </div>
                <div id="Code_VIEW" class="panel-body"></div>
            </div>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 코드 목록에서 선택하시거나 추가 버튼을 클릭하세요.</p>
        </div>
    </div>
</body>
</html>