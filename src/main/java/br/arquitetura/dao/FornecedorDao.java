package br.arquitetura.dao;

import java.util.List;

import br.arquitetura.dominio.Fornecedor;

public interface FornecedorDao {
	public List<Fornecedor> findAll();
	
	public Fornecedor findByNome(String nome);
}
