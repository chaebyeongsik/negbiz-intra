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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자메뉴컨텐츠 비교</title>

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.common.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    /*
     * 수평 수직 비교 class 변경
     */
    var opChangePos = function(direction) {
        var hClassName = "col-xs-6";
        var vClassName = "col-xs-12";

        if(direction == "horizontal") {
            $("#leftCntntsWindow").removeClass(vClassName);
            $("#rightCntntsWindow").removeClass(vClassName);
            $("#leftCntntsWindow").addClass(hClassName);
            $("#rightCntntsWindow").addClass(hClassName);
            $("#leftCntntsWindow").css({"height":"100%;"});
            $("#rightCntntsWindow").css({"height":"100%;"});
        } else {
            $("#leftCntntsWindow").removeClass(hClassName);
            $("#rightCntntsWindow").removeClass(hClassName);
            $("#leftCntntsWindow").addClass(vClassName);
            $("#rightCntntsWindow").addClass(vClassName);
            $("#leftCntntsWindow").css({"height":"50%;"});
            $("#rightCntntsWindow").css({"height":"50%;margin-top:5px;"});
        }
    }

    //]]>
</script>
</head>
<body>

    <div id="opTabs" class="tabbable block">
        <ul class="nav nav-tabs">
            <li><a
                href="PD_compareSourceCntnts.do?q_siteSn=${baseVo.siteSn}&amp;q_userMenuEngNm=${baseVo.userMenuEngNm}&amp;q_leftCntntsSn=${baseVo.leftCntntsSn}&amp;q_rightCntntsSn=${baseVo.rightCntntsSn}"
            >소스비교</a></li>
            <li class="active"><a
                href="PD_compareScreenCntnts.do?q_siteSn=${baseVo.siteSn}&amp;q_userMenuEngNm=${baseVo.userMenuEngNm}&amp;q_leftCntntsSn=${baseVo.leftCntntsSn}&amp;q_rightCntntsSn=${baseVo.rightCntntsSn}"
            >화면비교</a></li>
        </ul>
    </div>

    <div>
        <form id="dataForm" name="dataForm" method="get" action="PD_compareScreenCntnts.do">
            <input type="hidden" id="q_siteSn" name="q_siteSn" value="${baseVo.siteSn}" />
            <input type="hidden" id="q_userMenuEngNm" name="q_userMenuEngNm" value="${baseVo.userMenuEngNm}" />
            <div class="row">
                <div class="col-xs-8">
                    <div class="form-group">
                        <label for="q_leftCntntsSn" class="controll-label">좌측 버전</label>
                        <select id="q_leftCntntsSn" name="q_leftCntntsSn" class="select" style="width: 80px">
                            <c:forEach items="${baseVo.contsSns}" var="sntntsSn">
                                <option value="${sntntsSn}" <c:if test="${sntntsSn eq baseVo.leftCntntsSn}">selected="selected"</c:if>>#${sntntsSn}</option>
                            </c:forEach>
                        </select>

                        <label for="q_rightCntntsSn" class="controll-label">우측 버전</label>
                        <select id="q_rightCntntsSn" name="q_rightCntntsSn" class="select" style="width: 80px">
                            <c:forEach items="${baseVo.contsSns}" var="sntntsSn">
                                <option value="${sntntsSn}" <c:if test="${sntntsSn eq baseVo.rightCntntsSn}">selected="selected"</c:if>>#${sntntsSn}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-info">비교</button>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group pull-right">
                        <button type="button" class="btn btn-info" onclick="opChangePos('horizontal');">수평비교</button>
                        <button type="button" class="btn btn-info" onclick="opChangePos('vertical');">수직비교</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="row">
        <div id="leftCntntsWindow" class="col-xs-6" style="height:500px;">
            <iframe name="leftCntntsWindow" style="border: 1px solid #333333; width: 100%; height: 100%;"
                src="<c:url value="/intra/cms/cntnts/PV_LayoutContentPreView.do" />?q_siteSn=${dataVo.leftCntnts.siteSn}&q_lytCdNo=${dataVo.leftCntnts.lytCdNo}&q_userMenuEngNm=${dataVo.leftCntnts.userMenuEngNm}&q_contsSn=${dataVo.leftCntnts.contsSn}"
            ></iframe>
        </div>
        <div id="rightCntntsWindow" class="col-xs-6" style="height:500px;">
            <iframe name="rightCntntsWindow" style="border: 1px solid #333333; width: 100%; height: 100%;"
                src="<c:url value="/intra/cms/cntnts/PV_LayoutContentPreView.do" />?q_siteSn=${dataVo.rightCntnts.siteSn}&q_lytCdNo=${dataVo.rightCntnts.lytCdNo}&q_userMenuEngNm=${dataVo.rightCntnts.userMenuEngNm}&q_contsSn=${dataVo.rightCntnts.contsSn}"
            ></iframe>
        </div>
    </div>
</body>
</html>