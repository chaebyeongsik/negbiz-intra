<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<title><sitemesh:write property="title" /></title>

<op:jsTag type="intra" items="base, modal" />
<op:jsTag type="libs" items="jgrowl" />

<script>
    //<![CDATA[
    $().ready(function() {

    });
    //]]>
</script>

<sitemesh:write property="head" />
</head>
<body>
    <div style="padding:5px 20px 5px 5px;">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-bubble4"></i>
                    <sitemesh:write property="title" />
                </h6>
                <div class="panel-icons-group">
                    <a href="#" class="btn btn-link btn-icon" onclick="opCloseWin();" role="button"><i class="icon-cancel-circle"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <sitemesh:write property="body" />
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-right">
                <button class="btn btn-primary btn-sm" onclick="opCloseWin();">
                    <i class="icon-cancel-circle"></i>닫기
                </button>
            </div>
        </div>
    </div>
</body>
</html>