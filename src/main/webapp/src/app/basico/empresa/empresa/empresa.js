(function () {
	
	'use strict';

	angular
		.module('empresa.empresa')
		.controller('Empresa', CtrlForm);

	CtrlForm.$inject = [
		'empresaRest', 
		'$q', 
		'$http', 
		'multiPromise',
		'empresaCodigoRegimeTributarioUtils',
		'empresaEscritorioContabilUtils',
		'enderecoCidadeUtils',
		'$location']

	function CtrlForm(
		dataservice, 
		$q, 
		$http, 
		multiPromise,
		empresaCodigoRegimeTributarioUtils,
		empresaEscritorioContabilUtils,
		enderecoCidadeUtils,
		$location) {

		/* jshint validthis: true */
		var vm = this;

		var CODIGO_REGIME_TRIBUTARIO = 0;
		var ESCRITORIO_CONTABIL      = 1;
		var CIDADE                   = 2;
		
		vm.carregarSiglaEstadoPorCidade = carregarSiglaEstadoPorCidade;
		vm.estado                       = '-';
		vm.modoEdicao                   = false;
		vm.salvar                       = salvar;
		vm.voltar                       = voltar;

		init();

		function carregarSiglaEstadoPorCidade(cidade) {
			var encontrou = false;

			for (var i = 0; i < vm.cidadeList.length; i++) {
				if (vm.cidadeList[i].id === cidade) {
					vm.estado = vm.cidadeList[i].dados.estado;
					encontrou = true;
					break;
				}

				if (!encontrou) {
					vm.estado = '-';
				}
			}
		}

		function init() {
			var promises = [];
			promises.push(empresaCodigoRegimeTributarioUtils.carregarCombo());
			promises.push(empresaEscritorioContabilUtils.carregarCombo());
			promises.push(enderecoCidadeUtils.carregarCombo());
			promises.push(ultimo());

			multiPromise.ready(promises).then(function(values) {
				if (values[CODIGO_REGIME_TRIBUTARIO].exec) {
					vm.crtList = values[CODIGO_REGIME_TRIBUTARIO].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de códigos do regime tributário.');
				}

				if (values[ESCRITORIO_CONTABIL].exec) {
					vm.escritorioList = values[ESCRITORIO_CONTABIL].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de escritório contábil.');
				}

				if (values[CIDADE].exec) {
					vm.cidadeList = values[CIDADE].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de cidades.');
				}
			});
		}

		function salvar(formulario) {
			dataservice.atualizar(vm.model.id, vm.model).then(success).catch(error);		

			function error(response) {
				console.log(response);
				toastr.error("Ocorreu um erro ao salvar.");
			}

			function success(response) {
				if (response.data.status == 'true') {
					toastr.success(response.data.message[0].mensagem);
				} else {
					toastr.error(response.data.message[0].mensagem);
				}
			}
		}

		function ultimo() {
			dataservice.ultimo().then(success);

			function success(response) {
				vm.model = response.data.data.EmpresaDto;
			}
		}

		function voltar() {
			$location.path('/');
		}
	}

})(); 