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
<title>사용자메뉴컨텐츠 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form, ckeditor" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.cntnts.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var INSERT_URL = "<c:url value="ND_insertCmsCntnts.do" />";
    var UPDATE_URL = "<c:url value="ND_updateCmsCntnts.do" />";
    var DELETE_URL = "<c:url value="ND_deleteCmsCntnts.do" />";

    /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
    <valid:script type="msgbox" />
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            return validate();
        });

        // 창을 닫으면 부모창의 텝 정보를 리로드 한다.
        $(window).unload(function() {
            var openWin = opener;
            if(!opener && window.showModalDialog) {
                openWin = window.dialogArguments;
            }
            if(openWin && openWin.opReloadTab) {
                openWin.opReloadTab();
            }
        });

        // 컨텐츠 빌더 팝업
        $("#contentbuilderBtn").click(function() {
            opContentbuilderOpen();
        });

        // 자원관리 팝업
        $("#resrceBtn").click(function() {
            opResrceOpen();
        });

        // 연결된자원관리 상세팝업
        $("#resrceDetlBtn").click(function() {
            var dataSn = $("#dataSn").val();
            var chgCycl = $("#chgCycl").val();
            opResrceDetlOpen(dataSn, chgCycl);
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    /* 작성방법에 따른 화면 변경 */
    // ckeditor instance
    var editor = null;
    var opChangeWritngMth = function(el) {
        var wrtTypeNm = $(el).val();
        opEditorDestroy();
        if(wrtTypeNm == "textarea") {
            $("#resrceBlock").hide();
            $("#contentbuilderBlock").hide();
        } else if(wrtTypeNm == "editor") {
            editor = $("#mainContsCn").ckeditor({
                toolbar : toolbar_config.default_toolbar,
                height : 500,
                contentsCss : "${baseVo.cssFileNm}"
            }).editor;
            $("#resrceBlock").hide();
            $("#contentbuilderBlock").hide();
        } else if(wrtTypeNm == "contentbuilder") {
            $("#resrceBlock").hide();
            $("#contentbuilderBlock").show();
        } else if(wrtTypeNm == "resrce") {
            $("#resrceBlock").show();
            $("#contentbuilderBlock").hide();
        }
    };

    // 에디터 객체 삭제
    var opEditorDestroy = function() {
        if(editor) {
            editor.destroy();
            editor = null;
        }
    };

    //]]>
</script>
</head>
<body>
    <form name="contentbuilderForm" id="contentbuilderForm" target="_blank" method="post" action="/component/contentbuilder/ND_selectEditor.do">
        <input type="hidden" name="q_cssFileNm" id="q_cssFileNm" value="${baseVo.cssFileNm}" class="sr-only" />
        <textarea name="q_content" id="q_content" class="sr-only" placeholder="본문내용">${dataVo.mainContsCn}</textarea>
        <textarea name="q_header" id="q_header" class="sr-only" placeholder="상단내용">${dataVo.strtContsCn}</textarea>
        <button type="submit" class="sr-only">편집</button>
    </form>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertCmsCntnts.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="siteSn" id="siteSn" value="${baseVo.siteSn}" />
        <input type="hidden" name="userMenuEngNm" id="userMenuEngNm" value="${baseVo.userMenuEngNm}" />
        <input type="hidden" name="lytCdNo" id="lytCdNo" value="${baseVo.lytCdNo}" />
        <input type="hidden" name="contsSn" id="contsSn" value="${baseVo.contsSn}" />

        <input type="hidden" name="dataSn" id="dataSn" value="${dataVo.dataSn}" />
        <input type="hidden" name="chgCycl" id="chgCycl" value="${dataVo.chgCycl}" />

        <input type="hidden" name="aprvSttsNo" id="aprvSttsNo" value="${dataVo.aprvSttsNo}" />

        <div class="panel panel-default">
            <div class="panel-body">

                <div class="form-group">
                    <label class="control-label col-sm-2">메뉴정보 </label>
                    <div class="col-sm-10">
                        <p class="form-control-static">
                            <strong>[ <span class="text-info">${baseVo.siteExpln} &nbsp;&nbsp;&nbsp; ${baseVo.siteNm}</span> ]
                            </strong> ${baseVo.menuPathNm}
                        </p>
                    </div>
                </div>

                <c:if test="${not empty dataVo.aprvSttsNo and dataVo.aprvSttsNo ne 'C1010'}">
                    <div class="form-group">
                        <label class="control-label col-sm-2">승인정보 </label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="table-responsive">
                                        <!-- 리스트 -->
                                        <table class="table table-bordered table-striped table-hover" summary="승인상태 정보를 제공합니다.">
                                            <caption class="hidden">승인정보</caption>
                                            <colgroup>
                                                <col width="15%" />
                                                <col width="35%" />
                                                <col width="15%" />
                                                <col width="35%" />
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                    <th>승인상태</th>
                                                    <td>${dataVo.confmSttusNm}</td>
                                                    <th>적용여부</th>
                                                    <td>${dataVo.aplcnYn}</td>
                                                </tr>
                                                <tr>
                                                    <th>승인일시</th>
                                                    <td>
                                                        <fmt:formatDate value="${dataVo.aprvDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                    </td>
                                                    <th>승인자</th>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${dataVo.aprvSttsNo eq 'C1050'}">
                                                                자동승인
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${dataVo.confmerNm}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>발행사유</th>
                                                    <td>
                                                        <op:nrToBr content="${dataVo.pblcnRsn}" />
                                                    </td>
                                                    <th>승인내용</th>
                                                    <td>
                                                        <op:nrToBr content="${dataVo.aprvRsn}" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="strtContsCn" class="control-label col-sm-2">상단컨텐츠 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static">
                                    <strong><span class="text-info">HTML 코드중 &lt;head&gt; &lt;/head&gt; 태그 사이에 위치될 css 또는 javascript 코드를 입력하세요.</span></strong>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="strtContsCn" id="strtContsCn" rows="5" cols="80" class="form-control" placeholder="상단컨텐츠"><c:out value="${dataVo.strtContsCn}"/></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="wrtTypeNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 작성방법
                    </label>
                    <div class="col-sm-10">
                        <c:set var="wrtTypeNm" value="${dataVo.wrtTypeNm}" />
                        <c:if test="${empty wrtTypeNm }">
                            <c:set var="wrtTypeNm" value="textarea" />
                        </c:if>
                        <op:cdId type="radio" hghrkCdId="CMS" cdId="wrtTypeNm" id="wrtTypeNm" click="opChangeWritngMth(this);" values="${wrtTypeNm}" />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="wrtTypeNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mainContsCn" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 본문컨텐츠
                    </label>
                    <div class="col-sm-10">
                        <div id="contentbuilderBlock" class="block row" <c:if test="${dataVo.wrtTypeNm ne 'contentbuilder'}">style="display:none;"</c:if>>
                            <div class="col-sm-12">
                                <button type="button" id="contentbuilderBtn" class="btn btn-xs btn-info">컨텐츠빌더를 이용하여 편집하기</button>
                            </div>
                        </div>
                        <div id="resrceBlock" class="block row" <c:if test="${dataVo.wrtTypeNm ne 'resrce'}">style="display:none;"</c:if>>
                            <div class="col-sm-12">
                                <button type="button" id="resrceBtn" class="btn btn-xs btn-info">자원관리에서 가져오기</button>
                                <button type="button" id="resrceDetlBtn" class="btn btn-xs btn-info">자원관리화면보기</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <p>
                                    <strong><span class="text-info">본문영역에 해당되는 컨텐츠를 입력합니다.</span></strong>
                                </p>
                                <p>
                                    <strong><span class="text-info">적용중인 CSS 파일</span> : ${baseVo.cssFileNm}</strong>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="mainContsCn" id="mainContsCn" rows="30" cols="80" class="form-control" placeholder="본문컨텐츠"><c:out value="${dataVo.mainContsCn}"/></textarea>
                                <c:if test="${dataVo.wrtTypeNm eq 'editor'}">
                                    <script type="text/javascript">
                                        //<![CDATA[
                                        /* 공통 초기화 기능 */
                                        $(document).ready(function() {
                                            editor = $("#mainContsCn").ckeditor({
                                                toolbar : toolbar_config.default_toolbar,
                                                height : 500,
                                                contentsCss : "${baseVo.cssFileNm}"
                                            }).editor;
                                        });
                                        //]]>
                                    </script>
                                </c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="mainContsCn" />
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${dataVo.aprvSttsNo eq 'C1010'}">
                    <div class="form-group">
                        <label for="pblcnRsn" class="control-label col-sm-2">
                            발행사유 <span class="mandatory">*</span>
                        </label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-sm-12">
                                    <textarea name="pblcnRsn" id="pblcnRsn" rows="3" cols="80" class="form-control" placeholder="발행사유">${dataVo.pblcnRsn}</textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <valid:msg name="pblcnRsn" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-left">
                    <c:choose>
                        <c:when test="${baseVo.aprvYn eq 'N' and dataVo.aprvSttsNo eq 'C1010'}">
                            <button type="button" class="btn btn-info" onclick="deployCmsCntnts();">자동발행</button>
                            <span class="text-info"><strong>자동발행은 실제 컨텐츠로 배포되므로 주의 하시기 바랍니다.</strong></span>
                        </c:when>
                        <c:when test="${dataVo.aprvSttsNo eq 'C1010'}">
                            <button type="button" class="btn btn-info" onclick="deployRequestCmsCntnts();">발행요청</button>
                        </c:when>
                        <c:when test="${dataVo.aprvSttsNo eq 'C1020'}">
                            <button type="button" class="btn btn-info" onclick="deployCancelCmsCntnts();">발행요청취소</button>
                        </c:when>
                    </c:choose>
                </div>
                <div class="pull-right">
                    <c:choose>
                        <c:when test="${dataVo.aprvSttsNo eq 'C1010'}">
                            <button type="button" class="btn btn-success" onclick="updateCmsCntnts();">수정</button>
                            <button type="button" class="btn btn-danger" onclick="deleteCmsCntnts();">삭제</button>
                        </c:when>
                        <c:when test="${dataVo.aprvSttsNo ne 'C1010' and dataVo.aprvSttsNo ne 'C1020'}">
                            <button type="submit" class="btn btn-success">현재컨텐츠로 신규생성</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

</body>
</html>