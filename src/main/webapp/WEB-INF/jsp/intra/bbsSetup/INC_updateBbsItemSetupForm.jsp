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

<op:jsTag type="intra" items="opform, opvalidate" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            dataString = $("#dataForm").serialize();
            $.ajax({
                type: "POST",
                url: "ND_updateBbsItemSetup.do",
                data: dataString,
                dataType: "json",
                success: function(response){
                    try {
                        opJsonMsg(response);
                    } catch (e) {
                        // 시스템 오류 발생시 처리 구간
                        opErrorMsg(response, e);
                        return;
                    }
                    //changeFlag = false;
                    //jsReloadTab();
                }
            });
            return false;
            
        });
        
        $("select[name$=_esntlYn]").change(function(i) {
            var selectObj = $(this).parent().parent().parent();
            var selectIdx = $(this).attr("id").replace("esntlYn", "");
            if ($(this).val() == "Y") {
                //selectObj.find("tr").eq(selectIdx).removeClass("danger");
            } else {
                //selectObj.find("tr").eq(selectIdx).addClass("danger");
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
    <form name="dataForm" id="dataForm" method="post" action="ND_updateBbsItemSetup.do" class="form-horizontal">
        <input type="hidden" id="seCdId" name="seCdId" value="<%= BbsSetupConstant.SECTION_CODE_ITEM %>" />
        <input type="hidden" id="bbsStngSn" name="bbsStngSn" value="${dataVo.bbsStngSn}" />
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="table-responsive">
            
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>컬럼 / 컬럼명</th>
                            <th>화면명</th>
                            <th>설명</th>
                            <th>입력유형</th>
                            <th>검색유형</th>
                            <th>필수</th>
                            <th>화면표시여부</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dataVo.bbsItemList}" var="bbsItemList" varStatus="status">
                            <tr>
                                <td>
                                    ${bbsItemList.colId}<br />
                                    ${bbsItemList.colNm}
                                    <input type="hidden" name="colId" id="colId${status.index}" value="${bbsItemList.colId}" />
                                    <input type="hidden" name="${bbsItemList.colId}_colNm" id="colNm${status.index}" value="${bbsItemList.colNm}" class="form-control" placeholder="컬럼명" />
                                </td>
                                <td>
                                    <input type="text" name="${bbsItemList.colId}_scrnNm" id="scrnNm${status.index}" value="${bbsItemList.scrnNm}" class="form-control" placeholder="화면명" />
                                </td>
                                <td>
                                    <input type="text" name="${bbsItemList.colId}_bbsColExpln" id="bbsColExpln${status.index}" value="${bbsItemList.bbsColExpln}" class="form-control" placeholder="안내문구" />
                                </td>
                                <td>
                                    <select name="${bbsItemList.colId}_colTypeNm" id="colTypeNm${status.index}" class="select">
                                        <option value="">--</option>
                                        <option value="TEXT" <c:if test="${bbsItemList.colTypeNm eq 'TEXT' or empty bbsItemList.colTypeNm}">selected="selected"</c:if>>한줄</option>
                                        <option value="TEXTAREA" <c:if test="${bbsItemList.colTypeNm eq 'TEXTAREA'}">selected="selected"</c:if>>여러줄</option>
                                        <option value="EMAIL" <c:if test="${bbsItemList.colTypeNm eq 'EMAIL'}">selected="selected"</c:if>>이메일</option>
                                        <option value="DATE" <c:if test="${bbsItemList.colTypeNm eq 'DATE'}">selected="selected"</c:if>>날짜</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="${bbsItemList.colId}_srchType" id="srchType${status.index}" class="select">
                                        <option value="">--</option>
                                        <c:forEach items="${dataList}" var="srchType">
                                            <option value="${srchType.cdId}" <c:if test="${srchType.cdId eq bbsItemList.srchType}">selected="selected"</c:if>>${srchType.cdNm}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="${bbsItemList.colId}_esntlYn" id="esntlYn${status.index}" class="select">
                                        <option value="Y" <c:if test="${bbsItemList.esntlYn eq 'Y' or empty bbsItemList.esntlYn}">selected="selected"</c:if>>예</option>
                                        <option value="N" <c:if test="${bbsItemList.esntlYn eq 'N'}">selected="selected"</c:if>>아니오</option>
                                    </select>
                                </td>
                                <td>
                                    <div class="col-md-12">
                                        <div class="row">
                                            <label for="lstIndctYn${status.index}" class="control-label col-md-4 text-right">목록</label>
                                            <div class="col-md-8">
                                                <select name="${bbsItemList.colId}_lstIndctYn" id="lstIndctYn${status.index}" class="select">
                                                    <option value="Y" <c:if test="${bbsItemList.lstIndctYn eq 'Y' or empty bbsItemList.lstIndctYn}">selected="selected"</c:if>>표시</option>
                                                    <option value="N" <c:if test="${bbsItemList.lstIndctYn eq 'N'}">selected="selected"</c:if>>미표시</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="inqIndctYn${status.index}" class="control-label col-md-4 text-right">읽기</label>
                                            <div class="col-md-8">
                                                <select name="${bbsItemList.colId}_inqIndctYn" id="inqIndctYn${status.index}" class="select">
                                                    <option value="Y" <c:if test="${bbsItemList.inqIndctYn eq 'Y' or empty bbsItemList.inqIndctYn}">selected="selected"</c:if>>표시</option>
                                                    <option value="N" <c:if test="${bbsItemList.inqIndctYn eq 'N'}">selected="selected"</c:if>>미표시</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="inptIndctYn${status.index}" class="control-label col-md-4 text-right">쓰기</label>
                                            <div class="col-md-8">
                                                <select name="${bbsItemList.colId}_inptIndctYn" id="inptIndctYn${status.index}" class="select">
                                                    <option value="Y" <c:if test="${bbsItemList.inptIndctYn eq 'Y' or empty bbsItemList.inptIndctYn}">selected="selected"</c:if>>표시</option>
                                                    <option value="N" <c:if test="${bbsItemList.inptIndctYn eq 'N'}">selected="selected"</c:if>>미표시</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            
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