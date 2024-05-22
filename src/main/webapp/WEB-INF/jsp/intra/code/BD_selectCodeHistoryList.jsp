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

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>코드이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/codeHistory/js/codeHistory.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectCodeHistoryList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_regSn" id="q_regSn" value="" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="셈플게시물 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="코드이력 코드선택이력  목록으로 순번,로그일시,로그유형,코드,코드명,상위코드,상위코드명,다국어코드,다국어코드명,코드설명,사용여부,수정자ID,수정자명,수정일시, 정보를 제공합니다.">
            <caption class="hidden">코드이력 코드선택이력 목록</caption>
            <colgroup>
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>로그일시</th>
                    <th>로그유형</th>
                    <th>코드</th>
                    <th>코드명</th>
                    <th>상위코드</th>
                    <th>상위코드명</th>
                    <th>다국어코드</th>
                    <th>다국어코드명</th>
                    <th>코드설명</th>
                    <th>사용여부</th>
                    <th>수정자ID</th>
                    <th>수정자명</th>
                    <th>수정일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>${dataVo.logDt}</td>
                        <td>${dataVo.logType}</td>
                        <td>
                            <a href="BD_selectCodeHistory.do?q_logSn=${dataVo.logSn}&amp;q_hghrkCdId=${dataVo.hghrkCdId}&amp;q_cdId=${dataVo.cdId}">${dataVo.cdId}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeHistory.do?q_logSn=${dataVo.logSn}&amp;q_hghrkCdId=${dataVo.hghrkCdId}&amp;q_cdId=${dataVo.cdId}">${dataVo.cdNm}</a>
                        </td>
                        <td>${dataVo.upCdId}</td>
                        <td>${dataVo.upCdIdNm}</td>
                        <td>${dataVo.mtlngCdNm}</td>
                        <td>${dataVo.multiLangCodeNm}</td>
                        <td>${dataVo.cdExpln}</td>
                        <td>${dataVo.useYn}</td>
                        <td>${dataVo.mdfrId}</td>
                        <td>${dataVo.updusrNm}</td>
                        <td>${dataVo.updtDt}</td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="14" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>