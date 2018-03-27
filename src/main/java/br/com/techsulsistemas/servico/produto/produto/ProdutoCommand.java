/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produto;

import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
public class ProdutoCommand {
    
    private Integer id;
    private String codigo;
    private String ean;
    private String eanTributario;
    private String descricao;
    private boolean situacao;
    private Integer unidade;
    private Integer cest;
    private Integer grupo;
    private Double precoCusto;
    private Double lucro;
    private Double precoVenda;
    private Double precoEspecial;
    private Double quantidadeEspecial;
    private Integer origem;
    private Integer csosn;
    
}
