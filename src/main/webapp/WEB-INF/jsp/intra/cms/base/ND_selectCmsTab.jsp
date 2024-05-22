<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<op:jsTag type="intra" items="ui" />
<script type="text/javascript">
    //<![CDATA[

     var $tabs;
     var options = {
         // 내용을 표시할 패널을 지정. 생략 가능
         tabContainer : "tabContainer",
         // 공통 파라미터를 지정할 수 있음. 생략 가능
         baseParam: {
             q_siteSn : "${paramMap.q_siteSn}",
             q_userMenuEngNm : "${paramMap.q_userMenuEngNm}",
             q_date : new Date()
         }
     };

     /* 공통 초기화 기능 */
    $(document).ready(function() {
        $tabs = $("#opTabs").opTabs(options);
    });

     // 현재 텝 컨텐츠 리로드
    var opReloadTab = function() {
        $tabs.refresh();
    };
    //]]>
</script>

<div id="opTabs" class="tabbable">
    <ul class="nav nav-tabs nav-justified">
        <li><a href="<c:url value="/intra/cms/base/INC_updateCmsBaseForm.do"/>">기본정보</a></li>
        <li><a href="<c:url value="/intra/cms/base/INC_updateCmsAddForm.do"/>">부가정보</a></li>
        <c:if test="${dataVo.menuType eq 'CONTENTS' }">
            <li><a href="<c:url value="/intra/cms/cntnts/INC_selectCmsCntntsList.do"/>">컨텐츠관리</a></li>
            <li><a href="<c:url value="/intra/cms/meta/INC_updateCmsMetaForm.do"/>">메타정보</a></li>
        </c:if>
        <c:if test="${dataVo.dgstfnIndctYn eq 'Y'}">
            <li><a href="<c:url value="/intra/cms/evl/INC_selectCmsEvlList.do"/>">만족도</a></li>
        </c:if>
<!--
        <li><a href="#">접속통계</a></li>
-->
    </ul>
</div>