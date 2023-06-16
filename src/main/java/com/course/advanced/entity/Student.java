package com.course.advanced.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "students")
@Getter @Setter @ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    private String lastName;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    // SET

//    @ElementCollection
//    @CollectionTable(
//            name = "student_images", // name of the table we want to use as collection
//            joinColumns = @JoinColumn(name = "students_id") // student_images' column by which students table will be joined
//    )
//    @Column(name="file_name") // student_images' column for value (name) of the image
//    private Set<String> images = new HashSet<>();

    // ORDERED SET

//    @ElementCollection
//    @CollectionTable(
//            name = "student_images",
//            joinColumns = @JoinColumn(name = "students_id")
//    )
//    @OrderBy("file_name DESC, image_description DESC")
//    @Column(name = "file_name")
//    private Set<String> images = new LinkedHashSet<>();

    // ORDERED LIST

//    @ElementCollection
//    @CollectionTable(
//            name = "student_images",
//            joinColumns = @JoinColumn(name = "students_id")
//    )
//    @OrderColumn(name = "image_order") // name of the column by which the order is established (hibernate will automatically set a new value during the creation of a new image)
//    @Column(name="file_name")
//    private List<String> images = new ArrayList<>();

    // Ordered MAP

    @ElementCollection
    @CollectionTable(
            name = "student_images",
            joinColumns = @JoinColumn(name = "students_id")
    )
    @MapKeyColumn(name = "file_name")
    @Column(name = "image_description") // (value)
    @OrderBy // based on the key value by default (@OrderBy("file_name desc") would work as well)
    private Map<String, String> images = new TreeMap<>();

    // EMBEDDED EMBEDDABLE

    @Embedded // optional
    private Address address;

    @AttributeOverrides({
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "BILLING_STREET")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "BILLING_CITY")
            ),
            @AttributeOverride(
                    name = "zipcode",
                    column = @Column(name = "BILLING_ZIPCODE")
            )
    })
    @Embedded // optional
    private Address billingAddress;

    // ENUM

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", columnDefinition = "varchar(20) default 'INACTIVE'")
    private Status status = Status.INACTIVE; // default to be inserted
}

