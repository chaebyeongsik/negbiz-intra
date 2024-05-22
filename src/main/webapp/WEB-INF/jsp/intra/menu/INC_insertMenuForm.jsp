<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>메뉴상세</title>

<script type="text/javascript">
//<![CDATA[
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            if($("#menuNm").val() == '') {
                opWarningMsg("메뉴명은 필수입력 항목입니다.");
                $("#menuNm").focus();
                return false;
            }

            $(this).ajaxSubmit({
                url : "ND_insertMenu.do",
                type : "POST",
                dataType : "json",
                async : false,
                success : function(response) {
                    if(response.result){
                        opJsonMsg(response);
                        if(response.result){
                            addChild(response.value); // 노드에 추가
                            $("#treeCont").html($("#dummy").html()); // 트리 오른쪽 상세부분 날려줌
                            checkInsertCnt++;
                        }
                    }
                }
            });
            return false;
        });
    });

//]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_insertMenu.do" class="form-horizontal">
        <div class="panel panel-default">
            <div class="panel-body">
                <!-- 메뉴위치 R -->
                <div class="form-group">
                    <label for="upMenuSn" class="control-label col-xs-3">메뉴위치</label>
                    <div class="col-xs-9">
                        <div class="row">
                            <input type="hidden" name="upMenuSn" id="upMenuSn" class="form-control" />
                            <div class="col-xs-11" id="nodePath">
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 메뉴명 -->
                <div class="form-group">
                    <label for="menuNm" class="control-label col-xs-3">메뉴명 <span class="mandatory">*</span></label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-11">
                                <input type="text" name="menuNm" id="menuNm" placeholder="메뉴명을 입력해주세요" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-11">
                                <valid:msg name="menuNm" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="row">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">등록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>