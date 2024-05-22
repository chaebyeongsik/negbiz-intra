/* 정렬순서 변경창 팝업 */
var opUpdateSortOrdrList = function() {

    var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
    option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
    var href = "PD_selectBannerSortOrdrList.do";

    var opUpdateSortOrdrList = window.open(href, "opUpdateSortOrdrList", option);
    opUpdateSortOrdrList.focus();
};

/**
 * 베너유형 선택시 하위 상세유형이 코드에 등록되어 있다면 하위 코드 목록을 가져온다.
 */
var opLoadBannerDetailTy = function(hghrkCdId, targetId, value) {
    var cdId = $("input[name='bnnTypeNm']:checked").val();
    var param = {
        q_hghrkCdId : hghrkCdId,
        q_cdId : cdId,
        q_date : new Date().toDateString()
    };
    $.ajax({
        url : "ND_selectBannerDetailTy.do",
        type : "post",
        dataType : "json",
        data : param,
        success : function(response) {
            if(response.result) {
                $("#" + targetId).html("");
                var bannerDetailTyList = response.value;
                var bannerDetailTyListCnt = bannerDetailTyList.length;
                $("#" + targetId).append("<option value=\"\">" + OpMessage.common.selectOption + "</option>");
                for(var i = 0 ; i < bannerDetailTyListCnt ; i++) {
                    $("#" + targetId).append("<option value=\"" + bannerDetailTyList[i].cdId + "\">" + bannerDetailTyList[i].cdNm + "</option>");
                }
                $("#" + targetId).select2("val", value);
            } else {
                opErrorMsg(response.message);
            }
        },
        error : function(response) {
            opSysErrorMsg(response.responseText);
            return;
        }
    });
};

// 날짜형식으로 변환
var opDateAuto = function(obj){
    // cursor를 문장 끝으로 이동
    if(obj.createTextRange){
        var FieldRange = obj.createTextRange();
        FieldRange.moveStart('character', obj.value.length);
        FieldRange.collapse();
        FieldRange.select();
    }

    //입력 허용 키
    if ((event.keyCode >= 48 && event.keyCode <= 57) ||   //숫자열 0 ~ 9 : 48 ~ 57
        (event.keyCode >= 96 && event.keyCode <= 105) ||  //키패드 0 ~ 9 : 96 ~ 105
         event.keyCode == 8  || event.keyCode == 46 ||    //BackSpace, Delete
         event.keyCode == 35 || event.keyCode == 36 ||    //End 키, Home 키
         event.keyCode == 9  || event.keyCode == 45)    {    //Tab 키
        event.returnValue = true;
    }else{
        event.returnValue = false;
    }
    if(event.keyCode != 8){
        var qStDate = $("#bgngYmd").val();
        var qEnDate = $("#endYmd").val();
        if(qStDate.length > 10 || qEnDate.length >10){
            return;
        }

        if(qStDate.length == 4){
            $("#bgngYmd").val(qStDate+"-");
        }else if(qStDate.length == 7){
            $("#bgngYmd").val(qStDate+"-");
        }

        if(qEnDate.length == 4){
            $("#endYmd").val(qEnDate+"-");
        }else if(qEnDate.length == 7){
            $("#endYmd").val(qEnDate+"-");
        }
    }
};
