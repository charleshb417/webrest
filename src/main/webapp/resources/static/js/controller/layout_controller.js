'use strict';
angular.module('app').controller('LayoutController', ['$scope', 'CrimeService', 'VacantService', function($scope, CrimeService, VacantService) {

	var self=this;
	self.crimes = [];
	self.vacants = [];
	
	// Pre-populate Crimes and Vacants from database
	listCrimes();
	listVacants();
	
    function listCrimes(){
        CrimeService.listCrimes()
            .then(
            function(d) {
                self.crimes = d;
            },
            function(errResponse){
                console.error('Error while fetching Crimes');
            }
        );
    }
 
    function listVacants(){
        VacantService.listVacants()
            .then(
            function(d) {
                self.vacants = d;
            },
            function(errResponse){
                console.error('Error while fetching Vacants');
            }
        );
    }
}]);