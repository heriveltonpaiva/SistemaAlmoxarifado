package br.arquitetura.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Herivelton
 */
@Entity
@Table(schema="almoxarifado", name = "fornecedor")
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.fornecedor_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;
    
    @Column(name = "codigo")
    private int codigo;
  
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "cnpj")
    private String cnpj;
    
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    @Column(name = "observacoes")
    private String observacoes;

    @JoinColumn(name = "id_contato", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Contato contato;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco endereco;

    public Fornecedor(int codigo, String nome, String cnpj, String fantasia, String obs) {
    	this.codigo = codigo;
    	this.nome = nome;
    	this.cnpj = cnpj;
    	this.nomeFantasia = fantasia;
    	this.observacoes = obs;
    }
    
    public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	public void AddContato(String telefone, String celular, String email){
    	this.contato = new Contato();
    	this.contato.setTelefone(telefone);
    	this.contato.setCelular(celular);
    	this.contato.setEmail(email);
    }
    
    public void addEndereco(String rua, String cep, Integer numero, String complemento, String bairro, String cidade, String uf){
    	this.endereco = new Endereco();
    	this.endereco.setDescricao(rua);
    	this.endereco.setNumero(numero);
    	this.endereco.setCep(cep);
    	this.endereco.setComplemento(complemento);
    	this.endereco.setBairro(new Bairro(bairro));
    	this.endereco.getBairro().setCidade(new Cidade(cidade));
    	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}



	@Override
    public String toString() {
        return nome;
    }
    
}
