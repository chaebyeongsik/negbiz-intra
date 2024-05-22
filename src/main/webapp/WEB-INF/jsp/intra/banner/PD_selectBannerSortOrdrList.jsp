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
<title>배너 관리</title>

<op:jsTag type="intra" items="opform" />
<op:jsTag type="libs" items="selectbox" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/banner/js/banner.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var isUpdate = false;
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 검색폼
        $("#dataForm").submit(function() {
            var bnnTypeNm = $("#q_bnnTypeNm").val();
            if(!bnnTypeNm) {
                opWarningMsg("배너유형을 선택하세요.");
                return false;
            }
        });

        // 정렬순서변경 폼
        $("#sortForm").submit(function() {
            $("#regSns").selectOptions(/./, true);
            return true;
        });

        $("#sortUp").click(function() {
            $('#regSns option:selected').each(function() {
                var selectObj = $(this);
                if(selectObj.index() == 0) {
                    return false;
                }
                var targetObj = $('#regSns option:eq(' + (selectObj.index() - 1) + ')');
                targetObj.before(selectObj);
                isUpdate = true;
            });
        });
        $("#sortDn").click(function() {
            $('#regSns option:selected').each(function() {
                var selectObj = $(this);
                if(selectObj.index() == $('#regSns').children().length) {
                    return false;
                }
                var targetObj = $('#regSns option:eq(' + (selectObj.index() + 1) + ')');
                targetObj.after(selectObj);
                isUpdate = true;
            });
        });
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_selectBannerSortOrdrList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_siteSn" class="control-label"> 도메인</label>
                        <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_bnnTypeNm" class="control-label"> 배너유형</label>
                        <op:cdId type="select" hghrkCdId="banner" cdId="bnnTypeNm" id="q_bnnTypeNm" values="${paramMap.q_bnnTypeNm}" etc=" style=\"width: 150px;\"" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
            </fieldset>
        </form>
    </div>

    <c:choose>
        <c:when test="${empty paramMap.q_bnnTypeNm}">
            <div class="row">
                <div class="col-sm-12">
                    <div class="bg-warning with-padding block-inner text-center">배너유형을 선택 후 검색하여 주세요.</div>
                </div>
            </div>
        </c:when>
        <c:otherwise>

            <form name="sortForm" id="sortForm" method="post" action="ND_updateBannerSortSn.do" class="form-horizontal">
                <input type="hidden" name="bnnTypeNm" value="${paramMap.q_bnnTypeNm}">
                <input type="hidden" name="q_bnnTypeNm" value="${paramMap.q_bnnTypeNm}">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="siteSns" class="control-label col-sm-2"> 정렬순서 </label>
                            <div class="col-sm-7">
                                <select id="regSns" name="regSns" multiple="multiple" class="form-control">
                                    <c:forEach items="${dataList}" var="dataVo" varStatus="status">
                                        <option value="${dataVo.regSn}">[
                                            <op:cdId hghrkCdId="banner" cdId="${dataVo.bnnTypeNm}" />] ${dataVo.ttl}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <div class="btn-group">
                                    <button type="button" id="sortUp" class="btn btn-danger">
                                        <i class="icon-point-up"></i>
                                    </button>
                                    <button type="button" id="sortDn" class="btn btn-danger">
                                        <i class="icon-point-down"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <op:auth needAuthType="MANAGER">
                    <!-- 버튼 -->
                    <div class="row">
                        <div class="col-sm-12 btn-group">
                            <div class="pull-right">
                                <button type="submit" class="btn btn-success">정렬순서변경</button>
                            </div>
                        </div>
                    </div>
                    <!-- //버튼 -->
                </op:auth>
            </form>
        </c:otherwise>
    </c:choose>

</body>
</html>