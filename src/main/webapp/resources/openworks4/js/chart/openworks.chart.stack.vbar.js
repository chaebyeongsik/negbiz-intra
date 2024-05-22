/*
 * json data 셈플
 *  [ 
 *      { "label" : "명칭1", "value" : 0.3},
 *      { "label" : "명칭2", "value" : 0.1},
 *      { "label" : "명칭3", "value" : 0.15},
 *      { "label" : "명칭4", "value" : 0.45}
 *  ];
 *  
 * option 셈플 
 *  var opChartOption = {
 *      divId : "opChartDiv", 
 *      width : 600, 
 *      height : 500, 
 *      dataUrl : "/intra/path/to/jsonData.do", 
 *      margin : {top : 20, right : 20, bottom : 30, left : 40}, 
 *      yFormat : ".0%", 
 *      xBarNm : "xdata", 
 *      yBarNm : "ydata"
 *  }
 *  
 *  실행
 *  opChartBars(opChartOption);
 */
var opChartBars = function(option) {

    var margin = option.margin;

    if(!option.width) {
        option.width = $("#" + option.divId).width();
    }
    if(!option.height) {
        option.height = $("#" + option.divId).height();
    }

    var width = option.width - margin.left - margin.right;
    var height = option.height - margin.top - margin.bottom;

    // X 축
    var x = d3.scale.ordinal().rangeRoundBands([ 0, width ], .1, 1);
    var xAxis = d3.svg.axis().scale(x).orient("bottom");

    // Y 축
    var y = d3.scale.linear().range([ height, 0 ]);
    var yFormat;
    var yAxis;
    if(option.yFormat) {
        yFormat = d3.format(option.yFormat);
        yAxis = d3.svg.axis().scale(y).orient("left").tickFormat(yFormat);
    } else {
        yAxis = d3.svg.axis().scale(y).orient("left");
    }

    // 패널 생성
    var svg = d3.select("#" + option.divId).append("svg").attr("width", width + margin.left + margin.right).attr("height", height + margin.top + margin.bottom).append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // 데이터 호출하여 바 그래프 생성
    d3.json(option.dataUrl, function(data) {

        // 데이터가 없는 경우
        if(!data || data.length < 1) {
            $("#" + option.divId).css("text-align", "center").text("데이터가 없습니다.");
            return false;
        }

        // 숫자 타입으로 데이터변경
        data.forEach(function(d) {
            d.population = +d.population;
        });

        // X 축 텍스트 반영
        x.domain(data.map(function(d) {
            return d.label;
        }));

        // Y 축 텍스트 반영
        y.domain([ 0, d3.max(data, function(d) {
            return d.value;
        }) ]);

        svg.append("g").attr("class", "x axis").attr("transform", "translate(0," + height + ")").call(xAxis);
        svg.append("g").attr("class", "y axis").call(yAxis).append("text").attr("transform", "rotate(-90)").attr("y", 6).attr("dy", ".71em").style("text-anchor", "end").attr("class", "barText").text(option.yBarNm);

        svg.selectAll(".bar").data(data).enter().append("rect").attr("class", "bar").attr("x", function(d) {
            return x(d.label);
        }).attr("width", x.rangeBand()).attr("y", function(d) {
            return y(d.value);
        }).attr("height", function(d) {
            return height - y(d.value);
        });
    });
};