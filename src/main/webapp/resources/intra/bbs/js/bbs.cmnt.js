/* 댓글 목록 갱신 */
var opCmntCmntList = function(cPage) {
    var bbsSn = $("#cmntBbsSn").val();
    var bbsDocNo = $("#cmntBbbsDocNo").val();
    var pageNum = (cPage ? cPage : 1);

    if(!bbsSn || !bbsDocNo) {
        return false;
    } else {
        var params = {
            bbsSn : bbsSn,
            bbsDocNo : bbsDocNo,
            q_currPage : pageNum 
        };
        var href = CTX_PATH + "/intra/bbs/INC_selectBbsCmntList.do";
        $("#opCmntListDiv").load(href, params, function(responseTxt, statusTxt, response) {
            // 목록 로드시 이벤트설정
            if(statusTxt == "success") {
                $(".opCmntRefen").click(function() {
                    opCmntRefrn($(this));
                });
                $(".opCmntReComend").click(function() {
                    opCmntCmntAttr($(this));
                });
                $(".opCmntNonReComend").click(function() {
                    opCmntCmntAttr($(this));
                });
                $(".opCmntSttemnt").click(function() {
                    opCmntCmntAttr($(this));
                });
                $(".opCmntDelete").click(function() {
                    opCmntDelete($(this));
                });
                $(".opCmntDisplayDelete").click(function() {
                    opCmntDisplayDelete($(this));
                });
                $("textarea[name='opnnCn']").keyup(function() {
                    return opCmntMessageCount($(this));
                });
                setCmntDataFormSubmit("cmntRfrnDataForm");
            } else {
                opSysErrorMsg(responseTxt);
            }
        });
    }
};

/* 글자수 표시 및 오버시 잘르기 */
var opCmntMessageCount = function(targetObj) {

    var targetObjId = targetObj.attr("id");
    var opnnCn = $.trim(targetObj.val());
    $("#" + targetObjId + "Cnt").text(opnnCn.length);

    if(opnnCn.length < 5 || opnnCn.length > 1000) {
        opnnCn = opnnCn.substring(0, 1000);
        $("#" + targetObjId + "Cnt").text(opnnCn.length);
        if(opnnCn.length > 5) {
            targetObj.val(opnnCn);
        }

        return false;
    }
    return true;
};

// 관리자 댓글 제한 표시
var opCmntDisplayDelete = function(btnObj) {
    var cmntId = btnObj.attr("id");
    var cmntKeyArr = cmntId.split("__");

    var picDelYn;
    var msg;
    if(cmntKeyArr[4] == "Y") {
        msg = "내용 표시 제한을 해제 하시겠습니까?";
        picDelYn = "N";
    } else {
        msg = "내용 표시를 제한 하시겠습니까?";
        picDelYn = "Y";
    }

    if(confirm(msg)) {

        var param = {
            bbsSn : cmntKeyArr[1],
            bbsDocNo : cmntKeyArr[2],
            opnnSn : cmntKeyArr[3],
            picDelYn : picDelYn
        };

        $.ajaxSettings.traditional = true;
        $.ajax({
            url : CTX_PATH + "/intra/bbs/ND_updateBbsDisplayCmnt.do",
            type : "post",
            dataType : "json",
            data : param,
            success : function(response) {
                if(response.result) {
                    // 화면 갱신
                    opCmntCmntList();
                } else {
                    opErrorMsg(response.message);
                }
            },
            error : function(response) {
                opErrorMsg(response.message);
                return;
            }
        });
    }
};

// 댓글삭제
var opCmntDelete = function(btnObj) {
    if(confirm("정말 삭제하시겠습니까?")) {
        var cmntId = btnObj.attr("id");
        var cmntKeyArr = cmntId.split("__");

        var param = {
            bbsSn : cmntKeyArr[1],
            bbsDocNo : cmntKeyArr[2],
            opnnSn : cmntKeyArr[3]
        };

        $.ajaxSettings.traditional = true;
        $.ajax({
            url : CTX_PATH + "/intra/bbs/ND_deleteBbsCmnt.do",
            type : "post",
            dataType : "json",
            data : param,
            success : function(response) {
                if(response.result) {
                    // 화면 갱신
                    opCmntCmntList();
                } else {
                    opErrorMsg(response.message);
                }
            },
            error : function(response) {
                opErrorMsg(response.message);
                return;
            }
        });
    }
};

// 댓글등록폼 생성
var opCmntRefrn = function(btnObj) {
    $(".opCmntRefenForm").hide();
    var targetId = btnObj.attr("id").replace("opCmntRefen", "opCmntRefenForm");
    $("#" + targetId).show();
};

// 추천/비추천/신고
var opCmntCmntAttr = function(btnObj) {

    var cmntId = btnObj.attr("id");
    var cmntKeyArr = cmntId.split("__");

    var type = "";
    var name = "";
    if(cmntKeyArr[0] == "opCmntReComend") {
        type = "recomend";
        name = "추천";
    } else if(cmntKeyArr[0] == "opCmntNonReComend") {
        type = "nonrecom";
        name = "비추천";
    } else {
        type = "sttemnt";
        name = "신고";
    }

    var param = {
        bbsSn : cmntKeyArr[1],
        bbsDocNo : cmntKeyArr[2],
        opnnSn : cmntKeyArr[3],
        type : type
    };

    $.ajaxSettings.traditional = true;
    $.ajax({
        url : CTX_PATH + "/intra/bbs/ND_updateBbsCmnt.do",
        type : "post",
        dataType : "json",
        data : param,
        success : function(response) {
            if(response.result) {
                // 화면 갱신
                var count = btnObj.text().split(" ")[1];
                var cnt = new Number(count) + 1;
                btnObj.text(name + " " + cnt);
            } else {
                opWarningMsg(response.message);
            }
        },
        error : function(response) {
            opErrorMsg(response.message);
            return;
        }
    });
};

// 각 폼에 등록이벤트 설정
var setCmntDataFormSubmit = function(formName) {

    $("form[name='" + formName + "']").submit(function() {
        var opnnCnObj = $(this).find("textarea[name='opnnCn']");
        var message = $.trim(opnnCnObj.val());
        opnnCnObj.val(message);

        if(!opCmntMessageCount(opnnCnObj)) {
            opWarningMsg("내용은 5자 이상 1000자 이하로 작성해 주세요.");
            opnnCnObj.focus();
        } else {
            var addParam = {};
            var hasFormId = $(this).attr("id");
            if(!hasFormId) {
                addParam = {
                    bbsSn : $("#cmntDataForm #cmntBbsSn").val(),
                    bbsDocNo : $("#cmntDataForm #cmntBbbsDocNo").val()
                };
            }

            $.ajaxSettings.traditional = true;
            $(this).ajaxSubmit({
                type : "POST",
                dataType : "json",
                data : addParam,
                success : function(response) {
                    if(response.result) {
                        // 화면 갱신
                        opCmntCmntList();
                        opnnCnObj.val("");
                    } else {
                        opWarningMsg(response.message);
                    }
                },
                error : function(response) {
                    opErrorMsg(response.message);
                    return;
                }
            });
        }
        return false;
    });
};

/** 페이징 */
var opBbsCmntMovePage = function(cpage) {
    opCmntCmntList(cpage);
};

/* 초기화 */
$(document).ready(function() {

    setCmntDataFormSubmit("cmntDataForm");

    $("textarea[name='opnnCn']").keyup(function() {
        return opCmntMessageCount($(this));
    });

    opCmntCmntList();
});
