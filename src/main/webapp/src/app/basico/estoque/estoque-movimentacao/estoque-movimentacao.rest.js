(function () {
    'use strict';

    angular
        .module('estoque.estoque-movimentacao')
        .factory('estoqueMovimentacaoRest', dataservice);

    dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

    function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
        var service = new RestUtils('basico/estoque/estoque-movimentacao');        

        return service;
    }
    
})();