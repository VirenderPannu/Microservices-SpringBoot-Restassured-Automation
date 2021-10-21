package com.staleelement.hrmsapplication.controller;

import java.util.List;

import com.staleelement.hrmsapplication.model.Employee;
import com.staleelement.hrmsapplication.service.EmployeeMockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Controllers (@RestControllers > alias for @Contoller > alias for @Component) are mapped end points 
// and they are talking to beneath services (@Service) where we have written our business logic
@RestController
public class EmployeeControllerForMockedEmployeeService {

    @Autowired
    private EmployeeMockService employeeMockService;
    
    // @RequestMapping(method = RequestMethod.GET, value = "/employeeList")
    @GetMapping("/hr/employeeList")
    public List<Employee> getEmployeeList(){
        return employeeMockService.getAllEmployees();
    }

    // @RequestMapping(method = RequestMethod.GET, value = "/employee/{id}")
    @GetMapping("/hr/employee/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeMockService.getEmployeeById(id);
    }

    // @RequestMapping(method = RequestMethod.POST, value="/addEmployee")
    @PostMapping(value="/hr/addEmployee")
    public List<Employee> addEmployee(@RequestBody Employee newEmployee){
        return employeeMockService.addEmployee(newEmployee);
    }

    // @RequestMapping(method = RequestMethod.PUT, value="/addEmployee")
    @PutMapping("/hr/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee existingEmployee){
        return employeeMockService.updateEmployee(id, existingEmployee);
    }

    // @RequestMapping(method = RequestMethod.DELETE, value="/deleteEmployee")
    @DeleteMapping("/hr/deleteEmployee/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id){
        return employeeMockService.deleteEmployee(id);
    }
    
}
