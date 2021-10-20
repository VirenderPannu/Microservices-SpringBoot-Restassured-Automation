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

import io.restassured.response.Response;

// We have to add webEnvironment to start actual application context (IoC Container) and Apache tomcat server on defined/random port
// We have also no need to run explicitely HrmsApplication class, as the below WebEnvironment run the App with embedded Tomcat server
// And we can test the integration APIs with our automated test scripts
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrmsAppRestAssuredTests {
    
    @Value("${base.url}")
    private String baseUrl;

    @LocalServerPort
    private int port;

    @Test
	void testGetEmployeeByID() {
        given()
            .baseUri(baseUrl)
            .port(port)
            .basePath("/hr/employee/101")
            .get()
            .then()
            .assertThat()
            .body("name", Matchers.equalTo("Virender"));
    }

    @Test
	void testGetEmployee(){
        // ARRANGE - to proovide Expected result
        // String employee = "Employee(id=101, name=Virender, address=Address(city=Hisar, state=Haryana, country=India), email=virender.pannu@gmail.com, phone=987654)";
		Employee expectedEmployee = Employee.builder()
									.id(101)
									.name("Virender")
									.email("virender.pannu@gmail.com")
									.phone(987654)
									.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
									.build();
        
        // ACT - to provide Actual result
        Response response = given().baseUri(baseUrl).port(port).basePath("/hr/employee/101").get();
        Employee actualEmployee = response.body().as(Employee.class);

        // ASSERT - to map Actual with Expected result
        assertThat(actualEmployee).isEqualTo(expectedEmployee);
	}

    @Test
	void testPostEmployee(){
        // ARRANGE - providing request body for Post request
		Employee newEmployee = Employee.builder()
									.id(104)
									.name("newQA")
									.email("qa.staleelement@gmail.com")
									.phone(987656)
									.address(Address.builder().city("Hissar").state("Haryanaa").country("Indiaa").build())
									.build();
        // ACT - posting above request body with content type
        Response response = given()
                            .baseUri(baseUrl)
                            .port(port)
                            .basePath("/hr/addEmployee")
                            .contentType("application/json")
                            .body(newEmployee)
                            .post();
        
        Employee[] responseEmployees = response.body().as(Employee[].class);
        Employee responseEmployee = Arrays.stream(responseEmployees).filter(employee -> employee.getId() == 104).findFirst().get();
        // ASSERT - verifying newly added employee
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
        Response response = given()
                            .baseUri(baseUrl)
                            .port(port)
                            .basePath("/hr/updateEmployee/103")
                            .contentType("application/json")
                            .body(existingEmployee)
                            .put();
        
        Employee changedEmployee = response.body().as(Employee.class);
        // Assert
        assertThat(changedEmployee).isEqualTo(existingEmployee);
	}
}
