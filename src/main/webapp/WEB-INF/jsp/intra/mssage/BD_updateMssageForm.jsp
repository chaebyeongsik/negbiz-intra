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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>메시지 관리</title>

    <op:jsTag type="intra" items="opform" />
    <op:jsTag type="intra" items="opvalidate" />

    <!-- 기능별 스크립트 삽입 부 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mssage/js/mssage.js"></script>

    <script type="text/javascript">
        //<![CDATA[

        /* 공통 초기화 기능 */
        $(document).ready(function() {
            /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
            <valid:script type="msgbox" />
            $("#dataForm").submit(function() {
                return validate();
            });
        });

        /*
         * 사용자 정의 추가 검증(필요한 검증 코드 삽입)
         * 기본 검증후 메소드가 자동 호출된다.
         */
        var customValidate = function() {
            return true;
        };

        //]]>
    </script>
</head>
<body>
<div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
<form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateMssage.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
<div class="panel panel-default">
            <div class="panel-body">
<div class="form-group">
                    <label for="regSn" class="control-label col-md-2">순번 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="regSn" id="regSn" value="${dataVo.regSn}" class="form-control" placeholder="순번" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="regSn" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="picId" class="control-label col-md-2">담당자ID <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="picId" id="picId" value="${dataVo.picId}" class="form-control" placeholder="담당자ID" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="picId" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="msgCn" class="control-label col-md-2">내용 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="msgCn" id="msgCn" value="${dataVo.msgCn}" class="form-control" placeholder="내용" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="msgCn" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="sndptyId" class="control-label col-md-2">송신자ID </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="sndptyId" id="sndptyId" value="${dataVo.sndptyId}" class="form-control" placeholder="송신자ID" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="sndptyId" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="rcptnDt" class="control-label col-md-2">수신일시 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="rcptnDt" id="rcptnDt" value="${dataVo.rcptnDt}" class="form-control" placeholder="수신일시" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="rcptnDt" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="rcvrDelYnY" class="control-label col-md-2">수신자삭제여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="rcvrDelYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="rcvrDelYn" id="rcvrDelYnY" value="Y" <c:if test="${dataVo.rcvrDelYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="radioN" class="radio-inline radio-primary">
                                    <input type="radio" name="rcvrDelYn" id="rcvrDelYnN" value="N" <c:if test="${dataVo.rcvrDelYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="rcvrDelYn" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="sndptyDelYnY" class="control-label col-md-2">송신자삭제여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="sndptyDelYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="sndptyDelYn" id="sndptyDelYnY" value="Y" <c:if test="${dataVo.sndptyDelYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="radioN" class="radio-inline radio-primary">
                                    <input type="radio" name="sndptyDelYn" id="sndptyDelYnN" value="N" <c:if test="${dataVo.sndptyDelYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="sndptyDelYn" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="dsptchYnY" class="control-label col-md-2">송신여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="dsptchYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="dsptchYn" id="dsptchYnY" value="Y" <c:if test="${dataVo.dsptchYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="radioN" class="radio-inline radio-primary">
                                    <input type="radio" name="dsptchYn" id="dsptchYnN" value="N" <c:if test="${dataVo.dsptchYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="dsptchYn" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="fileSn" class="control-label col-md-2">파일순번 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="fileSn" id="fileSn" value="${dataVo.fileSn}" class="form-control" placeholder="파일순번" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="fileSn" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="regDt" class="control-label col-md-2">등록일시 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="regDt" id="regDt" value="${dataVo.regDt}" class="form-control" placeholder="등록일시" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="regDt" />
                            </div>
                        </div>
                    </div>
                </div>
<div class="form-group">
                    <label for="siteSn" class="control-label col-md-2"> 텍스트셈플 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <p class="form-control-static">텍스트</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                            <div class="help-block op-validate">
                                <ul class="list-group">
                                    <li class="list-group-item"><span class="validate-msg">별도로 문구를 작성하는 경우 참조</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="checkboxY" class="control-label col-md-2">체크박스예제</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="checkboxY" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="checkbox" id="checkboxY" value="Y" class="styled" />
                                    서울
                                </label>
                                <label for="checkboxY" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="checkbox" id="checkboxY" value="Y" class="styled" />
                                    경기
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                소스상의 name 수정 필요
                                <valid:msg name="ntcPstYn" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="radioY" class="control-label col-md-2">라디오버튼예제</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <label for="radioY" class="radio-inline radio-primary">
                                    <input type="radio" name="radio" id="radioY" value="Y" class="styled" />
                                    공개
                                </label>
                                <label for="radioN"  class="radio-inline radio-primary">
                                    <input type="radio" name="radio" id="radioN" value="N" class="styled" />
                                    비공개
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                소스상의 name 수정 필요
                                <valid:msg name="rlsYn" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="select" class="control-label col-md-2">Select 예제</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <select name="select" id="select" class="select">
                                    <option value="">조건선택</option>
                                    <option value="1001">제목</option>
                                    <option value="1002">내용</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                소스상의 name 수정 필요
                                <valid:msg name="rlsYn" />
                            </div>
                        </div>
                    </div>
                </div>
</div>
        </div>
<!-- 버튼 -->
    <div class="block clearfix">
        <div class="col-md-12 btn-group">
            <div class="pull-right">
                <button type="submit" class="btn btn-success">저장</button>
                <button type="reset" class="btn btn-default">취소</button>

                <button type="submit" class="btn btn-success" onclick="opInsertForm('BD_insertMssageForm.do');">등록</button>
                <button type="button" class="btn btn-success" onclick="opUpdateForm('BD_updateMssageForm.do');">수정</button>
                <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteMssage.do');">삭제</button>
                <button type="button" class="btn btn-primary" onclick="opList('BD_selectMssageList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</form>
</body>
</html>