package com.staleelement.hrmsapplication.service;

import java.util.List;

import com.staleelement.hrmsapplication.model.Employee;

import org.springframework.stereotype.Service;

// Services (@Service) where we have written our business logic
// Also creating Objects of POJO classes of Model
// But Here our services are not ready so we will moock it with in test cases "HrmsAppMockMvcWIthSprintBootTest"
@Service // @Service is alias for Component so we can autowire it anywhere and can use the bean of this class
public class EmployeeMockService {

        public List<Employee>  getAllEmployees(){
            return null;
        }

        public Employee getEmployeeById(int id){
            return null;
        }

        public List<Employee> addEmployee(Employee newEmployee){
            return null;
        }

        public Employee updateEmployee(int id, Employee existingEmployee){
            return null;
        }

        public List<Employee> deleteEmployee(int id){
            return null;
        }
}
