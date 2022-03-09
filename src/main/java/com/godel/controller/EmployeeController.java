package com.godel.controller;

import com.godel.api.service.IEmployeeService;
import com.godel.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Employee employee) {
        employeeService.delete(employee);
        return ResponseEntity.noContent().build();
    }
}
