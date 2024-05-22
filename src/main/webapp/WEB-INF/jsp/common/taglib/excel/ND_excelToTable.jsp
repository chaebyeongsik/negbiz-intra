<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){
    //대상 체크박스 전체 선택 및 색상변경
    $("input[name=chk-all]").click(function(){
        var isChecked = this.checked;
        $("input[name=chkbox]").prop("checked", isChecked);
        if(isChecked) {
            $("tbody :checkbox").parent().parent().addClass("info");
        } else {
            $("tbody :checkbox").parent().parent().removeClass("info");
        }
    });
    
    $("input[name=chkbox]").click(function() {

        if($(this).is(":checked")) {
            $(this).parent().parent().addClass("info");
        } else {
            $(this).parent().parent().removeClass("info");
        }
    });
});
//]]>
</script>
<div class="block table-responsive">
    <table class="table table-bordered table-striped table-hover op-table-list" summary="엑셀데이터 불러오기 목록">
        <caption class="hidden">엑셀데이터 불러오기 목록</caption>
        <colgroup>
            <c:if test="${fn:toUpperCase(inputUseYn) eq 'Y'}">
                <col width="30px" />
            </c:if>
            <c:choose>
                <c:when test="${not empty sizes}">
                    <c:forEach items="${sizes}" var="sizeVo" varStatus="vst">
                        <c:choose>
                            <c:when test="${fn:toUpperCase(sizeVo) eq 'L'}">
                                <c:set value="160px" var="colSize" />
                            </c:when>
                            <c:when test="${fn:toUpperCase(sizeVo) eq 'S'}">
                                <c:set value="40px" var="colSize" />
                            </c:when>
                            <c:otherwise>
                                <c:set value="60px" var="colSize" />
                            </c:otherwise>
                        </c:choose>
                        <col width="${colSize}" />
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${headerNms}" var="hdNm" varStatus="vst">
                        <col width="*" />
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </colgroup>
        <thead>
            <tr>
                <c:if test="${fn:toUpperCase(inputUseYn) eq 'Y'}">
                    <th><input type="checkbox" value="Y" name="chk-all" id="chk-all" /></th>
                </c:if>
                <c:forEach items="${headerNms}" var="hdNm" varStatus="vst">
                    <th><c:out value="${hdNm}" /></th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${excelData}" var="columnList" varStatus="vst">
            <tr>
                <c:if test="${fn:toUpperCase(inputUseYn) eq 'Y'}">
                    <td class="text-center"><input type="checkbox" name="chkbox" value="${vst.count}" id="chkbox_${vst.count}" /></td>
                </c:if>
                <c:forEach items="${columnList}" var="columnInfo" varStatus="status">
                <td class="text-center">
                <c:choose>
                    <c:when test="${fn:toUpperCase(columnInfo.inputSize) eq 'S'}">
                        <c:set value="5" var="inputSize" />
                    </c:when>
                    <c:when test="${fn:toUpperCase(columnInfo.inputSize) eq 'M'}">
                        <c:set value="15" var="inputSize" />
                    </c:when>
                    <c:otherwise>
                        <c:set value="30" var="inputSize" />
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${fn:toUpperCase(inputUseYn) eq 'N'}">
                        <c:out value="${columnInfo.value}" />
                        <input type="hidden" value="${columnInfo.value}" id="${columnInfo.inputId}_${vst.count}" name="${columnInfo.inputId}"
                               title="${columnInfo.inputNm}" size="${inputSize}" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" value="${columnInfo.value}" id="${columnInfo.inputId}_${vst.count}" name="${columnInfo.inputId}"
                               title="${columnInfo.inputNm}" size="${inputSize}" />
                    </c:otherwise>
                </c:choose>
                </td>
                </c:forEach>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="row block">
    <div class="col-md-12 btn-group">
        <c:if test="${fn:toUpperCase(inputUseYn) eq 'Y'}">
            <div class="pull-left">
                <button type="button" class="btn btn-danger" onclick="opDeleteSelected();">선택삭제</button>
            </div>
        </c:if>
        <div class="pull-right">
            <button type="button" class="btn btn-success" onclick="opSubmit();">일괄등록</button>
        </div>
    </div>
</div>