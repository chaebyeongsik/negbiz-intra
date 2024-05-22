<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@page import="zesinc.web.auth.AuthType"%>
<%@page import="zesinc.web.support.BaseConfig"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 관리</title>

<op:jsTag type="intra" items="opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />
<op:adres zip="zip" adres="adres" adres2="adres2" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/user.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/userHist.js"></script>

<script type="text/javascript">
    //<![CDATA[
    var ihidnumUseAt = '${indvdlUserIemEstbs.ihidnumUseAt}';//주민등록번호
    var emailUseAt = '${indvdlUserIemEstbs.emailUseAt}';//이메일
    var telnoUseAt = '${indvdlUserIemEstbs.telnoUseAt}';//전화번호
    var moblphonNoUseAt = '${indvdlUserIemEstbs.moblphonNoUseAt}';//휴대전화
    var adresUseAt = '${indvdlUserIemEstbs.adresUseAt}';//주소
    var brthdyUseAt = '${indvdlUserIemEstbs.brthdyUseAt}';//생일
    var sexdstnUseAt = '${indvdlUserIemEstbs.sexdstnUseAt}';//성별
    var mailingSvcUseAt = '${indvdlUserIemEstbs.mailingSvcUseAt}';//메일링서비스
    var smsRecptnUseAt = '${indvdlUserIemEstbs.smsRecptnUseAt}';//SMS수신

    var lastAcdmcrUseAt = '${indvdlUserIemEstbs.lastAcdmcrUseAt}';//최종학력
    var mrrgUseAt = '${indvdlUserIemEstbs.mrrgUseAt}';//결혼
    var wrcNmUseAt = '${indvdlUserIemEstbs.wrcNmUseAt}';//직장명
    var rspofcUseAt = '${indvdlUserIemEstbs.rspofcUseAt}';//직책
    var wrcAdresUseAt = '${indvdlUserIemEstbs.wrcAdresUseAt}';//직장주소
    var wrcTelnoUseAt = '${indvdlUserIemEstbs.wrcTelnoUseAt}';//직장전화번호
    var intrstIemNmUseAt = '${indvdlUserIemEstbs.intrstIemNmUseAt}';//관심항목명


    <valid:script type="msgbox" />
    /* 공통 초기화 기능 */
    $(document).ready(function() {

        $("input[name=slrcldLrr]").click(function() {
            if ($(this).val() == '1001') {
                $("#lpnh").attr("checked", false);
                $("#lpnh").attr("disabled", true);
            } else {
                $("#lpnh").attr("disabled", false);
            }
        });

        $("#emailCode").change(function() {
            opEmailCodeChange();
        });

        $("#brdt").blur(function() {
            opCheckBirthday();//생년월일 유효성 체크
        });

//         $("#confirmPassword").blur(function() {
//             opCheckUpdateFormConfirmPassword();
//         });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {

        // 설정값이 필수입력사항인 항목 필수입력체크
        if (!opIndvdlInfoEssntlCheck())
            return false;

        // 수정하고자 하는 담당자 비밀번호 체크
        if (!opCheckPicPswd()) {
            return false;
        }

        //생년월일 유효성 체크
        if (!opCheckBirthday())
            return false;

        //양력음력윤달 여부 값 판별 후 input(hidden)에 셋팅
        opSetSlrcldLrrLpnhAt();

        //사용자등급코드 input에 셋팅
        opSetUserGradCode();

        //비밀번호암호화
        if ($.trim($("#picPswd").val()) != "") {
            var enc_val = opEncrypt($("#picPswd").val());
            $("#picPswd").val(enc_val);
        }

        return true;
    };

    var checkUpdateCnt = 0;
    var opCloseWin = function() {
        // 등록한 내역이 있을 경우에만 초기화 또는 등록 후 바로 현재 탭으로 온 경우..목록을 리로드 할 필요가 있음
        if (checkUpdateCnt > 0)
            parent.opReload();

        if (parent.$.fancybox) {
            parent.$.fancybox.close();
        } else if ($.fancybox) {
            $.fancybox.close();
        } else {
            self.close();
        }
    };
    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateIndvdlUser.do" class="form-horizontal">
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-bubble4"></i> 기본정보</h6></div>
        <div class="panel-body">
            <div class="form-group">
                <label for="userId" class="control-label col-xs-3"> 사용자ID </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="hidden" name="userId" id="userId" value="${dataVo.userId}" />
                            ${dataVo.userIdntfNm}
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="userNm" class="control-label col-xs-3"> 사용자명 </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="hidden" name="userNm" id="userNm" value="${dataVo.userNm}" />
                            ${dataVo.userNm}
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${indvdlUserIemEstbs.ihidnumUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.ihidnumUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        주민등록번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-3">
                                <label for="rrno" class="sr-only">주민등록번호 앞자리</label>
                                <input type="text" name="rrno" id="rrno" value="${dataVo.rrno}" maxlength="13" class="form-control" title="주민번호 앞자리를 입력해주세요" />
                            </div>
                            <%-- <div class="col-xs-3">
                                <label for="ihidnum2" class="sr-only">주민등록번호 뒷자리</label>
                                <input type="text" name="ihidnum2" id="ihidnum2" value="${dataVo.ihidnum2}" maxlength="7" class="form-control" title="주민번호 뒷자리를 입력해주세요" />
                            </div> --%>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="rrno" />
                                <%-- <valid:msg name="ihidnum2" /> --%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.emailUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.emailUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        이메일
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="emlId" class="sr-only">이메일 앞부분</label>
                                <input type="text" name="emlId" id="emlId" value="${dataVo.emlId}" class="form-control" placeholder="이메일1" />
                            </div>
                            <div class="col-xs-4">
                                <label for="emlSiteNm" class="sr-only">이메일 뒷부분</label>
                                <div class="input-group">
                                    <span class="input-group-addon">@</span>
                                    <input type="text" name="emlSiteNm" id="emlSiteNm" value="${dataVo.emlSiteNm}" class="form-control" placeholder="이메일2" />
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <op:cdId type="select" hghrkCdId="emailCode" cdId="emailCode" size="2" id="emailCode" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="emlId" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label for="userGrdCdId" class="control-label col-xs-3">사용자등급 </label>
                <div class="col-xs-9">
                    <div class="block-inner">
                        <input type="hidden" name="userGrdCdDsctn" id="userGrdCdDsctn" value="${dataVo.userGrdCdDsctn}" />
                        <c:forEach items="${userGradList}" var="userGradVo" varStatus="vst">
                            <c:set value="" var="gradeCheckYn" />
                            <c:forTokens items="${dataVo.userGrdCdDsctn}" delims="," var="gradCodeVal">
                                <c:if test="${gradCodeVal eq userGradVo.userGrdCdId}"><c:set value="checked=\"checked\"" var="gradeCheckYn" /></c:if>
                            </c:forTokens>
                            <label for="userGrdCdId${vst.count}" class="checkbox-inline">
                                ${userGradVo.userGrdNm}
                                <input type="checkbox" name="userGrdCdId" id="userGrdCdId${vst.count}" value="${userGradVo.userGrdCdId}" class="styled" ${gradeCheckYn} />
                            </label>
                        </c:forEach>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <valid:msg name="userGrdCdDsctn" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="joinTypeSn" class="control-label col-xs-3">가입유형 </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <op:cdId type="select" hghrkCdId="joinTypeSn" cdId="joinTypeSn" id="joinTypeSn" values="${dataVo.joinTypeSn}" />
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${indvdlUserIemEstbs.telnoUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.telnoUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        전화번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="rgnTelno" class="sr-only">전화번호 국번</label>
                                <input type="text" name="rgnTelno" id="rgnTelno" value="${dataVo.rgnTelno}" class="form-control" title="국번을 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="telofcTelno" class="sr-only">전화번호 앞자리</label>
                                <input type="text" name="telofcTelno" id="telofcTelno" value="${dataVo.telofcTelno}" class="form-control" title="앞자리를 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="indivTelno" class="sr-only">전화번호 뒷자리</label>
                                <input type="text" name="indivTelno" id="indivTelno" value="${dataVo.indivTelno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.moblphonNoUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.moblphonNoUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        휴대전화번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="mblRgnTelno" class="sr-only">휴대전화번호 앞자리</label>
                                <op:cdId type="select" hghrkCdId="mobileTy" cdId="mobileTy" id="mblRgnTelno" values="${dataVo.mblRgnTelno}" />
                            </div>
                            <div class="col-xs-4">
                                <label for="mblTelofcTelno" class="sr-only">휴대전화번호 중간자리</label>
                                <input type="text" name="mblTelofcTelno" id="mblTelofcTelno" value="${dataVo.mblTelofcTelno}" class="form-control" title="중간자리를 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="mblIndivTelno" class="sr-only">휴대전화번호 뒷자리</label>
                                <input type="text" name="mblIndivTelno" id="mblIndivTelno" value="${dataVo.mblIndivTelno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.adresUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.adresUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        주소
                    </label>
                    <div class="col-xs-9">
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label for="zip" class="sr-only">우편번호</label>
                                <input type="text" name="zip" id="zip" value="${dataVo.zip}" class="form-control zip" placeholder="우편번호" maxlength="5" />
                            </div>
                            <div class="col-xs-4">
                                <button type="button" class="btn btn-default adresPopBtn">우편번호찾기</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="userAddr" class="sr-only">기본주소</label>
                                <input type="text" name="userAddr" id="userAddr" value="${dataVo.userAddr}" class="form-control adres" placeholder="기본주소" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="daddr" class="sr-only">상세주소</label>
                                <input type="text" name="daddr" id="daddr" value="${dataVo.daddr}" class="form-control adres2" placeholder="상세주소" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.brthdyUseAt ne 1002 }">
                <div class="form-group">
                    <label for="brdt" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.brthdyUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        생년월일
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="text" name="brdt" id="brdt" value="${dataVo.brdt}" class="form-control" maxlength="8" placeholder="예) 20150101" />
                            </div>
                            <div class="col-xs-6">
                                <input type="hidden" id="brthYmdClsfSn" name="brthYmdClsfSn" value="${dataVo.brthYmdClsfSn}" />
                                <input type="radio" name="slrcldLrr" id="slrcld" value="1001" checked="checked" />
                                <label for="brthYmdClsfSn1">양력</label>
                                <input type="radio" name="slrcldLrr" id="lrr" value="1002" <c:if test="${dataVo.brthYmdClsfSn eq 1002 || dataVo.brthYmdClsfSn eq 1003}">checked="checked"</c:if> />
                                <label for="brthYmdClsfSn2">음력</label>
                                <input type="checkbox" name="lpnh" id="lpnh" disabled="disabled" <c:if test="${dataVo.brthYmdClsfSn eq 1003}">checked="checked"</c:if> />
                                <label for="brthYmdClsfSn3">윤달</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="brdt" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.sexdstnUseAt ne 1002 }">
                <div class="form-group">
                    <label for="gndrClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.sexdstnUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        성별
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <op:cdId type="radio" hghrkCdId="gndrClsfSn" cdId="gndrClsfSn" id="gndrClsfSn" values="${dataVo.gndrClsfSn}" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.mailingSvcUseAt ne 1002 }">
                <div class="form-group">
                    <label for="emlRcptnAgreClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.mailingSvcUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        메일링서비스
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="emlRcptnAgreClsfSn" cdId="emlRcptnAgreClsfSn" id="emlRcptnAgreClsfSn" values="${dataVo.emlRcptnAgreClsfSn}" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.smsRecptnUseAt ne 1002 }">
                <div class="form-group">
                    <label for="smsRcptnClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.smsRecptnUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        SMS수신여부
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="smsRcptnClsfSn" cdId="smsRcptnClsfSn" id="smsRcptnClsfSn" values="${dataVo.smsRcptnClsfSn}" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label for="userSttsSn" class="control-label col-xs-3">사용자상태</label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <op:cdId type="radio" hghrkCdId="userSttsSn" cdId="userSttsSn" id="userSttsSn" values="${dataVo.userSttsSn}" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="regDt" class="control-label col-xs-3">가입일시</label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="lastCntnDt" class="control-label col-xs-3">최근접속일시</label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">${dataVo.lastCntnDt}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-bubble-plus"></i> 추가정보</h6></div>
        <div class="panel-body">
            <c:if test="${indvdlUserIemEstbs.lastAcdmcrUseAt != 1002 }">
                <div class="form-group">
                    <label for="lastAcbgNo" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.lastAcdmcrUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        최종학력
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="select" hghrkCdId="lastAcbgNo" cdId="lastAcbgNo" id="lastAcbgNo" values="${dataVo.lastAcbgNo}" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.mrrgUseAt != 1002 }">
                <div class="form-group">
                    <label for="mrgSeSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.mrrgUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        결혼여부
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="mrgSeSn" cdId="mrgSeSn" id="mrgSeSn" values="${dataVo.mrgSeSn}" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.wrcNmUseAt != 1002 }">
                <div class="form-group">
                    <label for="wrcNm" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.wrcNmUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직장명
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="text" name="wrcNm" id="wrcNm" value="${dataVo.wrcNm}" class="form-control" placeholder="직장명" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.rspofcUseAt != 1002 }">
                <div class="form-group">
                    <label for="jbttlNm" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.rspofcUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직책
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="text" name="jbttlNm" id="jbttlNm" value="${dataVo.jbttlNm}" class="form-control" placeholder="직책" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.wrcAdresUseAt != 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.wrcAdresUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직장주소
                    </label>
                    <div class="col-xs-9">
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label for="wrcZip" class="sr-only">직장주소 - 우편번호</label>
                                <input type="text" name="wrcZip" id="wrcZip" value="${dataVo.wrcZip}" class="form-control zip" placeholder="우편번호" maxlength="5" />
                            </div>
                            <div class="col-xs-4">
                                <button type="button" class="btn btn-default adresPopBtn">우편번호찾기</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="wrcAddr" class="sr-only">직장주소 - 기본주소</label>
                                <input type="text" name="wrcAddr" id="wrcAddr" value="${dataVo.wrcAddr}" class="form-control adres" placeholder="기본주소" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="wrcDaddr" class="sr-only">직장주소 - 상세주소</label>
                                <input type="text" name="wrcDaddr" id="wrcDaddr" value="${dataVo.wrcDaddr}" class="form-control adres2" placeholder="상세주소" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.wrcTelnoUseAt != 1002 }">
                <div class="form-group">
                    <label for="wrcRgnTelno" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.wrcTelnoUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직장전화번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="wrcRgnTelno" class="sr-only">직장전화번호 국번</label>
                                <input type="text" name="wrcRgnTelno" id="wrcRgnTelno" value="${dataVo.wrcRgnTelno}" class="form-control" title="국번을 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="wrcTelofcTelno" class="sr-only">직장전화번호 앞자리</label>
                                <input type="text" name="wrcTelofcTelno" id="wrcTelofcTelno" value="${dataVo.wrcTelofcTelno}" class="form-control" title="앞자리를 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="wrcIndivTelno" class="sr-only">직장전화번호 뒷자리</label>
                                <input type="text" name="wrcIndivTelno" id="wrcIndivTelno" value="${dataVo.wrcIndivTelno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.intrstIemNmUseAt != 1002 }">
                <div class="form-group" id="intrstIemDiv">
                    <label for="itrstArtclCn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.intrstIemNmUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        관심항목
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <input type="hidden" name="itrstArtclCn" id="itrstArtclCn" value="${dataVo.itrstArtclCn}" />
                                <op:cdId type="checkbox" hghrkCdId="userIntrstIem" cdId="indvdlUserIntrstIem" id="intrstIemNmCode" values="${dataVo.itrstArtclCn}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

        <div class="panel panel-primary">
            <div class="panel-heading"><h6 class="panel-title"><i class="icon-bubble-check"></i> 수정 사유 입력</h6></div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="picPswd" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 비밀번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="password" name="picPswd" id="picPswd" value="" class="form-control" placeholder="비밀번호" autocomplete="off" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="logCn" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 수정사유
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <textarea rows="3" cols="7" name="logCn" id="logCn" placeholder="수정사유" class="limited form-control"></textarea>
                                <span class="help-block" id="limit-text">사용자 정보를 수정하는 사유를 입력하세요.</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="block clearfix">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <button type="button" onclick="opUpdateIndvdlUser()" class="btn btn-success">수정</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>