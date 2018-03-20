/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.config.comum;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */
@Data
@Builder
public class ComboDto {
    private final Integer id;
    private final String descricao;
    private final Map<String, Object> dados;
}
