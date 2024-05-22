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
<script type="text/javascript" src="/resources/intra/cms/js/cms.common.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    /** 화면비교 폼 팝업 */
    var opCompareForm = function() {
        // 체크 박스 변경사항을 반영
        $.uniform.update();

        var siteSn = $("#q_siteSn").val();
        var userMenuEngNm = $("#q_userMenuEngNm").val();
        var leftSn = $("input[name=compare1]:checked").val();
        var rightSn = $("input[name=compare2]:checked").val();

        if(!leftSn || !rightSn) {
            opWarningMsg("비교할 두개의 목록을 각각 선택해야 합니다.");
            return false;
        }
        if(leftSn == rightSn) {
            opWarningMsg("동일버전은 비교할 수 없습니다.");
            return false;
        }
        if(new Number(leftSn) > new Number(rightSn)) {
            opWarningMsg("좌측버전이 더 낮아야 합니다.");
            return false;
        }

        var queryString = "q_siteSn=" + siteSn + "&q_userMenuEngNm=" + userMenuEngNm + "&q_leftCntntsSn=" + leftSn + "&q_rightCntntsSn=" + rightSn;

        var url = "<c:url value="/intra/cms/cntnts/PD_compareSourceCntnts.do"/>?" + queryString;
        var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, ";
        option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
        var cmsCompareWin = window.open(url, "cmsCompareWin", option);
        cmsCompareWin.focus();
    };

    /** 페이징 */
    var opMovePage = function(cpage) {
        var url = "<c:url value="INC_selectCmsCntntsList.do"/>?" + opSearchQueryString(form);
        var selectedIndex = tabs.currentIndex();

        $tabs.changeUrl(selectedIndex, url);
        $tabs.loadTab(selectedIndex);
    };

    //]]>
</script>
</head>
<body>
    <div class="block"></div>
    <div class="text-center">
        <form name="dataForm" id="dataForm" method="get" action="INC_selectCmsCntntsList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam view="view" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="사용자메뉴컨텐츠  목록으로 순번,레이아웃명,적용여부,승인상태,발행사유,등록자명,등록일시 정보를 제공합니다.">
            <caption class="hidden">사용자메뉴컨텐츠 목록</caption>
            <colgroup>
                <col width="80px" />
                <col width="80px" />
                <col width="100px" />
                <col width="" />
                <col width="10%" />
                <col width="10%" />
                <col width="12%" />
            </colgroup>
            <thead>
                <tr>
                    <th>비교1</th>
                    <th>비교2</th>
                    <th>버전</th>
                    <th>발행사유</th>
                    <th>승인상태</th>
                    <th>등록자명</th>
                    <th>등록일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">
                            <label for="compare1${dataVo.contsSn}" class="radio-inline radio-primary">
                                <input type="radio" name="compare1" id="compare1${dataVo.contsSn}" value="${dataVo.contsSn}" class="styled" />
                            </label>
                        </td>
                        <td class="text-center">
                            <label for="compare2${dataVo.contsSn}" class="radio-inline radio-primary">
                                <input type="radio" name="compare2" id="compare2${dataVo.contsSn}" value="${dataVo.contsSn}" class="styled" />
                            </label>
                        </td>
                        <td class="text-center">
                            <a
                                href="<c:url value="/intra/cms/cntnts/PD_updateCmsCntntsForm.do"/>?q_siteSn=${dataVo.siteSn}&amp;q_userMenuEngNm=${dataVo.userMenuEngNm}&amp;q_contsSn=${dataVo.contsSn}"
                                onclick="cntntsEdit('${dataVo.siteSn}','${dataVo.userMenuEngNm}','${dataVo.contsSn}');return false;"
                            > <c:choose>
                                    <c:when test="${dataVo.aplcnYn eq 'Y'}">
                                        <span class="label label-success">적용버전 # ${dataVo.contsSn}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-default"># ${dataVo.contsSn}</span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </td>
                        <td>
                            <op:nrToBr content="${dataVo.pblcnRsn}" />
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.aprvSttsNo eq 'C1040' or dataVo.aprvSttsNo eq 'C1050'}">
                                    <span class="label label-info">${dataVo.confmSttusNm}</span>
                                </c:when>
                                <c:when test="${dataVo.aprvSttsNo eq 'C1030'}">
                                    <span class="label label-danger">${dataVo.confmSttusNm}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-default">${dataVo.confmSttusNm}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="7" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row block">
        <div class="col-sm-12 btn-group">
            <c:if test="${pager.totalNum > 1}">
                <div class="pull-left">
                    <button type="button" class="btn btn-info" onclick="opCompareForm();">소스비교</button>
                </div>
            </c:if>
            <c:if test="${pager.totalNum < 1}">
                <div class="pull-right">
                    <button type="button" class="btn btn-success" onclick="cntntsEdit('${paramMap.q_siteSn}','${paramMap.q_userMenuEngNm}','');">신규등록</button>
                </div>
            </c:if>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>