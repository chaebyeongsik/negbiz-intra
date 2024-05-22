/*
 * CMS 관련 공통 스크립트 모음
 */

// 컨텐츠 편집 URI
var cntntsEditUrl = CTX_PATH + "/intra/cms/cntnts/PD_updateCmsCntntsForm.do?";

/**
 * 컨텐츠 편집창 팝업
 */
var cntntsEdit = function(siteSn, userMenuEngNm, contsSn) {
    var option = "chrome, centerscreen, dependent=yes, width=1024, height=650, dialog=yes, modal=yes, ";
    option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";

    var href = cntntsEditUrl + "q_siteSn=" + siteSn + "&q_userMenuEngNm=" + userMenuEngNm + "&q_contsSn=" + contsSn;
    var cntntsEditWin = window.open(href, "cntntsEditWin", option);

    cntntsEditWin.focus();
};

/**
 * 새윈도우를 옵션 없이 생성
 */
var opNewWin = function(elemId, url) {
    var windowName = new Date().toDateString();
    var href = url;
    if(!href) {
        href = $("#" + elemId).val();
    }
    var option = "chrome, centerscreen, dependent=yes, location=yes, status=yes, dialog=yes, modal=yes, resizable=yes, scrollbars=yes";
    window.open(href, windowName, option);
};
