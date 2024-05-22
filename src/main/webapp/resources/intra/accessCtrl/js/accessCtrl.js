/* 공통 초기화 기능 */
$(document).ready(function() {

    /** 사이트담당자 설정 Autocomplete */
    $("#tmpPicNm").autocomplete({
        minLength : 1,
        delay : 800,
        source : function(request, response) {
            $.ajax({
                url : "ND_selectMngrList.do",
                type : "get",
                dataType : "json",
                data : {
                    q_picNm : request.term,
                    q_useYn : "Y",
                    q_maxRowNum : 20
                },
                success : function(data) {
                    response(data);
                },
                error : function(response) {
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        },
        focus : function(event, ui) {
            return false;
        },
        close : function(event, ui) {
            $("#tmpPicNm").val("");
            $("#toggleButton").show();
        },
        select : function(event, ui) {
            $("#prmsnId").val(ui.item.picId);
            $("#chrgDeptNm").html(ui.item.deptNm + "&nbsp;&nbsp;/&nbsp;&nbsp;" + ui.item.picNm);
        }
    });

});

// 허용유형에 따른 변경
var opChangePermTy = function(prmsnTypeNm) {
    if(!prmsnTypeNm) {
        prmsnTypeNm = "IP";
    }
    if(prmsnTypeNm == "IP") {
        $("#tmpPicNm").removeAttr("placeholder");
        $("#tmpPicNm").prop("disabled", true);
        $("#tmpPicNm").val("");
        $("#prmsnBgngIp").prop("disabled", false);
        $("#prmsnEndIp").prop("disabled", false);
        deleteCharger();
    } else {
        $("#tmpPicNm").attr("placeholder", "담당자 이름을 입력하세요.");
        $("#tmpPicNm").prop("disabled", false);
        $("#prmsnBgngIp").prop("disabled", true);
        $("#prmsnEndIp").prop("disabled", true);
        $("#prmsnBgngIp").val("");
        $("#prmsnEndIp").val("");
    }
};

/*
 * 허용ID 담당자 삭제(히든 폼이 존재하므로 버튼 삭제)
 */
var deleteCharger = function() {
    $("#tmpPicNm").val("");
    $("#prmsnId").val("");
    $("#chrgDeptNm").text("");
    $("#toggleButton").hide();
};