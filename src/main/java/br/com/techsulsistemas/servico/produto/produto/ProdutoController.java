/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produto;

import br.com.techsulsistemas.servico.config.ForgeController;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path ("basico/produto/produto")
public class ProdutoController extends ForgeController {
    
    private final ProdutoServico servico;
    
    public ProdutoController() {
        this.servico = new ProdutoServico();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProdutoCommand command) {
        try {
            ProdutoDto produtoDto = ProdutoDto.builder()
                    .codigo(command.getCodigo())
                    .ean(command.getEan())
                    .eanTributario(command.getEanTributario())
                    .descricao(command.getDescricao())
                    .situacao(command.isSituacao())
                    .unidade(command.getUnidade())
                    .cest(command.getCest())
                    .grupo(command.getGrupo())
                    .precoCusto(command.getPrecoCusto())
                    .lucro(command.getLucro())
                    .precoVenda(command.getPrecoVenda())
                    .precoEspecial(command.getPrecoEspecial())
                    .quantidadeEspecial(command.getQuantidadeEspecial())
                    .origem(command.getOrigem())
                    .csosn(command.getCsosn())
                    .build();
            
            servico.criar(produtoDto);
            addMessage("Sucesso ao registrar o produto.");
        } catch (Exception ex) {
            ex.printStackTrace();
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
    @Path("primeiro")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findFirst() {
        try {
            ProdutoDto dto = servico.primeiro();
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
            ProdutoDto dto = servico.ultimo();
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
            ProdutoDto dto = servico.proximo(id);
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
            ProdutoDto dto = servico.anterior(id);
            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
}
