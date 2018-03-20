/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtocest;

import br.com.techsulsistemas.servico.produto.produto.Produto;
import java.io.Serializable;
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

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "produto_cest")
@NamedQueries({
    @NamedQuery(name = "ProdutoCest.findAll", query = "SELECT p FROM ProdutoCest p")})
public class ProdutoCest implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoCest")
    private List<Produto> produtoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produto_cest")
    private Integer idProdutoCest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cest")
    private String cest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ncm")
    private String ncm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public ProdutoCest() {
    }

    public ProdutoCest(Integer idProdutoCest) {
        this.idProdutoCest = idProdutoCest;
    }

    public ProdutoCest(Integer idProdutoCest, String cest, String ncm, String descricao) {
        this.idProdutoCest = idProdutoCest;
        this.cest = cest;
        this.ncm = ncm;
        this.descricao = descricao;
    }

    public Integer getIdProdutoCest() {
        return idProdutoCest;
    }

    public void setIdProdutoCest(Integer idProdutoCest) {
        this.idProdutoCest = idProdutoCest;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdutoCest != null ? idProdutoCest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoCest)) {
            return false;
        }
        ProdutoCest other = (ProdutoCest) object;
        if ((this.idProdutoCest == null && other.idProdutoCest != null) || (this.idProdutoCest != null && !this.idProdutoCest.equals(other.idProdutoCest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.produto.produtocest.ProdutoCest[ idProdutoCest=" + idProdutoCest + " ]";
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }
    
}
