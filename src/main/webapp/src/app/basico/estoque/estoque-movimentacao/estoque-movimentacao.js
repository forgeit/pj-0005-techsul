(function () {
    
    'use strict';

    angular
        .module('estoque.estoque-movimentacao')
        .controller('EstoqueMovimentacao', CtrlForm);

    CtrlForm.$inject = [
        'estoqueMovimentacaoRest',
        'multiPromise',
        '$location',
        'estoqueMovimentacaoTipoUtils',
        'produtoUtils']

    function CtrlForm(
        dataservice, 
        multiPromise,
        $location,
        estoqueMovimentacaoTipoUtils, 
        produtoUtils) {

        /* jshint validthis: true */
        var vm = this;

        var ESTOQUE_MOVIMENTACAO_TIPO = 0;

        vm.autocomplete = autocomplete;
        vm.voltar = voltar;

        init();

        function autocomplete(auxiliar) {
            console.log(auxiliar);

            return produtoUtils.autocomplete(auxiliar).then(success).catch(error);

            function error(response) {
                return [];
            }

            function success(response) {
                if (response.exec) {
                    return response.objeto;
                } else {
                    return [];
                }
            }
        }

        function init() {
            var promises = [];

            promises.push(estoqueMovimentacaoTipoUtils.carregarCombo());

            multiPromise.ready(promises).then(function(values) {
                if (values[ESTOQUE_MOVIMENTACAO_TIPO].exec) {
                    vm.tipoMovimentacaoList = values[ESTOQUE_MOVIMENTACAO_TIPO].objeto;
                } else {
                    toastr.error('Erro ao carregar a lista de tipo de movimentação.');
                }
            });

        }

        function voltar() {
            $location.path('/');
        }
    }

})(); 