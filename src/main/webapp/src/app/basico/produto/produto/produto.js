(function () {
	
	'use strict';

	angular
		.module('produto.produto')
		.controller('Produto', CtrlForm);

	CtrlForm.$inject = [
		'produtoRest',
		'produtoGrupoUtils',
		'multiPromise',
		'produtoUnidadeUtils',
		'produtoCestUtils',
		'produtoOrigemUtils',
		'produtoCsosnUtils']

	function CtrlForm(
		dataservice, 
		produtoGrupoUtils,
		multiPromise,
		produtoUnidadeUtils,
		produtoCestUtils,
		produtoOrigemUtils,
		produtoCsosnUtils) {

		/* jshint validthis: true */
		var vm = this;

		var PRODUTO_GRUPO   = 0;
		var PRODUTO_UNIDADE = 1;
		var PRODUTO_CEST    = 2;
		var PRODUTO_ORIGEM  = 3;
		var PRODUTO_CSOSN   = 4;

		var KEY_ENTER = 13;

		vm.anterior            = anterior;
		vm.buscarPorCodigo     = buscarPorCodigo;
		vm.evtChangeLucro      = evtChangeLucro;
		vm.evtChangePrecoCusto = evtChangePrecoCusto;
		vm.evtChangePrecoVenda = evtChangePrecoVenda;
		vm.modoEdicao          = false;
		vm.novo                = novo;
		vm.primeiro            = primeiro;
		vm.proximo             = proximo;
		vm.remover             = remover;
		vm.salvar              = salvar;
		vm.setCsosn            = setCsosn;
		vm.setGrupo            = setGrupo;
		vm.setNCM              = setNCM;
		vm.setOrigem           = setOrigem;
		vm.setUnidade          = setUnidade;
		vm.ultimo              = ultimo;

		init();

		function anterior() {
			if (vm.model.id) {
				dataservice.anterior(vm.model.id).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
			}

			function success(response) {
				if (response.data.status == 'true') {
					vm.model = response.data.data.ProdutoDto;
					carregarObjetosAuxiliares(vm.model);
				}
			}
		}

		function buscarPorCodigo(codigo, evt) {
			if (evt.keyCode === KEY_ENTER) {
				if (codigo) {
					dataservice.buscar(codigo).then(success).catch(error);
				} else {
					vm.modoEdicao = false;
				}

				function error(response) {
					console.log(response);
				}

				function success(response) {
					vm.model = response.data.data.ProdutoDto;
					carregarObjetosAuxiliares(vm.model);
				}
			}
		}

		function carregarObjetosAuxiliares(objeto) {
			vm.modoEdicao = true;
			objeto.situacao = objeto.situacao ? 'true' : 'false';
			vm.unidadeList.some(someUnidade);
			vm.grupoList.some(someGrupo);
			vm.origemMercadoriaList.some(someOrigem);
			vm.ncmList.some(someNcm);
			vm.csosnList.some(someCsosn);
		}

		function evtChangeLucro() {
			if (vm.model.lucro && vm.model.lucro > 0) {
				vm.model.precoVenda = vm.model.precoCusto + (vm.model.lucro * vm.model.precoCusto);
			} else {
				vm.model.precoVenda = vm.model.precoCusto;
			}
		}

		function evtChangePrecoCusto() {
			if (vm.model.lucro && vm.model.lucro > 0) {
				vm.model.precoVenda = vm.model.precoCusto + (vm.model.lucro * vm.model.precoCusto);
			} else {
				vm.model.precoVenda = vm.model.precoCusto;
			}
		}

		function evtChangePrecoVenda() {
			if (vm.model.precoVenda > vm.model.precoCusto) {
				vm.model.lucro = (((vm.model.precoVenda - vm.model.precoCusto)) * 100 / vm.model.precoCusto) / 100;
			} else {
				vm.model.lucro = 0;
			}
		}

		function init() {
			var promises = [];

			setarObjetoInicial();

			promises.push(produtoGrupoUtils.carregarCombo());
			promises.push(produtoUnidadeUtils.carregarCombo());
			promises.push(produtoCestUtils.carregarCombo());
			promises.push(produtoOrigemUtils.carregarCombo());
			promises.push(produtoCsosnUtils.carregarCombo());

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

				if (values[PRODUTO_CEST].exec) {
					vm.ncmList = values[PRODUTO_CEST].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de NCM/CEST.');
				}

				if (values[PRODUTO_ORIGEM].exec) {
					vm.origemMercadoriaList = values[PRODUTO_ORIGEM].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de origens da mercadoria.');
				}

				if (values[PRODUTO_CSOSN].exec) {
					vm.csosnList = values[PRODUTO_CSOSN].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de CSOSN.');
				}
			});
		}

		function novo(formulario) {
			setarObjetoInicial();
			vm.modoEdicao = false;
			formulario.$setPristine();
		}

		function primeiro() {
			dataservice.primeiro().then(success).catch(error);

			function error(response) {
				toastr.error('Ocorreu um erro ao carregar os dados.');
			}

			function success(response) {
				vm.model = response.data.data.ProdutoDto;
				carregarObjetosAuxiliares(vm.model);
			}
		}

		function proximo() {
			if (vm.model.id) {
				dataservice.proximo(vm.model.id).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
			}

			function success(response) {
				if (response.data.status == 'true') {
					vm.model = response.data.data.ProdutoDto;
					carregarObjetosAuxiliares(vm.model);
				}
			}
		}

		function remover(formulario) {
			if (!confirm("Tem certeza que deseja remover o produto?")) {
				return false;
			} else {
				dataservice.remover(vm.model.id).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
				toastr.error('Ocorreu um erro ao remover o registro.');
			}

			function success(response) {
				if (response.data.status == 'true') {
					toastr.success(response.data.message[0].mensagem);
					novo(formulario);					
				} else {
					toastr.error(response.data.message[0].mensagem);
				}
			}
		}

		function salvar(formulario) {
			if (vm.modoEdicao) {
				dataservice.atualizar(vm.model.id, vm.model).then(success).catch(error);
			} else {
				dataservice.salvar(vm.model).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
				toastr.error("Ocorreu um erro ao salvar.");
			}

			function success(response) {
				if (response.data.status == 'true') {
					toastr.success(response.data.message[0].mensagem);
					novo(formulario);					
				} else {
					toastr.error(response.data.message[0].mensagem);
				}
			}
		}

		function setCsosn(objeto) {
			if (objeto == null) {
				delete vm.model.csosn;
			} else {
				vm.model.csosn = objeto.id;
			}
		}

		function setGrupo(objeto) {
			if (objeto == null) {
				delete vm.model.grupo;
			} else {
				vm.model.grupo = objeto.id;
				vm.model.lucro = objeto.dados.lucro == null ? vm.model.lucro : objeto.dados.lucro;

				if (objeto.dados.lucro != null) {
					if (vm.model.precoCusto != 0 && vm.model.precoCusto) {
						vm.model.precoVenda = vm.model.precoCusto + (objeto.dados.lucro * vm.model.precoCusto);
					}
				}
			}
		}

		function setNCM(objeto) {
			if (objeto == null) {
				delete vm.model.cest;
				delete vm.cest;
			} else {
				vm.model.cest = objeto.id;
				vm.cest = objeto.dados.cest + ' - ' + objeto.dados.cestDescricao;
			}
		}

		function setarObjetoInicial() {
			vm.model = {};
			vm.model.precoCusto         = 0;
			vm.model.precoVenda         = 0;
			vm.model.precoEspecial      = 0;
			vm.model.lucro              = 0;
			vm.model.quantidadeEspecial = 0;
			vm.model.situacao           = 'true';

			delete vm.csosn;
			delete vm.grupo;
			delete vm.cest;
			delete vm.origemMercadoria;
			delete vm.ncm;
			delete vm.unidade;
		}

		function setOrigem(objeto) {
			if (objeto == null) {
				delete vm.model.origem;
			} else {
				vm.model.origem = objeto.id;
			}
		}

		function setUnidade(objeto) {
			if (objeto == null) {
				delete vm.model.unidade;
			} else {
				vm.model.unidade = objeto.id;
			}
		}

		function someCsosn(objeto) {
			if (vm.model.csosn === objeto.id) {
				vm.csosn = objeto;
				return true;
			}
		}

		function someGrupo(objeto) {
			if (vm.model.grupo === objeto.id) {
				vm.grupo = objeto;
				return true;
			}
		}

		function someNcm(objeto) {
			if (vm.model.cest === objeto.id) {
				vm.ncm = objeto;
				setNCM(objeto);
				return true;
			}
		}

		function someOrigem(objeto) {
			if (vm.model.origem === objeto.id) {
				vm.origemMercadoria = objeto;
				return true;
			}
		}

		function someUnidade(objeto) {
			if (vm.model.unidade === objeto.id) {
				vm.unidade = objeto;
				return true;
			}
		}

		function ultimo() {
			dataservice.ultimo().then(success).catch(error);

			function error(response) {
				toastr.error('Ocorreu um erro ao carregar os dados.');
			}

			function success(response) {
				vm.model = response.data.data.ProdutoDto;
				carregarObjetosAuxiliares(vm.model);
			}
		}
	}

})(); 