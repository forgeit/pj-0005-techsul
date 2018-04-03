/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
public class EstoqueMovimentacaoCommand {
    private Date dataMovimentacao;
    private Integer tipoMovimentacao;
    private Integer produto;
    private Double quantidade;
    private Double precoCusto;
    private Double precoVenda;
    private String observacao;
}
