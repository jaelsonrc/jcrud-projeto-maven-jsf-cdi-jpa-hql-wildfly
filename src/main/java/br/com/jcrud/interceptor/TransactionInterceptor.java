package br.com.jcrud.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jcrud.annotations.Transactional;

/**
 * Created by jaels on 20/10/2016.
 */

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

     private static final long serialVersionUID = 1L;

     @Inject
     private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction trx = manager.getTransaction();
        
        
        Object result=null;
        
        try{
        	if(!trx.isActive()){
        		trx.begin();
        		result=context.proceed();
        		if(!trx.getRollbackOnly()){
        			trx.commit();
        		}else{
        			trx.rollback();
        		}
        	}
        }catch(Exception e){
        	if(trx.isActive()){
        		trx.rollback();
        	}
        }
        
        return result;
        
    }
}
