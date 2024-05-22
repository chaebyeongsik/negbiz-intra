$(document).ready(function() {

    /** 사이트담당자 설정 Autocomplete */
    $("#tmpPicNm").autocomplete({
        minLength: 1,
        delay: 800,
        source: function(request, response) {
            $.ajax({
                url: "ND_selectMngrList.do",
                type: "get",
                dataType: "json",
                data: {
                    q_picNm: request.term,
                    q_useYn: "Y",
                    q_maxRowNum: 20
                },
                success: function( data ) {
                    response(data);
                },
                error :  function(response) {
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        },
        focus : function(event, ui) {
            return false;
        },
        close : function(event, ui) {
            $("#tmpPicNm").val("");
            $("#toggleButton").show();
        },
        select: function(event, ui) {
            $("#picId").val(ui.item.picId);
            $("#tkcgDeptCdId").val(ui.item.deptCdId);

            $("#chrgDeptNm").html(ui.item.deptNm + "&nbsp;&nbsp;/&nbsp;&nbsp;" + ui.item.picNm);
        }
    });
});

/*
 * 담당자 정보 삭제(히든 폼이 존재하므로 버튼 삭제)
 */
var deleteCharger = function() {
    $("#tmpPicNm").val("");
    $("#picId").val("");
    $("#tkcgDeptCdId").val("");
    $("#chrgDeptNm").text("");

    $("#toggleButton").hide();
};

/**
 * 도메인 등록 폼
 */
var opInsertForm = function(_url, _param) {

    $("#DOMN_VIEW").load(_url, _param, function(result) {
    });
};

/**
 * 도메인 수정 폼
 */
var opUpdateForm = function(_url, _param) {

    $("#DOMN_VIEW").load(_url, _param, function(result) {
    });
};

/**
 * 도메인 IP 그룹 폼 추가 
 */
var addDomainGroup = function() {
    $("#domainGroupDiv").after($("#domainGroupDummy").html());
};

/**
 * 도메인 IP 그룹 폼 삭제
 */
var delDomainGroup = function(obj) {
    $(obj).parent().parent().remove();
};

/**
 * 상세화면 초기화
 */
var initView = function() {
    $("#DOMN_VIEW").html($("#dummy").html());
};

