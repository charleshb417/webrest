'use strict';
angular.module('app').directive('adjacency', function($window) {
	return {
		restrict : "EA",
		template : '<svg id="adjacencySvg" width="600" height="600"></svg>',
		link : function(scope, elem, attrs) {
			var d3 = $window.d3;
			var rawSvg = elem.find("svg")[0];
			var svg = d3.select(rawSvg);
			var padding = 20;
			
			// Generate a normalized key for the transformData algorithm
			function generateKey(x, y){
				if (x < y)
					return (x+"&"+y).toLowerCase();
				else
					return (y+"&"+x).toLowerCase();
			}

			// Generate adjacency data
			function transformData(data, xKey, yKey) {
				var res = {
					nodes : [],
					links : []
				};
				var tmpNodes = [];
				var tmpKeys = []; // keeps track of indicies
				var tmpKey = '';
				var tmpX = '';
				var tmpY = '';

				for (var i = 0; i < data.length; i++) {
					tmpX = data[i][xKey];
					tmpY = data[i][yKey];

					// If both values exist and are not the same
					if (tmpX != undefined && tmpY != undefined
							&& tmpX.length > 0 && tmpY.length > 0) {
						
						// Check if nodes knows of both keys
						if (tmpNodes.indexOf(tmpX) == -1) {
							tmpNodes.push(tmpX);
							res.nodes.push({name: tmpX});
						}
						if (tmpNodes.indexOf(tmpY) == -1) {
							tmpNodes.push(tmpY);
							res.nodes.push({name: tmpY});
						}

						// Generate a normalized key to keep track of
						tmpKey = generateKey(tmpX, tmpY);

						// If normalized key does not exist, add it
						if (tmpKeys.indexOf(tmpKey) == -1) {
							tmpKeys.push(tmpKey);
							res.links.push({
								source : tmpNodes.indexOf(tmpX),
								target : tmpNodes.indexOf(tmpY),
								value : 1
							})
						} else {
							// Because links is modified whenever tmpKeys is,
							// use the tmpKeys index to increment the link
							res.links[tmpKeys.indexOf(tmpKey)].value += 1;
						}
					}
				}
				return res;
			}
			

			function draw(d, xKey, yKey) {
				var data = transformData(d, xKey, yKey);
	
				var margin = {
					top : 120,
					right : 100,
					bottom : 10,
					left : 120
				}, 
				width = 600, height = 600;
	
				var x = d3.scale.ordinal().rangeBands([ 0, width ]), 
					z = d3.scale.linear().domain([ 0, 4 ]).clamp(true), 
					c = d3.scale.category10().domain(d3.range(10));
	
				svg.selectAll("*").remove();
				svg.append("svg").attr("width", width + margin.left + margin.right)
						.attr("height", height + margin.top + margin.bottom)
						.style("margin-left",margin.left + "px")
						.style("margin-top", margin.top + "px")
						.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
				var matrix = [], nodes = data.nodes, n = nodes.length;
	
				var tip = d3.tip().attr('class', 'd3-tip')
						.offset([ -10, 0 ])
						.html(function(d) {
							return "<span class='tooltipText'>"
										+ nodes[d.x].name + " & "
										+ nodes[d.y].name + ": "
										+ d.z + "</span>"; 
						});
	
				svg.call(tip);
	
				// Compute index per node.
				nodes.forEach(function(node, i) {
					node.index = i;
					node.count = 0;
					matrix[i] = d3.range(n).map(function(j) {
						return {
							x : j,
							y : i,
							z : 0
						};
					});
				});
	
				// Convert links to matrix; count character
				// occurrences.
				data.links.forEach(function(link) {
					matrix[link.source][link.target].z += link.value;
					matrix[link.target][link.source].z += link.value;
					matrix[link.source][link.source].z += link.value;
					matrix[link.target][link.target].z += link.value;
					nodes[link.source].count += link.value;
					nodes[link.target].count += link.value;	
				});
			
				x.domain(d3.range(n).sort( function(a, b) {
							return d3.ascending(nodes[a].name,
									nodes[b].name);
						}));
	
				svg.append("rect").attr("class", "background")
						.attr("width", width).attr("height", height);
	
				var row = svg.selectAll(".row").data(matrix)
						.enter().append("g").attr("class", "row")
						.attr("transform", function(d, i) {
							return "translate(0," + x(i) + ")";
						}).each(row);
	
				row.append("line").attr("x2", width);
	
				row.append("text").attr("x", -6).attr("y",
						x.rangeBand() / 5).attr("dy", ".32em")
						.attr("text-anchor", "end").text(
								function(d, i) {
									return nodes[i].name;
								});
	
				var column = svg.selectAll(".column").data(
						matrix).enter().append("g").attr(
						"class", "column").attr(
						"transform",
						function(d, i) {
							return "translate(" + x(i)+ ")rotate(-90)";
						});
	
				column.append("line").attr("x1", -width);
	
				column.append("text").attr("x", 6).attr("y", x.rangeBand() / 5)
					.attr("dy", ".32em")
					.attr("text-anchor", "start").text(
							function(d, i) {
								return nodes[i].name;
					});
	
				function row(row) {
					var cell = d3.select(this).selectAll(
							".cell").data(
							row.filter(function(d) {
								return d.z;
							})).enter().append("rect").attr(
							"class", "cell").attr("x",
							function(d) {
								return x(d.x);
							}).attr("width", x.rangeBand())
							.attr("height", x.rangeBand())
							.style("fill-opacity", function(d) {
								return z(d.z);
							}).style("fill", function(d) {
								return "purple";
							}).on("mouseover", function(d) {
								tip.show(d);
							}).on("mouseout", function(d) {
								tip.hide(d);
							});
				}
	
				function order(value) {
					x.domain(orders[value]);
	
					var t = svg.transition().duration(2500);
	
					t.selectAll(".row").delay(function(d, i) {
						return x(i) * 4;
					}).attr("transform", function(d, i) {
						return "translate(0," + x(i) + ")";
					}).selectAll(".cell").delay(function(d) {
						return x(d.x) * 4;
					}).attr("x", function(d) {
						return x(d.x);
					});
	
					t.selectAll(".column").delay(
							function(d, i) {
								return x(i) * 4;
							}).attr(
							"transform",
							function(d, i) {
								return "translate(" + x(i) + ")rotate(-90)";
							});
				}
	
			}
			
			// Watch for changes in scope
			scope.$watchGroup([attrs.chartType, attrs.chartKeyX, attrs.chartKeyY, attrs.chartData], function(newVal, oldVal, scope){
				var data = scope[attrs.chartData];
				var x = newVal[1];
				var y = newVal[2];
				if (data != undefined && x != undefined && y != undefined
						&& data.length > 0 && data[0].hasOwnProperty(x) && data[0].hasOwnProperty(y)){
					draw(data, x, y);
				}
            });
		}
	};
});