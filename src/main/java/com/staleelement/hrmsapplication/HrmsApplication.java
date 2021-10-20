package com.staleelement.hrmsapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrmsApplication {

	// Run this to start Apache Tomcat server at port 9003 i.e. http://localhost:9003/
	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

}
