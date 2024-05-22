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
<title>코드 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/code/js/code.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    var insertCnt = 0;
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(validate()) {
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        if(response.result) {
                            // 추가하는 코드가 최상위 코드인경우는 hghrkCdId는 자신이 된다.
                            var baseKey = $("#hghrkCdId").val();
                            if(baseKey == OpConfig.defaultCode.highCommonCd) {
                                baseKey = $("#cdId").val();
                            }
                            Optree.addChildren({
                                "key" : $("#cdId").val(),
                                "title" : $("#cdNm").val(),
                                "baseKey" : baseKey,
                                "lazy" : false
                            });
                            $("#dataForm")[0].reset();

                            insertCnt++;
                        }
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

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var checkYn = $("#duplicateYn").val();
        if(checkYn == 'N') {
            opWarningMsg(OpMessage.common.overlapCode);
            return false;
        }
        return true;
    };

    // 코드 중복 체크
    $("#cdId").keyup(function(event) {
        var len = $(this).val().length;
        if(len >= 2) {
            $.ajax({
                url : "ND_selectDplctChckCode.do",
                type : "GET",
                dataType : "json",
                data : {
                    cdId : $(this).val(),
                    q_hghrkCdId : $("#hghrkCdId").val(),
                    q_cdId : $(this).val(),
                    q_date : new Date()
                },
                success : function(response) {
                    var msg = response.message;
                    if(response.result) {
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
        if(event.which == 13) {
            event.preventDefault();
        }
    });

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_insertCode.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <input type="hidden" name="duplicateYn" id="duplicateYn" value="Y" />
        <input type="hidden" name="hghrkCdId" id="hghrkCdId" value="${paramMap.q_hghrkCdId}" />
        <input type="hidden" name="upCdId" id="upCdId" value="${paramMap.q_cdId}" />

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label class="control-label col-sm-2"> 상위 코드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static" id="nodePath">코드를 선택하세요.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cdId" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 코드
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-5">
                                <input type="text" name="cdId" id="cdId" value="" maxlength="20" class="form-control" placeholder="코드" />
                            </div>
                            <div class="col-sm-7" id="duplicateText"></div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="cdId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cdNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 코드명
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="cdNm" id="cdNm" value="" class="form-control" placeholder="코드명" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="cdNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <%--
                <div class="form-group">
                    <label for="pbadmsStdCdYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 행정표준코드여부
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="pbadmsStdCdYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="pbadmsStdCdYn" id="pbadmsStdCdYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="pbadmsStdCdYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="pbadmsStdCdYn" id="pbadmsStdCdYnN" value="N" checked="checked" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="pbadmsStdCdYn" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block">
                                    <span class="text-info">현재코드가 행정표준코드에서 참조한경우에만 <code>예</code>를 선택합니다.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="pbadmsStdCdId" class="control-label col-sm-2">행정표준코드</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="pbadmsStdCdId" id="pbadmsStdCdId" value="" class="form-control" placeholder="행정표준코드" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="pbadmsStdCdId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mtlngCdNm" class="control-label col-sm-2">다국어코드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="mtlngCdNm" id="mtlngCdNm" value="" class="form-control" placeholder="다국어코드" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="mtlngCdNm" />
                            </div>
                        </div>
                    </div>
                </div>
                 --%>
                <div class="form-group">
                    <label for="cdExpln" class="control-label col-sm-2">코드설명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="cdExpln" id="cdExpln" rows="5" cols="80" class="form-control" placeholder="코드설명"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="cdExpln" />
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
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>