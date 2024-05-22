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
<title>자원 등록</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="tags, ckeditor" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/resrce/js/resrce.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        // 키워드 태그 적용
        $('#dataSrchNm').tagsInput({
            width : '100%',
            defaultText : '키워드 입력'
        });

        // 컨텐츠빌더 팝업
        $("#contentbuilderBtn").click(function() {
            opContentbuilderOpen();
        });

        // 기본값 설정
        opChecked("wrtTypeNm", "textarea", "textarea");
        opChecked("rlsYn", "N", "N");
        opChecked("useYn", "Y", "Y");

        // 자원유형에 따른 초기화
        opChangeResrceTy();
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var dataTypeNo = $("input[name='dataTypeNo']:checked").val();
        if(dataTypeNo == "1030") {
            var dataDtlTypeNm = $("input[name='dataDtlTypeNm']:checked").val();
            if(dataDtlTypeNm != "2020") {
                opWarningMsg("자원유형이 '템플릿'인 경우 자원상세유형은 HTML만 선택할 수 있습니다.");
                return false;
            }
        }

        var dataTypeNo = $("input[name='dataTypeNo']:checked").val();
        if(dataTypeNo == "1020" || dataTypeNo == "1030") {
            var contsCn = $("#contsCn").val();
            if($.trim(contsCn) == "") {
                opWarningMsg("자원유형이 컨텐츠 또는 템플릿의 경우 컨텐츠내용을 입력해야 합니다.");
                return false;
            }
        }

        return true;
    };

    // 컨텐츠유형에 따른 토글
    var opChangeResrceTy = function() {
        var dataTypeNo = $("input[name='dataTypeNo']:checked").val();
        if(dataTypeNo == "1010") {
            // 파일자원
            $(".resrceTyFile").show();
            $(".resrceTyNotFile").hide();
            $(".contResrce").each(function() {
                $(this).val("");
            });
        } else if(dataTypeNo == "1020" || dataTypeNo == "1030") {
            // 컨텐츠자원
            $(".resrceTyNotFile").show();
            $(".resrceTyFile").hide();
            $(".fileResrce").each(function() {
                $(this).val("");
            });
        }
    };

    /* 작성방법에 따른 화면 변경 */
    // ckeditor instance
    var editor = null;
    var opChangeWritngMth = function(el) {
        var wrtTypeNm = $(el).val();
        if(wrtTypeNm == "textarea") {
            if(editor) {
                editor.destroy();
                editor = null;
            }
            $("#contentbuilderBlock").hide();
        } else if(wrtTypeNm == "editor") {
            editor = $("#contsCn").ckeditor({
                toolbar : toolbar_config.default_toolbar,
                height : 500
            //,contentsCss : "${baseVo.cssFileNm}"
            }).editor;
            $("#contentbuilderBlock").hide();
        } else if(wrtTypeNm == "contentbuilder") {
            if(editor) {
                editor.destroy();
                editor = null;
            }
            $("#contentbuilderBlock").show();
        }
    };

    /* 컨텐츠빌더 화면을 새창으로 띄움 */
    var opContentbuilderOpen = function() {
        var contsCn = $("#dataForm #contsCn").val();
        $("#contentbuilderForm #q_content").val(contsCn);

        $("#contentbuilderForm").submit();
    };

    //]]>
</script>
</head>
<body>
    <form name="contentbuilderForm" id="contentbuilderForm" target="_blank" method="post" action="/component/contentbuilder/ND_selectResrceEditor.do">
        <textarea name="q_content" id="q_content" class="sr-only" placeholder="본문내용">${dataVo.contsCn}</textarea>
        <button type="submit" class="sr-only">편집</button>
    </form>

    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_insertResrce.do" class="form-horizontal">
        <input type="hidden" name="q_ctgrySn" id="q_ctgrySn" value="${paramMap.q_ctgrySn}" />
        <input type="hidden" name="ctgrySn" id="ctgrySn" value="${paramMap.q_ctgrySn}" />

        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="ttl" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 제목
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="ttl" id="ttl" value="${dataVo.ttl}" maxlength="500" class="form-control" />
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
                    <label for="dataTypeNo" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 자원유형
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <op:cdId type="radio" hghrkCdId="resrce" cdId="dataTypeNo" id="dataTypeNo" values="${dataVo.dataTypeNo}" click="opChangeResrceTy(this);" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="help-block">
                                    <span class="text-danger">※ 자원유형은 최초 설정 후 변경할 수 없습니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dataDtlTypeNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 자원상세유형
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:cdId type="radio" hghrkCdId="resrce" cdId="dataDtlTypeNm" id="dataDtlTypeNm" values="${dataVo.dataDtlTypeNm}" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="help-block">
                                    <span class="text-danger">※ 자원상세유형은 최초 설정 후 변경할 수 없습니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group resrceTyFile" style="display: none;">
                    <label for="fileSn" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 파일첨부
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="file" name="fileSn" id="fileSn" class="fileResrce" value="" style="width: 100%;" />
                                <input type="text" name="fileExpln" value="" maxlength="100" class="form-control" placeholder="파일설명" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group resrceTyNotFile" style="display: none;">
                    <label for="resrceFile" class="control-label col-sm-2"> 파일첨부</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="row block">
                                    <div id="attFileLocationDiv" class="col-sm-6">
                                        <input type="file" name="resrceFile" id="resrceFile" class="contResrce" value="" style="width: 100%;" />
                                        <input type="text" name="fileExpln" value="" maxlength="100" class="form-control contResrce" placeholder="파일설명" />
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-info btn-xs" onclick="addResrceFile(this);">파일추가</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group resrceTyNotFile" style="display: none;">
                    <label for="wrtTypeNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 작성방법
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:cdId type="radio" hghrkCdId="resrce" cdId="wrtTypeNm" id="wrtTypeNm" values="${dataVo.wrtTypeNm}" click="opChangeWritngMth(this);" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group resrceTyNotFile" style="display: none;">
                    <label for="contsCn" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 컨텐츠
                    </label>
                    <div class="col-sm-10">
                        <div id="contentbuilderBlock" class="block row" <c:if test="${dataVo.wrtTypeNm ne 'contentbuilder'}">style="display:none;"</c:if>>
                            <div class="col-sm-12">
                                <button type="button" id="contentbuilderBtn" class="btn btn-xs btn-info">컨텐츠빌더를 이용하여 편집하기</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="contsCn" id="contsCn" rows="20" cols="80" class="form-control" placeholder="컨텐츠">${dataVo.contsCn}</textarea>
                                <c:if test="${dataVo.wrtTypeNm eq 'editor'}">
                                    <script type="text/javascript">
                                        //<![CDATA[
                                        /* 공통 초기화 기능 */
                                        $(document).ready(function() {
                                            editor = $("#contsCn").ckeditor({
                                                toolbar : toolbar_config.default_toolbar,
                                                height : 400
                                            //,contentsCss : "${baseVo.cssFileNm}"
                                            }).editor;
                                        });
                                        //]]>
                                    </script>
                                </c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="contsCn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dataExpln" class="control-label col-sm-2"> 자원설명</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="dataExpln" id="dataExpln" rows="3" cols="80" class="form-control">${dataVo.dataExpln}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="help-block">
                                    <span class="text-info">※ 자원공개시 다른 사용자에게 전달하고자 하는 내용을 입력하세요.</span><br /> <span>최대 길이에 맞게 입력하세요. (2000자)</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dataSrchNm" class="control-label col-sm-2"> 자원키워드</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="text" name="dataSrchNm" id="dataSrchNm" value="${dataVo.dataSrchNm}" maxlength="300" class="form-control tags" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="help-block">
                                    <span class="text-info">※ 검색에 사용됩니다. 키워드를 입력 후 <code>엔터키</code>를 누르세요.
                                    </span> <br /> <span>최대 길이에 맞게 입력하세요. (1000자)</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rlsYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 공개여부
                    </label>
                    <div class="col-sm-10">
                        <label for="rlsYnY" class="radio-inline radio-primary">
                            <input type="radio" name="rlsYn" id="rlsYnY" value="Y" <c:if test="${dataVo.rlsYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="rlsYnN" class="radio-inline radio-primary">
                            <input type="radio" name="rlsYn" id="rlsYnN" value="N" <c:if test="${dataVo.rlsYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="rlsYn" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 사용여부
                    </label>
                    <div class="col-sm-10">
                        <label for="useYnY" class="radio-inline radio-primary">
                            <input type="radio" name="useYn" id="useYnY" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="useYnN" class="radio-inline radio-primary">
                            <input type="radio" name="useYn" id="useYnN" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="useYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="srcNm" class="control-label col-sm-2"> 출처</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="text" name="srcNm" id="srcNm" value="${dataVo.srcNm}" maxlength="100" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="srcNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="srcExpln" class="control-label col-sm-2"> 출처설명</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="srcExpln" id="srcExpln" rows="5" cols="80" class="form-control">${dataVo.srcExpln}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="srcExpln" />
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

    <div id="addResrceFileDummy" style="display: none;">
        <div class="row">
            <div class="col-sm-12">
                <input type="file" name="resrceFile" class="contResrce" value="" style="width: 100%;" />
                <input type="text" name="fileExpln" value="" maxlength="100" class="form-control contResrce" placeholder="파일설명" />
            </div>
        </div>
    </div>
</body>
</html>