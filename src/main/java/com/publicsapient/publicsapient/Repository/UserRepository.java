package com.publicsapient.publicsapient.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.publicsapient.publicsapient.Model.APIUser;




public interface UserRepository extends JpaRepository<APIUser,Long> {

    APIUser findByEmail(String email);
    @Query("SELECT p from APIUser p WHERE "+
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR "+
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR "+
            "LOWER(p.ssn) LIKE LOWER(CONCAT('%', :keyword ,'%'))")
    List<APIUser> findByKeyword(String keyword);

    
    
}
