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
<title>담당자 부서이동</title>

<op:jsTag type="intra" items="opvalidate, tree" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    var Optree;
    var options = {
            USE_DND : false
        };
    $(document).ready(function() {
        Optree = $("#TreePan1").optree(options);
    });

    /* 트리의 노드 선택시 부서코드 자동입력 */
    var treeView = function(event, data) {
        $("#deptCdId").val(data.node.key);
    };

    var opUpdateDeptTrnsfer = function () {
        if ($("#deptCdId").val() == "" || $("#beforeDeptCdId").val() == $("#deptCdId").val()) {
            opWarningMsg("이동할 부서를 선택해 주세요.");
            return;
        }

        $.ajax({
            url : "<c:url value="/intra/mngr/ND_updateDeptTrnsfer.do" />",
            type : "POST",
            data : $("#dataForm").serialize(),
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    parent.opSuccessMsg(response.message);
                    parent.opMngrList();
                    opCloseWin();
                } else {
                    opErrorMsg(response.message);
                }
            }
        });

    }

    //]]>
</script>
</head>
<body>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-people"></i> 이동할 부서를 선택한 후 저장하세요.
            </h6>
        </div>
        
        <form name="dataForm" id="dataForm" method="get" action="ND_updateDeptTrnsfer.do" class="form-inline">
            <input type="hidden" id="picIds" name="picIds" value="${param.picIds}" />
            <input type="hidden" id="beforeDeptCdId" name="beforeDeptCdId" value="${dataVo.deptCdId}" />
            <input type="hidden" id="deptCdId" name="deptCdId" value="" />
        <div class="panel-body">
            <div class="alert alert-danger block-inner text-left">부서 이동 시 기존 권한은 삭제되오니 신규로 권한을 부여하시기 바랍니다.</div>
            <div class="block table-responsive">
                <table class="table table-bordered">
                    <colgroup>
                        <col width="20%" />
                        <col width="" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th>직원명(직급)</th>
                            <th>${dataVo.picNm}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>현재 부서명</td>
                            <td>${dataVo.deptNm}</td>
                        </tr>
                        <tr>
                            <td>이동부서명</td>
                            <td>
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
<!--                                 <div id="TreePan1"></div> -->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <!-- 버튼 -->
            <div class="block clearfix pull-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-info" onclick="opUpdateDeptTrnsfer();">저장</button>
                </div>
            </div>
        </div>
        
        </form>
    </div>

</body>
</html>