(function () {
	'use strict';

	angular
		.module('empresa.empresa-escritorio-contabil')
		.factory('empresaEscritorioContabilUtils', empresaEscritorioContabilUtils);

	empresaEscritorioContabilUtils.$inject = ['controller', 'empresaEscritorioContabilRest'];

	function empresaEscritorioContabilUtils(utils, dataservice) {
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