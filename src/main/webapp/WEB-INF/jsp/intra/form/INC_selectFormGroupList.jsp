<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>


<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="ckeditor, tags" />

<script type="text/javascript">
    //<![CDATA[
var url = '/intra/form/INC_insertGroupIem.do?q_formSn=' + $("#q_formSn").val();    
    /* 공통 초기화 기능 */
    $(document).ready(function() {
    	/* 유효성 검증 BEAN Annotation 기반 자동 설정 */
    	 <valid:script type="msgbox" /> 
   		 
    	var chkymd = $(this).find('input[name=chkymd]').val();
 		if(chkymd == -2){
 			$(".sortArtcl").sortable({ 
 			    cancel: ".sortArtcl" 
 			});
 		}
 		
    	 $(".sortArtcl").sortable({
    		
 			axis: "y",
 			containment: "parent",
 			update: function (event, ui) { 					
 				var newOrder = $(this).sortable('toArray', {
 					attribute: 'data-order'
 				});
 				var formSn = $(this).find('input[name=formSn]').val();
 	    		var groupSn = $(this).find('input[name=groupSn]').val();
 	    		
 	    		
 				$.ajax({
 		  			url : "ND_updateSortIem.do",
 		  			type : "POST",
 		  			dataType : "json",
 		  			data: {
 		  				artclSns : newOrder,
 		  				formSn : formSn,
 		  				groupSn : groupSn},
 		  			success : function(response) {
 		  				opJsonMsg(response);
 		  				$('#groupList').load(url);
 		  			},
 		  			error : function(response) {
 		  				opSysErrorMsg(response.responseText);
 		  				return;
 		  			},
 		  			
 		  		}); 

 			}
 		});
    });
  
	 /* 항목 삭제 */
	  function delIem(frm) { 
		    frm.action='ND_deleteFormGroupIem.do'; 
		    var form = "#" + frm.id;

       		dataString = $(form).serialize();
       			
	        $.ajax({
    			url : "ND_deleteFormGroupIem.do",
    			type : "POST",
    			dataType : "json",
    			data: dataString,
    			success : function(response) {
    				opJsonMsg(response);
    				$('#groupList').load(url);
    			},
    			error : function(response) {
    				opSysErrorMsg(response.responseText);
    				return;
    			}
    		}); 
	        return true;
		   
	  };
	  
	  /* 그룹 수정 */
	  function updateGrp(frm) { 
		    frm.action='ND_updateFormGroup.do'; 
		    var form = "#" + frm.id;
		    if(validate(form)){
	     		dataString = $(form).serialize();
	     			
		        $.ajax({
		  			url : "ND_updateFormGroup.do",
		  			type : "POST",
		  			dataType : "json",
		  			data: dataString,
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
	        return true;
		   
	  };
	  

      var delOpt = function(obj) {         
         $(obj).parent().parent().remove();
      };
      
                   
    //]]>
</script>

<div id="groupList">
 
	<c:set var="artclIdx" value="0" />

	<!-- 그룹 정보 출력  -->    
	<form class="form-horizontal dataFormDel" name="dataFormDel" id="dataFormDel_${grpList.groupSn}" method="post"  enctype="multipart/form-data" action="ND_deleteFormGroup.do" > 
		<input type="hidden" name="q_groupSn" value="${grpList.groupSn}"/> 
		<input type="hidden" name="q_formSn" value="${grpList.formSn}" /> 
		<input type="hidden" name="groupSn" value="${grpList.groupSn}"/> 
		<input type="hidden" name="formSn" value="${grpList.formSn}" /> 
		<input type="hidden" name="sortSn" value="${grpList.sortSn}">	
		<div class="panel-body">
			<div class="pull-right" <c:if test="${chkymd eq -2}">style="display:none;"</c:if>>
				<button type="button" class="btn btn-success" onclick='return updateGrp(this.form);'>수정</button>
				<button type="submit" class="btn btn-danger">그룹삭제</button>
			</div>
			 <div class="form-group">
			 	<div class="col-sm-10">
					<div class="row">
						<div class="col-sm-12">
							<input type="text" name="groupNm" id="groupNm${grpList.groupSn}" value="${grpList.groupNm}" maxlength="100" class="form-control" placeholder="그룹명을 입력하세요." <c:if test="${chkymd eq -2}"> disabled</c:if>/>
						</div>
					</div><br>
				</div>
			</div>
			<div class="form-group">
			 	<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-12">
							<textarea name="groupExpln" id="groupExpln${grpList.groupSn}" rows="4" cols="80" class="form-control"<c:if test="${chkymd eq -2}"> disabled</c:if>>${grpList.groupExpln}</textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
                 <label for="useYn" class="control-label col-sm-2">
                     <span class="mandatory">*</span> 사용여부
                 </label>
                 <div class="col-sm-10">
                     <label for="useYnY${grpList.groupSn}" class="radio-inline radio-primary">
                         <input type="radio" name="useYn" id="useYnY${grpList.groupSn}" value="Y" <c:if test="${grpList.useYn eq 'Y'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
                             예
                     </label>
                     <label for="useYnN${grpList.groupSn}" class="radio-inline radio-primary">
                         <input type="radio" name="useYn" id="useYnN${grpList.groupSn}" value="N" <c:if test="${grpList.useYn eq 'N'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
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
	</form>


	<!-- 항목 목록 출력 -->
	<c:set var="itemName" value="grpIemList_${grpList.groupSn}" />
	<c:set var="order" value="1" />
	<div class="sortArtcl">
	<c:forEach items="${dataList[itemName]}" var="iemList" > 	
		<form  data-order="${iemList.artclSn}" name="updateIem" id="updateIem_${grpList.groupSn}_${iemList.artclSn}" method="post" enctype="multipart/form-data" action="ND_updateFormGroupIem.do" class="form-horizontal updateIem"> 
			<input type="hidden" name="q_artclSn" value="${iemList.artclSn}" /> 
			<input type="hidden" name="q_groupSn" value="${grpList.groupSn}"/> 
			<input type="hidden" name="q_formSn" value="${grpList.formSn}">
			<input type="hidden" name="artclSn" value="${iemList.artclSn}" /> 
			<input type="hidden" name="groupSn" value="${grpList.groupSn}"/> 
			<input type="hidden" name="formSn" value="${grpList.formSn}">
			<input type="hidden" id="artclIdx"  value="${artclIdx+1}" />
			<input type="hidden" name="chkymd" value="${chkymd}">
			<input type="hidden" name="sortSn" value="${iemList.iemSortSn}">
			
			
			<div class="panel-body draggable" >  
                <div class="callout well" >
                    <div class="pull-right" <c:if test="${chkymd eq -2}">style="display:none;"</c:if>>
                        <button type="submit" class="btn btn-success">저장</button>
                        <button type="button" class="btn btn-danger" onclick='return delIem(this.form);'>항목삭제</button> 
                    </div>
                    <div class="form-group">
                        <label for="artclTypeCd" class="control-label col-sm-2">
                            <span class="mandatory">*</span> 유형
                        </label>
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-8">
                                    <select name="artclTypeCd" id="artclTypeCd_${grpList.groupSn}_${iemList.artclSn}" onclick="selectOpt('${grpList.groupSn}', '${iemList.artclSn}', this.value)" class="select" <c:if test="${chkymd eq -2}"> disabled</c:if>>
                                        <option value=""><op:message cdId="common.selectOption" /></option>
                                        <option id="shortTxIem${artclIdx}" value="shortTxIem" <c:if test="${iemList.artclTypeCd eq 'shortTxIem'}">selected="selected"</c:if>>단답형</option>
                                        <option id="longTxIem${artclIdx}" value="longTxIem" <c:if test="${iemList.artclTypeCd eq 'longTxIem'}">selected="selected"</c:if>>장문형</option>
                                        <option id="selectIem${artclIdx}" value="selectIem" <c:if test="${iemList.artclTypeCd eq 'selectIem'}">selected="selected"</c:if>>셀렉트박스</option>
                                        <option id="radioIem${artclIdx}" value="radioIem" <c:if test="${iemList.artclTypeCd eq 'radioIem'}">selected="selected"</c:if>>라디오버튼</option>
                                        <option id="checkIem${artclIdx}" value="checkIem" <c:if test="${iemList.artclTypeCd eq 'checkIem'}">selected="selected"</c:if>>체크박스</option>
                                        <option id="ynIem${artclIdx}" value="ynIem" <c:if test="${iemList.artclTypeCd eq 'ynIem'}">selected="selected"</c:if>>Y/N</option>
                                        <option id="emlIem${artclIdx}" value="emlIem" <c:if test="${iemList.artclTypeCd eq 'emlIem'}">selected="selected"</c:if>>이메일</option>
                                        <option id="telnoIem${artclIdx}" value="telnoIem" <c:if test="${iemList.artclTypeCd eq 'telnoIem'}">selected="selected"</c:if>>연락처</option>
                                        <option id="ymdIem${artclIdx}" value="ymdIem" <c:if test="${iemList.artclTypeCd eq 'ymdIem'}">selected="selected"</c:if>>날짜/시간</option>
                                        <option id="addrIem${artclIdx}" value="addrIem" <c:if test="${iemList.artclTypeCd eq 'addrIem'}">selected="selected"</c:if>>주소</option>
                                        <option id="numIem${artclIdx}" value="numIem" <c:if test="${iemList.artclTypeCd eq 'numIem'}">selected="selected"</c:if>>숫자</option>
                                        <option id="fileIem${artclIdx}" value="fileIem" <c:if test="${iemList.artclTypeCd eq 'fileIem'}">selected="selected"</c:if>>파일</option>
                                    </select>   
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
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
                            <label for="esntlYnY_${grpList.groupSn}_${iemList.artclSn}" class="radio-inline radio-primary">
                                <input type="radio" name="esntlYn" id="esntlYnY_${grpList.groupSn}_${iemList.artclSn}" value="Y" <c:if test="${iemList.esntlYn eq 'Y'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
                                    예
                            </label>
                            <label for="esntlYnN_${grpList.groupSn}_${iemList.artclSn}" class="radio-inline radio-primary">
                                <input type="radio" name="esntlYn" id="esntlYnN_${grpList.groupSn}_${iemList.artclSn}" value="N" <c:if test="${iemList.esntlYn eq 'N'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
                                아니오
                            </label>
                            <div class="row">
                                <div class="col-sm-4">
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
		                     <label for="useYnY_${grpList.groupSn}_${iemList.artclSn}" class="radio-inline radio-primary">
		                         <input type="radio" name="useYn" id="useYnY_${grpList.groupSn}_${iemList.artclSn}" value="Y" <c:if test="${iemList.iemUseYn eq 'Y'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
		                             예
		                     </label>
		                     <label for="useYnN_${grpList.groupSn}_${iemList.artclSn}" class="radio-inline radio-primary">
		                         <input type="radio" name="useYn" id="useYnN_${grpList.groupSn}_${iemList.artclSn}" value="N" <c:if test="${iemList.iemUseYn eq 'N'}">checked="checked"</c:if> <c:if test="${chkymd eq -2}"> disabled</c:if> class="styled" />
		                         아니오
		                     </label>
		                     <div class="row">
		                         <div class="col-sm-4">
		                             <valid:msg name="useYn" />
		                         </div>
		                     </div>
		                 </div>
		             </div>
                    <div class="form-group" id="artcl_${grpList.groupSn}_${iemList.artclSn}">
                        <label for="artclNms" class="control-label col-sm-2">
                            <span class="mandatory">*</span> 항목명
                        </label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-sm-6">
                                    <input type="text" name="artclNm" id="artclNm_${grpList.groupSn}_${iemList.artclSn}" value="${iemList.artclNm}" maxlength="100" class="form-control" placeholder="항목명을 입력하세요." <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <valid:msg name="artclNm" />
                                </div>
                            </div>               
                        </div>
                    </div>
                    <div class="form-group" id="artclExp_${grpList.groupSn}_${iemList.artclSn}">
                        <label for="artclExpln" class="control-label col-sm-2">
                            <span class="mandatory"></span> 항목설명
                        </label>
                        <div class="col-sm-10">
                            <div id="textareaBlock" class="block row">
                                <div class="col-sm-12">
                                    <textarea name="artclExpln" id="artclExpln_${grpList.groupSn}_${iemList.artclSn}" rows="3" cols="80" class="form-control" <c:if test="${chkymd eq -2}"> disabled</c:if>>${iemList.artclExpln}</textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <valid:msg name="artclExpln" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="appendOpt_${grpList.groupSn}_${iemList.artclSn}"></div>
                
                    <c:set var="optName" value="grpOptList_${grpList.groupSn}_${iemList.artclSn}" />
                    <c:forEach items="${dataList[optName]}" var="optList"> 
                        <div class="box_${grpList.groupSn}_${iemList.artclSn}">
                        	<c:if test="${iemList.artclTypeCd eq 'selectIem' || iemList.artclTypeCd eq 'radioIem' || iemList.artclTypeCd eq 'checkIem'}">  
                            <div class="form-group">
                                <label for="optCns" class="control-label col-sm-2">
                                    <span class="mandatory"></span> 옵션
                                </label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div id="updateOptDiv" class="col-md-12">
                                            <div class="row block">
                                                <div class="col-sm-8">
                                                    <input type="text" name="optCns" id="optCns${artclIdx}" value="${optList.optCn}" maxlength="1000" class="form-control" placeholder="옵션내용을 입력하세요." <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </c:if>
					</div>          
				</c:forEach>
                    <div class="plChcCnt_${grpList.groupSn}_${iemList.artclSn}"> 
                        <c:if test="${iemList.artclTypeCd eq 'checkIem'}">  
                                <div class="form-group">	
                                <label for="plChcCnt" class="control-label col-sm-2">
                                    <span class="mandatory">*</span> 복수선택
                                </label>
                                <div class="col-sm-4">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="plChcCnt" id="plChcCnt${grpList.groupSn}_${iemList.artclSn}" value="${iemList.plChcCnt}" maxlength="10" placeholder="값 입력" <c:if test="${chkymd eq -2}"> disabled</c:if> />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <valid:msg name="plChcCnt"/>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                        </c:if> 
                    </div>
                    <div class="lmtVl_${grpList.groupSn}_${iemList.artclSn}">
                        <c:if test="${iemList.artclTypeCd eq 'numIem'}">
                            <div class="form-group">
                                <label for="initVl" class="control-label col-sm-2">
                                    <span class="mandatory">*</span> 제한범위
                                </label>
                                <div class="col-sm-4">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="initVl" id="initVl${grpList.groupSn}_${iemList.artclSn}" value="${iemList.initVl}" maxlength="4" placeholder="초기값" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if> />
                                        </div>
                                        <label for="" class="control-label col-sm-2">~
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" name="lmtVl" id="lmtVl${grpList.groupSn}_${iemList.artclSn}" value="${iemList.lmtVl}" maxlength="4" placeholder="제한값" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if> />
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
                        </c:if>
                    </div>
                
                    <div class="lmtFileSz_${grpList.groupSn}_${iemList.artclSn}">
                        <c:if test="${iemList.artclTypeCd eq 'fileIem'}">
                            <div class="form-group">
                                <label for="lmtFileSz" class="control-label col-sm-2">
                                    <span class="mandatory"></span> 파일용량
                                </label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <input type="text" name="lmtFileSz" id="lmtFileSz${grpList.groupSn}_${iemList.artclSn}" value="${iemList.lmtFileSz}" class="form-control" placeholder="" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                                                </div>
                                                <div  class="col-md-2">
				                                    <div class="help-block op-validate">
				                                        <ul class="list-group">
				                                            <li class="list-group-item"><span class="validate-msg">단위 : Mb(메가바이트)</span></li>
				                                        </ul>
				                                    </div>
				                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <valid:msg name="lmtFileSz"/>
                                        </div>
                                    </div>
                                </div>             
                            </div>
                                
                            <div class="form-group">
                                <label for="prmsnFileExtnMttr" class="control-label col-sm-2">
                                    <span class="mandatory"></span> 확장자
                                </label>
                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="row block">
                                                <div class="col-md-8">
                                                    <input type="text" name="prmsnFileExtnMttr" id="prmsnFileExtnMttr${grpList.groupSn}_${iemList.artclSn}" value="${iemList.prmsnFileExtnMttr}" class="form-control" placeholder="" <c:if test="${chkymd eq -2}"> disabled</c:if> />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <div class="help-block op-validate">
                                                <ul class="list-group">
                                                    <li class="list-group-item"><span class="validate-msg">- 허용할 첨부파일 확장자를<strong>띄어쓰기 없이 쉼표 문자( ',' )</strong>로 구분하여 등록하세요. (ex : jpg,gif,xls).</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                        </c:if>
                    </div>
                </div>  
            </div> 
        </form>
		
                    
        <div class="form-group" id="num_${grpList.groupSn}_${iemList.artclSn}" style="display: none">
            <label for="initVl" class="control-label col-sm-2">
                <span class="mandatory">*</span> 제한범위
            </label>
            <div class="col-sm-4">
                <div class="row">
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="initVl" id="initVl${grpList.groupSn}_${iemList.artclSn}" value="${dataVo.initVl}" maxlength="4" placeholder="초기값" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                    </div>
                    <label for="" class="control-label col-sm-2">~
                    </label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="lmtVl" id="lmtVl${grpList.groupSn}_${iemList.artclSn}" value="${dataVo.lmtVl}" maxlength="4" placeholder="제한값" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
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
        <div class="form-group" id="box_${grpList.groupSn}_${iemList.artclSn}" style="display: none">
            <label for="optCns" class="control-label col-sm-2">
                <span class="mandatory">*</span> 옵션
            </label>
            <div class="col-sm-10">
                <div class="row">
	                <div class="col-md-12" id="insertBoxDiv_${grpList.groupSn}_${iemList.artclSn}">
	                    <div class="row block" id="boxDiv_${grpList.groupSn}_${iemList.artclSn}">
	                        <div class="col-sm-8">
	                            <input type="text" name="optCns" id="optCns_${grpList.groupSn}_${iemList.artclSn}" value="" maxlength="500" class="form-control" placeholder="옵션내용을 입력하세요." <c:if test="${chkymd eq -2}"> disabled</c:if>/>
	                        </div>
	                        <div class="col-md-2">
	                            <button type="button" class="btn btn-info" id="insert_${grpList.groupSn}_${iemList.artclSn}">옵션추가</button>
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

        <div class="form-group" id="cnt_${grpList.groupSn}_${iemList.artclSn}" style="display: none">
            <label for="plChcCnt" class="control-label col-sm-2">
                <span class="mandatory">*</span> 복수선택
            </label>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="plChcCnt" id="plChcCnt${grpList.groupSn}_${iemList.artclSn}" value="${dataVo.plChcCnt}" maxlength="10" placeholder="값 입력" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <valid:msg name="plChcCnt"/>
                    </div>
                </div>
            </div>          
        </div>
        <div class="form-group" id="file_${grpList.groupSn}_${iemList.artclSn}" style="display: none">
            <label for="lmtFileSz" class="control-label col-sm-2">
                <span class="mandatory">*</span> 파일용량
            </label>
            <div class="col-sm-10">
                <div class="row">
                    <div class="col-md-4">
                        <input type="text" name="lmtFileSz" id="lmtFileSz${grpList.groupSn}_${iemList.artclSn}" value="${dataVo.lmtFileSz}" class="form-control" placeholder="" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
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
                        <input type="text" name="prmsnFileExtnMttr" id="prmsnFileExtnMttr${grpList.groupSn}_${iemList.artclSn}" value="${dataVo.prmsnFileExtnMttr}" class="form-control" placeholder="" <c:if test="${chkymd eq -2}"> disabled</c:if>/>
                    </div>
                </div>
                <div class="row"> 
                    <div class="col-sm-12">
                        <div class="help-block op-validate">
                                <ul class="list-group">
                                    <li class="list-group-item"><span class="validate-msg">- 허용할 첨부파일 확장자를<strong>띄어쓰기 없이 쉼표 문자( ',' )</strong>로 구분하여 등록하세요. (ex : jpg,gif,xls).</span></li>
                                </ul>
                            </div>
                    </div>
                </div>
            </div>
        </div> 
                    
        <!-- 옵션 추가 div -->	 
        <div id="insertOptDummy_${grpList.groupSn}_${iemList.artclSn}" style="display: none">
            <div class="row block">
                <div class="col-md-8">
                    <input type="text" name="optCns" value="" class="form-control" placeholder="" />
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-danger" onclick="delOpt(this);">삭제</button>
                </div>
            </div>
        </div> 
	</c:forEach></div>
</div>
