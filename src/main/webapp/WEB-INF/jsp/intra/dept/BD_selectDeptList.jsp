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
<title>부서관리</title>

<op:jsTag type="intra" items="tree" />

<script type="text/javascript">
    //<![CDATA[
    /*
     * 공통 초기화 기능
     */
    var Optree;
    $(document).ready(function() {
        var options = {
            VIEW_URL : "/intra/dept/INC_updateDeptForm.do",
            MOVE_URL : "/intra/dept/ND_updateDeptSortOrder.do"
        };
        Optree = $("#TreePan1").optree(options);
        $("#insertForm").opmodal({
            width : 980,
            height : 500,
            href : "/intra/dept/PD_insertDeptForm.do"
        });
        $("#insertExcel").opmodal({
            width : 980,
            height : 500,
            href : "/intra/dept/PD_insertExcelForm.do"
        });
        $("#downloadExcel").click(function() {
            $("#excelForm").submit();
        });
    });

    var opNodeDelete = function(key) {
        var treeContHtml = "<div class=\"callout callout-danger fade in\">"
                + "<h5>부서가 선택되지 않았습니다.</h5>"
                + "<p>좌측 목록에서 부서를 선택하세요.</p>"
                + "</div>";
        Optree.removeNode(key);
        $("#treeCont").html(treeContHtml);
    };

    /* 부서이동시 제약사항 적용 */
    var treeMoveEnter = function(node, data, options) {
        if(!options.MOVE_URL) {
            return false;
        }
        // 최상위 메뉴는 하위추가만 가능
        if(node.parent.isRootNode()) {
            return ["over"];
        }
        // 이외는 모두 허용
        return ["before", "after", "over"];
    };

    /* 트리의 노드 이동시 상위부서코드 자동입력 */
    var afterTreeMove = function(nodeKey, pNodeVal, orderNo) {
        if ($("#treeCont").find("#deptCdId").val() ==  nodeKey) {
            if ($("#treeCont").find("#upDeptCdId").val() != 'undefined') {
                var nodePath = Optree.getNodePath('', nodeKey);
                nodePath = nodePath.substring(0, nodePath.lastIndexOf('&gt;'));
                $("#treeCont").find("#upperDeptNm").html(nodePath);
                $("#treeCont").find("#upDeptCdId").val(pNodeVal);
            }
        }
    };
    //]]>
</script>
<op:excelDown excelKey="dept" btnId="excelDown" />
</head>
<body>

    <div class="row block">
        <div class="col-md-12 btn-group">
            <div class="pull-right">
                <a href="#" id="insertExcel" class="btn btn-info">엑셀일괄등록</a>
                <a href="#" id="excelDown" class="btn btn-info">엑셀출력</a>
            </div>
        </div>
    </div>

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
                    <div id="TreePan1"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="button" class="btn btn-info" id="insertForm">부서추가</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 부서상세 및 수정
                    </h6>
                </div>
                <div id="treeCont" class="panel-body">
                    <div class="callout callout-danger fade in">
                        <h5>부서가 선택되지 않았습니다.</h5>
                        <p>좌측 목록에서 부서를 선택하세요.</p>
                    </div>
                </div>
            </div>
        </div>
        
    </div>

</body>
</html>