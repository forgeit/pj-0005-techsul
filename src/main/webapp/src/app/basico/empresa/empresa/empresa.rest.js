(function () {
	'use strict';

	angular
		.module('empresa.empresa')
		.factory('empresaRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		return new RestUtils('basico/empresa/empresa');
	}
	
})();