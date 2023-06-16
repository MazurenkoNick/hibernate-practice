package com.course.hibernatepr.manyToMany;

import com.course.hibernatepr.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetStudents {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (Session session1 = sessionFactory.getCurrentSession()) {
            session1.beginTransaction();

            Course course = session1.get(Course.class, 23L);
            System.out.println("Students: " + course.getStudents());

            session1.getTransaction().commit();
        }
    }
}
