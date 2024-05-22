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

<op:jsTag type="intra" items="opform, opvalidate" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/banner/js/banner.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        opLoadBannerDetailTy('banner', 'bnnDtlTypeNm', '${dataVo.bnnDtlTypeNm}');
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var bgngYmd = $.trim($("#bgngYmd").val());
        var endYmd = $.trim($("#endYmd").val());

        if(bgngYmd == "" && endYmd != "") {
            opWarningMsg("시작일을 입력하십시오.");
            $("#bgngYmd").focus();
            return false;
        }

        if(bgngYmd != "" && endYmd == "") {
            opWarningMsg("종료일을 입력하십시오.");
            $("#endYmd").focus();
            return false;
        }

        if(bgngYmd != "" && endYmd != "") {
            // 시작일과 종료일 비교
            if(bgngYmd > endYmd) {
                opWarningMsg("종료일이 시작일보다 클수 없습니다.");
                $("#endYmd").focus();
                return false;
            }
        }
        return true;
    };

    //]]>
</script>
</head>
<body>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 배너 등록
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateBanner.do" class="form-horizontal">
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />

                <input type="hidden" name="regSn" id="regSn" value="${dataVo.regSn}" />

                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="siteSns" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 사이트
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:forEach items="${domainList}" var="domain" varStatus="status">
                                            <c:set var="isContains" value="N" />
                                            <c:set var="checked" value="" />
                                            <c:forEach items="${dataVo.domnList}" var="bdomain">
                                                <c:if test="${domain.siteSn eq bdomain.siteSn and isContains eq 'N'}">
                                                    <c:set var="checked" value="checked='checked'" />
                                                    <c:set var="isContains" value="Y" />
                                                </c:if>
                                            </c:forEach>
                                            <label for="siteSns" class="checkbox-inline checkbox-primary">
                                                <input type="checkbox" name="siteSns" id="siteSns${status.index}" value="${domain.siteSn}" ${checked} class="styled" />
                                                ${domain.siteExpln}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="siteSns" />
                                        <div class="help-block">
                                            <span class="text-info">선택된 사이트 모두에 적용됩니다.</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ttl" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 제목
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <input type="text" name="ttl" id="ttl" value="${dataVo.ttl}" maxlength="500" class="form-control" placeholder="제목" />
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
                            <label for="" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 기간
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <label for="bgngYmd" class="control-label col-sm-1"> 시작일</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="bgngYmd" id="bgngYmd" value="${dataVo.bgngYmd}" maxlength="10" onkeydown="opDateAuto(this);" class="datepicker form-control from-date" placeholder="시작일" />
                                    </div>
                                    <label for="endYmd" class="control-label col-sm-1"> 종료일</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="endYmd" id="endYmd" value="${dataVo.endYmd}" maxlength="10" onkeydown="opDateAuto(this);" class="datepicker form-control to-date" placeholder="종료일" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="bgngYmd" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bnnTypeNm" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 배너유형
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <op:cdId type="radio" hghrkCdId="banner" cdId="bnnTypeNm" id="bnnTypeNm" values="${dataVo.bnnTypeNm}" click="opLoadBannerDetailTy('banner', 'bnnDtlTypeNm', '');" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="bnnTypeNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bnnDtlTypeNm" class="control-label col-sm-2">
                                배너상세유형
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <select name="bnnDtlTypeNm" id="bnnDtlTypeNm" class="select">
                                            <option value="">-- 선택 --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="linkUrlAddr" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 링크URL
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <input type="text" name="linkUrlAddr" id="linkUrlAddr" value="${dataVo.linkUrlAddr}" maxlength="200" class="form-control" placeholder="링크URL" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="linkUrlAddr" />
                                        <div class="help-block">
                                            <span class="text-info">외부사이트는 <code>http://</code> 부터 모두 입력해야 합니다.
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="linkTypeNm" class="control-label col-sm-2"> 링크유형</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <op:cdId type="radio" hghrkCdId="banner" cdId="linkTypeNm" id="linkTypeNm" values="${dataVo.linkTypeNm}" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="linkTypeNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="linkExpln" class="control-label col-sm-2"> 링크설명</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <input type="text" name="linkExpln" id="linkExpln" value="${dataVo.linkExpln}" maxlength="100" class="form-control" placeholder="링크설명" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="linkExpln" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="filePathNm" class="control-label col-sm-2"> 파일첨부</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:choose>
                                            <c:when test="${not empty dataVo.filePathNm}">
                                                <img alt="" src="${dataVo.filePathNm}" alt="${dataVo.imgAltrtvNm}">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="holder.js/170x70" alt="이미지없음">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <op:fileUpload view="basic" name="filePathNm" count="1" size="500K" exts="jpg,jpeg,gif,png" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="help-block">
                                            <span class="text-info">새로 파일을 등록하면 기존 파일이 대체됩니다. </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="imgAltrtvNm" class="control-label col-sm-2"> 이미지대체텍스트</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <input type="text" name="imgAltrtvNm" id="imgAltrtvNm" value="${dataVo.imgAltrtvNm}" maxlength="100" class="form-control" placeholder="이미지대체텍스트" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="imgAltrtvNm" />
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
                            <op:auth needAuthType="MANAGER">
                                <button type="submit" class="btn btn-success">저장</button>
                                <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteBanner.do');">삭제</button>
                            </op:auth>
                            <button type="button" class="btn btn-primary" onclick="opList('BD_selectBannerList.do');">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>