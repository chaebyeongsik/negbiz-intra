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
<%@ page import="zesinc.web.support.tag.bbsTmplat.support.BbsStyleSupport"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <c:choose>
        <c:when test="${bbsType eq 'list'}">
            <op:nrToBr content="${bbsObj.docContsCn}"/>
        </c:when>
        <c:when test="${bbsType eq 'view'}">
            <div class="txt">
                 <%-- <op:nrToBr content="${bbsObj.docContsCn}"/> --%>
                ${bbsObj.docContsCn}
            </div>
        </c:when>
        <c:when test="${bbsType eq 'value'}">
            ${bbsObj.docContsCn}
        </c:when>
        <c:when test="${bbsType eq 'text'}">
            ${bbsItemVo.colNm}
        </c:when>
        <c:when test="${bbsType eq 'label'}">
            <label for="${bbsItemVo.colId}" class="<%= BbsStyleSupport.BBS_LABEL_CLASS %>">
                <c:if test="${bbsItemVo.esntlYn eq 'Y'}">
                    <span class="<%= BbsStyleSupport.BBS_REQUIRE_CLASS %>">*</span>
                </c:if>
                ${bbsItemVo.colNm}
            </label>
        </c:when>
        <c:when test="${bbsType eq 'desc'}">
            ${bbsItemVo.bbsColExpln}
        </c:when>
        <c:when test="${bbsType eq 'form'}">
            <textarea class="<%= BbsStyleSupport.BBS_TEXTAREA_CLASS %>" name="${bbsItemVo.colId}" id="${bbsItemVo.colId}" rows="5" cols="80"><c:out value="${bbsObj.docContsCn}"/></textarea>
            <c:if test="${bbsConfigVo.mngrEdtrUseYn eq 'Y'}">
                <script type="text/javascript">
                    //<![CDATA[
                    $(document).ready(function() {
                       $("#${bbsItemVo.colId}").ckeditor({
                            toolbar : toolbar_config.default_toolbar,
                            filebrowserImageUploadUrl: "/component/file/ND_fileUpload.do"
                        });
                    });
                    //]]>
                </script>
            </c:if>
        </c:when>
    </c:choose>

