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
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    var opUpdateAction = function() {
        if($("#docCn").val() == '') {
            opWarningMsg("도움말 내용은 필수입력 항목입니다.");
            $("#docCn").focus();
            return;
        }

        $("#dataForm").ajaxSubmit({
            url : "ND_updateHpcm.do",
            type : "POST",
            dataType : "json",
            async : false,
            success : function(response) {
                if(response){
                    opJsonMsg(response);
                    checkInsertCnt++;
                }
            }
        });
    };

    var opCloseWin = function() {
        // 등록한 내역이 있을 경우에만 초기화
        if (checkInsertCnt > 0) parent.opReload();

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
        <div class="col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 도움말 수정
                    </h6>
                </div>
                <div class="panel-body">
                    <div class="help-block text-right">
                        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                    </div>
                    <form name="dataForm" id="dataForm" method="post" action="ND_updateHpcm.do" class="form-horizontal">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <!-- 메뉴위치 R -->
                                <div class="form-group">
                                    <label for="menuSn" class="control-label col-xs-3">메뉴명</label>
                                    <div class="col-xs-9">
                                        <input type="hidden" name="menuSn" id="menuSn" value="${dataVo.menuSn}" class="form-control" />
                                        <input type="hidden" name="regSn" id="regSn" value="${dataVo.regSn}" />
                                        <div id="nodeNm">
                                        ${dataVo.menuPath}
                                        </div>
                                    </div>
                                </div>
                                <!-- 메뉴명 -->
                                <div class="form-group">
                                    <label for="docCn" class="control-label col-xs-3"><span class="mandatory">*</span> 도움말 내용</label>
                                    <div class="col-xs-9">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <textarea name="docCn" id="docCn" placeholder="도움말 내용을 입력해주세요" rows="5" class="form-control">${dataVo.docCn}</textarea>
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
                        <div class="row" id="btnDiv">
                            <div class="col-xs-12 btn-group">
                                <div class="pull-right">
                                    <button type="button" class="btn btn-success" onclick="opUpdateAction();">수정</button>
                                </div>
                            </div>
                        </div>
                        <!-- //버튼 -->
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>