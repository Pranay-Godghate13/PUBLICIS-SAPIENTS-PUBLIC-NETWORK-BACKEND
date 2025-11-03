package com.publicsapient.publicsapient.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String ssn;
    private Integer age;
    private String role;
    
    
}
