<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>


<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="ckeditor, tags" />
<script type="text/javascript" src="/resources/intra/form/js/form.js"></script>

<script type="text/javascript">
var url = '/intra/form/INC_selectFormGroupList.do?q_formSn=' + $("#q_formSn").val();    

/* 공통 초기화 기능 */
$(document).ready(function() {
	/* 유효성 검증 BEAN Annotation 기반 자동 설정 */
	<valid:script type="msgbox" /> 
    	var chkymd = $(this).find('input[name=chkymd]').val();
		if(chkymd == -2){
			$(".sortGroup").sortable({ 
			    cancel: ".sortGroup" 
			});
		}
		
    	/* 항목 추가 */
 		$(".dataFormIem").submit(function() {
 			var formId = "#" + $(this).attr("id");
 			var id = $(formId).find("input[name='groupSn']").val();
 			
 			var IemName = $(this).find('option:selected').val(); 			
 			if(IemName == 'selectIem' || IemName == 'radioIem' || IemName == 'checkIem') {
		       	target = $("#optCns_"+id);
				if(!target.notnullValidator()) {
					opWarningMsg("옵션 : 최소 1개 이상 필수 입력 항목입니다.");
					target.focus();
					return false;
				}
		    }
 			if(IemName == 'fileIem') {
		       	target = $("#lmtFileSz"+id);
				if(!target.notnullValidator() || !target.rangeValidator(0,99)) {
					opWarningMsg("파일당 최대 용량은 숫자 2자리 이하로 필수입력 항목입니다.");
					target.focus();
					return false;
				}
				target = $("#prmsnFileExtnMttr"+id);
				if(!target.notnullValidator() || !target.alphaCommaValidator()){
					opWarningMsg("확장자 : 필수입력 항목이며 영문으로만 입력해주세요.");
					target.focus();
					return false;
				}
		    }
 			
 			else if(IemName == 'numIem') {	
	    		var initVl = $("#initVl"+id).val();
	    		var lmtVl = $("#lmtVl"+id).val();

	    		target = $("#initVl"+id);
	    		
	    		if(!$("#initVl"+id).notnullValidator() || !$("#initVl"+id).notnullValidator()) {
					opWarningMsg("제한범위 : 필수입력 항목입니다.");
					target.focus();
					return false;
				}
	    		
	    		else if(parseInt(initVl) > parseInt(lmtVl)) {
					opWarningMsg("초기값은 제한값보다 클 수 없습니다.");
					target.focus();
					return false;
				}
			}
			else if(IemName == 'checkIem') {
		        target = $("#plChcCnt"+id);
		       	var checkCnt = $(this).find('[name=optCns]').length;
				var plChcCnt = $(this).find('[name=plChcCnt]').val();

				if(!target.notnullValidator()) {
					opWarningMsg("복수선택 : 필수입력 항목입니다.");
					target.focus();
					return false;
				}
				
		       	if (plChcCnt > checkCnt) {
					opWarningMsg("복수선택수는 항목 갯수보다 클 수 없습니다.");
					target.focus();
					return false;
				}
		    }

 			 if(validate(formId)){
	              dataString = $(this).serialize();
	              $.ajax({
	                 type: "POST",
	                 url: "ND_insertFormGroupIem.do",
	                 data: dataString,
	                 dataType: "json",
	                 success: function(response){
	                     try {
	                         opJsonMsg(response);
	                         $('#groupList').load(url);
	                     } catch (e) {
	                         // 시스템 오류 발생시 처리 구간
	                         opErrorMsg(validate, e);
	                         return;
	                     }
	                 }
	             });
 			};
           	return false;   
       	});
 		
    	$(".dataFormDel").submit(function() {
			var formId = "#" + $(this).attr("id");
			if(validate(formId)){
				dataString = $(formId).serialize();
		         $.ajax({
		             type: "POST",
		             url: "ND_deleteFormGroup.do",
		             data: dataString,
		             dataType: "json",
		             success: function(response){
		                 try {
		                     opJsonMsg(response);
		                     $('#groupList').load(url);
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

 		/* 항목 수정 */
       	$(".updateIem").submit(function() {
    		var formId = "#" + $(this).attr("id");
			var groupSn = $(formId).find("input[name='groupSn']").val();
			var artclSn = $(formId).find("input[name='artclSn']").val();
			var id = groupSn+"_"+artclSn;
     		var IemName = $(this).find('option:selected').val();
     		console.log('IemName : ',IemName);
     		
     		if(IemName == 'fileIem') {
		       	target = $("#lmtFileSz"+id);
				if(!target.notnullValidator() || !target.rangeValidator(0,99)) {
					opWarningMsg("파일당 최대 용량은 숫자 2자리 이하로 필수입력 항목입니다.");
					target.focus();
					return false;
				}
				target = $("#prmsnFileExtnMttr"+id);
				if(!target.notnullValidator() || !target.alphaCommaValidator()){
					opWarningMsg("확장자 : 필수입력 항목이며 영문으로만 입력해주세요.");
					target.focus();
					return false;
				}
		    }
			else if(IemName == 'numIem') {	
	    		var initVl = $("#initVl"+id).val();
	    		var lmtVl = $("#lmtVl"+id).val();
	    		
	    		target = $("#initVl"+id);
	    		
	    		if(!$("#initVl"+id).notnullValidator() || !$("#initVl"+id).notnullValidator()) {
					opWarningMsg("제한범위 : 필수입력 항목입니다.");
					target.focus();
					return false;
				}
	    		
	    		else if(parseInt(initVl) > parseInt(lmtVl)) {
					opWarningMsg("초기값은 제한값보다 클 수 없습니다.");
					target.focus();
					return false;
				}
			}
			else if(IemName == 'checkIem') {
		        target = $("#plChcCnt"+id);
		       	var checkCnt = $(this).find('[name=optCns]').length;
				var plChcCnt = $(this).find('[name=plChcCnt]').val();

				if(!target.notnullValidator()) {
					opWarningMsg("복수선택 : 필수입력 항목입니다.");
					target.focus();
					return false;
				}
				
		       	if (plChcCnt > checkCnt) {
					opWarningMsg("복수선택수는 항목 갯수보다 클 수 없습니다.");
					target.focus();
					return false;
				}
		    }
      		if(validate(formId)){
		       	dataString = $(this).serialize();
		       	$.ajax({
		       		type : "POST",
		       		url : "ND_updateFormGroupIem.do",
		       		data: dataString,
		       		dataType : "json",
		       		success : function(response) {
		                opJsonMsg(response);
		                $('#groupList').load(url);
		            },
		            error : function(response) {
		                opSysErrorMsg(response.responseText);
		                return;
		            }
		       	});
     		};
      	return false;
        });   	
 		
 		
       	$(".sortGroup").sortable({
 			axis: "y",
 			containment: "parent",
 			update: function (event, ui) { 					
 				var newOrder = $(this).sortable('toArray', {
 					attribute: 'data-order'
 				});
 				var formSn = $(this).find('input[name=formSn]').val();
 				$.ajax({
 		  			url : "ND_updateSortGroup.do",
 		  			type : "POST",
 		  			dataType : "json",
 		  			data: {groupSns : newOrder,
 		  				formSn : formSn
 		  				},
 		  			success : function(response) {
 		  				opJsonMsg(response);
 		  			},
 		  			error : function(response) {
 		  				opSysErrorMsg(response.responseText);
 		  				return;
 		  			},
 		  			
 		  		});

 			}
 		});
});


var delOpt = function(obj) {         
    $(obj).parent().parent().remove();
 };
 

</script>

<div id="groupList">
<div class="sortGroup">
    <c:forEach items="${dataList.grpList}" var="grpList" varStatus="status"> 
    	 
        <br><div class="panel panel-primary" data-order="${grpList.groupSn}">
       
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-libreoffice"></i> 그룹
                </h6>
            </div> 
           
            <%@include file="INC_selectFormGroupList.jsp"%>
          
            <div class="panel-body" <c:if test="${chkymd eq -2}">style="display:none;"</c:if>>
                <div class="callout panel panel-default">
                    <div class="panel-heading">
                        <h6 class="callout callout-info">
                            <i></i> 항목 등록
                        </h6>
                    </div><br>	
                    <form name="dataFormIem" id="dataFormIem_${grpList.groupSn}" method="post" action="ND_insertFormGroupIem.do" class="form-horizontal dataFormIem"> 
                        <div class="pull-right">
                            <button type="submit" class="btn btn-info">항목추가</button>
                        </div>
                        <input type="hidden" name="formSn" id="q_formSn" value="${grpList.formSn}" />
                        <input type="hidden" name="groupSn" id="groupSn" value="${grpList.groupSn}" /> 
                        <input type="hidden" name="chkymd" value="${chkymd}">
                        
                        <br>
                        
                        <div class="form-group"> 
                            <label for="artclTypeCd" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 유형${grpList.groupSn}
                            </label>
                            <div class="col-sm-4">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <select name="artclTypeCd" id="addSelectBox_${grpList.groupSn}" onclick="selectOpt('${grpList.groupSn}', '0',this.value)" class="select" >
                                            <option value="ttt">전체</option>
                                            <option value="shortTxIem" >단답형</option>
                                            <option value="longTxIem" >장문형</option>
                                            <option value="selectIem" >셀렉트박스</option>
                                            <option value="radioIem" >라디오버튼</option>
                                            <option value="checkIem" >체크박스</option>
                                            <option value="ynIem" >Y/N</option>
                                            <option value="emlIem" >이메일</option>
                                            <option value="telnoIem" >연락처</option>
                                            <option value="ymdIem" >날짜/시간</option>
                                            <option value="addrIem" >주소</option>
                                            <option value="numIem" >숫자</option>
                                            <option value="fileIem" >파일</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="artclTypeCd" />
                                    </div>
                                </div> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="esntlYn" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 필수여부
                            </label>
                            <div class="col-sm-10">
                                <label for="esntlYnY${grpList.groupSn}" class="radio-inline radio-primary">
                                    <input type="radio" name="esntlYn" id="esntlYnY${grpList.groupSn}" value="Y" <c:if test="${dataVo.esntlYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="esntlYnN${grpList.groupSn}" class="radio-inline radio-primary">
                                    <input type="radio" name="esntlYn" id="esntlYnN${grpList.groupSn}" value="N" <c:if test="${dataVo.esntlYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="esntlYn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
			                 <label for="useYn" class="control-label col-sm-2">
			                     <span class="mandatory">*</span> 사용여부
			                 </label>
			                 <div class="col-sm-10">
			                     <label for="iemUseYnY${grpList.groupSn}" class="radio-inline radio-primary">
			                         <input type="radio" name="useYn" id="iemUseYnY${grpList.groupSn}" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
			                             예
			                     </label>
			                     <label for="iemUseYnN${grpList.groupSn}" class="radio-inline radio-primary">
			                         <input type="radio" name="useYn" id="iemUseYnN${grpList.groupSn}" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
			                         아니오
			                     </label>
			                     <div class="row">
			                         <div class="col-sm-4">
			                             <valid:msg name="useYn" />
			                         </div>
			                     </div>
			                 </div>
			             </div>
                        <div class="form-group" id="artcl_${grpList.groupSn}_0">
                            <label for="artclNm" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 항목명
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <input type="text" name="artclNm" id="artclNm${grpList.groupSn}" value="${dataVo.artclNm}" maxlength="100" class="form-control" placeholder="항목명을 입력하세요." />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="artclNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="artclExp_${grpList.groupSn}_0">
                            <label for="artclExpln" class="control-label col-sm-2">
                                <span class="mandatory"></span> 항목설명
                            </label>
                            <div class="col-sm-10">
                                <div id="textareaBlock" class="block row">
                                    <div class="col-sm-12">
                                        <textarea name="artclExpln" id="artclExpln${grpList.groupSn}" rows="3" cols="80" class="form-control">${dataVo.artclExpln}</textarea>
                                    </div>
                                </div>
                                <div class="row">
									<div class="col-sm-12">
	 									<valid:msg name="artclExpln" />
									</div>
								</div>
                            </div>
                        </div>
                        <div id="appendOpt_${grpList.groupSn}_0"></div>
                    </form>
                </div>
            </div> 

            <div class="form-group" id="num_${grpList.groupSn}_0" style="display: none">
                <label for="" class="control-label col-sm-2">
                    <span class="mandatory">*</span> 제한범위
                </label>
                <div class="col-sm-4">
                    <div class="row">
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="initVl" id="initVl${grpList.groupSn}" value="${dataVo.initVl}" maxlength="4" placeholder="초기값" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                        </div>
                        <label for="" class="control-label col-sm-2">~
                        </label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="lmtVl" id="lmtVl${grpList.groupSn}" value="${dataVo.lmtVl}" maxlength="4" placeholder="제한값" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <valid:msg name="initVl" />
                        </div>
                        <div class="col-sm-6">
                            <valid:msg name="lmtVl" />
                        </div>
                    </div> 
                </div>
            </div>
            <div class="form-group" id="box_${grpList.groupSn}_0" style="display: none">
                <label for="optCns" class="control-label col-sm-2">
                    <span class="mandatory">*</span> 옵션
                </label>
                <div class="col-sm-10">
                    <div class="row">
                       	<div class="col-md-12"  id="insertBoxDiv_${grpList.groupSn}_0">
                            <div class="row block"  id="boxDiv_${grpList.groupSn}_0">
                                <div class="col-sm-8">
                                    <input type="text" name="optCns" id="optCns_${grpList.groupSn}" value="" maxlength="1000" class="form-control" placeholder="옵션내용을 입력하세요." <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                                </div>
                                <div class="col-md-2">
                                	<button type="button" class="btn btn-info" id="insert_${grpList.groupSn}_0">옵션추가</button>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div class="row"> 
                        <div class="col-sm-12">
                            <div class="help-block op-validate">
                                <ul class="list-group">
                                    <li class="list-group-item"><span class="validate-msg">- 선택형 항목 등록 시 최소 <strong>한 개의 옵션</strong>을 입력해주세요.</span></li>
                                </ul>
                                <valid:msg name="optCn"/>
                            </div>
                        </div>
                    </div>
                </div>         
            </div>
   
            <div class="form-group" id="cnt_${grpList.groupSn}_0" style="display: none">
                <label for="plChcCnt" class="control-label col-sm-2">
                    <span class="mandatory">*</span> 복수선택
                </label>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="plChcCnt" id="plChcCnt${grpList.groupSn}" value="${dataVo.plChcCnt}" maxlength="10" placeholder="값 입력" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <valid:msg name="plChcCnt"/>
                        </div>
                    </div>
                </div>          
            </div>
            <div class="form-group" id="file_${grpList.groupSn}_0" style="display: none">
                <label for="lmtFileSz" class="control-label col-sm-2">
                    <span class="mandatory">*</span> 파일용량
                </label>
                <div class="col-sm-10">
                    <div class="row">
                        <div class="col-md-4">
                            <input type="text" name="lmtFileSz" id="lmtFileSz${grpList.groupSn}" value="${dataVo.lmtFileSz}" class="form-control" placeholder="" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                        </div>
                       <div  class="col-md-2">
                           <div class="help-block op-validate">
                               <ul class="list-group">
                                   <li class="list-group-item"><span class="validate-msg">단위 : Mb(메가바이트)</span></li>
                               </ul>
                           </div>
                       </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <valid:msg name="lmtFileSz"/>
	                    </div>
                    </div>
                </div>

	                <label for="prmsnFileExtnMttr" class="control-label col-sm-2">
	                    <span class="mandatory">*</span> 확장자
	                </label>
	                <div class="col-sm-10">
	                    <div class="row">
	                        <div class="col-md-8">
	                            <input type="text" name="prmsnFileExtnMttr" id="prmsnFileExtnMttr${grpList.groupSn}" value="${dataVo.prmsnFileExtnMttr}" class="form-control" placeholder="" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
	                        </div>
	                    </div>
	                    <div class="row"> 
	                        <div class="col-sm-12">
	                            <div class="help-block op-validate">
	                                <ul class="list-group">
	                                    <li class="list-group-item"><span class="validate-msg">- 허용할 첨부파일 확장자를 <strong>띄어쓰기 없이 쉼표 문자( ',' )</strong>로 구분하여 등록하세요. (ex : jpg,gif,xls).</span></li>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	               
                </div> 
            </div> 

            <!-- 옵션 추가 div -->	 
            <div id="insertOptDummy_${grpList.groupSn}_0" style="display: none">
                <div class="row block">
                    <div class="col-md-8">
                        <input type="text" name="optCns" value="" class="form-control" placeholder="" />
                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-danger" onclick="delOpt(this);">삭제</button>
                    </div>
                </div>
            </div>   
        </div>
    </c:forEach></div>
</div>

