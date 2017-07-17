/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquitetura.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Herivelton
 */
@Entity
@Table(schema="almoxarifado",name = "produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.produto_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;
    
    @Column(name = "codigo")
    private int codigo;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "unidade")
    private String unidade;
    
    @Column(name = "estoque_minimo")
    private Integer estoqueMinimo;
    
    @Column(name = "estoque_maximo")
    private Integer estoqueMaximo;

    @Column(name = "valor")
    private BigDecimal valor;
    
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    
    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "observacoes")
    private String observacoes;
    
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;

    public Produto() {
    }

    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Integer getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(Integer estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Fornecedor getFornecedor() {
		return fornecedor;
	}
    public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


    @Override
    public String toString() {
        return descricao;
    }

    
}
