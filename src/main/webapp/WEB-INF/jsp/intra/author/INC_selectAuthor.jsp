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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>권한 관리</title>

    <op:jsTag type="intra" items="opform" />

    <!-- 기능별 스크립트 삽입 부 -->
<%--     <script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/author/js/author.js"></script> --%>

    <script type="text/javascript">
        //<![CDATA[
        /* 공통 초기화 기능 */
        $(document).ready(function() {
            
        });

        //]]>
    </script>
</head>
<body>
<div class="help-block text-right">
    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
</div>

<form name="dataForm" id="dataForm" method="post" action="ND_insertAuthor.do" class="form-horizontal">
    <div class="panel panel-default">
        <div class="panel-body">
            <!-- 권한명 -->
            <div class="form-group">
                <label for="authrtNm" class="control-label col-md-2">
                    <span class="mandatory">*</span> 권한명
                </label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <input type="text" name="authrtNm" id="authrtNm" placeholder="권한명을 입력해주세요" value="${dataVo.authrtNm}" class="form-control" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <valid:msg name="authrtNm" />
                        </div>
                    </div>
                </div>
            </div>
            <!-- 권한코드 -->
            <div class="form-group">
                <label for="authrtCdId" class="control-label col-md-2">
                    <span class="mandatory">*</span> 권한코드
                </label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <input type="text" name="authrtCdId" id="authrtCdId" placeholder="권한코드를 입력해주세요" value="${dataVo.authrtCdId}" class="form-control" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <valid:msg name="authrtCdId" />
                        </div>
                    </div>
                </div>
            </div>
            <!-- 권한설명 -->
            <div class="form-group">
                <label for="authrtExpln" class="control-label col-md-2">
                    권한설명
                </label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <textarea name="authrtExpln" id="authrtExpln" placeholder="권한설명을 입력해주세요" rows="3" class="form-control">${dataVo.authrtExpln}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <valid:msg name="authrtExpln" />
                        </div>
                    </div>
                </div>
            </div>
            <!-- 사용여부 -->
            <div class="form-group">
                <label for="useYnY" class="control-label col-md-2">
                    사용여부
                </label>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <label for="useYnY" class="radio-inline radio-primary">
                                <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" checked="checked" />
                                사용
                            </label>
                            <label for=""  class="radio-inline radio-primary">
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
        </div>
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="btn-group col-md-12">
            <div class="pull-right">
                <button type="button" class="btn btn-success" onclick="opInsertAction();">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</form>
</body>
</html>