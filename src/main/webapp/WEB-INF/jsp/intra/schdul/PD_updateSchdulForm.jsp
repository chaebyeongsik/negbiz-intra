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
<title>일정 등록</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="timepicker, colorpicker, form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/schdul/js/schdul.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var changeForm = false;
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if(validate()) {
                opLoadStart();
                $(this).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        opLoadEnd();
                        if(response.result) {
                            if(response.value == "insertOk") {
                                changeForm = true;
                                var msg = response.message + "\n계속 등록하시겠습니까?";
                                if(confirm(msg)) {
                                    location.reload(false);
                                } else {
                                    opCloseWin();
                                }
                            } else {
                                opJsonMsg(response);
                                location.reload(true);
                            }
                        }
                    },
                    error : function(response) {
                        opLoadEnd();
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });

        opChecked("seCdId", "${dataVo.seCdId}", "program");
        opChecked("rpttYn", "${dataVo.rpttYn}", "N");
        opChecked("rlsYn", "${dataVo.rlsYn}", "Y");
        opChecked("rpttTypeCd", "${dataVo.rpttTypeCd}", "N");

        opChecked("mndayRsvtNo", "${dataVo.mndayRsvtNo}");
        opChecked("tsdayRsvtNo", "${dataVo.tsdayRsvtNo}");
        opChecked("wddayRsvtNo", "${dataVo.wddayRsvtNo}");
        opChecked("trdayRsvtNo", "${dataVo.trdayRsvtNo}");
        opChecked("frdayRsvtNo", "${dataVo.frdayRsvtNo}");
        opChecked("stdayRsvtNo", "${dataVo.stdayRsvtNo}");
        opChecked("sndayRsvtNo", "${dataVo.sndayRsvtNo}");

        toggleSe("seCdId");
        toggleRepttit("rpttYn");
        toggleWeek("rpttTypeCd");

        // 시작시간과 종료시간 설정
        $("#bgngHr").timepicker({
            'timeFormat' : 'H:i'
        });
        $("#endHr").timepicker({
            'timeFormat' : 'H:i'
        });

        // Colorpicker 자동 설정
        $('.color-picker-hex').colorpicker({
            format : 'hex'
        }).on('changeColor', function(ev) {
            $("#colorNm").css("background-color", ev.color.toHex());
            $('.color-picker-hex').val(ev.color.toHex());
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {

        var bgngYmd = $.trim($("#bgngYmd").val());
        var endYmd = $.trim($("#endYmd").val());
        var rpttEndYmd = $.trim($("#rpttEndYmd").val());

        if(endYmd != "") {
            // 시작일과 종료일 비교
            if(bgngYmd > endYmd) {
                opWarningMsg("종료일이 시작일보다 클수 없습니다.");
                $("#endYmd").focus();
                return false;
            }
        }
        
        if(bgngYmd != "" && endYmd != "") {
            if(bgngYmd == endYmd) {
                var bgngHr = $.trim($("#bgngHr").val());
                var endHr = $.trim($("#endHr").val());
                
                if(bgngHr != "" && endHr != "") {
                    if(bgngHr > endHr ) {
                        alert("종료시간이 시작시간보다 클수 없습니다.");
                        return false;
                    }
                }
            }
        }

        // 반복여부에 따른 조건
        var rpttYn = $("input[name='rpttYn']:checked").val();
        if(rpttYn == "Y") {

            if(rpttEndYmd == "") {
                opWarningMsg("반복종료일을 입력하세요.");
                return false;
            }

            var rpttTypeCd = $("input[name='rpttTypeCd']:checked").val();
            if(rpttTypeCd == "W") {
                var checked = false;
                $(".weekend").each(function(idx) {
                    if(!checked) {
                        checked = $(this).prop("checked");
                    }
                });
                if(!checked) {
                    opWarningMsg("반복 유형이 매주인경우 하나 이상의 요일을 선택해야 합니다.");
                    return false;
                }
            }

            // 반복 유형에 따른 종료일 입력 확인
            if(rpttTypeCd == "W" || rpttTypeCd == "D") {
                if(confirm("[안내문] 반복유형이 매주 또는 매일인 경우\n종료일은 반복종료일이 기준이 됩니다.\n\n계속 진행하시겠습니까?")) {
                    $("#endYmd").val("");
                    endYmd = $.trim($("#endYmd").val());
                } else {
                    return false;
                }
            } else {
                if(confirm("[안내문] 반복유형이 매년 또는 매월인 경우\n종료일은 시작일과 같거나\n반복종료일보다 작아야 합니다.\n\n계속 진행하시겠습니까?")) {
                    if(endYmd == "") {
                        opWarningMsg("반복 일정이 매년 또는 매월인 경우 종료일을 입력해야 합니다.");
                        $("#endYmd").focus();
                        return false;
                    } else if (/* (bgngYmd != endYmd) || */ (endYmd > rpttEndYmd)) {
                        opWarningMsg("종료일은 시작일과 같거나\n반복종료일보다 작아야 합니다.");
                        $("#endYmd").focus();
                        return false;
                    }
                } else {
                    return false;
                }
            }

            // 반복종료일과 종료일 비교
            if(endYmd != "" && endYmd > rpttEndYmd) {
                opWarningMsg("종료일이 반복종료일보다 클수 없습니다.");
                $("#endYmd").focus();
                return false;
            }
        } else {
            if(endYmd == "") {
                opWarningMsg("반복 일정이 아닌 경우 종료일을 입력해야 합니다..");
                return false;
            }
            // 반복종료일 삭제
            $("#rpttEndYmd").val("");
        }

        return true;
    };

    /* modal창 닫기 */
    var opCloseWin = function() {
        if(changeForm) {
            parent.location.reload(false);
        }
        parent.$.fancybox.close();
    }

    //]]>
</script>
</head>
<body>
    <c:choose>
        <c:when test="${not empty dataVo and not empty dataVo.regSn}">
            <c:set var="ACTION" value="ND_updateSchdul.do" />
        </c:when>
        <c:otherwise>
            <c:set var="ACTION" value="ND_insertSchdul.do" />
        </c:otherwise>
    </c:choose>
    <form name="dataForm" id="dataForm" method="post" action="${ACTION}" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <div class="help-block text-right">
            <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="ttl" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 제목
                    </label>
                    <div class="col-xs-10">
                        <input type="text" name="ttl" id="ttl" value="${dataVo.ttl}" maxlength="500" class="form-control" />
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="ttl" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="docContsCn" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 내용
                    </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-12">
                                <textarea name="docContsCn" id="docContsCn" rows="8" cols="80" class="form-control">${dataVo.docContsCn}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="docContsCn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="fileSns" class="control-label col-sm-2"> 파일첨부</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:fileUpload view="basic" name="fileSns" count="3" size="3M" maxSize="10M" needDc="true" fileList="${dataVo.fileList}" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="seCdId" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 구분
                    </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="schdul" cdId="se" id="seCdId" values="${dataVo.seCdId}" click="toggleSe(this.name);" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group program">
                    <label for="schdlSeCdId" class="control-label col-xs-2"> 일정구분</label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-5">
                                <op:cdId type="select" hghrkCdId="schdul" cdId="schdulSe" id="schdlSeCdId" values="${dataVo.schdlSeCdId}" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group restde">
                    <label for="hldySeCdId" class="control-label col-xs-2"> 휴일구분</label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-5">
                                <op:cdId type="select" hghrkCdId="schdul" cdId="restdeSe" id="hldySeCdId" values="${dataVo.hldySeCdId}" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group program">
                    <label for="plcCdId" class="control-label col-xs-2"> 장소</label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-5">
                                <op:cdId type="select" hghrkCdId="schdul" cdId="place" id="plcCdId" values="${dataVo.plcCdId}" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="bgngYmd" class="control-label col-xs-2"> 기간설정 </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <label for="bgngYmd" class="control-label col-xs-2">
                                <span class="mandatory">*</span> 시작일
                            </label>
                            <div class="col-xs-3">
                                <input type="text" name="bgngYmd" id="bgngYmd" value="${dataVo.bgngYmd}" maxlength="10" class="form-control from-date" />
                            </div>
                            <label for="endYmd" class="control-label col-xs-2"> 종료일 </label>
                            <div class="col-xs-3">
                                <input type="text" name="endYmd" id="endYmd" value="${dataVo.endYmd}" maxlength="10" class="form-control to-date" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="text-info">※ 직접입력 시 <code>2015-01-08</code> 형식으로 입력하세요.
                                        </span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group program">
                    <label for="bgngHr" class="control-label col-xs-2"> 시간설정</label>
                    <div class="col-xs-10">
                        <div class="row">
                            <label for="bgngHr" class="control-label col-xs-2"> 시작시간</label>
                            <div class="col-xs-2">
                                <input type="text" name="bgngHr" id="bgngHr" value="${dataVo.bgngHr}" maxlength="5" class="form-control" />
                            </div>
                            <div class="col-xs-1"></div>
                            <label for="endHr" class="control-label col-xs-2"> 종료시간</label>
                            <div class="col-xs-2">
                                <input type="text" name="endHr" id="endHr" value="${dataVo.endHr}" maxlength="5" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block">
                                    <span class="text-info">※ 직접입력 시 <code>14:30</code> 형식으로 입력하세요.(24시간 사용)
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rpttYnY" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 반복여부
                    </label>
                    <div class="col-xs-10">
                        <label for="rpttYnY" class="radio-inline radio-primary">
                            <input type="radio" name="rpttYn" id="rpttYnY" value="Y" class="styled" onclick="toggleRepttit(this.name);" />
                            예
                        </label>
                        <label for="rpttYnN" class="radio-inline radio-primary">
                            <input type="radio" name="rpttYn" id="rpttYnN" value="N" class="styled" onclick="toggleRepttit(this.name);" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="rpttYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group rpttYn">
                    <label for="rpttEndYmd" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 반복종료일
                    </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-3">
                                <input type="text" name="rpttEndYmd" id="rpttEndYmd" value="${dataVo.rpttEndYmd}" maxlength="10" class="form-control to-date" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block">
                                    <span class="text-info">※ 직접입력 시 <code>2015-01-08</code> 형식으로 입력하세요.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group rpttYn">
                    <label for="rpttTypeCd" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 반복유형
                    </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-6">
                                <c:forEach items="${reptitTyList}" var="rpttTypeCd">
                                    <label for="rpttTypeCd${rpttTypeCd.key}" class="radio-inline radio-primary">
                                        <input type="radio" name="rpttTypeCd" id="rpttTypeCd${rpttTypeCd.key}" value="${rpttTypeCd.key}" class="styled" onclick="toggleWeek(this.name);" />
                                        ${rpttTypeCd.text}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="rpttTypeCd" />
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="text-info">※ 매년/매월 반복시 시작일부터 종료일까지 매년 또는 매월 반복됩니다.</span></li>
                                        <li class="list-group-item"><span class="text-info">※</span> 설정 예 : 시작일 : 2015-01-01 종료일 : 2015-01-03 인 경우</li>
                                        <li class="list-group-item"><span class="text-info">※</span> 설명 예 : 매년반복 01월01일 ~ 01월 03일, 매월반복 01일 ~ 03일을 반복종료일까지 반복</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group rpttTypeCd">
                    <label for="" class="control-label col-xs-2"> 요일선택 </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-12">
                                <label for="sndayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="sndayRsvtNo" id="sndayRsvtNo" value="1" class="styled weekend" />
                                    <span class="text-danger">일요일</span>
                                </label>
                                <label for="mndayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="mndayRsvtNo" id="mndayRsvtNo" value="2" class="styled weekend" />
                                    월요일
                                </label>
                                <label for="tsdayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="tsdayRsvtNo" id="tsdayRsvtNo" value="3" class="styled weekend" />
                                    화요일
                                </label>
                                <label for="wddayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="wddayRsvtNo" id="wddayRsvtNo" value="4" class="styled weekend" />
                                    수요일
                                </label>
                                <label for="trdayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="trdayRsvtNo" id="trdayRsvtNo" value="5" class="styled weekend" />
                                    목요일
                                </label>
                                <label for="frdayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="frdayRsvtNo" id="frdayRsvtNo" value="6" class="styled weekend" />
                                    금요일
                                </label>
                                <label for="stdayRsvtNo" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="stdayRsvtNo" id="stdayRsvtNo" value="7" class="styled weekend" />
                                    <span class="text-info">토요일</span>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12"></div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="colorNm" class="control-label col-xs-2">색상 </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-2">
                                <input type="text" name="colorNm" id="colorNm" value="${dataVo.colorNm}" maxlength="7" class="form-control color-picker-hex" style="background-color: ${dataVo.colorNm};" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="text-info">※ 입력상자를 클릭 후 색상을 선택하세요.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rlsYnY" class="control-label col-xs-2">
                        <span class="mandatory">*</span> 공개여부
                    </label>
                    <div class="col-xs-10">
                        <label for="rlsYnY" class="radio-inline radio-primary">
                            <input type="radio" name="rlsYn" id="rlsYnY" value="Y" class="styled" onclick="toggleRepttit(this.name);" />
                            예
                        </label>
                        <label for="rlsYnN" class="radio-inline radio-primary">
                            <input type="radio" name="rlsYn" id="rlsYnN" value="N" class="styled" onclick="toggleRepttit(this.name);" />
                            아니오
                        </label>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="rlsYn" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <c:choose>
                        <c:when test="${not empty dataVo and not empty dataVo.regSn}">
                            <%-- 권한확인 --%>
                            <op:auth picId="${dataVo.rgtrId}">
                                <button type="submit" class="btn btn-success">수정</button>
                                <button type="button" class="btn btn-danger" onclick="opSchdulDelete('ND_deleteSchdul.do');">삭제</button>
                            </op:auth>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-success">등록</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>