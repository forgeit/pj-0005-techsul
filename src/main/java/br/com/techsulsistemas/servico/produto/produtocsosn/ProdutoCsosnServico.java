/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtocsosn;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class ProdutoCsosnServico {
    private final ProdutoCsosnDao dao;
    
    public ProdutoCsosnServico() {
        dao = new ProdutoCsosnDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<ProdutoCsosn> dados = dao.findAll();
            
            for (ProdutoCsosn model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(ProdutoCsosn model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdProdutoCsosn())
                .descricao(model.getCodigo() + " - " + model.getDescricao().substring(0, 30))
                .build();
    }
}
