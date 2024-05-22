$(document).ready(function() {

    /**
     * 아이디 중복체크
     */
    $("#picId").keyup(function(event) {
        var len = $(this).val().length;
        if(len >= 2) {
            // 기존담당자아이디가 아닌경우 중복체크
            if($("#beforePicId").val() != $("#picId").val()) {
                $.ajax({
                    url : "ND_selectDplctChckId.do",
                    type : "GET",
                    dataType : "json",
                    data : {
                        picId : $("#picId").val(),
                        beforeDeptCdId : $("#beforeDeptCdId").val(),
                        q_date : new Date().toString()
                    },
                    success : function(response) {
                        if(response.result) {
                            $("#dplctChckId").html("<p class=\"text-info form-control-static\">" + response.message + "</p>");
                        } else {
                            $("#dplctChckId").html("<p class=\"text-danger form-control-static\">" + response.message + "</p>");
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

    /**
     * 대상 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chk-all]").click(function() {
        var isChecked = this.checked;

        $("input[name=sns]").prop("checked", isChecked);
        if(isChecked) {
            $("tbody :checkbox").parent().parent().addClass("info");
        } else {
            $("tbody :checkbox").parent().parent().removeClass("info");
        }
    });

    /**
     * 대상 체크박스 선택에 따른 색상변경
     */
    $("input[name=sns]").click(function() {

        if($(this).is(":checked")) {
            $(this).parent().parent().addClass("info");
        } else {
            $(this).parent().parent().removeClass("info");
        }
    });
});

/** 비밀번호보기 */
var opPasswdView = function() {
    if($("#picPswd").val() == '') {
        opMsg("입력하신 비밀번호가 없습니다.");
    } else {
        opMsg("입력하신 비밀번호는 [" + $("#picPswd").val() + "]입니다.");
    }
};

/** 체크된 담당자 아이디 가져오기 */
var opCheckedArray = function() {
    var selectedPicId = new Array;

    $("input[name=sns]:checked").each(function(i) {
        selectedPicId[i] = $(this).val();
    });

    return selectedPicId;
};

/** 비밀번호 보안등급정책 팝업 */
var opScrtyGradPop = function() {
    var option = "chrome, centerscreen, dependent=yes, width=700, height=570, dialog=yes, modal=yes, resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
    window.open("PD_ScrtyGrad.do", "passwdScrtyGrad", option);
};

/** 담당자 목록 검색 */
var opMngrSearch = function(page) {

    if(typeof page != "undefined") {
        $("#dataForm input[id=q_currPage]").val(page);
    }

    var params = $("#dataForm").serialize();
    $("#" + parent.options.CONT_PAN_ID).load(parent.options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
        if(statusTxt != "success") {
            opSysErrorMsg(responseTxt);
        }
    });
};

/** 담당자 목록 검색 초기화 */
var opMngrSearchReset = function() {

    $("#dataForm [name^='" + OpConfig.prefixSearchParam + "']").each(function() {
        if($(this).attr("type") != "hidden") {
            $(this).val("");
        }
    });
    // 페이징 초기화
    $("#q_currPage").val("1");

    var params = $("#dataForm").serialize();
    $("#" + parent.options.CONT_PAN_ID).load(parent.options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
        if(statusTxt != "success") {
            opSysErrorMsg(responseTxt);
        }
    });
};

/** 지정권한 메뉴목록 팝업 */
var opAuthorPop = function(authrtCdId, authrtNm) {
    var url = "/intra/authorAsgn/PD_selectAuthorMenuList.do?q_authrtCdId=" + authrtCdId + "&q_authrtNm=" + authrtNm;
    var option = "chrome, centerscreen, dependent=yes, width=680, height=500, dialog=yes, modal=yes, resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
    window.open(encodeURI(url), "authorAsgnMenuList", option);
};
