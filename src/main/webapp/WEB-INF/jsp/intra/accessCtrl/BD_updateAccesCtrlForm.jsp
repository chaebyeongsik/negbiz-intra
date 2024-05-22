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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>접근제어 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/accessCtrl/js/accessCtrl.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        $("#prmsnTypeNm").change(function() {
            var val = $(this).val();
            opChangePermTy(val);
        });
        opChangePermTy("${dataVo.prmsnTypeNm}");

        opChecked("useYn", "${dataVo.useYn}", "Y");
        opSelected("prmsnTypeNm", "${dataVo.prmsnTypeNm}", "IP");
    });

    //]]>
</script>
</head>
<body>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 접근제어 등록
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" action="ND_updateAccesCtrl.do" class="form-horizontal">
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />
                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="prmsnTypeNm" class="control-label col-xs-2">
                                <span class="mandatory">*</span> 허용유형
                            </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-2">
                                        <select name="prmsnTypeNm" id="prmsnTypeNm" class="select">
                                            <option value="IP">IP허용</option>
                                            <option value="ID">ID허용</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="prmsnTypeNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="prmsnBgngIp" class="control-label col-xs-2"> 허용IP</label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <input type="text" name="prmsnBgngIp" id="prmsnBgngIp" value="${dataVo.prmsnBgngIp}" maxlength="20" class="form-control" />
                                    </div>
                                    <div class="col-xs-1">~</div>
                                    <div class="col-xs-4">
                                        <input type="text" name="prmsnEndIp" id="prmsnEndIp" value="${dataVo.prmsnEndIp}" maxlength="20" class="form-control" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 help-block op-validate">
                                        <ul class="list-group">
                                            <li class="list-group-item"><span class="validate-msg">IP 주소를 입력합니다. 예 : <code>127.0.0.12</code></span></li>
                                            <li class="list-group-item"><span class="text-info">대역폭으로 지정하는 경우 해당 자릿수까지 입력합니다 예: <code>127.0.0</code></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tmpPicNm" class="control-label col-xs-2"> 허용ID </label>
                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="row">
                                            <div class="col-xs-2">
                                                <input type="text" name="tmpPicNm" id="tmpPicNm" value="" class="form-control" />
                                            </div>
                                            <div class="col-xs-10">
                                                <div class="col-xs-3">
                                                    <p class="form-control-static" id="chrgDeptNm">
                                                        <c:if test="${not empty dataVo.permPicNm}">${dataVo.permChrgDeptNm}&nbsp;&nbsp;/&nbsp;&nbsp;${dataVo.permPicNm}</c:if>
                                                    </p>
                                                </div>
                                                <div class="col-xs-3" id="toggleButton">
                                                    <c:if test="${not empty dataVo.prmsnId}">
                                                        <button type="button" class="btn btn-danger" onclick="deleteCharger();">삭제</button>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="hidden" name="prmsnId" id="prmsnId" value="${dataVo.prmsnId}" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="help-block">
                                            <span class="text-info">담당자 이름을 입력한 후 선택하세요. &nbsp;(<span class="text-danger">바로 Enter키 누르지 마세요</span>)
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="useYnY" class="control-label col-xs-2">
                                <span class="mandatory">*</span> 사용여부
                            </label>
                            <div class="col-xs-10">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <valid:msg name="useYn" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty dataVo.rgtrId}">
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
                        </c:if>
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
                    <div class="col-xs-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success">저장</button>
                            <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteAccesCtrl.do');">삭제</button>
                            <button type="button" class="btn btn-primary" onclick="opList('BD_selectAccesCtrlList.do');">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>