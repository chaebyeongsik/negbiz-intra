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
<title>담당자 검색</title>

<op:jsTag type="intra" items="ui, opform" />
<op:jsTag type="libs" items="form" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/mngr/js/mngr.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("q_searchKey", "${paramMap.q_searchKey}");

        $(".updateForm").each(function () {
            var picId = $(this).attr("id");
            $(this).opmodal({
                width : 980,
                height : 500,
                href : "/intra/mngr/PD_updateMngrForm.do?q_picId=" + picId
            });
        });

        $("#dataForm").submit(function() {
            return true;
        });

        /* 담당자 접속이력 */
        $(".mngrConnectHist").opmodal({
            width : 900,
            height : 600
        });

        /* 담당자 변경이력 */
        $(".mngrChangeHist").opmodal({
            width : 900,
            height : 600
        });
    });

    //]]>
</script>
<op:excelDown excelKey="mngr" searchAt="Y" btnId="excelDown" />
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectMngrList.do" class="form-inline">

            <input type="hidden" name="q_regSn" id="q_regSn" value="" />
            <div class="block">
                <div class="form-group">
                    <label for=q_deptNm class="">부서명</label>
                    <input type="text" name="q_deptNm" id="q_deptNm" value="${paramMap.q_deptNm}" class="form-control" placeholder="부서명" />
                </div>
                <div class="form-group">
                    <label for="q_searchVal" class="">담당자</label>
                    <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="이름 또는 ID" />
                </div>
                <button type="submit" class="btn btn-info">검색</button>
                <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
            </div>
            <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
            <op:pagerParam title="담당자 검색 목록" ignores="" />
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="담당자 목록으로 순번,부서명,직급명,담당자ID,담당자명,접속이력,변경이력 정보를 제공합니다.">
            <caption class="hidden">담당자 목록</caption>
            <colgroup>
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
                    <th>부서명</th>
                    <th>직급명</th>
                    <th>담당자ID</th>
                    <th>담당자명</th>
                    <th>접속이력</th>
                    <th>변경이력</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">
                            ${dataVo.upperDeptNm} <c:if test="${not empty dataVo.upperDeptNm}">&gt;</c:if> ${dataVo.deptNm}
                        </td>
                        <td class="text-center">
                            ${dataVo.clsfNm}
                        </td>
                        <td class="text-center">
                            ${dataVo.picId}
                        </td>
                        <td class="text-center">
                            <a href="#" id="${dataVo.picId}" class="updateForm">${dataVo.picNm}</a>
                        </td>
                        <td class="text-center">
                            <a href="/intra/mngr/connect/PD_selectConnectList.do?q_picId=${dataVo.picId}" class="mngrConnectHist">${dataVo.lgnDt}</a>
                        </td>
                        <td class="text-center">
                            <a href="/intra/mngr/change/PD_selectChangeList.do?q_picId=${dataVo.picId}" class="mngrChangeHist">${dataVo.updtDt}</a>
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="7" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>