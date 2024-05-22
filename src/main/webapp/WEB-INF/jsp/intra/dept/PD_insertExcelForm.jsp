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
    <title>부서 엑셀일괄등록</title>

    <op:jsTag type="libs" items="colorbox, highlight, ui"/>
    <op:jsTag type="intra" items="ui, opform, opvalidate" />

    <!-- 기능별 스크립트 정의 -->
    <script type="text/javascript">
        //<![CDATA[

        var opCount = 0;
        $(document).ready(function() {

        });

        var opSubmit = function(){

            var upDeptCdIds = new Array();
            var deptNms = new Array();
            var deptCdIds = new Array();
            var rgnTelnos = new Array();
            var telofcTelnos = new Array();
            var indivTelnos = new Array();
            var rgnFxnos = new Array();
            var telofcFxnos = new Array();
            var indivFxnos = new Array();
            var tkcgTaskNms = new Array();
            var useYns = new Array();

            $("#dataForm input[name=chkbox]").each(function (i) {
                upDeptCdIds[i] = $("#dataForm input[name=upDeptCdId]").eq(i).val();
                deptNms[i] = $("#dataForm input[name=deptNm]").eq(i).val();
                deptCdIds[i] = $("#dataForm input[name=deptCdId]").eq(i).val();
                rgnTelnos[i] = $("#dataForm input[name=rgnTelno]").eq(i).val();
                telofcTelnos[i] = $("#dataForm input[name=telofcTelno]").eq(i).val();
                indivTelnos[i] = $("#dataForm input[name=indivTelno]").eq(i).val();
                rgnFxnos[i] = $("#dataForm input[name=rgnFxno]").eq(i).val();
                telofcFxnos[i] = $("#dataForm input[name=telofcFxno]").eq(i).val();
                indivFxnos[i] = $("#dataForm input[name=indivFxno]").eq(i).val();
                tkcgTaskNms[i] = $("#dataForm input[name=tkcgTaskNm]").eq(i).val();
                useYns[i] = $("#dataForm input[name=useYn]").eq(i).val();
                $("#dataForm input[name=deptCdId]").eq(i).removeClass("has-warning").removeClass("callout-danger");
            });

            /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
            <valid:script type="msgbox" />
            if (validate()) {
                /** 부서 일괄등록 */
                $.ajaxSettings.traditional = true;
                $.ajax({
                    url : "<c:url value="/intra/dept/ND_insertDeptExcel.do" />",
                    type : "POST",
                    data : {
                        upDeptCdIds : upDeptCdIds,
                        deptNms : deptNms,
                        deptCdIds : deptCdIds,
                        rgnTelnos : rgnTelnos,
                        telofcTelnos : telofcTelnos,
                        indivTelnos : indivTelnos,
                        rgnFxnos : rgnFxnos,
                        telofcFxnos : telofcFxnos,
                        indivFxnos : indivFxnos,
                        tkcgTaskNms : tkcgTaskNms,
                        useYns : useYns
                    },
                    dataType : "json",
                    async : false,
                    success : function(response) {
                        if (response.result) {
                            //성공시 액션(modal창 닫고 부모창 초기화)
                            parent.opSuccessMsg(response.message);
                            parent.Optree.reload();
                            opCloseWin();
                        } else {
                            //실패시 로직(중복코드인경우 해당코드 경고박스처리)
                            opWarningMsg(response.message);
                            opErrorCode(response.value)
                        }
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            } else {
                return false;
            }

        };

        // 폼의 부서코드 중복체크
        var customValidate = function() {

            var dplctChck = new Boolean(true);
            $("#dataForm input[name=deptCdId]").each(function (fir) {
                var dtCd = $("[name='deptCdId']").eq(fir);
                $("#dataForm input[name=deptCdId]").each(function (sec) {
                    if (fir != sec && dtCd.val() == $(this).val()) {
                        opWarningMsg(dtCd.val() + "는 중복된 코드입니다.");
                        dtCd.focus();
                        dplctChck = false;
                        return dplctChck;
                    }
                })
                return dplctChck;
            });

            // 사용여부 오류 표시
            $("#dataForm input[name=useYn]").each(function (i) {
                if ($.trim($(this).val()) != "Y" && $.trim($(this).val()) != "N") {
                    opWarningMsg("사용여부는 Y 또는 N 값만 입력할 수 있습니다.");
                    $(this).focus();
                    dplctChck = false;
                }
            })

            return dplctChck;
        };

        var opDeleteSelected = function(){

            if ($("#dataForm input[name=chkbox]:checked").length < 1) {
                alert("선택하신 목록이 없습니다.");
                return false;
            } else {
                $("#dataForm input[name=chkbox]:checked").each(function(i) {
                    var chkVal = $(this).val();
                    $("#dataForm input[name=chkbox]").each(function(el) {
                        if (chkVal == $(this).val()) {
                            $(this).parent().parent().parent().find("tr").eq(el).remove();
                        }
                    })
                })

                if ($("#dataForm input[name=chkbox]").length == $("#dataForm input[name=chkbox]:checked").length) {
                    var tabHtml = "<tr><td class=\"text-center\" colspan=\"12\"><span class=\"text-warning\">등록할 데이터가 없습니다.</span></td></tr>";
                    $("#dataForm input[name=chk-all]").parent().parent().parent().parent().find("tbody").append(tabHtml);
                }
            }
        };

        // 폼 전송후 중복코드 표시
        var opErrorCode = function(obj) {
            var focusCnt = 0;
            $("#dataForm input[name=deptCdId]").each(function(i) {
                if ($.inArray($(this).val(), obj) != -1) {
                    $(this).addClass("has-warning").addClass("callout-danger");
                    focusCnt++;
                    if (focusCnt == 1) $(this).focus();
                }
            })

            // 상위코드 오류 표시
            if(focusCnt == 0) {
                $("#dataForm input[name=upDeptCdId]").each(function(i) {
                    if ($.inArray($(this).val(), obj) != -1) {
                        $(this).addClass("has-warning").addClass("callout-danger");
                        focusCnt++;
                        if (focusCnt == 1) $(this).focus();
                    }
                })
            }
        };

        // 폼 전송후 상위부서코드 표시
        var opErrorUpperCode = function(obj) {
            var focusCnt = 0;
            $("#dataForm input[name=deptCdId]").each(function(i) {
                if ($.inArray($(this).val(), obj) != -1) {
                    $(this).addClass("has-warning").addClass("callout-danger");
                    focusCnt++;
                    if (focusCnt == 1) $(this).focus();
                }
            })
        };
        //]]>
    </script>
</head>
<body>

    <div class="panel-body">
        <div class="block clearfix">
            <div class="col-sm-8 btn-group">
                <p>
                    샘플파일을 다운로드 하여 양식을 작성하여 불러오기 버튼을 눌러주세요.
                    <a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=1&chgCycl=1&fileSn=1" title="부서업로드셈플엑셀파일 파일받기" class="btn btn-success">샘플파일 다운로드</a>
                </p>
            </div>
        </div>
        <ul>
            <li>엑셀 데이터의 행은 500행을 넘지 않게 작성합니다.</li>
            <li>부서코드 20자 이하, 상위부서코드 20자 이하, 코드명은 30자 이하, 주요업무 100자 이하</li>
        </ul>

        <div class="row">
            <div class="help-block text-right">
                <span class="mandatory">*</span> 일괄등록할 엑셀을 선택하여 주십시오.
            </div>

            <c:set value="상위부서코드,부서명,부서코드,전화번호1,전화번호2,전화번호3,팩스번호1,팩스번호2,팩스번호3,주요업무,사용여부" var="headerNm" />
            <c:set value="upDeptCdId,deptNm,deptCdId,rgnTelno,telofcTelno,indivTelno,rgnFxno,telofcFxno,indivFxno,tkcgTaskNm,useYn" var="headerId" />
            <c:set value="M,M,M,S,S,S,S,S,S,L,S" var="columnSize" /><!-- s,m,l -->
            <op:excelToTable headerId="${headerId}" headerNm="${headerNm}" size="${columnSize}" headerLineCnt="1" />
        </div>
    </div>

</body>
</html>