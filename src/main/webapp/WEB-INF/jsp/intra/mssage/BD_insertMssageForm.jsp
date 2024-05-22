<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>메시지 관리</title>

<op:jsTag type="intra" items="opform, opvalidate, tree" />
<op:jsTag type="libs" items="form, selectbox" />

<!-- 기능별 스크립트 삽입 부 -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/mssage/js/mssage.js"></script> --%>

<script type="text/javascript">
    //<![CDATA[

    var Optree;

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        var validate = function() {
            var v = [];
            $("#managerList option").each(function() {
                v[v.length] = this.value;
            });

            if(v == "") {
                opWarningMsg("수신자를 1명 이상 지정하세요.");
                $("#q_searchVal").focus();
                return false;
            }

            var contentsLen = $("#msgCn").val();
            if(contentsLen.length < 1) {
                opWarningMsg("보낼 메시지 내용을 입력하세요");
                $("#msgCn").focus();
                return false;
            }
            if(contentsLen.length > 500) {
                opWarningMsg("500글자를 초과할 수 없습니다.");
                $("#msgCn").focus();
                return false;
            }

            $("#receivers").val(v);

            $("#dataForm").ajaxSubmit({
                url : "ND_insertMssage.do",
                type : "POST",
                success : function(response) {
                    if(response.result) {
                        opSuccessMsg("메시지를 " + v.length + "명에게 전송 했습니다.");
                        opList('T');
                    } else {
                        opErrorMsg(response.message);
                    }
                }
            });
            return false;
        };

        $("#dataForm").submit(function() {
            return validate();
        });

        Optree = $("#TreePan1").optree({
            "SRC_URL" : "/intra/dept/ND_selectDeptUserTreeList.do"
        });

        /** 수신대상 담당자 검색 */
        $("#q_searchVal").autocomplete({
            minLength : 1,
            // delay : 10,
            source : function(request, response) {
                $.ajax({
                    url : "ND_selectReceptionChargerList.do",
                    type : "POST",
                    dataType : "json",
                    data : {
                        q_searchVal : request.term
                    },
                    success : function(data) {
                        response(data);
                    }
                });
            },
            select : function(event, ui) {
                opDeptAndUserAddOption(ui.item.picId, ui.item.label);
            },
            close : function(event, ui) {
                $("#q_searchVal").val("");
            }
        });

        // 트리에서 대상자 끌어 놓을 경우 적용
        $("#managerList").droppable({
            drop : function(event, ui) {
                var sourceNode = $(ui.helper).data("ftSourceNode");
                if(sourceNode.data.baseKey != "DEPT") {
                    var title = sourceNode.parent.title + "/" + sourceNode.title;
                    $(event.target).addOption(sourceNode.key, title, false);
                } else {
                    opWarningMsg("부서는 지정할 수 없습니다.");
                }
            },
        });

    });

    /* 트리 내에서의 이동금지 제한 조건 설정 */
    var treeMoveEnter = function(node, data, options) {
        return false;
    };

    // 검색된 담당자 추가
    var opDeptAndUserAddOption = function(cdId, text) {
        $("#managerList").addOption(cdId, text);
        $("#q_searchVal").val("");
    };

    /*
     * 사용자 정의 추가 검증(필요한 검증 코드 삽입)
     * 기본 검증후 메소드가 자동 호출된다.
     */
    var customValidate = function() {
        return true;
    };

    var opList = function(targetList) {
        var url = "BD_selectMssageList.do";
        var flag = '${flag}';
        flag = (targetList == 'R' || targetList == 'T') ? targetList : flag;
        if(flag != '') {
            url += "?flag=" + flag;
        }
        location.href = url;
    };

    var opDeleteSeletedAdmin = function(selectedOnly) {
        $("#managerList").removeOption(/./, selectedOnly);
    };
    //]]>
</script>
</head>
<body>
    <div id="tabs" class="tabbable page-tabs">
        <ul class="nav nav-tabs">
            <li><a href="BD_selectMssageList.do?flag=R">받은 메시지함</a></li>
            <li><a href="BD_selectMssageList.do?flag=T">보낸 메시지함</a></li>
            <li class="active"><a href="BD_insertMssageForm.do">메시지 보내기</a></li>
        </ul>
        <div class="tab-content">
            <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_insertMssage.do" class="form-horizontal">
                <input type="hidden" name="receivers" id="receivers" />
                <div class="col-sm-4">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="picId">수신대상 담당자 검색</label>
                            <input type="text" name="q_searchVal" id="q_searchVal" class="form-control" placeholder="수신대상 담당자 이름을 입력하세요." />
                        </div>
                        <div class="form-group">
                            <div class="block-inner">
                                <label for="picId">수신대상 담당자 목록</label>
                                <select multiple="multiple" class="form-control" id="managerList" title="검색할당 된 담당자목록이 보여집니다.">
                                </select>
                            </div>
                            <div class="col-sm-12 row">
                                <div class="btn-group text-right btn-group-xs">
                                    <button type="button" class="btn btn-danger" onclick="opDeleteSeletedAdmin(true);">삭제</button>
                                    <button type="button" class="btn btn-danger" onclick="opDeleteSeletedAdmin(false);">전체삭제</button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="help-block">
                                <span class="text-info">※ 수신자를 드레그하여 '수신대상 담당자 목록' 이동하세요.</span>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h6 class="panel-title">
                                        <i class="icon-list2"></i> 부서목록
                                    </h6>
                                    <div class="panel-icons-group">
                                        <a href="#" class="btn btn-link btn-icon" onclick="reload();">
                                            <i class="icon-spinner7"></i>
                                        </a>
                                        <a href="#" class="btn btn-link btn-icon" onclick="expandAll();">
                                            <i class="icon-plus-circle"></i>
                                        </a>
                                        <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();">
                                            <i class="icon-minus-circle"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div id="TreePan1"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label for="msgCn">
                                내용 <span data-icon="&#xe11d;"></span>
                            </label>
                            <textarea name="msgCn" id="msgCn" rows="20" class="form-control" placeholder="메시지를 입력하세요."></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fileSns">첨부파일</label>
                        <div class="col-md-10">
                            <op:fileUpload view="basic" count="3" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-10">
                            <label for="sndptyDelYn" class="checkbox-inline checkbox-primary">
                                <input type="checkbox" name="sndptyDelYn" id="sndptyDelYn" value="Y" class="styled" checked="checked" />
                                보낸쪽지함 저장
                            </label>
                        </div>
                    </div>
                </div>


                <!-- 버튼 -->
                <div class="block clearfix">
                    <div class="col-md-12 btn-group">
                        <div class="pull-right">
                            <button type="submit" class="btn btn-success">보내기</button>
                            <button type="button" class="btn btn-default" onclick="opList();">취소</button>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </form>
        </div>
    </div>
</body>
</html>