'use strict';

angular.module('app').factory('NeighborhoodService', ['$http', '$q', function($http, $q){
    var REST_SERVICE_BASE_URI = 'http://localhost:8080/webrest/rest/neighborhoods';
 
    var factory = {
    	listNeighborhoods: listNeighborhoods,
    	getNeighborhood: getNeighborhood
    };
 
    return factory;
 
    function listNeighborhoods(pageNumber, perPage) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_BASE_URI + "?pageNumber="+pageNumber+"&perPage="+perPage)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching neighborhoods');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function getNeighborhood(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_BASE_URI+"/"+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while getting neighborhood');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);