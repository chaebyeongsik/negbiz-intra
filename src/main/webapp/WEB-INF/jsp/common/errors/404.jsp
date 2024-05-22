<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    boolean isXHR = false;
    if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
        isXHR = true;
    }

    if ( isXHR ) { 
%>
    <div style="margin:10px 0;">파일을 찾을 수 없습니다. (404 Page not found)</div>
    <div>
        <%= request.getAttribute("javax.servlet.forward.request_uri") %>
    </div>
    
<% } else { %>
<html>
<head>
    <title>404 Page not found</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <script type="text/javascript">
    </script>
    <style type="text/css">
        dl {
            padding: 8px; border: 1px solid gray;
        }
        dl dt {
            color: blue; margin-top: 6px; 
        }
        dl dd {
        }
        dd.req-page { color: red; font-weight: bold; }
        dd.debug-error { color: orange; }
    </style>
</head>

<body>

<h2>404 Page not found</h2>

<dl>
    <dt>JSP 페이지</dt>
    <dd class="req-page"><%= request.getAttribute("javax.servlet.forward.request_uri") %></dd>
</dl>

</body>
</html>

<% } %>