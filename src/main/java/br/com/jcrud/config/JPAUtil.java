package br.com.jcrud.config;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by jaels on 20/10/2016.
 */
public class JPAUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @Produces
    @RequestScoped
    public EntityManager criarEntityManager(){
        return emf.createEntityManager();
    }

    public void fecharEntityManager(@Disposes EntityManager em){
        if(em != null && em.isOpen()){
            em.close();
        }
    }
}
