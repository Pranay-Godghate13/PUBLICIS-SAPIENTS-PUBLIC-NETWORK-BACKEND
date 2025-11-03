package com.publicsapient.publicsapient.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIUser {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String ssn;
    private int age;
    private String role;
    
    
}
