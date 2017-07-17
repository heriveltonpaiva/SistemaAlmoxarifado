/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquitetura.dominio;

import java.io.Serializable;

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
@Table(schema="almoxarifado",name = "funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.funcionario_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;
    
    @Column(name = "nome")
    private String nome;
    @Column(name = "cargo")
    private String cargo;
    @JoinColumn(name = "id_contato", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Contato contato;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco endereco;

    public Funcionario(String nome, String cargo, String telefone, String celular, String email) {
    	this.nome = nome;
    	this.cargo = cargo;
    	this.contato = new Contato();
    	this.contato.setTelefone(telefone);
    	this.contato.setCelular(celular);
    	this.contato.setEmail(email);
    }
    
    public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public void addEndereco(String rua, String cep, Integer numero, String complemento, String bairro, String cidade, String uf){
    	this.endereco = new Endereco();
    	this.endereco.setDescricao(rua);
    	this.endereco.setNumero(numero);
    	this.endereco.setCep(cep);
    	this.endereco.setComplemento(complemento);
    	this.endereco.setBairro(new Bairro(bairro));
//    	this.endereco.getBairro().setCidade(new Cidade(cidade,new Estado(uf)));
    }
    

    public void setId(int id) {
		this.id = id;
	}
    public int getId() {
		return id;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setContato(Contato contato) {
		this.contato = contato;
	}
    public Contato getContato() {
		return contato;
	}
    public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
    public Endereco getEndereco() {
		return endereco;
	}
    
    @Override
    public String toString() {
        return nome;
    }
    
}
