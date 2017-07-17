package br.arquitetura.dominio;


import java.io.Serializable;
import java.util.ArrayList;
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
@Table(schema="almoxarifado", name = "cidade")
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.cidade_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;

    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne
	@JoinColumn(name="id_estado", referencedColumnName="id")
    private Estado estado;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade", orphanRemoval = true)
    //private List<Bairro> bairros = new ArrayList<>();
    
    public Cidade(String descricao) {
    	this.descricao = descricao;
    }

    public Cidade() {
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
    
    public Estado getEstado() {
		return estado;
	}
    public void setEstado(Estado estado) {
		this.estado = estado;
	}

//    public List<Bairro> getBairros() {
//		return bairros;
//	}
//    public void setBairros(List<Bairro> bairros) {
//		this.bairros = bairros;
//	}
    
    @Override
    public String toString() {
        return descricao;
    }
    
}
