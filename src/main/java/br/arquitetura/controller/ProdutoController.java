package br.arquitetura.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.FornecedorImpl;
import br.arquitetura.dao.ProdutoImpl;
import br.arquitetura.dominio.Fornecedor;
import br.arquitetura.dominio.Produto;
import br.arquitetura.service.ListaComandos;
import br.arquitetura.service.Movimento;
import br.arquitetura.service.ProcessadorCadastroProduto;
import br.arquitetura.utils.PaginasUtil;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class ProdutoController extends AbstractController<Produto>{

	private List<Fornecedor> listaFornecedores;
	
	public ProdutoController() {
		reset();
	}
	
	public void reset(){
		obj = new Produto();
		obj.setFornecedor(new Fornecedor());
		setPaginaForm(PaginasUtil.CADASTRAR_PRODUTO);
		setPaginaForm(PaginasUtil.LISTAR_PRODUTO);
	}

	@Override
	@Transactional
	public String cadastrar() {
		
		Movimento mov = new Movimento();
		ProcessadorCadastroProduto processador = new ProcessadorCadastroProduto();
		
		mov.setCodMovimento(ListaComandos.CADASTRAR_PRODUTO);
		mov.setObjMovimentado(obj);
		processador.setMovimento(mov);
		mov.setComando(processador);
		mov.execute();
		
		exibirMensagemSucesso();
		reset();
		getListagem();
		return getPaginaForm();
	}
	
	@Transactional
	public String remover(Produto produto) {
		ProdutoImpl dao = new ProdutoImpl();
	        try {
	        	dao.remove(produto);
	        	getListagem();
	        	exibirMensagemSucesso();
	        } catch (Exception ex) {
	        	exibirMensagemErro();	        
	        }
	        return getPaginaList();
	 }
	
	@Transactional
	public String alterar(){
		ProdutoImpl dao = new ProdutoImpl();
		dao.update(obj);
		getListagem();
		exibirMensagemSucesso();
		reset();
		return getPaginaList();
	}
	
	 public void limparObjetoDialog() {
	        this.obj = null;
	        this.obj = new Produto();
	 }
	 
    public String carregarObjetoEditar() {
	    return getPaginaForm();
	}
	
	@Override
	public List<Produto> getListagem() {
		ProdutoImpl dao = new ProdutoImpl();
		listagem = dao.findAll();
		return listagem;
	}
	
	public List<Fornecedor> getListaFornecedores() {
		FornecedorImpl fornImpl = new FornecedorImpl();
		listaFornecedores = fornImpl.findAll();
		return listaFornecedores;
	}
	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}
	
}
