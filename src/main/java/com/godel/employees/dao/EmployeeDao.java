package com.godel.employees.dao;

import com.godel.employees.api.dao.IEmployeeDao;
import com.godel.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao implements IEmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee get(Long id) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE employee_id=? ", new BeanPropertyRowMapper<>(Employee.class), id)
                .stream().findAny().orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee save(Employee employee) {
        Long employeeId = jdbcTemplate.queryForObject("INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) " +
                        "VALUES(?,?,?,?, cast(? as gender_enum),?) RETURNING employee_id ", Long.class,
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
                employee.getGender().toString(), employee.getDateOfBirth());
        employee.setEmployeeId(employeeId);
        return employee;
    }

    @Override
    public Employee update(Long employeeId, Employee employee) {
        int nullOrNotNull = jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=cast(? as gender_enum), date_of_birth=? WHERE employee_id=?",
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth(), employeeId);
        if (nullOrNotNull == 0) {
            throw new RuntimeException("Employee not found");
        }
        employee.setEmployeeId(employeeId);
        return employee;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", id);
    }
}
