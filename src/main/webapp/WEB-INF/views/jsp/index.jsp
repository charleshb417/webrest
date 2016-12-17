<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>WebRest</title>
    <link rel="stylesheet" href="./resources/static/css/app.css">
    <link rel="stylesheet" href="./webjars/bootstrap/3.3.5/css/bootstrap.css">
	<link rel="stylesheet" href="./webjars/ng-table/1.0.0-beta.9/ng-table.min.css">
	<!--link rel="stylesheet" href="./resources/static/lib/ngtable/ng-table.min.css"-->
</head>
<body ng-app="app">
	<!-- Navigation/Control Bar -->
	<div class="generic-container" ng-controller="NavbarController as nav">
	
	</div>
	
	<!-- Main Layout -->
	<div class="generic-container" ng-controller="LayoutController as ctrl">
	
		<!-- A bubblechart which allows the user to view frequency of different attributes in the data -->
		<!-- bubblechart /-->
		
		<!-- A map showing where all of the events transpired -->
		<!-- mapchart /-->
		
		<!-- A linechart showing frequency of events over time -->
		<!-- timechart /-->
		
		<!-- Handle Tables -->
		<div id="tableControls">
			<select ng-model="tableSelection" ng-options="x for x in availableTableSelections"></select>
			<div class="animate-switch-container" ng-switch on="tableSelection">
				<table ng-table="tableParams.crimes" class="table animate-switch" ng-switch-when="crimes"
				 show-filter="true">
					<tr ng-repeat="crime in $data">
						<td title="'CrimeID'" sortable="'crimeID'">{{crime.crimeID}}</td>
	        			<td title="'CrimeDate'" sortable="'crimeDate'">{{crime.crimeDate}}</td>
	        			<td title="'CrimeCode'" sortable="'crimeCode'">{{crime.crimeCode}}</td>
	        			<td title="'Location'" sortable="'location'">{{crime.location}}</td>
	        			<td title="'Description'" sortable="'description'">{{crime.description}}</td>
	        			<td title="'Weapon'" sortable="'weapon'">{{crime.weapon}}</td>
	        			<td title="'District'" sortable="'district'">{{crime.district}}</td>
	        			<td title="'Neighborhood'" sortable="'neighborhood'">{{crime.neighborhood}}</td>
			    	</tr>
				</table>
				
				<table ng-table="tableParams.vacants" class="table animate-switch" ng-switch-when="vacants"
				 show-filter="true">
					<tr ng-repeat="vacant in $data">
						<td title="'ReferenceID'" sortable="'referenceID'">{{vacant.referenceID}}</td>
	        			<td title="'Block'" sortable="'block'">{{vacant.block}}</td>
	        			<td title="'Lot'" sortable="'lot'">{{vacant.lot}}</td>
	        			<td title="'BuildingAddress'" sortable="'buildingAddress'">{{vacant.buildingAddress}}</td>
	        			<td title="'NoticeDate'" sortable="'noticeDate'">{{vacant.noticeDate}}</td>
	        			<td title="'Neighborhood'" sortable="'neighborhood'">{{vacant.neighborhood}}</td>
	        			<td title="'PoliceDistrict'" sortable="'policeDistrict'">{{vacant.policeDistrict}}</td>
	        			<td title="'CouncilDistrict'" sortable="'councilDistrict'">{{vacant.councilDistrict}}</td>
	        			<td title="'Location'" sortable="'location'">{{vacant.location}}</td>	
			    	</tr>
				</table>
			</div>
		</div>
	</div>
	
	<!-- External Files -->
	<script src="./webjars/angularjs/1.6.0/angular.js"></script>
	<script src="./webjars/angularjs/1.6.0/angular-resource.js"></script>
	<script src="./webjars/angularjs/1.6.0/angular-route.js"></script>
	<script src="./webjars/ng-table/1.0.0-beta.9/ng-table.min.js"></script>
	<!--script src="./resources/static/lib/ngtable/ng-table.min.js"></script>-->
	<script src="./webjars/d3js/3.5.17/d3.min.js"></script>
	
	<script src="<c:url value='./resources/static/js/app.js' />"></script>
	<script src="<c:url value='./resources/static/js/service/crime_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/service/vacant_service.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/navbar_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/controller/layout_controller.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/bubblechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/linechart.js' />"></script>
	<script src="<c:url value='./resources/static/js/directive/timechart.js' />"></script>
</body>
</html>