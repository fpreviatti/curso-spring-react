package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);

    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

       Employee employee = employeeService.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " +id ));

       return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        var employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " +id ));

        if(employee!=null){
            employeeDetails.setId(employee.getId());
            employeeService.updateEmployee(employeeDetails);
        }

        return ResponseEntity.ok(employeeDetails);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){

        var employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " +id));

        employeeService.deleteEmployee(employee);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return ResponseEntity.ok(response);
    }

}
