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

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>일괄권한지정</title>

<op:jsTag type="intra" items="opvalidate" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mngr/js/mngr.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });
    
    var opUpdateAuthorAsgn = function () {
        var selectedPicId = new Array;
        $("input[name=authrtCdId]:checked").each(function(i) {
            selectedPicId[i] = $(this).val();
        });
        
        if (selectedPicId.length == 0) {
            alert("지정하고자하는 권한을 선택해 주세요.");
            return;
        }
        
        $.ajax({
            url : "<c:url value="/intra/mngr/ND_updateAuthorAsgn.do" />",
            type : "POST",
            data : {
                picId : $("#picId").val(),
                picIds : $("#picIds").val(),
                authrtCdIds : selectedPicId.join(",")
            },
            dataType : "json",
            success : function(response) {
                opJsonMsg(response);
                if (response.result) {
                    parent.opSuccessMsg(response.message);
                    parent.opMngrList();
                    opCloseWin();
                } else {
                    opErrorMsg(response.message);
                }
            }
        });
        
    }
    
    //]]>
</script>
</head>
<body>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="icon-people"></i> 지정하고자 하는 권한을 선택한 후 저장버튼을 클릭하세요.
            </h6>
        </div>
        
        <form name="dataForm" id="dataForm" method="post" action="ND_updateAuthorAppn.do" class="form-inline">
            <input type="hidden" id="picId" name="picId" value="${param.picId}" />
            <input type="hidden" id="picIds" name="picIds" value="${param.picIds}" />
            <input type="hidden" id="authrtCdIds" name="authrtCdIds" value="" />
            <div class="panel-body">
                <div class="alert alert-info block-inner text-left">목록 클릭시 지정된 권한 목록을 보실 수 있습니다.</div>
                <div class="block-inner">
                    <c:forEach items="${dataList}" var="authorList" varStatus="status">
                        <div class="form-group">
                            <div class="col-md-4">
                                <label for="authrtCdId${status.index}" class="checkbox-inline">
                                    <input type="checkbox" name="authrtCdId" id="authrtCdId${status.index}" value="${authorList.authrtCdId}" class="styled"
                                        <c:forEach items="${authorAsgnList}" var="asgnlist" varStatus="status">
                                            <c:if test="${authorList.authrtCdId eq asgnlist.authrtCdId}">checked="checked"</c:if>
                                        </c:forEach>
                                     />
                                    ${authorList.authrtNm}
                                </label>
                                <a href="#authorPop" onclick="opAuthorPop('${authorList.authrtCdId}', '${authorList.authrtNm}');">[목록]</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
                <c:if test="${fn:indexOf(param.picIds, ',') > -1}">
                    <div class="block col-sm-12">
                        <i class="icon-info"></i> 개인별로 표시된 권한 외에 추가로 지정하고자 할 때는 개인별권한 지정관리를 이용하세요.
                    </div>
                </c:if>
                <!-- 버튼 -->
                <div class="block clearfix pull-right">
                    <div class="btn-group">
                        <button type="button" class="btn btn-info" onclick="opUpdateAuthorAsgn();">저장</button>
                    </div>
                </div>
            </div>
        </form>
        
    </div>

</body>
</html>