package br.arquitetura.dao;

import java.util.Collection;

import org.hibernate.Session;


public interface GenericDao<T> {

	public void create(T obj);
	
	public void update(T obj);

	public void remove(T obj);

	public Collection<T> findAll(Class<?> obj);
	
	public T findById(Class<?> obj, int id);
		
	public Session getSession();

	
}
