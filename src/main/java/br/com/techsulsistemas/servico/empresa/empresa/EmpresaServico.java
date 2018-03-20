/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.empresa.empresaescritoriocontabil.EmpresaEscritorioContabil;
import br.com.techsulsistemas.servico.empresa.empresaregimetributario.EmpresaCodigoRegimeTributario;
import br.com.techsulsistemas.servico.endereco.enderecocidade.EnderecoCidade;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kelvin
 */
public class EmpresaServico {
    
    private final EmpresaDao dao;
    
    public EmpresaServico() {
        dao = new EmpresaDao();
    }
    
    public void atualizar(EmpresaDto dto) throws Exception {
        validarEntrada(dto);
        
        try {
            Empresa model = dao.encontrar(dto.getId());
            
            model.setRazaoSocial(dto.getRazaoSocial());
            model.setNomeFantasia(dto.getNomeFantasia());
            model.setEndereco(dto.getEndereco());
            model.setNumero(dto.getNumero());
            model.setComplemento(dto.getComplemento());
            model.setEnderecoCidade(dto.getCidade() == null ? null : new EnderecoCidade(dto.getCidade()));
            model.setCep(dto.getCep());
            model.setBairro(dto.getBairro());
            model.setInscricaoEstadual(dto.getInscricaoEstadual());
            model.setCelular(dto.getCelular());
            model.setEmpresaCodigoRegimeTributario(new EmpresaCodigoRegimeTributario(dto.getCrt()));
            model.setBaseIcms(dto.getBaseIcms());
            model.setBaseIcmsSt(dto.getBaseIcmsSt());
            model.setAliquotaCredito(dto.getAliquotaCredito());
            model.setCnpj(dto.getCnpj());
            model.setEmail(dto.getEmail());
            model.setEmpresaEscritorioContabil(dto.getEscritorio()== null ? null : new EmpresaEscritorioContabil(dto.getEscritorio()));
            
            DAO.begin();
            DAO.getEM().merge(model);
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public EmpresaDto ultimo() throws Exception {
        return criarConversorDto(dao.encontrarUltimo("idEmpresa"));
    }
    
    private void validarEntrada(EmpresaDto dto) throws Exception {
        if (StringUtils.isBlank(dto.getRazaoSocial())) {
            throw new Exception("O atributo razão social é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getNomeFantasia())) {
            throw new Exception("O atributo nome fantasia é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getInscricaoEstadual())) {
            throw new Exception("O atributo inscrição estadual é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getCnpj())) {
            throw new Exception("O atributo CNPJ é obrigatório.");
        }
        
        if (dto.getCrt() == null) {
            throw new Exception("O atributo CRT é obrigatório.");
        }        
        
        if (dto.getBaseIcms() == null) {
            throw new Exception("O atributo Base ICMS é obrigatório.");
        }
        
        if (dto.getBaseIcmsSt() == null) {
            throw new Exception("O atributo Base ICMS ST é obrigatório.");
        }
        
        if (dto.getAliquotaCredito() == null) {
            throw new Exception("O atributo Aliquota de Crédito é obrigatório.");
        }
        
        if (dao.isNotUnique("empresa", "cnpj", dto.getCnpj(), dto.getId())) {
            throw new Exception("O CNPJ informado já consta na base de dados.");
        }
    }
    
    private EmpresaDto criarConversorDto(Empresa model) throws Exception {
        return EmpresaDto.builder()
                .id(model.getIdEmpresa())
                .razaoSocial(model.getRazaoSocial())
                .nomeFantasia(model.getNomeFantasia())
                .endereco(model.getEndereco())
                .numero(model.getNumero())
                .complemento(model.getComplemento())
                .cidade(model.getEnderecoCidade() == null ? null : model.getEnderecoCidade().getIdEnderecoCidade())
                .cep(model.getCep())
                .cnpj(model.getCnpj())
                .inscricaoEstadual(model.getInscricaoEstadual())
                .celular(model.getCelular())
                .email(model.getEmail())
                .baseIcms(model.getBaseIcms())
                .baseIcmsSt(model.getBaseIcmsSt())
                .aliquotaCredito(model.getAliquotaCredito())
                .crt(model.getEmpresaCodigoRegimeTributario().getIdEmpresaCodigoRegimeTributario())
                .escritorio(model.getEmpresaEscritorioContabil() == null ? null : model.getEmpresaEscritorioContabil().getIdEmpresaEscritorioContabil())
                .build();
    }
    
}
