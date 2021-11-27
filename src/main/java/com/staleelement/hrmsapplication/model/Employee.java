package com.staleelement.hrmsapplication.model;


import lombok.Builder;
import lombok.Data;

// By using Lombok's Builder pattern, no need to write getter/setter bolier plate code. 
// Just decorate @Builder and @Data annotation on class or a method and it will automatically create getter/setter bolier plate code.
@Builder // Build Object methods
@Data // getter-setter methods
public class Employee { // POJO - Plain Old Java Object Class
    private int id;
    private String name;
    private Address address;
    private String email;
    private int phone;
}
