package br.arquitetura.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dominio.Funcionario;

@Repository
@Transactional
public class FuncionarioImpl extends GenericDaoImpl implements FuncionarioDao {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> findAll() {
		Query q = getSession().createQuery("From Funcionario ");
		return q.list();
	}
	
	public Funcionario findByNome(String nome) {
		Query q = getSession().createQuery("From Funcionario where nome like '%"+nome+"%'");
		return (Funcionario) q.uniqueResult();
	}

}
