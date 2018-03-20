(function () {
	'use strict';

	angular
		.module('produto.produto-unidade')
		.factory('produtoUnidadeRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		return new RestUtils('basico/produto/produto-unidade');
	}
	
})();