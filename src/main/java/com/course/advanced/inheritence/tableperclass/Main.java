package com.course.advanced.inheritence.tableperclass;

import com.course.advanced.inheritence.single.Animal;
import com.course.advanced.inheritence.single.NonExistentAnimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(Postman.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();

            Driver driver = new Driver();
            Postman postman = new Postman();

            driver.setDriverLicense(License.B_CATEGORY);
            driver.setFirstName("First");
            driver.setLastName("Driverenko");

            postman.setFirstName("Second");
            postman.setLastName("Postmanenko");
            postman.setBadgeNumber("#40382");

            session.persist(driver);
            session.persist(postman);

            session.getTransaction().commit();
        }
    }
}
