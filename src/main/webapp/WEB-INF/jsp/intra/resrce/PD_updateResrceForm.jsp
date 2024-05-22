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

<op:jsTag type="intra" items="opform, opvalidate, modal" />
<op:jsTag type="libs" items="tags, ckeditor, multifile" />

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
        opChecked("wrtTypeNm", "${dataVo.wrtTypeNm}", "textarea");
        opChecked("dataTypeNo", "${dataVo.dataTypeNo}", "1020");
        opChecked("dataDtlTypeNm", "${dataVo.dataDtlTypeNm}", "2020");
        opChecked("rlsYn", "${dataVo.rlsYn}", "N");
        opChecked("useYn", "${dataVo.useYn}", "Y");
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

    /* 작성방법에 따른 화면 변경 */
    // ckeditor instance
    var editor = null;
    var cssFiles =
        [
<c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
    <c:if test="${fileVo.fileExtnNm eq 'css'}">
    '${fileVo.fileUrlAddr}',
    </c:if>
</c:forEach>
];
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
                height : 500,
                contentsCss : cssFiles
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

    /* modal창 닫기 */
    var opCloseWin = function() {
        parent.opList("INC_selectResrceList.do");
        parent.$.fancybox.close();
    }

    //]]>
</script>
</head>
<body>
    <form name="contentbuilderForm" id="contentbuilderForm" target="_blank" method="post" action="/component/contentbuilder/ND_selectResrceEditor.do">
        <c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
            <c:if test="${fileVo.fileExtnNm eq 'css'}">
                <input type="hidden" name="q_cssFileNm" id="q_cssFileNm" value="${fileVo.fileUrlAddr}" />
            </c:if>
        </c:forEach>

        <textarea name="q_content" id="q_content" class="sr-only" placeholder="본문내용">${dataVo.contsCn}</textarea>
        <button type="submit" class="sr-only">편집</button>
    </form>

    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateResrce.do" class="form-horizontal">
        <input type="hidden" name="ctgrySn" id="ctgrySn" value="${dataVo.ctgrySn}" />
        <input type="hidden" name="q_ctgrySn" id="q_ctgrySn" value="${dataVo.ctgrySn}" />

        <input type="hidden" name="dataSn" id="dataSn" value="${dataVo.dataSn}" />
        <input type="hidden" name="chgCycl" id="chgCycl" value="${dataVo.chgCycl}" />
        <input type="hidden" name="q_dataSn" id="q_dataSn" value="${dataVo.dataSn}" />
        <input type="hidden" name="q_chgCycl" id="q_chgCycl" value="${dataVo.chgCycl}" />

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
                    <label for="dataTypeNo" class="control-label col-sm-2">자원유형</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <code><op:cdId hghrkCdId="resrce" cdId="${dataVo.dataTypeNo}"/></code>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dataDtlTypeNm" class="control-label col-sm-2">자원상세유형</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static">
                                    <code><op:cdId hghrkCdId="resrce" cdId="${dataVo.dataDtlTypeNm}"/></code>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${dataVo.dataTypeNo eq '1010'}">
                        <div class="form-group">
                            <label for="fileSn" class="control-label col-sm-2"> 파일첨부</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
                                            <a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${fileVo.dataSn}&amp;chgCycl=${fileVo.chgCycl}&amp;fileSn=${fileVo.fileSn}" title="${fileVo.orgnlFileNm}">${fileVo.orgnlFileNm}</a>
                                            <span class="text-success">(${fileVo.fileSzNm}, ${fileVo.fileTypeNm})</span>
                                            <button type="button" class="btn btn-xs btn-info" onclick="opResrceFileThumbs('${fileVo.dataSn}', '${fileVo.chgCycl}', '${fileVo.fileSn}');">상세보기</button>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="row block">
                                    <div class="col-sm-6">
                                        <input type="file" name="fileSn" id="fileSn" value="" style="width: 100%;"  />
                                        <input type="text" name="fileExpln" value="" maxlength="100" class="form-control" placeholder="파일설명" />
                                    </div>
                                </div>
                                <c:if test="${not empty dataVo.fileList}">
                                    <blockquote style="font-size: 9pt;">
                                        <p>
                                            <strong>첨부파일의 아래 링크정보를 복사하여 컨텐츠 본문에 사용할 수 있습니다. '상세보기'로 파일정보를 확인하세요.</strong>
                                        </p>
                                        <p>
                                            <c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${fileVo.fileTypeNm eq 'image/jpeg'}">
                                                        &lt;img src="${fileVo.fileUrlAddr}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                        &lt;img src="${fileVo.thmbPathNm1}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                        &lt;img src="${fileVo.thmbPathNm2}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                        &lt;img src="${fileVo.thmbPathNm3}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                    </c:when>
                                                    <c:when test="${fileVo.fileExtnNm eq 'css'}">
                                                        &lt;link rel="stylesheet" type="text/css" href="${fileVo.fileUrlAddr}" /&gt;<br />
                                                    </c:when>
                                                    <c:when test="${fileVo.fileExtnNm eq 'js'}">
                                                        &lt;script type="text/javascript" src="${fileVo.fileUrlAddr}"&gt;&lt;/script&gt;<br />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${not empty fileVo.fileExpln}">
                                                                <c:set var="title" value="${fileVo.fileExpln}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="title" value="${fileVo.orgnlFileNm}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                        &lt;a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${fileVo.dataSn}&amp;chgCycl=${fileVo.chgCycl}&amp;fileSn=${fileVo.fileSn}" title="${title} 파일받기"&gt;다운로드&lt;/a&gt;<br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </p>
                                    </blockquote>
                                </c:if>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="help-block">
                                            <span class="text-info">※ 신규로 파일을 선택하시면 기존파일을 덮어씁니다.</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="fileSn" class="control-label col-sm-2"> 파일첨부</label>
                            <div class="col-sm-10">
                                <c:if test="${not empty dataVo.fileList}">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <p class="text-info form-control-static">
                                                기존 첨부파일을 <span class="text-danger">삭제</span>하시려면 아래 <span class="text-danger">체크박스를 선택</span>하세요.
                                                <button type="button" class="btn btn-primary btn-xs" onclick="opChangeResrceFileInfo('${dataVo.dataSn}', '${dataVo.chgCycl}');">정보수정</button>
                                            </p>
                                            <ul>
                                                <c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
                                                    <li>
                                                        <label for="fileSns${index.count}" class="checkbox-inline checkbox-primary">
                                                            <input type="checkbox" name="fileSns" id="fileSns${index.count}" value="${fileVo.fileSn }" class="styled" />
                                                        </label>
                                                        <a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${fileVo.dataSn}&amp;chgCycl=${fileVo.chgCycl}&amp;fileSn=${fileVo.fileSn}" title="${fileVo.orgnlFileNm}">${fileVo.orgnlFileNm}</a>
                                                        <span class="text-success">(${fileVo.fileSzNm}, ${fileVo.fileTypeNm})</span>
                                                        <button type="button" class="btn btn-xs btn-info" onclick="opResrceFileThumbs('${fileVo.dataSn}', '${fileVo.chgCycl}', '${fileVo.fileSn}');">상세보기</button>
                                                        <br />
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="row block">
                                    <div id="attFileLocationDiv" class="col-sm-6">
                                        <input type="file" name="resrceFile" id="resrceFile" value="" style="width: 100%;" />
                                        <input type="text" name="fileExpln" value="" maxlength="100" class="form-control" placeholder="파일설명" />
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-info btn-xs" onclick="addResrceFile(this);">파일추가</button>
                                    </div>
                                </div>
                                <c:if test="${not empty dataVo.fileList}">
                                    <blockquote style="font-size: 9pt;">
                                        <p>
                                            <strong>첨부파일의 아래 링크정보를 복사하여 컨텐츠 본문에 사용할 수 있습니다. '상세보기'로 파일정보를 확인하세요.</strong>
                                        </p>
                                        <p>
                                            <c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${fileVo.fileTypeNm eq 'image/jpeg'}">
                                                        &lt;img src="${fileVo.fileUrlAddr}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                        &lt;img src="${fileVo.thmbPathNm1}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                        &lt;img src="${fileVo.thmbPathNm2}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                        &lt;img src="${fileVo.thmbPathNm3}" alt="${fileVo.fileExpln}" /&gt;<br />
                                                    </c:when>
                                                    <c:when test="${fileVo.fileExtnNm eq 'css'}">
                                                        &lt;link rel="stylesheet" type="text/css" href="${fileVo.fileUrlAddr}" /&gt;<br />
                                                    </c:when>
                                                    <c:when test="${fileVo.fileExtnNm eq 'js'}">
                                                        &lt;script type="text/javascript" src="${fileVo.fileUrlAddr}"&gt;&lt;/script&gt;<br />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${not empty fileVo.fileExpln}">
                                                                <c:set var="title" value="${fileVo.fileExpln}" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="title" value="${fileVo.orgnlFileNm}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                        &lt;a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${fileVo.dataSn}&amp;chgCycl=${fileVo.chgCycl}&amp;fileSn=${fileVo.fileSn}" title="${title} 파일받기"&gt;다운로드&lt;/a&gt;<br />
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </p>
                                    </blockquote>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
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
                        <div class="form-group">
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
                                                        height : 500,
                                                        contentsCss : cssFiles
                                                    }).editor;
                                                    alert(cssFiles);
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
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <label for="dataExpln" class="control-label col-sm-2"> 자원설명</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="dataExpln" id="dataExpln" rows="5" cols="80" class="form-control">${dataVo.dataExpln}</textarea>
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
                    <op:auth picId="${dataVo.rgtrId}">
                        <button type="submit" class="btn btn-success">저장</button>
                        <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteResrce.do');">삭제</button>
                    </op:auth>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
    <div id="addResrceFileDummy" style="display: none;">
        <div class="row">
            <div class="col-sm-12">
                <input type="file" name="resrceFile" value="" style="width: 100%;" />
                <input type="text" name="fileExpln" value="" maxlength="100" class="form-control" placeholder="파일설명" />
            </div>
        </div>
    </div>
</body>
</html>