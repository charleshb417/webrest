'use strict';
angular.module('app').controller('LayoutController', ['$scope', '$q', '$uibModal', 'ngTableParams', 'CrimeService', 'VacantService', function($scope, $q, $uibModal, NgTableParams, CrimeService, VacantService) {

	var defaultPageNumber = 0;
	var defaultPerPage = 0;
	
    var vacantsComplete = false;
    var crimesComplete = false;
	$scope.overlayClass = 'overlay';
	
	$scope.crimes = [];
	$scope.vacants = [];

	// Set dashboard controls
	$scope.availableDashboardSelections = ['Frequency Chart', 'Adjacency Matrix'];
	$scope.dashboardSelection = $scope.availableDashboardSelections[0];
	
	// Set table controls
	$scope.availableTableSelections = ['crimes', 'vacants'];
	$scope.tableSelection = $scope.availableTableSelections[0];
	$scope.isGridTableOpen = true;
	
	// Parameters for table
	$scope.tableParams = {
			crimes: new NgTableParams({}, {}),
			vacants: new NgTableParams({}, {})
	}
	
	$scope.collapsed = { dataset: false, dashboard: false };
	/*
	 * Visualization Specific Properties
	 */
	$scope.bubblechartKeys = {
			crimes: ['description', 'weapon', 'district', 'neighborhood'],
			vacants: ['neighborhood', 'policeDistrict']
	};

	// Initialize visualization scope variables
	$scope.currentBubbleChartKey = { crimes: $scope.bubblechartKeys.crimes[0], vacants: $scope.bubblechartKeys.vacants[0] };	
	$scope.adjacencyKeyX = { crimes: $scope.bubblechartKeys.crimes[0], vacants: $scope.bubblechartKeys.vacants[0] };
	$scope.adjacencyKeyY = { crimes: $scope.bubblechartKeys.crimes[1], vacants: $scope.bubblechartKeys.vacants[1] };
	
    function listAllData(pageNumber, perPage){
    	$q.all([
    	        VacantService.listVacants(pageNumber, perPage), 
    	        CrimeService.listCrimes(pageNumber, perPage)])
    	.then(function(results){
    		$scope.vacants = results[0];
    		$scope.crimes = results[1];
    		removeOverlayWhenComplete();
    	});
    }
    
    function updateTableParams(){
    	$scope.tableParams = new NgTableParams({}, { dataset: $scope.crimes });
    }
    
    // Remove loading overlay
    function removeOverlayWhenComplete(){
    	$scope.overlayClass = '';
    }
    
    $scope.openDataModal = function(){
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: './resources/static/partials/data_table.html',
            controller: 'LayoutController',
            size: 'lg'
          });
    }
    
    $scope.openAboutModal = function(){
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: './resources/static/partials/about.html',
            controller: 'LayoutController',
            controller: function($scope, $uibModalInstance) {
                $scope.ok = function () {
                	$uibModalInstance.dismiss('cancel');
                }
            }
          });
    }
    
	// Pre-populate data from database
	listAllData(defaultPageNumber, defaultPerPage);
}]);