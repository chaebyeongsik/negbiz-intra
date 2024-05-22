/**
 * 파일 정렬순서 변경
 */
var opChangeSortOrdr = function(fileSn) {
    var url = CTX_PATH + "/component/file/PD_fileUpdateForm.do?q_fileSn=" + fileSn;
    var option = {
        href : url,
        width : 807,
        height : 500
    };

    $.fn.opmodal(option);
};

/**
 * 파일 다운이력
 */
var opShowFileHistory = function(fileSn, fileId) {
    var url = CTX_PATH + "/component/file/PD_fileLogList.do?q_fileSn=" + fileSn + "&q_fileId=" + fileId;
    var option = {
        href : url,
        width : 807,
        height : 500
    };

    $.fn.opmodal(option);
};

/**
 * ID 네이밍 기준 select 선택값 설정
 *
 * @param id
 *            대상 객체의 id 문자열
 * @param value
 *            대상 객체의 설정 값(선택되어 질 값)
 * @param defaultValue
 *            value 값이 없는 경우 기본값
 * @param form
 *            대상 폼. 기본값 : #dataForm
 * @returns
 */
var opSelected = function(id, value, defaultValue, form) {
    var formName = "";
    if(!form) {
        formName = "#" + OpConfig.defaultForm + " ";
    } else {
        formName = form + " ";
    }

    var val = value;
    if(value == "" && defaultValue != "") {
        val = defaultValue;
    }
    $(formName + "#" + id).val(val);
    try {
        $(formName + "#" + id).select2("val", val);
    } catch(e) {
    }
};

/**
 * input name 네이밍 기준 input radio 선택값 설정
 *
 * @param 대상
 *            객체의 name 문자열
 * @param value
 *            대상 객체의 설정 값(체크되어 질 값)
 * @param defaultValue
 *            value 값이 없는 경우 기본값
 * @param form
 *            대상 폼. 기본값 : #dataForm
 * @returns
 */
var opChecked = function(name, value, defaultValue, form) {
    var formName = "";
    if(!form) {
        formName = "#" + OpConfig.defaultForm + " ";
    } else {
        formName = form + " ";
    }

    var val = value;
    if(value == "" && defaultValue) {
        val = defaultValue;
    }
    var arrVal = val.split(",");

    $(formName + "input[name='" + name + "']").each(function(idx) {
        for(var i = 0 ; i < arrVal.length ; i++) {
            if($(this).val() == arrVal[i]) {
                $(this).attr("checked", "true");
                $(this).prop("checked", "true");
            }
        }
    });

    if($.uniform) {
        $.uniform.update(formName + "input[name='" + name + "']");
    }
};

/**
 * 팝업 또는 colorbox 윈도우 닫기
 */
var opCloseWin = function() {
    var winName = window.name;
    if(winName) {
        if(winName.indexOf("fancybox") > -1) {
            if(parent.$.fancybox) {
                parent.$.fancybox.close();
            } else if($.fancybox) {
                $.fancybox.close();
            }
        } else {
            self.close();
        }
    } else {
        if(parent.$.fancybox) {
            parent.$.fancybox.close();
        } else if($.fancybox) {
            $.fancybox.close();
        } else {
            self.close();
        }
    }
};

/**
 * 일반타입 폼에서 파일첨부시 확장자 필터링
 */
var opFilterFileExtBasic = function(el, exts) {

    var fileFormName = el.name;

    // 설정상의 최대 업로드 크기 제한
    var maxEachSize = Number($("#" + fileFormName + "EachSize").val());
    var maxTotalSize = Number($("#" + fileFormName + "TotalSize").val());

    // 현재 변경되는 파일 입력 폼의 객체
    var totalSize = 0;
    var eachSize = 0;

    if($(el)[0].files) {
        // 현재 파일 사이즈
        if($(el)[0].files[0]) {
            eachSize = $(el)[0].files[0].size;
        }

        // 전체 파일 사이즈
        $("input[name='" + fileFormName + "']:file").each(function(idx) {
            if($(this)[0].files[0]) {
                eachSize = $(this)[0].files[0].size;
                totalSize += eachSize;
            }
        });

        if(eachSize > maxEachSize) {
            opWarningMsg("파일당 최대 첨부 크기를 초과했습니다. (현재 : " + eachSize + " Byte, 최대 : " + maxEachSize + " Byte)");
            $(el).val("");
            return false;
        }
        if(totalSize > maxTotalSize) {
            opWarningMsg("전체 최대 첨부 크기를 초과했습니다.  (현재 : " + totalSize + " Byte, 최대 : " + maxTotalSize + "  Byte)");
            $(el).val("");
            return false;
        }
    }

    var isAllow = true;
    var filePath = $(el).val();
    if(filePath && exts) {
        isAllow = false;
        var chFilePath = filePath.toLowerCase();
        var chExts = exts.toLowerCase();
        var arrExts = chExts.split(",");
        if(arrExts && arrExts.length) {
            var pos = chFilePath.lastIndexOf(".");
            if(pos > -1) {
                var ext = chFilePath.substring(pos + 1);
                for(var i = 0 ; i < arrExts.length ; i++) {
                    if(arrExts[i] === ext) {
                        isAllow = true;
                        break;
                    }
                }
            } else {
                isAllow = false;
            }
        }
    }

    if(!isAllow) {
        var control = $(el);
        var agent = navigator.userAgent.toLowerCase();
        if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) { // IE일경우
            control.replaceWith(control = control.clone(true));
        } else { // IE가 아닐경우
            control.val("");
        }
        opWarningMsg("허용되지 않는 파일유형입니다.\r\n첨부파일은 '" + exts + "' 확장자를 가진 파일만 가능합니다.");
    }
    return isAllow;
};

/**
 * 일반 타입 폼에서 파일첨부 갯수 자동 조절 1삭제 체크에 1추가 토글 체크시 현재 첨부가능한 전체 파일 크기를 조정한다.
 */
var opFileInputCtrl = function(checkBoxObj, fileFormName) {
    var isChecked = $(checkBoxObj).prop("checked");
    var opFileFormDiv = "op" + fileFormName + "FileFormDiv";
    var opFileDummyDiv = "op" + fileFormName + "FileDummyDiv";

    if(isChecked) {
        var dummyHtml = $("#" + opFileDummyDiv).html();
        $("#" + opFileFormDiv).append(dummyHtml);
        $("#" + opFileFormDiv + " input[name='dummy']").attr("name", fileFormName);
    } else {
        $("#" + opFileFormDiv + " > div:last-child").remove();
    }

    // 현재 선택된 체크박스의 파일 크기
    var fileSize = $(checkBoxObj).attr("data-file-size");
    var numFileSize = Number(fileSize);
    // 추가로 첨부가능한 전체 파일 크기
    var totalSize = $("#" + fileFormName + "TotalSize").val();
    var numTotalSize = Number(totalSize);
    if(isChecked) {
        numTotalSize += numFileSize;
    } else {
        numTotalSize -= numFileSize;
    }
    $("#" + fileFormName + "TotalSize").val(numTotalSize);
};

/**
 * 로딩중 표시
 * 프로세스 시작시 화면내의 모든 항목을 컨트롤하지 못하도록 함.
 * message를 전달하면 아이콘이 아닌 메시지를 표시한다.
 *
 * @param message 표시할 문구. 기본값 : 요청을 처리중입니다. 잠시 기다려 주십시오.
 * @param timeout 스크립트가 비정상인 경우 자동으로 닫을 시간 기본값 : 20000 (20초)
 */
var opLoadStart = function(message, timeout) {
    // 기본 메시지
    if(!message) {
        message = "요청을 처리중입니다. 잠시 기다려 주십시오.";
    }
    // 기본값 20초
    if(!timeout) {
        timeout = 20000;
    }

    var overlayHtml = "";
    overlayHtml =   "<div id=\"opLoadingOverlay\">";
    overlayHtml += "    <div id=\"opLoadingOpacity\">";
    overlayHtml += "        <div class=\"op-spin\">";
    overlayHtml += "            <div class=\"op-alert\">";
    overlayHtml += "            <img src=\"/resources/openworks4/image/icon/loding/loading_03.gif\" width=\"15\" height=\"15\" alt=\"loding...\">&nbsp;";
    overlayHtml += message;
    overlayHtml += "           </div>";
    overlayHtml += "        </div>";
    overlayHtml += "    </div>";
    overlayHtml += "</div>";

    $("body").append(overlayHtml);
    $("#opLoadingOverlay").fadeIn(150);

    // 만약을 대비하여 자동으로 닫히기 10초
    window.setTimeout(function(){
        opLoadEnd();
    }, timeout);
};

/**
 * 로딩중 제거
 */
var opLoadEnd = function() {
    $("#opLoadingOverlay").fadeOut(150, function() {
        $(this).remove();
    });
};

/**
 * 객체 속성보기 (디버깅용) 예 : viewProp(대상 Object);
 */
var viewProp = function() {
    var allHtml = "";

    for(var x = 0 ; x < arguments.length ; x++) {
        var obj = arguments[x];

        var names = "<table border='1' cellspacing='1' cellpadding='1'><tr>";
        if(obj && obj.toString().substr(0, 8) === '[object ') {
            names += "<td>name</td><td>value</td></tr>";
            var arr = new Array();
            for( var name in obj) {
                arr.push(name);
            }
            arr.sort();
            for(var i = 0 ; i < arr.length ; i++) {
                var key = arr[i];
                names += "<tr><td>";
                names += key;
                names += '</td><td>';
                names += obj[key];
                names += '</td></tr>';
            }
            names += "</table>";
            arr = null;
        } else {
            names += "<td>value</td></tr>";
            names += "<tr><td>";
            names += obj;
            names += '</td></tr>';
        }
        allHtml += names + "<br/>";
    }

    var pop = window.open("", "viewProperty" + Math.random(), "", "");
    pop.focus();
    pop.document.body.innerHTML = allHtml.fixed();

};