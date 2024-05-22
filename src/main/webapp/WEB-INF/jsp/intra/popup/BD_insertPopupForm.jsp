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
<title>팝업 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="ckeditor" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/popup/js/popup.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        $("#contentbuilderBtn").click(function() {
            opContentbuilderOpen();
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
    <form name="contentbuilderForm" id="contentbuilderForm" target="_blank" method="post" action="/component/contentbuilder/ND_selectEditor.do">
        <textarea name="q_content" id="q_content" rows="15" cols="80" class="sr-only" placeholder="장문내용">${dataVo.docContsCn}</textarea>
        <button type="submit" class="sr-only">편집</button>
    </form>

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-libreoffice"></i> 팝업 등록
            </h6>
        </div>
        <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_insertPopup.do" class="form-horizontal">
                <%-- 페이징 관련 파라미터 생성 --%>
                <op:pagerParam view="view" ignores="" />
                <div class="help-block text-right">
                    <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="siteSns" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 사이트
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:forEach items="${domainList}" var="domain" varStatus="status">
                                            <label for="siteSns" class="checkbox-inline checkbox-primary">
                                                <input type="checkbox" name="siteSns" id="siteSns${status.index}" value="${domain.siteSn}" class="styled" />
                                                ${domain.siteExpln}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="siteSns" />
                                        <div class="help-block">
                                            <span class="text-info">선택된 사이트 모두에 적용됩니다.</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ttl" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 제목
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <input type="text" name="ttl" id="ttl" value="${dataVo.ttl}" maxlength="500" class="form-control" placeholder="제목" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="ttl" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 기간
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <label for="bgngYmd" class="control-label col-sm-1"> 시작일</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="bgngYmd" id="bgngYmd" value="${dataVo.bgngYmd}" maxlength="10" class="form-control from-date" placeholder="시작일" />
                                    </div>
                                    <label for="endYmd" class="control-label col-sm-1"> 종료일</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="endYmd" id="endYmd" value="${dataVo.endYmd}" maxlength="10" class="form-control to-date" placeholder="종료일" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <valid:msg name="bgngYmd" />
                                    </div>
                                    <div class="col-sm-3">
                                        <valid:msg name="endYmd" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 크기/위치
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <label for="wdthSz" class="control-label col-sm-3">가로크기(Pixel 단위)</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="wdthSz" id="wdthSz" value="${dataVo.wdthSz}" maxlength="5" class="form-control text-right" placeholder="가로크기" />
                                    </div>
                                    <label for="vrtcSz" class="control-label col-sm-3"> 세로크기(Pixel 단위)</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="vrtcSz" id="vrtcSz" value="${dataVo.vrtcSz}" maxlength="5" class="form-control text-right" placeholder="세로크기" />
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" onclick="opSelectSizeAndPosition();" class="btn btn-success">자동 설정</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-5">
                                        <valid:msg name="vrtcSz" />
                                    </div>
                                    <div class="col-sm-5">
                                        <valid:msg name="wdthSz" />
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="yAxis" class="control-label col-sm-3">상단위치(Pixel 단위)</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="yAxis" id="yAxis" value="${dataVo.yAxis}" maxlength="5" class="form-control text-right" placeholder="상단위치" />
                                    </div>
                                    <label for="xAxis" class="control-label col-sm-3"> 좌측위치(Pixel 단위)</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="xAxis" id="xAxis" value="${dataVo.xAxis}" maxlength="5" class="form-control text-right" placeholder="좌측위치" />
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="bg-danger form-control" style="width: 100px;">100 Pixel 크기</div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-5">
                                        <valid:msg name="yAxis" />
                                    </div>
                                    <div class="col-sm-5">
                                        <valid:msg name="xAxis" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="scrollYn" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 스크롤
                            </label>
                            <div class="col-sm-4">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <label for="scrollYnY" class="radio-inline radio-primary">
                                            <input type="radio" name="scrollYn" id="scrollYnY" value="Y" <c:if test="${dataVo.scrollYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                            사용
                                        </label>
                                        <label for="scrollYnN" class="radio-inline radio-primary">
                                            <input type="radio" name="scrollYn" id="scrollYnN" value="N" <c:if test="${dataVo.scrollYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                            미사용
                                        </label>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <valid:msg name="scrollYn" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <label for="popupSzChgYn" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 리사이징
                            </label>
                            <div class="col-sm-4">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label for="popupSzChgYnY" class="radio-inline radio-primary">
                                            <input type="radio" name="popupSzChgYn" id="popupSzChgYnY" value="Y" <c:if test="${dataVo.popupSzChgYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                            사용
                                        </label>
                                        <label for="popupSzChgYnN" class="radio-inline radio-primary">
                                            <input type="radio" name="popupSzChgYn" id="popupSzChgYnN" value="N" <c:if test="${dataVo.popupSzChgYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                            미사용
                                        </label>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <valid:msg name="popupSzChgYn" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="popupTypeNo" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 팝업유형
                            </label>
                            <div class="col-sm-10">
                                <op:cdId type="radio" hghrkCdId="popup" cdId="popupTypeNo" id="popupTypeNo" values="${dataVo.popupTypeNo}" />
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="popupTypeNo" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="popupRpttSeNo" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 팝업주기
                            </label>
                            <div class="col-sm-10">
                                <op:cdId type="radio" hghrkCdId="popup" cdId="popupRpttSeNo" id="popupRpttSeNo" values="${dataVo.popupRpttSeNo}" />
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="popupRpttSeNo" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="wrtTypeNm" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 작성방법
                            </label>
                            <div class="col-sm-10">
                                <op:cdId type="radio" hghrkCdId="popup" cdId="wrtTypeNm" id="wrtTypeNm" click="opChangeWritngMth(this);" values="${dataVo.wrtTypeNm}" />
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="wrtTypeNm" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="docContsCn" class="control-label col-sm-2">
                                <span class="mandatory">*</span> 내용
                            </label>
                            <div class="col-sm-10">
                                <div id="contentbuilderBlock" class="block row" <c:if test="${dataVo.wrtTypeNm ne '2030'}">style="display:none;"</c:if>>
                                    <div class="col-sm-12">
                                        <button type="button" id="contentbuilderBtn" class="btn btn-xs btn-info">컨텐츠빌더를 이용하여 편집하기</button>
                                    </div>
                                </div>
                                <div id="textareaBlock" class="block row">
                                    <div class="col-sm-12">
                                        <textarea name="docContsCn" id="docContsCn" rows="15" cols="80" class="form-control" placeholder="장문내용">${dataVo.docContsCn}</textarea>
                                        <c:if test="${dataVo.wrtTypeNm eq '2020'}">
                                            <script type="text/javascript">
                                                //<![CDATA[
                                                /* 공통 초기화 기능 */
                                                $(document).ready(function() {
                                                    /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
                                                    editor = $("#docContsCn").ckeditor({
                                                        toolbar : toolbar_config.default_toolbar
                                                    }).editor;
                                                });
                                                //]]>
                                            </script>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class=" callout callout-info fade in">
                                            <h5>링크 스크립트 사용 예제</h5>
                                            <dl style="margin: 0;">
                                                <dt class="text-info">닫은 후 부모 창 이동 :</dt>
                                                <dd>&lt;a href="링크주소" onclick="opPopupParent(this);return false;" title="팝업을 닫고, 부모 창으로 이동"&gt;&lt;img src="파일경로" alt="이미지설명" /&gt;&lt;/a&gt;</dd>
                                                <dt class="text-info">닫은 후 새 창 이동 :</dt>
                                                <dd>&lt;a href="링크주소" onclick="opPopupCloseWin(this);return false;" title="팝업을 닫고, 새 창으로 이동"/&gt;&lt;img src="파일경로" alt="이미지설명" /&gt;&lt;/a&gt;</dd>
                                                <dt class="text-info">새 창 이동 :</dt>
                                                <dd>&lt;a href="링크주소" onclick="opPopupWin(this);return false;" title="팝업을 유지하고, 새 창으로 이동"/&gt;&lt;img src="파일경로" alt="이미지설명" /&gt;&lt;/a&gt;</dd>
                                            </dl>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
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
                                        <op:fileUpload view="basic" name="fileSns" count="3" size="3M" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="useYnY" class="control-label col-sm-2"> 사용여부</label>
                            <div class="col-sm-10">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    예
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    아니오
                                </label>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <valid:msg name="useYn" />
                                        <div class="help-block">
                                            <span class="text-danger"><strong>주의 !</strong></span> <span class="text-info">사용여부가 <code>예</code>인 경우 시작일을 다시한번 확인하세요. 저장 후 미리보기로 확인 하세요.
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 버튼 -->
                <div class="row">
                    <div class="col-sm-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success">저장</button>
                            <button type="button" class="btn btn-primary" onclick="opList('BD_selectPopupList.do');">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>