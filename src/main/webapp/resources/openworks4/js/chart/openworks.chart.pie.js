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
 *      dataUrl : "/intra/path/to/jsonData.do" 
 *  }
 *  
 *  실행
 *  opChartPie(opChartOption);
 */
var opChartPie = function(option) {

    if(!option.width) {
        option.width = $("#" + option.divId).width();
    }
    if(!option.height) {
        option.height = $("#" + option.divId).height();
    }

    var width = option.width;
    var height = option.height;

    var radius = Math.min(width, height) / 2;

    var color = d3.scale.ordinal().range([ "#6A3D9A", "#DD9E58", "#B2DF8A", "#1F78B4", "#FF7F00", "#FDBF6F", "#38A235" ]);

    var arc = d3.svg.arc().outerRadius(radius - 10).innerRadius(0);

    var pie = d3.layout.pie().sort(null).value(function(d) {
        return d.value;
    });

    var svg = d3.select("#" + option.divId).append("svg").attr("width", width).attr("height", height).append("g").attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    d3.json(option.dataUrl, function(error, data) {

        data.forEach(function(d) {
            d.value = +d.value;
        });

        var g = svg.selectAll(".arc").data(pie(data)).enter().append("g").attr("class", "arc");

        g.append("path").attr("d", arc).style("fill", function(d) {
            return color(d.data.label);
        });

        g.append("text").attr("transform", function(d) {
            return "translate(" + arc.centroid(d) + ")";
        }).attr("dy", ".35em").style("text-anchor", "middle").text(function(d) {
            return d.data.label + " " + d.data.value;
        });

    });

};