(function () {
    'use strict';

    angular
        .module('produto.produto')
        .factory('produtoUtils', produtoUtils);

    produtoUtils.$inject = ['controller', 'produtoRest'];

    function produtoUtils(utils, dataservice) {
        var service = {
            autocomplete: autocomplete,
            carregarCombo: carregarCombo,
            carregarUltimoId: carregarUltimoId
        };

        return service;

        function autocomplete(filtro) {
            return dataservice.autocomplete(filtro).then(success).catch(error);

            function error(response) {
                return utils.criarRetornoPromise(false, []);
            }

            function success(response) {
                var array = utils.ler(response, 'ArrayList');
                return utils.criarRetornoPromise(true, array);
            }
        }

        function carregarCombo() {
            return dataservice.buscarCombo().then(success).catch(error);

            function error(response) {
                return utils.criarRetornoPromise(false, []);
            }

            function success(response) {
                var array = utils.ler(response, 'ArrayList');
                return utils.criarRetornoPromise(true, array);
            }
        }

        function carregarUltimoId() {
            return dataservice.buscarUltimoId().then(success).catch(error);

            function error(response) {
                return utils.criarRetornoPromise(false, []);
            }

            function success(response) {
                var array = utils.ler(response, 'Integer');
                return utils.criarRetornoPromise(true, array);
            }
        }
    }
})();