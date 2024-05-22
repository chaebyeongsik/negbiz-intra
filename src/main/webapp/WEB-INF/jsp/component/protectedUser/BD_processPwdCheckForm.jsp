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
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>비밀번호 확인 폼</title>
<op:jsTag type="intra" items="opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />
<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            opSubmit();
            return false;
        });
    });
    var opSubmit = function() {
        var pwd = $("#picPswd").val();
        if($.trim(pwd) == ''){
            opWarningMsg("비밀번호를 입력해주세요");
            return;
        }
        if($.trim(pwd) != ''){
            var enc_val = opEncrypt(pwd);
            $("#picPswd").val(enc_val);
            $("#dataForm").ajaxSubmit({
                url      : "ND_processPwdCheck.do",
                type     : "POST",
                dataType : "json",
                success  : function(response) {
                    if (response.result) {
                        self.location.href = response.message;
                    }else{
                        opJsonMsg(response);
                        $("#picPswd").val("");
                    }
                }
            });
        }
    };
    //]]>
</script>
</head>
<body>
<form name="dataForm" id="dataForm" method="post" action="ND_processPwdCheck.do" class="form-horizontal">
    <input type="hidden" name="returnUrl" id="returnUrl" value="${param.returnUrl}" />
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-paragraph-right2"></i> 비밀번호 입력폼</h6></div>
        <div class="panel-body">
            <div class="form-group col-sm-5">
                <label for="picPswd" class="control-label col-sm-3 text-right">
                    비밀번호
                </label>
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-sm-12">
                            <input type="password" name="picPswd" id="picPswd" placeholder="비밀번호를 입력하세요" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="help-block op-validate col-sm-12">
                             <ul class="list-group">
                                <li class="list-group-item"><span class="validate-msg">로그인시 사용한 비밀번호를 입력하세요.</span></li>
                             </ul>
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