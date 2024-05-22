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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자메뉴만족도 관리</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            opMovePage(1);
            return false;
        });
    });

    /** 페이징 */
    var opMovePage = function(currPage) {
        $("#q_currPage").val(currPage);
        var url = "<c:url value="INC_selectCmsEvlList.do"/>?" + opSearchQueryString();
        var selectedIndex = $tabs.currentIndex();

        $tabs.changeUrl(selectedIndex, url);
        $tabs.loadTab(selectedIndex);
    };

    //]]>
</script>
</head>

<div class="block text-center">
    <form name="dataForm" id="dataForm" method="get" action="INC_selectCmsEvlList.do" class="form-inline">
        <fieldset>
            <legend class="sr-only">목록검색조건</legend>
            <div class="block">
                <div class="form-group">
                    <label for="q_siteSn" class="control-label">도메인선택</label>
                    <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                        <option value=""><op:message cdId="common.selectOption" /></option>
                        <c:forEach items="${domainList}" var="domain" varStatus="vst">
                            <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                        </c:forEach>
                    </select>
                    &nbsp;
                    <label for="q_menuNm" class="">메뉴경로명</label>&nbsp;
                    <input type="text" name="q_menuNm" id="q_menuNm" value="${paramMap.q_menuNm}" class="form-control" placeholder="검색어를 입력하세요." />
                </div>

                <button type="submit" class="btn btn-info">검색</button>
                <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
            </div>
            <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
            <op:pagerParam title="사용자메뉴만족도 목록" ignores="" />
        </fieldset>
    </form>
</div>

<div class="block table-responsive">
    <!-- 리스트 -->
    <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자메뉴레이아웃  목록으로 순번,도메인명,메뉴경로,만족도점수,만족도응답자수,권한/담당자 정보를 제공합니다.">
        <caption class="hidden">사용자메뉴만족도 목록</caption>
        <colgroup>
            <col width="7%" />
            <col width="15%" />
            <col width="" />
            <col width="15%" />
            <col width="10%" />
            <col width="15%" />
        </colgroup>
        <thead>
            <tr>
                <th>순번</th>
                <th>도메인명</th>
                <th>메뉴경로</th>
                <th>만족도점수</th>
                <th>만족도응답수</th>
                <th>권한/담당자</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="index" value="${pager.indexNo}" />
            <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                <tr>
                    <td class="text-center">${index-status.index}</td>
                    <td class="text-center">${dataVo.siteExpln}</td>
                    <td>${dataVo.menuPathNm}</td>
                    <td class="text-center">평균 <fmt:formatNumber value="${dataVo.allAvg}" type="percent" pattern="###.#" />점 / 총 ${dataVo.sumScr}점</td>
                    <td class="text-center">${dataVo.wholRspnsCnt}명</td>
                    <td class="text-center">
                        <op:cdId hghrkCdId="CMS" cdId="${dataVo.authrtGroupNm}" />
                        <br />
                        <c:choose>
                            <c:when test="${dataVo.authrtGroupNm eq 'IN_DEPT'}">${dataVo.authorDeptNm} 하위부서</c:when>
                            <c:when test="${dataVo.authrtGroupNm eq 'EQ_DEPT'}">${dataVo.authorDeptNm}</c:when>
                            <c:when test="${dataVo.authrtGroupNm eq 'CHARGER'}">
                                <c:forEach items="${dataVo.authorChargerList}" var="authorCharger">
                                    ${authorCharger.deptNm} / ${authorCharger.authorPicNm}
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <op:no-data obj="${pager}" colspan="6" />
        </tbody>
    </table>
    <!-- //리스트 -->
</div>

<!-- 페이징 -->
<op:pager pager="${pager}" />
<!-- //페이징 -->
</body>