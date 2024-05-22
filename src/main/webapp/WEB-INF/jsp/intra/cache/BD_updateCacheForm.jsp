<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>캐시 관리</title>

<op:jsTag type="libs" items="form" />

<script type="text/javascript">
    //<![CDATA[

    /*
     * 공통 초기화 기능
     */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            var siteSns = $("input[name=q_siteSn]:checked");
            var cacheName = $("input[name=q_cacheName]:checked").val();
            if(siteSns.length < 1 || !cacheName) {
                opWarningMsg("사이트와 캐시이름을 선택해야 합니다.");
                return false;
            }
            opLoadStart();
            $(this).ajaxSubmit({
                type : "post",
                dataType : "json",
                success : function(response) {
                    opLoadEnd();
                    opJsonMsg(response, response.value);
                },
                error : function(response) {
                    opLoadEnd();
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });

            return false;
        });
    });
    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 캐시새로고침
                    </h6>
                </div>
                <div class="panel-body">
                    <form name="dataForm" id="dataForm" method="post" action="ND_updateCache.do" class="form-horizontal">

                        <div class="panel panel-default">
                            <div class="panel-body">

                                <div class="form-group">
                                    <label for="q_siteSn" class="control-label col-md-2"> 사이트 선택 </label>
                                    <div class="col-md-10">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label for="q_siteSn0" class="checkbox-inline checkbox-primary">
                                                    <input type="checkbox" name="q_siteSn" id="q_siteSn0" value="0" class="styled" />
                                                    관리서버
                                                </label>
                                                <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                                    <label for="q_siteSn${domain.siteSn}" class="checkbox-inline checkbox-primary">
                                                        <input type="checkbox" name="q_siteSn" id="q_siteSn${domain.siteSn}" value="${domain.siteSn}" class="styled" />
                                                        ${domain.siteExpln}
                                                    </label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="q_cacheName" class="control-label col-md-2"> 캐시이름 선택 </label>
                                    <div class="col-md-10">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <c:forEach items="${cacheList}" var="cache" varStatus="status">
                                                    <label for="q_cacheName${status.index}" class="radio-inline radio-primary">
                                                        <input type="radio" name="q_cacheName" id="q_cacheName${status.index}" value="${cache.key}" class="styled" />
                                                        ${cache.value}
                                                    </label>
                                                    <c:if test="${status.index eq 3}">
                                                        <p />
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 버튼 -->
                        <div class="row">
                            <div class="col-md-12 btn-group">
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-success">케시새로고침</button>
                                </div>
                            </div>
                        </div>
                        <!-- //버튼 -->
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>