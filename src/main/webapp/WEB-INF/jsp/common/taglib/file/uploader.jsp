<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<op:jsTag type="libs" items="multifile" />
업로드 컨트롤은 구현중
                <c:set var="fileCnt" value="0" />

                <c:if test="${not empty opFileList}">
                    <p class="text-info">
                        - 기존 첨부파일을 <span class="text-danger">삭제</span>하시려면 아래 <span class="text-danger">체크박스를 선택</span>하세요.
                        <button type="button" class="btn btn-primary btn-xs" onclick="opFileUpdate('${opFileSn}');">파일정보 보기/수정</button>
                    </p>
                    <ul>
                    <c:forEach var="fileVo" items="${opFileList}" varStatus="index">
                        <c:set var="fileCnt" value="${fileCnt + 1}" />
                        <li>
                            <label for="fileSns${fileVo.fileSn}" class="checkbox-inline checkbox-primary">
                            <input type="checkbox" name="fileIds" value="${fileVo.fileId }" class="styled"/></label>
                            <a href="/component/file/ND_fileDownload.do?q_fileSn=${fileVo.fileSn}&amp;q_fileId=${fileVo.fileId}" title="${fileVo.orgnlFileNm}">${fileVo.orgnlFileNm}</a>
                            <span class="text-success">(download ${fileVo.dwnldCnt}, ${fileVo.fileSzNm}, ${fileVo.fileTypeNm})</span>
                            <button type="button" class="btn btn-primary btn-xs" onclick="opChangeSortSn('${fileVo.fileSn}', '${fileVo.fileId}');">정렬순서변경</button>
                            <button type="button" class="btn btn-primary btn-xs" onclick="opShowFileHistory('${fileVo.fileSn}', '${fileVo.fileId}');">이력보기</button>
                        </li>
                    </c:forEach>
                    </ul>
                </c:if>

                <c:if test="${fileCnt lt opFileCount}">
                    <div class="row">
                        <p class="help-block">* ${opFileCount - fileCnt} 개의 파일을 추가 할 수 있습니다. (파일당 ${opFileViewSize}, 전체 ${opFileViewMaxSize} 크기를 업로드 할 수 있습니다.)</p>
                        <div class="block col-md-6">
                            <input type="file" name="${opFileInputName}" multiple maxlength="${opFileCount - fileCnt}" data-maxsize="${opFileMaxSize}" data-maxfile="${opFileSize}" class="multi" accept="${opFileExts}" value=""/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="block col-md-6">
                            <c:forEach begin="${fileCnt+1}" end="${opFileCount}" step="1" varStatus="index" >
                                    <textarea name="fileExpln" rows="2" cols="50" class="form-control" placeholder="${index.count} 번 파일설명을 입력하세요."></textarea>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
