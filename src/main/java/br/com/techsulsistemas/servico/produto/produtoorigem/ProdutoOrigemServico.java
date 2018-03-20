/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtoorigem;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class ProdutoOrigemServico {
    private final ProdutoOrigemDao dao;
    
    public ProdutoOrigemServico() {
        dao = new ProdutoOrigemDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<ProdutoOrigem> dados = dao.findAll();
            
            for (ProdutoOrigem model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(ProdutoOrigem model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdProdutoOrigem())
                .descricao(model.getDescricao())
                .build();
    }
}
