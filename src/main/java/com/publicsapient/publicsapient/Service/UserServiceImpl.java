package com.publicsapient.publicsapient.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.publicsapient.publicsapient.Model.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String loadData() {
        return "Data loaded";
    }

    @Override
    public List<User> getAllUsers(String keyword) {
        return new ArrayList<>();
    }

    @Override
    public User getUserById(Long id) {
        return new User();
    }

    @Override
    public User getUserByEmail(String email) {
        return new User();
    }
    
}
