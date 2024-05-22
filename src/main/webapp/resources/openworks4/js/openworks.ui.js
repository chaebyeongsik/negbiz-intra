
/**
 * 프레임워크 Tab UI 헨들러
 * @param option 대상 패널 명과 URI 파라미터를 전달 할 수 있다.
 *         미 지정시 기본 값이 사용된다.
 */
(function($) {
    $.extend($.fn, {
        opTabs : function(options) {

            /* option 설정 */
            var defaultTabOption = {
                tabContainer : "tabContainer",
                baseParam: {}
            };
            if(!options) {
                options = defaultTabOption;
            }
            if(!options.baseParam) {
                options.baseParam = {};
            }

            var prefix_tab_id = "op_tabs_id_";

            /* Tab 대상 링크 객체 수집 및 고유 ID 생성*/
            var items = [];
            $(this).find("a").each(function(index) {
                $(this).addClass("tab-link");
                $(this).attr("id", prefix_tab_id + index);
                items[index] = $(this);
            });

            /* Tab 컨텐츠 패널 생성 */
            if($("#"+options.tabContainer).length < 1) {
                $("<div id='"+options.tabContainer+"'></div>").insertAfter($(this));
            }

            /** Tabs 객체생성 */
            var opTabs = function(tabOptions, tabItems) {

                this.options = tabOptions;
                this.items = tabItems;

                /** 현재 선택된 Tab Index */
                this.selectedIndex = 0;
                this.currentPanel = "";
                this.currentUrl = "";

                /* Tab 대상 링크 객체 수집 */
                for(var i=0 ; i < this.items.length ; i++) {
                    this.items[i].bind("click", {idx : i}, function(event) {
                        tab.loadTab(event.data.idx);
                        return false;
                    });
                }

                ///////////////// 이벤트 설정 //////////////

                /** Tab 로드 전 이벤트 */
                this.beforeLoad = function(event, item) {
                    return true;
                };
                /** Tab 로드 후 이벤트 */
                this.afterLoad = function(event, item) {
                    return true;
                };

                /** 이벤트 설정(Tab 로드전과 로드후) */
                if(tabOptions.beforeLoad) {
                    this.beforeLoad = tabOptions.beforeLoad;
                }
                if(tabOptions.afterLoad) {
                    this.afterLoad = tabOptions.afterLoad;
                }

                ///////////////// 기능 설정 //////////////

                /** 대상 Tab객체 id에 해당하는 Tab load */
                this.loadTab = function(idx, event) {

                    var before = this.beforeLoad(event, this.items[idx]);
                    if(!before) {
                        return false;
                    }

                    /* 모든 Tab를 초기화 한다.*/
                    this.clearTab();
                    this.selectedIndex = idx;

                    this.items[idx].parent().addClass("active");

                    var $panel = this.viewTab(idx);
                    $panel.show();

                    this.currentPanel = $panel;

                    this.afterLoad(event, this.items[idx]);
                };

                /** Tab 대상 컨텐츠를 표시하고 현재 컨텐츠를 표시하는 panel을 반환한다. */
                this.viewTab = function(idx) {
                    var $href = this.items[idx].attr("href");

                    var $panel;
                    if($href.indexOf("#", 0) == 0) {
                        var container = $href.substring(1);
                        $panel = $("#"+container);
                    } else {
                        options.baseParam["date"] = new Date().toString();
                        $("#"+this.options.tabContainer).load($href, options.baseParam, function(responseTxt, statusTxt, response) {
                            if(statusTxt != "success") {
                                opSysErrorMsg(responseTxt);
                            }
                        });
                        $panel = $("#"+this.options.tabContainer);
                    }
                    this.currentUrl = $href;
                    return $panel;
                };

                /** 현재 선택된 Tab 일련번호 반환 */
                this.currentIndex = function() {
                    return this.selectedIndex;
                };

                /** 지정된 일련번호(idx) Tab의 URL을 변경 */
                this.changeUrl = function(idx, url) {
                    this.items[idx].attr("href",url);
                };

                /** Tab 이동 */
                this.moveTab = function(index) {
                    var $tab = this.items[index];
                    $tab.trigger("click");
                };

                /** 현재 Tab 새로 고침 */
                this.refresh = function() {
                    this.loadTab(this.selectedIndex);
                };

                /** 모든 텝 초기화 */
                this.clearTab = function() {
                    $(".tab-link").each(function(idx) {
                        $(this).parent().removeClass("active");
                    });
                    // 선택전 화면을 보이지 않도록 한다.
                    if(this.currentPanel) {
                        this.currentPanel.hide();
                    }
                };
                
            };

            /* 초기화 후 반환 */
            var tab = new opTabs(options, items, arguments);
            if(options.loadTabIdx){
                tab.loadTab(options.loadTabIdx);
            }else{
                tab.loadTab(0);
            }

            return tab;
        }
    });
})(jQuery);
