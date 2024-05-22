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
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>권한별 담당자</title>

<op:jsTag type="intra" items="ui, opform" />

<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function() {

        // check 박스 선택 값 설정
        var paramCode = "${paramMap.q_authrtCdId}";
        opChecked("q_author", paramCode);

        $("#dataForm").submit(function() {

            var authrtCdIds = "";
            $("input[name=q_author]:checked").each(function(i) {
                authrtCdIds += (authrtCdIds != "" ? "," : "") + $(this).val();
            });
            $("#dataForm input[name=q_authrtCdId]").val(authrtCdIds);

            return;
        });

    });
    //]]>
</script>
</head>
<body>

<div class="panel panel-default">

    <div class="panel-body">
        <div class="block">
            <form name="dataForm" id="dataForm" method="get" action="PD_selectAuthorMngrList.do" class="form-inline">
                <input type="hidden" id="q_authrtCdId" name="q_authrtCdId" value="" />
                <div class="block text-center">
                    <div class="form-inline" id="authrtCdId">
                        <div class="block-inner">
                        <c:forEach items="${dataList}" var="authorList" varStatus="status">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="q_author" id="q_author${status.index}" value="${authorList.authrtCdId}" class="styled" />
                                ${authorList.authrtNm}
                            </label>
                        </c:forEach>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="q_searchKey" class="sr-only">항목</label>
                        <select name="q_searchKey" id="q_searchKey" class="select" style="width: 120px;">
                            <option value="">전체</option>
                            <option value="picId" <c:if test="${paramMap.q_searchKey eq 'picId'}">selected="selected"</c:if>>ID</option>
                            <option value="picNm" <c:if test="${paramMap.q_searchKey eq 'picNm'}">selected="selected"</c:if>>담당자명</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
                <op:pagerParam title="권한별 담당자 목록" ignores="" />
            </form>
        </div>

        <div class="block table-responsive">
            <!-- 리스트 -->
            <table id="authorTab" class="table table-bordered table-striped table-hover op-table-list"
                summary="권한별담당자 목록으로 번호,ID,부서명,직급,담당자명,권한그룹,사용여부 정보를 제공합니다.">
                <caption class="hidden">담당자권한할당 권한 목록</caption>
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
                        <th>번호</th>
                        <th>ID</th>
                        <th>부서명</th>
                        <th>직급</th>
                        <th>담당자명</th>
                        <th>권한그룹</th>
                        <th>사용여부</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="${pager.indexNo}" />
                    <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                        <tr>
                            <td class="text-center">${index-status.index}</td>
                            <td class="text-center">${dataVo.picId}</td>
                            <td class="text-center">${dataVo.deptNm}</td>
                            <td class="text-center">${dataVo.clsfNm}</td>
                            <td class="text-center">${dataVo.picNm}</td>
                            <td class="text-center">${fn:join(dataVo.authrtCdIdArray, ', ')}</td>
                            <td class="text-center">
                                <c:if test="${dataVo.useYn eq 'Y'}"><span>사용</span></c:if>
                                <c:if test="${dataVo.useYn eq 'N'}"><span>미사용</span></c:if>
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
    </div>

</div>

</body>
</html>