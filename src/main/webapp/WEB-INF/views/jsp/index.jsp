<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>WebRest</title>
    <link rel="stylesheet" href="./resources/static/css/app.css">
    <link rel="stylesheet" href="./webjars/bootstrap/3.3.5/css/bootstrap.css">
	<link rel="stylesheet" href="./webjars/ng-table/1.0.0-beta.9/ng-table.min.css">
	<link rel="stylesheet" href="./resources/static/lib/loading-bar.min.css">
</head>
<body ng-app="app">
	<!-- Main Layout -->
	<div class="generic-container parentDiv {{overlayClass}}" ng-controller="LayoutController as ctrl">
		<div id="appControls" class="leftSide">
			<span class="toolbarLabel">Select Dataset:</span><br/>
			<select ng-model="tableSelection" ng-options="x for x in availableTableSelections"></select><br/>
			<span class="toolbarLabel">Select Dashboard:</span><br/>
			<select ng-model="dashboardSelection" ng-options="x for x in availableDashboardSelections"></select><br/>
			<div class="animate-switch-container" ng-switch on="dashboardSelection">
				<div class="animate-switch" ng-switch-when="bubblechart">
					<span class="toolbarLabel">Frequency Key:</span><br/>
					<select ng-model="$parent.currentBubbleChartKey" ng-options="x for x in bubblechartKeys[tableSelection]"></select><br/>
				</div>
				<div class="animate-switch" ng-switch-when="adjacency">
					<span class="toolbarLabel">X-Axis:</span><br/>
					<select ng-model="$parent.adjacencyKeyX" ng-options="x for x in bubblechartKeys[tableSelection]"></select><br/>
					
					<span class="toolbarLabel">Y-Axis</span><br/>
					<select ng-model="$parent.adjacencyKeyY" ng-options="x for x in bubblechartKeys[tableSelection]"></select><br/>
				</div>
			</div>
			<button class="btn btn-info" ng-click="openModal();">View Data</button>
		</div>
		<div class="rightSide">
			<div id="dashboard" class="animate-switch-container" ng-switch on="dashboardSelection">
				<!-- A bubblechart which allows the user to view frequency of different attributes in the data -->
				<div class="animate-switch" ng-switch-when="bubblechart">
					<bubblechart chart-type="tableSelection" 
						chart-key="currentBubbleChartKey"
						chart-data="{{tableSelection}}"
						 />
				</div>
				<!-- An adjacency matrix showing co-occurrence between keys -->
				<div class="animate-switch" ng-switch-when="adjacency" >
					<adjacency chart-type="tableSelection" 
						chart-key-x="adjacencyKeyX"
						chart-key-y="adjacencyKeyY"
						chart-data="{{tableSelection}}"
						/>
				</div>
				<!-- A linechart showing frequency of events over time -->
				<!-- timechart /-->
			</div>
		</div>
		
	</div>
	
	<!-- External Files -->
	<script src="./webjars/angularjs/1.6.0/angular.js"></script>
	<script src="./webjars/angularjs/1.6.0/angular-resource.js"></script>
	<script src="./webjars/angularjs/1.6.0/angular-route.js"></script>
	<script src="http://angular-ui.github.com/bootstrap/ui-bootstrap-tpls-2.3.1.js"></script>
	<script src="./webjars/ng-table/1.0.0-beta.9/ng-table.min.js"></script>
	<script src="./webjars/d3js/3.5.17/d3.min.js"></script>
	<script src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script>
	<script src="<c:url value='./resources/static/lib/loading-bar.min.js' />"></script>
	
	
	<script src="<c:url value='./resources/static/js/app.js' />"></script>
	<script src="<c:url value='./resources/static/js/service/crime_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/service/vacant_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/navbar_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/layout_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/bubblechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/adjacency.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/linechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/timechart.js' />"></script>
</body>
</html>