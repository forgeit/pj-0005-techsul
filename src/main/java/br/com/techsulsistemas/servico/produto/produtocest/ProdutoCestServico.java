/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtocest;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class ProdutoCestServico {
    
    private final ProdutoCestDao dao;
    
    public ProdutoCestServico() {
        dao = new ProdutoCestDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<ProdutoCest> dados = dao.findAll();
            
            for (ProdutoCest model : dados) {
                HashMap<String, Object> cest = new HashMap<>();
                cest.put("cest", model.getCest());
                cest.put("cestDescricao", model.getDescricao());
                lista.add(criarConversorComboDto(model, cest));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(ProdutoCest model, HashMap<String, Object> cest) throws Exception {
        return ComboDto.builder()
                .id(model.getIdProdutoCest())
                .descricao(model.getNcm())
                .dados(cest)
                .build();
    }
    
}
