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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>자원파일 정보</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>
    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="자원파일 정보입니다.">
            <caption class="hidden">자원파일 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <tbody>
                <tr>
                    <th>원본파일명</th>
                    <td>
                        ${dataVo.orgnlFileNm}
                        <a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${dataVo.dataSn}&amp;chgCycl=${dataVo.chgCycl}&amp;fileSn=${dataVo.fileSn}" title="${dataVo.orgnlFileNm}" class="btn btn-xs btn-info">다운로드</a>
                    </td>
                </tr>
                <tr>
                    <th>서버파일명</th>
                    <td>${dataVo.srvrFileNm}</td>
                </tr>
                <tr>
                    <th>파일사이즈</th>
                    <td>${dataVo.fileSzNm}</td>
                </tr>
                <tr>
                    <th>파일설명</th>
                    <td>
                        <op:nrToBr content="${dataVo.fileExpln}" />
                    </td>
                </tr>
<c:choose>
    <c:when test="${dataVo.fileTypeNm eq 'image/jpeg'}">
                <tr>
                    <th>원본 이미지</th>
                    <td>
                        <code>&lt;img alt="${dataVo.fileExpln}" src="${dataVo.fileUrlAddr}" /&gt;</code>
                        <br /> <img src="${dataVo.fileUrlAddr}" alt="${dataVo.fileExpln}" />
                    </td>
                </tr>
                <tr>
                    <th>썸네일1 이미지</th>
                    <td>
                        <code>&lt;img alt="${dataVo.fileExpln}" src="${dataVo.thmbPathNm1}" /&gt;</code>
                        <br /> <img src="${dataVo.thmbPathNm1}" alt="${dataVo.fileExpln}" />
                    </td>
                </tr>
                <tr>
                    <th>썸네일2 이미지</th>
                    <td>
                        <code>&lt;img alt="${dataVo.fileExpln}" src="${dataVo.thmbPathNm2}" /&gt;</code>
                        <br /> <img src="${dataVo.thmbPathNm2}" alt="${dataVo.fileExpln}" />
                    </td>
                </tr>
                <tr>
                    <th>썸네일3 이미지</th>
                    <td>
                        <code>&lt;img alt="${dataVo.fileExpln}" src="${dataVo.thmbPathNm3}" /&gt;</code>
                        <br /> <img src="${dataVo.thmbPathNm3}" alt="${dataVo.fileExpln}" />
                    </td>
                </tr>
    </c:when>
    <c:when test="${dataVo.fileExtnNm eq 'css'}">
                <tr>
                    <th>CSS 파일</th>
                    <td>
                        <code>&lt;link rel="stylesheet" type="text/css" href="${dataVo.fileUrlAddr}" /&gt;</code>
                    </td>
                </tr>
    </c:when>
    <c:when test="${dataVo.fileExtnNm eq 'js'}">
                <tr>
                    <th>JS 파일</th>
                    <td>
                        <code>&lt;script type="text/javascript" src="${dataVo.fileUrlAddr}"&gt;&lt;/script&gt;</code>
                    </td>
                </tr>
    </c:when>
    <c:otherwise>
                <tr>
                    <th>기타 파일</th>
                    <td>
                        <c:choose>
                            <c:when test="${empty dataVo.fileExpln}">
                                <c:set var="title" value="${dataVo.fileExpln}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="title" value="${dataVo.orgnlFileNm}" />
                            </c:otherwise>
                        </c:choose>
                        <code>&lt;a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${dataVo.dataSn}&amp;chgCycl=${dataVo.chgCycl}&amp;fileSn=${dataVo.fileSn}" title="${title} 파일받기"&gt;다운로드&lt;/a&gt;</code>
                    </td>
                </tr>
    </c:otherwise>
</c:choose>
                <tr>
                    <th>등록자ID</th>
                    <td>${dataVo.rgtrId}</td>
                </tr>
                <tr>
                    <th>등록자명</th>
                    <td>${dataVo.rgtrNm}</td>
                </tr>
                <tr>
                    <th>등록일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>