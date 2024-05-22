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
<title>자원 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/resrce/js/resrce.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            opList();
            return false;
        });

        $(".opModal").opmodal({
            width : 1010
        });
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="INC_selectResrceList.do" class="form-inline">
            <input type="hidden" name="upCtgrySn" id="upCtgrySn" value="${paramMap.q_upCtgrySn}" />
            <input type="hidden" name="ctgrySn" id="ctgrySn" value="${paramMap.q_ctgrySn}" />

            <input type="hidden" name="q_upCtgrySn" id="q_upCtgrySn" value="${paramMap.q_upCtgrySn}" />
            <input type="hidden" name="q_ctgrySn" id="q_ctgrySn" value="${paramMap.q_ctgrySn}" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_dataTypeNo" class="control-label">유형</label>
                        <op:cdId type="select" hghrkCdId="resrce" cdId="dataTypeNo" id="q_dataTypeNo" values="${paramMap.q_dataTypeNo}" etc="style='width:120px;'" />
                    </div>
                    <div class="form-group">
                        <label for="q_dataDtlTypeNm" class="control-label">상세유형</label>
                        <op:cdId type="select" hghrkCdId="resrce" cdId="dataDtlTypeNm" id="q_dataDtlTypeNm" values="${paramMap.q_dataDtlTypeNm}" etc="style='width:120px;'" />
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="control-label">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="자원 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="자원 목록으로 순번,제목,자원유형,자원상세유형,공개여부,사용여부,등록자명,등록일시 정보를 제공합니다.">
            <caption class="hidden">자원 목록</caption>
            <colgroup>
                <col width="7%" />
                <col width="" />
                <col width="10%" />
                <col width="10%" />
                <col width="80px" />
                <col width="80px" />
                <col width="100px" />
                <col width="100px" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>제목</th>
                    <th>자원유형</th>
                    <th>상세유형</th>
                    <th>공개여부</th>
                    <th>사용여부</th>
                    <th>등록자명</th>
                    <th>등록일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <a href="PD_updateResrceForm.do?q_dataSn=${dataVo.dataSn}&amp;q_chgCycl=${dataVo.chgCycl}" class="opModal">${dataVo.ttl}</a>
                        </td>
                        <td class="text-center">
                            <op:cdId hghrkCdId="resrce" cdId="${dataVo.dataTypeNo}" />
                        </td>
                        <td class="text-center">
                            <op:cdId hghrkCdId="resrce" cdId="${dataVo.dataDtlTypeNm}" />
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.rlsYn eq 'Y'}"><span class="label label-success">공개</span></c:when>
                                <c:otherwise><span class="label label-danger">비공개</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'Y'}"><span class="label label-success">사용</span></c:when>
                                <c:otherwise><span class="label label-danger">미사용</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
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
                <button type="submit" class="btn btn-success" onclick="opInsertForm('PD_insertResrceForm.do');">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>