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
<title>게시판설정 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="tags" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsMngr/js/bbsMngr.js"></script>
<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        // radio 버튼 선택 값 설정
        opChecked("clsfUseYn", "${dataVo.clsfUseYn}", "N");
        opChecked("ntcUseYn", "${dataVo.ntcUseYn}", "N");
        opChecked("nfeedUseYn", "${dataVo.nfeedUseYn}", "N");
        opChecked("pstgUseYn", "${dataVo.pstgUseYn}", "N");
        opChecked("prvcClctAgreYn", "${dataVo.prvcClctAgreYn}", "N");
        opChecked("useYn", "${dataVo.useYn}", "Y");
        opSelected("bbsStngSn", "${dataVo.bbsStngSn}", "");

        // 분류명 Tags-input
        $('.tags').tagsInput({
            width : '100%',
            defaultText : '하위분류'
        });

        $("input[name=clsfUseYn]").click(function() {
            if(this.value == 'Y') {
                $("#clConfig").show();
            } else {
                $("#clConfig").hide();
            }
        });

        $("input[name=siteSns]").click(function() {
            var isChecked = this.checked;
            var chkDiv = $(this).parent().parent().parent().parent().parent();

            chkDiv.find("select").prop("disabled", !isChecked);
        });


        /* 개인정보수집 구분에 따른 항목 활성/비활성화 */
        $("input:radio[name='prvcClctAgreYn']").click(function() {
            if($(this).val() == "N") {
                opSelected("prvcStrgDayCnt", "");
                $("input:text[name='prvcDelYmd']").val("");

                $("input:radio[name='prvcDelSeCd']").removeAttr("checked");
                opChecked("prvcDelSeCd", "");

                $("input:text[name='prvcDelYmd']").attr("disabled", "disabled");
                $("select[name='prvcStrgDayCnt']").attr("disabled", "disabled");
            } else {
                $("input:text[name='prvcDelYmd']").removeAttr("disabled");
                $("select[name='prvcStrgDayCnt']").attr("disabled", "disabled");

                opChecked("prvcDelSeCd", "D");
            }
        });
        /* 개인정보삭제일 구분에 따른 항목 활성/비활성화 */
        $("input:radio[name='prvcDelSeCd']").click(function() {
            var prvcClctAgreYn = $("input:radio[name='prvcClctAgreYn']:checked").val();

            if(prvcClctAgreYn == "Y") {
                if($(this).val() == "D") {
                    opSelected("prvcStrgDayCnt", "");

                    $("input:text[name='prvcDelYmd']").removeAttr("disabled");
                    $("select[name='prvcStrgDayCnt']").attr("disabled", "disabled");
                } else {
                    $("input:text[name='prvcDelYmd']").val("");

                    $("input:text[name='prvcDelYmd']").attr("disabled", "disabled");
                    $("select[name='prvcStrgDayCnt']").removeAttr("disabled");
                }
            } else {
                opChecked("prvcDelSeCd", "");
            }
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var returnVal = true;
        /*
        $("input[name=siteSns]").each(function(i) {
            var siteSn;
            var value;
            if($(this).prop("checked")) {
                siteSn = $(this).val();
                value = $("#lstTmpltNm" + siteSn).val();
                if(value == "") {
                    opWarningMsg("목록스킨을 선택하세요.");
                    $("#lstTmpltNm" + siteSn).focus();
                    returnVal = false;
                    return false;
                }
                value = $("#pstTmpltNm" + siteSn).val();
                if(value == "") {
                    opWarningMsg("읽기스킨을 선택하세요.");
                    $("#pstTmpltNm" + siteSn).focus();
                    returnVal = false;
                    return false;
                }
                value = $("#inptTmpltNm" + siteSn).val();
                if(value == "") {
                    opWarningMsg("쓰기스킨을 선택하세요.");
                    $("#inptTmpltNm" + siteSn).focus();
                    returnVal = false;
                    return false;
                }
            }
        });
         */

        $("#clGroupDiv input[name='clsfNms']").each(function(index) {
            target = $(this);
            if(!target.requirefromValidator("clsfUseYn", "Y", "분류사용여부")) {
                opWarningMsg("분류명을 입력하세요.");
                target.focus();
                returnVal = false;
                return false;
            }
        });

        var prvcClctAgreYn = $("input:radio[name='prvcClctAgreYn']:checked").val();
        if(prvcClctAgreYn == "Y") {
            var prvcDelSeCd = $("input:radio[name='prvcDelSeCd']:checked").val();
            if(prvcDelSeCd == "D") {
                if(!$("#prvcDelYmd").notnullValidator()) {
                    opWarningMsg("개인정보삭제 날짜를 입력하세요.");
                    $("#prvcDelYmd").focus();
                    returnVal = false;
                    return false;
                }
            } else if(prvcDelSeCd == "Y") {
                if(!$("#prvcStrgDayCnt").notnullValidator()) {
                    opWarningMsg("개인정보삭제 기간을 선택하세요.");
                    $("#prvcStrgDayCnt").focus();
                    returnVal = false;
                    return false;
                }
            }
        }

        if(!returnVal) {
            return false;
        }

        return true;
    };

    //]]>
</script>
</head>
<body>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 게시판설정 등록
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" action="ND_updateBbsMngr.do" class="form-horizontal">
                <input type="hidden" name="bbsSn" id="bbsSn" value="${dataVo.bbsSn}" />
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />
                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="bbsNm" class="control-label col-xs-2">
                                <span class="mandatory">*</span> 게시판명
                            </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <input type="text" name="bbsNm" id="bbsNm" value="${dataVo.bbsNm}" maxlength="300" class="form-control" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="bbsNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bbsExpln" class="control-label col-xs-2"> 게시판설명</label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <textarea name="bbsExpln" id="bbsExpln" rows="5" cols="80" class="form-control">${dataVo.bbsExpln}</textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="bbsExpln" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bbsStngSn" class="control-label col-xs-2">
                                <span class="mandatory">*</span> 게시판환경설정
                            </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <select name="bbsStngSn" id="bbsStngSn" class="form-control">
                                            <option value="">--</option>
                                            <c:forEach items="${bbsSetupList}" var="bbsSetupVo">
                                                <option value="${bbsSetupVo.bbsStngSn}">${bbsSetupVo.bbsStngNm}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="bbsStngSn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bbsType" class="control-label col-md-2"> 게시판 종류 </label>
                            <div class="col-md-10">
                                <div class="row">
                                    <div class="col-md-12">

                                        <c:set var="siteSn" value="" />
                                        <c:set var="disabled" value="disabled=\"disabled\"" />
                                        <c:forEach items="${bbsDomnList}" var="bbsDomn">
                                            <c:if test="${'0' eq bbsDomn.siteSn}">
                                                <c:set var="siteSn" value="0" />
                                                <c:set var="disabled" value="" />
                                            </c:if>
                                        </c:forEach>

                                        <div class="row">
                                            <div class="col-md-3" id="divDomn0">
                                                <label for="siteSn0" class="checkbox-inline checkbox-primary">
                                                    <input type="checkbox" name="siteSns" id="siteSn0" value="0" class="styled" <c:if test="${'0' eq siteSn}">checked="checked"</c:if> />
                                                    관리자
                                                </label>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="row">
                                                    <label for="lstTmpltNm0" class="control-label col-md-4 text-right">목록</label>
                                                    <div class="col-md-8">
                                                        <select name="lstTmpltNm" id="lstTmpltNm0" class="form-control" ${disabled}>
                                                            <option value="">--</option>
                                                            <c:forEach items="${listTmplats}" var="list">
                                                                <option value="0_${list.tmpltId}" <c:forEach items="${bbsDomnList}" var="bbsDomn" varStatus="status">
                                                            <c:if test="${list.tmpltId eq bbsDomn.lstTmpltNm and '0' eq bbsDomn.siteSn}">selected="selected"</c:if>
                                                            </c:forEach>>${list.tmpltNm}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="row">
                                                    <label for="pstTmpltNm0" class="control-label col-md-4 text-right">읽기</label>
                                                    <div class="col-md-8">
                                                        <select name="pstTmpltNm" id="pstTmpltNm0" class="form-control" ${disabled}>
                                                            <option value="">--</option>
                                                            <c:forEach items="${redngTmplats}" var="view">
                                                                <option value="0_${view.tmpltId}" <c:forEach items="${bbsDomnList}" var="bbsDomn" varStatus="status">
                                                            <c:if test="${view.tmpltId eq bbsDomn.pstTmpltNm and '0' eq bbsDomn.siteSn}">selected="selected"</c:if>
                                                            </c:forEach>>${view.tmpltNm}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="row">
                                                    <label for="inptTmpltNm0" class="control-label col-md-4 text-right">쓰기</label>
                                                    <div class="col-md-8">
                                                        <select name="inptTmpltNm" id="inptTmpltNm0" class="form-control" ${disabled}>
                                                            <option value="">--</option>
                                                            <c:forEach items="${formTmplats}" var="form">
                                                                <option value="0_${form.tmpltId}" <c:forEach items="${bbsDomnList}" var="bbsDomn" varStatus="status">
                                                            <c:if test="${form.tmpltId eq bbsDomn.inptTmpltNm and '0' eq bbsDomn.siteSn}">selected="selected"</c:if>
                                                            </c:forEach>>${form.tmpltNm}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:forEach items="${domainList}" var="domnList" varStatus="status">

                                            <c:set var="siteSn" value="" />
                                            <c:set var="disabled" value="disabled=\"disabled\"" />
                                            <c:forEach items="${bbsDomnList}" var="bbsDomn">
                                                <c:if test="${domnList.siteSn eq bbsDomn.siteSn}">
                                                    <c:set var="siteSn" value="${domnList.siteSn}" />
                                                    <c:set var="disabled" value="" />
                                                </c:if>
                                            </c:forEach>

                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label for="siteSn${status.count}" class="checkbox-inline checkbox-primary">
                                                        <input type="checkbox" name="siteSns" id="siteSn${status.count}" value="${domnList.siteSn}" class="styled" <c:if test="${domnList.siteSn eq siteSn}">checked="checked"</c:if> />
                                                        ${domnList.siteExpln}
                                                    </label>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="row">
                                                        <label for="lstTmpltNm${domnList.siteSn}" class="control-label col-md-4 text-right">목록</label>
                                                        <div class="col-md-8">
                                                            <select name="lstTmpltNm" id="lstTmpltNm${domnList.siteSn}" class="form-control" ${disabled}>
                                                                <option value="">--</option>
                                                                <c:forEach items="${listTmplats}" var="list">
                                                                    <option value="${domnList.siteSn}_${list.tmpltId}"
                                                                    <c:forEach items="${bbsDomnList}" var="bbsDomn" varStatus="status">
                                                                        <c:if test="${list.tmpltId eq bbsDomn.lstTmpltNm and domnList.siteSn eq bbsDomn.siteSn}">selected="selected"</c:if>
                                                                    </c:forEach>>${list.tmpltNm}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="row">
                                                        <label for="pstTmpltNm${domnList.siteSn}" class="control-label col-md-4 text-right">읽기</label>
                                                        <div class="col-md-8">
                                                            <select name="pstTmpltNm" id="pstTmpltNm${domnList.siteSn}" class="form-control" ${disabled}>
                                                                <option value="">--</option>
                                                                <c:forEach items="${redngTmplats}" var="view">
                                                                    <option value="${domnList.siteSn}_${view.tmpltId}"
                                                                    <c:forEach items="${bbsDomnList}" var="bbsDomn" varStatus="status">
                                                                        <c:if test="${view.tmpltId eq bbsDomn.pstTmpltNm and domnList.siteSn eq bbsDomn.siteSn}">selected="selected"</c:if>
                                                                    </c:forEach>>${view.tmpltNm}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="row">
                                                        <label for="inptTmpltNm${domnList.siteSn}" class="control-label col-md-4 text-right">쓰기</label>
                                                        <div class="col-md-8">
                                                            <select name="inptTmpltNm" id="inptTmpltNm${domnList.siteSn}" class="form-control" ${disabled}>
                                                                <option value="">--</option>
                                                                <c:forEach items="${formTmplats}" var="form">
                                                                    <option value="${domnList.siteSn}_${form.tmpltId}"
                                                                    <c:forEach items="${bbsDomnList}" var="bbsDomn" varStatus="status">
                                                                        <c:if test="${form.tmpltId eq bbsDomn.inptTmpltNm and domnList.siteSn eq bbsDomn.siteSn}">selected="selected"</c:if>
                                                                    </c:forEach>>${form.tmpltNm}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ntcUseYnY" class="control-label col-xs-2"> 공지사용여부</label>
                            <div class="col-xs-10">
                                <label for="ntcUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="ntcUseYn" id="ntcUseYnY" value="Y" <c:if test="${dataVo.ntcUseYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="ntcUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="ntcUseYn" id="ntcUseYnN" value="N" <c:if test="${dataVo.ntcUseYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="ntcUseYn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nfeedUseYnY" class="control-label col-xs-2"> FEED사용여부</label>
                            <div class="col-xs-10">
                                <label for="nfeedUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="nfeedUseYn" id="nfeedUseYnY" value="Y" <c:if test="${dataVo.nfeedUseYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="nfeedUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="nfeedUseYn" id="nfeedUseYnN" value="N" <c:if test="${dataVo.nfeedUseYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="nfeedUseYn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clsfUseYnY" class="control-label col-md-2">분류 사용여부 </label>
                            <div class="col-md-10">
                                <div class="row">
                                    <div class="col-md-8">
                                        <label for="clsfUseYnY" class="radio-inline radio-primary">
                                            <input type="radio" name="clsfUseYn" id="clsfUseYnY" value="Y" class="styled" />
                                            예
                                        </label>
                                        <label for="clsfUseYnN" class="radio-inline radio-primary">
                                            <input type="radio" name="clsfUseYn" id="clsfUseYnN" value="N" class="styled" />
                                            아니오
                                        </label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="clsfUseYn" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group" id="clConfig" <c:if test="${dataVo.clsfUseYn ne 'Y'}"> style="display:none;"</c:if>>
                            <label class="control-label col-md-2"> 분류 설정 </label>
                            <div id="clGroupDiv" class="col-md-10">
                                <div class="row block">
                                    <div class="col-md-12">
                                        <button type="button" class="btn btn-success" onclick="addClCode();">분류추가</button>
                                    </div>
                                </div>
                                <div class="row block">
                                    <div class="col-md-12">
                                        <blockquote>
                                            <strong class="text-info">앞 입력 항목에는 대분류를 입력합니다.</strong> <strong class="text-info">필요시 뒷 입력 항목에는 추가 상세 분류를 입력할 수 있습니다.</strong>
                                        </blockquote>
                                    </div>
                                </div>
                                <c:forEach items="${dataVo.clNmList}" var="clVo">
                                    <div class="row well">
                                        <div class="row">
                                            <div class="col-md-2">
                                                <input type="text" name="clsfNms" value="${clVo.clsfNm}" class="form-control" placeholder="분류 입력" />
                                            </div>
                                            <div class="col-md-1">
                                                <button type="button" class="btn btn-danger" onclick="delClsfNo(this);">삭제</button>
                                            </div>
                                            <div class="col-md-9">
                                                <input type="text" name="lwrkClsfNms" value="${clVo.lwrkClsfNm}" class="form-control tags" placeholder="하위분류명" />
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pstgUseYnY" class="control-label col-xs-2"> 게시사용여부</label>
                            <div class="col-xs-10">
                                <label for="pstgUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="pstgUseYn" id="pstgUseYnY" value="Y" <c:if test="${dataVo.pstgUseYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="pstgUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="pstgUseYn" id="pstgUseYnN" value="N" <c:if test="${dataVo.pstgUseYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="pstgUseYn" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="prvcClctAgreYn" class="control-label col-sm-2">개인정보수집</label>
                            <div class="col-md-10">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <label for="prvcClctAgreYnN" class="radio-inline radio-primary">
                                                    <input type="radio" name="prvcClctAgreYn" id="prvcClctAgreYnN" value="N" <c:if test="${dataVo.prvcClctAgreYn eq 'N'}">checked="checked"</c:if> class="styled" /> 미수집
                                                </label>
                                                <label for="prvcClctAgreYnN" class="radio-inline radio-primary">
                                                    <input type="radio" name="prvcClctAgreYn" id="prvcClctAgreYnY" value="Y" <c:if test="${dataVo.prvcClctAgreYn eq 'Y'}">checked="checked"</c:if> class="styled" /> 수집
                                                </label>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <valid:msg name="prvcClctAgreYn" />
                                                    </div>
                                                </div>
                                            </div>
                                            <label for="prvcDelSeCd" class="control-label col-sm-2">개인정보삭제일</label>
                                            <div class="col-sm-4">
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <label for="prvcDelSeCdD" class="radio-inline radio-primary">
                                                                    <input type="radio" name="prvcDelSeCd" id="prvcDelSeCdD" value="D" <c:if test="${dataVo.prvcDelSeCd eq 'D'}">checked="checked"</c:if> class="styled" /> 날짜입력
                                                                </label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="datepicker form-control" name="prvcDelYmd" id="prvcDelYmd" value="${dataVo.prvcDelYmd}" maxlength="10" <c:if test="${dataVo.prvcDelSeCd ne 'D'}">disabled="disabled"</c:if> />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-12" style="padding-top:5px;">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <label for="prvcDelSeCdY" class="radio-inline radio-primary">
                                                                    <input type="radio" name="prvcDelSeCd" id="prvcDelSeCdY" value="Y" <c:if test="${dataVo.prvcDelSeCd eq 'Y'}">checked="checked"</c:if> class="styled" /> 기간선택
                                                                </label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <select id="prvcStrgDayCnt" name="prvcStrgDayCnt" class="select" <c:if test="${dataVo.prvcDelSeCd ne 'Y'}">disabled="disabled"</c:if>>
                                                                        <option value="">- 선택 -</option>
                                                                    <c:forEach var="trm" begin="1" end="2" step="1">
                                                                        <option value="${trm}" <c:if test="${dataVo.prvcStrgDayCnt eq trm}">selected="selected"</c:if>>${trm}년</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <valid:msg name="prvcDelSeCd" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="useYnY" class="control-label col-xs-2"> 사용여부</label>
                            <div class="col-xs-10">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="useYn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 버튼 -->
                <div class="row">
                    <div class="col-xs-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success">저장</button>
                            <button type="button" class="btn btn-primary" onclick="opList('BD_selectBbsMngrList.do');">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>

    <div id="clUseDummy" style="display: none">
        <div class="row well">
            <div class="row">
                <div class="col-md-2">
                    <input type="text" name="clsfNms" value="" class="form-control" placeholder="분류명 입력" />
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-danger" onclick="delClsfNo(this);">삭제</button>
                </div>
                <div class="col-md-9">
                    <input type="text" name="lwrkClsfNms" value="" class="form-control" placeholder="하위분류명" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>
