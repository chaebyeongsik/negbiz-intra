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

<!DOCTYPE html>
<html lang="ko">
<head>
<title>코드선택이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/codeChoiceHistory/js/codeChoiceHistory.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectCodeChoiceHistoryList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_regSn" id="q_regSn" value="" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchKey" class="sr-only">항목</label>
                        <select name="q_searchKey" id="q_searchKey" class="select">
                            <option value="">-- 검색선택 --</option>
                            <option value="1001">제목</option>
                            <option value="1002">내용</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <div class="form-group">
                        <label for="q_startDt" class="sr-only">시작일</label>
                        <input type="text" class="from-date form-control" name="q_startDt" id="q_startDt" placeholder="시작일" />
                    </div>
                    <div class="form-group">
                        <label for="q_endDt" class="sr-only">종료일</label>
                        <input type="text" class="to-date form-control" name="q_endDt" id="q_endDt" placeholder="종료일" />
                    </div>
                    <div class="form-group">
                        <label for="q_regDt" class="sr-only">작성일</label>
                        <input type="text" class="datepicker form-control" name="q_regDt" id="q_regDt" placeholder="작성일">
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="셈플게시물 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="코드이력 코드선택이력  목록으로 순번,로그일시,로그유형,코드선택,코드,상위코드,상위코드명,수정자ID,수정자명,수정일시, 정보를 제공합니다.">
            <caption class="hidden">코드이력 코드선택이력 목록</caption>
            <colgroup>
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>로그일시</th>
                    <th>로그유형</th>
                    <th>코드선택</th>
                    <th>코드</th>
                    <th>상위코드</th>
                    <th>상위코드명</th>
                    <th>수정자ID</th>
                    <th>수정자명</th>
                    <th>수정일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.logDt}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.logType}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.codeChoise}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.cdId}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.upCdId}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.upCdIdNm}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.mdfrId}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.updusrNm}</a>
                        </td>
                        <td>
                            <a href="BD_selectCodeChoiceHistory.do?q_logDt=${dataVo.logDt}&amp;q_logType=${dataVo.logType}&amp;q_codeChoise=${dataVo.codeChoise}">${dataVo.updtDt}</a>
                        </td>

                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="10" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="submit" class="btn btn-success">저장</button>
                <button type="reset" class="btn btn-default">재작성</button>

                <button type="submit" class="btn btn-success" onclick="opInsertForm('BD_insertCodeChoiceHistoryForm.do');">등록</button>
                <button type="button" class="btn btn-success" onclick="opUpdateForm('BD_updateCodeChoiceHistoryForm.do');">수정</button>
                <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteCodeChoiceHistory.do');">삭제</button>
                <button type="button" class="btn btn-primary" onclick="opList('BD_selectCodeChoiceHistoryList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>