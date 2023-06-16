package com.course.hibernatepr.oneToMany;

import com.course.hibernatepr.entities.Course;
import com.course.hibernatepr.entities.Instructor;
import com.course.hibernatepr.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class GetInstructorAndCoursesSeparately {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // retrieving instructor
        try (Session session1 = sessionFactory.getCurrentSession()) {
            session1.beginTransaction();

            Instructor instructor = session1.get(Instructor.class, 20L);

            session1.getTransaction().commit();
            System.out.println("Instructor: " + instructor);
        }
        // ... retrieving instructor's courses (later in the program)
        try (Session session2 = sessionFactory.getCurrentSession()) {
            session2.beginTransaction();
            Long instructorId = 20L;

            // creating query
            Query<Course> query = session2.createQuery("SELECT c FROM Course c " +
                                                        "WHERE c.instructor.id=:instructorId", Course.class);
            query.setParameter("instructorId", instructorId);
            // retrieving courses
            List<Course> courses = query.getResultList();

            session2.getTransaction().commit();
            System.out.println("Courses: " + courses);
        }
    }
}
