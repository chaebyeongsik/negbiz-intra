/**
 * 사용자 등급 수정폼 로드
 */
var opUpdateUserGradForm = function(userGrdCdId){
    $("#userGrad_View").load("INC_updateUserGradForm.do", {userGrdCdId : userGrdCdId}, function(result) {
    });
};

/**
 * 사용자 등급 등록폼 로드
 */
var opInsertUserGradForm = function(){
    $("#userGrad_View").load("INC_insertUserGradForm.do", function(result) {
    });
};

/**
 * 등록처리
 */
var opInsertAction = function() {
    if(!validate()){
        return;
    }
    $("#dataForm").ajaxSubmit({
        url : "ND_insertUserGrad.do",
        type : "POST",
        dataType : "json",
        async : false,
        success : function(response) {
            if (response) {
                opJsonMsg(response);
                if (response.result) {
                    location.href = "/intra/user/grad/BD_selectUserGradList.do";
                }
            }
        }
    });
};

/**
 * 수정처리
 */
var opUpdateAction = function() {
    if(!validate()){
        return;
    }
    $("#dataForm").ajaxSubmit({
        url : "ND_updateUserGrad.do",
        type : "POST",
        dataType : "json",
        async : false,
        success : function(response) {
            if (response) {
                opJsonMsg(response);
                if(response.result){
                    location.href = "BD_selectUserGradList.do?userGrdCdId=" + $("#userGrdCdId").val();
                }
            }
        }
    });
};

var customValidate = function() {
    if ($("#dupCheckVal").val() != 'Y') {
        // 수정화면, 사용자등급코드를 수정한 상태 
        if(($("#originalUserGradCode").val() != '' && $("#originalUserGradCode").val() != $("#userGrdCdId").val())
                || $("#originalUserGradCode").val() == ''){
            opWarningMsg("입력하신 사용자등급코드가 이미 존재합니다. 사용자등급코드를 다시 입력하여 주시기바랍니다.");
            return false;
        }
    }
    return true;
};

/**
 * 사용자등급코드 중복체크
 */
var opDupCheckUserGradCode = function(userGrdCdId){
    if($("#userGrdCdId").val() != ''){
        $.ajax({
            url : "ND_selectDplctChckGrad.do",
            type : "POST",
            dataType : "json",
            data : {
                userGrdCdId : $("#userGrdCdId").val(),
                originalUserGradCode : $("#originalUserGradCode").val(),
                q_date : new Date().toString()
            },
            success : function(data) {
                if (data.result) {
                    $("#dupCheckMessage").html("<p class=\"text-info form-control-static\">사용 가능한 코드입니다.</p>");
                    $("#dupCheckVal").val("Y");
                } else {
                    $("#dupCheckMessage").html("<p class=\"text-danger form-control-static\">입력하신 사용자등급코드가 이미 존재합니다.</p>");
                    $("#dupCheckVal").val("N");
                }
            }
        });
    }else{
        $("#dupCheckMessage").html("");
        $("#dupCheckVal").val("");
    }
};