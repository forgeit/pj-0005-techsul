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
        'produtoUtils',
        'estoqueUtils',
        'estoqueMovimentacaoUtils',
        'controller',
        '$scope']

    function CtrlForm(
        dataservice, 
        multiPromise,
        $location,
        estoqueMovimentacaoTipoUtils, 
        produtoUtils,
        estoqueUtils,
        estoqueMovimentacaoUtils,
        controller,
        $scope) {

        /* jshint validthis: true */
        var vm = this;

        var ATALHO_SALVAR = 113;

        var ESTOQUE_MOVIMENTACAO_TIPO = 0;

        var PROD_ESTOQUE     = 0;
        var PROD_MVT_ESTOQUE = 1;

        var $doc = angular.element(document);
        var serialUltimasMoficacoes = 1;

        vm.autocomplete        = autocomplete;
        vm.novo = novo;
        vm.salvar              = salvar;
        vm.setProduto          = setProduto;
        vm.setTipoMovimentacao = setTipoMovimentacao;
        vm.ultimasMovimentacoes = [];
        vm.voltar              = voltar;

        init();

        $doc.on("keyup", atalho);

        $scope.$on("$destroy", function () {
            $doc.off("keyup", atalho);
        });

        function adicionarUltimaModificacao(objeto) {
            vm.ultimasMovimentacoes.push({
                id: serialUltimasMoficacoes++,
                produto: objeto.produto,
                quantidade: objeto.soma ? objeto.quantidade : (objeto.quantidade * -1),
                valor: objeto.valor
            });
        }

        function atalho(e) {
            if (ATALHO_SALVAR === e.keyCode) {
                if (vm.formulario.$valid) {
                    salvar(vm.formulario);
                } else {
                    toastr.error('Informe os campos necessários para salvar.');
                }
            }
        }

        function autocomplete(auxiliar) {
            return produtoUtils.autocomplete(auxiliar).then(success).catch(error);

            function error(response) {
                return [];
            }

            function success(response) {
                return response.exec ? response.objeto : [];
            }
        }

        function init() {
            var promises = [];

            setarObjetoInicial();

            promises.push(estoqueMovimentacaoTipoUtils.carregarCombo());

            multiPromise.ready(promises).then(function(values) {
                if (values[ESTOQUE_MOVIMENTACAO_TIPO].exec) {
                    vm.tipoMovimentacaoList = values[ESTOQUE_MOVIMENTACAO_TIPO].objeto;
                } else {
                    toastr.error('Erro ao carregar a lista de tipo de movimentação.');
                }
            });
        }

        function novo(formulario, objetoInicial) {
            vm.model = {};
            angular.element('#produto').focus();
            vm.usuario = controller.buscarDadosUsuario().nome;
            delete vm.produto;
            setProduto(null);

            if (objetoInicial) {
                if (objetoInicial.tipoMovimentacao) {
                    vm.model.tipoMovimentacao = objetoInicial.tipoMovimentacao;
                }

                if (objetoInicial.dataMovimentacao) {
                    vm.model.dataMovimentacao = objetoInicial.dataMovimentacao;
                } else {
                    vm.model.dataMovimentacao = new Date();
                }
            } else {
                vm.model.dataMovimentacao = new Date();
                delete vm.tipoMovimentacao;
                setTipoMovimentacao(null);
            }
        }

        function salvar(formulario) {
            if (formulario.$valid) {
                dataservice.salvar(vm.model).then(success).catch(error);
            } else {
                toastr.error('Informe os campos necessários para salvar.');
            }

            function error(response) {
                console.log(response);
                toastr.error("Ocorreu um erro ao salvar.");
            }

            function success(response) {
                if (response.data.status == 'true') {
                    toastr.success(response.data.message[0].mensagem);

                    adicionarUltimaModificacao({ 
                        produto: vm.produtoDescricao, 
                        quantidade: vm.model.quantidade, 
                        soma: vm.tipoMovimentacao.dados.soma, 
                        valor: vm.model.precoCusto ? vm.model.precoCusto : 0});

                    novo(formulario, {
                        tipoMovimentacao: vm.model.tipoMovimentacao, 
                        dataMovimentacao: vm.model.dataMovimentacao});
                } else {
                    toastr.error(response.data.message[0].mensagem);
                }
            }
        }

        function setarObjetoInicial(codigo) {
            vm.model = {};
            vm.model.dataMovimentacao   = new Date();
            delete vm.produto;
        }

        function setProduto(objeto) {
            var promises = [];

            if (objeto === null) {
                delete vm.produtoDescricao;
                delete vm.produtoEstoque;
                delete vm.model.produto;
            } else {
                if (vm.produto.id) {
                    vm.model.produto = vm.produto.id;

                    promises.push(estoqueUtils.carregarPorProduto(vm.produto.id));

                    multiPromise.ready(promises).then(function(values) {
                        if (values[PROD_ESTOQUE].exec) {
                            vm.produtoDescricao  = values[PROD_ESTOQUE].objeto.produto;
                            vm.produtoEstoque = values[PROD_ESTOQUE].objeto.quantidade;
                        } else {
                            delete vm.produtoDescricao;
                            delete vm.produtoEstoque;
                        }
                    });
                }
            }
        }

        function setTipoMovimentacao(objeto) {
            if (objeto && objeto.id) {
                vm.model.tipoMovimentacao = objeto.id;
            } else {
                delete vm.model.tipoMovimentacao;
            }
        }

        function voltar() {
            $location.path('/');
        }
    }

})(); 