package br.com.jcrud.persistence.daointerfaces;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

/**
 * Created by jaels on 20/10/2016.
 */
public interface DAO<T> extends Serializable {
    T save(T entity);
    T update(T entity);
    List<T> getAll();
    List<T> findByHQLQuery(String queryId, int maxResults);
    List<T> findByHQLQuery(String queryId, List<Object> value, int maxResults);
    void remove(T entity);
    T finById(Serializable id);
	int updateHQLQuery(String queryId);
	int updateHQLQuery(String queryId, List<Object> value);
	Query query(String hql);
	List<T> findByHQLNamedQuery(String queryNamed, List<Object> value, int maxResults);
	List<T> findByHQLNamedQuery(String queryNamed, int maxResults);
}
