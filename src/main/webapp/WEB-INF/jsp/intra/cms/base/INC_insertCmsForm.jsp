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

<op:jsTag type="intra" items="opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.js"></script>

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
                            Optree.addChildren({
                                "key" : $("#userMenuEngNm").val(),
                                "title" : $("#menuNm").val(),
                                "baseKey" : $("#siteSn").val(),
                                "lazy" : false
                            });
                            $("#dataForm")[0].reset();
                            opSelected("lytCdNo", "", "");
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

        opSelected("lytCdNo", "${dataVo.lytCdNo}", "");
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var duplicateYn = $("#duplicateYn").val();
        if(duplicateYn == "N") {
            opWarningMsg(OpMessage.common.overlapCode);
            return false;
        }
        return true;
    };

    //]]>
</script>
</head>
<body>

    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertCms.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <input type="hidden" name="duplicateYn" id="duplicateYn" value="Y" />
        <input type="hidden" name="siteSn" id="siteSn" value="${paramMap.q_siteSn}" />
        <input type="hidden" name="parntsMenuSn" id="parntsMenuSn" value="${paramMap.q_userMenuEngNm}" />

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label class="control-label col-sm-2"> 상위 코드 </label>
                    <div class="col-sm-10">
                        <p class="form-control-static" id="nodePath">코드를 선택하세요.</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userMenuEngNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 사용자메뉴코드
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-4">
                                <input type="text" name="userMenuEngNm" id="userMenuEngNm" value="" style="ime-mode: inactive;" class="form-control" placeholder="사용자메뉴코드" />
                            </div>
                            <div class="col-sm-8" id="duplicateText"></div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="userMenuEngNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuNm" class="control-label col-sm-2">메뉴명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-4">
                                <input type="text" name="menuNm" id="menuNm" value="" class="form-control" placeholder="메뉴명" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="menuNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuType" class="control-label col-sm-2">메뉴유형 </label>
                    <div class="col-sm-10">
                        <op:cdId type="radio" hghrkCdId="CMS" cdId="menuType" id="menuType" values="${dataVo.menuType}" />
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="menuType" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="linkTypeNm" class="control-label col-sm-2">링크유형 </label>
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
                        <select name="lytCdNo" id="lytCdNo" class="select">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${dataList}" var="layoutVo" varStatus="vst">
                                <option value="${layoutVo.lytCdNo}">${layoutVo.lytNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="control-label col-sm-2">부모레이아웃사용여부 </label>
                    <div class="col-sm-4">
                        <label for="upLytUseYnY" class="radio-inline radio-primary">
                            <input type="radio" name="upLytUseYn" id="upLytUseYnY" value="Y" <c:if test="${dataVo.upLytUseYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                            예
                        </label>
                        <label for="upLytUseYnN" class="radio-inline radio-primary">
                            <input type="radio" name="upLytUseYn" id="upLytUseYnN" value="N" <c:if test="${dataVo.upLytUseYn eq 'N'}">checked="checked"</c:if> class="styled" />
                            아니오
                        </label>
                    </div>
                </div>
            </div>
        </div>

        <div class="row block">
            <div class="col-sm-12">
                <div class="pull-right">
                    ※ <span class="text-info">레이아웃 설정은 특별한 경우를 제외하고 기본 설정을 사용하시기 바랍니다.</span>
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