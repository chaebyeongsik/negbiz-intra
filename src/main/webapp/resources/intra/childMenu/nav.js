$(function(){
    $(".nav > li").bind("mouseenter focusin", function () { 
        $(this).addClass("nav_on").siblings().removeClass("nav_on");
    });
    $(".nav > li").bind("mouseleave", function () {
        $(this).removeClass("nav_on");
    });
});