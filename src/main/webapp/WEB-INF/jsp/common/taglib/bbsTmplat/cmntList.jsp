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
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <c:if test="${bbsConfigVo.opnnDocYn eq 'Y' and bbsObj.ntcPstYn ne 'Y'}">
        <script type="text/javascript">
            //<![CDATA[
            $(document).ready(function() {
                opLoadCmntLayout();
            });
            <%-- 실제 댓글 화면을 로드 --%>
            var opLoadCmntLayout = function() {
                var params = {
                    q_bbsSn : "${bbsObj.bbsSn}",
                    q_bbsDocNo : "${bbsObj.bbsDocNo}"
                };
                var href = "<c:url value="/intra/bbs/ND_selectBbsCmnt.do"/>";
                $("#opCmntLayout").load(href, params, function(responseTxt, statusTxt, response) {
                    if(statusTxt == "success") {
                    	console.log(response);
                    } else {
                        opSysErrorMsg(responseTxt);
                    }
                });
            };
            //]]>
        </script>
        <div class="row well">
            <div id="opCmntLayout" class="col-md-12"></div>
        </div>
    </c:if>