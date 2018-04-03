(function () {
    'use strict';

    angular
        .module('estoque.estoque')
        .factory('estoqueRest', dataservice);

    dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

    function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
        var service = new RestUtils('basico/estoque/estoque');

        service.buscarPorProduto = buscarPorProduto;
        
        return service;

        function buscarPorProduto(produto) {
            return $http.get(service.url + '/produto/' + produto);
        }
    }
    
})();