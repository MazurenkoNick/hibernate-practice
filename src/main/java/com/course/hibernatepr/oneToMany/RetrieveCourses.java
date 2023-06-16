package com.course.hibernatepr.oneToMany;

import com.course.hibernatepr.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RetrieveCourses {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            // OPTION 1 (using getter)
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 20L);
            System.out.println("Instructor: " + instructor);


            System.out.println("Instructor: " + instructor);
            // get all courses while session is open

            // delete course
//            Course course = instructor.getCourses().get(0);
//            session.remove(course);

            session.getTransaction().commit();


            // OPTION 2 (using HQL)
            // query instructor with all courses not using getters
            Session session2 = sessionFactory.getCurrentSession();
            session2.beginTransaction();

            // creating query
            Long instructorId = 20L;
            // A "fetch" join allows associations or collections of values to be initialized along with their parent objects using a single select.
            // This is particularly useful in the case of a collection.
//            Query<Instructor> query = session2.createQuery("SELECT i from Instructor i " +
//                                                            "JOIN FETCH i.courses " +
//                                                            "WHERE i.id=:instructorId", Instructor.class);
//            query.setParameter("instructorId", instructorId);
//
//            Instructor instructor2 = query.getSingleResult();

            session2.update(instructor);
            System.out.println(instructor.getCourses());
            session2.getTransaction().commit();
            session2.close();
        }
        finally {
            session.close();
        }
    }
}
