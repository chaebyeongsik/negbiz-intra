/**
 * 개인 사용자 등록폼 호출
 */
var opInsertIndvdlForm = function(){
    $().opmodal({
        width : 700,
        height : 700,
        href : "PD_insertIndvdlUserForm.do"
    });
};

/**
 * 사업자 사용자 등록폼 호출
 */
var opInsertEntrprsForm = function(){
    $().opmodal({
        width : 700,
        height : 700,
        href : "PD_insertEntrprsUserForm.do"
    });
};

/**
 * 개인사용자 - 이메일 셀렉트 박스 선택 시 emlSiteNm에 자동 할당
 */
var opEmailCodeChange = function() {
    if($("#emailCode").val() == '') {
        $("#emlSiteNm").val('');
        return;
    }
    $("#emlSiteNm").val($("#emailCode option:selected").text());
};

/**
 * 우편번호
 */
var opZipcodePop = function () {
    alert("보류~!");
    /*
    $().opmodal({
        width : 700,
        height : 800,
        href : "PD_searchList.do"
    });
     */
};

/** 비밀번호 보안등급정책 팝업 */
var opScrtyGradPop = function() {
    var option = "chrome, centerscreen, dependent=yes, width=700, height=500, dialog=yes, modal=yes, resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
    window.open("/intra/mngr/PD_ScrtyGrad.do", "passwdScrtyGrad", option);
};

/**
 * 사용자 ID 중복체크
 */
var opValidateUserId = function(){
    if($("#userId").val() != ''){
        // 영문아이디+숫자+특수기호만 허용(한글 제외)
        if(!opOnlyEngNumCheck($("#userId").val())){
            $("#dupCheckMessage").html("");
            $("#dupCheckVal").val("N");
            return false;
        }else{
            $.ajax({
                url : "ND_selectDupCheckUserId.do",
                type : "POST",
                dataType : "json",
                async : false,
                data : {
                    userId : $("#userId").val(),
                    q_date : new Date().toString()
                },
                success : function(data) {
                    if (data == 0) {
                        $("#dupCheckMessage").html("<p class=\"text-info form-control-static\">사용 가능한 아이디입니다.</p>");
                        $("#dupCheckVal").val("Y");
                    } else {
                        $("#dupCheckMessage").html("<p class=\"text-danger form-control-static\">입력하신 아이디가 이미 존재합니다.</p>");
                        $("#dupCheckVal").val("N");
                    }
                }
            });
        }
    }else{
        $("#dupCheckMessage").html("");
        $("#dupCheckVal").val("");
    }
};

/**
 * 개인사용자 - 기본정보 저장
 */
var opInsertIndvdlUser = function() {
    if(validate()){
        $("#itrstArtclCn").val(opCheckedIntrstIemArray());
        $("#dataForm").ajaxSubmit({
            url      : "ND_insertIndvdlUser.do",
            type     : "POST",
            dataType : "json",
            success  : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    // 등록 후 부모창 리로드
                    opCloseWin();
                    parent.opReload();
                }
            }
        });
    }
};

/**
 * 개인사용자 - 관심항목 배열로 받기
 */
var opCheckedIntrstIemArray = function(){
    var selectedItem = new Array();
    $("#intrstIemDiv input:checkbox:checked").each(function(i){
        selectedItem[i] = $(this).val();
    });
    return selectedItem;
};

/**
 * 개인사용자 - 기본정보 탭 : 수정버튼 눌렀을 때
 */
var opUpdateIndvdlUser = function() {
    if(validate()){
        $("#itrstArtclCn").val(opCheckedIntrstIemArray());
        $("#dataForm").ajaxSubmit({
            url      : "ND_updateIndvdlUser.do",
            type     : "POST",
            dataType : "json",
            success  : function(response) {
                opJsonMsg(response);
                checkUpdateCnt++;
            }
        });
    }
};

/**
 * 생년월일 유효성 체크
 */
var opCheckBirthday = function(){
    if($("#brdt").val() != ''){
        var birthday   = $("#brdt").val();
        var birthYear  = birthday.substr(0, 4);
        var birthMonth = birthday.substr(4, 2)-1;
        var birthDate  = birthday.substr(6, 2);
        var birth = new Date(birthYear, birthMonth, birthDate);

        if ( birth.getFullYear() != birthYear || birth.getMonth() != birthMonth || birth.getDate() != birthDate) {
            opWarningMsg("생년월일을 확인해 주세요.");
            return false;
        }
    }
    return true;
};

/**
 * 양력음력윤달 여부 값 판별 후 input(hidden)에 셋팅
 */
var opSetSlrcldLrrLpnhAt = function(){
    // 1001:양력(slrcld),1002:음력(lrr),1003:윤달(lpnh)
    if ($("#slrcld").is(":checked")) {
        $("#brthYmdClsfSn").val("1001");
    } else if ($("#lrr").is(":checked")) {
        if ($("#lpnh").is(":checked")) {
            $("#brthYmdClsfSn").val("1003");
        } else {
            $("#brthYmdClsfSn").val("1002");
        }
    }
};

/**
 * 개인사용자 - 기본정보 탭 : 비밀번호와 확인용 비밀번호가 일치하는지 체크
 */
var opCheckConfirmPassword = function(){
    if($("#confirmPassword").val() != $("#userPswd").val()){
        opWarningMsg("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    }
    return true;
};


/**
 * 사용자 수정 폼 호출
 */
var opUserInfoPopup = function(userId, userTypeNm){
    var menuNm = encodeURIComponent($("#menuNm").val());
    if(userTypeNm == 'I'){
        $().opmodal({
            width : 700,
            height : 700,
            href : "PD_updateIndvdlUserForm.do?userId="+userId+"&menuNm="+menuNm+"&menuPathNm="+$("#menuPathNm").val()
        });
    }else{
        $().opmodal({
            width : 700,
            height : 700,
            href : "PD_updateEntrprsUserForm.do?userId="+userId+"&menuNm="+menuNm+"&menuPathNm="+$("#menuPathNm").val()
        });
    }
};

/**
 * @param str
 * @returns {Boolean}
 */
var opOnlyEngNumCheck = function(str){
    for( var i = 0; i <= str.length -1 ; i++ ) {//'a' <= str.charAt(i) && str.charAt(i) <= 'z'
        if(('a' <= str.charAt(i) && str.charAt(i) <= 'z') || ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') || str.charAt(i) >= '0' && str.charAt(i) <= '9'){

        } else {
            opWarningMsg("아이디는 영문,숫자만으로 입력해 주십시오.");
            return false;
        }
    }
    return true;
};

var opCheckValidateUserPassword = function(passwordStr){
    if (passwordStr != "" && passwordStr.length > 0) {
        $.ajax({
            url : "/component/passwd/ND_allowPasswd.do",
            type : "GET",
            dataType : "json",
            data : {
                pswd : passwordStr
            },
            success : function(data) {
                if (data.result) {
                    $("#allowPasswd").html("<p class=\"text-info form-control-static\">올바른 비밀번호 조합입니다.</p>");
                    $("#allowPasswdCheckVal").val("Y");
                } else {
                    $("#allowPasswd").html("<p class=\"text-danger form-control-static\">부적합한 비밀번호 조합입니다.</p>");
                    $("#allowPasswdCheckVal").val("N");
                }
            }
        });
    } else {
        $("#allowPasswdCheckVal").val("N");
    }
};

/**
 * 기업사용자 - 기본정보 저장
 */
var opInsertEntrprsUser = function() {
    if(validate()){
        $("#itrstArtclCn").val(opCheckedIntrstIemArray());
        $("#dataForm").ajaxSubmit({
            url      : "ND_insertEntrprsUser.do",
            type     : "POST",
            dataType : "json",
            success  : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    // 등록 후 부모창 리로드
                    opCloseWin();
                    parent.opReload();
                }
            }
        });
    }
};

/**
 * 기업사용자 - 기본정보의 수정 버튼 눌렀을 때
 */
var opUpdateEntrprsUser = function() {
    if(validate()){
        $("#itrstArtclCn").val(opCheckedIntrstIemArray());
        $("#dataForm").ajaxSubmit({
            url      : "ND_updateEntrprsUser.do",
            type     : "POST",
            dataType : "json",
            success  : function(response) {
                opJsonMsg(response);
                checkUpdateCnt++;
                opCloseWin();
            }
        });
    }
};

/**
 * 삭제버튼
 */
var opDelete = function() {
    var selectedUserId = opCheckedUserIdArray();
    var selectedUserTy = opCheckedUserTyArray();
    if (selectedUserId.length == 0) {
        opWarningMsg("삭제할 사용자를 선택해 주세요.");
        return;
    }
    if(confirm("정말 삭제하시겠습니까?")){
        $("#userIds").val(selectedUserId);
        $("#userTypeNms").val(selectedUserTy);
        $.ajax({
            url : "ND_deleteUser.do",
            type : "POST",
            dataType : "json",
            data : { userId : $("#userIds").val(), userTypeNm : $("#userTypeNms").val()},
            success : function(response) {
                opJsonMsg(response);
                if(response.result) {
                    self.location.reload();
                }
            },
            error :  function(response) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
    }
};

/**  체크된 사용자 아이디 가져오기 */
var opCheckedUserIdArray = function() {
    var selectedStr = new Array;

    $("input[name=sns]:checked").each(function(i) {
        selectedStr[i] = $(this).val();
    });

    return selectedStr;
};

/**  체크된 사용자 유형 가져오기 */
var opCheckedUserTyArray = function() {
    var selectedStr = new Array;

    $("input[name=sns]:checked").each(function(i) {
        selectedStr[i] =$(this).parent().parent().find('input[name=userTypeNm]').val();
    });

    return selectedStr;
};

/**
 * 개인사용자 - 기본정보탭 : 필수입력항목으로 설정된 항목들 체크
 */
var opIndvdlInfoEssntlCheck = function(obj){
    if(ihidnumUseAt == 1003){ // 주민등록번호
        if(!isRequiredValidate('rrno')) return false;
    }
    if(emailUseAt == 1003){ // 이메일
        if(!isRequiredValidate('emlId') || !isRequiredValidate('emlSiteNm')) return false;
    }
    if(telnoUseAt == 1003){ // 전화번호
        if(!isRequiredValidate('rgnTelno') || !isRequiredValidate('telofcTelno') || !isRequiredValidate('indivTelno')) return false;
    }
    if(moblphonNoUseAt == 1003){ // 휴대전화번호
        if(!isRequiredValidate('mblRgnTelno') || !isRequiredValidate('mblTelofcTelno') || !isRequiredValidate('mblIndivTelno')) return false;
    }
    if(adresUseAt == 1003) { // 주소
        if(!isRequiredValidate('zip') || !isRequiredValidate('userAddr') || !isRequiredValidate('daddr')) return false;
    }
    if(brthdyUseAt == 1003) { // 생년월일
        if(!isRequiredValidate('slrcldLrr') || !isRequiredValidate('brdt')) return false;
    }
    if(sexdstnUseAt == 1003) { // 성별
        if(!isRequiredValidate('gndrClsfSn')) return false;
    }
    if(mailingSvcUseAt == 1003) { // 메일링서비스
        if(!isRequiredValidate('emlRcptnAgreClsfSn')) return false;
    }
    if(smsRecptnUseAt == 1003) { // SMS수신여부
        if(!isRequiredValidate('smsRcptnClsfSn')) return false;
    }



    if(lastAcdmcrUseAt == 1003){
        if(!isRequiredValidate('lastAcbgNo')) return false; // 최종학력
    }
    if(mrrgUseAt == 1003){
        if(!isRequiredValidate('mrgSeSn')) return false; // 결혼
    }
    if(wrcNmUseAt == 1003){
        if(!isRequiredValidate('wrcNm')) return false; // 직장명
    }
    if(rspofcUseAt == 1003){
        if(!isRequiredValidate('jbttlNm')) return false; // 직책
    }
    if(wrcAdresUseAt == 1003){
        if(!isRequiredValidate('wrcZip') || !isRequiredValidate('wrcAddr') || !isRequiredValidate('wrcDaddr')) return false; // 직장주소
    }
    if(wrcTelnoUseAt == 1003){
        if(!isRequiredValidate('wrcRgnTelno') || !isRequiredValidate('wrcTelofcTelno') || !isRequiredValidate('wrcIndivTelno')) return false; // 직장전화번호
    }
    if(intrstIemNmUseAt == 1003){
        if(!isRequiredValidate('intrstIemNmCode')) return false; // 관심항목명
    }
    return true;
};

var opEntrprsInfoEssntlCheck = function(obj){
    // 설정값이 필수입력사항인 항목 필수입력체크
    if(rprsntvNmUseAt == 1003){
        if(!isRequiredValidate('rprsvNm')) return false; // 대표자명
    }
    if(jurirnoUseAt == 1003) { // 법인등록번호
        if(!isRequiredValidate('crno')) return false;
    }
    if(bizrnoUseAt == 1003) { // 사업자등록번호
        if(!isRequiredValidate('brno')) return false;;
    }
    if(telnoUseAt == 1003) { // 전화번호
        if(!isRequiredValidate('rgnTelno') || !isRequiredValidate('telofcTelno') || !isRequiredValidate('indivTelno')) return false;
    }
    if(adresUseAt == 1003) { // 주소
        if(!isRequiredValidate('zip') || !isRequiredValidate('userAddr') || !isRequiredValidate('daddr')) return false;
    }




    if(picNmUseAt == 1003){
        if(!isRequiredValidate('picNm')) return false; // 담당자명
    }
    if(deptNmUseAt == 1003){
        if(!isRequiredValidate('deptNm')) return false; // 부서명
    }
    if(rspofcUseAt == 1003){
        if(!isRequiredValidate('jbttlNm')) return false; // 직책
    }
    if(emailUseAt == 1003){
        if(!isRequiredValidate('emlId') || !isRequiredValidate('emlSiteNm')) return false; // 이메일
    }
    if(moblphonNoUseAt == 1003){
        if(!isRequiredValidate('mblRgnTelno') || !isRequiredValidate('mblTelofcTelno') || !isRequiredValidate('mblIndivTelno')) return false; // 휴대전화번호
    }
    if(deptTelnoUseAt == 1003){
        if(!isRequiredValidate('deptRgnTelno') || !isRequiredValidate('deptTelofcTelno') || !isRequiredValidate('deptIndivTelno')) return false; // 부서전화번호
    }
    if(fxnumUseAt == 1003){
        if(!isRequiredValidate('rgnFxno') || !isRequiredValidate('telofcFxno') || !isRequiredValidate('indivFxno')) return false; // 팩스전화번호
    }
    if(intrstIemNmUseAt == 1003){
        if(!isRequiredValidate('intrstIemNmCode')) return false; // 관심항목
    }
    return true;
};


/**
 * 필수항목 체크
 */
var isRequiredValidate = function(obj){
    $validateTarget = $("[name="+obj+"]");

    if(!$validateTarget.notnullValidator()) {
        var msg = "필수입력 항목입니다.";
        var label = $("label[for="+obj+"]").text().trim();
        if(label) {
            label = label.replace("*","");
            msg = label + " : " + msg;
        }
        opWarningMsg(msg);
        if($validateTarget.length <= 1) {
            $validateTarget.focus();
        } else {
            $validateTarget[0].focus();
        }
        return false;
    }
    return true;
};

var opSetUserGradCode = function(){
    var checkedItem = new Array();
    $("input[name=userGrdCdId]:checked").each(function(i){
        checkedItem[i] = $(this).val();
    });
    $("#userGrdCdDsctn").val(checkedItem);
};
