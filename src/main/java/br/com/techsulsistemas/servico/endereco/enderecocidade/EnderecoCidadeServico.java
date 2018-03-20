/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.endereco.enderecocidade;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EnderecoCidadeServico {
    
    private final EnderecoCidadeDao dao;
    
    public EnderecoCidadeServico() {
        dao = new EnderecoCidadeDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<EnderecoCidade> dados = dao.findAll();
            
            for (EnderecoCidade model : dados) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("estado", model.getEnderecoEstado().getSigla());
                lista.add(criarConversorComboDto(model, map));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(EnderecoCidade model, HashMap<String, Object> dados) throws Exception {
        return ComboDto.builder()
                .id(model.getIdEnderecoCidade())
                .descricao(model.getNome())
                .dados(dados)
                .build();
    }
    
}
