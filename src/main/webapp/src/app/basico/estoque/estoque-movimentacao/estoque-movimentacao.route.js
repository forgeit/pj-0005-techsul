(function () {

    'use strict';

    angular
        .module('app')
        .config(routes);

    routes.$inject = ['$routeProvider', '$locationProvider'];

    function routes($routeProvider, $locationProvider) {
        $routeProvider
            .when('/basico/estoque/estoque-movimentacao', {
                templateUrl: 'src/app/basico/estoque/estoque-movimentacao/estoque-movimentacao.html?' + new Date().getTime(),
                controller: 'EstoqueMovimentacao',
                controllerAs: 'vm'
            });
    }

})();