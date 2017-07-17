package br.arquitetura.dominio;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 *
 * @author Herivelton
 */
@Entity
@Table(name = "contato", schema="almoxarifado")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.contato_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;

    @Column(name = "telefone")
    private String telefone;
 
    @Column(name = "celular")
    private String celular;

    @Column(name = "email")
    private String email;

    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public String toString() {
        return "";
    }
    
}
