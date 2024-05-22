<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="zesinc.web.support.BaseConfig"%>
<%@ page import="zesinc.web.vo.cache.CmsCacheVO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>
    <c:choose>
        <c:when test="${not empty UserMenu.ttl}">${UserMenu.ttl} : ${UserMenu.menuPathNm}</c:when>
        <c:otherwise>${UserMenu.menuNm} : ${UserMenu.menuPathNm}</c:otherwise>
    </c:choose>
</title>

<op:jsTag type="user" items="base, modal" />

<link href="/resources/templateSample/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/templateSample/css/modern-business.css" rel="stylesheet">
<link href="/resources/templateSample/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- Bootstrap Core JavaScript -->
<script src="/resources/templateSample/js/bootstrap.min.js"></script>

<script>
/* 초기화 */
$(document).ready(function() {
    //퀵메뉴관리
    $("#quick-area").load("/ND_quickMenu.do", function(result) { });
});

var opUserMenuLayer = function(obj) {
    $.fn.opmodal({
        href : $(obj).attr("href"),
        width : $(window).width() * 0.8,
        height : $(window).height() * 0.8,
        closeBtn : true
    });
};
</script>

<sitemesh:write property="head" />

</head>
<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">메뉴 접기/펼치기</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">OpenWorks 4</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${empty sessionScope['__usk']}">
                            <li><a href="/login/BD_loginForm.do">로그인</a></li>
                        </c:when>
                        <c:otherwise>
                             <li><a href="/login/ND_logoutAction.do"><strong>${sessionScope['__usk'].userNm}</strong>님 로그아웃</a></li>
                             <li><a href="/user/join/BD_processPasswordForm.do">마이페이지</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="userTopMenuVo" items="${UserTopMenu.childList}" varStatus="index">
                        <c:if test="${userTopMenuVo.indctYn eq 'Y'}">
                            <c:choose>
                                <c:when test="${userTopMenuVo.linkTypeNm eq 'POPUP'}">
                                    <c:set var="linkAction" value="target=\"_blank\" title=\"새창으로 이동합니다.\"" />
                                </c:when>
                                <c:when test="${userTopMenuVo.linkTypeNm eq 'LAYER'}">
                                    <c:set var="linkAction" value="onclick=\"opUserMenuLayer(this); return false;\"" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="linkAction" value="" />
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${not empty userTopMenuVo.childList}">
                                    <c:choose>
                                        <c:when test="${empty userTopMenuVo.userMenuUrlAddr}">
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    <c:choose>
                                                        <c:when test="${not empty userTopMenuVo.userMenuImgNm}"><img alt="${userTopMenuVo.userMenuImgNm}" src="${userTopMenuVo.userMenuImgNm}" /></c:when>
                                                        <c:otherwise><c:out value="${userTopMenuVo.ttl}" default="${userTopMenuVo.menuNm}" /></c:otherwise>
                                                    </c:choose>
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <c:forEach var="childMenuVo" items="${userTopMenuVo.childList}" varStatus="index">
                                                    	<c:if test="${childMenuVo.indctYn eq 'Y'}">
								                            <c:choose>
								                                <c:when test="${childMenuVo.linkTypeNm eq 'POPUP'}">
								                                    <c:set var="linkAction" value="target=\"_blank\" title=\"새창으로 이동합니다.\"" />
								                                </c:when>
								                                <c:when test="${childMenuVo.linkTypeNm eq 'LAYER'}">
								                                    <c:set var="linkAction" value="onclick=\"opUserMenuLayer(this); return false;\"" />
								                                </c:when>
								                                <c:otherwise>
								                                    <c:set var="linkAction" value="" />
								                                </c:otherwise>
								                            </c:choose>
	                                                        <li><a href="${childMenuVo.userMenuUrlAddr}" ${linkAction}>
	                                                            <c:choose>
	                                                                <c:when test="${not empty childMenuVo.userMenuImgNm}"><img alt="${childMenuVo.userMenuImgNm}" src="${childMenuVo.userMenuImgNm}" /></c:when>
	                                                                <c:otherwise><c:out value="${childMenuVo.ttl}" default="${childMenuVo.menuNm}" /></c:otherwise>
	                                                            </c:choose>
	                                                        </a></li>
	                                                    </c:if>
                                                    </c:forEach>
                                                </ul></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <a href="<c:url value="${userTopMenuVo.userMenuUrlAddr}" />" ${linkAction}>
                                                    <c:choose>
                                                        <c:when test="${not empty userTopMenuVo.userMenuImgNm}"><img alt="${userTopMenuVo.userMenuImgNm}" src="${userTopMenuVo.userMenuImgNm}" /></c:when>
                                                        <c:otherwise><c:out value="${userTopMenuVo.ttl}" default="${userTopMenuVo.menuNm}" /></c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="<c:url value="${userTopMenuVo.userMenuUrlAddr}" />" ${linkAction}>
                                            <c:choose>
                                                <c:when test="${not empty userTopMenuVo.userMenuImgNm}"><img alt="${userTopMenuVo.userMenuImgNm}" src="${userTopMenuVo.userMenuImgNm}" /></c:when>
                                                <c:otherwise><c:out value="${userTopMenuVo.ttl}" default="${userTopMenuVo.menuNm}" /></c:otherwise>
                                            </c:choose>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    <c:choose>
                        <c:when test="${not empty UserMenu.menuTtlImgNm}"><img alt="<c:out value="${UserMenu.ttl}" default="${UserMenu.menuNm}" />" src="${UserMenu.menuTtlImgNm}" /></c:when>
                        <c:otherwise><c:out value="${UserMenu.ttl}" default="${UserMenu.menuNm}" /></c:otherwise>
                    </c:choose>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Content Row -->
        <div class="row">
            <!-- Sidebar Column -->
            <div class="col-md-3">

                <div class="list-group">

                    <c:forEach var="first" items="${UserSubMenu.childList}" varStatus="index">
                        <c:if test="${first.indctYn eq 'Y'}">
                            <c:choose>
                                <c:when test="${first.userMenuEngNm eq UserMenu.userMenuEngNm}">
                                    <c:set var="active" value="active" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="active" value="" />
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${first.linkTypeNm eq 'POPUP'}">
                                    <c:set var="linkAction" value="target=\"_blank\" title=\"새창으로 이동합니다.\"" />
                                </c:when>
                                <c:when test="${first.linkTypeNm eq 'LAYER'}">
                                    <c:set var="linkAction" value="onclick=\"opUserMenuLayer(this); return false;\"" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="linkAction" value="" />
                                </c:otherwise>
                            </c:choose>
                            <a href="<c:url value="${first.userMenuUrlAddr}" />" class="list-group-item ${active}" ${linkAction} >
                                <c:choose>
                                    <c:when test="${not empty first.menuOnImgNm and not empty active}">
                                        <img alt="<c:out value="${first.ttl}" default="${first.menuNm}" />" src="${first.menuOnImgNm}" />
                                    </c:when>
                                    <c:when test="${not empty first.menuOffImgNm and empty active}">
                                        <img alt="<c:out value="${first.ttl}" default="${first.menuNm}" />" src="${first.menuOffImgNm}" />
                                    </c:when>
                                    <c:otherwise><c:out value="${first.ttl}" default="${first.menuNm}" /></c:otherwise>
                                </c:choose>
                            </a>

                            <c:if test="${not empty first.childList}">

                                <c:forEach var="second" items="${first.childList}" varStatus="index">
                                    <c:if test="${second.indctYn eq 'Y'}">
                                        <c:choose>
                                            <c:when test="${second.userMenuEngNm eq UserMenu.userMenuEngNm}">
                                                <c:set var="active" value="active" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="active" value="" />
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
			                                <c:when test="${second.linkTypeNm eq 'POPUP'}">
			                                    <c:set var="linkAction" value="target=\"_blank\" title=\"새창으로 이동합니다.\"" />
			                                </c:when>
			                                <c:when test="${second.linkTypeNm eq 'LAYER'}">
			                                    <c:set var="linkAction" value="onclick=\"opUserMenuLayer(this); return false;\"" />
			                                </c:when>
			                                <c:otherwise>
			                                    <c:set var="linkAction" value="" />
			                                </c:otherwise>
			                            </c:choose>
                                        <a href="<c:url value="${second.userMenuUrlAddr}" />" class="list-group-item ${active}" style="padding-left:10px;" ${linkAction}>
                                            <c:choose>
                                                <c:when test="${not empty second.menuOnImgNm and not empty active}">
                                                    <img alt="><c:out value="${second.ttl}" default="${second.menuNm}" />" src="${second.menuOnImgNm}" />
                                                </c:when>
                                                <c:when test="${not empty second.menuOffImgNm and empty active}">
                                                    <img alt="><c:out value="${second.ttl}" default="${second.menuNm}" />" src="${second.menuOffImgNm}" />
                                                </c:when>
                                                <c:otherwise><c:out value="${second.ttl}" default="${second.menuNm}" /></c:otherwise>
                                            </c:choose>
                                        </a>

                                        <c:if test="${not empty second.childList}">
                                            <c:forEach var="third" items="${second.childList}" varStatus="index">
                                                <c:if test="${third.indctYn eq 'Y'}">
                                                    <c:choose>
                                                        <c:when test="${third.userMenuEngNm eq UserMenu.userMenuEngNm}">
                                                            <c:set var="active" value="active" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="active" value="" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
						                                <c:when test="${third.linkTypeNm eq 'POPUP'}">
						                                    <c:set var="linkAction" value="target=\"_blank\" title=\"새창으로 이동합니다.\"" />
						                                </c:when>
						                                <c:when test="${third.linkTypeNm eq 'LAYER'}">
						                                    <c:set var="linkAction" value="onclick=\"opUserMenuLayer(this); return false;\"" />
						                                </c:when>
						                                <c:otherwise>
						                                    <c:set var="linkAction" value="" />
						                                </c:otherwise>
						                            </c:choose>
                                                    <a href="<c:url value="${third.userMenuUrlAddr}" />" class="list-group-item ${active}" style="padding-left:10px;" ${linkAction}>
                                                        <c:choose>
                                                            <c:when test="${not empty third.menuOnImgNm and not empty active}">
                                                                <img alt="><c:out value="${third.ttl}" default="${third.menuNm}" />" src="${third.menuOnImgNm}" />
                                                            </c:when>
                                                            <c:when test="${not empty third.menuOffImgNm and empty active}">
                                                                <img alt="><c:out value="${third.ttl}" default="${third.menuNm}" />" src="${third.menuOffImgNm}" />
                                                            </c:when>
                                                            <c:otherwise><c:out value="${third.ttl}" default="${third.menuNm}" /></c:otherwise>
                                                        </c:choose>
                                                    </a>

                                                    <c:if test="${not empty third.childList}">
                                                        <c:forEach var="fourth" items="${third.childList}" varStatus="index">
                                                            <c:if test="${fourth.indctYn eq 'Y'}">
                                                                <c:choose>
                                                                    <c:when test="${fourth.userMenuEngNm eq UserMenu.userMenuEngNm}">
                                                                        <c:set var="active" value="active" />
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:set var="active" value="" />
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <c:choose>
									                                <c:when test="${fourth.linkTypeNm eq 'POPUP'}">
									                                    <c:set var="linkAction" value="target=\"_blank\" title=\"새창으로 이동합니다.\"" />
									                                </c:when>
									                                <c:when test="${fourth.linkTypeNm eq 'LAYER'}">
									                                    <c:set var="linkAction" value="onclick=\"opUserMenuLayer(this); return false;\"" />
									                                </c:when>
									                                <c:otherwise>
									                                    <c:set var="linkAction" value="" />
									                                </c:otherwise>
									                            </c:choose>
                                                                <a href="<c:url value="${fourth.userMenuUrlAddr}" />" class="list-group-item ${active}" style="padding-left:10px;" ${linkAction}>
                                                                    <c:choose>
                                                                        <c:when test="${not empty fourth.menuOnImgNm and not empty active}">
                                                                            <img alt="><c:out value="${fourth.ttl}" default="${fourth.menuNm}" />" src="${fourth.menuOnImgNm}" />
                                                                        </c:when>
                                                                        <c:when test="${not empty fourth.menuOffImgNm and empty active}">
                                                                            <img alt="><c:out value="${fourth.ttl}" default="${fourth.menuNm}" />" src="${fourth.menuOffImgNm}" />
                                                                        </c:when>
                                                                        <c:otherwise><c:out value="${fourth.ttl}" default="${fourth.menuNm}" /></c:otherwise>
                                                                    </c:choose>
                                                                </a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <!-- Content Column -->
            <div class="col-md-9">
                <div class="row">
                    <ol class="breadcrumb text-right">
                        <li class="active">${UserMenu.menuPathNm}</li>
                    </ol>
                </div>

                <c:if test="${not empty UserMenu.strtContsCn}">
                    <div class="row">
                        <div class="col-md-12">
                            ${UserMenu.strtContsCn}
                        </div>
                    </div>
                </c:if>

                <sitemesh:write property="body" />

                <c:if test="${not empty UserMenu.endContsCn}">
                    <div class="row">
                        <div class="col-md-12">
                            ${UserMenu.endContsCn}
                        </div>
                    </div>
                </c:if>

                <p />

                <%-- 담당자화면표시여부 설정에 따른 표시 화면 --%>
                <jsp:include page="../inc/INC_ChargerView.jsp" flush="true"></jsp:include>

                <%-- 만족도사용여부 설정에 따른 표시 화면 --%>
                <jsp:include page="../inc/INC_SatisfyForm.jsp" flush="true"></jsp:include>

            </div>

        </div>
        <!-- /.row -->

        <hr>
        <div id="quick-area"></div>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12 text-right">
                    <p>Copyright &copy; Openwork4 2015</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

</body>

</html>
