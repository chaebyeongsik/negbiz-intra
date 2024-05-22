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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>도움말 관리</title>

<op:jsTag type="intra" items="opform, opvalidate, tree" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/hpcm/js/hpcm.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[
    var checkInsertCnt = 0;
    var Optree;
    var options = {
        ROOT_NODE_NM : "메뉴목록",
        ROOT_NODE_VAL : OpConfig.defaultCode.highMenuCd,
        SRC_URL : "/intra/menu/ND_selectMenuTreeList.do",
        USE_ROOT_VIEW : false,
        P_NODE_NM : "q_upMenuSn",
        NODE_NM : "q_menuSn"
    };
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        Optree = $("#TreePan1").optree(options);
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#treeCont").html($("#initDummy").html());
    });

    var opInsertAction = function() {
        if ($("#docCn").val() == '') {
            opWarningMsg("도움말 내용은 필수입력 항목입니다.");
            $("#docCn").focus();
            return;
        }

        $("#dataForm").ajaxSubmit({
            url : "ND_insertHpcm.do",
            type : "POST",
            dataType : "json",
            async : false,
            success : function(response) {
                if (response) {
                    opJsonMsg(response);
                    $("#treeCont").html($("#initDummy").html());
                    $("#btnDiv").hide();
                    checkInsertCnt++;
                }
            }
        });
    };

    var treeView = function(event, data) {
        var node = data.node;
        // 저장하고자 하는 메뉴가 이미 저장 되어있는 메뉴일 경우 등록버튼 숨김 및 도움말 내용칸 읽기모드
        $.ajax({
            url : "ND_selectHpcmExistsAt.do",
            type : "POST",
            dataType : "json",
            data : {
                menuSn : node.key
            },
            success : function(response) {
                if (response.result) {
                    // html 로딩
                    $("#treeCont").html($("#dummy").html());
                    // 메뉴코드..
                    $("#menuSn").val(node.key);
                    // 메뉴패스명..
                    $("#nodeNm").html(Optree.getNodePath('&gt;', node.key));
                    $("#btnDiv").show();
                } else {
                    opWarningMsg("이미 등록되어 있는 메뉴입니다.");
                    $("#treeCont").html($("#initDummy").html());
                }
            }
        });
    };

    var opCloseWin = function() {
        // 등록한 내역이 있을 경우에만 초기화
        if (checkInsertCnt > 0)
            parent.opReload();

        if (parent.$.fancybox) {
            parent.$.fancybox.close();
        } else if ($.fancybox) {
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
        <div class="col-xs-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-bubble4"></i> 메뉴목록
                    </h6>
                    <div class="panel-icons-group">
                        <a href="#" class="btn btn-link btn-icon" onclick="reload();">
                            <i class="icon-spinner7"></i>
                        </a>
                        <a href="#" class="btn btn-link btn-icon" onclick="expandAll();">
                            <i class="icon-plus-circle"></i>
                        </a>
                        <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();">
                            <i class="icon-minus-circle"></i>
                        </a>
                    </div>
                </div>
                <div class="panel panel-body">
                    <div id="TreePan1"></div>
                </div>
            </div>
        </div>
        <div class="col-xs-7">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 도움말 등록
                    </h6>
                </div>
                <div class="panel-body">
                    <div id="treeCont"></div>
                </div>
            </div>
        </div>
    </div>
    <div id="initDummy" style="display: none;">
        <div class="callout callout-danger fade in">
            <h5>항목이 선택되지 않았습니다.</h5>
            <p>좌측 메뉴목록에서 선택하세요.</p>
        </div>
    </div>
    <div id="dummy" style="display: none;">
        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>
        <form name="dataForm" id="dataForm" method="post" action="ND_insertHpcm.do" class="form-horizontal">
            <div class="panel panel-default">
                <div class="panel-body">

                    <!-- 메뉴위치 R -->
                    <div class="form-group">
                        <label for="menuSn" class="control-label col-xs-3">메뉴명</label>
                        <div class="col-xs-9">
                            <input type="hidden" name="menuSn" id="menuSn" value="" />
                            <div id="nodeNm"></div>
                        </div>
                    </div>
                    <!-- 메뉴명 -->
                    <div class="form-group">
                        <label for="docCn" class="control-label col-xs-3">
                            <span class="mandatory">*</span> 도움말 내용
                        </label>
                        <div class="col-xs-9">
                            <div class="row">
                                <div class="col-xs-12">
                                    <textarea name="docCn" id="docCn" placeholder="도움말 내용을 입력해주세요" rows="5" class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <valid:msg name="docCn" />
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- 버튼 -->
            <div class="row" style="display: none;" id="btnDiv">
                <div class="col-xs-12 btn-group">
                    <div class="pull-right">
                        <button type="button" class="btn btn-success" onclick="opInsertAction();">등록</button>
                    </div>
                </div>
            </div>
            <!-- //버튼 -->
        </form>
    </div>
</body>
</html>