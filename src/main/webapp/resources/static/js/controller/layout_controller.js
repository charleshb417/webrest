'use strict';
angular.module('app').controller('LayoutController', ['$scope', 'ngTableParams', 'CrimeService', 'VacantService', function($scope, NgTableParams, CrimeService, VacantService) {

	var defaultPageNumber = 0;
	var defaultPerPage = 0;
	
    var vacantsComplete = false;
    var crimesComplete = false;
	$scope.overlayClass = 'overlay';
	
	$scope.crimes = [];
	$scope.vacants = [];

	// Set dashboard controls
	$scope.availableDashboardSelections = ['bubblechart', 'adjacency'];
	$scope.dashboardSelection = $scope.availableDashboardSelections[0];
	
	// Set table controls
	$scope.availableTableSelections = ['crimes', 'vacants'];
	$scope.tableSelection = $scope.availableTableSelections[0];
	$scope.isGridTableOpen = true;
	
	// Pre-populate Crimes and Vacants from database
	listCrimes(defaultPageNumber, defaultPerPage);
	listVacants(defaultPageNumber, defaultPerPage);
	
	// Parameters for table
	$scope.tableParams = {
			crimes: new NgTableParams({}, {}),
			vacants: new NgTableParams({}, {})
	}
	
	/*
	 * Visualization Specific Properties
	 */
	$scope.bubblechartKeys = {
			crimes: ['description', 'weapon', 'district', 'neighborhood'],
			vacants: ['neighborhood', 'policeDistrict', 'councilDistrict']
	};

	$scope.currentBubbleChartKey = '';
	
	//TODO these are temp
	$scope.adjacencyKeyX = '';
	$scope.adjacencyKeyY = '';
	
    function listCrimes(pageNumber, perPage){
        CrimeService.listCrimes(pageNumber, perPage)
            .then(
            function(d) {
            	$scope.crimes = d;
            	$scope.tableParams.crimes = new NgTableParams({}, { dataset: $scope.crimes });
            	crimesComplete = true;
            	removeOverlayWhenComplete();
            },
            function(errResponse){
                console.error('Error while fetching Crimes');
            }
        );
    }
 
    function listVacants(pageNumber, perPage){
        VacantService.listVacants(pageNumber, perPage)
            .then(
            function(d) {
            	$scope.vacants = d;
            	$scope.tableParams.vacants = new NgTableParams({}, { dataset: $scope.vacants });
            	vacantsComplete = true;
            	removeOverlayWhenComplete();
            },
            function(errResponse){
                console.error('Error while fetching Vacants');
            }
        );
    }
    
    function updateTableParams(){
    	$scope.tableParams = new NgTableParams({}, { dataset: $scope.crimes });
    }
    
    // When vacants and crimes are complete, remove overlay
    function removeOverlayWhenComplete(){
    	if(vacantsComplete && crimesComplete){
    		$scope.overlayClass = '';
    	}
    }
}]);