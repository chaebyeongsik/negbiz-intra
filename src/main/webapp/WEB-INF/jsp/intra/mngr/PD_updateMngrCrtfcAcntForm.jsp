<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.*, java.io.*, java.net.*, java.util.*" %>
<%@ page import="com.gpki.servlet.GPKIHttpServletResponse" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<%@ include file="/gpkisecureweb/jsp/gpkisecureweb.jsp" %>
<%
    String challenge = gpkiresponse.getChallenge();
    String sessionid = gpkirequest.getSession().getId();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<title>GPKI인증서 등록</title>

<script type="text/javascript" src="/gpkisecureweb/client/var.js"></script>
<script type="text/javascript" src="/gpkisecureweb/client/install.js"></script>
<script type="text/javascript" src="/gpkisecureweb/client/GPKIFunc.js"></script>
<script type="text/javascript" src="/gpkisecureweb/client/object.js"></script>

<link rel="stylesheet" type="text/css" href="/gpkisecureweb/css/style.css" />

<script type="text/javascript">
    //<![CDATA[
    $().ready(function() {
        Login(this,document.getElementById('popForm'),true);
    });
    //]]>
</script>

</head>
<body>

    <c:choose>
        <c:when test="${not empty dataVo}">
            <div class="well" style="width:315px; margin-left: 260px;">
                <div class="form-group has-feedback">
                    <label for="picId">인증서선택</label>
                    <div id="embededlogin" ></div>
                </div>

                <div class="form-group has-feedback">
                    <label for="passwd">비밀번호</label>
                    <input id="passwd" type="password" value="" onkeypress="return embeddedEnterEvent(event)" class="form-control" />
                    <i class="icon-lock form-control-feedback"></i>
                </div>

                <div class="row form-actions">
                    <div class="col-xs-6">
                    </div>
                    <div class="col-xs-6">
                        <button type="button" onclick="return LoginEmbedded(popForm);" class="btn btn-warning pull-right">
                            <i class="icon-menu2"></i> 인증
                        </button>
                    </div>
                </div>
                <form name="popForm" id="popForm" method="post" action="/intra/mngr/ND_updateMngrCrtfcAcnt.do">
                    <input type="hidden" name="challenge" value="<%=challenge%>" />
                    <input type="hidden" name="sessionid" id="sessionid" value="<%=sessionid%>" />
                    <input type="hidden" name="ssn" value=""/>
                    <input type="hidden" name="keysec" id="keysec" value="2" />
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-md-12">
                    <blockquote>
                        <p>
                            <strong>등록권한이 없습니다.</strong>
                        </p>
                    </blockquote>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</body>
</html>