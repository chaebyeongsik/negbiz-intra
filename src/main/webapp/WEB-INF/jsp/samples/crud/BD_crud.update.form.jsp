<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시물 등록</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form, validate, multifile, froala" /><!-- ckeditor -->

<!-- 기능별 스크립트 정의 -->
<script type="text/javascript" src="/resources/samples/crud/samples.crud.js"></script>

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 */
    $(document).ready(function() {

        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });
/* 
        $("#htmlContent").ckeditor({
            toolbar : toolbar_config.default_toolbar
        });
 */
        $('#htmlContent').froalaEditor({
            fileUploadURL: '/component/file/ND_fileFroalaUpload.do',
            imageUploadURL : '/component/file/ND_fileFroalaUpload.do',
            height: 500
        });

        <%--
         $("#dataForm").validate({
         // 유효성 검증 BEAN Annotation 기반 자동 설정 
            <valid:script type="jquery" />,
             submitHandler : function(form) {
                 $(form).ajaxSubmit({
                     type : "POST",
                     dataType: "json",
                     success : function(response) {
                         opJsonMsg(response);
                     },
                     error :  function(response) {
                         opSysErrorMsg(response.responseText);
                         return;
                     }
                 });
             }
         });
         --%>
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
            href : "/component/file/PD_fileUpdateForm.do?fileSeq=" + fileSeq,
            width : "650",
            height : "450",
            iframe : true
        });
    }

    //]]>
</script>
</head>
<body>

    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>

    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_crud.update.do" class="form-horizontal">

        <%-- 페이징 관련 파라미터 생성. view 설정 모든 검색 파라미터가 hidden으로 생성됨 --%>
        <op:pagerParam view="view" />
        <div class="panel panel-default">
            <div class="panel-body">
                <!-- 내용쓰기 -->
                <div class="form-group">
                    <label for="password" class="control-label col-md-2">비밀번호 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-2">
                                <input type="password" name="password" id="password" value="${dataVo.password}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="password" />
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="form-group">
                    <label for="ttl" class="control-label col-md-2">제목 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <input type="text" name="ttl" id="ttl" title="제목을 입력해주세요" value="${dataVo.ttl}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="ttl" />
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="form-group">
                    <label for="clsfNo" class="control-label col-md-2">분류 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-2">
                                <select name="clsfNo" id="clsfNo" title="분류선택해주세요" class="select">
                                    <option value="">-- 선택 --</option>
                                    <option value="1001" <c:if test="${dataVo.clsfNo eq '1001'}">selected="selected"</c:if>>분류1</option>
                                    <option value="1002" <c:if test="${dataVo.clsfNo eq '1002'}">selected="selected"</c:if>>분류2</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="clsfNo" />
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="form-group">
                    <label for="ntcPstYnㅛ" class="control-label col-md-2">공지여부</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="ntcPstYnY" class="checkbox-inline checkbox-primary">
                                    <input type="checkbox" name="ntcPstYn" id="ntcPstYnY" value="Y" <c:if test="${dataVo.ntcPstYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    공지로 사용
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="ntcPstYn" />
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="form-group">
                    <label for="docContsCn" class="control-label col-md-2">내용 <span class="mandatory">*</span></label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <textarea id="htmlContent" name="docContsCn" rows="10" class="form-control ckeditor" title="내용입력">${dataVo.docContsCn}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="docContsCn" />
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="form-group">
                    <label for="rlsYnY" class="control-label col-md-2">공개여부</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="rlsYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="rlsYn" id="rlsYnY" value="Y" <c:if test="${dataVo.rlsYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    공개
                                </label>
                                <label for="rlsYnN"  class="radio-inline radio-primary">
                                    <input type="radio" name="rlsYn" id="rlsYnN" value="N" <c:if test="${dataVo.rlsYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    비공개
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="rlsYn" />
                            </div>
                        </div>
                    </div>
                </div>
        
                <div class="form-group">
                    <label for="fileInput" class="control-label col-md-2">첨부파일1</label>
                    <div class="col-md-10">
                        <op:fileUpload view="basic" name="fileInput" count="3" size="2M" maxSize="5M" exts="jpg,jpeg,gif,png" fileList="${dataVo.fileList}" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="fileInput1" class="control-label col-md-2">첨부파일2(실제업로드는 없음)</label>
                    <div class="col-md-10">
                        <op:fileUpload view="basic" name="fileInput1" fileIdNm="test" count="3" size="2M" maxSize="5M" exts="jpg,jpeg,gif,png" fileList="${dataVo.fileList}" />
                    </div>
                </div>

                <!-- //내용쓰기 -->
        
                <!-- 버튼 -->
                <div class="row">
                    <div class="col-md-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success">수정</button>
                            <button type="reset" class="btn btn-danger">취소</button>
                            <button type="button" class="btn btn btn-primary" onclick="opList();">목록</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </div>
        </div>
    </form>

</body>
</html>