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
<title>썸네일 생성 결과 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />

<script type="text/javascript">
    //<![CDATA[

    var isUpdate = false;
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            var target = $("#q_pagingStart");
            if(!target.requiredValidator() || !target.digitsValidator()) {
                opWarningMsg("시작번호를 숫자로 입력하세요.");
                target.focus();

                return false;
            }
            var startNum = Number(target.val());
            var target = $("#q_pagingEnd");
            if(!target.requiredValidator() || !target.digitsValidator()) {
                opWarningMsg("종료번호를 숫자로 입력하세요.");
                target.focus();
                return false;
            }
            var endNum = Number(target.val());
            if(startNum > endNum || (startNum + 100) < endNum) {
                return confirm("100개 이상 파일의 썸네일 생성을 선택하였습니다.\n첨부된 원본 이미지 파일의 크기에 따라 시스템에\n심대한 영향을 줄 수 있습니다.\n\n정말로 진행하시겠습니까?");
            }

            opLoadStart();

            return true;
        });

        opChecked("q_createType", "${paramMap.q_createType}", "new");
    });

    //]]>
</script>
</head>
<body>

    <div class="row block">
        <div class="col-md-12">
            <blockquote>
                <p class="text-danger">
                    ※ 시작번호와 종료번호는 최대한 100개 이내에서 생성하는 것을 권한합니다. 파일은 메모리를 많이 사용하기 때문에 시스템에 심대한 영향을 줄 수 있습니다.
                </p>
                <p class="text-danger">
                    ※ 썸네일 생성이 필요한 경우 안전하게 개발서버에서 생성한 후에 운영 시스템으로 파일을 복사하여 이관을 하시기 바랍니다.
                </p>
            </blockquote>
        </div>
    </div>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="PD_createThumbNail.do" class="form-inline">
            <input type="hidden" name="q_bbsSn" value="${paramMap.q_bbsSn}" />
            <input type="hidden" name="bbsSn" value="${paramMap.q_bbsSn}" />
            <fieldset>
                <legend class="sr-only">썸네일생성조건 선택</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_createTypeNew" class="radio-inline radio-primary">
                            <input type="radio" name="q_createType" id="q_createTypeNew" value="new" class="styled" />
                            썸네일이 없는 이미지 파일만 생성
                        </label>
                        <label for="q_createTypeAll" class="radio-inline radio-primary">
                            <input type="radio" name="q_createType" id="q_createTypeAll" value="all" class="styled" />
                            모든 이미지 파일의 썸네일 생성
                        </label>
                    </div>
                </div>
                <div class="block">
                    <div class="form-group">
                        <label for="q_pagingStart" class=""> 시작번호</label>
                        <input type="text" name="q_pagingStart" id="q_pagingStart" value="<c:out value="${paramMap.q_pagingStart}" default="1" />" class="form-control" style="width: 80px;" />
                    </div>
                    <div class="form-group">
                        <label for="q_pagingEnd" class="">종료번호</label>
                        <input type="text" name="q_pagingEnd" id="q_pagingEnd" value="<c:out value="${paramMap.q_pagingEnd}" default="50" />" class="form-control" style="width: 80px;" />
                    </div>
                    <div class="form-group">
                        <label for="q_bbsDocNo" class="">게시물일련번호</label>
                        <input type="text" name="q_bbsDocNo" id="q_bbsDocNo" value="${paramMap.q_bbsDocNo}" class="form-control" style="width: 180px;" />
                    </div>
                    <button type="submit" class="btn btn-info">썸네일생성</button>
                </div>
            </fieldset>
        </form>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">썸네일 생성 결과(오류만 표시)</h6>
        </div>
        <div class="table-responsive">
            <!-- 리스트 -->
            <table class="table table-bordered op-table-list">
                <caption class="hidden">썸네일 생성 오류 목록</caption>
                <col width="15%" />
                <col width="" />
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>오류 내역</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="${fn:length(dataList)}" />
                    <c:forEach items="${dataList}" var="errorMsg" varStatus="status">
                        <tr>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${status.count > 1}">
                                                ${index-status.count}
                                            </c:when>
                                    <c:otherwise>
                                                결과
                                            </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${errorMsg}</td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${dataList}" colspan="2" msg="썸네일생성 옵션을 선택한 후 생성버튼을 클릭하세요." />
                </tbody>
            </table>
            <!-- //리스트 -->
        </div>
    </div>

</body>
</html>