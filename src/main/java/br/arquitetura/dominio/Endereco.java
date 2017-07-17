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

/**
 *
 * @author Herivelton
 */
@Entity
@Table(name = "endereco", schema="almoxarifado")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.endereco_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;

    @Column(name = "descricao")
    private String descricao;
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;
    
    @JoinColumn(name = "id_bairro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bairro bairro;

    
    public Endereco() {
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Bairro getBairro() {
		return bairro;
	}
    public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
   

    @Override
    public String toString() {
        return "br.unirn.model.Endereco[ idEndereco=" + id + " ]";
    }
    
}
