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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>보안등급정책</title>

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });
    //]]>
</script>
</head>
<body>
    <div class="well panel-body">
        <ul>
            <li>
                안전한 비밀번호 만드는 방법
                <ul>
                    <li><strong>단순한 개인정보 사용 자제</strong> : 생년월일, 주민등록번호, 전화번호, 학번 등을 비밀번호로 사용하는 것은 절대로 피해 주시기 바랍니다.</li>
                    <li><strong>아이디와 비슷한 비밀번호 사용 자제</strong> : 아이디와 비밀번호를 같이 사용하거나 아이디에 있는 숫자를 비밀번호로 사용하는 것은 주위에 있는 사람뿐만 아니라 전혀 모르느 사람도 접근할 수 있으므로 자제해 주시기 바랍니다.</li>
                    <li><strong>연속된 숫자 사용 자제</strong> : 연속된 숫자나 쉬운 숫자로만 이루어진 비밀번호도 절대로 사용하지 말아주시기 바랍니다. (예) 111111, 222222, 123456, 1004등</li>
                    <li class="text-danger">비밀번호는 비밀번호 보안등급에 따라 3개월에 한 번씩 주기적으로 바꾸어 사용하시는 것이 안전합니다.</li>
                </ul>
            </li>
            <li>
                비밀번호 만들기
                <ul>
                    <li>${PASSWD_MIN_LENGTH}~${PASSWD_MAX_LENGTH}자의 영문 대소문자, 숫자, 특수문자만 가능합니다.<br />※ 사용 가능한 특수문자 : <c:out value="${SPECIAL_CHAR}" /></li>
                    <li class="text-danger">10자 미만의 비밀번호는 3종류의 문자로 구성해야 하며, 10자 이상은 2종류 이상의 문자로 구성해야 합니다.</li>
                    <li>영문, 숫자, 특수문자로 조합하시면 보다 안전합니다.</li>
                    <li>비밀번호는 길수록, 그리고 문자를 많이 혼용할수록 안전합니다.</li>
                </ul>
            </li>
            <li>
                사용 불가능한 비밀번호
                <ul>
                    <li>공백은 비밀번호로 사용이 불가능합니다.</li>
                    <li>숫자만으로 이루어진 비밀번호는 사용이 불가능합니다.</li>
                    <li>동일한 문자를 많이 포함할 경우 사용이 불가능합니다.</li>
                    <li>ID, 주민번호, 생일 등의 개인정보로만 이루어진 비밀번호는 사용이 불가능합니다.</li>
                    <li>비밀번호 변경 시 현재 사용 중인 비밀번호의 재사용은 불가능하며, 기존과 다른 비밀번호로 변경하셔야 합니다.</li>
                </ul>
            </li>
        </ul>
    </div>

</body>
</html>