<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>WebRest</title>
    <link rel="stylesheet" href="./resources/static/css/app.css">
</head>
<body ng-app="app">
	<!-- Navigation/Control Bar -->
	<div class="generic-container" ng-controller="NavbarController as nav">
	
	</div>
	
	<!-- Main Layout -->
	<div class="generic-container" ng-controller="LayoutController as ctrl">
		<!-- A bubblechart which allows the user to view frequency of different attributes in the data -->
		<bubblechart />
		
		<!-- A map showing where all of the events transpired -->
		<mapchart />
		
		<!-- A linechart showing frequency of events over time -->
		<timechart />
		
		<!-- Grid Control showing what data is being represented on screen -->
		<gridcontrol />
	</div>
	
	<!-- External Files -->
	<script src="./webjars/angularjs/1.6.0/angular.js"></script>
	<script src="./webjars/angularjs/1.6.0/angular-resource.js"></script>
	<script src="./webjars/angularjs/1.6.0/angular-route.js"></script>
	<script src="./webjars/d3js/3.5.17/d3.min.js"></script>
	
	<script src="<c:url value='./resources/static/js/app.js' />"></script>
	<script src="<c:url value='./resources/static/js/service/crime_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/service/vacant_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/navbar_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/layout_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/bubblechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/linechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/timechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/gridcontrol.js' />"></script>
	
	<link rel="stylesheet" href="./webjars/bootstrap/3.3.5/css/bootstrap.css">
</body>
</html>