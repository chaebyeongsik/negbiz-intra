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
<title>출력사유입력 폼</title>
<op:jsTag type="intra" items="opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />
<script type="text/javascript">
    //<![CDATA[
    <valid:script type="msgbox" />
    $(document).ready(function() {

        //검색조건별로 출력하고 싶은경우 파라메터 추가
        $("#dataForm [name^='" + OpConfig.prefixSearchParam + "']").each(function() {
            var param = "<input type='hidden' name='" + $(this).attr("name") + "' value='" + $(this).val() + "' />";
            $("#excelForm").append(param);
        });
    });
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
            $("#dataForm").attr("action", "/intra/user/hist/ND_insertUserOutputLog.do");
            $("#dataForm").submit();
            opCloseWin();
        }
    };
    //]]>
</script>
</head>
<body>
<form name="dataForm" id="dataForm" method="post" class="form-horizontal">
    <input type="hidden" name="excelKey" id="excelKey" value="${param.excelKey}" />
    <input type="hidden" name="menuSeCd" id="menuSeCd" value="O" />
    <op:pagerParam view="view" />
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-paragraph-right2"></i> 출력사유 입력폼</h6></div>
        <div class="panel-body">
            <div class="form-group">
                <label for="picPswd" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 비밀번호
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="password" name="picPswd" id="picPswd" value="" class="form-control" placeholder="비밀번호" autocomplete="off" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="logCn" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 출력사유
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-12">
                            <textarea rows="3" cols="7" id="logCn" name="logCn" placeholder="출력사유" class="limited form-control"></textarea>
                            <span class="help-block" id="limit-text">사용자 정보를 출력하는 사유를 입력하세요.</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 버튼 -->
    <div class="block clearfix text-center">
        <div class="btn-group btn-group-lg ">
            <button type="button" onclick="opSubmit();" class="btn btn-primary">엑셀출력</button>
        </div>
    </div>
    <!-- //버튼 -->
</form>
</body>
</html>