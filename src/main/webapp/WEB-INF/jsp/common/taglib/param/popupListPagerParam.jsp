<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

                    <div class="row">
                        <div class="col-xs-12">
                            <c:choose>
                                <c:when test="${not empty RPP_NUM}">
                                    <fieldset>
                                        <legend class="sr-only">페이지당 목록갯수 선택</legend>
                                        <div class="pull-left">
                                            <h4>
                                                <c:if test="${not empty paggingTitle}">
                                                        ${paggingTitle}
                                                </c:if>
                                                <small> 전체 ${pager.totalNum}건 (${pager.currPage}페이지 / 전체 ${pager.totalPage}페이지)</small>
                                            </h4>
                                        </div>
<c:set var="pagerParamName" value="${opCustomPrefix}rowPerPage" />
                                        <div class="pull-right">
                                            <label for="${opCustomPrefix}rowPerPage">페이지당 목록</label>
                                            <select name="${opCustomPrefix}rowPerPage" id="${opCustomPrefix}rowPerPage" class="select" style="width:70px;">
                                                <c:forEach items="${RPP_NUM}" var="rppNum">
                                                    <option value="${rppNum}" <c:if test="${rppNum eq paramMap[pagerParamName]}">selected="selected"</c:if>>${rppNum}</option>
                                                </c:forEach>
                                            </select>
                                            <button type="submit" class="btn btn-primary " data-loading-text="<i class='icon-spinner7 spin'></i>">변경</button>
                                        </div>
                                    </fieldset>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="${opCustomPrefix}rowPerPage" id="${opCustomPrefix}rowPerPage" value="${paramMap[pagerParamName]}" />
                                </c:otherwise>
                            </c:choose>

<c:set var="pagerParamName" value="${opCustomPrefix}currPage" />
                            <input type="hidden" name="${opCustomPrefix}currPage" id="${opCustomPrefix}currPage" value="${paramMap[pagerParamName]}" />
<c:set var="pagerParamName" value="${opCustomPrefix}sortName" />
                            <input type="hidden" name="${opCustomPrefix}sortName" id="${opCustomPrefix}sortName" value="${paramMap[pagerParamName]}" />
<c:set var="pagerParamName" value="${opCustomPrefix}sortOrder" />
                            <input type="hidden" name="${opCustomPrefix}sortOrder" id="${opCustomPrefix}sortOrder" value="${paramMap[pagerParamName]}" />
                        </div>
                    </div>
