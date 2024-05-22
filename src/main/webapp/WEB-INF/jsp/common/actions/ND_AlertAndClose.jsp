<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>${__mdk}</title>

<op:jsTag type="libs" items="jquery" />
<script type="text/javascript" src="/resources/openworks4/js/openworks.global.js"></script>

<script type="text/javascript">
    <c:if test="${not empty __mdk}">
    alert("${__mdk}");
    </c:if>
    if (opCloseWin) {
        opCloseWin();
    }
</script>
</head>

<body>

</body>

</html>