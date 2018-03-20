/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtogrupo;

import br.com.techsulsistemas.servico.produto.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "produto_grupo")
@NamedQueries({
    @NamedQuery(name = "ProdutoGrupo.findAll", query = "SELECT p FROM ProdutoGrupo p")})
@XmlRootElement
public class ProdutoGrupo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoGrupo")
    private List<Produto> produtoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produto_grupo")
    private Integer idProdutoGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_completa")
    private String descricaoCompleta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "percentual_lucro")
    private BigDecimal percentualLucro;

    public ProdutoGrupo() {
    }

    public ProdutoGrupo(Integer idProdutoGrupo) {
        this.idProdutoGrupo = idProdutoGrupo;
    }

    public ProdutoGrupo(Integer idProdutoGrupo, String descricao, String descricaoCompleta) {
        this.idProdutoGrupo = idProdutoGrupo;
        this.descricao = descricao;
        this.descricaoCompleta = descricaoCompleta;
    }

    public Integer getIdProdutoGrupo() {
        return idProdutoGrupo;
    }

    public void setIdProdutoGrupo(Integer idProdutoGrupo) {
        this.idProdutoGrupo = idProdutoGrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public BigDecimal getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(BigDecimal percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdutoGrupo != null ? idProdutoGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoGrupo)) {
            return false;
        }
        ProdutoGrupo other = (ProdutoGrupo) object;
        if ((this.idProdutoGrupo == null && other.idProdutoGrupo != null) || (this.idProdutoGrupo != null && !this.idProdutoGrupo.equals(other.idProdutoGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.ProdutoGrupo[ idProdutoGrupo=" + idProdutoGrupo + " ]";
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }
    
}
