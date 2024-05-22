<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>메뉴 등록</title>

<op:jsTag type="intra" items="opvalidate, tree" />
<op:jsTag type="libs" items="form" />

<script type="text/javascript">
    //<![CDATA[
    var checkInsertCnt = 0;
    var options = {
        ROOT_NODE_NM : "메뉴목록",
        ROOT_NODE_VAL : OpConfig.defaultCode.highMenuCd,
        // DND 사용여부
        USE_DND : true,
        SRC_URL : "ND_selectMenuTreeList.do",
        VIEW_URL : "INC_insertMenuForm.do",
        MOVE_URL : "ND_updateMenuSortOrder.do",
        USE_ROOT_VIEW : true,
        CONT_PAN_ID : "treeCont",
        P_NODE_NM : "q_upMenuSn",
        NODE_NM : "q_menuSn",
        ORDER_NM : "q_sortSn"
    };

    $(document).ready(function() {

        Optree = $("#TreePan1").optree(options);
        $("#treeCont").html($("#dummy").html());
    });

    var addChild = function(key) {
        Optree.addChildren({
            "key" : key,
            "title" : $("#menuNm").val(),
            "lazy" : false
        });
    };

    var treeView = function(event, data) {
        var node = data.node;
        var params = {};
        params[options.NODE_NM] = node.key;

        $("#treeCont").load(options.VIEW_URL, params, function(result) {
            $("#upMenuSn").val(node.key);
            $("#nodePath").html(Optree.getNodePath() + " > 하위");
        });
    };

    var afterTreeMove = function(nodeKey, pNodeVal){
        if($("#menuSn").val() == nodeKey){
            $("#upMenuSn").val(pNodeVal);
            $("#nodePath").html(Optree.getNodePath());
        }
    };

    var opCloseWin = function() {
        // 등록한 내역이 있을경우에만 트리 초기화
        if (checkInsertCnt > 0) parent.Optree.reload();

        if(parent.$.fancybox) {
            parent.$.fancybox.close();
        } else if($.fancybox) {
            $.fancybox.close();
        } else {
            self.close();
        }
    };
    //]]>
</script>
</head>
<body>
    <div class="row">
        <!-- 메뉴목록트리 -->
        <div class="col-xs-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-bubble4"></i> 메뉴목록
                    </h6>
                    <div class="panel-icons-group">
                        <a href="#" class="btn btn-link btn-icon" onclick="reload();"><i class="icon-spinner7"></i></a>
                        <a href="#" class="btn btn-link btn-icon" onclick="expandAll();"><i class="icon-plus-circle" ></i></a>
                        <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();"><i class="icon-minus-circle"></i></a>
                    </div>
                </div>
                <div class="panel panel-body">
                    <div id="TreePan1"></div>
                </div>
            </div>
        </div>
        <div class="col-xs-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 메뉴 등록
                    </h6>
                </div>
                <div id="treeCont" class="panel-body"></div>
            </div>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 메뉴 목록에서 선택하세요.</p>
        </div>
    </div>
</body>
</html>