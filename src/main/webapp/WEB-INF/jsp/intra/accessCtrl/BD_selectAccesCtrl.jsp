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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>접근제어 관리</title>

    <op:jsTag type="intra" items="opform" />
    

    <!-- 기능별 스크립트 삽입 부 -->
    <!--
    <script type="text/javascript" src="/resources/intra/accesCtrl/js/accesCtrl.js"></script>
    -->

    <script type="text/javascript">
        //<![CDATA[

        /* 공통 초기화 기능 */
        $(document).ready(function() {
            
        });

        

        //]]>
    </script>
</head>
<body>
<form name="dataForm" id="dataForm" method="post" action="BD_selectAccesCtrlList.do">
                <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
                <op:pagerParam view="view" ignores="" />
            </form>
<!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="접근제어 정보입니다.">
            <caption class="hidden">접근제어 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
<tbody>
<tr>
                    <th>일련번호</th>
                    <td>${dataVo.regSn}</td>
                </tr>
<tr>
                    <th>허용유형</th>
                    <td>${dataVo.prmsnTypeNm}</td>
                </tr>
<tr>
                    <th>허용ID</th>
                    <td>${dataVo.prmsnId}</td>
                </tr>
<tr>
                    <th>허용시작IP</th>
                    <td>${dataVo.prmsnBgngIp}</td>
                </tr>
<tr>
                    <th>허용종료IP</th>
                    <td>${dataVo.prmsnEndIp}</td>
                </tr>
<tr>
                    <th>사용여부</th>
                    <td>${dataVo.useYn}</td>
                </tr>
<tr>
                    <th>등록자ID</th>
                    <td>${dataVo.rgtrId}</td>
                </tr>
<tr>
                    <th>등록자명</th>
                    <td>${dataVo.rgtrNm}</td>
                </tr>
<tr>
                    <th>등록일시</th>
                    <td><fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
<tr>
                    <th>수정자ID</th>
                    <td>${dataVo.mdfrId}</td>
                </tr>
<tr>
                    <th>수정자명</th>
                    <td>${dataVo.updusrNm}</td>
                </tr>
<tr>
                    <th>수정일시</th>
                    <td><fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
</tbody>
</table>
    </div>
<!-- 버튼 -->
    <div class="row">
        <div class="col-xs-12 btn-group">
            <div class="pull-right">
                <button type="submit" class="btn btn-success">저장</button>
                <button type="reset" class="btn btn-default">재작성</button>

                <button type="submit" class="btn btn-success" onclick="opInsertForm('BD_insertAccesCtrlForm.do');">등록</button>
                <button type="button" class="btn btn-success" onclick="opUpdateForm('BD_updateAccesCtrlForm.do');">수정</button>
                <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteAccesCtrl.do');">삭제</button>
                <button type="button" class="btn btn-primary" onclick="opList('BD_selectAccesCtrlList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>