package com.course.advanced.inheritence.single;

import com.course.advanced.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.time.Year;
import java.time.temporal.TemporalAmount;


public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Animal.class)
                .addAnnotatedClass(NonExistentAnimal.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            NonExistentAnimal nonExistentAnimal = new NonExistentAnimal();

            nonExistentAnimal.setName("Dino");
            nonExistentAnimal.setStoppedToExistYear(Year.of(-9999).getValue());
            Animal defaultAnimal = new Animal();
            defaultAnimal.setName("Tiger");

            session.persist(nonExistentAnimal);
            session.persist(defaultAnimal);

            session.getTransaction().commit();
        }
    }
}
