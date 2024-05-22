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
<title>사용자메뉴 관리</title>

<op:jsTag type="intra" items="tree, opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var insertCnt = 0;
    var contPadId = "Cms_ADD_VIEW";
    var Optree;
    // 예제 옵션 위의 주석을 참조하여 필요한 설정을 하도록 한다. 
    var options = {
        // ROOT 노드 클릭시 상세화면 표시여부
        USE_ROOT_VIEW : true,
        USE_ROOT_NODE : false,
        // 폴더인 경우 view를 열지 여부 (기본값 true)
        FOLDER_OPEN : true,
        // view가 로드될 패널
        CONT_PAN_ID : contPadId,
        // 트리 데이터 url
        SRC_URL : "ND_selectCmsTreeList.do",
        // view url
        VIEW_URL : "INC_insertCmsForm.do",
        // node move시 호출될 url
        MOVE_URL : "ND_updateCmsSortOrder.do",
        // 기본 표시 명칭
        ROOT_NODE_NM : "사용자메뉴목록",
        // 기본 표시 데이터 키값 (dummy)
        ROOT_NODE_VAL : OpConfig.defaultCode.highCmsCd,
        // 기본적으로 따라다닐 파라미터 명/값
        BASE_PARAM_NM : "q_siteSn",
        BASE_PARAM_VAL : "${paramMap.q_siteSn}",
        // 부모 노드의 파라미터 명칭
        P_NODE_NM : "q_upMenuEngNm",
        P_NODE_VAL : OpConfig.defaultCode.highCmsCd,
        // 현재 노드의 파라미터 명칭
        NODE_NM : "q_userMenuEngNm",
        // 이동시 현재 일련번호 값을 전달할 파라미터 키명칭
        ORDER_NM : "q_sortSn",
        // 사이트별 사용자메뉴 초기 데이터 생성 URL
        INIT_URL : ""
    };

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 도메인 파라미터 존재시 해당 도메인으로 초기화
        Optree = $("#TreePan").optree(options);
        <c:choose>
            <c:when test="${menuCnt gt 1}">
                // 초기 상세화면 영역 안내 메시지 표시
                initView(contPadId);
            </c:when>
            <c:otherwise>
                // 기초 데이터가 없는 경우 사이트 기초 데이터 생성 폼을 연다.
                initSite();
            </c:otherwise>
        </c:choose>
    });

    /*
     * 마우스 클릭시 기본 이벤트 처리 함수
     */
    var treeView = function(event, data, options) {
        if (!options.VIEW_URL) {
            return;
        }

        // baseKey 가 없는 경우 최상위 dummy 키로 설정
        var baseKey = data.node.data.baseKey;
        if (!baseKey) {
            baseKey = options.BASE_PARAM_VAL;
        }

        var params = {};
        params[options.BASE_PARAM_NM] = baseKey;
        params[options.P_NODE_NM] = data.node.parent.key;
        params[options.NODE_NM] = data.node.key;

        $("#" + options.CONT_PAN_ID).load(options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
            if (statusTxt == "success") {
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
        if (node.key == OpConfig.defaultCode.highCmsCd) {
            return false;
        }
        // 최상위 메뉴와 동일 레벨의 메뉴 이동 케이스(불가) 인 경우 최상위 메뉴하위 첫번째로 메뉴 이동
        if(node.parent.isRootNode()) {
            return ["over"];
        }
        return ["over", "before", "after"];
    };

    var treeMove = function(node, data, options) {
        // 하위로 이동되는 경우는 대상이 부모이므로 대상의 key를 사용
        var pNodeKey = node.parent.key;
        if (data.hitMode == "over") {
            pNodeKey = node.key;
        }

        var params = {};
        // 도메인 코드
        params[options.BASE_PARAM_NM] = node.data.baseKey;
        // 이동 대상 트리의 부모메뉴코드
        params[options.P_NODE_NM] = pNodeKey;
        // 순서 번호
        params[options.ORDER_NM] = data.otherNode.getIndex();
        // 현재 노드의 메뉴코드
        params[options.NODE_NM] = data.otherNode.key;

        $.ajax({
            type : "get",
            dataType : "json",
            url : options.MOVE_URL,
            data : params,
            success : function(response, statusTxt, xhr) {
                opJsonMsg(response);
            },
            error : function(response, status, error) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
    };

    /** 트리 클릭 후 상위 코드 경로 표시 */
    var afterTreeView = function(key, parentKey, response) {
        if (key == OpConfig.defaultCode.highCmsCd) {
            key = "최상위코드";
        }
        $("#nodePath").html(Optree.getNodePath() + "&nbsp;&nbsp;&nbsp;<code>" + key + "</code>");
    };

    /**
     * 상세화면 초기화
     */
    var initView = function(id) {
        $("#" + id).html($("#dummy").html());
    };

    /* modal창 닫기 */
    var opCloseWin = function() {
        // 등록한 내역이 있을경우에만 트리 초기화
        if (insertCnt > 0) {
            parent.Optree.reload();
        }
        parent.$.fancybox.close();
    }

    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-xs-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 사용자메뉴
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

        <div class="col-xs-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 사용자메뉴상세
                    </h6>
                </div>
                <div id="Cms_ADD_VIEW" class="panel-body"></div>
            </div>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 사용자메뉴 목록에서 선택하세요.</p>
        </div>
    </div>
</body>
</html>