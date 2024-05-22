
/**
 * 등록된  카테고리 삭제 단! 최하위 노드만 삭제가 가능하다
 */
var opDelete = function(url) {
    if (confirm("정말 삭제하시겠습니까?\n사용중인 카테고리는 삭제되지 않습니다.")) {
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
                    if(response.value < 0) {
                        opWarningMsg("사용중인 카테고리는 삭제할 수 없습니다.");
                    } else {
                        opErrorMsg(response.message);
                    }
                }
            },
            error :  function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    }
};
