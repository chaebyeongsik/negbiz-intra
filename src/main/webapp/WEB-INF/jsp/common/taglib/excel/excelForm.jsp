<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function() {
        /* 엑셀출력 */
        $("#${btnId}").click(function() {
            if ($("#excelForm").length > 0) {
                $("#excelForm").remove();
            }

            var excelForm = "<form name='excelForm' id='excelForm' method='post' action='/component/excel/download/ND_excelDownload.do'>";
            excelForm += "<input type='hidden' name='excelKey' id='excelKey' value='${excelKey}' />";
            excelForm += "<input type='hidden' name='excelFileNm' id='excelFileNm' value='${excelFileNm}' />";
            $("body").append(excelForm);

            //검색조건별로 출력하고 싶은경우 파라메터 추가
            if ("Y" == "${searchAt}") {
                $("#dataForm [name^='" + OpConfig.prefixSearchParam + "']").each(function() {
                    var param = "<input type='hidden' name='" + $(this).attr("name") + "' value='" + $(this).val() + "' />";
                    $("#excelForm").append(param);
                })
            }

            $("#excelForm").submit();
        });
    });
    //]]>
</script>
