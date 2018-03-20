/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
public class EmpresaCommand {
    private Integer id;
    private String razaoSocial;
    private String nomeFantasia;
    private String endereco;
    private String numero;
    private String complemento;
    private Integer cidade;
    private String cep;
    private String bairro;
    private String inscricaoEstadual;
    private String celular;
    private Integer crt;
    private Integer baseIcms;
    private Integer baseIcmsSt;
    private BigDecimal aliquotaCredito;
    private String cnpj;
    private String logo;
    private String email;
    private Integer escritorio;
}
