package com.publicsapient.publicsapient.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.publicsapient.publicsapient.Model.User;
import com.publicsapient.publicsapient.Service.UserService;
import com.publicsapient.publicsapient.Service.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/user/loadData")
    public String loadData() {
        
        return "Data loaded successfully";
    }

    @GetMapping("/user/{keyword}")
    public List<User> getAllUsers(@PathVariable String keyword) {
        List<User> allUsers=userServiceImpl.getAllUsers(keyword);
        return allUsers;
    }

    @GetMapping("/user/id/{id}")
    public User getUserById(@PathVariable Long id) {
        User user=userServiceImpl.getUserById(id);
        return user;
    }
    
    @GetMapping("/user/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        User user=userServiceImpl.getUserByEmail(email);
        return user;
    }
    
    
    
}
