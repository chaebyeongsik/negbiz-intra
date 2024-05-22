$(document).ready(function() {

    // 템플릿ID 중복체크
    $("#tmpltId").keyup(function(event) {
        var len = $(this).val().length;
        if(len >= 2) {
            // 기존템플릿ID가 아닌경우 중복체크
            if($("#beforeTmpltId").val() != $("#tmpltId").val()) {
                $.ajax({
                    url : "ND_selectDplctChckId.do",
                    type : "GET",
                    dataType : "json",
                    data : {
                        tmpltTypeCd : $("#tmpltTypeCd").val(),
                        tmpltId : $("#tmpltId").val(),
                        date : new Date().toString()
                    },
                    success : function(response) {
                        if(response == 0) {
                            $("#dplctChckId").html("<p class=\"text-info form-control-static\">" + OpMessage.common.dplctChckTrue + "</p>");
                        } else if(response == 1) {
                            $("#dplctChckId").html("<p class=\"text-danger form-control-static\">" + OpMessage.common.dplctChckFalse + "</p>");
                        } else {
                            $("#dplctChckId").html("<p class=\"text-danger form-control-static\">" + OpMessage.common.validateFail + "</p>");
                        }
                    }
                });
            } else {
                $("#dplctChckId").html("");
            }
        }
    }).keydown(function(event) {
        if(event.which == 13) {
            event.preventDefault();
        }
    });
});

/**
 * 목록
 */
var opBbsTmplatList = function() {
    location.href = "BD_selectBbsTmplatList.do?" + opSearchQueryString("dataForm");
};

/**
 * 등록 폼
 */
var opBbsTmplatInsertForm = function() {
    // $("#q_regSn").val("");
    location.href = "BD_insertBbsTmplatForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 수정 폼
 */
var opBbsTmplatUpdateForm = function() {
    // $("#q_tmpltTypeCd").val();
    // $("#q_tmpltId").val();
    location.href = "BD_updateBbsTmplatForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 삭제
 */
var opBbsTmplatDelete = function() {
    var msg = "정말 삭제하시겠습니까?";
    var applcBbsCo = Number($("#applcBbsCo").val());
    if(applcBbsCo > 0) {
        msg = "적용중인 게시판이 있습니다.\n정말 삭제하시겠습니까?";
    }
    if(!confirm(msg))
        return;
    $("#dataForm").attr("action", "ND_deleteBbsTmplat.do");
    $("#dataForm").submit();
};
