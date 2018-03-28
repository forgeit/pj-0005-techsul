/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produto;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.config.banco.GenericDAO;

/**
 *
 * @author kelvin
 */
public class ProdutoDao extends GenericDAO<Produto> {
    
    public Produto encontrarProdutoPorCodigo(String codigo) throws Exception {
        return (Produto) DAO.getEM().createQuery("SELECT p FROM Produto p WHERE p.codigo = :codigo")
                .setParameter("codigo", codigo)
                .setMaxResults(1)
                .getSingleResult();
    }
    
    public Integer ultimoId() throws Exception{
        return (Integer) DAO.getEM().createQuery("SELECT MAX(p.idProduto) FROM Produto p")
                .getSingleResult();
    }
    
}
