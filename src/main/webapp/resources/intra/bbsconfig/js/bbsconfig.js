$(document).ready(function() {

    /**
     * 지정권한담당자목록 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chkall]").click(function() {
        var isChecked = $(this).prop("checked");
        var chkDiv = $(this).parent().parent().parent().parent();
        $("input[name=bbsSns]").prop("checked", isChecked);

        if(isChecked) {
            chkDiv.find("tbody > tr").addClass("info");
        } else {
            chkDiv.find("tbody > tr").removeClass("info");
        }
    });

    /**
     * 테이블 row별 구분 컬러 추가
     */
    $("table.config-list > tbody > tr").hover(function() {
        // 체크박스 선택되었을때
        if($(this).attr("class").indexOf('info') > -1) {
            $(this).addClass("info-hover");
            $(this).removeClass("info");
            if($(this).index() % 2 == 0) { // index가 짝수일때 다음row로 같이 처리
                $(this).next().addClass("info-hover");
                $(this).next().removeClass("info");
            } else { // index가 짝수아닐때 이전row로 같이 처리
                $(this).prev().addClass("info-hover");
                $(this).prev().removeClass("info");
            }
        } else {
            $(this).addClass("bg-hover");
            if($(this).index() % 2 == 0)
                $(this).next().addClass("bg-hover");
            else
                $(this).prev().addClass("bg-hover");
        }
    }, function() {
        // 체크박스 선택되었을때
        if($(this).attr("class").indexOf('info-hover') > -1) {
            $(this).removeClass("info-hover");
            $(this).addClass("info");
            if($(this).index() % 2 == 0) { // index가 짝수일때 다음row로 같이 처리
                $(this).next().removeClass("info-hover");
                $(this).next().addClass("info");
            } else { // index가 짝수아닐때 이전row로 같이 처리
                $(this).prev().removeClass("info-hover");
                $(this).prev().addClass("info");
            }
        } else {
            $(this).removeClass("bg-hover");
            if($(this).index() % 2 == 0)
                $(this).next().removeClass("bg-hover");
            else
                $(this).prev().removeClass("bg-hover");
        }
    });
});

/**
 * 선택 삭제
 */
var opCheckDelete = function() {
    var bbsSns = $("input[name=bbsSns]:checked").length;
    if(bbsSns < 1) {
        opWarningMsg("게시판을 선택해 주세요.");
        return;
    }

    if(confirm("게시판을 정말 삭제하시겠습니까?")) {
        $("#listForm").submit();
    }
};

/**
 * 사용/미사용 전환
 */
var opUpdateUseYn = function(el, bbsSn, seCdId) {

    var elementz = $(el).children();
    var useAtVal = elementz.find("span").html() == "사용" ? "N" : "Y";

    var isProcessing = true;
    if(elementz.attr("id") == "useYn" && useAtVal == "N") {
        if(!confirm("사용여부를 미사용으로 설정 시에는 본 게시판이 서비스되지 않습니다.\n\n계속 진행하시겠습니까?")) {
            isProcessing = false;
        }
    }

    if(isProcessing) {
        $.ajax({
            url : "/intra/bbsconfig/ND_updateUseYn.do",
            type : "POST",
            data : {
                bbsSn : bbsSn,
                seCdId : seCdId,
                colId : elementz.attr("id"),
                useYn : useAtVal
            },
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if(response.result) {
                    elementz.find("i").removeClass();

                    if(useAtVal == "Y") {
                        elementz.find("i").addClass("icon-checkmark");
                        elementz.find("span").text("사용");
                    } else {
                        elementz.find("i").addClass("icon-close");
                        elementz.find("span").text("미사용");
                    }
                }
            },
            error : function(response) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
    }

};

/**
 * 분류코드 그룹 폼 추가
 */
var addClCode = function() {
    $("#clGroupDiv").append($("#clUseDummy").html());

    // 분류명 Tags-input
    var lastIndex = $("#clGroupDiv input[name='lwrkClsfNms']").length;
    $("#clGroupDiv input[name='lwrkClsfNms']").each(function(index) {
        if(index == (lastIndex -1)) {
        $(this).tagsInput({
            width : '100%',
            defaultText : '하위분류'
        });
        }
    });
};

/**
 * 분류코드 그룹 폼 삭제
 */
var delClCode = function(obj) {
    $(obj).parent().parent().parent().remove();
};
