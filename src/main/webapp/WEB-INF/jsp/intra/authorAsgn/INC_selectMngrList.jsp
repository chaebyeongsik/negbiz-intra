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
<title>담당자 목록</title>

<op:jsTag type="intra" items="ui, opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("q_searchKey", "${paramMap.q_searchKey}");

        /**
         * 대상 체크박스 전체 선택 및 색상변경
         */
        $("input[name=ids-chk]").click(function() {
            var isChecked = this.checked;

            $("input[name=ids]").prop("checked", isChecked);
            if(isChecked) {
                $(this).parent().parent().parent().parent().find("tbody > tr").addClass("info");
            } else {
                $(this).parent().parent().parent().parent().find("tbody > tr").removeClass("info");
            }
        });

        /**
         * 대상 체크박스 선택에 따른 색상변경
         */
        $("input[name=ids]").click(function() {

            if($(this).is(":checked")) {
                $(this).parent().parent().addClass("info");
            } else {
                $(this).parent().parent().removeClass("info");
            }
        })

    });

    var opAddMngr = function () {
        var selectedPicId = new Array;
        $("input[name=ids]:checked").each(function(i) {
            selectedPicId[i] = $(this).val();
        });

        if (selectedPicId.length == 0) {
            opWarningMsg("담당자를 선택해 주세요.");
            return;
        }

        $.ajax({
            url : "<c:url value="/intra/authorAsgn/ND_selectAuthorMngrList.do" />",
            type : "POST",
            data : { picIds : selectedPicId.join(",") },
            dataType : "json",
            success : function(response) {
                if (response.result) {
                    parent.opAddMngrList(response.value);
                } else {
                    return;
                }
            }
        });
    }


    //]]>
</script>
</head>
<body>
    <div class="block text-center">
        <form name="mngrForm" id="mngrForm" method="get" action="INC_selectMngrList.do" class="form-inline">
            <input type="hidden" id="q_deptCdId" name="q_deptCdId" value="${paramMap.q_deptCdId}" />
            <fieldset>
                <legend class="hidden">목록검색조건</legend>
                <input type="hidden" name="q_regSn" id="q_regSn" value="" />
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchKey" class="sr-only">항목</label>
                        <select name="q_searchKey" id="q_searchKey" class="select">
                            <option value="">항목선택</option>
                            <option value="1001">ID</option>
                            <option value="1002">담당자명</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchVal" class="sr-only">검색어</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <button type="button" class="btn btn-info" onclick="opMngrSearch();">검색</button>
                    <button type="button" class="btn btn-info" onclick="opMngrSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
                <op:pagerParam title="${deptNm} 담당자 목록" ignores="" page="param/listPagerParam2.jsp" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="담당자 목록으로 ID,부서명,직급,담당자명 정보를 제공합니다.">
            <caption class="hidden">담당자 목록</caption>
            <colgroup>
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th><input type="checkbox" value="Y" name="ids-chk" id="ids-chk" /></th>
                    <th>ID</th>
                    <th>부서명</th>
                    <th>직급</th>
                    <th>담당자명</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center"><input type="checkbox" name="ids" value="${dataVo.picId}" /></td>
                        <td class="text-center">${dataVo.picId}</td>
                        <td class="text-center">${dataVo.deptNm}</td>
                        <td class="text-center">${dataVo.clsfNm}</td>
                        <td class="text-center">${dataVo.picNm}</td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="5" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 버튼 -->
    <div class="row">
        <div class="block clearfix">
            <div class="col-md-12 btn-group">
                <div class="pull-left">
                    <button type="button" class="btn btn-info" onclick="opToggle();">권한담당자 목록 보기</button>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-info" onclick="opAddMngr();">권한담당자 목록에 추가</button>
                </div>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" script="opMngrSearch" />
    <!-- //페이징 -->
    <hr>
</body>
</html>