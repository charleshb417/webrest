'use strict';
angular.module('app').controller('LayoutController', ['$scope', 'CrimeService', 'VacantService', function($scope, CrimeService, VacantService) {

	var defaultPageNumber = 0;
	var defaultPerPage = 10;
	
	$scope.crimes = [];
	$scope.vacants = [];
	
	// Pre-populate Crimes and Vacants from database
	listCrimes(defaultPageNumber, defaultPerPage);
	listVacants(defaultPageNumber, defaultPerPage);
	
    function listCrimes(pageNumber, perPage){
        CrimeService.listCrimes(pageNumber, perPage)
            .then(
            function(d) {
                $scope.crimes = d;
                console.log(d);
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
                console.log(d);
            },
            function(errResponse){
                console.error('Error while fetching Vacants');
            }
        );
    }
}]);