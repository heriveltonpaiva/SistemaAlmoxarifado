package br.arquitetura.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bairro", schema="almoxarifado")
public class Bairro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.bairro_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;

    @Column(name = "descricao")
    private String descricao;
  
    @JoinColumn(name = "id_cidade", referencedColumnName = "id")
    @ManyToOne
    private Cidade cidade;

    public Bairro(String descricao) {
    	this.descricao = descricao;
    }
    
    public Bairro() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cidade getCidade() {
		return cidade;
	}
    public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

    
    @Override
    public String toString() {
        return descricao;
    }
    
}
