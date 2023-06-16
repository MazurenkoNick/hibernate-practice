package com.course.hibernatepr.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch=FetchType.LAZY, cascade={
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    // course_id points to the field in the "review" table because we are using list and uni-directional relationship
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> reviews;

    @ManyToMany(fetch=FetchType.LAZY, cascade={
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> students;

    public Course(String title) {
        this.title = title;
    }

    public Course(String title, Instructor instructor) {
        this.title = title;
        this.instructor = instructor;
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void addAllReviews(Review... reviews) {
        for (Review rev : reviews) {
            addReview(rev);
        }
    }

    public void addStudent(Student student) {
        if (students == null) {
            this.students = new ArrayList<>();
        }
        students.add(student);
    }

    public void addAllStudents(Student... students) {
        for (Student s : students) {
            addStudent(s);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructorID=" + (instructor != null ? instructor.getId() : null) +
                '}';
    }
}
