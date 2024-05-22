<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="(주)제스아이앤씨 기술연구소" />
    <meta name="description" content="Openworks 포털관리 시스템" />

    <title><sitemesh:write property="title" /></title>

    <script type="text/javascript">var CTX_PATH="<%=request.getContextPath()%>";</script>

    <op:jsTag type="libs" items="jquery,notice,colorbox" />
    <op:jsTag type="intra" items="base" />

    <script>
       //<![CDATA[
        $().ready(function() {
        });
        //]]>
    </script>

    <sitemesh:write property="head" />
</head>
<body>

	<div id="bodyDiv">
		<div id="contentDiv">
			<sitemesh:write property="body" />
		</div>
	</div>

</body>
</html>