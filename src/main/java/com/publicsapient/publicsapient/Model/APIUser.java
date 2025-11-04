package com.publicsapient.publicsapient.Model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String firstName;
    
   
    private String lastName;
    
    private String email;
    
   
    private String ssn;
    
    private int age;
    
    private String role;
    
    
}
