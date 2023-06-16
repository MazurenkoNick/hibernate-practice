package com.course.advanced.inheritence.mapsuperclass;

import com.course.advanced.inheritence.joined.Cooker;
import com.course.advanced.inheritence.joined.FootballPlayer;
import com.course.advanced.inheritence.joined.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainMapSuperclass {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Seller.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Seller seller = new Seller();
            seller.setSalary(12345);
            seller.setJobTitle("Toy Seller");
            seller.setFirstName("Joe");
            seller.setLastName("Biden");

            session.persist(seller);

            session.getTransaction().commit();
        }
    }
}
