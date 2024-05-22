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
<title>사용자메뉴컨텐츠 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });

    /*
     * 텝 클릭시 화면이동
     */
    var opCmsManageTabList = function(url, aprvSttsNo) {
        $("#q_aprvSttsNo").val(aprvSttsNo);
        opList(url);
    };

    /** 화면비교 폼 팝업 */
    var opCompareForm = function(siteSn, userMenuEngNm, contsSn) {

        var queryString = "q_siteSn=" + siteSn + "&q_userMenuEngNm=" + userMenuEngNm + "&q_contsSn=" + contsSn;

        var url = "PD_selectCmsManageSource.do?" + queryString;
        var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
        option += "resizable=yes, scrollbars=yes, location=yes, status=yes, menubar=0, toolbar=0";
        var cmsCompareWin = window.open(url, "cmsCompareWin", option);
        cmsCompareWin.focus();
    };

    //]]>
</script>
</head>
<body>
    <div class="text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectCmsManageList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_aprvSttsNo" id="q_aprvSttsNo" value="${paramMap.q_aprvSttsNo}" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchDomnCode" class="sr-only">사이트 선택</label>
                        <select name="q_searchDomnCode" id="q_searchDomnCode" class="select" style="width: 180px;">
                            <option value="">-- 전체 사이트 --</option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${paramMap.q_searchDomnCode eq domain.siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchKey" class="sr-only">검색유형</label>
                        <select name="q_searchKey" id="q_searchKey" class="select" style="width: 120px;">
                            <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>메뉴명</option>
                            <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected="selected"</c:if>>요청자</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>

                <div class="tabbable block">
                    <ul class="nav nav-tabs nav-justified">
                        <li <c:if test="${empty paramMap.q_aprvSttsNo or paramMap.q_aprvSttsNo eq 'C1020'}">class="active"</c:if>><a href="BD_selectCmsManageList.do" onclick="opCmsManageTabList('BD_selectCmsManageList.do', 'C1020'); return false;">발행요청</a></li>
                        <li <c:if test="${paramMap.q_aprvSttsNo eq 'C1030'}">class="active"</c:if>><a href="BD_selectCmsManageList.do" onclick="opCmsManageTabList('BD_selectCmsManageList.do', 'C1030'); return false;">발행반려</a></li>
                        <li <c:if test="${paramMap.q_aprvSttsNo eq 'C1040'}">class="active"</c:if>><a href="BD_selectCmsManageList.do" onclick="opCmsManageTabList('BD_selectCmsManageList.do', 'C1040'); return false;">발행승인</a></li>
                        <li <c:if test="${paramMap.q_aprvSttsNo eq 'C1050'}">class="active"</c:if>><a href="BD_selectCmsManageList.do" onclick="opCmsManageTabList('BD_selectCmsManageList.do', 'C1050'); return false;">자동승인</a></li>
                        <li <c:if test="${paramMap.q_aprvSttsNo eq 'C0000'}">class="active"</c:if>><a href="BD_selectCmsManageList.do" onclick="opCmsManageTabList('BD_selectCmsManageList.do', 'C0000'); return false;">전체(작성중 포함)</a></li>
                    </ul>
                </div>

                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="컨텐츠발행요청 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자메뉴 사용자메뉴관리 목록으로 순번,사이트명,사용자메뉴경로,발행사유,버전,승인상태,요청자명,요청일시 정보를 제공합니다.">
            <caption class="hidden">사용자메뉴컨텐츠관리 목록</caption>
            <colgroup>
                <col width="60px" />
                <col width="150px" />
                <col width="250px" />
                <col width="" />
                <col width="60px" />
                <col width="80px" />
                <col width="80px" />
                <col width="90px" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>사이트명</th>
                    <th>사용자메뉴경로</th>
                    <th>발행사유</th>
                    <th>버전</th>
                    <th>승인상태</th>
                    <th>요청자명</th>
                    <th>요청일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>${dataVo.siteExpln}</td>
                        <td>
                            <a href="PD_selectCmsManageSource.do?q_siteSn=${dataVo.siteSn}&amp;q_userMenuEngNm=${dataVo.userMenuEngNm}&amp;q_contsSn=${dataVo.contsSn}" onclick="opCompareForm('${dataVo.siteSn}','${dataVo.userMenuEngNm}','${dataVo.contsSn}');return false;">${dataVo.menuPathNm}</a>
                        </td>
                        <td>
                            <op:nrToBr content="${dataVo.pblcnRsn}" />
                        </td>
                        <td>
                            <a href="PD_selectCmsManageSource.do?q_siteSn=${dataVo.siteSn}&amp;q_userMenuEngNm=${dataVo.userMenuEngNm}&amp;q_contsSn=${dataVo.contsSn}" onclick="opCompareForm('${dataVo.siteSn}','${dataVo.userMenuEngNm}','${dataVo.contsSn}');return false;"> <c:choose>
                                    <c:when test="${dataVo.aplcnYn eq 'Y'}">
                                        <span class="label label-success"># ${dataVo.contsSn}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-default"># ${dataVo.contsSn}</span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </td>
                        <td class="text-center">
                            <span class="label label-default">${dataVo.confmSttusNm}</span>
                        </td>
                        <td class="text-center">${dataVo.updusrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd" />
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="10" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>