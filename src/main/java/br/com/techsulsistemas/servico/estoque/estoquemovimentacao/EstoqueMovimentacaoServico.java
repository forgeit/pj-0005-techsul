/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.estoque.estoque.Estoque;
import br.com.techsulsistemas.servico.estoque.estoque.EstoqueDao;
import br.com.techsulsistemas.servico.estoque.estoquemovimentacaotipo.EstoqueMovimentacaoTipo;
import br.com.techsulsistemas.servico.estoque.estoquemovimentacaotipo.EstoqueMovimentacaoTipoDao;
import br.com.techsulsistemas.servico.produto.produto.Produto;
import br.com.techsulsistemas.servico.produto.produto.ProdutoDao;
import br.com.techsulsistemas.servico.usuario.usuario.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EstoqueMovimentacaoServico {

    private final EstoqueMovimentacaoDao dao;
    private final Usuario usuario;

    public EstoqueMovimentacaoServico() {
        this.dao = new EstoqueMovimentacaoDao();
        /*
        TODO: pegar usuário do token
         */
        this.usuario = new Usuario(1);
    }

    public void create(EstoqueMovimentacaoDto dto) throws Exception {
        validarEntrada(dto);

        try {

            EstoqueMovimentacaoTipoDao estoqueMovimentacaoTipoDao = new EstoqueMovimentacaoTipoDao();
            EstoqueMovimentacaoTipo tipo = estoqueMovimentacaoTipoDao.encontrar(dto.getTipoMovimentacao());

            ProdutoDao produtoDao = new ProdutoDao();
            Produto produto = produtoDao.encontrar(dto.getProduto());

            EstoqueMovimentacao model = new EstoqueMovimentacao();

            model.setDataMovimentacao(dto.getDataMovimentacao());
            model.setTsRegistro(new Date());
            model.setProduto(produto);
            model.setEstoqueMovimentacaoTipo(tipo);
            model.setQuantidade(new BigDecimal(dto.getQuantidade()));
            model.setObservacao(dto.getObservacao());
            model.setUsuario(usuario);

            boolean editouProduto = false;
            boolean precisaRecalcularLucro = false;

            if (tipo.getFgAlterarPrecoCusto()) {
                model.setPrecoVenda(dto.getPrecoCusto() == null ? BigDecimal.ZERO : new BigDecimal(dto.getPrecoCusto()));
                produto.setPrecoCusto(dto.getPrecoCusto() == null ? produto.getPrecoCusto() : new BigDecimal(dto.getPrecoCusto()));
                editouProduto = true;
                precisaRecalcularLucro = true;

            }

            if (tipo.getFgAlterarPrecoVenda()) {
                model.setPrecoVenda(dto.getPrecoVenda() == null ? BigDecimal.ZERO : new BigDecimal(dto.getPrecoVenda()));
                produto.setPrecoVenda(dto.getPrecoVenda() == null ? produto.getPrecoVenda() : new BigDecimal(dto.getPrecoVenda()));
                editouProduto = true;
                precisaRecalcularLucro = true;
            }

            if (precisaRecalcularLucro) {
                if (produto.getPrecoVenda().compareTo(produto.getPrecoCusto()) > 0) {
                    Double custo = produto.getPrecoCusto().doubleValue();
                    Double venda = produto.getPrecoVenda().doubleValue();
                    Double lucro = (venda - custo) / custo;

                    produto.setLucro(new BigDecimal(lucro));
                } else {
                    produto.setLucro(BigDecimal.ZERO);
                }
            }

            boolean editouEstoque = false;
            Estoque estoque = null;

            if (tipo.getFgAlterarEstoque()) {
                EstoqueDao estoqueDao = new EstoqueDao();
                estoque = estoqueDao.carregarEstoquePorProduto(dto.getProduto());

                if (tipo.getFgSoma()) {
                    estoque.setQuantidade(estoque.getQuantidade().add(model.getQuantidade()));
                } else {
                    estoque.setQuantidade(estoque.getQuantidade().subtract(model.getQuantidade()));
                }

                editouEstoque = true;
            }

            DAO.begin();
            DAO.getEM().persist(model);

            if (editouProduto) {
                DAO.getEM().merge(produto);
            }

            if (editouEstoque) {
                DAO.getEM().merge(estoque);
            }

            DAO.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            DAO.rollback();
            throw ex;
        }
    }

    private void validarEntrada(EstoqueMovimentacaoDto dto) throws Exception {
        if (dto.getDataMovimentacao() == null) {
            throw new Exception("O campo data de movimentação é obrigatório.");
        }

        if (dto.getTipoMovimentacao() == null) {
            throw new Exception("O campo tipo é obrigatório.");
        }

        if (dto.getProduto() == null) {
            throw new Exception("O campo produto é obrigatório.");
        }

        if (dto.getQuantidade() == null && dto.getQuantidade() <= 0) {
            throw new Exception("O campo quantidade é obrigatório.");
        }
    }

    public List<EstoqueMovimentacaoDtoUltimos> carregarUltimasMovimentacoesPorProduto(Integer produto) throws Exception {
        try {
            List<EstoqueMovimentacao> lista = dao.carregarUltimasMovimentacoesPorProduto(produto);
            List<EstoqueMovimentacaoDtoUltimos> retorno = new ArrayList<>();

            for (EstoqueMovimentacao mvt : lista) {
                
                EstoqueMovimentacaoDtoUltimos dto = EstoqueMovimentacaoDtoUltimos.builder()
                        .id(mvt.getIdEstoqueMovimentacao())
                        .produto(mvt.getProduto().getDescricao())
                        .quantidade(mvt.getEstoqueMovimentacaoTipo().getFgSoma() ? 
                                mvt.getQuantidade().doubleValue() : 
                                mvt.getQuantidade().negate().doubleValue())
                        .valor(mvt.getPrecoVenda() == null ? new Double(0) : mvt.getPrecoVenda().doubleValue())
                        .tipo(mvt.getEstoqueMovimentacaoTipo().getDescricao())
                        .build();

                retorno.add(dto);
            }

            return retorno;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
