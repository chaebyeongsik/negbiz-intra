/*응답관리 내 삭제*/
$(document).ready(function() {
    /**
     * 지정권한담당자목록 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chkall]").click(function() {
        var isChecked = $(this).prop("checked");
        $("input[name=rspnsHeadNos]").each(function() {
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
 * 응답관리 선택 삭제
 */
var opCheckDelete = function() {
    var rspnsHeadNos = $("input[name=rspnsHeadNos]:checked").length;
    if(rspnsHeadNos < 1) {
        opWarningMsg("응답자를 선택해 주세요.");
        return;
    }

    if(confirm("응답자를 정말 삭제하시겠습니까?")) {
        $("#listForm").submit();
    }
};