package br.arquitetura.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.arquitetura.utils.SampleEntity;

/**
 *
 * @author Herivelton
 */
@Entity
@Table(name = "estado", schema="almoxarifado")
public class Estado implements Serializable, SampleEntity{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.estado_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;
    
    @Column(name = "descricao")
    private String descricao;
    
    public Estado(String descricao) {
    	this.descricao = descricao;
    }
    public Estado(int id, String descricao){
    this.id = id;
    this.descricao = descricao;
    }
    public Estado() {
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

    @Override
    public String toString() {
        return descricao;
    }
    
}
