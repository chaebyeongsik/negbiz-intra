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
<title>도움말 관리</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });

    var opUpdateForm = function(obj, regSn){
        $().opmodal({
            width : 600,
            height : 500,
            href : "PD_updateHpcmForm.do?regSn=" + regSn
        });
    };

    var opInsertForm = function(){
        $().opmodal({
            width : 900,
            height : 500,
            href : "PD_insertHpcmForm.do"
        });
    };

    var opDelete = function(regSn) {
        if(!confirm("정말 삭제하시겠습니까?")) return;
        $("#regSn").val(regSn);
        $("#dataForm").attr("method","post");
        $("#dataForm").attr("action","ND_deleteHpcm.do");
        $("#dataForm").submit();
    };

    var opReload = function(){
        location.reload();
    };
    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectHpcmList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="regSn" id="regSn" value="" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchKey" class="sr-only">항목</label>
                        <select name="q_searchKey" id="q_searchKey" class="select">
                            <option value="">-- 선택 --</option>
                            <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected=selected</c:if>>메뉴명</option>
                            <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected=selected</c:if>>내용</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="도움말게시판 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="도움말 목록으로 순번,도움말아이디,메뉴경로,메뉴사용여부,도움말내용에 대한 정보 및 각 도움말별 수정/삭제 버튼을 제공합니다.">
            <caption class="hidden">도움말 목록</caption>
            <colgroup>
                <col width="70px" />
                <col width="250px" />
                <col width="120px" />
                <col width="" />
                <col width="180px" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>메뉴경로</th>
                    <th>메뉴사용여부</th>
                    <th>도움말내용</th>
                    <th>수정/삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            ${dataVo.menuPath}
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.menuUseYn eq 'Y'}">사용</c:when>
                                <c:otherwise><span class="text-danger">미사용</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <op:nrToBr content="${dataVo.docCn}" />
                        </td>
                        <td class="text-center">
                            <button type="button" class="btn btn-success btn-xs" onclick="opUpdateForm(this, ${dataVo.regSn});">수정</button>
                            <button type="button" class="btn btn-danger btn-xs" onclick="opDelete(${dataVo.regSn});">삭제</button>
                        </td>

                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="5" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <op:pager pager="${pager}" />

    <!-- 버튼 -->
    <div class="block clearfix">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="submit" class="btn btn-success" onclick="opInsertForm('BD_insertHpcmForm.do');">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>