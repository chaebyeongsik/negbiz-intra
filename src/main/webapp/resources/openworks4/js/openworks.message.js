
/**
 * 시스템 오류(프로그램 오류 등) 메시지 발생 시의 메시지 박스
 * 설정에 따라서 시스템 오류 자체를 보여 주거나 또는 대체 메시지만 표시한다. 
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opSysErrorMsg = function(msg, e, options) {
    if(!options)
        options = {};

    options["theme"] = "syserror";
    options["sticky"] = true;

    var message = "";

    if(productionMode) {
        if(e) {
            message += "JavaScript Error : " + e + " \r <br />";
        }
        if(msg) {
            message += " System Error : " + msg;
        }
        opSendMsg(message, options);
    } else {
        opSendMsg(OpMessage.common.processError, options);
    }

    if (message) {
        return true;
    }
    return false;
};

/**
 * Controller에서 responseJson(model, Boolean.TRUE, value, Messages) 
 * 형태로 반환되는 경우 내부에서 결과(response.result)의 참/거짓 여부를 확인하여,
 *  opSuccessMsg 또는 opErrorMsg를 선별하여 메시지를 출력한다.
 * 
 * @param response 응답객체
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opJsonMsg = function(response, msg) {
    var message = response.message;
    if(msg) {
        message += "<br />" + msg;
    }
    if(response.result) {
        return opSuccessMsg(message);
    } else {
        return opErrorMsg(message);
    }
};

/**
 * 일반 알림 메시지
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opMsg = function(msg, options) {
    if(!options)
        options = {};

    //options["theme"] = "success";
    options["life"] = 2000;

    opSendMsg(msg, options);

    if (msg) {
        return true;
    }
    return false;
};

/**
 * 프로세스 성공시 알림 메시지
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opSuccessMsg = function(msg, options) {
    if(!options)
        options = {};

    options["theme"] = "success";
    options["life"] = 3000;

    opSendMsg(msg, options);

    if (msg) {
        return true;
    }
    return false;
};

/**
 * 프로세스 경고시 알림 메시지
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opWarningMsg = function(msg, options) {
    if(!options)
        options = {};

    options["theme"] = "warning";
    options["life"] = 3000;

    opSendMsg(msg, options);

    if (msg) {
        return true;
    }
    return false;
};

/**
 * 프로세스 실패시 알림 메시지
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opErrorMsg = function(msg, options) {
    if(!options)
        options = {};

    options["theme"] = "error";
    options["sticky"] = true;

    opSendMsg(msg, options);

    if (msg) {
        return true;
    }
    return false;
};

/**
 * 최상위 창에서 메시지를 띄우기 위한 컨트롤
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opSendMsg = function(msg, options) {
//    if(opener) {
//        opener.opShowMsg(msg, options);
//    }
    opShowMsg(msg, options);
};

/**
 * 옵션을 받아서 메시지 표시
 *
 * @param msg 메시지 내용
 * @param options 메시지 옵션
 * @returns
 */
var opShowMsg = function(msg, options) {

    options["theme"] = "growl-"+ options["theme"];

    if(msg) {
        $.jGrowl(msg, options);
    }
};
