package com.course.hibernatepr.oneToMany;

import com.course.hibernatepr.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesAndInstructor {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try(Session session1 = sessionFactory.getCurrentSession()) {
            session1.beginTransaction();

            Course course = new Course("How to be good at nothing");
            Instructor instructor = new Instructor("badName", "badLastName", "bad@email.com");
            course.setInstructor(instructor);

            session1.save(instructor);
            session1.save(course);

            session1.getTransaction().commit();
        }
    }
}
