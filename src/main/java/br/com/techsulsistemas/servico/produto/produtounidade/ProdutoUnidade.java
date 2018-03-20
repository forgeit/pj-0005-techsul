/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtounidade;

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
import javax.validation.constraints.Size;

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "produto_unidade")
@NamedQueries({
    @NamedQuery(name = "ProdutoUnidade.findAll", query = "SELECT p FROM ProdutoUnidade p")})
public class ProdutoUnidade implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoUnidade")
    private List<Produto> produtoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produto_unidade")
    private Integer idProdutoUnidade;
    @Size(max = 2147483647)
    @Column(name = "unidade")
    private String unidade;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public ProdutoUnidade() {
    }

    public ProdutoUnidade(Integer idProdutoUnidade) {
        this.idProdutoUnidade = idProdutoUnidade;
    }

    public Integer getIdProdutoUnidade() {
        return idProdutoUnidade;
    }

    public void setIdProdutoUnidade(Integer idProdutoUnidade) {
        this.idProdutoUnidade = idProdutoUnidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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
        hash += (idProdutoUnidade != null ? idProdutoUnidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoUnidade)) {
            return false;
        }
        ProdutoUnidade other = (ProdutoUnidade) object;
        if ((this.idProdutoUnidade == null && other.idProdutoUnidade != null) || (this.idProdutoUnidade != null && !this.idProdutoUnidade.equals(other.idProdutoUnidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.produto.produtounidade.ProdutoUnidade[ idProdutoUnidade=" + idProdutoUnidade + " ]";
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }
    
}
