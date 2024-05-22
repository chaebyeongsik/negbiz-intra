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
<title>사용자메뉴컨텐츠 비교</title>

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.common.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 비교
        $("#dataForm").submit(function() {
            var leftSn = $("#q_leftCntntsSn").val();
            var rightSn = $("#q_rightCntntsSn").val();

            if (leftSn == rightSn) {
                opWarningMsg("동일버전은 비교할 수 없습니다.");
                return false;
            }
            if (leftSn > rightSn) {
                opWarningMsg("좌측버전이 더 낮아야 합니다.");
                return false;
            }
        });

        // 승인
        $("#confmForm").submit(function() {
            var aprvRsn = $("#aprvRsn").val();
            if (aprvRsn == "") {
                opWarningMsg("승인내용을 입력해야합니다.");
                return false;
            }
        });
    });

    // 반려
    var returnCmsCntnts = function() {
        $("#aprvSttsNo").val("C1030");
        $("#confmForm").submit();
    }
    //]]>
</script>
</head>
<body>

    <div id="opTabs" class="tabbable block">
        <ul class="nav nav-tabs">
            <li class="active"><a
                href="PD_selectCmsManageSource.do?q_siteSn=${baseVo.siteSn}&amp;q_userMenuEngNm=${baseVo.userMenuEngNm}&amp;q_leftCntntsSn=${baseVo.leftCntntsSn}&amp;q_rightCntntsSn=${baseVo.rightCntntsSn}"
            >소스비교</a></li>
            <li><a
                href="PD_selectCmsManageScreen.do?q_siteSn=${baseVo.siteSn}&amp;q_userMenuEngNm=${baseVo.userMenuEngNm}&amp;q_leftCntntsSn=${baseVo.leftCntntsSn}&amp;q_rightCntntsSn=${baseVo.rightCntntsSn}"
            >화면비교</a></li>
        </ul>
    </div>

    <div>
        <form id="dataForm" name="dataForm" method="get" action="PD_selectCmsManageSource.do">
            <input type="hidden" id="q_siteSn" name="q_siteSn" value="${baseVo.siteSn}" />
            <input type="hidden" id="q_userMenuEngNm" name="q_userMenuEngNm" value="${baseVo.userMenuEngNm}" />

            <div class="form-group">
                <label for="q_leftCntntsSn" class="controll-label">좌측 버전</label>
                <select id="q_leftCntntsSn" name="q_leftCntntsSn" class="select" style="width: 80px">
                    <c:forEach items="${baseVo.contsSns}" var="sntntsSn">
                        <option value="${sntntsSn}" <c:if test="${sntntsSn eq baseVo.leftCntntsSn}">selected="selected"</c:if>>#${sntntsSn}</option>
                    </c:forEach>
                </select>

                <label for="q_rightCntntsSn" class="controll-label">우측 버전</label>
                <select id="q_rightCntntsSn" name="q_rightCntntsSn" class="select" style="width: 80px">
                    <c:forEach items="${baseVo.contsSns}" var="sntntsSn">
                        <option value="${sntntsSn}" <c:if test="${sntntsSn eq baseVo.rightCntntsSn}">selected="selected"</c:if>>#${sntntsSn}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-info">비교</button>
            </div>
        </form>
    </div>

    <form id="confmForm" name="confmForm" method="post" action="ND_updateCmsManage.do">
        <input type="hidden" name="siteSn" id="siteSn" value="${baseVo.siteSn}" />
        <input type="hidden" name="userMenuEngNm" id="userMenuEngNm" value="${baseVo.userMenuEngNm}" />
        <input type="hidden" id="contsSn" name="contsSn" value="${baseVo.rightCntntsSn}" />

        <input type="hidden" id="q_siteSn" name="q_siteSn" value="${baseVo.siteSn}" />
        <input type="hidden" id="q_userMenuEngNm" name="q_userMenuEngNm" value="${baseVo.userMenuEngNm}" />
        <input type="hidden" id="q_contsSn" name="q_contsSn" value="${baseVo.rightCntntsSn}" />

        <input type="hidden" id="aprvSttsNo" name="aprvSttsNo" value="C1040" />

        <div class="block table-responsive">
            <!-- 리스트 -->
            <table class="table table-bordered op-table-list" summary="소스비교 목록으로 결과, 좌측버전, 우측버전 정보를 제공합니다.">
                <caption class="hidden">소스비교 목록</caption>
                <colgroup>
                    <col width="8%" />
                    <col width="46%" />
                    <col width="46%" />
                </colgroup>
                <thead>
                    <tr>
                        <th>결과</th>
                        <th>#${baseVo.leftCntntsSn} 버전</th>
                        <th>#${baseVo.rightCntntsSn} 버전</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dataList}" var="compareVo">
                        <tr>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${compareVo.tag eq 'add'}">
                                        <span class="label label-success">${compareVo.tagName}</span>
                                    </c:when>
                                    <c:when test="${compareVo.tag eq 'modify'}">
                                        <span class="label label-info">${compareVo.tagName}</span>
                                    </c:when>
                                    <c:when test="${compareVo.tag eq 'delete'}">
                                        <span class="label label-danger">${compareVo.tagName}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-default">${compareVo.tagName}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td style="table-layout:fixed; word-break:break-all;">${compareVo.leftLine}</td>
                            <td style="table-layout:fixed; word-break:break-all;">${compareVo.rightLine}</td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${dataList}" colspan="3" msg="비교 내용이 없습니다." />
                </tbody>
            </table>
        </div>

        <div class="block table-responsive">
            <!-- 리스트 -->
            <table class="table table-bordered op-table-list" summary="소스비교 목록으로 결과, 좌측버전, 우측버전 정보를 제공합니다.">
                <caption class="hidden">소스비교 목록</caption>
                <colgroup>
                    <col width="8%" />
                    <col width="46%" />
                    <col width="46%" />
                </colgroup>
                <thead>
                    <tr>
                        <th>항목</th>
                        <th>#${baseVo.leftCntntsSn} 버전 내역</th>
                        <th>#${baseVo.rightCntntsSn} 버전 내역</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th class="text-center">요청사유</th>
                        <td>
                            <op:nrToBr content="${baseVo.leftCntnts.pblcnRsn}" />
                        </td>
                        <td>
                            <op:nrToBr content="${baseVo.rightCntnts.pblcnRsn}" />
                        </td>
                    </tr>
                    <tr>
                        <th class="text-center">요청자</th>
                        <td>${baseVo.leftCntnts.updusrNm}
                            <fmt:formatDate value="${baseVo.leftCntnts.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>${baseVo.rightCntnts.updusrNm}
                            <fmt:formatDate value="${baseVo.rightCntnts.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                    </tr>
                    <tr>
                        <th class="text-center">승인/반려내용</th>
                        <td>
                            <op:nrToBr content="${baseVo.leftCntnts.aprvRsn}" />
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${baseVo.rightCntnts.aprvSttsNo eq 'C1020'}">
                                    <textarea name="aprvRsn" id="aprvRsn" rows="3" cols="80" class="form-control" placeholder="승인/반려내용"></textarea>
                                </c:when>
                                <c:otherwise>
                                    <op:nrToBr content="${baseVo.rightCntnts.aprvRsn}" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th class="text-center">승인자</th>
                        <td>
                            <c:choose>
                                <c:when test="${baseVo.leftCntnts.aprvSttsNo eq 'C1050'}">
                                    <span class="text-warning">자동승인</span>
                                </c:when>
                                <c:otherwise>
                                    ${baseVo.leftCntnts.confmerNm}
                                </c:otherwise>
                            </c:choose>
                            <fmt:formatDate value="${baseVo.leftCntnts.aprvDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${baseVo.rightCntnts.aprvSttsNo eq 'C1050'}">
                                    <span class="text-warning">자동승인</span>
                                </c:when>
                                <c:otherwise>
                                    ${baseVo.rightCntnts.confmerNm}
                                </c:otherwise>
                            </c:choose>
                            <fmt:formatDate value="${baseVo.rightCntnts.aprvDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <c:choose>
            <c:when test="${baseVo.rightCntnts.aprvSttsNo eq 'C1020'}">
                <div class="clearfix">
                    <strong class="text-danger pull-right">승인시 컨텐츠가 운영시스템에 반영됨을 주의하시기 바랍니다.</strong>
                </div>
                <!-- 버튼 -->
                <div class="row">
                    <div class="col-sm-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-info">승인</button>
                            <button type="button" class="btn btn-info" onclick="returnCmsCntnts();">반려</button>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </form>
    <!-- //버튼 -->
</body>
</html>