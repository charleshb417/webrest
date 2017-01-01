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
	<div class="generic-container parentDiv {{overlayClass}}" ng-controller="LayoutController as ctrl">
		
		<!-- Handle the left-hand side navigation / controls -->
		<div id="appControls" class="leftSide">
			<div class="toolbarSection">
				<div class="toolbarLabel mainLabel clickable" ng-click="openAboutModal();">
					About
					<i class="pull-right glyphicon glyphicon-user"></i>
				</div>
			</div>
		    <div class="toolbarSection">
				<div class="toolbarLabel mainLabel clickable" ng-click="collapsed.dataset = !collapsed.dataset">
					Select Dataset 
					<i class="pull-right glyphicon" 
						ng-class="{'glyphicon-chevron-down': collapsed.dataset, 'glyphicon-chevron-right': !collapsed.dataset}">
					</i>
				</div>
				<div class="toolbarContent" ng-show="collapsed.dataset">
					<select class="contentObj" ng-model="tableSelection" ng-options="x for x in availableTableSelections"></select>
				</div>
			</div>
			
			<div class="toolbarSection">
				<div class="toolbarLabel mainLabel clickable" ng-click="collapsed.dashboard = !collapsed.dashboard">
					Select Dashboard 
					<i class="pull-right glyphicon" 
						ng-class="{'glyphicon-chevron-down': collapsed.dashboard, 'glyphicon-chevron-right': !collapsed.dashboard}">
					</i>
				</div>
				<div class="toolbarContent" ng-show="collapsed.dashboard">
					<select class="contentObj" ng-model="dashboardSelection" ng-options="x for x in availableDashboardSelections"></select><br/>
					<div class="animate-switch-container" ng-switch on="dashboardSelection">
						<div class="animate-switch" ng-switch-when="Frequency Chart">
							<span class="toolbarLabel">Frequency Key:</span><br/>
							<select class="contentObj" ng-model="$parent.currentBubbleChartKey[tableSelection]" ng-options="x for x in bubblechartKeys[tableSelection]"></select><br/>
						</div>
						<div class="animate-switch" ng-switch-when="Adjacency Matrix">
							<span class="toolbarLabel">X-Axis:</span><br/>
							<select class="contentObj" ng-model="$parent.adjacencyKeyX[tableSelection]" ng-options="x for x in bubblechartKeys[tableSelection]"></select><br/>
							
							<span class="toolbarLabel">Y-Axis</span><br/>
							<select class="contentObj" ng-model="$parent.adjacencyKeyY[tableSelection]" ng-options="x for x in bubblechartKeys[tableSelection]"></select><br/>
						</div>
					</div>
				</div>
			</div>
			<div class="toolbarSection">
				<div class="toolbarLabel mainLabel clickable" ng-click="openDataModal();">
					View Data
					<i class="pull-right glyphicon glyphicon-list"></i>
				</div>
			</div>
			<div class="detailsView">
				<span class="toolbarLabel">Current Data:</span><br/>
				<span class="toolbarLabel labelValue">{{tableSelection.charAt(0).toUpperCase() + tableSelection.slice(1)}}</span><br/>
				<span class="toolbarLabel">Current View:</span><br/>
				<span class="toolbarLabel labelValue">{{dashboardSelection}}</span><br/>
				<span class="toolbarLabel">Quantity:</span><br/>
				<span class="toolbarLabel labelValue">{{this[tableSelection].length}}</span>
			</div>
		</div>
		
		<!-- Handle the content area on screen -->
		<div class="rightSide contentView">
			<div id="dashboard" class="animate-switch-container" ng-switch on="dashboardSelection">
				<!-- A bubblechart which allows the user to view frequency of different attributes in the data -->
				<div class="animate-switch" ng-switch-when="Frequency Chart">
					<bubblechart chart-type="tableSelection" 
						chart-key="currentBubbleChartKey[tableSelection]"
						chart-data="{{tableSelection}}"
						 />
				</div>
				<!-- An adjacency matrix showing co-occurrence between keys -->
				<div class="animate-switch" ng-switch-when="Adjacency Matrix" >
					<adjacency chart-type="tableSelection" 
						chart-key-x="adjacencyKeyX[tableSelection]"
						chart-key-y="adjacencyKeyY[tableSelection]"
						chart-data="{{tableSelection}}"
						/>
				</div>
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
	<script src="<c:url value='./resources/static/js/service/neighborhood_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/layout_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/bubblechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/adjacency.js' />"></script>
</body>
</html>