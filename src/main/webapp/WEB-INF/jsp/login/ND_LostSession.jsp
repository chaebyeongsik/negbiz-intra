<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<html>
<head>
<title>로그인 정보가 소멸되었습니다.</title>

<op:jsTag type="intra" items="base" />

<script type="text/javascript">
    //<CDATA[[
    var hasOpenner = false;
    if(opener) {
        hasOpenner = true;
    }
    $(document).ready(function() {
        alert("로그인 정보가 소멸되었습니다.\n\n다시 로그인해 주시기 바랍니다.");
        opMoveLogin();
    });

    var opMoveLogin = function() {
        if(hasOpenner) {
            opener.top.location.href = CTX_PATH + OpConfig.urls.mgrLogin;
            self.close();
        } else {
            top.location.href = CTX_PATH + OpConfig.urls.mgrLogin;
        }
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
                        <i class="icon-bubble4"></i> 로그인 정보가 소멸되었습니다.
                    </h6>
                </div>
                <div class="panel-body">
                    <div class="bg-info with-padding block-inner">
                        <div class="block text-right">로그인이 필요한 서비스입니다. 이동하기 버튼을 클릭하시면 로그인 페이지로 이동합니다.</div>
                        <div class="block text-right">
                            <button type="button" onclick="opMoveLogin();" class="btn btn-default btn-xs">로그인</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>