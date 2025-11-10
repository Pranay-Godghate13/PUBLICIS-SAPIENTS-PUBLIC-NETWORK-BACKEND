package com.publicsapient.publicsapient.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import com.publicsapient.publicsapient.Model.APIUser;
import com.publicsapient.publicsapient.Payload.APIUserDTO;
import com.publicsapient.publicsapient.Payload.ResponseDTO;
import com.publicsapient.publicsapient.Service.UserServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Validated
@RestController
// @RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/user/loadData")
    public ResponseEntity<String> loadData() {
        String message=userServiceImpl.loadData();
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/user/id")
    public ResponseEntity<APIUserDTO> getUserById(@RequestParam@NotNull(message = "Id is required") @Min(value = 1,message="Id must be atleast 1") @Max(value = 30,message = "Id can be atmost 30") Long id) {
       
        APIUserDTO user=userServiceImpl.findUserById(id);
        return new ResponseEntity<APIUserDTO>(user, HttpStatus.FOUND);
    }
    
    // @GetMapping("/user")
    // public List<APIUser> getAllUsers() {
    //     List<APIUser> users=userServiceImpl.getAllUsers();
        
    //     return users;
    // }
    
    @GetMapping("/user/email")
    public ResponseEntity<APIUserDTO> getUserByEmail(@RequestParam @NotBlank(message = "Mail should not be blank") @Email(message = "Enter valid email id") String email) {
        
        APIUserDTO user=userServiceImpl.findUserByEmail(email);
        return new ResponseEntity<APIUserDTO>(user, HttpStatus.FOUND);
    }
    
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/user/keyword")
    public ResponseEntity<ResponseDTO> getUsersByKeyword(@RequestParam String keyword) {
        
            ResponseDTO users=userServiceImpl.findByKeyword(keyword);
            return new ResponseEntity<ResponseDTO>(users, HttpStatus.FOUND);
        
    }
    

    
    

   


        
    
}
