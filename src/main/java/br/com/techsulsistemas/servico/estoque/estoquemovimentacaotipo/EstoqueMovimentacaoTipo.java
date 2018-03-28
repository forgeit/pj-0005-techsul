/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacaotipo;

import br.com.techsulsistemas.servico.estoque.estoquemovimentacao.EstoqueMovimentacao;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "estoque_movimentacao_tipo")
@NamedQueries({
    @NamedQuery(name = "EstoqueMovimentacaoTipo.findAll", query = "SELECT e FROM EstoqueMovimentacaoTipo e")})
public class EstoqueMovimentacaoTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estoque_movimentacao_tipo")
    private Integer idEstoqueMovimentacaoTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fg_alterar_estoque")
    private boolean fgAlterarEstoque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fg_soma")
    private boolean fgSoma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fg_alterar_preco_custo")
    private boolean fgAlterarPrecoCusto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fg_alterar_preco_venda")
    private boolean fgAlterarPrecoVenda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estoqueMovimentacaoTipo")
    private Collection<EstoqueMovimentacao> estoqueMovimentacaoCollection;

    public EstoqueMovimentacaoTipo() {
    }

    public EstoqueMovimentacaoTipo(Integer idEstoqueMovimentacaoTipo) {
        this.idEstoqueMovimentacaoTipo = idEstoqueMovimentacaoTipo;
    }

    public EstoqueMovimentacaoTipo(Integer idEstoqueMovimentacaoTipo, String descricao, boolean fgAlterarEstoque, boolean fgSoma, boolean fgAlterarPrecoCusto, boolean fgAlterarPrecoVenda) {
        this.idEstoqueMovimentacaoTipo = idEstoqueMovimentacaoTipo;
        this.descricao = descricao;
        this.fgAlterarEstoque = fgAlterarEstoque;
        this.fgSoma = fgSoma;
        this.fgAlterarPrecoCusto = fgAlterarPrecoCusto;
        this.fgAlterarPrecoVenda = fgAlterarPrecoVenda;
    }

    public Integer getIdEstoqueMovimentacaoTipo() {
        return idEstoqueMovimentacaoTipo;
    }

    public void setIdEstoqueMovimentacaoTipo(Integer idEstoqueMovimentacaoTipo) {
        this.idEstoqueMovimentacaoTipo = idEstoqueMovimentacaoTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getFgAlterarEstoque() {
        return fgAlterarEstoque;
    }

    public void setFgAlterarEstoque(boolean fgAlterarEstoque) {
        this.fgAlterarEstoque = fgAlterarEstoque;
    }

    public boolean getFgSoma() {
        return fgSoma;
    }

    public void setFgSoma(boolean fgSoma) {
        this.fgSoma = fgSoma;
    }

    public boolean getFgAlterarPrecoCusto() {
        return fgAlterarPrecoCusto;
    }

    public void setFgAlterarPrecoCusto(boolean fgAlterarPrecoCusto) {
        this.fgAlterarPrecoCusto = fgAlterarPrecoCusto;
    }

    public boolean getFgAlterarPrecoVenda() {
        return fgAlterarPrecoVenda;
    }

    public void setFgAlterarPrecoVenda(boolean fgAlterarPrecoVenda) {
        this.fgAlterarPrecoVenda = fgAlterarPrecoVenda;
    }

    public Collection<EstoqueMovimentacao> getEstoqueMovimentacaoCollection() {
        return estoqueMovimentacaoCollection;
    }

    public void setEstoqueMovimentacaoCollection(Collection<EstoqueMovimentacao> estoqueMovimentacaoCollection) {
        this.estoqueMovimentacaoCollection = estoqueMovimentacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstoqueMovimentacaoTipo != null ? idEstoqueMovimentacaoTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstoqueMovimentacaoTipo)) {
            return false;
        }
        EstoqueMovimentacaoTipo other = (EstoqueMovimentacaoTipo) object;
        if ((this.idEstoqueMovimentacaoTipo == null && other.idEstoqueMovimentacaoTipo != null) || (this.idEstoqueMovimentacaoTipo != null && !this.idEstoqueMovimentacaoTipo.equals(other.idEstoqueMovimentacaoTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.estoque.estoque.EstoqueMovimentacaoTipo[ idEstoqueMovimentacaoTipo=" + idEstoqueMovimentacaoTipo + " ]";
    }
    
}
