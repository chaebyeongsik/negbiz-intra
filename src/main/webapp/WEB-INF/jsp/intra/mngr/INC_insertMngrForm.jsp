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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>관리자 관리</title>

    <op:jsTag type="intra" items="opform, opvalidate" />
    <op:jsTag type="libs" items="form" />

    <!-- 기능별 스크립트 삽입 부 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mngr/js/mngr.js"></script>
    <script type="text/javascript">
        //<![CDATA[

        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        /* 공통 초기화 기능 */
        $(document).ready(function() {
            $("#picPswd").bind("keyup", function() {
                var len = $(this).val().length;
                if ($(this).val() != "" && len > 7 && $(this).length > 0) {
                    $(this).ajaxSubmit({
                        url : "ND_allowPasswd.do",
                        type : "GET",
                        dataType : "json",
                        data : { q_chargerPasswd : $(this).val(), q_date : new Date().toString() },
                        success : function(response) {
                            if (response.result) {
                                $("#allowPasswd").html("<p class=\"text-info form-control-static\">올바른 비밀번호 조합입니다.</p>");
                            } else {
                                $("#allowPasswd").html("<p class=\"text-danger form-control-static\">부적합한 비밀번호 조합입니다.</p>");
                            }
                        }
                    });
                }
            });

        });


        /** 담당자 등록 */
        var opInsertMngr = function() {
            if (validate()) {
                if ($("#deptCdId").val() == "") {
                    opWarningMsg("부서를 선택하세요.");
                    return;
                }
                if ($("#jbgdCdId option:selected").val() == "") {
                    opWarningMsg("직급을 선택하세요.");
                    $("#jbgdCdId").focus();
                    return;
                }
                if ($("#picPswd").val() != "" && $("#confirmPassword").val() == "") {
                    opWarningMsg("비밀번호가 일치하지 않습니다. (대소문자 구분)");
                    $("#confirmPassword").focus();
                    return;
                }
                if ($("#picPswd").val() != $("#confirmPassword").val()) {
                    opWarningMsg("비밀번호가 일치하지 않습니다. (대소문자 구분)");
                    $("#confirmPassword").focus();
                    return;
                }

                $("#dataForm").ajaxSubmit({
                    url : "<c:url value="/intra/mngr/ND_insertMngr.do" />",
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        if (response.result) {
                            $("#dataForm input").each(function() {
                                if ($(this).attr("type") != 'radio' && $(this).attr("type") != 'hidden' ) {
                                    $(this).val("");
                                }
                            });
                            $("#jbgdCdId option:eq(0)").attr("selected", "selected");
                            $("#s2id_jbgdCdId > a > span:eq(0)").text("직급선택");
                            $("#dplctChckId").html("");
                            opCount++;
                        } else {
                            return;
                        }
                    }
                });
            }

        };

        //]]>
    </script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertMngr.do" class="form-horizontal">

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="picId" class="control-label col-md-2">아이디 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="picId" id="picId" value="" class="form-control" placeholder="아이디를 입력하세요." />
                            </div>
                            <div class="col-md-6" id="dplctChckId">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="picId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="picNm" class="control-label col-md-2">이름 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="picNm" id="picNm" value="" class="form-control" placeholder="이름을 입력하세요." />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="picNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="jbgdCdId" class="control-label col-md-2">직급 <span class="mandatory">*</span></label>
                    <div class="col-md-6">
                        <op:cdId id="jbgdCdId" type="select" hghrkCdId="ofcps" cdId="ofcps" etc="style='width:82%;'" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="deptCdId" class="control-label col-md-2">부서명 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <p class="form-control-static" id="deptNm"></p>
                                <input type="hidden" name="deptCdId" id="deptCdId" value="" class="form-control" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="picPswd" class="control-label col-md-2">비밀번호 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="password" name="picPswd" id="picPswd" value="" maxlength="32" class="form-control" placeholder="담당자비밀번호" />
                            </div>
                            <div class="col-md-6" id="allowPasswd"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
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
                            <div class="col-md-3">
                                <button type="button" class="btn btn-xs btn-info" onclick="opScrtyGradPop();">보안등급정책</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="control-label col-md-2">비밀번호 확인 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="password" name="confirmPassword" id="confirmPassword" value="" maxlength="32" class="form-control" placeholder="담당자비밀번호 확인" />
                            </div>
                            <button type="button" class="btn btn-info" onclick="opPasswdView();">비밀번호보기</button>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rgnTelno" class="control-label col-md-2">전화번호 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" name="rgnTelno" id="rgnTelno" value="" maxlength="4" class="form-control" />
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="telofcTelno" id="telofcTelno" value="" maxlength="4" class="form-control" />
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="indivTelno" id="indivTelno" value="" maxlength="4" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
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
                    <label for="rgnFxno" class="control-label col-md-2">팩스번호 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" name="rgnFxno" id="rgnFxno" value="" maxlength="4" class="form-control" />
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="telofcFxno" id="telofcFxno" value="" maxlength="4" class="form-control" />
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="indivFxno" id="indivFxno" value="" maxlength="4" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
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
                    <label for="mblRgnTelno" class="control-label col-md-2">휴대전화번호 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" name="mblRgnTelno" id="mblRgnTelno" maxlength="4" value="" class="form-control" />
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="mblTelofcTelno" id="mblTelofcTelno" maxlength="4" value="" class="form-control" />
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="mblIndivTelno" id="mblIndivTelno" maxlength="4" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
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
                    <label for="emlId" class="control-label col-md-2">이메일 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" name="emlId" id="emlId" value="" class="form-control" />
                            </div>
                            <div class="col-md-1">
                                <p class="form-control-static">@</p>
                            </div>
                            <div class="col-md-4">
                                <input type="text" name="emlSiteNm" id="emlSiteNm" value="" class="form-control" placeholder="zesinc.co.kr" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="emlId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="taskCn" class="control-label col-md-2">담당업무 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-10">
                                <textarea name="taskCn" id="taskCn" rows="5" class="form-control" placeholder="담당업무를 입력해주세요"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="taskCn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-md-2">사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" checked="checked" class="styled" />
                                    예
                                </label>
                                <label for="radioN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="block clearfix">
                <div class="col-md-12 btn-group">
                    <div class="text-right">
                        <button type="button" class="btn btn-success" onclick="opInsertMngr();">저장</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>