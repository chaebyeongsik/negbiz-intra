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
<title>관리자 관리</title>

<op:jsTag type="intra" items="tree, opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mngr/js/mngr.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
    <valid:script type="msgbox" />
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("jbgdCdId", "${dataVo.jbgdCdId}");

        $("#changePasswd").bind("keyup", function() {
            var len = $(this).val().length;
            if ($(this).val() != "" && len > 7 && $(this).length > 0) {
                $(this).ajaxSubmit({
                    url : "ND_allowPasswd.do",
                    type : "GET",
                    dataType : "json",
                    data : {
                        q_chargerPasswd : $(this).val(),
                        q_picPswd : $("picPswd").val(),
                        q_date : new Date().toString()
                    },
                    success : function(response) {
                        if (response.result) {
                            $("#allowPasswd").html("<p class=\"text-info form-control-static\">올바른 비밀번호 조합입니다.</p>");
                        } else {
                            $("#allowPasswd").html("<p class=\"text-danger form-control-static\">부적합한 비밀번호 조합입니다.</p>");
                        }
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
        });
    });

    /** 담당자 수정 */
    var opUpdateMngr = function() {
        if (validate()) {
            if ($("#changePasswd").val() != "") {
                if ($("#changePasswd").val() != $("#confirmPasswd").val()) {
                    opWarningMsg("비밀번호가 일치하지 않습니다. (대소문자 구분)");
                    $("#confirmPasswd").focus();
                    return;
                }
                if ($("#confirmPasswd").val() == "") {
                    opWarningMsg("비밀번호가 일치하지 않습니다. (대소문자 구분)");
                    $("#confirmPasswd").focus();
                    return;
                }
            }

            if ($.trim($("#picPswd").val()) != "") {
                var enc_val = opEncrypt($("#picPswd").val());
                $("#picPswd").val(enc_val);
            }
            if ($.trim($("#changePasswd").val()) != "") {
                var enc_val = opEncrypt($("#changePasswd").val());
                $("#changePasswd").val(enc_val);
            }
            if ($.trim($("#confirmPasswd").val()) != "") {
                var enc_val = opEncrypt($("#confirmPasswd").val());
                $("#confirmPasswd").val(enc_val);
            }
            $("#dataForm").ajaxSubmit({
                type : "POST",
                dataType : "json",
                success : function(response) {
                    if (response.result) {
                        opSuccessMsg(response.message);
                    } else {
                        opWarningMsg(response.message);
                    }
                    $("#picPswd").val("");
                    $("#changePasswd").val("");
                    $("#confirmPasswd").val("");
                },
                error : function(response) {
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        }
    };
    //]]>
</script>
</head>
<body>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 담당자 수정
            </h6>
        </div>

        <div id="Mngr_FORM" class="panel-body">
            <div class="help-block text-right">
                <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
            </div>

            <form name="dataForm" id="dataForm" method="post" action="ND_updateCharger.do" class="form-horizontal">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="picId" class="control-label col-xs-2">아이디 <span class="mandatory">*</span></label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <p class="form-control-static">${dataVo.picId}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="picNm" class="control-label col-xs-2">이름 <span class="mandatory">*</span></label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <p class="form-control-static">${dataVo.picNm}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="picNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="jbgdCdId" class="control-label col-xs-2">직급 <span class="mandatory">*</span></label>
                            <div class="col-xs-6">
                                <op:cdId id="jbgdCdId" type="select" hghrkCdId="ofcps" cdId="ofcps"  values="${dataVo.jbgdCdId}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="deptCdId" class="control-label col-xs-2">부서명 <span class="mandatory">*</span></label>
                            <div class="col-xs-10">
                                <p class="form-control-static" id="deptNm">${dataVo.deptNm}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="picPswd" class="control-label col-xs-2">현재 비밀번호 <span class="mandatory">*</span></label>
                            <div class="col-xs-5">
                                <input type="password" name="picPswd" id="picPswd" value="" maxlength="32" class="form-control" placeholder="현재 비밀번호를 입력하세요." />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="changePasswd" class="control-label col-xs-2">새 비밀번호 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <input type="password" name="changePasswd" id="changePasswd" value="" maxlength="32" class="form-control" placeholder="변경시에만 입력하세요." />
                                    </div>
                                    <div class="col-xs-6" id="allowPasswd"></div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-9">
                                        <div class="help-block op-validate">
                                            <ul class="list-group">
                                                <li class="list-group-item">
                                                    <span class="validate-msg">
                                                        비밀번호 변경 시에만 입력합니다.
                                                    </span>
                                                </li>
                                                <li class="list-group-item">
                                                    <span class="validate-msg">
                                                        (최소 8자, 최대 32자/영문(대/소문자),숫자,특수문자 조합)
                                                    </span>
                                                </li>
                                                <li class="list-group-item">
                                                    <span class="validate-msg">
                                                        비밀번호 등록 또는 변경시 보안등급 확인하여 안전하게 변경하시기 바랍니다.
                                                    </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-xs-3">
                                        <button type="button" class="btn btn-xs btn-info" onclick="opScrtyGradPop();">보안등급정책</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmPasswd" class="control-label col-xs-2">새 비밀번호 확인 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <input type="password" name="confirmPasswd" id="confirmPasswd" value="" class="form-control" placeholder="변경할 비밀번호를 입력하세요." />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10">
                                        <div class="help-block op-validate">
                                            <ul class="list-group">
                                                <li class="list-group-item">
                                                    <span class="validate-msg">
                                                        재확인을 위해서 입력하신 비밀번호를 다시 한번 입력해주세요.
                                                    </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rgnTelno" class="control-label col-xs-2">전화번호 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <input type="text" name="rgnTelno" id="rgnTelno" value="${dataVo.rgnTelno}" maxlength="4" class="form-control" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text" name="telofcTelno" id="telofcTelno" value="${dataVo.telofcTelno}" maxlength="4" class="form-control" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text" name="indivTelno" id="indivTelno" value="${dataVo.indivTelno}" maxlength="4" class="form-control" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="help-block op-validate">
                                            <ul class="list-group">
                                                <li class="list-group-item"><span class="validate-msg">숫자만 입력하세요.</span></li>
                                                <li class="list-group-item"><span class="validate-msg">최대 길이에 맞게 입력하세요. (4자)</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rgnFxno" class="control-label col-xs-2">팩스번호 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <input type="text" name="rgnFxno" id="rgnFxno" value="${dataVo.rgnFxno}" maxlength="4" class="form-control" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text" name="telofcFxno" id="telofcFxno" value="${dataVo.telofcFxno}" maxlength="4" class="form-control" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text" name="indivFxno" id="indivFxno" value="${dataVo.indivFxno}" maxlength="4" class="form-control" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="help-block op-validate">
                                            <ul class="list-group">
                                                <li class="list-group-item"><span class="validate-msg">숫자만 입력하세요.</span></li>
                                                <li class="list-group-item"><span class="validate-msg">최대 길이에 맞게 입력하세요. (4자)</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mblRgnTelno" class="control-label col-xs-2">휴대전화번호 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <input type="text" name="mblRgnTelno" id="mblRgnTelno" value="${dataVo.mblRgnTelno}" maxlength="4" class="form-control" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text" name="mblTelofcTelno" id="mblTelofcTelno" value="${dataVo.mblTelofcTelno}" maxlength="4" class="form-control" />
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text" name="mblIndivTelno" id="mblIndivTelno" value="${dataVo.mblIndivTelno}" maxlength="4" class="form-control" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="help-block op-validate">
                                            <ul class="list-group">
                                                <li class="list-group-item"><span class="validate-msg">숫자만 입력하세요.</span></li>
                                                <li class="list-group-item"><span class="validate-msg">최대 길이에 맞게 입력하세요. (4자)</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="emlId" class="control-label col-xs-2">이메일 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <input type="text" name="emlId" id="emlId" value="${dataVo.emlId}" class="form-control" />
                                    </div>
                                    <div class="col-xs-1">
                                        <p class="form-control-static">@</p>
                                    </div>
                                    <div class="col-xs-4">
                                        <input type="text" name="emlSiteNm" id="emlSiteNm" value="${dataVo.emlSiteNm}" class="form-control" placeholder="zesinc.co.kr" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="emlId" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="taskCn" class="control-label col-xs-2">담당업무 </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <textarea name="taskCn" id="taskCn" rows="5" class="form-control" placeholder="담당업무를 입력해주세요">${dataVo.taskCn}</textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="taskCn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 버튼 -->
                <div class="row">
                    <div class="block clearfix">
                        <div class="col-xs-12 btn-group">
                            <div class="text-right">
                                <button type="button" class="btn btn-success" onclick="opUpdateMngr();">수정</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>

</body>
</html>