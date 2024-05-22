<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="zesinc.core.config.Config"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<%
    request.setAttribute("deptNm",
					Config.getString("system-config.adminInfo.deptNm"));
			request.setAttribute("name",
					Config.getString("system-config.adminInfo.name"));
			request.setAttribute("telNo",
					Config.getString("system-config.adminInfo.telNo"));
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<title>메뉴 사용 권한이 없습니다.</title>

<op:jsTag type="intra" items="base" />

<script>
    //<![CDATA[
    $().ready(function() {

    });
    //]]>
</script>

</head>
<body>
    <div class="row">
        <div class="block"></div>
        <div class="col-md-offset-3 col-md-6">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-bubble4"></i> 메뉴 사용 권한이 없습니다.
                    </h6>
                </div>
                <div class="panel-body">
                    <div class="bg-info with-padding block-inner">
                        <div class="block text-right">해당 메뉴에 대한 접근 권한이 없거나 부족합니다. 관리자에 문의하여 주시기 바랍니다.</div>
                        <div class="block text-right">
                            관리자정보 : ${deptNm} ${name} (${telNo})
                            <button type="button" onclick="history.back();" class="btn btn-default btn-xs">돌아가기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>