(function () {
	
	'use strict';

	angular
		.module('produto.produto')
		.controller('Produto', CtrlForm);

	CtrlForm.$inject = [
		'produtoRest',
		'produtoGrupoUtils',
		'multiPromise',
		'produtoUnidadeUtils']

	function CtrlForm(
		dataservice, 
		produtoGrupoUtils,
		multiPromise,
		produtoUnidadeUtils) {

		/* jshint validthis: true */
		var vm = this;

		var PRODUTO_GRUPO = 0;
		var PRODUTO_UNIDADE = 1;

		var zerado = {
			precoCusto: 0,
			precoVenda: 0,
			precoEspecial: 0,
			quantidadePrecoEspecial: 0
		}

		vm.model = zerado;

		init();

		function init() {
			var promises = [];
			promises.push(produtoGrupoUtils.carregarCombo());
			promises.push(produtoUnidadeUtils.carregarCombo());

			multiPromise.ready(promises).then(function(values) {
				if (values[PRODUTO_GRUPO].exec) {
					vm.grupoList = values[PRODUTO_GRUPO].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de grupos.');
				}

				if (values[PRODUTO_UNIDADE].exec) {
					vm.unidadeList = values[PRODUTO_UNIDADE].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de unidades.');
				}
			});
		}
	}

})(); 