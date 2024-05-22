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
<title>코드검색</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/code/js/code.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
<op:excelDown excelKey="code" searchAt="Y" btnId="excelDown" />
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectSearchCodeList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchVal" class="">코드명</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" style="width: 80px;" />
                    </div>
                    <div class="form-group">
                        <label for="q_upCdId" class="">상위코드</label>
                        <input type="text" name="q_upCdId" id="q_upCdId" value="${paramMap.q_upCdId}" class="form-control" style="width: 80px;" />
                    </div>
                    <div class="form-group">
                        <label for="q_hghrkCdId" class="">최상위코드</label>
                        <select name="q_hghrkCdId" id="q_hghrkCdId" class="select-search" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${baseList}" var="hghrkCdIdVo" varStatus="vst">
                                <option value="${hghrkCdIdVo.hghrkCdId}" <c:if test="${hghrkCdIdVo.hghrkCdId eq paramMap.q_hghrkCdId}">selected="selected"</c:if>>${hghrkCdIdVo.cdNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_useYn" class="">사용여부</label>
                        <select name="q_useYn" id="q_useYn" class="select" style="width: 100px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <option value="Y" <c:if test="${'Y' eq paramMap.q_useYn}">selected="selected"</c:if>>사용</option>
                            <option value="N" <c:if test="${'N' eq paramMap.q_useYn}">selected="selected"</c:if>>미사용</option>
                        </select>
                    </div>
                    <%--
                    <div class="form-group">
                        <label for="q_pbadmsStdCdYn" class="">표준여부</label>
                        <select name="q_pbadmsStdCdYn" id="q_pbadmsStdCdYn" class="select" style="width: 100px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <option value="Y" <c:if test="${'Y' eq paramMap.q_pbadmsStdCdYn}">selected="selected"</c:if>>표준</option>
                            <option value="N" <c:if test="${'N' eq paramMap.q_pbadmsStdCdYn}">selected="selected"</c:if>>비표준</option>
                        </select>
                    </div>
                    --%>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                    <button type="button" class="btn btn-info" id="excelDown">엑셀출력</button>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="코드 목록으로 순번, 코드,코드명,상위코드,상위코드명,코드설명,사용여부,등록자,등록일시,수정자,수정일시 정보를 제공합니다.">
            <caption class="hidden">코드이력 코드선택이력 목록</caption>
            <colgroup>
                <col width="5%" />
                <col width="8%" />
                <col width="10%" />
                <col width="8%" />
                <col width="10%" />
                <col width="" />
                <col width="8%" />
                <col width="8%" />
                <col width="10%" />
                <col width="8%" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>코드</th>
                    <th>코드명</th>
                    <th>상위코드</th>
                    <th>상위코드명</th>
                    <th>코드설명</th>
                    <th>사용여부</th>
                    <th>등록자</th>
                    <th>등록일시</th>
                    <th>수정자</th>
                    <th>수정일시</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dataList}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${status.count}</td>
                        <td>${dataVo.cdId}</td>
                        <td>${dataVo.cdNm}</td>
                        <td>${dataVo.upCdId}</td>
                        <td>${dataVo.upCdIdNm}</td>
                        <td>${dataVo.cdExpln}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'Y'}">
                                    <span class="text-info">사용</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-danger">미사용</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                        </td>
                        <td class="text-center">${dataVo.updusrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd" />
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${dataList}" colspan="11" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
</body>
</html>