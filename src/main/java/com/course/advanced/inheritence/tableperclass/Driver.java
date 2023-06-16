package com.course.advanced.inheritence.tableperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "drivers")
@Getter @Setter @ToString
public class Driver extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_license")
    private License driverLicense;
}
