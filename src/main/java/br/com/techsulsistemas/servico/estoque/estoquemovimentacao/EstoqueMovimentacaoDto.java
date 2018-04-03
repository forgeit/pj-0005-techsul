/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
@Builder
public class EstoqueMovimentacaoDto {
    private final Date dataMovimentacao;
    private final Integer tipoMovimentacao;
    private final Integer produto;
    private final Double quantidade;
    private final Double precoCusto;
    private final Double precoVenda;
    private final String observacao;
}
