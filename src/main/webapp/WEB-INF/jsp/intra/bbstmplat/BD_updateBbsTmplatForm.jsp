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
<title>게시판템플릿 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbstmplat/js/bbstmplat.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
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

    <c:set var="tmpltTypeCd" value="목록템플릿" />
    <c:set var="applcBbsCo" value="${dataVo.listApplcBbsCo}" />
    <c:if test="${paramMap.q_tmpltTypeCd eq 'V'}">
        <c:set var="tmpltTypeCd" value="읽기템플릿" />
        <c:set var="applcBbsCo" value="${dataVo.redngApplcBbsCo}" />
    </c:if>
    <c:if test="${paramMap.q_tmpltTypeCd eq 'F'}">
        <c:set var="tmpltTypeCd" value="쓰기템플릿" />
        <c:set var="applcBbsCo" value="${dataVo.formApplcBbsCo}" />
    </c:if>

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 게시판 ${tmpltTypeCd} 수정
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateBbsTmplat.do" class="form-horizontal">
                <input type="hidden" name="tmpltTypeCd" id="tmpltTypeCd" value="${dataVo.tmpltTypeCd}" />
                <input type="hidden" name="applcBbsCo" id="applcBbsCo" value="${applcBbsCo}" />
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />

                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-sm-9">
                            <div class="form-group">
                                <label for="tmpltId" class="control-label col-sm-2">
                                    <span class="mandatory">*</span> 템플릿아이디
                                </label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <p class="form-control-static">${dataVo.tmpltId}</p>
                                            <input type="hidden" name="tmpltId" id="tmpltId" value="${dataVo.tmpltId}" maxlength="15" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tmpltNm" class="control-label col-sm-2">
                                    <span class="mandatory">*</span> 템플릿이름
                                </label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <input type="text" name="tmpltNm" id="tmpltNm" value="${dataVo.tmpltNm}" maxlength="100" class="form-control" placeholder="템플릿이름" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <valid:msg name="tmpltNm" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tmpltCn" class="control-label col-sm-2">
                                    <span class="mandatory">*</span> 템플릿소스
                                </label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <textarea name="tmpltCn" id="tmpltCn" rows="40" cols="80" class="form-control" placeholder="HTML코딩된 소스를 입력"><c:out value="${dataVo.tmpltCn}" /></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="help-block op-validate">
                                                <ul class="list-group">
                                                    <li class="list-group-item">
                                                        <span class="validate-msg">필수입력 항목입니다.</span>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <span class="validate-msg">출력할 자리에 Taglib의 코드를 입력하세요.</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tmplatScrsht" class="control-label col-sm-2"> 템플릿미리보기첨부</label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <op:fileUpload view="basic" name="tmplatScrsht" count="1" size="300K" maxSize="300K" exts="jpg,jpeg,gif,png" fileList="${dataVo.fileList}" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h6 class="panel-title"><i class="icon-file-css"></i>Taglib 템플릿</h6>
                                </div>
                                <div class="panel-body" style="max-height:550px;overflow-y:scroll;">
                                    <c:forEach items="${tagList}" var="tagList">
                                        <c:set var="colId" value="${tagList.colId}" />
                                        <c:set var="colId" value="${fn:replace(colId, 'ntcPstYn', 'notice')}" />
                                        <c:set var="colId" value="${fn:replace(colId, 'rlsYn', 'othbc')}" />
                                        <h6 style="margin : 5px 0 3px 0;">${tagList.colNm}</h6>
                                        <c:choose>
                                            <c:when test="${paramMap.q_tmpltTypeCd eq 'L'}">
                                                                                    이름 <code>&lt;bbs:${colId} type="text" /&gt;</code><br/>
                                                                                    내용 <code>&lt;bbs:${colId} type="list" obj="&#36;{dataVo}" /&gt;</code>
                                            </c:when>
                                            <c:when test="${paramMap.q_tmpltTypeCd eq 'V'}">
                                                                                    이름 <code>&lt;bbs:${colId} type="text" /&gt;</code><br/>
                                                                                    내용 <code>&lt;bbs:${colId} type="view" obj="&#36;{dataVo}" /&gt;</code>
                                            </c:when>
                                            <c:when test="${paramMap.q_tmpltTypeCd eq 'F'}">
                                                                                    라벨 <code>&lt;bbs:${colId} type="label" /&gt;</code><br/>
                                                Form <code>&lt;bbs:${colId} type="form" obj="&#36;{dataVo}" /&gt;</code><br/>
                                                                                    설명 <code>&lt;bbs:${colId} type="desc" /&gt;</code>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${paramMap.q_tmpltTypeCd eq 'L'}">
                                            <h6 style="margin : 5px 0 3px 0;">썸네일이미지</h6>
                                                                                이름 <code>&lt;bbs:thumbnail type="text" /&gt;</code><br/>
                                                                                이미지 <code>&lt;bbs:thumbnail type="list" obj="&#36;{dataVo}" /&gt;</code>
                                        </c:when>
                                        <c:when test="${paramMap.q_tmpltTypeCd eq 'V'}">
                                            <h6 style="margin : 5px 0 3px 0;">썸네일이미지</h6>
                                                                                이름 <code>&lt;bbs:thumbnail type="text" /&gt;</code><br/>
                                                                                이미지 <code>&lt;bbs:thumbnail type="view" obj="&#36;{dataVo}" /&gt;</code>
                                        </c:when>
                                    </c:choose>
                                    <h6 style="margin : 5px 0 3px 0;">버튼그룹</h6>
                                    <c:choose>
                                        <c:when test="${paramMap.q_tmpltTypeCd eq 'L'}">
                                                                                버튼 <code>&lt;bbs:button type="list" /&gt;</code><br/>
                                        </c:when>
                                        <c:when test="${paramMap.q_tmpltTypeCd eq 'V'}">
                                                                                버튼 <code>&lt;bbs:button type="view" obj="&#36;{dataVo}" /&gt;</code>
                                        </c:when>
                                        <c:when test="${paramMap.q_tmpltTypeCd eq 'F'}">
                                                                                버튼 <code>&lt;bbs:button type="form" obj="&#36;{dataVo}" /&gt;</code><br/>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${paramMap.q_tmpltTypeCd eq 'V'}">
                                        <h6 style="margin : 5px 0 3px 0;">이전글/다음글</h6>
                                                                                이전글/다음글 <code>&lt;bbs:brftrSntnc type="view" obj="&#36;{dataVo}" /&gt;</code>
                                    </c:if>
                                    <c:if test="${paramMap.q_tmpltTypeCd eq 'V'}">
                                        <h6 style="margin : 5px 0 3px 0;">댓글</h6>
                                                                                댓글 <code>&lt;bbs:cmnt type="view" obj="&#36;{dataVo}" /&gt;</code>
                                    </c:if>
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
                            <button type="button" class="btn btn-danger" onclick="opBbsTmplatDelete();">삭제</button>
                            <button type="button" class="btn btn-primary" onclick="opBbsTmplatList();">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>