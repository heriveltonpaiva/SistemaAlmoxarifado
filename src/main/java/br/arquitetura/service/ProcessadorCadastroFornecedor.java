package br.arquitetura.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.EstadoImpl;
import br.arquitetura.dao.FornecedorImpl;
import br.arquitetura.dominio.Bairro;
import br.arquitetura.dominio.Cidade;
import br.arquitetura.dominio.Fornecedor;

@Transactional
@Service
public class ProcessadorCadastroFornecedor extends AbstractProcessador implements Comando{

	@Override
	public Movimento execute() {
		FornecedorImpl dao = new FornecedorImpl();
		EstadoImpl estadoDao = new EstadoImpl();
		Fornecedor fornecedor = (Fornecedor) movimento.getObjMovimentado();
		Movimento mov = movimento;
				
		validate();
		
		if(hasErros())
			return null;
		
		if(mov.getCodMovimento() == ListaComandos.CADASTRAR_FORNECEDOR){
			fornecedor.setDataCadastro(new Date());
			Bairro bairro = fornecedor.getEndereco().getBairro();
			Cidade cidade = bairro.getCidade();
			if(cidade.getEstado() != null)
				bairro.getCidade().setEstado(estadoDao.findBySigla(cidade.getEstado().getDescricao()));
				
			try{
				dao.create(cidade);
				dao.create(bairro);
				dao.create(fornecedor.getEndereco());
				dao.create(fornecedor.getContato());
				dao.create(fornecedor);
			}catch(Exception e){
				System.out.println("Erro ao tentar cadastrar fornecedor"+e.getMessage());
			}
			
		}
		return movimento;
	}

	@Override
	public void validate() {
		Fornecedor fornecedor = (Fornecedor) movimento.getObjMovimentado();

		Bairro bairro = fornecedor.getEndereco().getBairro();
		Cidade cidade = bairro.getCidade();
		
		if(bairro != null && bairro.getDescricao().equals(""))
			addErro("Campo Obrigatório: Bairro");
		if(cidade != null && cidade.getDescricao().equals(""))
			addErro("Campo Obrigatório: Cidade");
		if(fornecedor.getEndereco() != null && fornecedor.getEndereco().getDescricao().equals(""))
			addErro("Campo Obrigatório: Endereço");
		if(fornecedor.getContato() != null && fornecedor.getContato().getEmail().equals(""))
			addErro("Campo Obrigatório: Email");
		
	}
}
