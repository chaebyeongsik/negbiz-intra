<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty validateResult.typeMsgs}">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <c:forEach items="${validateResult.typeMsgs}" var="typeMsg">
                                        <li class="list-group-item"><span class="validate-msg">${typeMsg.validMsg}</span></li>
                                        </c:forEach>
                                    </ul>
                                </div>
</c:if>