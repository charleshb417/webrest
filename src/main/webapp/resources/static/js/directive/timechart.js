'use strict';
angular.module('app').directive('timechart', function($window) {
	return {
		restrict : "EA",
		template : "<svg width='850' height='200'></svg>",
		link : function(scope, elem, attrs) {
			
			var plotData=scope[attrs.chartData];
			var padding = 20;
			    
			var d3 = $window.d3;
			var rawSvg = elem.find("svg")[0];
			var svg = d3.select(rawSvg);
		
		}
	};
});