/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtounidade;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class ProdutoUnidadeServico {
    private final ProdutoUnidadeDao dao;
    
    public ProdutoUnidadeServico() {
        dao = new ProdutoUnidadeDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<ProdutoUnidade> dados = dao.findAllOrderBy("unidade");
            
            for (ProdutoUnidade model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(ProdutoUnidade model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdProdutoUnidade())
                .descricao(model.getUnidade())
                .build();
    }
}
