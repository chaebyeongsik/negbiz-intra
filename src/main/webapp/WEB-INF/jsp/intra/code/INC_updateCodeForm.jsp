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
<title>코드 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/code/js/code.js"></script>
-->
<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(validate()) {
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });

        // 라디오 버튼 자동 선택
        opChecked("useYn", "${dataVo.useYn}", "Y");
        opChecked("pbadmsStdCdYn", "${dataVo.pbadmsStdCdYn}", "Y");

        // 코드 등록/수정폼 오픈
        $("#cdChcIdModal").opmodal({
            width : 950
        });

    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
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
    <form name="dataForm" id="dataForm" method="post" action="ND_updateCode.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="upCdId" class="control-label col-sm-2">상위코드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <p class="form-control-static">
                                    <strong> <c:choose>
                                            <c:when test="${dataVo.upCdId != 'dummy'}">${dataVo.upCdIdNm} <code>${dataVo.upCdId}</code>
                                            </c:when>
                                            <c:otherwise>최상위코드</c:otherwise>
                                        </c:choose>
                                    </strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cdId" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 코드
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <code>${dataVo.cdId}</code>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <blockquote>
                                    <p>
                                        <strong>아래 예에서 표기되지 않은 추가 속성들은 태그 도움말(<code>Eclipse 개발 툴의 마우스 오버 툴팁</code>)을 참조한다.
                                        </strong>
                                    </p>
                                    <p>
                                        코드명 사용 예 :<br />
                                        <code>&lt;op:cdId hghrkCdId="${dataVo.hghrkCdId}" cdId="${dataVo.cdId}"/&gt;</code>
                                    </p>
                                    <c:if test="${dataVo.isFolder}">
                                        <p>
                                            체크박스 사용 예 :<br />
                                            <code>&lt;op:cdId type="checkbox" hghrkCdId="${dataVo.hghrkCdId}" cdId="${dataVo.cdId}" id="test" values="기본값들"/&gt;</code>
                                        </p>
                                        <p>
                                            라디오 사용 예 :<br />
                                            <code>&lt;op:cdId type="radio" hghrkCdId="${dataVo.hghrkCdId}" cdId="${dataVo.cdId}" id="test" values="기본값"/&gt;</code>
                                        </p>
                                        <p>
                                            실렉트 사용 예 : (size 속성이 2 이상인 경우 복수선택으로 자동전환됨.)<br />
                                            <code>&lt;op:cdId type="select" hghrkCdId="${dataVo.hghrkCdId}" cdId="${dataVo.cdId}" size="2" id="test" values="기본값들"/&gt;</code>
                                        </p>
                                    </c:if>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cdNm" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 코드명
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="cdNm" id="cdNm" value="${dataVo.cdNm}" class="form-control" placeholder="코드" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="cdNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <%--
                <div class="form-group">
                    <label for="pbadmsStdCdYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 행정표준코드여부
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="pbadmsStdCdYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="pbadmsStdCdYn" id="pbadmsStdCdYnY" value="Y" checked="checked" class="styled" />
                                    예
                                </label>
                                <label for="pbadmsStdCdYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="pbadmsStdCdYn" id="pbadmsStdCdYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="pbadmsStdCdYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="pbadmsStdCdId" class="control-label col-sm-2">행정표준코드</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="pbadmsStdCdId" id="pbadmsStdCdId" value="${dataVo.pbadmsStdCdId}" class="form-control" placeholder="행정표준코드" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="pbadmsStdCdId" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mtlngCdNm" class="control-label col-sm-2">다국어코드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="mtlngCdNm" id="mtlngCdNm" value="${dataVo.mtlngCdNm}" class="form-control" placeholder="다국어코드" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="mtlngCdNm" />
                            </div>
                        </div>
                    </div>
                </div>
                 --%>
                <div class="form-group">
                    <label for="cdExpln" class="control-label col-sm-2">코드설명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="cdExpln" id="cdExpln" rows="5" cols="80" class="form-control" placeholder="코드설명">${dataVo.cdExpln}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="cdExpln" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYnY" class="control-label col-sm-2">
                        <span class="mandatory">*</span> 사용여부
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="useYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rgtrId" class="control-label col-sm-2">등록정보</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <strong>${dataVo.rgtrNm} (${dataVo.rgtrId}) <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty dataVo.mdfrId}">
                    <div class="form-group">
                        <label class="control-label col-sm-2">수정정보</label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-sm-6">
                                    <p class="form-control-static">
                                        <strong>${dataVo.updusrNm} (${dataVo.mdfrId}) <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" /></strong>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <c:if test="${dataVo.isFolder}">
                    <div class="pull-left">
                        <a href="PD_selectCodeChoiceList.do?q_hghrkCdId=${paramMap.q_hghrkCdId}&amp;q_cdId=${paramMap.q_cdId}" class="btn btn-info" id="cdChcIdModal">코드선택편집</a>
                    </div>
                </c:if>
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <c:if test="${!dataVo.isFolder}">
                        <button type="button" class="btn btn-danger" onclick="opCodeDelete('ND_deleteCode.do');">삭제</button>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>