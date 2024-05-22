
/**
 * 페이지 이동
 *
 * @param cpage 이동할 페이지 번호
 * @returns
 */
var opMovePage = function(cpage, form) {
    if(!form) form = OpConfig.defaultForm;

    $("#"+OpConfig.prefixSearchParam+"currPage").val(cpage);

    var href = location.pathname + "?";
    location.href = href + opSearchQueryString(form);
};

/**
 * URL 이동
 * @param url 이동대상 주소
 * @param form 대상 폼
 * @returns
 */
var opMoveUrl = function(url, target, form) {
    if(!form) form = OpConfig.defaultForm;

    var link = url;
    if(url.indexOf("?") < 0) {
        link += "?";
    } else {
        link += "&";
    }

    link = opFilterQueryString(link);

    // 윈도우 target 설정
    if(target) {
        var openWin = window.open(url, new Date().getTime(),"");
        openWin.location.href = link;
    } else {
        location.href = link;
    }

};

/**
 * 목록
 * @param uri action="uri"
 * @param form 대상 폼
 * @returns
 */
var opList = function(uri, form) {
    if(!form) form = OpConfig.defaultForm;
    if(!uri) uri = $("#"+form).attr("action");

    location.href = uri + opGetMarkChar(uri) + opSearchQueryString(form);
};

/**
 * 상세
 * @param uri action="uri"
 * @param form 대상 폼
 * @returns
 */
var opView = function(uri, form) {
    if(!form) form = OpConfig.defaultForm;
    if(!uri) uri = $("#"+form).attr("action");

    location.href = uri + opGetMarkChar(uri) + opSearchQueryString(form);
};

/**
 * 등록 폼
 * @param uri action="uri"
 * @param form 대상 폼
 * @returns
 */
var opInsertForm = function(uri, form) {
    if(!form) form = OpConfig.defaultForm;
    if(!uri) uri = $("#"+form).attr("action");

    location.href = uri + opGetMarkChar(uri) + opSearchQueryString(form);
};

/**
 * 수정 폼
 * @param uri action="uri"
 * @param form 대상 폼
 * @returns
 */
var opUpdateForm = function(uri, form) {
    if(!form) form = OpConfig.defaultForm;
    if(!uri) uri = $("#"+form).attr("action");

    location.href = uri + opGetMarkChar(uri) + opSearchQueryString(form);
};

/**
 * 삭제
 * @param uri action="uri"
 * @param form 대상 폼
 * @returns
 */
var opDelete = function(uri, form) {
    if (confirm("정말 삭제하시겠습니까?")) {
        if(!form) form = OpConfig.defaultForm;
        if(!uri) uri = $("#"+form).attr("action");

        var method = $("#"+form).attr("method");
        var action = $("#"+form).attr("action");

        $("#"+form).attr("action", uri);
        $("#"+form).attr("method", "post");
        $("#"+form).submit();
        // 복원
        $("#"+form).attr("method", method);
        $("#"+form).attr("action", action);
    }
};

/**
 * 목록 삭제
 * @param uri action="uri"
 * @param form 대상 폼
 * @returns
 */
var opDeleteList = function(uri, form) {
    if (confirm("정말 삭제하시겠습니까?")) {
        if(!form) form = OpConfig.defaultForm;
        if(!uri) uri = $("#"+form).attr("action");

        var method = $("#"+form).attr("method");
        var action = $("#"+form).attr("action");

        $("#"+form).attr("action", uri);
        $("#"+form).attr("method", "post");
        $("#"+form).submit();
        // 복원
        $("#"+form).attr("method", method);
        $("#"+form).attr("action", action);
    }
};

/**
 * 폼에서 검색용 파라미터만 추출하여 QueryString로 생성 단 <textarea/>는 자동 제외됨
 * @param form 대상 폼
 * @returns
 */
var opSearchQueryString = function(form) {
    if(!form) form = OpConfig.defaultForm;

    return opQueryString(form, OpConfig.prefixSearchParam);
};

/**
 * 폼에서 전체 파라미터를 추출하여 QueryString로 생성 단 <textarea/>는 자동 제외됨
 * @param form 대상 폼
 * @param prefix 접두어
 * @returns
 */
var opQueryString = function(form, prefix) {
    if(!form) form = OpConfig.defaultForm;

    var queryString = "";
    var filter = opGetFilter(prefix, form);

    $("#"+ filter).each(function(idx) {
        queryString += opFilterParam($(this), queryString);
    });

    return queryString;
};

/**
 * 검색 초기화. 기본 폼 값인 dataForm 사용
 * @param form 대상 폼
 * @returns
 */
var opSearchReset = function(form) {
    if(!form) form = OpConfig.defaultForm;

    opSearchResetForm(form);
};

/**
 * 검색초기화
 *
 * @param form form 태그의 id를 지정
 * @returns
 */
var opSearchResetForm = function(form) {
    if(!form) form = OpConfig.defaultForm;

    $("#"+form+" [name^='"+ OpConfig.prefixSearchParam +"']").each(function() {
        if($(this).attr("type") != "hidden" && $(this).attr("name") != "q_rowPerPage") {
            $(this).val("");
        }
    });

    // 페이징 초기화
    $("#q_currPage").val("1");

    // 기간 날짜 검색 있을 경우 전체로 변경
    if ($("#q_selectDateColor").length > 0) {
        opDateInputSet('ALL');
        if ($("#q_dateSetYn").val() == "Y") {
            $("#dateSet").hide();
            $("#q_dateSetYn").val("N");
        }
    }

    $("#"+form).submit();
};

/**
 * jquery selector 추출
 * @param prefix 접두어
 * @param form 대상 폼
 */
var opGetFilter = function(prefix, form) {
    var filter = form;

    if(prefix) {
        filter += " [name^='" + prefix + "']";
    } else {
        filter = (filter + " input, "+filter + " select");
    }

    return filter;
};

/**
 * 복수개의 파라미터가 발견되었을 경우 복수개를 가질수 있는지를 판단하여 추가여부를 결정함.
 * TODO : radio 예외처리 필요
 * @param formElem jquery 객체
 * @param queryString 생성된 QueryString 문자열
 * @returns QueryString로 추가해도 된다면 추가하여 반환.
 */
var opFilterParam = function(formElem, queryString) {

    var filterQueryString = "";
    if(!formElem.is("textarea")) {
        if(queryString.indexOf(formElem.attr("name")+"=") < 0) {
            var value = formElem.val();
            if(formElem.is("input") && formElem.attr("type") == "checkbox" && !formElem.prop("checked")) {
                value = "";
            }
            filterQueryString = formElem.attr("name") + "=" + encodeURIComponent(value) + "&";
        } else {
            if(((formElem.is("input") && formElem.attr("type") == "checkbox") && formElem.prop("checked"))
                    || (formElem.is("select") && formElem.attr("multiple"))) {
                filterQueryString = formElem.attr("name") + "=" + encodeURIComponent(formElem.val()) + "&";
            }
        }
    }

    return filterQueryString;
};

/**
 * 복수개의 파라미터가 발견되었을 경우 복수개를 가질수 있는지를 판단하여 URL을 생성
 * TODO : radio 예외처리 필요
 * @param url URL 문자열
 * @param form QueryString 문자열 추출 대상 폼
 * @returns QueryString 반환
 */
var opFilterQueryString = function(url, form) {
    if(!form) form = OpConfig.defaultForm;

    var addedUrl = url;
    $("#"+form+" [name^='"+ OpConfig.prefixSearchParam +"']").each(function() {
        if(!$(this).is("textarea")) {
            if(addedUrl.indexOf($(this).attr("name")+"=") < 0) {
                var value = $(this).val();
                if($(this).is("input") && $(this).attr("type") == "checkbox" && !$(this).prop("checked")) {
                    value = "";
                }
                addedUrl += $(this).attr("name") + "=" + encodeURIComponent(value) + "&";
            } else {
                if((($(this).is("input") && $(this).attr("type") == "checkbox") && $(this).prop("checked"))
                        || ($(this).is("select") && $(this).attr("multiple"))) {
                    addedUrl +=  $(this).attr("name") + "=" + encodeURIComponent($(this).val()) + "&";
                }
            }
        }
    });

    return addedUrl;
};

/**
 * URL인지 URI인지를 구분하여 연결문자를 반환.
 */
var opGetMarkChar= function(url) {
    if(url.indexOf("?") >= 0) {
        return "&";
    }
    return "?";
};

