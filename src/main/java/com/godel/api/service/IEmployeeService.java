package com.godel.api.service;

import com.godel.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getAll();

    Employee get(Long id);

    void save(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
}
