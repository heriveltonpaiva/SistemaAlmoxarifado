package br.arquitetura.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.controller.SpringUtils;

@Repository
@EnableTransactionManagement
@Transactional
public class GenericDaoImpl implements GenericDao<Object>, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
	
	public GenericDaoImpl() {
		if(sessionFactory == null){
			try{
				sessionFactory = (SessionFactory) SpringUtils.ctx.getBean("sessionFactory");
			}catch(HibernateException e){
				System.out.println("Erro na tentativa de recuperação do sessionFactory injetado pelo Spring. "+e.getMessage());
			}
		}
	}
	
	public void create(Object obj) {
		getSession().save(obj);
		System.out.println("Registro Salvo na base de dados {"+obj.toString()+"}");
	}

	public void update(Object obj) {
		getSession().merge(obj);
		System.out.println("Registro Atualizado na base de dados {"+obj.toString()+"}");
	}

	public void remove(Object obj) {
		getSession().delete(obj);
		System.out.println("Registro Removido da base de dados {"+obj.toString()+"}");
	}

	
	@SuppressWarnings("unchecked")
	public Collection<Object> findAll(Class<?> obj) {
	 return getSession().createCriteria(obj.getClass()).list();
	}
	
	public Object findById(Class<?> obj, int id) {
        Query query = getSession().createQuery("from "+obj.getSimpleName() +" obj where obj.id = "+id);
		return query.uniqueResult();
	}
	
	
	public Session getSession() {
		Session openedSession;
		try{
			openedSession = sessionFactory.getCurrentSession();
		}catch(HibernateException e){
			openedSession = sessionFactory.openSession();
		}
		
		if(openedSession.isConnected())
			System.out.println("Conexão com a base de dados realizada com sucesso.");
		else
			System.out.println("Falha na conexão com a base de dados");
		
		return openedSession;
	}
	
}
