<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-paragraph-right2"></i> ${param.formNm}
                </h6>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${param.listType eq 'ETC'}">
                        <c:set value="${estbsList.etcInfo}" var="list" />
                    </c:when>
                    <c:when test="${param.listType eq 'BASIC'}">
                        <c:set value="${estbsList.basicInfo}" var="list" />
                    </c:when>
                </c:choose>
                <c:forEach items="${list}" var="dataVo" varStatus="vst">
                    <div class="form-group">
                        <label for="q_useYnY${dataVo.stngArtclSn}" class="control-label col-sm-3">${dataVo.stngArtclNm}</label>
                        <div class="col-sm-9">
                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="hidden" name="stngArtclSns" id="stngArtclSn_${dataVo.stngArtclSn}" value="${dataVo.stngArtclSn}" />
                                    <label for="q_useYnY${dataVo.stngArtclSn}" class="radio-inline radio-primary">
                                        <input type="radio" name="q_useYn${dataVo.stngArtclSn}" id="q_useYnY${dataVo.stngArtclSn}" value="Y" class="styled" <c:if test="${dataVo.useYnCode eq 1001 or dataVo.useYnCode eq 1003}">checked="checked"</c:if> />
                                        사용
                                    </label>
                                    <label for="q_useYnN${dataVo.stngArtclSn}" class="radio-inline radio-primary">
                                        <input type="radio" name="q_useYn${dataVo.stngArtclSn}" id="q_useYnN${dataVo.stngArtclSn}" value="N" class="styled" <c:if test="${dataVo.useYnCode eq 1002}">checked="checked"</c:if> />
                                        미사용
                                    </label>&nbsp;
                                    <c:set value="" var="essntlAtDisabled" />
                                    <c:set value="" var="essntlAtAttrDisabled" />
                                    <c:if test="${dataVo.useYnCode eq 1002}">
                                        <c:set value="disabled" varS="essntlAtDisabled" />
                                        <c:set value="disabled=\"disabled\"" var="essntlAtAttrDisabled" />
                                    </c:if>
                                    <label for="esntlYn${dataVo.stngArtclSn}" class="checkbox-inline checkbox-primary ${essntlAtDisabled}">
                                        <input type="checkbox" name="q_esntlYn${dataVo.stngArtclSn}" id="esntlYn${dataVo.stngArtclSn}" value="Y" class="styled" ${essntlAtAttrDisabled} <c:if test="${dataVo.useYnCode eq 1003}">checked="checked"</c:if> />
                                        필수항목 지정
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>