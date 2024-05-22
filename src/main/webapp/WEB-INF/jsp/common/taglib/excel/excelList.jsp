<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<op:jsTag type="libs" items="form" />
<script type="text/javascript">
//<![CDATA[
var opExcelToTable = function(){
    if($("input:file").val() == '' || $("input:file").val() == undefined) {
        opWarningMsg("파일을 선택해주세요.");
        return;
    }
    $("#dataForm").ajaxSubmit({
        url     : $(this).attr("action"),
        dataType: "html",
        type    : "POST",
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        success: opMakeHtml
    });
};

var opMakeHtml = function(obj){
    $("#contentTable").html(obj);
};
//]]>
</script>
<form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="/component/excel/upload/ND_excelToTable.do">
    <div class="form-group">
        <div class="col-md-10">
            <op:fileUpload exts="xlsx,xls"/>
        </div>
    </div>
    <!-- 컬럼명 -->
    <c:forEach items="${headerNm}" var="headerNmVo" varStatus="vst">
        <input type="hidden" value="${headerNmVo}" name="headerNm" />
    </c:forEach>
    <!-- 컬럼ID -->
    <c:forEach items="${headerId}" var="headerIdVo" varStatus="vst">
        <input type="hidden" value="${headerIdVo}" name="headerId" />
    </c:forEach>
    <!-- 컬럼사이즈 -->
    <c:forEach items="${inputSize}" var="sizeVo" varStatus="vst">
        <input type="hidden" value="${sizeVo}" name="size" />
    </c:forEach>
    <input type="hidden" value="${rowNo}" name="rowNo" />
    <input type="hidden" value="${inputUseYn}" name="inputUseYn" />
    <input type="hidden" value="${headerLineCnt}" name="headerLineCnt" />
    <!-- 버튼 -->
    <div class="row block">
        <div class="col-md-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-success" onclick="opExcelToTable();">불러오기</button>
            </div>
        </div>
    </div>
    <div id="contentTable">
    </div>
</form>
        <!-- //버튼 -->