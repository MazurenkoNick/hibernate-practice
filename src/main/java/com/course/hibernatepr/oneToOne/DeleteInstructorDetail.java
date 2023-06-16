package com.course.hibernatepr.oneToOne;

import com.course.hibernatepr.entities.Instructor;
import com.course.hibernatepr.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetail {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        InstructorDetail instructorDetail = session.get(InstructorDetail.class,24L);
        // break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        session.delete(instructorDetail);

        session.getTransaction().commit();
    }
}
