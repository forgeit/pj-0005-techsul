/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Builder
@Data
public class EmpresaDto {
    private final Integer id;
    private final String razaoSocial;
    private final String nomeFantasia;
    private final String endereco;
    private final String numero;
    private final String complemento;
    private final Integer cidade;
    private final String cep;
    private final String email;
    private final String bairro;
    private final String inscricaoEstadual;
    private final String celular;
    private final Integer crt;
    private final Integer baseIcms;
    private final Integer baseIcmsSt;
    private final BigDecimal aliquotaCredito;
    private final String cnpj;
    private final String logo;
    private final Integer escritorio;  
}
