package br.arquitetura.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dominio.Estado;
import br.arquitetura.dominio.Produto;
import br.arquitetura.dominio.Requisicao;

@Repository
@Transactional
public class ProdutoImpl extends GenericDaoImpl implements ProdutoDao {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findAll() {
		Query q = getSession().createQuery("From Produto");
		return q.list();
	}
	
	@Override
	public Produto findByCodigo(Integer codigoProduto) {
		Query q = getSession().createQuery(" From Produto where codigo = "+codigoProduto.intValue());
		return (Produto) q.uniqueResult();
	}

}
