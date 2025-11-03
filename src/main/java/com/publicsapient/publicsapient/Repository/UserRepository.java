package com.publicsapient.publicsapient.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.publicsapient.publicsapient.Model.APIUser;



public interface UserRepository extends JpaRepository<APIUser,Long> {

    APIUser findByEmail(String email);
    
}
