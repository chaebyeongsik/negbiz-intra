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
<title>다국어 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/multilang/js/multilang.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if (validate()) {
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        if (response.result) {
                            var key = response.value;

                            var keys = key.split(".");
                            var keyCnt = keys.length;

                            var title = keys[keyCnt -1];
                            Optree.addChildren({
                                "key" : key,
                                "title" : title,
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

        // 다국어 코드 중복 체크
        $("#mtlngUnqCdNm").keyup(function(event) {
            var len = $(this).val().length;
            if (len >= 2) {
                $.ajax({
                    url : "ND_selectDplctChckMultilang.do",
                    type : "GET",
                    dataType : "json",
                    data : {
                        q_upMtlngCdNm : $("#upMtlngCdNm").val(),
                        q_mtlngUnqCdNm : $(this).val(),
                        mtlngUnqCdNm : $(this).val(),
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

    // 국가언어별 메시지 길이 체크
    var customValidate = function() {
        var checkYn = $("#duplicateYn").val();
        if (checkYn == 'N') {
            opWarningMsg(OpMessage.common.overlapCode);
            return false;
        }
        $("[name='multiLangMsgCn']").each(function(idx) {
            if (!$(this).maxlengthValidator(100)) {
                // 메시지 포멧팅
                opWarningMsg($.fn.opformat(OpMessage.validator.maxlength, [ 100 ]));
                $(this).focus();
                return false;
            }
        });
        return true;
    };

    /* 트리 클릭 후 상위 코드 경로 표시 */
    var afterTreeView = function(key, parentKey, response) {
        var cdId = key;
        if (key == "dummy") {
            cdId = "최상위코드";
        }
        $("#nodePath").html(Optree.getNodePath() + "&nbsp;&nbsp;&nbsp;<code>" + cdId + "</code>");
    };
    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_insertMultilang.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <input type="hidden" name="duplicateYn" id="duplicateYn" value="Y" />
        <input type="hidden" name="upMtlngCdNm" id="upMtlngCdNm" value="${dataVo.mtlngCdNm}" />
        <input type="hidden" name="upperMultiLangKey" id="upperMultiLangKey" value="${dataVo.mtlngUnqCdNm}" />

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="upMtlngCdNm" class="control-label col-sm-2"> 상위다국어코드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static" id="nodePath">코드를 선택하세요.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mtlngUnqCdNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 다국어키
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-5">
                                <input type="text" name="mtlngUnqCdNm" id="mtlngUnqCdNm" value="" class="form-control" placeholder="다국어키" />
                            </div>
                            <div class="col-sm-7" id="duplicateText"></div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="mtlngUnqCdNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mtlngCdExpln" class="control-label col-sm-2">다국어코드설명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="mtlngCdExpln" id="mtlngCdExpln" placeholder="다국어코드설명" rows="5" class="form-control"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="mtlngCdExpln" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">다국어설정 </label>
                    <div class="col-sm-10">
                        <c:if test="${empty langList}">
                            <div class="row">
                                <div class="col-sm-12">
                                    <p class="form-control-static text-warning">등록된 도메인(사이트관리)이 없습니다.</p>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${not empty langList}">
                            <c:forEach var="langCdId" items="${langList}">
                                <div class="row">
                                    <div class="col-sm-3 text-right">
                                        <label for="multiLangMsgCns${langCdId}" class="control-label">
                                            <op:cdId hghrkCdId="localeCode" cdId="${langCdId}" />
                                            :
                                        </label>
                                    </div>
                                    <div class="col-sm-9">
                                        <input type="text" name="multiLangMsgCns" id="multiLangMsgCns${langCdId}" value="" class="form-control"
                                            placeholder="<op:cdId hghrkCdId="localeCode" cdId="${langCdId}"/> 메시지 작성"
                                        />
                                        <input type="hidden" name="langCdIds" id="langCdIds${langCdId}" value="${langCdId}" />
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="col-sm-12">
                                        <div class="help-block op-validate">
                                            <ul class="list-group">
                                                <li class="list-group-item">각 언어별 메시지를 최대 길이에 맞게 입력하세요. (100자)</li>
                                                <li class="list-group-item">메시지가 등록되지 않은 코드가 사용될 경우 '등록된 메시지가 없습니다' 가 표시됩니다.</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2">사용여부 </label>
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