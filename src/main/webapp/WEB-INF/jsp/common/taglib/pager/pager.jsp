<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pagez" value="${pager.pages}" />

    <div class="block text-center" id="pagination-section">
        <ul class="pagination">
            <c:choose>
                <c:when test="${pager.totalPage > 1}">
                    <c:if test="${!pager.startPagePart}">
                        <li class="previous first"><a href="#" onclick="${script}(1); return false;" title="처음페이지로 가기">처음</a></li>
                        <li class="previous"><a href="#" onclick="${script}(${pager.previousPartLastPage}); return false;" title="이전페이지그룹 가기">&lt;<i class="entypo-left-open"></i></a></li>
                    </c:if>
                    <c:forEach items="${pagez}" var="pages">
                        <c:if test="${pages eq pager.currPage}">
                            <li class="active">
                                <a href="#" title="현재 ${pages} 페이지" onclick="return false;">${pages}</a>
                            </li>
                        </c:if>
                        <c:if test="${pages ne pager.currPage}">
                            <li><a href="#" onclick="${script}(${pages}); return false;" title="${pages} 페이지">${pages}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${!pager.lastPagePart}">
                        <li class="next"><a href="#" onclick="${script}(${pager.nextPartFirstPage}); return false;" title="다음페이지그룹 가기">&gt;<i class="entypo-right-open"></i></a></li>
                        <li class="next last"><a href="#" onclick="${script}(${pager.lastPage}); return false;" title="마지막페이지로 가기">마지막</a></li>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <li class="active"><a href="#" title="현재 1 페이지" onclick="return false;">1</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
        <c:if test="${pager.totalPage > 1}">
        <div class="movePage">
            <label class="control-label" for="txtMovePage">
                <span>페이지이동:</span>
                <input type="text" value="" id="txtMovePage" name="txtMovePage" class="" title="이동할 페이지를 입력하세요" />
                page
            </label>
            <button class="btn btn-primary" onclick="${script}($('#txtMovePage').val()); return false;">이동</button>
        </div>
        </c:if>
    </div>
