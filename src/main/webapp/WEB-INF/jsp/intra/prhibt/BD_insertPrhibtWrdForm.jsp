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
<title>금지단어 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/prhibtWrd/js/prhibtWrd.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        // 코드 중복 체크
        $("#phbwdCdId").keyup(function(event) {
            var len = $(this).val().length;
            if (len >= 2) {
                $.ajax({
                    url : "ND_selectDplctChckCode.do",
                    type : "GET",
                    dataType : "json",
                    data : {
                        q_phbwdCdId : $(this).val(),
                        q_date : new Date()
                    },
                    success : function(response) {
                        var msg = response.message;
                        if (response.result) {
                            $("#duplicateText").html("<p class=\"text-info form-control-static\">" + msg + "</p>");
                            $("#duplicateYn").val("Y");
                        } else {
                            $("#duplicateText").html("<p class=\"text-danger form-control-static\">" + msg + "</p>");
                            $("#duplicateYn").val("N");
                        }
                    }
                });
            }
        }).keydown(function(event) {
            if (event.which == 13) {
                event.preventDefault();
            }
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    //]]>
</script>
</head>
<body>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertPrhibtWrd.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <input type="hidden" name="duplicateYn" id="duplicateYn" value="Y" />

        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-libreoffice"></i> 금지단어 등록
                </h6>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="phbwdCdId" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 금지단어코드
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-3">
                                <input type="text" name="phbwdCdId" id="phbwdCdId" value="" maxlength="20" class="form-control" placeholder="금지단어코드" />
                            </div>
                            <div class="col-sm-9" id="duplicateText"></div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="phbwdCdId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ttl" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 제목
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="ttl" id="ttl" value="" maxlength="100" class="form-control" placeholder="제목" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="ttl" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phbwdCn" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 금지단어내용
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="phbwdCn" id="phbwdCn" rows="15" cols="80" class="form-control" placeholder="금지단어내용"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="phbwdCn" />
                                <div class="help-block">
                                    <span class="text-info">중복단어는 자동으로 필터링되어 하나의 단어만 저장됩니다. </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 사용여부
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" checked="checked" class="styled" />
                                    예
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="useYn" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <button type="button" class="btn btn-primary" onclick="opList('BD_selectPrhibtWrdList.do');">목록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>