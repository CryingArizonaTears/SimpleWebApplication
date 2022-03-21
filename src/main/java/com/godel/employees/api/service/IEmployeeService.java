package com.godel.employees.api.service;

import com.godel.employees.dto.EmployeeDto;
import com.godel.employees.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto get(Long id);

    Employee save(EmployeeDto employeeDto);

    Employee update(Long employeeId, EmployeeDto employeeDto);

    void delete(Long id);
}
