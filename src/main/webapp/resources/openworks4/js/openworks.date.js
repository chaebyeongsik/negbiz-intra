$().ready(function() {
    var selectDateColor = $("#q_selectDateColor").val();
    $("#"+selectDateColor).switchClass("btn-default", "btn-warning");

    if($("#dateSet")){
        if($("#q_dateSetYn").val() == "Y"){
            $("#dateSet").show();
        }else{
            $("#dateSet").hide();
        }
    }

    $("#dateSet1 button.dateIem").click(function() {
        opDateInputSet($(this).attr('id'));
    });
});

var opDateInputSet = function(id){
    $('#q_endDt').val(opGetDateStr(new Date()));
    var sType = id;
    var stDate = null;
    switch(sType) {
    case '0' :
        stDate = new Date();
        $("#q_selectDateColor").val("0");
        opDateButton();
        break;
    case '1D':
        stDate = opGetCalDate(new Date(),0,-1);
        $("#q_selectDateColor").val("1D");
        opDateButton();
        break;
    case '7D':
        stDate = opGetCalDate(new Date(),0,-7);
        $("#q_selectDateColor").val("7D");
        opDateButton();
        break;
    case '10D':
        stDate = opGetCalDate(new Date(),0,-10);
        $("#q_selectDateColor").val("10D");
        opDateButton();
        break;
    case '15D':
        stDate = opGetCalDate(new Date(),0,-15);
        $("#q_selectDateColor").val("15D");
        opDateButton();
        break;
    case '1M':
        stDate = opGetCalDate(new Date(),-1,0);
        $("#q_selectDateColor").val("1M");
        opDateButton();
        break;
    case '3M':
        stDate = opGetCalDate(new Date(),-3,0);
        $("#q_selectDateColor").val("3M");
        opDateButton();
        break;
    case '6M':
        stDate = opGetCalDate(new Date(),-6,0);
        $("#q_selectDateColor").val("6M");
        opDateButton();
        break;
    case 'ALL':
        stDate = new Date();
        $("#q_selectDateColor").val("ALL");
        opDateButton();
        break;
    default:
        stDate = new Date();
        break;
    }
    $('#q_startDt').val(opGetDateStr(stDate));
    if ($('#q_endDt').val() == '') {
        $('#q_endDt').val(opGetDateStr(new Date()));
    }

    if (sType == 'ALL') {
        $('#q_startDt').val("");
        $('#q_endDt').val("");
    }
};

var opDateButton = function(){
    $(".dateIem").switchClass("btn-warning", "btn-default");
    $("#"+$("#q_selectDateColor").val()).switchClass("btn-default", "btn-warning");
};

var opGetCalDate = function(date, months, days) {
    var newDate = date;
    if (typeof(date.getDate) != 'function') {
        newDate = new Date();
    }

    newDate.setMonth(newDate.getMonth() + months);
    newDate.setDate(newDate.getDate() + days);

    return newDate;

};

var opGetDateStr = function(date) {
    return '' + date.getFullYear() + '-' + opSetToStr(date.getMonth() + 1) + '-' + opSetToStr(date.getDate());
};

var opSetToStr = function(vl) {
    return parseInt(vl,10) > 9 ? vl : '0' + vl;
};

var opDateSet = function(){
    $(".dateIem").switchClass("btn-warning", "btn-default");
    $("#q_selectDateColor").val("");
    if($("#q_dateSetYn").val() == "Y"){
        $("#dateSet").hide();
        $('#q_startDt').val('');
        $('#q_endDt').val('');
        $("#q_dateSetYn").val("N");
    }else{
        $("#dateSet").show();
        $('#q_startDt').val('');
        $('#q_endDt').val('');
        //$('#q_startDt').focus();
        $("#q_dateSetYn").val("Y");
    }
};

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
        var qStDate = $("#q_startDt").val();
        var qEnDate = $("#q_endDt").val();
        if(qStDate.length > 10 || qEnDate.length >10){
            return;
        }

        if(qStDate.length == 4){
            $("#q_startDt").val(qStDate+"-");
        }else if(qStDate.length == 7){
            $("#q_startDt").val(qStDate+"-");
        }

        if(qEnDate.length == 4){
            $("#q_endDt").val(qEnDate+"-");
        }else if(qEnDate.length == 7){
            $("#q_endDt").val(qEnDate+"-");
        }
    }
};