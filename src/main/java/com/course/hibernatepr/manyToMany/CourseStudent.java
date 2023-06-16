package com.course.hibernatepr.manyToMany;

import com.course.hibernatepr.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CourseStudent {

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

            // Create course
            Course course = new Course("FIFA15");

            // save course
            session1.save(course);

            Student student1 = new Student("fn1", "fn1", "fn1@gmail.com");
            Student student2 = new Student("fn2", "fn2", "fn2@gmail.com");
            Student student3 = new Student("fn3", "fn3", "fn3@gmail.com");

            course.addAllStudents(student1, student2, student3);

            // save course ... students will also be saved thanks to cascade types we've implemented
            session1.save(student1);
            session1.save(student2);
            session1.save(student3);

            session1.getTransaction().commit();
        }
    }
}
