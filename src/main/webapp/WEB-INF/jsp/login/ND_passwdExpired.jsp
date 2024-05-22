<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<html>
<head>
<title>비밀번호 유효기간이 만료되었습니다.</title>

<op:jsTag type="intra" items="base" />

<script type="text/javascript">
    //<CDATA[[
    var opMoveMgrMain = function() {
        top.location.href = CTX_PATH + OpConfig.urls.mgrMain;
    };
    //]]>
</script>
</head>

<body>
    <div class="row">
        <div class="block"></div>
        <div class="col-md-offset-3 col-md-6">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h6 class="panel-title">
                        <i class="icon-bubble4"></i> 비밀번호 유효기간이 만료되었습니다.
                    </h6>
                </div>
                <div class="panel-body">
                    <div class="bg-info with-padding block-inner">
                        <div class="block text-right">비밀번호는 주기적으로 변경해야 안전하게 시스템을 이용할 수 있습니다. 비밀번호를 변경하여 주시기 바랍니다.</div>
                        <div class="block text-right">
                            <button type="button" onclick="opMoveMgrMain();" class="btn btn-default btn-xs">메인화면으로 이동</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>