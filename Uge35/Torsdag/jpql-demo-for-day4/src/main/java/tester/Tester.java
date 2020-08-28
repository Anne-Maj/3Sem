package tester;

import entity.Employee;
import static entity.Employee_.id;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            Query query = em.createQuery("Select e FROM Employee e WHERE e.salary > 100000");
            List<Employee> result = query.getResultList();
            System.out.println("Employees with a salary above 100000 " + result);

            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            Query query2 = em.createQuery("Select e FROM Employee e WHERE e.id = :id");
            query2.setParameter("id", "klo999");
            Employee result2 = (Employee) query2.getSingleResult();
            System.out.println("Employee with id klo999 is: " + result2);

            //3) Create a query to fetch the highest salary and print the value
            Query query3 = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            BigDecimal result3 = (BigDecimal) query3.getSingleResult();
            System.out.println("The highest salary is: " + result3);

            //4) Create a query to fetch the firstName of all Employees and print the names
            Query query4 = em.createQuery("Select e.firstName FROM Employee e");
            List<String> result4 = query4.getResultList();
            System.out.println("Firstnames: "+result4);
            
            //5 Create a query to calculate the number of employees and print the number
            Query query5 = em.createQuery("Select count (e.firstName) FROM Employee e");
            long result5 = (long) query5.getSingleResult();
            System.out.println("Number of employees: "+result5);
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query query6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            Employee result6 = (Employee) query6.getSingleResult();
            System.out.println("Details of employee with highest salary: "+ result6);
            
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
