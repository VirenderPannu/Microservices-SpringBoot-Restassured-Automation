package com.staleelement.hrmsapplication.model;


import lombok.Builder;
import lombok.Data;

@Builder // By using Lombok's Builder pattern, no need to write getter/setter bolier plate code. 
// Just decorate @Builder and @Data annotation on class or a method and it will automatically create getter/setter bolier plate code.
@Data
public class Employee {
    // POJO Plain Old Java Object class
    private int id;
    private String name;
    private Address address;
    private String email;
    private int phone;
}
