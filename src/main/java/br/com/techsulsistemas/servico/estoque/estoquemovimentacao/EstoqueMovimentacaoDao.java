/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.config.banco.GenericDAO;
import java.util.Collections;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author kelvin
 */
public class EstoqueMovimentacaoDao extends GenericDAO<EstoqueMovimentacao> {
    
    private final Integer MAX_RESULTS = 10;
    
    public List<EstoqueMovimentacao> carregarUltimasMovimentacoesPorProduto(Integer produto) throws Exception {
        try {
            Query query = DAO.getEM().createQuery("SELECT e FROM EstoqueMovimentacao e WHERE e.produto.idProduto = :produto ORDER BY e.idEstoqueMovimentacao DESC");
            query.setParameter("produto", produto);
            query.setMaxResults(MAX_RESULTS);
            return (List<EstoqueMovimentacao>) query.getResultList();
        } catch (NoResultException ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
}
