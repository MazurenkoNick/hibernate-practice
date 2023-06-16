package com.course.advanced.elementcollection;

import com.course.advanced.entity.Address;
import com.course.advanced.entity.Status;
import com.course.advanced.entity.Student;
import com.course.hibernatepr.entities.Instructor;
import com.course.hibernatepr.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class MainElementCollection {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            // SET, LIST

//            Student s = new Student();
//            s.setEmail("emaiss1l@.com");
//            s.setFirstName("Firssdsatenkoo");
//            s.setLastName("Lasteadsnkoo");
//
//            List<String> images = s.getImages();
//            images.add("image1.jpg");
//            images.add("image2.jpg");
//            images.add("image3.jpg");
//            images.add("image4.jpg");
//            images.add("image5.jpg");
//
//            session.persist(s);

//            Student student = session.get(Student.class, 9L);
//            System.out.println(student.getImages());

            // MAP
//            Student s = new Student();
//            s.setEmail("ivahnenko@gmail");
//            s.setLastName("ivahnenko");
//            s.setFirstName("ivhan");
//
//            Map<String, String> images = s.getImages();
//            images.put("image1.jpg", "the best picture irl");
//            images.put("image2.jpg", "the worst picture irl");
//
//            session.persist(s);

//            Student student = session.get(Student.class, 12L);
//            System.out.println(student.getImages());

            // EMBEDDABLE
            Student s = new Student();
            Address a = new Address();
            a.setCity("CITY");
            a.setStreet("STREET");
            a.setZipcode("ZIPCODE");
            s.setEmail("email25@gmail.com");
            s.setFirstName("Mazur");
            s.setLastName("Mazurenko");
            s.setAddress(a);
            s.setBillingAddress(a);

            session.persist(s);

            session.getTransaction().commit();

            Session session2 = sessionFactory.getCurrentSession();
            session2.beginTransaction();

            System.out.println(session2.get(Student.class, 13L));

            session2.getTransaction().commit();
            session2.close();
        }
    }
}
