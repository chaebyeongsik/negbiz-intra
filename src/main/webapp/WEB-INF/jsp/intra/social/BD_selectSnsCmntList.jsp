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
<title>SNS커멘트 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/snsCmnt/js/snsCmnt.js"></script>
    -->

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#checkAll").click(function() {
            var checked = $(this).prop("checked");
            if(checked) {
                $("#listForm input[name=snsSns]").prop("checked", true);
            } else {
                $("#listForm input[name=snsSns]").prop("checked", false);
            }
        });

        $("#listForm").submit(function() {
            var checkedCnt = 0;
            $("#listForm input[name=snsSns]:checked").each(function(index) {
                checkedCnt++;
            });
            if(checkedCnt < 1) {
                opWarningMsg("삭제할 목록을 선택하세요.");
                return false;
            }

            return true;
        });

        opSelected("q_siteSn", "${paramMap.q_siteSn}", "");
        opSelected("q_picDelYn", "${paramMap.q_picDelYn}", "");
        opSelected("q_sortTar", "${paramMap.q_sortTar}", "");
    });

    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectSnsCmntList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="row block">
                    <div class="form-group">
                        <label for="q_siteSn" class="control-label">사이트</label>
                        <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}">${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_userMenuNm" class="">메뉴명</label>
                        <input type="text" name="q_userMenuNm" id="q_userMenuNm" value="${paramMap.q_userMenuNm}" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="q_msgCn" class="">내용</label>
                        <input type="text" name="q_msgCn" id="q_msgCn" value="${paramMap.q_msgCn}" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>

                <div class="row block">
                    <div class="form-group">
                        <label for="q_picDelYn" class="control-label">관리자삭제여부</label>
                        <select name="q_picDelYn" id="q_picDelYn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <option value="N">정상</option>
                            <option value="Y">삭제</option>
                        </select>
                    </div>
                    <label for="" class="control-label">정렬대상 : </label>
                    <select name="q_sortTar" id="q_sortTar" class="select" style="width: 150px;">
                        <option value=""><op:message cdId="common.selectOption" /></option>
                        <option value="1010">신고횟수</option>
                        <option value="1020">추천횟수</option>
                        <option value="1030">비추천횟수</option>
                    </select>
                </div>

                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="SNS커멘트 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <form id="listForm" name="listForm" method="post" action="ND_deleteSnsCmnt.do">
        <div class="block table-responsive">
            <!-- 리스트 -->
            <table class="table table-bordered table-striped table-hover op-table-list" summary="SNS커멘트  목록으로 순번,도메인명,사용자메뉴명,내용,닉네임,프로필,등록일시,삭제일시,담당자삭제일시 정보를 제공합니다.">
                <caption class="hidden">SNS커멘트 목록</caption>
                <colgroup>
                    <col width="5%" />
                    <col width="5%" />
                    <col width="8%" />
                    <col width="" />
                    <col width="140px" />
                    <col width="100px" />
                </colgroup>
                <thead>
                    <tr>
                        <th><input id="checkAll" name="checkAll" type="checkbox" value="" /></th>
                        <th>순번</th>
                        <th>프로필</th>
                        <th>내용</th>
                        <th>등록일시<br />삭제일시
                        </th>
                        <th>담당자<br />삭제일시
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="${pager.indexNo}" />
                    <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                        <tr>
                            <td class="text-center">
                                <c:if test="${dataVo.picDelYn ne 'Y'}">
                                    <input type="checkbox" id="snsSns${dataVo.snsSn}" name="snsSns" value="${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" />
                                </c:if>
                            </td>
                            <td class="text-center">${index-status.index}</td>
                            <td class="text-center">
                                <c:if test="${not empty dataVo.profilAdres}">
                                    <a href="${dataVo.profilAdres}" target="_blank" title="새창으로 ${dataVo.ncnm}님의 SNS로 이동합니다. ">
                                </c:if>
                                <c:choose>
                                    <c:when test="${not empty dataVo.profilImageUrl}">
                                        <img src="${dataVo.profilImageUrl}" alt="${dataVo.ncnm} 사진" />
                                    </c:when>
                                    <c:otherwise>${dataVo.ncnm}</c:otherwise>
                                </c:choose>
                                <c:if test="${not empty dataVo.profilAdres}">
                                    </a>
                                </c:if>
                            </td>
                            <c:set var="paddingLeft" value="${dataVo.opnnGrdSn * 40 + 10}" />
                            <td style="padding-left: ${paddingLeft}px;">
                                <div class="well">
                                    <p><op:nrToBr content="${dataVo.msgCn}" /></p>
                                    <c:choose>
                                        <c:when test="${dataVo.delYn eq 'Y'}">
                                            <span class="text-danger">※ 사용자가 삭제한 댓글입니다.</span>
                                            <br />
                                        </c:when>
                                        <c:when test="${dataVo.picDelYn eq 'Y'}">
                                            <span class="text-danger">※ 관리자가 삭제 처리한 댓글입니다.</span>
                                            <br />
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${not empty dataVo.svcId}">
                                    SNS <span class="text-success">${dataVo.svcId}</span>
                                        <br />
                                    </c:if>
                                    <span class="">${dataVo.siteExpln}</span>
                                    <a href="${dataVo.pgeUrl}" target="_blank">${dataVo.userMenuNm}</a>
                                </div>
                            </td>
                            <td>
                                등록 :
                                <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                                <br /> 삭제 :
                                <c:if test="${empty dataVo.delDt}">
                                    <fmt:formatDate value="${dataVo.delDt}" pattern="yyyy-MM-dd" />
                                </c:if>
                            </td>
                            <td class="text-center">
                                <fmt:formatDate value="${dataVo.picDelDt}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${pager}" colspan="7" />
                </tbody>
            </table>
            <!-- //리스트 -->
        </div>
        <!-- 버튼 -->
        <op:auth needAuthType="MANAGER">
            <div class="row">
                <div class="col-sm-12 btn-group">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-danger">선택삭제</button>
                    </div>
                </div>
            </div>
        </op:auth>
    </form>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>