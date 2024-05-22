<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>${__mdk}</title>

<op:jsTag type="libs" items="jquery" />

<script type="text/javascript">
	if(confirm("${__mdk}")) {
		<c:if test="${empty url}">
		alert("이동을 위한 URL 정보가 없습니다.");
		</c:if>
		document.location.href = "${url}";
	} else {
		history.back();
	}
</script>
</head>

<body>

</body>
</html>