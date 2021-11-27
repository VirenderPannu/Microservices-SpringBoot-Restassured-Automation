package com.staleelement.hrmsapplication.model;

import lombok.Builder;
import lombok.Data;

// By using Lombok's Builder pattern, no need to write getter/setter bolier plate code. 
// Just decorate @Builder and @Data annotation on class or a method and it will automatically create getter/setter bolier plate code. 
@Builder // Build Object methods
@Data // getter-setter methods
public class Address {  // POJO - Plain Old Java Object Class
    private String city;
    private String state;
    private String country;
}
