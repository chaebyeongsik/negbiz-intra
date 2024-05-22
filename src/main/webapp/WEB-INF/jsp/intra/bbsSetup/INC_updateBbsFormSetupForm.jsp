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
<%@ page import="zesinc.intra.bbsSetup.support.BbsSetupConstant" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판환경설정 관리</title>

<op:jsTag type="intra" items="opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {

            var atchFileUseYn = $("input[name='atchFileUseYn']:checked").val();
            if(atchFileUseYn == 'Y') {
                var target = $("#fileLmtCnt");
                if(!target.notnullValidator() || !target.digitsValidator() || !target.rangeValidator(0,99)) {
                    opWarningMsg("업로드 파일 최대 갯수는 숫자 2자리 이하로 필수입력 항목입니다.");
                    target.focus();
                    return false;
                }
                target = $("#lmtFileSz");
                if(!target.notnullValidator() || !target.digitsValidator() || !target.rangeValidator(0,99)) {
                    opWarningMsg("파일당 최대 용량은 숫자 2자리 이하로 필수입력 항목입니다.");
                    target.focus();
                    return false;
                }
                target = $("#wholUldSz");
                if(!target.notnullValidator() || !target.digitsValidator() || !target.rangeValidator(0,999)) {
                    opWarningMsg("업로드 전체 파일 용량은 숫자 3자리 이하로 필수입력 항목입니다.");
                    target.focus();
                    return false;
                }
            }
            target = $("#phbwdCdId");
            if(!target.requirefromValidator("phbwdUseYn", "Y", "금지단어사용여부")) {
                opWarningMsg("금지단어를 사용하는 경우 금지단어코드는 필수입력 항목입니다.");
                target.focus();
                return false;
            }

            $(this).ajaxSubmit({
                url: "ND_updateBbsFormSetup.do",
                type: "POST",
                dataType: "json",
                success: function(response){
                    try {
                        opJsonMsg(response);
                    } catch (e) {
                        // 시스템 오류 발생시 처리 구간
                        opErrorMsg(response, e);
                        return;
                    }
                }
            });
            return false;
        });
        
        // radio 버튼 선택 값 설정
        opChecked("mngrEdtrUseYn", "${dataVo.mngrEdtrUseYn}", 'N');
        opChecked("userEdtrUseYn", "${dataVo.userEdtrUseYn}", 'N');
        opChecked("atchFileUseYn", "${dataVo.atchFileUseYn}", 'N');
        opChecked("cchaUseYn", "${dataVo.cchaUseYn}", 'N');
        opChecked("rlsYn", "${dataVo.rlsYn}", 'N');
        opChecked("phbwdUseYn", "${dataVo.phbwdUseYn}", 'N');
        
        $("input[name=atchFileUseYn]").click(function() {
            if (this.value == 'Y') {
                $("#atchmnflSetup").show();
                $("#fileLmtCnt").prop("disabled", false);
                $("#lmtFileSz").prop("disabled", false);
                $("#wholUldSz").prop("disabled", false);
                $("#prmsnFileExtnMttr").prop("disabled", false);
            } else {
                $("#atchmnflSetup").hide();
                $("#fileLmtCnt").prop("disabled", true);
                $("#lmtFileSz").prop("disabled", true);
                $("#wholUldSz").prop("disabled", true);
                $("#prmsnFileExtnMttr").prop("disabled", true);
            }
        });
        
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var valid = true;

        var phbwdUseYn = $("#phbwdUseYnY").prop("checked");
        alert(phbwdUseYn);
        if(phbwdUseYn) {
            var phbwdCdId = $.trim($("#phbwdCdId").val());
            if(phbwdCdId == "") {
                opWarningMsg("금지단어를 사용하는 경우 금지단어코드는 필수입력 항목입니다.");
                valid = false;
            }
        }

        return valid;
    };

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateBbsFormSetup.do" class="form-horizontal">
        <input type="hidden" id="seCdId" name="seCdId" value="<%= BbsSetupConstant.SECTION_CODE_FORM %>" />
        <input type="hidden" id="bbsStngSn" name="bbsStngSn" value="${dataVo.bbsStngSn}" />
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="dclrUseYnY" class="control-label col-md-2">웹에디터 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row block">
                            <label for="mngrEdtrUseYnY" class="control-label col-md-2">관리자단 </label>
                            <div class="col-md-3">
                                <label for="mngrEdtrUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="mngrEdtrUseYn" id="mngrEdtrUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="mngrEdtrUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="mngrEdtrUseYn" id="mngrEdtrUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 관리자 화면에서 글 등록/수정 시 웹에디터를 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <label for="userEdtrUseYnY" class="control-label col-md-2">사용자단 </label>
                            <div class="col-md-3">
                                <label for="userEdtrUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="userEdtrUseYn" id="userEdtrUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="userEdtrUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="userEdtrUseYn" id="userEdtrUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 사용자 화면에서 글 등록/수정 시 웹에디터를 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="atchFileUseYnY" class="control-label col-md-2">첨부파일 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row block">
                            <div class="col-md-3">
                                <label for="atchFileUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="atchFileUseYn" id="atchFileUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="atchFileUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="atchFileUseYn" id="atchFileUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 글 등록/수정 시 첨부파일을 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div id="atchmnflSetup" <c:if test="${dataVo.atchFileUseYn eq 'N'}">style="display:none;"</c:if>>
                            <div class="row">
                                <label for="fileLmtCnt" class="control-label col-sm-3">- 업로드 파일 최대 갯수 </label>
                                <div class="col-sm-1">
                                    <input type="text" name="fileLmtCnt" id="fileLmtCnt" value="${dataVo.fileLmtCnt}" class="form-control" />
                                </div>
                                <div class="col-sm-7">
                                    <div class="help-block op-validate">
                                        <ul class="list-group">
                                            <li class="list-group-item"><span class="validate-msg">- 한 게시물당 첨부할 수 있는 최대 파일 갯수</span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label for="lmtFileSz" class="control-label col-sm-3">- 파일당 최대 용량 </label>
                                <div class="col-sm-1">
                                    <input type="text" name="lmtFileSz" id="lmtFileSz" value="${dataVo.lmtFileSz}" class="form-control" />
                                </div>
                                <div class="col-sm-7">
                                    <div class="help-block op-validate">
                                        <ul class="list-group">
                                            <li class="list-group-item"><span class="validate-msg">단위 : Mb(메가바이트)</span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label for="wholUldSz" class="control-label col-sm-3">- 업로드 전체 파일 용량 </label>
                                <div class="col-sm-1">
                                    <input type="text" name="wholUldSz" id="wholUldSz" value="${dataVo.wholUldSz}" class="form-control" />
                                </div>
                                <div class="col-sm-7">
                                    <div class="help-block op-validate">
                                        <ul class="list-group">
                                            <li class="list-group-item"><span class="validate-msg">단위 : Mb(메가바이트)</span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label for="prmsnFileExtnMttr" class="control-label col-sm-3">- 첨부파일 허용 확장자 </label>
                                <div class="col-sm-7">
                                    <input type="text" name="prmsnFileExtnMttr" id="prmsnFileExtnMttr" value="${dataVo.prmsnFileExtnMttr}" class="form-control" />
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="help-block op-validate">
                                                <ul class="list-group">
                                                    <li class="list-group-item"><span class="validate-msg">- 허용할 첨부파일 확장자를<strong>띄어쓰기 없이 쉼표 문자( ',' )</strong>로 구분하여 등록하세요. (ex : jpg,gif,xls).</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="help-block op-validate">
                                        <ul class="list-group">
                                            <li class="list-group-item"><span class="validate-msg">최대 90자</span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cchaUseYnY" class="control-label col-md-2">CAPTCHA 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="cchaUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="cchaUseYn" id="cchaUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="cchaUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="cchaUseYn" id="cchaUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phbwdUseYnY" class="control-label col-md-2">금지단어 사용여부 </label>
                    <div class="col-md-4">
                        <label for="phbwdUseYnY" class="radio-inline radio-primary">
                            <input type="radio" name="phbwdUseYn" id="phbwdUseYnY" value="Y" class="styled" />
                            예
                        </label>
                        <label for="phbwdUseYnN" class="radio-inline radio-primary">
                            <input type="radio" name="phbwdUseYn" id="phbwdUseYnN" value="N" class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 글 등록/수정 시 제한할 금지단어를 체크해야 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <label for="phbwdCdId" class="control-label col-md-2">금지단어코드 </label>
                    <div class="col-md-4">
                        <input type="text" name="phbwdCdId" id="phbwdCdId" value="${dataVo.phbwdCdId}" class="form-control" />
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 금지단어 관리에서 등록한 금지단어 코드를 입력하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<%--
                <div class="form-group">
                    <label for="rlsYnY" class="control-label col-md-2">비공개글 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="rlsYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="rlsYn" id="rlsYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="rlsYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="rlsYn" id="rlsYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 비공개글 설정을 사용하려면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
--%>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="block clearfix">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-success">저장</button>
    
                        <button type="button" class="btn btn-primary" onclick="opList('BD_selectBbsSetupList.do');">목록</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>