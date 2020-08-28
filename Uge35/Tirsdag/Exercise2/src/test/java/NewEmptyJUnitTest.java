/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.Customer;
import Facade.CustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 45222
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

//    @Test
//    public void testFindCustomerById() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//        CustomerFacade cusFad = CustomerFacade.getCustomerFacade(emf);
//        CustomerFacade facade = new CustomerFacade();
//        Customer cus = new Customer("Lise", "Hansen");
//        assertEquals("{id=1, firstName=Lise, lastName=Hansen}", facade.findCustomerById(cus.getId()).toString());
//    }
    
}
