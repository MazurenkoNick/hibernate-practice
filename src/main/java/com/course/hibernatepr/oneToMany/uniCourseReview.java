package com.course.hibernatepr.oneToMany;

import com.course.hibernatepr.entities.Course;
import com.course.hibernatepr.entities.Instructor;
import com.course.hibernatepr.entities.InstructorDetail;
import com.course.hibernatepr.entities.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class uniCourseReview {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // CREATE REVIEWS
        try (Session session1 = sessionFactory.getCurrentSession()) {
            session1.beginTransaction();

            // create a course
            Course course = new Course("Pacman - how to be good 4.0");

            // create a review
            Review review = new Review("Very cool course!");

            // save the course ... thanks to "cascade all" it'll save associated reviews too
            course.addReview(review);
            course.addReview(new Review("Nice job, man"));

            session1.save(course);

            session1.getTransaction().commit();
        }

        // GET COURSE AND REVIEWS
        try (Session session2 = sessionFactory.getCurrentSession()) {
            session2.beginTransaction();

            Long courseId = 14L;
            // creating query (using fetch because we have LAZY fetch, so we can get course with all its review at once).
            // As an alternative, we could use session.get method and then call getReviews.
            Query<Course> query = session2.createQuery("SELECT c FROM Course c " +
                    "JOIN FETCH c.reviews " +
                    "WHERE c.id=:courseId", Course.class);
            query.setParameter("courseId", courseId);
            // executing query
            Course course = query.getSingleResult();

            System.out.println("Reviews: " + course.getReviews());

            session2.getTransaction().commit();
        }
        // DELETE COURSE (should delete all associate reviews)
        try (Session session3 = sessionFactory.getCurrentSession()) {
            session3.beginTransaction();

            // get course from db
            Course course = session3.get(Course.class, 14L);

            // delete course
            session3.delete(course);

            session3.getTransaction().commit();
        }
    }
}
