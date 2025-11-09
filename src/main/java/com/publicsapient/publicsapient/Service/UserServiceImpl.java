package com.publicsapient.publicsapient.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.publicsapient.publicsapient.Exception.ResourceNotFoundException;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String loadData() {

        jdbcTemplate.execute("DROP TABLE IF EXISTS apiuser");
        jdbcTemplate.execute("""
            CREATE TABLE apiuser (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                first_name VARCHAR(255),
                last_name VARCHAR(255),
                email VARCHAR(255),
                ssn VARCHAR(255),
                role VARCHAR(255),
                age INT,
                gender VARCHAR(50)
            )
        """);

        String url="https://dummyjson.com/users";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> users = (List<Map<String, Object>>) response.getBody().get("users");
        List<APIUser> userList=users.stream().map(data->{
            APIUser user=new APIUser();
            // user.setId(Long.valueOf(data.get("id").toString()));
            user.setFirstName(data.get("firstName").toString());
            user.setLastName(data.get("lastName").toString());
            user.setEmail(data.get("email").toString());
            user.setSsn(data.get("ssn").toString());
            user.setRole(data.get("role").toString());
            user.setAge((int)data.get("age"));
            user.setGender(data.get("gender").toString());
            return user;
        }).toList();
        
        userRepository.saveAll(userList);
        return "Data loaded successfully";
    }

    // @Override
    // public List<APIUser> getAllUsers() {
    //     List<APIUser> users=userRepository.findAll();
    //     return users;
    // }


    @Override
    public APIUserDTO findUserById(Long id) {

        APIUser userFromDb=userRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Id", "id", id));
        APIUserDTO userDTO=modelMapper.map(userFromDb,APIUserDTO.class);
        return userDTO;
    }

    @Override
    public APIUserDTO findUserByEmail(String email) {
        APIUser userFromDb=userRepository.findByEmail(email);
        if(userFromDb==null)
        throw new ResourceNotFoundException("Email", "email", email);
        APIUserDTO userDTO=modelMapper.map(userFromDb, APIUserDTO.class);
        return userDTO;
        
    }

    @Override
    public ResponseDTO findByKeyword(String keyword) {
        List<APIUser> usersFromDb=userRepository.findByKeyword(keyword);
        if(usersFromDb==null || usersFromDb.isEmpty())
        throw new ResourceNotFoundException("keyword", "keyword", keyword);
        List<APIUserDTO> userDTO=usersFromDb.stream()
                            .map(m->modelMapper.map(m, APIUserDTO.class))
                            .toList();
        ResponseDTO responseDTO=new ResponseDTO();
        
        
        responseDTO.setContent(userDTO);
        return responseDTO;
    }
    
}