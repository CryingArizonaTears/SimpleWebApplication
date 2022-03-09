package com.godel.service;

import com.godel.api.dao.IEmployeeDao;
import com.godel.api.service.IEmployeeService;
import com.godel.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Employee get(Long id) {
        return employeeDao.get(id);
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Transactional
    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Transactional
    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }
}
