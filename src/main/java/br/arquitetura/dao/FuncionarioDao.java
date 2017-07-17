package br.arquitetura.dao;

import java.util.List;

import br.arquitetura.dominio.Funcionario;

public interface FuncionarioDao {

	public List<Funcionario> findAll();
	
	public Funcionario findByNome(String nome);

}
