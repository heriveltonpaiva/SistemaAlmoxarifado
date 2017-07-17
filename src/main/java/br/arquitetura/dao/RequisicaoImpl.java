package br.arquitetura.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dominio.ItemRequisicao;
import br.arquitetura.dominio.Requisicao;

@Repository
@Transactional
public class RequisicaoImpl extends GenericDaoImpl implements RequisicaoDao {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Requisicao> listar() {
		Query q = getSession().createQuery(" From Requisicao ");
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<ItemRequisicao> findItensByRequisicao(int idReq){
		Query q = getSession().createQuery(" From ItemRequisicao where requisicao.id = :idRequisicao");
		q.setInteger("idRequisicao", idReq);
		return q.list();
	}
}
