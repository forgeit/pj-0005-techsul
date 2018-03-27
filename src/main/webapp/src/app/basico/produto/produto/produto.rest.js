(function () {
	'use strict';

	angular
		.module('produto.produto')
		.factory('produtoRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		var service = new RestUtils('basico/produto/produto');

        service.buscar = buscar;
        
        return service;

        function buscar(data) { 
            return $http.get(service.url + '/codigo/' + data);
        }
	}
	
})();