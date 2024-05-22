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
<title>사용자메뉴레이아웃 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />

<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/cmsLayout/js/cmsLayout.js"></script>
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

        // 코드 중복 체크
        $("#lytCdNo").keyup(function(event) {
            var len = $(this).val().length;
            if(len >= 2) {
                var siteSn = $("#siteSn").val();
                if(!siteSn) {
                    opWarningMsg("사이트를 선택해야 합니다.");
                    return false;
                }
                $.ajax({
                    url : "ND_selectDplctChckCode.do",
                    type : "GET",
                    dataType : "json",
                    data : {
                        q_siteSn : $("#siteSn").val(),
                        q_lytCdNo : $(this).val(),
                        q_date : new Date()
                    },
                    success : function(response) {
                        var msg = response.message;
                        if(response.result) {
                            $("#duplicateText").html("<p class=\"text-info form-control-static\">" + msg + "</p>");
                            $("#duplicateYn").val("Y");
                        } else {
                            $("#duplicateText").html("<p class=\"text-danger form-control-static\">" + msg + "</p>");
                            $("#duplicateYn").val("N");
                        }
                    }
                });
            }
        }).keydown(function(event) {
            if(event.which == 13) {
                event.preventDefault();
            }
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
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 사용자메뉴레이아웃 등록
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" action="ND_insertCmsLayout.do" class="form-horizontal">
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />
                <input type="hidden" name="duplicateYn" id="duplicateYn" value="Y" />

                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="siteSn" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 사이트
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <select name="siteSn" id="siteSn" class="select">
                                            <option value=""><op:message cdId="common.selectOption" /></option>
                                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="siteSn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lytCdNo" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 레이아웃코드
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <input type="text" name="lytCdNo" id="lytCdNo" value="${dataVo.lytCdNo}" class="form-control" placeholder="레이아웃코드" />
                                    </div>
                                    <div class="col-sm-9" id="duplicateText"></div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="lytCdNo" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lytNm" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 레이아웃명
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <input type="text" name="lytNm" id="lytNm" value="${dataVo.lytNm}" class="form-control" placeholder="레이아웃명" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="lytNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lytContsCn" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 레이아웃컨텐츠
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <textarea name="lytContsCn" id="lytContsCn" rows="30" cols="80" class="form-control" placeholder="레이아웃컨텐츠">${dataVo.lytContsCn}</textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="lytContsCn" />
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
                            <button type="button" class="btn btn-primary" onclick="opList('BD_selectCmsLayoutList.do');">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>