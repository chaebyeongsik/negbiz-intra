/* 공통 초기화 기능 */
$(document).ready(function() {
    // 공지옵션 설정시 공지시작일과 공지종료일 항목에 켈린더 설정
    $("input[name='ntcBgngDt']").datepicker({
        dateFormat: "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".to-date").datepicker("option", "minDate", selectedDate);
        }
    });
    $("input[name='ntcEndDt']").datepicker({
        dateFormat: "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".from-date").datepicker("option", "maxDate", selectedDate);
        }
    });

    // 게시사용여부옵션 설정시 게시시작일과 게시종료일 항목에 켈린더 설정
    $("input[name='pstgBgngDt']").datepicker({
        dateFormat: "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".to-date").datepicker("option", "minDate", selectedDate);
        }
    });
    $("input[name='pstgEndDt']").datepicker({
        dateFormat: "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".from-date").datepicker("option", "maxDate", selectedDate);
        }
    });

    _prefix = "PD_";
    if($(location).attr("pathname").indexOf('BD_') > -1) { //팝업창에서 열리면 계속 팝업 아니면 팝업 출력안함
        _prefix = "BD_";
    };
});

/**
 * 목록
 */
var opList = function() {
    $("#q_bbsDocNo").val("");
    location.href =  _prefix + "selectBbsList.do?" + opSearchQueryString("dataForm");
};

/**
 * 상세
 */
var opView = function(bbsDocNo) {
    $("#q_bbsDocNo").val(bbsDocNo);
    location.href =  _prefix + "selectBbs.do?" + opSearchQueryString("dataForm");
};

/**
 * 취소
 */
var opCancel = function() {
    location.href =  _prefix + "selectBbs.do?" + opSearchQueryString("dataForm");
};

/**
 * 등록 폼
 */
var opInsertForm = function() {
    $("#q_bbsDocNo").val("");
    location.href =  _prefix + "insertBbsForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 수정 폼
 */
var opUpdateForm = function() {
    location.href =  _prefix + "updateBbsForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 답글 폼
 */
var opReplyForm = function() {
    location.href =  _prefix + "replyBbsForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 플래그삭제
 */
var opFlagDelete = function(mngrDelYn) {

    if("Y" == mngrDelYn) {
        if(!confirm("이 글을 플래그 삭제 하시겠습니까?\n삭제 후 사용자화면에서 관리자 삭제글로 표시됩니다."))
            return false;
    } else {
        if(!confirm("이 글을 플래그 삭제복구 하시겠습니까?"))
            return false;
    }

    $("#mngrDelYn").val(mngrDelYn);
    $("#dataForm").attr("method", "post");
    $("#dataForm").attr("action", "ND_deleteFlagDelete.do");
    $("#dataForm").submit();
};

/**
 * 목록 플래그삭제
 */
var opFlagDeleteList = function() {
    var bbsDocNos;
    var selectedPicId = new Array;
    $("input[name=bbsDocNo]:checked").each(function(i) {
        selectedPicId[i] = $(this).val();
    });

    if(selectedPicId.length == 0) {
        opWarningMsg("삭제 대상 게시물을 1개 이상 선택하세요.");
        return;
    } else {
        bbsDocNos = selectedPicId.join(",");
    }

    if(!confirm("선택한 " + selectedPicId.length + "개의 게시물을 삭제하시겠습니까?\n삭제 후 사용자화면에서 관리자 삭제글로 표시됩니다."))
        return false;

    $("#bbsDocNos").val(bbsDocNos);
    $("#mngrDelYn").val('Y');
    $("#dataForm").attr("method", "POST");
    $("#dataForm").attr("action", "ND_deleteFlagBbsList.do");
    $("#dataForm").submit();
};

/**
 * 완전삭제
 */
var opDelete = function() {

    if(!confirm("삭제하시면 첨부파일 및 댓글도 모두 삭제됩니다.\n\n게시물을 정말 삭제 하시겠습니까?\n삭제 후 복구는 불가능 합니다.")) {
        return false;
    }

    var dtrn = "상세 보기 삭제";

    $("#dtrn").val(dtrn);
    $("#dataForm").attr("method", "POST");
    $("#dataForm").attr("action", "ND_deleteBbs.do");
    $("#dataForm").submit();
};

/**
 * 목록 완전삭제
 */
var opDelete = function() {
    var bbsDocNos;
    var selectedPicId = new Array;
    $("input[name=bbsDocNo]:checked").each(function(i) {
        selectedPicId[i] = $(this).val();
    });

    if(selectedPicId.length == 0) {
        opWarningMsg("삭제 대상 게시물을 1개 이상 선택하세요.");
        return;
    } else {
        bbsDocNos = selectedPicId.join(",");
    }

    if(!confirm("선택한 " + selectedPicId.length + "개의 게시물을 정말 삭제하시겠습니까?\n삭제 후 복구는 불가능 합니다.")) {
        return false;
    }

    var dtrn = "목록 보기 삭제";

    $("#bbsDocNos").val(bbsDocNos);
    $("#dtrn").val(dtrn);
    $("#dataForm").attr("method", "POST");
    $("#dataForm").attr("action", "ND_deleteBbsList.do");
    $("#dataForm").submit();
};

/**
 * 게시글 게시판 이동폼
 */
var opMoveBbsPopup = function() {
    var bbsSn = $("#q_bbsSn").val();
    var bbsDocNo = $("#q_bbsDocNo").val();

    $().opmodal({
        width : 600,
        href : "PD_updateMoveBbsForm.do?q_bbsSn=" + bbsSn + "&q_bbsDocNo=" + bbsDocNo
    });
};

/**
 * ATOM, RSS 변환
 */
var opConversion = function(type) {

    var url = "http://" + "<%= request.getServerName() + ':' + request.getLocalPort() %>" + "<c:url value='/intra/bbs/ND_selectCnvr.do' />";

    if(window.clipboardData) {
        var copyAt = window.confirm("URL : " + url + " \n클립보드에 복사하시겠습니까?");
        if(copyAt) {
            window.clipboardData.setData("Text", url);
            opSuccessMsg('클립보드에 URL이 복사되었습니다.');
        }
    } else {

        return false;
        // 브라우져의 주소창에 about:config 를 치면 해당 설정 내용이 쭉 나타나는데
        // 그 중에서 Signed.applets.codebase_principal_support 라는 부분을 찾아서
        // true로 바꿔주면 문제없이 작동할 것이다.

        // dit is belangrijk maar staat nergens duidelijk vermeld:
        // you have to sign the code to enable this, or see notes below
        netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');

        // maak een interface naar het clipboard
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if(!clip)
            return;

        // maak een transferable
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if(!trans)
            return;

        // specificeer wat voor soort data we op willen halen; text in dit geval
        trans.addDataFlavor('text/unicode');

        // om de data uit de transferable te halen hebben we 2 nieuwe objecten nodig om het in op te slaan
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = url;
        str.data = copytext;
        trans.setTransferData("text/unicode", str, copytext.length * 2);

        var clipid = Components.interfaces.nsIClipboard;
        if(!clip)
            return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
        opSuccessMsg('클립보드에 URL이 복사되었습니다. ');
    }
};

/**
 * 게시글 만족도평가
 */
var opUpdateStsfdgEvl = function(btn, bbsSn, bbsDocNo, incrsLmttTime) {
    if(Number($("#stsfdgForm select[name=dgstfnSumScr]").val()) < 1) {
        opWarningMsg("만족도 점수를 선택하세요.");
        $("#stsfdgForm select[name=dgstfnSumScr]").focus();
        return false;
    }
    if((timeWait = opCheckCookieTime("stsfdgEvlCookie:" + bbsSn + ":" + bbsDocNo)) > 0) {
        $("#stsfdgForm select[name=dgstfnSumScr]").val("0");
        opWarningMsg(timeWait + "시간 후 다시 평가하실 수 있습니다.");
        return false;
    }
    var confirm = window.confirm("이 게시물을 평가하시겠습니까?");
    if(confirm != true)
        return;

    $("#stsfdgForm").ajaxSubmit({
        url : "ND_updateStsfdgEvl.do",
        type : "POST",
        dataType : "json",
        success : function(response) {
            if(response.result) {
                var result = response.value;
                if(Number(result.dgstfnSumScr) > 0) {
                    $("#stsfdgScoreAvg").html(Math.round(result.stsfdgEvlAvrg * 10) / 10);
                    $("#stsfdgSum").html(result.dgstfnSumScr);
                    $("#stsfdgCo").html(result.dgstfnEvlCnt);
                    opSetCookieExpTime("stsfdgEvlCookie:" + bbsSn + ":" + bbsDocNo, incrsLmttTime);
                } else {
                    opWarningMsg('지금은 평가하실 수 없습니다.');
                }
                $("#stsfdgForm select[name=dgstfnSumScr]").val("0");
            } else {
                opJsonMsg(response);
            }
        }
    });

};

/**
 * 게시글 추천
 */
var opUpdateRecomend = function(btn, bbsSn, bbsDocNo, incrsLmttTime) {
    if((timeWait = opCheckCookieTime("recommendCookie:" + bbsSn + ":" + bbsDocNo)) > 0) {
        opWarningMsg(timeWait + "시간 후 다시 추천하실 수 있습니다.");
        return false;
    }
    var confirm = window.confirm("이 게시물을 추천하시겠습니까?");
    if(confirm != true)
        return;

    $(btn).ajaxSubmit({
        url : "ND_updateRecomend.do",
        type : "POST",
        dataType : "json",
        data : {
            bbsSn : bbsSn,
            bbsDocNo : bbsDocNo
        },
        success : function(response) {
            if(response.result) {
                if(Number(response.value) > 0) {
                    $(btn).val("추천 " + response.value);
                    opSetCookieExpTime("recommendCookie:" + bbsSn + ":" + bbsDocNo, incrsLmttTime);
                } else {
                    opWarningMsg('지금은 추천하실 수 없습니다.');
                }
            } else {
                opJsonMsg(response);
            }
        }
    });

};

/**
 * 게시글 신고
 */
var opUpdateSttemnt = function(btn, bbsSn, bbsDocNo, incrsLmttTime) {
    if((timeWait = opCheckCookieTime("sttemntCookie:" + bbsSn + ":" + bbsDocNo)) > 0) {
        opWarningMsg(timeWait + "시간 후 다시 신고하실 수 있습니다.");
        return false;
    }
    var confirm = window.confirm("이 게시물을 신고하시겠습니까?");
    if(confirm != true)
        return;

    $(btn).ajaxSubmit({
        url : "ND_updateSttemnt.do",
        type : "POST",
        dataType : "json",
        data : {
            bbsSn : bbsSn,
            bbsDocNo : bbsDocNo
        },
        success : function(response) {
            if(response.result) {
                if(Number(response.value) > 0) {
                    $(btn).val("신고 " + response.value);
                    opSetCookieExpTime("sttemntCookie:" + bbsSn + ":" + bbsDocNo, incrsLmttTime);
                } else {
                    opWarningMsg('지금은 신고하실 수 없습니다.');
                }
            } else {
                opJsonMsg(response);
            }
        }
    });

};

/**
 * 다단 카테고리인 경우 현재 카테고리 키/값을 파라미터로 하여 하위 카테고리를 불러와 생성한다.
 */
var opLoadClCode = function(selectObj, targetId) {
    var value = $(selectObj).val();
    var param = {
        q_bbsSn : value,
        q_date : new Date().toDateString()
    };
    $.ajax({
        url : "selectClCode.do",
        type : "post",
        dataType : "json",
        data : param,
        success : function(response) {
            if(response.result) {
                $("#" + targetId).html("");
                var clList = response.value;
                var clListCnt = clList.length;
                $("#" + targetId).append("<option value=\"\">" + OpMessage.common.selectOption + "</option>");
                for(var i = 0 ; i < clListCnt ; i++) {
                    $("#" + targetId).append("<option value=\"" + clList[i].clsfNo + "\">" + clList[i].clsfNm + "</option>");
                }
                $("#clsfNo").select2("val", "");
                $("#lwrkClsfSn").html("");
                $("#lwrkClsfSn").select2("val", "");
            } else {
                opErrorMsg(response.message);
            }
        },
        error : function(response) {
            opSysErrorMsg(response.responseText);
            return;
        }
    });
};

/**
 * 다단 카테고리인 경우 현재 카테고리 키/값을 파라미터로 하여 하위 카테고리를 불러와 생성한다.
 */
var opLoadLwprtClCode = function(selectObj, bbsSn, targetId) {
    var value = $(selectObj).val();
    if(!bbsSn) {
        bbsSn = $("#bbsSn").val();
    }
    var param = {
        q_bbsSn : bbsSn,
        q_clsfNo : value,
        q_date : new Date().toDateString()
    };
    $.ajax({
        url : "selectLwprtClCode.do",
        type : "post",
        dataType : "json",
        data : param,
        success : function(response) {
            if(response.result) {
                $("#" + targetId).html("");
                var lwprtClList = response.value;
                var lwprtClListCnt = lwprtClList.length;
                $("#" + targetId).append("<option value=\"\">" + OpMessage.common.selectOption + "</option>");
                for(var i = 0 ; i < lwprtClListCnt ; i++) {
                    $("#" + targetId).append("<option value=\"" + lwprtClList[i].lwrkClsfSn + "\">" + lwprtClList[i].lwrkClsfNm + "</option>");
                }
                $("#" + targetId).select2("val", "");
            } else {
                opErrorMsg(response.message);
            }
        },
        error : function(response) {
            opSysErrorMsg(response.responseText);
            return;
        }
    });
};

/**
 * SUMMARY Toggle
 */
var opToggleSumry = function() {
    $(".bbsSummary").toggle();
};

// *********************** 쿠키관련 함수 ***************************//
// 쿠키에 저장된 값으로 앞으로 얼마나 유효한지 확인
var opCheckCookieTime = function(cookieName) {
    var cukie = $.cookie(cookieName);
    if(cukie == null)
        return 0; // 쿠키가 없다
    var nowTime = new Date();
    var expTime = new Date(eval(cukie));
    if((expTime.getTime() - nowTime.getTime()) > 0)
        return (Math.ceil((expTime.getTime() - nowTime.getTime()) / 1000 / 60 / 60));
    else
        return 0;
};

// 쿠키이름과 시간설정
var opSetCookieExpTime = function(cookieName, readCookieHour) {
    var expDate = new Date();
    expDate.setTime(expDate.getTime() + (60 * 60 * 1000) * readCookieHour);
    $.cookie(cookieName, expDate.getTime(), {
        path : '/',
        expires : expDate
    });
};

// *********************** 쿠키관련 함수 끝 ***************************//
