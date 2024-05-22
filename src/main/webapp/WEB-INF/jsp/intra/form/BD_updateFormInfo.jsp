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
<title>폼 기본 관리</title>

<op:jsTag type="intra" items="opform, opvalidate, ui" />
<op:jsTag type="libs" items="tags, ckeditor, docs, timepicker" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/form/js/form.js"></script>

<script type="text/javascript">
    //<![CDATA[
    
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        
          opChecked("frstcmYn", "${dataVo.frstcmYn}", 'N');
          opChecked("wrtrInfoClctSttsNo", "${dataVo.wrtrInfoClctSttsNo}", '0');
          
          /*작성자 정보수집 설정에 따른 개인정보동의 에디터 출력 설정*/
          var chkWrtInfo = $("input[name='wrtrInfoClctSttsNo']:checked").val();
          
          if(chkWrtInfo == '0'){
              $("#prvcDiv").hide();
          } else {
              $("#prvcDiv").show();
          }
          console.log("chkWrtInfo"+chkWrtInfo)
          
          $("input[name=wrtrInfoClctSttsNo]").click(function() {
          console.log("this.value"+this.value)
          if (this.value == '0') {
                $("#prvcDiv").hide();
                CKEDITOR.instances.prvcClctAgreCn.setData("");
            } else {
                $("#prvcDiv").show();
            }
        }); 
          
          $("input[name=frstcmYn]").click(function() {
            if (this.value == 'Y') {
                $("#frstcmYnSetup").show();
                $("#lmtNope").prop("disabled", false);
            } else {
                $("#frstcmYnSetup").hide();
                $("#lmtNope").prop("disabled", true);
            }
        });
               
          /* 폼 수정 */
        $("#dataForm").submit(function() {
           
           var chkFrstcmYn = $("input[name='frstcmYn']:checked").val();
           var wrtrInfoClctSttsNo = $("input[name='wrtrInfoClctSttsNo']:checked").val();
           
           if(chkFrstcmYn == 'Y'){
              var target = $("#lmtNope");
              if(!target.notnullValidator() || !target.digitsValidator()){
                 opWarningMsg("선착순여부 체크 시 제한인원은 필수입력 항목입니다.(숫자만 입력 가능)");
                    target.focus();
                    return false;
              }
           }
            
           
           if(wrtrInfoClctSttsNo != '0'){
              target = $("#prvcClctAgreCn");
              if(!target.notnullValidator()){
                 opWarningMsg("작성자정보수집 시 개인정보처리방침은 필수입력 항목입니다.");
                    target.focus();
                    return false;
              }
           }
           
           if(validate){
              $.ajax({
                    type: "POST",
                    dataType: "json",
                    success : function(response) {
                       try {
                            opJsonMsg(response);
                        } catch (e) {
                            // 시스템 오류 발생시 처리 구간
                            opErrorMsg(response, e);
                            return;
                        }
                   }       
                 });  
               return validate();   
            };
            return false;  
       });
        
        // 시작시간과 종료시간 설정
        $("#endHr").timepicker({
            'timeFormat' : 'H:i'
        });
        $("#bgngHr").timepicker({
            'timeFormat' : 'H:i'
        });
        
    });
        
        
 
    //]]>
</script>
</head>
<body>
   <div id="tabs" class="tabbable page-tabs">
    <ul class="nav nav-tabs nav-justified">
        <li class="active"><a href="/intra/form/BD_updateFormInfo.do?q_formSn=${paramMap.q_formSn}">[STEP 1] 폼작성</a></li>
        <li><a href="/intra/form/BD_insertFormGroup.do?q_formSn=${paramMap.q_formSn}">[STEP 2] 그룹 • 항목작성</a></li>
        <li><a href="/intra/form/BD_selectFormResponseList.do?q_formSn=${paramMap.q_formSn}">[STEP 3] 응답관리</a></li>
    </ul>
     
    <div class="well">
        <blockquote> 
            <p><%-- ${dataVo.formSn} : --%> ${dataVo.formTtl}</p>
            
        </blockquote>
    </div>
</div>
    <div class="panel-body">
        <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateForm.do" class="form-horizontal">
            <input type="hidden" id="formSn" name="formSn" value="${dataVo.formSn}" />
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />
                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="siteSns" class="control-label col-md-2">
                                <span class="mandatory">*</span> 도메인
                            </label>
                            <div class="col-md-10">
                                <div class="row">
                                    <div class="col-md-6">
                                        <c:forEach items="${domainList}" var="domain" varStatus="status">
                                            <c:set var="isContains" value="N" />
                                            <c:set var="checked" value="" />
                                            <c:forEach items="${dataVo.domnList}" var="bdomain">
                                                <c:if test="${domain.siteSn eq bdomain.siteSn and isContains eq 'N'}">
                                                    <c:set var="checked" value="checked='checked'" />
                                                    <c:set var="isContains" value="Y" />
                                                </c:if>
                                            </c:forEach>
                                            <label for="siteSns${status.index}" class="checkbox-inline checkbox-primary">
                                                <input type="checkbox" name="siteSns" id="siteSns${status.index}" value="${domain.siteSn}" ${checked} class="styled" />
                                                    ${domain.siteExpln}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="siteSns" />
                                        <div class="help-block">
                                            <span class="text-info">선택된 사이트 모두에 적용됩니다.</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="formTtl" class="control-label col-md-2">
                                <span class="mandatory">*</span> 폼 제목
                            </label>
                            <div class="col-md-10">
                                <div class="row">
                                    <div class="col-md-6">
                                        <input type="text" name="formTtl" id="formTtl" value="${dataVo.formTtl}" maxlength="500" class="form-control" placeholder="폼 제목을 입력하세요." />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="formTtl" />
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                        <div class="form-group">
                            <label for="formExpln" class="control-label col-md-2">
                                <span class="mandatory"></span> 상세설명
                            </label>
                            <div class="col-md-10">
                                <div id="textareaBlock" class="block row">
                                    <div class="col-md-12">
                                        <textarea name="formExpln" id="formExpln" rows="15" cols="80" class="form-control">${dataVo.formExpln}</textarea>
                                            <script type="text/javascript">
                                                //<![CDATA[
                                                /* 공통 초기화 기능 */
                                                $(document).ready(function() {
                                                    /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
                                                    editor = $("#formExpln").ckeditor({
                                                        toolbar : toolbar_config.default_toolbar
                                                    }).editor;
                                                });
                                                //]]>
                                            </script>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="formExpln" />
                                    </div>
                                </div>
                            </div>
                        </div>    
                            
                        <div class="form-group">
                            <label for="formFile" class="control-label col-md-2"> 파일첨부</label>
                            <div class="col-md-10">
                                <div class="row">
                                    <div class="col-md-12">
                                        <op:fileUpload view="basic" name="formFile" count="3" size="500K" maxSize="4M" exts="jpg,jpeg,gif,png,pdf,xlsx,hwp,txt,doc,docx,ppt,bmp,xls,zip,alz,swf,flv,pptx,pps,ppsx,tif,wmv" fileList="${dataVo.fileList}" />
                                    </div>
                                </div>
                            </div>
                        </div>

						<div class="form-group">
							<label for="bgngYmdStr" class="control-label col-sm-2"> 
								<span class="mandatory">*</span> 응답기간
							</label>
							<div class="col-sm-9">
								<div class="row">
									<div class="col-sm-2">
										<input type="text" name="bgngYmdStr" id="bgngYmdStr" class="from-date form-control" value="<fmt:formatDate value="${dataVo.bgngYmd}" pattern="yyyy-MM-dd" />" maxlength="10" placeholder="시작일" />
									</div>
									<div class="col-md-2">
										<input type="text" class="to-date form-control" name="endYmdStr" id="endYmdStr" value="<fmt:formatDate value="${dataVo.endYmd}" pattern="yyyy-MM-dd" />" maxlength="10" placeholder="종료일" />
									</div>
									<label for="bgngHr" class="control-label col-sm-2">시간설정</label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="bgngHr" id="bgngHr" value="${dataVo.bgngHr}" maxlength="5" onkeydown="opDateAuto(this);" placeholder="시작시간 선택" />
									</div>
									<div class="col-sm-2">
										<input type="text" name="endHr" id="endHr" class="form-control" value="${dataVo.endHr}" maxlength="5" onkeydown="opDateAuto(this);" placeholder="종료시간 선택" />
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<valid:msg name="bgngYmdStr" />
									</div>
									<div class="col-sm-6 help-block">
										<span class="validate-msg">시간은 30분 단위로 설정합니다.</span>
									</div>
								</div>
							</div>
						</div>


                        <div class="form-group">
                            <label for="frstcmYN" class="control-label col-md-2">
                                <span class="mandatory"></span> 선착순여부
                            </label>
                            <div class="col-md-10">
                                <label for="frstcmYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="frstcmYn" id="frstcmYnY" value="Y" class="styled" />
                                        예
                                </label>
                                <label for="frstcmYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="frstcmYn" id="frstcmYnN" value="N" class="styled" />
                                        아니오
                                </label>
                                <div id="frstcmYnSetup" <c:if test="${dataVo.frstcmYn eq 'N'}">style="display:none;"</c:if>>
                                    <div class="row">
                                        <label for="" class="control-label col-sm-2"> 제한인원수</label>
                                        <div class="col-md-2">
                                            <input type="text" id="lmtNope" name="lmtNope" class="form-control" value="${dataVo.lmtNope}" placeholder="인원입력" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="frstcmYn" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pstgYn" class="control-label col-md-2">
                                <span class="mandatory"></span> 게시여부
                            </label>
                            <div class="col-md-10">
                                <label for="pstgYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="pstgYn" id="pstgYnY" value="Y" <c:if test="${dataVo.pstgYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                        예
                                </label>
                                <label for="pstgYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="pstgYn" id="pstgYnN" value="N" <c:if test="${dataVo.pstgYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                        아니오
                                </label>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="pstgYn" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="wrtrInfoClctSttsNo" class="control-label col-md-2">
                                <span class="mandatory"></span> 작성자정보수집설정
                            </label>
                            <div class="col-md-10">
                                <label for="wrtrInfoClctSttSNoN" class="radio-inline radio-primary">
                                    <input type="radio" name="wrtrInfoClctSttsNo" id="wrtrInfoClctSttSNoN" value="0" <c:if test="${dataVo.wrtrInfoClctSttsNo eq '0'}">checked="checked"</c:if> class="styled" />
                                            수집안함
                                </label>
                                <label for="wrtrInfoClctSttSNo1" class="radio-inline radio-primary">
                                        <input type="radio" name="wrtrInfoClctSttsNo" id="wrtrInfoClctSttSNo1" value="1" <c:if test="${dataVo.wrtrInfoClctSttsNo eq '1'}">checked="checked"</c:if> class="styled" />
                                            1단계(이름,이메일)
                                </label>
                                <label for="wrtrInfoClctSttSNo2" class="radio-inline radio-primary">
                                        <input type="radio" name="wrtrInfoClctSttsNo" id="wrtrInfoClctSttSNo2" value="2" <c:if test="${dataVo.wrtrInfoClctSttsNo eq '2'}">checked="checked"</c:if> class="styled" />
                                            2단계(이름,이메일,연락처)
                                </label>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="wrtrInfoClctSttsNo" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class=" callout callout-info fade in">
                                            <h5>작성자정보 수집설정에 따라 접수 중복 체크 기준이 다르게 적용됩니다.</h5>
                                            <dl style="margin: 500;">
                                                <dt class="text-info">수집안함 :</dt>
                                                <dd>작성자를 체크하지 않음</dd>
                                                <dt class="text-info">1단계 :</dt>
                                                <dd>이름,이메일 값을 이용하여 작성자를 체크</dd>
                                                <dt class="text-info">2단계 :</dt>
                                                <dd>이름,이메일 값,연락처 값을 이용하여 작성자를 체크</dd>
                                            </dl>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 

                        <div class="form-group" id="prvcDiv">
                            <label for="prvcClctAgreCn" class="control-label col-md-2">
                                <span class="mandatory"></span> 개인정보동의
                            </label>
                            <div class="col-md-10">
                                <div id="textareaBlock" class="block row">
                                    <div class="col-md-12">
                                        <textarea name="prvcClctAgreCn" id="prvcClctAgreCn" rows="15" cols="80" class="form-control">${dataVo.prvcClctAgreCn}</textarea>
                                            <script type="text/javascript">
                                                //<![CDATA[
                                                /* 공통 초기화 기능 */
                                                $(document).ready(function() {
                                                    /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
                                                    editor = $("#prvcClctAgreCn").ckeditor({
                                                        toolbar : toolbar_config.default_toolbar
                                                    }).editor;
                                                });
                                                //]]>
                                            </script>
                                    </div>
                                </div>
                                <div class="help-block">
                                    <span class="text-info">* 1,2단계 선택 시 개인정보동의 입력은 필수입니다.</span>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <valid:msg name="prvcClctAgreCn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 버튼 -->
                <div class="row">
                    <div class="col-md-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success">저장</button>
                            <button type="button" class="btn btn-primary" onclick="opList('BD_selectFormList.do');">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
        </form> 
    </div>
</body>
</html>