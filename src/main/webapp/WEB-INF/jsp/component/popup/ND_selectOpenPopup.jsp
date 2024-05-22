<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<c:choose>
    <c:when test="${dataVo.popupRpttSeNo eq '3010'}">
        <c:set var="closeMessage" value="popup.noOpen" />
        <c:set var="expires" value="30" />
    </c:when>
    <c:when test="${dataVo.popupRpttSeNo eq '3020'}">
        <c:set var="closeMessage" value="popup.oneDayOpen" />
        <c:set var="expires" value="1" />
    </c:when>
    <c:when test="${dataVo.popupRpttSeNo eq '3030'}">
        <c:set var="closeMessage" value="popup.weekOpen" />
        <c:set var="expires" value="7" />
    </c:when>
    <c:otherwise>
        <c:set var="closeMessage" value="popup.alwaysOpen" />
        <c:set var="expires" value="0" />
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>${dataVo.ttl}</title>

<op:jsTag type="libs" items="jquery, cookie" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 설정된 기간에 따른 닫기. 관리자단 미리보기는 쿠키 적용 않함
        $("#closeWinBtn").click(function() {
            /*
            Cookies.set("${dataVo.cookiePrefix}${dataVo.regSn}", "${dataVo.regSn}", {
                expires : ${expires},
                path : "/"
            });
            */
            self.close();
        });
        // 단순 닫기
        $("#closeBtn").click(function() {
            self.close();
        });
    });

    /* 부모창으로 URL 이동하고 창닫기 */
    var opPopupParent = function(aObj) {
        var href = $(aObj).attr("href");
        if(href) {
            if(opener) {
                opener.location.href = href;
                opener.focus();
            }
            if(parent) {
                parent.location.href = href;
                parent.focus();
            }
        }
        self.close();
    };

    /* 새창을 열어 URL 이동하고 창닫기 */
    var opPopupCloseWin = function(aObj) {
        var href = $(aObj).attr("href");
        if(href) {
            var option = "chrome, centerscreen, dependent=yes, dialog=yes, modal=yes, ";
            option += "resizable=yes, scrollbars=yes, location=yes, status=yes, menubar=yes, toolbar=yes";
            var newPopupWin = window.open(href, "_blank", option);
            newPopupWin.focus();
        }
        self.close();
    };

    /* 새창을 열어 URL 이동하고 창유지 */
    var opPopupWin = function(aObj) {
        var href = $(aObj).attr("href");
        if(href) {
            var option = "chrome, centerscreen, dependent=yes, dialog=yes, modal=yes, ";
            option += "resizable=yes, scrollbars=yes, location=yes, status=yes, menubar=yes, toolbar=yes";
            var newPopupWin = window.open(href, "_blank", option);
            newPopupWin.focus();
        }
    };

    //]]>
</script>
</head>
<body>

    ${dataVo.docContsCn}

    <c:if test="${not empty dataVo.fileList}">
        <div style="margin: 2px 0 0 0;">
            <ul>
                <c:forEach var="fileVo" items="${dataVo.fileList}">
                    <li><span class="glyphicon glyphicon-file"></span> <a href="/component/file/ND_fileDownload.do?q_fileSn=${fileVo.fileSn}&amp;q_fileId=${fileVo.fileId}"
                        title="${fileVo.fileExpln}"
                    >${fileVo.orgnlFileNm}</a> <span class="text-success">(download ${fileVo.dwnldCnt}, ${fileVo.fileSzNm}, ${fileVo.fileTypeNm})</span></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <div style="margin: 2px 0 0 0;">
        <button type="button" id="closeWinBtn">
            <op:message cdId="${closeMessage}" />
        </button>
        <button type="button" id="closeBtn">
            <op:message cdId="popup.popupClose" />
        </button>
    </div>

</body>
</html>
