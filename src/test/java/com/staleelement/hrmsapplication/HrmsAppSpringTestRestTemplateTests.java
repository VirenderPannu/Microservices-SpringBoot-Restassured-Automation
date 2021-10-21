package com.staleelement.hrmsapplication;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import com.staleelement.hrmsapplication.model.Address;
import com.staleelement.hrmsapplication.model.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

// We have to add webEnvironment to start actual application context (IoC Container) and Apache tomcat server on defined/random port
// We have also no need to run explicitely HrmsApplication class, as the below WebEnvironment run the App with embedded Tomcat server
// And we can test the integration APIs with our automated test scripts
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HrmsAppSpringTestRestTemplateTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Value("${base.url}")
	private String baseUrl;

	@LocalServerPort
	private int port;


	@Test
	void testGetEmployeeByID() {
		// ARRANGE -> to provide Expected result
		final String url = baseUrl+":"+""+port+"/hr/employee/101";
		// String employee = "Employee(id=101, name=Virender, address=Address(city=Hisar, state=Haryana, country=India), email=virender.pannu@gmail.com, phone=987654)";
		Employee expectedEmployee = Employee.builder()
										.id(101)
										.name("Virender")
										.email("virender.pannu@gmail.com")
										.phone(987654)
										.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
										.build();
		// ACT -> to fetch Actual result
		Employee responseEntity = this.restTemplate.getForObject(url, Employee.class);
		//ASSERT -> to map actual with expected result
		assertThat(responseEntity).isEqualTo(expectedEmployee);
	}

	@Test
	void testGetEmployees(){
		// ARRANGE
		baseUrl = baseUrl+":"+port+"/hr/employeeList";
		Employee expectedEmployee = Employee.builder()
									.id(101)
									.name("Virender")
									.email("virender.pannu@gmail.com")
									.phone(987654)
									.address(Address.builder().city("Hisar").state("Haryana").country("India").build())
									.build();
		// ACT
		Employee[] responseEntity  = this.restTemplate.getForObject(baseUrl, Employee[].class);
		Employee responseEmployee = Arrays.stream(responseEntity).filter(employee -> employee.getId() == 101).findFirst().get();
		// ASSERT
		assertThat(responseEmployee).isEqualTo(expectedEmployee);
	}

	@Test
	void testPostEmployees(){
		// ARRANGE - providing request body for Post request
		baseUrl = baseUrl+":"+port+"/hr/addEmployee";
		Employee expectedEmployee = Employee.builder()
									.id(105)
									.name("Virenders")
									.email("virender.pannus@gmail.com")
									.phone(987655)
									.address(Address.builder().city("Hisars").state("Haryanas").country("Indias").build())
									.build();
		// ACT
		var responseEntity  = this.restTemplate.postForObject(baseUrl, expectedEmployee, Employee[].class);
		var responseEmployee = Arrays.stream(responseEntity).filter(employee -> employee.getId() == 105).findFirst().get();
		// ASSERT
		assertThat(responseEmployee).isEqualTo(expectedEmployee);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< HrmsAppSpringTestRestTemplateTests Ended Here >>>>>>>>>>>>>>>>>>>>>>");
	}

}
 