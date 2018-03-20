(function () {
	'use strict';

	angular
		.module('produto.produto-grupo')
		.factory('produtoGrupoUtils', produtoGrupoUtils);

	produtoGrupoUtils.$inject = ['controller', 'produtoGrupoRest'];

	function produtoGrupoUtils(utils, dataservice) {
		var service = {
			carregarCombo: carregarCombo
		};

		return service;

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
	}
})();