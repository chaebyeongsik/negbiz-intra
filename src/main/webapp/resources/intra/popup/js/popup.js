
/* 윈도우 크기/위치 자동설정 창 팝업 */
var opSelectSizeAndPosition = function() {

    var wdthSz = $("#wdthSz").val();
    var vrtcSz = $("#vrtcSz").val();
    var yAxis = $("#yAxis").val();
    var xAxis = $("#xAxis").val();

    if(wdthSz == '') {
        wdthSz = 200;
    }
    if(vrtcSz == '') {
        vrtcSz = 200;
    }
    if(yAxis == '') {
        yAxis = 20;
    }
    if(xAxis == '') {
        Xaxis = 20;
    }

    var option = "chrome, centerscreen, dependent=yes, left=" + xAxis + ", top=" + yAxis + ",width=" + wdthSz + ", height=" + vrtcSz + ", ";
    option += "dialog=yes, modal=yes, resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";

    var href = "ND_selectSizeAndPosition.do";
    var opSelectSizeAndPositionPreview = window.open(href, "opSelectSizeAndPosition", option);

    opSelectSizeAndPositionPreview.focus();
};

/* 팝업 미리보기 */
var opPopupPreview = function(regSn) {
    var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
    option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
    var href = "ND_selectPopup.do?q_regSn=" + regSn;
    var popupPreview = window.open(href, "opPopupPreview", option);
    popupPreview.focus();

    return false;
};

/* 작성방법에 따른 화면 변경 */
// ckeditor instance
var editor = null;
var opChangeWritngMth = function(el) {
    var wrtTypeNm = $(el).val();
    if(wrtTypeNm == "2010") {
        if(editor) {
            editor.destroy();
            editor = null;
        }
        $("#contentbuilderBlock").hide();
    } else if(wrtTypeNm == "2020") {
        editor = $("#docContsCn").ckeditor({
            toolbar : toolbar_config.default_toolbar
        }).editor;
        $("#contentbuilderBlock").hide();
    } else if(wrtTypeNm == "2030") {
        if(editor) {
            editor.destroy();
            editor = null;
        }
        $("#contentbuilderBlock").show();
    }
};

/* 컨텐츠빌더 화면을 새창으로 띄움 */
var opContentbuilderOpen = function() {
    var docContsCn = $("#dataForm #docContsCn").val();
    $("#contentbuilderForm #q_content").val(docContsCn);

    $("#contentbuilderForm").submit();
};

// 컨텐츠 빌더 팝업으로 부터 저장할때 html을 건내받기 위한 함수(필수)
var opReceiveContentBuilder = function(contents) {
    $("#dataForm #docContsCn").val(contents);
};