/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
@Builder
public class ProdutoDto {
    private final Integer id;
    private final String codigo;
    private final String ean;
    private final String eanTributario;
    private final String descricao;
    private final boolean situacao;
    private final Integer unidade;
    private final Integer cest;
    private final Integer grupo;
    private final Double precoCusto;
    private final Double lucro;
    private final Double precoVenda;
    private final Double precoEspecial;
    private final Double quantidadeEspecial;
    private final Integer origem;
    private final Integer csosn;
}
