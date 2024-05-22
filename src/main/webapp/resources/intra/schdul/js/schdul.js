/*
 * 등록/수정 관련
 */

/**
 * 일정과 휴일에 따른 입력 폼 변경
 */
var toggleSe = function(radioNm) {
    var val = $("input[name='" + radioNm + "']:checked").val();
    if(val == "program") {
        $(".program").show();
        $(".restde").hide();
    } else {
        $(".program").hide();
        $(".restde").show();
    }
};

/**
 * 반복여부에 따른 입력 폼 변경
 */
var toggleRepttit = function(radioNm) {
    var val = $("input[name='" + radioNm + "']:checked").val();
    if(val == "Y") {
        $(".rpttYn").show();
        $("#rpttEndYmd").prop("disabled", false);
        $("input[name='rpttTypeCd']").prop("disabled", false);
    } else {
        $(".rpttYn").hide();
        $("#rpttEndYmd").prop("disabled", true);
        $("input[name='rpttTypeCd']").prop("disabled", true);
    }
};

/**
 * 반복유형이 주간인 경우에 따른 입력 폼 변경
 */
var toggleWeek = function(radioNm) {
    var val = $("input[name='" + radioNm + "']:checked").val();
    if(val == "W") {
        $(".rpttTypeCd").show();
    } else {
        $(".rpttTypeCd").hide();
    }
};

/**
 * 일정 삭제
 */
var opSchdulDelete = function(url) {
    if(confirm("정말 삭제하시겠습니까?")) {
        $.ajax({
            url : url,
            type : "POST",
            data : $("#dataForm").serialize(),
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if(response.result) {
                    parent.location.reload(false);
                }
            },
            error : function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    }
};
