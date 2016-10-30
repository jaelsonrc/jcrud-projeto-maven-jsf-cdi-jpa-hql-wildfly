package br.com.jcrud.persistence.dao;

import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.fileservice.FileXMLService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jaels on 20/10/2016.
 */
public class DAOImpl<T> implements DAO<T> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager em;

    private final Class<T> classe;

    private static final FileXMLService hqlQuery;
   // private static final FileXMLService sqlQuery;

    static {
         hqlQuery = new FileXMLService("hql.xml");
    //    sqlQuery = new FileXMLService("sql.xml");
    }

    public  DAOImpl(Class<T> classe, EntityManager em){
           this.classe=classe;
           this.em=em;
    }

    @Override
    public T save(T entity) {
        em.merge(entity);
        em.flush();
        return  entity;
    }

    @Override
    public T update(T entity) {
        entity= em.merge(entity);
        em.flush();
        return entity;
    }

    @Override
    public List<T> getAll() {
        String hql="Select e from "+this.classe.getSimpleName() +" e";
        TypedQuery<T> query = em.createQuery(hql, this.classe);
        return query.getResultList();
    }

    @Override
    public List<T> findByHQLQueryNoParamaters(String queryId,int maxResults){
        String hql = hqlQuery.findValue(queryId);
        TypedQuery<T> query = em.createQuery(hql,this.classe);
        return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();

    }


    @Override
    public List<T> findByHQLQuery(String queryId, List<Object> value, int maxResults){
        String hql = hqlQuery.findValue(queryId);
        Pattern pattern = Pattern.compile("(:\\w+)");
        Matcher matcher = pattern.matcher(hql);
        List<String> params = new ArrayList<>();
        while (matcher.find()){
            params.add(matcher.group().replace( ":",  ""));
        }
        System.out.println();
        System.out.print(hql);
        TypedQuery<T> query = em.createQuery(hql,this.classe);
        for (int i = 0; i < params.size() ; i++) {
            System.out.println(params.get(i)+ " - " +  value.get(i));
            query.setParameter(params.get(i), value.get(i));
        }
        System.out.println();
        System.out.println();
        return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();
       // return  null;
    }

    @Override
    public int updateHQLQueryNoParamaters(String queryId){
    	  em.getTransaction().begin();
    	  String hql = hqlQuery.findValue(queryId);
    	  Query  query = em.createQuery(hql);
    	  int updateCount = query.executeUpdate();
    	  em.flush();
    	  em.getTransaction().commit();    	
    	  return updateCount;
    }
    
    @Override
    public int updateHQLQuery(String queryId, List<Object> value){
    	 em.getTransaction().begin();
        String hql = hqlQuery.findValue(queryId);
        Pattern pattern = Pattern.compile("(:\\w+)");
        Matcher matcher = pattern.matcher(hql);
        List<String> params = new ArrayList<>();
        while (matcher.find()){
            params.add(matcher.group().replace( ":",  ""));
        }
        System.out.print(hql);
        Query query = em.createQuery(hql);
        for (int i = 0; i < params.size() ; i++) {         
            query.setParameter(params.get(i), value.get(i));
        }
        int updateCount = query.executeUpdate();
        em.flush();
  	    em.getTransaction().commit();
        return updateCount;
    }
    
    @Override
    public void remove(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.flush();
    }

    @Override
    public T finById(Serializable id) {
        return em.find(this.classe, id);
    }
    
    @Override
    public Query query(String hql){
    	return em.createQuery(hql);
    }
}
