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

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판 이동</title>

<op:jsTag type="intra" items="opform" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbs/js/bbs.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            if(validate()) {
                $(this).ajaxSubmit({
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        if(response.result) {
                            parent.opList();
                            opCloseWin();
                        }
                    }
                });
            }
            return false;
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var validate = function() {

        var bbsSn = $("#bbsSn").val();
        if(!bbsSn) {
            opWarningMsg("게시판을 선택하세요.");
            $("#bbsSn").focus();
            return false;
        }

        var clsfNo = $("#clsfNo").val();
        var clsfNoCnt = $("#clsfNo option").length;
        if(!clsfNo && clsfNoCnt > 1) {
            opWarningMsg("대분류를 선택하세요.");
            $("#clsfNo").focus();
            return false;
        }

        var lwrkClsfSn = $("#lwrkClsfSn").val();
        var lwrkClsfSnCnt = $("#lwrkClsfSn option").length;
        if(!lwrkClsfSn && lwrkClsfSnCnt > 1) {
            opWarningMsg("소분류를 선택하세요.");
            $("#lwrkClsfSn").focus();
            return false;
        }

        return true;
    };
    //]]>
</script>
</head>
<body>

    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateMoveBbs.do" class="form-horizontal">

        <input type="hidden" name="mvmnBbsSn" id="mvmnBbsSn" value="${paramMap.q_bbsSn}" />
        <input type="hidden" name="bbsDocNo" id="bbsDocNo" value="${paramMap.q_bbsDocNo}" />

        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 이동대상 게시물
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <ul class="list-group form-control-static">
                                    <c:forEach items="${dataList}" var="dataVo">
                                        <li class="list-group-item">- ${dataVo.ttl}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">이동대상 게시판을 선택하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="bbsSn" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 게시판선택
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <select name="bbsSn" id="bbsSn" class="select" onchange="opLoadClCode(this, 'clsfNo');">
                                    <option value="">--</option>
                                    <c:forEach items="${baseList}" var="bbsVo">
                                        <option value="${bbsVo.bbsSn}">${bbsVo.bbsNm}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">이동대상 게시판을 선택하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="clsfNo" class="control-label col-xs-3">분류선택</label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <%-- 대분류 --%>
                                <select name="clsfNo" id="clsfNo" class="select" onchange="opLoadLwrkClsfSn(this, null, 'lwrkClsfSn');">
                                    <option value="">--</option>
                                </select>
                            </div>
                            <div class="col-xs-6">
                                <%-- 소분류 --%>
                                <select name="lwrkClsfSn" id="lwrkClsfSn" class="select">
                                    <option value="">--</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">이동대상 게시판의 분류를 선택하세요.</span></li>
                                        <li class="list-group-item"><span class="text-danger">분류가 없는 경우에는 선택하지 않습니다.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="block clearfix">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">이동</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

</body>
</html>