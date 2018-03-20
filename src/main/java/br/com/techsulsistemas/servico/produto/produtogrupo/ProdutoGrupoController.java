/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtogrupo;

import br.com.techsulsistemas.servico.config.comum.AutoCompleteDto;
import br.com.techsulsistemas.servico.config.ForgeController;
import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
@Path("basico/produto/produto-grupo")
public class ProdutoGrupoController extends ForgeController {
    
    private final ProdutoGrupoServico servico;

    public ProdutoGrupoController() {
        this.servico = new ProdutoGrupoServico();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProdutoGrupoCommand command) {
        try {
            ProdutoGrupoDto dto = ProdutoGrupoDto.builder()
                    .descricao(command.getDescricao())
                    .percentualLucro(command.getPercentualLucro())
                    .build();
            
            servico.criar(dto);
            addMessage("Sucesso ao salvar o grupo de produto");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @PUT
    @Path("{id:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, ProdutoGrupoCommand command) {
        try {
            ProdutoGrupoDto dto = ProdutoGrupoDto.builder()
                    .id(id)
                    .descricao(command.getDescricao())
                    .percentualLucro(command.getPercentualLucro())
                    .build();
            
            servico.atualizar(dto);
            addMessage("Sucesso ao atualizar o grupo de produto");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @DELETE
    @Path("{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        try {
            servico.remover(id);
            addMessage("Sucesso ao remover o registro");
        } catch (Exception ex) {
            addError("Ocorreu um erro ao remover o registro");
        }

        return build();
    }

    @GET
    @Path("{id:\\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
            ProdutoGrupoDto dto = servico.carregar(id);
            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("primeiro")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findFirst() {
        try {
            ProdutoGrupoDto dto = servico.primeiro();
            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("ultimo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findLast() {
        try {
            ProdutoGrupoDto dto = servico.ultimo();
            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("proximo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response proximo(@PathParam("id") Integer id) {
        try {
            ProdutoGrupoDto dto = servico.proximo(id);
            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("anterior/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response anterior(@PathParam("id") Integer id) {
        try {
            ProdutoGrupoDto dto = servico.anterior(id);
            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            List<ProdutoGrupoDto> lista = servico.carregarPorRange(new int[] {from, to});
            addData(lista);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("autocomplete/{filtro}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response autocomplete(@PathParam("filtro") String filtro) {
        try {
            List<AutoCompleteDto> lista = servico.autocomplete(filtro);
            addData(lista);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar a lista");
        }
        
        return build();
    }
    
    @GET
    @Path("combo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response combo() {
        try {
            List<ComboDto> lista = servico.combo();
            addData(lista);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar os dados");
        }
        
        return build();
    }
}