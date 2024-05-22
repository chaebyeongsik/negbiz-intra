<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="zesinc.core.utils.StringUtil"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>500 Internal server error</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
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

<h2>500 Internal server error</h2>

<dl>
    <%
    java.util.Enumeration<?> _e = request.getAttributeNames();
    while( _e.hasMoreElements() ) {
        String key = (String)_e.nextElement();
        out.println( "<dt>" + key + "</dt>" );
         if ( key.equals("javax.servlet.error.message") ) {
            out.println( "<dd>" + request.getAttribute(key) + "</dd>" );
        }
        else {
            out.println( "<dd>" + request.getAttribute(key) + "</dd>" );
        }
    }
    %>
</dl>

<dl>
    <%
    Throwable th = (Throwable) request.getAttribute("javax.servlet.error.exception");
    if(th != null) {
        out.println( "<dt>StackTrace</dt>" );
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        th.printStackTrace(pw);
        out.println( "<dd>" + StringUtil.replace(sw.toString(), "\n", "<br />") + "</dd>" );
    }
    %>
</dl>

</body>
</html>
