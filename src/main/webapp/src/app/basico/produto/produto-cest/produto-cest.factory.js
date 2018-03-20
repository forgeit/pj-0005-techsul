(function () {
	'use strict';

	angular
		.module('produto.produto-cest')
		.factory('produtoCestUtils', produtoCestUtils);

	produtoCestUtils.$inject = ['controller', 'produtoCestRest'];

	function produtoCestUtils(utils, dataservice) {
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