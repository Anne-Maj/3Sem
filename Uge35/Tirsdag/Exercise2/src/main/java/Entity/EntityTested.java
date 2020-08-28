/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Facade.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 45222
 */
public class EntityTested {
    
    public static void main(String[] args) {
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Customer svend = new Customer("Svend", "Jensen");
        em.persist(svend);
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        CustomerFacade cusFad = new CustomerFacade();
       
    }
    
}
