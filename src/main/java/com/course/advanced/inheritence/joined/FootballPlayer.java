package com.course.advanced.inheritence.joined;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "footballers")
@Getter @Setter @ToString
public class FootballPlayer extends Person {

    @Column(name = "club")
    private String club;
}
