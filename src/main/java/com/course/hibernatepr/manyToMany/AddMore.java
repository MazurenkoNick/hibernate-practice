package com.course.hibernatepr.manyToMany;

import com.course.hibernatepr.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddMore {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // adding courses for student
        try (Session session1 = sessionFactory.getCurrentSession()) {
            session1.beginTransaction();
            // retrieve student from db
            Student student = session1.get(Student.class, 22L);
            System.out.println(student.getCourses());

            // create courses
            Course course1 = new Course("Fast cube assembly");
            Course course2 = new Course("Basketball course");

            course1.addStudent(student);
            course2.addStudent(student);

            // save courses
            session1.save(course1);
            session1.save(course2);

            session1.getTransaction().commit();
        }

        // adding students for course
        try (Session session2 = sessionFactory.getCurrentSession()) {
            session2.beginTransaction();

            // get course
            Course course = session2.get(Course.class, 23L);

            // create students
            Student student1 = new Student("f1", "l1", "ksksk@gmail");
            Student student2 = new Student("f2", "l2", "ksksk2@gmail");

            // add students to the course
            course.addAllStudents(student1, student2);

            // save students;
            session2.save(student1);
            session2.save(student2);

            session2.getTransaction().commit();
        }
    }
}
