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
<title>사용자메뉴 관리</title>

<op:jsTag type="intra" items="opvalidate, ui" />
<op:jsTag type="libs" items="form, selectbox" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.common.js"></script>
<script type="text/javascript" src="/resources/intra/cms/js/cms.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {

        // 권한유형에 따른 validate
        var authTyCode = $("input[name=authrtGroupNm]:checked").val();
        var authTyNm = $("input[name=authrtGroupNm]:checked").closest("label").text();
        if (authTyCode == "IN_DEPT" || authTyCode == "EQ_DEPT") { // 하위부서 포함, 동일부서
            if ($("#authrtDeptCdId").val() == "") {
                opWarningMsg("'" + $.trim(authTyNm) + "' 선택시 필수 입력항목 입니다.");
                $("#tmpAuthorDeptNm").focus();
                return false;
            }
        } else if (authTyCode == "CHARGER") { // 담당자
            if ($("#authrtPicIds option").length == 0) {
                opWarningMsg("'" + $.trim(authTyNm) + "' 선택시 필수 입력항목 입니다.");
                $("#tmpAuthorChargerNm").focus();
                return false;
            }
        }

        return true;
    };

    /* 응용컨텐츠 선택시 서브 기능 상세 목록을 표시(URL 포함) */
    var cntrlData = {};

    var opChangeControl = function(readyAt) {
        var selectValue = $("#cntrlNm").val();
        if(!selectValue || selectValue == "") {
            $("#methodNm").removeOption(/./);
            $("#methodNm").val(null).trigger("change");
            return false;
        }

        var siteSn = $("#q_siteSn").val();
        var arrSelectValue = selectValue.split("|");
        var param = {
            siteSn : siteSn,
            beanNm : arrSelectValue[0],
            methodNm : arrSelectValue[1]
        };

        var methodValue = "";
        var userMenuUrlAddr = "${dataVo.userMenuUrlAddr}";
        var mngrMenuUrlAddr = "${dataVo.mngrMenuUrlAddr}";

        if (readyAt != "INIT") {
            $("#methodNm").removeOption(/./);
            $("#methodNm").val(null).trigger("change");
        }
        $.ajax({
            url : "selectCmsRefernce.do",
            type : "post",
            dataType : "json",
            data : param,
            success : function(response) {
                cntrlData = {};
                var refernceList = response;
                for(var i = 0 ; i < refernceList.length ; i++) {
                    var refernce = refernceList[i];
                    var refUserMainMenuUrl = refernce.userMainMenuUrl;
                    var refMngrMenuUrlAddr = refernce.mngrMenuUrlAddr;

                    var value = refUserMainMenuUrl + "||" + refMngrMenuUrlAddr;
                    $("#methodNm").addOption(value, refernce.name, false);

                    cntrlData[value] = refernce;

                    if(userMenuUrlAddr.indexOf(refUserMainMenuUrl) > -1 && mngrMenuUrlAddr.indexOf(refMngrMenuUrlAddr) > -1) {
                        methodValue = value;
                    }
                }

                $("#methodNm").select2("val", methodValue);
            }
        });
    };

    /* 공통 초기화 기능 */
    $().ready(function() {

        // 메뉴유형에 따른 폼 정리
        toggleMenuTypeCode("${dataVo.menuType}");
        // 메뉴유형이 프로그램인경우 컨트롤 활성화
        if("${dataVo.menuType}" == "PROGRAM") {
            opChangeControl("INIT");
        }
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(validate()) {
                // 모든 담당자가 선택 상태로 되어 submit
                $("#authrtPicIds").selectOptions(/./, true);
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });

        /**
         * 레이아웃 및 컨텐츠 미리보기
         */
        $(".layoutPreview, .cntntsPreview").click(function() {
            var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, ";
            option += "resizable=yes, scrollbars=yes, location=1, status=1, menubar=10, toolbar=1";
            var href = $(this).attr("href");
            var layoutPreview = window.open(href, "userMenuPreview", option);
            layoutPreview.focus();
            return false;
        });

    });
    //]]>
</script>
</head>
<body>
    <!--     <div class="help-block text-right"> -->
    <!--         <span class="mandatory">*</span> 항목은 필수 입력항목입니다. -->
    <!--     </div> -->

    <form name="dataForm" id="dataForm" method="post" action="ND_updateCmsBase.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="contsSn" id="contsSn" value="${dataVo.contsSn}" />
        <input type="hidden" name="siteSn" id="siteSn" value="${dataVo.siteSn}" />
        <input type="hidden" name="userMenuEngNm" id="userMenuEngNm" value="${dataVo.userMenuEngNm}" />
        <input type="hidden" name="q_upMenuEngNm" id="q_upMenuEngNm" value="${dataVo.upMenuEngNm}" />

        <input type="hidden" name="actlStngYn" id="actlStngYn" value="${dataVo.actlStngYn}" />
        <input type="hidden" name="sitePathNm" id="sitePathNm" value="${dataVo.sitePathNm}" />

        <div class="panel panel-default" style="border-top-color: #ffffff;">
            <div class="panel-body">

                <div class="form-group">
                    <label for="userMenuEngNm" class="control-label col-sm-2"> 사용자메뉴코드 </label>
                    <div class="col-sm-10">
                        <p class="form-control-static">
                            <code>${dataVo.userMenuEngNm}</code>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 메뉴명
                    </label>
                    <div class="col-sm-4">
                        <input type="text" name="menuNm" id="menuNm" value="${dataVo.menuNm}" class="form-control" placeholder="메뉴명" />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="menuNm" />
                            </div>
                        </div>
                    </div>
                    <label for="ttl" class="control-label col-sm-2">제목 </label>
                    <div class="col-md-4">
                        <input type="text" name="ttl" id="ttl" value="${dataVo.ttl}" class="form-control" placeholder="별도 제목 사용시 입력하세요." />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="ttl" />
                                <div class="help-block">
                                    <span class="text-info">입력시 메뉴명을 대체하여 화면에 표시됩니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 사용여부
                    </label>
                    <div class="col-sm-4">
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
                                <div class="help-block">
                                    <span class="text-info"><code>아니오</code> 선택시 해당 기능을 사용할 수 없습니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <label for="indctYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 표시여부
                    </label>
                    <div class="col-sm-4">
                        <label for="indctYnY" class="radio-inline radio-primary">
                            <input type="radio" name="indctYn" id="indctYnY" value="Y" <c:if test="${dataVo.indctYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="indctYnN" class="radio-inline radio-primary">
                            <input type="radio" name="indctYn" id="indctYnN" value="N" <c:if test="${dataVo.indctYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="indctYn" />
                                <div class="help-block">
                                    <span class="text-info"><code>아니오</code> 선택시 화면 메뉴에 표시되지 않습니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuType" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 메뉴유형
                    </label>
                    <div class="col-sm-10">
                        <op:cdId type="radio" hghrkCdId="CMS" cdId="menuType" id="menuType" values="${dataVo.menuType}" click="toggleMenuType(this);" />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="menuType" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group contents-group">
                    <label for="aprvYnY" class="control-label col-sm-2">승인필요여부 </label>
                    <div class="col-sm-10">
                        <label for="aprvYnY" class="radio-inline radio-primary">
                            <input type="radio" name="aprvYn" id="aprvYnY" value="Y" <c:if test="${dataVo.aprvYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="aprvYnN" class="radio-inline radio-primary">
                            <input type="radio" name="aprvYn" id="aprvYnN" value="N" <c:if test="${dataVo.aprvYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="aprvYn" />
                                <div class="help-block">
                                    <span class="text-info"><code>아니오</code> 선택시 컨텐츠는 관리자 승인이 필요없이 자동으로 배포됩니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%-- 외부에서 접속할 수 있는 전체 URL 생성 --%>
                <c:choose>
                    <c:when test="${dataVo.httpsYn eq 'Y'}">
                        <c:set var="protocol" value="https" />
                        <c:set var="port"><c:out value="${dataVo.scrtyPortSn}" default="443" /></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="protocol" value="http" />
                        <c:set var="port"><c:out value="${dataVo.portSn}" default="80" /></c:set>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${dataVo.actlStngYn eq 'Y'}">
                        <c:set var="sitePathNm" value="${dataVo.sitePathNm}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="sitePathNm" value="" />
                    </c:otherwise>
                </c:choose>
                <c:set var="fullUserMenuUrlAddr" value="${protocol}://${dataVo.siteNm}:${port}${sitePathNm}${dataVo.userMenuUrlAddr}" />

                <div class="form-group contents-group">
                    <label for="menuType" class="control-label col-sm-2">컨텐츠편집 </label>
                    <div class="col-sm-10">
                        <button type="button" class="btn btn-info" onclick="cntntsEdit('${dataVo.siteSn}','${dataVo.userMenuEngNm}','${dataVo.contsSn}');">편집하기</button>
                        <a href="${CSS_VALIDATE}${fullUserMenuUrlAddr}" target="_blank" class="btn btn-info">CSS 확인</a>
                        <a href="${HTML_VALIDATE}${fullUserMenuUrlAddr}" target="_blank" class="btn btn-info">HTML 확인</a>
                    </div>
                </div>

                <div class="form-group program-group">
                    <label for="menuType" class="control-label col-sm-2">응용컨텐츠선택 </label>
                    <div class="col-sm-10">
                        <select id="cntrlNm" name="cntrlNm" class="select" style="width: 150px;" onchange="opChangeControl();">
                            <option value="">-- 선택하세요 --</option>
                            <c:forEach items="${CONTROL_LIST}" var="control">
                                <option value="${control.cntrlNm}" <c:if test="${dataVo.cntrlNm eq control.cntrlNm}">selected='selected'</c:if>>${control.name}</option>
                            </c:forEach>
                        </select>
                        <select id="methodNm" name="methodm" class="select" style="width: 200px;" onchange="opChangeMethod(this)">
                            <option value="">-- 선택하세요 --</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="userMenuUrlAddr" class="control-label col-sm-2">사용자메뉴URL </label>
                    <div class="col-sm-10">
                        <div class="row block">
                            <div class="col-sm-8">
                                <input type="text" name="userMenuUrlAddr" id="userMenuUrlAddr" value="${dataVo.userMenuUrlAddr}" class="form-control" placeholder="사용자메뉴URL" />
                            </div>
                            <div class="col-sm-2">
                                <a href="${fullUserMenuUrlAddr}" class="btn btn-info cntntsPreview">운영화면</a>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-info" onclick="addLwprtMenuUrlAddr(this);">URL추가</button>
                            </div>
                        </div>
                        <div class="row">
                            <div id="lwprtMenuUrlAddrDiv" class="col-md-12">
                                <c:forEach var="lwprtMenuUrlAddr" items="${dataVo.lwprtMenuUrlAddr}">
                                    <div class="row block">
                                        <div class="col-md-8">
                                            <input type="text" name="lwprtMenuUrlAddr" value="${lwprtMenuUrlAddr}" class="form-control" />
                                        </div>
                                        <div class="col-md-2">
                                            <button type="button" class="btn btn-danger" onclick="delLwprtMenuUrlAddr(this);">삭제</button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mngrMenuUrlAddr" class="control-label col-sm-2">관리자메뉴URL </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-10">
                                <input type="text" name="mngrMenuUrlAddr" id="mngrMenuUrlAddr" value="${dataVo.mngrMenuUrlAddr}" class="form-control" placeholder="관리자메뉴URL" />
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-info" onclick="opNewWin('mngrMenuUrlAddr');">새창열기</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="linkTypeNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 링크유형
                    </label>
                    <div class="col-sm-10">
                        <op:cdId type="radio" hghrkCdId="CMS" cdId="linkTypeNm" id="linkTypeNm" values="${dataVo.linkTypeNm}" />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="linkTypeNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lytCdNo" class="control-label col-sm-2">레이아웃코드 </label>
                    <div class="col-sm-4">
                        <select name="lytCdNo" id="lytCdNo" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${dataList}" var="layoutVo" varStatus="vst">
                                <option value="${layoutVo.lytCdNo}" <c:if test="${dataVo.lytCdNo eq layoutVo.lytCdNo}">selected="selected"</c:if>>${layoutVo.lytNm}</option>
                            </c:forEach>
                        </select>
                        <a href="<c:url value="/intra/cms/cntnts/PV_LayoutContentPreView.do" />?q_siteSn=${dataVo.siteSn}&amp;q_lytCdNo=${dataVo.lytCdNo}" class="btn btn-info layoutPreview">새창열기</a>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="lytNm" />
                            </div>
                        </div>
                    </div>
                    <label for="upLytUseYnY" class="control-label col-sm-2">부모레이아웃사용여부</label>
                    <div class="col-sm-4">
                        <label for="upLytUseYnY" class="radio-inline radio-primary">
                            <input type="radio" name="upLytUseYn" id="upLytUseYnY" value="Y" <c:if test="${dataVo.upLytUseYn eq 'Y'}"> checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="upLytUseYnN" class="radio-inline radio-primary">
                            <input type="radio" name="upLytUseYn" id="upLytUseYnN" value="N" <c:if test="${dataVo.upLytUseYn eq 'N'}"> checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dgstfnIndctYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 만족도표시여부
                    </label>
                    <div class="col-sm-10">
                        <label for="dgstfnIndctYnY" class="radio-inline radio-primary">
                            <input type="radio" name="dgstfnIndctYn" id="dgstfnIndctYnY" value="Y" <c:if test="${dataVo.dgstfnIndctYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="dgstfnIndctYnN" class="radio-inline radio-primary">
                            <input type="radio" name="dgstfnIndctYn" id="dgstfnIndctYnN" value="N" <c:if test="${dataVo.dgstfnIndctYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="dgstfnIndctYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <%--
                <div class="form-group">
                    <label for="snsUseYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> SNS사용여부
                    </label>
                    <div class="col-sm-4">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="snsUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="snsUseYn" id="snsUseYnY" value="Y" <c:if test="${dataVo.snsUseYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="snsUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="snsUseYn" id="snsUseYnN" value="N" <c:if test="${dataVo.snsUseYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="snsUseYn" />
                            </div>
                        </div>
                    </div>
                    <label for="snsPrmttNm" class="control-label col-sm-2">SNS파라미터</label>
                    <div class="col-md-4">
                        <input type="text" name="snsPrmttNm" id="snsPrmttNm" value="${dataVo.snsPrmttNm}" class="form-control" />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="snsPrmttNm" />
                                <div class="help-block">
                                    <span class="text-info">파라미터는 <code>bbsSn</code> 과 같이 키만 입력해야 합니다.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                 --%>
                <div class="form-group">
                    <label for="picIndctYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 담당자표시여부
                    </label>
                    <div class="col-sm-10">
                        <label for="picIndctYnY" class="radio-inline radio-primary">
                            <input type="radio" name="picIndctYn" id="picIndctYnY" value="Y" onclick="toggleChargerIndictTy(this);" <c:if test="${dataVo.picIndctYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="picIndctYnN" class="radio-inline radio-primary">
                            <input type="radio" name="picIndctYn" id="picIndctYnN" value="N" onclick="toggleChargerIndictTy(this);" <c:if test="${dataVo.picIndctYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block">
                                    <span class="text-info"><code>아니오</code> 선택시 사용자화면에 업무담당자 정보가 표시되지 않습니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group charger-group" <c:if test="${dataVo.picIndctYn ne 'Y'}">style="display:none;"</c:if>>
                    <label for="tmpPicNm" class="control-label col-md-2">
                        <span class="mandatory">*</span> 표시담당자정보
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" name="tmpPicNm" id="tmpPicNm" value="" class="form-control" placeholder="이름을 입력하세요." />
                            </div>
                            <div class="col-md-4">
                                <p class="form-control-static" id="chrgDeptNm">
                                    <c:if test="${not empty dataVo.picNm}">${dataVo.chrgDeptNm}&nbsp;&nbsp;/&nbsp;&nbsp;${dataVo.picNm}</c:if>
                                </p>
                            </div>
                            <div class="col-md-5" id="chargerToggleButton" <c:if test="${empty dataVo.picId}">style="display:none;"</c:if>>
                                <button type="button" class="btn btn-danger" onclick="deleteCharger();">삭제</button>
                            </div>
                        </div>
                        <input type="hidden" name="tkcgDeptCdId" id="tkcgDeptCdId" value="${dataVo.tkcgDeptCdId}" />
                        <input type="hidden" name="picId" id="picId" value="${dataVo.picId}" />
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block">
                                    <span class="text-info">담당자 이름을 입력한 후 선택하세요. &nbsp;(<span class="text-danger">바로 Enter키 누르지 마세요.</span>)
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="authrtGroupNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 권한유형
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:cdId type="radio" hghrkCdId="CMS" cdId="authrtGroupNm" id="authrtGroupNm" values="${dataVo.authrtGroupNm}" click="toggleAuthrtGroupNm(this);" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group author-dept-group" <c:if test="${dataVo.authrtGroupNm ne 'IN_DEPT' and dataVo.authrtGroupNm ne 'EQ_DEPT'}">style="display:none;"</c:if>>
                    <label for="tmpDeptNm" class="control-label col-md-2">
                        <span class="mandatory">*</span> 관리부서
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-3">
                                <input type="text" name="tmpAuthorDeptNm" id="tmpAuthorDeptNm" value="" class="form-control" placeholder="부서명을 입력하세요." />
                            </div>
                            <div class="col-md-9">
                                <div class="col-md-5">
                                    <p class="form-control-static" id="authorDeptNm">
                                        <c:if test="${not empty dataVo.authorDeptNm}">${dataVo.authorDeptNm}</c:if>
                                    </p>
                                </div>
                                <div class="col-md-7" id="authorDeptToggleButton" <c:if test="${empty dataVo.authrtDeptCdId}">style="display:none;"</c:if>>
                                    <button type="button" class="btn btn-danger" onclick="deleteAuthorDept();">삭제</button>
                                </div>
                            </div>
                            <input type="hidden" name="authrtDeptCdId" id="authrtDeptCdId" value="${dataVo.authrtDeptCdId}" />
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block">
                                    <span class="text-info">부서명을 입력한 후 선택하세요. &nbsp;(<span class="text-danger">바로 Enter키 누르지 마세요.</span>)
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group author-charger-group" <c:if test="${dataVo.authrtGroupNm ne 'CHARGER'}">style="display:none;"</c:if>>
                    <label for="tmpDeptNm" class="control-label col-md-2">
                        <span class="mandatory">*</span> 관리담당자
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-5">
                                <select name="authrtPicIds" id="authrtPicIds" multiple="multiple" size="5" class="form-control">
                                    <c:forEach items="${dataVo.authorChargerList}" var="authorCharger">
                                        <option value="${authorCharger.authrtPicId}">${authorCharger.authorPicNm}&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;${authorCharger.deptNm}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2" id="authorChargerToggleButton">
                                <button type="button" class="btn btn-danger btn-xs" onclick="deleteAuthorCharger();">선택삭제</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <input type="text" name="tmpAuthorChargerNm" id="tmpAuthorChargerNm" value="" class="form-control" placeholder="이름을 입력하세요." />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block">
                                    <span class="text-info">담당자 이름을 입력한 후 선택하세요. &nbsp;(<span class="text-danger">바로 Enter키 누르지 마세요.</span>)
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty dataVo.userGradList}">
                    <div class="form-group">
                        <label for="authrtGroupNm" class="control-label col-sm-2">사용자유형 </label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-sm-12">
                                    <c:forEach items="${dataVo.userGradList}" var="userGrad">
                                        <label for="userGrdCdIds${userGrad.userGrdCdId}" class="checkbox-inline checkbox-primary">
                                            <input type="checkbox" name="userGrdCdIds" id="userGrdCdIds${userGrad.userGrdCdId}" value="${userGrad.userGrdCdId}" <c:if test="${not empty userGrad.hasGrad and userGrad.hasGrad}">checked="checked"</c:if> class="styled" />
                                            ${userGrad.userGrdNm}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="help-block">
                                        <span class="text-info"><strong>특정 등급의 회원에게만 공개</strong>를 원하는 경우에 해당 등급을 선택합니다.</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
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
        <div class="row block">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <c:if test="${!dataVo.isFolder}">
                        <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteCms.do');">삭제</button>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

    <div id="lwprtMenuUrlAddrDummy" style="display: none">
        <div class="row block">
            <div class="col-md-8">
                <input type="text" name="lwprtMenuUrlAddr" value="" class="form-control" placeholder="사용자메뉴URL" />
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-danger" onclick="delLwprtMenuUrlAddr(this);">삭제</button>
            </div>
        </div>
    </div>
</body>
</html>