package br.arquitetura.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.EstadoImpl;
import br.arquitetura.dao.FuncionarioImpl;
import br.arquitetura.dominio.Bairro;
import br.arquitetura.dominio.Cidade;
import br.arquitetura.dominio.Contato;
import br.arquitetura.dominio.Endereco;
import br.arquitetura.dominio.Estado;
import br.arquitetura.dominio.Funcionario;
import br.arquitetura.service.ListaComandos;
import br.arquitetura.service.Movimento;
import br.arquitetura.service.ProcessadorCadastroFuncionario;
import br.arquitetura.utils.PaginasUtil;

@Component
@Scope("session")
public class FuncionarioController extends AbstractController<Funcionario> implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private List<Estado> listaEstados;

	public FuncionarioController() {
		reset();
	}
	
	public void reset(){
		obj = new Funcionario();
		obj.setContato(new Contato());
		obj.setEndereco(new Endereco());
		obj.getEndereco().setBairro(new Bairro());
		obj.getEndereco().getBairro().setCidade(new Cidade());
		obj.getEndereco().getBairro().getCidade();
		
		setPaginaForm(PaginasUtil.CADASTRAR_FUNCIONARIO);
		setPaginaForm(PaginasUtil.LISTAR_FUNCIONARIO);
		carregarListagensCombo();
		iniciarListagem();
	}

	@Override
	@Transactional
	public String cadastrar() {
		
		Movimento mov = new Movimento();
		ProcessadorCadastroFuncionario processador = new ProcessadorCadastroFuncionario();
		
		mov.setCodMovimento(ListaComandos.CADASTRAR_FUNCIONARIO);
		mov.setObjMovimentado(obj);
		processador.setMovimento(mov);
		mov.setComando(processador);
		mov.execute();
		
		exibirMensagemSucesso();
		reset();
		FuncionarioImpl funDao = new FuncionarioImpl();
		listagem = funDao.findAll();
		return getPaginaForm();
	}
	
	@Override
	public String iniciarListagem() {
		listagem = new ArrayList<Funcionario>();
		FuncionarioImpl funDao = new FuncionarioImpl();
		listagem = funDao.findAll();
		return getPaginaList();
	}
	
	@Transactional
	public String remover(Funcionario fornecedor) {
		FuncionarioImpl funDao = new FuncionarioImpl();
	        try {
	        	funDao.remove(fornecedor);
	        	listagem = funDao.findAll();
	        	exibirMensagemSucesso();
	        } catch (Exception ex) {
	        	exibirMensagemErro();	        
	        }
	        return getPaginaList();
	 }
	
	@Transactional
	public String alterar(){
		FuncionarioImpl funDao = new FuncionarioImpl();
		funDao.update(obj);
		listagem = funDao.findAll();
		exibirMensagemSucesso();
		reset();
		return getPaginaList();
	}
	
	 public void limparObjetoDialog() {
	        this.obj = null;
	        this.obj = new Funcionario();
	 }
	 
    public String carregarObjetoEditar() {
	    return getPaginaForm();
	}
	
	public void carregarListagensCombo(){
		  EstadoImpl dao = new EstadoImpl();
		  listaEstados =  dao.findAll();
	}
	public List<Estado> getListaEstados() {
		return listaEstados;
	}
	
	@Override
	public List<Funcionario> getListagem() {
		FuncionarioImpl funDao = new FuncionarioImpl();
		listagem = funDao.findAll();
		return listagem;
	}
	
}
