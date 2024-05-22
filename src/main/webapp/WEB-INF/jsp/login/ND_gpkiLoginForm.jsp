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

<title>관리자 GPKI인증서 로그인</title>

<op:jsTag type="intra" items="base" />
<op:jsTag type="libs" items="collapsible, jgrowl" />

<script type="text/javascript" src="/gpkisecureweb/client/var.js"></script>
<script type="text/javascript" src="/gpkisecureweb/client/install.js"></script>
<script type="text/javascript" src="/gpkisecureweb/client/GPKIFunc.js"></script>
<script type="text/javascript" src="/gpkisecureweb/client/object.js"></script>

<link rel="stylesheet" type="text/css" href="/gpkisecureweb/css/style.css" />

<style type="text/css">
#GPKISecureWebX {
    display: none;
}
</style>
</head>
<body onload="Login(this,document.getElementById('popForm'),true);" class="full-width page-condensed">

    <!-- Navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><img src=/resources/openworks4/theme/default/images/layout/logo.png alt="로고"></a>
        </div>
    </div>
    <!-- /navbar -->

    <!-- Login wrapper -->
    <div class="login-wrapper">
        <div class="login_title">
            <div class="title_script"><span class="login_title_p">관리자 로그인</span>입니다.</div>
        </div>
        <div class="popup-header">
            <a href="#" class="pull-left"><i class="icon-user-plus"></i></a> <span class="text-semibold">인증서 로그인</span>
            <div class="btn-group pull-right">
            </div>
        </div>

        <div class="well">
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
                    <a href="/login/ND_loginForm.do" class="btn btn-info pull-left">
                        <i class="icon-menu2"></i> ID로그인
                    </a>
                </div>
                <div class="col-xs-6">
                    <button type="button" onclick="return LoginEmbedded(popForm);" class="btn btn-warning pull-right">
                        <i class="icon-menu2"></i> 로그인
                    </button>
                </div>
            </div>

        </div>
        <form name="popForm" id="popForm" method="post" action="/login/ND_gpkiLogin.do">
            <input type="hidden" name="challenge" value="<%=challenge%>" />
            <input type="hidden" name="sessionid" id="sessionid" value="<%=sessionid%>" />
            <input type="hidden" name="ssn" value=""/>
            <input type="hidden" name="keysec" id="keysec" value="2" />
        </form>
    </div>
    <!-- /login wrapper -->


    <!-- Footer -->
    <div class="footer clearfix">
        <div class="pull-right">
            Copyright &copy; 2015 <a href="http://www.zesinc.co.kr/" target="_blank">ZES Inc</a>
        </div>
    </div>
    <!-- /footer -->


</body>
</html>