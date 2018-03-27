/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produto;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.produto.produtocest.ProdutoCest;
import br.com.techsulsistemas.servico.produto.produtocsosn.ProdutoCsosn;
import br.com.techsulsistemas.servico.produto.produtogrupo.ProdutoGrupo;
import br.com.techsulsistemas.servico.produto.produtoorigem.ProdutoOrigem;
import br.com.techsulsistemas.servico.produto.produtounidade.ProdutoUnidade;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kelvin
 */
public class ProdutoServico {
    
    private final ProdutoDao dao;
    
    public ProdutoServico() {
        dao = new ProdutoDao();
    }
    
    public void criar(ProdutoDto dto) throws Exception {
        validarEntrada(dto);
        
        try {        
            Produto produto = new Produto();
            
            produto.setCodigo(dto.getCodigo());
            produto.setEan(dto.getEan());
            produto.setEanTributario(dto.getEanTributario());
            produto.setDescricao(dto.getDescricao());
            produto.setFgSituacao(dto.isSituacao());
            produto.setProdutoUnidade(new ProdutoUnidade(dto.getUnidade()));
            produto.setProdutoCest(new ProdutoCest(dto.getCest()));
            produto.setProdutoGrupo(new ProdutoGrupo(dto.getGrupo()));
            produto.setPrecoCusto(new BigDecimal(dto.getPrecoCusto()));
            produto.setLucro(new BigDecimal(dto.getLucro()));
            produto.setPrecoVenda(new BigDecimal(dto.getPrecoVenda()));
            produto.setPrecoEspecial(new BigDecimal(dto.getPrecoEspecial()));
            produto.setQuantidadePrecoEspecial(new BigDecimal(dto.getQuantidadeEspecial()));
            produto.setProdutoOrigem(new ProdutoOrigem(dto.getOrigem()));
            produto.setProdutoCsosn(new ProdutoCsosn(dto.getCsosn()));
            
            DAO.begin();
            DAO.getEM().persist(produto);
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public ProdutoDto carregar(String codigo) throws Exception {
        return criarConversorDto(dao.encontrarProdutoPorCodigo(codigo));
    }
    
    public void atualizar(ProdutoDto dto) throws Exception {
        validarEntrada(dto);
        
        try {        
            Produto produto = dao.encontrar(dto.getId());
            
            produto.setCodigo(dto.getCodigo());
            produto.setEan(dto.getEan());
            produto.setEanTributario(dto.getEanTributario());
            produto.setDescricao(dto.getDescricao());
            produto.setFgSituacao(dto.isSituacao());
            produto.setProdutoUnidade(new ProdutoUnidade(dto.getUnidade()));
            produto.setProdutoCest(new ProdutoCest(dto.getCest()));
            produto.setProdutoGrupo(new ProdutoGrupo(dto.getGrupo()));
            produto.setPrecoCusto(new BigDecimal(dto.getPrecoCusto()));
            produto.setLucro(new BigDecimal(dto.getLucro()));
            produto.setPrecoVenda(new BigDecimal(dto.getPrecoVenda()));
            produto.setPrecoEspecial(new BigDecimal(dto.getPrecoEspecial()));
            produto.setQuantidadePrecoEspecial(new BigDecimal(dto.getQuantidadeEspecial()));
            produto.setProdutoOrigem(new ProdutoOrigem(dto.getOrigem()));
            produto.setProdutoCsosn(new ProdutoCsosn(dto.getCsosn()));
            
            DAO.begin();
            DAO.getEM().merge(produto);
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public ProdutoDto primeiro() throws Exception {
        return criarConversorDto(dao.encontrarPrimeiro("idProduto"));
    }
    
    public ProdutoDto ultimo() throws Exception {
        return criarConversorDto(dao.encontrarUltimo("idProduto"));
    }
    
    public ProdutoDto proximo(Integer ultimoId) throws Exception {
        return criarConversorDto(dao.proximo("idProduto", ultimoId));
    }
    
    public ProdutoDto anterior(Integer ultimoId) throws Exception {
        return criarConversorDto(dao.anterior("idProduto", ultimoId));
    }
    
    private ProdutoDto criarConversorDto (Produto model) {
        return ProdutoDto.builder()
                    .id(model.getIdProduto())
                    .codigo(model.getCodigo())
                    .ean(model.getEan())
                    .eanTributario(model.getEanTributario())
                    .descricao(model.getDescricao())
                    .situacao(model.getFgSituacao())
                    .unidade(model.getProdutoUnidade().getIdProdutoUnidade())
                    .cest(model.getProdutoCest().getIdProdutoCest())
                    .grupo(model.getProdutoGrupo().getIdProdutoGrupo())
                    .precoCusto(model.getPrecoCusto() == null ? 0 : model.getPrecoCusto().doubleValue())
                    .lucro(model.getLucro() == null ? 0 : model.getLucro().doubleValue())
                    .precoVenda(model.getPrecoVenda() == null ? 0 : model.getPrecoVenda().doubleValue())
                    .precoEspecial(model.getPrecoEspecial() == null ? 0 : model.getPrecoEspecial().doubleValue())
                    .quantidadeEspecial(model.getQuantidadePrecoEspecial() == null ? 0 : model.getQuantidadePrecoEspecial().doubleValue())
                    .origem(model.getProdutoOrigem().getIdProdutoOrigem())
                    .csosn(model.getProdutoCsosn().getIdProdutoCsosn())
                    .build();
    }
    
    public void remover(Integer id) throws Exception {
        dao.remover(id);
    }
    
    private void validarEntrada(ProdutoDto dto) throws Exception {
        if (StringUtils.isBlank(dto.getCodigo())) {
            throw new Exception("O campo código é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getDescricao())) {
            throw new Exception("O campo descrição é obrigatório.");
        }
        
        if (dto.getUnidade() == null) {
            throw new Exception("O campo unidade é obrigatório.");
        }
        
        if (dto.getCest() == null) {
            throw new Exception("O campo NCM/CEST é obrigatório.");
        }
        
        if (dto.getGrupo() == null) {
            throw new Exception("O campo grupo é obrigatório.");
        }
        
        if (dto.getOrigem() == null) {
            throw new Exception("O campo origem da mercadoria é obrigatório.");
        }
        
        if (dto.getCsosn() == null) {
            throw new Exception("O campo CSOSN é obrigatório.");
        }
        
        if (dao.isNotUnique("produto", "codigo", dto.getCodigo(), dto.getId())) {
            throw new Exception("O código informado para o produto já consta na base de dados.");
        }
        
        if (dao.isNotUnique("produto", "ean", dto.getEan(), dto.getId())) {
            throw new Exception("O EAN informado para o produto já consta na base de dados.");
        }
        
        if (dao.isNotUnique("produto", "ean_tributario", dto.getEanTributario(), dto.getId())) {
            throw new Exception("O EAN tributário informado para o produto já consta na base de dados.");
        }
        
        if (dao.isNotUnique("produto", "descricao", dto.getDescricao(), dto.getId())) {
            throw new Exception("A descrição informada para o produto já consta na base de dados.");
        }
    }
}
