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
<title>메뉴권한할당</title>

<op:jsTag type="intra" items="tree" />

<script type="text/javascript">
    //<![CDATA[
    var treeData = ${dataList};
    var optionsHtml = "";
    <c:forEach items="${authList}" var="auth">
        optionsHtml += "<option value=\"${auth.cdId}\">${auth.name}</option>";
    </c:forEach>
    var Optree;
    $(document).ready(function() {
        var options = {
            USE_ROOT_NODE : false,
            USE_DND : false,
            USE_TABLE : true,
            USE_CHKBOX : true,
            SELECT_MODE : 2,
            // 트리 데이터
            TREE_DATA : treeData,
            select : function(event, data) {
                var node = data.node;
                // 클릭 대상이 checkbox 인경우에만 활성화
                var targetType = data.targetType;
                if(targetType && targetType == "checkbox") {
                    togleSelected(node);
                    if(node.isSelected()) {
                        // 현재 선택된 항목의 부모라인 모두를 자동 선택
                        var pnode = node.getParent();
                        if(pnode) {
                            while (pnode) {
                                if (!pnode.isSelected()) {
                                    pnode.setSelected(true);
                                    togleSelected(pnode);
                                }
                                pnode = pnode.getParent();
                            }
                        }
                        selectChildren(node);
                    } else {
                        hasSelectedChildren(node);
                    }
                }
            },
            table : {
                // 자동 생성 체크박스 table td index
                checkboxColumnIdx : 0,
                // 제목 데이터  table td index
                nodeColumnIdx : 1,
               // 뎁스별 인덴트 px 단위
                indentation : 30
            },
            renderColumns : function(event, data) {
                var node = data.node;
                var data = node.data;
                var $tdList = $(node.tr).find("td");

                // CSS 설정 (table에 직접 설정을 무시됨)
                $tdList.eq(0).addClass("text-center");
                $tdList.eq(2).addClass("text-center");
                $tdList.eq(3).addClass("text-center");

                var disabled = "disabled=\"disabled\"";
                if (node.isSelected()) {
                    disabled = "";
                }
                var keyInput = "<input type=\"hidden\" name=\"menuSn\" id=\"menuSn"+node.key+"\" value=\""+ node.key +"\" "+disabled+" />";

                $tdList.eq(2).html(node.key + keyInput);

                // 권한 코드 목록
                var selectHtml = "";
                selectHtml += "<select name=\"authrtGrntCdId\" id=\"authrtGrntCdId"+node.key+"\" class=\"form-control\" "+disabled+" >";
                selectHtml += optionsHtml
                selectHtml += "</select>";

                $tdList.eq(3).html(selectHtml);
                // 권한 체크(기본권한/조회권한/총괄권한 중)
                //console.log(data);
                if(data.value) $tdList.eq(3).find("select").val(data.value);
            }
        };
        // options

        // 자식 노드 모두를 선택 상태로 변경
        var selectChildren = function(node) {
            var childNodeList = node.children;
            if(childNodeList) {
                for(var i=0 ; i < childNodeList.length ; i++) {
                    childNodeList[i].setSelected(true);
                    togleSelected(childNodeList[i]);
                    selectChildren(childNodeList[i]);
                }
            }
        };

         // 자식 노드중에 선택되어 있는 항목이 있는지 체크 자식 존재시 부모 삭제 못하도록
        var hasSelectedChildren = function(node) {
            var childNodeList = node.children;
            if(childNodeList) {
                for(var i=0 ; i < childNodeList.length ; i++) {
                    if(childNodeList[i].isSelected()) {
                        node.setSelected(true);
                        opWarningMsg("하위 메뉴선택을 우선 해제해야 합니다.");
                        break;
                    }
                }
            }
        };

        // checkbox의 선택 상태에 따라서 hidden 상태를 .
        var togleSelected = function(node) {
            // 현재 선택상태에 따라서 변경시킨다.
            if (node.isSelected()) {
                $("#menuSn" + node.key).prop("disabled", false);
                $("#authrtGrntCdId" + node.key).prop("disabled", false);
            } else {
                $("#menuSn" + node.key).prop("disabled", true);
                $("#authrtGrntCdId" + node.key).prop("disabled", true);
            }
        };

        Optree = $("#treetable").optree(options);
        Optree.expandAll();
    });
    //]]>
</script>
</head>
<body>
<form name="dataForm" id="dataForm" method="post" action="ND_insertAuthor.do" class="form-horizontal">
    <input type="hidden" name="authrtCdId" value="${param.authrtCdId}" />
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-list2"></i> 메뉴권한할당 목록 <span class="label label-info" id="authrtNm" style="margin: -2px 0 0 5px;font-weight: normal;"></span>
                </h6>
                <div class="panel-icons-group">
                    <a href="#" class="btn btn-link btn-icon" onclick="expandAll();"><i class="icon-plus-circle" ></i></a>
                    <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();"><i class="icon-minus-circle"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table id="treetable" class="table table-bordered">
                        <colgroup>
                            <col width="60"/>
                            <col />
                            <col width="100"/>
                            <col width="200"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="text-center">선택</th>
                                <th class="text-center">메뉴명</th>
                                <th class="text-center">메뉴코드</th>
                                <th class="text-center">권한</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-center"></td>
                                <td></td>
                                <td class="text-center"></td>
                                <td class="text-center"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn-success" onclick="opMenuAsgnSave();">저장</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>