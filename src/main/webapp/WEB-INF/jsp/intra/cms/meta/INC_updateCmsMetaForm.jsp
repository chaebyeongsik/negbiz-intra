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
<title>사용자메뉴메타 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="tags" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/cms/js/cms.common.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            if (validate()) {
                $(this).ajaxSubmit({
                    dataType : "json",
                    success : function(response) {
                        opJsonMsg(response);
                        $tabs.refresh();
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
            return false;
        });

        // 키워드 Tags-input
        $('#userMenuSrchNm').tagsInput({width:'100%', defaultText:'키워드 입력'});

        // 사용자메뉴로봇 Tags-input
        $('#siteSrchNm').tagsInput({width:'100%', defaultText:'지시어 입력'});
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        return true;
    };

    //]]>
</script>
</head>
<body>

    <form name="dataForm" id="dataForm" method="post" action="<c:url value="/intra/cms/meta/ND_updateCmsMeta.do" />" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />

        <input type="hidden" name="siteSn" id="siteSn" value="${paramMap.q_siteSn}" />
        <input type="hidden" name="userMenuEngNm" id="userMenuEngNm" value="${paramMap.q_userMenuEngNm}" />
        <%-- 저작권 자동 생성에서 사용되는 필드 --%>
        <input type="hidden" name="koglTitle" id="ttl" value="${dataVo.menuNm}" />

        <div class="panel panel-default" style="border-top-color: #ffffff;">
            <div class="panel-body">
                <div class="form-group">
                    <label for="userMenuSrchNm" class="control-label col-sm-2">키워드 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="text" name="userMenuSrchNm" id="userMenuSrchNm" value="${dataVo.userMenuSrchNm}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="userMenuSrchNm" />
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item text-info"><code>&lt;meta name="keywords" content="키워드" /&gt;</code> 태그로 사용되며,  <code>검색</code>에 사용될수 있습니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userMenuExpln" class="control-label col-sm-2">설명 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="text" name="userMenuExpln" id="userMenuExpln" value="${dataVo.userMenuExpln}" class="form-control" placeholder="설명" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="userMenuExpln" />
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item text-info"><code>&lt;meta name="description" content="설명" /&gt;</code> 태그로 사용됩니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="siteSrchNm" class="control-label col-sm-2">사용자메뉴로봇 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="text" name="siteSrchNm" id="siteSrchNm" value="${dataVo.siteSrchNm}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item text-info"><code>&lt;meta name="robots" content="noindex" /&gt;</code> 태그로 사용됩니다.</li>
                                        <li class="list-group-item text-info"><code>index</code> : 검색엔진에서 이 페이지를 저장하도록 합니다.(기본값)</li>
                                        <li class="list-group-item text-info"><code>follow</code> : 검색엔진에서 이페이지 내의 링크 페이지를 검색하도록 합니다.(기본값)</li>
                                        <li class="list-group-item text-info"><code>noindex</code> : 검색엔진에서 이 페이지를 저장하지 않도록 합니다.</li>
                                        <li class="list-group-item text-info"><code>nofollow</code> : 검색엔진에서 이페이지 내의 링크 페이지를 검색하지 않도록 합니다.</li>
                                        <li class="list-group-item"><a href="https://developers.google.com/webmasters/control-crawl-index/docs/robots_meta_tag?csw=1" target="_blank">Google 도움말 참조(시간이 지나면 변경될 수 있습니다.)</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="menuTagCn" class="control-label col-sm-2">메타태그 </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <textarea name="menuTagCn" id="menuTagCn" rows="5" cols="80" class="form-control" placeholder="메타태그입력">${dataVo.menuTagCn}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="menuTagCn" />
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item text-info">페이스북 예 :<code>&lt;meta property="og:속성" content=""/&gt;</code></li>
                                        <li class="list-group-item text-info">트위터 예 : <code>&lt;meta name="twitter:속성" content=""&gt;</code>
                                        </li>
                                        <li class="list-group-item text-info">위의 예와 같은 SNS 연계등의 메타테그를 별도로 작성할 수 있습니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cprgtUseYnY" class="control-label col-sm-2">
                        저작권사용여부 <span class="mandatory">*</span>
                    </label>
                    <div class="col-sm-10">
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="cprgtUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="cprgtUseYn" id="cprgtUseYnY" value="Y" onclick="toggleCpyrhtUseAt(this);"
                                        <c:if test="${dataVo.cprgtUseYn eq 'Y'}">checked="checked"</c:if> class="styled"
                                    />
                                    예
                                </label>
                                <label for="cprgtUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="cprgtUseYn" id="cprgtUseYnN" value="N" onclick="toggleCpyrhtUseAt(this);"
                                        <c:if test="${empty dataVo.cprgtUseYn or dataVo.cprgtUseYn eq 'N'}">checked="checked"</c:if> class="styled"
                                    />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <valid:msg name="cprgtUseYn" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group cpyrht-group" <c:if test="${dataVo.cprgtUseYn ne 'Y'}"> style="display:none;"</c:if>>
                    <label for="authrIndict" class="control-label col-sm-2">저작권설정 </label>
                    <div class="col-sm-10">
                        <%-- 공공누리 저작권 --%>
                        <c:import url="/WEB-INF/jsp/common/include/incKogl.jsp"></c:import>
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
                </c:if>
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