/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import br.com.techsulsistemas.servico.config.ForgeController;
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
@Path("basico/empresa/empresa")
public class EmpresaController extends ForgeController {
    
    private final EmpresaServico servico;

    public EmpresaController() {
        this.servico = new EmpresaServico();
    }

    @PUT
    @Path("{id:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, EmpresaCommand command) {
        try {
            EmpresaDto dto = EmpresaDto.builder()
                    .id(id)
                    .razaoSocial(command.getRazaoSocial())
                    .nomeFantasia(command.getNomeFantasia())
                    .endereco(command.getEndereco())
                    .numero(command.getNumero())
                    .complemento(command.getComplemento())
                    .cidade(command.getCidade())
                    .cep(command.getCep())
                    .bairro(command.getBairro())
                    .inscricaoEstadual(command.getInscricaoEstadual())
                    .celular(command.getCelular())
                    .crt(command.getCrt())
                    .baseIcms(command.getBaseIcms())
                    .baseIcmsSt(command.getBaseIcmsSt())
                    .aliquotaCredito(command.getAliquotaCredito())
                    .cnpj(command.getCnpj())
                    .logo(command.getLogo())
                    .email(command.getEmail())
                    .escritorio(command.getEscritorio())
                    .build();
              
            servico.atualizar(dto);
            addMessage("Sucesso ao atualizar a empresa.");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @GET
    @Path("ultimo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findLast() {
        try {
            EmpresaDto dto = servico.ultimo();
            addData(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
}