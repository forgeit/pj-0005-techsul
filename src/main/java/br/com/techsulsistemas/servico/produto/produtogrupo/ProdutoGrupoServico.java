/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtogrupo;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.config.comum.AutoCompleteDto;
import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kelvin
 */
public class ProdutoGrupoServico {
    
    private final ProdutoGrupoDao dao;
    
    public ProdutoGrupoServico() {
        dao = new ProdutoGrupoDao();
    }
    
    public void atualizar(ProdutoGrupoDto dto) throws Exception {
        validarEntrada(dto);
        
        try {
            ProdutoGrupo model = dao.encontrar(dto.getId());
            
            model.setDescricao(dto.getDescricao());
            model.setPercentualLucro(dto.getPercentualLucro());
            model.setDescricaoCompleta(model.getIdProdutoGrupo() + " - " + model.getDescricao());
            
            DAO.begin();
            DAO.getEM().merge(model);
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public List<AutoCompleteDto> autocomplete(String valor) throws Exception {
        try {
            List<AutoCompleteDto> lista = new ArrayList<>();
            List<ProdutoGrupo> dados = dao.buscarSemelhantes("descricaoCompleta", valor);
            
            for (ProdutoGrupo model : dados) {
                lista.add(criarConversorAutoCompleteDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<ProdutoGrupo> dados = dao.findAll();
            
            for (ProdutoGrupo model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(ProdutoGrupo model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdProdutoGrupo())
                .descricao(model.getDescricao())
                .build();
    }
    
    public ProdutoGrupoDto carregar(Integer id) throws Exception {
        return criarConversorDto(dao.encontrar(id));
    }
    
    public ProdutoGrupoDto primeiro() throws Exception {
        return criarConversorDto(dao.encontrarPrimeiro("idProdutoGrupo"));
    }
    
    public ProdutoGrupoDto ultimo() throws Exception {
        return criarConversorDto(dao.encontrarUltimo("idProdutoGrupo"));
    }
    
    public ProdutoGrupoDto proximo(Integer ultimoId) throws Exception {
        return criarConversorDto(dao.proximo("idProdutoGrupo", ultimoId));
    }
    
    public ProdutoGrupoDto anterior(Integer ultimoId) throws Exception {
        return criarConversorDto(dao.anterior("idProdutoGrupo", ultimoId));
    }
    
    public List<ProdutoGrupoDto> carregarPorRange(int[] params) throws Exception {
        try {
            
            List<ProdutoGrupoDto> lista = new ArrayList<>();
            List<ProdutoGrupo> dados = dao.findRange(params);
            
            for (ProdutoGrupo model : dados) {
                lista.add(ProdutoGrupoDto.builder()
                        .id(model.getIdProdutoGrupo())
                        .descricao(model.getDescricao())
                        .percentualLucro(model.getPercentualLucro())
                        .build()
                );
            }
            
            return lista;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void criar(ProdutoGrupoDto dto) throws Exception {
        validarEntrada(dto);
        
        try {
            ProdutoGrupo model = new ProdutoGrupo();
            
            model.setDescricao(dto.getDescricao());
            model.setPercentualLucro(dto.getPercentualLucro());
            
            DAO.begin();
            DAO.getEM().persist(model);
            DAO.getEM().flush();
            
            model.setDescricaoCompleta(model.getIdProdutoGrupo() + " - " + model.getDescricao());
            
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void remover(Integer id) throws Exception {
        dao.remover(id);
    }
    
    private void validarEntrada(ProdutoGrupoDto dto) throws Exception {
        if (StringUtils.isBlank(dto.getDescricao())) {
            throw new Exception("O campo descrição é obrigatório.");
        }
        
        if (dao.isNotUnique("produto_grupo", "descricao", dto.getDescricao(), dto.getId())) {
            throw new Exception("O valor informado já consta na base de dados.");
        }
    }
    
    private ProdutoGrupoDto criarConversorDto(ProdutoGrupo model) throws Exception {
        return ProdutoGrupoDto.builder()
                    .id(model.getIdProdutoGrupo())
                    .descricao(model.getDescricao())
                    .percentualLucro(model.getPercentualLucro())
                    .build();
    }
    
    private AutoCompleteDto criarConversorAutoCompleteDto(ProdutoGrupo model) throws Exception {
        return AutoCompleteDto.builder()
                .id(model.getIdProdutoGrupo())
                .descricao(model.getDescricao())
                .build();
    }
}
