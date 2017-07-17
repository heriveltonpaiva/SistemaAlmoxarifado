package br.arquitetura.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dominio.Estado;

@Repository
@Transactional
public class EstadoImpl extends GenericDaoImpl implements EstadoDao{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> findAll() {
		Query q = getSession().createQuery("From Estado");
		return q.list();
	}

	public Estado findBySigla(String descricao) {
		Query q = getSession().createQuery("From Estado where descricao like '%"+descricao+"%'");
		return (Estado) q.uniqueResult();
	}
	
}
