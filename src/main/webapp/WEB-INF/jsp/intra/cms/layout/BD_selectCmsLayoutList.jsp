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
<title>사용자메뉴레이아웃 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.common.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

        $(".layoutPreview, .historyPreview").click(function() {
            var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
            option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
            var href = $(this).attr("href");
            var layoutPreview = window.open(href, "layoutPreview", option);
            layoutPreview.focus();
            return false;
        });
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectCmsLayoutList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_regSn" id="q_regSn" value="" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_siteSnParam" class="control-label">사이트선택</label>
                        <select name="q_siteSnParam" id="q_siteSnParam" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSnParam}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="사용자메뉴레이아웃 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자메뉴레이아웃  목록으로 순번,도메인명,레이아웃명,파일경로,등록자ID,등록자명, 미리보기 정보를 제공합니다.">
            <caption class="hidden">사용자메뉴레이아웃 목록</caption>
            <colgroup>
                <col width="7%" />
                <col width="15%" />
                <col width="15%" />
                <col width="" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>사이트</th>
                    <th>레이아웃명</th>
                    <th>파일경로</th>
                    <th>등록자</th>
                    <th>등록일</th>
                    <th>미리보기</th>
                    <th>이력보기</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">${dataVo.siteExpln}</td>
                        <td>
                            <a href="BD_updateCmsLayoutForm.do?q_siteSn=${dataVo.siteSn}&amp;q_lytCdNo=${dataVo.lytCdNo}&amp;q_siteSnParam=${paramMap.q_siteSnParam}">${dataVo.lytNm}</a>
                        </td>
                        <td>
                            <a href="BD_updateCmsLayoutForm.do?q_siteSn=${dataVo.siteSn}&amp;q_lytCdNo=${dataVo.lytCdNo}&amp;q_siteSnParam=${paramMap.q_siteSnParam}">${dataVo.filePathNm}</a>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                        </td>
                        <td class="text-center">
                            <a href="<c:url value="/intra/cms/cntnts/PV_LayoutContentPreView.do" />?q_siteSn=${dataVo.siteSn}&amp;q_lytCdNo=${dataVo.lytCdNo}" class="btn btn-xs btn-info layoutPreview">미리보기</a>
                        </td>
                        <td class="text-center">
                            <a href="PD_selectCmsLayoutHistoryList.do?q_siteSn=${dataVo.siteSn}&amp;q_lytCdNo=${dataVo.lytCdNo}" class="btn btn-xs btn-info historyPreview">이력보기</a>
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="8" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="submit" class="btn btn-success" onclick="opInsertForm('BD_insertCmsLayoutForm.do');">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>