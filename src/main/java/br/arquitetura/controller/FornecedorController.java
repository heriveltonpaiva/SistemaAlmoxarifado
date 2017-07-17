package br.arquitetura.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.FornecedorImpl;
import br.arquitetura.dominio.Bairro;
import br.arquitetura.dominio.Cidade;
import br.arquitetura.dominio.Contato;
import br.arquitetura.dominio.Endereco;
import br.arquitetura.dominio.Estado;
import br.arquitetura.dominio.Fornecedor;
import br.arquitetura.service.ListaComandos;
import br.arquitetura.service.Movimento;
import br.arquitetura.service.ProcessadorCadastroFornecedor;
import br.arquitetura.utils.PaginasUtil;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class FornecedorController extends AbstractController<Fornecedor>{

	public FornecedorController() {
		reset();
	}
	
	public void reset(){
		obj = new Fornecedor();
		obj.setContato(new Contato());
		obj.setEndereco(new Endereco());
		obj.getEndereco().setBairro(new Bairro());
		obj.getEndereco().getBairro().setCidade(new Cidade());
		obj.getEndereco().getBairro().getCidade().setEstado(new Estado());
		setPaginaForm(PaginasUtil.CADASTRAR_FORNECEDOR);
		setPaginaForm(PaginasUtil.LISTAR_FORNECEDOR);
	}

	@Override
	@Transactional
	public String cadastrar() {
		
		Movimento mov = new Movimento();
		ProcessadorCadastroFornecedor processador = new ProcessadorCadastroFornecedor();
		
		mov.setCodMovimento(ListaComandos.CADASTRAR_FORNECEDOR);
		mov.setObjMovimentado(obj);
		processador.setMovimento(mov);
		mov.setComando(processador);
		mov.execute();
		
		exibirMensagemSucesso();
		reset();
		return getPaginaForm();
	}
	
	@Transactional
	public String remover(Fornecedor fornecedor) {
		FornecedorImpl funDao = new FornecedorImpl();
	        try {
	        	funDao.remove(fornecedor);
	        	getListagem();
	        	exibirMensagemSucesso();
	        } catch (Exception ex) {
	        	exibirMensagemErro();	        
	        }
	        return getPaginaList();
	 }
	
	@Transactional
	public String alterar(){
		FornecedorImpl funDao = new FornecedorImpl();
		funDao.update(obj);
		getListagem();
		exibirMensagemSucesso();
		reset();
		return getPaginaList();
	}
	
	 public void limparObjetoDialog() {
	        this.obj = null;
	        this.obj = new Fornecedor();
	 }
	 
    public String carregarObjetoEditar() {
	    return getPaginaForm();
	}
	
	@Override
	public List<Fornecedor> getListagem() {
		FornecedorImpl funDao = new FornecedorImpl();
		listagem = funDao.findAll();
		return listagem;
	}
}
