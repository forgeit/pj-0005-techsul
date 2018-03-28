/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacaotipo;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EstoqueMovimentacaoTipoServico {
    
    private final EstoqueMovimentacaoTipoDao dao;
    
    public EstoqueMovimentacaoTipoServico() {
        this.dao = new EstoqueMovimentacaoTipoDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<EstoqueMovimentacaoTipo> dados = dao.findAllOrderBy("descricao");
            
            for (EstoqueMovimentacaoTipo model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(EstoqueMovimentacaoTipo model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdEstoqueMovimentacaoTipo())
                .descricao(model.getDescricao())
                .build();
    }
}
