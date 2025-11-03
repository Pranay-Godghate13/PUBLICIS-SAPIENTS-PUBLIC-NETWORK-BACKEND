package com.publicsapient.publicsapient.Service;

import java.util.List;

import com.publicsapient.publicsapient.Model.APIUser;


public interface UserService {
    String loadData();
    List<APIUser> getAllUsers(String keyword);
    APIUser getUserById(Long id);
    APIUser getUserByEmail(String email);
}
