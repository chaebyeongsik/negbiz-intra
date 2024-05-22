/**
 * 상세화면 초기화
 */
var initView = function(id) {
    $("#" + id).html($("#dummy").html());
};

/** 트리 클릭 후 상위 코드 경로 표시 */
var afterTreeView = function(key, parentKey, response) {
    if(key == OpConfig.defaultCode.highCommonCd) {
        key = "최상위코드";
    }
    $("#nodePath").html(Optree.getNodePath() + "&nbsp;&nbsp;&nbsp;<code>" + key + "</code>");
};

/**
 * 등록된 코드 삭제 단! 최하위 노드만 삭제가 가능하다
 */
var opCodeDelete = function(url) {
    if (confirm("정말 삭제하시겠습니까?\n사용중인 코드가 삭제될 경우 시스템이 오작동 할 수 있습니다.")) {
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

/**
 * 등록된 코드 삭제 단! 최하위 노드만 삭제가 가능하다
 */
var opCodeChoiceDelete = function(url) {
    if (confirm("정말 삭제하시겠습니까?\n사용중인 코드가 삭제될 경우 시스템이 오작동 할 수 있습니다.")) {
        $.ajax({
            url : url,
            type : "POST",
            data : $("#dataForm").serialize(),
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    location.reload(false);
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

/**
 * 코드선택자 등록 폼
 */
var opInsertForm = function(_url, _param) {

    $("#CdChcId_VIEW").load(_url, _param, function(result) {
    });
};

/**
 * 코드선택자 수정 폼
 */
var opUpdateForm = function(_url, _param) {S

    $("#CdChcId_VIEW").load(_url, _param, function(result) {
    });
};