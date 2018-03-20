/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtocsosn;

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
@Table(name = "produto_csosn")
@NamedQueries({
    @NamedQuery(name = "ProdutoCsosn.findAll", query = "SELECT p FROM ProdutoCsosn p")})
public class ProdutoCsosn implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoCsosn")
    private List<Produto> produtoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produto_csosn")
    private Integer idProdutoCsosn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public ProdutoCsosn() {
    }

    public ProdutoCsosn(Integer idProdutoCsosn) {
        this.idProdutoCsosn = idProdutoCsosn;
    }

    public ProdutoCsosn(Integer idProdutoCsosn, int codigo) {
        this.idProdutoCsosn = idProdutoCsosn;
        this.codigo = codigo;
    }

    public Integer getIdProdutoCsosn() {
        return idProdutoCsosn;
    }

    public void setIdProdutoCsosn(Integer idProdutoCsosn) {
        this.idProdutoCsosn = idProdutoCsosn;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
        hash += (idProdutoCsosn != null ? idProdutoCsosn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoCsosn)) {
            return false;
        }
        ProdutoCsosn other = (ProdutoCsosn) object;
        if ((this.idProdutoCsosn == null && other.idProdutoCsosn != null) || (this.idProdutoCsosn != null && !this.idProdutoCsosn.equals(other.idProdutoCsosn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.produto.produtocsosn.ProdutoCsosn[ idProdutoCsosn=" + idProdutoCsosn + " ]";
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }
    
}
