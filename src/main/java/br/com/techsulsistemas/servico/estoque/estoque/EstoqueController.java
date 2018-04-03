/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoque;

import br.com.techsulsistemas.servico.config.ForgeController;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */

@Stateless
@Path("basico/estoque/estoque")
public class EstoqueController extends ForgeController {
    
    private final EstoqueServico servico;
    
    public EstoqueController () {
        this.servico = new EstoqueServico();
    }
    
    @GET
    @Path("produto/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response carregarEstoquePorProduto(@PathParam("id") Integer id) {
        try {
            addData(servico.carregarEstoquePorProduto(id));
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o estoque do produto.");
        }

        return build();
    }
    
    
}
