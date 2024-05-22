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
<title>배너 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/banner/js/banner.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });

    /**
     * 상세
     */
    var opView = function(regSn) {
        $("#q_regSn").val(regSn);
        location.href = "BD_updateBannerForm.do?" + opSearchQueryString("dataForm");
    };

    var listDateAuto = function(obj){
        var stDate = $("#q_startDt").val();
        if(stDate.length > 10){
            return;
        }
        if(stDate.length == 4){
            $("#q_startDt").val(stDate+"-");
        }else if(stDate.length == 7){
            $("#q_startDt").val(stDate+"-");
        }
    }

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectBannerList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_regSn" id="q_regSn" value="" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_siteSn" class="control-label"> 사이트</label>
                        <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_bnnTypeNm" class="control-label"> 배너유형</label>
                        <op:cdId type="select" hghrkCdId="banner" cdId="bnnTypeNm" id="q_bnnTypeNm" values="${paramMap.q_bnnTypeNm}" etc=" style=\"width: 150px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class=""> 제목</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <div class="form-group">
                        <label for="q_startDt" class=""> 시작일</label>
                        <input type="text" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" maxlength="10" onkeydown="listDateAuto(this);" class="datepicker form-control" placeholder="시작일" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="배너 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="배너 목록으로 순번,사이트,이미지,특성,상태 정보를 제공합니다.">
            <caption class="hidden">배너 목록</caption>
            <colgroup>
                <col width="8%" />
                <col width="15%" />
                <col width="15%" />
                <col width="" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>사이트</th>
                    <th>이미지</th>
                    <th>특성</th>
                    <th>상태</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <div class="callout callout-success">
                                <c:forEach items="${dataVo.domnList}" var="domnVo">
                                    <strong>${domnVo.siteExpln}<br /></strong>
                                </c:forEach>
                            </div>
                        </td>
                        <td>
                            <c:if test="${not empty dataVo.filePathNm}">
                                <div class="thumbnail thumbnail-boxed">
                                    <img alt="${dataVo.imgAltrtvNm}" src="${dataVo.filePathNm}">
                                </div>
                            </c:if>
                        </td>
                        <td>
                            <div class="callout callout-info">
                                <a href="BD_updateBannerForm.do?q_regSn=${dataVo.regSn}" onclick="opView('${dataVo.regSn}');return false;">
                                    <strong>종류 : </strong><code><op:cdId hghrkCdId="banner" cdId="${dataVo.bnnTypeNm}" /></code><br />
                                    <strong>제목 : </strong>${dataVo.ttl}<br />
                                    <strong>표시기간 : </strong>${dataVo.bgngYmd} ~ ${dataVo.endYmd}
                                </a>
                            </div>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'N'}">
                                    <span class="label label-danger">미사용</span>
                                </c:when>
                                <c:when test="${dataVo.statusEnd > 0}">
                                    <span class="label label-danger">종료</span>
                                </c:when>
                                <c:when test="${dataVo.statusBegin < 0}">
                                    <span class="label label-default">대기</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-success">진행중</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="5" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <op:auth needAuthType="MANAGER">
        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn-info" onclick="opUpdateSortOrdrList();">정렬순서변경</button>
                    <button type="button" class="btn btn-success" onclick="opInsertForm('BD_insertBannerForm.do');">등록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </op:auth>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>