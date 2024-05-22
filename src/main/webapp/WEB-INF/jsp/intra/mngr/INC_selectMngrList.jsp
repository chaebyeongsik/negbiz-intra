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
<title>관리자 관리</title>

<op:jsTag type="intra" items="ui, opform" />
<op:jsTag type="libs" items="form" />


<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mngr/js/mngr.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("q_searchKey", "${paramMap.q_searchKey}");

        $(".updateForm").each(function () {
            var picId = $(this).attr("id");
            $(this).opmodal({
                width : 980,
                height : 500,
                href : "/intra/mngr/PD_updateMngrForm.do?q_picId=" + picId
            });
        });

        $("#dataForm").submit(function() {
            return opMngrSearch();
        });

        /* 담당자 접속이력 */
        $(".mngrConnectHist").opmodal({
            width : 900,
            height : 600
        });

        /* 담당자 변경이력 */
        $(".mngrChangeHist").opmodal({
            width : $(window).width() - 60,
            height : $(window).height() - 50
        });
    });

    /* 담당자 사용여부 수정 */
    var opUpdateUseYn = function (picId) {

        var elementz = $("#useYn_" + picId);
        var useYnVal = elementz.find("span").html() == "사용" ? "N" : "Y";

        if (confirm("사용여부를 변경하시겠습니까?")) {
            $("#dataForm").ajaxSubmit({
                url : "ND_updateUseYn.do",
                type : "POST",
                dataType : "json",
                data : {
                    picId : picId,
                    useYn : useYnVal
                },
                success : function(response) {
                    try {
                        if(eval(response.result)) {
                            opSuccessMsg(response.message);
                            elementz.find("i").removeClass();
                            elementz.find("div").removeClass();

                            if(useYnVal == "Y"){
                                elementz.attr("title", "클릭 시 미사용으로 변경");
                                elementz.find("div").addClass("text-info");
                                elementz.find("i").addClass("icon-checkmark");
                                elementz.find("span").text("사용").addClass("text-info");
                            }else{
                                elementz.attr("title", "클릭 시 사용으로 변경");
                                elementz.find("div").addClass("text-danger");
                                elementz.find("i").addClass("icon-close");
                                elementz.find("span").text("미사용").addClass("text-danger");
                            }
                        } else {
                            opErrorMsg(response.message);
                        }
                    } catch (e) {
                        opSysErrorMsg(response.message, e);
                        return;
                    }
                }
            })
        }
    };

    /* 담당자 정보 삭제 */
    var opDelete = function () {
        var picIds;
        var selectedPicId = opCheckedArray();
        if (selectedPicId.length == 0) {
            opWarningMsg("담당자를 선택해 주세요.");
            return;
        } else {
            picIds = selectedPicId.join(",");
        }

        if (confirm("정말 삭제 하시겠습니까?")) {
            $("#dataForm").ajaxSubmit({
                url : "ND_deleteListMngr.do",
                type : "POST",
                dataType : "json",
                data : {
                    picIds : picIds
                },
                success : function(response) {
                    try {
                        if(eval(response)) {
                            opSuccessMsg(response.message);
                            opMngrList();
                        } else {
                            opErrorMsg(response.message);
                        }
                    } catch (e) {
                        opSysErrorMsg(response.message, e);
                        return;
                    }
                }
            })
        }
    }

    /* 담당자 부서이동 */
    var opDeptTrnsfer = function (el) {
        var selectedPicId = opCheckedArray();
        if (selectedPicId.length == 0) {
            alert("담당자를 선택해 주세요.");
            return;
        }

        $(el).opmodal({
            width : 760,
            height : 600,
            href : "/intra/mngr/PD_updateDeptTrnsferForm.do?picIds=" + selectedPicId.join(",")
        });
    };

    /* 일괄권한지정 */
    var opAuthorAppn = function (el) {
        var selectedPicId = opCheckedArray();
        if (selectedPicId.length == 0) {
            alert("담당자를 선택해 주세요.");
            return;
        }

        $(el).opmodal({
            width : 760,
            height : 500,
            href : "/intra/mngr/PD_updateAuthorAsgnForm.do?picIds=" + selectedPicId.join(",")
        });
    };

    /* 담당자 권한수정 */
    var opAuthorAsgn = function (picId) {
        $().opmodal({
            width : 760,
            height : 500,
            href : "/intra/mngr/PD_updateAuthorAsgnForm.do?picId=" + picId
        });
    };

    /* 담당자 권한할당된 메뉴보기 */
    var opAuthorAsgnMenu = function(obj, picId) {
        $().opmodal({
            width : 760,
            height : 500,
            href : "/intra/mngr/PD_selectAuthorAsgnMenuList.do?picId=" + picId
        });
    }

    //]]>
</script>
<op:excelDown excelKey="mngr" searchAt="Y" btnId="excelDown" />
</head>
<body>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectMngrList.do" class="form-inline" onsubmit="return false;">
            <input type="hidden" id="q_deptCdId" name="q_deptCdId" value="${paramMap.q_deptCdId}" />
            <input type="hidden" name="q_regSn" id="q_regSn" value="" />
            <div class="block">
                <div class="form-group">
                    <label for="q_searchVal" class="sr-only">검색어</label>
                    <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="이름 또는 ID" />
                </div>
                <button type="button" class="btn btn-info" onclick="opMngrSearch();">검색</button>
                <button type="button" class="btn btn-info" onclick="opMngrSearchReset();">초기화</button>
            </div>
            <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 생성됨--%>
            <op:pagerParam title="${deptNm} 담당자 목록" ignores="" />
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="담당자 목록으로 순번,담당자ID,직급명,담당자명,권한그룹,접속이력,변경이력,사용여부 정보를 제공합니다.">
            <caption class="hidden">관리자 목록</caption>
            <colgroup>
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
                <col width="" />
            </colgroup>
            <thead>
                <tr>
                    <th><input type="checkbox" value="Y" name="chk-all" id="chk-all" /></th>
                    <th>순번</th>
                    <th>담당자ID</th>
                    <th>직급명</th>
                    <th>담당자명</th>
                    <th>권한그룹</th>
                    <th>접속이력</th>
                    <th>변경이력</th>
                    <th>사용여부</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center"><input type="checkbox" name="sns" value="${dataVo.picId}" /></td>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">
                            ${dataVo.picId}
                        </td>
                        <td class="text-center">
                            ${dataVo.clsfNm}
                        </td>
                        <td class="text-center">
                            <a href="#" id="${dataVo.picId}" class="updateForm">${dataVo.picNm}</a>
                        </td>
                        <td class="text-center">
                            <button type="button" class="btn btn-xs btn-info" onclick="opAuthorAsgnMenu(this, '${dataVo.picId}');">보기</button>
                            <button type="button" class="btn btn-xs btn-info" onclick="opAuthorAsgn('${dataVo.picId}');">수정</button>
                        </td>
                        <td class="text-center">
                            <a href="/intra/mngr/connect/PD_selectConnectList.do?q_picId=${dataVo.picId}" class="mngrConnectHist">${dataVo.lgnDt}</a>
                        </td>
                        <td class="text-center">
                            <a href="/intra/mngr/change/PD_selectChangeList.do?q_picId=${dataVo.picId}" class="mngrChangeHist">${dataVo.updtDt}</a>
                        </td>
                        <td class="text-center">
                            <a href="#${index-status.index}" id="useYn_${dataVo.picId}" onclick="opUpdateUseYn('${dataVo.picId}');">
                                <c:if test="${dataVo.useYn eq 'Y'}"><div class="text-info"><i class="icon-checkmark"></i> <span>사용</span></div></c:if>
                                <c:if test="${dataVo.useYn eq 'N'}"><div class="text-danger"><i class="icon-close"></i> <span>미사용</span></div></c:if>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="9" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 버튼 -->
    <div class="row">
        <div class="block clearfix">
            <div class="col-md-12 btn-group">
                <div class="pull-left">
                    <button type="button" class="btn btn-info" onclick="opDeptTrnsfer();">부서이동</button>
                    <button type="button" class="btn btn-info" onclick="opAuthorAppn();">일괄권한지정</button>
                    <button type="button" class="btn btn-info" id="excelDown">엑셀출력</button>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-danger" onclick="opDelete();">삭제</button>
                </div>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
    <!-- 페이징 -->
    <op:pager pager="${pager}" script="opMngrSearch" />
    <!-- //페이징 -->
</body>
</html>