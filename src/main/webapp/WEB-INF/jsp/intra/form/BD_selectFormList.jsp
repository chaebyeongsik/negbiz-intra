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
<title>폼 관리</title>

<op:jsTag type="intra" items="ui, opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/form/js/form.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // select 박스 선택 값 설정
        opSelected("q_searchKey", "${paramMap.q_searchKey}");
        opSelected("q_siteSn", "${paramMap.q_siteSn}");
      
        
    });

    function test() {
    	if ($('#q_form').val() != '') {
    		  jsExcel();
    		} else {
    		  alert("신청서를 선택해주세요.");
    		}
	}
    //]]>
</script>
</head>
<body>
<div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectFormList.do" class="form-inline">
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_formSn" id="q_formSn" value="" />
                <div class="block">	
                    <div class="form-group">
                        <label for="q_siteSn" class="control-label">사이트</label>
                        <select name="q_siteSn" id="q_siteSn" class="select" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${domainList}" var="domain" varStatus="vst">
                                <option value="${domain.siteSn}" <c:if test="${domain.siteSn eq paramMap.q_siteSn}">selected="selected"</c:if>>${domain.siteExpln}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_searchKey" class="control-label">상태</label>
                        <select name="q_searchKey" id="q_searchKey" class="select" style="width: 100px">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>대기</option>
                            <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected="selected"</c:if>>진행</option>
                            <option value="1003" <c:if test="${paramMap.q_searchKey eq '1003'}">selected="selected"</c:if>>종료</option>
                            <option value="1004" <c:if test="${paramMap.q_searchKey eq '1004'}">selected="selected"</c:if>>미사용</option>
							<option value="1005" <c:if test="${paramMap.q_searchKey eq '1005'}">selected="selected"</c:if>>수정중</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_bgngYmd" class="">시작일</label>
                        <input type="text" class="from-date form-control" name="q_bgngYmd" id="q_bgngYmd" placeholder="시작일" />
                    </div>
                    <div class="form-group">
                        <label for="q_endYmd" class="">종료일</label>
                        <input type="text" class="from-date form-control" name="q_endYmd" id="q_endYmd" placeholder="종료일" />
                    </div>
                    
                    <div class="form-group">
                        <label for="q_searchVal" class="">제목</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div><br>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="폼목록" ignores="" />
            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
    <form name="listForm" id="listForm" method="post" action="ND_deleteFormList.do" class="form-inline">
        <!-- 리스트 -->
        <table class="table table-bordered op-table-list config-list" summary="폼 목록으로 순번,게시판명,게시판설명,사용여부,분류사용여부 첨부파일사용여부, 의견글사용여부, 이전/다음글 사용여부, 게시물관리 정보를 제공합니다.">
            <caption class="hidden">폼 목록</caption>
             <colgroup>
                <col width="8%" />
                <col width="8%" />
                <col width="30%" />
                <col width="8%" />
                <col width="10%" />
                <col width="20%" />
                <col width="8%" />
            </colgroup>
            <thead>
                <tr>
                    <th><input type="checkbox" id="chkall" name="chkall" /></th>
                    <th>순번</th>
                    <th>제목</th>
                    <th>상태</th>
                    <th>응답인원/제한인원</th>
                    <th>기간</th>
                    <th>응답관리</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <c:set var="style" value="${status.count % 2 != 1 ? 'bg-style' : ''}" />
                     <tr class="${style}">
                        <td class="text-center">
                            <input type="checkbox" id="formSns${status.count}" name="formSns" value="${dataVo.formSn}" />
                        </td>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <div class="block">
                                <a href="BD_updateFormInfo.do?q_formSn=${dataVo.formSn}">${dataVo.formTtl}</a>
                            </div>
                        </td>
                        <td class="text-center">
							<c:set var="dataYn" value="${dataVo.dataYn}"/>
                            <c:choose>
                               <c:when test="${dataVo.pstgYn eq 'N'}">
                                    <span class="label label-danger">미사용</span>
                                </c:when>
								<c:when test="${dataVo.pstgYn eq 'Y' && dataYn eq 'N'}">
                                    <span class="label label-danger">수정중</span>
                                </c:when>
                                <c:when test="${dataVo.statusEnd > '0'}">
                                    <span class="label label-danger">종료</span>  
                                </c:when>
                                <c:when test="${dataVo.statusBegin < '0'}">
                                    <span class="label label-default">대기</span>
                                   
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-success">진행중</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:set var="nope" value="${dataVo.rspnsNope}"/>
                        <td class="text-center">
	                        <c:choose>
	                        		<c:when test="${dataVo.lmtNope eq NULL && nope eq NULL}">-/-</c:when>
	                        		<c:when test="${dataVo.rspnsNope eq NULL}">-/${dataVo.lmtNope}</c:when>
	                        		<c:when test="${dataVo.lmtNope eq NULL}">${dataVo.rspnsNope}/-</c:when>    
	                        		<c:otherwise>${dataVo.rspnsNope}/${dataVo.lmtNope}</c:otherwise>
	                        </c:choose>
                        </td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.bgngYmd}" pattern="yyyy-MM-dd" />
                            ~
                            <fmt:formatDate value="${dataVo.endYmd}" pattern="yyyy-MM-dd" />
                        </td>
                        <td class = "text-center">
                     <a href="/intra/form/BD_selectFormResponseList.do?q_formSn=${dataVo.formSn}"><button type="button" class="btn btn-info">관리</button></a>
                  </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="7" />
            </tbody>
        </table>
        </form>
    </div>
    <!-- 버튼 -->
    <div class="row">
        <div class="block clearfix">
            <div class="col-sm-12 btn-group">
                <div class="pull-left">
                    <button type="button" class="btn btn-danger" onclick="opCheckDelete();">선택삭제</button>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-info" onclick="opInsertForm('BD_insertFormInfo.do');">신규등록</button>
                </div>
                <!-- <div class="pull-right">
                	<a href="javascript:void(0);" onclick="test();">
                    	<button type="button" class="btn btn-info">엑셀출력 테스트</button>
                    </a>
                </div> -->
            </div>
        </div>
    </div>
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
</body>
</html>