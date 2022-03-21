package com.godel.employees.api.dao;

import com.godel.employees.model.Employee;

import java.util.List;

public interface IEmployeeDao {

    List<Employee> getAll();

    Employee get(Long id);

    Employee save(Employee employee);

    Employee update(Long employeeId, Employee employee);

    void delete(Long id);

}
