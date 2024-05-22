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
<%@ taglib uri="http://www.openworks.kr/jsp/bbs" prefix="bbs"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판 관리</title>

<op:jsTag type="user" items="ui, opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/user/bbs/js/bbs.js"></script>

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
        <form name="dataForm" id="dataForm" method="get" action="BD_selectBbsList.do" class="form-inline">

            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_bbsSn" id="q_bbsSn" value="${paramMap.q_bbsSn}" />
                <input type="hidden" name="q_bbsDocNo" id="q_bbsDocNo" value="" />
                <div class="block">
                    <div class="form-group">
                        <c:if test="${bbsConfigVo.clsfUseYn eq 'Y'}">
                            <label for="q_clsfNo" class="sr-only">분류</label>
                            <select name="q_clsfNo" id="q_clsfNo" class="select" style="width: 150px;">
                                <option value="">-- 분류선택 --</option>
                                <c:forEach items="${bbsConfigVo.bbsCtgryList}" var="ctgryVo" varStatus="status">
                                    <option value="${ctgryVo.clsfNo}"<c:if test="${paramMap.q_clsfNo eq ctgryVo.clsfNo}"> selected="selected"</c:if>>${ctgryVo.clsfNm}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <label for="q_searchKeyTy" class="sr-only">항목</label>
                        <select name="q_searchKeyTy" id="q_searchKeyTy" class="select" style="width: 150px;">
                            <option value="">-- 검색선택 --</option>
                            <c:forEach items="${bbsConfigVo.searchColunms}" var="searchDataVo" varStatus="status">
                                <c:set var="searchKey" value="${searchDataVo.colId}___${searchDataVo.srchType}" />
                                <option value="${searchKey}"<c:if test="${paramMap.q_searchKeyTy eq searchKey}"> selected="selected"</c:if>>${searchDataVo.colNm}</option>
                            </c:forEach>
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
                <op:pagerParam title="게시판 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <%-- 게시판 항목을 사용이 용이하게 변수를 할당하여 가져옴 --%>
    <c:set var="column" value="${bbsConfigVo.bbsItemMap}" />
    <%-- 목록 표시 컬럼 수 + 순번 --%>
    <c:set var="itemLen" value="${fn:length(bbsConfigVo.listColunms) + 1}" />
    <%-- 썸네일 사용시 추가 1 --%>
    <c:if test="${bbsConfigVo.thmbUseYn eq 'Y'}" >
        <c:set var="itemLen" value="${itemLen + 1}" />
    </c:if>

    <div class="block table-responsive">

        <%-- summary 정보 생성. 필요시에만 사용 --%>
        <c:set var="tableSummary" value="${UserMenu.menuPathNm} 메뉴의 게시물 정보이며" />
        <c:forEach var="listColunm"  items="${bbsConfigVo.listColunms}" varStatus="tableSummaryStatus">
            <c:set var="tableSummary" value="${tableSummary} ${listColunm.colNm}," />
        </c:forEach>

        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover" summary="${tableSummary} 항목을 제공합니다.">
            <caption class="hidden">${bbsConfigVo.bbsExpln}</caption>
            <colgroup>
                <col width="" />
                <c:if test="${column.bbsSn.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.bbsDocNo.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.rfrncDocNo.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.notice.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.ntcBgngDt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.ntcEndDt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${bbsConfigVo.thmbUseYn eq 'Y'}" ><col width="150" /></c:if>
                <c:if test="${column.ttl.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.docContsCn.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.clsfNo.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.lwrkClsfSn.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.mainCn.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.pstgBgngDt.inptIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.pstgEndDt.inptIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.ansDtlCn.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.ansDt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.file.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.deptCdId.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.deptNm.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.picId.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.picNm.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.cpyrht.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.tagNms.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.inqCnt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.dclrCnt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.rcmdtnCnt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.stsfdgEvl.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.cntnBrwsrNm.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.userIdntfNm.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.ipAddr.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.rgtrId.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.rgtrNm.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.regDt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.pswd.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.mdfrId.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.updtDt.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn1.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn2.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn3.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn4.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn5.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn6.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn7.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn8.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn9.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
                <c:if test="${column.flctnColCn10.lstIndctYn eq 'Y'}" ><col width="" /></c:if>
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <c:if test="${column.bbsSn.lstIndctYn eq 'Y'}" ><th><bbs:bbsSn type="text" /></th></c:if>
                    <c:if test="${column.bbsDocNo.lstIndctYn eq 'Y'}" ><th><bbs:bbsDocNo type="text" /></th></c:if>
                    <c:if test="${column.rfrncDocNo.lstIndctYn eq 'Y'}" ><th><bbs:rfrncDocNo type="text" /></th></c:if>
                    <c:if test="${column.ntcPstYn.lstIndctYn eq 'Y'}" ><th><bbs:notice type="text" /></th></c:if>
                    <c:if test="${column.ntcBgngDt.lstIndctYn eq 'Y'}" ><th><bbs:ntcBgngDt type="text" /></th></c:if>
                    <c:if test="${column.ntcEndDt.lstIndctYn eq 'Y'}" ><th><bbs:ntcEndDt type="text" /></th></c:if>
                    <c:if test="${bbsConfigVo.thmbUseYn eq 'Y'}" ><th><bbs:thumbnail type="text" /></th></c:if>
                    <c:if test="${column.ttl.lstIndctYn eq 'Y'}" ><th><bbs:ttl type="text" /></th></c:if>
                    <c:if test="${column.docContsCn.lstIndctYn eq 'Y'}" ><th><bbs:docContsCn type="text" /></th></c:if>
                    <c:if test="${column.clsfNo.lstIndctYn eq 'Y'}" ><th><bbs:clsfNo type="text" /></th></c:if>
                    <c:if test="${column.lwrkClsfSn.lstIndctYn eq 'Y'}" ><th><bbs:lwrkClsfSn type="text" /></th></c:if>
                    <c:if test="${column.mainCn.lstIndctYn eq 'Y'}" ><th><bbs:mainCn type="text" /></th></c:if>
                    <c:if test="${column.pstgBgngDt.inptIndctYn eq 'Y'}" ><th><bbs:pstgBgngDt type="text" /></th></c:if>
                    <c:if test="${column.pstgEndDt.inptIndctYn eq 'Y'}" ><th><bbs:pstgEndDt type="text" /></th></c:if>
                    <c:if test="${column.ansDtlCn.lstIndctYn eq 'Y'}" ><th><bbs:ansDtlCn type="text" /></th></c:if>
                    <c:if test="${column.ansDt.lstIndctYn eq 'Y'}" ><th><bbs:ansDt type="text" /></th></c:if>
                    <c:if test="${column.file.lstIndctYn eq 'Y'}" ><th><bbs:file type="text" /></th></c:if>
                    <c:if test="${column.deptCdId.lstIndctYn eq 'Y'}" ><th><bbs:deptCdId type="text" /></th></c:if>
                    <c:if test="${column.deptNm.lstIndctYn eq 'Y'}" ><th><bbs:deptNm type="text" /></th></c:if>
                    <c:if test="${column.picId.lstIndctYn eq 'Y'}" ><th><bbs:picId type="text" /></th></c:if>
                    <c:if test="${column.picNm.lstIndctYn eq 'Y'}" ><th><bbs:picNm type="text" /></th></c:if>
                    <c:if test="${column.cpyrht.lstIndctYn eq 'Y'}" ><th><bbs:cpyrht type="text" /></th></c:if>
                    <c:if test="${column.tagNms.lstIndctYn eq 'Y'}" ><th><bbs:tagNms type="text" /></th></c:if>
                    <c:if test="${column.inqCnt.lstIndctYn eq 'Y'}" ><th><bbs:inqCnt type="text" /></th></c:if>
                    <c:if test="${column.dclrCnt.lstIndctYn eq 'Y'}" ><th><bbs:dclrCnt type="text" /></th></c:if>
                    <c:if test="${column.rcmdtnCnt.lstIndctYn eq 'Y'}" ><th><bbs:rcmdtnCnt type="text" /></th></c:if>
                    <c:if test="${column.stsfdgEvl.lstIndctYn eq 'Y'}" ><th><bbs:stsfdgEvl type="text" /></th></c:if>
                    <c:if test="${column.cntnBrwsrNm.lstIndctYn eq 'Y'}" ><th><bbs:cntnBrwsrNm type="text" /></th></c:if>
                    <c:if test="${column.userIdntfNm.lstIndctYn eq 'Y'}" ><th><bbs:userIdntfNm type="text" /></th></c:if>
                    <c:if test="${column.ipAddr.lstIndctYn eq 'Y'}" ><th><bbs:ipAddr type="text" /></th></c:if>
                    <c:if test="${column.rgtrId.lstIndctYn eq 'Y'}" ><th><bbs:rgtrId type="text" /></th></c:if>
                    <c:if test="${column.rgtrNm.lstIndctYn eq 'Y'}" ><th><bbs:rgtrNm type="text" /></th></c:if>
                    <c:if test="${column.regDt.lstIndctYn eq 'Y'}" ><th><bbs:regDt type="text" /></th></c:if>
                    <c:if test="${column.pswd.lstIndctYn eq 'Y'}" ><th><bbs:pswd type="text" /></th></c:if>
                    <c:if test="${column.mdfrId.lstIndctYn eq 'Y'}" ><th><bbs:mdfrId type="text" /></th></c:if>
                    <c:if test="${column.updtDt.lstIndctYn eq 'Y'}" ><th><bbs:updtDt type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn1.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn1 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn2.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn2 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn3.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn3 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn4.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn4 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn5.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn5 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn6.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn6 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn7.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn7 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn8.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn8 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn9.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn9 type="text" /></th></c:if>
                    <c:if test="${column.flctnColCn10.lstIndctYn eq 'Y'}" ><th><bbs:flctnColCn10 type="text" /></th></c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${noticeList}" var="dataVo" varStatus="noticeStatus">
                    <tr>
                        <td class="text-center">공지</td>
                        <c:if test="${column.bbsSn.lstIndctYn eq 'Y'}" ><td><bbs:bbsSn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.bbsDocNo.lstIndctYn eq 'Y'}" ><td><bbs:bbsDocNo type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rfrncDocNo.lstIndctYn eq 'Y'}" ><td><bbs:rfrncDocNo type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.notice.lstIndctYn eq 'Y'}" ><td><bbs:notice type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ntcBgngDt.lstIndctYn eq 'Y'}" ><td><bbs:ntcBgngDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ntcEndDt.lstIndctYn eq 'Y'}" ><td><bbs:ntcEndDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${bbsConfigVo.thmbUseYn eq 'Y'}" ><td><bbs:thumbnail type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ttl.lstIndctYn eq 'Y'}" ><td><bbs:ttl type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.docContsCn.lstIndctYn eq 'Y'}" ><td><bbs:docContsCn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.clsfNo.lstIndctYn eq 'Y'}" ><td><bbs:clsfNo type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.lwrkClsfSn.lstIndctYn eq 'Y'}" ><td><bbs:lwrkClsfSn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.mainCn.lstIndctYn eq 'Y'}" ><td><bbs:mainCn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.pstgBgngDt.inptIndctYn eq 'Y'}" ><td><bbs:pstgBgngDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.pstgEndDt.inptIndctYn eq 'Y'}" ><td><bbs:pstgEndDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ansDtlCn.lstIndctYn eq 'Y'}" ><td><bbs:ansDtlCn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ansDt.lstIndctYn eq 'Y'}" ><td><bbs:ansDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.file.lstIndctYn eq 'Y'}" ><td><bbs:file type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.deptCdId.lstIndctYn eq 'Y'}" ><td><bbs:deptCdId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.deptNm.lstIndctYn eq 'Y'}" ><td><bbs:deptNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.picId.lstIndctYn eq 'Y'}" ><td><bbs:picId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.picNm.lstIndctYn eq 'Y'}" ><td><bbs:picNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.cpyrht.lstIndctYn eq 'Y'}" ><td><bbs:cpyrht type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.tagNms.lstIndctYn eq 'Y'}" ><td><bbs:tagNms type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.inqCnt.lstIndctYn eq 'Y'}" ><td><bbs:inqCnt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.dclrCnt.lstIndctYn eq 'Y'}" ><td><bbs:dclrCnt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rcmdtnCnt.lstIndctYn eq 'Y'}" ><td><bbs:rcmdtnCnt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.stsfdgEvl.lstIndctYn eq 'Y'}" ><td><bbs:stsfdgEvl type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.cntnBrwsrNm.lstIndctYn eq 'Y'}" ><td><bbs:cntnBrwsrNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.userIdntfNm.lstIndctYn eq 'Y'}" ><td><bbs:userIdntfNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ipAddr.lstIndctYn eq 'Y'}" ><td><bbs:ipAddr type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rgtrId.lstIndctYn eq 'Y'}" ><td><bbs:rgtrId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rgtrNm.lstIndctYn eq 'Y'}" ><td><bbs:rgtrNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.regDt.lstIndctYn eq 'Y'}" ><td><bbs:regDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.pswd.lstIndctYn eq 'Y'}" ><td><bbs:pswd type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.mdfrId.lstIndctYn eq 'Y'}" ><td><bbs:mdfrId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.updtDt.lstIndctYn eq 'Y'}" ><td><bbs:updtDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn1.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn1 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn2.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn2 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn3.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn3 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn4.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn4 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn5.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn5 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn6.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn6 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn7.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn7 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn8.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn8 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn9.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn9 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn10.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn10 type="list" obj="${dataVo}" /></td></c:if>
                    </tr>
                </c:forEach>

                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <c:if test="${column.bbsSn.lstIndctYn eq 'Y'}" ><td><bbs:bbsSn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.bbsDocNo.lstIndctYn eq 'Y'}" ><td><bbs:bbsDocNo type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rfrncDocNo.lstIndctYn eq 'Y'}" ><td><bbs:rfrncDocNo type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.notice.lstIndctYn eq 'Y'}" ><td><bbs:notice type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ntcBgngDt.lstIndctYn eq 'Y'}" ><td><bbs:ntcBgngDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ntcEndDt.lstIndctYn eq 'Y'}" ><td><bbs:ntcEndDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${bbsConfigVo.thmbUseYn eq 'Y'}" ><td><bbs:thumbnail type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ttl.lstIndctYn eq 'Y'}" ><td><bbs:ttl type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.docContsCn.lstIndctYn eq 'Y'}" ><td><bbs:docContsCn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.clsfNo.lstIndctYn eq 'Y'}" ><td><bbs:clsfNo type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.lwrkClsfSn.lstIndctYn eq 'Y'}" ><td><bbs:lwrkClsfSn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.mainCn.lstIndctYn eq 'Y'}" ><td><bbs:mainCn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.pstgBgngDt.inptIndctYn eq 'Y'}" ><td><bbs:pstgBgngDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.pstgEndDt.inptIndctYn eq 'Y'}" ><td><bbs:pstgEndDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ansDtlCn.lstIndctYn eq 'Y'}" ><td><bbs:ansDtlCn type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ansDt.lstIndctYn eq 'Y'}" ><td><bbs:ansDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.file.lstIndctYn eq 'Y'}" ><td><bbs:file type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.deptCdId.lstIndctYn eq 'Y'}" ><td><bbs:deptCdId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.deptNm.lstIndctYn eq 'Y'}" ><td><bbs:deptNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.picId.lstIndctYn eq 'Y'}" ><td><bbs:picId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.picNm.lstIndctYn eq 'Y'}" ><td><bbs:picNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.cpyrht.lstIndctYn eq 'Y'}" ><td><bbs:cpyrht type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.tagNms.lstIndctYn eq 'Y'}" ><td><bbs:tagNms type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.inqCnt.lstIndctYn eq 'Y'}" ><td><bbs:inqCnt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.dclrCnt.lstIndctYn eq 'Y'}" ><td><bbs:dclrCnt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rcmdtnCnt.lstIndctYn eq 'Y'}" ><td><bbs:rcmdtnCnt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.stsfdgEvl.lstIndctYn eq 'Y'}" ><td><bbs:stsfdgEvl type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.cntnBrwsrNm.lstIndctYn eq 'Y'}" ><td><bbs:cntnBrwsrNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.userIdntfNm.lstIndctYn eq 'Y'}" ><td><bbs:userIdntfNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.ipAddr.lstIndctYn eq 'Y'}" ><td><bbs:ipAddr type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rgtrId.lstIndctYn eq 'Y'}" ><td><bbs:rgtrId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.rgtrNm.lstIndctYn eq 'Y'}" ><td><bbs:rgtrNm type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.regDt.lstIndctYn eq 'Y'}" ><td><bbs:regDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.pswd.lstIndctYn eq 'Y'}" ><td><bbs:pswd type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.mdfrId.lstIndctYn eq 'Y'}" ><td><bbs:mdfrId type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.updtDt.lstIndctYn eq 'Y'}" ><td><bbs:updtDt type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn1.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn1 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn2.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn2 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn3.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn3 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn4.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn4 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn5.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn5 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn6.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn6 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn7.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn7 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn8.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn8 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn9.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn9 type="list" obj="${dataVo}" /></td></c:if>
                        <c:if test="${column.flctnColCn10.lstIndctYn eq 'Y'}" ><td><bbs:flctnColCn10 type="list" obj="${dataVo}" /></td></c:if>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="${itemLen}" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 버튼 -->
    <div class="row block">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <bbs:button type="list" />
            </div>
        </div>
    </div>
    <!-- //버튼 -->

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>
