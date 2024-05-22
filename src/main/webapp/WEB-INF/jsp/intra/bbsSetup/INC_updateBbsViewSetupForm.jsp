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
<%@ page import="zesinc.intra.bbsSetup.support.BbsSetupConstant" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판환경설정 관리</title>

<op:jsTag type="intra" items="opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>

<script type="text/javascript">
    //<![CDATA[

	$(".numberOnly").keyup(function(){
	    $(this).val( $(this).val().replace(/[^0-9]/gi,"") );
	});
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            $(this).ajaxSubmit({
                url: "ND_updateBbsViewSetup.do",
                type: "POST",
                dataType: "json",
                success: function(response){
                    try {
                        opJsonMsg(response);
                    } catch (e) {
                        // 시스템 오류 발생시 처리 구간
                        opErrorMsg(response, e);
                        return;
                    }
                }
            });
            return false;
        });

        // radio 버튼 선택 값 설정
        opChecked("bfrAftrDocIndctYn", "${dataVo.bfrAftrDocIndctYn}");
        opChecked("rcmdtnUseYn", "${dataVo.rcmdtnUseYn}");
        opChecked("dclrUseYn", "${dataVo.dclrUseYn}");
        opChecked("dgstfnUseYn", "${dataVo.dgstfnUseYn}");
        opChecked("opnnDocYn", "${dataVo.opnnDocYn}");
        opChecked("tagUseYn", "${dataVo.tagUseYn}");

    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var valid = true;
        return valid;
    };

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateBbsViewSetup.do" class="form-horizontal">
        <input type="hidden" id="seCdId" name="seCdId" value="<%= BbsSetupConstant.SECTION_CODE_VIEW %>" />
        <input type="hidden" id="bbsStngSn" name="bbsStngSn" value="${dataVo.bbsStngSn}" />
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="bfrAftrDocIndctYnY" class="control-label col-md-2">이전/다음 글보기 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="bfrAftrDocIndctYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="bfrAftrDocIndctYn" id="bfrAftrDocIndctYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="bfrAftrDocIndctYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="bfrAftrDocIndctYn" id="bfrAftrDocIndctYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 게시글 하단에 이전/다음글이동 기능을 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<%--
                <div class="form-group">
                    <label for="rcmdtnUseYnY" class="control-label col-md-2">추천 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="rcmdtnUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="rcmdtnUseYn" id="rcmdtnUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="rcmdtnUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="rcmdtnUseYn" id="rcmdtnUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 게시글의 추천 기능을 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dclrUseYnY" class="control-label col-md-2">신고 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="dclrUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="dclrUseYn" id="dclrUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="dclrUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="dclrUseYn" id="dclrUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 게시글의 신고 기능을 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dgstfnUseYnY" class="control-label col-md-2">만족도 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="dgstfnUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="dgstfnUseYn" id="dgstfnUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="dgstfnUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="dgstfnUseYn" id="dgstfnUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 게시글에 대한 사용자 만족도를 입력받고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
--%>
                <div class="form-group">
                    <label for="opnnDocYnY" class="control-label col-md-2">의견글 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="opnnDocYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="opnnDocYn" id="opnnDocYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="opnnDocYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="opnnDocYn" id="opnnDocYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 게시글의 의견글 기능을 이용하고자 한다면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<%--
                <div class="form-group">
                    <label for="tagUseYnY" class="control-label col-md-2">태그 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="tagUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="tagUseYn" id="tagUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="tagUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="tagUseYn" id="tagUseYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 게시물 태그를 사용하려면 사용으로 설정하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
--%>
                <div class="form-group">
                    <label for="inqCntLmtHrCnt" class="control-label col-md-2"> <span class="mandatory">*</span> 조회수 증가 시간</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-1">
                                <input type="text" name="inqCntLmtHrCnt" id="inqCntLmtHrCnt" value="${dataVo.inqCntLmtHrCnt}" class="form-control numberOnly" maxlength="3"/>
                            </div>
                            <div class="col-md-5">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">단위: 시간 (숫자, 최대 3자)</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">- 조회수/추천수/신고수 등 증가할 기준 시간을 지정합니다. 기준 시간이 지나야만 조회수가 증가합니다. (쿠키에 저장됨)</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="block clearfix">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-success">저장</button>
                        <button type="button" class="btn btn-primary" onclick="opList('BD_selectBbsSetupList.do');">목록</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>