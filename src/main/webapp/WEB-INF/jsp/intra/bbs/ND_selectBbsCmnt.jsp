<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="zesinc.web.support.BaseConfig"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<script type="text/javascript" src="/resources/intra/bbs/js/bbs.cmnt.js"></script>

<form id="cmntDataForm" name="cmntDataForm" action="<c:url value="/intra/bbs/ND_insertBbsCmnt.do" />" method="post">
    <input type="hidden" id="cmntBbsSn" name="bbsSn" value="${paramMap.q_bbsSn}" />
    <input type="hidden" id="cmntBbsDocNo" name="bbsDocNo" value="${paramMap.q_bbsDocNo}" />

    <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
    <input type="hidden" name="q_currPage" id="q_cmntCurrPage" value="${paramMap['q_currPage']}" />
    <input type="hidden" name="q_rowPerPage" id="q_cmntRowPerPage" value="${paramMap['q_rowPerPage']}" />

    <div class="row" style="margin-top: 3px;">
        <label class="control-label col-xs-2"> 댓글등록 </label>
        <div class="col-xs-10">
            <textarea name="opnnCn" id="opCmntMessage" rows="3" cols="50" class="form-control"></textarea>
        </div>
    </div>

    <div class="row" style="margin-top: 3px;">
        <div class="col-xs-2">&nbsp;</div>
        <div class="col-xs-5 ">
            ※ 입력글자 제한 <span id="opCmntMessageCnt">0</span> / 1000
        </div>
        <div class="col-xs-5 btn-group">
            <div class="pull-right">
                <button type="submit" value="등록" class="btn btn-sm btn-success">등록</button>
            </div>
        </div>
    </div>
</form>

<%-- 댓글 목록 --%>
<div class="row" id="opCmntListDiv" style="margin-top: 3px;"></div>
