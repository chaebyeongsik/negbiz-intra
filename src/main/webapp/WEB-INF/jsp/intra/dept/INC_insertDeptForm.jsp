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
<title>부서관리</title>

<op:jsTag type="intra" items="opvalidate" />

<script type="text/javascript">
    //<![CDATA[

    /* 유효성 검증 BEAN Annotation  기반 자동 설정 */
    <valid:script type="msgbox" />
    /* 공통 초기화 */
    $(document).ready(function() {

        /** 부서코드 중복체크 */
        $("input[name=deptCdId]").bind("keyup", function() {
            var deptCdId = $("#deptCdId").val();
            if(deptCdId) {
                $.ajax({
                    url : "<c:url value="/intra/dept/ND_selectDplctChckDept.do" />",
                    type : "GET",
                    dataType : "json",
                    data : {
                        deptCdId : deptCdId,
                        beforeDeptCdId : $("#beforeDeptCdId").val(),
                        q_date : new Date().toString()
                    },
                    success : function(data) {
                        if(data == 0) {
                            $("#dplctChckCode").html("<p class=\"text-info form-control-static\">사용 가능한 코드입니다.</p>");
                        } else {
                            $("#dplctChckCode").html("<p class=\"text-danger form-control-static\">중복된 코드입니다.</p>");
                        }
                    }
                });
            }
        });

    });

    /** 부서 등록 */
    var opInsertDept = function() {
        var validMsg = "상위부서를 선택하세요.";
        if($("#upDeptCdId").val() == "") {
            opWarningMsg(validMsg);
            return;
        }

        if(validate()) {
            $.ajax({
                url : "<c:url value="/intra/dept/ND_insertDept.do" />",
                type : "POST",
                data : $("#dataForm").serialize(),
                dataType : "json",
                success : function(response) {
                    opJsonMsg(response);
                    if(response.result) {
                        Optree.addChildren({
                            "key" : $("#deptCdId").val(),
                            "title" : $("#deptNm").val(),
                            "lazy" : false
                        }, $("#upDeptCdId").val());
                        $("#dataForm input").each(function() {
                            if($(this).attr("type") != 'radio' && $(this).attr("type") != 'hidden') {
                                $(this).val("");
                            }
                        });
                        $("#dplctChckCode").html("");
                        opCount++;
                    } else {
                        return;
                    }
                }
            });
        }

    };

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>

    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_insertDept.do" class="form-horizontal">

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="upDeptCdId" class="control-label col-sm-3"> 상위부서명 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-9">
                                <p class="form-control-static" id="upperDeptNm">상위부서를 선택하세요.</p>
                                <input type="hidden" name="upDeptCdId" id="upDeptCdId" value="" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="deptNm" class="control-label col-sm-3">
                        <span class="mandatory">*</span> 부서명
                    </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="deptNm" id="deptNm" value="" class="form-control input-sm" placeholder="부서명을 입력해주세요" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <valid:msg name="deptNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="deptCdId" class="control-label col-sm-3">
                        <span class="mandatory">*</span> 부서코드
                    </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="deptCdId" id="deptCdId" value="" class="form-control input-sm" placeholder="부서코드를 입력해주세요" />
                            </div>
                            <div class="col-sm-6" id="dplctChckCode"></div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <valid:msg name="deptCdId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rgnTelno" class="control-label col-sm-3">전화번호 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-2">
                                <input type="text" name="rgnTelno" id="rgnTelno" value="" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="telofcTelno" id="telofcTelno" value="" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="indivTelno" id="indivTelno" value="" class="form-control input-sm" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <valid:msg name="rgnTelno" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rgnFxno" class="control-label col-sm-3">팩스번호 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-2">
                                <input type="text" name="rgnFxno" id="rgnFxno" value="" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="telofcFxno" id="telofcFxno" value="" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="indivFxno" id="indivFxno" value="" class="form-control input-sm" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <valid:msg name="rgnFxno" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tkcgTaskNm" class="control-label col-sm-3">주요업무 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-9">
                                <textarea name="tkcgTaskNm" id="tkcgTaskNm" rows="5" class="form-control input-sm" placeholder="주요업무을 입력해주세요"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-9">
                                <valid:msg name="tkcgTaskNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-3">사용여부</label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" checked="checked" />
                                    사용
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                                    미사용
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <valid:msg name="useYn" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="block clearfix">
                <div class="col-md-12 btn-group">
                    <div class="text-right">
                        <button type="button" class="btn btn-success" onclick="opInsertDept();">저장</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>