<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<title>게시물 목록</title>

<op:jsTag type="intra" items="ui, opform" />

<!-- 기능별 스크립트 정의 -->
<script type="text/javascript" src="/resources/samples/crud/samples.crud.js"></script>
<script type="text/javascript">
        //<![CDATA[

        /*
         * 공통 초기화 기능
         */
        $(document).ready(function() {
            // select 박스 선택 값 설정
            opSelected("q_searchKey", "${paramMap.q_searchKey}");

            // 다국어관리 (셈플 예제이므로 아래 링크에서 test 라는 코드가 등록되어 있지 않으면 정상 작동하지 않음)
            $("#multiLang").click(function(event) {
                var option = "chrome, centerscreen, dependent=yes, width=950, height=650, dialog=yes, modal=yes, resizable=yes,"; 
                option += "scrollbars=yes, location=0, status=0, menubar=0, toolbar=0, left=50, top=50";
                var href = $(this).attr("href");
                var multiLangWin = window.open(href, "multiLang", option);
                multiLangWin.focus();

                return false;
            });
        });

        var opExcelPop = function() {
            var url = "PD_excelRegistSample.do";
            var option = {
                href:url,
                width: "100%",
                height: 300
            };

            $.fn.opmodal(option);
        };

        //]]>
    </script>

<%-- 엑셀출력 스크립트 생성--%>
<op:excelDown excelKey="crud" btnId="excelDown" searchAt="Y" />
</head>
<body>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_crud.list.do" class="form-inline">
            <input type="hidden" name="q_regSn" id="q_regSn" value="" />
            <div class="block">
                <div class="form-group">
                    <label for="q_searchKey" class="sr-only">항목</label>
                    <select name="q_searchKey" id="q_searchKey" class="select-search" style="width: 100px">
                        <option value=""><op:message cdId="common.selectOption" /></option>
                        <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>제목</option>
                        <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected="selected"</c:if>>내용</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="q_searchVal" class="sr-only">검색어</label>
                    <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                </div>
                <div class="form-group">
                    <label for="q_startDt" class="sr-only">시작일</label>
                    <input type="text" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" class="from-date form-control" placeholder="시작일" />
                </div>
                <div class="form-group">
                    <label for="q_endDt" class="sr-only">종료일</label>
                    <input type="text" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" class="to-date form-control" placeholder="종료일" />
                </div>
                <div class="form-group">
                    <label for="q_endDt" class="sr-only">작성일</label>
                    <input type="text" name="q_regDt" id="q_regDt" value="${paramMap.q_regDt}" class="datepicker form-control" placeholder="작성일">
                </div>
                <button type="submit" class="btn btn-info">검색</button>
                <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
            </div>
            <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
            <op:pagerParam title="셈플게시물 목록" ignores="" />
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="기본게시판 목록으로 순번,분류,제목,작성자,작성일,조회 정보를 제공합니다.">
            <caption class="hidden">기본게시판 목록</caption>
            <colgroup>
                <col width="6%" />
                <col width="6%" />
                <col width="8%" />
                <col width="" />
                <col width="10%" />
                <col width="12%" />
                <col width="8%" />
                <col width="8%" />
            </colgroup>
            <thead>
                <tr>
                    <th><input type="checkbox" value="Y" name="chk-all" id="chk-all" /></th>
                    <th>순번</th>
                    <th>분류</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회</th>
                    <th>답변</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="baseVo" varStatus="status">
                    <tr class="">
                        <td class="text-center">
                            <input type="checkbox" name="regSns" value="${baseVo.regSn}" />
                        </td>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">${baseVo.clsfNo}</td>
                        <td>
                            <a onclick="opView(${baseVo.regSn});return false;" href="BD_crud.view.do?q_regSn=${baseVo.regSn}">${baseVo.ttl}</a>
                            <c:if test="${baseVo.fileCnt > 0}">
                                <a href="/component/file/zipdownload.do?q_fileSn=${baseVo.fileSn}" title="첨부파일이 ${baseVo.fileCnt}개 존재합니다."><span class="label label-danger">${baseVo.fileCnt}</span></a>
                            </c:if>
                            <c:if test="${baseVo.commCnt > 0}">
                                <a href="#" class="t_reply" title="댓글이 ${baseVo.commCnt}개 존재합니다."><span class="label label-default">${baseVo.commCnt}</span></a>
                            </c:if>
                        </td>
                        <td class="text-center">${baseVo.rgtrNm}</td>
                        <td class="text-center">${baseVo.regDt}</td>
                        <td class="text-center">
                            <span class="text_primary">${baseVo.inqCnt}</span>
                        </td>
                        <td class="text-center">
                            <span class="label label-success"> <c:choose>
                                    <c:when test="${baseVo.answerAt eq 'Y'}">
                                    완료
                                </c:when>
                                    <c:otherwise>
                                    대기
                                </c:otherwise>
                                </c:choose>
                            </span>
                        </td>
                    </tr>
                </c:forEach>

                <op:no-data obj="${pager}" colspan="8" />

            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <div class="row block">
        <div class="col-md-12 btn-group">
            <div class="pull-left">
                <button type="button" class="btn btn-danger" onclick="opDeleteList();">선택삭제</button>
                <button type="button" class="btn btn-success" onclick="opExcelPop();">엑셀일괄등록</button>
                <button type="button" class="btn btn-success" id="excelDown">엑셀출력</button>
                <a href="/intra/multilang/PD_selectConnectMultilangList.do?q_upMtlngCdNm=test" class="btn btn-success" id="multiLang">다국어관리</a>
            </div>
            <div class="pull-right">
                <button type="button" class="btn btn-success" onclick="opInsertForm();">신규등록</button>
            </div>
        </div>
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>