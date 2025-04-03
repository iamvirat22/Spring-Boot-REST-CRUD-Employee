package com.luv2code.springboot.cruddemo.rest;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeDAOService) //constructor injection
    {
        employeeService = theEmployeeDAOService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable int employeeId)
    {
        Optional<Employee> theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null) {
            throw new RuntimeException("Employee Id not found " + employeeId);
        }
        return theEmployee;
    }

    //add new employee POST /employees
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        theEmployee.setId(0);
        Employee DEmployee = employeeService.save(theEmployee);
        return DEmployee;
    }

    //update employee - PUT
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee)
    {
        Employee DEmployee = employeeService.save(theEmployee);
        return DEmployee;
    }

    //Deleting employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Optional<Employee> theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null)
        {
            throw new RuntimeException("Employee Id not found " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee is " + employeeId ;
    }
}