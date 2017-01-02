'use strict';
angular.module('app').controller('LayoutController', ['$scope', '$q', '$uibModal', 'ngTableParams', 'CrimeService', 'VacantService', 'NeighborhoodService', 
    function($scope, $q, $uibModal, NgTableParams, CrimeService, VacantService, NeighborhoodService) {

	var defaultPageNumber = 0;
	var defaultPerPage = 0;
	
    var vacantsComplete = false;
    var crimesComplete = false;
	$scope.overlayClass = 'overlay';
	
	$scope.crimes = [];
	$scope.vacants = [];
	$scope.neighborhoods = [];

	// Set dashboard controls
	$scope.availableDashboardSelections = ['Frequency Chart', 'Adjacency Matrix'];
	$scope.dashboardSelection = $scope.availableDashboardSelections[0];
	
	// Set table controls
	$scope.availableTableSelections = ['vacants', 'crimes', 'neighborhoods'];
	$scope.tableSelection = $scope.availableTableSelections[0];
	$scope.isGridTableOpen = true;
	
	$scope.collapsed = { dataset: false, dashboard: false };
	/*
	 * Visualization Specific Properties
	 */
	$scope.bubblechartKeys = {
			crimes: ['description', 'weapon', 'district', 'neighborhood'],
			vacants: ['neighborhood', 'policeDistrict'],
			neighborhoods: ['district']
	};

	// Initialize visualization scope variables
	$scope.currentBubbleChartKey = { crimes: $scope.bubblechartKeys.crimes[0], vacants: $scope.bubblechartKeys.vacants[0],  neighborhoods: $scope.bubblechartKeys.neighborhoods[0] };	
	$scope.adjacencyKeyX = { crimes: $scope.bubblechartKeys.crimes[0], vacants: $scope.bubblechartKeys.vacants[0], neighborhoods: $scope.bubblechartKeys.neighborhoods[0] };
	$scope.adjacencyKeyY = { crimes: $scope.bubblechartKeys.crimes[1], vacants: $scope.bubblechartKeys.vacants[1], neighborhoods: $scope.bubblechartKeys.neighborhoods[0] };
	
    function listAllData(pageNumber, perPage){
    	$q.all([
    	        VacantService.listVacants(pageNumber, perPage), 
    	        CrimeService.listCrimes(pageNumber, perPage),
    	        NeighborhoodService.listNeighborhoods(pageNumber, perPage)]
    	).then(function(results){
    		$scope.vacants = results[0];
    		$scope.crimes = results[1];
    		$scope.neighborhoods = results[2];
    		removeOverlayWhenComplete();
    	});
    }
    
    // Remove loading overlay
    function removeOverlayWhenComplete(){
    	$scope.overlayClass = '';
    }
    
    $scope.openFullTable = function(){
    	$scope.openDataModal($scope[ $scope.tableSelection ]);
    }
    
    $scope.openFilteredTable = function(params){
    	// Expects a "table" property, "keys" array, and "values" array. the keys and values arrays must
    	// be in the same order
    	if (params.hasOwnProperty('table') && params.hasOwnProperty('keys') && params.hasOwnProperty('values')){
    		
    		var keys = params.keys;
    		var values = params.values;
    		var tmpData = [];
    		var tmpVal;
    		var i, j;
    		var addObject;
    		
    		for (i=0; i<$scope[params.table].length; i++){
    			addObject = true;
    			
    			//Iterate through the keys to check that all expected values match
    			for (j=0; j<keys.length; j++){
    				tmpVal = $scope[params.table][i][keys[j]];
    				if (tmpVal != values[j]){
    					addObject = false;
    				}
    			}
    			
    			if (addObject){
    				tmpData.push($scope[params.table][i]);
    			}
    		}
    		$scope.openDataModal(tmpData);
    	}
    }
    
    $scope.openDataModal = function(data){
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: './resources/static/partials/data_table.html',
            size: 'lg',
            resolve: {
            	tableParams: new NgTableParams({}, { dataset: data }),
            	tableSelection: function(){
            		return $scope.tableSelection;
            	}
            },
            controller: function($scope, $uibModalInstance, tableParams, tableSelection) {
                $scope.tableSelection = tableSelection;
                $scope.tableParams = tableParams;
                
            	$scope.ok = function () {
                	$uibModalInstance.dismiss();
                }
            }
          });
    }
    
    $scope.openAboutModal = function(){
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: './resources/static/partials/about.html',
            controller: function($scope, $uibModalInstance) {
                $scope.ok = function () {
                	$uibModalInstance.dismiss();
                }
            }
          });
    }
    
	// Pre-populate data from database
	listAllData(defaultPageNumber, defaultPerPage);
}]);