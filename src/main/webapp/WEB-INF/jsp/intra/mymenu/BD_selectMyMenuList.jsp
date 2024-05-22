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
<title>내메뉴 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/myMenu/js/listbox.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $.configureBoxes();

        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(validate()) {
                $("#box2View option").each(function() {
                    $(this).prop("selected", true);
                });
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });
    });

    //]]>
</script>
</head>
<body>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertMyMenu.do" class="form-horizontal">
        <!-- Dual selects -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-arrow3"></i> 좌측 전체 메뉴에서 자주 사용하는 메뉴를 우측으로 이동 후 저장하세요.
                </h6>
            </div>
            <div class="panel-body">
                <!-- Left box -->
                <div class="left-box">
                    <input type="text" id="box1Filter" class="form-control" placeholder="메뉴검색">
                    <button type="button" id="box1Clear" class="filter">x</button>
                    <select id="box1View" multiple="multiple" class="form-control" style="height: 400px;">
                        <c:forEach items="${baseList}" var="baseVo">
                            <option value="${baseVo.menuSn}">${baseVo.menuNm}</option>
                        </c:forEach>
                    </select> <span id="box1Counter" class="count-label"></span> <select id="box1Storage"></select>
                </div>
                <!-- /left-box -->

                <!-- Control buttons -->
                <div class="dual-control">
                    <button id="to2" type="button" class="btn btn-default">&nbsp;&gt;&nbsp;</button>
                    <button id="allTo2" type="button" class="btn btn-default">&nbsp;&gt;&gt;&nbsp;</button>
                    <br />
                    <button id="to1" type="button" class="btn btn-default">&nbsp;&lt;&nbsp;</button>
                    <button id="allTo1" type="button" class="btn btn-default">&nbsp;&lt;&lt;&nbsp;</button>
                </div>
                <!-- /control buttons -->

                <!-- Right box -->
                <div class="right-box">
                    <input type="text" id="box2Filter" class="form-control" placeholder="메뉴검색">
                    <button type="button" id="box2Clear" class="filter">x</button>
                    <select id="box2View" name="menuSns" multiple="multiple" class="form-control" style="height: 400px;">
                        <c:forEach items="${dataList}" var="dataVo">
                            <option value="${dataVo.menuSn}">${dataVo.menuNm}</option>
                        </c:forEach>
                    </select> <span id="box2Counter" class="count-label"></span> <select id="box2Storage"></select>
                </div>
                <!-- /right box -->

            </div>
        </div>
        <!-- /dual selects -->

        <!-- 버튼 -->
        <div class="row">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>