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
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>담당자권한할당 관리</title>

<op:jsTag type="intra" items="tree, opform" />
<op:jsTag type="libs" items="highlight" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/intra/authorAsgn/js/authorAsgn.js"></script>

<script type="text/javascript">
    //<![CDATA[

    var flag = false;
    var contPadId = "AuthorAsgn_VIEW";
    var Optree;
    var options = {
        // ROOT 노드 클릭시 상세화면 표시여부
        USE_ROOT_VIEW : false,
        // Drag and drop 사용여부
        USE_DND : false,
        // 폴더인 경우 view를 열지 여부 (기본값 true)
        FOLDER_OPEN : true,
        // view가 로드될 패널
        CONT_PAN_ID : contPadId,
        // 트리 데이터 url
        SRC_URL : "/intra/dept/ND_selectDeptTreeList.do",
        // view url
        VIEW_URL : "/intra/authorAsgn/INC_selectMngrList.do",
        // 기본 표시 명칭
        ROOT_NODE_NM : "부서목록",
        // 기본 표시 데이터 키값 (dummy)
        ROOT_NODE_VAL : OpConfig.defaultCode.highDeptCd,
        // 추가 파라미터가 필요한 경우에 사용자 정의 추가
        // 부모 노드의 파라미터 명칭
        P_NODE_NM : "q_upDeptCdId",
        // 현재 노드의 파라미터 명칭
        NODE_NM : "q_deptCdId",
        // 이동시 현재 일련번호 값을 전달할 파라미터 키명칭
        ORDER_NM : "q_sortSn"
    };
    
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        // 위의 defaults 옵션으로 생성하는 경우
        Optree = $("#TreePan").optree(options);

        // 초기 상세화면 영역 안내 메시지 표시
        initView(contPadId);

        $("#authorMngr").opmodal({
            width : 760,
            height : 500,
            href : "/intra/authorAsgn/PD_selectAuthorMngrList.do?q_rowPerPage=5"
        });

    });

    /**
     * 상세화면 초기화
     */
    var initView = function(id) {
        $("#" + id).html($("#dummy").html());
    };
    
    /*
     * 트리 이동 후 추가 사용자 정의 메소드
     */
    var afterTreeView = function(nodeKey, pNodeKey, orderNo, result) {
        if ($("#"+contPadId).css("display") == 'none') {
            $("#"+contPadId).slideToggle("slow");
        }
    };

    var opToggle = function () {
        $("#"+contPadId).slideToggle("slow");
    }

    //]]>
</script>
</head>
<body>

<div class="row">
    <div class="col-md-3">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-list2"></i> 부서목록
                </h6>
                <div class="panel-icons-group">
                    <a href="#" class="btn btn-link btn-icon" onclick="reload();"><i class="icon-spinner7"></i></a>
                    <a href="#" class="btn btn-link btn-icon" onclick="expandAll();"><i class="icon-plus-circle"></i></a>
                    <a href="#" class="btn btn-link btn-icon" onclick="collapseAll();"><i class="icon-minus-circle"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div id="TreePan"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn-info" id="authorMngr">권한별 담당자 검색</button>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-9">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-libreoffice"></i> 담당자권한할당
                </h6>
            </div>
            
            <div id="AuthorAsgn_VIEW" class="panel-body" style="display:none;"></div>
        
            <div class="panel-body">
            <form name="dataForm" id="dataForm" method="post" action="ND_insertAuthorAsgn.do" class="form-inline">
                <input type="hidden" id="authrtCdIds" name="authrtCdIds" />
                <input type="hidden" id="picIds" name="picIds" />
                <div class="block">
                    <span class="subtitle">
                        <i class="icon-list"></i> 지정권한목록
                    </span>
                    <div class="form-inline" id="authrtCdId">
                        <div class="block-inner">
                        <c:forEach items="${dataList}" var="authorList" varStatus="status">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="authrtCdId" id="authrtCdId${status.index}" value="${authorList.authrtCdId}" class="styled" />
                                <span>${authorList.authrtNm}</span>
                                <a href="#authorPop" onclick="opAuthorPop('${authorList.authrtCdId}', '${authorList.authrtNm}');">[목록]</a>
                            </label>
                        </c:forEach>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="block table-responsive">
                    <span class="subtitle">
                        <i class="icon-list"></i> 지정권한담당자목록
                    </span>
                    
                    <!-- 리스트 -->
                    <table id="authorTab" class="table table-bordered table-striped table-hover op-table-list"
                        summary="담당자권한할당 권한 목록으로 아이디,부서명,직급,담당자명,권한그룹,삭제 정보를 제공합니다.">
                        <caption class="hidden">담당자권한할당 권한 목록</caption>
                        <colgroup>
                            <col width="" />
                            <col width="" />
                            <col width="" />
                            <col width="" />
                            <col width="" />
                            <col width="" />
                            <col width="" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th><input type="checkbox" value="Y" name="chkall" id="chkall" /></th>
                                <th>ID</th>
                                <th>부서명</th>
                                <th>직급</th>
                                <th>담당자명</th>
                                <th>권한그룹</th>
                                <th>삭제</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="7" class="text-center text-warning">권한담당자를 지정해주십시오.</td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- //리스트 -->
                    
                </div>

                <div class="row">
                    <!-- 버튼 -->
                    <div class="block clearfix">
                        <div class="col-sm-12 btn-group">
                            <div class="pull-left">
                                <button type="button" class="btn btn-info" onclick="opDeleteChk();">선택삭제</button>
                            </div>
                            <div class="pull-right">
                                <button type="button" class="btn btn-success" onclick="opInsertAuthorAsgn();">전체할당</button>
                            </div>
                        </div>
                    </div>
                    <!-- //버튼 -->
                </div>
            </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>