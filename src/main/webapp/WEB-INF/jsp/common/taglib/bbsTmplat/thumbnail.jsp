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
            <c:if test="${not empty bbsObj.fileList}">
                <c:set var="doneLoop" value="true" />
                <c:forEach var="fileVo" items="${bbsObj.fileList}">
                    <c:if test="${not empty fileVo.thmbPathNm and doneLoop}">
                        <c:set var="fileExpln" value="${fileVo.orgnlFileNm}" />
                        <c:if test="${not empty fileVo.fileExpln}">
                            <c:set var="fileExpln" value="${fileVo.fileExpln}" />
                        </c:if>
                        <img src="${fileVo.thmbPathNm}" alt="${fileExpln}" width="100%" />
                        <c:set var="doneLoop" value="false" />
                    </c:if>
                </c:forEach>
            </c:if>
        </c:when>
        <c:when test="${bbsType eq 'view'}">
            <c:if test="${bbsConfigVo.thmbUseYn eq 'Y' and not empty bbsThumbList}">

                <div class="connected-carousels">
                    <div class="stage">
                        <div class="carousel carousel-stage" data-jcarousel="true">
                            <ul>
                                <c:forEach var="thumbVo" items="${bbsThumbList}">
                                    <c:set var="fileExpln" value="${thumbVo.orgnlFileNm}" />
                                    <c:if test="${not empty thumbVo.fileExpln}">
                                        <c:set var="fileExpln" value="${thumbVo.fileExpln}" />
                                    </c:if>
                                    <li><img src="${thumbVo.thmbPathNm}" width="500" height="333" alt="${fileExpln}" /></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <a href="#" class="prev prev-stage inactive" data-jcarouselcontrol="true"><span>이전사진보기</span></a>
                        <a href="#" class="next next-stage" data-jcarouselcontrol="true"><span>다음사진보기</span></a>
                    </div>
                    <div class="navigation">
                        <a href="#" class="prev prev-navigation inactive" data-jcarouselcontrol="true">이전사진리스트보기</a>
                        <a href="#" class="next next-navigation" data-jcarouselcontrol="true">다음사진리스트보기</a>
                        <div class="carousel carousel-navigation" data-jcarousel="true">
                            <ul>
                                <c:forEach var="thumbVo" items="${bbsThumbList}" varStatus="thumbIdx">
                                    <c:set var="fileExpln" value="${thumbVo.orgnlFileNm}" />
                                    <c:if test="${not empty thumbVo.fileExpln}">
                                        <c:set var="fileExpln" value="${thumbVo.fileExpln}" />
                                    </c:if>
                                    <li data-jcarouselcontrol="true" <c:if test="${thumbIdx.index  < 1}"> class="active" </c:if>><img src="${thumbVo.thmbPathNm}" width="80" height="61" alt="${fileExpln}" /></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <script type="text/javascript" src="/resources/libs/jcarousel/js/jquery.jcarousel.min.js"></script>
                <script type="text/javascript" src="/resources/libs/jcarousel/js/jcarousel.connected-carousels.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/libs/jcarousel/css/jcarousel.connected-carousels.css">
            </c:if>
        </c:when>
        <c:when test="${bbsType eq 'value'}">
            ${bbsObj.fileList}
        </c:when>
        <c:when test="${bbsType eq 'text'}">
            사진
        </c:when>
        <c:when test="${bbsType eq 'label'}">
            사진
        </c:when>
        <c:when test="${bbsType eq 'desc'}">
            사진
        </c:when>
        <c:when test="${bbsType eq 'form'}">
            사진
        </c:when>
    </c:choose>
