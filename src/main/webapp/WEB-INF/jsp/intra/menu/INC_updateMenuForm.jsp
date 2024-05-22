<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>메뉴상세</title>

<op:jsTag type="intra" items="opform, opvalidate" />
<op:jsTag type="libs" items="form, validate" />

<script type="text/javascript">
    //<![CDATA[

    var opGetRequestMappingUrl = function() {
        var progrmNmVal = $("#prgrmNm").find("option:selected").val();
        // -- 선택 -- <= 눌렀을 시
        if(progrmNmVal == ''){
            $("#menuUrlSection").html(setMenuUrlHtml(0, '', ''));
            $("input[name='mpgUrlYn']").eq(0).prop("checked", true);
            $("input[name='menuUrlAddr']").eq(0).prop("readonly", false);
            return;
        }

        $.ajax({
            url : "ND_processRequestMappingInfo.do",
            type : "POST",
            data : {
            	prgrmNm : $("#prgrmNm").find("option:selected").text()
            },
            dataType : "json",
            success : function(data) {
                $("#menuUrlSection").html("");
                $.each(data, function(idx, val) {
                    $("#menuUrlSection").append(setMenuUrlHtml(idx, val.pattern, val.authCode));
                    $("input[name='mpgUrlYn']").eq(0).prop("checked", true);
                });
            },
            error :  function(response) {
                opSysErrorMsg(response.responseText);
                return false;
            }
        });
    };

    var setMenuUrlHtml = function(idx, pattern, authCode) {
        var str = "";
        if((pattern.indexOf("/ND_") > -1) || (pattern.indexOf("/INC_") > -1)) {
            str += '<input type="hidden" name="menuUrlAddr" value="' + pattern + '" />';
            str += '<input type="hidden" name="grntCdId" value="' + authCode + '" />';
        } else {
            str = '<div class="col-md-8">';
            str += '    <input type="text" name="menuUrlAddr" id="menuUrlAddr_' + (idx + 1) + '" readonly="readonly" placeholder="URL을 입력해주세요" value="' + pattern + '" class="form-control" />';
            str += '    <input type="hidden" name="grntCdId" value="' + authCode + '" />';
            str += '</div>';
            str += '<div class="col-md-4">';
            str += '    <label for="mpgUrlYn_' + (idx + 1) + '" class="radio-primary">';
            str += '    <input type="radio" name="mpgUrlYn" id="mpgUrlYn_' + (idx + 1) + '" value="' + (idx + 1) + '"';
            str += ' class="styled" /> 메인URL 여부';
            str += '    </label>';
            str += '</div>';
        }
        return str;
    };

    $(document).ready(function() {

        $("#prgrmNm").change(function() {
            opGetRequestMappingUrl();
        });

        if($("#prgrmNm").val() != ''){
            $("input[name=menuUrlAddr]").attr("readonly", "readonly");
        }
    });

    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_updateMenu.do" class="form-horizontal">
        <div class="panel panel-default">
            <div class="panel-body">
                <!-- 메뉴코드 R -->
                <div class="form-group">
                    <label for="menuSn" class="control-label col-md-2">
                        메뉴코드 <span class="mandatory">*</span>
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <input type="hidden" name="menuSn" id="menuSn" value="${dataVo.menuSn}" />
                                <input type="hidden" name="upMenuSn" id="upMenuSn" value="${dataVo.upMenuSn}" />
                                <input type="hidden" name="sortSn" id="sortSn" value="${dataVo.sortSn}" />
                                ${dataVo.menuSn}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 메뉴명 -->
                <div class="form-group">
                    <label for="menuNm" class="control-label col-md-2">
                        메뉴명 <span class="mandatory">*</span>
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <input type="text" name="menuNm" id="menuNm" placeholder="메뉴명을 입력해주세요" value="${dataVo.menuNm}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="menuNm" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 메뉴경로 R -->
                <div class="form-group">
                    <label for="nodePath" class="control-label col-md-2">메뉴경로</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <p class="form-control-static" id="nodePath"></p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 메뉴URL -->
                <div class="form-group">
                    <label for="menuUrlAddr" class="control-label col-md-2">메뉴URL</label>
                    <div class="col-md-10">
                        <div class="row" id="menuUrlSection">
                                <c:choose>
                                    <c:when test="${fn:length(dataVo.menuUrlInfo) > 0}">
                                        <c:forEach items="${dataVo.menuUrlInfo}" var="urlVo" varStatus="status">
                                            <c:choose>
                                                <c:when test="${fn:contains(urlVo.menuUrlAddrStr, '/ND_') or fn:contains(urlVo.menuUrlAddrStr, '/INC_')}">
                                                    <input type="hidden" name="menuUrlAddr" value="${urlVo.menuUrlAddrStr}" />
                                                    <input type="hidden" name="grntCdId" value="${urlVo.grntCdIdStr}" />
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="col-md-8">
                                                        <input type="text" name="menuUrlAddr" id="menuUrlAddr_${status.count}" placeholder="URL을 입력해주세요" value="${urlVo.menuUrlAddrStr}" class="form-control" />
                                                        <input type="hidden" name="grntCdId" value="${urlVo.grntCdIdStr}" />
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label for="mpgUrlYn_${status.count}" class="radio-inline radio-primary">
                                                            <input type="radio" name="mpgUrlYn" id="mpgUrlYn_${status.count}" value="${status.count}"
                                                                <c:if test="${urlVo.mpgUrlYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                                            메인URL 여부
                                                        </label>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-8">
                                            <input type="text" name="menuUrlAddr" id="menuUrlAddr" placeholder="URL을 입력해주세요" value="" class="form-control" />
                                        </div>
                                        <div class="col-md-4">
                                            <label for="mpgUrlYn_${status.count}" class="radio-inline radio-primary">
                                                <input type="radio" name="mpgUrlYn" id="mpgUrlYn_1" checked="checked" value="1" class="styled" /> 메인URL 여부
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                        </div>
                        <div class="row">
                            <div class="col-md-8 help-block">
                                하위레벨 메뉴가 있을 경우에는 입력하지 않습니다
                                <valid:msg name="menuUrlAddr" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 파라미터 -->
                <div class="form-group">
                    <label for="prmttNm1" class="control-label col-md-2">파라미터</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" name="prmttNm1" id="prmttNm1" placeholder="파라미터1을 입력해주세요" value="${dataVo.prmttNm1}" class="form-control" />
                            </div>
                            <div class="col-md-4">
                                <input type="text" name="prmttNm2" id="prmttNm2" placeholder="파라미터2를 입력해주세요" value="${dataVo.prmttNm2}" class="form-control" />
                            </div>
                            <div class="col-md-4">
                                <input type="text" name="prmttNm3" id="prmttNm3" placeholder="파라미터3을 입력해주세요" value="${dataVo.prmttNm3}" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="prmttNm1" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 프로그램명 -->
                <div class="form-group">
                    <label for="prgrmNm" class="control-label col-md-2">프로그램명</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <select name="prgrmNm" id="prgrmNm" class="select-search">
                                    <option value="">-- 선택 --</option>
                                    <c:forEach items="${programInfo}" var="prInfo" varStatus="vst">
                                        <option value="${prInfo.progrmVal}" <c:if test="${dataVo.prgrmNm eq prInfo.progrmVal}">selected="selected"</c:if>><c:out value="${prInfo.prgrmNm}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 표시여부 -->
                <div class="form-group">
                    <label for="indctYnY" class="control-label col-md-2">
                        표시여부 <span class="mandatory">*</span>
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="indctYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="indctYn" id="indctYnY" value="Y" <c:if test="${dataVo.indctYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    표시
                                </label>
                                <label for="indctYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="indctYn" id="indctYnN" value="N" <c:if test="${dataVo.indctYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    미표시
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="form-group">
                    <label for="useYnY" class="control-label col-md-2">
                        사용여부 <span class="mandatory">*</span>
                    </label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="useYnY" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnY" value="Y" <c:if test="${dataVo.useYn eq 'Y'}">checked="checked"</c:if> class="styled" />
                                    사용
                                </label>
                                <label for="useYnN" class="radio-inline radio-primary">
                                    <input type="radio" name="useYn" id="useYnN" value="N" <c:if test="${dataVo.useYn eq 'N'}">checked="checked"</c:if> class="styled" />
                                    미사용
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <valid:msg name="useYn" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 지정된권한 R -->
                <div class="form-group">
                    <label for="author" class="control-label col-md-2">지정된권한</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <c:choose>
                                    <c:when test="${fn:length(dataVo.authNms) > 0}">
                                    <p class="form-control-static">
                                        <c:forEach items="${dataVo.authNms}" var="authNm" varStatus="status">
                                            ${authNm}<c:if test="${not status.last }">,</c:if>
                                        </c:forEach>
                                    </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="form-control-static">지정된 권한이 없습니다.</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 매뉴얼 -->
                <div class="form-group">
                    <label for="menuDtlExpln" class="control-label col-md-2">매뉴얼</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <textarea name="menuDtlExpln" id="htmlContent" placeholder="매뉴얼을 입력해주세요" rows="5" class="form-control ckeditor">${dataVo.menuDtlExpln}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 등록일 R -->
                <div class="form-group">
                    <label for="regDt" class="control-label col-md-2">등록일</label>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12 form-control-static">
                                <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty dataVo.updtDt}">
                    <!-- 수정일 R -->
                    <div class="form-group">
                        <label for="updtDt" class="control-label col-md-2">수정일</label>
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-md-12 form-control-static">
                                    <fmt:formatDate value="${dataVo.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <!-- 버튼 -->
                <div class="row">
                    <div class="btn-group col-md-12">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success" onclick="opUpdateMenu()">수정</button>
                            <c:if test="${!dataVo.isFolder}">
                                <button type="button" class="btn btn-danger" onclick="opDeleteMenu()">삭제</button>
                            </c:if>
                        </div>
                    </div>
                </div>
                <!-- //버튼 -->
            </div>
        </div>
    </form>
</body>
</html>