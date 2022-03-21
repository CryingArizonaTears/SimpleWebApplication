package com.godel.employees.dto;

import com.godel.employees.model.Gender;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class EmployeeDto {

    private Long employeeId;
    @NotEmpty(message = "Not empty")
    private String firstName;
    @NotEmpty(message = "Not empty")
    private String lastName;
    @NotNull(message = "Not null")
    @Min(value = 1, message = "Min > 0")
    private Long departmentId;
    @NotEmpty(message = "Not empty")
    private String jobTitle;
    private Gender gender;
    @NotNull(message = "Not null")
    private LocalDate dateOfBirth;
}
