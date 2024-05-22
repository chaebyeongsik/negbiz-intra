<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>요청 URL로 이동합니다.</title>

<op:jsTag type="libs" items="jquery" />

<script type="text/javascript">
    <c:if test="${empty action}">
        alert("이동을 위한 URL 정보가 없습니다.");
        history.back();
    </c:if>
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#paramListForm").submit();
    });
</script>
</head>

<body>
    <form id="paramListForm" name="paramListForm" method="post" action="${action}">
        <c:forEach items="${postParamList}" var="postParam">
            <textarea name="${postParam.name}" rows="0" cols="0" style="display: none;">${postParam.value}</textarea>
        </c:forEach>
        <input type="submit" value="submitParam" style="display: none;" />
    </form>
</body>

</html>