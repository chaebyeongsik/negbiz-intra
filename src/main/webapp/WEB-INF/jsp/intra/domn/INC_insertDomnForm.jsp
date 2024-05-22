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
<title>도메인 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/domn/js/domn.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        // 라디오 버튼 자동 선택
        opChecked("useYn", "Y");
        opChecked("httpsYn", "N");
        opChecked("actlStngYn", "N");
    });

    /*
     * 사용자 정의 추가 검증(필요한 검증 코드 삽입)
     * 기본 검증후 메소드가 자동 호출된다.
     */
    var customValidate = function() {
        var sitePathNm = $("#sitePathNm").val();
        sitePathNm = $.trim(sitePathNm);
        if(sitePathNm == "") {
            opWarningMsg("컨텍스트를 입력해야 합니다. 기본값은 / 입니다.");
            $("#sitePathNm").val(sitePathNm);
            $("#sitePathNm").focus();
            return false;
        }

        var firstChar = sitePathNm.substring(0, 1);
        if(firstChar != "/") {
            opWarningMsg("컨텍스트는 / 로 시작되어야 합니다.");
            $("#sitePathNm").val(sitePathNm);
            $("#sitePathNm").focus();
            return false;
        }

        var nextSlash = sitePathNm.indexOf("/", 1);
        if(nextSlash > -1) {
            opWarningMsg("컨텍스트의 / 문자는 첫자리 이외에는 사용할 수 없습니다.");
            $("#sitePathNm").val(sitePathNm);
            $("#sitePathNm").focus();
            return false;
        }

        return true;
    };

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_insertDomn.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="siteExpln" class="control-label col-md-2">
                        <span class="mandatory">*</span> 도메인설명
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-5">
                                <input type="text" name="siteExpln" id="siteExpln" value="" class="form-control" placeholder="홈페이지 한글이름" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="siteExpln" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="siteNm" class="control-label col-md-2">
                        <span class="mandatory">*</span> 도메인명
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" name="siteNm" id="siteNm" value="" class="form-control" placeholder="www.example.com" />
                            </div>
                            <div class="col-md-3">
                                <div class="row">
                                    <label for="portSn" class="control-label col-md-5">
                                        <span class="mandatory">*</span> 포트
                                    </label>
                                    <div class="col-md-7">
                                        <input type="text" name="portSn" id="portSn" value="" class="form-control" placeholder="80" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="row">
                                    <label for="sitePathNm" class="control-label col-md-4">
                                        <span class="mandatory">*</span> 컨텍스트
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" name="sitePathNm" id="sitePathNm" value="" class="form-control" placeholder="/content" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">고유한 도메인명을 입력하세요.</span></li>
                                        <li class="list-group-item"><span class="validate-msg">서버의 포트번호를 입력하세요.</span></li>
                                        <li class="list-group-item"><span class="validate-msg">서버에서 컨텍스트 설정을 사용한다면 동일한 컨텍스트를 입력해야 합니다.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="httpsYnN" class="control-label col-md-2">
                        <span class="mandatory">*</span> 보안프로토콜여부
                    </label>
                    <div class="col-md-4">
                        <label for="httpsYnY" class="radio-inline radio-primary">
                            <input type="radio" name="httpsYn" id="httpsYnY" value="Y" class="styled" />
                            예
                        </label>
                        <label for="radioN" class="radio-inline radio-primary">
                            <input type="radio" name="httpsYn" id="httpsYnN" value="N" class="styled" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg"><code>https</code> 를 사용하는 경우 '예' 를 선택하세요.</span></li>
                                        <li class="list-group-item">※ 미사용 중인 경우 예를 선택시 <span class="text-danger">오류가 발생</span>됩니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <label for="scrtyPortSn" class="control-label col-md-2"> 보안포트번호 </label>
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" name="scrtyPortSn" id="scrtyPortSn" value="" class="form-control" placeholder="443" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item">※ 위 포트와 동일한 경우 <span class="text-danger">입력하지 않거나 동일하게 입력</span>합니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="actlStngYnN" class="control-label col-md-2">
                        <span class="mandatory">*</span> 실컨텍스트여부
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="actlStngYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="actlStngYn" id="actlStngYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="actlStngYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="actlStngYn" id="actlStngYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">WAS 서버설정을 통한 컨텍스의 경우 '예' 를 선택하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="srvrIpAddr" class="control-label col-md-2"> 서버 그룹 </label>
                    <div class="col-md-10">
                        <div id="domainGroupDiv" class="row">
                            <div class="col-md-12">
                                <button type="button" class="btn btn-success btn-xs" onclick="addDomainGroup();">추가</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">IP주소와 포트번호를 입력하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="langCdId" class="control-label col-md-2">
                        <span class="mandatory">*</span> 언어코드
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-5">
                                <op:cdId id="langCdId" type="select" hghrkCdId="localeCode" cdId="localeCode" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="langCdId" />
                                <div class="help-block">
                                    <span class="text-info">언어가 없는경우 <code>공통코드관리</code>에서 해당국가 언어의 사용여부를 사용으로 변경하세요.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<%--
                <div class="form-group">
                    <label for="tmpPicNm" class="control-label col-md-2"> 담당자 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-3">
                                        <input type="text" name="tmpPicNm" id="tmpPicNm" value="" class="form-control" />
                                    </div>
                                    <div class="col-md-9">
                                        <div class="col-md-8">
                                            <p class="form-control-static" id="chrgDeptNm"></p>
                                        </div>
                                        <div class="col-md-4" id="toggleButton" style="display: none;">
                                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteCharger();">삭제</button>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="tkcgDeptCdId" id="tkcgDeptCdId" value="" />
                                <input type="hidden" name="picId" id="picId" value="" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block">
                                    <span class="text-info">담당자 이름을 입력한 후 선택하세요. &nbsp;(<span class="text-danger">바로 Enter키 누르지 마세요</span>)
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
--%>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-md-2">
                        <span class="mandatory">*</span> 사용여부
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="radioN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="useYn" />
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
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

    <div id="domainGroupDummy" style="display: none">
        <div class="row">
            <div class="col-md-5">
                <div class="row">
                    <label for="srvrIpAddr" class="control-label col-md-4">
                        <span class="mandatory">*</span> IP주소
                    </label>
                    <div class="col-md-8">
                        <input type="text" name="srvrIpAddr" id="srvrIpAddr" value="" class="form-control" placeholder="예:1.2.3.4" />
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="row">
                    <label for="groupPortSn" class="control-label col-md-5">
                        <span class="mandatory">*</span> 포트
                    </label>
                    <div class="col-md-7">
                        <input type="text" name="groupPortSn" id="groupPortSn" value="80" class="form-control" placeholder="80" />
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-danger" onclick="delDomainGroup(this);">삭제</button>
            </div>
        </div>
    </div>

</body>
</html>