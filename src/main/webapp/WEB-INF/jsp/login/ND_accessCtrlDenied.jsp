<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="zesinc.core.config.Config"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%
    request.setAttribute("deptNm", Config.getString("system-config.adminInfo.deptNm"));
    request.setAttribute("name", Config.getString("system-config.adminInfo.name"));
    request.setAttribute("telNo", Config.getString("system-config.adminInfo.telNo"));
%>

<html>
<head>
<title>접근이 제한되었습니다.</title>

<op:jsTag type="intra" items="base" />

<script type="text/javascript">
    //<CDATA[[
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
                        <i class="icon-bubble4"></i> 접근이 제한되었습니다.
                    </h6>
                </div>
                <div class="panel-body">
                    <div class="bg-info with-padding block-inner">
                        <div class="block">본 시스템은 외부에서의 접근이 허용되지 않습니다. 관리자에 문의하여 주시기 바랍니다.</div>
                        <div class="block">접속자정보 : 접근IP ${ACCESS_IP}, 계정ID ${ACCESS_ID}</div>
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