<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>담당자 목록</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    $(document).ready(function() {
    });

    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="get" action="PD_selectMngrInAuthorList.do" class="form-inline form-xs">
    <div class="block text-center">
        <input type="hidden" id="authrtCdId" name="authrtCdId" value="${param.authrtCdId}" />
        <fieldset>
            <legend class="hidden">목록검색조건</legend>
                <div class="form-group">
                    <label for="q_searchKey" class="sr-only">항목</label>
                    <select name="q_searchKey" id="q_searchKey" class="select">
                        <option value="">항목선택</option>
                        <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected=selected</c:if>>ID</option>
                        <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected=selected</c:if>>담당자명</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="q_searchVal" class="sr-only">검색어</label>
                    <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                </div>
                <button type="submit" class="btn btn-info">검색</button>
                <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
        </fieldset>
    </div>
    <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
    <op:pagerParam title="담당자 목록" ignores="" />
    </form>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">담당자 목록</h6>
        </div>
        <div class="table-responsive">
            <!-- 리스트 -->
            <table class="table" summary="담당자 목록으로 순번,담당자ID,부서명,직급명,담당자명 정보를 제공합니다.">
                <caption class="hidden">담당자 목록</caption>
                <col width="" />
                <thead>
                    <tr>
                        <th class="text-center">순번</th>
                        <th class="text-center">담당자ID</th>
                        <th class="text-center">부서명</th>
                        <th class="text-center">직급명</th>
                        <th class="text-center">담당자명</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="${pager.indexNo}" />
                    <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                        <tr>
                            <td class="text-center">${index-status.index}</td>
                            <td class="text-center">
                                ${dataVo.picId}
                            </td>
                            <td class="text-center">
                                ${dataVo.deptNm}
                            </td>
                            <td class="text-center">
                                <op:cdId hghrkCdId="ofcps" cdId="${dataVo.jbgdCdId}" />
                            </td>
                            <td class="text-center">
                                ${dataVo.picNm}
                            </td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${pager}" colspan="5" />
                </tbody>
            </table>
            <!-- //리스트 -->
        </div>
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>