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
<title>부서관리</title>

<op:jsTag type="intra" items="opvalidate" />

<script type="text/javascript">
    //<![CDATA[

    /* 유효성 검증 BEAN Annotation  기반 자동 설정 */
    <valid:script type="msgbox" />
    /* 공통 초기화 */
    $(document).ready(function() {

        /** 부서코드 중복체크 */
        $("input[name=deptCdId]").bind("keyup", function() {
            var deptCdId = $("#deptCdId").val();
            if(deptCdId) {
                //기존부서코드가 아닌경우 중복체크
                if ($("#beforeDeptCdId").val() != $("#deptCdId").val()) {
                    $.ajax({
                        url : "<c:url value="/intra/dept/ND_selectDplctChckDept.do" />",
                        type : "GET",
                        dataType : "json",
                        data : {
                            deptCdId : deptCdId,
                            beforeDeptCdId : $("#beforeDeptCdId").val(),
                            q_date : new Date().toString()
                        },
                        success : function(data) {
                            if (data == 0) {
                                $("#dplctChckCode").html("<p class=\"text-info form-control-static\">사용 가능한 코드입니다.</p>");
                            } else {
                                $("#dplctChckCode").html("<p class=\"text-danger form-control-static\">중복된 코드입니다.</p>");
                            }
                        }
                    });
                } else {
                    $("#dplctChckCode").html("");
                }
            }
        });

        var nodePath = Optree.getNodePath('', '${dataVo.deptCdId}');
        nodePath = nodePath.substring(0, nodePath.lastIndexOf('&gt;'));
        $("#upperDeptNm").html(nodePath);
    });

    /** 부서 수정 */
    var opUpdateDept = function() {
        if (validate()) {
            $.ajax({
                url : "<c:url value="/intra/dept/ND_updateDept.do" />",
                type : "POST",
                data : $("#dataForm").serialize(),
                dataType : "json",
                success : function(response) {
                    opJsonMsg(response);
                    // 메뉴명 변경(트리명)
                    parent.Optree.setTitle($("#deptNm").val());
                    // 사용/미사용 여부에 해당하는 노드 색깔 변경
                    parent.Optree.setUseYn($("input[name=useYn]:checked").val());
                    // 업데이트폼 초기화
                    parent.treeView(event, {
                            node : {
                                key : $("#deptCdId").val(),
                                parent : { key : $("#upDeptCdId").val()}
                            }
                        },
                        {
                            CONT_PAN_ID : "treeCont",
                            NODE_NM : "q_deptCdId",
                            VIEW_URL : "/intra/dept/INC_updateDeptForm.do"
                        }
                    );
                },
                error : function(response) {
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        }
    }
    
    /** 부서 삭제 */
    var opDeleteDept = function() {
        if (confirm("정말 삭제 하시겠습니까?")) {
            $.ajax({
                url : "<c:url value="/intra/dept/ND_deleteDept.do" />",
                type : "POST",
                data : { deptCdId : "${dataVo.deptCdId}"},
                dataType : "json",
                success : function(response) {
                    opJsonMsg(response);
                    parent.opNodeDelete("${dataVo.deptCdId}");
                },
                error : function(response) {
                    opSysErrorMsg(response.responseText);
                    return;
                }
            });
        }
    };

    
    //]]>
</script>
</head>
<body>
        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateDept.do" class="form-horizontal">
            <input type="hidden" id="beforeDeptCdId" name="beforeDeptCdId" value="${dataVo.deptCdId}" />
            <input type="hidden" id="sortSn" name="sortSn" value="${dataVo.sortSn}" />

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="upDeptCdId" class="control-label col-md-2">상위부서명 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-6">
                                <p class="form-control-static">
                                    <span id="upperDeptNm"></span>
                                    <input type="hidden" name="upDeptCdId" id="upDeptCdId" value="${dataVo.upDeptCdId}" class="form-control input-sm" />
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="deptNm" class="control-label col-md-2"> 부서명 <span class="mandatory">*</span></label>
                        <div class="col-sm-10">
                            <div class="col-sm-6">
                                <input type="text" name="deptNm" id="deptNm" value="${dataVo.deptNm}" class="form-control input-sm"  placeholder="부서명을 입력해주세요" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="deptCdId" class="control-label col-md-2"> 부서코드 <span class="mandatory">*</span></label>
                        <div class="col-sm-10">
                            <div class="col-sm-6">
                                <c:choose>
                                    <c:when test="${dataVo.hasChild}">
                                        <p class="form-control-static">
                                            <code>${dataVo.deptCdId}</code>
                                        </p>
                                        <input type="hidden" name="deptCdId" id="deptCdId" value="${dataVo.deptCdId}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="deptCdId" id="deptCdId" value="${dataVo.deptCdId}" class="form-control input-sm" placeholder="부서코드를 입력해주세요" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-sm-6" id="dplctChckCode"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rgnTelno" class="control-label col-sm-2">전화번호 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-2">
                                <input type="text" name="rgnTelno" id="rgnTelno" value="${dataVo.rgnTelno}" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="telofcTelno" id="telofcTelno" value="${dataVo.telofcTelno}" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="indivTelno" id="indivTelno" value="${dataVo.indivTelno}" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rgnFxno" class="control-label col-sm-2">팩스번호 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-2">
                                <input type="text" name="rgnFxno" id="rgnFxno" value="${dataVo.rgnFxno}" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="telofcFxno" id="telofcFxno" value="${dataVo.telofcFxno}" class="form-control input-sm" />
                            </div>
                            <div class="col-sm-2">
                                <input type="text" name="indivFxno" id="indivFxno" value="${dataVo.indivFxno}" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tkcgTaskNm" class="control-label col-md-2">주요업무 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-10">
                                <textarea name="tkcgTaskNm" id="tkcgTaskNm" rows="5" class="form-control input-sm" placeholder="주요업무을 입력해주세요">${dataVo.tkcgTaskNm}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="useYnY" class="control-label col-md-2">사용여부 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-6">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" class="styled" <c:if test="${empty dataVo.useYn or dataVo.useYn eq 'Y'}"> checked="checked"</c:if> />
                                    사용
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" class="styled" <c:if test="${dataVo.useYn eq 'N'}"> checked="checked"</c:if> />
                                    미사용
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="regDt" class="control-label col-md-2">등록일시 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-6">
                                <p class="form-control-static">${dataVo.regDt}</p>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updtDt" class="control-label col-md-2">수정일시 </label>
                        <div class="col-sm-10">
                            <div class="col-sm-6">
                                <p class="form-control-static">${dataVo.updtDt}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 버튼 -->
            <div class="row">
                <div class="block clearfix">
                    <div class="col-md-12 btn-group">
                        <div class="text-right">
                            <button type="button" class="btn btn-success" onclick="opUpdateDept();">저장</button>
                            <button type="button" class="btn btn-danger" onclick="opDeleteDept();">삭제</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- //버튼 -->
        </form>
</body>
</html>