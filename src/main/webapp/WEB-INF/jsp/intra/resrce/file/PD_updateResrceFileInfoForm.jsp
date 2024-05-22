<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>파일 정보 수정</title>

<op:jsTag type="libs" items="form, validate" />

<script type="text/javascript">
    $().ready(function() {
        $("#dataForm").validate({
            rules : {
                q_sortSn : {
                    required : true,
                    number : true
                },
                q_orgnlFileNm : {
                    required : true,
                    maxlength : 50
                }
            },
            messages : {
                q_sortSn : {
                    required : "필수입력 항목입니다.",
                    number : "숫자로 입력하세요."
                },
                q_orgnlFileNm : {
                    required : "필수입력 항목입니다.",
                    maxlength : "최대 50자 이내로 작성하세요."
                }
            },
            submitHandler : function(form) {
                $(form).ajaxSubmit({
                    type : "POST",
                    dataType : "json",
                    success : function(response) {
                        alert(response.message);
                        location.reload(false);
                    },
                    error : function(response) {
                        opSysErrorMsg(response.responseText);
                        return;
                    }
                });
            }
        });
    });
</script>

</head>

<body>

    <div class="row block">
        <div class="col-sm-12">
            <blockquote>
                <p>파일 순번은 1부터 순서대로 입력해야 합니다.</p>
                <p>파일명은 최대 한글 30(영수 50)자까지 가능합니다.</p>
                <p>파일설명은 대체 텍스트로 사용될 수 있습니다. (예 : 사진파일의 alt 속성)</p>
                <p><span class="text-danger">※ 대체 텍스트로 사용하는 경우 파일설명에 쌍따옴표( " )는 사용할 수 없습니다.</span></p>
                <p><span class="text-danger">※ 파일명을 수정하신 경우 본화면을 새로고침하여 확인하세요.</span></p>
            </blockquote>
        </div>
    </div>

    <form name="dataForm" id="dataForm" method="post" action="/intra/resrce/file/ND_updateResrceFileInfo.do">

        <div class="block">
            <table class="table table-bordered table-striped table-hover op-table-list">
                <colgroup>
                    <col width="10%" />
                    <col width="35%" />
                    <col width="" />
                </colgroup>
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>파일명</th>
                        <th>파일설명</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dataList}" var="fileVo" varStatus="status">
                        <tr>
                            <td>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <input type="hidden" name="q_dataSn" value="${fileVo.dataSn}" />
                                        <input type="hidden" name="q_chgCycl" value="${fileVo.chgCycl}" />
                                        <input type="hidden" name="q_fileSn" value="${fileVo.fileSn}" />
                                        <input type="text" name="q_sortSn" value="${fileVo.sortSn}" class="form-control text-right" />
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <input type="text" name="q_orgnlFileNm" value="${fileVo.orgnlFileNm}" maxlength="50" class="form-control" />
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <input type="text" name="q_fileExpln" value="${fileVo.fileExpln}" maxlength="100" class="form-control" placeholder="파일설명" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <op:no-data obj="${dataList}" colspan="3" />
                </tbody>
            </table>
        </div>

        <!-- 버튼 -->
        <div class="row">
            <div class="col-sm-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>
                    <button type="reset" class="btn btn-default">재작성</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

</body>
</html>