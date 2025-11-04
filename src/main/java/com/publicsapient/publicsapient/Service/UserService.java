package com.publicsapient.publicsapient.Service;

import java.util.List;

import com.publicsapient.publicsapient.Model.APIUser;


public interface UserService {
    String loadData();
    APIUser findUserById(Long id);
    // List<APIUser> getAllUsers();
    APIUser findUserByEmail(String email);
    List<APIUser> findUsersByKeyword(String keyword);
    
}
