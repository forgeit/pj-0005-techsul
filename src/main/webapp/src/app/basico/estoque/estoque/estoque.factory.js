(function () {
    'use strict';

    angular
        .module('estoque.estoque')
        .factory('estoqueUtils', estoqueUtils);

    estoqueUtils.$inject = ['controller', 'estoqueRest'];

    function estoqueUtils(utils, dataservice) {
        var service = {
            carregarPorProduto: carregarPorProduto
        };

        return service;

        function carregarPorProduto(produto) {
            return dataservice.buscarPorProduto(produto).then(success).catch(error);

            function error(response) {
                return utils.criarRetornoPromise(false, []);
            }

            function success(response) {
                var array = utils.ler(response, 'EstoqueDtoMovimentacao');
                return utils.criarRetornoPromise(true, array);
            }
        }
    }
})();