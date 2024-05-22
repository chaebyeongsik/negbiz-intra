/*
 * CMS 메뉴 관리 관련 스크립트 모음
 */

$(document).ready(function() {
    /** 표시담당자 설정 Autocomplete */
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
        },
        select : function(event, ui) {
            $("#picId").val(ui.item.picId);
            $("#tkcgDeptCdId").val(ui.item.deptCdId);

            $("#chrgDeptNm").html(ui.item.deptNm + "&nbsp;&nbsp;/&nbsp;&nbsp;" + ui.item.picNm);
            $("#chargerToggleButton").show();
        }
    });

    /** 관리부서 설정 Autocomplete */
    $("#tmpAuthorDeptNm").autocomplete({
        minLength : 1,
        delay : 800,
        source : function(request, response) {
            $.ajax({
                url : "ND_selectDeptList.do",
                type : "get",
                dataType : "json",
                data : {
                    q_deptNm : request.term,
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
            $("#tmpAuthorDeptNm").val("");
        },
        select : function(event, ui) {
            $("#authrtDeptCdId").val(ui.item.value);

            $("#authorDeptNm").html(ui.item.deptNm);
            $("#authorDeptToggleButton").show();
        }
    });

    /** 관리담당자 설정 Autocomplete */
    $("#tmpAuthorChargerNm").autocomplete({
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
            $("#tmpAuthorChargerNm").val("");
        },
        select : function(event, ui) {
            if($("#authorPicIds").containsOption(ui.item.picId)) {
                opWarningMsg("이미 등록된 담당자입니다.");
            } else {
                $("#authorPicIds").addOption(ui.item.picId, ui.item.deptNm + " / " + ui.item.picNm);
            }
        }
    });

    // 코드 중복 체크
    $("#userMenuEngNm").keyup(function(event) {
        var len = $(this).val().length;
        if(len >= 2) {
            $.ajax({
                url : "ND_selectDplctChckCode.do",
                type : "GET",
                dataType : "json",
                data : {
                    cdId : $(this).val(),
                    q_siteSn : $("#siteSn").val(),
                    q_userMenuEngNm : $(this).val(),
                    q_date : new Date()
                },
                success : function(response) {
                    var msg = response.message;
                    if(response.result) {
                        $("#duplicateText").html("<p class=\"text-info form-control-static\">" + msg + "</p>");
                        $("#duplicateYn").val("Y");
                    } else {
                        $("#duplicateText").html("<p class=\"text-danger form-control-static\">" + msg + "</p>");
                        $("#duplicateYn").val("N");
                    }
                },
                error : function(response) {
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        }
    }).keydown(function(event) {
        if(event.which == 13) {
            event.preventDefault();
        }
    });
});


/*
 * 서브메뉴를 선택 시 해당 URL을 관리자와 사용자 URL에 설정한다.
 * 선택값이 없다면 "" 으로 설정한다.
 */
var changeCnt = 0;
var opChangeMethod = function(selectObj) {
    $("#lwprtMenuUrlAddrDiv").children().remove();

    var selectValue = selectObj.value;
    if(selectValue == "") {
        $("#userMenuUrlAddr, #mngrMenuUrlAddr").val("");
    } else {
        var rlCntxt = "";
        var sitePathNm = $("#sitePathNm").val();
        var actlStngYn = $("#actlStngYn").val();
        if(actlStngYn && actlStngYn == "N" && sitePathNm != "/") {
            rlCntxt = $("#sitePathNm").val();
        }
        var refernce = cntrlData[selectValue];
        var userMainMenuUrl = refernce.userMainMenuUrl;
        var mngrMenuUrlAddr = refernce.mngrMenuUrlAddr;
        var lwprtMenuUrlAddr = refernce.lwprtMenuUrlAddr;

        $("#userMenuUrlAddr").val(rlCntxt + userMainMenuUrl);
        $("#mngrMenuUrlAddr").val(mngrMenuUrlAddr);

        // 폼추가
        for(var i = 0 ; i < lwprtMenuUrlAddr.length ; i++) {
            addLwprtMenuUrlAddr();
        }
        // 추가된 폼에 하위URL 입력
        $("input[name='lwprtMenuUrlAddr']").each(function(idx) {
            $(this).val(rlCntxt + lwprtMenuUrlAddr[idx]);
        });
    }
};

// 메뉴유형에 따른 화면 표시 그룹 토클
var toggleMenuType = function(radioObj) {
    toggleMenuTypeCode($(radioObj).val());
};

//메뉴유형에 따른 화면 표시 그룹 토클
var toggleMenuTypeCode = function(val) {
    if(val == "LINK") {
        $(".contents-group").hide();
        $(".program-group").hide();
    } else if(val == "CONTENTS") {
        $(".contents-group").show();
        $(".program-group").hide();
    } else if(val == "PROGRAM") {
        $(".contents-group").hide();
        $(".program-group").show();
    }
};

// 담당자표시여부에 따른 화면 표시 그룹 토클
var toggleChargerIndictTy = function(radioObj) {
    var val = $(radioObj).val();
    if(val == "Y") {
        $(".charger-group").show();
    } else {
        $(".charger-group").hide();
    }
};

// 권한유형에 따른 화면 표시 그룹 토클
var toggleAuthrtGroupNm = function(radioObj) {
    var val = $(radioObj).val();
    if(val == "IN_DEPT" || val == "EQ_DEPT") {
        $(".author-dept-group").show();
        $(".author-charger-group").hide();
    } else if(val == "CHARGER") {
        $(".author-dept-group").hide();
        $(".author-charger-group").show();
    } else {
        $(".author-dept-group").hide();
        $(".author-charger-group").hide();
    }
};

/*
 * 관리부서 정보 삭제(히든 폼이 존재하므로 버튼 삭제)
 */
var deleteAuthorDept = function() {
    $("#tmpAuthorDeptNm").val("");
    $("#authrtDeptCdId").val("");
    $("#authorDeptNm").text("");

    $("#authorDeptToggleButton").hide();
};

/*
 * 관리담당자 정보 삭제(selected 항목 삭제)
 */
var deleteAuthorCharger = function() {
    $("#tmpAuthorChargerNm").val("");
    $("#authorPicIds").removeOption(/./, true);
};

/*
 * 표시담당자 정보 삭제(히든 폼이 존재하므로 버튼 삭제)
 */
var deleteCharger = function() {
    $("#tmpPicNm").val("");
    $("#picId").val("");
    $("#tkcgDeptCdId").val("");
    $("#chrgDeptNm").text("");

    $("#toggleButton").hide();
};

/*
 * 메타정보관리에서 저작권사용여부에 따른 폼변경
 */
var toggleCpyrhtUseAt = function(radioObj) {
    var val = $(radioObj).val();
    if(val == "Y") {
        $(".cpyrht-group").show();
    } else {
        $(".cpyrht-group").hide();
    }
};

// 사이트 생성 후 초기화 등록 화면
var initSite = function() {
    var params = {};
    params[options.BASE_PARAM_NM] = options.BASE_PARAM_VAL;
    params[options.NODE_NM] = options.P_NODE_VAL;

    $("#" + options.CONT_PAN_ID).load(options.VIEW_URL, params, function(responseTxt, statusTxt, response) {
        if(statusTxt == "success") {
            if(typeof afterTreeView == "function") {
                afterTreeView(options.P_NODE_VAL, options.P_NODE_VAL, null);
            }
        } else {
            opSysErrorMsg(responseTxt);
        }
    }, "json");
};

/**
 * 하위URL 추가 
 */
var addLwprtMenuUrlAddr= function() {
    $("#lwprtMenuUrlAddrDiv").append($("#lwprtMenuUrlAddrDummy").html());
};

/**
 * 하위URL 삭제
 */
var delLwprtMenuUrlAddr = function(obj) {
    $(obj).parent().parent().remove();
};

// 이미지 삭제
var deleteImage = function(obj, id) {
    $("#" + id).val("");
    $(obj).parent().parent().remove();
};

/**
 * 등록된  카테고리 삭제 단! 최하위 노드만 삭제가 가능하다
 */
var opDelete = function(url) {
    if (confirm("정말 삭제하시겠습니까?\n삭제된 메뉴는 복구할 수 없습니다.")) {
        $.ajax({
            url : url,
            type : "POST",
            data : $("#dataForm").serialize(),
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    Optree.removeNode(response.value);
                    initView(contPadId);
                }
            },
            error :  function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    }
};
