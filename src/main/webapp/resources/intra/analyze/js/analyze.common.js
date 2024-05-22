
/**
 * 상세화면 초기화
 */
var initView = function() {
    $("#ANALYZE_DETAIL_VIEW").html($("#dummy").html());
};

/**
 * Ajax 페이지 조회
 */
var opAnalyzeDetailView = function(pageUrl, params) {
    var date = new Date().toString();
    if(!params) {
        params = $("#dataForm").serialize();
    }
    params += "&date="+date;
    $("#ANALYZE_DETAIL_VIEW").load(pageUrl, params, function(responseTxt, statusTxt, response) {
        if(statusTxt == "success") {

        } else {
            opSysErrorMsg(responseTxt);
        }
    });
};

//  검색 폼에 DatePicker 적용
var setDatePicker = function() {
    $("#q_startDt").datepicker({
        dateFormat : "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel : true,
        currentText : "오늘",
        closeText : "닫기",
        onClose : function(selectedDate) {
            $(".to-date").datepicker("option", "minDate", selectedDate);
        },
        beforeShow : function(input, inst) {
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $("#q_endDt").datepicker({
        dateFormat : "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel : true,
        currentText : "오늘",
        closeText : "닫기",
        onClose : function(selectedDate) {
            $(".from-date").datepicker("option", "maxDate", selectedDate);
        },
        beforeShow : function(input, inst) {
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });
};