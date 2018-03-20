(function () {
	'use strict';

	angular
		.module('produto.produto-csosn')
		.factory('produtoCsosnRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		return new RestUtils('basico/produto/produto-csosn');
	}
	
})();