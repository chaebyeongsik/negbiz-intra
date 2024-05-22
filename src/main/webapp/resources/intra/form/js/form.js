$(document).ready(function() {
    /**
     * 지정권한담당자목록 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chkall]").click(function() {
        var isChecked = $(this).prop("checked");
        $("input[name=formSns]").each(function() {
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
 * 선택 삭제
 */
var opCheckDelete = function() {
    var formSns = $("input[name=formSns]:checked").length;
    if(formSns < 1) {
        opWarningMsg("폼을 선택해 주세요.");
        return;
    }

    if(confirm("폼을 정말 삭제하시겠습니까?")) {
        $("#listForm").submit();
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
        var qStDate = $("#q_bgngYmd").val();
        var qEnDate = $("#q_endYmd").val();
        if(qStDate.length > 10 || qEnDate.length >10){
            return;
        }

        if(qStDate.length == 4){
            $("#q_bgngYmd").val(qStDate+"-");
        }else if(qStDate.length == 7){
            $("#q_bgngYmd").val(qStDate+"-");
        }

        if(qEnDate.length == 4){
            $("#q_endYmd").val(qEnDate+"-");
        }else if(qEnDate.length == 7){
            $("#q_endYmd").val(qEnDate+"-");
        }
    }
};
 
/**
 * 항목 유형에 따른 표출
 */  
function selectOpt(groupSn, artclSn, selectVal) {
    var id = groupSn+"_"+artclSn;
	var appendOpt = $("#appendOpt_" + id);
	
	
    $("#num_"+ id).hide();
    $("#cnt_"+ id).hide();
    $("#box_"+ id).hide();
    $("#file_"+ id).hide();
	
    if(selectVal == "numIem"){
		$("#num_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		
		$("#artcl_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artclExp_"+id).find('textarea[name=artclExpln]').each(function(){
			$(this).val('');
		});
		
		
        $("#num_"+ id).show();
        $("#cnt_"+ id).hide();
        $("#box_"+ id).hide();
        $("#file_"+ id).hide();


		if(artclSn != '0'){
	        $(".box_"+ id).hide();
	        $(".plChcCnt_"+ id).hide();
	        $(".lmtFileSz_"+ id).hide();
	
	        delData(groupSn,artclSn);
        }
        appendOpt.append($("#num_"+ id));
        
    }
	else if(selectVal == "selectIem"){	
		$("#insertBoxDiv_"+id).find('input[type=text]').each(function(){
			$(this).val('');
			});
		$("#artcl_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artclExp_"+id).find('textarea[name=artclExpln]').each(function(){
			$(this).val('');
		});
		
		
		$("#boxDiv_"+id).nextAll().remove();
		
        $("#box_"+ id).show(); 
        $("#num_"+ id).hide();
        $("#cnt_"+ id).hide();
        $("#file_"+ id).hide();

        $("#insert_"+ id).off().on('click', function(e){
			$("#insertBoxDiv_"+id).append($("#insertOptDummy_"+id).html()); 
        });
        
		if(artclSn != '0'){
	        $(".lmtVl_"+ id).hide();
	        $(".plChcCnt_"+ id).hide();
	        $(".lmtFileSz_"+ id).hide();
	        $(".box_"+ id).hide();
	        delData(groupSn,artclSn);
		}
        
        appendOpt.append($("#box_"+ id));

    }
	else if(selectVal == "radioIem"){
		$("#insertBoxDiv_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artcl_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artclExp_"+id).find('textarea[name=artclExpln]').each(function(){
			$(this).val('');
		});
		
		
		$("#boxDiv_"+id).nextAll().remove();

		$("#box_"+ id).show(); 
        $("#num_"+ id).hide();
        $("#cnt_"+ id).hide();
        $("#file_"+ id).hide();

        
		$("#insert_"+ id).off().on('click', function(e){
			$("#insertBoxDiv_"+id).append($("#insertOptDummy_"+id).html()); 
        });

		if(artclSn != '0'){
	        $(".lmtVl_"+ id).hide();
	        $(".plChcCnt_"+ id).hide();
	        $(".lmtFileSz_"+ id).hide();
	        $(".box_"+ id).hide();
	        delData(groupSn,artclSn);
		}
        
		appendOpt.append($("#box_"+ id));
	}
	else if(selectVal == "checkIem"){
		$("#insertBoxDiv_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artcl_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artclExp_"+id).find('textarea[name=artclExpln]').each(function(){
			$(this).val('');
		});
		
		
		
		$("#boxDiv_"+id).nextAll().remove();

		$("#box_"+ id).show();		
        $("#cnt_"+ id).show(); 
        $("#num_"+ id).hide();
        $("#file_"+ id).hide();

		if(artclSn != '0'){
	        $(".lmtFileSz_"+ id).hide();
	        $(".lmtVl_"+ id).hide();
			$(".box_"+ id).hide();
	    	delData(groupSn,artclSn);
		}
		

		$("#insert_"+ id).off().on('click', function(e){
			$("#insertBoxDiv_"+id).append($("#insertOptDummy_"+id).html()); 
        });

    	appendOpt.append($("#box_"+ id)).append($("#cnt_"+ id));

        
    }
    else if(selectVal == "fileIem"){
		$("#file_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artcl_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
		$("#artclExp_"+id).find('textarea[name=artclExpln]').each(function(){
			$(this).val('');
		});

		

        $("#file_"+ id).show();
        $("#num_"+ id).hide();
        $("#cnt_"+ id).hide();
        $("#box_"+ id).hide();

		if(artclSn != '0'){
	        $(".lmtVl_"+ id).hide();
	        $(".plChcCnt_"+ id).hide();
	        $(".box_"+ id).hide();
	        
	        delData(groupSn,artclSn);
		}
        appendOpt.append($("#file_"+ id));
        
    }
    else if(selectVal == "shortTxIem" || "longTxIem" || "ynIem" || "emlIem" || "telnoIem" || "ymdIem" || "addrIem"){
    	$("#artcl_"+id).find('input[type=text]').each(function(){
			$(this).val('');
		});
    	$("#artclExp_"+id).find('textarea[name=artclExpln]').each(function(){
			$(this).val('');
		});

        
    }
	else{
		if(artclSn != '0'){
	        $(".lmtVl_"+ id).hide();
	        $(".box_"+ id).hide();
	        $(".plChcCnt_"+ id).hide();
	        $(".lmtFileSz_"+ id).hide();
	        
			delData(groupSn,artclSn);
		}
    }

}


function delData(groupSn,artclSn){
	var id = groupSn+"_"+artclSn;
	//기존 데이터 삭제
	$(".box_"+ id+" input[name=optCns]").prop("disabled", true);
	$(".plChcCnt_"+ id+" input[name=plChcCnt]").prop("disabled", true);
	$(".lmtVl_"+ id+" input[name=initVl]").prop("disabled", true);
	$(".lmtVl_"+ id+" input[name=lmtVl]").prop("disabled", true);
	$(".lmtFileSz_"+ id+" input[name=lmtFileSz]").prop("disabled", true);
	$(".lmtFileSz_"+ id+" input[name=prmsnFileExtnMttr]").prop("disabled", true);
}



