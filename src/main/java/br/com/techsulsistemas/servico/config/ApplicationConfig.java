/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author kelvin
 */
@javax.ws.rs.ApplicationPath("rs")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.techsulsistemas.servico.empresa.empresa.EmpresaController.class);
        resources.add(br.com.techsulsistemas.servico.empresa.empresaescritoriocontabil.EmpresaEscritorioContabilController.class);
        resources.add(br.com.techsulsistemas.servico.empresa.empresaregimetributario.EmpresaCodigoRegimeTributarioController.class);
        resources.add(br.com.techsulsistemas.servico.endereco.enderecocidade.EnderecoCidadeController.class);
        resources.add(br.com.techsulsistemas.servico.produto.produtocest.ProdutoCestController.class);
        resources.add(br.com.techsulsistemas.servico.produto.produtocsosn.ProdutoCsosnController.class);
        resources.add(br.com.techsulsistemas.servico.produto.produtogrupo.ProdutoGrupoController.class);
        resources.add(br.com.techsulsistemas.servico.produto.produtoorigem.ProdutoOrigemController.class);
        resources.add(br.com.techsulsistemas.servico.produto.produtounidade.ProdutoUnidadeController.class);
    }
    
}
