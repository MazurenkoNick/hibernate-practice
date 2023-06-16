package com.course.advanced.inheritence.tableperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "postmen")
@Getter @Setter @ToString
public class Postman extends User {

    @Column(name = "badge_number")
    private String badgeNumber;
}
