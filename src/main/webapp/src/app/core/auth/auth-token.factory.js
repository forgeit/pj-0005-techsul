(function () {
	'use strict';

	angular
		.module('core.auth')
		.factory('AuthToken', AuthToken);

	AuthToken.$inject = ['$sessionStorage'];

	/* @ngInject */
	function AuthToken($sessionStorage) {
		var service = {
			deleteToken: deleteToken,
			getToken: getToken,
			setToken: setToken,
			ler: ler
		};

		return service;
		//////////////

		function ler() {
			if($sessionStorage.accessToken){
            	return $sessionStorage.accessToken == 'notAuth' ?  false : $sessionStorage.accessToken ;
			}
			return false;
        }

		function deleteToken(id) {
			delete $sessionStorage[id];
		}

		function getToken(id) {
			var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8va29wcC5jb20uYnIvIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1MjI4MjE4MTgsImlhdCI6MTUyMjczNTQxOCwicm9sIjoiQURNSU4iLCJub21lIjoiS2VsdmluIFBlcmVpcmEgQWx2ZXMifQ.x2-HoazgI_SQv_SGpC6pdotBUN-begiMpCFt6igqqfk";
			// return $sessionStorage[id];
			return token;
		}

		function setToken(token, id) {
			$sessionStorage[id] = token;
		}
	}
})();	