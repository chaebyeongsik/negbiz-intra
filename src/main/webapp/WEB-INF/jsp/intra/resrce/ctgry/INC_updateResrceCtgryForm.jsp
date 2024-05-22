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
<title>카테고리 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/resrce/js/resrce.ctgry.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            var title = $("#ctgryNm").val();
            if(validate()) {
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        if(response.result) {
                            Optree.setTitle(title);
                            $("#nodePath").html(Optree.getNodePath());
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
        return true;
    };

    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateResrceCtgry.do" class="form-horizontal">

        <input type="hidden" name="upCtgrySn" id="upCtgrySn" value="${dataVo.upCtgrySn}" />
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="ctgrySn" class="control-label col-sm-2"> 상위카테고리 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <strong> <c:choose>
                                            <c:when test="${dataVo.upCtgrySn != '0'}">${dataVo.ctgryNm} <code>${dataVo.ctgrySn}</code>
                                            </c:when>
                                            <c:otherwise>최상위카테고리</c:otherwise>
                                        </c:choose>
                                    </strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ctgryNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 카테고리명
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="ctgryNm" id="ctgryNm" value="${dataVo.ctgryNm}" maxlength="100" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="ctgryNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ctgryPathNm" class="control-label col-sm-2"> 카테고리경로</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static" id="nodePath">${dataVo.ctgryPathNm}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2"> 사용여부</label>
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
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <c:if test="${!dataVo.isFolder}">
                        <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteResrceCtgry.do');">삭제</button>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>