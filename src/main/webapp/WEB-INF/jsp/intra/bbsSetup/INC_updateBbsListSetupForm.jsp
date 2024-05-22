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

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>
<%@ page import="zesinc.intra.bbsSetup.support.BbsSetupConstant" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판환경설정 관리</title>

<op:jsTag type="intra" items="opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {

            var target = $("#ttlAjmtSz");
            if(!target.notnullValidator() || !target.digitsValidator() || !target.rangeValidator(0,99)) {
                opWarningMsg("제목생략길이는 숫자 2자리 이하로 필수입력 항목입니다.");
                target.focus();
                return false;
            }

            target = $("#newIndctDayCnt");
            if(!target.notnullValidator() || !target.digitsValidator() || !target.rangeValidator(0,99)) {
                opWarningMsg("새 글 표시 날짜는 숫자 2자리 이하로 필수입력 항목입니다.");
                target.focus();
                return false;
            }

            $(this).ajaxSubmit({
                url: "ND_updateBbsListSetup.do",
                type: "POST",
                dataType: "json",
                success: function(response){
                    try {
                        opJsonMsg(response);
                    } catch (e) {
                        // 시스템 오류 발생시 처리 구간
                        opErrorMsg(response, e);
                        return;
                    }
                }
            });
            return false;
        });
    });

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateBbsListSetup.do" class="form-horizontal">
        <input type="hidden" id="seCdId" name="seCdId" value="<%= BbsSetupConstant.SECTION_CODE_LIST %>" />
        <input type="hidden" id="bbsStngSn" name="bbsStngSn" value="${dataVo.bbsStngSn}" />
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="bbsNm" class="control-label col-md-2"> <span class="mandatory">*</span> 페이지당 목록 수</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-2">
                                <select name="pagePstCnt" id="pagePstCnt" class="select">
                                    <c:forEach items="${RPP_NUM}" var="rppNum">
                                        <option value="${rppNum}" <c:if test="${rppNum eq dataVo.pagePstCnt}">selected="selected"</c:if>>${rppNum}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-5">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">한 페이지에 표시할 게시글 수를 정의합니다.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="clsfNms" class="control-label col-md-2"> <span class="mandatory">*</span> 제목 생략 길이</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-2">
                                <input type="text" name="ttlAjmtSz" id="ttlAjmtSz" value="${dataVo.ttlAjmtSz}" class="form-control" />
                            </div>
                            <div class="col-md-5">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">최대 3자리</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 제목이 화면보다 길어 잘릴 경우 생략해야 할 길이를 지정합니다. [한글 : 2글자, 영문 :1 글자]</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="bbsType" class="control-label col-md-2"> <span class="mandatory">*</span> 새 글 표시 날짜</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-2">
                                <input type="text" name="newIndctDayCnt" id="newIndctDayCnt" value="${dataVo.newIndctDayCnt}" class="form-control" />
                            </div>
                            <div class="col-md-5">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">단위: 일 (숫자, 최대 2자리)</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 새글 [아이콘 표시] 아이콘을 표시할 기준 날짜를 지정합니다. 기준 날짜에 포함된다면 아이콘이 표시됩니다.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="clsfUseYnY" class="control-label col-md-2">강조 조회 수 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-2">
                                <input type="text" name="pstEmphsInqCnt" id="pstEmphsInqCnt" value="${dataVo.pstEmphsInqCnt}" class="form-control" />
                            </div>
                            <div class="col-md-5">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">숫자, 최대 3자리</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 강조해야 할 게시글의 기준 조회수를 지정합니다. 조회수가 설정 값 초과 시 강조됩니다.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="block clearfix">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-success">저장</button>
                        <button type="button" class="btn btn-primary" onclick="opList('BD_selectBbsSetupList.do');">목록</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>