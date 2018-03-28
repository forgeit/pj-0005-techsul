(function () {
    'use strict';

    angular
        .module('estoque.estoque-movimentacao-tipo')
        .factory('estoqueMovimentacaoTipoRest', dataservice);

    dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

    function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
        var service = new RestUtils('basico/estoque/estoque-movimentacao-tipo');        

        return service;
    }
    
})();