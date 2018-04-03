/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoque;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.config.banco.GenericDAO;
import javax.persistence.Query;

/**
 *
 * @author kelvin
 */
public class EstoqueDao extends GenericDAO<Estoque> {
    
    public Estoque carregarEstoquePorProduto(Integer produto) throws Exception {
        Query query = DAO.getEM().createQuery("SELECT e FROM Estoque e WHERE e.produto.idProduto = :produto");
        query.setParameter("produto", produto);
        query.setMaxResults(1);
        
        return (Estoque) query.getSingleResult();
    }
    
}
