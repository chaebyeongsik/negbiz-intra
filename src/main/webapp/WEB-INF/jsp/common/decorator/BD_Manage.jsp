<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="zesinc.web.support.BaseConfig"%>
<%@ page import="zesinc.web.support.helper.OpHelper"%>
<%@ page import="zesinc.web.vo.cache.MenuCacheVO"%>
<%@ page import="zesinc.login.domain.LoginVO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%
    // 현재 메뉴의 상위 메뉴들의 목록
    String upperMenus = "";
    @SuppressWarnings("unchecked")
    List<MenuCacheVO> upperMenuList = (List<MenuCacheVO>)request.getAttribute(BaseConfig.MENU_PATH_KEY);
    if(upperMenuList != null) {
        for(MenuCacheVO menuCacheVo : upperMenuList) {
            upperMenus += ",M_" + menuCacheVo.getMenuSn();
        }
        upperMenus = upperMenus.substring(1);
    }

    // 로그인 정보
    LoginVO loginVo = (LoginVO)OpHelper.getMgrSession();
    request.setAttribute("loginVo", loginVo);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<title><%= BaseConfig.SYSTEM_NAME %></title>

<op:jsTag type="intra" items="base, modal, childMenu" />
<op:jsTag type="libs" items="collapsible, jgrowl" />

<script type="text/javascript">
    //<![CDATA[
    $().ready(function() {

        <c:forEach var="menuVo" items="<%=upperMenuList%>" >
            $("#L_${menuVo.menuSn}").addClass("active");
        </c:forEach>

        $(window).resize(function () {
            if ($(window).width() < 992) {
               $('.sidebar').appendTo('.navbar');
            }
            else {
               $('.sidebar').appendTo('.page-container');
            }
        }).resize();

        // ===== 하위 메뉴 토글 =====//
        $('.sidebar-wide li:not(.disabled) .expand, .sidebar-narrow .navigation > li ul .expand').collapsible({
            defaultOpen: '<%=upperMenus%>',
            cssOpen: 'level-opened',
            cssClose: 'level-closed',
            speed: 150
        });

        //===== Disabling main navigation links =====//
        $('.navigation li.disabled a, .navbar-nav > .disabled > a').click(function(e) {
            e.preventDefault();
        });

        /* 도움말 보기 */
        $("#op-help").click(function () {
            $('#helpDiv').slideToggle(300, function() {});
        });
        /* 화면프린트 */
        $("#op-prrint").click(function () {
            window.print();
        });
        /* 화면새로고침 */
        $("#op-refresh").click(function () {
            location.reload();
        });

        /* 인증서등록 */
        $("#updateMngrCrtfcAcntFormPop").opmodal({width : 830, height: 500});
        /* 개인정보변경 */
        $("#updateChargerFormPop").opmodal({width : 700});

    });
    //]]>
</script>

<sitemesh:write property="head" />

</head>
<body class="navbar-fixed sidebar-wide">
    <!-- navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">

        <div class="navbar-header">
            <a class="navbar-brand" href="#"><img src="/resources/openworks4/theme/default/images/layout/logo.png" alt="Openworks4"></a>
            <a class="sidebar-toggle"><i class="icon-paragraph-justify2" ></i></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-icons">
                <span class="sr-only">Toggle navbar</span>
                <i class="icon-grid3"></i>
            </button>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar">
                <span class="sr-only">Toggle navigation</span>
                <i class="icon-paragraph-justify2"></i>
            </button>
        </div>

        <ul class="nav navbar-nav collapse" id="navbar-icons">
            <c:forEach var="topMenu" items="${__TopMenuKey}">
                <c:if test="${topMenu.indctYn eq 'Y'}">
                    <c:choose>
                        <c:when test="${empty topMenu.mainUrl}">
                            <c:set var="href" value="#" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="href" value="${topMenu.mainUrl}" />
                        </c:otherwise>
                    </c:choose>
                    <li id="L_${topMenu.menuSn}"><a href="<c:url value="${href}" />"><i class="icon-grid"></i> <span>${topMenu.menuNm}</span></a>
                        <!-- childMenu -S -->
                        <c:if test="${not empty topMenu.childList}">
                        <ul class="nav_2dep">
                            <c:forEach var="item" items="${topMenu.childList}" varStatus="status">
                                <li>
                                    <a href="${item.mainUrl}">${item.menuNm}</a>
                                    <c:if test="${not empty item.childList}">
                                        <ul class="nav_3dep">
                                            <c:forEach var="child" items="${item.childList}" varStatus="status">
                                                <li><a href="${child.mainUrl}">${child.menuNm}</a></li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                        </c:if>
                        <!-- childMenu -E -->
                    </li>
                </c:if>
            </c:forEach>
        </ul>

    </div>

    <!-- /navbar -->
    <!-- Page container -->
    <div class="page-container">

        <!-- sidebar -->
        <div class="sidebar collapse">
            <div class="sidebar-content">

                <!-- User dropdown -->
                <div class="user-menu">
                    <div class="user-info">
                        ${loginVo.deptNm} / ${loginVo.picNm}
                        <!-- <a href="/intra/mngr/PD_updateMngrCrtfcAcntForm.do" id="updateMngrCrtfcAcntFormPop" class="btn btn-xs btn-default">인증서</a> -->
                        <a href="/intra/mngr/PD_updateChargerForm.do" id="updateChargerFormPop" class="btn btn-xs btn-default">정보변경</a>
                        <a href="/login/ND_processLogout.do" class="btn btn-xs btn-default">로그아웃</a>
                    </div>
                </div>
                <!-- /user dropdown -->

                <c:if test="${not empty __SubMenuKey}">
                    <ul class="navigation navigation-icons-left">
                        <li id="MY_L_F" class="bg-warning"><a href="" class="expand" id="MY_M_F"><span>바로가기</span><i class="icon-screen2"></i></a>
                            <ul>
                                <c:choose>
                                    <c:when test="${not empty __MyMenuKey}">
                                        <c:forEach items="${__MyMenuKey}" var="myMenuVo">
                                            <li id="MY_L_${myMenuVo.menuSn}"><a href="<c:url value="${myMenuVo.mainUrl}" />" id="MY_M_${myMenuVo.menuSn}">${myMenuVo.menuNm}</a> </li>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <li id="MY_L_${myMenuVo.menuSn}"><a href="<c:url value="/intra/mymenu/BD_selectMyMenuList.do" />" id="MY_M_${myMenuVo.menuSn}">바로가기관리이동</a> </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </li>
                        <c:forEach var="first" items="${__SubMenuKey}">
                        <c:if test="${first.indctYn eq 'Y'}">
                            <li id="L_${first.menuSn}"><a href="<c:url value="${first.mainUrl}" />" <c:if test="${not empty first.childList}">class="expand"</c:if> id="M_${first.menuSn}"><span>${first.menuNm}</span>
                                    <i class="icon-screen2"></i></a> <c:if test="${not empty first.childList}">
                                    <ul>
                                        <c:forEach var="second" items="${first.childList}">
                                        <c:if test="${second.indctYn eq 'Y'}">
                                            <li id="L_${second.menuSn}"><a href="<c:url value="${second.mainUrl}" />" <c:if test="${not empty second.childList}">class="expand"</c:if>
                                                id="M_${second.menuSn}"
                                            >${second.menuNm}</a> <c:if test="${not empty second.childList}">
                                                    <ul>
                                                        <c:forEach var="third" items="${second.childList}">
                                                        <c:if test="${third.indctYn eq 'Y'}">
                                                            <li id="L_${third.menuSn}"><a href="<c:url value="${third.mainUrl}" />"
                                                                <c:if test="${not empty third.childList}">class="expand"</c:if> id="M_${third.menuSn}"
                                                            >${third.menuNm}</a> <c:if test="${not empty third.childList}">
                                                                    <ul>
                                                                        <c:forEach var="fourth" items="${third.childList}">
                                                                        <c:if test="${fourth.indctYn eq 'Y'}">
                                                                            <li id="L_${fourth.menuSn}"><a href="<c:url value="${fourth.mainUrl}" />"
                                                                                <c:if test="${not empty fourth.childList}">class="expand"</c:if> id="M_${fourth.menuSn}"
                                                                            >${fourth.menuNm}</a> <c:if test="${not empty fourth.childList}">
                                                                                    <ul>
                                                                                        <c:forEach var="fifth" items="${fourth.childList}">
                                                                                        <c:if test="${fifth.indctYn eq 'Y'}">
                                                                                            <li id="L_${fifth.menuSn}"><a href="<c:url value="${fifth.mainUrl}" />" id="M_${fifth.menuSn}">${fifth.menuNm}</a></li>
                                                                                        </c:if>
                                                                                        </c:forEach>
                                                                                    </ul>
                                                                                </c:if></li>
                                                                        </c:if>
                                                                        </c:forEach>
                                                                    </ul>
                                                                </c:if></li>
                                                        </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if></li>
                                        </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if></li>
                        </c:if>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
        <!-- /sidebar -->


        <!-- Page content -->
        <div class="page-content">

            <div class="row">
                <div class="col-md-8">
                    <h3 style="margin-top: 5px;">
                        <i class="icon-stack"></i>
                        <c:choose>
                            <c:when test="${not empty __MenuKey.menuNm}">
                                ${__MenuKey.menuNm}
                            </c:when>
                            <c:otherwise>
                                <sitemesh:write property="title" />
                            </c:otherwise>
                        </c:choose>
                    </h3>
                </div>
                <div class="col-md-4 text-right">
                    <h3 style="margin-top: 5px;">
                    	<a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=3&chgCycl=1&fileSn=1" title="Openworks v4.0_매뉴얼_v1.1.pdf 파일받기">
	                    	<button type="button" class="btn btn-icon btn-xs btn-default" id="op-manu" title="매뉴얼 파일받기">
	                            <i class="icon-file-pdf"></i>
	                        </button>
	                    </a>
                        <button type="button" class="btn btn-icon btn-xs btn-default" id="op-help" title="도움말보기">
                            <i class="icon-list"></i>
                        </button>
                        <button type="button" class="btn btn-icon btn-xs btn-default" id="op-prrint" title="프린트">
                            <i class="icon-print2"></i>
                        </button>
                        <button type="button" class="btn btn-icon btn-xs btn-default" id="op-refresh" title="새로고침">
                            <i class="icon-spinner8"></i>
                        </button>
                    </h3>
                </div>
            </div>
            <div class="">
                <div style="display: none;" id="helpDiv" class="help-line">
                    <h5>
                        <i class="icon-wand2"></i> 도움말
                    </h5>
                    <op:help menuSn="${__MenuKey.menuSn}" />
                </div>
            </div>

            <sitemesh:write property="body" />
        </div>
        <!-- /page-content -->
    </div>
    <!-- /page-container -->
</body>
</html>