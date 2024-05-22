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
<title>게시판설정 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsMngr/js/bbsMngr.js"></script>
<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("q_searchKey", "${paramMap.q_searchKey}");
    });

    // 게시물 관리
    var opBbsPop = function(obj, bbsSn) {
        var option = "chrome, centerscreen, dependent=yes, width=1024, height=768, dialog=yes, modal=yes, ";
        option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";

        var href = "/intra/bbs/PD_selectBbsList.do?q_bbsSn=" + bbsSn;
        var bbsPreview = window.open(href, "opBbsPreview"+bbsSn, option);
        bbsPreview.focus();

        return false;
    };

    // 수정폼
    var opBbsMngrUpdateForm = function(bbsSn, bbsNm) {
        location.href = " BD_updateBbsMngrForm.do?q_bbsSn=" + bbsSn + "&q_bbsNm=" + encodeURIComponent(bbsNm);
    };
    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectBbsMngrList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_siteSn" class="control-label">도메인</label>
                        <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_bbsStngSn" class="control-label">환경설정</label>
                        <select name="q_bbsStngSn" id="q_bbsStngSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${bbsSetupList}" var="bbsSetupVo" varStatus="vst">
                                <option value="${bbsSetupVo.bbsStngSn}" <c:if test="${bbsSetupVo.bbsStngSn eq paramMap.q_bbsStngSn}">selected="selected"</c:if>>${bbsSetupVo.bbsStngNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchKey" class="sr-only">항목</label>
                        <select name="q_searchKey" id="q_searchKey" class="select" style="width: 150px;">
                            <option value="">-- 검색선택 --</option>
                            <option value="1001">게시판명</option>
                            <option value="1002">게시판코드</option>
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
                <op:pagerParam title="게시판설정 목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
        <form name="listForm" id="listForm" method="post" action="ND_deleteBbsMngrList.do" class="form-inline">
            <!-- 리스트 -->
            <table class="table table-bordered table-striped table-hover op-table-list" summary="게시판설정 목록으로 순번,게시판코드,게시판설정일련번호,게시판명,게시판설명,공지사용여부,분류사용여부,FEED사용여부,정렬순서,사용여부,등록자ID,등록자명,등록일시,수정자ID,수정자명,수정일시, 정보를 제공합니다.">
                <caption class="hidden">게시판설정 목록</caption>
                <colgroup>
                    <col width="5%" />
                    <col width="*" />
                    <col width="8%" />
                    <col width="8%" />
                    <col width="8%" />
    <%--                 <col width="8%" /> --%>
                    <col width="10%" />
                    <col width="10%" />
                </colgroup>
                <thead>
                    <tr>
                        <th><input type="checkbox" id="chkall" name="chkall" /></th>
                        <th>게시판명</th>
                        <th>사용여부</th>
                        <th>분류</th>
                        <th>공지</th>
    <!--                     <th>FEED</th> -->
                        <th>썸네일생성</th>
                        <th>게시물관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                        <c:set var="style" value="${status.count % 2 != 1 ? 'bg-style' : ''}" />
                        <tr class="${style}">
                            <td class="text-center">
                                <input type="checkbox" id="bbsSns${status.count}" name="bbsSns" value="${dataVo.bbsSn}" />
                            </td>
                            <td>
                                <div class="block">
                                    <a href="#none" onclick="opBbsMngrUpdateForm('${dataVo.bbsSn}', '${dataVo.bbsNm}');">${dataVo.bbsNm} <${dataVo.bbsSn}></a>
                                    <i class="icon-file"></i> <span>${dataVo.bbscttCo}</span>
                                    <i class="icon-attachment"></i> <span>${dataVo.fileCo}</span>
                                    <i class="icon-bubble6"></i> <span>${dataVo.opatclCo}</span>
                                </div>
                                <div>
                                    ${dataVo.bbsExpln}
                                </div>
                            </td>
                            <td class="text-center">
                                <a href="#useYn${status.index}" onclick="opUpdateUseYn(this, '${dataVo.bbsSn}', '0');" >
                                    <c:if test="${dataVo.useYn eq 'Y'}"><div id="useYn"><i class="icon-checkmark"></i> <span>사용</span></div></c:if>
                                    <c:if test="${dataVo.useYn eq 'N'}"><div id="useYn"><i class="icon-close"></i> <span>미사용</span></div></c:if>
                                </a>
                            </td>
                            <td class="text-center">
                                <a href="#clsfUseYn${status.index}" onclick="opUpdateUseYn(this, '${dataVo.bbsSn}');" >
                                    <c:if test="${dataVo.clsfUseYn eq 'Y'}"><div id="clsfUseYn"><i class="icon-checkmark"></i> <span>사용</span></div></c:if>
                                    <c:if test="${dataVo.clsfUseYn eq 'N'}"><div id="clsfUseYn"><i class="icon-close"></i> <span>미사용</span></div></c:if>
                                </a>
                            </td>
                            <td class="text-center">
                                <a href="#atchFileUseYn${status.index}" onclick="opUpdateUseYn(this, '${dataVo.bbsSn}');" >
                                    <c:if test="${dataVo.ntcUseYn eq 'Y'}"><div id="ntcUseYn"><i class="icon-checkmark"></i> <span>사용</span></div></c:if>
                                    <c:if test="${dataVo.ntcUseYn eq 'N'}"><div id="ntcUseYn"><i class="icon-close"></i> <span>미사용</span></div></c:if>
                                </a>
                            </td>
    <!--                         <td class="text-center"> -->
    <%--                             <a href="#opatclsfUseYn${status.index}" onclick="opUpdateUseYn(this, '${dataVo.bbsSn}');" > --%>
    <%--                                 <c:if test="${dataVo.nfeedUseYn eq 'Y'}"><div id="nfeedUseYn"><i class="icon-checkmark"></i> <span>사용</span></div></c:if> --%>
    <%--                                 <c:if test="${dataVo.nfeedUseYn eq 'N'}"><div id="nfeedUseYn"><i class="icon-close"></i> <span>미사용</span></div></c:if> --%>
    <!--                             </a> -->
    <!--                         </td> -->
                            <td class="text-center">
                                <button type="button" class="btn btn-info" onclick="opBbsCreateThumb('${dataVo.bbsSn}');">썸네일생성</button>
                            </td>
                            <td class="text-center">
                                <button type="button" class="btn btn-info" onclick="opBbsPop(this, '${dataVo.bbsSn}');">게시물 관리</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${pager}" colspan="7" />
                </tbody>
            </table>
        </form>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="block clearfix">
            <div class="col-sm-12 btn-group">
                <div class="pull-left">
                    <button type="button" class="btn btn-danger" onclick="opCheckDelete();">선택삭제</button>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-info" onclick="opInsertForm('BD_insertBbsMngrForm.do');">신규등록</button>
                </div>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>