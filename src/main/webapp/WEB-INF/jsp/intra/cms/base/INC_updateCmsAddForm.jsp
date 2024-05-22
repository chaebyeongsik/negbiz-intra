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
<title>사용자메뉴 관리</title>

<op:jsTag type="intra" items="opvalidate, ui" />
<op:jsTag type="libs" items="form" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if (validate()) {
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
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    // 이미지 삭제
    var deleteImage = function(obj, id) {
        $("#" + id).val("");
        $(obj).parent().parent().remove();
    };
    //]]>
</script>
</head>
<body>
    <!--     <div class="help-block text-right"> -->
    <!--         <span class="mandatory">*</span> 항목은 필수 입력항목입니다. -->
    <!--     </div> -->

    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_updateCmsAdd.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="contsSn" id="contsSn" value="${dataVo.contsSn}" />
        <input type="hidden" name="siteSn" id="siteSn" value="${dataVo.siteSn}" />
        <input type="hidden" name="userMenuEngNm" id="userMenuEngNm" value="${dataVo.userMenuEngNm}" />

        <div class="panel panel-default" style="border-top-color: #ffffff;">
            <div class="panel-body">

                <div class="form-group">
                    <label for="strtContsCn" class="control-label col-sm-2">상단컨텐츠 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="strtContsCn" id="strtContsCn" rows="5" cols="80" class="form-control" placeholder="상단컨텐츠">${dataVo.strtContsCn}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="strtContsCn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="endContsCn" class="control-label col-sm-2">하단컨텐츠 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="endContsCn" id="endContsCn" rows="5" cols="80" class="form-control" placeholder="하단컨텐츠">${dataVo.endContsCn}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="endContsCn" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="cssFileTmp" class="control-label col-sm-2">CSS파일</label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:fileUpload view="basic" name="cssFileTmp" count="1" size="3K" exts="css" />
                                <input type="hidden" name="cssFileNm" id="cssFileNm" value="${dataVo.cssFileNm}" />
                            </div>
                        </div>
                        <c:if test="${not empty dataVo.cssFileNm}">
                            <div class="row">
                                <div class="col-sm-10">
                                    <a href="${dataVo.cssFileNm}" target="_blank" title="다운로드">${dataVo.cssFileNm} 다운로드</a>
                                    <button type="button" class="btn btn-danger btn-xs" onclick="deleteImage(this, 'cssFileNm');">삭제</button>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="sjImageFile" class="control-label col-sm-2">제목이미지 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:fileUpload view="basic" name="sjImageFile" count="1" size="1M" exts="jpg,jpeg,gif,png" />
                                <input type="hidden" name="userMenuImgNm" id="userMenuImgNm" value="${dataVo.userMenuImgNm}" />
                            </div>
                        </div>
                        <c:if test="${not empty dataVo.userMenuImgNm}">
                            <div class="row">
                                <div class="col-sm-10">
                                    <img alt="${dataVo.menuNm} 타이틀" src="${dataVo.userMenuImgNm}">
                                    <button type="button" class="btn btn-danger btn-xs" onclick="deleteImage(this, 'userMenuImgNm');">삭제</button>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="menuTtlImgNmFile" class="control-label col-sm-2">메뉴제목이미지 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:fileUpload view="basic" name="menuTtlImgNmFile" count="1" size="1M" exts="jpg,jpeg,gif,png" />
                                <input type="hidden" name="menuTtlImgNm" id="menuTtlImgNm" value="${dataVo.menuTtlImgNm}" />
                            </div>
                        </div>
                        <c:if test="${not empty dataVo.menuTtlImgNm}">
                            <div class="row">
                                <div class="col-sm-10">
                                    <img alt="${dataVo.menuNm} 메뉴 타이틀" src="${dataVo.menuTtlImgNm}">
                                    <button type="button" class="btn btn-danger btn-xs" onclick="deleteImage(this, 'menuTtlImgNm');">삭제</button>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuOnImageFile" class="control-label col-sm-2">메뉴온이미지 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:fileUpload view="basic" name="menuOnImageFile" count="1" size="1M" exts="jpg,jpeg,gif,png" />
                                <input type="hidden" name="menuOnImgNm" id="menuOnImgNm" value="${dataVo.menuOnImgNm}" />
                            </div>
                        </div>
                        <c:if test="${not empty dataVo.menuOnImgNm}">
                            <div class="row">
                                <div class="col-sm-10">
                                    <img alt="${dataVo.menuNm} 메뉴온이미지" src="${dataVo.menuOnImgNm}">
                                    <button type="button" class="btn btn-danger btn-xs" onclick="deleteImage(this, 'menuOnImgNm');">삭제</button>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuOffImageFile" class="control-label col-sm-2">메뉴오프이미지 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <op:fileUpload view="basic" name="menuOffImageFile" count="1" size="1M" exts="jpg,jpeg,gif,png" />
                                <input type="hidden" name="menuOffImgNm" id="menuOffImgNm" value="${dataVo.menuOffImgNm}" />
                            </div>
                        </div>
                        <c:if test="${not empty dataVo.menuOffImgNm}">
                            <div class="row">
                                <div class="col-sm-10">
                                    <img alt="${dataVo.menuNm} 메뉴오프이미지" src="${dataVo.menuOffImgNm}">
                                    <button type="button" class="btn btn-danger btn-xs" onclick="deleteImage(this, 'menuOffImgNm');">삭제</button>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

</body>
</html>