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

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(validate()) {
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        if(response.result) {
                            var key = response.value;
                            Optree.addChildren({
                                "key" : key,
                                "title" : $("#ctgryNm").val(),
                                "lazy" : false
                            });
                            $("#dataForm")[0].reset();
                            $("#ctgryNm").focus();
                            insertCnt++;
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

        // 라디오버튼 자동 선택
        opChecked("useYn", "Y");
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    /* 트리 클릭 후 상위 코드 경로 표시 */
    var afterTreeView = function(key, parentKey, response) {
        var cdId = key;
        if(key == OpConfig.defaultCode.highTreeCd) {
            cdId = "최상위카테고리";
        }
        $("#nodePath").html(Optree.getNodePath() + "&nbsp;&nbsp;&nbsp;<code>" + cdId + "</code>");
    };

    //]]>
</script>
</head>
<body>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertResrceCtgry.do" class="form-horizontal">

        <input type="hidden" name="upCtgrySn" id="upCtgrySn" value="${paramMap.q_ctgrySn}" />

        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="upmtlngCdNm" class="control-label col-sm-2"> 상위카테고리 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static" id="nodePath">카테고리를 선택하세요.</p>
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
                                <input type="text" name="ctgryNm" id="ctgryNm" value="" maxlength="100" class="form-control" />
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
                    <label for="useYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 사용여부
                    </label>
                    <div class="col-sm-10">
                        <label for="useYnY" class="radio-inline radio-primary">
                            <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" />
                            예
                        </label>
                        <label for="useYnN" class="radio-inline radio-primary">
                            <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="useYn" />
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

</body>
</html>