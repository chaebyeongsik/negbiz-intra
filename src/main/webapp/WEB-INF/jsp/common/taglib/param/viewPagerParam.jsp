<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="zesinc.web.support.BaseConfig"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<%
    // 지정된 파라미터를 담고 있는 파라미터 멥. 설정 파일 참조 (기본값 : q_)
    Map<String, Object> paramMap = (HashMap<String, Object>)request.getAttribute("paramMap");
    // 자동 생성에서 제외 시킬 파라미터 멥
    Map<String, Boolean> ignores = (HashMap<String, Boolean>)request.getAttribute("ignores");
    // 사용자 지정 파라미터 접두사. 사용자 지정 접두사는 화면에 hidden 폼을 생성하지 않는다.
    // list 페이지와 혼용할 경우(2단 목록 프로그램 또는 한 화면에 목록이 두개 있는 경우등) 파라미터 중복을 제거하기 위함
    String opCustomPrefix = (String)request.getAttribute("opCustomPrefix");

    if(null != paramMap) {
        Iterator<String> it = paramMap.keySet().iterator();
        while(it.hasNext()) {
            String paramName = it.next();
            // 제외설정 파라미터 확인
            if(!ignores.containsKey(paramName)) {
                String[] customPrefixs = opCustomPrefix.split(",");
                for(String customPrefix : customPrefixs) {
                    if(paramName.startsWith(customPrefix)) {
%>
                        <input type="hidden" name="<%= paramName %>" id="<%= paramName %>" value="<%= paramMap.get(paramName) %>" />
<%
                    }
                }
            }
        }
    }
%>

