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
<title>사용자메뉴레이아웃이력 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(confirm("복원을 하시면 운영중인 시스템의 레이아웃이 변경됩니다. 실행하시겠습니까?")) {
                if(validate()) {
                    $(this).ajaxSubmit({
                        url : "ND_updateCmsLayout.do",
                        type : "POST",
                        dataType : "json",
                        success : function(response) {
                            opJsonMsg(response);
                        },
                        error : function(response) {
                            opSysErrorMsg(response.responseText);
                            return;
                        }
                    });
                }
            }
            return false;
        });
    });

    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateCmsLayout.do">
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="lytCdNo" id="lytCdNo" value="${dataVo.lytCdNo}" />
        <input type="hidden" name="lytNm" id="lytNm" value="${dataVo.lytNm}" />
        <input type="hidden" name="siteSn" id="siteSn" value="${dataVo.siteSn}" />
        <div class="row">
            <!-- 내용보기 -->
            <div class="col-sm-12 block table-responsive">
                <table class="table table-bordered" summary="사용자메뉴레이아웃이력 정보입니다.">
                    <caption class="hidden">사용자메뉴레이아웃이력 상세보기</caption>
                    <colgroup>
                        <col width="15%" />
                        <col />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>레이아웃코드</th>
                            <td>
                                ${dataVo.lytNm}
                                <code>${dataVo.lytCdNo}</code>
                            </td>
                        </tr>
                        <tr>
                            <th>도메인명</th>
                            <td>${dataVo.siteExpln}&nbsp;&nbsp;&nbsp;
                                <code>${dataVo.siteNm}</code>
                            </td>
                        </tr>
                        <tr>
                            <th>로그유형</th>
                            <td>
                                <c:choose>
                                    <c:when test="${dataVo.logType eq 'I'}">등록</c:when>
                                    <c:when test="${dataVo.logType eq 'U'}">수정</c:when>
                                    <c:when test="${dataVo.logType eq 'D'}">삭제</c:when>
                                </c:choose>
                                &nbsp;&nbsp; &nbsp;
                                <fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>레이아웃컨텐츠</th>
                            <td>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <textarea name="lytContsCn" id="lytContsCn" rows="30" cols="80" class="form-control" placeholder="레이아웃컨텐츠"><c:out value="${dataVo.lytContsCn}" /></textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="lytContsCn" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>파일경로</th>
                            <td>${dataVo.filePathNm}</td>
                        </tr>
                        <tr>
                            <th>수정정보</th>
                            <td>
                                <strong>${dataVo.updusrNm} (${dataVo.mdfrId}) <fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">복원</button>
                    <%--                     <button type="button" class="btn btn-primary" onclick="layoutPreview('<c:url value="/intra/cms/cntnts/PV_LayoutContentPreView.do" />');">미리보기</button> --%>
                    <button type="button" class="btn btn-primary" onclick="opList('PD_selectCmsLayoutHistoryList.do');">목록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>