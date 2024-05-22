// 테마 변경
var opChangeTheme = function(el, chart, option, theme) {
    if(chart) {
        // 버튼 데코레이션
        if(el) {
            $(el).parent().parent().find("li").removeClass("active");
            $(el).parent().addClass("active");
        }
        option.theme = theme;
        if(theme == "dark") {
            option.bgColor = "#282828";
            option.bgImage = null;
        } else if(theme == "black") {
            option.bgColor = "#222222";
            option.bgImage = null;
        } else if(theme == "chalk") {
            option.bgColor = "#282828";
            option.bgImage = "/resources/libs/amcharts-3.18.6/images/board.jpg";
        } else {
            option.bgColor = "#ffffff";
            option.bgImage = null;
        }

        if(graphType(option) == "pie") {
            opChartPie(option, chart);
        } else if(graphType(option) == "column") {
            opChartColumn(option, chart);
        } else if(graphType(option) == "mixed") {
            opChartMixed(option, chart);
        } else if(graphType(option) == "radar") {
            opChartRadar(option, chart);
        }
    }
};

// 3D 토글
var opChange2D3D = function(el, chart, option) {
    if(chart) {
        option.use3D = !option.use3D;

        if(graphType(option) == "pie") {
            opChartPie(option, chart);
        } else if(graphType(option) == "column") {
            opChartColumn(option, chart);
        } else if(graphType(option) == "mixed") {
            opChartMixed(option, chart);
        } else if(graphType(option) == "radar") {
            opChartRadar(option, chart);
        }
    }
};

// Chart 유형을 반환
var graphType = function(option) {
    if(option.graphType == "pie" || option.graphType == "donut") {
        return "pie";
    } else if(option.graphType == "column" || option.graphType == "bar" || option.graphType == "line" || option.graphType == "smoothedLine") {
        return "column";
    } else if(option.graphType == "mixcolumn" || option.graphType == "mixbar" || option.graphType == "mixstackbar" || option.graphType == "mixstackcolumn") {
        return "mixed";
    } else if(option.graphType == "radar") {
        return "radar";
    }
    return "";
};