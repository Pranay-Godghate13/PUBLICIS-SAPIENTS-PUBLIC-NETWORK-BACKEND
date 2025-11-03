package com.publicsapient.publicsapient.Service;

import java.util.List;

import com.publicsapient.publicsapient.Model.User;

public interface UserService {
    String loadData();
    List<User> getAllUsers(String keyword);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
