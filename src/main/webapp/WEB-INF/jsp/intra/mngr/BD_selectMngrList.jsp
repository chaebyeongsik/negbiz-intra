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
<title>담당자 관리</title>

<op:jsTag type="intra" items="tree" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mngr/js/mngr.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /*
     * 공통 초기화 기능
     */
    var Optree;
    // 예제 옵션 위의 주석을 참조하여 필요한 설정을 하도록 한다. 
    var options = {
        // Drag and drop 사용여부
        USE_DND : false,
        // 폴더인 경우 view를 열지 여부 (기본값 true)
        FOLDER_OPEN : true,
        // view가 로드될 패널
        CONT_PAN_ID : "Mngr_VIEW",
        // 트리 데이터 url
        SRC_URL : "/intra/dept/ND_selectDeptTreeList.do",
        // view url
        VIEW_URL : "INC_selectMngrList.do",
        // node move시 호출될 url
        MOVE_URL : "ND_updateMngr.do",
        // 기본 표시 명칭
        ROOT_NODE_NM : "부서목록",
        // 기본 표시 데이터 키값
        ROOT_NODE_VAL : OpConfig.defaultCode.highDeptCd,
        // 추가 파라미터가 필요한 경우에 사용자 정의 추가
        // 부모 노드의 파라미터 명칭
        P_NODE_NM : "q_upDeptCdId",
        // 현재 노드의 파라미터 명칭
        NODE_NM : "q_deptCdId",
        // 이동시 현재 일련번호 값을 전달할 파라미터 키명칭
        ORDER_NM : "q_sortSn"
    };
    $(document).ready(function() {

        // 위의 defaults 옵션으로 생성하는 경우
        Optree = $("#TreePan").optree(options);

        // 담당자 등록
        $("#insertForm").opmodal({
            width : 980,
            height : 500,
            href : "/intra/mngr/PD_insertMngrForm.do"
        });

        // 담당자 검색
        $("#chargerSearch").click(function() {
            var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, ";
            option += "resizable=yes, scrollbars=yes, location=1, status=1, menubar=10, toolbar=1";

            var href = "/intra/mngr/PD_selectMngrList.do";
            var chargerSearchWin = window.open(href, "chargerSearchWin", option);
            chargerSearchWin.focus();
            return false;
        });
    });

    /*
     * 담당자목록 새로고침
     */
    var opMngrList = function() {
        var params = {};
        var url =  "INC_selectMngrList.do?" + opSearchQueryString("dataForm");
        $("#Mngr_VIEW").load(url, params, function(result) {
        });
    } ;

    //]]>
</script>
</head>
<body>
<div class="row">
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 부서목록
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
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="button" class="btn btn-info" id="chargerSearch">담당자검색</button>
                        <button type="button" class="btn btn-info" id="insertForm">담당자추가</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 담당자 목록
                    </h6>
                </div>
                <div id="Mngr_VIEW" class="panel-body">
                    <div class="callout callout-danger fade in">
                        <h5>항목이 선택되지 않았습니다.</h5>
                        <p>좌측 부서목록에서 선택하시거나 담당자추가 버튼을 클릭하세요.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>