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
        $(".opmodal").opmodal({
            width : 1000,
            height : 600
        });
    });

    //]]>
</script>
</head>
<body>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectCmsEvlList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="row block">
                    <div class="form-group">
                        <label for="q_siteSn" class="control-label">도메인선택</label>
                        <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select> &nbsp;
                        <label for="q_menuNm" class="">메뉴경로</label>
                        &nbsp;
                        <input type="text" name="q_menuNm" id="q_menuNm" value="${paramMap.q_menuNm}" class="form-control" placeholder="검색어를 입력하세요." />
                        <button type="submit" class="btn btn-info">검색</button>
                        <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label for="q_startDt" class="">시작일</label>
                        <input type="text" class="from-date form-control" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" maxlength="10" placeholder="시작일" />
                    </div>
                    <div class="form-group">
                        <label for="q_endDt" class="">종료일</label>
                        <input type="text" class="to-date form-control" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" maxlength="10" placeholder="종료일" />
                    </div>
                    <label for="q_sortTar" class="control-label">정렬대상</label>
                    <select name="q_sortTar" id="q_sortTar" class="select" style="width: 100px;">
                        <option value=""><op:message cdId="common.selectOption" /></option>
                        <option value="1010" <c:if test="${paramMap.q_sortTar eq '1010'}">selected="selected"</c:if>>메뉴</option>
                        <option value="1020" <c:if test="${paramMap.q_sortTar eq '1020'}">selected="selected"</c:if>>점수</option>
                        <option value="1030" <c:if test="${paramMap.q_sortTar eq '1030'}">selected="selected"</c:if>>응답수</option>
                        <option value="1040" <c:if test="${paramMap.q_sortTar eq '1040'}">selected="selected"</c:if>>평균</option>
                    </select> &nbsp;
                    <label for="q_sortDir" class="control-label">정렬방법</label>
                    <select name="q_sortDir" id="q_sortDir" class="select" style="width: 100px;">
                        <option value=""><op:message cdId="common.selectOption" /></option>
                        <option value="asc" <c:if test="${paramMap.q_sortDir eq 'asc'}">selected="selected"</c:if>>오름차순</option>
                        <option value="desc" <c:if test="${paramMap.q_sortDir eq 'desc'}">selected="selected"</c:if>>내림차순</option>
                    </select>
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
                        <td class="text-center">
                            <a href="<c:url value="/intra/cms/evl/PD_selectCmsEvlList.do" />?q_siteSn=${dataVo.siteSn}&amp;q_userMenuEngNm=${dataVo.userMenuEngNm}&amp;q_startDt=${paramMap.q_startDt}&amp;q_endDt=${paramMap.q_endDt}" class="opmodal">
                                평균
                                <fmt:formatNumber value="${dataVo.allAvg}" type="percent" pattern="###.#" />
                                점 / 총 ${dataVo.sumScr}점
                            </a>
                        </td>
                        <td class="text-center">${dataVo.wholRspnsCnt}명</td>
                        <td class="text-center">
                            <op:cdId hghrkCdId="CMS" cdId="${dataVo.authrtGroupNm}" />
                            <br />
                            <c:choose>
                                <c:when test="${dataVo.authrtGroupNm eq 'IN_DEPT'}">${dataVo.authorDeptNm}</c:when>
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
</html>