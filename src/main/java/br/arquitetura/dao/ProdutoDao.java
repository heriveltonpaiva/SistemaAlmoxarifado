package br.arquitetura.dao;

import java.util.List;

import br.arquitetura.dominio.Produto;

public interface ProdutoDao {

	public List<Produto> findAll();
	
	public Produto findByCodigo(Integer codigo);
}
