package com.staleelement.hrmsapplication;

import com.staleelement.hrmsapplication.model.Address;
import com.staleelement.hrmsapplication.model.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

// We have to add webEnvironment to start actualapplication context and Apache tomcat server on defined or random  port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HrmsAppSpringTestRestTemplateTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void testGetEmployeeByID() {
		// Arrange
		final String baseUrl = "http://localhost:"+port+"/hr/employee/101";
		Employee employee = Employee.builder()
									.id(101)
									.name("Virender")
									.email("virender.pannu@gmail.com")
									.phone(987654)
									.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
									.build();
		// Act
		var responseEntity = this.restTemplate.getForObject(baseUrl, Employee.class);
		//Assert
		assertThat(responseEntity).isEqualTo(employee);
	}

	@Test
	void testGetEmployees(){
		// Arrange
		final String baseUrl = "http://localhost:"+port+"/hr/employeeList";
		Employee employee = Employee.builder()
									.id(101)
									.name("Virender")
									.email("virender.pannu@gmail.com")
									.phone(987654)
									.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
									.build();
		// Act
		var responseEntity  = this.restTemplate.getForObject(baseUrl,Employee[].class);
		var responseEmployee = Arrays.stream(responseEntity).filter(x -> x.getId() == 101).findFirst().get();
		// Assert
		assertThat(responseEmployee).isEqualTo(employee);
	}

	@Test
	void testPostEmployees(){
		// Arrange
		final String baseUrl = "http://localhost:"+port+"/hr/addEmployee";
		Employee employee = Employee.builder()
									.id(105)
									.name("Virenders")
									.email("virender.pannus@gmail.com")
									.phone(987655)
									.address(Address.builder().city("Hisars").state("Haryanas").country("Indias").build())
									.build();
		// Act
		var responseEntity  = this.restTemplate.postForObject(baseUrl, employee, Employee[].class);
		var responseEmployee = Arrays.stream(responseEntity).filter(x -> x.getId() == 105).findFirst().get();
		// Assert
		assertThat(responseEmployee).isEqualTo(employee);
	}

}
 