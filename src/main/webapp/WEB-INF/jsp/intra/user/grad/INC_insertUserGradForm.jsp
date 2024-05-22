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

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자등급 관리</title>

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            return validate();
        });

        $("#userGrdCdId").keyup(function(){
            opDupCheckUserGradCode($(this).val());
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
        <div class="panel panel-default">
            <div class="panel-body">
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
                <!-- 사용자등급코드 -->
                <div class="form-group">
                    <label for="userGrdCdId" class="control-label col-md-2">
                        <span class="mandatory">*</span> 사용자등급코드
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="userGrdCdId" id="userGrdCdId" placeholder="사용자등급코드를 입력해주세요" value="" class="form-control" />
                                <input type="hidden" name="dupCheckVal" id="dupCheckVal" value="" />
                            </div>
                            <div class="col-sm-6" id="dupCheckMessage">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="userGrdCdId" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 사용자등급설명 -->
                <div class="form-group">
                    <label for="userGrdExpln" class="control-label col-md-2"> 사용자등급설명 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <textarea name="userGrdExpln" id="userGrdExpln" placeholder="사용자등급설명을 입력해주세요" rows="3" class="form-control"></textarea>
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
                    <label for="useYnY" class="control-label col-md-2"> 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" checked="checked" />
                                    사용
                                </label>
                                <label for="" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
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