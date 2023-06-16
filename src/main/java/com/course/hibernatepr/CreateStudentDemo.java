package com.course.hibernatepr;

import com.course.hibernatepr.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {
        // create a session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();


        try {
            // USE THE SESSION OBJECT TO SAVE JAVA OBJECT
            // create a session
            Session session1 = sessionFactory.getCurrentSession();
            // create student objectS
            Student student = new Student("Ivan", "Mazurenko", "ivn_mzrnk@gmail.com");
            // start a transaction
            session1.beginTransaction();
            // save the object
            session1.save(student);
            // commit transaction
            session1.getTransaction().commit();

            // RETRIEVING STUDENT BY ID
            // now we get a new session and get new transaction
            Session session2 = sessionFactory.getCurrentSession();
            session2.beginTransaction();
            // retrieving process
            Long id = student.getId();
            Student student1 = session2.get(Student.class, id);
            // commit transaction
            session2.getTransaction().commit();

            System.out.println(student1);

            // RETRIEVING STUDENT USING QUERY
            // create session and begin transaction
            Session session3 = sessionFactory.getCurrentSession();
            session3.beginTransaction();
            // start querying
            Student student2 = session3.createQuery("from Student s where s.firstName = 'Nichi'", Student.class)
                                       .getSingleResult();
            // commit transaction
            session3.getTransaction().commit();

            System.out.println(student2);

            // UPDATING OBJECT IN DB
            // get session and begin transaction
            Session session4 = sessionFactory.getCurrentSession();
            session4.beginTransaction();

            // updates objects using query
            session4.createQuery("update Student set lastName='Mazurenko' where id=1")
                    .executeUpdate();

            // update local object by changing the object and using commit
            // query object
            Student student3 = session4.createQuery("from Student s where s.firstName = 'Nichi'", Student.class)
                                       .getSingleResult();
            student3.setFirstName("Nichi");

            // commit transaction
            session4.getTransaction().commit();

            System.out.println(student2);

            // DELETING STUDENTS
            // get current session and begin a new transaction
            Session session5 = sessionFactory.getCurrentSession();
            session5.beginTransaction();

            // delete using query
            session5.createQuery("delete from Student s where s.firstName = 'Ivan' and s.id > 2")
                    .executeUpdate();
            // delete using retrieving
            Student student4 = session5.get(Student.class, 2L);
            session5.delete(student4);

            // commit transaction
            session5.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}
