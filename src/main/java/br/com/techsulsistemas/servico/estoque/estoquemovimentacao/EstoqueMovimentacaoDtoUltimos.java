/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
@Builder
public class EstoqueMovimentacaoDtoUltimos {
    private final Integer id;
    private final String produto;
    private final Double quantidade;
    private final Double valor;
    private final String tipo;
}
