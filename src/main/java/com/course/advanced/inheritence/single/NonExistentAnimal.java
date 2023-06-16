package com.course.advanced.inheritence.single;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Year;

@Entity
@DiscriminatorValue("non-existent")
@Getter @Setter @ToString
public class NonExistentAnimal extends Animal {

    @Column(name = "stopped_to_exist_year")
    private int stoppedToExistYear;

    // columns which will not be mapped as fields of the class - will be ignored
}
