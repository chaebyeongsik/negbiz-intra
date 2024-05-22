<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    focusCleanup: true,
    errorClass: "validate-has-error",
    validClass: "",
    errorElement: "span",
    highlight: function( element, errorClass, validClass ) {
        if ( element.type === "radio" ) {
            this.findByName( element.name ).addClass( errorClass );
        } else {
            $( element ).addClass( errorClass );
        }
    },
    unhighlight: function( element, errorClass, validClass ) {
        if ( element.type === "radio" ) {
            this.findByName( element.name ).removeClass( errorClass );
        } else {
            $( element ).removeClass( errorClass );
        }
    },
    errorPlacement: function(error, element) {
        opWarningMsg(error.text());
    },
    rules: {
    <c:forEach items="${validateScript.results}" var="validateResult" varStatus="validateResultStatus">
        ${validateResult.fieldName} : {
        <c:forEach items="${validateResult.typeMsgs}" var="typeMsg" varStatus="typeMsgStatus">
            ${typeMsg.validType} :  <c:choose>
                                        <c:when test="${empty typeMsg.validData}">true</c:when>
                                        <c:otherwise>[<c:forEach items="${typeMsg.validData}" var="validData" varStatus="dataStatus">${validData}<c:if test="${not dataStatus.last}">,</c:if></c:forEach>]</c:otherwise>
                                    </c:choose><c:if test="${not typeMsgStatus.last}">,</c:if>
        </c:forEach>
        }<c:if test="${not validateResultStatus.last}">,</c:if>
    </c:forEach>
    },
    messages:{
    <c:forEach items="${validateScript.results}" var="validateResult" varStatus="validateResultStatus">
        ${validateResult.fieldName} : {
            <c:forEach items="${validateResult.typeMsgs}" var="typeMsg" varStatus="typeMsgStatus">
                ${typeMsg.validType} : function() {
                    var msg = "${typeMsg.validMsg}";
                    var label = $("label[for='${validateResult.fieldName}']").text().trim();
                    if(label) {
                        label = label.replace("*","");
                        msg = label + " : " + msg;
                    }
                    return msg;
                }<c:if test="${not typeMsgStatus.last}">,</c:if>
            </c:forEach>
        }<c:if test="${not validateResultStatus.last}">,</c:if>
    </c:forEach>
   }
