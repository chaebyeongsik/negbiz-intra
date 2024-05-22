var dummyTr = "<tr><td colspan='7' class='text-center text-warning'>권한담당자를 지정해주십시오.</td></tr>";

$(document).ready(function() {

    /**
     * 지정권한담당자목록 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chkall]").click(function() {
        var isChecked = this.checked;

        $("input[name=chargers]").prop("checked", isChecked);
        if (isChecked) {
            $(this).parent().parent().parent().parent().find("tbody > tr").addClass("info");
        } else {
            $(this).parent().parent().parent().parent().find("tbody > tr").removeClass("info");
        }
    });

    /**
     * 선택된 지정권한 highlight처리
     */
    $("input[name=authrtCdId]").click(function() {
        var checkStr = $.trim($(this).parent().parent().parent().find("span").text());
        if ($(this).prop("checked")) {
            $("#authorTab").jmHighlight(checkStr);
        } else {
            $("#authorTab").jmRemoveHighlight({}, checkStr);
        }
    })
    

});

/**
 * 지정권한담당자 목록 추가
 */
var opAddMngrList = function(data) {

    // 지정권한담당자목록에 기본row 삭제
    if ($("#authorTab > tbody > tr").find("input[type=checkbox]").length == 0) {
        $("#authorTab > tbody > tr").remove();
    }

    for (var i = 0; i < data.length; i++) {

        var authorList = new Array;
        var authorAsgnList = data[i].authorAsgnList;
        for (var j = 0; j < authorAsgnList.length; j++) {
            authorList[j] = authorAsgnList[j].authrtNm;
        }

        var listHtml = "<tr>" + "<td class='text-center'><input type='checkbox' name='chargers' value='" + data[i].picId + "' onclick='opChangeClass(this);' /></td>" + "<td class='text-center'>" + data[i].picId + "</td>" + "<td class='text-center'>" + data[i].deptNm + "</td>" + "<td class='text-center'>" + data[i].clsfNm + "</td>" + "<td class='text-center'>" + data[i].picNm + "</td>" + "<td class='text-center'>" + authorList.join(", ") + "</td>" + "<td class='text-center'><button type='button' class='btn btn-danger' onclick='opDeleteTr(this);'>삭제</button></td>" + "</tr>";

        // 중복된 목록 체크
        var dplctChk = true;
        $("#authorTab > tbody > tr").find("input[type=checkbox]").each(function() {
            if ($(this).val() == data[i].picId)
                dplctChk = false;
        })

        // 중복되지 않은 목록만 추가
        if (dplctChk) {
            $("#authorTab > tbody").append(listHtml);
        }
    }
    
    /**
     * 선택된 지정권한 highlight처리
     */
    $("input[name=authrtCdId]:checked").each(function() {
        var checkStr = $.trim($(this).parent().parent().parent().find("span").text());
        if ($(this).prop("checked")) {
            $("#authorTab").jmHighlight(checkStr);
        } else {
            $("#authorTab").jmRemoveHighlight({}, checkStr);
        }
    })
    
}

/**
 * 지정권한담당자목록 체크박스 선택에 따른 색상변경
 */
var opChangeClass = function(el) {
    if (el.checked) {
        $("input[name=chargers]").parent().parent().addClass("info");
    } else {
        $("input[name=chargers]").parent().parent().removeClass("info");
    }
}

/**
 * 지정권한담당자 선택목록 삭제
 */
var opDeleteChk = function(el) {
    var selectedPicId = new Array;
    $("input[name=chargers]:checked").each(function(i) {
        selectedPicId[i] = $(this).val();
        $(this).parent().parent().remove();
    });

    if (selectedPicId.length == 0) {
        opWarningMsg("지정권한을 선택해 주세요.");
        return;
    }

    if ($("#authorTab > tbody > tr").find("input[type=checkbox]").length < 1) {
        $("#authorTab > tbody").append(dummyTr);
        $("input[name=chkall]").prop("checked", false);
    }
}

/**
 * 지정권한담당자 삭제
 */
var opDeleteTr = function(el) {
    $(el).parent().parent().remove();

    if ($("#authorTab > tbody > tr").find("input[type=checkbox]").length < 1) {
        $("#authorTab > tbody").append(dummyTr);
    }
}

/** 지정권한 메뉴목록 팝업 */
var opAuthorPop = function(authrtCdId, authrtNm) {
    var url = "/intra/authorAsgn/PD_selectAuthorMenuList.do?q_authrtCdId=" + authrtCdId + "&q_authrtNm=" + authrtNm;
    var option = "chrome, centerscreen, dependent=yes, width=680, height=500, dialog=yes, modal=yes, resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
    window.open(encodeURI(url), "authorAsgnMenuList", option);
};

/** 지정권한 전체할당 */
var opInsertAuthorAsgn = function () {
    if ($("input[name=authrtCdId]:checked").length < 1) {
        opWarningMsg("지정권한을 선택해 주세요.");
        return;
    }
    
    if ($("#authorTab > tbody > tr").find("input[type=checkbox]").length < 1) {
        opWarningMsg("담당자를 추가해 주세요.");
        return;
    }
    
    var authors = "";
    var chargers = "";
    
    $("input[name=authrtCdId]:checked").each(function(i) {
        authors += (authors  != "" ? "," : "") + $(this).val();
    });
    $("input[name=chargers]").each(function(i) {
        chargers += (chargers != "" ? "," : "") + $(this).val();
    });
    
    $("#dataForm > input[name=authrtCdIds]").val(authors);
    $("#dataForm > input[name=picIds]").val(chargers);
    $("#dataForm").submit();
}

/** 담당자 목록 검색 */
var opMngrSearch = function(page) {
    if (page) $("#mngrForm [name=q_currPage]").val(page);
    
    var params = $("#mngrForm").serialize();
    $("#" + parent.contPadId).load(parent.options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
        if(statusTxt != "success") {
            opSysErrorMsg(responseTxt);
        }
    });
}

/** 담당자 목록 검색 초기화 */
var opMngrSearchReset = function() {
    
    $("#mngrForm [name^='"+ OpConfig.prefixSearchParam +"']").each(function() {
        if($(this).attr("type") != "hidden") {
            $(this).val("");
        }
    });
    // 페이징 초기화
    $("#q_currPage").val("1");
    
    var params = $("#mngrForm").serialize();
    $("#" + parent.contPadId).load(parent.options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
        if(statusTxt != "success") {
            opSysErrorMsg(responseTxt);
        }
    });
}
