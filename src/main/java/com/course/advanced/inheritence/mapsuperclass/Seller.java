package com.course.advanced.inheritence.mapsuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sellers")
@Getter @Setter @ToString
public class Seller extends AbstractPerson {

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "salary")
    private double salary;
}
