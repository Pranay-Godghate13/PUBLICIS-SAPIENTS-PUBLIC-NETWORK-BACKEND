package com.publicsapient.publicsapient.Service;

import java.util.List;

import com.publicsapient.publicsapient.Model.APIUser;


public interface UserService {
    String loadData();
    APIUser findUserById(Long id);
    APIUser findUserByEmail(String email);
    List<APIUser> findByKeyword(String keyword);

}
