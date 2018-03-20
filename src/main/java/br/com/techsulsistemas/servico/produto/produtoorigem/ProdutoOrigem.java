/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtoorigem;

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
@Table(name = "produto_origem")
@NamedQueries({
    @NamedQuery(name = "ProdutoOrigem.findAll", query = "SELECT p FROM ProdutoOrigem p")})
public class ProdutoOrigem implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoOrigem")
    private List<Produto> produtoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produto_origem")
    private Integer idProdutoOrigem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public ProdutoOrigem() {
    }

    public ProdutoOrigem(Integer idProdutoOrigem) {
        this.idProdutoOrigem = idProdutoOrigem;
    }

    public ProdutoOrigem(Integer idProdutoOrigem, int codigo, String descricao) {
        this.idProdutoOrigem = idProdutoOrigem;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getIdProdutoOrigem() {
        return idProdutoOrigem;
    }

    public void setIdProdutoOrigem(Integer idProdutoOrigem) {
        this.idProdutoOrigem = idProdutoOrigem;
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
        hash += (idProdutoOrigem != null ? idProdutoOrigem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoOrigem)) {
            return false;
        }
        ProdutoOrigem other = (ProdutoOrigem) object;
        if ((this.idProdutoOrigem == null && other.idProdutoOrigem != null) || (this.idProdutoOrigem != null && !this.idProdutoOrigem.equals(other.idProdutoOrigem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.produto.produtoorigem.ProdutoOrigem[ idProdutoOrigem=" + idProdutoOrigem + " ]";
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }
    
}
