package com.publicsapient.publicsapient.Model;



import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

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
@Indexed
public class APIUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field  
    private String firstName;
    @Field
    private String lastName;
    private String email;
    @Field
    private String ssn;
    private int age;
    private String role;
    
    
}
