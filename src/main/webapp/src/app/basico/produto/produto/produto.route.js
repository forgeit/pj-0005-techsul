(function () {

	'use strict';

	angular
		.module('app')
		.config(routes);

	routes.$inject = ['$routeProvider', '$locationProvider'];

	function routes($routeProvider, $locationProvider) {
		$routeProvider
			.when('/basico/produto/produto', {
				templateUrl: 'src/app/basico/produto/produto/produto.html?' + new Date().getTime(),
				controller: 'Produto',
				controllerAs: 'vm'
			});
	}

})();