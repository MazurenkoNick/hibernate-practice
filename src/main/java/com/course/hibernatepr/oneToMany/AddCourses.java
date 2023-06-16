package com.course.hibernatepr.oneToMany;

import com.course.hibernatepr.entities.Course;
import com.course.hibernatepr.entities.Instructor;
import com.course.hibernatepr.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourses {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 20L);
            Course course1 = new Course("3 Course");
            Course course2 = new Course("4 Course");

            instructor.addAllCourses(course1, course2);

            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }
}
