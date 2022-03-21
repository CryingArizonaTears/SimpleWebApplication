package com.godel.employees.controller;

import com.godel.employees.api.service.IEmployeeService;
import com.godel.employees.dto.EmployeeDto;
import com.godel.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.update(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
