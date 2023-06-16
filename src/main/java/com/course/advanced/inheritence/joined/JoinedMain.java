package com.course.advanced.inheritence.joined;

import com.course.advanced.inheritence.tableperclass.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JoinedMain {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Cooker.class)
                .addAnnotatedClass(FootballPlayer.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Cooker cooker = new Cooker();
            FootballPlayer footballer = new FootballPlayer();

            cooker.setFirstName("Leha");
            cooker.setLastName("Lehovich");
            cooker.setExperienceYears(20);

            footballer.setFirstName("Ronaldo");
            footballer.setLastName("Petrovich");
            footballer.setClub("Barca");

            session.persist(cooker);
            session.persist(footballer);

            session.getTransaction().commit();
        }
    }
}
