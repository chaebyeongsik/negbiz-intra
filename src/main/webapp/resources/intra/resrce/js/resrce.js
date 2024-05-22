/**
 * ajax load 방식으로 표시하므로 공통 부분을 따로 메소드화 함
 */
var opAjaxLoadSubmit = function(form, uri, page) {
    if(!uri) {
        uri = $("#" + form).attr("action");
    }
    // 페이징 초기화
    if(page) {
        $("#" + form + " #q_currPage").val(page);
    }

    var params = {};
    var url = uri + opGetMarkChar(uri) + opSearchQueryString(form);

    $("#" + options.CONT_PAN_ID).load(url, params, function(responseTxt, statusTxt, response) {
        if(statusTxt != "success") {
            opSysErrorMsg(responseTxt);
        }
    });
};

/**
 * 검색초기화
 */
var opSearchReset = function(form) {
    if(!form) {
        form = OpConfig.defaultForm;
    }

    $("#" + form + " [name^='" + OpConfig.prefixSearchParam + "']").each(function() {
        if($(this).attr("type") != "hidden" && $(this).attr("name") != "q_rowPerPage") {
            $(this).val("");
        }
    });

    opAjaxLoadSubmit(form, null, 1);
};

/**
 * 페이지 이동
 */
var opMovePage = function(cpage, form) {
    if(!form) {
        form = OpConfig.defaultForm;
    }
    opAjaxLoadSubmit(form, null, cpage);
};

/**
 * 목록
 */
var opList = function(uri, form) {
    if(!form) {
        form = OpConfig.defaultForm;
    }
    opAjaxLoadSubmit(form, uri);
};

/**
 * 상세
 */
var opView = function(uri, form) {
    if(!form) {
        form = OpConfig.defaultForm;
    }
    opAjaxLoadSubmit(form, uri, cpage);
};

/**
 * 등록 폼
 */
var opInsertForm = function(uri, form) {
    if(!form) {
        form = OpConfig.defaultForm;
    }
    if(!uri) {
        uri = $("#" + form).attr("action");
    }

    var url = uri + opGetMarkChar(uri) + opSearchQueryString(form);
    var options = {
        width : 900,
        href : url
    };

    $.fn.opmodal(options);
};

/**
 * 수정 폼
 */
var opUpdateForm = function(uri, form) {
    if(!form)
        form = OpConfig.defaultForm;
    if(!uri)
        uri = $("#" + form).attr("action");

    location.href = uri + opGetMarkChar(uri) + opSearchQueryString(form);
};

/**
 * 자원 삭제
 */
var opDelete = function(url) {
    if(confirm("자원을 사용중일 수 있습니다.\n\r정말 삭제하시겠습니까? ")) {
        $.ajax({
            url : url,
            type : "POST",
            data : $("#dataForm").serialize(),
            dataType : "json",
            success : function(response) {
                parent.opJsonMsg(response);
                if(response.result) {
                    parent.opList();
                    opCloseWin();
                }
            },
            error : function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    }
};

/**
 * 자원 파일명/순번/설명 수정 폼
 */
var opChangeResrceFileInfo = function(dataSn, chgCycl) {
    var url = CTX_PATH + "/intra/resrce/file/PD_updateResrceFileInfoForm.do?dataSn=" + dataSn + "&chgCycl=" + chgCycl;
    var option = {
        href : url,
        width : 807,
        height : 500
    };

    $.fn.opmodal(option);
};

/**
 * 이미지 파일 보기(썸네일 포함)
 */
var opResrceFileThumbs = function(dataSn, chgCycl, fileSn) {
    var url = CTX_PATH + "/intra/resrce/file/PD_selectResrceFile.do?dataSn=" + dataSn + "&chgCycl=" + chgCycl + "&fileSn=" + fileSn;
    var option = {
        href : url,
        width : 807,
        height : 500
    };

    $.fn.opmodal(option);
};

// 첨부파일 추가
var addResrceFile = function() {
    $("#attFileLocationDiv").append($("#addResrceFileDummy").html());
    
};

// 컨텐츠 빌더 팝업으로 부터 저장할때 html을 건내받기 위한 함수(필수)
var opReceiveContentBuilder = function(contents) {
    $("#dataForm #contsCn").val(contents);
};
