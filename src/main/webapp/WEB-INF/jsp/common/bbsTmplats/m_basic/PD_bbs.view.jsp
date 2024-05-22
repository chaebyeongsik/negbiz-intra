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
<%@ taglib uri="http://www.openworks.kr/jsp/bbs" prefix="bbs"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시판 관리</title>

<op:jsTag type="intra" items="opform" />
<op:jsTag type="libs" items="form, cookie" />

<link rel="stylesheet" type="text/css" href="/resources/libs/jcarousel/css/jcarousel.connected-carousels.css">
<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbs/js/bbs.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });
    
    //]]>
</script>
</head>
<body>
    <%-- 게시판 항목을 사용이 용이하게 변수를 할당하여 가져옴 --%>
    <c:set var="column" value="${bbsConfigVo.bbsItemMap}" />

    <form name="dataForm" id="dataForm" method="post" action="PD_selectBbs.do">
        <input type="hidden" name="bbsDocNo" id="bbsDocNo" value="" />
        <input type="hidden" name="dtrn" id="dtrn" value="" />
        <input type="hidden" name="mngrDelYn" id="mngrDelYn" value="" />
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />
    </form>
    <form name="dataForm" id="dataForm" method="post" action="PD_selectBbsList.do">
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />
    </form>
    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="게시판 정보입니다.">
            <caption class="hidden">게시판 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col width="35%" />
                <col width="15%" />
                <col />
            </colgroup>
            <c:if test="${column.ttl.inqIndctYn eq 'Y'}" >
            <thead>
                <tr>
                    <th colspan="4">
                        <div class="col-md-9">
                            <c:if test="${column.clsfNo.inqIndctYn eq 'Y'}" >
                            <span class="text-info">
                                [<bbs:clsfNo type="view" obj="${dataVo}" />
                                <c:if test="${column.lwrkClsfSn.inqIndctYn eq 'Y'}" >
                                     / <bbs:lwrkClsfSn type="view" obj="${dataVo}" />
                                </c:if>
                                ]
                            </span>
                            </c:if>
                            <bbs:ttl type="view" obj="${dataVo}" />
                        </div>
                    </th>
                </tr>
            </thead>
            </c:if>
            <tbody>
                <tr>
                    <c:if test="${column.bbsSn.inqIndctYn eq 'Y'}" >
                    <th><bbs:bbsSn type="text" /></th>
                    <td><bbs:bbsSn type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.bbsDocNo.inqIndctYn eq 'Y'}" >
                    <th><bbs:bbsDocNo type="text" /></th>
                    <td><bbs:bbsDocNo type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.rfrncDocNo.inqIndctYn eq 'Y'}" >
                    <th><bbs:rfrncDocNo type="text" /></th>
                    <td><bbs:rfrncDocNo type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.notice.inqIndctYn eq 'Y'}" >
                    <th><bbs:notice type="text" /></th>
                    <td><bbs:notice type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.ntcBgngDt.inqIndctYn eq 'Y'}" >
                    <th><bbs:ntcBgngDt type="text" /></th>
                    <td><bbs:ntcBgngDt type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.ntcEndDt.inqIndctYn eq 'Y'}" >
                    <th><bbs:ntcEndDt type="text" /></th>
                    <td><bbs:ntcEndDt type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${bbsConfigVo.thmbUseYn eq 'Y'}" >
                    <th><bbs:thumbnail type="text" /> </th>
                    <td><bbs:thumbnail type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.docContsCn.inqIndctYn eq 'Y'}" >
                    <th><bbs:docContsCn type="text" /></th>
                    <td><bbs:docContsCn type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.clsfNo.inqIndctYn eq 'Y'}" >
                    <th><bbs:clsfNo type="text" /></th>
                    <td>
                        <bbs:clsfNo type="view" obj="${dataVo}" />
                        <c:if test="${column.lwrkClsfSn.inqIndctYn eq 'Y'}" >
                             / <bbs:lwrkClsfSn type="view" obj="${dataVo}" />
                        </c:if>
                    </td>
                    </c:if>
                    <c:if test="${column.mainCn.inqIndctYn eq 'Y'}" >
                    <th><bbs:mainCn type="text" /></th>
                    <td><bbs:mainCn type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.ansDtlCn.inqIndctYn eq 'Y'}" >
                    <th><bbs:ansDtlCn type="text" /></th>
                    <td><bbs:ansDtlCn type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.ansDt.inqIndctYn eq 'Y'}" >
                    <th><bbs:ansDt type="text" /></th>
                    <td><bbs:ansDt type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.file.inqIndctYn eq 'Y'}" >
                    <th><bbs:file type="text" /></th>
                    <td><bbs:file type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.deptCdId.inqIndctYn eq 'Y'}" >
                    <th><bbs:deptCdId type="text" /></th>
                    <td><bbs:deptCdId type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.deptNm.inqIndctYn eq 'Y'}" >
                    <th><bbs:deptNm type="text" /></th>
                    <td><bbs:deptNm type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.picId.inqIndctYn eq 'Y'}" >
                    <th><bbs:picId type="text" /></th>
                    <td><bbs:picId type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.picNm.inqIndctYn eq 'Y'}" >
                    <th><bbs:picNm type="text" /></th>
                    <td><bbs:picNm type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.cpyrht.inqIndctYn eq 'Y'}" >
                    <th><bbs:cpyrht type="text" /></th>
                    <td><bbs:cpyrht type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.tagNms.inqIndctYn eq 'Y'}" >
                    <th><bbs:tagNms type="text" /></th>
                    <td><bbs:tagNms type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.inqCnt.inqIndctYn eq 'Y'}" >
                    <th><bbs:inqCnt type="text" /></th>
                    <td><bbs:inqCnt type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.dclrCnt.inqIndctYn eq 'Y'}" >
                    <th><bbs:dclrCnt type="text" /></th>
                    <td><bbs:dclrCnt type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.rcmdtnCnt.inqIndctYn eq 'Y'}" >
                    <th><bbs:rcmdtnCnt type="text" /></th>
                    <td><bbs:rcmdtnCnt type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.stsfdgEvl.inqIndctYn eq 'Y'}" >
                    <th><bbs:stsfdgEvl type="text" /></th>
                    <td><bbs:stsfdgEvl type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.cntnBrwsrNm.inqIndctYn eq 'Y'}" >
                    <th><bbs:cntnBrwsrNm type="text" /></th>
                    <td><bbs:cntnBrwsrNm type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.ipAddr.inqIndctYn eq 'Y'}" >
                    <th><bbs:ipAddr type="text" /></th>
                    <td><bbs:ipAddr type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.userIdntfNm.inqIndctYn eq 'Y'}" >
                    <th><bbs:userIdntfNm type="text" /></th>
                    <td><bbs:userIdntfNm type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.rgtrId.inqIndctYn eq 'Y'}" >
                    <th><bbs:rgtrId type="text" /></th>
                    <td><bbs:rgtrId type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.rgtrNm.inqIndctYn eq 'Y'}" >
                    <th><bbs:rgtrNm type="text" /></th>
                    <td><bbs:rgtrNm type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.regDt.inqIndctYn eq 'Y'}" >
                    <th><bbs:regDt type="text" /></th>
                    <td><bbs:regDt type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.pswd.inqIndctYn eq 'Y'}" >
                    <th><bbs:pswd type="text" /></th>
                    <td><bbs:pswd type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.mdfrId.inqIndctYn eq 'Y'}" >
                    <th><bbs:mdfrId type="text" /></th>
                    <td><bbs:mdfrId type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.updtDt.inqIndctYn eq 'Y'}" >
                    <th><bbs:updtDt type="text" /></th>
                    <td><bbs:updtDt type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.flctnColCn1.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn1 type="text" /></th>
                    <td><bbs:flctnColCn1 type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.flctnColCn2.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn2 type="text" /></th>
                    <td><bbs:flctnColCn2 type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.flctnColCn3.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn3 type="text" /></th>
                    <td><bbs:flctnColCn3 type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.flctnColCn4.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn4 type="text" /></th>
                    <td><bbs:flctnColCn4 type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.flctnColCn5.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn5 type="text" /></th>
                    <td><bbs:flctnColCn5 type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.flctnColCn6.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn6 type="text" /></th>
                    <td><bbs:flctnColCn6 type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.flctnColCn7.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn7 type="text" /></th>
                    <td><bbs:flctnColCn7 type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.flctnColCn8.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn8 type="text" /></th>
                    <td><bbs:flctnColCn8 type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${column.flctnColCn9.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn9 type="text" /></th>
                    <td><bbs:flctnColCn9 type="view" obj="${dataVo}" /></td>
                    </c:if>
                    <c:if test="${column.flctnColCn10.inqIndctYn eq 'Y'}" >
                    <th><bbs:flctnColCn10 type="text" /></th>
                    <td><bbs:flctnColCn10 type="view" obj="${dataVo}" /></td>
                    </c:if>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- 버튼 -->
    <div class="row block">
        <div class="col-sm-12 btn-group">
            <div class="pull-right">
                <bbs:button type="view" obj="${dataVo}" />
            </div>
        </div>
    </div>
    <!-- //버튼 -->

    <bbs:brftrSntnc type="view" obj="${dataVo}" />

    <bbs:cmnt type="view" obj="${dataVo}" />

</body>
</html>
