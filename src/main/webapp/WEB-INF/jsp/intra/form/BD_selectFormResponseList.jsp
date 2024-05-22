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
<title>응답 관리</title>

<op:jsTag type="intra" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/form/js/formResponse.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
   	
    });

  
  function opExcel(frm) {
	  if ($("#excelForm").length > 0) {
          $("#excelForm").remove();
      }

      var excelForm = "<form name='excelForm' id='excelForm' method='post' action='/intra/form/ND_formExcelDownload.do'>";
      excelForm += "<input type='hidden' name='excelKey' id='excelKey' value='formResponse' />";
      excelForm += "<input type='hidden' name='excelFileNm' id='excelFileNm' value='' />";
      $("body").append(excelForm);

      //검색조건별로 출력하고 싶은경우 파라메터 추가
      if ("Y" == "Y") {
          $("#dataForm [name^='" + OpConfig.prefixSearchParam + "']").each(function() {
              var param = "<input type='hidden' name='" + $(this).attr("name") + "' value='" + $(this).val() + "' />";
              $("#excelForm").append(param);
          })
      } 

      $("#excelForm").submit();

  }
  
  function test(test) {
	alert(test);
	}

    //]]>
</script>
</head>
<body>
	<div id="opTabs" class="tabbable page-tabs">
    <ul class="nav nav-tabs nav-justified">
        <li><a href="/intra/form/BD_updateFormInfo.do?q_formSn=${paramMap.q_formSn}">[STEP 1] 폼작성</a></li>
        <li><a href="/intra/form/BD_insertFormGroup.do?q_formSn=${paramMap.q_formSn}">[STEP 2] 그룹 • 항목작성</a></li>
        <li class="active"><a href="/intra/form/BD_selectFormResponseList.do?q_formSn=${paramMap.q_formSn}">[STEP 3] 응답관리</a></li>
    </ul>
	</div>
    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectFormResponseList.do" class="form-inline">

            <fieldset>
            	<%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="${dataVo.rspnsNope}" ignores="" /><br>

                <legend class="sr-only">목록검색조건</legend>
                <input type="hidden" name="q_rspnsHeadNo" id="q_rspnsHeadNo" value="" />
                <input type="hidden" name="q_formSn" id="q_formSn" value="${paramMap.q_formSn}" />
                <input type="hidden" name="formSn" id="formSn" value="${paramMap.q_formSn}" />
                <input type='hidden' name='excelKey' id='excelKey' value='formResponse' />
            	<input type='hidden' name='excelFileNm' id='excelFileNm' value='' />
                
                <div class="block">
	                	<div class="pull-left">
	                		<div class="form-group">
		                        <label for="q_searchKey" class="sr-only">항목</label>
		                        <select name="q_searchKey" id="q_searchKey" class="select" style="width: 120px;">
		                            <option value="">-- 검색선택 --</option>
		                            <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>이름</option>
		                            <option value="1002" <c:if test="${paramMap.q_searchKey eq '1003'}">selected="selected"</c:if>>답변</option>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label for="q_searchVal" class="">이름</label>
		                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
		                    </div>
		                    
		                    <button type="submit" class="btn btn-info">검색</button>
		                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
	                    </div>
			            <div class="pull-right">
			                <button type="button" id="excelDown" class="btn btn-info"  onclick="opExcel(${paramMap.q_formSn});">엑셀출력</button>
			                <button type="button" class="btn btn-danger" onclick="opCheckDelete();">선택삭제</button>
			            </div>
	                </div>

            </fieldset>
        </form>
    </div>
    <div class="block table-responsive">
    <form name="listForm" id="listForm" method="post" action="ND_deleteIndivResponseList.do" class="form-inline">
    	<input type="hidden" name="q_formSn" id="q_formSn" value="${paramMap.q_formSn}" />
        <!-- 리스트 -->
        <table style="overflow:auto" class="table table-bordered table-striped table-hover op-table-list" summary="응답관리 목록으로 순번, 이름, 이메일, 연락처, 접수일 정보를 제공합니다.">
            <caption class="hidden">응답관리</caption>
            <colgroup>
            <thead>
	                <tr>
	                	<th><input type="checkbox" id="chkall" name="chkall" /></th>
	                    <th>순번</th>
	                    <th>이름</th>
	                    <th>이메일</th>
	                    <th>연락처</th>
	                    <th>접수일</th>
	                    <c:forEach items="${pager.list}" var="nmVo" varStatus="status" begin="0" end="0">
	                    	<c:forEach items="${nmVo.rspnsArtclNmList}" var="artclList" varStatus="status" >
	                    		<th>${artclList}</th>
	                    	</c:forEach>
	                    </c:forEach>
                </tr>
            </thead>
            <tbody>
	            <c:set var="index" value="${pager.indexNo}" />
               	<c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <c:set var="style" value="${status.count % 2 != 1 ? 'bg-style' : ''}" />
                    <tr class="${style}">
                        <td class="text-center">
                            <input type="checkbox" id="rspnsHeadNos${status.count}" name="rspnsHeadNos" value="${dataVo.rspnsHeadNo}" />
                        </td>
                        <td class="text-center">${index-status.index}</td>
                        <td class="text-center">
                        	${dataVo.rspnsNm}
                        </td>
                        	
                        <td class="text-center">${dataVo.rspnsEmlId}</td>
                        <td class="text-center">${dataVo.rgnTelno}</td>
                        <td class="text-center">
                            <fmt:formatDate value="${dataVo.rspnsDt}" pattern="yyyy-MM-dd" />
                        </td>
	                    	<c:forEach items="${dataVo.rspnsHeadAnsCnList}" var="rspnsList" varStatus="status">
	                    		<td class="text-center">
	                    			<c:set var="file" value="${rspnsList}"/>
	                    			<c:choose>
	                    				<c:when test="${fn:contains(file, '[##]')}">
	                    					<c:set var="fileSn" value=" ${fn:split(rspnsList,'[##]')[0]}"/>
	                    					<c:set var="fileId" value=" ${fn:split(rspnsList,'[##]')[1]}"/>
		                    				<c:set var="fileNm" value=" ${fn:split(rspnsList,'[##]')[2]}"/>
		                    				
	                    				 	<c:set var = "fileSns" value = "${fn:trim(fileSn)}" />
	                    				  	<c:set var = "fileIds" value = "${fn:trim(fileId)}" />

									        <a href="/component/file/ND_fileDownload.do?q_fileSn=${fileSns}&amp;q_fileId=${fileIds}" title="${fileNm}">
									            <button type="button" class="btn btn-icon btn-xs btn-default" id="fileDown" title="${fileNm}">
									                <i class="icon-file-pdf"></i>
									            </button>
									        </a>
	                    				</c:when>
	                    				<c:otherwise>
	                    					${rspnsList}
	                    				</c:otherwise>
	                    			</c:choose>
	                    		</td>
	                    		
	                    </c:forEach>
                    </tr>
                </c:forEach>
	                	
                <op:no-data obj="${pager}" colspan="6" />
            </tbody>
        </table>
        
        </form>
    </div>
    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
    
    <div class="row">
        <div class="block clearfix">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn-primary" <c:if test="${chkymd eq -2}">style="display:block;"</c:if> onclick="opList('BD_selectFormList.do');">목록</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 