package br.arquitetura.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.GenericDaoImpl;
import br.arquitetura.dominio.Bairro;
import br.arquitetura.dominio.Cidade;
import br.arquitetura.dominio.Estado;
import br.arquitetura.dominio.Funcionario;

@Transactional
@Service
public class ProcessadorCadastroFuncionario extends AbstractProcessador implements Comando{
	
	@Override
	public Movimento execute() {
		GenericDaoImpl dao = new GenericDaoImpl();
		
		Funcionario funcionario = (Funcionario) movimento.getObjMovimentado();
		Movimento mov = movimento;
				
		validate();
		
		if(hasErros())
			return null;
		
		if(mov.getCodMovimento() == ListaComandos.CADASTRAR_FUNCIONARIO){
			
			Bairro bairro = funcionario.getEndereco().getBairro();
			Cidade cidade = bairro.getCidade();
			if(cidade.getEstado() == null)
				cidade.setEstado(new Estado(300, "SP"));
			try{
				dao.create(cidade);
				dao.create(bairro);
				dao.create(funcionario.getEndereco());
				dao.create(funcionario.getContato());
				dao.create(funcionario);
			}catch(Exception e){
				System.out.println("Erro ao tentar cadastrar funcionário"+e.getMessage());
			}
			
		}
		return movimento;
	}

	@Override
	public void validate() {
		Funcionario funcionario = (Funcionario) movimento.getObjMovimentado();

		Bairro bairro = funcionario.getEndereco().getBairro();
		Cidade cidade = bairro.getCidade();
		
		if(bairro != null && bairro.getDescricao().equals(""))
			addErro("Campo Obrigatório: Bairro");
		if(cidade != null && cidade.getDescricao().equals(""))
			addErro("Campo Obrigatório: Cidade");
		if(funcionario.getEndereco() != null && funcionario.getEndereco().getDescricao().equals(""))
			addErro("Campo Obrigatório: Endereço");
		if(funcionario.getContato() != null && funcionario.getContato().getEmail().equals(""))
			addErro("Campo Obrigatório: Email");
		
	}
}
