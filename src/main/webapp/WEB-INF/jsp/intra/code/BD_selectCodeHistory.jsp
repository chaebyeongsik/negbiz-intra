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
<title>코드이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/codeHistory/js/codeHistory.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    //]]>
</script>
</head>
<body>
    <form name="dataForm" id="dataForm" method="post" action="BD_selectCodeHistoryList.do">
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />
    </form>
    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="코드이력 정보입니다.">
            <caption class="hidden">코드이력 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <tbody>
                <tr>
                    <th>로그일시</th>
                    <td>${dataVo.logDt}</td>
                </tr>
                <tr>
                    <th>로그유형</th>
                    <td>${dataVo.logType}</td>
                </tr>
                <tr>
                    <th>코드</th>
                    <td>${dataVo.cdId}</td>
                </tr>
                <tr>
                    <th>코드명</th>
                    <td>${dataVo.cdNm}</td>
                </tr>
                <tr>
                    <th>상위코드</th>
                    <td>${dataVo.upCdId}</td>
                </tr>
                <tr>
                    <th>상위코드명</th>
                    <td>${dataVo.upCdIdNm}</td>
                </tr>
                <tr>
                    <th>다국어코드</th>
                    <td>${dataVo.multih}</td>
                </tr>
                <tr>
                    <th>다국어코드명</th>
                    <td>${dataVo.multiLangCodeNm}</td>
                </tr>
                <tr>
                    <th>코드설명</th>
                    <td>${dataVo.cdExpln}</td>
                </tr>
                <tr>
                    <th>사용여부</th>
                    <td>${dataVo.useYn}</td>
                </tr>
                <tr>
                    <th>수정자ID</th>
                    <td>${dataVo.mdfrId}</td>
                </tr>
                <tr>
                    <th>수정자명</th>
                    <td>${dataVo.updusrNm}</td>
                </tr>
                <tr>
                    <th>수정일시</th>
                    <td>${dataVo.updtDt}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-primary" onclick="opList('BD_selectCodeHistoryList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>