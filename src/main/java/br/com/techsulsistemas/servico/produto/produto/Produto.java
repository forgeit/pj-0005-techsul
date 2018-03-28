/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produto;

import br.com.techsulsistemas.servico.estoque.estoque.Estoque;
import br.com.techsulsistemas.servico.estoque.estoquemovimentacao.EstoqueMovimentacao;
import br.com.techsulsistemas.servico.produto.produtocest.ProdutoCest;
import br.com.techsulsistemas.servico.produto.produtocsosn.ProdutoCsosn;
import br.com.techsulsistemas.servico.produto.produtogrupo.ProdutoGrupo;
import br.com.techsulsistemas.servico.produto.produtoorigem.ProdutoOrigem;
import br.com.techsulsistemas.servico.produto.produtounidade.ProdutoUnidade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")})
public class Produto implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<Estoque> estoqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<EstoqueMovimentacao> estoqueMovimentacaoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produto")
    private Integer idProduto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    
    @Size(min = 1, max = 2147483647)
    @Column(name = "ean")
    private String ean;
    @Size(min = 1, max = 2147483647)
    @Column(name = "ean_tributario")
    private String eanTributario;
    
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fg_situacao")
    private boolean fgSituacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_custo")
    private BigDecimal precoCusto;
    @Column(name = "lucro")
    private BigDecimal lucro;
    @Column(name = "preco_venda")
    private BigDecimal precoVenda;
    @Column(name = "preco_especial")
    private BigDecimal precoEspecial;
    @Column(name = "quantidade_preco_especial")
    private BigDecimal quantidadePrecoEspecial;
    @JoinColumn(name = "id_produto_cest", referencedColumnName = "id_produto_cest")
    @ManyToOne(optional = false)
    private ProdutoCest produtoCest;
    @JoinColumn(name = "id_produto_csosn", referencedColumnName = "id_produto_csosn")
    @ManyToOne(optional = false)
    private ProdutoCsosn produtoCsosn;
    @JoinColumn(name = "id_produto_grupo", referencedColumnName = "id_produto_grupo")
    @ManyToOne(optional = false)
    private ProdutoGrupo produtoGrupo;
    @JoinColumn(name = "id_produto_origem", referencedColumnName = "id_produto_origem")
    @ManyToOne(optional = false)
    private ProdutoOrigem produtoOrigem;
    @JoinColumn(name = "id_produto_unidade", referencedColumnName = "id_produto_unidade")
    @ManyToOne(optional = false)
    private ProdutoUnidade produtoUnidade;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(Integer idProduto, String codigo, String descricao, boolean fgSituacao) {
        this.idProduto = idProduto;
        this.codigo = codigo;
        this.descricao = descricao;
        this.fgSituacao = fgSituacao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getFgSituacao() {
        return fgSituacao;
    }

    public void setFgSituacao(boolean fgSituacao) {
        this.fgSituacao = fgSituacao;
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

    public BigDecimal getPrecoEspecial() {
        return precoEspecial;
    }

    public void setPrecoEspecial(BigDecimal precoEspecial) {
        this.precoEspecial = precoEspecial;
    }

    public BigDecimal getQuantidadePrecoEspecial() {
        return quantidadePrecoEspecial;
    }

    public void setQuantidadePrecoEspecial(BigDecimal quantidadePrecoEspecial) {
        this.quantidadePrecoEspecial = quantidadePrecoEspecial;
    }

    public ProdutoCest getProdutoCest() {
        return produtoCest;
    }

    public void setProdutoCest(ProdutoCest produtoCest) {
        this.produtoCest = produtoCest;
    }

    public ProdutoCsosn getProdutoCsosn() {
        return produtoCsosn;
    }

    public void setProdutoCsosn(ProdutoCsosn produtoCsosn) {
        this.produtoCsosn = produtoCsosn;
    }

    public ProdutoGrupo getProdutoGrupo() {
        return produtoGrupo;
    }

    public void setProdutoGrupo(ProdutoGrupo produtoGrupo) {
        this.produtoGrupo = produtoGrupo;
    }

    public ProdutoOrigem getProdutoOrigem() {
        return produtoOrigem;
    }

    public void setProdutoOrigem(ProdutoOrigem produtoOrigem) {
        this.produtoOrigem = produtoOrigem;
    }

    public ProdutoUnidade getProdutoUnidade() {
        return produtoUnidade;
    }

    public void setProdutoUnidade(ProdutoUnidade produtoUnidade) {
        this.produtoUnidade = produtoUnidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.produto.produto.Produto[ idProduto=" + idProduto + " ]";
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getEanTributario() {
        return eanTributario;
    }

    public void setEanTributario(String eanTributario) {
        this.eanTributario = eanTributario;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public Collection<Estoque> getEstoqueCollection() {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection) {
        this.estoqueCollection = estoqueCollection;
    }

    public Collection<EstoqueMovimentacao> getEstoqueMovimentacaoCollection() {
        return estoqueMovimentacaoCollection;
    }

    public void setEstoqueMovimentacaoCollection(Collection<EstoqueMovimentacao> estoqueMovimentacaoCollection) {
        this.estoqueMovimentacaoCollection = estoqueMovimentacaoCollection;
    }
    
    
    
}
