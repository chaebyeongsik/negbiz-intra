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
<title>일정 관리 - 목록보기</title>

<op:jsTag type="intra" items="opform, ui" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/schdul/js/schdul.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });
        
        // 등록화면
        $("#registBtn").opmodal({
            href : "PD_updateSchdulForm.do",
            width : 850
        });
        // 수정화면
        $(".opSchdulPopup").opmodal({
            width : 850
        });

    });
    
    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {

        var bgngYmd = $.trim($("#q_startDt").val());
        var endYmd = $.trim($("#q_endDt").val());
        
        if(bgngYmd == "" && endYmd != "") {
            opWarningMsg("시작일을 입력하십시오.");
            $("#q_startDt").focus();
            return false;
        }
        
        if(bgngYmd != "" && endYmd == "") {
            opWarningMsg("종료일을 입력하십시오.");
            $("#q_endDt").focus();
            return false;
        }

        if(bgngYmd != "" && endYmd != "") {
            // 시작일과 종료일 비교
            if(bgngYmd > endYmd) {
                opWarningMsg("종료일이 시작일보다 클수 없습니다.");
                $("#q_endDt").focus();
                return false;
            }
        }

        return true;
    };

    //]]>
</script>
</head>
<body>
    <div id="tabs" class="tabbable page-tabs">
        <ul class="nav nav-tabs">
            <li class="active"><a href="BD_selectSchdulList.do">목록 보기</a></li>
            <li><a href="BD_selectSchdulCal.do">달력 보기</a></li>
        </ul>
    </div>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectSchdulList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_seCdId" class="control-label">구분</label>
                        <op:cdId type="select" hghrkCdId="schdul" cdId="se" id="q_seCdId" values="${paramMap.q_seCdId}" etc=" style=\"width: 100px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_hldySeCdId" class="control-label">휴일구분</label>
                        <op:cdId type="select" hghrkCdId="schdul" cdId="restdeSe" id="q_hldySeCdId" values="${paramMap.q_hldySeCdId}" etc=" style=\"width: 100px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_schdlSeCdId" class="control-label">일정구분</label>
                        <op:cdId type="select" hghrkCdId="schdul" cdId="schdulSe" id="q_schdlSeCdId" values="${paramMap.q_schdlSeCdId}" etc=" style=\"width: 100px;\"" />
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="">제목</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <div class="form-group">
                        <label for="q_startDt" class="control-label">시작일</label>
                        <input type="text" class="from-date form-control" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" />
                    </div>
                    <div class="form-group">
                        <label for="q_endDt" class="control-label">종료일</label>
                        <input type="text" class="to-date form-control" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="일정 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="일정관리  목록으로 순번,제목,구분,일정구분,시작일,종료일,시작시간,종료시간,등록자명,등록일시 정보를 제공합니다.">
            <caption class="hidden">일정관리 목록</caption>
            <colgroup>
                <col width="50" />
                <col width="" />
                <col width="100" />
                <col width="100" />
                <col width="300" />
                <col width="120" />
                <col width="100" />
                <col width="100" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>제목</th>
                    <th>구분</th>
                    <th>상세구분</th>
                    <th>기간</th>
                    <th>시간</th>
                    <th>등록자명</th>
                    <th>등록일시</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <a href="PD_updateSchdulForm.do?q_regSn=${dataVo.regSn}" class="opSchdulPopup">${dataVo.ttl}</a>
                        </td>
                        <td class="text-center">${dataVo.seNm}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${dataVo.seCdId eq 'program'}">
                                    ${dataVo.schdulSeNm}
                                </c:when>
                                <c:when test="${dataVo.seCdId eq 'restde'}">
                                    ${dataVo.restdeSeNm}
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-left">
                            ${dataVo.bgngYmd} ~
                            <c:choose>
                                <c:when test="${dataVo.rpttYn eq 'Y' and empty dataVo.endYmd}">반복종료일</c:when>
                                <c:otherwise>${dataVo.endYmd}</c:otherwise>
                            </c:choose>

                            <div>
                                <c:choose>
                                    <c:when test="${dataVo.rpttYn eq 'Y'}">
                                        <c:if test="${not empty dataVo.rpttEndYmd}">
                                            <div class="text-info">반복종료일 : ${dataVo.rpttEndYmd}</div>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${dataVo.rpttTypeCd eq 'Y'}">
                                                <strong>매년</strong>
                                                <code>${fn:substring(dataVo.bgngYmd, 5, 7)}월 ${fn:substring(dataVo.bgngYmd, 8, -1)}일~${fn:substring(dataVo.endYmd, 5, 7)}월 ${fn:substring(dataVo.endYmd, 8, -1)}일</code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'M'}">
                                                <strong>매월</strong>
                                                <code>${fn:substring(dataVo.bgngYmd, 8, -1)}일~${fn:substring(dataVo.endYmd, 8, -1)}일</code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'W'}">
                                                <strong>매주</strong>
                                                <code>
                                                    <c:if test="${dataVo.sndayRsvtNo eq 0}">일 </c:if>
                                                    <c:if test="${dataVo.mndayRsvtNo eq 2}">월 </c:if>
                                                    <c:if test="${dataVo.tsdayRsvtNo eq 3}">화 </c:if>
                                                    <c:if test="${dataVo.wddayRsvtNo eq 4}">수 </c:if>
                                                    <c:if test="${dataVo.trdayRsvtNo eq 5}">목 </c:if>
                                                    <c:if test="${dataVo.frdayRsvtNo eq 6}">금 </c:if>
                                                    <c:if test="${dataVo.stdayRsvtNo eq 7}">토 </c:if>
                                                </code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'D'}">
                                                <strong>매일</strong>
                                            </c:when>
                                        </c:choose>
                                        반복
                                    </c:when>
                                    <c:otherwise>
                                        <!-- <strong>매일</strong> -->
                                    </c:otherwise>
                                </c:choose>
                                <!-- 반복 -->
                            </div>

                        </td>
                        <td class="text-center">
                            <c:if test="${not empty dataVo.bgngHr and not empty dataVo.endHr}">
                                ${dataVo.bgngHr} ~ ${dataVo.endHr}
                             </c:if>
                        </td>
                        <td class="text-center">${dataVo.rgtrNm}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="8" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-xs-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-success" id="registBtn">등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>