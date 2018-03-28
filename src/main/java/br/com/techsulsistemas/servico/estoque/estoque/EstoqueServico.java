/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoque;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.produto.produto.Produto;
import java.math.BigDecimal;

/**
 *
 * @author kelvin
 */
public class EstoqueServico {
  
    private final EstoqueDao dao;
    
    public EstoqueServico() {
        dao = new EstoqueDao();
    }
    
    public void criar(EstoqueDto dto) throws Exception {
        validarEntrada(dto);
        
        try {
            Estoque estoque = new Estoque();
            
            estoque.setProduto(new Produto(dto.getProduto()));
            estoque.setQuantidade(new BigDecimal(dto.getQuantidade()));
            
            DAO.getEM().persist(estoque);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void validarEntrada(EstoqueDto dto) throws Exception {
        if (dto.getProduto() == null) {
            throw new Exception("Para registrar um item no estoque é necessário saber o seu código.");
        }
    }
}
