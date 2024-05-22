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
<title>사용자 관리</title>

<op:jsTag type="intra" items="opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />
<op:adres zip="zip" adres="adres" adres2="adres2" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/user/js/user.js"></script>

<script type="text/javascript">
	//<![CDATA[
	var rprsntvNmUseAt = '${entrprsUserIemEstbs.rprsntvNmUseAt}';//대표자명
	var bizrnoUseAt = '${entrprsUserIemEstbs.bizrnoUseAt}';//사업자등록번호
	var jurirnoUseAt = '${entrprsUserIemEstbs.jurirnoUseAt}';//법인등록번호
	var adresUseAt = '${entrprsUserIemEstbs.adresUseAt}';//주소
	var telnoUseAt = '${entrprsUserIemEstbs.telnoUseAt}';//전화번호
	//var captchaUseAt = '${entrprsUserIemEstbs.captchaUseAt}';//자동가입방지 - 임시 주석(추후 개발)

	var picNmUseAt = '${entrprsUserIemEstbs.picNmUseAt}';//담당자명
	var deptNmUseAt = '${entrprsUserIemEstbs.deptNmUseAt}';//부서명
	var rspofcUseAt = '${entrprsUserIemEstbs.rspofcUseAt}';//직책
	var emailUseAt = '${entrprsUserIemEstbs.emailUseAt}';//이메일
	var moblphonNoUseAt = '${entrprsUserIemEstbs.moblphonNoUseAt}';//휴대전화번호
	var deptTelnoUseAt = '${entrprsUserIemEstbs.deptTelnoUseAt}';//부서전화번호
	var fxnumUseAt = '${entrprsUserIemEstbs.fxnumUseAt}';//팩스번호
	var intrstIemNmUseAt = '${entrprsUserIemEstbs.intrstIemNmUseAt}';//관심항목명

	<valid:script type="msgbox" />
	/* 공통 초기화 기능 */
	$(document).ready(function() {

		$("#userPswd").keyup(function() {
			opCheckValidateUserPassword($(this).val());
		});

		$("#userId").keyup(function() {
			opValidateUserId();
		});

		$("#confirmPassword").blur(function() {
			opCheckConfirmPassword();
		});

		$("#emailCode").change(function() {
			opEmailCodeChange();
		});
	});

	/* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
	var customValidate = function() {

		// 설정값이 필수입력사항인 항목 필수입력체크
		if(!opEntrprsInfoEssntlCheck()) return false;

		// 아이디 중복 확인
		if ($("#dupCheckVal").val() != 'Y') {
			opWarningMsg("입력하신 사용자 아이디가 이미 존재합니다.\n사용자 아이디를 다시 입력하여 주시기바랍니다.");
			return false;
		}
		// 비밀번호와 확인용 비밀번호가 일치하는지 체크
		if (!opCheckConfirmPassword())
			return false;

		// 비밀번호 유효성 체크
		if ($("#allowPasswdCheckVal").val() != 'Y') {
			opWarningMsg("입력하신 비밀번호가 부적합한 비밀번호 조합입니다.\n비밀번호를 다시 입력하여 주시기바랍니다.");
			return false;
		}

		//비밀번호암호화
		if ($.trim($("#userPswd").val()) != "") {
			var enc_val = opEncrypt($("#userPswd").val());
			$("#userPswd").val(enc_val);
		}
		if ($.trim($("#confirmPassword").val()) != "") {
			var enc_val = opEncrypt($("#confirmPassword").val());
			$("#confirmPassword").val(enc_val);
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
	<form name="dataForm" id="dataForm" method="post" action="ND_insertEntrprsUser.do" class="form-horizontal">

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<label for="userId" class="control-label col-xs-3">
						<span class="mandatory">*</span> 사용자ID
					</label>
					<div class="col-xs-9">
						<div class="row">
							<div class="col-xs-6">
								<input type="text" name="userId" id="userId" value="${dataVo.userId}" class="form-control" placeholder="사용자ID" />
								<input type="hidden" name="dupCheckVal" id="dupCheckVal" value="" />
							</div>
							<div class="col-sm-6" id="dupCheckMessage" style="display: inline-block;"></div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<valid:msg name="userId" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="control-label col-xs-3">
						<span class="mandatory">*</span> 회사명
					</label>
					<div class="col-xs-9">
						<div class="row">
							<div class="col-xs-6">
								<input type="text" name="userNm" id="userNm" value="${dataVo.userNm}" class="form-control" placeholder="회사명" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<valid:msg name="userNm" />
							</div>
						</div>
					</div>
				</div>
				<c:if test="${entrprsUserIemEstbs.rprsntvNmUseAt ne 1002 }">
					<div class="form-group">
						<label for="rprsvNm" class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.rprsntvNmUseAt eq 1003 }"><span class="mandatory">*</span></c:if>
							대표자명
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-6">
									<input type="text" name="rprsvNm" id="rprsvNm" value="${dataVo.rprsvNm}" class="form-control" placeholder="대표자명" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<valid:msg name="rprsvNm" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label for="userPswd" class="control-label col-xs-3">
						<span class="mandatory">*</span> 사용자비밀번호
					</label>
					<div class="col-xs-9">
						<div class="row">
							<div class="col-xs-6">
								<input type="password" name="userPswd" id="userPswd" value="${dataVo.userPswd}" class="form-control" placeholder="사용자비밀번호" />
								<input type="hidden" name="allowPasswdCheckVal" id="allowPasswdCheckVal" />
							</div>
							<div class="col-xs-6" id="allowPasswd"></div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="help-block op-validate col-xs-9">
									<ul class="list-group">
										<li class="list-group-item"><span class="validate-msg"> (최소 9자, 최대 32자/영문(대/소문자),숫자,특수문자 조합) </span></li>
										<li class="list-group-item"><span class="validate-msg"> 비밀번호 등록 또는 변경시 보안등급 확인하여 안전하게 변경하시기 바랍니다. </span></li>
									</ul>
								</div>
								<div class="col-xs-3">
									<button type="button" class="btn btn-xs btn-info" onclick="opScrtyGradPop();">보안등급정책</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label col-xs-3">
						<span class="mandatory">*</span> 사용자비밀번호 확인
					</label>
					<div class="col-xs-9">
						<div class="row">
							<div class="col-xs-6">
								<input type="password" name="confirmPassword" id="confirmPassword" value="" class="form-control" placeholder="사용자비밀번호 확인" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12"></div>
						</div>
					</div>
				</div>
				<c:if test="${entrprsUserIemEstbs.jurirnoUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.jurirnoUseAt eq 1003 }"><span class="mandatory">*</span></c:if>
							법인등록번호
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-3">
									<label for="crno" class="sr-only">법인등록번호</label>
									<input type="text" name="crno" id="crno" value="${dataVo.crno}" maxlength="13" class="form-control" title="법인등록번호를 입력해주세요" />
								</div>
								<%-- <div class="col-xs-3">
									<label for="jurirno2" class="sr-only">법인등록번호 뒷자리</label>
									<input type="text" name="jurirno2" id="jurirno2" value="${dataVo.jurirno2}" maxlength="7" class="form-control" title="법인등록번호를 입력해주세요" />
								</div> --%>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.bizrnoUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.bizrnoUseAt eq 1003 }">
								<span class="mandatory">*</span>
							</c:if>
							사업자등록번호
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-4">
									<label for="brno" class="sr-only">사업자등록번호</label>
									<input type="text" name="brno" id="brno" value="${dataVo.brno}" class="form-control" title="사업자등록번호를 입력해주세요" maxlength="10" />
								</div>
								<%-- <div class="col-xs-4">
									<label for="bizrno2" class="sr-only">사업자등록번호 중간자리</label>
									<input type="text" name="bizrno2" id="bizrno2" value="${dataVo.bizrno2}" class="form-control" title="사업자등록번호 중간자리를 입력해주세요" maxlength="2" />
								</div>
								<div class="col-xs-4">
									<label for="bizrno3" class="sr-only">사업자등록번호 뒷자리</label>
									<input type="text" name="bizrno3" id="bizrno3" value="${dataVo.bizrno3}" class="form-control" title="사업자등록번호 뒷자리를 입력해주세요" maxlength="5" />
								</div> --%>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.telnoUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.telnoUseAt eq 1003 }">
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
				<c:if test="${entrprsUserIemEstbs.adresUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.adresUseAt eq 1003 }">
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
									<button type="button" class="btn btn-default adresPopBtn" id="zipCodeBtn">우편번호찾기</button>
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
				<%--
				<c:if test="${entrprsUserIemEstbs.captchaUseAt ne 1002 }">
					<div class="form-group">
						<label for="captcha" class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.captchaUseAt eq 1003 }">
								<span class="mandatory">*</span>
							</c:if>
							자동가입방지
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-6"></div>
							</div>
							<div class="row">
								<div class="col-xs-12"></div>
							</div>
						</div>
					</div>
				</c:if>
				 --%>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading"><h6 class="panel-title"><i class="icon-bubble-plus"></i> 추가정보</h6></div>
			<div class="panel-body">
				<c:if test="${entrprsUserIemEstbs.picNmUseAt ne 1002 }">
					<div class="form-group">
						<label for="picNm" class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.picNmUseAt eq 1003 }"><span class="mandatory">*</span></c:if> 담당자명
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-6">
									<input type="text" name="picNm" id="picNm" value="${dataVo.picNm}" class="form-control" placeholder="담당자명" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<valid:msg name="picNm" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.deptNmUseAt ne 1002 }">
					<div class="form-group">
						<label for="deptNm" class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.deptNmUseAt eq 1003 }"><span class="mandatory">*</span></c:if>
							부서명
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-6">
									<input type="text" name="deptNm" id="deptNm" value="${dataVo.deptNm}" class="form-control" placeholder="부서명" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<valid:msg name="deptNm" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.rspofcUseAt ne 1002 }">
					<div class="form-group">
						<label for="jbttlNm" class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.rspofcUseAt eq 1003 }">
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
							<div class="row">
								<div class="col-xs-12">
									<valid:msg name="jbttlNm" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.emailUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.emailUseAt eq 1003 }">
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
				<c:if test="${entrprsUserIemEstbs.moblphonNoUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.moblphonNoUseAt eq 1003 }">
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
				<c:if test="${entrprsUserIemEstbs.deptTelnoUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.deptTelnoUseAt eq 1003 }">
								<span class="mandatory">*</span>
							</c:if>
							부서전화번호
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-4">
									<label for="deptRgnTelno" class="sr-only">부서전화번호 국번</label>
									<input type="text" name="deptRgnTelno" id="deptRgnTelno" value="${dataVo.deptRgnTelno}" class="form-control" title="국번을 입력해주세요" maxlength="4" />
								</div>
								<div class="col-xs-4">
									<label for="deptTelofcTelno" class="sr-only">부서전화번호 앞자리</label>
									<input type="text" name="deptTelofcTelno" id="deptTelofcTelno" value="${dataVo.deptTelofcTelno}" class="form-control" title="앞자리를 입력해주세요" maxlength="4" />
								</div>
								<div class="col-xs-4">
									<label for="deptIndivTelno" class="sr-only">부서전화번호 뒷자리</label>
									<input type="text" name="deptIndivTelno" id="deptIndivTelno" value="${dataVo.deptIndivTelno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.fxnumUseAt ne 1002 }">
					<div class="form-group">
						<label class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.fxnumUseAt eq 1003 }">
								<span class="mandatory">*</span>
							</c:if>
							팩스전화번호
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-4">
									<label for="rgnFxno" class="sr-only">팩스전화번호 앞자리</label>
									<input type="text" name="rgnFxno" id="rgnFxno" value="${dataVo.rgnFxno}" class="form-control" title="앞자리를 입력해주세요" maxlength="4" />
								</div>
								<div class="col-xs-4">
									<label for="telofcFxno" class="sr-only">팩스전화번호 중간자리</label>
									<input type="text" name="telofcFxno" id="telofcFxno" value="${dataVo.telofcFxno}" class="form-control" title="중간자리를 입력해주세요" maxlength="4" />
								</div>
								<div class="col-xs-4">
									<label for="indivFxno" class="sr-only">팩스전화번호 뒷자리</label>
									<input type="text" name="indivFxno" id="indivFxno" value="${dataVo.indivFxno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${entrprsUserIemEstbs.intrstIemNmUseAt ne 1002 }">
					<div class="form-group" id="intrstIemDiv">
						<label for="itrstArtclCn" class="control-label col-xs-3">
							<c:if test="${entrprsUserIemEstbs.intrstIemNmUseAt eq 1003 }">
								<span class="mandatory">*</span>
							</c:if>
						관심항목
						</label>
						<div class="col-xs-9">
							<div class="row">
								<div class="col-xs-12">
									<input type="hidden" name="itrstArtclCn" id="itrstArtclCn" value="${dataVo.itrstArtclCn}" />
									<op:cdId type="checkbox" hghrkCdId="userIntrstIem" cdId="bsnmUserIntrstIem" id="intrstIemNmCode" values="${dataVo.itrstArtclCn}"/>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>

		<!-- 버튼 -->
		<div class="block clearfix">
			<div class="col-xs-12 btn-group">
				<div class="pull-right">
					<button type="button" onclick="opInsertEntrprsUser()" class="btn btn-success">등록</button>
				</div>
			</div>
		</div>
		<!-- //버튼 -->
	</form>
</body>
</html>