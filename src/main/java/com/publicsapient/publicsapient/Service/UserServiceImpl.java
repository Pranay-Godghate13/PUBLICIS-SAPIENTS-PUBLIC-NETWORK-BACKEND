package com.publicsapient.publicsapient.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.publicsapient.publicsapient.Model.APIUser;
import com.publicsapient.publicsapient.Payload.APIUserDTO;
import com.publicsapient.publicsapient.Payload.ResponseDTO;
import com.publicsapient.publicsapient.Repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public String loadData() {
        String url="https://dummyjson.com/users";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> users = (List<Map<String, Object>>) response.getBody().get("users");
        List<APIUser> userList=users.stream().map(data->{
            APIUser user=new APIUser();
            user.setId(Long.valueOf(data.get("id").toString()));
            user.setFirstName(data.get("firstName").toString());
            user.setLastName(data.get("lastName").toString());
            user.setEmail(data.get("email").toString());
            user.setSsn(data.get("ssn").toString());
            user.setRole(data.get("role").toString());
            user.setAge((int)data.get("age"));
            return user;
        }).toList();
        userRepository.saveAll(userList);
        return "Data loaded";
    }

    @Override
    public List<APIUser> getAllUsers(String keyword) {
        List<APIUser> users=userRepository.findAll();
        return users;
    }

    @Override
    public APIUser getUserById(Long id) {
        APIUser user=userRepository.findById(id)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user;

    }

    @Override
    public APIUser getUserByEmail(String email) {
        APIUser user=userRepository.findByEmail(email);
        if(user==null)
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
    
}
}