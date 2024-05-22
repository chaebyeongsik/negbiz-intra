<%@ page contentType="text/html;charset=UTF-8"%>
<sitemesh:write property="body" />

<sitemesh:write property="head" />

<script type="text/javascript">
    //<![CDATA[
    $().ready(function() {
        // radio, checkbox 디자인 생성 스크립트
        try {
            $(".styled, .multiselect-container input").uniform({
                radioClass : 'choice',
                selectAutoWidth : false
            });
        } catch(e) { }
        // 일반 selectbox
        $(".select").each(function() {
            try {
                $(this).select2({
                    minimumResultsForSearch : "-1"
                });
            } catch(e) { }
        });
        // 필터링 selectbox
        $(".select-search").each(function() {
            try {
                $(this).select2({});
            } catch(e) { }
        });

        /**
         * 검색버튼 클릭시 자동으로 페이지 번호를 1로 갱신
         */
        $("button[type='submit']").click(function() {
            var text = $(this).text();
            if($.trim(text) == "검색") {
                var currPage = $("#q_currPage");
                if(currPage.length > 0) {
                    currPage.val("1");
                }
            }
        });
        $("input[type='submit']").click(function() {
            var text = $(this).text();
            if($.trim(text) == "검색") {
                var currPage = $("#q_currPage");
                if(currPage.length > 0) {
                    currPage.val("1");
                }
            }
        });

        /**
         * 페이지 목록 갯수 변경시 자동으로 페이지 번호를 1로 갱신
         */
        if($("#q_rowPerPage").length > 0) {
            $("#q_rowPerPage").next().click(function(){
               $("#q_currPage").val(1);
            });
        }

    });
    //]]>
</script>