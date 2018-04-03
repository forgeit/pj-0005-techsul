(function () {

	angular
		.module('core.utils')
		.factory('controller', controller);

	controller.$inject = ['AuthToken', 'jwtHelper'];

	function controller(AuthToken, jwtHelper) {

		var service = {
			buscarDadosUsuario: buscarDadosUsuario,
			criarRetornoPromise: criarRetornoPromise,
			ler: ler
		};

		return service;

		function buscarDadosUsuario() {
            var usuario = {};

            if (AuthToken.getToken('accessToken')) {
                usuario = jwtHelper.decodeToken(AuthToken.getToken('accessToken'));
            }

            return usuario;
        }

		function criarRetornoPromise(exec, objeto) {
			var retorno = {};
			retorno.exec = exec;
			retorno.objeto = objeto;
			return retorno;
		}

		function ler(data, value) {
			if (value === undefined) {
				return data.data.data;
			}

			return data.data.data[value];
		}
	}
})();
