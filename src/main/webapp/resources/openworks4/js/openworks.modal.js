(function($) {
    $.fn.opmodal = function(options) {
        var defaults = {
            href : "",
            type : "iframe",
            padding : 5,
            closeBtn : false,
            width : 700,
            height : 600,
            maxWidth : 700,
            maxHeight : 700
            // 아래 helpers의 closeClick 이 false인 경우 닫기 버튼으로만 닫힘.
            //, helpers : {
            //    overlay : {closeClick : false},
            //    title   : true
            //},
        };

        if(!options) {
            options = {};
        } else {
            if(options.height) {
                options.maxHeight = options.height;
                options.minHeight = options.height;
            }
            if(options.width) {
                options.maxWidth = options.width;
                options.minWidth = options.width;
            }
        }

        if($(this).length == 0) {
            this.settings = $.extend(true, {}, defaults, options);
            if(this.settings.href) {
                $.fancybox(this.settings);
            }
        } else {
            $.each($(this), function(index) {
                // 사용자 옵션 추가
                this.settings = $.extend(true, {}, defaults, options);
                var url = $(this).attr("href");
                if(url && url.substring(0, 1) != "#") {
                    $.extend(true, this.settings, {
                        href : url
                    });
                }
                if(this.settings.href) {
                    $(this).fancybox(this.settings);
                }
            });
        }
    };

})(jQuery);
