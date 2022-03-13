package com.godel.controller;

import com.godel.api.service.IEmployeeService;
import com.godel.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Long> create(@RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.save(employeeDto));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid EmployeeDto employeeDto) {
        employeeService.update(employeeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
