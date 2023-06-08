package com.example.employeemanagementbe;

import com.example.employeemanagementbe.controller.EmployeeController;
import com.example.employeemanagementbe.model.Employee;
import com.example.employeemanagementbe.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeManagementBeApplicationTests {

    @Autowired
    private EmployeeController employeeController;
    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void getAllEmployees(){
        List<Employee> data = new ArrayList<>();
        data.add(new Employee("lrishna","kumar","kk@gmail.com"));
        data.add(new Employee("lrishna2","kumar","kk@gmail.com"));
        when(employeeRepository.findAll()).thenReturn(data);
        assertEquals(2,employeeController.getAllEmployees().size());
    }
    @Test
    public void addEmployee(){
        Employee e1 = new Employee("lrishna","kumar","kk@gmail.com");
        when(employeeRepository.save(e1)).thenReturn(e1);
        assertEquals(e1,employeeController.addEmployee(e1));
    }
    @Test
    public void findEmployeeById(){
        Employee e1 = new Employee("lrishna","kumar","kk@gmail.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(e1));
        assertEquals(e1,employeeController.findEmployeeById(1L));
    }
    @Test
    public void deleteEmployeeById(){
        Employee e1 = new Employee("lrishna","kumar","kk@gmail.com");
        employeeRepository.deleteById((1L));
        verify(employeeRepository,times(1)).deleteById(1L);
    }
    @Test
    public void updateEmployeeById(){
        Employee e1 = new Employee("lrishna","kumar","kk@gmail.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(e1));
        when(employeeRepository.save(e1)).thenReturn(e1);
        assertEquals(ResponseEntity.ok(e1),employeeController.updateEmployeeById(1L,e1));

    }


}
