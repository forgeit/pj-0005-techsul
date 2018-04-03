(function () {
    'use strict';

    angular
        .module('estoque.estoque-movimentacao')
        .factory('estoqueMovimentacaoUtils', estoqueMovimentacaoUtils);

    estoqueMovimentacaoUtils.$inject = ['controller', 'estoqueMovimentacaoRest'];

    function estoqueMovimentacaoUtils(utils, dataservice) {
        var service = {
            buscarUltimasMovimentacoesPorProduto: buscarUltimasMovimentacoesPorProduto
        };

        return service;

        function buscarUltimasMovimentacoesPorProduto(produto) {
            return dataservice.buscarUltimasMovimentacoesPorProduto(produto).then(success).catch(error);

            function error(response) {
                return utils.criarRetornoPromise(false, []);
            }

            function success(response) {
                var array = utils.ler(response, 'ArrayList');
                return utils.criarRetornoPromise(true, array);
            }
        }
    }
})();