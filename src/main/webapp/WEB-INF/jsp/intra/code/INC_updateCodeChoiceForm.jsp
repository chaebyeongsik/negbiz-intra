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
<title>코드선택 관리</title>

<op:jsTag type="intra" items="opform, opvalidate, tree" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/code/js/code.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var treeData = ${dataList};
    var Optree;
    var options = {
        USE_DND : false,
        USE_TABLE : true,
        USE_CHKBOX : true,
        SELECT_MODE : 2,
        SRC_URL : "ND_selectCodeTreeList.do",
        P_NODE_NM : "q_upCdId",
        NODE_NM : "q_cdId",
        ORDER_NM : "q_sortSn",
        TREE_DATA : treeData,
        select : function(event, data) {
            var node = data.node;
            // 클릭 대상이 checkbox 인경우에만 활성화
            var targetType = data.targetType;
            if (targetType == "checkbox") {
                togleSelected(node);
                if (node.isSelected()) {
                    // 현재 선택된 항목의 부모라인 모두를 자동 선택
                    var pnode = node.getParent();
                    while (pnode) {
                        if (!pnode.isSelected()) {
                            pnode.setSelected(true);
                            togleSelected(pnode);
                        }
                        pnode = pnode.getParent();
                    }
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
            var $tdList = $(node.tr).find(">td");

            // CSS 설정 (table에 직접 설정을 무시됨)
            $tdList.eq(0).addClass("text-center");
            $tdList.eq(2).addClass("text-center");

            var html = "<code>" + node.key + "</code>";
            var disabled = "disabled=\"disabled\"";
            if (node.isSelected()) {
                disabled = "";
                html += "<input type=\"hidden\" name=\"prvLwrkCdIds\" id=\"prvLwrkCdIds"+node.key+"\" value=\""+node.key+"\" />";
                html += "<input type=\"hidden\" name=\"prvUpperCodes\" id=\"prvUpperCodes"+node.key+"\" value=\""+node.data.value+"\" />";
            }
            html += "<input type=\"hidden\" name=\"lwrkCdIds\" id=\"lwrkCdIds"+node.key+"\" value=\""+node.key+"\" "+disabled+" />";
            html += "<input type=\"hidden\" name=\"upCdIds\" id=\"upCdIds"+node.key+"\" value=\""+node.data.value+"\" "+disabled+" />";

            $tdList.eq(2).html(html);
        }
    };

    // checkbox의 선택 상태에 따라서 hidden 상태를 .
    var togleSelected = function(node) {
        // 현재 선택상태에 따라서 변경시킨다.
        if (node.isSelected()) {
            $("#lwrkCdIds" + node.key).prop("disabled", false);
            $("#upCdIds" + node.key).prop("disabled", false);
        } else {
            $("#lwrkCdIds" + node.key).prop("disabled", true);
            $("#upCdIds" + node.key).prop("disabled", true);
        }
    };

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if (validate()) {
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        opUpdateForm('INC_updateCodeChoiceForm.do', {q_hghrkCdId : '${dataVo.hghrkCdId}', q_cdId : '${dataVo.cdId}', q_cdChcId : '${paramMap.q_cdChcId}'});
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });

        Optree = $("#treetable").optree(options);
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>

    <form name="dataForm" id="dataForm" method="post" action="ND_updateCodeChoice.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="hghrkCdId" id="hghrkCdId" value="${dataVo.hghrkCdId}" />
        <input type="hidden" name="duplicateYn" id="duplicateYn" value="Y" />

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label class="control-label col-sm-2"> 코드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="form-control-static">
                                    ${dataVo.cdNm}
                                    <code>${dataVo.cdId}</code>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2"> 코드선택자 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="form-control-static">
                                    <code>${paramMap.q_cdChcId}</code>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">하위코드선택</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="panel-icons-group">
                                    <a href="#" class="btn btn-link btn-icon" onclick="reload(); return false;"><i class="icon-spinner7"></i></a> <a href="#" class="btn btn-link btn-icon"
                                        onclick="expandAll(); return false;"
                                    ><i class="icon-plus-circle"></i></a> <a href="#" class="btn btn-link btn-icon" onclick="collapseAll(); return false;"><i class="icon-minus-circle"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table id="treetable" class="table table-bordered">
                                    <colgroup>
                                        <col width="60" />
                                        <col />
                                        <col width="100" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th class="text-center">선택</th>
                                            <th class="text-center">코드명</th>
                                            <th class="text-center">코드</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <button type="button" class="btn btn-danger" onclick="opCdChcIdDelete('ND_deleteCodeChoice.do');">삭제</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>