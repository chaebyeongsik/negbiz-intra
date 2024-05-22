/*
 * 컨텐츠 수정에 관련된 스크립트 모음
 */

/**
 * 컨텐츠 수정
 */
var processCmsCntnts = function(aprvSttsNo) {
    // 검증 후 수정
    if(validate()) {
        $("#aprvSttsNo").val(aprvSttsNo);
        $("#dataForm").ajaxSubmit({
            url : UPDATE_URL,
            type : "POST",
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                location.reload();
            },
            error : function(response) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
    }
};

/**
 * 컨텐츠 수정
 */
var updateCmsCntnts = function() {

    processCmsCntnts($("#aprvSttsNo").val());
};

/**
 * 컨텐츠 자동 배포
 */
var deployCmsCntnts = function() {

    processCmsCntnts("C1050");
};

/**
 * 컨텐츠 배포 요청
 */
var deployRequestCmsCntnts = function() {

    processCmsCntnts("C1020");
};

/**
 * 컨텐츠 배포 요청 취소
 */
var deployCancelCmsCntnts = function() {

    processCmsCntnts("C1010");
};

/**
 * 작성중인 컨텐츠 삭제
 */
var deleteCmsCntnts = function() {
    if(confirm("정말삭제하시겠습니까? 삭제시 복구가 불가능합니다.")) {
        $("#dataForm").ajaxSubmit({
            url : DELETE_URL,
            type : "POST",
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                self.close();
            },
            error : function(response) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
    }
};

/* 컨텐츠빌더 화면을 새창으로 띄움 */
var opContentbuilderOpen = function() {
    var mainContsCn = $("#dataForm #mainContsCn").val();
    var strtContsCn = $("#dataForm #strtContsCn").val();

    $("#contentbuilderForm #q_content").val(mainContsCn);
    $("#contentbuilderForm #q_header").val(strtContsCn);

    $("#contentbuilderForm").submit();
};

/* 컨텐츠 빌더 팝업으로 부터 저장할때 html을 건내받기 위한 함수(필수) */
var opReceiveContentBuilder = function(contents) {
    $("#dataForm #mainContsCn").val(contents);
};

/* 연결된자원관리 상세화면을 새창으로 띄움 */
var opResrceDetlOpen = function(dataSn, chgCycl) {
    if(dataSn && chgCycl) {
        var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
        option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
        var param = "q_resrceSn=" + dataSn + "&q_resrceVer=" + chgCycl;
        var href = CTX_PATH + "/intra/resrce/othbc/PD_selectResrceOthbc.do?" + param;
        var opResrceDetlOpenView = window.open(href, "opResrceDetlOpen", option);
        opResrceDetlOpenView.focus();
    } else {
        opWarningMsg("자원관리에서 가져오기 사용 후 확인할 수 있습니다.");
    }
    return false;
};

/* 자원관리 화면을 새창으로 띄움 */
var opResrceOpen = function() {
    var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
    option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
    var href = CTX_PATH + "/intra/resrce/othbc/PD_selectResrceOthbcList.do";
    var opResrceOpenView = window.open(href, "opResrceOpen", option);
    opResrceOpenView.focus();

    return false;
};

// 자원관리에서 컨텐츠를 넘겨 받을때 사용되는 메소드
var opReceiveResrce = function(dataSn, chgCycl, contsCn, cssFiles, jsFiles) {
    if(contsCn) {
        $("#mainContsCn").val(contsCn);
    }
    var cssFileLink = "";
    if(cssFiles && cssFiles.length > 0) {
        for(var i = 0 ; i < cssFiles.length ; i++) {
            cssFileLink += makeCssFileLink(cssFiles[i]) + "\r";
        }
    }
    var jsFileLink = "";
    if(jsFiles && jsFiles.length > 0) {
        for(var i = 0 ; i < jsFiles.length ; i++) {
            jsFileLink += makeJsFileLink(jsFiles[i]) + "\r";
        }
    }
    var strtContsCn = "";
    strtContsCn += cssFileLink;
    strtContsCn += jsFileLink;
    $("#strtContsCn").val(strtContsCn);

    $("#dataSn").val(dataSn);
    $("#chgCycl").val(chgCycl);
};

// CSS 파일 링크 생성
var makeCssFileLink = function(cssFilePath) {
    var cssLink = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + cssFilePath + "\" />";
    return cssLink;
};

// JS 파일 링크 생성
var makeJsFileLink = function(jsFilePath) {
    var jsLink = "<script type=\"text/javascript\" src=\"" + jsFilePath + "\"><\/script>";
    return jsLink;
};
