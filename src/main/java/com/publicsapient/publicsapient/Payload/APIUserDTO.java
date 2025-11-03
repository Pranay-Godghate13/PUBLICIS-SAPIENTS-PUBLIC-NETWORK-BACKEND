package com.publicsapient.publicsapient.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIUserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String ssn;
    private int age;
    private String role;
}
