/**
 * 상세화면 초기화
 */
var initView = function(id) {
    $("#" + id).html($("#dummy").html());
};

/**
 * 등록된 코드 삭제 단! 최하위 노드만 삭제가 가능하다
 */
var opMultiLangDelete = function(url) {
    if (confirm("정말 삭제하시겠습니까?\n사용중인 다국어가 삭제될 경우 올바른 메시지가 출력되지 않습니다.")) {
        $.ajax({
            url : url,
            type : "POST",
            data : $("#dataForm").serialize(),
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    Optree.removeNode(response.value);
                    initView(contPadId);
                } else {
                    return;
                }
            },
            error :  function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    }
};
