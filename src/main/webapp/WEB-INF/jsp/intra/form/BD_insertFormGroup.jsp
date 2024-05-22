<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="ckeditor, tags" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/form/js/form.js"></script>

<script type="text/javascript">
    //<![CDATA[

	 <valid:script type="msgbox" /> 
     /* 공통 초기화 기능 */
    $(document).ready(function() {
    	/* 유효성 검증 BEAN Annotation 기반 자동 설정 */
    	var url = '/intra/form/INC_insertGroupIem.do?q_formSn=' + $("#formSns").val();
 
            $('#formGroupList').load(url);
    	
	       	$("#dataForm").submit(function() {
	       		if(validate()){
	       			dataString = $("#dataForm").serialize();
		               $.ajax({
		                   type: "POST",
		                   url: "ND_insertFormGroup.do",
		                   data: dataString,
		                   dataType: "json",
		                   success: function(response){
		                       try {
		                           opJsonMsg(response);
		                           $('#formGroupList').load(url);
		                           $('#groupNm').val('');
		                           $('#groupExpln').val('');
		                       } catch (e) {
		                           // 시스템 오류 발생시 처리 구간
		                           opErrorMsg(response, e);
		                    	   return;
		                       }
		                   }
		               	
		               });
		           };		       	
		        return false;
	       		});   	 
    });
  

    //]]>
</script>

	<div id="opTabs" class="tabbable page-tabs">
    <ul class="nav nav-tabs nav-justified">
        <li><a href="/intra/form/BD_updateFormInfo.do?q_formSn=${paramMap.q_formSn}">[STEP 1] 폼작성</a></li>
        <li class="active"><a href="/intra/form/BD_insertFormGroup.do?q_formSn=${paramMap.q_formSn}">[STEP 2] 그룹 • 항목작성</a></li>
        <li><a href="/intra/form/BD_selectFormResponseList.do?q_formSn=${paramMap.q_formSn}">[STEP 3] 응답관리</a></li>
    </ul>
	</div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 그룹 등록
            </h6>
        </div> 
		<div class="panel-body">
		<form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_insertFormGroup.do" class="form-horizontal">
			<input type="hidden" id="formSns" name="formSns" value="${paramMap.q_formSn}" />
			<input type="hidden" id="formSn" name="formSn" value="${paramMap.q_formSn}" />
			<input type="hidden" name="chkymd" value="${chkymd}">
			
			
			<%-- 페이징 관련 파라미터 생성 --%>
			<op:pagerParam view="view" ignores="" />
	
			<div class="help-block text-right">
				<span class="mandatory">*</span> 항목은 필수 입력항목입니다.
			</div>
	
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<div class="help-block" <c:if test="${chkymd eq -2}">style="display:none;"</c:if>>
								<span class="text-info">* 항목 추가를 위해서는 최소 한 개 이상의 그룹이 필요합니다.</span>
							</div>
							<div class="help-block" <c:if test="${chkymd ne -2}">style="display:none;"</c:if>>
								<span class="text-info">* 시작일이 지난 폼은 수정 및 삭제가 불가능합니다.</span>
							</div>
						</div>
						<div class="panel-body">
							<div class="pull-right">
								 <button type="submit" class="btn btn-success" <c:if test="${chkymd eq -2}">style="display:none;"</c:if>>그룹추가</button>
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label for="groupNms" class="control-label col-sm-2">
							<span class="mandatory">*</span> 그룹명
						</label>
						<div class="col-sm-10">
							<div class="row">
									<div class="col-sm-12">
										<input type="text" name="groupNm" id="groupNm" value="${dataVo.groupNm}" maxlength="100" class="form-control" placeholder="그룹명을 입력하세요." <c:if test="${chkymd eq -2}"> disabled</c:if>/>
									</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<valid:msg name="groupNm" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="groupExpln" class="control-label col-sm-2">
							<span class="mandatory"></span> 설명
						</label>
						<div class="col-sm-10">
							<div id="textareaBlock" class="block row">
								<div class="col-sm-12">
									<textarea name="groupExpln" id="groupExpln" rows="7" cols="80" class="form-control"<c:if test="${chkymd eq -2}"> disabled</c:if>>${dataVo.groupExpln}</textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
 									<valid:msg name="groupExpln" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
		                 <label for="useYn" class="control-label col-sm-2">
		                     <span class="mandatory">*</span> 사용여부
		                 </label>
		                 <div class="col-sm-10">
		                     <label for="useYnY" class="radio-inline radio-primary">
		                         <input type="radio" name="useYn" id="useYnY" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
		                             예
		                     </label>
		                     <label for="useYnN" class="radio-inline radio-primary">
		                         <input type="radio" name="useYn" id="useYnN" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
		                         아니오
		                     </label>
		                     <div class="row">
		                         <div class="col-sm-4">
		                             <valid:msg name="useYn" />
		                         </div>
		                     </div>
		                 </div>
		             </div>
				</div>
			</div>
		</form><br>
	</div> 
</div>
   <div id="formGroupList"></div> 
   
	<!-- 버튼 -->
	<div class="row">
		<div class="block clearfix">
			<div class="col-sm-12 btn-group">
				<div class="pull-right">
					<button type="button" class="btn btn-primary" <c:if test="${chkymd eq -2}">style="display:block;"</c:if> onclick="opList('BD_selectFormList.do');">목록</button>
				</div>
			</div>
		</div>
	</div>



 