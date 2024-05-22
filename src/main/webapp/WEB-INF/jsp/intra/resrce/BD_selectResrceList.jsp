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
<title>카테고리 관리</title>

<op:jsTag type="intra" items="tree, opform" />
<op:jsTag type="libs" items="form" />

<script type="text/javascript">
    //<![CDATA[

    var contPadId = "ResrceList_VIEW";
    var Optree;
    // 예제 옵션 위의 주석을 참조하여 필요한 설정을 하도록 한다. 
    var options = {
        USE_DND : false,
        // ROOT 노드 클릭시 상세화면 표시여부
        USE_ROOT_VIEW : false,
        // 폴더인 경우 view를 열지 여부 (기본값 true)
        FOLDER_OPEN : true,
        // view가 로드될 패널
        CONT_PAN_ID : contPadId,
        // 트리 데이터 url
        SRC_URL : "ctgry/ND_selectResrceCtgryTreeList.do",
        // view url
        VIEW_URL : "INC_selectResrceList.do",
        // 기본 표시 명칭
        ROOT_NODE_NM : "카테고리목록",
        // 기본 표시 데이터 키값
        ROOT_NODE_VAL : OpConfig.defaultCode.highTreeCd,
        // 추가 파라미터가 필요한 경우에 사용자 정의 추가
        // 부모 노드의 파라미터 명칭
        P_NODE_NM : "q_upCtgrySn",
        // 현재 노드의 파라미터 명칭
        NODE_NM : "q_ctgrySn"
    };

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 위의 defaults 옵션으로 생성하는 경우
        Optree = $("#TreePan").optree(options);
        // 초기 상세화면 영역 안내 메시지 표시
        initView(contPadId);

        // 카테고리 등록/수정폼 오픈 
        $("#addModal").opmodal({
            width : 980
        });
    });

    /* 정렬순서는 없기 때문에 폴더 이동시에만 적용하므로 over 이벤트만 허용 */
    var treeMoveEnter = function(node, data, options) {
        if (!options.MOVE_URL) {
            return false;
        }
        // 최상위정렬은 불가
        if(node.key == OpConfig.defaultCode.highTreeCd || data.otherNode.parent.key == node.key) {
            return false;
        }
        return ["over"];
    };

    /*
     * 트리 이동 후 추가 사용자 정의 메소드
     */
    var afterTreeMove = function(nodeKey, pNodeKey, orderNo, result) {
        initView(contPadId);
        return true;
    };

    /** 트리 클릭 후 상위 코드 경로 표시 */
    var afterTreeView = function(key, parentKey, response) {
        $("#nodePath").html(Optree.getNodePath());
    };

    /**
     * 상세화면 초기화
     */
    var initView = function(id) {
        $("#" + id).html($("#dummy").html());
    };
    //]]>
</script>
</head>
<body>
<div class="row">
        <div class="col-sm-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 카테고리목록
                    </h6>
                    <div class="panel-icons-group">
                        <a href="#" class="btn btn-link btn-icon" onclick="reload();"><i class="icon-spinner7"></i></a>
                        <a href="#" class="btn btn-link btn-icon" onclick="expandAll();"><i class="icon-plus-circle"></i></a>
                        <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();"><i class="icon-minus-circle"></i></a>
                    </div>
                </div>
                <div class="panel-body">
                    <div id="TreePan"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 btn-group">
                    <div class="pull-right">
                        <a href="/intra/resrce/ctgry/PD_selectResrceCtgryList.do" class="btn btn-info" id="addModal">카테고리추가</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i><span id="nodePath"></span>
                    </h6>
                </div>
                <div id="ResrceList_VIEW" class="panel-body"></div>
            </div>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 카테고리 목록에서 선택하시거나 추가 버튼을 클릭하세요.</p>
        </div>
    </div>
</body>
</html>