<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>팝업 관리</title>

<op:jsTag type="intra" items="base" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });

    /*
     * 부모창에 현재 윈도우의 크기 및 위치 값을 설정한다.
     */
    var selectSizeAndPosition = function() {
        var width = $(window).width();
        var height = $(window).height();

        var topPos = 0;
        if(window.screenTop) {
            topPos = window.screenTop;
        } else if(window.screenY) {
            topPos = window.screenY;
        }
        var leftPos = 0;
        if(window.screenLeft) {
            leftPos = window.screenLeft;
        } else if(window.screenX) {
            leftPos = window.screenX;
        }

        if(topPos > 59) {
            topPos = topPos - 59;
        } else {
            topPos = 0;
        }
        
        if(leftPos > 8) {
            leftPos = leftPos - 8;
        } else {
            leftPos = 0;
        }

        $("#wdthSz", opener.document).val(width);
        $("#vrtcSz", opener.document).val(height);
        $("#yAxis", opener.document).val(topPos);
        $("#xAxis", opener.document).val(leftPos);

        self.close();
    };

    //]]>
</script>
</head>
<body style="width: 100%; height: 100%">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class=" callout callout-info fade in">
                        <h5>위치 및 크기를 인지하여 적용합니다.</h5>
                        <dl style="margin: 0;">
                            <dt class="text-info">위치조정</dt>
                            <dd>모니터 상단과 좌측위치로 창을 이동한 후 적용합니다.</dd>
                            <dt class="text-info">크기조정</dt>
                            <dd>창의 크기를 조정하여 적당한 크기가 되면 적용합니다.</dd>
                            <dt class="text-danger">주의</dt>
                            <dd>
                                <code>레이어/상단열림</code>
                                은 미리보기 기능으로 화면을 확인하세요.
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>

            <!-- 버튼 -->
            <div class="row">
                <div class="col-xs-12 btn-group">
                    <div class="pull-right">
                        <button type="button" style="margin: auto;" class="btn btn-success" onclick="selectSizeAndPosition();">적용 후 닫기</button>
                    </div>
                </div>
            </div>
            <!-- //버튼 -->
        </div>
    </div>


</body>
</html>