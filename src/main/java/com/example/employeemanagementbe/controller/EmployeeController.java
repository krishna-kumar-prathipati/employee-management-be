package com.example.employeemanagementbe.controller;

import com.example.employeemanagementbe.exception.ResourceNotFoundException;
import com.example.employeemanagementbe.model.Employee;
import com.example.employeemanagementbe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestBody Employee emp){
        return employeeRepository.save(emp);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return ResponseEntity.ok(employee.get());
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee updatedEmployee){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Ue with id "+id+" Not found"));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmailId(updatedEmployee.getEmailId());

        employeeRepository.save(employee);
        return ResponseEntity.ok(employee);

    }
    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }

}
