package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<Employee> findAllEmployees(){
        return repository.findAll();
    }

    public void saveEmployee(Employee employee){
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());
        repository.save(employee);
    }

    public Optional<Employee> findById(Long id){
        return repository.findById(id);
    }

    public void updateEmployee(Employee employee){
        employee.setUpdated(LocalDateTime.now());
        repository.saveAndFlush(employee);
    }

    public void deleteEmployee(Employee employee){
        repository.delete(employee);
    }
}
