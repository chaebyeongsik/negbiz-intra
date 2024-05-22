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

<op:jsTag type="intra" items="base, opPassword" />
<op:jsTag type="libs" items="collapsible, jgrowl" />

<sitemesh:write property="head" />

<script>
    //<![CDATA[
    $().ready(function() {

        $("#dataForm").submit(function() {
            if ($("#picId").val() == "") {
                $("#picId").focus();
                opWarningMsg("로그인 아이디를 입력해주세요");

                return false;
            } else if ($("#picPswd").val() == "") {
                opWarningMsg("로그인 비밀번호를 입력해주세요");
                $("#picPswd").focus();

                return false;
            }

            var encPicId = opEncrypt($("#picId").val());
            var encPicPswd = opEncrypt($("#picPswd").val());
            $("#encPicId").val(encPicId);
            $("#encPicPswd").val(encPicPswd);
            $("#picId").prop("disabled", true);
            $("#picPswd").prop("disabled", true);

            return true;
        });

    });
    //]]>
</script>


</head>
<body class="full-width page-condensed">

    <!-- Navbar -->
    <div class="navbar navbar-inverse" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/login/ND_loginForm.do"><img src="/resources/openworks4/theme/default/images/layout/logo.png" alt="openWorks4"></a>
        </div>
    </div>
    <!-- /navbar -->

    <!-- Login wrapper -->
    <div class="login-wrapper">
        <form id="dataForm" method="post" action="<c:url value="/login" />" role="form">
            <div class="popup-header">
                <a href="#" class="pull-left"><i class="icon-user-plus"></i></a> <span class="text-semibold">담당자 로그인</span>
                <div class="btn-group pull-right">
                </div>
            </div>

            <div class="well">
                <div class="form-group has-feedback">
                    <label for="picId">아이디</label>
                    <input type="text" name="picId" id="picId" class="form-control" value="system" />
                    <input type="hidden" name="encPicId" id="encPicId" class="form-control" value="system" />
                    <i class="icon-users form-control-feedback"></i>
                </div>

                <div class="form-group has-feedback">
                    <label for="picPswd">비밀번호</label>
                    <input type="password" name="picPswd" id="picPswd" class="form-control" value="nis123!@#" />
                    <input type="hidden" name="encPicPswd" id="encPicPswd" class="form-control" value="nis123!@#" />
                    <i class="icon-lock form-control-feedback"></i>
                </div>

                <div class="row form-actions">
                    <div class="col-xs-6">
                        <!-- <a href="/login/ND_gpkiLoginForm.do" class="btn btn-info pull-left">
                            <i class="icon-menu2"></i> 인증서로그인
                        </a> -->
                    </div>

                    <div class="col-xs-6">
                        <button type="submit" class="btn btn-warning pull-right">
                            <i class="icon-menu2"></i> 로그인
                        </button>
                    </div>

                    <c:if test="${not empty param.loginFail}">
                        <div class="form-actions">
                            <div class="col-xs-12"><strong class="text-danger">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</strong></div>
                        </div>
                        <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
                    </c:if>

                </div>

            </div>

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