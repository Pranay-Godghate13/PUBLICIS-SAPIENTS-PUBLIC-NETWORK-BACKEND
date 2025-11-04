package com.publicsapient.publicsapient.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.publicsapient.publicsapient.Model.APIUser;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
// @RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/user/loadData")
    public String loadData() {
        userServiceImpl.loadData();
        return "Data loaded in db successfull";
    }
    
    @GetMapping("/user/keyword/{keyword}")
    public List<APIUser> getUsersByKeyword(@PathVariable String keyword) {
        List<APIUser> users=userServiceImpl.findUsersByKeyword(keyword);
        return users;
    }
    
    @GetMapping("/user/id/{id}")
    public APIUser getUserById(@PathVariable Long id) {
        APIUser user=userServiceImpl.findUserById(id);
        return user;
    }
    
    // @GetMapping("/user")
    // public List<APIUser> getAllUsers() {
    //     List<APIUser> users=userServiceImpl.getAllUsers();
        
    //     return users;
    // }
    
    @GetMapping("/user/email/{email}")
    public APIUser getUserByEmail(@PathVariable String email) {
        APIUser user=userServiceImpl.findUserByEmail(email);
        return user;
    }
    

        
    
}
