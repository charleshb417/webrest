'use strict';

angular.module('app').factory('VacantService', ['$http', '$q', function($http, $q){
    var REST_SERVICE_BASE_URI = 'http://localhost:8080/webrest/rest/vacants/';
 
    var factory = {
    	listVacants: listVacants,
    	getVacant: getVacant,
        createVacant: createVacant,
        updateVacant:updateVacant,
        deleteVacant:deleteVacant
    };
 
    return factory;
 
    function listVacants() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_BASE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching vacants');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createVacant(vacant) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_BASE_URI, vacant)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating vacant');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateVacant(vacant, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_BASE_URI+id, vacant)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating vacant');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteVacant(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_BASE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting vacant');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getVacant(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_BASE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while getting vacant');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);