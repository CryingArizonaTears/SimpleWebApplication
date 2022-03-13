package com.godel.api.service;

import com.godel.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto get(Long id);

    Long save(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto);

    void delete(Long id);
}
