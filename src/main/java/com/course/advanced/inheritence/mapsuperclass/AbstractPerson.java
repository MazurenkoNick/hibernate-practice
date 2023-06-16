package com.course.advanced.inheritence.mapsuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// IN TABLE PER CLASS - the root class is an entity. it allows us to perform polymorphic query.
// HERE - NOT AN ENTITY (polymorphic queries must be written with hql)
@MappedSuperclass
@Getter @Setter
public abstract class AbstractPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
