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
<title>담당자 관리</title>

<op:jsTag type="intra" items="opvalidate, tree" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    var opCount = 0;
    var Optree;
    var options = {
            USE_DND : false,
            VIEW_URL : "/intra/mngr/INC_insertMngrForm.do",
        };
    $(document).ready(function() {
        Optree = $("#TreePan1").optree(options);
    });

    /* 트리의 노드 선택시 부서코드 자동입력 */
    var treeView = function(event, data) {
        var node = data.node;
        var params = {};
        params[options.NODE_NM] = node.key;
        
        $("#Mngr_FORM").load(options.VIEW_URL, params, function(result) {
            $("#deptNm").html(Optree.getNodePath());
            $("#deptCdId").val(node.key);
        });
    };
    
    /* modal창 닫기 */
    var opCloseWin = function() {
        // 등록한 내역이 있을경우에만 트리 초기화
        if (opCount > 0) parent.opMngrSearch();

        if(parent.$.fancybox) {
            parent.$.fancybox.close();
        } else if($.fancybox) {
            $.fancybox.close();
        } else {
            self.close();
        }
    }

    //]]>
</script>
</head>
<body>

    <div class="col-md-3">
        <div class="panel panel-default">
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
    </div>

    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-libreoffice"></i> 담당자 등록
                </h6>
            </div>
            
            <div id="Mngr_FORM" class="panel-body">
                <div class="callout callout-danger fade in">
                    <h5>부서가 선택되지 않았습니다.</h5>
                    <p>좌측 목록에서 부서를 선택하세요.</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>