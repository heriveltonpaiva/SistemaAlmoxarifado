package br.arquitetura.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.FornecedorImpl;
import br.arquitetura.dao.GenericDaoImpl;
import br.arquitetura.dominio.Produto;
@Transactional
@Service
public class ProcessadorCadastroProduto extends AbstractProcessador implements Comando{

	@Override
	public Movimento execute() {
		GenericDaoImpl dao = new GenericDaoImpl();
		FornecedorImpl fornDao = new FornecedorImpl();
		Produto produto = (Produto) movimento.getObjMovimentado();
		Movimento mov = movimento;
				
		validate();
		
		if(hasErros())
			return null;
		
		if(mov.getCodMovimento() == ListaComandos.CADASTRAR_PRODUTO){
			if(produto.getFornecedor() != null)
				produto.setFornecedor(fornDao.findByNome(produto.getFornecedor().getNome()));
			
			try{
				dao.create(produto);
			}catch(Exception e){
				System.out.println("Erro ao tentar cadastrar funcionário"+e.getMessage());
			}
			
		}
		return movimento;
	}


	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}
}
