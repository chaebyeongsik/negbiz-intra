<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
                <c:set var="fileCnt" value="0" />

                <c:if test="${not empty opFileList}">
                    <p class="text-info">
                        기존 첨부파일을 <span class="text-danger">삭제</span>하시려면 아래 <span class="text-danger">체크박스를 선택</span>하세요.
                        <button type="button" class="btn btn-primary btn-xs" onclick="opChangeSortSn('${opFileList[0].fileSn}');">파일정보 보기/수정</button>
                    </p>
                    <ul>
                    <c:forEach var="fileVo" items="${opFileList}" varStatus="index">
                        <c:set var="fileCnt" value="${fileCnt + 1}" />
                        <li>
                            <label for="${opFileIdNm}${index.count}" class="checkbox-inline checkbox-primary">

                            <input type="checkbox" id="${opFileIdNm}${index.count}" name="${opFileIdNm}" value="${fileVo.fileId }" data-file-size="${fileVo.byteFileSz}" class="styled" onclick="opFileInputCtrl(this, '${opFileInputName}');" />삭제</label>
                            <a href="/component/file/ND_fileDownload.do?q_fileSn=${fileVo.fileSn}&amp;q_fileId=${fileVo.fileId}" title="${fileVo.orgnlFileNm}">${fileVo.orgnlFileNm}</a>
                            <span class="text-success">(download ${fileVo.dwnldCnt}, ${fileVo.fileSzNm}, ${fileVo.fileTypeNm})</span>
                            <button type="button" class="btn btn-primary btn-xs" onclick="opShowFileHistory('${fileVo.fileSn}', '${fileVo.fileId}');">이력보기</button>
                        </li>
                    </c:forEach>
                    </ul>
                </c:if>

                <div id="op${opFileInputName}FileFormDiv">
                    <c:if test="${fileCnt lt opFileCount}">
                        <c:forEach begin="${fileCnt+1}" end="${opFileCount}" step="1" varStatus="index" >
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <input type="file" onchange="opFilterFileExtBasic(this, '${opFileExts}');" name="${opFileInputName}" class="" data-opfile-ext="${opFileExts}" value=""/>
                                        </div>
                                    </div>
                                    <c:if test="${needDc}">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <input type="text" name="fileExpln" value="" maxlength="100" class="form-control" placeholder="파일설명을 입력하세요." />
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <p class="help-block">* 총 ${opFileCount}개 중 ${opFileCount - fileCnt} 개의 파일을 추가 할 수 있습니다. (파일당 ${opFileViewSize}, 전체 ${opFileViewMaxSize} 크기 이내로 업로드하세요.)</p>
                        <input type="hidden" name="${opFileInputName}EachSize" id="${opFileInputName}EachSize" value="${opFileSize}" />
                        <input type="hidden" name="${opFileInputName}TotalSize" id="${opFileInputName}TotalSize" value="${opFileMaxSize}" />
                    </div>
                </div>

                <div id="op${opFileInputName}FileDummyDiv" style="display:none;">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="row">
                                <div class="col-xs-6">
                                    <input type="file" onchange="opFilterFileExtBasic(this, '${opFileExts}');" name="dummy" class="" data-opfile-ext="${opFileExts}" value=""/>
                                </div>
                            </div>
                            <c:if test="${needDc}">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <input type="text" name="fileExpln" value="" maxlength="100" class="form-control" placeholder="파일설명을 입력하세요." />
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
