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
<title>금지단어 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/prhibtWrd/js/prhibtWrd.js"></script>
    -->

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

    <form name="dataForm" id="dataForm" method="post" action="ND_updatePrhibtWrd.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="phbwdCdId" id="phbwdCdId" value="${dataVo.phbwdCdId}" />

        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-libreoffice"></i> 금지단어 등록
                </h6>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="" class="control-label col-sm-2">
                        <span class="mandatory">*</span>금지단어코드
                    </label>
                    <div class="col-sm-10">
                        <p class="form-control-static">
                            <code>${dataVo.phbwdCdId}</code>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ttl" class="control-label col-sm-2">
                        <span class="mandatory">*</span>제목
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="ttl" id="ttl" value="${dataVo.ttl}" maxlength="100" class="form-control" placeholder="제목" />
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
                    <label for="phbwdCn" class="control-label col-sm-2">
                        <span class="mandatory">*</span>금지단어내용
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="phbwdCn" id="phbwdCn" rows="15" cols="80" class="form-control" placeholder="금지단어내용">${dataVo.phbwdCn}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="phbwdCn" />
                                <div class="help-block">
                                    <span class="text-info">중복단어는 자동으로 필터링되어 하나의 단어만 저장됩니다. </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span>사용여부
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> class="styled" />
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
                    <button type="button" class="btn btn-danger" onclick="opDelete('ND_deletePrhibtWrd.do');">삭제</button>
                    <button type="button" class="btn btn-primary" onclick="opList('BD_selectPrhibtWrdList.do');">목록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>