package br.arquitetura.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String nome;
	
	@Column(name="nome_mae")
    private String nomeMae;
	
	@Column(name="nome_pai")
	private String nomePai;
	
	private String nacionalidade;
	
	private String cpf;
	
	private String rg;
	
	@Column(name="data_nascimento")
	private Date dataNascimento;
	
	@Column(name="estado_civil")
	private String estadoCivil;
	
	private String sexo;
	
	private String naturalidade;
	
	private boolean ativo;
	
	private String observacao;
	
	@Column(name="tipo_pessoa")
	private int tipoPessoa;
	
	@ManyToOne
	@JoinColumn(name="id_endereco", referencedColumnName="id")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="id_contato", referencedColumnName="id")
	private Contato contato;
	
	@ManyToOne
	@JoinColumn(name="id_users", referencedColumnName="id")
	private Users usuario;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Users getUsuario() {
		return usuario;
	}

	public void setUsuario(Users usuario) {
		this.usuario = usuario;
	}

	public String getContatoFormatado(){

		String contatos = "";
		
		if(contato != null){
		if(contato.getTelefone() != null)
		    contatos +=""+getContato().getTelefone();
		if(contato.getCelular() != null)
		    contatos +="/"+getContato().getCelular();
		}
		
		return contatos;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		if(this.nome == null)
			return " ";
		return this.nome;
	}
	
}
