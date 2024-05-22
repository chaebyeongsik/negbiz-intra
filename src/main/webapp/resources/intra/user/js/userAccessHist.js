var opStatistics = function(){
    $("#todayNoLoginUser").show();
};

var opUserAccessInfoPop = function(userId){
    var menuNm = encodeURIComponent($("#menuNm").val());
    $().opmodal({
        width : 800,
        height : 700,
        href : "/intra/user/accessHist/PD_selectUserAccessHist.do?q_userId="+userId+"&menuNm="+menuNm+"&menuPathNm="+$("#menuPathNm").val()
    });
};