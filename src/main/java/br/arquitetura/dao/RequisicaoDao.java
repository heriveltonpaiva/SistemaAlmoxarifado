package br.arquitetura.dao;

import java.util.List;

import br.arquitetura.dominio.ItemRequisicao;
import br.arquitetura.dominio.Requisicao;

public interface RequisicaoDao {
	
	public List<Requisicao> listar();

	public List<ItemRequisicao> findItensByRequisicao(int idReq);

}
