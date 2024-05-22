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
<%@ page import="zesinc.web.support.tag.bbsTmplat.support.BbsStyleSupport"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <c:choose>
        <c:when test="${bbsType eq 'list'}">
            평균 <span><fmt:formatNumber value="${bbsObj.stsfdgEvlAvrg}" type="percent" pattern="###.#" /></span>점 / <span>${bbsObj.dgstfnSumScr}</span>점 (<span>${bbsObj.dgstfnEvlCnt}</span>회)
        </c:when>
        <c:when test="${bbsType eq 'view'}">
            <div class="op-dgstfnEvlCnt">
                <form name="stsfdgForm" id="stsfdgForm" method="post" action="ND_updateStsfdgEvl.do">
                    <input type="hidden" name="bbsSn" id="bbsSn" value="${bbsObj.bbsSn}" />
                    <input type="hidden" name="bbsDocNo" id="bbsDocNo" value="${bbsObj.bbsDocNo}" />
                    <div id="stsfdgEvl">
                        평균 <span id="stsfdgScoreAvg"><fmt:formatNumber value="${bbsObj.stsfdgEvlAvrg}" type="percent" pattern="###.#" /></span>점 / <span id="stsfdgSum">${bbsObj.dgstfnSumScr}</span>점 (<span id="stsfdgCo">${bbsObj.dgstfnEvlCnt}</span>회)
                    </div>
                    <select name="dgstfnSumScr" id="dgstfnSumScr">
                        <option value="0">선택</option>
                        <option value="1">1점</option>
                        <option value="2">2점</option>
                        <option value="3">3점</option>
                        <option value="4">4점</option>
                        <option value="5">5점</option>
                        <option value="6">6점</option>
                        <option value="7">7점</option>
                        <option value="8">8점</option>
                        <option value="9">9점</option>
                        <option value="10">10점</option>
                    </select>
                    <input type="button" class="<%= BbsStyleSupport.BBS_INNER_BUTTON_CLASS %>" value="평점주기" onclick="opUpdateStsfdgEvl(this, '${bbsObj.bbsSn}', '${bbsObj.bbsDocNo}', '${bbsConfigVo.inqCntLmtHrCnt}');" />
                </form>
            </div>
        </c:when>
        <c:when test="${bbsType eq 'value'}">
            <fmt:formatNumber value="${bbsObj.stsfdgEvlAvrg}" type="percent" pattern="###.#" />
        </c:when>
        <c:when test="${bbsType eq 'text'}">
            ${bbsItemVo.colNm}
        </c:when>
        <c:when test="${bbsType eq 'label'}">
            <label for="${bbsItemVo.colId}" class="<%= BbsStyleSupport.BBS_LABEL_CLASS %>">
                <c:if test="${bbsItemVo.esntlYn eq 'Y'}">
                    <span class="<%= BbsStyleSupport.BBS_REQUIRE_CLASS %>">*</span>
                </c:if>
                ${bbsItemVo.colNm}
            </label>
        </c:when>
        <c:when test="${bbsType eq 'desc'}">
            ${bbsItemVo.bbsColExpln}
        </c:when>
        <c:when test="${bbsType eq 'form'}">
            <fmt:formatNumber value="${bbsObj.stsfdgEvlAvrg}" type="percent" pattern="###.#" />
        </c:when>
    </c:choose>

