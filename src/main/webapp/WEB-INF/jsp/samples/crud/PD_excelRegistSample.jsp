<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>엑셀 일괄등록 샘플</title>

    <op:jsTag type="libs" items="colorbox, highlight, ui"/>
    <op:jsTag type="intra" items="ui, opform" />

    <!-- 기능별 스크립트 정의 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/samples/crud/samples.crud.js"></script>

    <script type="text/javascript">
        //<![CDATA[

        $(document).ready(function() {
        });

        var opSubmit = function(){
            //submit script
            alert("opSubmit 스크립트 넣으시면됨.");
        };

        var opDeleteSelected = function(){
            if ($("#dataForm input[name=chkbox]:checked").length < 1) {
                alert("선택하신 목록이 없습니다.");
                return false;
            } else {
                $("#dataForm input[name=chkbox]:checked").each(function(i) {
                    var chkVal = $(this).val();
                    $("#dataForm input[name=chkbox]").each(function(el) {
                        if (chkVal == $(this).val()) {
                            $(this).parent().parent().parent().find("tr").eq(el).remove();
                        }
                    })
                })
            }
        };
        //]]>
    </script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 일괄등록할 엑셀을 선택하여 주십시오.
    </div>
    <c:set value="구매구분,대분류,,1월,2월,3월,1분기 소계,4월,5월,6월,2분기 소계,7월,8월,9월,3분기 소계,10월,11월,12월,4분기 소계,총계" var="headerNm" />
    <c:set value="cel1,cel2,cel3,cel4,cel5,cel6,cel7,cel7,cel8,cel9,cel10,cel11,cel12,cel13,cel14,cel15,cel16,cel17,cel18,cel19" var="headerId" />
    <c:set value="L,M,L,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S" var="columnSize" /><!-- s,m,l -->
    <op:excelToTable headerId="${headerId}" headerNm="${headerNm}" size="${columnSize}" headerLineCnt="3"  />

</body>
</html>