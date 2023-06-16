package com.course.hibernatepr.oneToOne;

import com.course.hibernatepr.entities.Instructor;
import com.course.hibernatepr.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainInstructor {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        // CREATING
        Instructor instructor = new Instructor("Nick", "Mazurenko", "mzrnk@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("mazurYT", "sport");
        instructor.setInstructorDetail(instructorDetail);

        session.beginTransaction();
        session.save(instructor);
        session.getTransaction().commit();

        // DELETING
        // using retrieving
        Session session1 = sessionFactory.getCurrentSession();
        session1.beginTransaction();

        Instructor instructor1 = session1.get(Instructor.class, 17L);
        // instructor_detail entity will also be deleted for this instructor because of CASCADE.ALL
        session1.delete(instructor1);
        session1.getTransaction().commit();

        // using query (only sql cascade parameters will be applied and hibernate cascade params - ignored)
        Session session2 = sessionFactory.getCurrentSession();
        session2.beginTransaction();

        // instructor_detail of this entity will not be deleted
        session2.createQuery("DELETE FROM Instructor i where i.id=18")
                .executeUpdate();
        session2.getTransaction().commit();

        // READING
        // thanks to bi-directional relationship we can retrieve one entity and get dependent one using getter
        Session session3 = sessionFactory.getCurrentSession();
        session3.beginTransaction();

        InstructorDetail instructorDetail1 = session3.get(InstructorDetail.class, 23L);
        System.out.println("Instructor: " + instructorDetail1.getInstructor());
        session3.getTransaction().commit();
    }
}
