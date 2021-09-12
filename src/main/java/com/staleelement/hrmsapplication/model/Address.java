package com.staleelement.hrmsapplication.model;

import lombok.Builder;
import lombok.Data;

@Builder // By using Lombok's Builder pattern, no need to write getter/setter bolier plate code. 
        // Just decorate @Builder and @Data annotation on class or a method and it will automatically create getter/setter bolier plate code. 
@Data
public class Address {
    
    private String city;
    private String state;
    private String country;
}
