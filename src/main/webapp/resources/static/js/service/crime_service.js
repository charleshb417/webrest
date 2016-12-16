'use strict';

angular.module('app').factory('CrimeService', ['$http', '$q', function($http, $q){
    var REST_SERVICE_BASE_URI = 'http://localhost:8080/webrest/rest/crimes/';
 
    var factory = {
    	listCrimes: listCrimes,
    	getCrime: getCrime,
        createCrime: createCrime,
        updateCrime:updateCrime,
        deleteCrime:deleteCrime
    };
 
    return factory;
 
    function listCrimes() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_BASE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching crimes');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createCrime(crime) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_BASE_URI, crime)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating crime');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateCrime(crime, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_BASE_URI+id, crime)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating crime');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteCrime(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_BASE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting crime');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getCrime(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_BASE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while getting crime');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);