<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>
<%@ page import="zesinc.intra.user.support.UserConsts" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<c:set value="<%=UserConsts.LOG_TYPE%>" var="logType" />
<c:set value="<%=UserConsts.LOG_USE_YN%>" var="logUseYn" />

<!DOCTYPE html>
<html lang="ko">
<head>
<title>조회사유입력 폼</title>
<op:jsTag type="intra" items="opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>
<script type="text/javascript">
    //<![CDATA[
    var logType = "${logType}";
    var logUseYn = "${logUseYn}";

    <valid:script type="msgbox" />
    $(document).ready(function() {
    });

    var customValidate = function() {
        // 비번 맞나틀리나 체크
        if(!opCheckPicPswd()) return false;

        return true;
    };

    var opSubmit = function() {
        var logCn = $("#logCn").val();
        var pwd = $("#picPswd").val();
        if($.trim(pwd) == ''){
            opWarningMsg("비밀번호를 입력해주세요");
            return;
        }
        if($.trim(logCn) == ''){
            opWarningMsg("사유를 입력해주세요");
            return;
        }
        
        if(validate()){
            if ($.trim(pwd) != "") {
                var enc_val = opEncrypt(pwd);
                $("#picPswd").val(enc_val);
            }
            
            if(logType == 'searchLog'){
                $("#dataForm").ajaxSubmit({
                    url      : "ND_insertUserSearchLog.do",
                    type     : "POST",
                    dataType : "json",
                    success  : function(response) {
                        if (response.result) {
                            parent.opListSubmit();
                        } else {
                            opJsonMsg(response);
                            $("#picPswd").val("");
                        }
                    }
                });
            }else if(logType == 'personalLog'){
                $("#dataForm").ajaxSubmit({
                    url      : "ND_insertPersonalUserInfoLog.do",
                    type     : "POST",
                    dataType : "json",
                    success  : function(response) {
                        if (response.result) {
                            location.href= response.message;
                        } else {
                            opJsonMsg(response);
                            $("#picPswd").val("");
                        }
                    }
                });
            }
        }
    };
    //]]>
</script>
</head>
<body>
<form name="dataForm" id="dataForm" method="post" class="form-horizontal">
    <input type="hidden" name="menuSeCd" id="menuSeCd" value="R" />
    <input type="hidden" name="menuNm" id="menuNm" value="${param.menuNm}" />
    <input type="hidden" name="menuSn" id="menuSn" value="${param.menuSn}" />
    <c:if test="${logType eq 'searchLog'}">
        <input type="hidden" name="searchId" id="searchId" value="${param.searchId}" />
        <input type="hidden" name="srchStngCn" id="srchStngCn" value="${param.srchStngCn}" />
    </c:if>
    <op:pagerParam view="view" />
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-paragraph-right2"></i> 조회사유 입력폼</h6></div>
        <div class="panel-body">
            <div class="form-group">
                <label for="picPswd" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 비밀번호
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="password" name="picPswd" id="picPswd" value="" class="form-control" placeholder="비밀번호" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="logCn" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 조회사유
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-12">
                            <textarea rows="4" cols="6" id="logCn" name="logCn" placeholder="조회사유" class="limited form-control"></textarea>
                            <span class="help-block" id="limit-text">사용자 정보를 조회하는 사유를 입력하세요.</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 버튼 -->
    <div class="block clearfix text-center">
        <div class="btn-group btn-group-lg ">
            <button type="button" onclick="opSubmit();" class="btn btn-primary">전송</button>
        </div>
    </div>
    <!-- //버튼 -->
</form>
</body>
</html>