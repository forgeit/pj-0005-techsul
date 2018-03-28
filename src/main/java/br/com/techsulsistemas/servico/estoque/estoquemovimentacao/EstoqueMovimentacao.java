/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import br.com.techsulsistemas.servico.estoque.estoquemovimentacaotipo.EstoqueMovimentacaoTipo;
import br.com.techsulsistemas.servico.produto.produto.Produto;
import br.com.techsulsistemas.servico.usuario.usuario.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "estoque_movimentacao")
@NamedQueries({
    @NamedQuery(name = "EstoqueMovimentacao.findAll", query = "SELECT e FROM EstoqueMovimentacao e")})
public class EstoqueMovimentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estoque_movimentacao")
    private Integer idEstoqueMovimentacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_movimentacao")
    @Temporal(TemporalType.DATE)
    private Date dataMovimentacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Column(name = "preco_custo")
    private BigDecimal precoCusto;
    @Column(name = "preco_venda")
    private BigDecimal precoVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ts_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tsRegistro;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "id_estoque_movimentacao_tipo", referencedColumnName = "id_estoque_movimentacao_tipo")
    @ManyToOne(optional = false)
    private EstoqueMovimentacaoTipo estoqueMovimentacaoTipo;
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public EstoqueMovimentacao() {
    }

    public EstoqueMovimentacao(Integer idEstoqueMovimentacao) {
        this.idEstoqueMovimentacao = idEstoqueMovimentacao;
    }

    public EstoqueMovimentacao(Integer idEstoqueMovimentacao, Date dataMovimentacao, BigDecimal quantidade, Date tsRegistro) {
        this.idEstoqueMovimentacao = idEstoqueMovimentacao;
        this.dataMovimentacao = dataMovimentacao;
        this.quantidade = quantidade;
        this.tsRegistro = tsRegistro;
    }

    public Integer getIdEstoqueMovimentacao() {
        return idEstoqueMovimentacao;
    }

    public void setIdEstoqueMovimentacao(Integer idEstoqueMovimentacao) {
        this.idEstoqueMovimentacao = idEstoqueMovimentacao;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Date getTsRegistro() {
        return tsRegistro;
    }

    public void setTsRegistro(Date tsRegistro) {
        this.tsRegistro = tsRegistro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public EstoqueMovimentacaoTipo getEstoqueMovimentacaoTipo() {
        return estoqueMovimentacaoTipo;
    }

    public void setEstoqueMovimentacaoTipo(EstoqueMovimentacaoTipo estoqueMovimentacaoTipo) {
        this.estoqueMovimentacaoTipo = estoqueMovimentacaoTipo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstoqueMovimentacao != null ? idEstoqueMovimentacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstoqueMovimentacao)) {
            return false;
        }
        EstoqueMovimentacao other = (EstoqueMovimentacao) object;
        if ((this.idEstoqueMovimentacao == null && other.idEstoqueMovimentacao != null) || (this.idEstoqueMovimentacao != null && !this.idEstoqueMovimentacao.equals(other.idEstoqueMovimentacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.estoque.estoque.EstoqueMovimentacao[ idEstoqueMovimentacao=" + idEstoqueMovimentacao + " ]";
    }
    
}
