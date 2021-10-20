package com.staleelement.hrmsapplication;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.staleelement.hrmsapplication.controller.EmployeeController;
import com.staleelement.hrmsapplication.model.Address;
import com.staleelement.hrmsapplication.model.Employee;
import com.staleelement.hrmsapplication.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// Will test only Controllers, assuming that Microservice is not implamented so far, will use below Mock Service
@WebMvcTest(EmployeeController.class) 
public class HrmsAppMockMvcWIthSprintBootTest {
    
    @Autowired
    private MockMvc mockMvc;

    // Mocking- we always mock a service because the actual service is not ready so far. In our case we need to mock the employee service
    // So we have to create a bean of mocked employee service - see below
    @MockBean 
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeByID() throws Exception{
        // ARRANGE - to provide Expected result
        Employee employee = Employee.builder()
									.id(101)
									.name("Virender")
									.email("virender.pannu@gmail.com")
									.phone(987654)
									.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
									.build();
        
        // ACT - We are mocking the employee service
        // Actual service is not there so we will get NULL (getEmployeeById method) but the entity would be returned by Stub (thenReturn method)
        when(employeeService.getEmployeeById(101)).thenReturn(employee); // Mockito's when()

        // ASSERT - We are NOT mocking the employee service controller
        this.mockMvc.perform(get("/hr/employee/101")).andExpect(status().isOk());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hr/employee/101"))
                    .andExpect(MockMvcResultMatchers.jsonPath("name").value("Virender"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetEmployeeList() throws Exception{
        // ARRANGE - to provide Expected result
        List<Employee> employeeList = new ArrayList<>(List.of(
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
        //ACT - We are mocking the employee service
        // Actual service is not there so we will get NULL (getAllEmployees method) but the entity would be returned by Stub (thenReturn method)
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        // ASSERT - We are NOT mocking the employee service controller
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hr/employeeList"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Virender"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].address.city").value("Hisar"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
