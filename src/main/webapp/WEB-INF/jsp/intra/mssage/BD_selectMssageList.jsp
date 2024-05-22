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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>메시지 관리</title>

<op:jsTag type="intra" items="ui, opform" />


<!-- 기능별 스크립트 삽입 부 -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mssage/js/mssage.js"></script> --%>

<script type="text/javascript">
    //<![CDATA[

   var flag = '${flag}';

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            if ( $("#q_searchKey").val() != "" && $("#q_searchVal").val() == "" ) {
                opWarningMsg("검색어를 입력하세요.");
                $("#q_searchVal").focus();
                $("#q_currPage").val("1");
                return false;
            }
            return true;
        });
    });

    var opCheckedArray = function() {
        var selectedNotes = new Array();

        $("tbody .checkbox:checked").each(function(i) {
            selectedNotes[i] = $(this).val();
        });
        return selectedNotes;
    };

    var opDeleteSelection = function(){

        var selectedNotes = opCheckedArray();
        $("#arraySn").val(selectedNotes);
        if (selectedNotes.length == 0) {
            opWarningMsg("삭제할 메시지를 1개 이상 선택하세요.");
            return false;
        } else {
            if (confirm("선택한 " + selectedNotes.length + "개의 메시지를 삭제 하시겠습니까?")) {
                $.ajax({
                    type: "POST",
                    url: "ND_updateMssageDeleateAtList.do",
                    data: {
                        arraySn : $("#arraySn").val(),
                        flag    : flag
                    },
                    success: function(response){
                        if(response.result){
                            opSuccessMsg(response.value + "개의 메시지가 삭제 되었습니다.");
                            $('#dataForm').submit();
                        } else {
                            opErrorMsg(response.message);
                        }
                    },
                    error :  function(response) {
                        opSysErrorMsg(response.responseText);
                        return false;
                    }
                });
            }
        }
    };

    var opDelete = function(regSn){
        var message = "";
        if (flag == "R") {
            message = "받은 메시지를 삭제 하시겠습니까?";
        }
        if (flag == "T") {
            message = "보낸 메시지를 삭제 하시겠습니까?\n\n수신자의 메시지는 삭제되지 않습니다.";
        }
        if (confirm(message)) {
            $.ajax({
                type: "POST",
                url: "ND_updateMssageDeleteAt.do",
                data: {
                    regSn : regSn,
                    flag : flag
                },
                success: function(response){
                    if(response.result){
                        opJsonMsg(response);
                        $('#dataForm').submit();
                    } else {
                        opErrorMsg(response.message);
                    }
                },
                error :  function(response) {
                    opSysErrorMsg(response.responseText);
                    return false;
                }
            });
        }
    };

    var opView = function(regSn) {
        if ( $("#cnTrTag" + regSn).is(":hidden") ) {
            // 처음 메시지를 읽었을 때 수신일 업데이트
            if ( flag == 'R' && $("#rcptnDt" + regSn).val() == "" ) {
                $.ajax({
                    type: "POST",
                    url: "ND_updateRcptnDt.do",
                    data: {
                        regSn : regSn,
                        flag : flag
                    },
                    success: function(response){
                        if(response.result){
                            $("#cnTrTag" + regSn).show();
                            $("#cnDivTag" + regSn).show();
                            $("#mssageImg" + regSn).html('<img alt="읽음" src="/resources/intra/mssage/images/read_message.gif" />');
                        } else {
                            //opErrorMsg(response.message);
                        }
                    }
                });
            } else {
                $("#cnTrTag" + regSn).show(500);
                $("#cnDivTag" + regSn).show();
            }
        } else {
            $("#cnTrTag" + regSn).hide();
            $("#cnDivTag" + regSn).hide();
        }
    };

    /**
     * 페이지 이동
     * 
     * @param cpage 이동할 페이지 번호
     * @returns
     */
    var opMovePage = function(cpage, form) {
        if(!form) form = OpConfig.defaultForm;

        $("#q_currPage").val(cpage);
        location.href = "?flag=" + flag + "&" + opSearchQueryString(form);
    };
    //]]>
</script>
</head>
<body>
    <div id="tabs" class="tabbable page-tabs">
        <ul class="nav nav-tabs">
        <c:choose>
            <c:when test="${flag eq 'R'}">
                <c:set var="r_classActive" value="active" />
                <c:set var="targetUser" value="보낸이" />
            </c:when>
            <c:otherwise>
                <c:set var="t_classActive" value="active" />
                <c:set var="targetUser" value="받는이" />
            </c:otherwise>
        </c:choose>
            <li class="${r_classActive}"><a href="BD_selectMssageList.do?flag=R">받은 메시지함</a></li>
            <li class="${t_classActive}"><a href="BD_selectMssageList.do?flag=T">보낸 메시지함</a></li>
            <li><a href="BD_insertMssageForm.do">메시지 보내기</a></li>
        </ul>
        <div class="tab-content">
            <div class="block text-center">
                <form name="dataForm" id="dataForm" method="get" action="BD_selectMssageList.do" class="form-inline">
                    <input name="arraySn" type="hidden" id="arraySn" />
                    <fieldset>
                        <legend class="sr-only">목록검색조건</legend>
                        <div class="block">
                            <%-- 
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <label for="q_searchKey" class="sr-only">항목</label>
                                    <select name="q_searchKey" id="q_searchKey" class="select">
                                        <option value="">전체</option>
                                        <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>메시지함 내용</option>
                                        <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected="selected"</c:if>>${targetUser}</option>
                                    </select>
                                </div>
                                <div class="col-sm-7">
                                    <label for="q_searchVal" class="sr-only">검색어</label>
                                    <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                                </div>
                            </div>
                             --%>
                            
                            <div class="form-group">
                                <label for="q_searchKey" class="sr-only">항목</label>
                                <select name="q_searchKey" id="q_searchKey" class="select" style="width: 120px;">
                                    <option value="">전체</option>
                                    <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>메시지내용</option>
                                    <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected="selected"</c:if>>${targetUser}</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="q_searchVal" class="sr-only">검색어</label>
                                <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                            </div>
                            
                            <div class="btn-group">
                                <input type="hidden" name="flag" value="${flag}" />
                                <button type="submit" class="btn btn-info">검색</button>
                                <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                            </div>
                        </div>
                        <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                        <op:pagerParam title="메시지 목록" ignores="" />
                    </fieldset>
                </form>
            </div>
            <div class="block table-responsive">
                <!-- 리스트 -->
                <table class="table table-bordered table-striped table-hover op-table-list" summary="메시지 목록으로 순번,내용,등록일시 정보를 제공합니다.">
                    <caption class="hidden">메시지 목록</caption>
                    <colgroup>
                        <col width="20" /><!-- 순번 -->
                        <col width="" /><!-- 내용 -->
                        <col width="150" /><!-- ${targetUser} -->
                        <c:if test="${flag eq 'T'}">
                            <col width="200" /><!-- 수신일시 -->
                        </c:if>
                        <col width="200" /><!-- 보낸일시 -->
                    </colgroup>
                    <thead>
                        <tr>
                            <th><input type="checkbox" name="chk-all" id="chk-all" class="checkbox" /></th>
                            <th>내용</th>
                            <th>${targetUser}</th>
                            <c:if test="${flag eq 'T'}">
                                <th>수신일시</th>
                            </c:if>
                            <th>보낸일시</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="index" value="${pager.indexNo}" />
                        <c:set value="4" var="colspanNum" />
                        <c:if test="${flag eq 'T'}">
                            <c:set value="5" var="colspanNum" />
                        </c:if>
                        <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                            <tr>
                                <td class="text-center">
                                    <input type="checkbox" name="regSns" value="${dataVo.regSn}" class="checkbox" />
                                </td>
                                <td>
                                    <a href="#" id="summary${dataVo.regSn}" onclick="opView('${dataVo.regSn}'); return false;">
                                        <c:choose>
                                            <c:when test="${not empty dataVo.rcptnDt}">
                                                <img alt="읽음" src="/resources/intra/mssage/images/read_message.gif" />
                                            </c:when>
                                            <c:otherwise>
                                                <span id="mssageImg${dataVo.regSn}"> <img alt="새 쪽지" src="/resources/intra/mssage/images/new_message.gif" /> </span>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fn:length(dataVo.msgCn) > 30}">
                                                ${fn:substring(dataVo.msgCn, 0, 30)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${dataVo.msgCn}
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                    <c:if test="${not empty dataVo.fileList}">
                                        <a href="/component/file/zipdownload.do?q_fileSn=${dataVo.fileSn}" title="첨부파일이 ${fn:length(dataVo.fileList)}개 존재합니다."><span class="label label-default">${fn:length(dataVo.fileList)}</span></a>
                                    </c:if>
                                </td>
                                <c:if test="${flag eq 'R'}">
                                    <td class="text-center">
                                        <c:out value="${dataVo.trnsmiterNm}" />
                                        <input type="hidden" value="${dataVo.rcptnDt}" name="rcptnDt" id="rcptnDt${dataVo.regSn}" />
                                    </td>
                                </c:if>
                                <c:if test="${flag eq 'T'}">
                                    <td class="text-center"><c:out value="${dataVo.picNm}" /></td>
                                    <td class="text-center">
                                        <fmt:formatDate value="${dataVo.rcptnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                                    </td>
                                </c:if>
                                <td class="text-center">
                                    <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                            </tr>
        
                            <tr id="cnTrTag${dataVo.regSn}" style="display:none;">
                                <td id="cnTdTag${dataVo.regSn}" colspan="${colspanNum}" style="padding-left : 50px;">
                                    <div id="cnDivTag${dataVo.regSn}">
                                        <div class="col-md-10">
                                            <op:nrToBr content="${dataVo.msgCn}"/>
                                        </div>
                                        <div class="col-md-2 text-right">
                                            <button type="button" class="btn btn-danger" onclick="opDelete('${dataVo.regSn}');">삭제</button>
                                        </div>
                                        <div class="col-md-12 text-right">
                                            <c:if test="${not empty dataVo.fileList}">
                                                <op:fileDownload fileList="${dataVo.fileList}"/>
                                            </c:if>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        <op:no-data obj="${pager}" colspan="${colspanNum}" />
                    </tbody>
                </table>
                <!-- //리스트 -->
            </div>
            <op:pager pager="${pager}" />
            <!-- 버튼 -->
            <div class="block clearfix">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="button" class="btn btn-danger" onclick="opDeleteSelection();">선택 메시지 삭제</button>
                    </div>
                </div>
            </div>
            <!-- //버튼 -->
        </div>
    </div>
</body>
</html>