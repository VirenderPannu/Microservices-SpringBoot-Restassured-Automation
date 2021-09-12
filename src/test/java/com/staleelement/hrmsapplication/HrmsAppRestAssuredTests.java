package com.staleelement.hrmsapplication;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import com.staleelement.hrmsapplication.model.Address;
import com.staleelement.hrmsapplication.model.Employee;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrmsAppRestAssuredTests {
    
    @Value("${base.url}")
    private String baseUrl;

    @LocalServerPort
    private int  port;

    @Test
	void testGetEmployeeByID() {
        given()
                .baseUri(baseUrl)
                .port(port)
                .basePath("/hr/employee/101")
                .get().then().assertThat().body("name", Matchers.equalTo("Virender"));
    }

    @Test
	void testGetEmployee(){
        // Arrange
		Employee employee = Employee.builder()
									.id(101)
									.name("Virender")
									.email("virender.pannu@gmail.com")
									.phone(987654)
									.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
									.build();
        
        // Act
        var response = given().baseUri(baseUrl).port(port).basePath("/hr/employee/101").get();
        var responseEntity = response.body().as(Employee.class);

        // Assert
        assertThat(responseEntity).isEqualTo(employee);
	}

    @Test
	void testPostEmployee(){
        // Arrange
		Employee newEmployee = Employee.builder()
									.id(104)
									.name("newQA")
									.email("qa.staleelement@gmail.com")
									.phone(987656)
									.address(Address.builder().city("Hissar").state("Haryanaa").country("Indiaa").build())
									.build();
        // Act
        var response = given()
                            .baseUri(baseUrl)
                            .port(port)
                            .basePath("/hr/addEmployee")
                            .contentType("application/json")
                            .body(newEmployee)
                            .post();
        
        var responseEntity = response.body().as(Employee[].class);
        var responseEmployee = Arrays.stream(responseEntity).filter(x -> x.getId() == 104).findFirst().get();

        // Assert
        assertThat(responseEmployee).isEqualTo(newEmployee);
	}

    @Test
	void testPutEmployee(){
        // Arrange
		Employee existingEmployee = Employee.builder()
									.id(103)
									.name("newQAAutomation")
									.email("test.staleelement@gmail.com")
									.phone(987651)
									.address(Address.builder().city("Hissara").state("Haryanaa").country("Indiaa").build())
									.build();
        // Act
        var response = given()
                            .baseUri(baseUrl)
                            .port(port)
                            .basePath("/hr/updateEmployee/103")
                            .contentType("application/json")
                            .body(existingEmployee)
                            .put();
        
        var responseEntity = response.body().as(Employee.class);

        // Assert
        assertThat(responseEntity).isEqualTo(existingEmployee);
	}
}
