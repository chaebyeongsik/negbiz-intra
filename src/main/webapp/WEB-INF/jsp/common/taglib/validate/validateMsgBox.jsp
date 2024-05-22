<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    var ${validateFuncName} = function(formId) {
        var $validateTarget;
        var formIdVar = "";
        if(formId) {
            formIdVar = formId + " ";
        }
<c:forEach items="${validateScript.results}" var="validateResult"><c:forEach items="${validateResult.typeMsgs}" var="typeMsg">
        $validateTarget = $(formIdVar + "[name='${validateResult.fieldName}']");
        <c:set var="type" value="${typeMsg.validType}Validator" />
        if(!$validateTarget.${type}(<c:forEach items="${typeMsg.validData}" var="validData" varStatus="dataStatus">${validData}<c:if test="${not dataStatus.last}">,</c:if></c:forEach>)) {
            var msg = "${typeMsg.validMsg}";
            var label = $(formIdVar + "label[for='${validateResult.fieldName}']").text().trim();
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
</c:forEach></c:forEach>
<c:if test="${bbsConfigVo.bbsItemMap.file.esntlYn eq 'Y'}">
        $validateTarget = $(formIdVar + "[name='fileSn']");
        let validLength = 0;
        $validateTarget.each(function () {
            if ($(this).requiredValidator()) {
                validLength++;
            }
        });

        if (validLength == 0) {
            let msg = "필수입력 항목입니다.";
            let label = $(formIdVar + "label[for='${bbsConfigVo.bbsItemMap.file.colId}']").text().trim();
            if(label) {
                label = label.replace("*","");
                msg = label + " : " + msg;
            }
            if($validateTarget.length <= 1) {
                $validateTarget.focus();
            } else {
                $validateTarget[0].focus();
            }
            opWarningMsg(msg);
            return false;
        }
</c:if>

        if(typeof(customValidate) != "undefined") {
            return customValidate();
        };

        return true;
    };
