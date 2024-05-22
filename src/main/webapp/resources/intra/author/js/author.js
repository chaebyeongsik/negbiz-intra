/**
 * 권한 수정폼 로드
 */
var opUpdateAuthorForm = function(authrtCdId){
    $("#Author_VIEW").load("INC_updateAuthorForm.do", {authrtCdId : authrtCdId}, function(result) {
    });
};

/**
 * 권한 등록폼 로드
 */
var opInsertAuthorForm = function(){
    $("#Author_VIEW").load("INC_insertAuthorForm.do", function(result) {
    });
};

/**
 * 해당 권한이 할당된 담당자 목록 조회
 */
var opChargerInAuthor = function(obj, authrtCdId){
    $(obj).opmodal({
        width : 600,
        height : 500,
        href : "PD_selectMngrInAuthorList.do?authrtCdId=" + authrtCdId
    });
};

/**
 * 메뉴할당폼
 */
var opMenuAsgnForm = function(authrtCdId, authrtNm){
    opLoadStart();
    $("#Author_VIEW").load("INC_selectMenuAsgnForm.do", {authrtCdId : authrtCdId}, function(result) {
        opLoadEnd();
        if (authrtNm) $("#authrtNm").text(authrtNm);
    });
};

/**
 * 메뉴권한할당 저장
 */
var opMenuAsgnSave = function(authrtCdId){
    $("#dataForm").ajaxSubmit({
        url : "ND_insertMenuAuthorAsgn.do",
        type : "POST",
        dataType : "json",
        async : false,
        success : function(response) {
            if (response) {
                opJsonMsg(response);
            }
        }
    });
};

/**
 * 등록처리
 */
var opInsertAction = function() {
    if(!validate()){
        return;
    }
    // 그룹권한 신규 신청 시 기본값 설정
    if ($('input[name=authrtSeCdId]').val() == '') {
        $('input[name=authrtSeCdId]').val('G');
    }
    $("#dataForm").ajaxSubmit({
        url : "ND_insertAuthor.do",
        type : "POST",
        dataType : "json",
        async : false,
        success : function(response) {
            if (response) {
                opJsonMsg(response);
                if (response.result) {
                    location.href = "/intra/author/BD_selectAuthorList.do";
                }
            }
        }
    });
};

/**
 * 수정처리
 */
var opUpdateAction = function() {
    if(!validate()){
        return;
    }
    $("#dataForm").ajaxSubmit({
        url : "ND_updateAuthor.do",
        type : "POST",
        dataType : "json",
        async : false,
        success : function(response) {
            if (response) {
                opJsonMsg(response);
                if(response.result){
                    location.href = "BD_selectAuthorList.do?authrtCdId=" + $("#authrtCdId").val();
                }
            }
        }
    });
};

var customValidate = function() {
    if ($("#dupCheckVal").val() != 'Y') {
        // 수정화면, 권한코드를 수정한 상태
        if(($("#originalAuthrtCdId").val() != '' && $("#originalAuthrtCdId").val() != $("#authrtCdId").val())
                || $("#originalAuthrtCdId").val() == ''){
            opWarningMsg("입력하신 권한코드가 이미 존재합니다. 권한코드를 다시 입력하여 주시기바랍니다.");
            return false;
        }
    }
    return true;
};

/**
 * 삭제처리
 */
var opDeleteAction = function(){
    if(!confirm("정말 삭제하시겠습니까?")) return;
    $("#dataForm").ajaxSubmit({
        url : "ND_deleteAuthor.do",
        type : "POST",
        dataType : "json",
        async : false,
        success : function(response) {
            if(response.result){
                opJsonMsg(response);
                if(response.result){
                    location.href = "BD_selectAuthorList.do";
                }
            }else{
                opErrorMsg(response.message);
            }
        }
    });
};

/**
 * 권한코드 중복체크
 */
var opDupCheckAuthrtCdId = function(authrtCdId){
    if($("#authrtCdId").val() != ''){
        $.ajax({
            url : "ND_selectDupCheckAuthrtCdId.do",
            type : "POST",
            dataType : "json",
            data : {
                authrtCdId : $("#authrtCdId").val(),
                originalAuthrtCdId : $("#originalAuthrtCdId").val(),
                q_date : new Date().toString()
            },
            success : function(data) {
                if (data == 0) {
                    $("#dupCheckMessage").html("<p class=\"text-info form-control-static\">사용 가능한 코드입니다.</p>");
                    $("#dupCheckVal").val("Y");
                } else {
                    $("#dupCheckMessage").html("<p class=\"text-danger form-control-static\">입력하신 권한코드가 이미 존재합니다.</p>");
                    $("#dupCheckVal").val("N");
                }
            }
        });
    }else{
        $("#dupCheckMessage").html("");
        $("#dupCheckVal").val("");
    }
};