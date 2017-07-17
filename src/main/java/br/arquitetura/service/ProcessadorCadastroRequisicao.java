package br.arquitetura.service;

import java.math.BigDecimal;
import java.util.List;

import br.arquitetura.dao.ProdutoImpl;
import br.arquitetura.dao.RequisicaoImpl;
import br.arquitetura.dominio.ItemRequisicao;
import br.arquitetura.dominio.Produto;
import br.arquitetura.dominio.Requisicao;

public class ProcessadorCadastroRequisicao extends AbstractProcessador implements Comando{

	@Override
	public Movimento execute() {		
		Requisicao req = (Requisicao) movimento.getObjMovimentado();
		Movimento mov = movimento;
				
		validate();
		
		if(hasErros())
			return null;
		
		if(mov.getCodMovimento() == ListaComandos.CADASTRAR_REQUISICAO){
			try{
				adicionar(req);
			}catch(Exception e){
				System.out.println("Erro ao tentar cadastrar funcionário"+e.getMessage());
			}
			
		}
		return movimento;
	}

	@Override
	public void validate() {

	}
	
	public void adicionar(Requisicao req){
		List<ItemRequisicao> listaItens = req.getItens();
		RequisicaoImpl dao = new RequisicaoImpl();

		dao.create(req);

		for (ItemRequisicao itemLista : listaItens) {
			itemLista.setRequisicao(req);
			dao.create(itemLista);
			atualizarEstoque(itemLista);
		}

	}

	private void atualizarEstoque(ItemRequisicao itemLista) {
		ProdutoImpl prodDAO = new ProdutoImpl();
		//Atualizando o Estoque 
		   Produto produto = prodDAO.findByCodigo(itemLista.getProduto().getCodigo());
		   Long qntAtualizada =  produto.getQuantidade().longValue() - itemLista.getQuantidade();
		   
		   produto.setQuantidade(BigDecimal.valueOf(qntAtualizada));
		   prodDAO.update(produto);
	}  
}
