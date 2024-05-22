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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>관리자변경이력 관리</title>

<op:jsTag type="intra" items="opform" />


<!-- 기능별 스크립트 삽입 부 -->
<!--
    <script type="text/javascript" src="/resources/intra/change/js/change.js"></script>
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
    <form name="dataForm" id="dataForm" method="post" action="BD_selectChangeList.do">
        <!-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨-->
        <op:pagerParam view="view" ignores="" />
    </form>
    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="관리자변경이력 정보입니다.">
            <caption class="hidden">관리자변경이력 상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <tbody>
                <tr>
                    <th>담당자ID</th>
                    <td>${dataVo.picId}</td>
                </tr>
                <tr>
                    <th>로그일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.logDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th>로그유형</th>
                    <td>${dataVo.logType}</td>
                </tr>
                <tr>
                    <th>부서코드</th>
                    <td>${dataVo.deptCdId}</td>
                </tr>
                <tr>
                    <th>부서명</th>
                    <td>${dataVo.deptNm}</td>
                </tr>
                <tr>
                    <th>담당자명</th>
                    <td>${dataVo.picNm}</td>
                </tr>
                <tr>
                    <th>담당자비밀번호</th>
                    <td>${dataVo.picPswd}</td>
                </tr>
                <tr>
                    <th>직급코드</th>
                    <td>${dataVo.jbgdCdId}</td>
                </tr>
                <tr>
                    <th>직급명</th>
                    <td>${dataVo.clsfNm}</td>
                </tr>
                <tr>
                    <th>담당업무</th>
                    <td>${dataVo.taskCn}</td>
                </tr>
                <tr>
                    <th>전화번호1</th>
                    <td>${dataVo.rgnTelno}</td>
                </tr>
                <tr>
                    <th>전화번호2</th>
                    <td>${dataVo.telofcTelno}</td>
                </tr>
                <tr>
                    <th>전화번호3</th>
                    <td>${dataVo.indivTelno}</td>
                </tr>
                <tr>
                    <th>팩스번호1</th>
                    <td>${dataVo.rgnFxno}</td>
                </tr>
                <tr>
                    <th>팩스번호2</th>
                    <td>${dataVo.telofcFxno}</td>
                </tr>
                <tr>
                    <th>팩스번호3</th>
                    <td>${dataVo.indivFxno}</td>
                </tr>
                <tr>
                    <th>휴대전화번호1</th>
                    <td>${dataVo.mblRgnTelno}</td>
                </tr>
                <tr>
                    <th>휴대전화번호2</th>
                    <td>${dataVo.mblTelofcTelno}</td>
                </tr>
                <tr>
                    <th>휴대전화번호3</th>
                    <td>${dataVo.mblIndivTelno}</td>
                </tr>
                <tr>
                    <th>이메일1</th>
                    <td>${dataVo.emlId}</td>
                </tr>
                <tr>
                    <th>이메일2</th>
                    <td>${dataVo.emlSiteNm}</td>
                </tr>
                <tr>
                    <th>로그인건수</th>
                    <td>${dataVo.lgnNmtm}</td>
                </tr>
                <tr>
                    <th>로그인일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.lgnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th>사용여부</th>
                    <td>${dataVo.useYn}</td>
                </tr>
                <tr>
                    <th>알림이실행여부</th>
                    <td>${dataVo.avtsmtExcnYn}</td>
                </tr>
                <tr>
                    <th>이메일통지여부</th>
                    <td>${dataVo.emlAvtsmtYn}</td>
                </tr>
                <tr>
                    <th>SMS통지여부</th>
                    <td>${dataVo.smsAvtsmtYn}</td>
                </tr>
                <tr>
                    <th>비밀번호변경일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.pswdChgDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th>특별여부</th>
                    <td>${dataVo.trgtYn}</td>
                </tr>
                <tr>
                    <th>상태코드</th>
                    <td>${dataVo.sttsSn}</td>
                </tr>
                <tr>
                    <th>상태명</th>
                    <td>${dataVo.sttusNm}</td>
                </tr>
                <tr>
                    <th>상태시작일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.sttsBgngDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th>상태종료일시</th>
                    <td>
                        <fmt:formatDate value="${dataVo.sttsEndDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
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
                    <td>
                        <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
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
                    <td>
                        <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
                <tr>
                    <th>인증계정</th>
                    <td>${dataVo.acntNm}</td>
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

                <button type="submit" class="btn btn-success" onclick="opInsertForm('BD_insertChangeForm.do');">등록</button>
                <button type="button" class="btn btn-success" onclick="opUpdateForm('BD_updateChangeForm.do');">수정</button>
                <button type="button" class="btn btn-danger" onclick="opDelete('ND_deleteChange.do');">삭제</button>
                <button type="button" class="btn btn-primary" onclick="opList('BD_selectChangeList.do');">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>