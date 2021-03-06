package com.godel.employees.model;

import lombok.Data;

import java.time.LocalDate;


@Data
public class Employee {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private Gender gender;
    private LocalDate dateOfBirth;
}
