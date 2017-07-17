
package br.arquitetura.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

/**
 *
 * @author Herivelton
 */
@Entity
@Table(schema="almoxarifado",name = "requisicao")
public class Requisicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="sequence", sequenceName="almoxarifado.requisicao_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="sequence")
    private int id;
    
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Funcionario funcionario;

    @Transient
    private List<ItemRequisicao> itens;
    
    public Requisicao() {
    }
    
    public Requisicao(int codigo, Funcionario funcionario){
    	this.codigo = codigo;
    	this.funcionario = funcionario;
    	this.data = new Date();
    }

    public void setId(int id) {
		this.id = id;
	}
    public int getId() {
		return id;
	}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<ItemRequisicao> getItens() {
		return itens;
	}
	public void setItens(List<ItemRequisicao> itens) {
		this.itens = itens;
	}

    @Override
    public String toString() {
        return codigo+"";
    }
    
}
