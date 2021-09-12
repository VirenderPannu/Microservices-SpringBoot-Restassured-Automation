package com.staleelement.hrmsapplication.service;

import java.util.ArrayList;
import java.util.List;

import com.staleelement.hrmsapplication.model.Address;
import com.staleelement.hrmsapplication.model.Employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
     // Entity
     private List<Employee> employeeList = new ArrayList<>(List.of(
        Employee.builder()
            .id(101)
            .name("Virender")
            .email("virender.pannu@gmail.com")
            .phone(987654)
            .address(Address.builder().city("Hisar").state("Haryana").country("India").build())
            .build(),
        Employee.builder()
            .id(102)
            .name("Shiven")
            .email("shiven.pannu@gmail.com")
            .phone(909654)
            .address(Address.builder().city("Noida").state("UP").country("India").build())
            .build(),
        Employee.builder()
            .id(103)
            .name("Anupam")
            .email("anupam.pannu@gmail.com")
            .phone(804654)
            .address(Address.builder().city("Bhiwani").state("Haryyana").country("India").build())
            .build()
        ));

        public List<Employee>  getAllEmployees(){
            return employeeList;
        }

        public Employee getEmployeeById(int id){
            return employeeList.stream()
                .filter(x -> x.getId() == id)
                .findFirst().get();
        }

        public List<Employee> addEmployee(Employee newEmployee){
            employeeList.add(newEmployee);
            return employeeList;
        }

        public Employee updateEmployee(int id, Employee existingEmployee){
            return employeeList.stream()
                            .filter(x -> x.getId() == id)
                            .peek(o -> o.setName(existingEmployee.getName()))
                            .peek(o -> o.setEmail(existingEmployee.getEmail()))
                            .peek(o -> o.setPhone(existingEmployee.getPhone()))
                            .peek(o -> o.setAddress(existingEmployee.getAddress()))
                            .findFirst().get();
        }

        public List<Employee> deleteEmployee(int id){
            employeeList.removeIf(x -> x.getId() == id);
            return employeeList;
        }
}
