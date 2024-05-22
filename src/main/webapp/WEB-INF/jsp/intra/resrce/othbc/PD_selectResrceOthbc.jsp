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
<title>공개자원 상세</title>

<op:jsTag type="intra" items="opform" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

    });

    // 소스 보기와 화면보기 토글
    var opTtoggleScreen = function(targetNm) {
        if(targetNm == "viewScreen") {
            $("#viewScreen").show();
            $("#viewSource").hide();
        } else {
            $("#viewScreen").hide();
            $("#viewSource").show();
        }
    };

    var opUseResrceToCms = function() {
        if(opener) {
            var dataSn = $("#dataSn").val();
            var chgCycl = $("#chgCycl").val();
            var contsCn = $('#contsCn').val();
            var cssFiles = new Array();
            $("#resrceOthbcForm input[name='q_cssFileNm']").each(function() {
                cssFiles[cssFiles.length] = $(this).val();
            });

            var jsFiles = new Array();
            $("#resrceOthbcForm input[name='q_jsFile']").each(function() {
                jsFiles[jsFiles.length] = $(this).val();
            });

            if(!opener.opReceiveResrce) {
                opErrorMsg("부모윈도우의 화면이 변경되어 컨텐츠를 설정할 수 없습니다.\n\r다시 시작해야 합니다.");
            } else {
                opener.opReceiveResrce(dataSn, chgCycl, contsCn, cssFiles, jsFiles);
                self.close();
            }
        } else {
            opErrorMsg("부모윈도우를 확인할 수 없어 컨텐츠를 설정할 수 없습니다.\n\r다시 시작해야 합니다.");
        }
    };
    //]]>
</script>

</head>
<body>
    <form name="resrceOthbcForm" id="resrceOthbcForm" target="_blank" method="post" action="PD_selectResrceOthbcList.do">
        <c:forEach var="cssFileVo" items="${dataVo.fileList}" varStatus="index">
            <c:if test="${cssFileVo.fileExtnNm eq 'css'}">
                <input type="hidden" name="q_cssFileNm" value="${cssFileVo.fileUrlAddr}" />
            </c:if>
        </c:forEach>
        <c:forEach var="cssFileVo" items="${dataVo.fileList}" varStatus="index">
            <c:if test="${cssFileVo.fileExtnNm eq 'js'}">
                <input type="hidden" name="q_jsFile" value="${cssFileVo.fileUrlAddr}" />
            </c:if>
        </c:forEach>
        <button type="submit" class="sr-only">편집</button>
    </form>

    <form name="dataForm" id="dataForm" method="post" action="PD_selectResrceOthbcList.do" class="form-horizontal">
        <input type="hidden" name="dataSn" id="dataSn" value="${dataVo.dataSn}" />
        <input type="hidden" name="chgCycl" id="chgCycl" value="${dataVo.chgCycl}" />

        <%-- 페이징 관련 파라미터 생성. view 설정 모든 검색 파라미터가 hidden으로 생성됨 --%>
        <op:pagerParam view="view" />
        <!-- 내용보기 -->
        <div class="block table-responsive">
            <h3>
                <i class="icon-paragraph-justify2"></i> 기본정보
            </h3>
            <table class="table table-bordered" summary="자원 정보입니다.">
                <caption class="hidden">자원 상세보기</caption>
                <colgroup>
                    <col width="15%" />
                    <col />
                </colgroup>
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td>${dataVo.ttl}</td>
                    </tr>
                    <tr>
                        <th>자원설명</th>
                        <td>
                            <op:nrToBr content="${dataVo.dataExpln}" />
                        </td>
                    </tr>
                    <tr>
                        <th>자원유형</th>
                        <td>
                            유형 :
                            <code>
                                <op:cdId hghrkCdId="resrce" cdId="${dataVo.dataTypeNo}" />
                            </code>
                            &nbsp;상세유형 :
                            <code>
                                <op:cdId hghrkCdId="resrce" cdId="${dataVo.dataDtlTypeNm}" />
                            </code>
                        </td>
                    </tr>
                    <c:if test="${not empty dataVo.contsCn}">
                        <tr>
                            <th>컨텐츠</th>
                            <td>
                                <button type="button" class="btn btn-info btn-xs" onclick="opTtoggleScreen('viewScreen');">화면보기</button>
                                <button type="button" class="btn btn-info btn-xs" onclick="opTtoggleScreen('viewSource');">소스보기</button>
                                <div class="">
                                    <span class="text-info">※ 화면보기시 컨텐츠 표시 화면과 실제 사용될 곳의 크기와 비슷하게 조절하세요.</span>
                                </div>
                                <p />
                                <div id="viewScreen">
                                    <iframe style="width: 100%; height: 300px;" src="<c:url value="/intra/resrce/othbc/ND_selectResrceOthbc.do?q_dataSn=${dataVo.dataSn}&amp;q_chgCycl=${dataVo.chgCycl}" />"></iframe>
                                </div>
                                <div id="viewSource" style="display: none;">
                                    <textarea name="contsCn" id="contsCn" rows="20" cols="80" class="form-control" placeholder="컨텐츠">${dataVo.contsCn}</textarea>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <th>출처</th>
                        <td>${dataVo.srcNm}</td>
                    </tr>
                    <tr>
                        <th>출처설명</th>
                        <td>
                            <op:nrToBr content="${dataVo.srcExpln}" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <c:if test="${not empty dataVo.fileList}">
            <div class="block table-responsive">
                <h3>
                    <i class="icon-paragraph-justify2"></i> 개별 파일정보
                </h3>
                <div class="">
                    <span class="text-info">※ 사용하고자 하는 파일만 일부 사용할 경우 링크를 복사하여 사용하세요.</span>
                </div>
                <%-- 첨부파일 --%>
                <c:forEach var="fileVo" items="${dataVo.fileList}" varStatus="index">
                    <c:choose>
                        <c:when test="${not empty fileVo.fileExpln}">
                            <c:set var="title" value="${fileVo.fileExpln}" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="title" value="${fileVo.orgnlFileNm}" />
                        </c:otherwise>
                    </c:choose>
                    <table class="table table-bordered" summary="자원파일 정보입니다.">
                        <caption class="hidden">자원파일 상세보기</caption>
                        <colgroup>
                            <col width="15%" />
                            <col />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>원본파일명</th>
                                <td>
                                    ${fileVo.orgnlFileNm}
                                    <a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${fileVo.dataSn}&amp;chgCycl=${fileVo.chgCycl}&amp;fileSn=${fileVo.fileSn}" title="${fileVo.orgnlFileNm}" class="btn btn-xs btn-info">다운로드</a>
                                </td>
                            </tr>
                            <c:if test="${not empty fileVo.fileExpln}">
                                <tr>
                                    <th>파일설명</th>
                                    <td>${fileVo.fileExpln}</td>
                                </tr>
                            </c:if>
                            <c:choose>
                                <c:when test="${fileVo.fileTypeNm eq 'image/jpeg'}">
                                    <tr>
                                        <th>원본 이미지</th>
                                        <td>
                                            <code>&lt;img src="${fileVo.fileUrlAddr}" alt="${fileVo.fileExpln}" /&gt;</code>
                                            <br /> <img src="${fileVo.fileUrlAddr}" alt="${title}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>썸네일1 이미지</th>
                                        <td>
                                            <code>&lt;img src="${fileVo.thmbPathNm1}" alt="${fileVo.fileExpln}" /&gt;</code>
                                            <br /> <img src="${fileVo.thmbPathNm1}" alt="${title}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>썸네일2 이미지</th>
                                        <td>
                                            <code>&lt;img src="${fileVo.thmbPathNm2}" alt="${fileVo.fileExpln}" /&gt;</code>
                                            <br /> <img src="${fileVo.thmbPathNm2}" alt="${title}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>썸네일3 이미지</th>
                                        <td>
                                            <code>&lt;img src="${fileVo.thmbPathNm3}" alt="${fileVo.fileExpln}" /&gt;</code>
                                            <br /> <img src="${fileVo.thmbPathNm3}" alt="${title}" />
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${fileVo.fileExtnNm eq 'css'}">
                                    <tr>
                                        <th>CSS 파일</th>
                                        <td>
                                            <code>&lt;link rel="stylesheet" type="text/css" href="${fileVo.fileUrlAddr}" /&gt;</code>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${fileVo.fileExtnNm eq 'js'}">
                                    <tr>
                                        <th>JS 파일</th>
                                        <td>
                                            <code>&lt;script type="text/javascript" src="${fileVo.fileUrlAddr}"&gt;&lt;/script&gt;</code>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <th>기타 파일</th>
                                        <td>
                                            <code>&lt;a href="/component/resrce/file/ND_resrceFileDownload.do?dataSn=${fileVo.dataSn}&amp;chgCycl=${fileVo.chgCycl}&amp;fileSn=${fileVo.fileSn}" title="${title} 파일받기"&gt;다운로드&lt;/a&gt;</code>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                    <p />
                </c:forEach>
            </div>
        </c:if>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-md-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn btn-success" onclick="opUseResrceToCms();">현재자원을 사용</button>
                    <button type="button" class="btn btn btn-primary" onclick="opList();">목록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>