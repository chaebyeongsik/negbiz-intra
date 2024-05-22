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
 *      xFormat : ".0%", 
 *      yFormat : ".0%", 
 *      xBarNm : "xdata", 
 *      yBarNm : "ydata"
 *  }
 *  
 *  실행
 *  opHChartBars(opChartOption);
 */
var opHChartBars = function(option) {

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
    var x = d3.scale.linear().range([ 0, width ]);
    var xFormat;
    var xAxis;
    if(option.xFormat) {
        xFormat = d3.format(option.xFormat);
        xAxis = d3.svg.axis().scale(x).orient("top").tickFormat(xFormat);
    } else {
        xAxis = d3.svg.axis().scale(x).orient("top");
    }

    // Y 축
    var y = d3.scale.ordinal().rangeRoundBands([ 0, height ], .1, 1);
    var yAxis = d3.svg.axis().scale(y).orient("left");

    // 툴팁
    var tip = d3.tip().attr('class', 'd3-tip').offset([ -10, 0 ]).html(function(d) {
        return "<strong>" + d.label + ":</strong> <span style='color:red'>" + d.value + "</span>";
    });

    // 패널 생성
    var svg = d3.select("#" + option.divId).append("svg").attr("width", width + margin.left + margin.right).attr("height", height + margin.top + margin.bottom).append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // 툴팁 셋팅
    svg.call(tip);

    // 데이터 호출하여 바 그래프 생성
    d3.json(option.dataUrl, function(data) {

        // 데이터가 없는 경우
        if(!data || data.length < 1) {
            $("#" + option.divId).css("text-align", "center").text("데이터가 없습니다.");
            return false;
        }

        // 숫자 타입으로 데이터변경
        data.forEach(function(d) {
            d.value = +d.value;
        });

        // X 축 텍스트 반영
        x.domain([ 0, d3.max(data, function(d) {
            return d.value;
        }) ]);

        // Y 축 텍스트 반영
        y.domain(data.map(function(d) {
            return d.label;
        }));

        svg.append("g").attr("class", "x axis").call(xAxis); // .append("text").attr("y", 6).attr("dy", ".71em").style("text-anchor", "end").attr("class", "barText").text(option.xBarNm);
        svg.append("g").attr("class", "y axis").call(yAxis); // .append("text").attr("transform", "rotate(-90)").attr("y", 6).attr("dy", ".71em").style("text-anchor", "end").attr("class", "barText").text(option.yBarNm);

        svg.selectAll(".bar").data(data).enter().append("rect").attr("class", "bar").attr("y", function(d) {
            return y(d.label);
        }).attr("x", function(d) {
            return 0;
        }).attr("width", function(d) {
            return x(d.value);
        }).attr("height", y.rangeBand()).on('mouseover', tip.show).on('mouseout', tip.hide);
    });
};