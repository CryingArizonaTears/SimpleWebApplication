package com.godel.api.service;

import com.godel.dto.EmployeeDto;
import com.godel.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto get(Long id);

    void save(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto);

    void delete(Long id);
}
