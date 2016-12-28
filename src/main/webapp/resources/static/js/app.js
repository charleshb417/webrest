'use strict';

var App = angular.module('app',['ngTable', 'angular-loading-bar', 'ui.bootstrap']);

/*
 * Global utility functions
 */
function arrayIsValidNonEmpty(a){
	return (Array.isArray(a) && a.length > 0);
}