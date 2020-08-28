/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author 45222
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;

    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cusFad = CustomerFacade.getCustomerFacade(emf);

//Add Customer
        Customer cus1 = cusFad.addCustomer("Lise", "Hansen");
        Customer cus2 = cusFad.addCustomer("Henning", "Jensen");
        Customer cus3 = cusFad.addCustomer("Sanne", "Nielsen");
        Customer cus4 = cusFad.addCustomer("Per", "Smith");

//Find Customer By ID
        System.out.println("Customer 1, found by id: " + cusFad.findCustomerById(cus1.getId()).toString());

//List<Customer> findByLastName(String name)
        System.out.println("Customers with last name: " + cusFad.findByLastName("Nielsen").toString());

//int getNumberOfCustomers();
        System.out.println("Number of customers: " + cusFad.getAllCustomers().size());

//List<Customer> allCustomers();
        System.out.println("All customers " + cusFad.getAllCustomers().toString());

    }

    public Customer addCustomer(String firstName, String lastName) {
        Customer cus = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return cus;
        } finally {
            em.close();
        }
    }

    public Customer findCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cus = em.find(Customer.class, id);
            return cus;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query;
            query = em.createQuery("Select c from Customer c where c.lastName = :lastName", Customer.class);

            return query.setParameter("lastName", lastName).getResultList();
        } finally {
            em.close();

        }
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query;
            query = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
