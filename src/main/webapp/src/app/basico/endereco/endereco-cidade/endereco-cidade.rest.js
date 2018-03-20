(function () {
	'use strict';

	angular
		.module('endereco.endereco-cidade')
		.factory('enderecoCidadeRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		return new RestUtils('basico/endereco/endereco-cidade');
	}
	
})();