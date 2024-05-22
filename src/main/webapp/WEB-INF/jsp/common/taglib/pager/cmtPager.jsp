<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="pagez" value="${pager.pages}" />

<div class="comment_paging">
	<c:choose>
		<c:when test="${pager.totalPage > 1}">
			<c:if test="${!pager.startPagePart}">
				<a href="#" onclick="${script}(1); return false;" title="처음페이지로 가기">First</a>
				&nbsp; |  &nbsp; 
				<a href="#" onclick="${script}(${pager.previousPartLastPage}); return false;" title="이전페이지로 가기">Prev</a>
				&nbsp; |  &nbsp; 
			</c:if>

			<c:forEach items="${pagez}" var="pages" varStatus="status">
				<c:if test="${pages eq pager.currPage}">
					<span title="현재 ${pages} 페이지" class="tx_blue_w">${pages}</span>
				</c:if>
				<c:if test="${pages ne pager.currPage}">
					<a href="#" onclick="${script}(${pages}); return false;" title="${pages} 페이지">${pages}</a>
				</c:if>
				<c:if test="${!status.last}">
					&nbsp; |  &nbsp; 
				</c:if>
			</c:forEach>

			<c:if test="${!pager.lastPagePart}">
				&nbsp; |  &nbsp; 
				<a href="#" onclick="${script}(${pager.nextPartFirstPage}); return false;" class="p_gray" title="다음페이지로 가기">Next</a>
				&nbsp; |  &nbsp; 
				<a href="#" onclick="${script}(${pager.lastPage}); return false;" class="p_gray" title="마지막페이지로 가기">End</a>
			</c:if>
		</c:when>
		<c:otherwise>
			<span title="현재 1 페이지" class="tx_blue_w">1</span>
		</c:otherwise>
	</c:choose>
</div>