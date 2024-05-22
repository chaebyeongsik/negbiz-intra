/* ========================================================
 *
 * 공통 초기화 스크립트
 *
 * ======================================================== */

/**
 * 개발모드와 운영모드 설정에 따라서 Javascript도 같이 작동이 가능하도록 productionMode를 사용한다.
 */
var productionMode = false;
try {
    productionMode = OpConfig.productionMode;
} catch (e) {}

$(document).ready(function() {

    /*
     * 상호 작용 스크립트
     * ==================================================
     */

    // 메뉴 패널 토글
    $('.sidebar-toggle').click(function() {
        $('.page-container').toggleClass('sidebar-hidden');
    });

    /**
     * 검색버튼 클릭시 자동으로 페이지 번호를 1로 갱신
     */
    $("button[type='submit']").click(function() {
        var text = $(this).text();
        if($.trim(text) == "검색") {
            var currPage = $("#q_currPage");
            if(currPage.length > 0) {
                currPage.val("1");
            }
        }
    });
    $("input[type='submit']").click(function() {
        var text = $(this).text();
        if($.trim(text) == "검색") {
            var currPage = $("#q_currPage");
            if(currPage.length > 0) {
                currPage.val("1");
            }
        }
    });

    /**
     * 페이지 목록 갯수 변경시 자동으로 페이지 번호를 1로 갱신
     */
    if($("#q_rowPerPage").length > 0) {
        $("#q_rowPerPage").next().click(function(){
           $("#q_currPage").val(1);
        });
    }

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

    /**
     * 팝업창 닫기(modal 창 포함)
     */
    $("#opCloseWin").click(function() {
        opCloseWin();
    });

    //=====  jQuery UI  =====//
    /**
     * Datepicker 자동 설정
     */
    $(".datepicker").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".to-date").datepicker("option", "minDate", selectedDate);
        },
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $(".datepicker-inline").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $(".datepicker-multiple").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths : true,
        numberOfMonths : 2,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".from-date").datepicker("option", "maxDate", selectedDate);
        },
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $(".datepicker-trigger").datepicker({
        dateFormat: "yy-mm-dd",
        showOn : "button",
        buttonImage : "/resources/libs/londinium/images/interface/datepicker_trigger.png",
        buttonImageOnly : true,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $(".from-date").datepicker({
        dateFormat: "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".to-date").datepicker("option", "minDate", selectedDate);
        },
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $(".to-date").datepicker({
        dateFormat: "yy-mm-dd",
        numberOfMonths : 2,
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        onClose : function(selectedDate) {
            $(".from-date").datepicker("option", "maxDate", selectedDate);
        },
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    $(".datepicker-restricted").datepicker({
        dateFormat: "yy-mm-dd",
        minDate : -20,
        maxDate : "+1M +10D",
        showOtherMonths : true,
        showButtonPanel: true,
        currentText: "오늘",
        closeText: "닫기",
        beforeShow: function(input, inst){
            $(input).css({
                "position" : "relative",
                "z-index" : 999
            });
        }
    });

    /*
     * 디자인 관련
     * ==================================================
     */

    try{
        // Bootstrap dropdown 생성 및 동작
        $('.dropdown, .btn-group').on('show.bs.dropdown', function(e) {
            $(this).find('.dropdown-menu').first().stop(true, true).fadeIn(100);
        });
        // Bootstrap dropdown 애니메이션 동작
        $('.dropdown, .btn-group').on('hide.bs.dropdown', function(e) {
            $(this).find('.dropdown-menu').first().stop(true, true).fadeOut(100);
        });
        // Bootstrap dropdown 닫기시 이벤트 삭제
        $('.popup').click(function(e) {
            e.stopPropagation();
        });

        // radio, checkbox 디자인 생성 스크립트
        $(".styled, .multiselect-container input").uniform({
            radioClass : 'choice',
            selectAutoWidth : false
        });

        // 일반 selectbox
        $(".select").select2({
            minimumResultsForSearch: "-1"
        });

        // 필터링 selectbox
        $(".select-search").select2({
        });
    } catch(e) { }

});