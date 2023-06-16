package com.course.hibernatepr;

import com.course.hibernatepr.entities.Employee;
import com.course.hibernatepr.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeExercise {

    private final SessionFactory sessionFactory;

    public EmployeeExercise() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

    }

    public static void main(String[] args) {
        EmployeeExercise ex = new EmployeeExercise();
        Employee employee = new Employee("Nikita", "Mazurenko", "EPAM");

        // create
        ex.save(employee);

        // read
        Employee e1 = (Employee) ex.retrieve(3L, Employee.class);
        System.out.println(e1);

        // delete
        ex.deleteByPrimaryKey(employee.getId(), Employee.class);

        // update
        Session session = ex.sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("update Employee e set e.company='GlobalLogic' where e.firstName='Nikita'")
               .executeUpdate();
        session.getTransaction().commit();
    }

    public void save(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(entity);
        session.getTransaction().commit();
    }

    public Object retrieve(Long primaryKey, Class<?> aClass) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Object entity = session.get(aClass, primaryKey);
        session.getTransaction().commit();
        return entity;
    }

    public List<Employee> findByCompany(String companyName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Employee> employees = session.createQuery(
                String.format("from Employee e where e.company = %s", companyName), Employee.class)
                .getResultList();

        session.getTransaction().commit();
        return employees;
    }

    public <T> void deleteByPrimaryKey(Long pk, Class<T> aClass) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        T entity = session.get(aClass, pk);
        session.delete(entity);

        session.getTransaction().commit();
    }
}
