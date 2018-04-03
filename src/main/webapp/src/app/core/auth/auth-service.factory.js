(function () {
	'use strict';

	angular
		.module('core.auth')
		.factory('AuthService', AuthService);

	AuthService.$inject = ['controller','configuracaoREST', '$http', "AuthToken", '$q', '$location'];

	/* @ngInject */
	function AuthService(controller, configuracaoREST, $http, AuthToken, $q, $location) {
		var service = {
			logout: logout,
			sigin: sigin
		};

		var msg = controller.msg;

		return service;
		///////////////


		function logout() {
			AuthToken.deleteToken('accessToken');
		}

		function sigin(data) {
			$http.post(configuracaoREST.url + configuracaoREST.login, data)
				.then(function (data) {
					if(data.data.data.token){
						AuthToken.setToken(data.data.data.token, 'accessToken');
						$location.path('home');
					}else{
						controller.feed(msg.MG019);
					}
				}).catch(function(data){
					controller.feed(msg.MG019);
					AuthToken.setToken("notAuth", 'accessToken');
				});

		}
	}
})();
