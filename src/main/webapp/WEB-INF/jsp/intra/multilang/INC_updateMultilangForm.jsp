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
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });

        // 라디오 버튼 자동 선택
        opChecked("useYn", "${dataVo.useYn}", "Y");
    });

    // 국가언어별 메시지 길이 체크
    var customValidate = function() {
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

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateMultilang.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="upMtlngCdNm" class="control-label col-sm-2"> 상위다국어설명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static">
                                    <c:choose>
                                        <c:when test="${dataVo.upMtlngCdNm != 'dummy'}">
                                            <op:nrToBr content="${dataVo.upMtlngCdNmDc}" />
                                        </c:when>
                                        <c:otherwise>최상위코드</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 다국어코드
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <strong><code>${dataVo.mtlngCdNm}</code></strong>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <blockquote>
                                    <p>
                                        JAVA 사용 예 :<br />
                                        <code>MessageUtil.getMessage(&#34;${dataVo.mtlngCdNm}&#34;);</code>
                                    </p>
                                    <p>
                                        JSP 사용 예 :<br />
                                        <code>&lt;op:message code="${dataVo.mtlngCdNm}" /&gt;</code>
                                        <br />
                                        <code>&lt;op:message code="&#36;{dataVo.멤버변수}" /&gt;</code>
                                        <br />
                                    </p>
                                    <p>
                                        Spring 사용 예 : (특별한 일이 없는한
                                        <code>&lt;op:message /&gt;</code>
                                        사용)<br />
                                        <code>&lt;spring:message code="${dataVo.mtlngCdNm}"/&gt;</code>
                                        <br />
                                    </p>
                                    <p>
                                        Java Script 사용 예 :<br />
                                        <code>alert(OpMessage.${dataVo.mtlngCdNm});</code>
                                    </p>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mtlngCdExpln" class="control-label col-sm-2">다국어코드설명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="mtlngCdExpln" id="mtlngCdExpln" placeholder="다국어코드설명" rows="5" class="form-control">${dataVo.mtlngCdExpln}</textarea>
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
                    <label class="control-label col-md-2">다국어설정 </label>
                    <div class="col-md-10">
                        <c:if test="${empty langList}">
                            <div class="row">
                                <div class="col-md-12">
                                    <p class="form-control-static text-warning">등록된 도메인(사이트관리)이 없습니다.</p>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${not empty langList}">
                            <c:forEach var="langCdId" items="${langList}">

                                <c:set var="langMessage" value="" />
                                <c:forEach var="messageVo" items="${dataVo.messageList}">
                                    <c:if test="${messageVo.langCdId eq langCdId}">
                                        <c:set var="langMessage" value="${messageVo.multiLangMsgCn}" />
                                    </c:if>
                                </c:forEach>

                                <div class="row">
                                    <div class="col-md-3 text-right">
                                        <label for="multiLangMsgCns${langCdId}" class="control-label">
                                            <op:cdId hghrkCdId="localeCode" cdId="${langCdId}" />
                                            :
                                        </label>
                                    </div>
                                    <div class="col-md-9">
                                        <input type="text" name="multiLangMsgCns" id="multiLangMsgCns${langCdId}" value="<c:out value="${langMessage}" />" class="form-control"
                                            placeholder="<op:cdId hghrkCdId="localeCode" cdId="${langCdId}"/> 메시지 작성"
                                        />
                                        <input type="hidden" name="langCdIds" id="langCdIds${langCdId}" value="${langCdId}" />
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="help-block op-validate">
                                        <ul class="list-group">
                                            <li class="list-group-item">각 언어별 메시지를 최대 길이에 맞게 입력하세요. (100자)</li>
                                            <li class="list-group-item">메시지가 등록되지 않은 코드가 사용될 경우 '등록된 메시지가 없습니다' 가 표시됩니다.</li>
                                        </ul>
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
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" />
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
                <div class="form-group">
                    <label for="rgtrId" class="control-label col-sm-2">등록정보</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <strong>${dataVo.rgtrNm} (${dataVo.rgtrId}) <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty dataVo.mdfrId}">
                    <div class="form-group">
                        <label class="control-label col-sm-2">수정정보</label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-sm-6">
                                    <p class="form-control-static">
                                        <strong>${dataVo.updusrNm} (${dataVo.mdfrId}) <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong>
                                    </p>
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
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <c:if test="${!dataVo.isFolder}">
                        <button type="button" class="btn btn-danger" onclick="opMultiLangDelete('ND_deleteMultilang.do');">삭제</button>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>