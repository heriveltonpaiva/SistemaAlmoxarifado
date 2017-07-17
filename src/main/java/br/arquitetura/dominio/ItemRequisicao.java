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
@Table(schema="almoxarifado", name = "item_requisicao")
public class ItemRequisicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.item_requisicao_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
 
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "id_requisicao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Requisicao requisicao;

    public ItemRequisicao() {
    }

    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
		return produto;
	}
    public void setProduto(Produto produto) {
		this.produto = produto;
	}
    public Requisicao getRequisicao() {
		return requisicao;
	}
    public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

    @Override
    public String toString() {
        return produto.toString();
    }
    
}
