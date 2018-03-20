(function () {
	'use strict';

	angular
		.module('produto.produto-cest')
		.factory('produtoCestRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		var service = new RestUtils('basico/produto/produto-cest');

		service.buscarCombo = buscarCombo;

		return service;

		function buscarCombo(){
			return $http.get(service.url + "/combo/ncm");
		}
	}
	
})();