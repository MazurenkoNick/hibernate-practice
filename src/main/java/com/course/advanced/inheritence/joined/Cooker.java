package com.course.advanced.inheritence.joined;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cookers")
@Getter @Setter @ToString
public class Cooker extends Person {

    @Column(name = "experience_years")
    private int experienceYears;
}
