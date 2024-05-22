<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>${__mdk}</title>

<op:jsTag type="libs" items="jquery" />

<script type="text/javascript">
	<c:if test="${not empty __mdk}">
		alert("${__mdk}");
	</c:if>
	<c:if test="${not empty url}">
		self.location.href = "${url}";
	</c:if>
	<c:if test="${empty url}">
		alert("이동을 위한 URL 정보가 없습니다.");
	</c:if>
</script>
</head>

<body>

</body>

</html>