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
<%@ taglib uri="http://www.openworks.kr/jsp/bbs" prefix="bbs"%>

    <c:if test="${bbsConfigVo.bfrAftrDocIndctYn eq 'Y' and bbsObj.ntcPstYn ne 'Y'}">
        <!-- 이전/다음글 표시 -->
<!--         <form name="viewForm" id="viewForm" method="get" action="PD_selectBbs.do" class="form-inline"> -->
<%--             <op:pagerParam view="view" ignores="" /> --%>
            <c:set var="prevVo" value="${bbsObj.prevVo}" />
            <c:set var="nextVo" value="${bbsObj.nextVo}" />
            
            <table class="table table-responsive" summary="이전/다음글보기 페이지입니다.">
                <caption class="hidden">이전/다음글보기 페이지</caption>
                <colgroup>
                    <col width="10%" />
                    <col />
                </colgroup>
                <tbody>
                    <tr>
                        <th class="text-right"><label for="prev" class="label-controll">이전글 </label></th>
                        <td>
                            <div class="col-sm-12">
                                <bbs:ttl type="list" obj="${prevVo}"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th class="text-right"><label for="next" class="label-controll">다음글 </label></th>
                        <td>
                            <div class="col-sm-12">
                                <bbs:ttl type="list" obj="${nextVo}"/>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
<!--         </form> -->
        <!-- //이전/다음글 표시 -->
    </c:if>