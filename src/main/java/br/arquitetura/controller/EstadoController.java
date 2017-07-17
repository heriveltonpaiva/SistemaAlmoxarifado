package br.arquitetura.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.arquitetura.dao.GenericDaoImpl;
import br.arquitetura.dominio.Estado;
import br.arquitetura.utils.PaginasUtil;

@Component
@Scope("session")
public class EstadoController extends AbstractController<Estado>{

	private static final long serialVersionUID = 1L;
    private List<Estado> listaEstados;

	public EstadoController() {
		reset();
		setPaginaForm(PaginasUtil.CADASTRAR_ESTADOS);
	}
	
	public void reset(){
		obj = new Estado();
	}
	
	@SuppressWarnings("unchecked")
	public void carregarListagensCombo(){
		  GenericDaoImpl dao = new GenericDaoImpl();
		  listaEstados = (List<Estado>) dao.getSession().createQuery(" From Estado ");
	}
	public List<Estado> getListaEstados() {
		return listaEstados;
	}
	
}
