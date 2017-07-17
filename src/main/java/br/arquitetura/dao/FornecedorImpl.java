package br.arquitetura.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dominio.Fornecedor;

@Repository
@Transactional
public class FornecedorImpl extends GenericDaoImpl implements FornecedorDao {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> findAll() {
		Query q = getSession().createQuery("From Fornecedor");
		return q.list();
	}
	
	public Fornecedor findByNome(String nome) {
		Query q = getSession().createQuery("From Fornecedor where nome like '%"+nome+"%'");
		q.setMaxResults(1);
		if(q.list() != null)
			return (Fornecedor) q.list().get(0);
		return null;
	}
}
