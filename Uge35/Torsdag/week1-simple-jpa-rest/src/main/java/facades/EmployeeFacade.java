/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.Employee;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author 45222
 */
public class EmployeeFacade {

    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;

    public EmployeeFacade() {
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

        //createEmployee	
        Employee emp1 = facade.createEmployee("Jane Jensen", "Solvej 43", 40000);
        Employee emp2 = facade.createEmployee("Jens Hansen", "Landevejen 8", 35000);

        //Get employee by id
        System.out.println("Employee: " + facade.findEmployeeById(emp1.getId()).getName());

        //Get employee by name
        System.out.println("Find employee by name: " + facade.getEmployeeByName("Jane Jensen").toString());

        //Get all employees
        System.out.println("All employees: " + facade.getAllEmployees());

        //Get employee with highest salary
        System.out.println("Employee with highest salary: ");

    }

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    //Create employee
    public Employee createEmployee(String name, String adress, int salary) {
        Employee emp = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        } finally {
            em.close();
        }
    }

    //Get employee by id
    public Employee findEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = em.find(Employee.class, id);
            return emp;
        } finally {
            em.close();
        }
    }

    //Get employee by name
    public List<Employee> getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e where e.name = :name", Employee.class);
            return query.setParameter("name", name).getResultList();

        } finally {
            em.close();
        }

    }

    //Get all employees
    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select employee from Employee employee", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }

    }
}
