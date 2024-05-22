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

    <c:if test="${bbsConfigVo.atchFileUseYn eq 'Y'}">
        <c:choose>
            <c:when test="${bbsType eq 'list'}">
                <c:if test="${not empty bbsObj.fileList}">
                    <c:forEach var="fileVo" items="${bbsObj.fileList}">
                        <a href="/component/file/ND_fileDownload.do?q_fileSn=${fileVo.fileSn}&amp;q_fileId=${fileVo.fileId}" title="${fileVo.orgnlFileNm} Download" ><span class="glyphicon glyphicon-file"></span></a>
                    </c:forEach>
                </c:if>
            </c:when>
            <c:when test="${bbsType eq 'view'}">
                <op:fileDownload fileList="${bbsObj.fileList}" />
            </c:when>
            <c:when test="${bbsType eq 'value'}">
                ${bbsObj.fileList}
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
                <c:choose>
                    <c:when test="${bbsConfigVo.atchFileUseYn eq 'Y'}">
                        <div class="op-fileSn">
                            <c:set var="fileLmtCnt" value="${bbsConfigVo.fileLmtCnt}" />
                            <c:set var="exts" value="${bbsConfigVo.prmsnFileExtnMttr}" />
                            <op:fileUpload view="basic" name="fileSn" count="${fileLmtCnt}" maxSize="${bbsConfigVo.wholUldSz}M" size="${bbsConfigVo.lmtFileSz}M" exts="${exts}" needDc="true" fileList="${bbsObj.fileList}" />
                        </div>
                    </c:when>
                    <c:otherwise>
                        <op:message cdId="bbs.notAllow"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </c:if>
