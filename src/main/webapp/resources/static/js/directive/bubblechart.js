'use strict';
angular.module('app').directive('bubblechart', function($window) {
	return {
		restrict : "EA",
		template : '<div class="contentSvg"><svg id="bubbleSvg" width="600" height="600"></svg></div>',
		link : function(scope, elem, attrs) {
			
			var d3 = $window.d3;
			var rawSvg = elem.find("svg")[0];
			var svg = d3.select(rawSvg);
			var padding = 20;

			var tip = d3.tip()
			  .attr('class', 'd3-tip')
			  .offset([-10, 0])
			  .html(function(d) {
			    return "<span class='tooltipText'>" + d.key + ": " + d.value + " records</span>";
			  })
			  
			svg.call(tip);
			
			function transformData(data, key){
				var res = {};
				var tmpKey = '';
				for (var i = 0; i < data.length; i++){
					tmpKey = data[i][key];
					if (res.hasOwnProperty(tmpKey))
						res[tmpKey] += 1;
					else
						res[tmpKey] = 1;
				}
				
				// D3 expects data as a list of arrays, so let us transform the Object into that
				var list = [];
				for (var k in res){
					// Remove empties
					if (k.length > 0){
						list.push({
							key: k,
							value: res[k]
						});	
					}
				}
				return list;
			}
			
			function draw(d, keyToSort){
				var data = transformData(d, keyToSort);
				var diameter = 600, //max size of the bubbles
			    color = d3.scale.category20b(); //color category

			var bubble = d3.layout.pack()
			    .sort(null)
			    .size([diameter, diameter])
			    .padding(1.5);

			svg.selectAll("*").remove();
			svg.append("svg")
			    .attr("width", diameter)
			    .attr("height", diameter)
			    .attr("class", "bubble");

			    //bubbles needs very specific format, convert data to this.
			    var nodes = bubble.nodes({children:data}).filter(function(d) { return !d.children; });

			    //setup the chart
			    var bubbles = svg.append("g")
			        .attr("transform", "translate(0,0)")
			        .selectAll(".bubble")
			        .data(nodes)
			        .enter();

			    //create the bubbles
			    bubbles.append("circle")
			        .attr("r", function(d){ return d.r; })
			        .attr("cx", function(d){ return d.x; })
			        .attr("cy", function(d){ return d.y; })
			        .style("fill", function(d) { return color(d.value); })
			        .on("mouseover", function(d) {
			        	d3.select(this).classed("activeBubble", true);
			        	tip.show(d);
			        })
			        .on("mouseout",  function(d) {
			        	d3.select(this).classed("activeBubble", false);
			        	tip.hide(d);
			        })
			        .on("click", function(d){
			        	scope.openFilteredTable({
			        		table: attrs.chartData,
			        		keys: [scope.currentBubbleChartKey[attrs.chartData]],
			        		values: [d.key]
			        	});
			        });

			    //format the text for each bubble
			    bubbles.append("text")
			        .attr("x", function(d){ return d.x; })
			        .attr("y", function(d){ return d.y + 5; })
			        .attr("text-anchor", "middle")
			        .text(function(d){
			        	var str = d.key;
			        	// This gives us a way to cut down on text
			        	var slashIndex = str.indexOf("/");
			        	if (slashIndex >= 0){
			        		return str.substring(0, slashIndex);
			        	}
			        	else {
				        	return str; 	
			        	}			        	
			        })
			        .each(function(d,i) {
			        	// Remove text that is too large to render
			        	if (this.getComputedTextLength() > 2*d.r){
				        	this.remove();
				        }
				    })
			        .style({
			            "fill":"white", 
			            "font-family":"Helvetica Neue, Helvetica, Arial, san-serif",
			            "font-size": "12px"
			        });
			    
			}
			
			// Watch for changes in scope
			scope.$watchGroup([attrs.chartType, attrs.chartKey, attrs.chartData], function(newVal, oldVal, scope){
				var data = scope[attrs.chartData];
				var key = newVal[1];
				
				if (arrayIsValidNonEmpty(data) && key && data[0].hasOwnProperty(key)){
					draw(data, key);
				}
            });
		}
	};
});