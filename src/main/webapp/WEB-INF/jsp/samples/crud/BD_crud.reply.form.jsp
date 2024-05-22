<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시물 답글</title>

    <op:jsTag type="intra" items="opform, opvalidate, ckeditor" />
    <op:jsTag type="libs" items="multifile,validate, form"/>

    <!-- 기능별 스크립트 정의 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/samples/crud/samples.crud.js"></script>

    <!-- 유효성 검증 BEAN Annotation 기반 자동 설정 -->
    <valid:script type="msgbox" />
    <%-- <valid:script type="alert" /> --%>

    <script type="text/javascript">
        //<![CDATA[

        /* 공통 초기화 */
        $(document).ready(function() {
            $("#dataForm").submit(function() {
                return validate();
            });

            $("#crudFile").MultiFile({ max: 3 });
/*
            $("#dataForm").validate({
                rules: {
                    ctgCd : { required: ($op.defaults.ctgYn == 'Y' ? true : false) },
                    title  : { required: true, minlength: 2, maxlength: 100 },
                    contents : { required: true }
                },
                submitHandler: function(form) {
                    $(form).ajaxSubmit({
                        url      : "TR_insertAction.do",
                        type     : "POST",
                        success  : function(response) {
                             opSuccessMsg(response);
                        },
                        error :  function(response) {
                            opSysErrorMsg(response.responseText);
                            return;
                        }
                    });
                }
            });
*/
        });

        /* 
         * validate() 호출시 사용자 정의 검증을 자동으로 호출함
         * 따라서 필요시 추가 로직을 아래 함수 명으로 작성하면 됨
         * 물론 필요 없을 시 없어도 됨.(셈플이기 때문에 작성한 것임)
         */
        var customValidate = function() {
            return true;
        };

        /**
         * 파일정보 수정(파일명, 정렬순서, 파일설명)
         */
        var opFileUpdate = function(fileSeq) {
            $().colorbox({
                title : "파일정보 수정",
                href : "/component/file/PD_fileUpdateForm.do?fileSeq="+fileSeq,
                width : "650",
                height : "450",
                iframe : true
            });
        }

        //]]>
    </script>
</head>
<body>

    <div class="po_rel">
        <h4>원본글</h4>
    </div>

    <!-- 내용보기 -->
    <table class="table table-bordered" cellspacing="0" border="0" summary="${baseVo.title} 게시글 정보입니다.">
        <caption class="hidden">${baseVo.title} 상세보기</caption>
        <colgroup>
            <col width="100%" />
        </colgroup>
        <thead>
            <tr>
                <th>
                    <div class="float_l w_92p">
                        <span class="tx_blue_w">[${baseVo.ctgCd}]</span>
                        ${baseVo.title}
                    </div>
                    <div class="float_r w_8p tx_r">
                        <c:choose>
                            <c:when test="${baseVo.openYn eq 'Y'}">공개</c:when>
                            <c:otherwise>비공개</c:otherwise>
                        </c:choose>
                        <a href="#" class="public">미공개</a>
                    </div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="info">
                    <!--작성자 정보및 내용헤더정보 -->
                    <div class="con_info">
                        <dl>
                            <dt>작성자 :</dt>
                            <dd>${baseVo.regNm}</dd>
                            <dt>조회 :</dt>
                            <dd>${baseVo.readCnt}</dd>
                            <dt>등록일 :</dt>
                            <dd>${baseVo.regDt}
                                <c:if test="${not empty baseVo.modiDt}"> 
                                    (최종 수정 : ${baseVo.modiDt}) by ${baseVo.modiNm}
                                </c:if>
                            </dd>
                            <dt>IP :</dt>
                            <dd>${baseVo.ipAddr}</dd>
                        </dl>
                    </div>
                    <!--//작성자 정보및 내용헤더정보 -->
                </td>
            </tr>
            <tr>
                <td class="con">
                    ${baseVo.contents}
                </td>
            </tr>
            <tr>
                <td class="file">
                    <div>첨부파일</div>
                    <ul>
                        <c:choose>
                            <c:when test="${fn:length(baseVo.fileList) > 0}">
                                <c:forEach items="${baseVo.fileList}" var="fileVo">
                                    <li>
                                        <img src="/resources/user/theme/default/icon/icon_file_jpg.gif" class="vm" alt="파일 첨부" />
                                        <a href="/component/file/ND_fileDownload.do?id=${fileVo.fileId}" title="${fileVo.fileDesc}">${fileVo.localNm}</a>
                                        <span class="tx_gray">(download ${fileVo.downCnt}, ${fileVo.fileSzNm}, ${fileVo.fileType})</span>
                                        <button type="button" class="gray_s mar_l10" onclick="opShowFileHistory('${fileVo.fileSeq}', '${fileVo.fileId}');">이력보기</button>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li>첨부파일이 없습니다.</li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </td>
            </tr>
        </tbody>
    </table>
    <!-- //내용보기 -->

    <br />

    <c:choose>
        <c:when test="${empty dataVo.replySeq}">
            <c:set var="action" value="ND_crud.reply.insert.do" />
            <c:set var="title" value="답변등록" />
        </c:when>
        <c:otherwise>
            <c:set var="action" value="ND_crud.reply.update.do" />
            <c:set var="title" value="답변수정" />
        </c:otherwise>
    </c:choose>

    <div class="po_rel">
        <h4>${title}</h4>
        <div class="h4_r">
            <span class="tx_red tx_b">*</span> 표시는 필수입력사항입니다.
        </div>
    </div>

    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="${action}">

    <%-- 페이징 관련 파라미터 생성. view 설정 모든 검색 파라미터가 hidden으로 생성됨 --%>
    <op:pagerParam view="view" />

    <input type="hidden" name="seq" id="seq" value="${baseVo.seq}" />
    <input type="hidden" name="replySeq" id="replySeq" value="${dataVo.replySeq}" />

    <!-- 내용쓰기 -->
    <fieldset>
        <legend>답변글입력</legend>
        <table class="table table-bordered" cellspacing="0" border="0" summary="게시판 답변내용 작성페이지입니다.">
            <caption class="hidden">게시판 답변글입력 페이지</caption>
            <colgroup>
                <col width="15%" />
                <col width="85%" />
            </colgroup>
            <tbody>
                <tr>
                    <th><label for="regPwd">비밀번호</label></th>
                    <td>
                        <input type="text" name="regPwd" id="regPwd" value="${dataVo.regPwd}" />

                        <valid:msg name="regPwd" />
                    </td>
                </tr>
                <tr>
                    <th><label for="replyContents">내용</label></th>
                    <td>
                        <textarea id="replyContents" name="replyContents" rows="15" class="w99_p" title="내용입력">${dataVo.replyContents}</textarea>

                        <valid:msg name="replyContents" />
                    </td>
                </tr>
                <tr>
                    <th><label for="file">첨부파일</label></th>
                    <td>
                        <c:if test="${fn:length(dataVo.fileList) > 0}">
                            <p class="tx_blue_s">
                                - 기존 첨부파일을 <span class="tx_red">삭제</span>하시려면 아래 <span class="tx_red">체크박스를 선택</span>하세요.
                                <button type="button" class="gray_s mar_l10" onclick="opFileUpdate('${dataVo.fileSeq}');">파일정보 보기/수정</button>
                            </p>
                            <ul>
                                <c:forEach items="${dataVo.fileList}" var="fileVo">
                                    <li class="mar_b5">
                                        <label for="${fileVo.fileId }">${fileVo.localNm} 삭제</label>
                                        <input type="checkbox" name="fileIds" value="${fileVo.fileId }" />

                                        <a href="<c:url value="/component/file/NR_fileDownload.do"/>?id=${fileVo.fileId}">${fileVo.localNm}</a>
                                        <span class="tx_gray">(download ${fileVo.downCnt}, ${fileVo.fileSzNm}, ${fileVo.fileType})</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>

                        <p class="tx_blue_s">- 최대 3개까지 업로드 할 수 있습니다. 파일당 100MB, 전체 100MB 업로드 할 수 있습니다.</p>
                        <p class="mar_t5 mar_b5">
                            <input type="hidden" name="fileSeq" id="fileSeq" value="${dataVo.fileSeq}" />
                            <input type="file" name="crudReplyFile" id="crudReplyFile" class="w470 multi" accept="gif|jpg" value="찾아보기" />
                        </p>

                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
    <!-- //내용쓰기 -->

    <!-- 버튼 -->
    <div class="btn_wrap">
        <div class="f_right">
            <input type="submit" value="등록" class="btn btn-blue" />
            <input type="reset" value="취소" class="btn btn-red" />
            <input type="button" value="목록" class="btn btn-default" onclick="opList();" />
        </div>
    </div>
    <!-- //버튼 -->

</form>

</body>
</html>