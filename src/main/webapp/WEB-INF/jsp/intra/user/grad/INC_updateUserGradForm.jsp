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
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 등급 관리</title>

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    $(document).ready(function() {

        $("#userGrdCdId").keyup(function(){
            if ($("#originalUserGradCode").val() != $("#userGrdCdId").val()) {
                opDupCheckUserGradCode($(this).val());
            }else{
                $("#dupCheckMessage").html("");
                $("#dupCheckVal").val("Y");
            }
        });
    });
    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertUserGrad.do" class="form-horizontal">
        <input type="hidden" name="userGrdCdId" id="userGrdCdId" value="${dataVo.userGrdCdId}" />
        <input type="hidden" name="originalUserGradCode" id="originalUserGradCode" value="${dataVo.userGrdCdId}" />
        <input type="hidden" name="dupCheckVal" id="dupCheckVal" value="" />

        <div class="panel panel-default">
            <div class="panel-body">
                <!-- 사용자등급코드 -->
                <div class="form-group">
                    <label for="userGrdCdId" class="control-label col-md-2">
                        사용자등급코드
                    </label>
                    <div class="col-md-10">
                        <p class="form-control-static"><code>${dataVo.userGrdCdId}</code></p>
                    </div>
                </div>
                <!-- 사용자등급명 -->
                <div class="form-group">
                    <label for="userGrdNm" class="control-label col-md-2">
                        <span class="mandatory">*</span> 사용자등급명
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <input type="text" name="userGrdNm" id="userGrdNm" placeholder="사용자등급명을 입력해주세요" value="${dataVo.userGrdNm}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="userGrdNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 사용자 등급설명 -->
                <div class="form-group">
                    <label for="userGrdExpln" class="control-label col-md-2"> 사용자등급설명</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <textarea name="userGrdExpln" id="userGrdExpln" placeholder="사용자등급설명을 입력해주세요" rows="3" class="form-control">${dataVo.userGrdExpln}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="userGrdExpln" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="form-group">
                    <label for="useYnY" class="control-label col-md-2"> 사용여부</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" checked="checked" />
                                    사용
                                </label>
                                <label for="" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> />
                                    미사용
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="useYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 등록자 R -->
                <div class="form-group">
                    <label for="rgtrNm" class="control-label col-md-2">등록자</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12 form-control-static">
                                ${dataVo.rgtrNm}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 등록일 R -->
                <div class="form-group">
                    <label for="regDt" class="control-label col-md-2">등록일</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12 form-control-static">
                                <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty dataVo.updtDt}">
                    <!-- 수정자 R -->
                    <div class="form-group">
                        <label for="updusrNm" class="control-label col-md-2">수정자</label>
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-md-12 form-control-static">
                                    ${dataVo.updusrNm}
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 수정일 R -->
                    <div class="form-group">
                        <label for="updtDt" class="control-label col-md-2">수정일</label>
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-md-12 form-control-static">
                                    <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="btn-group col-md-12">
                <div class="pull-right">
                    <button type="button" class="btn btn-success" onclick="opUpdateAction();">수정</button>
<!--                     <button type="button" class="btn btn-danger" onclick="opDeleteAction();">삭제</button> -->
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>