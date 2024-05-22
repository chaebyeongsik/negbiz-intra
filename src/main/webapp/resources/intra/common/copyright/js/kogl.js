
// 공공누리 html 생성 후 저장대상 textarea에 자동 입력
var fnKoglTypeSet = function(sel) {
    var type = sel.value;
    // 기관명은 필수입니다. webapp-commons-config.xml 참조
    var sOrgName = OpConfig.orgName;
    // 저작물 제목 입력란의 id를 매칭시켜주세요. hidden으로라도 생성해줘야 함
    var sKoglTitle = $("#ttl").val();
    if(sOrgName.length == 0) {
        opWarningMsg("기관명을 설정해주세요.");
        sel.checked = false;
        return false;
    } else if(sKoglTitle.length == 0) {
        opWarningMsg("게시물의 제목이 작성되어야 합니다.");
        sel.checked = false;
        return false;
    }

    var sInfoUrl = "";
    var sInfoAlt = "";
    var sImgUrl = "";
    var sLpadd = "";
    if(type == "1") {
        sLpadd = 190;
        sInfoAlt = "\"출처표시\"";
        sInfoUrl = "<a href='http://www.kogl.or.kr/info/licenseType1.do' target='_blank'>" + sInfoAlt + "</a>";
        sImgUrl ="'http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opentype01.png' style='position:absolute;left:20px;top:12%;vertical-align:middle;width:149px;height:54px;'";
    } else if(type == "2") {
        sLpadd = 220;
        sInfoAlt = "\"출처표시+상업적 이용금지\"";
        sInfoUrl = "<a href='http://www.kogl.or.kr/info/licenseType2.do' target='_blank'>"+sInfoAlt+"</a>";
        sImgUrl ="'http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opentype02.png' style='position:absolute;left:20px;top:12%;vertical-align:middle;width:183px;height:54px;'";
    } else if(type == "3") {
        sLpadd = 220;
        sInfoAlt = "\"출처표시+변경금지\"";
        sInfoUrl = "<a href='http://www.kogl.or.kr/info/licenseType3.do' target='_blank'>"+sInfoAlt+"</a>";
        sImgUrl ="'http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opentype03.png' style='position:absolute;left:20px;top:12%;vertical-align:middle;width:183px;height:54px;'";
    } else if(type == "4") {
        sLpadd = 250;
        sInfoAlt = "\"출처표시+상업적 이용금지+변경금지\"";
        sInfoUrl = "<a href='http://www.kogl.or.kr/info/licenseType4.do' target='_blank'>"+sInfoAlt+"</a>";
        sImgUrl ="'http://www.kogl.or.kr/open/web/images/images_2014/codetype/new_img_opentype04.png' style='position:absolute;left:20px;top:12%;vertical-align:middle;width:219px;height:54px;'";
    } else if(type == "5") {
        sLpadd = 60;
        sInfoAlt = "공공저작물 자유이용 허락표시 적용 안함";
        sInfoUrl = "<a href='http://www.law.go.kr/lsInfoP.do?lsiSeq=148848&efYd=20140701#0000' target='_blank'>"+sInfoAlt+"</a>";
        sImgUrl ="'http://www.kogl.or.kr/open/web/images/images_2014/codetype/img_opencode0_1.jpg' style='position:absolute;left:20px;top:25%;vertical-align:middle;width:27px;height:27px;'";
    }

    // 데이터 베이스에 등록되는 자유이용 스크립트 완성
    var koglHtml = "<div style='position:relative;margin:0;padding:0;margin-top:5px;background:#fff;border:1px solid #dbdbdb;padding:30px 15px 30px " + sLpadd + "px;font-size:12px;color:#292929;font-weight:bold;'>";
    koglHtml += "<img src=" + sImgUrl + " alt='" + sInfoAlt + "'/>";
    if(type != "5") {
        koglHtml += "<span>" + sOrgName + "</span>이(가) 창작한 <span>[ " + sKoglTitle + " ]</span> 저작물은 공공누리&nbsp;";
    }
    koglHtml += sInfoUrl;
    if(type != "5") {
        koglHtml += "&nbsp;조건에 따라 이용할 수 있습니다.";
    }
    koglHtml += "</div>";

    $("#koglCont").val(koglHtml);
};

//공공누리 정보개방 관련 스크립트 시작
$(document).ready(function() {
    $('#koglType1').click(function() {
        $(".codeView02, .codeView03,.codeView04,.codeView05").addClass('hide');
        $(".codeView01").removeClass('hide');
    }); // type1 check
    $('#koglType2').click(function() {
        $(".codeView01, .codeView03,.codeView04,.codeView05").addClass('hide');
        $(".codeView02").removeClass('hide');
    }); // type2 check
    $('#koglType3').click(function() {
        $(".codeView02, .codeView01,.codeView04,.codeView05").addClass('hide');
        $(".codeView03").removeClass('hide');
    }); // type3 check
    $('#koglType4').click(function() {
        $(".codeView02, .codeView03,.codeView01,.codeView05").addClass('hide');
        $(".codeView04").removeClass('hide');
    }); // type4 check
    $('#koglType5').click(function() {
        $(".codeView02, .codeView03,.codeView04,.codeView01").addClass('hide');
        $(".codeView05").removeClass('hide');
    }); // 적용안함 check

    var cpyrhtTy = $("input[name='cpyrhtTy']:checked").val();
    if(cpyrhtTy) {
        $("#koglType"+cpyrhtTy).trigger("click");
    }
});
