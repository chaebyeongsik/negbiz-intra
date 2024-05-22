<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<title>메뉴 목록</title>

<op:jsTag type="intra" items="tree" />
<op:jsTag type="libs" items="docs" />

<!-- 기능별 스크립트 정의 -->
<script type="text/javascript">
    //<![CDATA[

   <valid:script type="msgbox" />

    var options = {
        ROOT_NODE_NM : "메뉴목록",
        ROOT_NODE_VAL : OpConfig.defaultCode.highMenuCd,
        SRC_URL : "ND_selectMenuTreeList.do",
        VIEW_URL : "INC_updateMenuForm.do",
        MOVE_URL : "ND_updateMenuSortOrder.do",
        CONT_PAN_ID : "treeCont",
        P_NODE_NM : "q_upMenuSn",
        NODE_NM : "q_menuSn",
        ORDER_NM : "q_sortSn"
    };
    $(document).ready(function() {

        Optree = $("#TreePan1").optree(options);
        $("#treeCont").html($("#hiddenTreeCont").html());
    });

    var treeView = function(event, data) {
        var node = data.node;
        var params = {};
        params[options.NODE_NM] = node.key;

        $("#treeCont").load(options.VIEW_URL, params, function(result) {
            if (node.key != 0) {
                $("#nodePath").html(Optree.getNodePath());
            }
        });
    };

    var opDeleteMenu = function() {
        if(!confirm("정말 삭제하시겠습니까?")) return;
        $("#dataForm").ajaxSubmit({
            url : "ND_deleteMenu.do",
            type : "POST",
            dataType : "json",
            async : false,
            success : function(response) {
                if(response.result){
                    opJsonMsg(response);
                    // 해당 노드 삭제
                    Optree.removeNode();
                    // 오른쪽 상세 부분 초기화
                    $("#treeCont").html($("#hiddenTreeCont").html());
                }else{
                    opErrorMsg(response.message);
                }
            }
        });
    };

    var treeMoveEnter = function(node, data, options) {
        if (!options.MOVE_URL) {
            return false;
        }
        // 최상위 메뉴는 하위추가만 가능
        if(node.parent.isRootNode()) {
            return ["over"];
        }
        // 이외는 모두 허용
        return ["before", "after", "over"];
    };

    var opUpdateMenu = function() {

        if(!validate()) return;

        $("#dataForm").ajaxSubmit({
            url : "ND_updateMenu.do",
            type : "POST",
            dataType : "json",
            async : false,
            success : function(response) {
                if(response.result){
                    opJsonMsg(response);
                    // 메뉴명 변경(트리명)
                    Optree.setTitle($("#menuNm").val());
                    // 사용/미사용 여부에 해당하는 노드 색깔 변경
                    Optree.setUseYn($("input[name=useYn]:checked").val());
                }else{
                    opErrorMsg(response.message);
                }
            }
        });
    };

    var opMenuInsertFormPop = function() {
        var url = "PD_insertMenuForm.do";
        var option = {
            href : url,
            width : 800,
            height : 600,
        };

        $.fn.opmodal(option);
    };

    var opMenuUrlSynch = function() {
        if(confirm("메뉴 URL을 동기화 하시겠습니까?")){
            opLoadStart();
            $.ajax({
                type: "get",
                dataType : "json",
                url: "/intra/menu/ND_processMenuUrlSynch.do",
                success: function(response){
                    opLoadEnd();
                    opJsonMsg(response);
                },
                error: function(response,status,error) {
                    opLoadEnd();
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        }
    };

    var afterTreeMove = function(nodeKey, pNodeVal, orderNo){
        if($("#menuSn").val() == nodeKey){
            $("#upMenuSn").val(pNodeVal);
            $("#nodePath").html(Optree.getNodePath());
            $("#sortSn").val(orderNo);
        }
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
                        <i class="icon-list2"></i> 메뉴목록
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
                    <%-- <div class="pull-left">
                        <button type="button" class="btn btn-success" onclick="opMenuUrlSynch()">메뉴URL동기화</button>
                    </div> --%>
                    <div class="pull-right">
                        <button type="button" class="btn btn-info" onclick="opMenuInsertFormPop()">메뉴추가</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 메뉴 상세
                    </h6>
                </div>
                <div id="treeCont" class="panel-body">
                </div>
            </div>
        </div>

        <div id="hiddenTreeCont" style="display: none;">
            <div class="callout callout-danger fade in">
                <h5>메뉴가 선택되지 않았습니다.</h5>
                <p>좌측 목록에서 메뉴를 선택하시거나 메뉴등록 버튼을 클릭하세요.</p>
            </div>
        </div>
    </div>
</body>
</html>