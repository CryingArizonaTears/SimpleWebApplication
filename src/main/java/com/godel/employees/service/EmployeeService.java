package com.godel.employees.service;

import com.godel.employees.api.dao.IEmployeeDao;
import com.godel.employees.api.service.IEmployeeService;
import com.godel.employees.dto.EmployeeDto;
import com.godel.employees.modelMapperMethods.ExtendedModelMapper;
import com.godel.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;
    @Autowired
    private ExtendedModelMapper modelMapper;

    @Override
    public List<EmployeeDto> getAll() {
        return modelMapper.mapList(employeeDao.getAll(), EmployeeDto.class);
    }

    @Override
    public EmployeeDto get(Long id) {
        return modelMapper.map(employeeDao.get(id), EmployeeDto.class);
    }

    @Transactional
    @Override
    public Employee save(EmployeeDto employeeDto) {
        return employeeDao.save(modelMapper.map(employeeDto, Employee.class));
    }

    @Transactional
    @Override
    public Employee update(Long employeeId, EmployeeDto employeeDto) {
        return employeeDao.update(employeeId, modelMapper.map(employeeDto, Employee.class));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }
}
