package com.godel.dao;

import com.godel.api.dao.IEmployeeDao;
import com.godel.model.Employee;
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
                .stream().findAny().orElseThrow(() -> new RuntimeException("null"));
    }

    @Override
    public void save(Employee employee) {
        jdbcTemplate.update("INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES(?,?,?,?, cast(? as gender_enum),?)",
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth());
    }

    @Override
    public void update(Employee employee) {
        jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=cast(? as gender_enum), date_of_birth=? WHERE employee_id=?",
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth(), employee.getEmployeeId());
    }

    @Override
    public void delete(Employee employee) {
        jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", employee.getEmployeeId());
    }
}
