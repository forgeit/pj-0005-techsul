/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import br.com.techsulsistemas.servico.empresa.empresaregimetributario.EmpresaCodigoRegimeTributario;
import br.com.techsulsistemas.servico.empresa.empresaescritoriocontabil.EmpresaEscritorioContabil;
import br.com.techsulsistemas.servico.endereco.enderecocidade.EnderecoCidade;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "razao_social")
    private String razaoSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Size(max = 2147483647)
    @Column(name = "endereco")
    private String endereco;
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "numero")
    private String numero;
    @Size(max = 2147483647)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 2147483647)
    @Column(name = "cep")
    private String cep;
    @Size(max = 2147483647)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
    @Size(max = 2147483647)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_icms")
    private int baseIcms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_icms_st")
    private int baseIcmsSt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "aliquota_credito")
    private BigDecimal aliquotaCredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cnpj")
    private String cnpj;
    @Size(max = 2147483647)
    @Column(name = "logo")
    private String logo;
    @JoinColumn(name = "crt", referencedColumnName = "id_empresa_codigo_regime_tributario")
    @ManyToOne(optional = false)
    private EmpresaCodigoRegimeTributario empresaCodigoRegimeTributario;
    @JoinColumn(name = "id_empresa_escritorio_contabil", referencedColumnName = "id_empresa_escritorio_contabil")
    @ManyToOne
    private EmpresaEscritorioContabil empresaEscritorioContabil;
    @JoinColumn(name = "id_cidade", referencedColumnName = "id_endereco_cidade")
    @ManyToOne
    private EnderecoCidade enderecoCidade;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa(Integer idEmpresa, String razaoSocial, String nomeFantasia, String inscricaoEstadual, int baseIcms, int baseIcmsSt, BigDecimal aliquotaCredito, String cnpj) {
        this.idEmpresa = idEmpresa;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.baseIcms = baseIcms;
        this.baseIcmsSt = baseIcmsSt;
        this.aliquotaCredito = aliquotaCredito;
        this.cnpj = cnpj;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getBaseIcms() {
        return baseIcms;
    }

    public void setBaseIcms(int baseIcms) {
        this.baseIcms = baseIcms;
    }

    public int getBaseIcmsSt() {
        return baseIcmsSt;
    }

    public void setBaseIcmsSt(int baseIcmsSt) {
        this.baseIcmsSt = baseIcmsSt;
    }

    public BigDecimal getAliquotaCredito() {
        return aliquotaCredito;
    }

    public void setAliquotaCredito(BigDecimal aliquotaCredito) {
        this.aliquotaCredito = aliquotaCredito;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public EmpresaCodigoRegimeTributario getEmpresaCodigoRegimeTributario() {
        return empresaCodigoRegimeTributario;
    }

    public void setEmpresaCodigoRegimeTributario(EmpresaCodigoRegimeTributario empresaCodigoRegimeTributario) {
        this.empresaCodigoRegimeTributario = empresaCodigoRegimeTributario;
    }

    public EmpresaEscritorioContabil getEmpresaEscritorioContabil() {
        return empresaEscritorioContabil;
    }

    public void setEmpresaEscritorioContabil(EmpresaEscritorioContabil empresaEscritorioContabil) {
        this.empresaEscritorioContabil = empresaEscritorioContabil;
    }

    public EnderecoCidade getEnderecoCidade() {
        return enderecoCidade;
    }

    public void setEnderecoCidade(EnderecoCidade enderecoCidade) {
        this.enderecoCidade = enderecoCidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.empresa.empresaregimetributario.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
