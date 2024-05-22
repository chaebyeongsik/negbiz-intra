$(document).ready(function() {
    /**
     * 지정권한담당자목록 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chkall]").click(function() {
        var isChecked = $(this).prop("checked");
        $("input[name=bbsStngSns]").each(function() {
            $(this).prop("checked", isChecked);

            var tr = $(this).parent().parent();
            if(isChecked) {
                tr.addClass("info");
            } else {
                tr.removeClass("info");
            }
        });
    });
});

/**
 * 선택 삭제
 */
var opCheckDelete = function() {
    var bbsStngSns = $("input[name=bbsStngSns]:checked").length;
    if(bbsStngSns < 1) {
        opWarningMsg("게시판환경설정을 선택해 주세요.");
        return;
    }

    if(confirm("게시판환경설정을 정말 삭제하시겠습니까?")) {
        $("#listForm").submit();
    }
};