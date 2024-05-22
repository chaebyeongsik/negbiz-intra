<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title></title>

<op:jsTag type="libs" items="form, validate" />
<script type="text/javascript">
    $().ready(function() {
        var url = location.href;
        var confmKey = "${confmKey}";
        var inputYn= "${param.inputYn}";
        if(inputYn != "Y"){
            document.dataForm.confmKey.value = confmKey;
            document.dataForm.returnUrl.value = url;
            document.dataForm.action="http://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
            document.dataForm.submit();
        }else{
            opener.jusoCallBack("${param.roadFullAddr}","${param.roadAddrPart1}","${param.addrDetail}","${param.roadAddrPart2}","${param.engAddr}","${param.jibunAddr}","${param.zipNo}", "${param.admCd}", "${param.rnMgtSn}", "${param.bdMgtSn}");
            window.close();
        }
    });
</script>

</head>

<body>

    <form name="dataForm" id="dataForm" method="post" action="">
        <input type="hidden" id="confmKey" name="confmKey" value=""/>
        <input type="hidden" id="returnUrl" name="returnUrl" value=""/>
    </form>

</body>
</html>