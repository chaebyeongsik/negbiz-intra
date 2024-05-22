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
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>권한 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/author/js/author.js"></script>

<script type="text/javascript">
    //<![CDATA[
    <valid:script type="msgbox" />
    $(document).ready(function() {
        var pAuthrtCdId = '${param.authrtCdId}';
        if(pAuthrtCdId != ''){
            opUpdateAuthorForm(pAuthrtCdId);
        }

        $(".chargerCnt").each(function () {
            var authrtCdId = $(this).attr("id");
            $(this).opmodal({
                width : 760,
                height : 500,
                href : "PD_selectMngrInAuthorList.do?authrtCdId=" + authrtCdId
            });
        });
    });

    //]]>
</script>
</head>
<body>
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-list2"></i> 권한목록
                    </h6>
                </div>
                <ul class="list-group">
                <c:forEach var="authorVo" items="${dataList}" varStatus="vst">
                    <li class="list-group-item has-button">
                        <a href="#" onclick="opUpdateAuthorForm('${authorVo.authrtCdId}'); return false;">${authorVo.authrtNm}</a>
                        <a href="#" class="chargerCnt" id="${authorVo.authrtCdId}">
<%--                         <a href="#" onclick="opChargerInAuthor(this, '${authorVo.authrtCdId}'); return false;" class="chargerCnt" id="${authorVo.authrtCdId}"> --%>
                            <strong class="text-danger">[${authorVo.authorCnt}명]</strong>
                        </a>
                        <button  onclick="opMenuAsgnForm('${authorVo.authrtCdId}', '${authorVo.authrtNm}');" class="btn-xs btn btn-default">메뉴</button>
                    </li>
                </c:forEach>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12 btn-group">
                    <div class="pull-right">
                        <button type="button" id="insertFormBtn" class="btn btn-info" onclick="opInsertAuthorForm();">권한추가</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-libreoffice"></i> 권한상세
                    </h6>
                </div>
                <div id="Author_VIEW" class="panel-body">
                    <div class="callout callout-danger fade in" <c:if test="${not empty param.authrtCdId}">style="display: none;"</c:if>>
                        <h5>항목이 선택되지 않았습니다.</h5>
                        <p>좌측 권한 목록에서 선택하시거나 추가 버튼을 클릭하세요.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>