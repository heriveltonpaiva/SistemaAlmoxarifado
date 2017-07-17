package br.arquitetura.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.GenericDaoImpl;

/**
 * Implementação do AbstractController 
 * @author Herivelton
 *
 * @param <T>
 */
public class AbstractController<T> implements Serializable{
	protected static final long serialVersionUID = 1L;
	T obj;
	List<T> listagem;
	private String paginaForm;
	private String paginaList;
	
	public AbstractController() {
	}
	
	@Transactional
	public String cadastrar(){
		GenericDaoImpl dao = new GenericDaoImpl();
		try{
			dao.create(obj);
			exibirMensagemSucesso();
		}catch(Exception e){
			exibirMensagemErro();
			e.printStackTrace();
		}
		return paginaForm;
	}
	
	@Transactional
	public String alterar(){
		GenericDaoImpl dao = new GenericDaoImpl();
		dao.update(obj);
		return paginaList;
	}
	
	@Transactional
	public String excluir(){
		GenericDaoImpl dao = new GenericDaoImpl();
		dao.remove(obj);
		return paginaList;
	}
	
	public String iniciarCadastro(){
		reset();
		return paginaForm; 
	}
	
	public String iniciarListagem(){
		reset();
		return paginaList;
	}
	
	public void exibirInfo(){
		//System.out.println("Pagina Exibida:"+obj.getClass().getName());
	}
	
	public void setObj(T obj) {
		this.obj = obj;
	}
	public T getObj() {
		return obj;
	}
	public List<T> getListagem() {
		return listagem;
	}
	public void setListagem(List<T> listagem) {
		this.listagem = listagem;
	}
	
	public void reset(){
	}
	
	public void setPaginaForm(String paginaForm) {
		this.paginaForm = paginaForm;
	}
	public String getPaginaForm() {
		return paginaForm;
	}
	public void setPaginaList(String paginaList) {
		this.paginaList = paginaList;
	}
	public String getPaginaList() {
		return paginaList;
	}
	
	
	public void exibirMensagemSucesso(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Operação realizada com Sucesso!"));
	}
	public void exibirMensagemErro(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error! Houve uma falha na execução da operação."));		  	
	}
}
