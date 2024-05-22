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
<title>일정 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/schdul/js/schdul.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="post" action="BD_selectSchdulList.do">
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />
    </form>
    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="일정 정보입니다.">
            <caption class="hidden">일정 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td>${dataVo.ttl}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><op:nrToBr content="${dataVo.docContsCn}"/></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td><op:fileDownload fileList="${dataVo.fileList}" /></td>
                </tr>
                <tr>
                    <th>구분명</th>
                    <td>${dataVo.seNm}</td>
                </tr>
                <tr>
                    <th>일정구분명</th>
                    <td>${dataVo.schdulSeNm}</td>
                </tr>
                <tr>
                    <th>휴일구분명</th>
                    <td>${dataVo.restdeSeNm}</td>
                </tr>
                <tr>
                    <th>장소명</th>
                    <td>${dataVo.placeNm}</td>
                </tr>
                <tr>
                    <th>일시</th>
                    <td>
                            ${dataVo.bgngYmd} ~
                            <c:choose>
                                <c:when test="${dataVo.rpttYn eq 'Y' and empty dataVo.endYmd}">반복종료일</c:when>
                                <c:otherwise>${dataVo.endYmd}</c:otherwise>
                            </c:choose>

                            <div>
                                <c:choose>
                                    <c:when test="${dataVo.rpttYn eq 'Y'}">
                                        <c:if test="${not empty dataVo.rpttEndYmd}">
                                            <div class="text-info">반복종료일 : ${dataVo.rpttEndYmd}</div>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${dataVo.rpttTypeCd eq 'Y'}">
                                                <strong>매년</strong>
                                                <code>${fn:substring(dataVo.bgngYmd, 5, 7)}월 ${fn:substring(dataVo.bgngYmd, 8, -1)}일~${fn:substring(dataVo.endYmd, 5, 7)}월 ${fn:substring(dataVo.endYmd, 8, -1)}일</code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'M'}">
                                                <strong>매월</strong>
                                                <code>${fn:substring(dataVo.bgngYmd, 8, -1)}일~${fn:substring(dataVo.endYmd, 8, -1)}일</code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'W'}">
                                                <strong>매주</strong>
                                                <code>
                                                    <c:if test="${dataVo.sndayRsvtNo eq 0}">일 </c:if>
                                                    <c:if test="${dataVo.mndayRsvtNo eq 2}">월 </c:if>
                                                    <c:if test="${dataVo.tsdayRsvtNo eq 3}">화 </c:if>
                                                    <c:if test="${dataVo.wddayRsvtNo eq 4}">수 </c:if>
                                                    <c:if test="${dataVo.trdayRsvtNo eq 5}">목 </c:if>
                                                    <c:if test="${dataVo.frdayRsvtNo eq 6}">금 </c:if>
                                                    <c:if test="${dataVo.stdayRsvtNo eq 7}">토 </c:if>
                                                </code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'D'}">
                                                <strong>매일</strong>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <strong>매일</strong>
                                    </c:otherwise>
                                </c:choose>
                                반복
                            </div>
                    </td>
                </tr>
                <tr>
                    <th>공개여부</th>
                    <td>
                        <c:choose>
                            <c:when test="${dataVo.rlsYn eq 'Y'}">예</c:when>
                            <c:otherwise><span class="text-danger">아니오</span></c:otherwise>
                        </c:choose>
                        
                    </td>
                </tr>
                <tr>
                    <th>등록정보</th>
                    <td><strong>${dataVo.rgtrNm} (${dataVo.rgtrId}) <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></td>
                </tr>
                <tr>
                    <th>수정정보</th>
                    <td><strong>${dataVo.updusrNm} (${dataVo.mdfrId}) <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong></td>
                </tr>
            </tbody>
        </table>
    </div>

</body>
</html>