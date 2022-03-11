package com.godel.service;

import com.godel.api.dao.IEmployeeDao;
import com.godel.api.service.IEmployeeService;
import com.godel.dto.EmployeeDto;
import com.godel.model.Employee;
import com.godel.modelMapperMethods.ExtendedModelMapper;
import org.modelmapper.ModelMapper;
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
    public void save(EmployeeDto employeeDto) {
        employeeDao.save(modelMapper.map(employeeDto, Employee.class));
    }

    @Transactional
    @Override
    public void update(EmployeeDto employeeDto) {
        employeeDao.update(modelMapper.map(employeeDto, Employee.class));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }
}
