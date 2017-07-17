package br.arquitetura.dao;

import java.util.List;

import br.arquitetura.dominio.Estado;

public interface EstadoDao {

	public List<Estado> findAll();
	
	public Estado findBySigla(String sigla);
}
