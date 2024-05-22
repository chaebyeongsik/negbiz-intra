<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<script type="text/javascript">
    //<![CDATA[
    var btnIndex = 0;

    $(document).ready(function() {
        /* 도로명주소팝업 호출 */
        $(".${adresPopBtn}").click(function() {
            var thisObj = $(this).get(0);
            $(".${adresPopBtn}").each(function(idx){
                var eachObj = $(this).get(0);
                if(eachObj === thisObj){
                    btnIndex = idx;
                }
            });
            opAdresPopup();
        });
    });
    
    function opAdresPopup(){
        // 주소검색을 수행할 팝업 페이지를 호출합니다.
        // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
        window.open("/component/adres/PD_selectAdres.do","selectAdresPop","width=570,height=420, scrollbars=yes, resizable=yes"); 
    }
    function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn){
        $(".${zip}:eq("+btnIndex+")").val(zipNo);
        $(".${adres}:eq("+btnIndex+")").val(roadAddrPart1);
        $(".${adres2}:eq("+btnIndex+")").val(addrDetail+" "+roadAddrPart2);
        $(".${adresPopBtn}:eq("+btnIndex+")").focus();
    }
    //]]>
</script>
