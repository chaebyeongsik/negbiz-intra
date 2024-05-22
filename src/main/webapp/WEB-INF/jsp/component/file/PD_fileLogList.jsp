<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>파일 다운로드 이력</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    var opMovePage = function(cpage) {
        $("#q_currPage").val(cpage);
        dataForm.submit();
    };
</script>

</head>

<body>

    <!-- 검색 -->
    <form name="dataForm" id="dataForm" method="get" action="/component/file/PD_fileLogList.do">
        <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
        <op:pagerParam title="파일다운로드 이력" />
        <input type="hidden" name="q_fileSn" value="${paramMap.q_fileSn}" />
        <input type="hidden" name="q_fileId" value="${paramMap.q_fileId}" />
        <br />
        <div class="block table-responsive">
            <table class="table table-bordered table-striped table-hover op-table-list">
                <colgroup>
                    <col width="15%" />
                    <col width="25%" />
                    <col width="" />
                </colgroup>
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>다운로더</th>
                        <th>다운로드 일시</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="${pager.indexNo}" />
                    <c:forEach items="${pager.list}" var="fileLogVo" varStatus="status">
                        <tr>
                            <td class="text-right">${index-status.index}</td>
                            <td>${fileLogVo.oprtrNm}</td>
                            <td>${fileLogVo.regDt}</td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${pager}" colspan="3" />
                </tbody>
            </table>
        </div>

        <op:pager pager="${pager}" />
    </form>
</body>
</html>