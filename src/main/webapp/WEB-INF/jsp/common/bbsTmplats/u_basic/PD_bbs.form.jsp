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
<%@ taglib uri="http://www.openworks.kr/jsp/bbs" prefix="bbs"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판 관리</title>

<op:jsTag type="user" items="opform, opvalidate" />
<op:jsTag type="libs" items="form, validate, ckeditor" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/user/bbs/js/bbs.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        // radio 버튼 선택 값 설정
        opChecked("rlsYn", "Y");
        opChecked("cprgtUseYn", "N");

    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    //]]>
</script>
</head>
<body>
    <%-- 게시판 항목을 사용이 용이하게 변수를 할당하여 가져옴 --%>
    <c:set var="column" value="${bbsConfigVo.bbsItemMap}" />

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 게시판 등록
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="${ACTION}" class="form-horizontal">
                <input type="hidden" id="bbsSn" name="bbsSn" value="${paramMap.q_bbsSn}" />
                <input type="hidden" id="bbsDocNo" name="bbsDocNo" value="${dataVo.bbsDocNo}" />
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />
                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">

                        <c:if test="${column.notice.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:notice type="label" />
                            <div class="col-sm-10">
                                <bbs:notice type="form" obj="${dataVo}" />
                                <bbs:notice type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.ntcBgngDt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:ntcBgngDt type="label" />
                            <div class="col-sm-10">
                                <bbs:ntcBgngDt type="form" obj="${dataVo}" />
                                <bbs:ntcBgngDt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.ntcEndDt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:ntcEndDt type="label" />
                            <div class="col-sm-10">
                                <bbs:ntcEndDt type="form" obj="${dataVo}" />
                                <bbs:ntcEndDt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.ttl.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:ttl type="label" />
                            <div class="col-sm-10">
                                <bbs:ttl type="form" obj="${dataVo}" />
                                <bbs:ttl type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.docContsCn.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:docContsCn type="label" />
                            <div class="col-sm-10">
                                <bbs:docContsCn type="form" obj="${dataVo}" />
                                <bbs:docContsCn type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.clsfNo.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:clsfNo type="label" />
                            <div class="col-sm-10">
                                <bbs:clsfNo type="form" obj="${dataVo}" />
                                <bbs:clsfNo type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.lwrkClsfSn.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:lwrkClsfSn type="label" />
                            <div class="col-sm-10">
                                <bbs:lwrkClsfSn type="form" obj="${dataVo}" />
                                <bbs:lwrkClsfSn type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.mainCn.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:mainCn type="label" />
                            <div class="col-sm-10">
                                <bbs:mainCn type="form" obj="${dataVo}" />
                                <bbs:mainCn type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.ansDtlCn.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:ansDtlCn type="label" />
                            <div class="col-sm-10">
                                <bbs:ansDtlCn type="form" obj="${dataVo}" />
                                <bbs:ansDtlCn type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.ansDt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:ansDt type="label" />
                            <div class="col-sm-10">
                                <bbs:ansDt type="form" obj="${dataVo}" />
                                <bbs:ansDt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.file.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:file type="label" />
                            <div class="col-sm-10">
                                <bbs:file type="form" obj="${dataVo}" />
                                <bbs:file type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.deptCdId.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:deptCdId type="label" />
                            <div class="col-sm-10">
                                <bbs:deptCdId type="form" obj="${dataVo}" />
                                <bbs:deptCdId type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.deptNm.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:deptNm type="label" />
                            <div class="col-sm-10">
                                <bbs:deptNm type="form" obj="${dataVo}" />
                                <bbs:deptNm type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.picId.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:picId type="label" />
                            <div class="col-sm-10">
                                <bbs:picId type="form" obj="${dataVo}" />
                                <bbs:picId type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.picNm.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:picNm type="label" />
                            <div class="col-sm-10">
                                <bbs:picNm type="form" obj="${dataVo}" />
                                <bbs:picNm type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.cpyrht.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:cpyrht type="label" />
                            <div class="col-sm-10">
                                <bbs:cpyrht type="form" obj="${dataVo}" />
                                <bbs:cpyrht type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.tagNms.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:tagNms type="label" />
                            <div class="col-sm-10">
                                <bbs:tagNms type="form" obj="${dataVo}" />
                                <bbs:tagNms type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.inqCnt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:inqCnt type="label" />
                            <div class="col-sm-10">
                                <bbs:inqCnt type="form" obj="${dataVo}" />
                                <bbs:inqCnt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.dclrCnt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:dclrCnt type="label" />
                            <div class="col-sm-10">
                                <bbs:dclrCnt type="form" obj="${dataVo}" />
                                <bbs:dclrCnt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.rcmdtnCnt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:rcmdtnCnt type="label" />
                            <div class="col-sm-10">
                                <bbs:rcmdtnCnt type="form" obj="${dataVo}" />
                                <bbs:rcmdtnCnt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.stsfdgEvl.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:stsfdgEvl type="label" />
                            <div class="col-sm-10">
                                <bbs:stsfdgEvl type="form" obj="${dataVo}" />
                                <bbs:stsfdgEvl type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.cntnBrwsrNm.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:cntnBrwsrNm type="label" />
                            <div class="col-sm-10">
                                <bbs:cntnBrwsrNm type="form" obj="${dataVo}" />
                                <bbs:cntnBrwsrNm type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.ipAddr.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:ipAddr type="label" />
                            <div class="col-sm-10">
                                <bbs:ipAddr type="form" obj="${dataVo}" />
                                <bbs:ipAddr type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.userIdntfNm.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:userIdntfNm type="label" />
                            <div class="col-sm-10">
                                <bbs:userIdntfNm type="form" obj="${dataVo}" />
                                <bbs:userIdntfNm type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.rgtrId.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:rgtrId type="label" />
                            <div class="col-sm-10">
                                <bbs:rgtrId type="form" obj="${dataVo}" />
                                <bbs:rgtrId type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.rgtrNm.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:rgtrNm type="label" />
                            <div class="col-sm-10">
                                <bbs:rgtrNm type="form" obj="${dataVo}" />
                                <bbs:rgtrNm type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.regDt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:regDt type="label" />
                            <div class="col-sm-10">
                                <bbs:regDt type="form" obj="${dataVo}" />
                                <bbs:regDt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.pswd.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:pswd type="label" />
                            <div class="col-sm-10">
                                <bbs:pswd type="form" obj="${dataVo}" />
                                <bbs:pswd type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.mdfrId.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:mdfrId type="label" />
                            <div class="col-sm-10">
                                <bbs:mdfrId type="form" obj="${dataVo}" />
                                <bbs:mdfrId type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.updtDt.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:updtDt type="label" />
                            <div class="col-sm-10">
                                <bbs:updtDt type="form" obj="${dataVo}" />
                                <bbs:updtDt type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn1.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn1 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn1 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn1 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn2.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn2 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn2 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn2 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn3.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn3 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn3 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn3 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn4.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn4 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn4 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn4 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn5.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn5 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn5 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn5 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn6.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn6 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn6 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn6 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn7.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn7 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn7 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn7 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn8.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn8 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn8 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn8 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn9.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn9 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn9 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn9 type="desc" />
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${column.flctnColCn10.inptIndctYn eq 'Y'}" >
                        <div class="form-group">
                            <bbs:flctnColCn10 type="label" />
                            <div class="col-sm-10">
                                <bbs:flctnColCn10 type="form" obj="${dataVo}" />
                                <bbs:flctnColCn10 type="desc" />
                            </div>
                        </div>
                        </c:if>

                    </div>
                </div>
                <!-- 버튼 -->
                <div class="row">
                    <div class="col-sm-12 btn-group">
                        <div class="pull-right">
                            <bbs:button type="form" obj="${dataVo}" />
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>