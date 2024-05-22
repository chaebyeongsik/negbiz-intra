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
<title>게시판환경설정 관리</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="tags" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/intra/bbsSetup/js/bbsSetup.js"></script>
<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {
        /* 유효성 검증 BEAN Annotation 기반 자동 설정 */
        <valid:script type="msgbox" />
        $("#dataForm").submit(function() {
            return validate();
        });

        // radio 버튼 선택 값 설정
        opChecked("lyrUseYn", "N");
        opChecked("thmbUseYn", "N");
        opChecked("wtmkUseYn", "N");

        // 분류명 Tags-input
        $('.tags').tagsInput({
            width : '100%',
            defaultText : '하위분류'
        });

        $("input[name=thmbUseYn]").click(function() {
            if(this.value == 'Y') {
                $("#thumbSetup").show();
            } else {
                opChecked("wtmkUseYn", "N");
                $("#thumbSetup").hide();
                $(".markSetup").hide();
            }
        });

        $("input[name=wtmkUseYn]").click(function() {
            if(this.value == 'Y') {
                $(".markSetup").show();
            } else {
                $(".markSetup").hide();
            }
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        target = $("#wdthSz");
        if(!target.requirefromValidator("thmbUseYn", "Y", "썸네일사용여부") || !target.digitsValidator()) {
            opWarningMsg("가로/세로 사이즈를 숫자로 입력하세요.");
            target.focus();
            return false;
        }

        target = $("#vrtcSz");
        if(!target.requirefromValidator("thmbUseYn", "Y", "썸네일사용여부") || !target.digitsValidator()) {
            opWarningMsg("가로/세로 사이즈를 숫자로 입력하세요.");
            target.focus();
            return false;
        }

        target = $("input[name='wtmkFileNm']");
        if(!target.requirefromValidator("wtmkUseYn", "Y", "워터마크사용여부")) {
            opWarningMsg("워터마크로 사용할 이미지 파일을 선택하세요.");
            target.focus();
            return false;
        }

        target = $("#wtmkPstnNm");
        if(!target.requirefromValidator("wtmkUseYn", "Y", "워터마크사용여부")) {
            opWarningMsg("워터마크 적용위치를 입력하세요.(도움말 참조)");
            target.focus();
            return false;
        }

        target = $("#wtmkTrnspc");
        if(!target.requirefromValidator("wtmkUseYn", "Y", "워터마크사용여부")) {
            opWarningMsg("워터마크 투명도를 입력하세요.(도움말 참조)");
            target.focus();
            return false;
        }

        return true;
    };
    //]]>
</script>
</head>
<body>

    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" enctype="multipart/form-data" action="ND_insertBbsSetup.do" class="form-horizontal">
        <%-- 페이징 관련 파라미터 생성 --%>
        <op:pagerParam view="view" ignores="" />
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="bbsStngNm" class="control-label col-md-2">
                        <span class="mandatory">*</span> 게시판환경설정명
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="bbsStngNm" id="bbsStngNm" value="" class="form-control" placeholder="게시판환경설정명" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="bbsStngNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="bbsStngExpln" class="control-label col-md-2">게시판환경설정설명 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <textarea name="bbsStngExpln" id="bbsStngExpln" rows="5" cols="80" class="form-control" placeholder="게시판환경설정설명">${dataVo.bbsStngExpln}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="bbsStngExpln" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lyrUseYnY" class="control-label col-md-2">계층형 사용여부 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="lyrUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="lyrUseYn" id="lyrUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="lyrUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="lyrUseYn" id="lyrUseYnN" value="N" checked="checked" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <valid:msg name="lyrUseYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="thmbUseYnY" class="control-label col-md-2">썸네일 사용여부 </label>
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="thmbUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="thmbUseYn" id="thmbUseYnY" value="Y" class="styled" />
                                    예
                                </label>
                                <label for="thmbUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="thmbUseYn" id="thmbUseYnN" value="N" checked="checked" class="styled" />
                                    아니오
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">썸네일 사용시 축소된 이미지가 자동으로 생성됩니다.</span></li>
                                        <li class="list-group-item"><span class="validate-msg">겔러리(이미지) 게시판과 같은 경우 사용</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <label for="" class="control-label col-md-2">썸네일사이즈 </label>
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="wdthSz" class="control-label col-md-6">가로</label>
                                <div class="col-md-6">
                                    <input type="text" name="wdthSz" id="wdthSz" value="" class="form-control" />
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="vrtcSz" class="control-label col-md-6">세로</label>
                                <div class="col-md-6">
                                    <input type="text" name="vrtcSz" id="vrtcSz" value="" class="form-control" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">썸네일 생성시에 사용될 수치를 입력하세요.</span></li>
                                        <li class="list-group-item"><span class="validate-msg">px 단위이며 숫자만 입력하세요.</span></li>
                                    </ul>
                                    <span class="text-info">가로/세로 중 하나만 0인 경우, 가로/세로 대비 비율로 생성됩니다. </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group" id="thumbSetup" style="display: none;">
                    <label for="wtmkUseYnY" class="control-label col-md-2">워터마크 설정</label>
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="wtmkUseYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="wtmkUseYn" id="wtmkUseYnY" value="Y" class="styled" />
                                    사용
                                </label>
                                <label for="wtmkUseYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="wtmkUseYn" id="wtmkUseYnN" value="N" checked="checked" class="styled" />
                                    미사용
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <span class="text-danger">썸네일 사용시에만 동작하며, 워터마크를 포함하여 생성합니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group markSetup" style="display: none;">
                    <label for="" class="control-label col-md-2">워터마크 설정 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="wtmkPstnNm" class="control-label col-md-4">위치</label>
                                <div class="col-md-6">
                                    <input type="text" name="wtmkPstnNm" id="wtmkPstnNm" value="BOTTOM_RIGHT" class="form-control" />
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="wtmkTrnspc" class="control-label col-md-4">투명도</label>
                                <div class="col-md-6">
                                    <input type="text" name="wtmkTrnspc" id="wtmkTrnspc" value="0.5" class="form-control" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg">TOP/CENTER/BOTTOM + "_" + LEFT/CENTER/RIGHT 로 조합
                                            <br /><span class="text-danger">단! 정중앙은 CENTER 만 사용</span></span>
                                            <br />예 : <code>TOP_LEFT</code>, <code>CENTER</code>, <code>CENTER_RIGHT</code>, <code>BOTTOM_RIGHT</code>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="help-block op-validate">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="text-info">투명도는 <code>1</code>이 <code>100%</code> 투명도를 뜻합니다. <code>0.5</code>는 <code>50%</code> 투명도로 설정됩니다.</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group markSetup" style="display: none;">
                    <label for="wtmkFileNm" class="control-label col-md-2">워터마크파일 </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <op:fileUpload name="wtmkFileNm" count="1" size="500K" maxSize="500K" exts="jpg,jpeg,gif,png"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="help-block op-validate">
                                    <span class="text-info"><code>jpg,jpeg,gif,png</code> 파일만 사용할 수 있습니다.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 버튼 -->
        <div class="block clearfix">
            <div class="col-md-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">저장</button>

                    <button type="button" class="btn btn-primary" onclick="opList('BD_selectBbsSetupList.do');">목록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>

</body>
</html>