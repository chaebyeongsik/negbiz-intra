var opExcelDownForm = function(excelKey){
    if(logUseYn == 'Y'){
        var urlParam = $("#dataForm").serialize();
        $().opmodal({
            width : 500,
            height : 600,
            href : "/intra/user/hist/PD_insertUserLogOutputForm.do?excelKey="+excelKey+"&"+urlParam
        });
    }else{
        $("#logForm").attr("action", "/intra/user/hist/ND_insertUserOutputLog.do?menuSeCd=O&excelKey="+excelKey);
        $("#logForm").attr("method", "post");
        $("#logForm").submit();
    }
};

/**
 * 사용자 정보 수정 시 비밀번호 입력받음
 * 담당자가 본인이 로그인했던 비밀번호를 입력하여야만 저장프로세스를 탈 수 있게한다.
 * 추후 수정 사유를 저장..
 */
var opCheckPicPswd = function(){
    if($("#picPswd").val() == ''){
        opWarningMsg("비밀번호를 입력하여 주세요.");
        $("#picPswd").focus();
        return false;
    }
    if($("#logCn").val() == ''){
        opWarningMsg("사유를 입력하여 주세요.");
        $("#logCn").focus();
        return false;
    }
    var returnVal = false;
    $.ajax({
        url : "/intra/user/hist/ND_selectCheckChargerPasswordMatch.do",
        type : "POST",
        dataType : "json",
        async : false,
        data : {
            picPswd : $("#picPswd").val()
        },
        success : function(response) {
            if (response.result) {
                returnVal = true;
            } else {
                returnVal = false;
                opWarningMsg("비밀번호가 맞지않습니다.");
                $("#picPswd").focus();
            }
        },
        error :  function(response) {
            opSysErrorMsg(response.responseText);
            return false;
        }
    });
    return returnVal;
};

var opSearchLogForm = function(){
    $("#q_currPage").val(1);
    var conts = "";
    var searchCndExists = false;
    $("#dataForm #search [name^=q_]").each(function(i) {
        var name = $(this).attr("name");
        var skip = false;
        if((name == 'q_searchKey' || name == 'q_searchVal') && $("#q_searchVal").val() == ''){
            skip = true;
        }
        if(!skip) {
            var labelCon = $("label[for="+name+"]").text();
            var value = $.trim($(this).val());
            if($(this).is("select")){
                if(value != '') {
                    value = $.trim($(this).find("option:selected").text());
                    searchCndExists = true;
                }
            }
            if(value != ''){
                conts += ",[" + labelCon + ">" + value + "]";
            }
        }
    });
    if(logType == 'searchLog' && logUseYn == 'Y'){
        if(!searchCndExists){
            opWarningMsg("검색조건을 한개 이상 선택(입력)하여 주십시오.");
            return;
        }
    }
    conts = conts.substr(1);

    if(logType == 'searchLog' && logUseYn == 'Y'){
        var urlParam = $("#dataForm").serialize();
        $().opmodal({
            width : 700,
            height : 600,
            href : "/intra/user/hist/PD_insertUserLogForm.do?"+urlParam+"&srchStngCn="+conts
        });
    } else {
        $("#dataForm").ajaxSubmit({
            url : "/intra/user/hist/ND_insertUserSearchLog.do",
            type : "POST",
            dataType : "json",
            async : false,
            data : {
                srchStngCn : conts,
                menuSeCd : "R"
            },
            success : function(response) {
                if(response.result){
                    opListSubmit();
                }
            },
            error : function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    }
};

// 사유 상세설명 레이어
var reasonLayerYn ="N";
var reasonLayer = function(idNum){
    if(idNum == $("#idNum").val() && $("#reasonLayerYn").val() == "Y"){
        $("#mo_why_"+idNum).hide();
        $("#reasonLayerYn").val("N");
    }else{
        $("#mo_why_"+$("#idNum").val()).hide();
        $("#mo_why_"+idNum).show();
        $("#idNum").val(idNum);
        $("#reasonLayerYn").val("Y");
    }
};

var opCheckDate = function(){
    if ( $("#q_searchKey1").val() != "" ) {
        if ( $("#q_searchVal").val() == "" ) {
//            opWarningMsg("검색어를 입력하세요.");
//            $("#q_searchVal").focus();
//            return false;
        }
    }else{
        if ( $("#q_searchVal").val() != "") {
            opWarningMsg("검색조건을 선택하세요.");
            $("#q_searchKey").focus();
            return false;
        }
    }

    if ($('#q_startDt').val() > $('#q_endDt').val()) {
        opWarningMsg('시작일이 종료일보다 늦습니다.');
        return false;
    }

    return true;
};

/**
 * 페이지 이동
 *
 * @param cpage 이동할 페이지 번호
 * @returns
 */
var opMovePage = function(cpage, form) {
    if(!form) form = OpConfig.defaultForm;

    $("#q_currPage").val(cpage);
    location.href = "?" + opSearchQueryString(form) + "&menuSeCd="+$("#menuSeCd").val();
};

var opSearchReset = function(){
    // 초기화 시 검색&로그사용이면 초기화 시 리스트 호출..
    if(logType == 'searchLog' && logUseYn == 'Y'){
        location.href = requestURI;
    }else{
        opSearchResetForm(OpConfig.defaultForm);
    }

};