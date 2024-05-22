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
<title>접근제어 관리</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

        opSelected("q_useYn", "${paramMap.q_useYn}", "");
    });

    /**
     * 상세
     */
    var opView = function(regSn) {
        $("#q_regSn").val(regSn);
        location.href = "BD_updateAccesCtrlForm.do?" + opSearchQueryString("dataForm");
    };

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectAccesCtrlList.do" class="form-inline">
            <input type="hidden" id="q_regSn" name="q_regSn" value="" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_useYn" class="control-label">사용여부</label>
                        <select name="q_useYn" id="q_useYn" class="select" style="width: 120px;">
                            <option value="">-- 사용여부 --</option>
                            <option value="Y">사용</option>
                            <option value="N">미사용</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="control-label">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="접근제어  목록으로 순번,허용유형,허용ID,허용IP,사용여부,등록자명,등록일시 정보를 제공합니다.">
            <caption class="hidden">접근제어 목록</caption>
            <colgroup>
                <col width="5%" />
                <col width="10%" />
                <col width="" />
                <col width="18%" />
                <col width="10%" />
                <col width="10%" />
                <col width="12%" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>허용유형</th>
                    <th>허용ID</th>
                    <th>허용IP</th>
                    <th>사용여부</th>
                    <th>등록자명</th>
                    <th>등록일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${fn:length(dataList)}" />
                <c:forEach items="${dataList}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index - status.index}</td>
                        <td class="text-center">
                            <a onclick="opView(${dataVo.regSn});return false;" href="BD_updateAccesCtrlForm.do?q_regSn=${dataVo.regSn}">${dataVo.prmsnTypeNm}</a>
                        </td>
                        <td class="text-center">
                            <c:if test="${not empty dataVo.prmsnId}">
                                <a href="BD_updateAccesCtrlForm.do?q_regSn=${dataVo.regSn}"> ${dataVo.permChrgDeptNm} ${dataVo.permPicNm} (${dataVo.prmsnId}) </a>
                            </c:if>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${not empty dataVo.prmsnBgngIp}">
                                    <a href="BD_updateAccesCtrlForm.do?q_regSn=${dataVo.regSn}">${dataVo.prmsnBgngIp} ~ ${dataVo.prmsnEndIp}</a>
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.useYn eq 'Y'}">
                                    사용
                                </c:when>
                                <c:otherwise>
                                    미사용
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${dataList}" colspan="7" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-xs-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-success" onclick="opInsertForm('BD_insertAccesCtrlForm.do');">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>